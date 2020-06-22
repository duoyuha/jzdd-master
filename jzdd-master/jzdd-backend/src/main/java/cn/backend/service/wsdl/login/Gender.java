/**
 * Gender.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.backend.service.wsdl.login;

public class Gender implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected Gender(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _Neuter = "Neuter";
    public static final java.lang.String _Masculine = "Masculine";
    public static final java.lang.String _Feminine = "Feminine";
    public static final java.lang.String _AnimateMasculine = "AnimateMasculine";
    public static final java.lang.String _ClassI = "ClassI";
    public static final java.lang.String _ClassIII = "ClassIII";
    public static final java.lang.String _ClassV = "ClassV";
    public static final java.lang.String _ClassVII = "ClassVII";
    public static final java.lang.String _ClassIX = "ClassIX";
    public static final java.lang.String _ClassXI = "ClassXI";
    public static final java.lang.String _ClassXIV = "ClassXIV";
    public static final java.lang.String _ClassXV = "ClassXV";
    public static final java.lang.String _ClassXVI = "ClassXVI";
    public static final java.lang.String _ClassXVII = "ClassXVII";
    public static final java.lang.String _ClassXVIII = "ClassXVIII";
    public static final Gender Neuter = new Gender(_Neuter);
    public static final Gender Masculine = new Gender(_Masculine);
    public static final Gender Feminine = new Gender(_Feminine);
    public static final Gender AnimateMasculine = new Gender(_AnimateMasculine);
    public static final Gender ClassI = new Gender(_ClassI);
    public static final Gender ClassIII = new Gender(_ClassIII);
    public static final Gender ClassV = new Gender(_ClassV);
    public static final Gender ClassVII = new Gender(_ClassVII);
    public static final Gender ClassIX = new Gender(_ClassIX);
    public static final Gender ClassXI = new Gender(_ClassXI);
    public static final Gender ClassXIV = new Gender(_ClassXIV);
    public static final Gender ClassXV = new Gender(_ClassXV);
    public static final Gender ClassXVI = new Gender(_ClassXVI);
    public static final Gender ClassXVII = new Gender(_ClassXVII);
    public static final Gender ClassXVIII = new Gender(_ClassXVIII);
    public java.lang.String getValue() { return _value_;}
    public static Gender fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        Gender enumeration = (Gender)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static Gender fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_;}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Gender.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "Gender"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
