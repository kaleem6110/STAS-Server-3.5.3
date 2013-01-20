package com.wfp.offline.synchronize.utils;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.enterprisehorizons.magma.server.admin.AdminConfigUtils;
import com.enterprisehorizons.magma.server.util.ServerUtils;
import com.enterprisehorizons.util.CastorUtils;
import com.enterprisehorizons.util.Logger;
import com.enterprisehorizons.util.SearchCriteria;
import com.enterprisehorizons.util.StringUtils;
import com.spacetimeinsight.db.config.model.UserPreferences;
import com.spacetimeinsight.db.model.util.DataModelsCache;
import com.spacetimeinsight.db.model.util.DataModelsCacheHelper;
import com.spacetimeinsight.db.model.util.UserPreferenecsHelper;
import com.spacetimeinsight.rubberbandregions.Rubberbandregion;
import com.spacetimeinsight.security.bean.JAASAuthenticationTypeInitializer;
import com.spacetimeinsight.security.bean.JAASConstants;
import com.wfp.db.helper.SyncDataModelsCacheHelper;
import com.wfp.db.layers.model.RiskZones;
import com.wfp.db.platform.model.MessageTemplate;
import com.wfp.db.util.PostgresSearchCriteriaDateFormatter;
import com.wfp.offline.synchronize.ISynchronizationService;
import com.wfp.offline.synchronize.ISynchronizationServiceConstants;
import com.wfp.offline.synchronize.terminal.TerminalService;
import com.wfp.synchronize.Geofencerule;
import com.wfp.synchronize.Riskzone;
import com.wfp.synchronize.db.config.model.SynchronizationTrailLog;
import com.wfp.synchronize.db.config.model.SynchronizationTrailStatusLog;
import com.wfp.utils.CommonUtils;


public class SynchronizationDBUtils implements ISynchronizationServiceConstants{
	public static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy HH:mm:ss";
	public static final SimpleDateFormat sdf = new SimpleDateFormat(
	DEFAULT_DATE_FORMAT);

  
	public static SynchronizationTrailLog syncTraceLog(long syncDataTypeId, String syncUniqueId,
			long syncTypeId, long syncStatus, String host, Long userId, String userUniqueId, Long moduleId){
		SynchronizationTrailLog trailLog = new SynchronizationTrailLog();
		SynchronizationTrailLog log  = CommonServiceDBUtils.getTraceLog(syncDataTypeId, syncUniqueId, syncTypeId, syncStatus, host, userId, userUniqueId, moduleId) ;


		if(log != null){
			return insertUpdateTraceLog(syncDataTypeId, syncUniqueId,
					syncTypeId, syncStatus, host, userId, userUniqueId,moduleId, trailLog,log.getId(),
					log.getCreatedAt(), null, log.getCreatedDate(), log.getModifiedDate());

		}else {
			return insertUpdateTraceLog(syncDataTypeId, syncUniqueId,
					syncTypeId, syncStatus, host, userId, userUniqueId,moduleId, trailLog, null,
					host, null,null, null);
		}
	}

	public static SynchronizationTrailLog insertUpdateTraceLog(long syncDataTypeId,
			String syncUniqueId, long syncTypeId, long syncStatus, String host,
			Long userId, String userUniqueId, Long moduleId,
			SynchronizationTrailLog trailLog, Long id, String createdAt, String ackId, Date createDate, Date modifiedDate) {

		if(trailLog == null){
			trailLog = new SynchronizationTrailLog();
		}
		if(id != null && id.intValue() > 0){
			trailLog.setId(id);
		}

		if(ackId != null){
			trailLog.setAckId(ackId);
		}
		trailLog.setCreatedAt(createdAt);


		trailLog.setSyncDataTypeId(syncDataTypeId);
		trailLog.setSyncUniqueId(syncUniqueId);
		trailLog.setSyncTypeId(syncTypeId);
		if(createDate == null){
			trailLog.setCreatedDate(new Date());
		}else {
			trailLog.setCreatedDate(createDate);
		}

		trailLog.setModifiedDate(new Date());
		trailLog.setStatus(syncStatus);

		trailLog.setUserId(userId);
		trailLog.setModuleId(moduleId);
		trailLog.setUserUniqueId(userUniqueId);
		if(JAASConstants.OPEN_LDAP_DATA_SOURCE.equalsIgnoreCase(JAASAuthenticationTypeInitializer.getInstance().getJAASAuthenticationType())){
			if(trailLog.getSyncDataTypeId() == SYNC_DATA_TYPE_RBREGION && (moduleId.intValue() <=0 )){
				return null;
			}
		}else {
			if(trailLog.getSyncDataTypeId() == SYNC_DATA_TYPE_RBREGION && (moduleId.intValue() <=0 || userId < 0)){
				return null;
			}
		}
		
		if(trailLog.insertOrUpdateData()){
			return trailLog;
		}

		return null;
	}



