/**
 * DeleteByExampleResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.backend.service.wsdl.login;

public class DeleteByExampleResult  implements java.io.Serializable {
    private cn.backend.service.wsdl.login.SObject entity;

    private cn.backend.service.wsdl.login.Error[] errors;

    private long rowCount;

    private boolean success;

    public DeleteByExampleResult() {
    }

    public DeleteByExampleResult(
           cn.backend.service.wsdl.login.SObject entity,
           cn.backend.service.wsdl.login.Error[] errors,
           long rowCount,
           boolean success) {
           this.entity = entity;
           this.errors = errors;
           this.rowCount = rowCount;
           this.success = success;
    }


    /**
     * Gets the entity value for this DeleteByExampleResult.
     * 
     * @return entity
     */
    public cn.backend.service.wsdl.login.SObject getEntity() {
        return entity;
    }


    /**
     * Sets the entity value for this DeleteByExampleResult.
     * 
     * @param entity
     */
    public void setEntity(cn.backend.service.wsdl.login.SObject entity) {
        this.entity = entity;
    }


    /**
     * Gets the errors value for this DeleteByExampleResult.
     * 
     * @return errors
     */
    public cn.backend.service.wsdl.login.Error[] getErrors() {
        return errors;
    }


    /**
     * Sets the errors value for this DeleteByExampleResult.
     * 
     * @param errors
     */
    public void setErrors(cn.backend.service.wsdl.login.Error[] errors) {
        this.errors = errors;
    }

    public cn.backend.service.wsdl.login.Error getErrors(int i) {
        return this.errors[i];
    }

    public void setErrors(int i, cn.backend.service.wsdl.login.Error _value) {
        this.errors[i] = _value;
    }


    /**
     * Gets the rowCount value for this DeleteByExampleResult.
     * 
     * @return rowCount
     */
    public long getRowCount() {
        return rowCount;
    }


    /**
     * Sets the rowCount value for this DeleteByExampleResult.
     * 
     * @param rowCount
     */
    public void setRowCount(long rowCount) {
        this.rowCount = rowCount;
    }


    /**
     * Gets the success value for this DeleteByExampleResult.
     * 
     * @return success
     */
    public boolean isSuccess() {
        return success;
    }


    /**
     * Sets the success value for this DeleteByExampleResult.
     * 
     * @param success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DeleteByExampleResult)) return false;
        DeleteByExampleResult other = (DeleteByExampleResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.entity==null && other.getEntity()==null) || 
             (this.entity!=null &&
              this.entity.equals(other.getEntity()))) &&
            ((this.errors==null && other.getErrors()==null) || 
             (this.errors!=null &&
              java.util.Arrays.equals(this.errors, other.getErrors()))) &&
            this.rowCount == other.getRowCount() &&
            this.success == other.isSuccess();
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
        if (getEntity() != null) {
            _hashCode += getEntity().hashCode();
        }
        if (getErrors() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getErrors());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getErrors(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += new Long(getRowCount()).hashCode();
        _hashCode += (isSuccess() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DeleteByExampleResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DeleteByExampleResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entity");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "entity"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:sobject.partner.soap.sforce.com", "sObject"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errors");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "errors"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "Error"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rowCount");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "rowCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("success");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "success"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
