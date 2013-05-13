package com.wfp.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.enterprisehorizons.magma.designtime.artifact.IGeoArtifact;
import com.enterprisehorizons.magma.server.util.ServerUtils;
import com.enterprisehorizons.util.Logger;
import com.enterprisehorizons.util.StringUtils;
import com.wfp.jobs.RestTrackingJob;
import com.wfp.beans.*;
/**
 * Common Utility class 
 * @author sti-user
 *
 */
public class CommonUtils implements IEPICConstants{

	/**
	 * format the middle ware date to generic format. <code>EPIC_DATE_FORMAT</code> to <code>PORTAL_DATE_FORMAT</code>
	 * @param datetime
	 * @return
	 */
	public static String formatDate(String datetime){
		if(StringUtils.isNull(datetime)){
			return null;
		}
		
		SimpleDateFormat formatter = new SimpleDateFormat(EPIC_DATE_FORMAT);  
		Date date;
        try {
			date = formatter.parse(datetime);
			return NEW_PORTAL_SIMPLE_FORMAT.format(date);
			//return PORTAL_SIMPLE_FORMAT.format(date);
		} catch (ParseException e) {
			Logger.error("Error ocurred while formatting date ["+datetime+"]", CommonUtils.class, e);
		}  
          
       return null;
	}
	
	public static String formatDate(String datetime, String timeformat){
		if(StringUtils.isNull(datetime)){
			return null;
		}
		
		SimpleDateFormat  formatter = new SimpleDateFormat(timeformat);  
		Date date;
        try {
			date = formatter.parse(datetime);
			return PORTAL_SIMPLE_FORMAT.format(date);
		} catch (ParseException e) {
			Logger.error("Error ocurred while formatting date ["+datetime+"]", CommonUtils.class, e);
		}  
          
       return null;
	}
	public static String formatDate(String datetime, String inputTimeformat, String outputTimeFormat){
		if(StringUtils.isNull(datetime)){
			return null;
		}
		
		SimpleDateFormat  formatter = new SimpleDateFormat(inputTimeformat);  
		Date date;
        try {
			date = formatter.parse(datetime);
			return new SimpleDateFormat(outputTimeFormat).format(date);
		} catch (ParseException e) {
			Logger.error("Error ocurred while formatting date ["+datetime+"]", CommonUtils.class, e);
		}  
          
       return null;
	}
	
	public static Date parseDate(String datetime, String timeformat){
		if(StringUtils.isNull(datetime)){
			return null;
		}
		
		SimpleDateFormat  formatter = new SimpleDateFormat(timeformat);  
		try {
			return formatter.parse(datetime);
			//return PORTAL_SIMPLE_FORMAT.format(date);
		} catch (ParseException e) {
			Logger.error("Error ocurred while formatting date ["+datetime+"]", CommonUtils.class, e);
		}  
          
       return null;
	}
	
	
	public static String getUTCDateString(String datetime, String timeformat){
		if(datetime.contains("PST") || datetime.contains("PDT")){
			datetime = datetime.replaceAll("PDT", "UTC");
			datetime = datetime.replaceAll("PST", "UTC");
		}else {
			return datetime;
		}
		return getUTCdatetime(parseDate(datetime, timeformat));
 
	}
	
	
	//EEE MMM dd HH:mm:ss z yyyy
	
	public static Date parseDate(String datetime){
		if(StringUtils.isNull(datetime)){
			return null;
		}
		
		SimpleDateFormat  formatter = new SimpleDateFormat(EPIC_DATE_FORMAT);  
		try {
			return formatter.parse(datetime);
		} catch (ParseException e) {
			Logger.error("Error ocurred while formatting date ["+datetime+"]", CommonUtils.class, e);
		}  
          
       return null;
	}
	