	@SuppressWarnings("unchecked")
	public static List<UserPreferences> getUserPreferences(com.wfp.synchronize.Rubberbandregion region, boolean match){
		UserPreferences prefer = new UserPreferences();

		SearchCriteria criteria = new SearchCriteria();
		if(match){
			if(JAASConstants.OPEN_LDAP_DATA_SOURCE.equalsIgnoreCase(JAASAuthenticationTypeInitializer.getInstance().getJAASAuthenticationType())){
				criteria.addCritirea(HB_PROP_USER_UNIQUE_ID, SearchCriteria.EQUALS, region.getUserUniqueId());
				if(StringUtils.isNull(region.getUserUniqueId())){
					criteria.addCritirea(HB_PROP_USER_ID, SearchCriteria.EQUALS, -2);
				}else {
					criteria.addCritirea(HB_PROP_USER_ID, SearchCriteria.EQUALS, -1);
				}
			}else {
				criteria.addCritirea(HB_PROP_USER_ID, SearchCriteria.EQUALS, CommonServiceDBUtils.getUserId(region.getUserName()));
			}
			
		}else {

			if(CommonServiceDBUtils.getUserId(region.getUserName()) > 0){ // user preference record
				//criteria.addCritirea("userId", SearchCriteria.EQUALS, 0);
			}else if(CommonServiceDBUtils.getUserId(region.getUserName()) <= 0){ // module preference record

			}
		}

		criteria.addCritirea(HB_PROP_MODULE_ID, SearchCriteria.EQUALS, (long)DataModelsCacheHelper.getModuleId(region.getModuleName()));
		criteria.addCritirea(HB_PROP_USER_UNIQUE_ID, SearchCriteria.EQUALS, region.getUserUniqueId());
		criteria.addCritirea(HB_PROP_PREFERENCE_TYPE_ID, SearchCriteria.EQUALS, UserPreferenecsHelper.PREFERENCE_ID_RBREGIONS);

		List<UserPreferences> list = prefer.searchData(criteria);
		//returns the list of userpreference object matching the region object
		return list;
	}

