/**
 * SearchRecordMetadata.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.backend.service.wsdl.login;

public class SearchRecordMetadata  implements java.io.Serializable {
    private boolean searchPromoted;

    private boolean spellCorrected;

    public SearchRecordMetadata() {
    }

    public SearchRecordMetadata(
           boolean searchPromoted,
           boolean spellCorrected) {
           this.searchPromoted = searchPromoted;
           this.spellCorrected = spellCorrected;
    }


    /**
     * Gets the searchPromoted value for this SearchRecordMetadata.
     * 
     * @return searchPromoted
     */
    public boolean isSearchPromoted() {
        return searchPromoted;
    }


    /**
     * Sets the searchPromoted value for this SearchRecordMetadata.
     * 
     * @param searchPromoted
     */
    public void setSearchPromoted(boolean searchPromoted) {
        this.searchPromoted = searchPromoted;
    }


    /**
     * Gets the spellCorrected value for this SearchRecordMetadata.
     * 
     * @return spellCorrected
     */
    public boolean isSpellCorrected() {
        return spellCorrected;
    }


    /**
     * Sets the spellCorrected value for this SearchRecordMetadata.
     * 
     * @param spellCorrected
     */
    public void setSpellCorrected(boolean spellCorrected) {
        this.spellCorrected = spellCorrected;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SearchRecordMetadata)) return false;
        SearchRecordMetadata other = (SearchRecordMetadata) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.searchPromoted == other.isSearchPromoted() &&
            this.spellCorrected == other.isSpellCorrected();
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
        _hashCode += (isSearchPromoted() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isSpellCorrected() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SearchRecordMetadata.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SearchRecordMetadata"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("searchPromoted");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "searchPromoted"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("spellCorrected");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "spellCorrected"));
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
