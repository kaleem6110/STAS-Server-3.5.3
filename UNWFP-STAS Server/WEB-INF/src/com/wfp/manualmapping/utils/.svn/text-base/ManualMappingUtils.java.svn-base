package com.wfp.manualmapping.utils;

import java.util.Date;
import java.util.List;

import com.enterprisehorizons.exception.EHRuntimeException;
import com.enterprisehorizons.magma.artifact.ecojavamodel.BaseEcoJavaModel;
import com.enterprisehorizons.magma.designtime.artifact.GeoArtifact;
import com.enterprisehorizons.magma.designtime.artifact.IArtifact;
import com.enterprisehorizons.magma.models.util.CoordinateUtils;
import com.enterprisehorizons.magma.server.admin.AdminConfigUtils;
import com.enterprisehorizons.magma.server.util.ServerUtils;
import com.enterprisehorizons.util.SearchCriteria;
import com.enterprisehorizons.util.StringUtils;
import com.spacetimeinsight.security.bean.JAASAuthenticationTypeInitializer;
import com.spacetimeinsight.security.bean.JAASConstants;
import com.wfp.db.layers.model.RiskZones;
import com.wfp.offline.synchronize.ISynchronizationServiceConstants;
import com.wfp.offline.synchronize.utils.SynchronizationDBUtils;

public class ManualMappingUtils {

	public static boolean rzArtifactAdded(IArtifact newArtifact, BaseEcoJavaModel baseObj){
		RiskZones zone = new RiskZones();
		String coords = CoordinateUtils.getCoordinatesAsString(((GeoArtifact)newArtifact).getCoordinates());		
		
		setZoneProperties(zone, newArtifact, baseObj, coords);
		zone.setCreatedDate(new Date());
		boolean isInserted = zone.insertData();
 		
		if(isInserted){
			SynchronizationDBUtils.syncTraceLog(ISynchronizationServiceConstants.SYNC_DATA_TYPE_RISKZONES,
					zone.getEventId(), ISynchronizationServiceConstants.SYNC_TYPE_ADD,
					ISynchronizationServiceConstants.SYNC_STATUS_NEW, ServerUtils.getSystemServerName()+"_"+AdminConfigUtils.getHttpServerPort(), 0L,
					null, null);
			throw new EHRuntimeException("Risk Zone added successfully");
		}else {
			throw new EHRuntimeException("Risk Zone adding failed");
		}
 		
 		
		//return false;
	}
	
