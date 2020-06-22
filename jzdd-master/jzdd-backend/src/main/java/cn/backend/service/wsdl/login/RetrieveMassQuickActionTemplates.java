/**
 * RetrieveMassQuickActionTemplates.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.backend.service.wsdl.login;

public class RetrieveMassQuickActionTemplates  implements java.io.Serializable {
    private java.lang.String quickActionName;

    private java.lang.String[] contextIds;

    public RetrieveMassQuickActionTemplates() {
    }

    public RetrieveMassQuickActionTemplates(
           java.lang.String quickActionName,
           java.lang.String[] contextIds) {
           this.quickActionName = quickActionName;
           this.contextIds = contextIds;
    }


    /**
     * Gets the quickActionName value for this RetrieveMassQuickActionTemplates.
     * 
     * @return quickActionName
     */
    public java.lang.String getQuickActionName() {
        return quickActionName;
    }


    /**
     * Sets the quickActionName value for this RetrieveMassQuickActionTemplates.
     * 
     * @param quickActionName
     */
    public void setQuickActionName(java.lang.String quickActionName) {
        this.quickActionName = quickActionName;
    }


    /**
     * Gets the contextIds value for this RetrieveMassQuickActionTemplates.
     * 
     * @return contextIds
     */
    public java.lang.String[] getContextIds() {
        return contextIds;
    }


    /**
     * Sets the contextIds value for this RetrieveMassQuickActionTemplates.
     * 
     * @param contextIds
     */
    public void setContextIds(java.lang.String[] contextIds) {
        this.contextIds = contextIds;
    }

    public java.lang.String getContextIds(int i) {
        return this.contextIds[i];
    }

    public void setContextIds(int i, java.lang.String _value) {
        this.contextIds[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RetrieveMassQuickActionTemplates)) return false;
        RetrieveMassQuickActionTemplates other = (RetrieveMassQuickActionTemplates) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.quickActionName==null && other.getQuickActionName()==null) || 
             (this.quickActionName!=null &&
              this.quickActionName.equals(other.getQuickActionName()))) &&
            ((this.contextIds==null && other.getContextIds()==null) || 
             (this.contextIds!=null &&
              java.util.Arrays.equals(this.contextIds, other.getContextIds())));
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
        if (getQuickActionName() != null) {
            _hashCode += getQuickActionName().hashCode();
        }
        if (getContextIds() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getContextIds());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getContextIds(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RetrieveMassQuickActionTemplates.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">retrieveMassQuickActionTemplates"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quickActionName");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "quickActionName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contextIds");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "contextIds"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ID"));
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