	public static Date parseDatePortalFormat(String datetime){
		if(StringUtils.isNull(datetime)){
			return null;
		}
		SimpleDateFormat  formatter = new SimpleDateFormat(PORTAL_DATE_FORMAT);  
		try {
			return formatter.parse(datetime);
		} catch (ParseException e) {
			Logger.error("Error ocurred while formatting date ["+datetime+"]", CommonUtils.class, e);
		}  
          
       return null;
	}
	
	public static String formatDate(Date datetime){
		if(datetime == null){
			return null;
		}
		try {
			
			//return PORTAL_SIMPLE_FORMAT.format(datetime);
			return NEW_PORTAL_SIMPLE_FORMAT.format(datetime);
		} catch (Exception e) {
			Logger.error("Error ocurred while formatting date ["+datetime+"]", CommonUtils.class, e);
		}  
          
       return null;
	}
	
	public static  void addDeviceDtls(com.wfp.security.form.LDAPUserBean ldapUserBean, IGeoArtifact artefact, String layerName){
		if(layerName.startsWith(LAYER_STAFF)){
			//System.out.println(ldapUserBean.getDeviceId());
			if(!StringUtils.isNull(ldapUserBean.getOrganization())){
				artefact.addAttribute(ATTR_ORGANIZATION, ldapUserBean.getOrganization());
			}
			
			if(!StringUtils.isNull(ldapUserBean.getCallSign())){
				artefact.addAttribute(ATTR_CALL_SIGN, ldapUserBean.getCallSign());
			}
			
		}else if(layerName.startsWith(LAYER_VEHICLE)){
			//System.out.println(ldapUserBean.getDeviceId());
			if(!StringUtils.isNull(ldapUserBean.getFleet())){
				artefact.addAttribute(ATTR_UNIT, ldapUserBean.getFleet());
			}
			
			if(!StringUtils.isNull(ldapUserBean.getUnit())){
				artefact.addAttribute(ATTR_FLEET, ldapUserBean.getFleet());
			}
			
			if(!StringUtils.isNull(ldapUserBean.getCallSign())){
				artefact.addAttribute(ATTR_CALL_SIGN, ldapUserBean.getCallSign());
			}
		}else if(layerName.startsWith(LAYER_AIRPLANE)){
			return;
		}
		
	}
	
	public static  String getContactDtls(IGeoArtifact artefact, String skypeId, String ocsId, String sipId){		
		return getContactDtls(skypeId, ocsId, sipId);
		
	}

	public static  String getContactDtls(String skypeId, String ocsId, String sipId) {
		StringBuffer contact = new StringBuffer();
		String serverRootUrl = ServerUtils.getServerBaseUrl();
		if(!com.enterprisehorizons.util.StringUtils.isNull(skypeId)){			
			contact.append( "<a href=\"skype:"+skypeId+"?call\">  <img src='"+serverRootUrl+SKYPE_CALL_IMG+"' width='16px' height='16px' title='Call on skype'>  </a>");
		}
		
		if(!com.enterprisehorizons.util.StringUtils.isNull(ocsId)){
		
			contact.append( " "+"<a href=\"tel:"+ocsId+"\">  <img src='"+serverRootUrl+MOBILE_IMG+"'   title='Call on Lync'>  </a>" );
		}
		
		if(!com.enterprisehorizons.util.StringUtils.isNull(sipId) && !com.enterprisehorizons.util.StringUtils.isNull(StringUtils.replaceAll(sipId, "\"NULL\" ", "")) && !com.enterprisehorizons.util.StringUtils.isNull(StringUtils.replaceAll(sipId, "\"\" ", ""))){
			
			if(NULL_STR.equalsIgnoreCase(sipId)){
				return contact.toString();
			}
			
			contact.append( " "+"<a href=\"sip:"+sipId+"\">  <img src='"+serverRootUrl+SIP_CALL_IMG+"' width='16' height='16' title='Send me IM'>  </a>");
		}
		
		return contact.toString();
	}
	
