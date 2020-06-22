/**
 * ChangeEventHeader.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.backend.service.wsdl.login;

public class ChangeEventHeader  implements java.io.Serializable {
    private java.lang.String entityName;

    private java.lang.String[] recordIds;

    private long commitTimestamp;

    private long commitNumber;

    private java.lang.String commitUser;

    private java.lang.String[] diffFields;

    private cn.backend.service.wsdl.login.ChangeEventType changeType;

    private java.lang.String changeOrigin;

    private java.lang.String transactionKey;

    private int sequenceNumber;

    private java.lang.String[] nulledFields;

    public ChangeEventHeader() {
    }

    public ChangeEventHeader(
           java.lang.String entityName,
           java.lang.String[] recordIds,
           long commitTimestamp,
           long commitNumber,
           java.lang.String commitUser,
           java.lang.String[] diffFields,
           cn.backend.service.wsdl.login.ChangeEventType changeType,
           java.lang.String changeOrigin,
           java.lang.String transactionKey,
           int sequenceNumber,
           java.lang.String[] nulledFields) {
           this.entityName = entityName;
           this.recordIds = recordIds;
           this.commitTimestamp = commitTimestamp;
           this.commitNumber = commitNumber;
           this.commitUser = commitUser;
           this.diffFields = diffFields;
           this.changeType = changeType;
           this.changeOrigin = changeOrigin;
           this.transactionKey = transactionKey;
           this.sequenceNumber = sequenceNumber;
           this.nulledFields = nulledFields;
    }


    /**
     * Gets the entityName value for this ChangeEventHeader.
     * 
     * @return entityName
     */
    public java.lang.String getEntityName() {
        return entityName;
    }


    /**
     * Sets the entityName value for this ChangeEventHeader.
     * 
     * @param entityName
     */
    public void setEntityName(java.lang.String entityName) {
        this.entityName = entityName;
    }


    /**
     * Gets the recordIds value for this ChangeEventHeader.
     * 
     * @return recordIds
     */
    public java.lang.String[] getRecordIds() {
        return recordIds;
    }


    /**
     * Sets the recordIds value for this ChangeEventHeader.
     * 
     * @param recordIds
     */
    public void setRecordIds(java.lang.String[] recordIds) {
        this.recordIds = recordIds;
    }

    public java.lang.String getRecordIds(int i) {
        return this.recordIds[i];
    }

    public void setRecordIds(int i, java.lang.String _value) {
        this.recordIds[i] = _value;
    }


    /**
     * Gets the commitTimestamp value for this ChangeEventHeader.
     * 
     * @return commitTimestamp
     */
    public long getCommitTimestamp() {
        return commitTimestamp;
    }


    /**
     * Sets the commitTimestamp value for this ChangeEventHeader.
     * 
     * @param commitTimestamp
     */
    public void setCommitTimestamp(long commitTimestamp) {
        this.commitTimestamp = commitTimestamp;
    }


    /**
     * Gets the commitNumber value for this ChangeEventHeader.
     * 
     * @return commitNumber
     */
    public long getCommitNumber() {
        return commitNumber;
    }


    /**
     * Sets the commitNumber value for this ChangeEventHeader.
     * 
     * @param commitNumber
     */
    public void setCommitNumber(long commitNumber) {
        this.commitNumber = commitNumber;
    }


    /**
     * Gets the commitUser value for this ChangeEventHeader.
     * 
     * @return commitUser
     */
    public java.lang.String getCommitUser() {
        return commitUser;
    }


    /**
     * Sets the commitUser value for this ChangeEventHeader.
     * 
     * @param commitUser
     */
    public void setCommitUser(java.lang.String commitUser) {
        this.commitUser = commitUser;
    }


    /**
     * Gets the diffFields value for this ChangeEventHeader.
     * 
     * @return diffFields
     */
    public java.lang.String[] getDiffFields() {
        return diffFields;
    }


    /**
     * Sets the diffFields value for this ChangeEventHeader.
     * 
     * @param diffFields
     */
    public void setDiffFields(java.lang.String[] diffFields) {
        this.diffFields = diffFields;
    }

    public java.lang.String getDiffFields(int i) {
        return this.diffFields[i];
    }

    public void setDiffFields(int i, java.lang.String _value) {
        this.diffFields[i] = _value;
    }


    /**
     * Gets the changeType value for this ChangeEventHeader.
     * 
     * @return changeType
     */
    public cn.backend.service.wsdl.login.ChangeEventType getChangeType() {
        return changeType;
    }


    /**
     * Sets the changeType value for this ChangeEventHeader.
     * 
     * @param changeType
     */
    public void setChangeType(cn.backend.service.wsdl.login.ChangeEventType changeType) {
        this.changeType = changeType;
    }


    /**
     * Gets the changeOrigin value for this ChangeEventHeader.
     * 
     * @return changeOrigin
     */
    public java.lang.String getChangeOrigin() {
        return changeOrigin;
    }


    /**
     * Sets the changeOrigin value for this ChangeEventHeader.
     * 
     * @param changeOrigin
     */
    public void setChangeOrigin(java.lang.String changeOrigin) {
        this.changeOrigin = changeOrigin;
    }


    /**
     * Gets the transactionKey value for this ChangeEventHeader.
     * 
     * @return transactionKey
     */
    public java.lang.String getTransactionKey() {
        return transactionKey;
    }


    /**
     * Sets the transactionKey value for this ChangeEventHeader.
     * 
     * @param transactionKey
     */
    public void setTransactionKey(java.lang.String transactionKey) {
        this.transactionKey = transactionKey;
    }


    /**
     * Gets the sequenceNumber value for this ChangeEventHeader.
     * 
     * @return sequenceNumber
     */
    public int getSequenceNumber() {
        return sequenceNumber;
    }


    /**
     * Sets the sequenceNumber value for this ChangeEventHeader.
     * 
     * @param sequenceNumber
     */
    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }


    /**
     * Gets the nulledFields value for this ChangeEventHeader.
     * 
     * @return nulledFields
     */
    public java.lang.String[] getNulledFields() {
        return nulledFields;
    }


    /**
     * Sets the nulledFields value for this ChangeEventHeader.
     * 
     * @param nulledFields
     */
    public void setNulledFields(java.lang.String[] nulledFields) {
        this.nulledFields = nulledFields;
    }

    public java.lang.String getNulledFields(int i) {
        return this.nulledFields[i];
    }

    public void setNulledFields(int i, java.lang.String _value) {
        this.nulledFields[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ChangeEventHeader)) return false;
        ChangeEventHeader other = (ChangeEventHeader) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.entityName==null && other.getEntityName()==null) || 
             (this.entityName!=null &&
              this.entityName.equals(other.getEntityName()))) &&
            ((this.recordIds==null && other.getRecordIds()==null) || 
             (this.recordIds!=null &&
              java.util.Arrays.equals(this.recordIds, other.getRecordIds()))) &&
            this.commitTimestamp == other.getCommitTimestamp() &&
            this.commitNumber == other.getCommitNumber() &&
            ((this.commitUser==null && other.getCommitUser()==null) || 
             (this.commitUser!=null &&
              this.commitUser.equals(other.getCommitUser()))) &&
            ((this.diffFields==null && other.getDiffFields()==null) || 
             (this.diffFields!=null &&
              java.util.Arrays.equals(this.diffFields, other.getDiffFields()))) &&
            ((this.changeType==null && other.getChangeType()==null) || 
             (this.changeType!=null &&
              this.changeType.equals(other.getChangeType()))) &&
            ((this.changeOrigin==null && other.getChangeOrigin()==null) || 
             (this.changeOrigin!=null &&
              this.changeOrigin.equals(other.getChangeOrigin()))) &&
            ((this.transactionKey==null && other.getTransactionKey()==null) || 
             (this.transactionKey!=null &&
              this.transactionKey.equals(other.getTransactionKey()))) &&
            this.sequenceNumber == other.getSequenceNumber() &&
            ((this.nulledFields==null && other.getNulledFields()==null) || 
             (this.nulledFields!=null &&
              java.util.Arrays.equals(this.nulledFields, other.getNulledFields())));
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
        if (getEntityName() != null) {
            _hashCode += getEntityName().hashCode();
        }
        if (getRecordIds() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRecordIds());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRecordIds(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += new Long(getCommitTimestamp()).hashCode();
        _hashCode += new Long(getCommitNumber()).hashCode();
        if (getCommitUser() != null) {
            _hashCode += getCommitUser().hashCode();
        }
        if (getDiffFields() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDiffFields());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDiffFields(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getChangeType() != null) {
            _hashCode += getChangeType().hashCode();
        }
        if (getChangeOrigin() != null) {
            _hashCode += getChangeOrigin().hashCode();
        }
        if (getTransactionKey() != null) {
            _hashCode += getTransactionKey().hashCode();
        }
        _hashCode += getSequenceNumber();
        if (getNulledFields() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNulledFields());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getNulledFields(), i);
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
        new org.apache.axis.description.TypeDesc(ChangeEventHeader.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ChangeEventHeader"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entityName");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "entityName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recordIds");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "recordIds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("commitTimestamp");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "commitTimestamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("commitNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "commitNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("commitUser");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "commitUser"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("diffFields");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "diffFields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("changeType");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "changeType"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "changeEventType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("changeOrigin");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "changeOrigin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionKey");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "transactionKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sequenceNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sequenceNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nulledFields");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "nulledFields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
