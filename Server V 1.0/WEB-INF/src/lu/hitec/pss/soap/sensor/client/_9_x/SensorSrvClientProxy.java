package lu.hitec.pss.soap.sensor.client._9_x;

public class SensorSrvClientProxy implements lu.hitec.pss.soap.sensor.client._9_x.SensorSrvClient_PortType {
  private String _endpoint = null;
  private lu.hitec.pss.soap.sensor.client._9_x.SensorSrvClient_PortType sensorSrvClient_PortType = null;
  
  public SensorSrvClientProxy() {
    _initSensorSrvClientProxy();
  }
  
  public SensorSrvClientProxy(String endpoint) {
    _endpoint = endpoint;
    _initSensorSrvClientProxy();
  }
  
  private void _initSensorSrvClientProxy() {
    try {
      sensorSrvClient_PortType = (new lu.hitec.pss.soap.sensor.client._9_x.SensorSrvClient_ServiceLocator()).getSensorSrvClientPort();
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
  
  public lu.hitec.pss.soap.sensor.client._9_x.SensorSrvClient_PortType getSensorSrvClient_PortType() {
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType;
  }
  
  public lu.hitec.pss.soap.sensor.client._9_x.Device getDevice(java.lang.String token, java.lang.String id, java.lang.String missionName) throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getDevice(token, id, missionName);
  }
  
  public lu.hitec.pss.soap.sensor.client._9_x.Device[] getDevices(java.lang.String token, java.lang.String[] deviceIds, java.lang.String missionName) throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getDevices(token, deviceIds, missionName);
  }
  
  public lu.hitec.pss.soap.sensor.client._9_x.LocationRange getLocationRange(java.lang.String token, java.lang.String deviceId, java.lang.String missionName, lu.hitec.pss.soap.sensor.client._9_x.RangeLimit rangeLimit) throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getLocationRange(token, deviceId, missionName, rangeLimit);
  }
  
  public lu.hitec.pss.soap.sensor.client._9_x.ProbeRange getProbeRange(java.lang.String token, java.lang.String deviceId, java.lang.String missionName, java.lang.String probeType, lu.hitec.pss.soap.sensor.client._9_x.RangeLimit rangeLimit) throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getProbeRange(token, deviceId, missionName, probeType, rangeLimit);
  }
  
  public lu.hitec.pss.soap.sensor.client._9_x.DeviceStatus[] getDeviceStatusByMission(java.lang.String token, java.lang.String missionName) throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getDeviceStatusByMission(token, missionName);
  }
  
  public lu.hitec.pss.soap.sensor.client._9_x.DeviceComparisonEntry[] getDevicesMissingInLDAPDirectoryService(java.lang.String token) throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getDevicesMissingInLDAPDirectoryService(token);
  }
  
  public lu.hitec.pss.soap.sensor.client._9_x.Project getProjectDetails() throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getProjectDetails();
  }
  
  public lu.hitec.pss.soap.sensor.client._9_x.ServiceLimitsImpl getServiceLimits() throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getServiceLimits();
  }
  
  public lu.hitec.pss.soap.sensor.client._9_x.UnitsReports getAllUnitsReports(java.lang.String token, java.lang.String missionName) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._9_x.AuthenticationException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getAllUnitsReports(token, missionName);
  }
  
  public lu.hitec.pss.soap.sensor.client._9_x.UnitsReports getAllUsersReports(java.lang.String token, java.lang.String missionName) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._9_x.AuthenticationException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getAllUsersReports(token, missionName);
  }
  
  public lu.hitec.pss.soap.sensor.client._9_x.DeviceLocationsForMission[] getLocationRanges(java.lang.String token, lu.hitec.pss.soap.sensor.client._9_x.DeviceMission[] deviceList, lu.hitec.pss.soap.sensor.client._9_x.RangeLimit rangeLimit) throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getLocationRanges(token, deviceList, rangeLimit);
  }
  
  
}