/**
 * LogInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.backend.service.wsdl.login;

public class LogInfo  implements java.io.Serializable {
    private cn.backend.service.wsdl.login.LogCategory category;

    private cn.backend.service.wsdl.login.LogCategoryLevel level;

    public LogInfo() {
    }

    public LogInfo(
           cn.backend.service.wsdl.login.LogCategory category,
           cn.backend.service.wsdl.login.LogCategoryLevel level) {
           this.category = category;
           this.level = level;
    }


    /**
     * Gets the category value for this LogInfo.
     * 
     * @return category
     */
    public cn.backend.service.wsdl.login.LogCategory getCategory() {
        return category;
    }


    /**
     * Sets the category value for this LogInfo.
     * 
     * @param category
     */
    public void setCategory(cn.backend.service.wsdl.login.LogCategory category) {
        this.category = category;
    }


    /**
     * Gets the level value for this LogInfo.
     * 
     * @return level
     */
    public cn.backend.service.wsdl.login.LogCategoryLevel getLevel() {
        return level;
    }


    /**
     * Sets the level value for this LogInfo.
     * 
     * @param level
     */
    public void setLevel(cn.backend.service.wsdl.login.LogCategoryLevel level) {
        this.level = level;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LogInfo)) return false;
        LogInfo other = (LogInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.category==null && other.getCategory()==null) || 
             (this.category!=null &&
              this.category.equals(other.getCategory()))) &&
            ((this.level==null && other.getLevel()==null) || 
             (this.level!=null &&
              this.level.equals(other.getLevel())));
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
        if (getCategory() != null) {
            _hashCode += getCategory().hashCode();
        }
        if (getLevel() != null) {
            _hashCode += getLevel().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LogInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "LogInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("category");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "category"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "LogCategory"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("level");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "level"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "LogCategoryLevel"));
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
