/**
 * Device.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package lu.hitec.pss.soap.sensor.client._5_x;

public class Device  implements java.io.Serializable {
    private lu.hitec.pss.soap.sensor.client._5_x.LocationSummary loc;

    private lu.hitec.pss.soap.sensor.client._5_x.ProbeSummary[] probe;

    private java.lang.String id;  // attribute

    private long modTime;  // attribute

    public Device() {
    }

    public Device(
           lu.hitec.pss.soap.sensor.client._5_x.LocationSummary loc,
           lu.hitec.pss.soap.sensor.client._5_x.ProbeSummary[] probe,
           java.lang.String id,
           long modTime) {
           this.loc = loc;
           this.probe = probe;
           this.id = id;
           this.modTime = modTime;
    }


    /**
     * Gets the loc value for this Device.
     * 
     * @return loc
     */
    public lu.hitec.pss.soap.sensor.client._5_x.LocationSummary getLoc() {
        return loc;
    }


    /**
     * Sets the loc value for this Device.
     * 
     * @param loc
     */
    public void setLoc(lu.hitec.pss.soap.sensor.client._5_x.LocationSummary loc) {
        this.loc = loc;
    }


    /**
     * Gets the probe value for this Device.
     * 
     * @return probe
     */
    public lu.hitec.pss.soap.sensor.client._5_x.ProbeSummary[] getProbe() {
        return probe;
    }


    /**
     * Sets the probe value for this Device.
     * 
     * @param probe
     */
    public void setProbe(lu.hitec.pss.soap.sensor.client._5_x.ProbeSummary[] probe) {
        this.probe = probe;
    }

    public lu.hitec.pss.soap.sensor.client._5_x.ProbeSummary getProbe(int i) {
        return this.probe[i];
    }

    public void setProbe(int i, lu.hitec.pss.soap.sensor.client._5_x.ProbeSummary _value) {
        this.probe[i] = _value;
    }


    /**
     * Gets the id value for this Device.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this Device.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the modTime value for this Device.
     * 
     * @return modTime
     */
    public long getModTime() {
        return modTime;
    }


    /**
     * Sets the modTime value for this Device.
     * 
     * @param modTime
     */
    public void setModTime(long modTime) {
        this.modTime = modTime;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Device)) return false;
        Device other = (Device) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.loc==null && other.getLoc()==null) || 
             (this.loc!=null &&
              this.loc.equals(other.getLoc()))) &&
            ((this.probe==null && other.getProbe()==null) || 
             (this.probe!=null &&
              java.util.Arrays.equals(this.probe, other.getProbe()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            this.modTime == other.getModTime();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getLoc() != null) {
            _hashCode += getLoc().hashCode();
        }
        if (getProbe() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getProbe());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getProbe(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        _hashCode += new Long(getModTime()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Device.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "device"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("id");
        attrField.setXmlName(new javax.xml.namespace.QName("", "id"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("modTime");
        attrField.setXmlName(new javax.xml.namespace.QName("", "modTime"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "locationSummary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("probe");
        elemField.setXmlName(new javax.xml.namespace.QName("", "probe"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/5.x", "probeSummary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
