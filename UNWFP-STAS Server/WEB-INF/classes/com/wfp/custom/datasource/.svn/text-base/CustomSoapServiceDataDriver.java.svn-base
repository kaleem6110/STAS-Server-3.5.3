package com.wfp.custom.datasource;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.enterprisehorizons.exception.EHRuntimeException;
import com.enterprisehorizons.magma.datamashup.BaseGeoDataDriver;
import com.enterprisehorizons.magma.datamashup.IMashupData;
import com.enterprisehorizons.magma.datamashup.IScriptableDataDriver;
import com.enterprisehorizons.magma.designtime.artifact.IScripter;
import com.enterprisehorizons.util.CollectionUtils;
import com.enterprisehorizons.util.DateUtils;
import com.enterprisehorizons.util.Logger;
import com.enterprisehorizons.util.SearchCriteria;
import com.enterprisehorizons.util.SearchCriteriaHelper;
import com.enterprisehorizons.util.StringUtils;
import com.wfp.jobs.SoapTrackingJob;
import com.wfp.security.form.DeviceBean;
import com.wfp.utils.IEPICConstants;

/**
 * allows  to retrieve information about sensor devices, and the location and/or probe measurements which are known for them.
 * @author sti-user
 *
 */
public class CustomSoapServiceDataDriver extends BaseGeoDataDriver implements IScriptableDataDriver, IEPICConstants {

	private CustomSoapServiceDataSource dataSource = null;
	//private Object dataList[][] = null;
	private IScripter scripter;
	private Object response = null;
	
	
	
	/*
	 * Constructor initializes datasource by invoking a local call to initialise method.
	 */
	public CustomSoapServiceDataDriver(CustomSoapServiceDataSource datasource) {
		super(datasource);
		this.dataSource = datasource;
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.enterprisehorizons.magma.datamashup.IDataDriver#getData()
	 */
	public List getData() {
		try {
			
			setCustomSearchCriteria();
			
			/*if(dataSource.getDeviceIds() != null){
				return SensorServiceUtils.getEmergencySpotDtls(dataSource.getDeviceIds(), getStartTime(), getEndTime(), dataSource.getLayerName());
			}
			return SensorServiceUtils.getEmergencySpotDtls(getStartTime(), getEndTime(),dataSource.getLayerName());
			*/
			if(dataSource.getLayerName().contains(LAYER_STAFF)){
				//return
				return getList(SoapTrackingJob.getInstance().getSoapServiceList(LAYER_STAFF), getStartTime(), getEndTime(), dataSource.getDeviceIds());
			}else if(dataSource.getLayerName().contains(LAYER_VEHICLE)){
				//return 
				return getList( SoapTrackingJob.getInstance().getSoapServiceList(LAYER_VEHICLE), getStartTime(), getEndTime(), dataSource.getDeviceIds());
			}else if(dataSource.getLayerName().contains(LAYER_AIRPLANE)){
				//return 
				return getList(SoapTrackingJob.getInstance().getSoapServiceList(LAYER_AIRPLANE), getStartTime(), getEndTime(), dataSource.getDeviceIds());
			}
			return null;
			

		} catch (Exception exception) {
			
			Logger.error("Exception during webservice invokation ", "WebServiceDataDriver", exception);
			throw new EHRuntimeException("Exception during webservice invokation " + exception.getMessage(), exception);
		}
	}
	
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	private List getList(
			Map<String, Map<String, List<DeviceBean>>> soapServiceMap,
			Date startTime, Date endTime, String[] deviceIds) {
		Collection<DeviceBean> result = null;
		Logger.info("Actual number of objects before filtering for the layer ["+dataSource.getLayerName()+"] are ["+ (soapServiceMap!=null?soapServiceMap.size():0)+"] ", CustomSoapServiceDataDriver.class);
		
		if(soapServiceMap != null){
			result =  new ArrayList<DeviceBean>();
			for(Map.Entry<String, Map<String, List<DeviceBean>>> tempMap : soapServiceMap.entrySet()){
				Map<String, List<DeviceBean>> deviceMap = null;
				Calendar cal = Calendar.getInstance();
				if(startTime != null && endTime != null && startTime.compareTo(endTime) == 0){
					
					endTime.setHours(23);
					endTime.setMinutes(00);
					endTime.setSeconds(00);
					cal.setTime(endTime);
					
					startTime.setHours(00);
					startTime.setMinutes(0);
					startTime.setSeconds(0);
				}

				if(startTime != null && endTime != null &&
						DateUtils.getDate(tempMap.getKey()).compareTo(startTime)>=0 && DateUtils.getDate(tempMap.getKey()).compareTo(endTime) <=0){
					deviceMap = tempMap.getValue();
					//CollectionUtils.addAll(result, tempMap.getValue()); 
				}else if(endTime == null  ||startTime == null){
					deviceMap = tempMap.getValue();
				} else if(startTime != null && endTime != null && startTime.compareTo(endTime) == 0){
					if(DateUtils.format(endTime).equalsIgnoreCase(tempMap.getKey()))
					deviceMap = tempMap.getValue();
				}
				
				if(deviceIds == null){
					if(deviceMap != null){
						for(Map.Entry<String, List<DeviceBean>> entry : deviceMap.entrySet()){
							CollectionUtils.addAll(result, entry.getValue().toArray());
						}
					}
					
				}else {
					for(String device:deviceIds){
						if(deviceMap != null){
							if(deviceMap.get(device) != null){
								CollectionUtils.addAll(result, deviceMap.get(device).toArray());
							}
							}
						}
						
					}
				}
			}
		Logger.info("Actual number of objects after filtering for the layer ["+dataSource.getLayerName()+"] are ["+ (result!=null?result.size():0)+"] ", CustomSoapServiceDataDriver.class);
		return (List) result;
	}

	
	
	/**
	 * functionality taking care for searching the data based on user inputs. 
	 */
	void setCustomSearchCriteria(){
		//retrieve the search criteria
		SearchCriteria sc = getSearchCriteria();
		SearchCriteriaHelper sch  = null;
		Object[][] timeSearch = null;
		if(sc != null){
			//get the search criteria for specified property
			sch = sc.getSearchCritera(PARAM_TIME);
			timeSearch = sch.getSearchConditions();
			//set the start date & enddate
			setTimeSpan( (Date) timeSearch[0][2],(Date) timeSearch[1][2]);
			
			//get the search criteria for specified property
			sch = sc.getSearchCritera(PARAM_NAME);
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
