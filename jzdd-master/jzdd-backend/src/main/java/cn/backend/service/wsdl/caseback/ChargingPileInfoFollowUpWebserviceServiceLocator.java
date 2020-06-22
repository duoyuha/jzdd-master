/**
 * ChargingPileInfoFollowUpWebserviceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.backend.service.wsdl.caseback;

import org.springframework.stereotype.Component;

@Component
public class ChargingPileInfoFollowUpWebserviceServiceLocator extends org.apache.axis.client.Service implements cn.backend.service.wsdl.caseback.ChargingPileInfoFollowUpWebserviceService {

    public ChargingPileInfoFollowUpWebserviceServiceLocator() {
    }


    public ChargingPileInfoFollowUpWebserviceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ChargingPileInfoFollowUpWebserviceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ChargingPileInfoFollowUpWebservice
    private java.lang.String ChargingPileInfoFollowUpWebservice_address = "https://ap6.salesforce.com/services/Soap/class/ChargingPileInfoFollowUpWebservice";

    public java.lang.String getChargingPileInfoFollowUpWebserviceAddress() {
        return ChargingPileInfoFollowUpWebservice_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ChargingPileInfoFollowUpWebserviceWSDDServiceName = "ChargingPileInfoFollowUpWebservice";

    public java.lang.String getChargingPileInfoFollowUpWebserviceWSDDServiceName() {
        return ChargingPileInfoFollowUpWebserviceWSDDServiceName;
    }

    public void setChargingPileInfoFollowUpWebserviceWSDDServiceName(java.lang.String name) {
        ChargingPileInfoFollowUpWebserviceWSDDServiceName = name;
    }

    public cn.backend.service.wsdl.caseback.ChargingPileInfoFollowUpWebservicePortType getChargingPileInfoFollowUpWebservice() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ChargingPileInfoFollowUpWebservice_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getChargingPileInfoFollowUpWebservice(endpoint);
    }

    public cn.backend.service.wsdl.caseback.ChargingPileInfoFollowUpWebservicePortType getChargingPileInfoFollowUpWebservice(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cn.backend.service.wsdl.caseback.ChargingPileInfoFollowUpWebserviceBindingStub _stub = new cn.backend.service.wsdl.caseback.ChargingPileInfoFollowUpWebserviceBindingStub(portAddress, this);
            _stub.setPortName(getChargingPileInfoFollowUpWebserviceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setChargingPileInfoFollowUpWebserviceEndpointAddress(java.lang.String address) {
        ChargingPileInfoFollowUpWebservice_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cn.backend.service.wsdl.caseback.ChargingPileInfoFollowUpWebservicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                cn.backend.service.wsdl.caseback.ChargingPileInfoFollowUpWebserviceBindingStub _stub = new cn.backend.service.wsdl.caseback.ChargingPileInfoFollowUpWebserviceBindingStub(new java.net.URL(ChargingPileInfoFollowUpWebservice_address), this);
                _stub.setPortName(getChargingPileInfoFollowUpWebserviceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ChargingPileInfoFollowUpWebservice".equals(inputPortName)) {
            return getChargingPileInfoFollowUpWebservice();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://soap.sforce.com/schemas/class/ChargingPileInfoFollowUpWebservice", "ChargingPileInfoFollowUpWebserviceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://soap.sforce.com/schemas/class/ChargingPileInfoFollowUpWebservice", "ChargingPileInfoFollowUpWebservice"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ChargingPileInfoFollowUpWebservice".equals(portName)) {
            setChargingPileInfoFollowUpWebserviceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
