/*
 * An XML document type.
 * Localname: sceCabecMsg
 * Namespace: http://www.portalfiscal.inf.br/nfe/wsdl/SCEConsultaRFB
 * Java type: br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsgDocument
 *
 * Automatically generated - do not modify.
 */
package br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.impl;
/**
 * A document containing one sceCabecMsg(@http://www.portalfiscal.inf.br/nfe/wsdl/SCEConsultaRFB) element.
 *
 * This is a complex type.
 */
public class SceCabecMsgDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsgDocument
{
    private static final long serialVersionUID = 1L;
    
    public SceCabecMsgDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SCECABECMSG$0 = 
        new javax.xml.namespace.QName("http://www.portalfiscal.inf.br/nfe/wsdl/SCEConsultaRFB", "sceCabecMsg");
    
    
    /**
     * Gets the "sceCabecMsg" element
     */
    public br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg getSceCabecMsg()
    {
        synchronized (monitor())
        {
            check_orphaned();
            br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg target = null;
            target = (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg)get_store().find_element_user(SCECABECMSG$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "sceCabecMsg" element
     */
    public void setSceCabecMsg(br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg sceCabecMsg)
    {
        synchronized (monitor())
        {
            check_orphaned();
            br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg target = null;
            target = (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg)get_store().find_element_user(SCECABECMSG$0, 0);
            if (target == null)
            {
                target = (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg)get_store().add_element_user(SCECABECMSG$0);
            }
            target.set(sceCabecMsg);
        }
    }
    
    /**
     * Appends and returns a new empty "sceCabecMsg" element
     */
    public br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg addNewSceCabecMsg()
    {
        synchronized (monitor())
        {
            check_orphaned();
            br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg target = null;
            target = (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg)get_store().add_element_user(SCECABECMSG$0);
            return target;
        }
    }
}
