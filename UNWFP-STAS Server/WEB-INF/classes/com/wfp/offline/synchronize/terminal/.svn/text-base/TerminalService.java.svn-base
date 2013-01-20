package com.wfp.offline.synchronize.terminal;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.enterprisehorizons.magma.server.admin.AdminConfigUtils;
import com.enterprisehorizons.magma.server.util.ServerUtils;
import com.enterprisehorizons.magma.services.WebserviceRESTHandler;
import com.enterprisehorizons.util.CastorUtils;
import com.enterprisehorizons.util.Logger;
import com.enterprisehorizons.util.StringUtils;
import com.enterprisehorizons.util.URLUtils;
import com.spacetimeinsight.db.model.util.DataModelsCacheHelper;
import com.spacetimeinsight.magma.server.security.util.CryptoUtils;
import com.wfp.offline.synchronize.ISynchronizationService;
import com.wfp.offline.synchronize.utils.CommonServiceDBUtils;
import com.wfp.offline.synchronize.utils.SynchronizationDBUtils;
import com.wfp.synchronize.Synchronize;
import com.wfp.synchronize.db.config.model.SynchronizationTrailLog;
import com.wfp.utils.WFPConfigUtils;
/**
 * Terminal Server
 * @author aditya.velala
 *
 */
public class TerminalService implements ISynchronizationService{

	//all trace logs at current time in database
	private List<SynchronizationTrailLog> allTraceLogs = null;
	//terminal server name
	private String terminalServerName =  ServerUtils.getSystemServerName()+"_"+AdminConfigUtils.getHttpServerPort();
	//central server details - need to get from the Manage configurations
	private String centralServerName =  null;
	private String centralServerUrl = WFPConfigUtils.getCentralServerUrl();
	//domain id - default
	private long domainId = DataModelsCacheHelper.getDomainId("$SELF$");
	//language id - default
	private long languageId= DataModelsCacheHelper.getLanguageId("en", "US");
	//terminal server processing timestamp
	private Date terminalRequestTime = null;
	private Date lastAccessedTime = null;
	private boolean isTraceById = false;
	/**
	 * initializing the trace logs & timestamp
	 */
	public TerminalService(){		
		this(0);
		Logger.info("Started executing TerminalService", TerminalService.class);
	}
  
	public TerminalService(int logId){
		Logger.info("Constuctor: Terminal Server with log Id["+logId+"]", TerminalService.class);
		lastAccessedTime = SynchronizationDBUtils.getLastAccessTime(terminalServerName);
		if(logId <=0){
			Logger.info("get all trace logs which are in SYNC STATUS NEW =1 from terminal server ["+terminalServerName+"]", TerminalService.class);
			//get all trace logs which are in SYNC STATUS NEW =1
			allTraceLogs = CommonServiceDBUtils.getTraceLogs(SYNC_STATUS_NEW, true, terminalServerName);
			isTraceById = false;
		}else {
			Logger.info("Operation: [Force Sync..../ Ignore Operation..]... Retrieveing trace log from the database", TerminalService.class);
			//System.out.println("updated log");
			SynchronizationTrailLog logItem = CommonServiceDBUtils.getTraceLogById(logId);
			if(allTraceLogs == null){
				allTraceLogs = new ArrayList<SynchronizationTrailLog>();
			}

			allTraceLogs.add(logItem);
			Logger.info("setting isTraceById flag", TerminalService.class);
			isTraceById = true;
		}

		if(allTraceLogs != null){
			Logger.info("Number of logs = "+ allTraceLogs.size(), TerminalService.class);
		}else {
			Logger.info("Number of logs = 0", TerminalService.class);
		}

		//terminal server timestamp
		terminalRequestTime = new Date();

		//extract central server name from URL
		centralServerName = extractURLDtls();

		Logger.info("Terminal Server ["+terminalServerName+"] connecting to... Central Server: ["+centralServerName+"] ", TerminalService.class);

	}

	public Date getLastAccessedTime(){
		return lastAccessedTime;
	}

