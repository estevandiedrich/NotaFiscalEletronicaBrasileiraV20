/*
 * An XML document type.
 * Localname: nfeStatusServicoNF2Result
 * Namespace: http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico2
 * Java type: br.inf.portalfiscal.nfe.wsdl.nfeStatusServico2.NfeStatusServicoNF2ResultDocument
 *
 * Automatically generated - do not modify.
 */
package br.inf.portalfiscal.nfe.wsdl.nfeStatusServico2.impl;
/**
 * A document containing one nfeStatusServicoNF2Result(@http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico2) element.
 *
 * This is a complex type.
 */
public class NfeStatusServicoNF2ResultDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements br.inf.portalfiscal.nfe.wsdl.nfeStatusServico2.NfeStatusServicoNF2ResultDocument
{
    private static final long serialVersionUID = 1L;
    
    public NfeStatusServicoNF2ResultDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NFESTATUSSERVICONF2RESULT$0 = 
        new javax.xml.namespace.QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico2", "nfeStatusServicoNF2Result");
    
    
    /**
     * Gets the "nfeStatusServicoNF2Result" element
     */
    public br.inf.portalfiscal.nfe.wsdl.nfeStatusServico2.NfeStatusServicoNF2ResultDocument.NfeStatusServicoNF2Result getNfeStatusServicoNF2Result()
    {
        synchronized (monitor())
        {
            check_orphaned();
            br.inf.portalfiscal.nfe.wsdl.nfeStatusServico2.NfeStatusServicoNF2ResultDocument.NfeStatusServicoNF2Result target = null;
            target = (br.inf.portalfiscal.nfe.wsdl.nfeStatusServico2.NfeStatusServicoNF2ResultDocument.NfeStatusServicoNF2Result)get_store().find_element_user(NFESTATUSSERVICONF2RESULT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "nfeStatusServicoNF2Result" element
     */
    public void setNfeStatusServicoNF2Result(br.inf.portalfiscal.nfe.wsdl.nfeStatusServico2.NfeStatusServicoNF2ResultDocument.NfeStatusServicoNF2Result nfeStatusServicoNF2Result)
    {
        synchronized (monitor())
        {
            check_orphaned();
            br.inf.portalfiscal.nfe.wsdl.nfeStatusServico2.NfeStatusServicoNF2ResultDocument.NfeStatusServicoNF2Result target = null;
            target = (br.inf.portalfiscal.nfe.wsdl.nfeStatusServico2.NfeStatusServicoNF2ResultDocument.NfeStatusServicoNF2Result)get_store().find_element_user(NFESTATUSSERVICONF2RESULT$0, 0);
            if (target == null)
            {
                target = (br.inf.portalfiscal.nfe.wsdl.nfeStatusServico2.NfeStatusServicoNF2ResultDocument.NfeStatusServicoNF2Result)get_store().add_element_user(NFESTATUSSERVICONF2RESULT$0);
            }
            target.set(nfeStatusServicoNF2Result);
        }
    }
    
    /**
     * Appends and returns a new empty "nfeStatusServicoNF2Result" element
     */
    public br.inf.portalfiscal.nfe.wsdl.nfeStatusServico2.NfeStatusServicoNF2ResultDocument.NfeStatusServicoNF2Result addNewNfeStatusServicoNF2Result()
    {
        synchronized (monitor())
        {
            check_orphaned();
            br.inf.portalfiscal.nfe.wsdl.nfeStatusServico2.NfeStatusServicoNF2ResultDocument.NfeStatusServicoNF2Result target = null;
            target = (br.inf.portalfiscal.nfe.wsdl.nfeStatusServico2.NfeStatusServicoNF2ResultDocument.NfeStatusServicoNF2Result)get_store().add_element_user(NFESTATUSSERVICONF2RESULT$0);
            return target;
        }
    }
    /**
     * An XML nfeStatusServicoNF2Result(@http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico2).
     *
     * This is a complex type.
     */
    public static class NfeStatusServicoNF2ResultImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements br.inf.portalfiscal.nfe.wsdl.nfeStatusServico2.NfeStatusServicoNF2ResultDocument.NfeStatusServicoNF2Result
    {
        private static final long serialVersionUID = 1L;
        
        public NfeStatusServicoNF2ResultImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        
    }
}
