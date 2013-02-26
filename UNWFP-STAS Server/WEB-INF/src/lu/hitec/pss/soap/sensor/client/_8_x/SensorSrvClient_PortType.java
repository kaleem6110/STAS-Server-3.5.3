/**
 * SensorSrvClient_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package lu.hitec.pss.soap.sensor.client._8_x;

public interface SensorSrvClient_PortType extends java.rmi.Remote {
    public lu.hitec.pss.soap.sensor.client._8_x.Device getDevice(java.lang.String token, java.lang.String id, java.lang.String missionName) throws java.rmi.RemoteException;
    public java.lang.String[] waitForAnyUpdate(java.lang.String token, lu.hitec.pss.soap.sensor.client._8_x.DeviceStatus[] devStatus, int maxWaitSeconds) throws java.rmi.RemoteException;
    public java.lang.String[] waitForUpdateRestricted(java.lang.String token, lu.hitec.pss.soap.sensor.client._8_x.DeviceStatus[] devStatus, int maxWaitSeconds) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._8_x.Device[] getDevices(java.lang.String token, java.lang.String[] deviceIds, java.lang.String missionName) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._8_x.LocationRange getLocationRange(java.lang.String token, java.lang.String deviceId, java.lang.String missionName, lu.hitec.pss.soap.sensor.client._8_x.RangeLimit rangeLimit) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._8_x.ProbeRange getProbeRange(java.lang.String token, java.lang.String deviceId, java.lang.String missionName, java.lang.String probeType, lu.hitec.pss.soap.sensor.client._8_x.RangeLimit rangeLimit) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._8_x.DeviceStatus[] getDeviceStatus() throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._8_x.DeviceStatus[] getDeviceStatusByMission(java.lang.String token, java.lang.String missionName) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._8_x.DeviceComparisonEntry[] getDevicesMissingInLDAPDirectoryService(java.lang.String token) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._8_x.Project getProjectDetails() throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._8_x.ServiceLimitsImpl getServiceLimits() throws java.rmi.RemoteException;
}
