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
		
		if( LDAPUtils.getOrgMap()==null ) LDAPUtils.getAllOrganizations();		
	
		Parameter[] params = parameters.getParameter();

		for (int i=0; i< params.length ; i++){
			paramsMap.put(params[i].getName(), params[i].getValue());	
		}
		
		getRestTrackingDtls();
		lastRefreshTime = CommonUtils.getUTCdatetimeAsString();
		return true;
	}

	private void getRestTrackingDtls(){
		List<DeviceBean> staffList =null;
		List<DeviceBean> vehicleList =null;
		List<DeviceBean> airplaneList =null;
		Element rootNode = null;
		String expression = null;
		
		String xmlString = ValidateCertificateCall.callSecureURI();
		
		if(StringUtils.isNull(xmlString)){
			return ;
		}
		rootNode = XMLUtils.getRootNode(xmlString,true);
		expression = PARAM_XPR;
	
		
		List<Element> list =  XMLUtils.evaluate(rootNode, expression);
		
		if(list != null){
			System.out.println(" list size:"+list.size() );
			staffList = new ArrayList<DeviceBean>();
			vehicleList =  new ArrayList<DeviceBean>();
			airplaneList = new ArrayList<DeviceBean>();
		}else {
			return;
		}
		//Object lists = getDataInput();
		for (int i=0;i <list.size(); i++){
			Element element = list.get(i); 
		
			if(LDAPUtils.validateVehicles(element.getContent(1).getValue(), paramsMap.get("vehicleresourcetype") != null?paramsMap.get("vehicleresourcetype").split(","):null)){
				addDevice(element, vehicleList, KEY_VEHICLE); 
			}else if(LDAPUtils.validateStaff(element.getContent(1).getValue(), paramsMap.get("staffresourcetype") != null?paramsMap.get("staffresourcetype").split(","):null)){
				addDevice(element, staffList,KEY_STAFF); 
			}else if(LDAPUtils.validatePlanes(element.getContent(1).getValue(), paramsMap.get("airplaneresourcetype") != null?paramsMap.get("airplaneresourcetype").split(","):null)){
				addDevice(element, airplaneList, KEY_AIRPLANE); 
			}/*else if(! (element.getContent(1).getValue().startsWith(DEVICE_VEHICLE) || element.getContent(1).getValue().startsWith(DEVICE_STAFF)
					|| element.getContent(1).getValue().contains("nrap") || element.getContent(1).getValue().contains("nreg"))){
				addDevice(element, airplaneList);
			}*/
			
		}
		
		getRestServiceMapCache().put(KEY_STAFF, staffList);
		getRestServiceMapCache().put(KEY_VEHICLE, vehicleList);
		getRestServiceMapCache().put(KEY_AIRPLANE, airplaneList);
		
		//Cache.store(KEY_STAFF, staffList);
		
	}
	
	@SuppressWarnings("unchecked")
	private void addDevice( Element element, List indigoList, String type ) {
		/*if(!SensorServiceUtils.isValidDevice(dataSource.getLayerName(), element.getContent(1).getValue())){
			return;
		}*/
		DeviceBean is = new DeviceBean();
		is.setLatitude(element.getAttribute(ATTR_LAT).getValue());
		is.setLongitude(element.getAttribute(ATTR_LNG).getValue());
		is.setTime(element.getContent(0).getValue());
		is.setName(element.getContent(1).getValue());
		//Commented Localtime calculation
		/*if(is.getLatitude()!=null &&is.getLongitude()!=null)
		is.setDeviceLocalTime( CommonUtils.getTimeZoneByLatLong(is.getLatitude(),is.getLongitude(),
				is.getTime(),EPIC_DATE_FORMAT) );*/
		is.setDeviceLocalTime( is.getTime() );
		indigoList.add(is);
		LDAPUtils.setLDAPUserDtls(is);
		//System.out.println(" Name :"+ is.getName() );
	}
	
	public static String getLastRefreshTime() {
		return lastRefreshTime;
	}
	
	
	
}
