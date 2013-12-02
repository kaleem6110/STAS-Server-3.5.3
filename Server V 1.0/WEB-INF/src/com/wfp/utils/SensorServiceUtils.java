package com.wfp.utils;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import lu.hitec.pss.soap.sensor.client._11_x.DeviceLocationsForMission;
import lu.hitec.pss.soap.sensor.client._11_x.DeviceMission;
import lu.hitec.pss.soap.sensor.client._11_x.DeviceTypeMission;
import lu.hitec.pss.soap.sensor.client._11_x.DeviceMissionWrapper;
import lu.hitec.pss.soap.sensor.client._11_x.DeviceStatus;
import lu.hitec.pss.soap.sensor.client._11_x.LocationRange;
import lu.hitec.pss.soap.sensor.client._11_x.LocationValue;
import lu.hitec.pss.soap.sensor.client._11_x.RangeLimit;
import lu.hitec.pss.soap.sensor.client._11_x.SensorSrvClientPortBindingStub;
import lu.hitec.pss.soap.sensor.client._11_x.SensorSrvClient_Service;
import lu.hitec.pss.soap.sensor.client._11_x.SensorSrvClient_ServiceLocator;
import lu.hitec.pss.soap.sensor.client._11_x.SubRangeType;
import lu.hitec.pss.soap.sensor.client._11_x.TimeRange;

import org.apache.axis.AxisFault;

import com.enterprisehorizons.constants.CommonConstants;
import com.enterprisehorizons.exception.EHRuntimeException;
import com.enterprisehorizons.util.Logger;
import com.wfp.security.form.DeviceBean;
import com.enterprisehorizons.util.Logger;

public class SensorServiceUtils implements IEPICConstants{    
	
/*	public static List<DeviceBean> getEmergencySpotDtls( Date startDate, Date endDate, String me){
		try {
			SensorSrvClientPortBindingStub stub = getServiceLocatorStub();
			
			DeviceStatus[] devices = null;
			try {
				devices = stub.getDeviceStatus();
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<DeviceBean> allEmergencyDtls = null;
			
			if(devices != null ){
				allEmergencyDtls = new ArrayList<DeviceBean>();
				RangeLimit rl = getRangeLimit(startDate, endDate, SubRangeType.SAMPLINCONTINUOUS_LATEST	//RangeLimit rl1 = getRangeLimit(startDate, endDate, SubRangeType.CONTINUOUS_OLDEST);
				
				for(int i=0; i<devices.length;i++){
					if(!isValidDevice(me,devilayerNameces[i].getId())){
						continue;
					}
					LocationRange lr = stub.getLocationRange(devices[i].getId(), rl);
					//LocationRange lr1 = stub.getLocationRange(devices[i].getId(), rl1);
					
					setAllEmergencyHotspots(devices[i].getId(), lr, allEmergencyDtls);
					//setAllEmergencyHotspots(devices[i].getId(), lr1, allEmergencyDtls);
				}
			}
			return allEmergencyDtls;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}*/
	
