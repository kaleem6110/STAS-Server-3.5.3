/**
 * EventSrvProviderPortBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package lu.hitec.pss.soap.event.provider._5_x;

public class EventSrvProviderPortBindingSkeleton implements lu.hitec.pss.soap.event.provider._5_x.EventSrvProvider_PortType, org.apache.axis.wsdl.Skeleton {
    private lu.hitec.pss.soap.event.provider._5_x.EventSrvProvider_PortType impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "newEvent"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://hitec.lu/pss/soap/event/provider/5.x", "evt"), lu.hitec.pss.soap.event.provider._5_x.Evt.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "domainName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("publishEvent", _params, new javax.xml.namespace.QName("", "eventRef"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/event/provider/5.x", "publishEvent"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("publishEvent") == null) {
            _myOperations.put("publishEvent", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("publishEvent")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "eventRef"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getEventStatusSummary", _params, new javax.xml.namespace.QName("", "eventStatusSummary"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/event/provider/5.x", "eventStatusSummary"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/event/provider/5.x", "getEventStatusSummary"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getEventStatusSummary") == null) {
            _myOperations.put("getEventStatusSummary", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getEventStatusSummary")).add(_oper);
    }

    public EventSrvProviderPortBindingSkeleton() {
        this.impl = new lu.hitec.pss.soap.event.provider._5_x.EventSrvProviderPortBindingImpl();
    }

    public EventSrvProviderPortBindingSkeleton(lu.hitec.pss.soap.event.provider._5_x.EventSrvProvider_PortType impl) {
        this.impl = impl;
    }
    public java.lang.String publishEvent(lu.hitec.pss.soap.event.provider._5_x.Evt newEvent, java.lang.String domainName) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.publishEvent(newEvent, domainName);
        return ret;
    }

    public lu.hitec.pss.soap.event.provider._5_x.EventStatusSummary getEventStatusSummary(java.lang.String eventRef) throws java.rmi.RemoteException
    {
        lu.hitec.pss.soap.event.provider._5_x.EventStatusSummary ret = impl.getEventStatusSummary(eventRef);
        return ret;
    }

}
