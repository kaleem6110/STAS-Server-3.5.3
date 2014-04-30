package com.wfp.jobs;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
import lu.hitec.pss.soap.ds.out._15_x.DirectoryServiceOutInterface_PortType;
import lu.hitec.pss.soap.ds.out._15_x.DtoMission;
import lu.hitec.pss.soap.sensor.client._12_x.LocationRange;
import lu.hitec.pss.soap.sensor.client._12_x.RangeLimit;
import lu.hitec.pss.soap.sensor.client._12_x.SensorSrvClientPortBindingStub;
import lu.hitec.pss.soap.sensor.client._12_x.SubRangeType;
import lu.hitec.pss.soap.sensor.client._12_x.UnitReport;
import lu.hitec.pss.soap.sensor.client._12_x.UnitType;
import lu.hitec.pss.soap.sensor.client._12_x.UnitsReports;

import com.enterprisehorizons.util.Logger;
import com.spacetimeinsight.config.scheduler.Parameter;
import com.spacetimeinsight.config.scheduler.Parameters;
import com.spacetimeinsight.magma.job.CustomJobTask;
import com.wfp.utils.CommonUtils;
import com.wfp.utils.EventServiceUtils;
import com.wfp.utils.IEPICConstants;
import com.wfp.utils.LDAPUtils;
import com.wfp.utils.SensorServiceUtils;

/**
 * 
 * @author sti-user
 *
 */
@SuppressWarnings("unchecked")
public class LDAPCacheJob implements CustomJobTask, IEPICConstants {
	
	private static LDAPCacheJob instance = null;
	private static Map<String, Object> ldapServiceMap = new HashMap();
	private static Map<String, List<String>> userMisionsMap = new HashMap();
	private static String lastRefreshTime = null;
	private static Map<String, String> paramsMap = new HashMap();
	public static Map<String,String> deviceOffsetMap = new HashMap();
	private static Map<String,DtoMission[]> placeMissionMap = new HashMap<String,DtoMission[]>();
	
	public LDAPCacheJob () {
			
	}
	
	private static synchronized void initializeInstance() 
	{
		if (instance == null) {
			instance = new LDAPCacheJob();
		}
	}
	public static LDAPCacheJob getInstance() {
		if (instance == null) {
			initializeInstance();
		}
		return instance;
	}	
	
	/* (non-Javadoc)
	 * @see com.spacetimeinsight.magma.job.CustomJobTask#executeCustomTask(com.spacetimeinsight.config.scheduler.Parameters)
	 */
	public boolean executeCustomTask(Parameters parameters)
	//public static boolean executeCustomTask()
	{
		System.out.println("### START LDAP service Job: "+CommonUtils.getUTCdatetimeAsString() );
		int staff=0,vehicle=0,plane=0;
		String token = EventServiceUtils.getLDAPToken();
		DirectoryServiceOutInterface_PortType stub=null;
		try {
			 stub= EventServiceUtils.getLDAPStub();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//SensorSrvClientPortBindingStub stub = SensorServiceUtils.getServiceLocatorStub();
		if( LDAPUtils.getOrgMap()==null ) LDAPUtils.getAllOrganizations();		
		
		Parameter[] params = parameters.getParameter();
		if(params!= null )
		{
			for (int i=0; i<  params.length ; i++) 
				paramsMap.put(params[i].getName(), params[i].getValue());	
		}
		if(paramsMap.get("clearcache") != null && "true".equalsIgnoreCase(paramsMap.get("clearcache")))
		{
			LDAPUtils.getLDAPUserDtlsMap().clear();
			System.out.println("### Cache Cleared: clearing the LDAP user details service Job: "+CommonUtils.getUTCdatetimeAsString() );
		}
		userMisionsMap.clear();
		@SuppressWarnings("unchecked")
		List<String> allDevices = LDAPUtils.getDevices();		
		List<String> allStaffDevices = LDAPUtils.getAllStaffDevices();
		List<String> allVehicleDevices = LDAPUtils.getAllVehicleDevices();
		List<String> allAirplaneDevices = LDAPUtils.getAllAirplaneDevices();
		ldapServiceMap.put(PARAM_ALLGROUPS, allDevices);		
		if( allStaffDevices!=null && allStaffDevices.size()>0)
		{	
			for(String device: allStaffDevices ){staff++;
				LDAPUtils.setLDAPUserDtls( device , KEY_STAFF , token, stub );			
			}
		}
		if( allVehicleDevices!=null && allVehicleDevices.size()>0)
		{	
			for(String device: allVehicleDevices ){vehicle++;
				LDAPUtils.setLDAPUserDtls( device , KEY_VEHICLE , token, stub );				
			}
		}
		if( allAirplaneDevices!=null && allAirplaneDevices.size()>0)
		{	
			for(String device: allAirplaneDevices ){plane++;
			LDAPUtils.setLDAPUserDtls( device , KEY_AIRPLANE , token, stub );	
			}				
		}
		Map<String, List<String>> allDomainsOnMissionList = LDAPUtils.getAllDomainsOnMissionMap();
		ldapServiceMap.put("allDomainsOnMissionList", allDomainsOnMissionList);
		
		Map<String, List<String>> allDevicesDomainsMap = LDAPUtils.getAllDevicesOnDomainMap();
		ldapServiceMap.put("allDevicesDomains", allDevicesDomainsMap);
		
		lastRefreshTime = CommonUtils.getUTCdatetimeAsString();
		if(allDevices!=null )System.out.println("### , LDAPCacheJob.class LDAP Devices count :"+ allDevices.size() );
		System.out.println("### , LDAPCacheJob.classStaff : "+staff+": Vehicle: "+vehicle+" : Plane :"+plane );
		System.out.println("### , LDAPCacheJob.class END LDAP service Job : "+lastRefreshTime);
		return true;
	}
	private static Map<String, Object> getLDAPServiceMapCache() {
		return ldapServiceMap;
	}

	public static Map<String, List<String>> getLDAPUsersMissionsList(){
		return userMisionsMap;
	}
	
	public static Object getLDAPCacheData(String key) {
		return getLDAPServiceMapCache().get(key);
	}
	public static String getLastRefreshTime() {
		return lastRefreshTime;
	}

	/**
	 * @return the deviceOffsetMap
	 */
	public static Map<String, String> getDeviceOffsetMap() {
		return deviceOffsetMap;
	}


	/**
	 * @param deviceOffsetMap the deviceOffsetMap to set
	 */
	public static void setDeviceOffsetMap(Map<String, String> deviceOffsetMap) {
		LDAPCacheJob.deviceOffsetMap = deviceOffsetMap;
	}

	/**
	 * @return the placeMissionMap
	 */
	public static Map<String, DtoMission[]> getPlaceMissionMap() {
		return placeMissionMap;
	}

	/**
	 * @param placeMissionMap the placeMissionMap to set
	 */
	public static void setPlaceMissionMap(Map<String, DtoMission[]> placeMissionMap) {
		LDAPCacheJob.placeMissionMap = placeMissionMap;
	}

	
}
