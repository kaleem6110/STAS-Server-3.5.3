package com.wfp.offline.synchronize.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.criterion.Restrictions;

import com.enterprisehorizons.constants.CommonConstants;
import com.enterprisehorizons.util.CastorUtils;
import com.enterprisehorizons.util.Logger;
import com.enterprisehorizons.util.SearchCriteria;
import com.enterprisehorizons.util.StringUtils;
import com.spacetimeinsight.db.config.model.UserPreferences;
import com.spacetimeinsight.db.model.util.DataModelsCacheHelper;
import com.spacetimeinsight.db.model.util.UserPreferenecsHelper;
import com.spacetimeinsight.db.platform.model.Module;
import com.spacetimeinsight.db.security.model.User;
import com.spacetimeinsight.security.bean.JAASAuthenticationTypeInitializer;
import com.spacetimeinsight.security.bean.JAASConstants;
import com.wfp.db.helper.SyncDataModelsCacheHelper;
import com.wfp.db.layers.model.RiskZones;
import com.wfp.db.platform.model.MessageTemplate;
import com.wfp.offline.synchronize.ISynchronizationService;
import com.wfp.offline.synchronize.ISynchronizationServiceConstants;
import com.wfp.synchronize.Geofencerule;
import com.wfp.synchronize.Geofencerules;
import com.wfp.synchronize.Riskzone;
import com.wfp.synchronize.Riskzones;
import com.wfp.synchronize.Rubberbandregion;
import com.wfp.synchronize.Rubberbandregions;
import com.wfp.synchronize.Synchronize;
import com.wfp.synchronize.db.config.model.SynchronizationTrailLog;
import com.wfp.synchronize.db.config.model.SynchronizationTrailStatusLog;
import com.wfp.utils.RBRegionsUtils;

public class CommonServiceDBUtils implements ISynchronizationServiceConstants{


	/**
	 * 
	 * @param sync
	 * @param iSyncService
	 * @return
	 */ 
	public static String synchrnozeData(Synchronize  sync, ISynchronizationService iSyncService) {
		if(sync != null){// am(synchronize) available... 
			Logger.info("Start: Synchronizing Rubberband Regions / Geo-fence ", CommonServiceDBUtils.class);
			Rubberbandregions allRegions = getRubberbandRegions(sync, iSyncService);
			Logger.info("End: Synchronized Rubberband Regions / Geo-fence ", CommonServiceDBUtils.class);
			Logger.info("Start: Synchronizing Riskzones ", CommonServiceDBUtils.class);
			Riskzones zones = getRiskzones(sync, iSyncService);
			Logger.info("End: Synchronized Riskzones ", CommonServiceDBUtils.class);
			Logger.info("Start: Synchronizing Geo-fence rules", CommonServiceDBUtils.class);
			Geofencerules rules = getGeofenceRules(sync, iSyncService);
			Logger.info("End: Synchronized Geo-fence rules ", CommonServiceDBUtils.class);
			
			sync.setRubberbandregions(allRegions);
			allRegions = sync.getRubberbandregions();
			if(allRegions == null || (allRegions != null && allRegions.getRubberbandregionCount() <= 0)){
				sync.setRubberbandregions(null);
			}
			
			sync.setRiskzones(zones);
			if(zones == null || (zones != null && zones.getRiskzoneCount() <= 0)){
				sync.setRiskzones(null);
			}
			
			sync.setGeofencerules(rules);
			if(rules == null || (rules != null && rules.getGeofenceruleCount() <= 0)){
				sync.setGeofencerules(null) ; //eofencerule(null);
			}
			
			
			if(iSyncService.isTerminalServer()){
				Logger.info("Terminal Server:  Recording when it had performed the synchronization with central server... ", CommonServiceDBUtils.class);
				updateTrailStatusLog(iSyncService.getTerminalServerName(), iSyncService.getTerminalRequestTime());
			}else{
				Logger.info("Central Server : Adding Acknowledge token before serializing the data that is send as response to terminal server", CommonServiceDBUtils.class);
				sync.setProcessedTime(iSyncService.getTerminalRequestTime());
			}
			
			return CastorUtils.marshal(sync); // final xml
		}
		return null;

	}
	
	private static Rubberbandregions getRubberbandRegions(Synchronize sync,
			ISynchronizationService iSyncService) {
		Logger.info("Start: getRubberbandRegions & perform CRUD operation of data", CommonServiceDBUtils.class);
		synchronizeRubberbandRegions(sync, iSyncService);
		
		//process for any conflicts..need to be performed only on central server
		Rubberbandregions tempProcessedRegions = sync.getRubberbandregions();
		Rubberbandregions regions  = getCentralServerChanges(iSyncService);
		
		
		if(!iSyncService.isTerminalServer()){
			if(tempProcessedRegions!= null && tempProcessedRegions.getRubberbandregionCount() > 0){
				for(com.wfp.synchronize.Rubberbandregion tempRegion:tempProcessedRegions.getRubberbandregion()){
					for(int i=0 ; i< regions.getRubberbandregionCount(); i++ ) {//Rubberbandregion targetRegion:regions.getRubberbandregion()){
						
						if(tempRegion.getRegionName().equalsIgnoreCase(regions.getRubberbandregion(i).getRegionName() )&& STATUS_CONFLICT.equalsIgnoreCase(tempRegion.getStatus() )){
							regions.removeRubberbandregion(i);
							regions.addRubberbandregion(tempRegion);
						}else if(tempRegion.getRegionName().equalsIgnoreCase(regions.getRubberbandregion(i).getRegionName() )){
							if(!StringUtils.isNull(regions.getRubberbandregion(i).getAckid())){
								regions.getRubberbandregion(i).setAckid(tempRegion.getAckid());
							}
							//regions.getRubberbandregion(i).setSyncType(tempRegion.getSyncType() );
							regions.getRubberbandregion(i).setIsServerRecord(false);
						}
					}
				}
			}
		}
		Logger.info("End: getRubberbandRegions  & perform CRUD operation of data", CommonServiceDBUtils.class);
		return regions;
	}
	
	private static Riskzones getRiskzones(Synchronize sync,
			ISynchronizationService iSyncService) {
		Logger.info("Start: getRiskzones  & perform CRUD operation of data", CommonServiceDBUtils.class);
		synchronizeRiskzones(sync, iSyncService);
		Riskzones zones  = getCentralServerRZChanges(iSyncService);
		
		
		
		//process for any conflicts..need to be performed only on central server
		Riskzones tempProcessedRegions = sync.getRiskzones();
		
		
		
		if(!iSyncService.isTerminalServer()){
			if(tempProcessedRegions!= null && tempProcessedRegions.getRiskzoneCount() > 0){
				for(Riskzone tempRiskzone:tempProcessedRegions.getRiskzone()){
					for(int i=0 ; i< zones.getRiskzoneCount(); i++ ) {//Rubberbandregion targetRegion:regions.getRubberbandregion()){
						
						if(tempRiskzone.getEventid().equalsIgnoreCase(zones.getRiskzone(i).getEventid())&& STATUS_CONFLICT.equalsIgnoreCase(tempRiskzone.getStatus() )){
							zones.removeRiskzone(i);//Rubberbandregion(i);
							zones.addRiskzone(tempRiskzone);
						}else if(tempRiskzone.getEventid().equalsIgnoreCase(zones.getRiskzone(i).getEventid())){
							if(!StringUtils.isNull(zones.getRiskzone(i).getAckid())){
								zones.getRiskzone(i).setAckid(tempRiskzone.getAckid());
							}
							zones.getRiskzone(i).setIsServerRecord(false);
						}
					}
				}
			}
		}
		
		Logger.info("End: getRiskzones  & perform CRUD operation of data", CommonServiceDBUtils.class);
		return zones;
	}
	
