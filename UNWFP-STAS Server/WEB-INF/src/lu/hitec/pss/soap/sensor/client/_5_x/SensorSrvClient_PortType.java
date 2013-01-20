/**
 * SensorSrvClient_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package lu.hitec.pss.soap.sensor.client._5_x;

public interface SensorSrvClient_PortType extends java.rmi.Remote {
    public lu.hitec.pss.soap.sensor.client._5_x.Device getDevice(java.lang.String id) throws java.rmi.RemoteException;
    public java.lang.String[] waitForAnyUpdate(lu.hitec.pss.soap.sensor.client._5_x.DeviceStatus[] devStatus, int maxWaitSeconds) throws java.rmi.RemoteException;
    public java.lang.String[] waitForUpdateRestricted(lu.hitec.pss.soap.sensor.client._5_x.DeviceStatus[] devStatus, int maxWaitSeconds) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._5_x.Device[] getDevices(java.lang.String[] id) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._5_x.LocationRange getLocationRange(java.lang.String deviceId, lu.hitec.pss.soap.sensor.client._5_x.RangeLimit rangeLimit) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._5_x.ProbeRange getProbeRange(java.lang.String deviceId, java.lang.String probeType, lu.hitec.pss.soap.sensor.client._5_x.RangeLimit rangeLimit) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._5_x.DeviceStatus[] getDeviceStatus() throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._5_x.Device[] getAllDevicesCircle(lu.hitec.pss.soap.sensor.client._5_x.Point center, double radiusInMeters) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._5_x.Device[] getAllDevicesInPolygon(lu.hitec.pss.soap.sensor.client._5_x.Point[] polygonVertexes) throws java.rmi.RemoteException;
    public lu.hitec.pss.soap.sensor.client._5_x.DeviceComparisonEntry[] getDevicesMissingInLDAPDirectoryService() throws java.rmi.RemoteException;
}