	/**
	 *
	 * @param region
	 * @param domainId
	 * @param languageId
	 * @return operation
	 * operation = add ==> Add Operation
	 * operation = update ==> Update Operation
	 * operation = delete ==> Delete Operation
	 * opeartion = null  ==> failure ...
	 */
	public static String crudUserPreferences(com.wfp.synchronize.Rubberbandregion region, ISynchronizationService iSyncService){
		String operation = null;
		UserPreferences prefer = null;
		//get the user preference object which matches the region object.....
		List<UserPreferences> list = getUserPreferences(region, true);
		if(list != null && list.size() > 0){
			prefer = list.get(0);
		}

		if(prefer == null){ // uufff...its a new record...let's create the user preference...object...
			prefer = new UserPreferences();
		}
		com.spacetimeinsight.rubberbandregions.Rubberbandregions regions = null;
		if(prefer.getPreferenceValue() != null){ //uuff...no preference object too ...let's create...
			 regions = (com.spacetimeinsight.rubberbandregions.Rubberbandregions) CastorUtils.unmarshalString(prefer.getPreferenceValue(), com.spacetimeinsight.rubberbandregions.Rubberbandregions.class);
		}
		if(regions == null){
			regions = new com.spacetimeinsight.rubberbandregions.Rubberbandregions();
		}

		//check if the region sync type operation is ADD / UPDATE
		if((OPERATION_UPDATE.equalsIgnoreCase(region.getSyncType() )) || (OPERATION_ADD.equalsIgnoreCase(region.getSyncType() ))){
			boolean isFound = false;
			for (int i = 0; i < regions.getRubberbandregionCount(); i++) {
				if (region.getRegionName().equals(regions.getRubberbandregion(i).getRegionName())) { /// implies that its an update operation.... on the target database
					com.spacetimeinsight.rubberbandregions.Rubberbandregion rbRegion = new com.spacetimeinsight.rubberbandregions.Rubberbandregion();
					try {
						BeanUtils.copyProperties(rbRegion, region); //copy the properties...
					} catch (IllegalAccessException e) {

						e.printStackTrace();
					} catch (InvocationTargetException e) {

						e.printStackTrace();
					}
					if(regions == null){
						regions = new com.spacetimeinsight.rubberbandregions.Rubberbandregions();
					}
					//remove the unwanted properties... plz...
					/*rbRegion.setAckid(null);
					rbRegion.setStatus(null);
					rbRegion.setSyncType(null);*/
					//set to the Rubberband Region ...
					regions.setRubberbandregion(i, rbRegion);
					isFound = true;
					region.setSyncType(OPERATION_UPDATE);
					operation = OPERATION_UPDATE;
				}
			}

			if(!isFound ){ //O...region object is not found...let's create one...now..
				com.spacetimeinsight.rubberbandregions.Rubberbandregion rbRegion = new com.spacetimeinsight.rubberbandregions.Rubberbandregion();
				try { //copy the region props
					BeanUtils.copyProperties(rbRegion, region);
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				}
				//reset the values...plz
				//rbRegion.setAckid(null);
				//rbRegion.setStatus(null);
				region.setSyncType(OPERATION_ADD);
				regions.addRubberbandregion(rbRegion);
				operation = OPERATION_ADD;
			}
		}else if(OPERATION_DELETE.equalsIgnoreCase(region.getSyncType() )){ //// uuff..its an delete operation....that need to be performed on DB
			if(regions != null){
				for (int i = 0; i < regions.getRubberbandregionCount(); i++) {
					if (region.getRegionName().equals(regions.getRubberbandregion(i).getRegionName())) {
						//removing the region object reference
						regions.removeRubberbandregion(i);
						operation = OPERATION_DELETE;
					}

				}
			}
			operation = OPERATION_DELETE;
		}else if((OPERATION_OVERWRITE.equalsIgnoreCase(region.getSyncType() ))){ //// uuff..its an overwrite operation....that need to be performed on DB
			if(regions != null){
				boolean isFound = false;
				for (int i = 0; i < regions.getRubberbandregionCount(); i++) {
					if (region.getRegionName().equals(regions.getRubberbandregion(i).getRegionName())) {
						String userName = region.getUserName();
						String moduleName = region.getModuleName();
						String userUniqueId = region.getUserUniqueId();

						try {
							BeanUtils.copyProperties(region, regions.getRubberbandregion(i)); //copy the properties...
							region.setUserName(userName);
							region.setModuleName(moduleName);
							region.setUserUniqueId(userUniqueId);
							region.setSyncType(OPERATION_ADD);
							region.setAckid(null);
							region.setStatus(STATUS_CONFLICT);
							region.setIsServerRecord(true);
						} catch (IllegalAccessException e) {

							e.printStackTrace();
						} catch (InvocationTargetException e) {

							e.printStackTrace();
						}
						if(regions == null){
							regions = new com.spacetimeinsight.rubberbandregions.Rubberbandregions();
						}
						//remove the unwanted properties... plz...
						//rbRegion.setAckid(null);
						//rbRegion.setStatus(-1);
						//rbRegion.setSyncType(-1);
						//set to the Rubberband Region ...
						//regions.setRubberbandregion(i, rbRegion);

						isFound = true;
					}
				}

				if(!isFound){
					region.setSyncType(OPERATION_DELETE);
				}

				return operation = OPERATION_OVERWRITE;
			}

		}

		String preferValue = null;
		if(regions != null){
			 preferValue = CastorUtils.marshal(regions);
		}

		prefer.setPreferenceValue(preferValue);
		prefer.setPreferenceTypeId((long)UserPreferenecsHelper.PREFERENCE_ID_RBREGIONS);
		/*if(prefer.insertOrUpdateData()){
			region.setAckid(ackid)
		}*/
		if(JAASConstants.OPEN_LDAP_DATA_SOURCE.equalsIgnoreCase(JAASAuthenticationTypeInitializer.getInstance().getJAASAuthenticationType())){
			prefer.setUserUniqueId(region.getUserUniqueId());
			prefer.setUserId(StringUtils.isNull(region.getUserUniqueId())?-2L:-1L);
		}else {
			prefer.setUserId(CommonServiceDBUtils.getUserId(region.getUserName()));
			prefer.setUserUniqueId(null);
		}
		
		
		prefer.setModuleId((long)DataModelsCacheHelper.getModuleId(region.getModuleName()));
		prefer.setDomainId(iSyncService.getDomainId());
		prefer.setLanguageId(iSyncService.getLanguageId());
		
		if(JAASConstants.OPEN_LDAP_DATA_SOURCE.equalsIgnoreCase(JAASAuthenticationTypeInitializer.getInstance().getJAASAuthenticationType())){
			if((long)DataModelsCacheHelper.getModuleId(region.getModuleName()) >0 ){
				region.setStatus(STATUS_SUCCESS);
				//update the record to DB...
				prefer.insertOrUpdateData();
				//refresh the cache ....
				DataModelsCache.getInstance().refresh(UserPreferences.class.getName());
				//System.out.println("Operation Performed: "+ operation);
				return operation;
			}
		}else {
			if((long)DataModelsCacheHelper.getModuleId(region.getModuleName()) >0 && CommonServiceDBUtils.getUserId(region.getUserName()) >= 0){
				region.setStatus(STATUS_SUCCESS);
				//update the record to DB...
				prefer.insertOrUpdateData();
				//refresh the cache ....
				DataModelsCache.getInstance().refresh(UserPreferences.class.getName());
				//System.out.println("Operation Performed: "+ operation);
				return operation;
			}
		}
		
		
		return null;
	}