	private static Geofencerules getGeofenceRules(Synchronize sync,
			ISynchronizationService iSyncService) {
		Logger.info("Start: getGeofenceRules  & perform CRUD operation of data", CommonServiceDBUtils.class);
		synchronizeGeofenceRules(sync, iSyncService);
		Geofencerules geofenceRules  = getCentralServerGeofenceRulesChanges(iSyncService);
		
		
		
		//process for any conflicts..need to be performed only on central server
		Geofencerules tempGeofenceRules = sync.getGeofencerules();
		
		
		
		if(!iSyncService.isTerminalServer()){
			if(tempGeofenceRules!= null && tempGeofenceRules.getGeofenceruleCount() > 0){
				for(Geofencerule tempRule:tempGeofenceRules.getGeofencerule()){
					for(int i=0 ; i< geofenceRules.getGeofenceruleCount(); i++ ) {//Rubberbandregion targetRegion:regions.getRubberbandregion()){
						
						if(tempRule.getName().equalsIgnoreCase(geofenceRules.getGeofencerule(i).getName())&& STATUS_CONFLICT.equalsIgnoreCase(tempRule.getStatus() )){
							geofenceRules.removeGeofencerule(i);//Rubberbandregion(i);
							geofenceRules.addGeofencerule(tempRule);
						}else if(tempRule.getName().equalsIgnoreCase(geofenceRules.getGeofencerule(i).getName())){
							if(!StringUtils.isNull(geofenceRules.getGeofencerule(i).getAckid())){
								geofenceRules.getGeofencerule(i).setAckid(tempRule.getAckid());
							}
							geofenceRules.getGeofencerule(i).setIsServerRecord(false);
						}
					}
				}
			}
		}
		Logger.info("End: getGeofenceRules  & perform CRUD operation of data", CommonServiceDBUtils.class);
		return geofenceRules;
	}

	public static String synchrnozeById(Synchronize  sync, ISynchronizationService iSyncService) {
		if(sync != null){// am(synchronize) available... 
		
			//Rubberbandregions regions = null;
			synchronizeRubberbandRegions(sync, iSyncService);
			synchronizeRiskzones(sync, iSyncService);
			
			Rubberbandregions regions  = sync.getRubberbandregions(); //getCentralServerChanges(iSyncService);
			Riskzones zones  = sync.getRiskzones();
				
			sync.setRubberbandregions(regions);
			if(regions == null || (regions != null && regions.getRubberbandregionCount() <= 0)){
				sync.setRubberbandregions(null);
			}
			
			sync.setRiskzones(zones);
			if(zones == null || (zones != null && zones.getRiskzoneCount() <= 0)){
				sync.setRiskzones(null);
			}
			
			if(!iSyncService.isTerminalServer()){
				sync.setProcessedTime(iSyncService.getTerminalRequestTime());
			}
			
			return CastorUtils.marshal(sync); // final xml
		}
		return null;

	}
	
	private static Riskzones getCentralServerRZChanges(
			ISynchronizationService iSyncService) {
		// TODO Auto-generated method stub
		
		Riskzones zones = null;
		if(iSyncService.getTraceLogs() != null && !iSyncService.isTerminalServer()){
			
			zones = getRiskzonesData(iSyncService.getTraceLogs() , SYNC_STATUS_NEW, iSyncService);
			if(zones == null){
				zones = new Riskzones();
				
			}
			
		}
		
		return zones;
	}
	
	private static Geofencerules getCentralServerGeofenceRulesChanges(
			ISynchronizationService iSyncService) {
		// TODO Auto-generated method stub
		
		Geofencerules rules = null;
		if(iSyncService.getTraceLogs() != null && !iSyncService.isTerminalServer()){
			
			rules = getGeofenceRulesData(iSyncService.getTraceLogs() , SYNC_STATUS_NEW, iSyncService);
			if(rules == null){
				rules = new Geofencerules();				
			}
			
		}
		
		return rules;
	}

	private static void synchronizeRiskzones(Synchronize sync,
			ISynchronizationService iSyncService) {
		Riskzones regions = sync.getRiskzones(); //seems that i received the acknowledgments & some incoming changes...
		if(regions != null){
			for (Riskzone riskzone:regions.getRiskzone()){
				//hmm...check whether the record is present with same values or not....if so, then get the record for processing
				SynchronizationTrailLog log = getTrasactionalTraceLog(riskzone, iSyncService.getLanguageId());
				
				if(sync.getTraceById() && !StringUtils.isNull(riskzone.getAckid()) ){
					log = getTraceLogByAck(riskzone.getAckid());
				}
				
				if(!iSyncService.isTerminalServer() && !sync.getTraceById()){
					if(log.getId() != null && log.getId().intValue() > 0){
						if(log.getSyncTypeId() == SYNC_TYPE_ADD && OPERATION_ADD.equalsIgnoreCase(riskzone.getSynctype() )){
							riskzone.setStatus(STATUS_CONFLICT);
						}
						
						if(log.getSyncTypeId() == SYNC_TYPE_DELETE && (OPERATION_ADD.equalsIgnoreCase(riskzone.getSynctype() )|| 
								OPERATION_UPDATE.equalsIgnoreCase(riskzone.getSynctype() ))){
							riskzone.setStatus(STATUS_CONFLICT);
						}
				
					}
				}
				
				String isSuccess = null;
				if(STATUS_CONFLICT.equalsIgnoreCase(riskzone.getStatus() ) && !sync.getTraceById() ){
					isSuccess = "Conflict";
				}else {
					//perform the CRUD operation on the target database
					if((riskzone.getIsServerRecord() && iSyncService.isTerminalServer()) || 
							(!riskzone.getIsServerRecord() && !iSyncService.isTerminalServer())||
							(!riskzone.getIsServerRecord() && iSyncService.isTerminalServer()&& iSyncService.isTraceById()) ){
						isSuccess = SynchronizationDBUtils.crudRiskzones(riskzone, iSyncService);
					}else {
						isSuccess = "Success";
					}
				}
				
				//perform the CRUD operation on the target database
				//String isSuccess = SynchronizationDBUtils.crudRiskzones(region, iSyncService);
				//operation status....
				if(log != null){
					//set some default status as new state...
					log.setStatus((long)SYNC_STATUS_NEW);
					log.setCreatedAt(iSyncService.getTerminalServerName()); //indicate where the sync type status is new... host name
					if(!StringUtils.isNull(isSuccess )&& !iSyncService.isTerminalServer()){ //Woww.its an CRUD operation at Central Server...
						//log.setModifiedDate(terminalRequestTime);					
						//set the ack id as terminal server name concate with current time in millisec
						if(!STATUS_CONFLICT.equalsIgnoreCase(riskzone.getStatus() )){
							riskzone.setAckid(sync.getHostname()+CommonConstants.UNDER_SCORE_STR+iSyncService.getTerminalRequestTime().getTime());
							log.setSyncTypeId((long)SyncDataModelsCacheHelper.getSyncOprTypeId(riskzone.getSynctype()));
						}
						
					}else if (!StringUtils.isNull(isSuccess ) && iSyncService.isTerminalServer()){ //Ohh...its an CRUD operation at Terminal Server...
						//update the status ...to success.. 
						if(STATUS_CONFLICT.equalsIgnoreCase(riskzone.getStatus() ) && !iSyncService.isTraceById()){
							log.setStatus(SYNC_STATUS_CONFLICT);
						}else {
							if(iSyncService.isTraceById()){
								log.setSyncTypeId((long)SyncDataModelsCacheHelper.getSyncOprTypeId(riskzone.getSynctype()));
							}
							
							log.setStatus( (long)SYNC_STATUS_SUCCESS);
						}
						
						//set the ack id received from the central server & close the record...
						log.setAckId(riskzone.getAckid());
					}else { // Oops..there is some conflict... :(
						log.setStatus( (long)SYNC_STATUS_FAILED);
						log.setAckId(riskzone.getAckid());
					}
					
					if(iSyncService.isTraceById() && !iSyncService.isTerminalServer()){
						riskzone.setAckid(log.getAckId());
						updateConflictRecord(log.getAckId(), SYNC_STATUS_PROCESS);
					}else if(!iSyncService.isTerminalServer() && STATUS_CONFLICT.equalsIgnoreCase(riskzone.getStatus() )){
						riskzone.setAckid(sync.getHostname()+CommonConstants.UNDER_SCORE_STR+iSyncService.getTerminalRequestTime().getTime());
						if(!StringUtils.isNull(riskzone.getAckid())){
							SynchronizationDBUtils.insertUpdateTraceLog(SYNC_DATA_TYPE_RISKZONES,
									riskzone.getEventid(), log.getSyncTypeId(),
									SYNC_STATUS_CONFLICT, log.getCreatedAt(), log.getUserId(),
									log.getUserUniqueId(),log.getModuleId(), null, null, log.getCreatedAt(),
									riskzone.getAckid(),log.getCreatedDate(), log.getModifiedDate());
						}
					}else {
						if(log.getCreatedDate() == null){
							log.setCreatedDate(iSyncService.getTerminalRequestTime());
						}
						log.setModifiedDate(iSyncService.getTerminalRequestTime());
						log.insertOrUpdateData();
						
						if(!iSyncService.isTerminalServer()){
							if(!StringUtils.isNull(riskzone.getAckid())){
								SynchronizationDBUtils.insertUpdateTraceLog(SYNC_DATA_TYPE_RISKZONES,
										riskzone.getEventid(), log.getSyncTypeId(),
										SYNC_STATUS_SUCCESS, log.getCreatedAt(), log.getUserId(),
										log.getUserUniqueId(),log.getModuleId(), null, null, log.getCreatedAt(),
										riskzone.getAckid(),log.getCreatedDate(), log.getModifiedDate());
							}
							
							
						}
					}
					
					
					
					// now re-validating the previously cached trace logs to check for any changes....
					if(!STATUS_CONFLICT.equalsIgnoreCase(riskzone.getStatus() )&& !iSyncService.isTerminalServer()){
						revalidateCachedTraceLogs(iSyncService.getTraceLogs(), log);
					}
				}
				
				
				long synctype = 0;
				if(!StringUtils.isNull(isSuccess ) && OPERATION_ADD.equalsIgnoreCase(isSuccess)){
					synctype =SYNC_TYPE_ADD;
				}else if(!StringUtils.isNull(isSuccess ) && OPERATION_UPDATE.equalsIgnoreCase(isSuccess)){
					synctype =SYNC_TYPE_UPDATE;
				}else if(!StringUtils.isNull(isSuccess ) && OPERATION_DELETE.equalsIgnoreCase(isSuccess)){
					synctype =SYNC_TYPE_DELETE;
				}else if(!StringUtils.isNull(isSuccess ) && OPERATION_OVERWRITE.equalsIgnoreCase(isSuccess)){
					synctype = (long)SyncDataModelsCacheHelper.getSyncOprTypeId(riskzone.getSynctype());
				}else if(StringUtils.isNull(isSuccess )){
					synctype = SYNC_STATUS_FAILED;
				}else {
					isSuccess = null;
				}
				
				//if its not the terminal server then peform the operation but don't log the events...
				//if(!iSyncService.isTerminalServer()){
				if(!StringUtils.isNull(isSuccess ) && !iSyncService.isTerminalServer()){ 
					if(!iSyncService.isTraceById()){
						SynchronizationTrailLog newTraceLog =SynchronizationDBUtils.syncTraceLog(SYNC_DATA_TYPE_RISKZONES,
								riskzone.getEventid(), synctype,
								SYNC_STATUS_NEW, iSyncService.isTerminalServer() ?iSyncService.getTerminalServerName():iSyncService.getCentralServerName(), null,
								null,null);
						
						iSyncService.addTraceLogs(newTraceLog);
						iSyncService.setTerminalRequestTime(newTraceLog.getModifiedDate());
					}
					
				}
					
				//}
				//this step is use to revalidate some of the special cases in rubber band region .... 
				// 1) Rubberband region name is unique for module...& User preferences. ... need to validate in the database...
				//SynchronizationDBUtils.revalidateUserPreferences(region, iSyncService);
				
			}
		}
		
	}
	
