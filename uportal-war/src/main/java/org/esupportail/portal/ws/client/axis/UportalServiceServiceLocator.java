/**
 * UportalServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package org.esupportail.portal.ws.client.axis;

public class UportalServiceServiceLocator extends org.apache.axis.client.Service implements org.esupportail.portal.ws.client.axis.UportalServiceService {

    public UportalServiceServiceLocator() {
    }


    public UportalServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public UportalServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for UportalService
    private java.lang.String UportalService_address = "http://UportalService";

    public java.lang.String getUportalServiceAddress() {
        return UportalService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String UportalServiceWSDDServiceName = "UportalService";

    public java.lang.String getUportalServiceWSDDServiceName() {
        return UportalServiceWSDDServiceName;
    }

    public void setUportalServiceWSDDServiceName(java.lang.String name) {
        UportalServiceWSDDServiceName = name;
    }

    public org.esupportail.portal.ws.client.axis.UportalService getUportalService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(UportalService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getUportalService(endpoint);
    }

    public org.esupportail.portal.ws.client.axis.UportalService getUportalService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.esupportail.portal.ws.client.axis.UportalServiceSoapBindingStub _stub = new org.esupportail.portal.ws.client.axis.UportalServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getUportalServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setUportalServiceEndpointAddress(java.lang.String address) {
        UportalService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.esupportail.portal.ws.client.axis.UportalService.class.isAssignableFrom(serviceEndpointInterface)) {
                org.esupportail.portal.ws.client.axis.UportalServiceSoapBindingStub _stub = new org.esupportail.portal.ws.client.axis.UportalServiceSoapBindingStub(new java.net.URL(UportalService_address), this);
                _stub.setPortName(getUportalServiceWSDDServiceName());
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
        if ("UportalService".equals(inputPortName)) {
            return getUportalService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("UportalService", "UportalServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("UportalService", "UportalService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("UportalService".equals(portName)) {
            setUportalServiceEndpointAddress(address);
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
