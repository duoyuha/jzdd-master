/**
 * DescribeSoqlListView.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.backend.service.wsdl.login;

public class DescribeSoqlListView  implements java.io.Serializable {
    private cn.backend.service.wsdl.login.ListViewColumn[] columns;

    private java.lang.String id;

    private cn.backend.service.wsdl.login.ListViewOrderBy[] orderBy;

    private java.lang.String query;

    private java.lang.String relatedEntityId;

    private java.lang.String scope;

    private java.lang.String scopeEntityId;

    private java.lang.String sobjectType;

    private cn.backend.service.wsdl.login.SoqlWhereCondition whereCondition;

    public DescribeSoqlListView() {
    }

    public DescribeSoqlListView(
           cn.backend.service.wsdl.login.ListViewColumn[] columns,
           java.lang.String id,
           cn.backend.service.wsdl.login.ListViewOrderBy[] orderBy,
           java.lang.String query,
           java.lang.String relatedEntityId,
           java.lang.String scope,
           java.lang.String scopeEntityId,
           java.lang.String sobjectType,
           cn.backend.service.wsdl.login.SoqlWhereCondition whereCondition) {
           this.columns = columns;
           this.id = id;
           this.orderBy = orderBy;
           this.query = query;
           this.relatedEntityId = relatedEntityId;
           this.scope = scope;
           this.scopeEntityId = scopeEntityId;
           this.sobjectType = sobjectType;
           this.whereCondition = whereCondition;
    }


    /**
     * Gets the columns value for this DescribeSoqlListView.
     * 
     * @return columns
     */
    public cn.backend.service.wsdl.login.ListViewColumn[] getColumns() {
        return columns;
    }


    /**
     * Sets the columns value for this DescribeSoqlListView.
     * 
     * @param columns
     */
    public void setColumns(cn.backend.service.wsdl.login.ListViewColumn[] columns) {
        this.columns = columns;
    }

    public cn.backend.service.wsdl.login.ListViewColumn getColumns(int i) {
        return this.columns[i];
    }

    public void setColumns(int i, cn.backend.service.wsdl.login.ListViewColumn _value) {
        this.columns[i] = _value;
    }


    /**
     * Gets the id value for this DescribeSoqlListView.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this DescribeSoqlListView.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the orderBy value for this DescribeSoqlListView.
     * 
     * @return orderBy
     */
    public cn.backend.service.wsdl.login.ListViewOrderBy[] getOrderBy() {
        return orderBy;
    }


    /**
     * Sets the orderBy value for this DescribeSoqlListView.
     * 
     * @param orderBy
     */
    public void setOrderBy(cn.backend.service.wsdl.login.ListViewOrderBy[] orderBy) {
        this.orderBy = orderBy;
    }

    public cn.backend.service.wsdl.login.ListViewOrderBy getOrderBy(int i) {
        return this.orderBy[i];
    }

    public void setOrderBy(int i, cn.backend.service.wsdl.login.ListViewOrderBy _value) {
        this.orderBy[i] = _value;
    }


    /**
     * Gets the query value for this DescribeSoqlListView.
     * 
     * @return query
     */
    public java.lang.String getQuery() {
        return query;
    }


    /**
     * Sets the query value for this DescribeSoqlListView.
     * 
     * @param query
     */
    public void setQuery(java.lang.String query) {
        this.query = query;
    }


    /**
     * Gets the relatedEntityId value for this DescribeSoqlListView.
     * 
     * @return relatedEntityId
     */
    public java.lang.String getRelatedEntityId() {
        return relatedEntityId;
    }


    /**
     * Sets the relatedEntityId value for this DescribeSoqlListView.
     * 
     * @param relatedEntityId
     */
    public void setRelatedEntityId(java.lang.String relatedEntityId) {
        this.relatedEntityId = relatedEntityId;
    }


    /**
     * Gets the scope value for this DescribeSoqlListView.
     * 
     * @return scope
     */
    public java.lang.String getScope() {
        return scope;
    }


    /**
     * Sets the scope value for this DescribeSoqlListView.
     * 
     * @param scope
     */
    public void setScope(java.lang.String scope) {
        this.scope = scope;
    }


    /**
     * Gets the scopeEntityId value for this DescribeSoqlListView.
     * 
     * @return scopeEntityId
     */
    public java.lang.String getScopeEntityId() {
        return scopeEntityId;
    }


    /**
     * Sets the scopeEntityId value for this DescribeSoqlListView.
     * 
     * @param scopeEntityId
     */
    public void setScopeEntityId(java.lang.String scopeEntityId) {
        this.scopeEntityId = scopeEntityId;
    }


    /**
     * Gets the sobjectType value for this DescribeSoqlListView.
     * 
     * @return sobjectType
     */
    public java.lang.String getSobjectType() {
        return sobjectType;
    }


    /**
     * Sets the sobjectType value for this DescribeSoqlListView.
     * 
     * @param sobjectType
     */
    public void setSobjectType(java.lang.String sobjectType) {
        this.sobjectType = sobjectType;
    }


    /**
     * Gets the whereCondition value for this DescribeSoqlListView.
     * 
     * @return whereCondition
     */
    public cn.backend.service.wsdl.login.SoqlWhereCondition getWhereCondition() {
        return whereCondition;
    }


    /**
     * Sets the whereCondition value for this DescribeSoqlListView.
     * 
     * @param whereCondition
     */
    public void setWhereCondition(cn.backend.service.wsdl.login.SoqlWhereCondition whereCondition) {
        this.whereCondition = whereCondition;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DescribeSoqlListView)) return false;
        DescribeSoqlListView other = (DescribeSoqlListView) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.columns==null && other.getColumns()==null) || 
             (this.columns!=null &&
              java.util.Arrays.equals(this.columns, other.getColumns()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.orderBy==null && other.getOrderBy()==null) || 
             (this.orderBy!=null &&
              java.util.Arrays.equals(this.orderBy, other.getOrderBy()))) &&
            ((this.query==null && other.getQuery()==null) || 
             (this.query!=null &&
              this.query.equals(other.getQuery()))) &&
            ((this.relatedEntityId==null && other.getRelatedEntityId()==null) || 
             (this.relatedEntityId!=null &&
              this.relatedEntityId.equals(other.getRelatedEntityId()))) &&
            ((this.scope==null && other.getScope()==null) || 
             (this.scope!=null &&
              this.scope.equals(other.getScope()))) &&
            ((this.scopeEntityId==null && other.getScopeEntityId()==null) || 
             (this.scopeEntityId!=null &&
              this.scopeEntityId.equals(other.getScopeEntityId()))) &&
            ((this.sobjectType==null && other.getSobjectType()==null) || 
             (this.sobjectType!=null &&
              this.sobjectType.equals(other.getSobjectType()))) &&
            ((this.whereCondition==null && other.getWhereCondition()==null) || 
             (this.whereCondition!=null &&
              this.whereCondition.equals(other.getWhereCondition())));
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
        if (getColumns() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getColumns());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getColumns(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getOrderBy() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOrderBy());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOrderBy(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getQuery() != null) {
            _hashCode += getQuery().hashCode();
        }
        if (getRelatedEntityId() != null) {
            _hashCode += getRelatedEntityId().hashCode();
        }
        if (getScope() != null) {
            _hashCode += getScope().hashCode();
        }
        if (getScopeEntityId() != null) {
            _hashCode += getScopeEntityId().hashCode();
        }
        if (getSobjectType() != null) {
            _hashCode += getSobjectType().hashCode();
        }
        if (getWhereCondition() != null) {
            _hashCode += getWhereCondition().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DescribeSoqlListView.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeSoqlListView"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("columns");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "columns"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ListViewColumn"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orderBy");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "orderBy"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ListViewOrderBy"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("query");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "query"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relatedEntityId");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "relatedEntityId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scope");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "scope"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scopeEntityId");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "scopeEntityId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sobjectType");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sobjectType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("whereCondition");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "whereCondition"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SoqlWhereCondition"));
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