	private static void synchronizeGeofenceRules(Synchronize sync,
			ISynchronizationService iSyncService) {
		Geofencerules geofenceRules = sync.getGeofencerules(); //seems that i received the acknowledgments & some incoming changes...
		if(geofenceRules != null){
			for (Geofencerule geofenceRule:geofenceRules.getGeofencerule()){
				//hmm...check whether the record is present with same values or not....if so, then get the record for processing
				SynchronizationTrailLog log = getTrasactionalTraceLog(geofenceRule, iSyncService.getLanguageId());
				
				if(sync.getTraceById() && !StringUtils.isNull(geofenceRule.getAckid()) ){
					log = getTraceLogByAck(geofenceRule.getAckid());
				}
				
				if(!iSyncService.isTerminalServer() && !sync.getTraceById()){
					if(log.getId() != null && log.getId().intValue() > 0){
						if(log.getSyncTypeId() == SYNC_TYPE_ADD && OPERATION_ADD.equalsIgnoreCase(geofenceRule.getSynctype() )){
							geofenceRule.setStatus(STATUS_CONFLICT);
						}
						
						if(log.getSyncTypeId() == SYNC_TYPE_DELETE && (OPERATION_ADD.equalsIgnoreCase(geofenceRule.getSynctype() )|| 
								OPERATION_UPDATE.equalsIgnoreCase(geofenceRule.getSynctype() ))){
							geofenceRule.setStatus(STATUS_CONFLICT);
						}
				
					}
				}
				
				String isSuccess = null;
				if(STATUS_CONFLICT.equalsIgnoreCase(geofenceRule.getStatus() ) && !sync.getTraceById() ){
					isSuccess = "Conflict";
				}else {
					//perform the CRUD operation on the target database
					if((geofenceRule.getIsServerRecord() && iSyncService.isTerminalServer()) || 
							(!geofenceRule.getIsServerRecord() && !iSyncService.isTerminalServer())||
							(!geofenceRule.getIsServerRecord() && iSyncService.isTerminalServer()&& iSyncService.isTraceById()) ){
						isSuccess = SynchronizationDBUtils.crudGeofenceRules(geofenceRule, iSyncService);
					}else {
						isSuccess = "Success";
					}
				}
				
				//perform the CRUD operation on the target database
				//String isSuccess = SynchronizationDBUtils.crudRiskzones(region, iSyncService);
				//operation status....
				if(log != null){
					//set some default status as new state...
					log.setStatus((long)SYNC_STATUS_NEW);
					log.setCreatedAt(iSyncService.getTerminalServerName()); //indicate where the sync type status is new... host name
					if(!StringUtils.isNull(isSuccess )&& !iSyncService.isTerminalServer()){ //Woww.its an CRUD operation at Central Server...
						//log.setModifiedDate(terminalRequestTime);					
						//set the ack id as terminal server name concate with current time in millisec
						if(!STATUS_CONFLICT.equalsIgnoreCase(geofenceRule.getStatus() )){
							geofenceRule.setAckid(sync.getHostname()+CommonConstants.UNDER_SCORE_STR+iSyncService.getTerminalRequestTime().getTime());
							log.setSyncTypeId((long)SyncDataModelsCacheHelper.getSyncOprTypeId(geofenceRule.getSynctype()));
						}
						
					}else if (!StringUtils.isNull(isSuccess ) && iSyncService.isTerminalServer()){ //Ohh...its an CRUD operation at Terminal Server...
						//update the status ...to success.. 
						if(STATUS_CONFLICT.equalsIgnoreCase(geofenceRule.getStatus() ) && !iSyncService.isTraceById()){
							log.setStatus(SYNC_STATUS_CONFLICT);
						}else {
							if(iSyncService.isTraceById()){
								log.setSyncTypeId((long)SyncDataModelsCacheHelper.getSyncOprTypeId(geofenceRule.getSynctype()));
							}
							
							log.setStatus( (long)SYNC_STATUS_SUCCESS);
						}
						
						//set the ack id received from the central server & close the record...
						log.setAckId(geofenceRule.getAckid());
					}else { // Oops..there is some conflict... :(
						log.setStatus( (long)SYNC_STATUS_FAILED);
						log.setAckId(geofenceRule.getAckid());
					}
					
					if(iSyncService.isTraceById() && !iSyncService.isTerminalServer()){
						geofenceRule.setAckid(log.getAckId());
						updateConflictRecord(log.getAckId(), SYNC_STATUS_PROCESS);
					}else if(!iSyncService.isTerminalServer() && STATUS_CONFLICT.equalsIgnoreCase(geofenceRule.getStatus() )){
						geofenceRule.setAckid(sync.getHostname()+CommonConstants.UNDER_SCORE_STR+iSyncService.getTerminalRequestTime().getTime());
						if(!StringUtils.isNull(geofenceRule.getAckid())){
							SynchronizationDBUtils.insertUpdateTraceLog(SYNC_DATA_TYPE_GEOFENCE_RULES,
									geofenceRule.getName(), log.getSyncTypeId(),
									SYNC_STATUS_CONFLICT, log.getCreatedAt(), log.getUserId(),
									log.getUserUniqueId(),log.getModuleId(), null, null, log.getCreatedAt(),
									geofenceRule.getAckid(),log.getCreatedDate(), log.getModifiedDate());
						}
					}else {
						if(log.getCreatedDate() == null){
							log.setCreatedDate(iSyncService.getTerminalRequestTime());
						}
						log.setModifiedDate(iSyncService.getTerminalRequestTime());
						log.insertOrUpdateData();
						
						if(!iSyncService.isTerminalServer()){
							if(!StringUtils.isNull(geofenceRule.getAckid())){
								SynchronizationDBUtils.insertUpdateTraceLog(SYNC_DATA_TYPE_GEOFENCE_RULES,
										geofenceRule.getName(), log.getSyncTypeId(),
										SYNC_STATUS_SUCCESS, log.getCreatedAt(), log.getUserId(),
										log.getUserUniqueId(),log.getModuleId(), null, null, log.getCreatedAt(),
										geofenceRule.getAckid(),log.getCreatedDate(), log.getModifiedDate());
							}
							
							
						}
					}
					
					
					
					// now re-validating the previously cached trace logs to check for any changes....
					if(!STATUS_CONFLICT.equalsIgnoreCase(geofenceRule.getStatus() )&& !iSyncService.isTerminalServer()){
						revalidateCachedTraceLogs(iSyncService.getTraceLogs(), log);
					}
				}
				
				
				long synctype = 0;
				if(!StringUtils.isNull(isSuccess ) && OPERATION_ADD.equalsIgnoreCase(isSuccess)){
					synctype =SYNC_TYPE_ADD;
				}else if(!StringUtils.isNull(isSuccess ) && OPERATION_UPDATE.equalsIgnoreCase(isSuccess)){
					synctype =SYNC_TYPE_UPDATE;
				}else if(!StringUtils.isNull(isSuccess ) && OPERATION_DELETE.equalsIgnoreCase(isSuccess)){
					synctype =SYNC_TYPE_DELETE;
				}else if(!StringUtils.isNull(isSuccess ) && OPERATION_OVERWRITE.equalsIgnoreCase(isSuccess)){
					synctype = (long)SyncDataModelsCacheHelper.getSyncOprTypeId(geofenceRule.getSynctype());
				}else if(StringUtils.isNull(isSuccess )){
					synctype = SYNC_STATUS_FAILED;
				}else {
					isSuccess = null;
				}
				
				//if its not the terminal server then peform the operation but don't log the events...
				//if(!iSyncService.isTerminalServer()){
				if(!StringUtils.isNull(isSuccess ) && !iSyncService.isTerminalServer()){ 
					if(!iSyncService.isTraceById()){
						SynchronizationTrailLog newTraceLog =SynchronizationDBUtils.syncTraceLog(SYNC_DATA_TYPE_GEOFENCE_RULES,
								geofenceRule.getName(), synctype,
								SYNC_STATUS_NEW, iSyncService.isTerminalServer() ?iSyncService.getTerminalServerName():iSyncService.getCentralServerName(), null,
								null,null);
						
						iSyncService.addTraceLogs(newTraceLog);
						iSyncService.setTerminalRequestTime(newTraceLog.getModifiedDate());
					}
					
				}
					
				//}
				//this step is use to revalidate some of the special cases in rubber band region .... 
				// 1) Rubberband region name is unique for module...& User preferences. ... need to validate in the database...
				//SynchronizationDBUtils.revalidateUserPreferences(region, iSyncService);
				
			}
		}
		
	}

