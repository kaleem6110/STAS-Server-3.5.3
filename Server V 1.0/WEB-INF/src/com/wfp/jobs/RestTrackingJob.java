package com.wfp.jobs;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lu.hitec.pss.soap.ds.out._15_x.DirectoryServiceOutInterface_ServiceLocator;
import lu.hitec.pss.soap.sensor.client._12_x.LocationRange;
import lu.hitec.pss.soap.sensor.client._12_x.RangeLimit;
import lu.hitec.pss.soap.sensor.client._12_x.SubRangeType;
import lu.hitec.pss.soap.sensor.client._12_x.UnitId;
import lu.hitec.pss.soap.sensor.client._12_x.UnitType;

import org.jdom.Element;

import com.enterprisehorizons.magma.ecosystem.model.Cache;
import com.enterprisehorizons.util.StringUtils;
import com.enterprisehorizons.util.XMLUtils;
import com.spacetimeinsight.config.scheduler.Parameter;
import com.spacetimeinsight.config.scheduler.Parameters;
import com.spacetimeinsight.magma.job.CustomJobTask;

import com.wfp.security.form.DeviceBean;
import com.wfp.utils.CommonUtils;
import com.wfp.utils.EventServiceUtils;
import com.wfp.utils.IEPICConstants;
import com.wfp.utils.LDAPUtils;
import com.wfp.utils.SensorServiceUtils;
import com.wfp.utils.ValidateCertificateCall;
/**
 * 
 * @author sti-user
 *
 */
public class RestTrackingJob implements CustomJobTask,IEPICConstants 
{
	
	private static RestTrackingJob instance = null;
	@SuppressWarnings("unchecked")
	private static Map<String, List> restServiceMap = new HashMap<String, List>();
	private static String lastRefreshTime = null;
	private static Map<String, String> paramsMap = new HashMap();
	private static Map<String, String> deviceOffset = new HashMap();
	public RestTrackingJob () {}
	
