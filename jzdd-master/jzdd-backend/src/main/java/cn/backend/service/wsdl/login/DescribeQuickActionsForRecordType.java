/**
 * DescribeQuickActionsForRecordType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.backend.service.wsdl.login;

public class DescribeQuickActionsForRecordType  implements java.io.Serializable {
    private java.lang.String[] quickActions;

    private java.lang.String recordTypeId;

    public DescribeQuickActionsForRecordType() {
    }

    public DescribeQuickActionsForRecordType(
           java.lang.String[] quickActions,
           java.lang.String recordTypeId) {
           this.quickActions = quickActions;
           this.recordTypeId = recordTypeId;
    }


    /**
     * Gets the quickActions value for this DescribeQuickActionsForRecordType.
     * 
     * @return quickActions
     */
    public java.lang.String[] getQuickActions() {
        return quickActions;
    }


    /**
     * Sets the quickActions value for this DescribeQuickActionsForRecordType.
     * 
     * @param quickActions
     */
    public void setQuickActions(java.lang.String[] quickActions) {
        this.quickActions = quickActions;
    }

    public java.lang.String getQuickActions(int i) {
        return this.quickActions[i];
    }

    public void setQuickActions(int i, java.lang.String _value) {
        this.quickActions[i] = _value;
    }


    /**
     * Gets the recordTypeId value for this DescribeQuickActionsForRecordType.
     * 
     * @return recordTypeId
     */
    public java.lang.String getRecordTypeId() {
        return recordTypeId;
    }


    /**
     * Sets the recordTypeId value for this DescribeQuickActionsForRecordType.
     * 
     * @param recordTypeId
     */
    public void setRecordTypeId(java.lang.String recordTypeId) {
        this.recordTypeId = recordTypeId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DescribeQuickActionsForRecordType)) return false;
        DescribeQuickActionsForRecordType other = (DescribeQuickActionsForRecordType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.quickActions==null && other.getQuickActions()==null) || 
             (this.quickActions!=null &&
              java.util.Arrays.equals(this.quickActions, other.getQuickActions()))) &&
            ((this.recordTypeId==null && other.getRecordTypeId()==null) || 
             (this.recordTypeId!=null &&
              this.recordTypeId.equals(other.getRecordTypeId())));
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
        if (getQuickActions() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getQuickActions());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getQuickActions(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRecordTypeId() != null) {
            _hashCode += getRecordTypeId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DescribeQuickActionsForRecordType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeQuickActionsForRecordType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quickActions");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "quickActions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recordTypeId");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "recordTypeId"));
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