	private static Rubberbandregions getCentralServerChanges(ISynchronizationService iSyncService) {	
		// TODO Auto-generated method stub
		Rubberbandregions regions = null;
		Riskzones zones = null;
		if(iSyncService.getTraceLogs() != null && !iSyncService.isTerminalServer()){
			
			regions = getRBRegionsData(iSyncService.getTraceLogs() , SYNC_STATUS_SUCCESS, iSyncService);
			if(regions == null){
				regions = new Rubberbandregions();
				
			}
			
		}
		
		return regions;
	}

	private static Rubberbandregions synchronizeRubberbandRegions(Synchronize sync, ISynchronizationService iSyncService){
		Rubberbandregions regions = sync.getRubberbandregions(); //seems that i received the acknowledgments & some incoming changes...
		if(regions != null){
			for (com.wfp.synchronize.Rubberbandregion region:regions.getRubberbandregion()){
				//hmm...check whether the record is present with same values or not....if so, then get the record for processing
				SynchronizationTrailLog log = getTrasactionalTraceLog(region, iSyncService.getLanguageId());
				
				if(sync.getTraceById() && !StringUtils.isNull(region.getAckid()) ){
					log = getTraceLogByAck(region.getAckid());
				}
				// First check
				if(!iSyncService.isTerminalServer() && !sync.getTraceById()){
					if(log.getId() != null && log.getId().intValue() > 0){
						if(log.getSyncTypeId() == SYNC_TYPE_ADD && OPERATION_ADD.equalsIgnoreCase(region.getSyncType() )){
							region.setStatus(STATUS_CONFLICT);
						}
						
						if(log.getSyncTypeId() == SYNC_TYPE_DELETE && (OPERATION_ADD.equalsIgnoreCase(region.getSyncType() ) || 
								OPERATION_UPDATE.equalsIgnoreCase(region.getSyncType() ))){
							region.setStatus(STATUS_CONFLICT);
						}
					
					}
				}
				
				String isSuccess = null;
				if(STATUS_CONFLICT.equalsIgnoreCase(region.getStatus() ) && !sync.getTraceById() ){
					isSuccess = "Conflict";
				}else {
					//perform the CRUD operation on the target database
					if((region.getIsServerRecord() && iSyncService.isTerminalServer()) || 
							(!region.getIsServerRecord() && !iSyncService.isTerminalServer())||
							(!region.getIsServerRecord() && iSyncService.isTerminalServer()&& iSyncService.isTraceById()) ){
						isSuccess = SynchronizationDBUtils.crudUserPreferences(region, iSyncService);
					}else {
						isSuccess = "Success";
					}
				}
				
				long synctype = 0;
				if(!StringUtils.isNull(isSuccess ) && OPERATION_ADD.equalsIgnoreCase(isSuccess)){
					synctype =SYNC_TYPE_ADD;
				}else if(!StringUtils.isNull(isSuccess ) && OPERATION_UPDATE.equalsIgnoreCase(isSuccess)){
					synctype =SYNC_TYPE_UPDATE;
				}else if(!StringUtils.isNull(isSuccess ) && OPERATION_DELETE.equalsIgnoreCase(isSuccess)){
					synctype =SYNC_TYPE_DELETE;
				}else if(!StringUtils.isNull(isSuccess ) && OPERATION_OVERWRITE.equalsIgnoreCase(isSuccess)){
					synctype =(long) SyncDataModelsCacheHelper.getSyncOprTypeId(region.getSyncType());
				}else if(StringUtils.isNull(isSuccess )){
					synctype = SYNC_STATUS_FAILED;
				}
				
				//operation status....
				if(log != null){
					//set some default status as new state...
					log.setStatus((long)SYNC_STATUS_NEW);
					log.setCreatedAt(iSyncService.getTerminalServerName()); //indicate where the sync type status is new... host name
					if(!StringUtils.isNull(isSuccess )&& !iSyncService.isTerminalServer()){ //Woww.its an CRUD operation at Central Server...
						//log.setModifiedDate(terminalRequestTime);					
						//set the ack id as terminal server name concate with current time in millisec
						if(!STATUS_CONFLICT.equalsIgnoreCase(region.getStatus() )){
							region.setAckid(sync.getHostname()+CommonConstants.UNDER_SCORE_STR+iSyncService.getTerminalRequestTime().getTime());
							log.setSyncTypeId((long) SyncDataModelsCacheHelper.getSyncOprTypeId(region.getSyncType()));
						}
						
					}else if (!StringUtils.isNull(isSuccess ) && iSyncService.isTerminalServer()){ //Ohh...its an CRUD operation at Terminal Server...
						//update the status ...to success.. 
						if(STATUS_CONFLICT.equalsIgnoreCase(region.getStatus() )){
							log.setStatus(SYNC_STATUS_CONFLICT);
						}else {
							if(iSyncService.isTraceById()){
								log.setSyncTypeId((long) SyncDataModelsCacheHelper.getSyncOprTypeId(region.getSyncType()));
							}else if(synctype != 0){
								log.setSyncTypeId(synctype);
							}
							
							log.setStatus( (long)SYNC_STATUS_SUCCESS);
						}
						
						//set the ack id received from the central server & close the record...
						log.setAckId(region.getAckid());
					}else { // Oops..there is some conflict... :(
						log.setStatus( (long)SYNC_STATUS_FAILED);
						log.setAckId(region.getAckid());
					}
					if(iSyncService.isTraceById() && !iSyncService.isTerminalServer()){
						region.setAckid(log.getAckId());
						updateConflictRecord(log.getAckId(), SYNC_STATUS_PROCESS);
					}else if(!iSyncService.isTerminalServer() && STATUS_CONFLICT.equalsIgnoreCase(region.getStatus() )){
						region.setAckid(sync.getHostname()+CommonConstants.UNDER_SCORE_STR+iSyncService.getTerminalRequestTime().getTime());
						if(!StringUtils.isNull(region.getAckid())){
							SynchronizationDBUtils.insertUpdateTraceLog(SYNC_DATA_TYPE_RBREGION,
									region.getRegionName(), log.getSyncTypeId(),
									SYNC_STATUS_CONFLICT, log.getCreatedAt(), log.getUserId(),
									log.getUserUniqueId(),log.getModuleId(), null, null, log.getCreatedAt(),
									region.getAckid(),log.getCreatedDate(), log.getModifiedDate());
						}
					}else {
						if(log.getCreatedDate() == null){
							log.setCreatedDate(iSyncService.getTerminalRequestTime());
						}
						
						log.setModifiedDate(iSyncService.getTerminalRequestTime());
						
						log.insertOrUpdateData();
						
						if(!iSyncService.isTerminalServer()){
							if(!StringUtils.isNull(region.getAckid())){
								SynchronizationDBUtils.insertUpdateTraceLog(SYNC_DATA_TYPE_RBREGION,
										region.getRegionName(), log.getSyncTypeId(),
										SYNC_STATUS_SUCCESS, log.getCreatedAt(), log.getUserId(),
										log.getUserUniqueId(),log.getModuleId(), null, null, log.getCreatedAt(),
										region.getAckid(),log.getCreatedDate(), log.getModifiedDate());
							}
							
							
						}
					}
					
					
					// now re-validating the previously cached trace logs to check for any changes....
					if(!STATUS_CONFLICT.equalsIgnoreCase(region.getStatus() ) && !iSyncService.isTerminalServer()){
						revalidateCachedTraceLogs(iSyncService.getTraceLogs(), log);
					}
					
				}
				
				if(synctype == 0) {
					isSuccess = null;
				}
				
				
				
				//if its not the terminal server then peform the operation but don't log the events...
				//if(!iSyncService.isTerminalServer()){
				if(!StringUtils.isNull(isSuccess ) && !iSyncService.isTerminalServer()){ 
					if(!iSyncService.isTraceById()){
						SynchronizationTrailLog newTraceLog =SynchronizationDBUtils.syncTraceLog(SYNC_DATA_TYPE_RBREGION,
								region.getRegionName(), synctype,
								SYNC_STATUS_NEW, iSyncService.isTerminalServer() ?iSyncService.getTerminalServerName():iSyncService.getCentralServerName(), getUserId(region.getUserName()),
								region.getUserUniqueId(),(long) DataModelsCacheHelper.getModuleId(region.getModuleName()));
						
						iSyncService.addTraceLogs(newTraceLog);
						iSyncService.setTerminalRequestTime(newTraceLog.getModifiedDate());
					}
					
				}
					
				//}
				//this step is use to revalidate some of the special cases in rubber band region .... 
				// 1) Rubberband region name is unique for module...& User preferences. ... need to validate in the database...
				//SynchronizationDBUtils.revalidateUserPreferences(region, iSyncService);
				
			}
		}
		
		return regions;
	}
	
