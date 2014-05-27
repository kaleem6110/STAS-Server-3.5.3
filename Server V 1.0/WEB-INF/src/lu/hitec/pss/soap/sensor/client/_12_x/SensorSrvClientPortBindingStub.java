/**
 * SensorSrvClientPortBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package lu.hitec.pss.soap.sensor.client._12_x;

public class SensorSrvClientPortBindingStub extends org.apache.axis.client.Stub implements lu.hitec.pss.soap.sensor.client._12_x.SensorSrvClient_PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[21];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAllUnitsReports");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "missionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "unitsReports"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._12_x.UnitsReports.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "res"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthorizationException"),
                      "lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException",
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthenticationException"),
                      "lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException",
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthenticationException"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAllUsersReports");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "missionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "unitReport"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._12_x.UnitReport[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "res"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthorizationException"),
                      "lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException",
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthenticationException"),
                      "lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException",
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthenticationException"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAllVehiclesReports");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "missionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "unitReport"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._12_x.UnitReport[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "res"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthorizationException"),
                      "lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException",
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthenticationException"),
                      "lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException",
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthenticationException"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("evaluateLocation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "location"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "point"), lu.hitec.pss.soap.sensor.client._12_x.Point.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "missionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "locationStatus"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._12_x.LocationStatus.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "res"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getLocationRanges");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "deviceList"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "deviceMission"), lu.hitec.pss.soap.sensor.client._12_x.DeviceMission[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "rangeLimit"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "rangeLimit"), lu.hitec.pss.soap.sensor.client._12_x.RangeLimit.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "deviceLocationsForMission"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._12_x.DeviceLocationsForMission[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "res"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getProjectDetails");
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "project"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._12_x.Project.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "res"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAllPlacesReports");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "missionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "unitReport"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._12_x.UnitReport[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "res"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthorizationException"),
                      "lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException",
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthenticationException"),
                      "lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException",
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthenticationException"), 
                      true
                     ));
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getUnitLocationRange");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "unitId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "unitId"), lu.hitec.pss.soap.sensor.client._12_x.UnitId.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "missionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "rangeLimit"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "rangeLimit"), lu.hitec.pss.soap.sensor.client._12_x.RangeLimit.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "locationRange"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._12_x.LocationRange.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "unitSummary"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getUnitSummary");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "unitId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "unitId"), lu.hitec.pss.soap.sensor.client._12_x.UnitId.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "missionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "unitSummary"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._12_x.UnitSummary.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "unitSummary"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getUnitLastLocation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "unitId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "unitId"), lu.hitec.pss.soap.sensor.client._12_x.UnitId.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "missionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "locationValue"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._12_x.LocationValue.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "location"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getServiceLimits");
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "serviceLimitsImpl"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._12_x.ServiceLimitsImpl.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "res"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getUnitProbeRange");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "unitId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "unitId"), lu.hitec.pss.soap.sensor.client._12_x.UnitId.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "probeType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "missionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "rangeLimit"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "rangeLimit"), lu.hitec.pss.soap.sensor.client._12_x.RangeLimit.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "probeRange"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._12_x.ProbeRange.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "unitSummary"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDevicesMissingInLdapDirectoryService");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "deviceComparisonEntry"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._12_x.DeviceComparisonEntry[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "res"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("searchUnitsByCircleZone");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "missionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "circle"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "simpleCircularZone"), lu.hitec.pss.soap.sensor.client._12_x.SimpleCircularZone.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "unitsReports"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._12_x.UnitsReports.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "res"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthorizationException"),
                      "lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException",
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthenticationException"),
                      "lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException",
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthenticationException"), 
                      true
                     ));
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("searchUnitsByPolygonZone");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "missionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "polygon"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "simplePolygonZone"), lu.hitec.pss.soap.sensor.client._12_x.SimplePolygonZone.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "unitsReports"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._12_x.UnitsReports.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "res"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthorizationException"),
                      "lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException",
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthenticationException"),
                      "lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException",
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthenticationException"), 
                      true
                     ));
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("searchPlacesByCircleZone");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "missionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "circle"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "simpleCircularZone"), lu.hitec.pss.soap.sensor.client._12_x.SimpleCircularZone.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "unitReport"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._12_x.UnitReport[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "res"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthorizationException"),
                      "lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException",
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthenticationException"),
                      "lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException",
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthenticationException"), 
                      true
                     ));
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("searchVehiclesByPolygonZone");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "missionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "polygon"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "simplePolygonZone"), lu.hitec.pss.soap.sensor.client._12_x.SimplePolygonZone.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "unitReport"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._12_x.UnitReport[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "res"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthorizationException"),
                      "lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException",
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthenticationException"),
                      "lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException",
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthenticationException"), 
                      true
                     ));
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("searchUsersByCircleZone");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "missionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "circle"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "simpleCircularZone"), lu.hitec.pss.soap.sensor.client._12_x.SimpleCircularZone.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "unitReport"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._12_x.UnitReport[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "res"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthorizationException"),
                      "lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException",
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthenticationException"),
                      "lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException",
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthenticationException"), 
                      true
                     ));
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("searchUsersByPolygonZone");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "missionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "polygon"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "simplePolygonZone"), lu.hitec.pss.soap.sensor.client._12_x.SimplePolygonZone.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "unitReport"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._12_x.UnitReport[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "res"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthorizationException"),
                      "lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException",
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthenticationException"),
                      "lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException",
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthenticationException"), 
                      true
                     ));
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("searchVehiclesByCircleZone");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "missionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "circle"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "simpleCircularZone"), lu.hitec.pss.soap.sensor.client._12_x.SimpleCircularZone.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "unitReport"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._12_x.UnitReport[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "res"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthorizationException"),
                      "lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException",
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthenticationException"),
                      "lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException",
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthenticationException"), 
                      true
                     ));
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("searchPlacesByPolygonZone");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "missionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "polygon"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "simplePolygonZone"), lu.hitec.pss.soap.sensor.client._12_x.SimplePolygonZone.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "unitReport"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._12_x.UnitReport[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "res"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthorizationException"),
                      "lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException",
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthorizationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthenticationException"),
                      "lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException",
                      new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthenticationException"), 
                      true
                     ));
        _operations[20] = oper;

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
            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "abstractCircularZone");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.AbstractCircularZone.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "abstractGeoFence");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.AbstractGeoFence.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "abstractPolygonalZone");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.AbstractPolygonalZone.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthenticationException");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "AuthorizationException");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "deviceComparisonEntry");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.DeviceComparisonEntry.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "deviceLocationsForMission");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.DeviceLocationsForMission.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "deviceMission");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.DeviceMission.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "locationRange");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.LocationRange.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "locationStatus");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.LocationStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "locationSummary");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.LocationSummary.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "locationValue");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.LocationValue.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "point");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.Point.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "probeRange");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.ProbeRange.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "probeStatus");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.ProbeStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "probeSummary");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.ProbeSummary.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "probeValue");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.ProbeValue.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "project");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.Project.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "rangeDetails");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.RangeDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "rangeLimit");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.RangeLimit.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "serviceLimitsImpl");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.ServiceLimitsImpl.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "severity");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.Severity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "simpleCircularZone");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.SimpleCircularZone.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "simplePolygonZone");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.SimplePolygonZone.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "subRangeDetails");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.SubRangeDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "subRangeType");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.SubRangeType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "timeRange");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.TimeRange.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "unitId");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.UnitId.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "unitReport");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.UnitReport.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "unitsReports");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.UnitsReports.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "unitSummary");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.UnitSummary.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "unitType");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.UnitType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "valueRange");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._12_x.ValueRange.class;
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

    public lu.hitec.pss.soap.sensor.client._12_x.UnitsReports getAllUnitsReports(java.lang.String token, java.lang.String missionId) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException, lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "getAllUnitsReports"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, missionId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._12_x.UnitsReports) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._12_x.UnitsReports) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._12_x.UnitsReports.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException) {
              throw (lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException) {
              throw (lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._12_x.UnitReport[] getAllUsersReports(java.lang.String token, java.lang.String missionId) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException, lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "getAllUsersReports"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, missionId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._12_x.UnitReport[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._12_x.UnitReport[]) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._12_x.UnitReport[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException) {
              throw (lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException) {
              throw (lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._12_x.UnitReport[] getAllVehiclesReports(java.lang.String token, java.lang.String missionId) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException, lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "getAllVehiclesReports"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, missionId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._12_x.UnitReport[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._12_x.UnitReport[]) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._12_x.UnitReport[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException) {
              throw (lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException) {
              throw (lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._12_x.LocationStatus evaluateLocation(java.lang.String token, lu.hitec.pss.soap.sensor.client._12_x.Point location, java.lang.String missionId) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "evaluateLocation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, location, missionId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._12_x.LocationStatus) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._12_x.LocationStatus) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._12_x.LocationStatus.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._12_x.DeviceLocationsForMission[] getLocationRanges(java.lang.String token, lu.hitec.pss.soap.sensor.client._12_x.DeviceMission[] deviceList, lu.hitec.pss.soap.sensor.client._12_x.RangeLimit rangeLimit) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "getLocationRanges"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, deviceList, rangeLimit});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._12_x.DeviceLocationsForMission[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._12_x.DeviceLocationsForMission[]) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._12_x.DeviceLocationsForMission[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._12_x.Project getProjectDetails() throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "getProjectDetails"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._12_x.Project) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._12_x.Project) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._12_x.Project.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._12_x.UnitReport[] getAllPlacesReports(java.lang.String token, java.lang.String missionId) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException, lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "getAllPlacesReports"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, missionId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._12_x.UnitReport[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._12_x.UnitReport[]) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._12_x.UnitReport[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException) {
              throw (lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException) {
              throw (lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._12_x.LocationRange getUnitLocationRange(java.lang.String token, lu.hitec.pss.soap.sensor.client._12_x.UnitId unitId, java.lang.String missionId, lu.hitec.pss.soap.sensor.client._12_x.RangeLimit rangeLimit) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "getUnitLocationRange"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, unitId, missionId, rangeLimit});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._12_x.LocationRange) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._12_x.LocationRange) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._12_x.LocationRange.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._12_x.UnitSummary getUnitSummary(java.lang.String token, lu.hitec.pss.soap.sensor.client._12_x.UnitId unitId, java.lang.String missionId) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "getUnitSummary"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, unitId, missionId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._12_x.UnitSummary) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._12_x.UnitSummary) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._12_x.UnitSummary.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._12_x.LocationValue getUnitLastLocation(java.lang.String token, lu.hitec.pss.soap.sensor.client._12_x.UnitId unitId, java.lang.String missionId) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "getUnitLastLocation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, unitId, missionId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._12_x.LocationValue) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._12_x.LocationValue) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._12_x.LocationValue.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._12_x.ServiceLimitsImpl getServiceLimits() throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "getServiceLimits"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._12_x.ServiceLimitsImpl) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._12_x.ServiceLimitsImpl) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._12_x.ServiceLimitsImpl.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._12_x.ProbeRange getUnitProbeRange(java.lang.String token, lu.hitec.pss.soap.sensor.client._12_x.UnitId unitId, java.lang.String probeType, java.lang.String missionId, lu.hitec.pss.soap.sensor.client._12_x.RangeLimit rangeLimit) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "getUnitProbeRange"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, unitId, probeType, missionId, rangeLimit});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._12_x.ProbeRange) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._12_x.ProbeRange) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._12_x.ProbeRange.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._12_x.DeviceComparisonEntry[] getDevicesMissingInLdapDirectoryService(java.lang.String token) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "getDevicesMissingInLdapDirectoryService"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._12_x.DeviceComparisonEntry[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._12_x.DeviceComparisonEntry[]) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._12_x.DeviceComparisonEntry[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._12_x.UnitsReports searchUnitsByCircleZone(java.lang.String token, java.lang.String missionId, lu.hitec.pss.soap.sensor.client._12_x.SimpleCircularZone circle) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException, lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "searchUnitsByCircleZone"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, missionId, circle});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._12_x.UnitsReports) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._12_x.UnitsReports) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._12_x.UnitsReports.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException) {
              throw (lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException) {
              throw (lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._12_x.UnitsReports searchUnitsByPolygonZone(java.lang.String token, java.lang.String missionId, lu.hitec.pss.soap.sensor.client._12_x.SimplePolygonZone polygon) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException, lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "searchUnitsByPolygonZone"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, missionId, polygon});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._12_x.UnitsReports) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._12_x.UnitsReports) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._12_x.UnitsReports.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException) {
              throw (lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException) {
              throw (lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._12_x.UnitReport[] searchPlacesByCircleZone(java.lang.String token, java.lang.String missionId, lu.hitec.pss.soap.sensor.client._12_x.SimpleCircularZone circle) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException, lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "searchPlacesByCircleZone"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, missionId, circle});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._12_x.UnitReport[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._12_x.UnitReport[]) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._12_x.UnitReport[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException) {
              throw (lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException) {
              throw (lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._12_x.UnitReport[] searchVehiclesByPolygonZone(java.lang.String token, java.lang.String missionId, lu.hitec.pss.soap.sensor.client._12_x.SimplePolygonZone polygon) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException, lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "searchVehiclesByPolygonZone"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, missionId, polygon});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._12_x.UnitReport[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._12_x.UnitReport[]) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._12_x.UnitReport[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException) {
              throw (lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException) {
              throw (lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._12_x.UnitReport[] searchUsersByCircleZone(java.lang.String token, java.lang.String missionId, lu.hitec.pss.soap.sensor.client._12_x.SimpleCircularZone circle) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException, lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "searchUsersByCircleZone"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, missionId, circle});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._12_x.UnitReport[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._12_x.UnitReport[]) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._12_x.UnitReport[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException) {
              throw (lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException) {
              throw (lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._12_x.UnitReport[] searchUsersByPolygonZone(java.lang.String token, java.lang.String missionId, lu.hitec.pss.soap.sensor.client._12_x.SimplePolygonZone polygon) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException, lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "searchUsersByPolygonZone"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, missionId, polygon});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._12_x.UnitReport[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._12_x.UnitReport[]) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._12_x.UnitReport[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException) {
              throw (lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException) {
              throw (lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._12_x.UnitReport[] searchVehiclesByCircleZone(java.lang.String token, java.lang.String missionId, lu.hitec.pss.soap.sensor.client._12_x.SimpleCircularZone circle) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException, lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "searchVehiclesByCircleZone"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, missionId, circle});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._12_x.UnitReport[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._12_x.UnitReport[]) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._12_x.UnitReport[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException) {
              throw (lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException) {
              throw (lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._12_x.UnitReport[] searchPlacesByPolygonZone(java.lang.String token, java.lang.String missionId, lu.hitec.pss.soap.sensor.client._12_x.SimplePolygonZone polygon) throws java.rmi.RemoteException, lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException, lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[20]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "searchPlacesByPolygonZone"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, missionId, polygon});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._12_x.UnitReport[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._12_x.UnitReport[]) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._12_x.UnitReport[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException) {
              throw (lu.hitec.pss.soap.sensor.client._12_x.AuthorizationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException) {
              throw (lu.hitec.pss.soap.sensor.client._12_x.AuthenticationException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
