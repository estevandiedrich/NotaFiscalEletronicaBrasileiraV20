/*
 * An XML document type.
 * Localname: nfeInutilizacaoNF2Result
 * Namespace: http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2
 * Java type: br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeInutilizacaoNF2ResultDocument
 *
 * Automatically generated - do not modify.
 */
package br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.impl;
/**
 * A document containing one nfeInutilizacaoNF2Result(@http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2) element.
 *
 * This is a complex type.
 */
public class NfeInutilizacaoNF2ResultDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeInutilizacaoNF2ResultDocument
{
    private static final long serialVersionUID = 1L;
    
    public NfeInutilizacaoNF2ResultDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NFEINUTILIZACAONF2RESULT$0 = 
        new javax.xml.namespace.QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2", "nfeInutilizacaoNF2Result");
    
    
    /**
     * Gets the "nfeInutilizacaoNF2Result" element
     */
    public br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeInutilizacaoNF2ResultDocument.NfeInutilizacaoNF2Result getNfeInutilizacaoNF2Result()
    {
        synchronized (monitor())
        {
            check_orphaned();
            br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeInutilizacaoNF2ResultDocument.NfeInutilizacaoNF2Result target = null;
            target = (br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeInutilizacaoNF2ResultDocument.NfeInutilizacaoNF2Result)get_store().find_element_user(NFEINUTILIZACAONF2RESULT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "nfeInutilizacaoNF2Result" element
     */
    public void setNfeInutilizacaoNF2Result(br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeInutilizacaoNF2ResultDocument.NfeInutilizacaoNF2Result nfeInutilizacaoNF2Result)
    {
        synchronized (monitor())
        {
            check_orphaned();
            br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeInutilizacaoNF2ResultDocument.NfeInutilizacaoNF2Result target = null;
            target = (br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeInutilizacaoNF2ResultDocument.NfeInutilizacaoNF2Result)get_store().find_element_user(NFEINUTILIZACAONF2RESULT$0, 0);
            if (target == null)
            {
                target = (br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeInutilizacaoNF2ResultDocument.NfeInutilizacaoNF2Result)get_store().add_element_user(NFEINUTILIZACAONF2RESULT$0);
            }
            target.set(nfeInutilizacaoNF2Result);
        }
    }
    
    /**
     * Appends and returns a new empty "nfeInutilizacaoNF2Result" element
     */
    public br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeInutilizacaoNF2ResultDocument.NfeInutilizacaoNF2Result addNewNfeInutilizacaoNF2Result()
    {
        synchronized (monitor())
        {
            check_orphaned();
            br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeInutilizacaoNF2ResultDocument.NfeInutilizacaoNF2Result target = null;
            target = (br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeInutilizacaoNF2ResultDocument.NfeInutilizacaoNF2Result)get_store().add_element_user(NFEINUTILIZACAONF2RESULT$0);
            return target;
        }
    }
    /**
     * An XML nfeInutilizacaoNF2Result(@http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2).
     *
     * This is a complex type.
     */
    public static class NfeInutilizacaoNF2ResultImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeInutilizacaoNF2ResultDocument.NfeInutilizacaoNF2Result
    {
        private static final long serialVersionUID = 1L;
        
        public NfeInutilizacaoNF2ResultImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        
    }
}
