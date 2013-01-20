package com.wfp.utils;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.enterprisehorizons.constants.CommonConstants;
import com.enterprisehorizons.magma.designtime.artifact.GeoArtifact;
import com.enterprisehorizons.magma.models.util.Coordinate;
import com.enterprisehorizons.magma.models.util.CoordinateUtils;
import com.enterprisehorizons.magma.server.util.Cache;
import com.enterprisehorizons.util.SearchCriteria;
import com.enterprisehorizons.util.StringUtils;
import com.spacetimeinsight.cache.CacheController;
import com.spacetimeinsight.db.config.model.EcoexpmlGroupMap;
import com.spacetimeinsight.db.config.model.UserPreferences;
import com.spacetimeinsight.db.model.util.DataModelsCache;
import com.spacetimeinsight.db.model.util.DataModelsCacheHelper;
import com.spacetimeinsight.db.model.util.UserPreferenecsHelper;
import com.spacetimeinsight.rubberbandregions.Rubberbandregion;
import com.spacetimeinsight.rubberbandregions.Rubberbandregions;
import com.spacetimeinsight.security.bean.UserBean;
import com.wfp.db.platform.model.MessageTemplate;

import flex.messaging.io.ArrayCollection;


public class CopyOfRBRegionsUtils implements IEPICConstants {

