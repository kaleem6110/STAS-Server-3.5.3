/**
 * EventRelatedInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package lu.hitec.pss.soap.event.provider._5_x;

public class EventRelatedInfo  implements java.io.Serializable {
    private java.lang.String relDeviceId;  // attribute

    private java.lang.String relEventRef;  // attribute

    private java.util.Calendar relPayloadTime;  // attribute

    private java.lang.String relPayloadType;  // attribute

    public EventRelatedInfo() {
    }

    public EventRelatedInfo(
           java.lang.String relDeviceId,
           java.lang.String relEventRef,
           java.util.Calendar relPayloadTime,
           java.lang.String relPayloadType) {
           this.relDeviceId = relDeviceId;
           this.relEventRef = relEventRef;
           this.relPayloadTime = relPayloadTime;
           this.relPayloadType = relPayloadType;
    }


    /**
     * Gets the relDeviceId value for this EventRelatedInfo.
     * 
     * @return relDeviceId
     */
    public java.lang.String getRelDeviceId() {
        return relDeviceId;
    }


    /**
     * Sets the relDeviceId value for this EventRelatedInfo.
     * 
     * @param relDeviceId
     */
    public void setRelDeviceId(java.lang.String relDeviceId) {
        this.relDeviceId = relDeviceId;
    }


    /**
     * Gets the relEventRef value for this EventRelatedInfo.
     * 
     * @return relEventRef
     */
    public java.lang.String getRelEventRef() {
        return relEventRef;
    }


    /**
     * Sets the relEventRef value for this EventRelatedInfo.
     * 
     * @param relEventRef
     */
    public void setRelEventRef(java.lang.String relEventRef) {
        this.relEventRef = relEventRef;
    }


    /**
     * Gets the relPayloadTime value for this EventRelatedInfo.
     * 
     * @return relPayloadTime
     */
    public java.util.Calendar getRelPayloadTime() {
        return relPayloadTime;
    }


    /**
     * Sets the relPayloadTime value for this EventRelatedInfo.
     * 
     * @param relPayloadTime
     */
    public void setRelPayloadTime(java.util.Calendar relPayloadTime) {
        this.relPayloadTime = relPayloadTime;
    }


    /**
     * Gets the relPayloadType value for this EventRelatedInfo.
     * 
     * @return relPayloadType
     */
    public java.lang.String getRelPayloadType() {
        return relPayloadType;
    }


    /**
     * Sets the relPayloadType value for this EventRelatedInfo.
     * 
     * @param relPayloadType
     */
    public void setRelPayloadType(java.lang.String relPayloadType) {
        this.relPayloadType = relPayloadType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EventRelatedInfo)) return false;
        EventRelatedInfo other = (EventRelatedInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.relDeviceId==null && other.getRelDeviceId()==null) || 
             (this.relDeviceId!=null &&
              this.relDeviceId.equals(other.getRelDeviceId()))) &&
            ((this.relEventRef==null && other.getRelEventRef()==null) || 
             (this.relEventRef!=null &&
              this.relEventRef.equals(other.getRelEventRef()))) &&
            ((this.relPayloadTime==null && other.getRelPayloadTime()==null) || 
             (this.relPayloadTime!=null &&
              this.relPayloadTime.equals(other.getRelPayloadTime()))) &&
            ((this.relPayloadType==null && other.getRelPayloadType()==null) || 
             (this.relPayloadType!=null &&
              this.relPayloadType.equals(other.getRelPayloadType())));
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
        if (getRelDeviceId() != null) {
            _hashCode += getRelDeviceId().hashCode();
        }
        if (getRelEventRef() != null) {
            _hashCode += getRelEventRef().hashCode();
        }
        if (getRelPayloadTime() != null) {
            _hashCode += getRelPayloadTime().hashCode();
        }
        if (getRelPayloadType() != null) {
            _hashCode += getRelPayloadType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EventRelatedInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/event/provider/5.x", "eventRelatedInfo"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("relDeviceId");
        attrField.setXmlName(new javax.xml.namespace.QName("", "relDeviceId"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("relEventRef");
        attrField.setXmlName(new javax.xml.namespace.QName("", "relEventRef"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("relPayloadTime");
        attrField.setXmlName(new javax.xml.namespace.QName("", "relPayloadTime"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("relPayloadType");
        attrField.setXmlName(new javax.xml.namespace.QName("", "relPayloadType"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
