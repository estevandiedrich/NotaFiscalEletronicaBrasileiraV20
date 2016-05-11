/*
 * An XML document type.
 * Localname: nfeRetRecepcao2Result
 * Namespace: http://www.portalfiscal.inf.br/nfe/wsdl/NfeRetRecepcao2
 * Java type: br.inf.portalfiscal.nfe.wsdl.nfeRetRecepcao2.NfeRetRecepcao2ResultDocument
 *
 * Automatically generated - do not modify.
 */
package br.inf.portalfiscal.nfe.wsdl.nfeRetRecepcao2.impl;
/**
 * A document containing one nfeRetRecepcao2Result(@http://www.portalfiscal.inf.br/nfe/wsdl/NfeRetRecepcao2) element.
 *
 * This is a complex type.
 */
public class NfeRetRecepcao2ResultDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements br.inf.portalfiscal.nfe.wsdl.nfeRetRecepcao2.NfeRetRecepcao2ResultDocument
{
    private static final long serialVersionUID = 1L;
    
    public NfeRetRecepcao2ResultDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NFERETRECEPCAO2RESULT$0 = 
        new javax.xml.namespace.QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeRetRecepcao2", "nfeRetRecepcao2Result");
    
    
    /**
     * Gets the "nfeRetRecepcao2Result" element
     */
    public br.inf.portalfiscal.nfe.wsdl.nfeRetRecepcao2.NfeRetRecepcao2ResultDocument.NfeRetRecepcao2Result getNfeRetRecepcao2Result()
    {
        synchronized (monitor())
        {
            check_orphaned();
            br.inf.portalfiscal.nfe.wsdl.nfeRetRecepcao2.NfeRetRecepcao2ResultDocument.NfeRetRecepcao2Result target = null;
            target = (br.inf.portalfiscal.nfe.wsdl.nfeRetRecepcao2.NfeRetRecepcao2ResultDocument.NfeRetRecepcao2Result)get_store().find_element_user(NFERETRECEPCAO2RESULT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "nfeRetRecepcao2Result" element
     */
    public void setNfeRetRecepcao2Result(br.inf.portalfiscal.nfe.wsdl.nfeRetRecepcao2.NfeRetRecepcao2ResultDocument.NfeRetRecepcao2Result nfeRetRecepcao2Result)
    {
        synchronized (monitor())
        {
            check_orphaned();
            br.inf.portalfiscal.nfe.wsdl.nfeRetRecepcao2.NfeRetRecepcao2ResultDocument.NfeRetRecepcao2Result target = null;
            target = (br.inf.portalfiscal.nfe.wsdl.nfeRetRecepcao2.NfeRetRecepcao2ResultDocument.NfeRetRecepcao2Result)get_store().find_element_user(NFERETRECEPCAO2RESULT$0, 0);
            if (target == null)
            {
                target = (br.inf.portalfiscal.nfe.wsdl.nfeRetRecepcao2.NfeRetRecepcao2ResultDocument.NfeRetRecepcao2Result)get_store().add_element_user(NFERETRECEPCAO2RESULT$0);
            }
            target.set(nfeRetRecepcao2Result);
        }
    }
    
    /**
     * Appends and returns a new empty "nfeRetRecepcao2Result" element
     */
    public br.inf.portalfiscal.nfe.wsdl.nfeRetRecepcao2.NfeRetRecepcao2ResultDocument.NfeRetRecepcao2Result addNewNfeRetRecepcao2Result()
    {
        synchronized (monitor())
        {
            check_orphaned();
            br.inf.portalfiscal.nfe.wsdl.nfeRetRecepcao2.NfeRetRecepcao2ResultDocument.NfeRetRecepcao2Result target = null;
            target = (br.inf.portalfiscal.nfe.wsdl.nfeRetRecepcao2.NfeRetRecepcao2ResultDocument.NfeRetRecepcao2Result)get_store().add_element_user(NFERETRECEPCAO2RESULT$0);
            return target;
        }
    }
    /**
     * An XML nfeRetRecepcao2Result(@http://www.portalfiscal.inf.br/nfe/wsdl/NfeRetRecepcao2).
     *
     * This is a complex type.
     */
    public static class NfeRetRecepcao2ResultImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements br.inf.portalfiscal.nfe.wsdl.nfeRetRecepcao2.NfeRetRecepcao2ResultDocument.NfeRetRecepcao2Result
    {
        private static final long serialVersionUID = 1L;
        
        public NfeRetRecepcao2ResultImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        
    }
}
