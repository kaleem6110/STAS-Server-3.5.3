package com.wfp.utils;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Calendar;

import lu.hitec.pss.soap.event.provider._10_x.EventDescription;
import lu.hitec.pss.soap.event.provider._10_x.EventRecipient;
import lu.hitec.pss.soap.event.provider._10_x.EventSrvProviderPortBindingStub;
import lu.hitec.pss.soap.event.provider._10_x.EventSrvProvider_Service;
import lu.hitec.pss.soap.event.provider._10_x.EventSrvProvider_ServiceLocator;
import lu.hitec.pss.soap.event.provider._10_x.EventStatus;
import lu.hitec.pss.soap.event.provider._10_x.EventStatusSummary;
import lu.hitec.pss.soap.event.provider._10_x.Evt;
import lu.hitec.pss.soap.event.provider._10_x.NotificationStatusSummary;
import lu.hitec.pss.soap.event.provider._10_x.Severity;

import org.apache.axis.AxisFault;

import com.enterprisehorizons.util.Logger;





public class EventServiceUtils {

	 
	
	/*public static List<DeviceBean> printEventDetails(){
		try {
			EventSrvClientPortBindingStub stub = getEventSrvClienrStub();
			DomStatus[] domStatus = stub.getDomainStatus();
			
			EvtFilter evtFilter = new EvtFilter();
			evtFilter.setSrc("trackMe9735");
			
			DomUpdate[] domUpdate = stub.getEventUpdates(domStatus, evtFilter, 10);
			if(domUpdate != null){
				for(int i=0;i<domUpdate.length;i++){
					Event[] evt = domUpdate[i].getEvts();
					if(evt != null){
						for(int j=0;j<evt.length;j++){
							//System.out.println(evt);
						}
					}
					
					//System.out.println(domUpdate[i].getEvts());
				}
			}
			
			return null;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}*/
	
	/*public static String publishEvent(String deviceId, String refId, String subject, String source, String body){
		try {
			EventSrvProviderPortBindingStub stub = getEventSrvProviderStub();
			String evtRef = stub.publishEvent(getEvt(deviceId, subject, body, source), null);
			//System.out.println("Event reference Id"+ evtRef );
			return evtRef;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static String publishEvent(Evt evt){
		try {
			EventSrvProviderPortBindingStub stub = getEventSrvProviderStub();
			String evtRef = stub.publishEvent(evt, null);
			//System.out.println("Event reference Id"+ evtRef );
			return evtRef;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Evt getEvt(String deviceId, String subject, String body, String source ){
		EventRelatedInfo eri = new EventRelatedInfo();
		//eri.setRelDeviceId("trackMe9735");
		//eri.setRelEventRef("Idmw-A1-service-3c2b5827-9410-4376-8c4e-5fbf7f6b8659");
		eri.setRelDeviceId(deviceId);
		
		Evt tempEvt = retrieveEvt(deviceId);
		if(tempEvt != null){
			EventRelatedInfo temperi = tempEvt.getRelatedInfo();				
			eri.setRelEventRef(temperi.getRelEventRef());
		}
	
		
		EventDescription desc = new EventDescription();
		//desc.setShortDesc("Testing from STI Portal ..Device trackMe9245 is danger zone..please ignore this mail");
		desc.setShortDesc(subject);
		desc.setLongDesc(body);
		
		
		
		Evt evt = new Evt();
		evt.setDesc(desc);
		evt.setRelatedInfo(eri);
		evt.setSeverity(Severity.WARNING);
		evt.setSrc(source);
		evt.setTime(Calendar.getInstance());
		evt.setType("Unsafe Zone");
		
		return evt;
	}*/
	
	/*public static List<DeviceBean> checkEventStatus(String id){
		try {
			EventSrvProviderPortBindingStub stub = getEventSrvProviderStub();
			
			
			EventStatus es = stub.getEventStatus(id);
			if(es != null){
				//System.out.println("Event reference Id"+ es.getValue() );
			}
			
			return null;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}*/
	
	/*public static EventSrvClientPortBindingStub  getEventSrvClienrStub(){
		EventSrvClient_Service service =  new EventSrvClient_ServiceLocator();		
		//http://middleware.service.emergency.lu/eventservice/admin/rest/events
		try {
			return new EventSrvClientPortBindingStub( 
					new java.net.URL("http://middleware.service.emergency.lu/eventservice/out/soap/EventSrvClient"),service) ;
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}*/
	
