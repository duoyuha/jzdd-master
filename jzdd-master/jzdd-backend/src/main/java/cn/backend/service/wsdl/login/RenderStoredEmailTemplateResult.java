/**
 * RenderStoredEmailTemplateResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.backend.service.wsdl.login;

public class RenderStoredEmailTemplateResult  implements java.io.Serializable {
    private cn.backend.service.wsdl.login.Error[] errors;

    private cn.backend.service.wsdl.login.SingleEmailMessage renderedEmail;

    private boolean success;

    public RenderStoredEmailTemplateResult() {
    }

    public RenderStoredEmailTemplateResult(
           cn.backend.service.wsdl.login.Error[] errors,
           cn.backend.service.wsdl.login.SingleEmailMessage renderedEmail,
           boolean success) {
           this.errors = errors;
           this.renderedEmail = renderedEmail;
           this.success = success;
    }


    /**
     * Gets the errors value for this RenderStoredEmailTemplateResult.
     * 
     * @return errors
     */
    public cn.backend.service.wsdl.login.Error[] getErrors() {
        return errors;
    }


    /**
     * Sets the errors value for this RenderStoredEmailTemplateResult.
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
     * Gets the renderedEmail value for this RenderStoredEmailTemplateResult.
     * 
     * @return renderedEmail
     */
    public cn.backend.service.wsdl.login.SingleEmailMessage getRenderedEmail() {
        return renderedEmail;
    }


    /**
     * Sets the renderedEmail value for this RenderStoredEmailTemplateResult.
     * 
     * @param renderedEmail
     */
    public void setRenderedEmail(cn.backend.service.wsdl.login.SingleEmailMessage renderedEmail) {
        this.renderedEmail = renderedEmail;
    }


    /**
     * Gets the success value for this RenderStoredEmailTemplateResult.
     * 
     * @return success
     */
    public boolean isSuccess() {
        return success;
    }


    /**
     * Sets the success value for this RenderStoredEmailTemplateResult.
     * 
     * @param success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RenderStoredEmailTemplateResult)) return false;
        RenderStoredEmailTemplateResult other = (RenderStoredEmailTemplateResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.errors==null && other.getErrors()==null) || 
             (this.errors!=null &&
              java.util.Arrays.equals(this.errors, other.getErrors()))) &&
            ((this.renderedEmail==null && other.getRenderedEmail()==null) || 
             (this.renderedEmail!=null &&
              this.renderedEmail.equals(other.getRenderedEmail()))) &&
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
        if (getRenderedEmail() != null) {
            _hashCode += getRenderedEmail().hashCode();
        }
        _hashCode += (isSuccess() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RenderStoredEmailTemplateResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "RenderStoredEmailTemplateResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errors");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "errors"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "Error"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("renderedEmail");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "renderedEmail"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SingleEmailMessage"));
        elemField.setNillable(true);
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