	public static String crudRiskzones(Riskzone riskzone, ISynchronizationService iSyncService){
		String operation = null;
		RiskZones prefer = null;
		//get the user preference object which matches the region object.....
		prefer = CommonServiceDBUtils.getRiskzones(riskzone.getEventid());

		if(prefer == null){ // uufff...its a new record...let's create the user preference...object...
			prefer = new RiskZones();
		}


		//check if the region sync type operation is ADD / UPDATE
		if((OPERATION_UPDATE.equalsIgnoreCase(riskzone.getSynctype()  )) || (OPERATION_ADD.equalsIgnoreCase(riskzone.getSynctype()  ))){


			CommonServiceDBUtils.copyRZProperties(riskzone, prefer, true); //ZProperties(prefer, region); //copy the properties...

			prefer.setEventId(riskzone.getEventid());
			operation = OPERATION_ADD;
			riskzone.setSynctype(OPERATION_ADD);
			if(!StringUtils.isNull(prefer.getEventId())){
				operation = OPERATION_UPDATE;
				riskzone.setSynctype(OPERATION_UPDATE);
			}
			prefer.insertOrUpdateData();
		}else if(OPERATION_DELETE.equalsIgnoreCase(riskzone.getSynctype()  )){ //// uuff..its an delete operation....that need to be performed on DB
			operation = OPERATION_DELETE;
			prefer.deleteData();
			riskzone.setSynctype(OPERATION_DELETE);
		}else if(OPERATION_OVERWRITE.equalsIgnoreCase(riskzone.getSynctype()  )){ //// uuff..its an overwrite operation....that need to be performed on DB

			if(prefer != null){
				String eventId= riskzone.getEventid();
				CommonServiceDBUtils.copyRZProperties(riskzone, prefer, false); //ZProperties(prefer, region); //copy the properties...

				riskzone.setEventid(riskzone.getEventid());

				riskzone.setAckid(null);
				riskzone.setStatus(STATUS_CONFLICT);
				riskzone.setIsServerRecord(true);
				riskzone.setEventid(eventId);
				if(!StringUtils.isNull(prefer.getEventId())){
					riskzone.setSynctype(OPERATION_ADD);
					operation = OPERATION_OVERWRITE;
				}else {
					riskzone.setSynctype(OPERATION_DELETE);
					operation = OPERATION_OVERWRITE;
				}

				return operation ;


			}

		}

		return operation;
	}
	
