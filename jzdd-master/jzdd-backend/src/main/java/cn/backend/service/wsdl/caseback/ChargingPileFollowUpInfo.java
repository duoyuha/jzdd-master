/**
 * ChargingPileFollowUpInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.backend.service.wsdl.caseback;

public class ChargingPileFollowUpInfo  implements java.io.Serializable {
    private java.lang.String caseFollowUpHistoryID;

    private java.lang.String caseId;

    private java.lang.String contactCustTime;

    private java.lang.String followUpType;

    private java.lang.String remark;

    private java.lang.String repliedName;

    private java.lang.String repliedTime;

    private java.lang.String solveMethod;

    private java.lang.String solveTime;

    public ChargingPileFollowUpInfo() {
    }

    public ChargingPileFollowUpInfo(
           java.lang.String caseFollowUpHistoryID,
           java.lang.String caseId,
           java.lang.String contactCustTime,
           java.lang.String followUpType,
           java.lang.String remark,
           java.lang.String repliedName,
           java.lang.String repliedTime,
           java.lang.String solveMethod,
           java.lang.String solveTime) {
           this.caseFollowUpHistoryID = caseFollowUpHistoryID;
           this.caseId = caseId;
           this.contactCustTime = contactCustTime;
           this.followUpType = followUpType;
           this.remark = remark;
           this.repliedName = repliedName;
           this.repliedTime = repliedTime;
           this.solveMethod = solveMethod;
           this.solveTime = solveTime;
    }


    /**
     * Gets the caseFollowUpHistoryID value for this ChargingPileFollowUpInfo.
     * 
     * @return caseFollowUpHistoryID
     */
    public java.lang.String getCaseFollowUpHistoryID() {
        return caseFollowUpHistoryID;
    }


    /**
     * Sets the caseFollowUpHistoryID value for this ChargingPileFollowUpInfo.
     * 
     * @param caseFollowUpHistoryID
     */
    public void setCaseFollowUpHistoryID(java.lang.String caseFollowUpHistoryID) {
        this.caseFollowUpHistoryID = caseFollowUpHistoryID;
    }


    /**
     * Gets the caseId value for this ChargingPileFollowUpInfo.
     * 
     * @return caseId
     */
    public java.lang.String getCaseId() {
        return caseId;
    }


    /**
     * Sets the caseId value for this ChargingPileFollowUpInfo.
     * 
     * @param caseId
     */
    public void setCaseId(java.lang.String caseId) {
        this.caseId = caseId;
    }


    /**
     * Gets the contactCustTime value for this ChargingPileFollowUpInfo.
     * 
     * @return contactCustTime
     */
    public java.lang.String getContactCustTime() {
        return contactCustTime;
    }


    /**
     * Sets the contactCustTime value for this ChargingPileFollowUpInfo.
     * 
     * @param contactCustTime
     */
    public void setContactCustTime(java.lang.String contactCustTime) {
        this.contactCustTime = contactCustTime;
    }


    /**
     * Gets the followUpType value for this ChargingPileFollowUpInfo.
     * 
     * @return followUpType
     */
    public java.lang.String getFollowUpType() {
        return followUpType;
    }


    /**
     * Sets the followUpType value for this ChargingPileFollowUpInfo.
     * 
     * @param followUpType
     */
    public void setFollowUpType(java.lang.String followUpType) {
        this.followUpType = followUpType;
    }


    /**
     * Gets the remark value for this ChargingPileFollowUpInfo.
     * 
     * @return remark
     */
    public java.lang.String getRemark() {
        return remark;
    }


    /**
     * Sets the remark value for this ChargingPileFollowUpInfo.
     * 
     * @param remark
     */
    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }


    /**
     * Gets the repliedName value for this ChargingPileFollowUpInfo.
     * 
     * @return repliedName
     */
    public java.lang.String getRepliedName() {
        return repliedName;
    }


    /**
     * Sets the repliedName value for this ChargingPileFollowUpInfo.
     * 
     * @param repliedName
     */
    public void setRepliedName(java.lang.String repliedName) {
        this.repliedName = repliedName;
    }


    /**
     * Gets the repliedTime value for this ChargingPileFollowUpInfo.
     * 
     * @return repliedTime
     */
    public java.lang.String getRepliedTime() {
        return repliedTime;
    }


    /**
     * Sets the repliedTime value for this ChargingPileFollowUpInfo.
     * 
     * @param repliedTime
     */
    public void setRepliedTime(java.lang.String repliedTime) {
        this.repliedTime = repliedTime;
    }


    /**
     * Gets the solveMethod value for this ChargingPileFollowUpInfo.
     * 
     * @return solveMethod
     */
    public java.lang.String getSolveMethod() {
        return solveMethod;
    }


    /**
     * Sets the solveMethod value for this ChargingPileFollowUpInfo.
     * 
     * @param solveMethod
     */
    public void setSolveMethod(java.lang.String solveMethod) {
        this.solveMethod = solveMethod;
    }


    /**
     * Gets the solveTime value for this ChargingPileFollowUpInfo.
     * 
     * @return solveTime
     */
    public java.lang.String getSolveTime() {
        return solveTime;
    }


    /**
     * Sets the solveTime value for this ChargingPileFollowUpInfo.
     * 
     * @param solveTime
     */
    public void setSolveTime(java.lang.String solveTime) {
        this.solveTime = solveTime;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ChargingPileFollowUpInfo)) return false;
        ChargingPileFollowUpInfo other = (ChargingPileFollowUpInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.caseFollowUpHistoryID==null && other.getCaseFollowUpHistoryID()==null) || 
             (this.caseFollowUpHistoryID!=null &&
              this.caseFollowUpHistoryID.equals(other.getCaseFollowUpHistoryID()))) &&
            ((this.caseId==null && other.getCaseId()==null) || 
             (this.caseId!=null &&
              this.caseId.equals(other.getCaseId()))) &&
            ((this.contactCustTime==null && other.getContactCustTime()==null) || 
             (this.contactCustTime!=null &&
              this.contactCustTime.equals(other.getContactCustTime()))) &&
            ((this.followUpType==null && other.getFollowUpType()==null) || 
             (this.followUpType!=null &&
              this.followUpType.equals(other.getFollowUpType()))) &&
            ((this.remark==null && other.getRemark()==null) || 
             (this.remark!=null &&
              this.remark.equals(other.getRemark()))) &&
            ((this.repliedName==null && other.getRepliedName()==null) || 
             (this.repliedName!=null &&
              this.repliedName.equals(other.getRepliedName()))) &&
            ((this.repliedTime==null && other.getRepliedTime()==null) || 
             (this.repliedTime!=null &&
              this.repliedTime.equals(other.getRepliedTime()))) &&
            ((this.solveMethod==null && other.getSolveMethod()==null) || 
             (this.solveMethod!=null &&
              this.solveMethod.equals(other.getSolveMethod()))) &&
            ((this.solveTime==null && other.getSolveTime()==null) || 
             (this.solveTime!=null &&
              this.solveTime.equals(other.getSolveTime())));
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
        if (getCaseFollowUpHistoryID() != null) {
            _hashCode += getCaseFollowUpHistoryID().hashCode();
        }
        if (getCaseId() != null) {
            _hashCode += getCaseId().hashCode();
        }
        if (getContactCustTime() != null) {
            _hashCode += getContactCustTime().hashCode();
        }
        if (getFollowUpType() != null) {
            _hashCode += getFollowUpType().hashCode();
        }
        if (getRemark() != null) {
            _hashCode += getRemark().hashCode();
        }
        if (getRepliedName() != null) {
            _hashCode += getRepliedName().hashCode();
        }
        if (getRepliedTime() != null) {
            _hashCode += getRepliedTime().hashCode();
        }
        if (getSolveMethod() != null) {
            _hashCode += getSolveMethod().hashCode();
        }
        if (getSolveTime() != null) {
            _hashCode += getSolveTime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ChargingPileFollowUpInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://soap.sforce.com/schemas/class/ChargingPileInfoFollowUpWebservice", "ChargingPileFollowUpInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("caseFollowUpHistoryID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://soap.sforce.com/schemas/class/ChargingPileInfoFollowUpWebservice", "CaseFollowUpHistoryID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("caseId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://soap.sforce.com/schemas/class/ChargingPileInfoFollowUpWebservice", "CaseId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contactCustTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://soap.sforce.com/schemas/class/ChargingPileInfoFollowUpWebservice", "ContactCustTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("followUpType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://soap.sforce.com/schemas/class/ChargingPileInfoFollowUpWebservice", "FollowUpType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("remark");
        elemField.setXmlName(new javax.xml.namespace.QName("http://soap.sforce.com/schemas/class/ChargingPileInfoFollowUpWebservice", "Remark"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("repliedName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://soap.sforce.com/schemas/class/ChargingPileInfoFollowUpWebservice", "RepliedName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("repliedTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://soap.sforce.com/schemas/class/ChargingPileInfoFollowUpWebservice", "RepliedTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("solveMethod");
        elemField.setXmlName(new javax.xml.namespace.QName("http://soap.sforce.com/schemas/class/ChargingPileInfoFollowUpWebservice", "SolveMethod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("solveTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://soap.sforce.com/schemas/class/ChargingPileInfoFollowUpWebservice", "SolveTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
