/**
 * UnitReport.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package lu.hitec.pss.soap.sensor.client._12_x;

public class UnitReport  implements java.io.Serializable {
    private lu.hitec.pss.soap.sensor.client._12_x.LocationValue locationValue;

    private java.lang.String unitId;

    private lu.hitec.pss.soap.sensor.client._12_x.Severity worstSeverity;

    private java.lang.String worstSeverityDevice;

    public UnitReport() {
    }

    public UnitReport(
           lu.hitec.pss.soap.sensor.client._12_x.LocationValue locationValue,
           java.lang.String unitId,
           lu.hitec.pss.soap.sensor.client._12_x.Severity worstSeverity,
           java.lang.String worstSeverityDevice) {
           this.locationValue = locationValue;
           this.unitId = unitId;
           this.worstSeverity = worstSeverity;
           this.worstSeverityDevice = worstSeverityDevice;
    }


    /**
     * Gets the locationValue value for this UnitReport.
     * 
     * @return locationValue
     */
    public lu.hitec.pss.soap.sensor.client._12_x.LocationValue getLocationValue() {
        return locationValue;
    }


    /**
     * Sets the locationValue value for this UnitReport.
     * 
     * @param locationValue
     */
    public void setLocationValue(lu.hitec.pss.soap.sensor.client._12_x.LocationValue locationValue) {
        this.locationValue = locationValue;
    }


    /**
     * Gets the unitId value for this UnitReport.
     * 
     * @return unitId
     */
    public java.lang.String getUnitId() {
        return unitId;
    }


    /**
     * Sets the unitId value for this UnitReport.
     * 
     * @param unitId
     */
    public void setUnitId(java.lang.String unitId) {
        this.unitId = unitId;
    }


    /**
     * Gets the worstSeverity value for this UnitReport.
     * 
     * @return worstSeverity
     */
    public lu.hitec.pss.soap.sensor.client._12_x.Severity getWorstSeverity() {
        return worstSeverity;
    }


    /**
     * Sets the worstSeverity value for this UnitReport.
     * 
     * @param worstSeverity
     */
    public void setWorstSeverity(lu.hitec.pss.soap.sensor.client._12_x.Severity worstSeverity) {
        this.worstSeverity = worstSeverity;
    }


    /**
     * Gets the worstSeverityDevice value for this UnitReport.
     * 
     * @return worstSeverityDevice
     */
    public java.lang.String getWorstSeverityDevice() {
        return worstSeverityDevice;
    }


    /**
     * Sets the worstSeverityDevice value for this UnitReport.
     * 
     * @param worstSeverityDevice
     */
    public void setWorstSeverityDevice(java.lang.String worstSeverityDevice) {
        this.worstSeverityDevice = worstSeverityDevice;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UnitReport)) return false;
        UnitReport other = (UnitReport) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.locationValue==null && other.getLocationValue()==null) || 
             (this.locationValue!=null &&
              this.locationValue.equals(other.getLocationValue()))) &&
            ((this.unitId==null && other.getUnitId()==null) || 
             (this.unitId!=null &&
              this.unitId.equals(other.getUnitId()))) &&
            ((this.worstSeverity==null && other.getWorstSeverity()==null) || 
             (this.worstSeverity!=null &&
              this.worstSeverity.equals(other.getWorstSeverity()))) &&
            ((this.worstSeverityDevice==null && other.getWorstSeverityDevice()==null) || 
             (this.worstSeverityDevice!=null &&
              this.worstSeverityDevice.equals(other.getWorstSeverityDevice())));
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
        if (getLocationValue() != null) {
            _hashCode += getLocationValue().hashCode();
        }
        if (getUnitId() != null) {
            _hashCode += getUnitId().hashCode();
        }
        if (getWorstSeverity() != null) {
            _hashCode += getWorstSeverity().hashCode();
        }
        if (getWorstSeverityDevice() != null) {
            _hashCode += getWorstSeverityDevice().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UnitReport.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "unitReport"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("locationValue");
        elemField.setXmlName(new javax.xml.namespace.QName("", "locationValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "locationValue"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unitId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "unitId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("worstSeverity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "worstSeverity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/12.x", "severity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("worstSeverityDevice");
        elemField.setXmlName(new javax.xml.namespace.QName("", "worstSeverityDevice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
