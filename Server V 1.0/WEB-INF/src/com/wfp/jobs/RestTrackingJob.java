package com.wfp.jobs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.jdom.Element;

import com.enterprisehorizons.util.Logger;
import com.enterprisehorizons.util.StringUtils;
import com.enterprisehorizons.util.XMLUtils;
import com.spacetimeinsight.config.scheduler.Parameter;
import com.spacetimeinsight.config.scheduler.Parameters;
import com.spacetimeinsight.magma.job.CustomJobTask;
import com.wfp.security.form.DeviceBean;
import com.wfp.utils.CommonUtils;
import com.wfp.utils.IEPICConstants;
import com.wfp.utils.LDAPUtils;
import com.wfp.utils.ValidateCertificateCall;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Node;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
/**
 * 
 * @author sti-user
 *
 */
public class RestTrackingJob implements CustomJobTask,IEPICConstants {
	
	private static RestTrackingJob instance = null;
	@SuppressWarnings("unchecked")
	private static Map<String, List> restServiceMap = new HashMap<String, List>();
	private static String lastRefreshTime = null;
	private static Map<String, String> paramsMap = new HashMap();
	public RestTrackingJob () {
			
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
	
	public boolean executeCustomTask(Parameters parameters) {
		System.out.println("####### REST START - TimeStamp: "+ CommonUtils.getUTCdatetimeAsString());
		
		//if( LDAPUtils.getOrgMap()==null ) LDAPUtils.getAllOrganizations();		
	
		Parameter[] params = parameters.getParameter();

		for (int i=0; i< params.length ; i++){
			paramsMap.put(params[i].getName(), params[i].getValue());	
		}
		
		getRestTrackingDtls();
		lastRefreshTime = CommonUtils.getUTCdatetimeAsString();
		
		System.out.println("####### REST END - TimeStamp: "+ lastRefreshTime);
		return true;
	}

	private void getRestTrackingDtls()
	{
		List<DeviceBean> staffList =null;
		List<DeviceBean> vehicleList =null;
		List<DeviceBean> airplaneList =null;
		Element rootNode = null;
		String expression = null;
		int count=0;
		String xmlString = ValidateCertificateCall.callSecureURI();
		List<String> ldapDeviceList = LDAPUtils.getLdapDeviceList();
		
		if(StringUtils.isNull(xmlString)){
			return ;
		}
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
		for (int i=0;i <list.size(); i++)
		{
			Element element = list.get(i); int size = element.getContentSize();
			//System.out.println(" 1: "+ element.getContent(size-1).getValue() );
			String deviceId = element.getContent(size-1).getValue();
			
			
			if( deviceId!=null && ( ldapDeviceList==null || (ldapDeviceList!=null&& ldapDeviceList.contains(deviceId ) ) ) )
			{
				
					count++;
				
					if(LDAPUtils.validateVehicles(deviceId,
							paramsMap.get("vehicleresourcetype") != null?
									paramsMap.get("vehicleresourcetype").split(","):null))
					{
						addDevice(element, vehicleList, KEY_VEHICLE); 
					}else if(LDAPUtils.validateStaff(deviceId,	
							paramsMap.get("staffresourcetype") != null?paramsMap.get("staffresourcetype").split(","):null)){
						addDevice(element, staffList,KEY_STAFF); 
					}else if(LDAPUtils.validatePlanes(deviceId, paramsMap.get("airplaneresourcetype") != null?paramsMap.get("airplaneresourcetype").split(","):null)){
						addDevice(element, airplaneList, KEY_AIRPLANE); 
					}
				
			}
			
		
			/*if(LDAPUtils.validateVehicles(element.getContent(size-1).getValue(),
					paramsMap.get("vehicleresourcetype") != null?paramsMap.get("vehicleresourcetype").split(","):null))
			{
				addDevice(element, vehicleList, KEY_VEHICLE); 
			}else if(LDAPUtils.validateStaff(element.getContent(size-1).getValue(),
					paramsMap.get("staffresourcetype") != null?paramsMap.get("staffresourcetype").split(","):null)){
				addDevice(element, staffList,KEY_STAFF); 
			}else if(LDAPUtils.validatePlanes(element.getContent(size-1).getValue(), paramsMap.get("airplaneresourcetype") != null?paramsMap.get("airplaneresourcetype").split(","):null)){
				addDevice(element, airplaneList, KEY_AIRPLANE); 
			}*//*else if(! (element.getContent(1).getValue().startsWith(DEVICE_VEHICLE) || element.getContent(1).getValue().startsWith(DEVICE_STAFF)
					|| element.getContent(1).getValue().contains("nrap") || element.getContent(1).getValue().contains("nreg"))){
				addDevice(element, airplaneList);
			}*/
			
		}
		String getAllDevices = paramsMap.get("getAllVehicleDevices") ;
		System.out.println("***  ********** paramsMap.get(getAllVehicleDevices) ::"+paramsMap.get("getAllVehicleDevices") );
		System.out.println("ldapDeviceList : "+ldapDeviceList.size()+": count :"+count );
		getRestServiceMapCache().clear();
		
		if(getAllDevices!=null&&getAllDevices.equals("false"))getRestServiceMapCache().put(KEY_VEHICLE,  removeSecondaryDevices( vehicleList ) );		
		else getRestServiceMapCache().put(KEY_VEHICLE, vehicleList);
		 
		getRestServiceMapCache().put(KEY_STAFF, staffList);
		getRestServiceMapCache().put(KEY_AIRPLANE, airplaneList);
		
		//Cache.store(KEY_STAFF, staffList);
		
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
	private void addDevice( Element element, List indigoList, String type ) {
		/*if(!SensorServiceUtils.isValidDevice(dataSource.getLayerName(), element.getContent(1).getValue())){
			return;
		}*/
		if(type.equals(KEY_VEHICLE))
		{
			if(indigoList!=null&&indigoList.size()>0)
			{
				for( Object o : indigoList )
				{
					DeviceBean d = (DeviceBean)o;
					if( d.getName().equals(element.getContent(0).getValue()) )
						{
									System.out.println(" found :"+ d.getName() );
						}
					
					
				}
			}
		}
		DeviceBean is = new DeviceBean(); 
		is.setLatitude(element.getAttribute(ATTR_LAT).getValue());
		is.setLongitude(element.getAttribute(ATTR_LNG).getValue());
		is.setTime(element.getContent(0).getValue());
		is.setName(element.getContent(element.getContentSize()-1).getValue());
		//Commented Localtime calculation
		/*if(is.getLatitude()!=null &&is.getLongitude()!=null)
		is.setDeviceLocalTime( CommonUtils.getTimeZoneByLatLong(is.getLatitude(),is.getLongitude(),
				is.getTime(),EPIC_DATE_FORMAT) );*/
		is.setDeviceLocalTime( is.getTime() );
		
		LDAPUtils.setLDAPUserDtls(is);
		if(is.getCn()!=null&&!is.getCn().isEmpty()) indigoList.add(is);
		//System.out.println(" Name :"+ is.getName() );
	}
	
	public static String getLastRefreshTime() {
		return lastRefreshTime;
	}
	
	
	
}
