/**
 * EventDescription.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package lu.hitec.pss.soap.event.provider._5_x;

public class EventDescription  implements java.io.Serializable {
    private java.lang.String longDesc;

    private java.lang.String shortDesc;

    public EventDescription() {
    }

    public EventDescription(
           java.lang.String longDesc,
           java.lang.String shortDesc) {
           this.longDesc = longDesc;
           this.shortDesc = shortDesc;
    }


    /**
     * Gets the longDesc value for this EventDescription.
     * 
     * @return longDesc
     */
    public java.lang.String getLongDesc() {
        return longDesc;
    }


    /**
     * Sets the longDesc value for this EventDescription.
     * 
     * @param longDesc
     */
    public void setLongDesc(java.lang.String longDesc) {
        this.longDesc = longDesc;
    }


    /**
     * Gets the shortDesc value for this EventDescription.
     * 
     * @return shortDesc
     */
    public java.lang.String getShortDesc() {
        return shortDesc;
    }


    /**
     * Sets the shortDesc value for this EventDescription.
     * 
     * @param shortDesc
     */
    public void setShortDesc(java.lang.String shortDesc) {
        this.shortDesc = shortDesc;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EventDescription)) return false;
        EventDescription other = (EventDescription) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.longDesc==null && other.getLongDesc()==null) || 
             (this.longDesc!=null &&
              this.longDesc.equals(other.getLongDesc()))) &&
            ((this.shortDesc==null && other.getShortDesc()==null) || 
             (this.shortDesc!=null &&
              this.shortDesc.equals(other.getShortDesc())));
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
        if (getLongDesc() != null) {
            _hashCode += getLongDesc().hashCode();
        }
        if (getShortDesc() != null) {
            _hashCode += getShortDesc().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EventDescription.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://hitec.lu/pss/soap/event/provider/5.x", "eventDescription"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("longDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "longDesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shortDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shortDesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
