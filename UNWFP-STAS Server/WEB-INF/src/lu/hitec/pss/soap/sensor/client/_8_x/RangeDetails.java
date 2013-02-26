/**
 * RangeDetails.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package lu.hitec.pss.soap.sensor.client._8_x;

public class RangeDetails  implements java.io.Serializable {
    private lu.hitec.pss.soap.sensor.client._8_x.TimeRange availableTimeRange;

    private lu.hitec.pss.soap.sensor.client._8_x.TimeRange requestedTimeRange;

    private lu.hitec.pss.soap.sensor.client._8_x.SubRangeDetails subRangeDetails;

    public RangeDetails() {
    }

    public RangeDetails(
           lu.hitec.pss.soap.sensor.client._8_x.TimeRange availableTimeRange,
           lu.hitec.pss.soap.sensor.client._8_x.TimeRange requestedTimeRange,
           lu.hitec.pss.soap.sensor.client._8_x.SubRangeDetails subRangeDetails) {
           this.availableTimeRange = availableTimeRange;
           this.requestedTimeRange = requestedTimeRange;
           this.subRangeDetails = subRangeDetails;
    }


    /**
     * Gets the availableTimeRange value for this RangeDetails.
     * 
     * @return availableTimeRange
     */
    public lu.hitec.pss.soap.sensor.client._8_x.TimeRange getAvailableTimeRange() {
        return availableTimeRange;
    }


    /**
     * Sets the availableTimeRange value for this RangeDetails.
     * 
     * @param availableTimeRange
     */
    public void setAvailableTimeRange(lu.hitec.pss.soap.sensor.client._8_x.TimeRange availableTimeRange) {
        this.availableTimeRange = availableTimeRange;
    }


    /**
     * Gets the requestedTimeRange value for this RangeDetails.
     * 
     * @return requestedTimeRange
     */
    public lu.hitec.pss.soap.sensor.client._8_x.TimeRange getRequestedTimeRange() {
        return requestedTimeRange;
    }


    /**
     * Sets the requestedTimeRange value for this RangeDetails.
     * 
     * @param requestedTimeRange
     */
    public void setRequestedTimeRange(lu.hitec.pss.soap.sensor.client._8_x.TimeRange requestedTimeRange) {
        this.requestedTimeRange = requestedTimeRange;
    }


    /**
     * Gets the subRangeDetails value for this RangeDetails.
     * 
     * @return subRangeDetails
     */
    public lu.hitec.pss.soap.sensor.client._8_x.SubRangeDetails getSubRangeDetails() {
        return subRangeDetails;
    }


    /**
     * Sets the subRangeDetails value for this RangeDetails.
     * 
     * @param subRangeDetails
     */
    public void setSubRangeDetails(lu.hitec.pss.soap.sensor.client._8_x.SubRangeDetails subRangeDetails) {
        this.subRangeDetails = subRangeDetails;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RangeDetails)) return false;
        RangeDetails other = (RangeDetails) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.availableTimeRange==null && other.getAvailableTimeRange()==null) || 
             (this.availableTimeRange!=null &&
              this.availableTimeRange.equals(other.getAvailableTimeRange()))) &&
            ((this.requestedTimeRange==null && other.getRequestedTimeRange()==null) || 
             (this.requestedTimeRange!=null &&
              this.requestedTimeRange.equals(other.getRequestedTimeRange()))) &&
            ((this.subRangeDetails==null && other.getSubRangeDetails()==null) || 
             (this.subRangeDetails!=null &&
              this.subRangeDetails.equals(other.getSubRangeDetails())));
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
        if (getAvailableTimeRange() != null) {
            _hashCode += getAvailableTimeRange().hashCode();
        }
        if (getRequestedTimeRange() != null) {
            _hashCode += getRequestedTimeRange().hashCode();
        }
        if (getSubRangeDetails() != null) {
            _hashCode += getSubRangeDetails().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RangeDetails.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/8.x", "rangeDetails"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("availableTimeRange");
        elemField.setXmlName(new javax.xml.namespace.QName("", "availableTimeRange"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/8.x", "timeRange"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestedTimeRange");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requestedTimeRange"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/8.x", "timeRange"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subRangeDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subRangeDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/8.x", "subRangeDetails"));
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
