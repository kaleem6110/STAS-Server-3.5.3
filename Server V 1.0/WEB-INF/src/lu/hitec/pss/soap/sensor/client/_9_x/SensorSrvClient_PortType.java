/**
 * SensorSrvClient_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package lu.hitec.pss.soap.sensor.client._9_x;

public interface SensorSrvClient_PortType extends java.rmi.Remote {
    public lu.hitec.pss.soap.sensor.client._9_x.Device getDevice(java.lang.String token, java.lang.String id, java.lang.String missionName) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._9_x.Device[] getDevices(java.lang.String token, java.lang.String[] deviceIds, java.lang.String missionName) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._9_x.LocationRange getLocationRange(java.lang.String token, java.lang.String deviceId, java.lang.String missionName, lu.hitec.pss.soap.sensor.client._9_x.RangeLimit rangeLimit) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._9_x.ProbeRange getProbeRange(java.lang.String token, java.lang.String deviceId, java.lang.String missionName, java.lang.String probeType, lu.hitec.pss.soap.sensor.client._9_x.RangeLimit rangeLimit) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._9_x.DeviceStatus[] getDeviceStatusByMission(java.lang.String token, java.lang.String missionName) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._9_x.DeviceComparisonEntry[] getDevicesMissingInLDAPDirectoryService(java.lang.String token) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._9_x.Project getProjectDetails() throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._9_x.ServiceLimitsImpl getServiceLimits() throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._9_x.UnitsReports getAllUnitsReports(java.lang.String token, java.lang.String missionName) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._9_x.AuthenticationException;
    public lu.hitec.pss.soap.sensor.client._9_x.UnitsReports getAllUsersReports(java.lang.String token, java.lang.String missionName) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._9_x.AuthenticationException;
    public lu.hitec.pss.soap.sensor.client._9_x.DeviceLocationsForMission[] getLocationRanges(java.lang.String token, lu.hitec.pss.soap.sensor.client._9_x.DeviceMission[] deviceList, lu.hitec.pss.soap.sensor.client._9_x.RangeLimit rangeLimit) throws java.rmi.RemoteException;
}