	public static void updateTrailStatusLog(String hostname, Date terminalRequestTime){
		SynchronizationTrailStatusLog trailLog = new SynchronizationTrailStatusLog();
		SearchCriteria criteria = new SearchCriteria();
		criteria.addCritirea(HB_PROP_HOSTNAME, SearchCriteria.EQUALS, hostname);
		
		
		List<SynchronizationTrailStatusLog> list = trailLog.searchData(criteria);
		
		trailLog = ((list != null && list.size() > 0)?list.get(0):null);
		
		if(trailLog == null){
			trailLog = new SynchronizationTrailStatusLog();
			
		}
		if(trailLog.getId() == null){
			trailLog.setCreatedDate(terminalRequestTime);
		}
		
		trailLog.setModifiedDate(terminalRequestTime);
		trailLog.setProcessedDate(terminalRequestTime);			
		trailLog.setHostname(hostname);
		trailLog.insertOrUpdateData();
		return;		
	}
	
	/**
	 * 
	 * @param allTraceLogs
	 * @param syncStatusProcess
	 * @param iSyncService
	 * @return
	 */
	public static Riskzones getRiskzonesData(List<SynchronizationTrailLog> allTraceLogs, long syncStatusProcess, ISynchronizationService iSyncService) {
		Riskzones regions  = null;
		if(allTraceLogs != null){ // check for logs availability
			regions  = new Riskzones();
			for (SynchronizationTrailLog log:allTraceLogs){
				Riskzone riskzone = new Riskzone();;
				
				if(log!= null && log.getSyncDataTypeId() == SYNC_DATA_TYPE_RISKZONES){//if the event is on RB Region then...					
					RiskZones zone =  null; 
					if(log.getSyncTypeId() == SYNC_TYPE_ADD || log.getSyncTypeId() == SYNC_TYPE_UPDATE){
						zone  = getRiskzones(log.getSyncUniqueId());
						copyRZProperties(riskzone, zone, false);
					}
					
					riskzone.setSynctype(SyncDataModelsCacheHelper.getSyncOprType(log.getSyncTypeId()));
					riskzone.setEventid(log.getSyncUniqueId());
					
					if(riskzone != null){
						//update the status of the region from new status to process status...
						changeTraceLogStatus(syncStatusProcess, log, iSyncService.isTerminalServer(), iSyncService.getTerminalRequestTime());
						if(!iSyncService.isTerminalServer()){
							riskzone.setAckid(iSyncService.getCentralServerName()+CommonConstants.UNDER_SCORE_STR+iSyncService.getTerminalRequestTime().getTime());
						}
						if(iSyncService.isTraceById()){
							riskzone.setSynctype(OPERATION_OVERWRITE);
							riskzone.setAckid(log.getAckId());
						}
						
						riskzone.setIsServerRecord(!iSyncService.isTerminalServer());
						regions.addRiskzone(riskzone); //adding the region to the regions array
					}
				}
			}
		}
		
		return regions;
		
	}
	
	
	public static Rubberbandregions getRBRegionsData(List<SynchronizationTrailLog> allTraceLogs, long syncStatusProcess, ISynchronizationService iSyncService) {
		Rubberbandregions regions  = null;
		if(allTraceLogs != null){ // check for logs availability
			regions  = new Rubberbandregions();
			for (SynchronizationTrailLog log:allTraceLogs){
				com.wfp.synchronize.Rubberbandregion region = null;
				
				if(log!= null && log.getSyncDataTypeId() == SYNC_DATA_TYPE_RBREGION){//if the event is on RB Region then...					
					region = getRBRegion(log, iSyncService.getDomainId(), iSyncService.getLanguageId());
					if(region != null){
						//update the status of the region from new status to process status...
						changeTraceLogStatus(syncStatusProcess, log, iSyncService.isTerminalServer(), iSyncService.getTerminalRequestTime());
						if(!iSyncService.isTerminalServer()){
							region.setAckid(iSyncService.getCentralServerName()+CommonConstants.UNDER_SCORE_STR+iSyncService.getTerminalRequestTime().getTime());
						}
						if(iSyncService.isTraceById()){
							region.setAckid(log.getAckId());
							region.setSyncType(OPERATION_OVERWRITE);
						}
						
						region.setIsServerRecord(!iSyncService.isTerminalServer());
						regions.addRubberbandregion(region); //adding the region to the regions array
					}
				}
			}
		}
		
		return regions;
		
	}
	
	/**
	 * 
	 * @param i
	 * @param log
	 * @param isTerminalServer
	 */
	private static void changeTraceLogStatus(long i, SynchronizationTrailLog log, boolean isTerminalServer, Date terminalRequestTime) {
		// TODO Auto-generated method stub
		if(isTerminalServer)
			log.setStatus( i);
		
		//log.setModifiedDate(terminalRequestTime);
		log.insertOrUpdateData();
	}
	
