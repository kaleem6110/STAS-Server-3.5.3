/**
 * SensorSrvClientPortBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package lu.hitec.pss.soap.sensor.client._11_x;

public class SensorSrvClientPortBindingStub extends org.apache.axis.client.Stub implements lu.hitec.pss.soap.sensor.client._11_x.SensorSrvClient_PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[17];
        _initOperationDesc1();
        _initOperationDesc2();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDeviceSummaries");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "deviceIds"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "missionName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "deviceSummary"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._11_x.DeviceSummary[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "deviceSummary"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getLocationRange");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "deviceId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "missionName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "rangeLimit"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "rangeLimit"), lu.hitec.pss.soap.sensor.client._11_x.RangeLimit.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "locationRange"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._11_x.LocationRange.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "locationRange"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getProbeRange");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "deviceId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "missionName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "probeType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "rangeLimit"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "rangeLimit"), lu.hitec.pss.soap.sensor.client._11_x.RangeLimit.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "probeRange"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._11_x.ProbeRange.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "probeRange"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDeviceStatusByMission");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "missionName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "deviceStatus"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._11_x.DeviceStatus[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "status"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDeviceSummary");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "missionName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "deviceSummary"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._11_x.DeviceSummary.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "deviceSummary"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDevicesMissingInLDAPDirectoryService");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "deviceComparisonEntry"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._11_x.DeviceComparisonEntry[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "res"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getProjectDetails");
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "project"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._11_x.Project.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "res"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getServiceLimits");
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "serviceLimitsImpl"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._11_x.ServiceLimitsImpl.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "res"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAllUnitsReports");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "missionName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "unitsReports"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._11_x.UnitsReports.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "res"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "AuthenticationException"),
                      "lu.hitec.pss.soap.sensor.client._11_x.AuthenticationException",
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "AuthenticationException"), 
                      true
                     ));
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAllUsersReports");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "missionName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "unitsReports"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._11_x.UnitsReports.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "res"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "AuthenticationException"),
                      "lu.hitec.pss.soap.sensor.client._11_x.AuthenticationException",
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "AuthenticationException"), 
                      true
                     ));
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAllVehiclesReports");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "missionName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "unitsReports"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._11_x.UnitsReports.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "res"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "AuthenticationException"),
                      "lu.hitec.pss.soap.sensor.client._11_x.AuthenticationException",
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "AuthenticationException"), 
                      true
                     ));
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAllPlacesReports");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "missionName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "unitsReports"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._11_x.UnitsReports.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "res"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "AuthenticationException"),
                      "lu.hitec.pss.soap.sensor.client._11_x.AuthenticationException",
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "AuthenticationException"), 
                      true
                     ));
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getUnitSummary");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "unitId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "unitType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "unitType"), lu.hitec.pss.soap.sensor.client._11_x.UnitType.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "missionName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "unitSummary"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._11_x.UnitSummary.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "unitSummary"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getUnitLocationRange");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "unitId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "unitType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "unitType"), lu.hitec.pss.soap.sensor.client._11_x.UnitType.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "missionName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "rangeLimit"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "rangeLimit"), lu.hitec.pss.soap.sensor.client._11_x.RangeLimit.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "locationRange"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._11_x.LocationRange.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "unitSummary"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getUnitProbeRange");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "unitId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "unitType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "unitType"), lu.hitec.pss.soap.sensor.client._11_x.UnitType.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "probeType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "missionName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "rangeLimit"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "rangeLimit"), lu.hitec.pss.soap.sensor.client._11_x.RangeLimit.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "probeRange"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._11_x.ProbeRange.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "unitSummary"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getLocationRanges");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "deviceList"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "deviceMission"), lu.hitec.pss.soap.sensor.client._11_x.DeviceMission[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "rangeLimit"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "rangeLimit"), lu.hitec.pss.soap.sensor.client._11_x.RangeLimit.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "deviceLocationsForMission"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._11_x.DeviceLocationsForMission[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "res"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("evaluateLocation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "location"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "point"), lu.hitec.pss.soap.sensor.client._11_x.Point.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "missionName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "locationStatus"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._11_x.LocationStatus.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "res"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[16] = oper;

    }

    public SensorSrvClientPortBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public SensorSrvClientPortBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public SensorSrvClientPortBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "AuthenticationException");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._11_x.AuthenticationException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "deviceComparisonEntry");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._11_x.DeviceComparisonEntry.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "deviceLocationsForMission");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._11_x.DeviceLocationsForMission.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "deviceMission");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._11_x.DeviceMission.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "deviceStatus");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._11_x.DeviceStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "deviceSummary");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._11_x.DeviceSummary.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "locationRange");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._11_x.LocationRange.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "locationStatus");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._11_x.LocationStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "locationSummary");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._11_x.LocationSummary.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "locationValue");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._11_x.LocationValue.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "point");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._11_x.Point.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "probeRange");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._11_x.ProbeRange.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "probeStatus");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._11_x.ProbeStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "probeSummary");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._11_x.ProbeSummary.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "probeValue");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._11_x.ProbeValue.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "project");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._11_x.Project.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "rangeDetails");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._11_x.RangeDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "rangeLimit");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._11_x.RangeLimit.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "serviceLimitsImpl");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._11_x.ServiceLimitsImpl.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "severity");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._11_x.Severity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "subRangeDetails");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._11_x.SubRangeDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "subRangeType");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._11_x.SubRangeType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "timeRange");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._11_x.TimeRange.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "unitReport");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._11_x.UnitReport.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "unitsReports");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._11_x.UnitsReports.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "unitSummary");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._11_x.UnitSummary.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "unitType");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._11_x.UnitType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "valueRange");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._11_x.ValueRange.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public lu.hitec.pss.soap.sensor.client._11_x.DeviceSummary[] getDeviceSummaries(java.lang.String token, java.lang.String[] deviceIds, java.lang.String missionName) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "getDeviceSummaries"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, deviceIds, missionName});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._11_x.DeviceSummary[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._11_x.DeviceSummary[]) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._11_x.DeviceSummary[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._11_x.LocationRange getLocationRange(java.lang.String token, java.lang.String deviceId, java.lang.String missionName, lu.hitec.pss.soap.sensor.client._11_x.RangeLimit rangeLimit) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "getLocationRange"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, deviceId, missionName, rangeLimit});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._11_x.LocationRange) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._11_x.LocationRange) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._11_x.LocationRange.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._11_x.ProbeRange getProbeRange(java.lang.String token, java.lang.String deviceId, java.lang.String missionName, java.lang.String probeType, lu.hitec.pss.soap.sensor.client._11_x.RangeLimit rangeLimit) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "getProbeRange"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, deviceId, missionName, probeType, rangeLimit});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._11_x.ProbeRange) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._11_x.ProbeRange) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._11_x.ProbeRange.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._11_x.DeviceStatus[] getDeviceStatusByMission(java.lang.String token, java.lang.String missionName) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "getDeviceStatusByMission"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, missionName});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._11_x.DeviceStatus[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._11_x.DeviceStatus[]) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._11_x.DeviceStatus[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._11_x.DeviceSummary getDeviceSummary(java.lang.String token, java.lang.String id, java.lang.String missionName) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "getDeviceSummary"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, id, missionName});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._11_x.DeviceSummary) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._11_x.DeviceSummary) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._11_x.DeviceSummary.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._11_x.DeviceComparisonEntry[] getDevicesMissingInLDAPDirectoryService(java.lang.String token) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "getDevicesMissingInLDAPDirectoryService"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._11_x.DeviceComparisonEntry[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._11_x.DeviceComparisonEntry[]) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._11_x.DeviceComparisonEntry[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._11_x.Project getProjectDetails() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "getProjectDetails"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._11_x.Project) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._11_x.Project) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._11_x.Project.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._11_x.ServiceLimitsImpl getServiceLimits() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "getServiceLimits"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._11_x.ServiceLimitsImpl) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._11_x.ServiceLimitsImpl) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._11_x.ServiceLimitsImpl.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._11_x.UnitsReports getAllUnitsReports(java.lang.String token, java.lang.String missionName) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._11_x.AuthenticationException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "getAllUnitsReports"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, missionName});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._11_x.UnitsReports) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._11_x.UnitsReports) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._11_x.UnitsReports.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof lu.hitec.pss.soap.sensor.client._11_x.AuthenticationException) {
              throw (lu.hitec.pss.soap.sensor.client._11_x.AuthenticationException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._11_x.UnitsReports getAllUsersReports(java.lang.String token, java.lang.String missionName) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._11_x.AuthenticationException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "getAllUsersReports"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, missionName});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._11_x.UnitsReports) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._11_x.UnitsReports) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._11_x.UnitsReports.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof lu.hitec.pss.soap.sensor.client._11_x.AuthenticationException) {
              throw (lu.hitec.pss.soap.sensor.client._11_x.AuthenticationException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._11_x.UnitsReports getAllVehiclesReports(java.lang.String token, java.lang.String missionName) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._11_x.AuthenticationException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "getAllVehiclesReports"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, missionName});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._11_x.UnitsReports) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._11_x.UnitsReports) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._11_x.UnitsReports.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof lu.hitec.pss.soap.sensor.client._11_x.AuthenticationException) {
              throw (lu.hitec.pss.soap.sensor.client._11_x.AuthenticationException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._11_x.UnitsReports getAllPlacesReports(java.lang.String token, java.lang.String missionName) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._11_x.AuthenticationException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "getAllPlacesReports"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, missionName});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._11_x.UnitsReports) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._11_x.UnitsReports) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._11_x.UnitsReports.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof lu.hitec.pss.soap.sensor.client._11_x.AuthenticationException) {
              throw (lu.hitec.pss.soap.sensor.client._11_x.AuthenticationException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._11_x.UnitSummary getUnitSummary(java.lang.String token, java.lang.String unitId, lu.hitec.pss.soap.sensor.client._11_x.UnitType unitType, java.lang.String missionName) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "getUnitSummary"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, unitId, unitType, missionName});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._11_x.UnitSummary) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._11_x.UnitSummary) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._11_x.UnitSummary.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._11_x.LocationRange getUnitLocationRange(java.lang.String token, java.lang.String unitId, lu.hitec.pss.soap.sensor.client._11_x.UnitType unitType, java.lang.String missionName, lu.hitec.pss.soap.sensor.client._11_x.RangeLimit rangeLimit) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "getUnitLocationRange"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, unitId, unitType, missionName, rangeLimit});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._11_x.LocationRange) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._11_x.LocationRange) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._11_x.LocationRange.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._11_x.ProbeRange getUnitProbeRange(java.lang.String token, java.lang.String unitId, lu.hitec.pss.soap.sensor.client._11_x.UnitType unitType, java.lang.String probeType, java.lang.String missionName, lu.hitec.pss.soap.sensor.client._11_x.RangeLimit rangeLimit) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "getUnitProbeRange"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, unitId, unitType, probeType, missionName, rangeLimit});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._11_x.ProbeRange) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._11_x.ProbeRange) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._11_x.ProbeRange.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._11_x.DeviceLocationsForMission[] getLocationRanges(java.lang.String token, lu.hitec.pss.soap.sensor.client._11_x.DeviceMission[] deviceList, lu.hitec.pss.soap.sensor.client._11_x.RangeLimit rangeLimit) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "getLocationRanges"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, deviceList, rangeLimit});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._11_x.DeviceLocationsForMission[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._11_x.DeviceLocationsForMission[]) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._11_x.DeviceLocationsForMission[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._11_x.LocationStatus evaluateLocation(java.lang.String token, lu.hitec.pss.soap.sensor.client._11_x.Point location, java.lang.String missionName) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "evaluateLocation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, location, missionName});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._11_x.LocationStatus) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._11_x.LocationStatus) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._11_x.LocationStatus.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
