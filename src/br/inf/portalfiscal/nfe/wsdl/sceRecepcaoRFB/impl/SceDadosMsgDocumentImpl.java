/*
 * An XML document type.
 * Localname: sceDadosMsg
 * Namespace: http://www.portalfiscal.inf.br/nfe/wsdl/SCERecepcaoRFB
 * Java type: br.inf.portalfiscal.nfe.wsdl.sceRecepcaoRFB.SceDadosMsgDocument
 *
 * Automatically generated - do not modify.
 */
package br.inf.portalfiscal.nfe.wsdl.sceRecepcaoRFB.impl;
/**
 * A document containing one sceDadosMsg(@http://www.portalfiscal.inf.br/nfe/wsdl/SCERecepcaoRFB) element.
 *
 * This is a complex type.
 */
public class SceDadosMsgDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements br.inf.portalfiscal.nfe.wsdl.sceRecepcaoRFB.SceDadosMsgDocument
{
    private static final long serialVersionUID = 1L;
    
    public SceDadosMsgDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SCEDADOSMSG$0 = 
        new javax.xml.namespace.QName("http://www.portalfiscal.inf.br/nfe/wsdl/SCERecepcaoRFB", "sceDadosMsg");
    
    
    /**
     * Gets the "sceDadosMsg" element
     */
    public br.inf.portalfiscal.nfe.wsdl.sceRecepcaoRFB.SceDadosMsgDocument.SceDadosMsg getSceDadosMsg()
    {
        synchronized (monitor())
        {
            check_orphaned();
            br.inf.portalfiscal.nfe.wsdl.sceRecepcaoRFB.SceDadosMsgDocument.SceDadosMsg target = null;
            target = (br.inf.portalfiscal.nfe.wsdl.sceRecepcaoRFB.SceDadosMsgDocument.SceDadosMsg)get_store().find_element_user(SCEDADOSMSG$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "sceDadosMsg" element
     */
    public void setSceDadosMsg(br.inf.portalfiscal.nfe.wsdl.sceRecepcaoRFB.SceDadosMsgDocument.SceDadosMsg sceDadosMsg)
    {
        synchronized (monitor())
        {
            check_orphaned();
            br.inf.portalfiscal.nfe.wsdl.sceRecepcaoRFB.SceDadosMsgDocument.SceDadosMsg target = null;
            target = (br.inf.portalfiscal.nfe.wsdl.sceRecepcaoRFB.SceDadosMsgDocument.SceDadosMsg)get_store().find_element_user(SCEDADOSMSG$0, 0);
            if (target == null)
            {
                target = (br.inf.portalfiscal.nfe.wsdl.sceRecepcaoRFB.SceDadosMsgDocument.SceDadosMsg)get_store().add_element_user(SCEDADOSMSG$0);
            }
            target.set(sceDadosMsg);
        }
    }
    
    /**
     * Appends and returns a new empty "sceDadosMsg" element
     */
    public br.inf.portalfiscal.nfe.wsdl.sceRecepcaoRFB.SceDadosMsgDocument.SceDadosMsg addNewSceDadosMsg()
    {
        synchronized (monitor())
        {
            check_orphaned();
            br.inf.portalfiscal.nfe.wsdl.sceRecepcaoRFB.SceDadosMsgDocument.SceDadosMsg target = null;
            target = (br.inf.portalfiscal.nfe.wsdl.sceRecepcaoRFB.SceDadosMsgDocument.SceDadosMsg)get_store().add_element_user(SCEDADOSMSG$0);
            return target;
        }
    }
    /**
     * An XML sceDadosMsg(@http://www.portalfiscal.inf.br/nfe/wsdl/SCERecepcaoRFB).
     *
     * This is a complex type.
     */
    public static class SceDadosMsgImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements br.inf.portalfiscal.nfe.wsdl.sceRecepcaoRFB.SceDadosMsgDocument.SceDadosMsg
    {
        private static final long serialVersionUID = 1L;
        
        public SceDadosMsgImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        
    }
}
