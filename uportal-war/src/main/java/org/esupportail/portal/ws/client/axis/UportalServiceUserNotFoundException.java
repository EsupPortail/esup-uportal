/**
 * UportalServiceUserNotFoundException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package org.esupportail.portal.ws.client.axis;

public class UportalServiceUserNotFoundException extends org.apache.axis.AxisFault {
    public java.lang.Object fault;
    public java.lang.Object getFault() {
        return this.fault;
    }

    public UportalServiceUserNotFoundException() {
    }

    public UportalServiceUserNotFoundException(java.lang.Exception target) {
        super(target);
    }

    public UportalServiceUserNotFoundException(java.lang.String message, java.lang.Throwable t) {
        super(message, t);
    }

      public UportalServiceUserNotFoundException(java.lang.Object fault) {
        this.fault = fault;
    }

    /**
     * Writes the exception data to the faultDetails
     */
    public void writeDetails(javax.xml.namespace.QName qname, org.apache.axis.encoding.SerializationContext context) throws java.io.IOException {
        context.serialize(qname, null, fault);
    }
}
