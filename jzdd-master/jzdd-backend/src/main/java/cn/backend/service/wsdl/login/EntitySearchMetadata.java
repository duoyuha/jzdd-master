/**
 * EntitySearchMetadata.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.backend.service.wsdl.login;

public class EntitySearchMetadata  implements java.io.Serializable {
    private java.lang.String entityName;

    private cn.backend.service.wsdl.login.FieldLevelSearchMetadata[] fieldMetadata;

    private cn.backend.service.wsdl.login.EntityIntentQueryMetadata intentQueryMetadata;

    private cn.backend.service.wsdl.login.EntitySearchPromotionMetadata searchPromotionMetadata;

    private cn.backend.service.wsdl.login.EntitySpellCorrectionMetadata spellCorrectionMetadata;

    public EntitySearchMetadata() {
    }

    public EntitySearchMetadata(
           java.lang.String entityName,
           cn.backend.service.wsdl.login.FieldLevelSearchMetadata[] fieldMetadata,
           cn.backend.service.wsdl.login.EntityIntentQueryMetadata intentQueryMetadata,
           cn.backend.service.wsdl.login.EntitySearchPromotionMetadata searchPromotionMetadata,
           cn.backend.service.wsdl.login.EntitySpellCorrectionMetadata spellCorrectionMetadata) {
           this.entityName = entityName;
           this.fieldMetadata = fieldMetadata;
           this.intentQueryMetadata = intentQueryMetadata;
           this.searchPromotionMetadata = searchPromotionMetadata;
           this.spellCorrectionMetadata = spellCorrectionMetadata;
    }


    /**
     * Gets the entityName value for this EntitySearchMetadata.
     * 
     * @return entityName
     */
    public java.lang.String getEntityName() {
        return entityName;
    }


    /**
     * Sets the entityName value for this EntitySearchMetadata.
     * 
     * @param entityName
     */
    public void setEntityName(java.lang.String entityName) {
        this.entityName = entityName;
    }


    /**
     * Gets the fieldMetadata value for this EntitySearchMetadata.
     * 
     * @return fieldMetadata
     */
    public cn.backend.service.wsdl.login.FieldLevelSearchMetadata[] getFieldMetadata() {
        return fieldMetadata;
    }


    /**
     * Sets the fieldMetadata value for this EntitySearchMetadata.
     * 
     * @param fieldMetadata
     */
    public void setFieldMetadata(cn.backend.service.wsdl.login.FieldLevelSearchMetadata[] fieldMetadata) {
        this.fieldMetadata = fieldMetadata;
    }

    public cn.backend.service.wsdl.login.FieldLevelSearchMetadata getFieldMetadata(int i) {
        return this.fieldMetadata[i];
    }

    public void setFieldMetadata(int i, cn.backend.service.wsdl.login.FieldLevelSearchMetadata _value) {
        this.fieldMetadata[i] = _value;
    }


    /**
     * Gets the intentQueryMetadata value for this EntitySearchMetadata.
     * 
     * @return intentQueryMetadata
     */
    public cn.backend.service.wsdl.login.EntityIntentQueryMetadata getIntentQueryMetadata() {
        return intentQueryMetadata;
    }


    /**
     * Sets the intentQueryMetadata value for this EntitySearchMetadata.
     * 
     * @param intentQueryMetadata
     */
    public void setIntentQueryMetadata(cn.backend.service.wsdl.login.EntityIntentQueryMetadata intentQueryMetadata) {
        this.intentQueryMetadata = intentQueryMetadata;
    }


    /**
     * Gets the searchPromotionMetadata value for this EntitySearchMetadata.
     * 
     * @return searchPromotionMetadata
     */
    public cn.backend.service.wsdl.login.EntitySearchPromotionMetadata getSearchPromotionMetadata() {
        return searchPromotionMetadata;
    }


    /**
     * Sets the searchPromotionMetadata value for this EntitySearchMetadata.
     * 
     * @param searchPromotionMetadata
     */
    public void setSearchPromotionMetadata(cn.backend.service.wsdl.login.EntitySearchPromotionMetadata searchPromotionMetadata) {
        this.searchPromotionMetadata = searchPromotionMetadata;
    }


    /**
     * Gets the spellCorrectionMetadata value for this EntitySearchMetadata.
     * 
     * @return spellCorrectionMetadata
     */
    public cn.backend.service.wsdl.login.EntitySpellCorrectionMetadata getSpellCorrectionMetadata() {
        return spellCorrectionMetadata;
    }


    /**
     * Sets the spellCorrectionMetadata value for this EntitySearchMetadata.
     * 
     * @param spellCorrectionMetadata
     */
    public void setSpellCorrectionMetadata(cn.backend.service.wsdl.login.EntitySpellCorrectionMetadata spellCorrectionMetadata) {
        this.spellCorrectionMetadata = spellCorrectionMetadata;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EntitySearchMetadata)) return false;
        EntitySearchMetadata other = (EntitySearchMetadata) obj;
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
            ((this.fieldMetadata==null && other.getFieldMetadata()==null) || 
             (this.fieldMetadata!=null &&
              java.util.Arrays.equals(this.fieldMetadata, other.getFieldMetadata()))) &&
            ((this.intentQueryMetadata==null && other.getIntentQueryMetadata()==null) || 
             (this.intentQueryMetadata!=null &&
              this.intentQueryMetadata.equals(other.getIntentQueryMetadata()))) &&
            ((this.searchPromotionMetadata==null && other.getSearchPromotionMetadata()==null) || 
             (this.searchPromotionMetadata!=null &&
              this.searchPromotionMetadata.equals(other.getSearchPromotionMetadata()))) &&
            ((this.spellCorrectionMetadata==null && other.getSpellCorrectionMetadata()==null) || 
             (this.spellCorrectionMetadata!=null &&
              this.spellCorrectionMetadata.equals(other.getSpellCorrectionMetadata())));
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
        if (getFieldMetadata() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFieldMetadata());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFieldMetadata(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIntentQueryMetadata() != null) {
            _hashCode += getIntentQueryMetadata().hashCode();
        }
        if (getSearchPromotionMetadata() != null) {
            _hashCode += getSearchPromotionMetadata().hashCode();
        }
        if (getSpellCorrectionMetadata() != null) {
            _hashCode += getSpellCorrectionMetadata().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EntitySearchMetadata.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "EntitySearchMetadata"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entityName");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "entityName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fieldMetadata");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "fieldMetadata"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "FieldLevelSearchMetadata"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("intentQueryMetadata");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "intentQueryMetadata"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "EntityIntentQueryMetadata"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("searchPromotionMetadata");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "searchPromotionMetadata"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "EntitySearchPromotionMetadata"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("spellCorrectionMetadata");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "spellCorrectionMetadata"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "EntitySpellCorrectionMetadata"));
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