	public static  String getContactDtls(List<String> pagersList, List<String> emailsList, List<String> mobilesList,String fullName) {
		StringBuffer contact = new StringBuffer();
		String serverRootUrl = ServerUtils.getServerBaseUrl();
		if(pagersList != null && pagersList.size() > 0){
			contact.append("<fieldset><legend>Pager</legend>");
			for(String pager:pagersList){
				if(pager.indexOf("skype") == 0)
					contact.append( "<a href=\""+pager+"\">  <img src='"+serverRootUrl+SKYPE_CALL_IMG+"' width='16px' height='16px' title='"+pager+"'>  </a>");
				else if(pager.indexOf("msn") == 0){
					contact.append( "<a href=\""+pager+"\">  <img src='"+serverRootUrl+MSN_IM+"' width='16px' height='16px' title='"+pager+"'>  </a>");
				}else if(pager.indexOf("gtalk") == 0){
					contact.append( "<a href=\""+pager+"\">  <img src='"+serverRootUrl+GTALK_IM+"' width='16px' height='16px' title='"+pager+"'>  </a>");
				}else {
					contact.append( "<a href=\""+pager+"\">  <img src='"+serverRootUrl+CONTACT+"' width='16px' height='16px' title='"+pager+"'>  </a>");
				}
				
			}
			contact.append("</fieldset>");
		}
		contact.append("<br>");
		
		if(emailsList != null && emailsList.size() > 0){
			contact.append("<fieldset><legend>Mail</legend>");
			for(String email:emailsList){
				if(email.indexOf("gmail") >= 0){
					contact.append( " "+"<a href=\"mailto:"+email+"\">  <img src='"+serverRootUrl+GTALK_IM+"' width='16' height='16' title='Send IM to "+fullName+" '>  </a>");
				}else {
					
					contact.append( " "+"<a href=\"mailto:"+email+"\">  <img src='"+serverRootUrl+CONTACT+"' width='16' height='16' title='Send IM to "+fullName+" '>  </a>");
				}
				
			}
			contact.append("</fieldset>");
		}
		contact.append("<br>");
		if(mobilesList != null && mobilesList.size() >0){
			contact.append("<fieldset><legend>Telephone</legend>");
			for(String mobile:mobilesList){
				contact.append( " "+"<a href=\"javascript:"+mobile+"\">  <img src='"+serverRootUrl+MOBILE_IMG+"' width='16' height='16' title='Call "+fullName+" '>  </a>");
			}
			contact.append("</fieldset>");
		}
		
		return contact.toString();
	}
	
	public static  String getPagerDtls(List<String> pagersList) {
		StringBuffer contact = new StringBuffer();
		String serverRootUrl = ServerUtils.getServerBaseUrl();
		if(pagersList != null && pagersList.size() > 0){
			//contact.append("<fieldset><legend>Pager</legend>");
			for(String pager:pagersList){
				if(pager.indexOf("skype") == 0)
					contact.append( "<a href=\""+pager+"\">  <img src='"+serverRootUrl+SKYPE_CALL_IMG+"' width='16px' height='16px' title='"+pager+"'>  </a>");
				else if(pager.indexOf("msn") == 0){
					contact.append( "<a href=\""+pager+"\">  <img src='"+serverRootUrl+MSN_IM+"' width='16px' height='16px' title='"+pager+"'>  </a>");
				}else if(pager.indexOf("gtalk") == 0){
					contact.append( "<a href=\""+pager+"\">  <img src='"+serverRootUrl+GTALK_IM+"' width='16px' height='16px' title='"+pager+"'>  </a>");
				}else {
					contact.append( "<a href=\""+pager+"\">  <img src='"+serverRootUrl+CONTACT+"' width='16px' height='16px' title='"+pager+"'>  </a>");
				}
				
			}
		}
		
		return contact.toString();
	}
	public static  Messaging getMessaging(List<String> pagersList) {
		Messaging mbean = new Messaging();
		String serverRootUrl = ServerUtils.getServerBaseUrl();
		if(pagersList != null && pagersList.size() > 0){
			//contact.append("<fieldset><legend>Pager</legend>");
			for(String pager:pagersList){
				if(pager.indexOf("skype") == 0)
					mbean.setSkype(pager +"<a href=\""+pager+"\">  <img src='"+serverRootUrl+SKYPE_CALL_IMG+"' width='16px' height='16px' title='"+pager+"'>  </a>");
				else if(pager.indexOf("msn") == 0){
					mbean.setMsn(pager+ "<a href=\""+pager+"\">  <img src='"+serverRootUrl+MSN_IM+"' width='16px' height='16px' title='"+pager+"'>  </a>");
				}else if(pager.indexOf("gtalk") == 0){
					mbean.setGtalk(pager+ "<a href=\""+pager+"\">  <img src='"+serverRootUrl+GTALK_IM+"' width='16px' height='16px' title='"+pager+"'>  </a>");
				}else {
					mbean.setOther( pager+"<a href=\""+pager+"\">  <img src='"+serverRootUrl+CONTACT+"' width='16px' height='16px' title='"+pager+"'>  </a>");
				}
				
			}
		}
		
		return mbean;
	}
	
