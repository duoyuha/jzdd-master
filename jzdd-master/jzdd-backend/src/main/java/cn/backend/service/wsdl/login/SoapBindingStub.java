/**
 * SoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.backend.service.wsdl.login;

public class SoapBindingStub extends org.apache.axis.client.Stub implements cn.backend.service.wsdl.login.Soap_PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[63];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
        _initOperationDesc4();
        _initOperationDesc5();
        _initOperationDesc6();
        _initOperationDesc7();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("login");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "username"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "LoginResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.LoginResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "LoginFault"),
                      "cn.backend.service.wsdl.login.LoginFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "LoginFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidIdFault"),
                      "cn.backend.service.wsdl.login.InvalidIdFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidIdFault"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("describeSObject");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sObjectType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeSObjectResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.DescribeSObjectResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"),
                      "cn.backend.service.wsdl.login.InvalidSObjectFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("describeSObjects");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sObjectType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeSObjectResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.DescribeSObjectResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"),
                      "cn.backend.service.wsdl.login.InvalidSObjectFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("describeGlobal");
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeGlobalResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.DescribeGlobalResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("describeDataCategoryGroups");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sObjectType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeDataCategoryGroupResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.DescribeDataCategoryGroupResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"),
                      "cn.backend.service.wsdl.login.InvalidSObjectFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("describeDataCategoryGroupStructures");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "pairs"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DataCategoryGroupSobjectTypePair"), cn.backend.service.wsdl.login.DataCategoryGroupSobjectTypePair[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "topCategoriesOnly"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeDataCategoryGroupStructureResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.DescribeDataCategoryGroupStructureResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"),
                      "cn.backend.service.wsdl.login.InvalidSObjectFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("describeDataCategoryMappings");
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeDataCategoryMappingResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.DescribeDataCategoryMappingResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("describeKnowledgeSettings");
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "KnowledgeSettings"));
        oper.setReturnClass(cn.backend.service.wsdl.login.KnowledgeSettings.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("describeAppMenu");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "appMenuType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "AppMenuType"), cn.backend.service.wsdl.login.AppMenuType.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "networkId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ID"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeAppMenuResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.DescribeAppMenuItem[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "appMenuItems"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("describeGlobalTheme");
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeGlobalTheme"));
        oper.setReturnClass(cn.backend.service.wsdl.login.DescribeGlobalTheme.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("describeTheme");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sobjectType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeThemeResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.DescribeThemeItem[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "themeItems"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("describeLayout");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sObjectType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "layoutName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "recordTypeIds"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ID"), java.lang.String[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeLayoutResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.DescribeLayoutResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"),
                      "cn.backend.service.wsdl.login.InvalidSObjectFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidIdFault"),
                      "cn.backend.service.wsdl.login.InvalidIdFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidIdFault"), 
                      true
                     ));
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("describeSoftphoneLayout");
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeSoftphoneLayoutResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.DescribeSoftphoneLayoutResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("describeSearchLayouts");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sObjectType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeSearchLayoutResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.DescribeSearchLayoutResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"),
                      "cn.backend.service.wsdl.login.InvalidSObjectFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("describeSearchableEntities");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "includeOnlyEntitiesWithTabs"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeSearchableEntityResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.DescribeSearchableEntityResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("describeSearchScopeOrder");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "includeRealTimeEntities"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), java.lang.Boolean.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeSearchScopeOrderResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.DescribeSearchScopeOrderResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("describeCompactLayouts");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sObjectType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "recordTypeIds"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ID"), java.lang.String[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeCompactLayoutsResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.DescribeCompactLayoutsResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("describePathAssistants");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sObjectType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "picklistValue"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "recordTypeIds"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ID"), java.lang.String[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribePathAssistantsResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.DescribePathAssistant[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "pathAssistants"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("describeApprovalLayout");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sObjectType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "approvalProcessNames"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeApprovalLayoutResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.DescribeApprovalLayout[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "approvalLayouts"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("describeSoqlListViews");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeSoqlListViewsRequest"), cn.backend.service.wsdl.login.DescribeSoqlListViewParams[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "listViewParams"));
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeSoqlListViewResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.DescribeSoqlListView[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "describeSoqlListViews"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"),
                      "cn.backend.service.wsdl.login.InvalidSObjectFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("executeListView");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ExecuteListViewRequest"), cn.backend.service.wsdl.login.ExecuteListViewRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ExecuteListViewResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.ExecuteListViewResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[20] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("describeSObjectListViews");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sObjectType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "recentsOnly"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "isSoqlCompatible"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "listViewIsSoqlCompatible"), cn.backend.service.wsdl.login.ListViewIsSoqlCompatible.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "limit"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "offset"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeSoqlListViewResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.DescribeSoqlListView[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "describeSoqlListViews"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"),
                      "cn.backend.service.wsdl.login.InvalidSObjectFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[21] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("describeTabs");
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeTabSetResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.DescribeTabSetResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[22] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("describeAllTabs");
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeTab"));
        oper.setReturnClass(cn.backend.service.wsdl.login.DescribeTab[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[23] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("describePrimaryCompactLayouts");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sObjectTypes"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String[].class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeCompactLayout"));
        oper.setReturnClass(cn.backend.service.wsdl.login.DescribeCompactLayout[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[24] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("create");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sObjects"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:sobject.partner.soap.sforce.com", "sObject"), cn.backend.service.wsdl.login.SObject[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SaveResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.SaveResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"),
                      "cn.backend.service.wsdl.login.InvalidSObjectFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidFieldFault"),
                      "cn.backend.service.wsdl.login.InvalidFieldFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidFieldFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidIdFault"),
                      "cn.backend.service.wsdl.login.InvalidIdFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidIdFault"), 
                      true
                     ));
        _operations[25] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("update");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sObjects"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:sobject.partner.soap.sforce.com", "sObject"), cn.backend.service.wsdl.login.SObject[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SaveResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.SaveResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"),
                      "cn.backend.service.wsdl.login.InvalidSObjectFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidFieldFault"),
                      "cn.backend.service.wsdl.login.InvalidFieldFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidFieldFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidIdFault"),
                      "cn.backend.service.wsdl.login.InvalidIdFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidIdFault"), 
                      true
                     ));
        _operations[26] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("upsert");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "externalIDFieldName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sObjects"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:sobject.partner.soap.sforce.com", "sObject"), cn.backend.service.wsdl.login.SObject[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "UpsertResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.UpsertResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"),
                      "cn.backend.service.wsdl.login.InvalidSObjectFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidFieldFault"),
                      "cn.backend.service.wsdl.login.InvalidFieldFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidFieldFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidIdFault"),
                      "cn.backend.service.wsdl.login.InvalidIdFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidIdFault"), 
                      true
                     ));
        _operations[27] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("merge");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "MergeRequest"), cn.backend.service.wsdl.login.MergeRequest[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "MergeResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.MergeResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"),
                      "cn.backend.service.wsdl.login.InvalidSObjectFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidFieldFault"),
                      "cn.backend.service.wsdl.login.InvalidFieldFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidFieldFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidIdFault"),
                      "cn.backend.service.wsdl.login.InvalidIdFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidIdFault"), 
                      true
                     ));
        _operations[28] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("delete");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ids"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ID"), java.lang.String[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DeleteResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.DeleteResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[29] = oper;

    }

    private static void _initOperationDesc4(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("undelete");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ids"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ID"), java.lang.String[].class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "UndeleteResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.UndeleteResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[30] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("emptyRecycleBin");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ids"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ID"), java.lang.String[].class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "EmptyRecycleBinResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.EmptyRecycleBinResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[31] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("retrieve");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "fieldList"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sObjectType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ids"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ID"), java.lang.String[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:sobject.partner.soap.sforce.com", "sObject"));
        oper.setReturnClass(cn.backend.service.wsdl.login.SObject[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"),
                      "cn.backend.service.wsdl.login.InvalidSObjectFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidFieldFault"),
                      "cn.backend.service.wsdl.login.InvalidFieldFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidFieldFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "MalformedQueryFault"),
                      "cn.backend.service.wsdl.login.MalformedQueryFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "MalformedQueryFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidIdFault"),
                      "cn.backend.service.wsdl.login.InvalidIdFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidIdFault"), 
                      true
                     ));
        _operations[32] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("process");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "actions"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ProcessRequest"), cn.backend.service.wsdl.login.ProcessRequest[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ProcessResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.ProcessResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidIdFault"),
                      "cn.backend.service.wsdl.login.InvalidIdFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidIdFault"), 
                      true
                     ));
        _operations[33] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("convertLead");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "leadConverts"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "LeadConvert"), cn.backend.service.wsdl.login.LeadConvert[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "LeadConvertResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.LeadConvertResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[34] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("logout");
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[35] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("invalidateSessions");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sessionIds"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "InvalidateSessionsResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.InvalidateSessionsResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[36] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDeleted");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sObjectType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "startDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "endDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "GetDeletedResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.GetDeletedResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"),
                      "cn.backend.service.wsdl.login.InvalidSObjectFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[37] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getUpdated");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sObjectType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "startDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "endDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "GetUpdatedResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.GetUpdatedResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"),
                      "cn.backend.service.wsdl.login.InvalidSObjectFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[38] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("query");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "queryString"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "QueryResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.QueryResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"),
                      "cn.backend.service.wsdl.login.InvalidSObjectFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidFieldFault"),
                      "cn.backend.service.wsdl.login.InvalidFieldFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidFieldFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidQueryLocatorFault"),
                      "cn.backend.service.wsdl.login.InvalidQueryLocatorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidQueryLocatorFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "MalformedQueryFault"),
                      "cn.backend.service.wsdl.login.MalformedQueryFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "MalformedQueryFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidIdFault"),
                      "cn.backend.service.wsdl.login.InvalidIdFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidIdFault"), 
                      true
                     ));
        _operations[39] = oper;

    }

    private static void _initOperationDesc5(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("queryAll");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "queryString"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "QueryResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.QueryResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"),
                      "cn.backend.service.wsdl.login.InvalidSObjectFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidFieldFault"),
                      "cn.backend.service.wsdl.login.InvalidFieldFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidFieldFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidQueryLocatorFault"),
                      "cn.backend.service.wsdl.login.InvalidQueryLocatorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidQueryLocatorFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "MalformedQueryFault"),
                      "cn.backend.service.wsdl.login.MalformedQueryFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "MalformedQueryFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidIdFault"),
                      "cn.backend.service.wsdl.login.InvalidIdFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidIdFault"), 
                      true
                     ));
        _operations[40] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("queryMore");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "queryLocator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "QueryLocator"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "QueryResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.QueryResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidFieldFault"),
                      "cn.backend.service.wsdl.login.InvalidFieldFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidFieldFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidQueryLocatorFault"),
                      "cn.backend.service.wsdl.login.InvalidQueryLocatorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidQueryLocatorFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "MalformedQueryFault"),
                      "cn.backend.service.wsdl.login.MalformedQueryFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "MalformedQueryFault"), 
                      true
                     ));
        _operations[41] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("search");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "searchString"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SearchResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.SearchResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"),
                      "cn.backend.service.wsdl.login.InvalidSObjectFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidFieldFault"),
                      "cn.backend.service.wsdl.login.InvalidFieldFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidFieldFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "MalformedSearchFault"),
                      "cn.backend.service.wsdl.login.MalformedSearchFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "MalformedSearchFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[42] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getServerTimestamp");
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "GetServerTimestampResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.GetServerTimestampResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[43] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setPassword");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "userId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ID"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SetPasswordResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.SetPasswordResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidNewPasswordFault"),
                      "cn.backend.service.wsdl.login.InvalidNewPasswordFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidNewPasswordFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidIdFault"),
                      "cn.backend.service.wsdl.login.InvalidIdFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidIdFault"), 
                      true
                     ));
        _operations[44] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("changeOwnPassword");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "oldPassword"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "newPassword"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ChangeOwnPasswordResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.ChangeOwnPasswordResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidNewPasswordFault"),
                      "cn.backend.service.wsdl.login.InvalidNewPasswordFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidNewPasswordFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidOldPasswordFault"),
                      "cn.backend.service.wsdl.login.InvalidOldPasswordFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidOldPasswordFault"), 
                      true
                     ));
        _operations[45] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("resetPassword");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "userId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ID"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ResetPasswordResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.ResetPasswordResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidIdFault"),
                      "cn.backend.service.wsdl.login.InvalidIdFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidIdFault"), 
                      true
                     ));
        _operations[46] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getUserInfo");
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "GetUserInfoResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.GetUserInfoResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[47] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteByExample");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sObjects"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:sobject.partner.soap.sforce.com", "sObject"), cn.backend.service.wsdl.login.SObject[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DeleteByExampleResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.DeleteByExampleResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[48] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("sendEmailMessage");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ids"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ID"), java.lang.String[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SendEmailResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.SendEmailResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[49] = oper;

    }

    private static void _initOperationDesc6(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("sendEmail");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "messages"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "Email"), cn.backend.service.wsdl.login.Email[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SendEmailResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.SendEmailResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[50] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("renderEmailTemplate");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "renderRequests"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "RenderEmailTemplateRequest"), cn.backend.service.wsdl.login.RenderEmailTemplateRequest[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "RenderEmailTemplateResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.RenderEmailTemplateResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[51] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("renderStoredEmailTemplate");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "RenderStoredEmailTemplateRequest"), cn.backend.service.wsdl.login.RenderStoredEmailTemplateRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "RenderStoredEmailTemplateResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.RenderStoredEmailTemplateResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[52] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("performQuickActions");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "quickActions"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "PerformQuickActionRequest"), cn.backend.service.wsdl.login.PerformQuickActionRequest[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "PerformQuickActionResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.PerformQuickActionResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[53] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("describeQuickActions");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "quickActions"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeQuickActionResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.DescribeQuickActionResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[54] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("describeQuickActionsForRecordType");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "quickActions"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "recordTypeId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeQuickActionResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.DescribeQuickActionResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[55] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("describeAvailableQuickActions");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "contextType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeAvailableQuickActionResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.DescribeAvailableQuickActionResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[56] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("retrieveQuickActionTemplates");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "quickActionNames"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "contextId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ID"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "QuickActionTemplateResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.QuickActionTemplateResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[57] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("retrieveMassQuickActionTemplates");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "quickActionName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "contextIds"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ID"), java.lang.String[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "QuickActionTemplateResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.QuickActionTemplateResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[58] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("describeVisualForce");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "includeAllDetails"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "namespacePrefix"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeVisualForceResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.DescribeVisualForceResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[59] = oper;

    }

    private static void _initOperationDesc7(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("findDuplicates");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sObjects"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:sobject.partner.soap.sforce.com", "sObject"), cn.backend.service.wsdl.login.SObject[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "FindDuplicatesResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.FindDuplicatesResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"),
                      "cn.backend.service.wsdl.login.InvalidSObjectFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidFieldFault"),
                      "cn.backend.service.wsdl.login.InvalidFieldFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidFieldFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        _operations[60] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("findDuplicatesByIds");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ids"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ID"), java.lang.String[].class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "FindDuplicatesResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.FindDuplicatesResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"),
                      "cn.backend.service.wsdl.login.UnexpectedErrorFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidIdFault"),
                      "cn.backend.service.wsdl.login.InvalidIdFault",
                      new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidIdFault"), 
                      true
                     ));
        _operations[61] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("describeNouns");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "nouns"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "onlyRenamed"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "includeFields"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeNounResult"));
        oper.setReturnClass(cn.backend.service.wsdl.login.DescribeNounResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[62] = oper;

    }

    public SoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public SoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public SoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
        addBindings0();
        addBindings1();
        addBindings2();
        addBindings3();
    }

    private void addBindings0() {
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "ApiFault");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ApiFault.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "ApiQueryFault");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ApiQueryFault.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "ExceptionCode");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ExceptionCode.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "FaultCode");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.FaultCode.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidFieldFault");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.InvalidFieldFault.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidIdFault");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.InvalidIdFault.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidNewPasswordFault");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.InvalidNewPasswordFault.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidOldPasswordFault");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.InvalidOldPasswordFault.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidQueryLocatorFault");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.InvalidQueryLocatorFault.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "InvalidSObjectFault");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.InvalidSObjectFault.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "LoginFault");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.LoginFault.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "MalformedQueryFault");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.MalformedQueryFault.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "MalformedSearchFault");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.MalformedSearchFault.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:fault.partner.soap.sforce.com", "UnexpectedErrorFault");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.UnexpectedErrorFault.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">AllOrNoneHeader");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.AllOrNoneHeader.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">AllowFieldTruncationHeader");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.AllowFieldTruncationHeader.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">AssignmentRuleHeader");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.AssignmentRuleHeader.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">CallOptions");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.CallOptions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">changeOwnPassword");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ChangeOwnPassword.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">changeOwnPasswordResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ChangeOwnPasswordResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">convertLead");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.LeadConvert[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "LeadConvert");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "leadConverts");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">convertLeadResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.LeadConvertResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "LeadConvertResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">create");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SObject[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:sobject.partner.soap.sforce.com", "sObject");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sObjects");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">createResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SaveResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SaveResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">DebuggingHeader");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DebuggingHeader.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">DebuggingInfo");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DebuggingInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">delete");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ID");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ids");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">deleteByExample");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SObject[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:sobject.partner.soap.sforce.com", "sObject");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sObjects");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">deleteByExampleResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DeleteByExampleResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DeleteByExampleResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">deleteResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DeleteResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DeleteResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeAllTabs");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeAllTabs.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeAllTabsResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeTab[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeTab");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeAppMenu");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeAppMenu.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeAppMenuResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeAppMenuResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeApprovalLayout");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeApprovalLayoutType1.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeApprovalLayoutResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeApprovalLayoutResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeAvailableQuickActions");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeAvailableQuickActions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeAvailableQuickActionsResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeAvailableQuickActionResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeAvailableQuickActionResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeCompactLayouts");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeCompactLayouts.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeCompactLayoutsResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeCompactLayoutsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeDataCategoryGroups");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sObjectType");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeDataCategoryGroupsResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeDataCategoryGroupResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeDataCategoryGroupResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeDataCategoryGroupStructures");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeDataCategoryGroupStructures.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeDataCategoryGroupStructuresResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeDataCategoryGroupStructureResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeDataCategoryGroupStructureResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeDataCategoryMappings");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeDataCategoryMappings.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeDataCategoryMappingsResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeDataCategoryMappingResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeDataCategoryMappingResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeGlobal");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeGlobal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeGlobalResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeGlobalResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeGlobalTheme");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeGlobalThemeType0.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeGlobalThemeResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeGlobalThemeResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeKnowledgeSettings");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeKnowledgeSettings.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeKnowledgeSettingsResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeKnowledgeSettingsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeLayout");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeLayoutType2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeLayoutResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeLayoutResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeNouns");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeNouns.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeNounsResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeNounResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeNounResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describePathAssistants");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribePathAssistants.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describePathAssistantsResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribePathAssistantsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describePrimaryCompactLayouts");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sObjectTypes");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describePrimaryCompactLayoutsResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeCompactLayout[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeCompactLayout");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeQuickActions");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "quickActions");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeQuickActionsForRecordType");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeQuickActionsForRecordType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeQuickActionsForRecordTypeResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeQuickActionResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeQuickActionResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeQuickActionsResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeQuickActionResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeQuickActionResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeSearchableEntities");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeSearchableEntities.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeSearchableEntitiesResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeSearchableEntityResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeSearchableEntityResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeSearchLayouts");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sObjectType");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeSearchLayoutsResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeSearchLayoutResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeSearchLayoutResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeSearchScopeOrder");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeSearchScopeOrder.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeSearchScopeOrderResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeSearchScopeOrderResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeSearchScopeOrderResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeSObject");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeSObject.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeSObjectListViews");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeSObjectListViews.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeSObjectListViewsResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeSObjectListViewsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeSObjectResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeSObjectResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeSObjects");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sObjectType");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeSObjectsResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeSObjectResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeSObjectResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeSoftphoneLayout");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeSoftphoneLayout.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeSoftphoneLayoutResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeSoftphoneLayoutResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeSoqlListViews");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeSoqlListViews.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeSoqlListViewsResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeSoqlListViewsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeTabs");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeTabs.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeTabsResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeTabSetResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeTabSetResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeTheme");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sobjectType");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeThemeResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeThemeResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeVisualForce");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeVisualForce.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">describeVisualForceResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeVisualForceResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">DisableFeedTrackingHeader");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DisableFeedTrackingHeader.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">DuplicateRuleHeader");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DuplicateRuleHeader.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">EmailHeader");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.EmailHeader.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">emptyRecycleBin");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ID");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ids");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">emptyRecycleBinResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.EmptyRecycleBinResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "EmptyRecycleBinResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">executeListView");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ExecuteListView.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">executeListViewResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ExecuteListViewResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">findDuplicates");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SObject[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:sobject.partner.soap.sforce.com", "sObject");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sObjects");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">findDuplicatesByIds");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ID");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ids");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">findDuplicatesByIdsResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.FindDuplicatesResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "FindDuplicatesResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">findDuplicatesResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.FindDuplicatesResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "FindDuplicatesResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">getDeleted");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.GetDeleted.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">getDeletedResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.GetDeletedResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">getServerTimestamp");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.GetServerTimestamp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }
    private void addBindings1() {
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">getServerTimestampResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.GetServerTimestampResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">getUpdated");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.GetUpdated.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">getUpdatedResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.GetUpdatedResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">getUserInfo");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.GetUserInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">getUserInfoResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.GetUserInfoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">invalidateSessions");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sessionIds");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">invalidateSessionsResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.InvalidateSessionsResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "InvalidateSessionsResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">LimitInfoHeader");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.LimitInfo[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "LimitInfo");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "limitInfo");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">LocaleOptions");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.LocaleOptions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">login");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.Login.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">loginResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.LoginResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">LoginScopeHeader");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.LoginScopeHeader.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">logout");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.Logout.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">logoutResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.LogoutResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">merge");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.MergeRequest[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "MergeRequest");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "request");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">mergeResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.MergeResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "MergeResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">MruHeader");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.MruHeader.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">OwnerChangeOptions");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.OwnerChangeOption[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "OwnerChangeOption");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "options");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">PackageVersionHeader");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.PackageVersion[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "PackageVersion");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "packageVersions");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">performQuickActions");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.PerformQuickActionRequest[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "PerformQuickActionRequest");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "quickActions");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">performQuickActionsResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.PerformQuickActionResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "PerformQuickActionResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">process");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ProcessRequest[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ProcessRequest");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "actions");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">processResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ProcessResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ProcessResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">query");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.Query.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">queryAll");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.QueryAll.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">queryAllResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.QueryAllResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">queryMore");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.QueryMore.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">queryMoreResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.QueryMoreResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">QueryOptions");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.QueryOptions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">queryResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.QueryResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">renderEmailTemplate");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.RenderEmailTemplateRequest[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "RenderEmailTemplateRequest");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "renderRequests");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">renderEmailTemplateResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.RenderEmailTemplateResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "RenderEmailTemplateResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">renderStoredEmailTemplate");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.RenderStoredEmailTemplate.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">renderStoredEmailTemplateResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.RenderStoredEmailTemplateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">resetPassword");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ResetPassword.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">resetPasswordResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ResetPasswordResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">retrieve");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.Retrieve.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">retrieveMassQuickActionTemplates");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.RetrieveMassQuickActionTemplates.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">retrieveMassQuickActionTemplatesResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.QuickActionTemplateResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "QuickActionTemplateResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">retrieveQuickActionTemplates");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.RetrieveQuickActionTemplates.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">retrieveQuickActionTemplatesResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.QuickActionTemplateResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "QuickActionTemplateResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">retrieveResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SObject[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:sobject.partner.soap.sforce.com", "sObject");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">search");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.Search.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">searchResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SearchResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">sendEmail");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.Email[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "Email");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "messages");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">sendEmailMessage");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ID");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ids");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">sendEmailMessageResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SendEmailResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SendEmailResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">sendEmailResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SendEmailResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SendEmailResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">SessionHeader");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SessionHeader.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">setPassword");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SetPassword.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">setPasswordResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SetPasswordResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">StreamingEnabledHeader");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.StreamingEnabledHeader.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">undelete");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ID");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ids");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">undeleteResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.UndeleteResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "UndeleteResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">update");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SObject[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:sobject.partner.soap.sforce.com", "sObject");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sObjects");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">updateResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SaveResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SaveResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">upsert");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.Upsert.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">upsertResponse");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.UpsertResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "UpsertResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", ">UserTerritoryDeleteHeader");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.UserTerritoryDeleteHeader.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ActionOverride");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ActionOverride.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "AdditionalInformationMap");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.AdditionalInformationMap.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "address");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.Address.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "AnalyticsCloudComponent");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.AnalyticsCloudComponent.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "AppMenuType");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.AppMenuType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "Article");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.Article.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "AttachmentRetrievalOption");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.AttachmentRetrievalOption.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "Canvas");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.Canvas.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "CaseType");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.CaseType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ChangeEventHeader");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ChangeEventHeader.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "changeEventType");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ChangeEventType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ChangeOwnPasswordResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ChangeOwnPasswordResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ChildRelationship");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ChildRelationship.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "CustomLinkComponent");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.CustomLinkComponent.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DataCategory");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DataCategory.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DataCategoryGroupSobjectTypePair");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DataCategoryGroupSobjectTypePair.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DebugLevel");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DebugLevel.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DeleteByExampleResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DeleteByExampleResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DeletedRecord");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DeletedRecord.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DeleteResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DeleteResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeAppMenuItem");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeAppMenuItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeAppMenuResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeAppMenuItem[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeAppMenuItem");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "appMenuItems");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeApprovalLayout");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeApprovalLayout.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeApprovalLayoutResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeApprovalLayout[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeApprovalLayout");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "approvalLayouts");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeAvailableQuickActionResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeAvailableQuickActionResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeColor");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeColor.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeColumn");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeColumn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeCompactLayout");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeCompactLayout.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeCompactLayoutsResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeCompactLayoutsResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeDataCategoryGroupResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeDataCategoryGroupResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeDataCategoryGroupStructureResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeDataCategoryGroupStructureResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeDataCategoryMappingResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeDataCategoryMappingResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeGlobalResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeGlobalResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeGlobalSObjectResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeGlobalSObjectResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeGlobalTheme");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeGlobalTheme.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeIcon");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeIcon.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeLayout");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeLayout.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeLayoutButton");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeLayoutButton.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeLayoutButtonSection");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeLayoutButton[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeLayoutButton");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "detailButtons");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeLayoutComponent");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeLayoutComponent.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeLayoutFeedFilter");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeLayoutFeedFilter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }
    private void addBindings2() {
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeLayoutFeedView");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeLayoutFeedFilter[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeLayoutFeedFilter");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "feedFilters");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeLayoutItem");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeLayoutItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeLayoutResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeLayoutResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeLayoutRow");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeLayoutRow.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeLayoutSaveOption");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeLayoutSaveOption.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeLayoutSection");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeLayoutSection.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeNounResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeNounResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribePathAssistant");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribePathAssistant.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribePathAssistantField");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribePathAssistantField.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribePathAssistantsResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribePathAssistant[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribePathAssistant");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "pathAssistants");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribePathAssistantStep");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribePathAssistantStep.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeQuickActionDefaultValue");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeQuickActionDefaultValue.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeQuickActionListItemResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeQuickActionListItemResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeQuickActionListResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeQuickActionListItemResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeQuickActionListItemResult");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "quickActionListItems");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeQuickActionResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeQuickActionResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeRelatedContentItem");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeRelatedContentItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeSearchableEntityResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeSearchableEntityResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeSearchLayoutResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeSearchLayoutResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeSearchScopeOrderResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeSearchScopeOrderResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeSObjectResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeSObjectResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeSoftphoneLayoutCallType");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeSoftphoneLayoutCallType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeSoftphoneLayoutInfoField");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeSoftphoneLayoutInfoField.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeSoftphoneLayoutItem");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeSoftphoneLayoutItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeSoftphoneLayoutResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeSoftphoneLayoutResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeSoftphoneLayoutSection");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeSoftphoneLayoutSection.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeSoftphoneScreenPopOption");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeSoftphoneScreenPopOption.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeSoqlListView");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeSoqlListView.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeSoqlListViewParams");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeSoqlListViewParams.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeSoqlListViewResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeSoqlListView[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeSoqlListView");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "describeSoqlListViews");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeSoqlListViewsRequest");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeSoqlListViewParams[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeSoqlListViewParams");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "listViewParams");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeTab");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeTab.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeTabSetResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeTabSetResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeThemeItem");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeThemeItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeThemeResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeThemeItem[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeThemeItem");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "themeItems");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeVisualForceResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeVisualForceResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "differenceType");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DifferenceType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DuplicateError");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DuplicateError.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DuplicateResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DuplicateResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "Email");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.Email.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "EmailFileAttachment");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.EmailFileAttachment.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "EmailPriority");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.EmailPriority.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "EmptyRecycleBinResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.EmptyRecycleBinResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "EntityIntentQueryMetadata");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.EntityIntentQueryMetadata.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "EntitySearchMetadata");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.EntitySearchMetadata.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "EntitySearchPromotionMetadata");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.EntitySearchPromotionMetadata.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "EntitySpellCorrectionMetadata");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.EntitySpellCorrectionMetadata.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "Error");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.Error.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ExecuteListViewRequest");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ExecuteListViewRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ExecuteListViewResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ExecuteListViewResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ExtendedErrorCode");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ExtendedErrorCode.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ExtendedErrorDetails");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ExtendedErrorDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "FeedLayoutFilterType");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.FeedLayoutFilterType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "Field");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.Field.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "FieldComponent");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.FieldComponent.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "FieldDiff");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.FieldDiff.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "FieldLayoutComponent");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.FieldLayoutComponent.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "FieldLevelSearchMetadata");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.FieldLevelSearchMetadata.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "fieldType");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.FieldType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "FilteredLookupInfo");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.FilteredLookupInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "FindDuplicatesResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.FindDuplicatesResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "Gender");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.Gender.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "GetDeletedResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.GetDeletedResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "GetServerTimestampResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.GetServerTimestampResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "GetUpdatedResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.GetUpdatedResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "GetUserInfoResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.GetUserInfoResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "GrammaticalNumber");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.GrammaticalNumber.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ID");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "InvalidateSessionsResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.InvalidateSessionsResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "JunctionIdListNames");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "names");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "KnowledgeLanguageItem");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.KnowledgeLanguageItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "KnowledgeSettings");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.KnowledgeSettings.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "LabelsSearchMetadata");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.LabelsSearchMetadata.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "layoutComponentType");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.LayoutComponentType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "LeadConvert");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.LeadConvert.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "LeadConvertResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.LeadConvertResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "LimitInfo");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.LimitInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ListViewColumn");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ListViewColumn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "listViewIsSoqlCompatible");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ListViewIsSoqlCompatible.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ListViewOrderBy");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ListViewOrderBy.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ListViewRecord");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ListViewRecordColumn[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ListViewRecordColumn");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "columns");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ListViewRecordColumn");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ListViewRecordColumn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "location");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.Location.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "LogCategory");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.LogCategory.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "LogCategoryLevel");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.LogCategoryLevel.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "LogInfo");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.LogInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "LoginResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.LoginResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "MassEmailMessage");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.MassEmailMessage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "MatchRecord");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.MatchRecord.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "MatchResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.MatchResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "MergeRequest");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.MergeRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "MergeResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.MergeResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "NameCaseValue");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.NameCaseValue.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "NamedLayoutInfo");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.NamedLayoutInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "NameObjectValuePair");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.NameObjectValuePair.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "NameValuePair");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.NameValuePair.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "orderByDirection");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.OrderByDirection.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "orderByNullsPosition");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.OrderByNullsPosition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "OwnerChangeOption");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.OwnerChangeOption.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "OwnerChangeOptionType");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.OwnerChangeOptionType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "PackageVersion");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.PackageVersion.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }
    private void addBindings3() {
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "PerformQuickActionRequest");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.PerformQuickActionRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "PerformQuickActionResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.PerformQuickActionResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "PicklistEntry");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.PicklistEntry.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "PicklistForRecordType");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.PicklistForRecordType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "Possessive");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.Possessive.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ProcessRequest");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ProcessRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ProcessResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ProcessResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ProcessSubmitRequest");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ProcessSubmitRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ProcessWorkitemRequest");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ProcessWorkitemRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "QueryLocator");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "QueryResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.QueryResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "QuickActionTemplateResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.QuickActionTemplateResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "RecordTypeCompactLayoutMapping");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.RecordTypeCompactLayoutMapping.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "RecordTypeInfo");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.RecordTypeInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "RecordTypeMapping");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.RecordTypeMapping.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "RecordTypesSupported");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.RecordTypeInfo[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "RecordTypeInfo");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "recordTypeInfos");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "RelatedContent");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.DescribeRelatedContentItem[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "DescribeRelatedContentItem");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "relatedContentItems");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "RelatedList");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.RelatedList.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "RelatedListColumn");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.RelatedListColumn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "RelatedListSort");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.RelatedListSort.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "RelationshipReferenceTo");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "referenceTo");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "RenderEmailTemplateBodyResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.RenderEmailTemplateBodyResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "RenderEmailTemplateError");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.RenderEmailTemplateError.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "RenderEmailTemplateRequest");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.RenderEmailTemplateRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "RenderEmailTemplateResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.RenderEmailTemplateResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "RenderStoredEmailTemplateRequest");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.RenderStoredEmailTemplateRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "RenderStoredEmailTemplateResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.RenderStoredEmailTemplateResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ReportChartComponent");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ReportChartComponent.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ReportChartSize");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ReportChartSize.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ResetPasswordResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ResetPasswordResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SaveResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SaveResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ScopeInfo");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ScopeInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SearchLayoutButton");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SearchLayoutButton.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SearchLayoutButtonsDisplayed");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SearchLayoutButtonsDisplayed.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SearchLayoutField");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SearchLayoutField.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SearchLayoutFieldsDisplayed");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SearchLayoutFieldsDisplayed.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SearchRecord");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SearchRecord.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SearchRecordMetadata");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SearchRecordMetadata.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SearchResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SearchResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SearchResultsMetadata");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SearchResultsMetadata.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SearchSnippet");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SearchSnippet.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SendEmailError");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SendEmailError.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SendEmailOptOutPolicy");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SendEmailOptOutPolicy.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SendEmailResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SendEmailResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SetPasswordResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SetPasswordResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "ShareAccessLevel");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.ShareAccessLevel.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SingleEmailMessage");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SingleEmailMessage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "soapType");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SoapType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SoqlCondition");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SoqlCondition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SoqlConditionGroup");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SoqlConditionGroup.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "soqlConjunction");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SoqlConjunction.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SoqlNotCondition");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SoqlNotCondition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "soqlOperator");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SoqlOperator.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SoqlSubQueryCondition");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SoqlSubQueryCondition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "SoqlWhereCondition");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SoqlWhereCondition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "StartsWith");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.StartsWith.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "StatusCode");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.StatusCode.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "StringList");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "values");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "TabOrderType");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.TabOrderType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "UndeleteResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.UndeleteResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "UpsertResult");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.UpsertResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "VisualforcePage");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.VisualforcePage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "WebLinkPosition");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.WebLinkPosition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "WebLinkType");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.WebLinkType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "WebLinkWindowType");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.WebLinkWindowType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("urn:sobject.partner.soap.sforce.com", "sObject");
            cachedSerQNames.add(qName);
            cls = cn.backend.service.wsdl.login.SObject.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public cn.backend.service.wsdl.login.LoginResult login(java.lang.String username, java.lang.String password) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.LoginFault, cn.backend.service.wsdl.login.UnexpectedErrorFault, cn.backend.service.wsdl.login.InvalidIdFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "login"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {username, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.LoginResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.LoginResult) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.LoginResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.LoginFault) {
              throw (cn.backend.service.wsdl.login.LoginFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidIdFault) {
              throw (cn.backend.service.wsdl.login.InvalidIdFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.DescribeSObjectResult describeSObject(java.lang.String sObjectType) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "describeSObject"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sObjectType});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.DescribeSObjectResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.DescribeSObjectResult) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.DescribeSObjectResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidSObjectFault) {
              throw (cn.backend.service.wsdl.login.InvalidSObjectFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.DescribeSObjectResult[] describeSObjects(java.lang.String[] sObjectType) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "describeSObjects"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sObjectType});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.DescribeSObjectResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.DescribeSObjectResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.DescribeSObjectResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidSObjectFault) {
              throw (cn.backend.service.wsdl.login.InvalidSObjectFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.DescribeGlobalResult describeGlobal() throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "describeGlobal"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.DescribeGlobalResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.DescribeGlobalResult) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.DescribeGlobalResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.DescribeDataCategoryGroupResult[] describeDataCategoryGroups(java.lang.String[] sObjectType) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "describeDataCategoryGroups"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sObjectType});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.DescribeDataCategoryGroupResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.DescribeDataCategoryGroupResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.DescribeDataCategoryGroupResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidSObjectFault) {
              throw (cn.backend.service.wsdl.login.InvalidSObjectFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.DescribeDataCategoryGroupStructureResult[] describeDataCategoryGroupStructures(cn.backend.service.wsdl.login.DataCategoryGroupSobjectTypePair[] pairs, boolean topCategoriesOnly) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "describeDataCategoryGroupStructures"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pairs, new java.lang.Boolean(topCategoriesOnly)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.DescribeDataCategoryGroupStructureResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.DescribeDataCategoryGroupStructureResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.DescribeDataCategoryGroupStructureResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidSObjectFault) {
              throw (cn.backend.service.wsdl.login.InvalidSObjectFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.DescribeDataCategoryMappingResult[] describeDataCategoryMappings() throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "describeDataCategoryMappings"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.DescribeDataCategoryMappingResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.DescribeDataCategoryMappingResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.DescribeDataCategoryMappingResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.KnowledgeSettings describeKnowledgeSettings() throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "describeKnowledgeSettings"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.KnowledgeSettings) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.KnowledgeSettings) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.KnowledgeSettings.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.DescribeAppMenuItem[] describeAppMenu(cn.backend.service.wsdl.login.AppMenuType appMenuType, java.lang.String networkId) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "describeAppMenu"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {appMenuType, networkId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.DescribeAppMenuItem[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.DescribeAppMenuItem[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.DescribeAppMenuItem[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.DescribeGlobalTheme describeGlobalTheme() throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "describeGlobalTheme"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.DescribeGlobalTheme) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.DescribeGlobalTheme) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.DescribeGlobalTheme.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.DescribeThemeItem[] describeTheme(java.lang.String[] sobjectType) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "describeTheme"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sobjectType});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.DescribeThemeItem[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.DescribeThemeItem[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.DescribeThemeItem[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.DescribeLayoutResult describeLayout(java.lang.String sObjectType, java.lang.String layoutName, java.lang.String[] recordTypeIds) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.UnexpectedErrorFault, cn.backend.service.wsdl.login.InvalidIdFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "describeLayout"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sObjectType, layoutName, recordTypeIds});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.DescribeLayoutResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.DescribeLayoutResult) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.DescribeLayoutResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidSObjectFault) {
              throw (cn.backend.service.wsdl.login.InvalidSObjectFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidIdFault) {
              throw (cn.backend.service.wsdl.login.InvalidIdFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.DescribeSoftphoneLayoutResult describeSoftphoneLayout() throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "describeSoftphoneLayout"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.DescribeSoftphoneLayoutResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.DescribeSoftphoneLayoutResult) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.DescribeSoftphoneLayoutResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.DescribeSearchLayoutResult[] describeSearchLayouts(java.lang.String[] sObjectType) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "describeSearchLayouts"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sObjectType});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.DescribeSearchLayoutResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.DescribeSearchLayoutResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.DescribeSearchLayoutResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidSObjectFault) {
              throw (cn.backend.service.wsdl.login.InvalidSObjectFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.DescribeSearchableEntityResult[] describeSearchableEntities(boolean includeOnlyEntitiesWithTabs) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "describeSearchableEntities"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Boolean(includeOnlyEntitiesWithTabs)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.DescribeSearchableEntityResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.DescribeSearchableEntityResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.DescribeSearchableEntityResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.DescribeSearchScopeOrderResult[] describeSearchScopeOrder(java.lang.Boolean includeRealTimeEntities) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "describeSearchScopeOrder"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {includeRealTimeEntities});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.DescribeSearchScopeOrderResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.DescribeSearchScopeOrderResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.DescribeSearchScopeOrderResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.DescribeCompactLayoutsResult describeCompactLayouts(java.lang.String sObjectType, java.lang.String[] recordTypeIds) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "describeCompactLayouts"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sObjectType, recordTypeIds});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.DescribeCompactLayoutsResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.DescribeCompactLayoutsResult) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.DescribeCompactLayoutsResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.DescribePathAssistant[] describePathAssistants(java.lang.String sObjectType, java.lang.String picklistValue, java.lang.String[] recordTypeIds) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "describePathAssistants"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sObjectType, picklistValue, recordTypeIds});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.DescribePathAssistant[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.DescribePathAssistant[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.DescribePathAssistant[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.DescribeApprovalLayout[] describeApprovalLayout(java.lang.String sObjectType, java.lang.String[] approvalProcessNames) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "describeApprovalLayout"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sObjectType, approvalProcessNames});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.DescribeApprovalLayout[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.DescribeApprovalLayout[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.DescribeApprovalLayout[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.DescribeSoqlListView[] describeSoqlListViews(cn.backend.service.wsdl.login.DescribeSoqlListViewParams[] request) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "describeSoqlListViews"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.DescribeSoqlListView[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.DescribeSoqlListView[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.DescribeSoqlListView[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidSObjectFault) {
              throw (cn.backend.service.wsdl.login.InvalidSObjectFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.ExecuteListViewResult executeListView(cn.backend.service.wsdl.login.ExecuteListViewRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[20]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "executeListView"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.ExecuteListViewResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.ExecuteListViewResult) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.ExecuteListViewResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.DescribeSoqlListView[] describeSObjectListViews(java.lang.String sObjectType, boolean recentsOnly, cn.backend.service.wsdl.login.ListViewIsSoqlCompatible isSoqlCompatible, int limit, int offset) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[21]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "describeSObjectListViews"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sObjectType, new java.lang.Boolean(recentsOnly), isSoqlCompatible, new java.lang.Integer(limit), new java.lang.Integer(offset)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.DescribeSoqlListView[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.DescribeSoqlListView[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.DescribeSoqlListView[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidSObjectFault) {
              throw (cn.backend.service.wsdl.login.InvalidSObjectFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.DescribeTabSetResult[] describeTabs() throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[22]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "describeTabs"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.DescribeTabSetResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.DescribeTabSetResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.DescribeTabSetResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.DescribeTab[] describeAllTabs() throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[23]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "describeAllTabs"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.DescribeTab[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.DescribeTab[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.DescribeTab[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.DescribeCompactLayout[] describePrimaryCompactLayouts(java.lang.String[] sObjectTypes) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[24]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "describePrimaryCompactLayouts"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sObjectTypes});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.DescribeCompactLayout[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.DescribeCompactLayout[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.DescribeCompactLayout[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.SaveResult[] create(cn.backend.service.wsdl.login.SObject[] sObjects) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.InvalidFieldFault, cn.backend.service.wsdl.login.UnexpectedErrorFault, cn.backend.service.wsdl.login.InvalidIdFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[25]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "create"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sObjects});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.SaveResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.SaveResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.SaveResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidSObjectFault) {
              throw (cn.backend.service.wsdl.login.InvalidSObjectFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidFieldFault) {
              throw (cn.backend.service.wsdl.login.InvalidFieldFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidIdFault) {
              throw (cn.backend.service.wsdl.login.InvalidIdFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.SaveResult[] update(cn.backend.service.wsdl.login.SObject[] sObjects) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.InvalidFieldFault, cn.backend.service.wsdl.login.UnexpectedErrorFault, cn.backend.service.wsdl.login.InvalidIdFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[26]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "update"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sObjects});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.SaveResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.SaveResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.SaveResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidSObjectFault) {
              throw (cn.backend.service.wsdl.login.InvalidSObjectFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidFieldFault) {
              throw (cn.backend.service.wsdl.login.InvalidFieldFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidIdFault) {
              throw (cn.backend.service.wsdl.login.InvalidIdFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.UpsertResult[] upsert(java.lang.String externalIDFieldName, cn.backend.service.wsdl.login.SObject[] sObjects) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.InvalidFieldFault, cn.backend.service.wsdl.login.UnexpectedErrorFault, cn.backend.service.wsdl.login.InvalidIdFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[27]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "upsert"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {externalIDFieldName, sObjects});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.UpsertResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.UpsertResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.UpsertResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidSObjectFault) {
              throw (cn.backend.service.wsdl.login.InvalidSObjectFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidFieldFault) {
              throw (cn.backend.service.wsdl.login.InvalidFieldFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidIdFault) {
              throw (cn.backend.service.wsdl.login.InvalidIdFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.MergeResult[] merge(cn.backend.service.wsdl.login.MergeRequest[] request) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.InvalidFieldFault, cn.backend.service.wsdl.login.UnexpectedErrorFault, cn.backend.service.wsdl.login.InvalidIdFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[28]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "merge"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.MergeResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.MergeResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.MergeResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidSObjectFault) {
              throw (cn.backend.service.wsdl.login.InvalidSObjectFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidFieldFault) {
              throw (cn.backend.service.wsdl.login.InvalidFieldFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidIdFault) {
              throw (cn.backend.service.wsdl.login.InvalidIdFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.DeleteResult[] delete(java.lang.String[] ids) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[29]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "delete"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {ids});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.DeleteResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.DeleteResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.DeleteResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.UndeleteResult[] undelete(java.lang.String[] ids) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[30]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "undelete"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {ids});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.UndeleteResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.UndeleteResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.UndeleteResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.EmptyRecycleBinResult[] emptyRecycleBin(java.lang.String[] ids) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[31]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "emptyRecycleBin"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {ids});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.EmptyRecycleBinResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.EmptyRecycleBinResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.EmptyRecycleBinResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.SObject[] retrieve(java.lang.String fieldList, java.lang.String sObjectType, java.lang.String[] ids) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.InvalidFieldFault, cn.backend.service.wsdl.login.UnexpectedErrorFault, cn.backend.service.wsdl.login.MalformedQueryFault, cn.backend.service.wsdl.login.InvalidIdFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[32]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "retrieve"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {fieldList, sObjectType, ids});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.SObject[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.SObject[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.SObject[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidSObjectFault) {
              throw (cn.backend.service.wsdl.login.InvalidSObjectFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidFieldFault) {
              throw (cn.backend.service.wsdl.login.InvalidFieldFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.MalformedQueryFault) {
              throw (cn.backend.service.wsdl.login.MalformedQueryFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidIdFault) {
              throw (cn.backend.service.wsdl.login.InvalidIdFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.ProcessResult[] process(cn.backend.service.wsdl.login.ProcessRequest[] actions) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault, cn.backend.service.wsdl.login.InvalidIdFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[33]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "process"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {actions});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.ProcessResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.ProcessResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.ProcessResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidIdFault) {
              throw (cn.backend.service.wsdl.login.InvalidIdFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.LeadConvertResult[] convertLead(cn.backend.service.wsdl.login.LeadConvert[] leadConverts) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[34]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "convertLead"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {leadConverts});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.LeadConvertResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.LeadConvertResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.LeadConvertResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void logout() throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[35]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "logout"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.InvalidateSessionsResult[] invalidateSessions(java.lang.String[] sessionIds) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[36]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "invalidateSessions"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sessionIds});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.InvalidateSessionsResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.InvalidateSessionsResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.InvalidateSessionsResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.GetDeletedResult getDeleted(java.lang.String sObjectType, java.util.Calendar startDate, java.util.Calendar endDate) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[37]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "getDeleted"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sObjectType, startDate, endDate});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.GetDeletedResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.GetDeletedResult) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.GetDeletedResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidSObjectFault) {
              throw (cn.backend.service.wsdl.login.InvalidSObjectFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.GetUpdatedResult getUpdated(java.lang.String sObjectType, java.util.Calendar startDate, java.util.Calendar endDate) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[38]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "getUpdated"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sObjectType, startDate, endDate});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.GetUpdatedResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.GetUpdatedResult) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.GetUpdatedResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidSObjectFault) {
              throw (cn.backend.service.wsdl.login.InvalidSObjectFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.QueryResult query(java.lang.String queryString) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.InvalidFieldFault, cn.backend.service.wsdl.login.InvalidQueryLocatorFault, cn.backend.service.wsdl.login.UnexpectedErrorFault, cn.backend.service.wsdl.login.MalformedQueryFault, cn.backend.service.wsdl.login.InvalidIdFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[39]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "query"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {queryString});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.QueryResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.QueryResult) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.QueryResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidSObjectFault) {
              throw (cn.backend.service.wsdl.login.InvalidSObjectFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidFieldFault) {
              throw (cn.backend.service.wsdl.login.InvalidFieldFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidQueryLocatorFault) {
              throw (cn.backend.service.wsdl.login.InvalidQueryLocatorFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.MalformedQueryFault) {
              throw (cn.backend.service.wsdl.login.MalformedQueryFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidIdFault) {
              throw (cn.backend.service.wsdl.login.InvalidIdFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.QueryResult queryAll(java.lang.String queryString) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.InvalidFieldFault, cn.backend.service.wsdl.login.InvalidQueryLocatorFault, cn.backend.service.wsdl.login.UnexpectedErrorFault, cn.backend.service.wsdl.login.MalformedQueryFault, cn.backend.service.wsdl.login.InvalidIdFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[40]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "queryAll"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {queryString});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.QueryResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.QueryResult) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.QueryResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidSObjectFault) {
              throw (cn.backend.service.wsdl.login.InvalidSObjectFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidFieldFault) {
              throw (cn.backend.service.wsdl.login.InvalidFieldFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidQueryLocatorFault) {
              throw (cn.backend.service.wsdl.login.InvalidQueryLocatorFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.MalformedQueryFault) {
              throw (cn.backend.service.wsdl.login.MalformedQueryFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidIdFault) {
              throw (cn.backend.service.wsdl.login.InvalidIdFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.QueryResult queryMore(java.lang.String queryLocator) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidFieldFault, cn.backend.service.wsdl.login.InvalidQueryLocatorFault, cn.backend.service.wsdl.login.UnexpectedErrorFault, cn.backend.service.wsdl.login.MalformedQueryFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[41]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "queryMore"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {queryLocator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.QueryResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.QueryResult) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.QueryResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidFieldFault) {
              throw (cn.backend.service.wsdl.login.InvalidFieldFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidQueryLocatorFault) {
              throw (cn.backend.service.wsdl.login.InvalidQueryLocatorFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.MalformedQueryFault) {
              throw (cn.backend.service.wsdl.login.MalformedQueryFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.SearchResult search(java.lang.String searchString) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.InvalidFieldFault, cn.backend.service.wsdl.login.MalformedSearchFault, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[42]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "search"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {searchString});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.SearchResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.SearchResult) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.SearchResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidSObjectFault) {
              throw (cn.backend.service.wsdl.login.InvalidSObjectFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidFieldFault) {
              throw (cn.backend.service.wsdl.login.InvalidFieldFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.MalformedSearchFault) {
              throw (cn.backend.service.wsdl.login.MalformedSearchFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.GetServerTimestampResult getServerTimestamp() throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[43]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "getServerTimestamp"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.GetServerTimestampResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.GetServerTimestampResult) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.GetServerTimestampResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.SetPasswordResult setPassword(java.lang.String userId, java.lang.String password) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidNewPasswordFault, cn.backend.service.wsdl.login.UnexpectedErrorFault, cn.backend.service.wsdl.login.InvalidIdFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[44]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "setPassword"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userId, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.SetPasswordResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.SetPasswordResult) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.SetPasswordResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidNewPasswordFault) {
              throw (cn.backend.service.wsdl.login.InvalidNewPasswordFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidIdFault) {
              throw (cn.backend.service.wsdl.login.InvalidIdFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.ChangeOwnPasswordResult changeOwnPassword(java.lang.String oldPassword, java.lang.String newPassword) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidNewPasswordFault, cn.backend.service.wsdl.login.UnexpectedErrorFault, cn.backend.service.wsdl.login.InvalidOldPasswordFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[45]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "changeOwnPassword"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {oldPassword, newPassword});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.ChangeOwnPasswordResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.ChangeOwnPasswordResult) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.ChangeOwnPasswordResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidNewPasswordFault) {
              throw (cn.backend.service.wsdl.login.InvalidNewPasswordFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidOldPasswordFault) {
              throw (cn.backend.service.wsdl.login.InvalidOldPasswordFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.ResetPasswordResult resetPassword(java.lang.String userId) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault, cn.backend.service.wsdl.login.InvalidIdFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[46]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "resetPassword"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.ResetPasswordResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.ResetPasswordResult) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.ResetPasswordResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidIdFault) {
              throw (cn.backend.service.wsdl.login.InvalidIdFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.GetUserInfoResult getUserInfo() throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[47]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "getUserInfo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.GetUserInfoResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.GetUserInfoResult) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.GetUserInfoResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.DeleteByExampleResult[] deleteByExample(cn.backend.service.wsdl.login.SObject[] sObjects) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[48]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "deleteByExample"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sObjects});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.DeleteByExampleResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.DeleteByExampleResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.DeleteByExampleResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.SendEmailResult[] sendEmailMessage(java.lang.String[] ids) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[49]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sendEmailMessage"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {ids});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.SendEmailResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.SendEmailResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.SendEmailResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.SendEmailResult[] sendEmail(cn.backend.service.wsdl.login.Email[] messages) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[50]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "sendEmail"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {messages});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.SendEmailResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.SendEmailResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.SendEmailResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.RenderEmailTemplateResult[] renderEmailTemplate(cn.backend.service.wsdl.login.RenderEmailTemplateRequest[] renderRequests) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[51]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "renderEmailTemplate"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {renderRequests});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.RenderEmailTemplateResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.RenderEmailTemplateResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.RenderEmailTemplateResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.RenderStoredEmailTemplateResult renderStoredEmailTemplate(cn.backend.service.wsdl.login.RenderStoredEmailTemplateRequest request) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[52]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "renderStoredEmailTemplate"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.RenderStoredEmailTemplateResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.RenderStoredEmailTemplateResult) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.RenderStoredEmailTemplateResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.PerformQuickActionResult[] performQuickActions(cn.backend.service.wsdl.login.PerformQuickActionRequest[] quickActions) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[53]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "performQuickActions"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {quickActions});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.PerformQuickActionResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.PerformQuickActionResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.PerformQuickActionResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.DescribeQuickActionResult[] describeQuickActions(java.lang.String[] quickActions) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[54]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "describeQuickActions"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {quickActions});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.DescribeQuickActionResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.DescribeQuickActionResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.DescribeQuickActionResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.DescribeQuickActionResult[] describeQuickActionsForRecordType(java.lang.String[] quickActions, java.lang.String recordTypeId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[55]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "describeQuickActionsForRecordType"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {quickActions, recordTypeId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.DescribeQuickActionResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.DescribeQuickActionResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.DescribeQuickActionResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.DescribeAvailableQuickActionResult[] describeAvailableQuickActions(java.lang.String contextType) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[56]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "describeAvailableQuickActions"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {contextType});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.DescribeAvailableQuickActionResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.DescribeAvailableQuickActionResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.DescribeAvailableQuickActionResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.QuickActionTemplateResult[] retrieveQuickActionTemplates(java.lang.String[] quickActionNames, java.lang.String contextId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[57]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "retrieveQuickActionTemplates"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {quickActionNames, contextId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.QuickActionTemplateResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.QuickActionTemplateResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.QuickActionTemplateResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.QuickActionTemplateResult[] retrieveMassQuickActionTemplates(java.lang.String quickActionName, java.lang.String[] contextIds) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[58]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "retrieveMassQuickActionTemplates"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {quickActionName, contextIds});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.QuickActionTemplateResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.QuickActionTemplateResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.QuickActionTemplateResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.DescribeVisualForceResult describeVisualForce(boolean includeAllDetails, java.lang.String namespacePrefix) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[59]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "describeVisualForce"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Boolean(includeAllDetails), namespacePrefix});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.DescribeVisualForceResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.DescribeVisualForceResult) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.DescribeVisualForceResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.FindDuplicatesResult[] findDuplicates(cn.backend.service.wsdl.login.SObject[] sObjects) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.InvalidFieldFault, cn.backend.service.wsdl.login.UnexpectedErrorFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[60]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "findDuplicates"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sObjects});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.FindDuplicatesResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.FindDuplicatesResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.FindDuplicatesResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidSObjectFault) {
              throw (cn.backend.service.wsdl.login.InvalidSObjectFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidFieldFault) {
              throw (cn.backend.service.wsdl.login.InvalidFieldFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.FindDuplicatesResult[] findDuplicatesByIds(java.lang.String[] ids) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault, cn.backend.service.wsdl.login.InvalidIdFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[61]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "findDuplicatesByIds"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {ids});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.FindDuplicatesResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.FindDuplicatesResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.FindDuplicatesResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.UnexpectedErrorFault) {
              throw (cn.backend.service.wsdl.login.UnexpectedErrorFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.backend.service.wsdl.login.InvalidIdFault) {
              throw (cn.backend.service.wsdl.login.InvalidIdFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.backend.service.wsdl.login.DescribeNounResult[] describeNouns(java.lang.String[] nouns, boolean onlyRenamed, boolean includeFields) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[62]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:partner.soap.sforce.com", "describeNouns"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {nouns, new java.lang.Boolean(onlyRenamed), new java.lang.Boolean(includeFields)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.backend.service.wsdl.login.DescribeNounResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.backend.service.wsdl.login.DescribeNounResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, cn.backend.service.wsdl.login.DescribeNounResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
