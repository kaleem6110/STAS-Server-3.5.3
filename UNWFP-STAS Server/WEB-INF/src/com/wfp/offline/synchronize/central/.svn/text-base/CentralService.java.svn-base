package com.wfp.offline.synchronize.central;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.enterprisehorizons.magma.server.admin.AdminConfigUtils;
import com.enterprisehorizons.magma.server.util.ServerUtils;
import com.enterprisehorizons.util.CastorUtils;
import com.enterprisehorizons.util.Logger;
import com.enterprisehorizons.util.StringUtils;
import com.spacetimeinsight.db.model.util.DataModelsCacheHelper;
import com.wfp.offline.synchronize.GzipServlet;
import com.wfp.offline.synchronize.ISynchronizationService;
import com.wfp.offline.synchronize.terminal.TerminalService;
import com.wfp.offline.synchronize.utils.CommonServiceDBUtils;
import com.wfp.synchronize.Synchronize;
import com.wfp.synchronize.db.config.model.SynchronizationTrailLog;

public class CentralService implements ISynchronizationService {

	private List<SynchronizationTrailLog> allTraceLogs = null;
	private String centralServerName =  ServerUtils.getSystemServerName()+"_"+AdminConfigUtils.getHttpServerPort();
	private String termialServerName =  null;
	private long domainId = DataModelsCacheHelper.getDomainId("$SELF$");
	private long languageId= DataModelsCacheHelper.getLanguageId("en", "US");
	private Synchronize syncObj = null;
	private Date terminalRequestTime = null;
	private boolean isTraceById = false;
	  
	public  CentralService(String xmlData){
		this(xmlData, false);
		Logger.info("Constructor: Received Data ["+xmlData+"] & not an acknowledgment resposne ", CentralService.class);
	}
	
	public  CentralService(String xmlData, boolean isTerminalResponse){
		Logger.info("Constructor: Received Data ["+xmlData+"] & is an acknowledgment resposne ", CentralService.class);
		if(!StringUtils.isNull(xmlData)){
			syncObj = (Synchronize) CastorUtils.unmarshalString(xmlData, Synchronize.class);
			termialServerName = syncObj.getHostname();
			isTraceById = syncObj.getTraceById();
			terminalRequestTime = syncObj.getProcessedTime();
		}
		if(!isTerminalResponse){
			if(!isTraceById ){
				allTraceLogs = CommonServiceDBUtils.getTraceLogs(SYNC_STATUS_NEW, false, termialServerName);
			}else{
				allTraceLogs = new ArrayList<SynchronizationTrailLog>();	
			}
			if(allTraceLogs != null){
				//System.out.println("Number of logs = "+ allTraceLogs.size());
			}else {
				//System.out.println("Number of logs = 0");
			}
			terminalRequestTime = new Date();
			Logger.info("Terminal Server ["+termialServerName+"] connected to... Central Server: ["+centralServerName+"] at ["+terminalRequestTime+"] ", TerminalService.class);
		}
		
	}
	
	
	
	public String synchronizeData(){
		return synchronizeData(syncObj);
	}

	private String synchronizeData(Object syncObj) {
		Logger.info("Central Server:  Synchronize data.. ", CentralService.class);
		isTraceById = ((Synchronize)syncObj).getTraceById();
		if(isTraceById){
			return CommonServiceDBUtils.synchrnozeById((Synchronize) syncObj, this);
		}
		
		return CommonServiceDBUtils.synchrnozeData((Synchronize) syncObj, this);
	}
	
	public void updateProcessedTime(String hostName, Date processedTime){
		CommonServiceDBUtils.updateTrailStatusLog(hostName, processedTime);
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
		return termialServerName;
	}



	public boolean isTerminalServer() {
		return false;
	}



	public List<SynchronizationTrailLog> getTraceLogs() {
		return allTraceLogs;
	}



	public void addTraceLogs(SynchronizationTrailLog newTraceLog) {
		if(allTraceLogs != null){
			allTraceLogs.add(newTraceLog);
		}						
	}



	public boolean isTraceById() {
		return isTraceById;
	}



	public void setTerminalRequestTime(Date date) {
		this.terminalRequestTime = date;
	}

	public void updateTraceLogs() {
		if(syncObj != null){
			Logger.info("Start: Acknowledgment response ... Commiting the trasaction.....", GzipServlet.class);
			CommonServiceDBUtils.updateTraceLogs(syncObj, this.getCentralServerName());
			if(!syncObj.getTraceById()){
				updateProcessedTime(getTerminalServerName(), getTerminalRequestTime());
			}
			Logger.info("End: Acknowledgment response ... Commited the trasaction.....", GzipServlet.class);
		}
		
	}
	
}
