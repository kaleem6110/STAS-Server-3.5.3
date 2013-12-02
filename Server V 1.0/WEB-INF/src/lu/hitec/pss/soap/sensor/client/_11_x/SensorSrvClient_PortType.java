/**
 * SensorSrvClient_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package lu.hitec.pss.soap.sensor.client._11_x;

public interface SensorSrvClient_PortType extends java.rmi.Remote {
    public lu.hitec.pss.soap.sensor.client._11_x.DeviceSummary[] getDeviceSummaries(java.lang.String token, java.lang.String[] deviceIds, java.lang.String missionName) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._11_x.LocationRange getLocationRange(java.lang.String token, java.lang.String deviceId, java.lang.String missionName, lu.hitec.pss.soap.sensor.client._11_x.RangeLimit rangeLimit) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._11_x.ProbeRange getProbeRange(java.lang.String token, java.lang.String deviceId, java.lang.String missionName, java.lang.String probeType, lu.hitec.pss.soap.sensor.client._11_x.RangeLimit rangeLimit) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._11_x.DeviceStatus[] getDeviceStatusByMission(java.lang.String token, java.lang.String missionName) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._11_x.DeviceSummary getDeviceSummary(java.lang.String token, java.lang.String id, java.lang.String missionName) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._11_x.DeviceComparisonEntry[] getDevicesMissingInLDAPDirectoryService(java.lang.String token) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._11_x.Project getProjectDetails() throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._11_x.ServiceLimitsImpl getServiceLimits() throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._11_x.UnitsReports getAllUnitsReports(java.lang.String token, java.lang.String missionName) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._11_x.AuthenticationException;
    public lu.hitec.pss.soap.sensor.client._11_x.UnitsReports getAllUsersReports(java.lang.String token, java.lang.String missionName) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._11_x.AuthenticationException;
    public lu.hitec.pss.soap.sensor.client._11_x.UnitsReports getAllVehiclesReports(java.lang.String token, java.lang.String missionName) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._11_x.AuthenticationException;
    public lu.hitec.pss.soap.sensor.client._11_x.UnitsReports getAllPlacesReports(java.lang.String token, java.lang.String missionName) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._11_x.AuthenticationException;
    public lu.hitec.pss.soap.sensor.client._11_x.UnitSummary getUnitSummary(java.lang.String token, java.lang.String unitId, lu.hitec.pss.soap.sensor.client._11_x.UnitType unitType, java.lang.String missionName) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._11_x.LocationRange getUnitLocationRange(java.lang.String token, java.lang.String unitId, lu.hitec.pss.soap.sensor.client._11_x.UnitType unitType, java.lang.String missionName, lu.hitec.pss.soap.sensor.client._11_x.RangeLimit rangeLimit) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._11_x.ProbeRange getUnitProbeRange(java.lang.String token, java.lang.String unitId, lu.hitec.pss.soap.sensor.client._11_x.UnitType unitType, java.lang.String probeType, java.lang.String missionName, lu.hitec.pss.soap.sensor.client._11_x.RangeLimit rangeLimit) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._11_x.DeviceLocationsForMission[] getLocationRanges(java.lang.String token, lu.hitec.pss.soap.sensor.client._11_x.DeviceMission[] deviceList, lu.hitec.pss.soap.sensor.client._11_x.RangeLimit rangeLimit) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._11_x.LocationStatus evaluateLocation(java.lang.String token, lu.hitec.pss.soap.sensor.client._11_x.Point location, java.lang.String missionName) throws java.rmi.RemoteException;
}
