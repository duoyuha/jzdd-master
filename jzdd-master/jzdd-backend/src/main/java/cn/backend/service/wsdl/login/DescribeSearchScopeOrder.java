/**
 * DescribeSearchScopeOrder.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.backend.service.wsdl.login;

public class DescribeSearchScopeOrder  implements java.io.Serializable {
    private java.lang.Boolean includeRealTimeEntities;

    public DescribeSearchScopeOrder() {
    }

    public DescribeSearchScopeOrder(
           java.lang.Boolean includeRealTimeEntities) {
           this.includeRealTimeEntities = includeRealTimeEntities;
    }


    /**
     * Gets the includeRealTimeEntities value for this DescribeSearchScopeOrder.
     * 
     * @return includeRealTimeEntities
     */
    public java.lang.Boolean getIncludeRealTimeEntities() {
        return includeRealTimeEntities;
    }


    /**
     * Sets the includeRealTimeEntities value for this DescribeSearchScopeOrder.
     * 
     * @param includeRealTimeEntities
     */
    public void setIncludeRealTimeEntities(java.lang.Boolean includeRealTimeEntities) {
        this.includeRealTimeEntities = includeRealTimeEntities;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DescribeSearchScopeOrder)) return false;
        DescribeSearchScopeOrder other = (DescribeSearchScopeOrder) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.includeRealTimeEntities==null && other.getIncludeRealTimeEntities()==null) || 
             (this.includeRealTimeEntities!=null &&
              this.includeRealTimeEntities.equals(other.getIncludeRealTimeEntities())));
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
        if (getIncludeRealTimeEntities() != null) {
            _hashCode += getIncludeRealTimeEntities().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DescribeSearchScopeOrder.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeSearchScopeOrder"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("includeRealTimeEntities");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "includeRealTimeEntities"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