	private String extractURLDtls() {
		URL tempURL = null;
		try {
			tempURL = new URL(centralServerUrl);
			return tempURL.getHost()+"_"+tempURL.getPort();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return centralServerUrl;

	}

	/**
	 * Construts all the changes performed by the terminal server in the form of XML
	 * @return
	 */
	private String buildSynchronizationDataXML(){
		return CommonServiceDBUtils.buildSyncXML(this);
	}

	/**
	 * Triggering the Sync Service from TS to CS
	 */
	public String synchronizeData() {
		Logger.info("Building for synchronizing data for  terminal server ["+terminalServerName+"]", TerminalService.class);
		Properties props = new Properties();
		props.put("syncXML", URLUtils.encode(buildSynchronizationDataXML()));  //encode the string to url compatible
		props.put("loginId", URLUtils.encode(CryptoUtils.encrypt(null, WFPConfigUtils.getLoginId())));  //encode loginId to url compatible
		props.put("password", URLUtils.encode(WFPConfigUtils.getEncryptedPassword()));  //encode password to url compatible
		
		try { 
			//send the request to CS
			//http://localhost:9090/magma/GzipServlet
			Logger.info("Sending request to central server ["+centralServerUrl+"] with syncXML as ["+URLUtils.decode(props.getProperty("syncXML"))+"]", TerminalService.class);
			InputStream is = WebserviceRESTHandler.sendRequest(centralServerUrl, props, "POST");

			Synchronize sync  = null;
			String responseXML = null;
			if(is != null){ //got the response from server.... :)
				Logger.info("Received the response from central server :  ["+centralServerName+"] & deserializing data received...", TerminalService.class);
				sync = (Synchronize) CastorUtils.unmarshal( is, Synchronize.class);
				responseXML = CastorUtils.marshal(sync);
				Logger.info("Response received from central server: ["+responseXML+"] & continue for synchronization", TerminalService.class);
			}
			//make the changes in the terminal server database with the incoming changes & update the acknowledgments as well.
			if(!StringUtils.isNull(synchronizeData(sync)) ) {
				Logger.info("Changes updated on the terminal server...", TerminalService.class);
				props.clear();
				props.put("terminalResponse", "success");
				props.put("syncXML",responseXML);
				props.put("loginId", URLUtils.encode(CryptoUtils.encrypt(null, WFPConfigUtils.getLoginId())));  //encode loginId to url compatible
				props.put("password", URLUtils.encode(WFPConfigUtils.getEncryptedPassword()));  //encode password to url compatible
				Logger.info("Sending acknowledgments for the received respose...terminalResponse:[success] ", TerminalService.class);
				WebserviceRESTHandler.sendRequest(centralServerUrl, props, "POST");
			}else if(StringUtils.isNull(synchronizeData(sync)) && !isTraceById) {
				Logger.info("Sorry...synchronization is not successfull... Operation: [Rollback] performed ", TerminalService.class);
				CommonServiceDBUtils.resetTraceLogs(allTraceLogs);
			}
		} catch (IOException e) {
			Logger.error("Error ["+e.getMessage()+"] while synchronizing..", TerminalService.class, e);
		}
		return null;
	}

	private String synchronizeData(Object syncObj) {
		if(syncObj instanceof Synchronize){ // hey..its the right object..i am expecting...
			Logger.info("Verifing  whether deserialized object is Synchronize object..... [Success...]", TerminalService.class);
			return CommonServiceDBUtils.synchrnozeData((Synchronize) syncObj, this);
		}

		return null;
	}

	public long getDomainId() {
		return domainId;
	}

	public long getLanguageId() {
		return languageId;
	}

	public String getCentralServerName() {
		return centralServerName;
	}

	public Date getTerminalRequestTime() {
		return terminalRequestTime;
	}

	public String getTerminalServerName() {
		return terminalServerName;
	}

	public boolean isTerminalServer() {
		return true;
	}

	public List<SynchronizationTrailLog> getTraceLogs() {
		return allTraceLogs;
	}

	public void addTraceLogs(SynchronizationTrailLog newTraceLog) {
		allTraceLogs.add(newTraceLog);
	}

	public boolean isTraceById() {
		// TODO Auto-generated method stub
		return isTraceById;
	}

	public void setTerminalRequestTime(Date date) {
		this.terminalRequestTime = date;
	}

}