	public static String crudGeofenceRules(Geofencerule geofenceRule, ISynchronizationService iSyncService){
		String operation = null;
		MessageTemplate messageTemplate = null;
		//get the user preference object which matches the region object.....
		messageTemplate = CommonServiceDBUtils.getGeofenceRule(geofenceRule.getName());

		if(messageTemplate == null){ // uufff...its a new record...let's create the user preference...object...
			messageTemplate = new MessageTemplate();
		}


		//check if the region sync type operation is ADD / UPDATE
		if((OPERATION_UPDATE.equalsIgnoreCase(geofenceRule.getSynctype()  )) || (OPERATION_ADD.equalsIgnoreCase(geofenceRule.getSynctype()  ))){


			//CommonServiceDBUtils.copyRZProperties(geofenceRule, messageTemplate, true); //ZProperties(prefer, region); //copy the properties...
			try {
				BeanUtils.copyProperties(messageTemplate, geofenceRule);
				messageTemplate.setEndDate(geofenceRule.getEndDateTime());
				messageTemplate.setStartDate(geofenceRule.getStartDateTime());
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//messageTemplate.setEventId(geofenceRule.getName());
			operation = OPERATION_ADD;
			geofenceRule.setSynctype(OPERATION_ADD);
			if(!StringUtils.isNull(messageTemplate.getName()) && (!StringUtils.isNull(messageTemplate.getId()) && messageTemplate.getId() > 0)){
				operation = OPERATION_UPDATE;
				geofenceRule.setSynctype(OPERATION_UPDATE);
			}
			messageTemplate.insertOrUpdateData();
		}else if(OPERATION_DELETE.equalsIgnoreCase(geofenceRule.getSynctype()  )){ //// uuff..its an delete operation....that need to be performed on DB
			operation = OPERATION_DELETE;
			messageTemplate.deleteData();
			geofenceRule.setSynctype(OPERATION_DELETE);
		}else if(OPERATION_OVERWRITE.equalsIgnoreCase(geofenceRule.getSynctype()  )){ //// uuff..its an overwrite operation....that need to be performed on DB

			if(messageTemplate != null){
				String eventId= geofenceRule.getName();
				//CommonServiceDBUtils.copyRZProperties(geofenceRule, messageTemplate, false); //ZProperties(prefer, region); //copy the properties...
				try {
					BeanUtils.copyProperties( geofenceRule, messageTemplate);
					geofenceRule.setEndDateTime(messageTemplate.getEndDate());
					geofenceRule.setStartDateTime(messageTemplate.getStartDate());
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				geofenceRule.setName(geofenceRule.getName());

				geofenceRule.setAckid(null);
				geofenceRule.setStatus(STATUS_CONFLICT);
				geofenceRule.setIsServerRecord(true);
				geofenceRule.setName(eventId);
				if(!StringUtils.isNull(messageTemplate.getName())){
					geofenceRule.setSynctype(OPERATION_ADD);
					operation = OPERATION_OVERWRITE;
				}else {
					geofenceRule.setSynctype(OPERATION_DELETE);
					operation = OPERATION_OVERWRITE;
				}

				return operation ;


			}

		}

		return operation;
	}

	public static void revalidateUserPreferences(Rubberbandregion region,
			ISynchronizationService iSyncService) {
		//if(!iSyncService.isTerminalServer()){
			//List<SynchronizationTrailLog> logs = iSyncService.getTraceLogs();
			/*long userId = CommonServiceDBUtils.getUserId(region.getUserName());
			long moduleId = DataModelsCacheHelper.getModuleId(region.getModuleName());
			
				if(userId > 0 || !StringUtils.isNull(region.getUserUniqueId())){ //Ohh..its an user preference region.....
					//should be unique for a User (implies this region name should not be allowed in module preference...)
					getConflictTraceLog( true, userId, moduleId, region, iSyncService, region.getUserUniqueId());
				}else {
					getConflictTraceLog(false, userId, moduleId,  region, iSyncService, region.getUserUniqueId());
				}*/
		//	}

	}

	private static void getConflictTraceLog(boolean needModulePrefs, long userId, long moduleId, Rubberbandregion region,
			ISynchronizationService iSyncService, String userUniqueId){
		List<SynchronizationTrailLog> logs = CommonServiceDBUtils.getTraceLogs(SYNC_STATUS_NEW, iSyncService.isTerminalServer(), iSyncService.isTerminalServer()?iSyncService.getTerminalServerName():iSyncService.getCentralServerName());
		List<SynchronizationTrailLog > list = null;
		if(logs != null) {
			list = new ArrayList<SynchronizationTrailLog>();
			for(SynchronizationTrailLog log: logs){
				if(log.getModuleId() != null && log.getModuleId()  == moduleId && log.getUserId() == 0 && StringUtils.isNull(log.getUserUniqueId()  ) && needModulePrefs){
					list.add(log) ;
				}else if(!needModulePrefs && log.getModuleId() != null && log.getModuleId() == moduleId && ( userId != log.getUserId() || !(userUniqueId !=null && userUniqueId.equalsIgnoreCase(log.getUserUniqueId()) )) ){
					list.add(log) ;
				}
			}
		}


			if(list != null){
				for(SynchronizationTrailLog temp:list){

					UserPreferences pref = SyncDataModelsCacheHelper.getSpecificUserPreferences(iSyncService.getDomainId(),iSyncService.getLanguageId() ,
							temp.getUserId(), temp.getUserUniqueId()==null?"":temp.getUserUniqueId(), temp.getModuleId(),  UserPreferenecsHelper.PREFERENCE_ID_RBREGIONS);
					//System.out.println("Pref Obj"+ pref);
					/*if(OPERATION_ADD.equalsIgnoreCase(region.getSyncType() )|| OPERATION_DELETE.equalsIgnoreCase(region.getSyncType() ) ){
						SynchronizationTrailLog updatedLog = UserPreferenecsHelper.deleteRBRegion(pref, region.getRegionName());
						//updatedLog.setAckId(region.getAckid());
						if(iSyncService.isTerminalServer()){
							updatedLog.deleteData();
							updatedLog = null;
						}
						if(updatedLog != null){
							updatedLog.setCreatedAt(iSyncService.isTerminalServer()?iSyncService.getTerminalServerName():iSyncService.getCentralServerName());
							iSyncService.addTraceLogs(updatedLog);
						}


					}else if(OPERATION_DELETE.equalsIgnoreCase(region.getSyncType() )){
						com.spacetimeinsight.rubberbandregions.Rubberbandregion tempRegion = new com.spacetimeinsight.rubberbandregions.Rubberbandregion();
						try {
							BeanUtils.copyProperties(tempRegion, region);
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(pref!= null && (pref.getUserId().intValue() == CommonServiceDBUtils.getUserId(region.getUserName()).intValue() || (!StringUtils.isNull(userUniqueId) && userUniqueId.equalsIgnoreCase(region.getUserUniqueId())))){
							SynchronizationTrailLog updatedLog = UserPreferenecsHelper.addorUpdateRBRegions(pref, tempRegion, OPERATION_ADD.equalsIgnoreCase(region.getSyncType())?true:false);
							//updatedLog.setAckId(region.getAckid());
							if(iSyncService.isTerminalServer()){
								updatedLog.deleteData();
								updatedLog = null;
							}
							if(updatedLog != null){
								updatedLog.setCreatedAt(iSyncService.isTerminalServer()?iSyncService.getTerminalServerName():iSyncService.getCentralServerName());
								iSyncService.addTraceLogs(updatedLog);
							}

						}
					}*/
					//com.spacetimeinsight.rubberbandregions.Rubberbandregions regions = (Rubberbandregions) CastorUtils.unmarshalString(pref.getPreferenceValue(), Rubberbandregions.class);

				}
			}



	}


	public  static List<SynchronizationTrailLog> getAllSynchronizationTraceLogs(java.util.Map inputCriteria){
		SynchronizationTrailLog syncTrailLog = new SynchronizationTrailLog();
		SearchCriteria criteria = new SearchCriteria();
		criteria.setSearchCriteriaFormatter(new PostgresSearchCriteriaDateFormatter());
		List<SynchronizationTrailLog> list;
		SynchronizationTrailLog item;

		Long id = inputCriteria.get("id") != null ? Long.valueOf(inputCriteria.get("id").toString()) : null;
		Long syncDataTypeId = inputCriteria.get("syncDataTypeId") != null ? Long.valueOf(inputCriteria.get("syncDataTypeId").toString()) : null;
		Long syncTypeId = inputCriteria.get("syncOperationTypeId") != null ? Long.valueOf(inputCriteria.get("syncOperationTypeId").toString()) : null;
		Long syncStatus = inputCriteria.get("syncStatusId") != null ? Long.valueOf(inputCriteria.get("syncStatusId").toString()) : null;

		String syncUniqueId = inputCriteria.get("syncUniqueId") != null ? inputCriteria.get("syncUniqueId").toString() : null;
		String createdAt = inputCriteria.get("host") != null ? inputCriteria.get("host").toString() : null;
		String createdStartDate = inputCriteria.get("createdStartDate") != null ? inputCriteria.get("createdStartDate").toString() : null;
		String createdEndDate = inputCriteria.get("createdEndDate") != null ? inputCriteria.get("createdEndDate").toString() : null;
		String modifiedStartDate = inputCriteria.get("modifiedStartDate") != null ? inputCriteria.get("modifiedStartDate").toString() : null;
		String modifiedEndDate = inputCriteria.get("modifiedEndDate") != null ? inputCriteria.get("modifiedEndDate").toString() : null;

		try{
			if(id != null){
				criteria.addCritirea(HB_PROP_ID, SearchCriteria.EQUALS, id);
				//System.out.println("id : "+id);
			}

			if(syncDataTypeId != null){
				criteria.addCritirea(HB_PROP_SYNC_DATA_TYPE_ID, SearchCriteria.EQUALS, syncDataTypeId);
				//System.out.println("syncDataTypeId : "+syncDataTypeId);
			}

			if(!StringUtils.isNull(syncUniqueId )){
				criteria.addCritirea(HB_PROP_SYNC_UNIQUE_ID, SearchCriteria.LIKE, "%"+syncUniqueId+"%");
				//System.out.println("syncUniqueId : "+syncUniqueId);
			}

			if(syncTypeId != null){
				criteria.addCritirea(HB_PROP_SYNC_TYPE_ID, SearchCriteria.EQUALS, syncTypeId);
				//System.out.println("syncTypeId : "+syncTypeId);
			}

			if(syncStatus != null){
				criteria.addCritirea(HB_PROP_STATUS, SearchCriteria.EQUALS, syncStatus);
				//System.out.println("syncStatus : "+syncStatus);
			}

			if(!StringUtils.isNull(createdAt )){
				criteria.addCritirea(HB_PROP_CREATED_AT, SearchCriteria.LIKE, "%"+createdAt+"%");
				//System.out.println("createdAt : "+createdAt);
			}

			if(!StringUtils.isNull(createdStartDate )){
				criteria.addCritirea(HB_PROP_CREATED_DATE, SearchCriteria.GREATER_THAN_EQUALS, sdf.parse(createdStartDate));
				//System.out.println("createdStartDate : "+createdStartDate);
			}

			if(!StringUtils.isNull(createdEndDate )){
				criteria.addCritirea(HB_PROP_CREATED_DATE, SearchCriteria.LESSER_THAN_EQUALS, sdf.parse(createdEndDate));
				//System.out.println("createdEndDate : "+createdEndDate);
			}

			if(!StringUtils.isNull(modifiedStartDate  )){
				criteria.addCritirea(HB_PROP_MODIFIED_DATE, SearchCriteria.GREATER_THAN_EQUALS, sdf.parse(modifiedStartDate));
				//System.out.println("modifiedStartDate : "+modifiedStartDate);
			}

			if(!StringUtils.isNull(modifiedEndDate )){
				criteria.addCritirea(HB_PROP_MODIFIED_DATE, SearchCriteria.LESSER_THAN_EQUALS, sdf.parse(modifiedEndDate));
				//System.out.println("modifiedEndDate : "+modifiedEndDate);
			}

			list  = syncTrailLog.searchData(criteria);

			if(list != null){
				for(int i=0;i<list.size();i++){
					item = list.get(i);
					
					
					item.setUserName(StringUtils.isNull(item.getUserId())?"":CommonServiceDBUtils.getUserName(item.getUserId()));
					item.setModuleName(StringUtils.isNull(item.getModuleId())?"":(CommonServiceDBUtils.getModuleName(item.getModuleId())));
					item.setStatusName(SyncDataModelsCacheHelper.getSyncStatus(item.getStatus().longValue()));
					item.setSyncDataTypeName(SyncDataModelsCacheHelper.getSyncDataType(item.getSyncDataTypeId().longValue()));
					item.setSyncTypeName(SyncDataModelsCacheHelper.getSyncOprType(item.getSyncTypeId().longValue()));
					item.setCreatedDateStr(CommonUtils.formatDate(item.getCreatedDate()));
					item.setModifiedDateStr(CommonUtils.formatDate(item.getModifiedDate()));
					list.set(i, item);
				}
			}

			return list;
		}catch(Exception ex){
			Logger.error("Error while retrieving the data", SynchronizationDBUtils.class, ex);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public static List<SynchronizationTrailLog> triggerSynchronization(){
		Logger.info("Started executing triggerSynchronization", SynchronizationDBUtils.class);
		TerminalService tService = new TerminalService();

		Map searchCriteria = new HashMap<String, String>();

		if(getLastAccessTime(tService.getTerminalServerName()) != null)
			searchCriteria.put("createdStartDate", sdf.format(getLastAccessTime(tService.getTerminalServerName())));

		searchCriteria.put("createdEndDate", sdf.format(new Date()));
		
		tService.synchronizeData();
		Logger.info("Executed Synchronization from Terminal. Getting thr logs for the same...", SynchronizationDBUtils.class);
		return getAllSynchronizationTraceLogs(searchCriteria);

	}

	@SuppressWarnings("unchecked")
	public static SynchronizationTrailLog forceSynchronization(Map record){
		String id = record.get("id") != null ? record.get("id").toString() : null;

		if(!StringUtils.isNull(id) && Integer.valueOf(id).intValue() > 0){
			TerminalService tService = new TerminalService(Integer.valueOf(id).intValue());

			Map searchCriteria = new HashMap<String, String>();
			searchCriteria.put("id", id);

			tService.synchronizeData();

			List<SynchronizationTrailLog> list = getAllSynchronizationTraceLogs(searchCriteria);

			if(list != null && list.size() > 0){
				return list.get(0);
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static SynchronizationTrailLog ignoreConflictRecord(java.util.Map record){
		SynchronizationTrailLog trailLog;
		SynchronizationTrailLog item;
		SearchCriteria criteria;

		int id = record.get("id") != null ? Integer.valueOf(record.get("id").toString()).intValue() : -1;

		try{
			trailLog = new SynchronizationTrailLog();

			//retrive the record that is to be ignored
			criteria = new SearchCriteria();
			criteria.addCritirea(HB_PROP_ID, SearchCriteria.EQUALS, id);

			List<SynchronizationTrailLog> list  = trailLog.searchData(criteria);//clear retrieve(restrictions);

			if(list != null && list.size() > 0){
				item = list.get(0);

				item.setStatus(SYNC_STATUS_IGNORED);
				item.setModifiedDate(new Date());

				if(item.insertOrUpdateData()){
					item.setUserName(StringUtils.isNull(item.getUserId())?"":CommonServiceDBUtils.getUserName(item.getUserId()));
					item.setModuleName(StringUtils.isNull(item.getModuleId())?"":(CommonServiceDBUtils.getModuleName(item.getModuleId())));
					item.setStatusName(SyncDataModelsCacheHelper.getSyncStatus(item.getStatus().longValue()));
					item.setSyncDataTypeName(SyncDataModelsCacheHelper.getSyncDataType(item.getSyncDataTypeId().longValue()));
					item.setSyncTypeName(SyncDataModelsCacheHelper.getSyncOprType(item.getSyncTypeId().longValue()));

					return item;
				}
			}
		}catch(Exception ex){
			Logger.error("Error while setting synchronization status to \"IGNOFRED\" for record with id : " + id + " ", SynchronizationDBUtils.class, ex);
		}

		return null;
	}

	public static String getLastSyncTime(String hostname) {

		if(StringUtils.isNull(hostname)){
			hostname = ServerUtils.getSystemServerName()+"_"+AdminConfigUtils.getHttpServerPort();
		}

		Date accessedAt = getLastAccessTime(hostname);
		if(accessedAt != null){
			return  sdf.format(accessedAt);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public static Date getLastAccessTime(String hostname){
		Logger.info("Getting last access time for host: ["+hostname+"]", TerminalService.class);
		SynchronizationTrailStatusLog access = new SynchronizationTrailStatusLog();


		SearchCriteria criteria = new SearchCriteria();
		criteria.addCritirea("hostname", SearchCriteria.EQUALS, hostname);

		List<SynchronizationTrailStatusLog> list = access.searchData(criteria);
		if(list != null && list.size() > 0){
			Logger.info("Last access time for host: ["+hostname+"] is ["+list.get(0).getProcessedDate()+"]", TerminalService.class);
			return  list.get(0).getProcessedDate();
		}

		return null;

	}
	
	public static boolean deleteMessageTemplate(String regionName){
		MessageTemplate mt = new MessageTemplate();
		SearchCriteria criteria = new SearchCriteria();
		criteria.addCritirea("name", SearchCriteria.EQUALS, regionName);
		
		List<MessageTemplate> mtList = mt.searchData(criteria);
		if(mtList != null && mtList.size() > 0){
			return mtList.get(0).deleteData();
		}
		
		return false;
	}

}
