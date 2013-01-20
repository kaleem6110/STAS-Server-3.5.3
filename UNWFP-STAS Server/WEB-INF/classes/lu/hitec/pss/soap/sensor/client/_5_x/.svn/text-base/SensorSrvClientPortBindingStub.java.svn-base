/**
 * SensorSrvClientPortBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package lu.hitec.pss.soap.sensor.client._5_x;

public class SensorSrvClientPortBindingStub extends org.apache.axis.client.Stub implements lu.hitec.pss.soap.sensor.client._5_x.SensorSrvClient_PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[10];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDevice");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "device"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._5_x.Device.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "dev"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("waitForAnyUpdate");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "devStatus"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "deviceStatus"), lu.hitec.pss.soap.sensor.client._5_x.DeviceStatus[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "maxWaitSeconds"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "updatedId"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("waitForUpdateRestricted");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "devStatus"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "deviceStatus"), lu.hitec.pss.soap.sensor.client._5_x.DeviceStatus[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "maxWaitSeconds"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "updatedId"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDevices");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "device"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._5_x.Device[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "dev"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getLocationRange");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "deviceId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "rangeLimit"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "rangeLimit"), lu.hitec.pss.soap.sensor.client._5_x.RangeLimit.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "locationRange"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._5_x.LocationRange.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "locationRange"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getProbeRange");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "deviceId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "probeType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "rangeLimit"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "rangeLimit"), lu.hitec.pss.soap.sensor.client._5_x.RangeLimit.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "probeRange"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._5_x.ProbeRange.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "probeRange"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDeviceStatus");
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "deviceStatus"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._5_x.DeviceStatus[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "status"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAllDevicesCircle");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "center"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "point"), lu.hitec.pss.soap.sensor.client._5_x.Point.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "radiusInMeters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"), double.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "device"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._5_x.Device[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "deviceInCircle"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAllDevicesInPolygon");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "polygonVertexes"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "point"), lu.hitec.pss.soap.sensor.client._5_x.Point[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "device"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._5_x.Device[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "deviceInPolygon"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDevicesMissingInLDAPDirectoryService");
        oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "deviceComparisonEntry"));
        oper.setReturnClass(lu.hitec.pss.soap.sensor.client._5_x.DeviceComparisonEntry[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "res"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

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
            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "device");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._5_x.Device.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "deviceComparisonEntry");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._5_x.DeviceComparisonEntry.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "deviceStatus");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._5_x.DeviceStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "locationRange");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._5_x.LocationRange.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "locationSummary");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._5_x.LocationSummary.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "locationValue");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._5_x.LocationValue.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "point");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._5_x.Point.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "probeRange");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._5_x.ProbeRange.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "probeStatus");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._5_x.ProbeStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "probeSummary");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._5_x.ProbeSummary.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "probeValue");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._5_x.ProbeValue.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "rangeDetails");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._5_x.RangeDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "rangeLimit");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._5_x.RangeLimit.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "subRangeDetails");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._5_x.SubRangeDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "subRangeType");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._5_x.SubRangeType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "timeRange");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._5_x.TimeRange.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "valueRange");
            cachedSerQNames.add(qName);
            cls = lu.hitec.pss.soap.sensor.client._5_x.ValueRange.class;
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

    public lu.hitec.pss.soap.sensor.client._5_x.Device getDevice(java.lang.String id) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "getDevice"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {id});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._5_x.Device) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._5_x.Device) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._5_x.Device.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String[] waitForAnyUpdate(lu.hitec.pss.soap.sensor.client._5_x.DeviceStatus[] devStatus, int maxWaitSeconds) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "waitForAnyUpdate"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {devStatus, new java.lang.Integer(maxWaitSeconds)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String[] waitForUpdateRestricted(lu.hitec.pss.soap.sensor.client._5_x.DeviceStatus[] devStatus, int maxWaitSeconds) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "waitForUpdateRestricted"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {devStatus, new java.lang.Integer(maxWaitSeconds)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._5_x.Device[] getDevices(java.lang.String[] id) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "getDevices"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {id});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._5_x.Device[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._5_x.Device[]) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._5_x.Device[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._5_x.LocationRange getLocationRange(java.lang.String deviceId, lu.hitec.pss.soap.sensor.client._5_x.RangeLimit rangeLimit) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "getLocationRange"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {deviceId, rangeLimit});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._5_x.LocationRange) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._5_x.LocationRange) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._5_x.LocationRange.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._5_x.ProbeRange getProbeRange(java.lang.String deviceId, java.lang.String probeType, lu.hitec.pss.soap.sensor.client._5_x.RangeLimit rangeLimit) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "getProbeRange"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {deviceId, probeType, rangeLimit});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._5_x.ProbeRange) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._5_x.ProbeRange) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._5_x.ProbeRange.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._5_x.DeviceStatus[] getDeviceStatus() throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "getDeviceStatus"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._5_x.DeviceStatus[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._5_x.DeviceStatus[]) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._5_x.DeviceStatus[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._5_x.Device[] getAllDevicesCircle(lu.hitec.pss.soap.sensor.client._5_x.Point center, double radiusInMeters) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "getAllDevicesCircle"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {center, new java.lang.Double(radiusInMeters)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._5_x.Device[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._5_x.Device[]) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._5_x.Device[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._5_x.Device[] getAllDevicesInPolygon(lu.hitec.pss.soap.sensor.client._5_x.Point[] polygonVertexes) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "getAllDevicesInPolygon"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {polygonVertexes});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._5_x.Device[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._5_x.Device[]) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._5_x.Device[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public lu.hitec.pss.soap.sensor.client._5_x.DeviceComparisonEntry[] getDevicesMissingInLDAPDirectoryService() throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "getDevicesMissingInLDAPDirectoryService"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (lu.hitec.pss.soap.sensor.client._5_x.DeviceComparisonEntry[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (lu.hitec.pss.soap.sensor.client._5_x.DeviceComparisonEntry[]) org.apache.axis.utils.JavaUtils.convert(_resp, lu.hitec.pss.soap.sensor.client._5_x.DeviceComparisonEntry[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
