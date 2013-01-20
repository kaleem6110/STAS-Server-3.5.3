package com.wfp.custom.datasource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdom.Element;

import com.enterprisehorizons.exception.EHRuntimeException;
import com.enterprisehorizons.magma.datamashup.BaseGeoDataDriver;
import com.enterprisehorizons.magma.datamashup.IMashupData;
import com.enterprisehorizons.magma.datamashup.IScriptableDataDriver;
import com.enterprisehorizons.magma.designtime.artifact.IScripter;
import com.enterprisehorizons.util.DateUtils;
import com.enterprisehorizons.util.Logger;
import com.enterprisehorizons.util.SearchCriteria;
import com.enterprisehorizons.util.SearchCriteriaHelper;
import com.enterprisehorizons.util.StringUtils;
import com.wfp.jobs.RestTrackingJob;
import com.wfp.security.form.DeviceBean;
import com.wfp.utils.CommonUtils;
import com.wfp.utils.IEPICConstants;
import com.wfp.utils.SensorServiceUtils;

/**
 * Rest Service handler which fetch & saves the details of all the known devices in cache.
 * This acts as a main datasource for STAFF/Vehicle/Airplane tracking ecosystems. This class stores 
 * all the data of devices in cache with Key "$REST_Service$"
 * @author sti-user
 *
 */
public class CustomWebServiceDataDriver extends BaseGeoDataDriver implements IScriptableDataDriver, IEPICConstants {

	private CustomWebServiceDataSource dataSource = null;
	//private Object dataList[][] = null;
	private IScripter scripter;
	private Object response = null;

	/*
	 * Constructor initializes datasource by invoking a local call to initialise method.
	 */
	public CustomWebServiceDataDriver(CustomWebServiceDataSource datasource) {
		super(datasource);
		this.dataSource = datasource; 
		/*if(getRestServiceCacheMap() == null){
			Cache.store(CACHE_REST_SERVICE, new HashMap<String, DeviceBean>());
		}*/
		
	}
	
/*	private Map<String, DeviceBean> getRestServiceCacheMap(){
		return (Map<String, DeviceBean>) Cache.retrieve(CACHE_REST_SERVICE);
	}*/

	

	/*
	 * (non-Javadoc)
	 * @see com.enterprisehorizons.magma.datamashup.IDataDriver#getData()
	 */
	@SuppressWarnings("unchecked")
	public List getData() {
		try {
			List<DeviceBean> indigoList = new ArrayList();
			   System.out.println("");
			List<DeviceBean> currentDeviceList = null;
			setCustomSearchCriteria();
			if(dataSource.getLayerName().toLowerCase().contains(KEY_STAFF)){
				currentDeviceList = RestTrackingJob.getInstance().getRestServiceList(KEY_STAFF);				
			}else if(dataSource.getLayerName().toLowerCase().contains(KEY_VEHICLE)){
				currentDeviceList =  RestTrackingJob.getInstance().getRestServiceList("vehicle");
			}else if(dataSource.getLayerName().toLowerCase().contains(KEY_AIRPLANE)){
				currentDeviceList = RestTrackingJob.getInstance().getRestServiceList(KEY_AIRPLANE);
			}
			Logger.info("Actual number of objects before filtering for the layer ["+dataSource.getLayerName()+"] are ["+ (currentDeviceList!=null?currentDeviceList.size():0)+"] ", CustomWebServiceDataDriver.class);
			
			if(currentDeviceList != null){
				for (DeviceBean device: currentDeviceList){
					addDevice(device, indigoList);
				}
			}
			Logger.perf("Actual number of objects after filtering for the layer ["+dataSource.getLayerName()+"] are ["+ (indigoList!=null?indigoList.size():0)+"] ", CustomWebServiceDataDriver.class);
			
			return indigoList;
			
			/*List<DeviceBean> indigoList = new ArrayList();
			Element rootNode = null;
			String expression = null;		
				
			String xmlString = ValidateCertificateCall.callSecureURI();
			if(StringUtils.isNull(xmlString)){
				return null;
			}
			
			rootNode = XMLUtils.getRootNode(xmlString,true);
			expression = PARAM_XPR;
			List<Element> list =  XMLUtils.evaluate(rootNode, expression);
			setCustomSearchCriteria();
			//Object lists = getDataInput();
				for (int i=0;i <list.size(); i++){
					Element element = list.get(i);
					
					addDevice(element, indigoList);
					
				}
				
			//return (List) Arrays.asList(getRestServiceCacheMap().values().toArray());
				return indigoList;*/

		} catch (Exception exception) {
			
			Logger.error("Exception during webservice invokation ", "WebServiceDataDriver", exception);
			throw new EHRuntimeException("Exception during webservice invokation " + exception.getMessage(), exception);
		}

	}
	
