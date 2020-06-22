/**
 * EntitySpellCorrectionMetadata.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.backend.service.wsdl.login;

public class EntitySpellCorrectionMetadata  implements java.io.Serializable {
    private java.lang.String correctedQuery;

    private boolean hasNonCorrectedResults;

    public EntitySpellCorrectionMetadata() {
    }

    public EntitySpellCorrectionMetadata(
           java.lang.String correctedQuery,
           boolean hasNonCorrectedResults) {
           this.correctedQuery = correctedQuery;
           this.hasNonCorrectedResults = hasNonCorrectedResults;
    }


    /**
     * Gets the correctedQuery value for this EntitySpellCorrectionMetadata.
     * 
     * @return correctedQuery
     */
    public java.lang.String getCorrectedQuery() {
        return correctedQuery;
    }


    /**
     * Sets the correctedQuery value for this EntitySpellCorrectionMetadata.
     * 
     * @param correctedQuery
     */
    public void setCorrectedQuery(java.lang.String correctedQuery) {
        this.correctedQuery = correctedQuery;
    }


    /**
     * Gets the hasNonCorrectedResults value for this EntitySpellCorrectionMetadata.
     * 
     * @return hasNonCorrectedResults
     */
    public boolean isHasNonCorrectedResults() {
        return hasNonCorrectedResults;
    }


    /**
     * Sets the hasNonCorrectedResults value for this EntitySpellCorrectionMetadata.
     * 
     * @param hasNonCorrectedResults
     */
    public void setHasNonCorrectedResults(boolean hasNonCorrectedResults) {
        this.hasNonCorrectedResults = hasNonCorrectedResults;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EntitySpellCorrectionMetadata)) return false;
        EntitySpellCorrectionMetadata other = (EntitySpellCorrectionMetadata) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.correctedQuery==null && other.getCorrectedQuery()==null) || 
             (this.correctedQuery!=null &&
              this.correctedQuery.equals(other.getCorrectedQuery()))) &&
            this.hasNonCorrectedResults == other.isHasNonCorrectedResults();
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
        if (getCorrectedQuery() != null) {
            _hashCode += getCorrectedQuery().hashCode();
        }
        _hashCode += (isHasNonCorrectedResults() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EntitySpellCorrectionMetadata.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "EntitySpellCorrectionMetadata"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("correctedQuery");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "correctedQuery"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hasNonCorrectedResults");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "hasNonCorrectedResults"));
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