	static {
		//CacheController.getInstance().storeObject("$staff_tracking$", new HashMap<String, String>());
		if(Cache.retrieve(CACHE_STAFF_TRACKING) == null){
			Cache.store(CACHE_STAFF_TRACKING, new HashMap<String, Map<String, String>>());
			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Map<String, String>> getStaffTackingCache() {
		return (Map<String, Map<String, String>>)Cache.retrieve(CACHE_STAFF_TRACKING);
	}
	
	@SuppressWarnings("unused")
	public static  boolean isInDangerZone(Rubberbandregions rubberBandedRegions,
			com.enterprisehorizons.magma.models.util.Coordinate coord) {
		if(rubberBandedRegions != null ){
			Rubberbandregion rbRegion = null;
			for (int i=0;i <rubberBandedRegions.getRubberbandregionCount();i++){
				rbRegion =rubberBandedRegions.getRubberbandregion(i);
				if(CoordinateUtils.isInside(rbRegion.getCoordinates(), coord)) {
					return true;
				}
			}
		}
		
		return false;
		
	}
	
	public static  String getDanzerZoneName(Rubberbandregions rubberBandedRegions,
			com.enterprisehorizons.magma.models.util.Coordinate coord) {
		if(rubberBandedRegions != null ){
			Rubberbandregion rbRegion = null;
			for (int i=0;i <rubberBandedRegions.getRubberbandregionCount();i++){
				rbRegion =rubberBandedRegions.getRubberbandregion(i);
				if(CoordinateUtils.isInside(rbRegion.getCoordinates(), coord)) {
					return rbRegion.getRegionName();
				}
			}
		}
		
		return null;
		
	}
	
	public static String[] getZoneNames(GeoArtifact artifact, UserBean userbean, String layerName, long layerId, String startsWith){
		long moduleId = CopyOfRBRegionsUtils.getModuleId(layerId);
		Rubberbandregions rubberbandregions = CopyOfRBRegionsUtils.getUserModuleRubberbandregion(userbean.getDomainId(), userbean.getLanguageId(), 
					userbean.getUserId(), userbean.getUserUniqueId(), moduleId, startsWith);
		return getZoneNames(rubberbandregions, artifact!= null?artifact.getFirstPoint():null);
		//return null;
	}

	private static String[] getZoneNames(Rubberbandregions rubberbandregions,
			Coordinate firstPoint) {
		List<String> allZones = null;
		
		if(rubberbandregions != null && firstPoint != null ){
			Rubberbandregion rbRegion = null;
			for (int i=0;i <rubberbandregions.getRubberbandregionCount();i++){
				rbRegion =rubberbandregions.getRubberbandregion(i);
				if(CoordinateUtils.isInside(rbRegion.getCoordinates(), firstPoint)) {
					if(allZones == null){
						allZones = new ArrayList<String>();
					}
					allZones.add(rbRegion.getRegionName());
					
				}
			}
		}
		if(allZones != null){
			String[] strResult=new String[allZones.size()];  
			return allZones.toArray(strResult);
		}
		 
		
		return  null;
	}
	
	public static boolean isDeviceTraced(GeoArtifact artifact, String[] allZones, String sessionid, String deviceId){
		if(allZones == null){
			return false; 
		}
		boolean isInUnsafeZone =  (!StringUtils.isNull(allZones));
		if(getStaffTackingCache().get(sessionid) == null){
			getStaffTackingCache().put(sessionid, new HashMap<String,String>());
		}
		Map<String, String> cache = getStaffTackingCache().get(sessionid);
		String statistics = cache.get(deviceId);	
		boolean isInSamePlace = (isInUnsafeZone?"Y":"N").equalsIgnoreCase(statistics);

		
		if(StringUtils.isNull(statistics) || (!isInSamePlace )){
			if((isInUnsafeZone?"Y":"N").equalsIgnoreCase(cache.get(deviceId))){
				
			}
			cache.put(deviceId, isInUnsafeZone?"Y":"N");
			boolean isTraced = (!isInSamePlace && isInUnsafeZone);
			return isTraced;
			/*if(isSendMail)	{
				for(String zone:allZones){
					//com.wfp.utils.EventServiceUtils.publishEvent(zone, deviceId);	
					//com.wfp.utils.AlertServiceUtils.publishAlert(deviceId, zone);
				}
				
			}	*/
			//return (!isInSamePlace && isInUnsafeZone);
		}
		
		return false;
	}

	public static MessageTemplate getMessageTemplate(String regionName){
		SearchCriteria sc = new SearchCriteria();
		sc.addCritirea("name", SearchCriteria.EQUALS, regionName);
		
		MessageTemplate mt = new MessageTemplate();
		List list = mt.searchData(sc);
		if(list != null && list.size() > 0){
			return (MessageTemplate) list.get(0);
		}
		
		return null;
	}
	
	private static List<UserPreferences> getUserModulePreferences(long domainId, long languageId, long userId, String userUniqueIdStr, long moduleId) {
		List<UserPreferences> userPreferenceList = new ArrayList<UserPreferences>();
		List userFavoritesList = DataModelsCache.getInstance().retrieve(UserPreferences.class.getName());
		int count = userFavoritesList == null ? 0 : userFavoritesList.size();
		UserPreferences userPrefrences = null;
		if (userId == 0)
			userId = -1;
		for (int i = 0; i < count; i++) {
			userPrefrences = (UserPreferences) userFavoritesList.get(i);

			if (domainId == userPrefrences.getDomainId() && languageId == userPrefrences.getLanguageId()
					&& (userId == userPrefrences.getUserId() || userUniqueIdStr.equals(userPrefrences.getUserUniqueId()))
					&& moduleId == userPrefrences.getModuleId()) {
				userPreferenceList.add(userPrefrences);
			}
			
			if (domainId == userPrefrences.getDomainId() && languageId == userPrefrences.getLanguageId()
					&& (0 == userPrefrences.getUserId() && StringUtils.isNull(userPrefrences.getUserUniqueId())  )
					&& moduleId == userPrefrences.getModuleId()) {
				userPreferenceList.add(userPrefrences);
			}
		}
		return userPreferenceList;
	}
	
	
	private static UserPreferences[] getUserModulePreferences(long domainId, long languageId, long userId,String userUniqueIdStr, long moduleId, int preferenceId) {
        if(userUniqueIdStr == null)
        userUniqueIdStr = CommonConstants.EMPTY_STRING;
        
		List<UserPreferences> preferencesList = getUserModulePreferences(domainId, languageId, userId, userUniqueIdStr,moduleId);
		int count = preferencesList == null ? 0 : preferencesList.size();
		UserPreferences[] preference = new UserPreferences[preferencesList.size()];
		for (int i = 0; i < count; i++) {
			
			if (preferencesList.get(i).getPreferenceTypeId() == preferenceId) {
				preference[i] = preferencesList.get(i);
			}
		}

		return preference;
	}
	
	
	public static  Rubberbandregions getUserModuleRubberbandregion(long domainId, long languageId, long userId,String userUniqueIdStr,long moduleId, String regionPrefix) {
		DataModelsCache.getInstance().refresh(UserPreferences.class.getName());
		UserPreferences[] userPreferences = getUserModulePreferences(domainId, languageId, userId,userUniqueIdStr, moduleId, UserPreferenecsHelper.PREFERENCE_ID_RBREGIONS);
		Rubberbandregions rubberbandregions = new Rubberbandregions();
		for (int i = 0; i < userPreferences.length; i++) {
			if(userPreferences[i] != null){
				Rubberbandregions rubberbandregionsTemp = UserPreferenecsHelper.getUserRBRegions(userPreferences[i].getPreferenceValue());
				for (int j = 0; j < rubberbandregionsTemp.getRubberbandregionCount(); j++) {
					if(StringUtils.isNull(regionPrefix ) || rubberbandregionsTemp.getRubberbandregion(j).getRegionName().toLowerCase().startsWith(regionPrefix.toLowerCase()) ){
						rubberbandregions.addRubberbandregion(rubberbandregionsTemp.getRubberbandregion(j));
					}
				}
			}
			
		}
		return rubberbandregions;
	}
	
	public static final ArrayCollection getUserModuleRegionNames(long domainId, long languageId, long userId,String userUniqueIdStr,long moduleId, String regionPrefix) {
		ArrayCollection rbRegionNames = null;
		Rubberbandregions rbRegions = getUserModuleRubberbandregion(domainId, languageId, userId, userUniqueIdStr, moduleId, regionPrefix);
		if(rbRegions != null ){
			rbRegionNames = new ArrayCollection();
			for(int i=0; i<rbRegions.getRubberbandregionCount();i++){
				rbRegionNames.add(rbRegions.getRubberbandregion(i).getRegionName());
			}
		}
		return rbRegionNames;
	}
	
	public static  ArrayCollection getAllUserModuleRegionMessages(long domainId, long languageId, long userId,String userUniqueIdStr,long moduleId, String regionPrefix){
		ArrayCollection rbRegionNames =  getUserModuleRegionNames(domainId, languageId, userId, userUniqueIdStr, moduleId, regionPrefix);
		ArrayCollection rbArrayCollection = null;
		if(rbRegionNames != null){
			rbArrayCollection = new ArrayCollection();
			MessageTemplate temp = new MessageTemplate();
			temp.setName(CommonConstants.EMPTY_STRING);
			rbArrayCollection.add(temp);
			for (int i=0;i<rbRegionNames.size(); i++){
				getRegionMessage(rbArrayCollection, (String) rbRegionNames.get(i)); 
			}
		}
		
		return rbArrayCollection;
	}
	
	public static void getRegionMessage(ArrayCollection rbArrayCollection, String rbRegionName){
		if(rbArrayCollection != null){
			MessageTemplate rbMessage = getRegionMessage(rbRegionName);
			rbArrayCollection.add(rbMessage);
		}
		
	}
	
	public static MessageTemplate getRegionMessage(String rbRegionName){
		MessageTemplate rbMessage = null;
		SearchCriteria sc = new SearchCriteria();
		sc.addCritirea(PARAM_NAME, SearchCriteria.EQUALS, rbRegionName);
		
		MessageTemplate mt = new MessageTemplate();
		List result = mt.searchData(sc);
		if(result != null && result.size() > 0){
			rbMessage = (MessageTemplate) result.get(0);
			
		}else {
			rbMessage = new MessageTemplate();
			rbMessage.setName(rbRegionName);
		}
		
		return rbMessage;
	}
	
	public static boolean saveRegionMessage(MessageTemplate mt){
		MessageTemplate crud = new MessageTemplate();
		crud.setName(mt.getName());
		crud.setBody(mt.getBody());
		crud.setSubject(mt.getSubject());
		crud.setRecurPerDay(mt.getRecurPerDay());
		crud.setTriggerTime(mt.getTriggerTime());
		if(mt.getId() > 0){
			crud.setId(mt.getId());
		}
		return crud.insertOrUpdateData();
	}
	
	public static boolean deleteRegionMessage(String rbRegionName){
		MessageTemplate map = new MessageTemplate();
		
		SearchCriteria criteria = new SearchCriteria();
		criteria.addCritirea(PARAM_NAME, SearchCriteria.EQUALS, rbRegionName);
		
		return map.deleteData(criteria);
	}
	
	
	
	public static long getModuleId(long layerId){
		EcoexpmlGroupMap map = new EcoexpmlGroupMap();
		
		SearchCriteria criteria = new SearchCriteria();
		criteria.addCritirea(PARAM_ID, SearchCriteria.EQUALS, layerId);
		
		List<EcoexpmlGroupMap> list = map.searchData(criteria);
		
		if(list != null && list.size()>0){
			return list.get(0).getModuleId();
		}
		
		
		return 0L;
	}
	
	public static long getModuleId(String name){
		EcoexpmlGroupMap map = new EcoexpmlGroupMap();
		
		SearchCriteria criteria = new SearchCriteria();
		criteria.addCritirea("ecoDisplayName", SearchCriteria.EQUALS, name);
		
		List<EcoexpmlGroupMap> list = map.searchData(criteria);
		
		if(list != null){
			return list.get(0).getModuleId();
		}
		
		
		return 0L;
	}
	
	public static boolean validateLocation(String country, Long[] allGroups){
		Map<String, Long> map = DataModelsCacheHelper.getAllGroups();
		 Iterator it = map.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry<String, Long> pairs = (Map.Entry)it.next();
		        
		        
		        for(int i=0; i<allGroups.length; i++){
		        	if(!pairs.getKey().startsWith(KEY_PAKISTAN) && !pairs.getKey().startsWith(KEY_DUBAI) && !pairs.getKey().startsWith(KEY_ROME) &&
		        			pairs.getValue().intValue() == allGroups[i].intValue()){
			        	return true;
			        }
		        	
		        	if(pairs.getValue().intValue() == allGroups[i].intValue() &&
		        			pairs.getKey().startsWith(KEY_PAKISTAN) && PAKISTAN.equalsIgnoreCase(country)){
			        	return true;
			        }else if(pairs.getValue().intValue() == allGroups[i].intValue() &&
		        			pairs.getKey().startsWith(KEY_DUBAI) && DUBAI.equalsIgnoreCase(country)){
			        	return true;
			        }else if(pairs.getValue().intValue() == allGroups[i].intValue() &&
		        			pairs.getKey().startsWith(KEY_ROME) && ROME.equalsIgnoreCase(country)){
			        	return true;
			        }
			       
		        }
		        
		    }
		    return false;
	}
	
	
}