	/**
	 * 
	 * @param log
	 * @param domainId
	 * @param languageId
	 * @return
	 */
	public static com.wfp.synchronize.Rubberbandregion getRBRegion(SynchronizationTrailLog log, long domainId, long languageId) {
		
		if(log.getSyncTypeId() != SYNC_TYPE_DELETE){//if the log event on RB Region is update /add operation then...
			
			//retrieve all the regions for that user & module
			List<UserPreferences> preferencesList = null; 
			
			if(JAASConstants.OPEN_LDAP_DATA_SOURCE.equalsIgnoreCase(JAASAuthenticationTypeInitializer.getInstance().getJAASAuthenticationType())){
				/*if(StringUtils.isNull(log.getUserUniqueId())){
					preferencesList = Arrays.asList(RBRegionsUtils.getUserModulePreferences(domainId, 
							languageId, -2,log.getUserUniqueId(), log.getModuleId().intValue(), UserPreferenecsHelper.PREFERENCE_ID_RBREGIONS) ) ;
				}else {
					preferencesList =Arrays.asList(RBRegionsUtils.getUserModulePreferences(domainId, 
							languageId, -1L, log.getModuleId().toString(), UserPreferenecsHelper.PREFERENCE_ID_RBREGIONS) ) ;
				}*/
			}/*else {
				preferencesList = Arrays.asList(RBRegionsUtils.getUserModulePreferences(domainId, 
						languageId, log.getUserId(),log.getUserUniqueId(), log.getModuleId().intValue(), UserPreferenecsHelper.PREFERENCE_ID_RBREGIONS) ) ;
			}*/
			
			if(preferencesList != null){ //if there are some regions then ...
				
				for(UserPreferences prefer:preferencesList){
					if(prefer != null){
						com.spacetimeinsight.rubberbandregions.Rubberbandregions regions = (com.spacetimeinsight.rubberbandregions.Rubberbandregions) CastorUtils.unmarshalString(prefer.getPreferenceValue(),com.spacetimeinsight.rubberbandregions.Rubberbandregions.class);
						if(regions != null){
							for (com.spacetimeinsight.rubberbandregions.Rubberbandregion region:regions.getRubberbandregion()){
								if(region.getRegionName().equalsIgnoreCase(log.getSyncUniqueId())){ //checking whether the region with same name is present/.....
									Rubberbandregion rbRegion = new Rubberbandregion();
									try {
										BeanUtils.copyProperties(rbRegion, region); //copy the properties...
										rbRegion.setUserName(getUserName(log.getUserId()));
										rbRegion.setUserUniqueId(log.getUserUniqueId());
										rbRegion.setModuleName(getModuleName(log.getModuleId()));
										rbRegion.setStatus(STATUS_NEW);
										rbRegion.setSyncType(SyncDataModelsCacheHelper.getSyncOprType(log.getSyncTypeId()));
										
										return rbRegion; //return the region
									} catch (IllegalAccessException e) {
										
										e.printStackTrace();
									} catch (InvocationTargetException e) {
										
										e.printStackTrace();
									}
									
								}
							}
						}
					}
					
				}
			}
		}else if(log.getSyncTypeId() == SYNC_TYPE_DELETE) {
			Rubberbandregion rbRegion = new Rubberbandregion();
			
			rbRegion.setRegionName(log.getSyncUniqueId());
			rbRegion.setUserName(getUserName(log.getUserId()));
			rbRegion.setUserUniqueId(log.getUserUniqueId());
			rbRegion.setModuleName(getModuleName(log.getModuleId()));
			rbRegion.setStatus(STATUS_NEW);
			rbRegion.setSyncType(SyncDataModelsCacheHelper.getSyncOprType(log.getSyncTypeId()));
			
			return rbRegion;
	}
		
		return null;
	}
	
	public static Riskzone getRiskzone(SynchronizationTrailLog log, long domainId, long languageId) {
		
		if(log.getSyncTypeId() != SYNC_TYPE_DELETE){//if the log event on RB Region is update /add operation then...
			//retrieve all the regions for that user & module
			RiskZones rZonesModel = getRiskzones(log.getSyncUniqueId());
			if(rZonesModel != null){ //if there are some regions then ...
	
				Riskzone rZone= new Riskzone();
				copyRZProperties(rZone,rZonesModel, true);//Properties(rZone, rZonesModel); //copy the properties...
				rZone.setStatus(STATUS_NEW);
				rZone.setSynctype(SyncDataModelsCacheHelper.getSyncOprType(log.getSyncTypeId()));
				
				return rZone; //return the region
			}
		}else if(log.getSyncTypeId() == SYNC_TYPE_DELETE) {
			Riskzone rZone= new Riskzone();
			
			rZone.setEventid(log.getSyncUniqueId());
			rZone.setStatus(STATUS_NEW);
			rZone.setSynctype(SyncDataModelsCacheHelper.getSyncOprType(log.getSyncTypeId()));
			
			return rZone;
	}
		
		return null;
	}
	
