/**
 * SensorSrvClient_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package lu.hitec.pss.soap.sensor.client._12_x;

public interface SensorSrvClient_PortType extends java.rmi.Remote {
    public lu.hitec.pss.soap.sensor.client._12_x.UnitsReports getAllUnitsReports(java.lang.String token, java.lang.String missionId) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException, lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException;
    public lu.hitec.pss.soap.sensor.client._12_x.UnitReport[] getAllUsersReports(java.lang.String token, java.lang.String missionId) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException, lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException;
    public lu.hitec.pss.soap.sensor.client._12_x.UnitReport[] getAllVehiclesReports(java.lang.String token, java.lang.String missionId) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException, lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException;
    public lu.hitec.pss.soap.sensor.client._12_x.LocationStatus evaluateLocation(java.lang.String token, lu.hitec.pss.soap.sensor.client._12_x.Point location, java.lang.String missionId) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._12_x.DeviceLocationsForMission[] getLocationRanges(java.lang.String token, lu.hitec.pss.soap.sensor.client._12_x.DeviceMission[] deviceList, lu.hitec.pss.soap.sensor.client._12_x.RangeLimit rangeLimit) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._12_x.Project getProjectDetails() throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._12_x.UnitReport[] getAllPlacesReports(java.lang.String token, java.lang.String missionId) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException, lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException;
    public lu.hitec.pss.soap.sensor.client._12_x.LocationRange getUnitLocationRange(java.lang.String token, lu.hitec.pss.soap.sensor.client._12_x.UnitId unitId, java.lang.String missionId, lu.hitec.pss.soap.sensor.client._12_x.RangeLimit rangeLimit) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._12_x.UnitSummary getUnitSummary(java.lang.String token, lu.hitec.pss.soap.sensor.client._12_x.UnitId unitId, java.lang.String missionId) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._12_x.LocationValue getUnitLastLocation(java.lang.String token, lu.hitec.pss.soap.sensor.client._12_x.UnitId unitId, java.lang.String missionId) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._12_x.ServiceLimitsImpl getServiceLimits() throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._12_x.ProbeRange getUnitProbeRange(java.lang.String token, lu.hitec.pss.soap.sensor.client._12_x.UnitId unitId, java.lang.String probeType, java.lang.String missionId, lu.hitec.pss.soap.sensor.client._12_x.RangeLimit rangeLimit) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._12_x.DeviceComparisonEntry[] getDevicesMissingInLdapDirectoryService(java.lang.String token) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._12_x.UnitsReports searchUnitsByCircleZone(java.lang.String token, java.lang.String missionId, lu.hitec.pss.soap.sensor.client._12_x.SimpleCircularZone circle) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException, lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException;
    public lu.hitec.pss.soap.sensor.client._12_x.UnitsReports searchUnitsByPolygonZone(java.lang.String token, java.lang.String missionId, lu.hitec.pss.soap.sensor.client._12_x.SimplePolygonZone polygon) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException, lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException;
    public lu.hitec.pss.soap.sensor.client._12_x.UnitReport[] searchPlacesByCircleZone(java.lang.String token, java.lang.String missionId, lu.hitec.pss.soap.sensor.client._12_x.SimpleCircularZone circle) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException, lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException;
    public lu.hitec.pss.soap.sensor.client._12_x.UnitReport[] searchVehiclesByPolygonZone(java.lang.String token, java.lang.String missionId, lu.hitec.pss.soap.sensor.client._12_x.SimplePolygonZone polygon) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException, lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException;
    public lu.hitec.pss.soap.sensor.client._12_x.UnitReport[] searchUsersByCircleZone(java.lang.String token, java.lang.String missionId, lu.hitec.pss.soap.sensor.client._12_x.SimpleCircularZone circle) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException, lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException;
    public lu.hitec.pss.soap.sensor.client._12_x.UnitReport[] searchUsersByPolygonZone(java.lang.String token, java.lang.String missionId, lu.hitec.pss.soap.sensor.client._12_x.SimplePolygonZone polygon) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException, lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException;
    public lu.hitec.pss.soap.sensor.client._12_x.UnitReport[] searchVehiclesByCircleZone(java.lang.String token, java.lang.String missionId, lu.hitec.pss.soap.sensor.client._12_x.SimpleCircularZone circle) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException, lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException;
    public lu.hitec.pss.soap.sensor.client._12_x.UnitReport[] searchPlacesByPolygonZone(java.lang.String token, java.lang.String missionId, lu.hitec.pss.soap.sensor.client._12_x.SimplePolygonZone polygon) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException, lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException;
}