	public static  String getMailDtls(List<String> emailsList) {
		StringBuffer contact = new StringBuffer();
		if(emailsList != null && emailsList.size() > 0){			
			for(String email:emailsList){
				
				contact.append(  "<a href='mailto:"+email+"' > "+email+"</a><br>");				
			}			
		}
		
		return contact.toString();
	}
	
	public static  String getPhoneDtls(List<String> mobilesList) {
		StringBuffer contact = new StringBuffer();
		if(mobilesList != null && mobilesList.size() >0){			
			for(String mobile:mobilesList){
				contact.append(mobile+ " <br>");
			}			
		}
		
		return contact.toString();
	}
	public static  Telephone getTelephones(List<String> mobilesList) {
		Telephone telephone = new Telephone();
		List<String> mobileList =new ArrayList<String>();
		if(mobilesList != null && mobilesList.size() >0){			
			for(String mobile:mobilesList){
				if(mobile.indexOf("Mobile:")>0)
				{
					mobileList.add(mobile.substring(mobile.indexOf("Mobile:")+7,mobile.length()-1));
				}else if(mobile.indexOf("Foodsat:")>0)
				{
					telephone.setFoodsat(mobile.substring(mobile.indexOf("Foodsat:")+8,mobile.length()-1));
				}else if(mobile.indexOf("Office:")>0)
				{
					telephone.setOffice( mobile.substring(mobile.indexOf("Office:")+7,mobile.length()-1));
				}else if(mobile.indexOf("WAVE:")>0)
				{
					telephone.setWave( mobile.substring(mobile.indexOf("WAVE:"),mobile.length()-1));
				}
			}			
		}
		
		return telephone;
	}
	
	public static  String getContactDtls(String skypeId, String skypeImg, String ocsId, String ocsImg,  String sipId, String sipImg) {
		StringBuffer contact = new StringBuffer();
		String serverRootUrl = ServerUtils.getServerBaseUrl();
		if(!com.enterprisehorizons.util.StringUtils.isNull(skypeId)){			
			contact.append( "<a href=\""+skypeId+"\">  <img src='"+serverRootUrl+skypeImg+"' width='16px' height='16px' title='Call on skype'>  </a>");
		}
		
		if(!com.enterprisehorizons.util.StringUtils.isNull(ocsId)){
		
			contact.append( " "+"<a href=\"tel:"+ocsId+"\">  <img src='"+serverRootUrl+ocsImg+"'   title='Call on Lync'>  </a>" );
		}
		
		if(!com.enterprisehorizons.util.StringUtils.isNull(sipId) && !com.enterprisehorizons.util.StringUtils.isNull(StringUtils.replaceAll(sipId, "\"NULL\" ", "")) && !com.enterprisehorizons.util.StringUtils.isNull(StringUtils.replaceAll(sipId, "\"\" ", ""))){
			
			if(NULL_STR.equalsIgnoreCase(sipId)){
				return contact.toString();
			}
			
			contact.append( " "+"<a href=\"sip:"+sipId+"\">  <img src='"+serverRootUrl+sipImg+"' width='16' height='16' title='Send me IM'>  </a>");
		}
		
		return contact.toString();
	}