	public static Geofencerules getGeofenceRulesData(List<SynchronizationTrailLog> allTraceLogs, long syncStatusProcess, ISynchronizationService iSyncService) {
		Geofencerules geofenceRules  = null;
		if(allTraceLogs != null){ // check for logs availability
			geofenceRules  = new Geofencerules();
			for (SynchronizationTrailLog log:allTraceLogs){
				Geofencerule geofenceRule = new Geofencerule();;
				
				if(log!= null && log.getSyncDataTypeId() == SYNC_DATA_TYPE_GEOFENCE_RULES){//if the event is on RB Region then...					
					MessageTemplate rule =  null; 
					if(log.getSyncTypeId() == SYNC_TYPE_ADD || log.getSyncTypeId() == SYNC_TYPE_UPDATE){
						rule  = getGeofenceRule(log.getSyncUniqueId());
						try {
							BeanUtils.copyProperties(geofenceRule, rule);
							geofenceRule.setStartDateTime(rule.getStartDate());
							geofenceRule.setEndDateTime(rule.getEndDate());
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//copyRZProperties(geofenceRule, rule, false);
					}
					
					geofenceRule.setSynctype(SyncDataModelsCacheHelper.getSyncOprType(log.getSyncTypeId()));
					//geofenceRule.setEventid(log.getSyncUniqueId());
					
					if(geofenceRule != null){
						//update the status of the region from new status to process status...
						changeTraceLogStatus(syncStatusProcess, log, iSyncService.isTerminalServer(), iSyncService.getTerminalRequestTime());
						if(!iSyncService.isTerminalServer()){
							geofenceRule.setAckid(iSyncService.getCentralServerName()+CommonConstants.UNDER_SCORE_STR+iSyncService.getTerminalRequestTime().getTime());
						}
						if(iSyncService.isTraceById()){
							geofenceRule.setSynctype(OPERATION_OVERWRITE);
							geofenceRule.setAckid(log.getAckId());
						}
						
						geofenceRule.setIsServerRecord(!iSyncService.isTerminalServer());
						geofenceRules.addGeofencerule(geofenceRule); //adding the region to the regions array
					}
				}
			}
		}
		
		return geofenceRules;
		
	}
	
	public static RiskZones getRiskzones(String eventId){
		RiskZones rZones = new RiskZones();
		
		SearchCriteria criteria = new SearchCriteria();
		criteria.addCritirea("eventId", SearchCriteria.EQUALS, eventId);
		
		List<RiskZones> zonesList = rZones.searchData(criteria);
		if(zonesList != null && zonesList.size() > 0) {
			return zonesList.get(0);
		}
		
		return null;
	}
	
	public static MessageTemplate getGeofenceRule(String regionName){
		MessageTemplate geofenceRules = new MessageTemplate();
		
		SearchCriteria criteria = new SearchCriteria();
		criteria.addCritirea("name", SearchCriteria.EQUALS, regionName);
		
		List<MessageTemplate> rulesList = geofenceRules.searchData(criteria);
		if(rulesList != null && rulesList.size() > 0) {
			return rulesList.get(0);
		}
		
		return null;
	}

	/**
	 * 
	 * @param region
	 * @param languageId
	 * @return
	 */
	public static SynchronizationTrailLog getTrasactionalTraceLog(
			Object region, long languageId) {
		//check in the database...
		SynchronizationTrailLog log = isChangeAvailable(region);
		//oops...no record..then create new one.... 
		if(log == null){ //hmm :( ...need to create one... 
			log = new SynchronizationTrailLog();
			if(region instanceof Rubberbandregion){
				Rubberbandregion tempregion = (Rubberbandregion) region;
				log.setSyncDataTypeId(SYNC_DATA_TYPE_RBREGION);
				log.setSyncUniqueId(tempregion.getRegionName());
				log.setSyncTypeId((long)SyncDataModelsCacheHelper.getSyncOprTypeId(tempregion.getSyncType()));
				log.setUserId(getUserId(tempregion.getUserName()));
				log.setUserUniqueId( tempregion.getUserUniqueId());
				log.setModuleId((long)DataModelsCacheHelper.getModuleId(tempregion.getModuleName()));
			}else if(region instanceof Riskzone){
				Riskzone tempregion = (Riskzone) region;
				log.setSyncDataTypeId(SYNC_DATA_TYPE_RISKZONES);
				log.setSyncUniqueId(tempregion.getEventid());
				log.setSyncTypeId((long)SyncDataModelsCacheHelper.getSyncOprTypeId(tempregion.getSynctype()));
			}else if(region instanceof Geofencerule){
				Geofencerule tempregion = (Geofencerule) region;
				log.setSyncDataTypeId(SYNC_DATA_TYPE_GEOFENCE_RULES);
				log.setSyncUniqueId(tempregion.getName());
				log.setSyncTypeId((long)SyncDataModelsCacheHelper.getSyncOprTypeId(tempregion.getSynctype()));
			}
			
			log.setCreatedDate(new Date());
			
			
			log.setModifiedDate(new Date());
			log.setLanguageId(languageId);
			
			
			if(log.getSyncDataTypeId()  == SYNC_DATA_TYPE_RBREGION && log.getModuleId() <=0){
				return null;
			}
			
		}
		return log; //return the log which indicates the current transaction.....
	}
	
	/**
	 * 
	 * @param region
	 * @return
	 */
	private static SynchronizationTrailLog isChangeAvailable(Object region) {
		//retrieve the SynchronizationTrailLog trace object
		if(region instanceof Rubberbandregion){
			Rubberbandregion tempregion = (Rubberbandregion) region;
			return getTraceLog(SYNC_DATA_TYPE_RBREGION, tempregion .getRegionName(), (long)SyncDataModelsCacheHelper.getSyncOprTypeId(tempregion.getSyncType()), 0L, null,
				getUserId(tempregion .getUserName()), tempregion .getUserUniqueId(), (long)DataModelsCacheHelper.getModuleId(tempregion .getModuleName()));
		}else if(region instanceof Riskzone){
			Riskzone tempregion = (Riskzone) region;
			return getTraceLog(SYNC_DATA_TYPE_RISKZONES, tempregion.getEventid(), (long)SyncDataModelsCacheHelper.getSyncOprTypeId(tempregion.getSynctype()), 0L, null,
					null, null, null);
		}else if(region instanceof Geofencerule){
			Geofencerule tempregion = (Geofencerule) region;
			return getTraceLog(SYNC_DATA_TYPE_GEOFENCE_RULES, tempregion.getName(), (long)SyncDataModelsCacheHelper.getSyncOprTypeId(tempregion.getSynctype()), 0L, null,
					null, null, null);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param syncDataTypeId
	 * @param syncUniqueId
	 * @param syncTypeId
	 * @param syncStatus
	 * @param host
	 * @param userId
	 * @param userUniqueId
	 * @param moduleId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static SynchronizationTrailLog getTraceLog(long syncDataTypeId, String syncUniqueId, 
			long syncTypeId, long syncStatus, String host, Long userId, String userUniqueId, Long moduleId){
		if(JAASConstants.OPEN_LDAP_DATA_SOURCE.equalsIgnoreCase(JAASAuthenticationTypeInitializer.getInstance().getJAASAuthenticationType())){
			userId = 0L;			
		}
		
		SynchronizationTrailLog trailLog = new SynchronizationTrailLog();

		//retrive the record whose acknowledge id is null.... (implies the record which we need to process...)
		SearchCriteria criteria = new SearchCriteria();
		criteria.addCritirea(HB_PROP_SYNC_DATA_TYPE_ID, SearchCriteria.EQUALS, syncDataTypeId);
		criteria.addCritirea(HB_PROP_SYNC_UNIQUE_ID, SearchCriteria.EQUALS, syncUniqueId);
		criteria.addCritirea(HB_PROP_USER_ID, SearchCriteria.EQUALS, userId);
		criteria.addCritirea(HB_PROP_MODULE_ID, SearchCriteria.EQUALS, moduleId);
		criteria.addCritirea(HB_PROP_USER_UNIQUE_ID, SearchCriteria.EQUALS, userUniqueId);
		criteria.addCritirea(HB_PROP_ACK_ID, SearchCriteria.EQUALS, null);
		
		List<SynchronizationTrailLog> list  = trailLog.searchData(criteria);//clear retrieve(restrictions);
		
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		
		return null;
	}
	
	public static SynchronizationTrailLog getTraceLogById(int logId){
		SynchronizationTrailLog trailLog = new SynchronizationTrailLog();

		//retrive the record whose acknowledge id is null.... (implies the record which we need to process...)
		SearchCriteria criteria = new SearchCriteria();
		criteria.addCritirea("id", SearchCriteria.EQUALS, logId);
		
		
		List<SynchronizationTrailLog> list  = trailLog.searchData(criteria);//clear retrieve(restrictions);
		
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		
		return null;
	}
	
	
	/**
	 * 	Gets all the trace logs from the SynchronizationTrailLog table with following conditions
	 * 		1. get the last accessed time from SynchronizationTrailStatusLog table
	 * 		2. retrieve all those whose modified timestamp is greater than last accessed timestamp 
	 * @param value
	 * @param isTerminalServer
	 * @param hostname
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<SynchronizationTrailLog> getTraceLogs(long value, boolean isTerminalServer, String hostname){
		Logger.info("Retrieving all trace logs for hostname ["+hostname+"]", CommonServiceDBUtils.class);
		Collection restrictions = new ArrayList();
		Date lastAccessTime = null;
		//if its not an central server then retrieve all records whose sync status  = 1
		if(!isTerminalServer){ //central server entry
			restrictions.clear();
			SynchronizationTrailStatusLog stausLog = new SynchronizationTrailStatusLog();	
			restrictions.add(Restrictions.eq(HB_PROP_HOSTNAME, hostname));
			List<SynchronizationTrailStatusLog> result = stausLog.retrieve(restrictions);
			//process the last access time for this terminal server on the central server
			if (result != null && result.size() > 0) {
				lastAccessTime = result.get(0).getProcessedDate();
			}
		}
		
		SynchronizationTrailLog trailLog = new SynchronizationTrailLog();
		restrictions.clear();
		restrictions.add(Restrictions.eq(HB_PROP_STATUS, value));
		//if its the central server then restrict the modified date.
		//if accessing for the first time, then retrieve all the records.
		if(!isTerminalServer && lastAccessTime != null){
			restrictions.add(Restrictions.gt(HB_PROP_MODIFIED_DATE, lastAccessTime));
		}
		
		return trailLog.retrieve(restrictions, null);
	}
	
	/**
	 * 
	 * @param iSyncService
	 * @return
	 */
	public static String buildSyncXML(ISynchronizationService iSyncService) {
		if(iSyncService.getTraceLogs() != null){ //check whether changes are available
			Synchronize snchronize = new Synchronize();
			
			Rubberbandregions regions  = null;
			//get regions holding region object for all the changes performed
			regions = CommonServiceDBUtils.getRBRegionsData(iSyncService.getTraceLogs(), SYNC_STATUS_PROCESS,
					iSyncService);
			
			Riskzones rZones = null; 
			
			rZones = CommonServiceDBUtils.getRiskzonesData(iSyncService.getTraceLogs(), SYNC_STATUS_PROCESS,
					iSyncService);
			
			Geofencerules gRules = null; 
			
			gRules = CommonServiceDBUtils.getGeofenceRulesData(iSyncService.getTraceLogs(), SYNC_STATUS_PROCESS,
					iSyncService);
			
			if(regions != null ){ //adding the regions..to synchronize object
				snchronize.setRubberbandregions(regions);
			}
			
			if(rZones != null ){ //adding the zones..to synchronize object
				snchronize.setRiskzones(rZones);
			}
			
			if(gRules != null ){ //adding the zones..to synchronize object
				snchronize.setGeofencerules(gRules);
			}
			
			//tagging the object with the server identification
			snchronize.setHostname(iSyncService.getTerminalServerName());	
			
			if(iSyncService.isTraceById()){
				snchronize.setTraceById(iSyncService.isTraceById());
			}
			snchronize.setTraceById(iSyncService.isTraceById());
			return CastorUtils.marshal(snchronize); //generate xml
		}
		
		return null;
	}

	/**
	 * this method is used to validate the trace logs... this step is used to make sure we are having 
	 * no conflicts with our database & central database....
	 * @param allTraceLog
	 * @param log
	 */
	public synchronized static void revalidateCachedTraceLogs(
			List<SynchronizationTrailLog> allTraceLog,
			SynchronizationTrailLog log) {
		//iterate all the trace logs ...which are cached before initiate the request... 
		if(allTraceLog != null){
			try{
				for(int i=0; i< allTraceLog.size(); i++ ){ //SynchronizationTrailLog tempLog:allTraceLog){
					//check whether the trace log is available...if so...remove it..plz
					if(allTraceLog.get(i) != null && allTraceLog.get(i).getId().intValue() == log.getId().intValue()){
						allTraceLog.remove(i);
					}
				}
			}catch (Exception e){
				e.printStackTrace();
			}
			
		}
		
	}

	static void copyRZProperties(Riskzone dest, RiskZones source, boolean inverseCopy){
		if(dest != null && source != null && !inverseCopy) {
			dest.setCountry_gwno(source.getCountryCd());
			dest.setEventid(source.getEventId());
			dest.setEvent_date(source.getEventDate());
			dest.setEventtype(source.getEventType());
			dest.setActor1(source.getActor1());
			dest.setActor1fatalities(source.getActor1Fatalities());
			dest.setActor2(source.getActor2());
			dest.setActor2fatalities(source.getActor2Fatalities());
			dest.setActorally1(source.getActorAlly1());
			dest.setActorally2(source.getActorAlly2());
			dest.setCountry(source.getCountry());
			dest.setGeoprecesion(null);
			
			dest.setCoordinates(source.getCoordinates());//Latitude(source.getLatitude());
			//dest.setLongitude(source.getLongitude());
			dest.setNotes(source.getNotes());
			dest.setLocaiton(source.getLocation());
			dest.setPublication(null);
			dest.setRegion(source.getRegion());
			//dest.setTimeprecision(StringUtils.isNull(source.getTimePrecision())?source.getTimePrecision().longValue():0L);
			dest.setUpdatedby(source.getUpdatedBy());
			dest.setAckid(null);
		
		}else if(dest != null && source != null && inverseCopy) {
			
			source.setCountryCd(dest.getCountry_gwno());
			source.setEventId(dest.getEventid());
			source.setEventDate(dest.getEvent_date());
			source.setEventType(dest.getEventtype());
			source.setActor1(dest.getActor1());
			source.setActor1Fatalities(dest.getActor1fatalities());
			source.setActor2(dest.getActor2());
			source.setActor2Fatalities(dest.getActor2fatalities());
			source.setActorAlly1(dest.getActorally1());
			source.setActorAlly2(dest.getActorally2());
			source.setCountry(dest.getCountry());
			source.setGeoprecesion(null);
			
			source.setCoordinates(dest.getCoordinates());
			source.setNotes(dest.getNotes());
			source.setLocation(dest.getLocaiton());
			source.setPublication(null);
			source.setRegion(dest.getRegion());
			//source.setTimePrecision(dest.getTimeprecision());
			source.setUpdatedBy(dest.getUpdatedby());
			
		}
	}
	
	public static String getUserName(Long userId){
		User user = new User();
		SearchCriteria criteria = new SearchCriteria();
		criteria.addCritirea(HB_PROP_ID, SearchCriteria.EQUALS, userId.intValue());
		
		List<User> list = user.searchData(criteria);
		
		return list != null && list.size()> 0  ? (list.get(0)).getLoginId():null;
	}
	
	public static Long getUserId(String loginId){
		User user = new User();
		
		SearchCriteria criteria = new SearchCriteria();
		criteria.addCritirea(HB_PROP_LOGIN_ID, SearchCriteria.EQUALS, loginId);
		
		List<User> list = user.searchData(criteria);
		return list != null && list.size()> 0  ? (list.get(0)).getId():0L;
	}
	
	public static final String getModuleName(Long moduleId) {
		if (moduleId > 0) {
			List moduleList = getAllDefinedModules();
			int count = moduleList == null ? 0 : moduleList.size();
			Module module = null;
			for (int i = 0; i < count; i++) {
				module = (Module) moduleList.get(i);
				if (module.getId().intValue() == moduleId.intValue()) {
					return module.getName();
				}
			}
		}
		return null;
	}
	
	public static List getAllDefinedModules() {
		ArrayList modules = null;
		Module module = new Module();
		List moduleList = module.retrieveAll();
		int size = moduleList == null ? 0 : moduleList.size();
		if (size > 0) {
			return moduleList;
		} else
			modules = new ArrayList();
	
		return modules;
	}

	public static void resetTraceLogs(List<SynchronizationTrailLog> allTraceLogs) {
		if(allTraceLogs != null){
			for (SynchronizationTrailLog log: allTraceLogs){
				log.setStatus(SYNC_STATUS_NEW);
				log.updateData();
			}
		}
		
	}

	public static void updateTraceLogs(Synchronize syncObj, String hostName) {
		Rubberbandregions regions = syncObj.getRubberbandregions();
		Riskzones zones = syncObj.getRiskzones();
		Geofencerules georules = syncObj.getGeofencerules();
		
		if(regions != null ){
			for(Rubberbandregion region : regions.getRubberbandregion()){
				if(region.getIsServerRecord() && !syncObj.getTraceById()){
					{
						SynchronizationDBUtils.insertUpdateTraceLog(SYNC_DATA_TYPE_RBREGION, region.getRegionName(),(long)SyncDataModelsCacheHelper.getSyncOprTypeId(region.getSyncType()),
								SYNC_STATUS_SUCCESS, hostName, getUserId(region.getUserName()), region.getUserUniqueId(), 
								(long)DataModelsCacheHelper.getModuleId(region.getModuleName()), null, null, hostName,
								region.getAckid(),null, null);
					}
					
				}
				if(syncObj.getTraceById() && !StringUtils.isNull(region.getAckid())){
					updateConflictRecord(region.getAckid(), SYNC_STATUS_SUCCESS);
				}
			}
		}
		
		if(zones != null){
			for(Riskzone zone : zones.getRiskzone()){
				if(zone.getIsServerRecord() && !syncObj.getTraceById() ){
					{
						SynchronizationDBUtils.insertUpdateTraceLog(SYNC_DATA_TYPE_RISKZONES, zone.getEventid(),(long)SyncDataModelsCacheHelper.getSyncOprTypeId(zone.getSynctype()),
								SYNC_STATUS_SUCCESS, hostName, null, null, 
								null, null, null, hostName, zone.getAckid(),zone.getCreatedate(), null);
					}
				}
				if(syncObj.getTraceById() && !StringUtils.isNull(zone.getAckid())){
					updateConflictRecord(zone.getAckid(), SYNC_STATUS_SUCCESS);
				}
			}
		}
		
		if(georules != null){
			for(Geofencerule rule:georules.getGeofencerule()){
				if(rule.getIsServerRecord() && !syncObj.getTraceById() ){
					{
						SynchronizationDBUtils.insertUpdateTraceLog(SYNC_DATA_TYPE_GEOFENCE_RULES, rule.getName(),(long)SyncDataModelsCacheHelper.getSyncOprTypeId(rule.getSynctype()),
								SYNC_STATUS_SUCCESS, hostName, null, null, 
								null, null, null, hostName, rule.getAckid(),rule.getCreateDate(), null);
					}
				}
				if(syncObj.getTraceById() && !StringUtils.isNull(rule.getAckid())){
					updateConflictRecord(rule.getAckid(), SYNC_STATUS_SUCCESS);
				}
			}
		}
		
		
	}

	private static void updateConflictRecord(String ackid,
			long syncStatusSuccess) {
		SynchronizationTrailLog trailLog = getTraceLogByAck(ackid);
		if(trailLog != null){
			trailLog.setAckId(ackid);
			trailLog.setStatus(syncStatusSuccess);
			trailLog.updateData();
		}
	}
	
	public static SynchronizationTrailLog getTraceLogByAck(String ackId){
		SynchronizationTrailLog trailLog = new SynchronizationTrailLog();
		
		SearchCriteria criteria = new SearchCriteria();
		criteria.addCritirea("ackId", SearchCriteria.EQUALS, ackId);
		
		List<SynchronizationTrailLog> list = trailLog.searchData(criteria);
		
		if(list != null && list.size() > 0){
			return list.get(0);
			
		}
		
		return null;
	}
	
}
