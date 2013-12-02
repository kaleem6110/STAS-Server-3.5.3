package lu.hitec.pss.soap.sensor.client._11_x;

public class SensorSrvClientProxy implements lu.hitec.pss.soap.sensor.client._11_x.SensorSrvClient_PortType {
  private String _endpoint = null;
  private lu.hitec.pss.soap.sensor.client._11_x.SensorSrvClient_PortType sensorSrvClient_PortType = null;
  
  public SensorSrvClientProxy() {
    _initSensorSrvClientProxy();
  }
  
  public SensorSrvClientProxy(String endpoint) {
    _endpoint = endpoint;
    _initSensorSrvClientProxy();
  }
  
  private void _initSensorSrvClientProxy() {
    try {
      sensorSrvClient_PortType = (new lu.hitec.pss.soap.sensor.client._11_x.SensorSrvClient_ServiceLocator()).getSensorSrvClientPort();
      if (sensorSrvClient_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sensorSrvClient_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sensorSrvClient_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sensorSrvClient_PortType != null)
      ((javax.xml.rpc.Stub)sensorSrvClient_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public lu.hitec.pss.soap.sensor.client._11_x.SensorSrvClient_PortType getSensorSrvClient_PortType() {
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType;
  }
  
  public lu.hitec.pss.soap.sensor.client._11_x.DeviceSummary[] getDeviceSummaries(java.lang.String token, java.lang.String[] deviceIds, java.lang.String missionName) throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getDeviceSummaries(token, deviceIds, missionName);
  }
  
  public lu.hitec.pss.soap.sensor.client._11_x.LocationRange getLocationRange(java.lang.String token, java.lang.String deviceId, java.lang.String missionName, lu.hitec.pss.soap.sensor.client._11_x.RangeLimit rangeLimit) throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getLocationRange(token, deviceId, missionName, rangeLimit);
  }
  
  public lu.hitec.pss.soap.sensor.client._11_x.ProbeRange getProbeRange(java.lang.String token, java.lang.String deviceId, java.lang.String missionName, java.lang.String probeType, lu.hitec.pss.soap.sensor.client._11_x.RangeLimit rangeLimit) throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getProbeRange(token, deviceId, missionName, probeType, rangeLimit);
  }
  
  public lu.hitec.pss.soap.sensor.client._11_x.DeviceStatus[] getDeviceStatusByMission(java.lang.String token, java.lang.String missionName) throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getDeviceStatusByMission(token, missionName);
  }
  
  public lu.hitec.pss.soap.sensor.client._11_x.DeviceSummary getDeviceSummary(java.lang.String token, java.lang.String id, java.lang.String missionName) throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getDeviceSummary(token, id, missionName);
  }
  
  public lu.hitec.pss.soap.sensor.client._11_x.DeviceComparisonEntry[] getDevicesMissingInLDAPDirectoryService(java.lang.String token) throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getDevicesMissingInLDAPDirectoryService(token);
  }
  
  public lu.hitec.pss.soap.sensor.client._11_x.Project getProjectDetails() throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getProjectDetails();
  }
  
  public lu.hitec.pss.soap.sensor.client._11_x.ServiceLimitsImpl getServiceLimits() throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getServiceLimits();
  }
  
  public lu.hitec.pss.soap.sensor.client._11_x.UnitsReports getAllUnitsReports(java.lang.String token, java.lang.String missionName) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._11_x.AuthenticationException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getAllUnitsReports(token, missionName);
  }
  
  public lu.hitec.pss.soap.sensor.client._11_x.UnitsReports getAllUsersReports(java.lang.String token, java.lang.String missionName) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._11_x.AuthenticationException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getAllUsersReports(token, missionName);
  }
  
  public lu.hitec.pss.soap.sensor.client._11_x.UnitsReports getAllVehiclesReports(java.lang.String token, java.lang.String missionName) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._11_x.AuthenticationException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getAllVehiclesReports(token, missionName);
  }
  
  public lu.hitec.pss.soap.sensor.client._11_x.UnitsReports getAllPlacesReports(java.lang.String token, java.lang.String missionName) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._11_x.AuthenticationException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getAllPlacesReports(token, missionName);
  }
  
  public lu.hitec.pss.soap.sensor.client._11_x.UnitSummary getUnitSummary(java.lang.String token, java.lang.String unitId, lu.hitec.pss.soap.sensor.client._11_x.UnitType unitType, java.lang.String missionName) throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getUnitSummary(token, unitId, unitType, missionName);
  }
  
  public lu.hitec.pss.soap.sensor.client._11_x.LocationRange getUnitLocationRange(java.lang.String token, java.lang.String unitId, lu.hitec.pss.soap.sensor.client._11_x.UnitType unitType, java.lang.String missionName, lu.hitec.pss.soap.sensor.client._11_x.RangeLimit rangeLimit) throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getUnitLocationRange(token, unitId, unitType, missionName, rangeLimit);
  }
  
  public lu.hitec.pss.soap.sensor.client._11_x.ProbeRange getUnitProbeRange(java.lang.String token, java.lang.String unitId, lu.hitec.pss.soap.sensor.client._11_x.UnitType unitType, java.lang.String probeType, java.lang.String missionName, lu.hitec.pss.soap.sensor.client._11_x.RangeLimit rangeLimit) throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getUnitProbeRange(token, unitId, unitType, probeType, missionName, rangeLimit);
  }
  
  public lu.hitec.pss.soap.sensor.client._11_x.DeviceLocationsForMission[] getLocationRanges(java.lang.String token, lu.hitec.pss.soap.sensor.client._11_x.DeviceMission[] deviceList, lu.hitec.pss.soap.sensor.client._11_x.RangeLimit rangeLimit) throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getLocationRanges(token, deviceList, rangeLimit);
  }
  
  public lu.hitec.pss.soap.sensor.client._11_x.LocationStatus evaluateLocation(java.lang.String token, lu.hitec.pss.soap.sensor.client._11_x.Point location, java.lang.String missionName) throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.evaluateLocation(token, location, missionName);
  }
  
  
}