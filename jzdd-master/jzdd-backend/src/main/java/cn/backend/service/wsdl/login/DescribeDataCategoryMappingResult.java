/**
 * DescribeDataCategoryMappingResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.backend.service.wsdl.login;

public class DescribeDataCategoryMappingResult  implements java.io.Serializable {
    private java.lang.String dataCategoryGroupId;

    private java.lang.String dataCategoryGroupLabel;

    private java.lang.String dataCategoryGroupName;

    private java.lang.String dataCategoryId;

    private java.lang.String dataCategoryLabel;

    private java.lang.String dataCategoryName;

    private java.lang.String id;

    private java.lang.String mappedEntity;

    private java.lang.String mappedField;

    public DescribeDataCategoryMappingResult() {
    }

    public DescribeDataCategoryMappingResult(
           java.lang.String dataCategoryGroupId,
           java.lang.String dataCategoryGroupLabel,
           java.lang.String dataCategoryGroupName,
           java.lang.String dataCategoryId,
           java.lang.String dataCategoryLabel,
           java.lang.String dataCategoryName,
           java.lang.String id,
           java.lang.String mappedEntity,
           java.lang.String mappedField) {
           this.dataCategoryGroupId = dataCategoryGroupId;
           this.dataCategoryGroupLabel = dataCategoryGroupLabel;
           this.dataCategoryGroupName = dataCategoryGroupName;
           this.dataCategoryId = dataCategoryId;
           this.dataCategoryLabel = dataCategoryLabel;
           this.dataCategoryName = dataCategoryName;
           this.id = id;
           this.mappedEntity = mappedEntity;
           this.mappedField = mappedField;
    }


    /**
     * Gets the dataCategoryGroupId value for this DescribeDataCategoryMappingResult.
     * 
     * @return dataCategoryGroupId
     */
    public java.lang.String getDataCategoryGroupId() {
        return dataCategoryGroupId;
    }


    /**
     * Sets the dataCategoryGroupId value for this DescribeDataCategoryMappingResult.
     * 
     * @param dataCategoryGroupId
     */
    public void setDataCategoryGroupId(java.lang.String dataCategoryGroupId) {
        this.dataCategoryGroupId = dataCategoryGroupId;
    }


    /**
     * Gets the dataCategoryGroupLabel value for this DescribeDataCategoryMappingResult.
     * 
     * @return dataCategoryGroupLabel
     */
    public java.lang.String getDataCategoryGroupLabel() {
        return dataCategoryGroupLabel;
    }


    /**
     * Sets the dataCategoryGroupLabel value for this DescribeDataCategoryMappingResult.
     * 
     * @param dataCategoryGroupLabel
     */
    public void setDataCategoryGroupLabel(java.lang.String dataCategoryGroupLabel) {
        this.dataCategoryGroupLabel = dataCategoryGroupLabel;
    }


    /**
     * Gets the dataCategoryGroupName value for this DescribeDataCategoryMappingResult.
     * 
     * @return dataCategoryGroupName
     */
    public java.lang.String getDataCategoryGroupName() {
        return dataCategoryGroupName;
    }


    /**
     * Sets the dataCategoryGroupName value for this DescribeDataCategoryMappingResult.
     * 
     * @param dataCategoryGroupName
     */
    public void setDataCategoryGroupName(java.lang.String dataCategoryGroupName) {
        this.dataCategoryGroupName = dataCategoryGroupName;
    }


    /**
     * Gets the dataCategoryId value for this DescribeDataCategoryMappingResult.
     * 
     * @return dataCategoryId
     */
    public java.lang.String getDataCategoryId() {
        return dataCategoryId;
    }


    /**
     * Sets the dataCategoryId value for this DescribeDataCategoryMappingResult.
     * 
     * @param dataCategoryId
     */
    public void setDataCategoryId(java.lang.String dataCategoryId) {
        this.dataCategoryId = dataCategoryId;
    }


    /**
     * Gets the dataCategoryLabel value for this DescribeDataCategoryMappingResult.
     * 
     * @return dataCategoryLabel
     */
    public java.lang.String getDataCategoryLabel() {
        return dataCategoryLabel;
    }


    /**
     * Sets the dataCategoryLabel value for this DescribeDataCategoryMappingResult.
     * 
     * @param dataCategoryLabel
     */
    public void setDataCategoryLabel(java.lang.String dataCategoryLabel) {
        this.dataCategoryLabel = dataCategoryLabel;
    }


    /**
     * Gets the dataCategoryName value for this DescribeDataCategoryMappingResult.
     * 
     * @return dataCategoryName
     */
    public java.lang.String getDataCategoryName() {
        return dataCategoryName;
    }


    /**
     * Sets the dataCategoryName value for this DescribeDataCategoryMappingResult.
     * 
     * @param dataCategoryName
     */
    public void setDataCategoryName(java.lang.String dataCategoryName) {
        this.dataCategoryName = dataCategoryName;
    }


    /**
     * Gets the id value for this DescribeDataCategoryMappingResult.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this DescribeDataCategoryMappingResult.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the mappedEntity value for this DescribeDataCategoryMappingResult.
     * 
     * @return mappedEntity
     */
    public java.lang.String getMappedEntity() {
        return mappedEntity;
    }


    /**
     * Sets the mappedEntity value for this DescribeDataCategoryMappingResult.
     * 
     * @param mappedEntity
     */
    public void setMappedEntity(java.lang.String mappedEntity) {
        this.mappedEntity = mappedEntity;
    }


    /**
     * Gets the mappedField value for this DescribeDataCategoryMappingResult.
     * 
     * @return mappedField
     */
    public java.lang.String getMappedField() {
        return mappedField;
    }


    /**
     * Sets the mappedField value for this DescribeDataCategoryMappingResult.
     * 
     * @param mappedField
     */
    public void setMappedField(java.lang.String mappedField) {
        this.mappedField = mappedField;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DescribeDataCategoryMappingResult)) return false;
        DescribeDataCategoryMappingResult other = (DescribeDataCategoryMappingResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dataCategoryGroupId==null && other.getDataCategoryGroupId()==null) || 
             (this.dataCategoryGroupId!=null &&
              this.dataCategoryGroupId.equals(other.getDataCategoryGroupId()))) &&
            ((this.dataCategoryGroupLabel==null && other.getDataCategoryGroupLabel()==null) || 
             (this.dataCategoryGroupLabel!=null &&
              this.dataCategoryGroupLabel.equals(other.getDataCategoryGroupLabel()))) &&
            ((this.dataCategoryGroupName==null && other.getDataCategoryGroupName()==null) || 
             (this.dataCategoryGroupName!=null &&
              this.dataCategoryGroupName.equals(other.getDataCategoryGroupName()))) &&
            ((this.dataCategoryId==null && other.getDataCategoryId()==null) || 
             (this.dataCategoryId!=null &&
              this.dataCategoryId.equals(other.getDataCategoryId()))) &&
            ((this.dataCategoryLabel==null && other.getDataCategoryLabel()==null) || 
             (this.dataCategoryLabel!=null &&
              this.dataCategoryLabel.equals(other.getDataCategoryLabel()))) &&
            ((this.dataCategoryName==null && other.getDataCategoryName()==null) || 
             (this.dataCategoryName!=null &&
              this.dataCategoryName.equals(other.getDataCategoryName()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.mappedEntity==null && other.getMappedEntity()==null) || 
             (this.mappedEntity!=null &&
              this.mappedEntity.equals(other.getMappedEntity()))) &&
            ((this.mappedField==null && other.getMappedField()==null) || 
             (this.mappedField!=null &&
              this.mappedField.equals(other.getMappedField())));
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
        if (getDataCategoryGroupId() != null) {
            _hashCode += getDataCategoryGroupId().hashCode();
        }
        if (getDataCategoryGroupLabel() != null) {
            _hashCode += getDataCategoryGroupLabel().hashCode();
        }
        if (getDataCategoryGroupName() != null) {
            _hashCode += getDataCategoryGroupName().hashCode();
        }
        if (getDataCategoryId() != null) {
            _hashCode += getDataCategoryId().hashCode();
        }
        if (getDataCategoryLabel() != null) {
            _hashCode += getDataCategoryLabel().hashCode();
        }
        if (getDataCategoryName() != null) {
            _hashCode += getDataCategoryName().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getMappedEntity() != null) {
            _hashCode += getMappedEntity().hashCode();
        }
        if (getMappedField() != null) {
            _hashCode += getMappedField().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DescribeDataCategoryMappingResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeDataCategoryMappingResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataCategoryGroupId");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "dataCategoryGroupId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataCategoryGroupLabel");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "dataCategoryGroupLabel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataCategoryGroupName");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "dataCategoryGroupName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataCategoryId");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "dataCategoryId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataCategoryLabel");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "dataCategoryLabel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataCategoryName");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "dataCategoryName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mappedEntity");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "mappedEntity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mappedField");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "mappedField"));
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
