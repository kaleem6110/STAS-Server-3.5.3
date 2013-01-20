package lu.hitec.pss.soap.sensor.client._5_x;

public class SensorSrvClientProxy implements lu.hitec.pss.soap.sensor.client._5_x.SensorSrvClient_PortType {
  private String _endpoint = null;
  private lu.hitec.pss.soap.sensor.client._5_x.SensorSrvClient_PortType sensorSrvClient_PortType = null;
  
  public SensorSrvClientProxy() {
    _initSensorSrvClientProxy();
  }
  
  public SensorSrvClientProxy(String endpoint) {
    _endpoint = endpoint;
    _initSensorSrvClientProxy();
  }
  
  private void _initSensorSrvClientProxy() {
    try {
      sensorSrvClient_PortType = (new lu.hitec.pss.soap.sensor.client._5_x.SensorSrvClient_ServiceLocator()).getSensorSrvClientPort();
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
  
  public lu.hitec.pss.soap.sensor.client._5_x.SensorSrvClient_PortType getSensorSrvClient_PortType() {
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType;
  }
  
  public lu.hitec.pss.soap.sensor.client._5_x.Device getDevice(java.lang.String id) throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getDevice(id);
  }
  
  public java.lang.String[] waitForAnyUpdate(lu.hitec.pss.soap.sensor.client._5_x.DeviceStatus[] devStatus, int maxWaitSeconds) throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.waitForAnyUpdate(devStatus, maxWaitSeconds);
  }
  
  public java.lang.String[] waitForUpdateRestricted(lu.hitec.pss.soap.sensor.client._5_x.DeviceStatus[] devStatus, int maxWaitSeconds) throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.waitForUpdateRestricted(devStatus, maxWaitSeconds);
  }
  
  public lu.hitec.pss.soap.sensor.client._5_x.Device[] getDevices(java.lang.String[] id) throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getDevices(id);
  }
  
  public lu.hitec.pss.soap.sensor.client._5_x.LocationRange getLocationRange(java.lang.String deviceId, lu.hitec.pss.soap.sensor.client._5_x.RangeLimit rangeLimit) throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getLocationRange(deviceId, rangeLimit);
  }
  
  public lu.hitec.pss.soap.sensor.client._5_x.ProbeRange getProbeRange(java.lang.String deviceId, java.lang.String probeType, lu.hitec.pss.soap.sensor.client._5_x.RangeLimit rangeLimit) throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getProbeRange(deviceId, probeType, rangeLimit);
  }
  
  public lu.hitec.pss.soap.sensor.client._5_x.DeviceStatus[] getDeviceStatus() throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getDeviceStatus();
  }
  
  public lu.hitec.pss.soap.sensor.client._5_x.Device[] getAllDevicesCircle(lu.hitec.pss.soap.sensor.client._5_x.Point center, double radiusInMeters) throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getAllDevicesCircle(center, radiusInMeters);
  }
  
  public lu.hitec.pss.soap.sensor.client._5_x.Device[] getAllDevicesInPolygon(lu.hitec.pss.soap.sensor.client._5_x.Point[] polygonVertexes) throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getAllDevicesInPolygon(polygonVertexes);
  }
  
  public lu.hitec.pss.soap.sensor.client._5_x.DeviceComparisonEntry[] getDevicesMissingInLDAPDirectoryService() throws java.rmi.RemoteException{
    if (sensorSrvClient_PortType == null)
      _initSensorSrvClientProxy();
    return sensorSrvClient_PortType.getDevicesMissingInLDAPDirectoryService();
  }
  
  
}