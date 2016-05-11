/*
 * An XML document type.
 * Localname: nfeConsultaNF2Result
 * Namespace: http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2
 * Java type: br.inf.portalfiscal.nfe.wsdl.nfeConsulta2.NfeConsultaNF2ResultDocument
 *
 * Automatically generated - do not modify.
 */
package br.inf.portalfiscal.nfe.wsdl.nfeConsulta2.impl;
/**
 * A document containing one nfeConsultaNF2Result(@http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2) element.
 *
 * This is a complex type.
 */
public class NfeConsultaNF2ResultDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements br.inf.portalfiscal.nfe.wsdl.nfeConsulta2.NfeConsultaNF2ResultDocument
{
    private static final long serialVersionUID = 1L;
    
    public NfeConsultaNF2ResultDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NFECONSULTANF2RESULT$0 = 
        new javax.xml.namespace.QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2", "nfeConsultaNF2Result");
    
    
    /**
     * Gets the "nfeConsultaNF2Result" element
     */
    public br.inf.portalfiscal.nfe.wsdl.nfeConsulta2.NfeConsultaNF2ResultDocument.NfeConsultaNF2Result getNfeConsultaNF2Result()
    {
        synchronized (monitor())
        {
            check_orphaned();
            br.inf.portalfiscal.nfe.wsdl.nfeConsulta2.NfeConsultaNF2ResultDocument.NfeConsultaNF2Result target = null;
            target = (br.inf.portalfiscal.nfe.wsdl.nfeConsulta2.NfeConsultaNF2ResultDocument.NfeConsultaNF2Result)get_store().find_element_user(NFECONSULTANF2RESULT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "nfeConsultaNF2Result" element
     */
    public void setNfeConsultaNF2Result(br.inf.portalfiscal.nfe.wsdl.nfeConsulta2.NfeConsultaNF2ResultDocument.NfeConsultaNF2Result nfeConsultaNF2Result)
    {
        synchronized (monitor())
        {
            check_orphaned();
            br.inf.portalfiscal.nfe.wsdl.nfeConsulta2.NfeConsultaNF2ResultDocument.NfeConsultaNF2Result target = null;
            target = (br.inf.portalfiscal.nfe.wsdl.nfeConsulta2.NfeConsultaNF2ResultDocument.NfeConsultaNF2Result)get_store().find_element_user(NFECONSULTANF2RESULT$0, 0);
            if (target == null)
            {
                target = (br.inf.portalfiscal.nfe.wsdl.nfeConsulta2.NfeConsultaNF2ResultDocument.NfeConsultaNF2Result)get_store().add_element_user(NFECONSULTANF2RESULT$0);
            }
            target.set(nfeConsultaNF2Result);
        }
    }
    
    /**
     * Appends and returns a new empty "nfeConsultaNF2Result" element
     */
    public br.inf.portalfiscal.nfe.wsdl.nfeConsulta2.NfeConsultaNF2ResultDocument.NfeConsultaNF2Result addNewNfeConsultaNF2Result()
    {
        synchronized (monitor())
        {
            check_orphaned();
            br.inf.portalfiscal.nfe.wsdl.nfeConsulta2.NfeConsultaNF2ResultDocument.NfeConsultaNF2Result target = null;
            target = (br.inf.portalfiscal.nfe.wsdl.nfeConsulta2.NfeConsultaNF2ResultDocument.NfeConsultaNF2Result)get_store().add_element_user(NFECONSULTANF2RESULT$0);
            return target;
        }
    }
    /**
     * An XML nfeConsultaNF2Result(@http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2).
     *
     * This is a complex type.
     */
    public static class NfeConsultaNF2ResultImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements br.inf.portalfiscal.nfe.wsdl.nfeConsulta2.NfeConsultaNF2ResultDocument.NfeConsultaNF2Result
    {
        private static final long serialVersionUID = 1L;
        
        public NfeConsultaNF2ResultImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        
    }
}
