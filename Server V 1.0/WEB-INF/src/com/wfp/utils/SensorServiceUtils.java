package com.wfp.utils;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lu.hitec.pss.soap.sensor.client._8_x.DeviceStatus;
import lu.hitec.pss.soap.sensor.client._8_x.LocationRange;
import lu.hitec.pss.soap.sensor.client._8_x.LocationValue;
import lu.hitec.pss.soap.sensor.client._8_x.RangeLimit;
import lu.hitec.pss.soap.sensor.client._8_x.SensorSrvClientPortBindingStub;
import lu.hitec.pss.soap.sensor.client._8_x.SensorSrvClient_Service;
import lu.hitec.pss.soap.sensor.client._8_x.SensorSrvClient_ServiceLocator;
import lu.hitec.pss.soap.sensor.client._8_x.SubRangeType;
import lu.hitec.pss.soap.sensor.client._8_x.TimeRange;

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
	public static Map<String, List<DeviceBean>> getEmergencySpotDtls( Date startDate, Date endDate, int lpCount, Map<String, String> paramsMap){
		Logger.error("START - SensorServiceUtils.getEmergencySpotDtls", SensorServiceUtils.class);
		//System.out.println("START - SensorServiceUtils.getEmergencySpotDtls");
		SensorSrvClientPortBindingStub stub = null;
		try {
			Map<String, List<DeviceBean>> map = null;
			stub = getServiceLocatorStub();
			List<String> allMissions = LDAPUtils.getAllMissions();
			if(allMissions!=null){				
				for(String mission: allMissions) System.out.println("Mission :"+mission );
			}
			//System.out.println("##### stub :"+stub );
			DeviceStatus[] devices = null;
			try { 
				devices = stub.getDeviceStatus();
				//stub.getDeviceStatusByMission(token, missionName)
				//System.out.println("##### devices :"+devices );
			} catch (RemoteException e) {
				Logger.error("SensorServiceUtils.getEmergencySpotDtls : Error occured while retrieving devices ["+e.getMessage()+"]", SensorServiceUtils.class);
				e.printStackTrace();
				throw new  EHRuntimeException("Error occured while retrieving devices ["+e.getMessage()+"]");
			}
			 
			if(devices != null ){
				map  = new HashMap<String, List<DeviceBean>>();
				List allStaffDevices = new ArrayList<DeviceBean>();
				List allVehicleDevices = new ArrayList<DeviceBean>();
				List allAirplaneDevices = new ArrayList<DeviceBean>();
				
				SubRangeType subRangetype =null;
				if(paramsMap.get("subrangetype")!= null ){
					subRangetype = SubRangeType.fromString(paramsMap.get("subrangetype"));
				}
				
				RangeLimit rl = getRangeLimit(startDate, endDate, subRangetype==null?SubRangeType.CONTINUOUS_LATEST:subRangetype, lpCount);
				//RangeLimit rl1 = getRangeLimit(startDate, endDate, SubRangeType.CONTINUOUS_OLDEST);
				//System.out.println("##### devices.length :"+devices.length );
				for(int i=0; i<devices.length;i++){
					if(devices[i].getId().contains("nrap") || devices[i].getId().contains("nreg")){
						continue;
					}
					LocationRange lr = null;
					try{
						
						if(LDAPUtils.validateStaff(devices[i].getId(), paramsMap.get("staffresourcetype") != null?paramsMap.get("staffresourcetype").split(","):null))
						{						
								List<String> missionList = LDAPUtils.getTrackMeMissionsList(devices[i].getId());	
								if( missionList!=null&&missionList.size()>0 ){
								lr = stub.getLocationRange( LDAPUtils.getSSOToken(),devices[i].getId(), missionList.get(0),rl );	
								setAllEmergencyHotspots(devices[i].getId(), lr, allStaffDevices);
								}
						}
						else if(LDAPUtils.validateVehicles(devices[i].getId(), paramsMap.get("vehicleresourcetype") != null?paramsMap.get("vehicleresourcetype").split(","):null)){
							List<String> missionList = LDAPUtils.getVehiclesMissionsList(devices[i].getId());	
							if( missionList!=null&&missionList.size()>0 ){
							lr = stub.getLocationRange( LDAPUtils.getSSOToken(),devices[i].getId(), missionList.get(0),rl );
							setAllEmergencyHotspots(devices[i].getId(), lr, allVehicleDevices);
							System.out.println(" DeviceId : "+devices[i].getId() +"missionList.get(0) : "+missionList.get(0) );
							}
							//setAllEmergencyHotspots(devices[i].getId(), lr1, allVehicleDevices);
						}else if(LDAPUtils.validatePlanes(devices[i].getId(), paramsMap.get("airplaneresourcetype") != null?paramsMap.get("airplaneresourcetype").split(","):null)){
							List<String> missionList = LDAPUtils.getTrackMeMissionsList(devices[i].getId());	
							//System.out.println("####Line:130: SensorServiceUtils: Planes : missionList : "+missionList +" device : "+devices[i].getId() );
							if( missionList!=null&&missionList.size()>0 ){
							lr = stub.getLocationRange( LDAPUtils.getSSOToken(),devices[i].getId(), missionList.get(0),rl );
							setAllEmergencyHotspots(devices[i].getId(), lr, allAirplaneDevices);
							}					
							
						}
			
						
					}catch(Exception e){
						Logger.error("Error in retrieving device details ["+devices[i].getId()+"]", SensorServiceUtils.class.getName(), e);
						System.out.println(" ###### Error in retrieving device details ["+devices[i].getId()+"]");
						continue;
					}
					/*if( lr!=null){
					if(LDAPUtils.validateStaff(devices[i].getId(), paramsMap.get("staffresourcetype") != null?paramsMap.get("staffresourcetype").split(","):null)){
						setAllEmergencyHotspots(devices[i].getId(), lr, allStaffDevices);
						//setAllEmergencyHotspots(devices[i].getId(), lr1, allStaffDevices);
					}else if(LDAPUtils.validateVehicles(devices[i].getId(), paramsMap.get("vehicleresourcetype") != null?paramsMap.get("vehicleresourcetype").split(","):null)){
						setAllEmergencyHotspots(devices[i].getId(), lr, allVehicleDevices);
						//setAllEmergencyHotspots(devices[i].getId(), lr1, allVehicleDevices);
					}else if(LDAPUtils.validatePlanes(devices[i].getId(), paramsMap.get("airplaneresourcetype") != null?paramsMap.get("airplaneresourcetype").split(","):null)){
						setAllEmergencyHotspots(devices[i].getId(), lr, allAirplaneDevices);
						//setAllEmergencyHotspots(devices[i].getId(), lr1,  allAirplaneDevices);
					}
					}*/
					
					
				}
				
				map.put(LAYER_STAFF, allStaffDevices);
				map.put(LAYER_VEHICLE, allVehicleDevices);
				map.put(LAYER_AIRPLANE, allAirplaneDevices);
			}
			return map;
			
		} catch (Exception e) {
			Logger.error("Error occured while retrieving devices data points ["+e.getMessage()+"]", SensorServiceUtils.class);
			e.printStackTrace();
			throw new EHRuntimeException("Error occured while retrieving device data points ["+e.getMessage()+"]");
		}finally {
			stub = null;
			
		}
		//return null;
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
	
	
	private static void setAllEmergencyHotspots(String deviceId, LocationRange lr, List<DeviceBean> allEmergencyDtls) {
		// TODO Auto-generated method stub
		
		LocationValue[] lv = lr != null ?lr.getVal():null;
		
		if(lv != null){
			
			for (int j=0; j < lv.length; j++){
				DeviceBean in = new DeviceBean();
				if(j == 0){
					in.setStartPoint(true);
				}else if(j == (lv.length -1 )){
					in.setEndPoint(true);
				}
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
				//System.out.println("Time "+lv[j].getTime().getTime());
				in.setDatetime(lv[j].getTime().getTime());
				String datetime = CommonUtils.formatDate(lv[j].getTime().getTime());
				in.setTime(datetime);
				//System.out.println(" datetime:"+datetime +": SENSOR_WS_DATE_FORMAT "+ SENSOR_WS_DATE_FORMAT );
				in.setDeviceLocalTime(  CommonUtils.getTimeZoneByLatLong(in.getLatitude(),in.getLongitude(),datetime, NEW_PORTAL_DATE_FORMAT ) );
				//System.out.println(" in.getTime:"+in.getDeviceLocalTime() );
				in.setLocationValue(lv[j]);
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