	public static String[] getHttpUrls(String desc){
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static List pullLinks(String text) {
		if(!StringUtils.isNull(text)){			
			List links = new ArrayList();
	
			String regex = "\\(?\\b(http://|www[.])[-A-Za-z0-9+&@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&@#/%=~_()|]";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(text);
			while (m.find()) {
				String urlStr = m.group();
				if (urlStr.startsWith("(") && urlStr.endsWith(")")) {
					urlStr = urlStr.substring(1, urlStr.length() - 1);
				}
				links.add(urlStr);
			}
			return links;
		}
		   
		
		return null;
	}

	public static Date getUTCdatetimeAsDate() 
	{ 
	    // note: doesn't check for null
	    return stringDateToDate(getUTCdatetimeAsString()); 
	} 
	 
	public static String getUTCdatetimeAsString() 
	{ 
	    final SimpleDateFormat sdf = new SimpleDateFormat(PORTAL_DATE_FORMAT); 
	    sdf.setTimeZone(TimeZone.getTimeZone("UTC")); 
	    final String utcTime = sdf.format(new Date()); 
	 
	    return utcTime.replace("T", " ").substring(0, utcTime.length()-5);
	} 
	
	public static String getUTCdatetime(Date datetime) 
	{ 
	    //Kaleem final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss z");
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss z");
	    sdf.setTimeZone(TimeZone.getTimeZone("UTC")); 
	    final String utcTime = sdf.format(datetime); 
	 
	    return utcTime; 
	}
	
	public static String getUTCdatetime(String datetime) 
	{ 
	    final SimpleDateFormat sdf = new SimpleDateFormat(PORTAL_DATE_FORMAT); 
	    sdf.setTimeZone(TimeZone.getTimeZone("UTC")); 
	    final String utcTime = sdf.format(datetime); 
	 
	    return utcTime; 
	}
	 
	public static Date stringDateToDate(String StrDate) 
	{ 
	    Date dateToReturn = null; 
	    SimpleDateFormat dateFormat = new SimpleDateFormat(NEW_PORTAL_DATE_FORMAT); 
	 
	    try 
	    { 
	        dateToReturn = (Date)dateFormat.parse(StrDate); 
	    } 
	    catch (ParseException e) 
	    { 
	        e.printStackTrace(); 
	    } 
	 
	    return dateToReturn; 
	} 
	public static Date stringDateToDate(String StrDate, String dateTimeFormat) 
	{ 
	    Date dateToReturn = null; 
	    SimpleDateFormat dateFormat = new SimpleDateFormat(dateTimeFormat); 
	 
	    try 
	    { 
	        dateToReturn = (Date)dateFormat.parse(StrDate); 
	    } 
	    catch (ParseException e) 
	    { 
	        e.printStackTrace(); 
	    } 
	 
	    return dateToReturn; 
	} 
	public static Date stringDateToISODate(String StrDate) 
	{ 
	    Date dateToReturn = null; 
	    try 
	    { 
	    	SimpleDateFormat sdf = new SimpleDateFormat(NEW_PORTAL_DATE_FORMAT); 
	        dateToReturn = (Date)sdf.parse(StrDate); 
	    } 
	    catch (ParseException e) 
	    { 
	        e.printStackTrace(); 
	    } 
	 
	    return dateToReturn; 
	} 
	public static String getTimeZoneByLatLong(String lat, String longitude, String zuluTime, String inputDateFormat)
	{
		String offset= null;
		StringBuffer sb = new StringBuffer();
		String uri =EARTH_TOOLS_URL+lat+"/"+longitude;
		try {
			
			URL earthURI = new URL(uri);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					earthURI.openStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null){
				sb.append(inputLine);
				//System.out.println(inputLine);
			}
			in.close();
			 DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			 InputSource is = new InputSource();
			 is.setCharacterStream(new StringReader(sb.toString()));

			 Document doc = db.parse(is);
		
			org.w3c.dom.NodeList nList = doc.getElementsByTagName(TIME_ZONE_NODE);
			
			if(nList != null){
				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);					
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						 
						org.w3c.dom.Element eElement = (org.w3c.dom.Element) nNode;
						org.w3c.dom.NodeList offsetList = eElement.getElementsByTagName(OFFSET_NODE);						
						org.w3c.dom.Element offsetElement = (org.w3c.dom.Element) offsetList.item(0);
						offset= getCharacterDataFromElement(offsetElement);
						
						DateFormat formatter = new SimpleDateFormat(inputDateFormat);
						Date zuluDate =  formatter.parse(zuluTime);
						if( !inputDateFormat.equals(NEW_PORTAL_DATE_FORMAT))formatter = new SimpleDateFormat(NEW_PORTAL_DATE_FORMAT);
					
						boolean decimal= false;
						if(offset!=null&&offset.trim().indexOf(".")>-1)
						{
							offset = offset.substring(0, offset.indexOf("."));
							decimal=true;
						}
						if(Integer.parseInt(offset)>0)
						{ zuluDate.setHours(zuluDate.getHours()+Integer.parseInt(offset)) ;
						if(decimal)zuluDate.setMinutes(zuluDate.getMinutes()+30);
						}
						else  {zuluDate.setHours(zuluDate.getHours()-Integer.parseInt(offset)) ;
						if(decimal)zuluDate.setMinutes(zuluDate.getMinutes()-30);
						}
						
						offset = formatter.format( zuluDate );
						//offset=offset.replace("T", " ").substring(0,offset.length()-5 );
						
				}
				}
				
				
			}			
			}
		catch (Exception exp) {
			Logger.error("CommonUtils.getTimeZoneByLatLong : Error ocurred @ :"+uri, RestTrackingJob.class, exp );
		}
		return offset;
	}
	 public static String getCharacterDataFromElement(org.w3c.dom.Element e) {
		    Node child = e.getFirstChild();
		    if (child instanceof CharacterData) {
		      CharacterData cd = (CharacterData) child;
		      return cd.getData();
		    }
		    return "";
		  }	
	 public static String parseJsonString( List<Tab> tabList )
	 {
		 //System.out.println(" #### START CommonUtils.parseJsonString #### ");
		 String jsonString="";
		 if( tabList!=null && tabList.size()>0)
		 {
			 jsonString ="DELIM&#123;";
			 int tabSize = tabList.size();
			 for(Tab tab: tabList)
			 {				
				 jsonString+="&quot;"+tab.getName()+"&quot; : &#091;&#123;";
				 List<Legend> legendList = tab.getLegendList();
				 if( legendList!=null && legendList.size()>0)
				 {
					 int legendSize = legendList.size();
					 for(Legend legend :legendList)
					 {
						 jsonString+="&quot;"+legend.getName()+"&quot; : &#123;";
						 Map<String, String> attribMap = legend.getAttribMap();
						 int mapSize = attribMap.size();
						 for (Map.Entry<String, String> entry : attribMap.entrySet()) {
							   	    jsonString+="&quot;"+entry.getKey()+"&quot;:&quot;"+entry.getValue()+"&quot;";	
							   	    if( mapSize>1) jsonString+=",";
							   	    mapSize--;							   	 
							   	 
							}
						 jsonString+="&#125;";
						 if(legendSize>1) jsonString+=",";
						 legendSize--;
					 }
				 }
				 jsonString+="&#125;&#093;";
				 if(tabSize>1) jsonString+=",";
				 tabSize--;
				 
			 }
			 jsonString+="&#125;DELIM";
		 }
		// System.out.println(": jsonString : "+jsonString );
		 //System.out.println(" #### END CommonUtils.parseJsonString #### ");
		 return jsonString;
	 }
	
}
