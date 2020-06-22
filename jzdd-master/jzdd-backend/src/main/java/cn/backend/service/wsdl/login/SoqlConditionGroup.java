/**
 * SoqlConditionGroup.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.backend.service.wsdl.login;

public class SoqlConditionGroup  extends cn.backend.service.wsdl.login.SoqlWhereCondition  implements java.io.Serializable {
    private cn.backend.service.wsdl.login.SoqlWhereCondition[] conditions;

    private cn.backend.service.wsdl.login.SoqlConjunction conjunction;

    public SoqlConditionGroup() {
    }

    public SoqlConditionGroup(
           cn.backend.service.wsdl.login.SoqlWhereCondition[] conditions,
           cn.backend.service.wsdl.login.SoqlConjunction conjunction) {
        this.conditions = conditions;
        this.conjunction = conjunction;
    }


    /**
     * Gets the conditions value for this SoqlConditionGroup.
     * 
     * @return conditions
     */
    public cn.backend.service.wsdl.login.SoqlWhereCondition[] getConditions() {
        return conditions;
    }


    /**
     * Sets the conditions value for this SoqlConditionGroup.
     * 
     * @param conditions
     */
    public void setConditions(cn.backend.service.wsdl.login.SoqlWhereCondition[] conditions) {
        this.conditions = conditions;
    }

    public cn.backend.service.wsdl.login.SoqlWhereCondition getConditions(int i) {
        return this.conditions[i];
    }

    public void setConditions(int i, cn.backend.service.wsdl.login.SoqlWhereCondition _value) {
        this.conditions[i] = _value;
    }


    /**
     * Gets the conjunction value for this SoqlConditionGroup.
     * 
     * @return conjunction
     */
    public cn.backend.service.wsdl.login.SoqlConjunction getConjunction() {
        return conjunction;
    }


    /**
     * Sets the conjunction value for this SoqlConditionGroup.
     * 
     * @param conjunction
     */
    public void setConjunction(cn.backend.service.wsdl.login.SoqlConjunction conjunction) {
        this.conjunction = conjunction;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SoqlConditionGroup)) return false;
        SoqlConditionGroup other = (SoqlConditionGroup) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.conditions==null && other.getConditions()==null) || 
             (this.conditions!=null &&
              java.util.Arrays.equals(this.conditions, other.getConditions()))) &&
            ((this.conjunction==null && other.getConjunction()==null) || 
             (this.conjunction!=null &&
              this.conjunction.equals(other.getConjunction())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getConditions() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getConditions());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getConditions(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getConjunction() != null) {
            _hashCode += getConjunction().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SoqlConditionGroup.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SoqlConditionGroup"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conditions");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "conditions"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SoqlWhereCondition"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conjunction");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "conjunction"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "soqlConjunction"));
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
