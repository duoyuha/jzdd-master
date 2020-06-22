/**
 * SforceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.backend.service.wsdl.login;

public interface SforceService extends javax.xml.rpc.Service {

/**
 * Sforce SOAP API
 */
    public java.lang.String getSoapAddress();

    public cn.backend.service.wsdl.login.Soap_PortType getSoap() throws javax.xml.rpc.ServiceException;

    public cn.backend.service.wsdl.login.Soap_PortType getSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