	private void addDevice(DeviceBean device, List<DeviceBean> indigoList) {
		
		if(getStartTime() != null && getEndTime() != null && dataSource.getDeviceIds() != null){
			boolean isValid = false;
			for(String deviceId:dataSource.getDeviceIds()){
				Date date = CommonUtils.parseDate(device.getTime() ) ;
				if((deviceId.equalsIgnoreCase(device.getName()) && 
						((date.compareTo(getStartTime()) >= 0 && date.compareTo(getEndTime()) <= 0)))){
					isValid = true;
					break;
				}
			}
			
			if(!isValid){
				return;
			}
		}
		
		
		indigoList.add(device);
	}

	/**
	 * Cache the device statistics.
	 * @param element
	 *//*
	private void addDevice( Element element) {
		
		DeviceBean is = new DeviceBean();
		is.setLatitude(element.getAttribute("lat").getValue());
		is.setLongitude(element.getAttribute("lon").getValue());
		is.setTime( CommonUtils.formatDate(element.getContent(0).getValue()));
		is.setName(element.getContent(1).getValue());
		
		getRestServiceCacheMap().put(is.getName(), is);
	}*/
	
	void setCustomSearchCriteria(){
		//retrieve the search criteria
		SearchCriteria sc = getSearchCriteria();
		SearchCriteriaHelper sch  = null;
		Object[][] timeSearch = null;
		if(sc != null){
			//get the search criteria for specified property
			sch = sc.getSearchCritera(PARAM_TIME);
			timeSearch = sch.getSearchConditions();
			if(timeSearch != null ){
			//set the start date & enddate
				setTimeSpan(DateUtils.getDate((String) timeSearch[0][2]), DateUtils.getDate((String) timeSearch[1][2]));
			}
			//get the search criteria for specified property
			sch = sc.getSearchCritera(PARAM_DEVICEID);
			timeSearch = sch.getSearchConditions();
			if(timeSearch[0][2] instanceof String){
				String[] str =  new String[1];
				str[0] = (String) timeSearch[0][2];
				dataSource.setDeviceIds((String[]) str );
			}else {
				dataSource.setDeviceIds((String[]) timeSearch[0][2]);
			}
			
		}
	}
	
	
	@Override
	public boolean supportsSearch() {
		// TODO Auto-generated method stub
		return true;
	}
	
	/**
	 * Cache the device statistics.
	 * @param element
	 */
	private void addDevice( Element element, List indigoList ) {
		/*if(!SensorServiceUtils.isValidDevice(dataSource.getLayerName(), element.getContent(1).getValue())){
			return;
		}*/
		
		if(getStartTime() != null && getEndTime() != null && dataSource.getDeviceIds() != null){
			boolean isValid = false;
			for(String deviceId:dataSource.getDeviceIds()){
				Date date = CommonUtils.parseDate(element.getContent(0).getValue()) ;
				if((deviceId.equalsIgnoreCase(element.getContent(1).getValue()) && 
						((date.compareTo(getStartTime()) >= 0 && date.compareTo(getEndTime()) <= 0)))){
					isValid = true;
					break;
				}
			}
			
			if(!isValid){
				return;
			}
		}
		
		DeviceBean is = new DeviceBean();
		is.setLatitude(element.getAttribute(ATTR_LAT).getValue());
		is.setLongitude(element.getAttribute(ATTR_LNG).getValue());
		is.setTime( CommonUtils.formatDate(element.getContent(0).getValue()));
		is.setName(element.getContent(1).getValue());
		indigoList.add(is);
		//getRestServiceCacheMap().put(is.getName(), is);
	}

	

	private IMashupData executeScript(String script) {
		Object obj = null;
		if (!StringUtils.isNull(script)) {
			obj = scripter.executeScript(script);
		}

		if (obj != null && obj instanceof IMashupData) {
			return (IMashupData) obj;
		} else {
			throw new EHRuntimeException(" Method = [" + script + "] must return object of type com.enterprisehorizons.magma.datamashup.IMashupData");
		}
	}

	public IScripter getScripter() {
		return scripter;
	}

	public void setScripter(IScripter scripter) {
		this.scripter = scripter;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}
	
	public static void main(String args[]){
		
	}

}
