/**
 * LeadConvert.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.backend.service.wsdl.login;

public class LeadConvert  implements java.io.Serializable {
    private java.lang.String accountId;

    private cn.backend.service.wsdl.login.SObject accountRecord;

    private java.lang.Boolean bypassAccountDedupeCheck;

    private java.lang.Boolean bypassContactDedupeCheck;

    private java.lang.String contactId;

    private cn.backend.service.wsdl.login.SObject contactRecord;

    private java.lang.String convertedStatus;

    private boolean doNotCreateOpportunity;

    private java.lang.String leadId;

    private java.lang.String opportunityId;

    private java.lang.String opportunityName;

    private cn.backend.service.wsdl.login.SObject opportunityRecord;

    private boolean overwriteLeadSource;

    private java.lang.String ownerId;

    private boolean sendNotificationEmail;

    public LeadConvert() {
    }

    public LeadConvert(
           java.lang.String accountId,
           cn.backend.service.wsdl.login.SObject accountRecord,
           java.lang.Boolean bypassAccountDedupeCheck,
           java.lang.Boolean bypassContactDedupeCheck,
           java.lang.String contactId,
           cn.backend.service.wsdl.login.SObject contactRecord,
           java.lang.String convertedStatus,
           boolean doNotCreateOpportunity,
           java.lang.String leadId,
           java.lang.String opportunityId,
           java.lang.String opportunityName,
           cn.backend.service.wsdl.login.SObject opportunityRecord,
           boolean overwriteLeadSource,
           java.lang.String ownerId,
           boolean sendNotificationEmail) {
           this.accountId = accountId;
           this.accountRecord = accountRecord;
           this.bypassAccountDedupeCheck = bypassAccountDedupeCheck;
           this.bypassContactDedupeCheck = bypassContactDedupeCheck;
           this.contactId = contactId;
           this.contactRecord = contactRecord;
           this.convertedStatus = convertedStatus;
           this.doNotCreateOpportunity = doNotCreateOpportunity;
           this.leadId = leadId;
           this.opportunityId = opportunityId;
           this.opportunityName = opportunityName;
           this.opportunityRecord = opportunityRecord;
           this.overwriteLeadSource = overwriteLeadSource;
           this.ownerId = ownerId;
           this.sendNotificationEmail = sendNotificationEmail;
    }


    /**
     * Gets the accountId value for this LeadConvert.
     * 
     * @return accountId
     */
    public java.lang.String getAccountId() {
        return accountId;
    }


    /**
     * Sets the accountId value for this LeadConvert.
     * 
     * @param accountId
     */
    public void setAccountId(java.lang.String accountId) {
        this.accountId = accountId;
    }


    /**
     * Gets the accountRecord value for this LeadConvert.
     * 
     * @return accountRecord
     */
    public cn.backend.service.wsdl.login.SObject getAccountRecord() {
        return accountRecord;
    }


    /**
     * Sets the accountRecord value for this LeadConvert.
     * 
     * @param accountRecord
     */
    public void setAccountRecord(cn.backend.service.wsdl.login.SObject accountRecord) {
        this.accountRecord = accountRecord;
    }


    /**
     * Gets the bypassAccountDedupeCheck value for this LeadConvert.
     * 
     * @return bypassAccountDedupeCheck
     */
    public java.lang.Boolean getBypassAccountDedupeCheck() {
        return bypassAccountDedupeCheck;
    }


    /**
     * Sets the bypassAccountDedupeCheck value for this LeadConvert.
     * 
     * @param bypassAccountDedupeCheck
     */
    public void setBypassAccountDedupeCheck(java.lang.Boolean bypassAccountDedupeCheck) {
        this.bypassAccountDedupeCheck = bypassAccountDedupeCheck;
    }


    /**
     * Gets the bypassContactDedupeCheck value for this LeadConvert.
     * 
     * @return bypassContactDedupeCheck
     */
    public java.lang.Boolean getBypassContactDedupeCheck() {
        return bypassContactDedupeCheck;
    }


    /**
     * Sets the bypassContactDedupeCheck value for this LeadConvert.
     * 
     * @param bypassContactDedupeCheck
     */
    public void setBypassContactDedupeCheck(java.lang.Boolean bypassContactDedupeCheck) {
        this.bypassContactDedupeCheck = bypassContactDedupeCheck;
    }


    /**
     * Gets the contactId value for this LeadConvert.
     * 
     * @return contactId
     */
    public java.lang.String getContactId() {
        return contactId;
    }


    /**
     * Sets the contactId value for this LeadConvert.
     * 
     * @param contactId
     */
    public void setContactId(java.lang.String contactId) {
        this.contactId = contactId;
    }


    /**
     * Gets the contactRecord value for this LeadConvert.
     * 
     * @return contactRecord
     */
    public cn.backend.service.wsdl.login.SObject getContactRecord() {
        return contactRecord;
    }


    /**
     * Sets the contactRecord value for this LeadConvert.
     * 
     * @param contactRecord
     */
    public void setContactRecord(cn.backend.service.wsdl.login.SObject contactRecord) {
        this.contactRecord = contactRecord;
    }


    /**
     * Gets the convertedStatus value for this LeadConvert.
     * 
     * @return convertedStatus
     */
    public java.lang.String getConvertedStatus() {
        return convertedStatus;
    }


    /**
     * Sets the convertedStatus value for this LeadConvert.
     * 
     * @param convertedStatus
     */
    public void setConvertedStatus(java.lang.String convertedStatus) {
        this.convertedStatus = convertedStatus;
    }


    /**
     * Gets the doNotCreateOpportunity value for this LeadConvert.
     * 
     * @return doNotCreateOpportunity
     */
    public boolean isDoNotCreateOpportunity() {
        return doNotCreateOpportunity;
    }


    /**
     * Sets the doNotCreateOpportunity value for this LeadConvert.
     * 
     * @param doNotCreateOpportunity
     */
    public void setDoNotCreateOpportunity(boolean doNotCreateOpportunity) {
        this.doNotCreateOpportunity = doNotCreateOpportunity;
    }


    /**
     * Gets the leadId value for this LeadConvert.
     * 
     * @return leadId
     */
    public java.lang.String getLeadId() {
        return leadId;
    }


    /**
     * Sets the leadId value for this LeadConvert.
     * 
     * @param leadId
     */
    public void setLeadId(java.lang.String leadId) {
        this.leadId = leadId;
    }


    /**
     * Gets the opportunityId value for this LeadConvert.
     * 
     * @return opportunityId
     */
    public java.lang.String getOpportunityId() {
        return opportunityId;
    }


    /**
     * Sets the opportunityId value for this LeadConvert.
     * 
     * @param opportunityId
     */
    public void setOpportunityId(java.lang.String opportunityId) {
        this.opportunityId = opportunityId;
    }


    /**
     * Gets the opportunityName value for this LeadConvert.
     * 
     * @return opportunityName
     */
    public java.lang.String getOpportunityName() {
        return opportunityName;
    }


    /**
     * Sets the opportunityName value for this LeadConvert.
     * 
     * @param opportunityName
     */
    public void setOpportunityName(java.lang.String opportunityName) {
        this.opportunityName = opportunityName;
    }


    /**
     * Gets the opportunityRecord value for this LeadConvert.
     * 
     * @return opportunityRecord
     */
    public cn.backend.service.wsdl.login.SObject getOpportunityRecord() {
        return opportunityRecord;
    }


    /**
     * Sets the opportunityRecord value for this LeadConvert.
     * 
     * @param opportunityRecord
     */
    public void setOpportunityRecord(cn.backend.service.wsdl.login.SObject opportunityRecord) {
        this.opportunityRecord = opportunityRecord;
    }


    /**
     * Gets the overwriteLeadSource value for this LeadConvert.
     * 
     * @return overwriteLeadSource
     */
    public boolean isOverwriteLeadSource() {
        return overwriteLeadSource;
    }


    /**
     * Sets the overwriteLeadSource value for this LeadConvert.
     * 
     * @param overwriteLeadSource
     */
    public void setOverwriteLeadSource(boolean overwriteLeadSource) {
        this.overwriteLeadSource = overwriteLeadSource;
    }


    /**
     * Gets the ownerId value for this LeadConvert.
     * 
     * @return ownerId
     */
    public java.lang.String getOwnerId() {
        return ownerId;
    }


    /**
     * Sets the ownerId value for this LeadConvert.
     * 
     * @param ownerId
     */
    public void setOwnerId(java.lang.String ownerId) {
        this.ownerId = ownerId;
    }


    /**
     * Gets the sendNotificationEmail value for this LeadConvert.
     * 
     * @return sendNotificationEmail
     */
    public boolean isSendNotificationEmail() {
        return sendNotificationEmail;
    }


    /**
     * Sets the sendNotificationEmail value for this LeadConvert.
     * 
     * @param sendNotificationEmail
     */
    public void setSendNotificationEmail(boolean sendNotificationEmail) {
        this.sendNotificationEmail = sendNotificationEmail;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LeadConvert)) return false;
        LeadConvert other = (LeadConvert) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.accountId==null && other.getAccountId()==null) || 
             (this.accountId!=null &&
              this.accountId.equals(other.getAccountId()))) &&
            ((this.accountRecord==null && other.getAccountRecord()==null) || 
             (this.accountRecord!=null &&
              this.accountRecord.equals(other.getAccountRecord()))) &&
            ((this.bypassAccountDedupeCheck==null && other.getBypassAccountDedupeCheck()==null) || 
             (this.bypassAccountDedupeCheck!=null &&
              this.bypassAccountDedupeCheck.equals(other.getBypassAccountDedupeCheck()))) &&
            ((this.bypassContactDedupeCheck==null && other.getBypassContactDedupeCheck()==null) || 
             (this.bypassContactDedupeCheck!=null &&
              this.bypassContactDedupeCheck.equals(other.getBypassContactDedupeCheck()))) &&
            ((this.contactId==null && other.getContactId()==null) || 
             (this.contactId!=null &&
              this.contactId.equals(other.getContactId()))) &&
            ((this.contactRecord==null && other.getContactRecord()==null) || 
             (this.contactRecord!=null &&
              this.contactRecord.equals(other.getContactRecord()))) &&
            ((this.convertedStatus==null && other.getConvertedStatus()==null) || 
             (this.convertedStatus!=null &&
              this.convertedStatus.equals(other.getConvertedStatus()))) &&
            this.doNotCreateOpportunity == other.isDoNotCreateOpportunity() &&
            ((this.leadId==null && other.getLeadId()==null) || 
             (this.leadId!=null &&
              this.leadId.equals(other.getLeadId()))) &&
            ((this.opportunityId==null && other.getOpportunityId()==null) || 
             (this.opportunityId!=null &&
              this.opportunityId.equals(other.getOpportunityId()))) &&
            ((this.opportunityName==null && other.getOpportunityName()==null) || 
             (this.opportunityName!=null &&
              this.opportunityName.equals(other.getOpportunityName()))) &&
            ((this.opportunityRecord==null && other.getOpportunityRecord()==null) || 
             (this.opportunityRecord!=null &&
              this.opportunityRecord.equals(other.getOpportunityRecord()))) &&
            this.overwriteLeadSource == other.isOverwriteLeadSource() &&
            ((this.ownerId==null && other.getOwnerId()==null) || 
             (this.ownerId!=null &&
              this.ownerId.equals(other.getOwnerId()))) &&
            this.sendNotificationEmail == other.isSendNotificationEmail();
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
        if (getAccountId() != null) {
            _hashCode += getAccountId().hashCode();
        }
        if (getAccountRecord() != null) {
            _hashCode += getAccountRecord().hashCode();
        }
        if (getBypassAccountDedupeCheck() != null) {
            _hashCode += getBypassAccountDedupeCheck().hashCode();
        }
        if (getBypassContactDedupeCheck() != null) {
            _hashCode += getBypassContactDedupeCheck().hashCode();
        }
        if (getContactId() != null) {
            _hashCode += getContactId().hashCode();
        }
        if (getContactRecord() != null) {
            _hashCode += getContactRecord().hashCode();
        }
        if (getConvertedStatus() != null) {
            _hashCode += getConvertedStatus().hashCode();
        }
        _hashCode += (isDoNotCreateOpportunity() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getLeadId() != null) {
            _hashCode += getLeadId().hashCode();
        }
        if (getOpportunityId() != null) {
            _hashCode += getOpportunityId().hashCode();
        }
        if (getOpportunityName() != null) {
            _hashCode += getOpportunityName().hashCode();
        }
        if (getOpportunityRecord() != null) {
            _hashCode += getOpportunityRecord().hashCode();
        }
        _hashCode += (isOverwriteLeadSource() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getOwnerId() != null) {
            _hashCode += getOwnerId().hashCode();
        }
        _hashCode += (isSendNotificationEmail() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LeadConvert.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "LeadConvert"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountId");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "accountId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountRecord");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "accountRecord"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:sobject.partner.soap.sforce.com", "sObject"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bypassAccountDedupeCheck");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "bypassAccountDedupeCheck"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bypassContactDedupeCheck");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "bypassContactDedupeCheck"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contactId");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "contactId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contactRecord");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "contactRecord"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:sobject.partner.soap.sforce.com", "sObject"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("convertedStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "convertedStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("doNotCreateOpportunity");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "doNotCreateOpportunity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("leadId");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "leadId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("opportunityId");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "opportunityId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("opportunityName");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "opportunityName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("opportunityRecord");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "opportunityRecord"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:sobject.partner.soap.sforce.com", "sObject"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("overwriteLeadSource");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "overwriteLeadSource"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ownerId");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ownerId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendNotificationEmail");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sendNotificationEmail"));
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