	@SuppressWarnings("unchecked")
	public static Map<String, List<DeviceBean>> getEmergencySpotDtls( 
			Date startDate, Date endDate, int lpCount, Map<String, String> paramsMap )
	{
		Logger.error("START - SensorServiceUtils.getEmergencySpotDtls", SensorServiceUtils.class);
		SensorSrvClientPortBindingStub stub = null;
		Map<String, List<DeviceBean>> map =  new HashMap<String, List<DeviceBean>>();
		try {
		
			stub = getServiceLocatorStub();
			
			DeviceStatus[] devices = null;
			
			List<DeviceBean> allStaffDevices = new ArrayList<DeviceBean>();
			List<DeviceBean> allVehicleDevices = new ArrayList<DeviceBean>();
			List<DeviceBean> allAirplaneDevices = new ArrayList<DeviceBean>();
			//Vector devicesVector = new Vector();
			List<String> allMissions = LDAPUtils.getAllMissions();
			LDAPUtils.getAllDeviceInDomain();
			java.util.Map<String, DeviceStatus> deviceMap = new HashMap<String, DeviceStatus>();
			if(allMissions!=null)
			{		
				map  = new HashMap<String, List<DeviceBean>>();
				for(String mission: allMissions) 
					{
						try { 
							System.out.println("mission:"+mission);
							//devices = stub.getDeviceStatus();								
							//devicesVector.add( stub.getDeviceStatusByMission(token, mission) );
							
							addUniqueDeviceToMap(deviceMap, 
									stub.getDeviceStatusByMission(EventServiceUtils.getLDAPToken(), mission));
							
							//addDevicesToMap( startDate, endDate, lpCount, paramsMap, map, devices, stub,
								//	allStaffDevices,allVehicleDevices, allAirplaneDevices);
							
						} catch (RemoteException e) {
							Logger.error("SensorServiceUtils.getEmergencySpotDtls : Error occured while retrieving devices ["+e.getMessage()+"]", SensorServiceUtils.class);
							e.printStackTrace();
							throw new  EHRuntimeException("Error occured while retrieving devices ["+e.getMessage()+"]");
						}
					}
				
				Object obj[] = deviceMap.values().toArray();
				if( obj!=null&&obj.length>0)
				{
					List<DeviceStatus> dlist = new ArrayList<DeviceStatus>();
					//devices = new DeviceStatus[obj.length];
					devices =java.util.Arrays.copyOf( obj, obj.length, DeviceStatus[].class);
					//for(int i =0;i<obj.length;i++) devices[i] = (DeviceStatus)obj[i];
					
				}				
				
				addDevicesToMap( startDate, endDate, lpCount, paramsMap, map, devices , stub,
							allStaffDevices,allVehicleDevices, allAirplaneDevices);	
			}
			
			map.put(LAYER_STAFF, allStaffDevices);
			map.put(LAYER_VEHICLE, allVehicleDevices);
			map.put(LAYER_AIRPLANE, allAirplaneDevices);
	
		} catch (Exception e) {
			Logger.error("Error occured while retrieving devices data points ["+e.getMessage()+"]", SensorServiceUtils.class);
			e.printStackTrace();
			throw new EHRuntimeException("Error occured while retrieving device data points ["+e.getMessage()+"]");
		}finally {
			stub = null;			
		}
		
		return map;
	}
	public static boolean addUniqueDeviceToMap(Map<String, DeviceStatus> deviceMap, DeviceStatus [] dList)
	{
		boolean isDuplicate = false;
		if( deviceMap!=null && dList!=null && dList.length >0 )
		{
			for(DeviceStatus d : dList)
			{
				isDuplicate = false;
				for( Map.Entry<String,DeviceStatus> entry: deviceMap.entrySet() )
				{
					if( entry.getKey().equalsIgnoreCase(d.getId()) ) isDuplicate= true;					
				}
				//Adding DeviceStatus to Map if not found.
				if( !isDuplicate) deviceMap.put(d.getId(), d );
			}
		}		
		return isDuplicate;
	}
	
