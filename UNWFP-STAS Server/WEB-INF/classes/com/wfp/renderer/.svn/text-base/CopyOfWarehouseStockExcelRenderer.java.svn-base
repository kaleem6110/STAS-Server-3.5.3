package com.wfp.renderer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;

import com.enterprisehorizons.constants.CommonConstants;
import com.enterprisehorizons.excel.CSVFile;
import com.enterprisehorizons.magma.renderer.dashboard.BaseDashboardRenderer;
import com.enterprisehorizons.util.ArrayUtils;
import com.enterprisehorizons.util.StringUtils;
import com.spacetimeinsight.security.bean.UserBean;
import com.wfp.utils.IEPICConstants;
import com.wfp.utils.RBRegionsUtils;

/**
 * Renderer for generating a  report which is used to show all  the stocks for a particular warehouse
 * @author sti-user
 *
 */
public class CopyOfWarehouseStockExcelRenderer extends BaseDashboardRenderer implements IEPICConstants {
	private static final String ROOT_NODE = "artifactdashboarddata";
	private static final String ELEMENT_ATTRIBUTES = "attributes";
	private static final String ELEMENT_ATTRIBUTE = "attribute";

	@Override
	protected String getRootNode() {
		return ROOT_NODE;
	}

	@Override
	protected StringBuffer render(Map params) {
		StringBuffer buff = new StringBuffer();
		//warehouse file location
		String whfilepath = (String) params.get(WAREHOUSE_FILEPATH);
		//stocks file location
		String filePath = (String) params.get(PARAM_FILEPATH);
		
		//headerrowindex ..by default it is 0
		//int headerRowIndex = StringUtils.getInt(params.get(HEADER_ROW));
		Map warehouseMap = null;
		SimpleDateFormat sdf = new SimpleDateFormat(PORTAL_DATE_FORMAT);
		
		//cacheing the details of warehouse
		warehouseMap = getWarehoueDtls(whfilepath);
		
		if (!StringUtils.isNull(filePath) && warehouseMap != null && warehouseMap.size() > 0) {
			//reading the stock items files 
			CSVFile file = new CSVFile(filePath, CommonConstants.COMMA_STRING, 0);
			String[] headerNames = file.getHeaderNames();
			headerNames = (String[]) ArrayUtils.add(headerNames, (Object[])warehouseMap.get("headers"));
			int count = headerNames == null ? 0 : headerNames.length;
			//placing all the header properties to XML Attributes
			for (int i = 0; i < count; i++) {
				headerNames[i] = StringUtils.toXMLAttribute(headerNames[i]);
			}
			

			Object[] data = null;
			UserBean userbean = getUserbean();
			buff.append(XML_START_TAG + ELEMENT_ATTRIBUTES + XML_END_TAG);int log= 0;
			//iterate over record by record & map the data with warehouse & convert he same to XML Data
			while (file.hasMoreElements()) {
				data = file.nextElement();
				Object[] whdata = (Object[]) warehouseMap.get(data[15]);
				if( whdata == null ){
					continue;
				}
				
				if(StringUtils.getInt(data[11]) <= 0){
					continue;
				}
				
				if(!RBRegionsUtils.validateLocation((String) whdata[3], userbean.getGroupIds())){
					continue;
				}
				buff.append(XML_START_TAG);
				buff.append(ELEMENT_ATTRIBUTE);
				//System.out.println(log++);
				//retrieve the warehouse information, data[15] indicates the warehouse code in stocks csv file
				//Object[] whdata = (Object[]) warehouseMap.get(data[15]);
				//check whether the data can be shown to the user based on the logon information.
				//Filtering of data based on the user belonging to the group & superuser
				
				
				
					
			
				
				for (int i = 0; i < (file.getHeaderNames().length -1); i++) {
					/*if(data[i]!=null && data[i] instanceof Date){
							data[i] = sdf.format(data[i]);
					}*/
					
					//warehouse is the property in stocks whihc is a primary key in warehouse csv.
					if(PARAM_WAREHOUSE.equalsIgnoreCase(headerNames[i])){
						
						Object[] whheaders = (Object[]) warehouseMap.get("headers");
						for(int j=0; j<whheaders.length; j++){
							
							//append the coordinates to the stock items
							if( PARAM_LATITUDE.equalsIgnoreCase((String) whheaders[j])){
								addAttribute(buff, (String)StringUtils.toXMLAttribute(PARAM_COORDINATES), StringEscapeUtils.escapeXml(whdata[j] == null ? 
										CommonConstants.EMPTY_STRING : whdata[j+1]+CommonConstants.COMMA_STRING+whdata[j]+",0"));
								continue;
							}
							
							//add other params to the stocks list
							addAttribute(buff, (String)whheaders[j], StringEscapeUtils.escapeXml(whdata[j] == null ? 
									CommonConstants.EMPTY_STRING : (String)whdata[j]));
						}
						continue;
					}
					
					//all remaining params to the stocks list
					addAttribute(buff, headerNames[i], StringEscapeUtils.escapeXml(data[i] == null ? CommonConstants.EMPTY_STRING : data[i]
							.toString()));
				}
				buff.append(XML_END_TAG_END);
			}
			buff.append(XML_END_TAG_START + ELEMENT_ATTRIBUTES + XML_END_TAG);
		}
		//System.out.println(buff);
		return buff;
	}
	
	
	private static Map getWarehoueDtls(String whfilepath){
		Map warehouseMap = null;
		//cache all the warehouse with their warehouse names as key & value having all the data with coordinates
		if (!StringUtils.isNull(whfilepath)) {
			CSVFile whfile = new CSVFile(whfilepath, CommonConstants.COMMA_STRING, 0);
			if(whfile.getNoOfRecords() > 0){
				warehouseMap = new HashMap();
			}
			
			if(whfile == null){
				return null;
			}
			warehouseMap.put("headers", whfile.getHeaderNames());
			Object[] data = null;
			
			while (whfile.hasMoreElements()) {
				data = whfile.nextElement();
				warehouseMap.put(data[1], data);
			}
			
		}
		return warehouseMap;
	}
}