	public static boolean rzArtifactUpdated(IArtifact newArtifact,
			IArtifact oldArtifact, BaseEcoJavaModel baseObj) {
		String coords = CoordinateUtils.getCoordinatesAsString(
				((GeoArtifact) newArtifact).getCoordinates());
		RiskZones zone = getRiskzones(newArtifact.getAttributeValueAsString("event_id"));
		if(zone != null){
			setZoneProperties(zone, newArtifact, baseObj, coords);
			zone.setModifiedDate(new Date());
			
			if(zone.updateData()){
				SynchronizationDBUtils.syncTraceLog(ISynchronizationServiceConstants.SYNC_DATA_TYPE_RISKZONES,
						zone.getEventId(), ISynchronizationServiceConstants.SYNC_TYPE_UPDATE,
						ISynchronizationServiceConstants.SYNC_STATUS_NEW, ServerUtils.getSystemServerName()+"_"+AdminConfigUtils.getHttpServerPort(), 0L,
						null, null);
				throw new EHRuntimeException("Risk Zone updated successfully");
			}else {
				throw new EHRuntimeException("Risk Zone updating failed");
			}
			
		}

		return false;
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
	
	public static boolean rzArtifactDeleted(IArtifact newArtifact,
			BaseEcoJavaModel baseObj) {
		RiskZones zone = getRiskzones(newArtifact.getAttributeValueAsString("event_id"));
		if(zone != null){
		
			if(zone.deleteData()){
				SynchronizationDBUtils.syncTraceLog(ISynchronizationServiceConstants.SYNC_DATA_TYPE_RISKZONES,
						zone.getEventId(), ISynchronizationServiceConstants.SYNC_TYPE_DELETE,
						ISynchronizationServiceConstants.SYNC_STATUS_NEW, ServerUtils.getSystemServerName()+"_"+AdminConfigUtils.getHttpServerPort(), 0L,
						null, null);
				throw new EHRuntimeException("Risk Zone deleted successfully");
			}else {
				//throw new EHRuntimeException("Risk Zone deleted successfully");
			}
			
		}

		return false;
	}

	
	
	public static void setZoneProperties(RiskZones zone, IArtifact newArtifact, BaseEcoJavaModel baseObj, String coords){
		if(!StringUtils.isNull(newArtifact.getAttributeValueAsString("country_gwno"))){
			zone.setCountryCd(newArtifact.getAttributeValueAsString("country_gwno"));
		}else{
			zone.setCountryCd("Region_"+newArtifact.getAttributeValue("event_id"));
		}
		
		if(StringUtils.isNull(newArtifact.getAttributeValueAsString("event_id"))){
			newArtifact = null;
			throw new EHRuntimeException("Event Id is mandatory");
		}
		
		if(StringUtils.isNull(newArtifact.getAttributeValueAsString("event_date"))){
			throw new EHRuntimeException("Event Date is mandatory");
		}
		zone.setEventId(newArtifact.getAttributeValueAsString("event_id"));
		zone.setEventDate(getDate(newArtifact.getAttributeValueAsString("event_date")));
		zone.setEventType(newArtifact.getAttributeValueAsString("event_type"));
		zone.setActor1(newArtifact.getAttributeValueAsString("actor_1"));
		zone.setActor1Fatalities(newArtifact.getAttributeValueAsString("actor_1_fatalities"));
		zone.setActor2(newArtifact.getAttributeValueAsString("actor_2"));
		zone.setActor2Fatalities(newArtifact.getAttributeValueAsString("actor_2_fatalities"));
		zone.setActorAlly1(newArtifact.getAttributeValueAsString("actor_ally_1"));
		zone.setActorAlly2(newArtifact.getAttributeValueAsString("actor_ally_2"));
		zone.setCountry(newArtifact.getAttributeValueAsString("country"));
		zone.setGeoprecesion(null);
		
		zone.setCoordinates(coords);
		zone.setNotes(newArtifact.getAttributeValueAsString("notes"));
		zone.setLocation(newArtifact.getAttributeValueAsString("location"));
		zone.setPublication(null);
		zone.setRegion(newArtifact.getAttributeValueAsString("region"));
		zone.setTimePrecision(0l);
		zone.setUpdatedBy(getUserId(baseObj));
		
	}
	
	public static String formatDate(String srcDate) {
		String retValue = "";
		try {
			java.text.DateFormat destFormat = new java.text.SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			java.text.DateFormat srcFormat = new java.text.SimpleDateFormat(
					"EEE MMM dd HH:mm:ss Z yyyy");
			java.util.Date date = null;
			if (!StringUtils.isNull(srcDate)) {
				date = srcFormat.parse(srcDate);
				retValue = destFormat.format(date);
			} else {
				retValue = destFormat.format(new Date());
			}

		} catch (Exception ex) {
			//System.out.println(ex);
		}
		return retValue;
	}
	
	public static Date getDate(String srcDate) {
		String retValue = "";
		try {
			/*java.text.DateFormat destFormat = new java.text.SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			*/java.text.DateFormat srcFormat = new java.text.SimpleDateFormat(
					"EEE MMM dd HH:mm:ss Z yyyy");
			//java.util.Date date = null;
			if (!StringUtils.isNull(srcDate)) {
				return srcFormat.parse(srcDate);
				
			} else {
				return new Date();
			}

		} catch (Exception ex) {
			throw new EHRuntimeException("Event Date is not a valid date format");
		}
		//return null;
	}
	
	public static String getUserId(BaseEcoJavaModel baseObj){
		com.spacetimeinsight.security.bean.UserBean userbean =  baseObj.getUserBean();
		if(userbean == null){
			return null;
		}
		if(JAASConstants.OPEN_LDAP_DATA_SOURCE.equalsIgnoreCase(JAASAuthenticationTypeInitializer.getInstance().getJAASAuthenticationType())){
			return userbean.getUserUniqueId();
		}else {
			return userbean.getLoginId();
		}
		
		
	}
}
