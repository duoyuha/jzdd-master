/**
 * ChangeEventType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.backend.service.wsdl.login;

public class ChangeEventType implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected ChangeEventType(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _CREATE = "CREATE";
    public static final java.lang.String _UPDATE = "UPDATE";
    public static final java.lang.String _DELETE = "DELETE";
    public static final java.lang.String _UNDELETE = "UNDELETE";
    public static final java.lang.String _GAP_CREATE = "GAP_CREATE";
    public static final java.lang.String _GAP_UPDATE = "GAP_UPDATE";
    public static final java.lang.String _GAP_DELETE = "GAP_DELETE";
    public static final java.lang.String _GAP_UNDELETE = "GAP_UNDELETE";
    public static final ChangeEventType CREATE = new ChangeEventType(_CREATE);
    public static final ChangeEventType UPDATE = new ChangeEventType(_UPDATE);
    public static final ChangeEventType DELETE = new ChangeEventType(_DELETE);
    public static final ChangeEventType UNDELETE = new ChangeEventType(_UNDELETE);
    public static final ChangeEventType GAP_CREATE = new ChangeEventType(_GAP_CREATE);
    public static final ChangeEventType GAP_UPDATE = new ChangeEventType(_GAP_UPDATE);
    public static final ChangeEventType GAP_DELETE = new ChangeEventType(_GAP_DELETE);
    public static final ChangeEventType GAP_UNDELETE = new ChangeEventType(_GAP_UNDELETE);
    public java.lang.String getValue() { return _value_;}
    public static ChangeEventType fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        ChangeEventType enumeration = (ChangeEventType)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static ChangeEventType fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(ChangeEventType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "changeEventType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
