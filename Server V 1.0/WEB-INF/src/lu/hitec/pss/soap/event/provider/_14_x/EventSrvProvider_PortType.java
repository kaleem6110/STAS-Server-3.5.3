/**
 * EventSrvProvider_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package lu.hitec.pss.soap.event.provider._14_x;

public interface EventSrvProvider_PortType extends java.rmi.Remote {
    public java.lang.String publishEvent(java.lang.String token, lu.hitec.pss.soap.event.provider._14_x.Evt newEvent) throws java.rmi.RemoteException, lu.hitec.pss.soap.event.provider._14_x.AuthenticationException, lu.hitec.pss.soap.event.provider._14_x.InsufficientCredentialsException;
    public lu.hitec.pss.soap.event.provider._14_x.EventStatusSummary getEventStatusSummary(java.lang.String token, java.lang.String eventRef) throws java.rmi.RemoteException, lu.hitec.pss.soap.event.provider._14_x.AuthenticationException, lu.hitec.pss.soap.event.provider._14_x.InsufficientCredentialsException;
    public void deleteEventByRef(java.lang.String token, java.lang.String eventRef) throws java.rmi.RemoteException, lu.hitec.pss.soap.event.provider._14_x.AuthenticationException, lu.hitec.pss.soap.event.provider._14_x.InsufficientCredentialsException;
}