	public static void addDevicesToMap(Date startDate, Date endDate, int lpCount, Map<String, String> paramsMap,Map map,
			DeviceStatus[] devices ,SensorSrvClientPortBindingStub stub ,
			List<DeviceBean> allStaffDevices,List<DeviceBean> allVehicleDevices,List<DeviceBean> allAirplaneDevices
			)
	{
		if(devices != null )
		{			
			DeviceLocationsForMission[] deviceLocationsForMissions =null;
			LocationRange lr = null;
			SubRangeType subRangetype =null;
			if(paramsMap.get("subrangetype")!= null )
			{
				subRangetype = SubRangeType.fromString(paramsMap.get("subrangetype"));
			}			
			//RangeLimit rl1 = getRangeLimit(startDate, endDate, SubRangeType.CONTINUOUS_OLDEST);
			//System.out.println("##### devices.length :"+devices.length );
			try
			{
				RangeLimit rl = getRangeLimit(startDate, endDate, subRangetype==null?SubRangeType.CONTINUOUS_LATEST:subRangetype, lpCount);
				
				DeviceMissionWrapper deviceMissionWrapper = getDeviceMissionList(devices,paramsMap );					
				
				if(deviceMissionWrapper!=null)  
					deviceLocationsForMissions = stub.getLocationRanges(LDAPUtils.getSSOToken(), deviceMissionWrapper.getDeviceMissionList(), rl );
				
				if(deviceLocationsForMissions!=null && deviceMissionWrapper.getDeviceTypeMissionList()!=null )
				{					
					for(DeviceTypeMission deviceType : deviceMissionWrapper.getDeviceTypeMissionList() )
					{
						if(deviceType.getDeviceType().equalsIgnoreCase( LAYER_STAFF ))
						{
							setAllEmergencyHotspots(deviceType.getDeviceId(), 
									getLocationRange( deviceType.getDeviceId(), deviceLocationsForMissions), allStaffDevices, 1800001 , 1800000);
						}
						else if(deviceType.getDeviceType().equalsIgnoreCase( LAYER_VEHICLE ))
						{
								
							setAllEmergencyHotspots(deviceType.getDeviceId(),
									getLocationRange( deviceType.getDeviceId(), deviceLocationsForMissions), allVehicleDevices, 1800001 , 1800000);
						}
						else if(deviceType.getDeviceType().equalsIgnoreCase( LAYER_AIRPLANE ))
						{
								
							setAllEmergencyHotspots(deviceType.getDeviceId(), 
									getLocationRange( deviceType.getDeviceId(), deviceLocationsForMissions), allAirplaneDevices, 1800001 , 1800000);
						}
						
					}
					
				}
/*				
				for(int i=0; i<devices.length;i++)
				{
					if(devices[i].getId().contains("nrap") || devices[i].getId().contains("nreg"))	continue;
					if(LDAPUtils.validateStaff(devices[i].getId(), paramsMap.get("staffresourcetype") != null?paramsMap.get("staffresourcetype").split(","):null))
					{						
							List<String> missionList = LDAPUtils.getTrackMeMissionsList(devices[i].getId());	
							if( missionList!=null&&missionList.size()>0 ){
							lr = stub.getLocationRange( EventServiceUtils.getLDAPToken(),devices[i].getId(), missionList.get(0),rl );	
							setAllEmergencyHotspots(devices[i].getId(), lr, allStaffDevices, 1800001 , 1800000);
							}
					}
					else if(LDAPUtils.validateVehicles(devices[i].getId(), paramsMap.get("vehicleresourcetype") != null?paramsMap.get("vehicleresourcetype").split(","):null)){
						List<String> missionList = LDAPUtils.getVehiclesMissionsList(devices[i].getId());	
						if( missionList!=null&&missionList.size()>0 ){
						lr = stub.getLocationRange( LDAPUtils.getSSOToken(),devices[i].getId(), missionList.get(0),rl );
						setAllEmergencyHotspots(devices[i].getId(), lr, allVehicleDevices , 3600001 , 3600000);						
						}
						//setAllEmergencyHotspots(devices[i].getId(), lr1, allVehicleDevices);
					}else if(LDAPUtils.validatePlanes(devices[i].getId(), paramsMap.get("airplaneresourcetype") != null?paramsMap.get("airplaneresourcetype").split(","):null)){
						List<String> missionList = LDAPUtils.getTrackMeMissionsList(devices[i].getId());	
						//System.out.println("####Line:130: SensorServiceUtils: Planes : missionList : "+missionList +" device : "+devices[i].getId() );
						if( missionList!=null&&missionList.size()>0 ){
						lr = stub.getLocationRange( LDAPUtils.getSSOToken(),devices[i].getId(), missionList.get(0),rl );
						setAllEmergencyHotspots(devices[i].getId(), lr, allAirplaneDevices, 3600001 , 3600000);
						}
					}			
				}*/
			}
			catch(RemoteException e)
			{
				Logger.error("Error in (getLocationRanges) retrieving device details ", SensorServiceUtils.class.getName(), e);					
			} 
			catch( javax.naming.NamingException e)
			{
				Logger.error("Error in (getSSOToken)retrieving token details ", SensorServiceUtils.class.getName(), e);					
			}			
		}	
		
	}
	public static DeviceMissionWrapper getDeviceMissionList( DeviceStatus[] devicesList,Map<String, String> paramsMap )
	{
		DeviceMissionWrapper  deviceMissionWrapper= new DeviceMissionWrapper();		
		DeviceMission[] deviceMissionList=  new DeviceMission[devicesList.length];
		DeviceTypeMission[] deviceTypeMissionList=  new DeviceTypeMission[devicesList.length];
		DeviceMission deviceMission = null;
		DeviceTypeMission deviceTypeMission = null;	
		String deviceType=null;
		List<String> missionList =null;
		int i=0;
		for(DeviceStatus device : devicesList )
		{
			deviceMission = new DeviceMission();
			deviceTypeMission = new DeviceTypeMission();
			Object[] objArray  = new Object[1];
			
			if(device.getId().contains("nrap") || device.getId().contains("nreg"))	continue;
			
			else if(LDAPUtils.validateStaff(device.getId(), 
					paramsMap.get("staffresourcetype") != null?paramsMap.get("staffresourcetype").split(","):null))
			{			
				missionList = LDAPUtils.getTrackMeMissionsList(device.getId()); System.out.println(" : missionList :"+missionList );
				if(missionList!=null) objArray  =  missionList.toArray(); 
				deviceType= LAYER_STAFF ;
				
			}
			else if(LDAPUtils.validateVehicles(device.getId(), 
					paramsMap.get("vehicleresourcetype") != null?paramsMap.get("vehicleresourcetype").split(","):null))
			{
				missionList = LDAPUtils.getVehiclesMissionsList(device.getId());
				if(missionList!=null) objArray  = missionList.toArray(); 
				deviceType=LAYER_VEHICLE;
			}
			else if(LDAPUtils.validatePlanes(device.getId(), 
					paramsMap.get("airplaneresourcetype") != null?paramsMap.get("airplaneresourcetype").split(","):null))
			{
				missionList = LDAPUtils.getTrackMeMissionsList(device.getId());
				if(missionList!=null) objArray  = missionList.toArray(); 
				deviceType=LAYER_AIRPLANE;
			}
			deviceMission.setDeviceId(device.getId() );
			deviceMission.setMissionList(   java.util.Arrays.copyOf( objArray, objArray.length, String[].class) )  ;	
			deviceMissionList[i]=deviceMission;
			
			deviceTypeMission.setDeviceId(device.getId() );
			deviceTypeMission.setMissionList( deviceMission.getMissionList() );
			deviceTypeMission.setDeviceType(deviceType );
			deviceTypeMissionList[i]=deviceTypeMission;
			
			i++;
		}
		deviceMissionWrapper.setDeviceMissionList(deviceMissionList);
		deviceMissionWrapper.setDeviceTypeMissionList(deviceTypeMissionList);
		
		return deviceMissionWrapper;
	}
	public static LocationRange getLocationRange(String deviceId, DeviceLocationsForMission[] deviceLocationsForMissions)
	{			
		if( deviceLocationsForMissions!=null && deviceLocationsForMissions.length >0 && deviceId!=null )
		{
			for( DeviceLocationsForMission d : deviceLocationsForMissions )
			{
				if( d.getDeviceId().equalsIgnoreCase(deviceId )) return d.getDtoLocationRange(); 
			}
		}		
		return null;		
	}	
/*	public static boolean isValidDevice(String layerName,
			String deviceId) {
		Logger.info("Checking whether ["+deviceId+"] a valid device for layer ["+layerName+"]", SensorServiceUtils.class);
		if(layerName.contains(LAYER_STAFF) && deviceId.contains(DEVICE_STAFF))
		{
			return true;
		}else if(layerName.contains(LAYER_VEHICLE) && deviceId.contains(DEVICE_VEHICLE)){
			return true;
		}else if(layerName.contains(LAYER_AIRPLANE) && 
				!(deviceId.contains(DEVICE_STAFF)|| deviceId.contains(DEVICE_VEHICLE) )){
			return true;
		}
		return false;
	}

	*/

