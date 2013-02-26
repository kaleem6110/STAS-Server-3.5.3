package com.wfp.jobs;

import java.util.Date;
import java.util.List;

import lu.hitec.pss.soap.event.provider._10_x.NotificationStatusSummary;

import com.enterprisehorizons.util.Logger;
import com.enterprisehorizons.util.StringUtils;
import com.spacetimeinsight.config.scheduler.Parameters;
import com.spacetimeinsight.db.model.util.SecurityDBUtils;
import com.spacetimeinsight.magma.job.CustomJobTask;
import com.wfp.db.platform.model.AlertService;
import com.wfp.mail.Renderable;
import com.wfp.utils.AlertServiceUtils;
import com.wfp.utils.EventServiceUtils;
import com.wfp.utils.MailRetrieverUtils;

/**
 * 
 * @author sti-user
 *
 */
public class EventServiceJob implements CustomJobTask {
	 
	public EventServiceJob () {
			
	}
	
	public boolean executeCustomTask(Parameters parameters) {
		System.out.println("## START EventServiceJob.executeCustomTask : Executing Event Service.... ");
		Logger.info("Executing Event Service.... ", EventServiceJob.class);		
		getEventStatus();
		Logger.info("Completed Executing Event Service.... ", EventServiceJob.class);
		System.out.println("## END EventServiceJob.executeCustomTask : Executing Event Service.... ");
		return true;
	}


	private void readMails() {
		System.out.println("## START EventServiceJob.readMails : .... ");
		Renderable[] msg = MailRetrieverUtils.getMessages("pop.service.emergency.lu", "sti",
				"gXx8eyJ6OO6DzJRlnZEz", "pop3");
		if(msg != null){
			for (int i = 0; i < msg.length; i++) {
				if(StringUtils.isNull(SecurityDBUtils.getDecreptedPassword(msg[i].getReferenceId()))){
					continue;
				}
				AlertService alertService = AlertServiceUtils.isValidAlert(Long.valueOf(SecurityDBUtils.getDecreptedPassword(msg[i].getReferenceId())));
				
				if(alertService != null){
					AlertServiceUtils.updateAlert(msg[i], alertService);
				}				
			}
		}
		System.out.println("## END EventServiceJob.readMails : .... ");
	}
	
	private void getEventStatus(){
		Logger.info("Event Service.... [Retrieving events from Alert Service table ]", EventServiceJob.class);
		System.out.println("## START EventServiceJob.getEventStatus : .... ");
		AlertService eventService = new AlertService();
		List<AlertService> eventServiceList = eventService.retrieveAll();
		
		if(eventServiceList != null && eventServiceList.size()>0){
			Logger.info("Event Service List.... ["+eventServiceList.size()+" ]", EventServiceJob.class);
			System.out.println("Event Service List.... ["+eventServiceList.size()+" ]" );
			for(AlertService tempEvent: eventServiceList){
				if(!StringUtils.isNull(tempEvent.getEventRefId())){
					Logger.info("Processing for Event Id ["+tempEvent.getEventRefId()+" ]", EventServiceJob.class);
					NotificationStatusSummary statusSummary = EventServiceUtils.getNotificationStatusSummary(tempEvent.getEventRefId());
					if(statusSummary != null && !StringUtils.isNull(statusSummary.getResponseBody())){
						Logger.info("Processed for staus summary ["+tempEvent.getEventRefId()+" ]", EventServiceJob.class);
						System.out.println("Processed for staus summary ["+tempEvent.getEventRefId()+" ]");
						String actualResponse = statusSummary.getResponseBody();
						Logger.info("Actual Response ["+actualResponse+" ]", EventServiceJob.class);
						System.out.println("Actual Response ["+actualResponse+" ]");
						if(actualResponse.lastIndexOf("-----Original Message-----") > -1){
							actualResponse = statusSummary.getResponseBody().substring(0, statusSummary.getResponseBody().lastIndexOf("-----Original Message-----"));
						}
						tempEvent.setReceivedMsg(actualResponse);
					}
					
					tempEvent.setReceivedOn(new Date());
					tempEvent.updateData();
				}
				
			}
		}
		System.out.println("## END EventServiceJob.getEventStatus : .... ");
	}
	
}
