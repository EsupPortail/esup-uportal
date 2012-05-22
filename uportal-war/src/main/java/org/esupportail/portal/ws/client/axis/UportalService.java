/**
 * UportalService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package org.esupportail.portal.ws.client.axis;

public interface UportalService extends java.rmi.Remote {
    public java.lang.Object[] getRootGroup() throws java.rmi.RemoteException;
    public java.lang.Object[] getUser(java.lang.String in0) throws java.rmi.RemoteException, org.esupportail.portal.ws.client.axis.UportalServiceErrorException, org.esupportail.portal.ws.client.axis.UportalServiceUserNotFoundException;
    public java.lang.Object[] searchUsers(java.lang.String in0) throws java.rmi.RemoteException, org.esupportail.portal.ws.client.axis.UportalServiceErrorException;
    public java.lang.Object[] getGroupById(java.lang.String in0) throws java.rmi.RemoteException, org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException, org.esupportail.portal.ws.client.axis.UportalServiceErrorException;
    public java.lang.Object[] getGroupByName(java.lang.String in0) throws java.rmi.RemoteException, org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException, org.esupportail.portal.ws.client.axis.UportalServiceErrorException;
    public java.lang.Object[] searchGroupsByName(java.lang.String in0) throws java.rmi.RemoteException, org.esupportail.portal.ws.client.axis.UportalServiceErrorException;
    public java.lang.Object[] getSubGroupsById(java.lang.String in0) throws java.rmi.RemoteException, org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException, org.esupportail.portal.ws.client.axis.UportalServiceErrorException;
    public java.lang.Object[] getSubGroupsByName(java.lang.String in0) throws java.rmi.RemoteException, org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException, org.esupportail.portal.ws.client.axis.UportalServiceErrorException;
    public java.lang.Object[] getContainingGroupsById(java.lang.String in0) throws java.rmi.RemoteException, org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException, org.esupportail.portal.ws.client.axis.UportalServiceErrorException;
    public java.lang.Object[] getContainingGroupsByName(java.lang.String in0) throws java.rmi.RemoteException, org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException, org.esupportail.portal.ws.client.axis.UportalServiceErrorException;
    public java.lang.Object[] getGroupHierarchy() throws java.rmi.RemoteException, org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException, org.esupportail.portal.ws.client.axis.UportalServiceErrorException;
    public java.lang.Object[] getGroupHierarchyById(java.lang.String in0) throws java.rmi.RemoteException, org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException, org.esupportail.portal.ws.client.axis.UportalServiceErrorException;
    public java.lang.Object[] getGroupHierarchyByName(java.lang.String in0) throws java.rmi.RemoteException, org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException, org.esupportail.portal.ws.client.axis.UportalServiceErrorException;
    public java.lang.Object[] getUserGroups(java.lang.String in0) throws java.rmi.RemoteException, org.esupportail.portal.ws.client.axis.UportalServiceErrorException, org.esupportail.portal.ws.client.axis.UportalServiceUserNotFoundException;
    public boolean isUserMemberOfGroup(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, org.esupportail.portal.ws.client.axis.UportalServiceGroupNotFoundException, org.esupportail.portal.ws.client.axis.UportalServiceErrorException, org.esupportail.portal.ws.client.axis.UportalServiceUserNotFoundException;
}