	private static void setAllEmergencyHotspots(String deviceId, LocationRange lr, 
			List<DeviceBean> allEmergencyDtls, long diff, long allowed ) 
	{
		// TODO Auto-generated method stub		
		LocationValue[] lv = lr != null ?lr.getVal():null;
		//String offset = "0";
		if(lv != null)
		{	
			for (int j=0; j < lv.length; j++)
			{	
					DeviceBean in = new DeviceBean();					
					LocationValue loc1 = lv[j];
					
					if(j == 0){						
						in.setStartPoint(true); 
						//offset=  CommonUtils.getOffsetByLatLong(String.valueOf(lv[j].getLat()), String.valueOf(lv[j].getLng()));
					}
					else if(j == (lv.length -1 )){ in.setEndPoint(true); diff =allowed+1; }					
					else  diff= lv[j+1].getTime().getTime().getTime()- loc1.getTime().getTime().getTime();	
					
					//if( diff < allowed ) continue;
					
					in.setLatitude(String.valueOf(lv[j].getLat()));
					in.setLongitude(String.valueOf(lv[j].getLng()));
					
					if(j < lv.length-1 ){
						in.setCoordStr(in.getLongitude()+CommonConstants.COMMA_STRING+in.getLatitude()+
							CommonConstants.COMMA_STRING+"0 "+String.valueOf(lv[j+1].getLng())+","+String.valueOf(lv[j+1].getLat()+",0"));
					}else{
						in.setCoordStr(in.getLongitude()+CommonConstants.COMMA_STRING+in.getLatitude()+
								CommonConstants.COMMA_STRING+"0");
					}
					
					in.setName(deviceId);
					in.setDatetime(loc1.getTime().getTime());
					String datetime = CommonUtils.formatDate(loc1.getTime().getTime());
					in.setTime(datetime);
					//in.setDeviceLocalTime( CommonUtils.getLocalTime(offset, datetime, NEW_PORTAL_DATE_FORMAT ) );
					in.setDeviceLocalTime(datetime);					
					in.setLocationValue(loc1);
					
					LDAPUtils.setLDAPUserDtls(in);
					allEmergencyDtls.add(in);	
					
			
			}
		}
	}


