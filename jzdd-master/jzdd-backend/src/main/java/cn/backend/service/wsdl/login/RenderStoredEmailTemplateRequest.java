/**
 * RenderStoredEmailTemplateRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.backend.service.wsdl.login;

public class RenderStoredEmailTemplateRequest  implements java.io.Serializable {
    private cn.backend.service.wsdl.login.AttachmentRetrievalOption attachmentRetrievalOption;

    private java.lang.String templateId;

    private java.lang.Boolean updateTemplateUsage;

    private java.lang.String whatId;

    private java.lang.String whoId;

    public RenderStoredEmailTemplateRequest() {
    }

    public RenderStoredEmailTemplateRequest(
           cn.backend.service.wsdl.login.AttachmentRetrievalOption attachmentRetrievalOption,
           java.lang.String templateId,
           java.lang.Boolean updateTemplateUsage,
           java.lang.String whatId,
           java.lang.String whoId) {
           this.attachmentRetrievalOption = attachmentRetrievalOption;
           this.templateId = templateId;
           this.updateTemplateUsage = updateTemplateUsage;
           this.whatId = whatId;
           this.whoId = whoId;
    }


    /**
     * Gets the attachmentRetrievalOption value for this RenderStoredEmailTemplateRequest.
     * 
     * @return attachmentRetrievalOption
     */
    public cn.backend.service.wsdl.login.AttachmentRetrievalOption getAttachmentRetrievalOption() {
        return attachmentRetrievalOption;
    }


    /**
     * Sets the attachmentRetrievalOption value for this RenderStoredEmailTemplateRequest.
     * 
     * @param attachmentRetrievalOption
     */
    public void setAttachmentRetrievalOption(cn.backend.service.wsdl.login.AttachmentRetrievalOption attachmentRetrievalOption) {
        this.attachmentRetrievalOption = attachmentRetrievalOption;
    }


    /**
     * Gets the templateId value for this RenderStoredEmailTemplateRequest.
     * 
     * @return templateId
     */
    public java.lang.String getTemplateId() {
        return templateId;
    }


    /**
     * Sets the templateId value for this RenderStoredEmailTemplateRequest.
     * 
     * @param templateId
     */
    public void setTemplateId(java.lang.String templateId) {
        this.templateId = templateId;
    }


    /**
     * Gets the updateTemplateUsage value for this RenderStoredEmailTemplateRequest.
     * 
     * @return updateTemplateUsage
     */
    public java.lang.Boolean getUpdateTemplateUsage() {
        return updateTemplateUsage;
    }


    /**
     * Sets the updateTemplateUsage value for this RenderStoredEmailTemplateRequest.
     * 
     * @param updateTemplateUsage
     */
    public void setUpdateTemplateUsage(java.lang.Boolean updateTemplateUsage) {
        this.updateTemplateUsage = updateTemplateUsage;
    }


    /**
     * Gets the whatId value for this RenderStoredEmailTemplateRequest.
     * 
     * @return whatId
     */
    public java.lang.String getWhatId() {
        return whatId;
    }


    /**
     * Sets the whatId value for this RenderStoredEmailTemplateRequest.
     * 
     * @param whatId
     */
    public void setWhatId(java.lang.String whatId) {
        this.whatId = whatId;
    }


    /**
     * Gets the whoId value for this RenderStoredEmailTemplateRequest.
     * 
     * @return whoId
     */
    public java.lang.String getWhoId() {
        return whoId;
    }


    /**
     * Sets the whoId value for this RenderStoredEmailTemplateRequest.
     * 
     * @param whoId
     */
    public void setWhoId(java.lang.String whoId) {
        this.whoId = whoId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RenderStoredEmailTemplateRequest)) return false;
        RenderStoredEmailTemplateRequest other = (RenderStoredEmailTemplateRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.attachmentRetrievalOption==null && other.getAttachmentRetrievalOption()==null) || 
             (this.attachmentRetrievalOption!=null &&
              this.attachmentRetrievalOption.equals(other.getAttachmentRetrievalOption()))) &&
            ((this.templateId==null && other.getTemplateId()==null) || 
             (this.templateId!=null &&
              this.templateId.equals(other.getTemplateId()))) &&
            ((this.updateTemplateUsage==null && other.getUpdateTemplateUsage()==null) || 
             (this.updateTemplateUsage!=null &&
              this.updateTemplateUsage.equals(other.getUpdateTemplateUsage()))) &&
            ((this.whatId==null && other.getWhatId()==null) || 
             (this.whatId!=null &&
              this.whatId.equals(other.getWhatId()))) &&
            ((this.whoId==null && other.getWhoId()==null) || 
             (this.whoId!=null &&
              this.whoId.equals(other.getWhoId())));
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
        if (getAttachmentRetrievalOption() != null) {
            _hashCode += getAttachmentRetrievalOption().hashCode();
        }
        if (getTemplateId() != null) {
            _hashCode += getTemplateId().hashCode();
        }
        if (getUpdateTemplateUsage() != null) {
            _hashCode += getUpdateTemplateUsage().hashCode();
        }
        if (getWhatId() != null) {
            _hashCode += getWhatId().hashCode();
        }
        if (getWhoId() != null) {
            _hashCode += getWhoId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RenderStoredEmailTemplateRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "RenderStoredEmailTemplateRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attachmentRetrievalOption");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "attachmentRetrievalOption"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "AttachmentRetrievalOption"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("templateId");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "templateId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("updateTemplateUsage");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "updateTemplateUsage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("whatId");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "whatId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("whoId");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "whoId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
