/**
 * UportalServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package org.esupportail.portal.ws.client.axis;

public class UportalServiceSoapBindingStub extends org.apache.axis.client.Stub implements org.esupportail.portal.ws.client.axis.UportalService {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[15];
        _initOperationDesc1();
        _initOperationDesc2();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getRootGroup");
        oper.setReturnType(new javax.xml.namespace.QName("UportalService", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getRootGroupReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getUser");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("UportalService", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getUserReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("UportalService", "fault"),
                      "org.esupportail.portal.ws.client.axis.UportalServiceErrorException",
                      new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"), 
                      false
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("UportalService", "fault"),
                      "org.esupportail.portal.ws.client.axis.UportalServiceUserNotFoundException",
                      new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"), 
                      false
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("searchUsers");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("UportalService", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "searchUsersReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("UportalService", "fault"),
                      "org.esupportail.portal.ws.client.axis.UportalServiceErrorException",
                      new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"), 
                      false
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getGroupById");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("UportalService", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getGroupByIdReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("UportalService", "fault"),
                      "org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException",
                      new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"), 
                      false
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("UportalService", "fault"),
                      "org.esupportail.portal.ws.client.axis.UportalServiceErrorException",
                      new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"), 
                      false
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getGroupByName");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("UportalService", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getGroupByNameReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("UportalService", "fault"),
                      "org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException",
                      new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"), 
                      false
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("UportalService", "fault"),
                      "org.esupportail.portal.ws.client.axis.UportalServiceErrorException",
                      new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"), 
                      false
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("searchGroupsByName");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("UportalService", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "searchGroupsByNameReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("UportalService", "fault"),
                      "org.esupportail.portal.ws.client.axis.UportalServiceErrorException",
                      new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"), 
                      false
                     ));
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getSubGroupsById");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("UportalService", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getSubGroupsByIdReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("UportalService", "fault"),
                      "org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException",
                      new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"), 
                      false
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("UportalService", "fault"),
                      "org.esupportail.portal.ws.client.axis.UportalServiceErrorException",
                      new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"), 
                      false
                     ));
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getSubGroupsByName");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("UportalService", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getSubGroupsByNameReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("UportalService", "fault"),
                      "org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException",
                      new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"), 
                      false
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("UportalService", "fault"),
                      "org.esupportail.portal.ws.client.axis.UportalServiceErrorException",
                      new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"), 
                      false
                     ));
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getContainingGroupsById");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("UportalService", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getContainingGroupsByIdReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("UportalService", "fault"),
                      "org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException",
                      new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"), 
                      false
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("UportalService", "fault"),
                      "org.esupportail.portal.ws.client.axis.UportalServiceErrorException",
                      new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"), 
                      false
                     ));
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getContainingGroupsByName");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("UportalService", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getContainingGroupsByNameReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("UportalService", "fault"),
                      "org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException",
                      new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"), 
                      false
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("UportalService", "fault"),
                      "org.esupportail.portal.ws.client.axis.UportalServiceErrorException",
                      new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"), 
                      false
                     ));
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getGroupHierarchy");
        oper.setReturnType(new javax.xml.namespace.QName("UportalService", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getGroupHierarchyReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("UportalService", "fault"),
                      "org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException",
                      new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"), 
                      false
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("UportalService", "fault"),
                      "org.esupportail.portal.ws.client.axis.UportalServiceErrorException",
                      new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"), 
                      false
                     ));
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getGroupHierarchyById");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("UportalService", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getGroupHierarchyByIdReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("UportalService", "fault"),
                      "org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException",
                      new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"), 
                      false
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("UportalService", "fault"),
                      "org.esupportail.portal.ws.client.axis.UportalServiceErrorException",
                      new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"), 
                      false
                     ));
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getGroupHierarchyByName");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("UportalService", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getGroupHierarchyByNameReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("UportalService", "fault"),
                      "org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException",
                      new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"), 
                      false
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("UportalService", "fault"),
                      "org.esupportail.portal.ws.client.axis.UportalServiceErrorException",
                      new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"), 
                      false
                     ));
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getUserGroups");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("UportalService", "ArrayOf_xsd_anyType"));
        oper.setReturnClass(java.lang.Object[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getUserGroupsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("UportalService", "fault"),
                      "org.esupportail.portal.ws.client.axis.UportalServiceErrorException",
                      new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"), 
                      false
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("UportalService", "fault"),
                      "org.esupportail.portal.ws.client.axis.UportalServiceUserNotFoundException",
                      new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"), 
                      false
                     ));
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("isUserMemberOfGroup");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "isUserMemberOfGroupReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("UportalService", "fault"),
                      "org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException",
                      new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"), 
                      false
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("UportalService", "fault"),
                      "org.esupportail.portal.ws.client.axis.UportalServiceErrorException",
                      new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"), 
                      false
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("UportalService", "fault"),
                      "org.esupportail.portal.ws.client.axis.UportalServiceUserNotFoundException",
                      new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"), 
                      false
                     ));
        _operations[14] = oper;

    }

    public UportalServiceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public UportalServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public UportalServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("UportalService", "ArrayOf_xsd_anyType");
            cachedSerQNames.add(qName);
            cls = java.lang.Object[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

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
                    _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
                    _call.setEncodingStyle(org.apache.axis.Constants.URI_SOAP11_ENC);
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

    public java.lang.Object[] getRootGroup() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("UportalService", "getRootGroup"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Object[] getUser(java.lang.String in0) throws java.rmi.RemoteException, org.esupportail.portal.ws.client.axis.UportalServiceErrorException, org.esupportail.portal.ws.client.axis.UportalServiceUserNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("UportalService", "getUser"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.esupportail.portal.ws.client.axis.UportalServiceErrorException) {
              throw (org.esupportail.portal.ws.client.axis.UportalServiceErrorException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.esupportail.portal.ws.client.axis.UportalServiceUserNotFoundException) {
              throw (org.esupportail.portal.ws.client.axis.UportalServiceUserNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.Object[] searchUsers(java.lang.String in0) throws java.rmi.RemoteException, org.esupportail.portal.ws.client.axis.UportalServiceErrorException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("UportalService", "searchUsers"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.esupportail.portal.ws.client.axis.UportalServiceErrorException) {
              throw (org.esupportail.portal.ws.client.axis.UportalServiceErrorException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.Object[] getGroupById(java.lang.String in0) throws java.rmi.RemoteException, org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException, org.esupportail.portal.ws.client.axis.UportalServiceErrorException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("UportalService", "getGroupById"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException) {
              throw (org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.esupportail.portal.ws.client.axis.UportalServiceErrorException) {
              throw (org.esupportail.portal.ws.client.axis.UportalServiceErrorException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.Object[] getGroupByName(java.lang.String in0) throws java.rmi.RemoteException, org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException, org.esupportail.portal.ws.client.axis.UportalServiceErrorException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("UportalService", "getGroupByName"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException) {
              throw (org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.esupportail.portal.ws.client.axis.UportalServiceErrorException) {
              throw (org.esupportail.portal.ws.client.axis.UportalServiceErrorException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.Object[] searchGroupsByName(java.lang.String in0) throws java.rmi.RemoteException, org.esupportail.portal.ws.client.axis.UportalServiceErrorException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("UportalService", "searchGroupsByName"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.esupportail.portal.ws.client.axis.UportalServiceErrorException) {
              throw (org.esupportail.portal.ws.client.axis.UportalServiceErrorException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.Object[] getSubGroupsById(java.lang.String in0) throws java.rmi.RemoteException, org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException, org.esupportail.portal.ws.client.axis.UportalServiceErrorException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("UportalService", "getSubGroupsById"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException) {
              throw (org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.esupportail.portal.ws.client.axis.UportalServiceErrorException) {
              throw (org.esupportail.portal.ws.client.axis.UportalServiceErrorException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.Object[] getSubGroupsByName(java.lang.String in0) throws java.rmi.RemoteException, org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException, org.esupportail.portal.ws.client.axis.UportalServiceErrorException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("UportalService", "getSubGroupsByName"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException) {
              throw (org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.esupportail.portal.ws.client.axis.UportalServiceErrorException) {
              throw (org.esupportail.portal.ws.client.axis.UportalServiceErrorException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.Object[] getContainingGroupsById(java.lang.String in0) throws java.rmi.RemoteException, org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException, org.esupportail.portal.ws.client.axis.UportalServiceErrorException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("UportalService", "getContainingGroupsById"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException) {
              throw (org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.esupportail.portal.ws.client.axis.UportalServiceErrorException) {
              throw (org.esupportail.portal.ws.client.axis.UportalServiceErrorException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.Object[] getContainingGroupsByName(java.lang.String in0) throws java.rmi.RemoteException, org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException, org.esupportail.portal.ws.client.axis.UportalServiceErrorException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("UportalService", "getContainingGroupsByName"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException) {
              throw (org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.esupportail.portal.ws.client.axis.UportalServiceErrorException) {
              throw (org.esupportail.portal.ws.client.axis.UportalServiceErrorException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.Object[] getGroupHierarchy() throws java.rmi.RemoteException, org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException, org.esupportail.portal.ws.client.axis.UportalServiceErrorException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("UportalService", "getGroupHierarchy"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException) {
              throw (org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.esupportail.portal.ws.client.axis.UportalServiceErrorException) {
              throw (org.esupportail.portal.ws.client.axis.UportalServiceErrorException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.Object[] getGroupHierarchyById(java.lang.String in0) throws java.rmi.RemoteException, org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException, org.esupportail.portal.ws.client.axis.UportalServiceErrorException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("UportalService", "getGroupHierarchyById"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException) {
              throw (org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.esupportail.portal.ws.client.axis.UportalServiceErrorException) {
              throw (org.esupportail.portal.ws.client.axis.UportalServiceErrorException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.Object[] getGroupHierarchyByName(java.lang.String in0) throws java.rmi.RemoteException, org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException, org.esupportail.portal.ws.client.axis.UportalServiceErrorException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("UportalService", "getGroupHierarchyByName"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException) {
              throw (org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.esupportail.portal.ws.client.axis.UportalServiceErrorException) {
              throw (org.esupportail.portal.ws.client.axis.UportalServiceErrorException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.Object[] getUserGroups(java.lang.String in0) throws java.rmi.RemoteException, org.esupportail.portal.ws.client.axis.UportalServiceErrorException, org.esupportail.portal.ws.client.axis.UportalServiceUserNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("UportalService", "getUserGroups"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.esupportail.portal.ws.client.axis.UportalServiceErrorException) {
              throw (org.esupportail.portal.ws.client.axis.UportalServiceErrorException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.esupportail.portal.ws.client.axis.UportalServiceUserNotFoundException) {
              throw (org.esupportail.portal.ws.client.axis.UportalServiceUserNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean isUserMemberOfGroup(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException, org.esupportail.portal.ws.client.axis.UportalServiceErrorException, org.esupportail.portal.ws.client.axis.UportalServiceUserNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("UportalService", "isUserMemberOfGroup"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException) {
              throw (org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.esupportail.portal.ws.client.axis.UportalServiceErrorException) {
              throw (org.esupportail.portal.ws.client.axis.UportalServiceErrorException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.esupportail.portal.ws.client.axis.UportalServiceUserNotFoundException) {
              throw (org.esupportail.portal.ws.client.axis.UportalServiceUserNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