	/*public static List<DeviceBean> getEmergencySpotDtls(String[] deviceIds, Date startDate, Date endDate, String string){
		try {
			
			SensorSrvClientPortBindingStub stub = getServiceLocatorStub();
			
			List<DeviceBean> allEmergencyDtls = null;
			
			if(deviceIds != null){
				allEmergencyDtls = new ArrayList<DeviceBean>();				
				RangeLimit rl = getRangeLimit(startDate, endDate, SubRangeType.CONTINUOUS_LATEST);
				RangeLimit rl1 = getRangeLimit(startDate, endDate, SubRangeType.CONTINUOUS_OLDEST);
				for(int i =0; i<deviceIds.length;i++){
					LocationRange lr = stub.getLocationRange(deviceIds[i], rl);
					LocationRange lr1 = stub.getLocationRange(deviceIds[i], rl1);
					setAllEmergencyHotspots(deviceIds[i], lr, allEmergencyDtls);
					setAllEmergencyHotspots(deviceIds[i], lr1, allEmergencyDtls);
				}
				
			}
			
			return allEmergencyDtls;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}*/

	public static  RangeLimit getRangeLimit(Date startDate, Date endDate, SubRangeType subRangeType, int lpCount){
		TimeRange tr = new TimeRange();
		if(startDate != null){
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			//cal.add(Calendar.MONTH, -1);
			tr.setStart(cal);
		}
		
		
		if(endDate != null){
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(endDate);
			tr.setEnd(cal1);
		}
		
		
		RangeLimit rl = new RangeLimit();
		rl.setTimeRange(tr);
		rl.setMaxValues(lpCount); 
		return rl;
	}
	
	public static SensorSrvClientPortBindingStub  getServiceLocatorStub(){   
		Logger.error("START - SensorServiceUtils.getServiceLocatorStub", SensorServiceUtils.class);
		System.out.println("START - SensorServiceUtils.getServiceLocatorStub"+WFPConfigUtils.getWFPConfigValue("soapgps") );
		// AxisProperties.setProperty("axis.socketSecureFactory","com.spacetimeinsght.webservice.ssl.factory.CertSSLSocketFactory");
		SensorSrvClient_Service service =  new SensorSrvClient_ServiceLocator();		
		System.out.println("service : "+service );
		SensorSrvClientPortBindingStub sensorSrvClientPortBindingStub=null;
		try {
			sensorSrvClientPortBindingStub = new SensorSrvClientPortBindingStub( 
					new java.net.URL(WFPConfigUtils.getWFPConfigValue("soapgps") == null?"http://middleware-qa.service.emergency.lu/sensorservice/out/soap/SensorSrvClient":WFPConfigUtils.getWFPConfigValue("soapgps")),service) ;
		} catch (AxisFault e) {
			Logger.error("Error occured while creating stub ["+e.getMessage()+"]", SensorServiceUtils.class);
			e.printStackTrace();
		} catch (MalformedURLException e) {
			Logger.error("Error occured while creating stub ["+e.getMessage()+"]", SensorServiceUtils.class);
			e.printStackTrace();
		}
		System.out.println("sensorSrvClientPortBindingStub : "+sensorSrvClientPortBindingStub );
		return sensorSrvClientPortBindingStub;
		
	}
	public static void main(String args[]){
		//List list = getEmergencySpotDtls( null, null, "Staff");
		//System.out.println(list);
	}
}