	/*public static EventSrvProviderPortBindingStub getEventSrvProviderStub(){
		EventSrvProvider_Service service =  new EventSrvProvider_ServiceLocator();		
		
		try {
			return new EventSrvProviderPortBindingStub( 
					new java.net.URL("http://middleware.service.emergency.lu/eventservice/in/soap/EventSrvProvider"),service) ;
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static void storeEvt(String deviceId, Evt evt){
		if(getAlertServiceCache() != null){
			getAlertServiceCache().put(deviceId, evt);
		}
	}
	
	public static Evt retrieveEvt(String deviceId){
		if(getAlertServiceCache() != null){
			getAlertServiceCache().get(deviceId);
		}
		
		return null;
	}*/
	
	
	
	
/*	public static Map<String, Object> getAlertServiceCache() {
		return (Map<String, Object>) Cache.retrieve("$ALERT_SERVICE$");
	}
	
	public static void publishEvent(String dangerZoneName, String deviceId ){
		com.wfp.db.platform.model.MessageTemplate mt = com.wfp.utils.RBRegionsUtils.getMessageTemplate(dangerZoneName);
		if(mt == null){
			return;
		}
		Evt tempEvt = com.wfp.utils.EventServiceUtils.getEvt(deviceId, mt.getSubject(), deviceId, mt.getBody() );
		String refId = com.wfp.utils.EventServiceUtils.publishEvent(tempEvt );
		tempEvt.getRelatedInfo().setRelEventRef(refId);
		com.wfp.utils.EventServiceUtils.storeEvt(deviceId, tempEvt);	
	}*/
	
	
	public static EventSrvProviderPortBindingStub  getServiceLocatorStub(){
		EventSrvProvider_Service service =  new EventSrvProvider_ServiceLocator();		
		
		try {
			Logger.info("Getting notification summary Service locator  ["+WFPConfigUtils.getWFPConfigValue("alertservice")+" ]", EventServiceUtils.class);
			return new EventSrvProviderPortBindingStub( 
					new java.net.URL(WFPConfigUtils.getWFPConfigValue("alertservice") == null?"http://middleware.service.emergency.lu/eventservice/in/soap/EventSrvProvider?wsdl":WFPConfigUtils.getWFPConfigValue("alertservice")),
					service) ;
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	/*public static void publishEvent(){
		EventSrvProviderPortBindingStub stub = getServiceLocatorStub();
		Evt evt = new Evt();
		evt.setType("alerting");
		
		EventDescription desc = new EventDescription();
		//desc.setShortDesc("Testing from STI Portal ..Device trackMe9245 is danger zone..please ignore this mail");
		desc.setShortDesc("Testing Alert Service");
		desc.setLongDesc("Hi ... Testing the Alert Service from EPIC Portal....");
		
		evt.setDesc(desc);
		EventRecipient er = new EventRecipient();
		er.setType("alerting");
		er.setUserOrGroupUID("");
		evt.setEventRecipient(er);
		
		try {
			String evtRef = stub.publishEvent(evt, null);
			
			EventStatusSummary summary = stub.getEventStatusSummary(evtRef);
			//summary.g
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public static String publishEventService(String userUniqueId, String subject, String body){
		System.out.println("#### START publishEventService : userUniqueId"+userUniqueId +" : subject :"+subject+":body: "+body );
		EventSrvProviderPortBindingStub stub = getServiceLocatorStub();
		
		EventRecipient er = new EventRecipient();
		er.setType("USER");
		er.setUserOrGroupUID(userUniqueId);
		
		
		
		EventDescription desc = new EventDescription();
		desc.setShortDesc(subject);
		desc.setLongDesc(body);
		//TimeZone.setDefault(TimeZone.getTimeZone("Rome"));
		Evt evt = new Evt();
		evt.setType("alerting");
		evt.setDesc(desc);		
		evt.setSrc("STAS_Engine");
		evt.setEventRecipient(er);
		evt.setMissionName("");
		Calendar cal = Calendar.getInstance();		
		evt.setTime(cal);
		evt.setSeverity(Severity.CRITICAL);
		evt.setEventStatus(EventStatus.OPEN);
		
		try {
			return  stub.publishEvent( null,evt);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String getEventStatus(String eventRef){
		EventSrvProviderPortBindingStub stub = getServiceLocatorStub();
		try {//FIXIT - 
			EventStatusSummary eventStatusSummary = stub.getEventStatusSummary(null,eventRef);
			NotificationStatusSummary[] statusSummary = eventStatusSummary.getNotificationStatusSummaries();
			if(statusSummary != null){
				return statusSummary[0].getNotificationStatus().getValue();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static NotificationStatusSummary getNotificationStatusSummary(String eventRef){
		Logger.info("Getting notification summary  ["+eventRef+" ]", EventServiceUtils.class);
		EventSrvProviderPortBindingStub stub = getServiceLocatorStub();
		try {
			EventStatusSummary eventStatusSummary = stub.getEventStatusSummary(null,eventRef);
			Logger.info("Got notification summary  ["+eventStatusSummary+" ]", EventServiceUtils.class);
			NotificationStatusSummary[] statusSummary = eventStatusSummary!=null?eventStatusSummary.getNotificationStatusSummaries():null;
			if(statusSummary != null){
				for(NotificationStatusSummary tempSummary: statusSummary){
					if(tempSummary.getNotificationStatus().getValue().equalsIgnoreCase("ACKNOWLEDGED")){
						return tempSummary;
					}
				}				
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String args[]){
		//printEventDetails();
		//publishEvent();
		 //checkEventStatus(publishEvent());
	}
}