	/* (non-Javadoc)
	 * @see com.spacetimeinsight.magma.job.CustomJobTask#executeCustomTask(com.spacetimeinsight.config.scheduler.Parameters)
	 */
	public boolean executeCustomTask(Parameters parameters) 
	{
		System.out.println("####### REST START - TimeStamp: "+ CommonUtils.getUTCdatetimeAsString());		
		//if( LDAPUtils.getOrgMap()==null ) LDAPUtils.getAllOrganizations();
		Parameter[] params = parameters.getParameter();
		for (int i=0; i< params.length ; i++) paramsMap.put(params[i].getName(), params[i].getValue());				
		getRestTrackingDtls();
		lastRefreshTime = CommonUtils.getUTCdatetimeAsString();		
		System.out.println("####### REST END - TimeStamp: "+ lastRefreshTime);
		return true;
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void getRestTrackingDtls()
	{
		List<DeviceBean> staffList =null;
		List<DeviceBean> vehicleList =null;
		List<DeviceBean> airplaneList =null;
		Element rootNode = null;
		String expression = null;
		//getRestServiceMapCache().clear();
		//Retreives all devices from the REST as xmlString.
		String xmlString = ValidateCertificateCall.callSecureURI();		

		//Getting all the devices from the LDAP.
		List<String> ldapDeviceList =( List<String>)LDAPCacheJob.getLDAPCacheData(PARAM_ALLGROUPS);	
		
		if(StringUtils.isNull(xmlString)) return ;		
		rootNode = XMLUtils.getRootNode(xmlString,true);
		expression = PARAM_XPR;	
		
		List<Element> list =  XMLUtils.evaluate(rootNode, expression);		
		if(list != null)
		{
			System.out.println(" list size:"+list.size() );
			staffList = new ArrayList<DeviceBean>();
			vehicleList =  new ArrayList<DeviceBean>();
			airplaneList = new ArrayList<DeviceBean>();
		}
		else  return;
		//Object lists = getDataInput();
		
		//Iterate the list obtained from REST.
		for (int i=0;i <list.size(); i++)
		{
			Element element = list.get(i); int size = element.getContentSize();
			String deviceId = element.getContent(size-1).getValue();			
			//check the device is added in LDAP.
			if( deviceId!=null && ( ldapDeviceList==null || (ldapDeviceList!=null&& ldapDeviceList.contains(deviceId.trim() ) ) ) )
			{
				if(  LDAPUtils.validateVehicle(deviceId ) )	addDevice(element, vehicleList, KEY_VEHICLE);
				else if( LDAPUtils.validateStaff(deviceId)) addDevice(element, staffList,KEY_STAFF); 
				else if( LDAPUtils.validatePlane(deviceId ))addDevice(element, airplaneList, KEY_AIRPLANE); 
				//list unassigned devices and send email if required.
			}
		}
		String getAllDevices = paramsMap.get("getAllVehicleDevices") ;		
		if(getAllDevices!=null&&getAllDevices.equals("false"))
				getRestServiceMapCache().put(KEY_VEHICLE,  removeSecondaryDevices( vehicleList ) );		
		else
				getRestServiceMapCache().put(KEY_VEHICLE, vehicleList);
		 
		getRestServiceMapCache().put(KEY_STAFF, staffList);
		getRestServiceMapCache().put(KEY_AIRPLANE, airplaneList);		
		if( ! (LDAPCacheJob.getDeviceOffsetMap().size() >0 ) )		
				LDAPCacheJob.setDeviceOffsetMap( deviceOffset  );
		
	}
	public List<DeviceBean> removeSecondaryDevices(List<DeviceBean> vehicleList)
	{
		List<DeviceBean> list = null;
		if( vehicleList!=null&&vehicleList.size()>0)
		{
			 list = new ArrayList<DeviceBean>();
			for( int i=0;i<vehicleList.size()-1;i++ )
			{ 	int ok=0;
				for(int j=i+1;j<vehicleList.size();j++ )
				{
					if(vehicleList.get(i).getCn().equals(vehicleList.get(j).getCn() ) )
					{						
						ok=1;
						System.out.println(" ** Found : "+vehicleList.get(i).getCn() +" has multiple devices ****");
						System.out.println(vehicleList.get(i).getTime() + ":****: " +vehicleList.get(j).getTime() );
						if( CommonUtils.parseDate(vehicleList.get(i).getTime(), EPIC_DATE_FORMAT).after(CommonUtils.parseDate(vehicleList.get(j).getTime() ) ) )
							list.add(vehicleList.get(i));
						else  list.add(vehicleList.get(j));
						break;	
					}
					
				}
				if(ok==0)list.add(vehicleList.get(i));
			}
		} System.out.println(" removeSecondaryDevices : list : size : "+list.size());
		return list;
	}
	@SuppressWarnings("unchecked")
	private void addDevice( Element element, List indigoList, String type ) 
	{
		String offset="";		
		DeviceBean is = new DeviceBean(); 
		is.setLatitude(element.getAttribute(ATTR_LAT).getValue());
		is.setLongitude(element.getAttribute(ATTR_LNG).getValue());
		is.setTime(element.getContent(0).getValue());
		is.setName(element.getContent(element.getContentSize()-1).getValue());		
		//Commented Localtime calculation
		if( LDAPCacheJob.getDeviceOffsetMap().size() >0 && LDAPCacheJob.getDeviceOffsetMap().containsKey(is.getName() ) )
		{
			offset= LDAPCacheJob.getDeviceOffsetMap().get( is.getName() );
			is.setDeviceLocalTime( CommonUtils.getLocalTime(offset ,is.getTime(),EPIC_DATE_FORMAT) ) ;
		}
		else
		{   
			if(is.getLatitude()!=null &&is.getLongitude()!=null)
			{	//calculating the device offset.
				offset = CommonUtils.getOffsetByLatLong(is.getLatitude(),is.getLongitude() );
				is.setDeviceLocalTime( CommonUtils.getLocalTime( offset,is.getTime(),EPIC_DATE_FORMAT) );
			}				
		}		
		//is.setDeviceLocalTime( is.getTime() );		
		LDAPUtils.setLDAPUserDtls(is);
		if(is.getCn()!=null&&!is.getCn().isEmpty()) indigoList.add(is);
		//Storing the offset for each device as Map.
		if( is.getName()!=null )deviceOffset.put(is.getName(), offset );
		
	}

	public static Calendar getStartDate(){
		Calendar startDate = Calendar.getInstance();
		startDate.set(Calendar.MONTH, 9 );
		startDate.set(Calendar.YEAR, 2013 );
		startDate.set(Calendar.DATE, 1 );
		startDate.set(Calendar.MINUTE, 0);
		startDate.set(Calendar.SECOND, 0);
		startDate.set(Calendar.HOUR, 0);
		
		return startDate;
	}
	
	public static String getLastRefreshTime() {
		return lastRefreshTime;
	}
	
	private static synchronized void initializeInstance() {
		if (instance == null) {
			instance = new RestTrackingJob();
		}
	}

	public static RestTrackingJob getInstance() {
		if (instance == null) {
			initializeInstance();
		}
		return instance;
	}
	
	
	public  Map<String, List> getRestServiceMapCache() {
		return restServiceMap;
	}

	
	public  List<DeviceBean> getRestServiceList(String key){
		return getRestServiceMapCache().get(key);
	}
	
}
