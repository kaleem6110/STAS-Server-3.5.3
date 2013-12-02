/**
 * SubRangeDetails.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package lu.hitec.pss.soap.sensor.client._11_x;

public class SubRangeDetails  implements java.io.Serializable {
    private int missingValues;  // attribute

    private lu.hitec.pss.soap.sensor.client._11_x.SubRangeType subRangeType;  // attribute

    public SubRangeDetails() {
    }

    public SubRangeDetails(
           int missingValues,
           lu.hitec.pss.soap.sensor.client._11_x.SubRangeType subRangeType) {
           this.missingValues = missingValues;
           this.subRangeType = subRangeType;
    }


    /**
     * Gets the missingValues value for this SubRangeDetails.
     * 
     * @return missingValues
     */
    public int getMissingValues() {
        return missingValues;
    }


    /**
     * Sets the missingValues value for this SubRangeDetails.
     * 
     * @param missingValues
     */
    public void setMissingValues(int missingValues) {
        this.missingValues = missingValues;
    }


    /**
     * Gets the subRangeType value for this SubRangeDetails.
     * 
     * @return subRangeType
     */
    public lu.hitec.pss.soap.sensor.client._11_x.SubRangeType getSubRangeType() {
        return subRangeType;
    }


    /**
     * Sets the subRangeType value for this SubRangeDetails.
     * 
     * @param subRangeType
     */
    public void setSubRangeType(lu.hitec.pss.soap.sensor.client._11_x.SubRangeType subRangeType) {
        this.subRangeType = subRangeType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SubRangeDetails)) return false;
        SubRangeDetails other = (SubRangeDetails) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.missingValues == other.getMissingValues() &&
            ((this.subRangeType==null && other.getSubRangeType()==null) || 
             (this.subRangeType!=null &&
              this.subRangeType.equals(other.getSubRangeType())));
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
        _hashCode += getMissingValues();
        if (getSubRangeType() != null) {
            _hashCode += getSubRangeType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SubRangeDetails.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "subRangeDetails"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("missingValues");
        attrField.setXmlName(new javax.xml.namespace.QName("", "missingValues"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("subRangeType");
        attrField.setXmlName(new javax.xml.namespace.QName("", "subRangeType"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/sensor/client/11.x", "subRangeType"));
        typeDesc.addFieldDesc(attrField);
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
