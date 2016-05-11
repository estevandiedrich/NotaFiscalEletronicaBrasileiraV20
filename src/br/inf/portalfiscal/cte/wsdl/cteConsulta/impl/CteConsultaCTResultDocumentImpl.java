/*
 * An XML document type.
 * Localname: cteConsultaCTResult
 * Namespace: http://www.portalfiscal.inf.br/cte/wsdl/CteConsulta
 * Java type: br.inf.portalfiscal.cte.wsdl.cteConsulta.CteConsultaCTResultDocument
 *
 * Automatically generated - do not modify.
 */
package br.inf.portalfiscal.cte.wsdl.cteConsulta.impl;
/**
 * A document containing one cteConsultaCTResult(@http://www.portalfiscal.inf.br/cte/wsdl/CteConsulta) element.
 *
 * This is a complex type.
 */
public class CteConsultaCTResultDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements br.inf.portalfiscal.cte.wsdl.cteConsulta.CteConsultaCTResultDocument
{
    private static final long serialVersionUID = 1L;
    
    public CteConsultaCTResultDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CTECONSULTACTRESULT$0 = 
        new javax.xml.namespace.QName("http://www.portalfiscal.inf.br/cte/wsdl/CteConsulta", "cteConsultaCTResult");
    
    
    /**
     * Gets the "cteConsultaCTResult" element
     */
    public br.inf.portalfiscal.cte.wsdl.cteConsulta.CteConsultaCTResultDocument.CteConsultaCTResult getCteConsultaCTResult()
    {
        synchronized (monitor())
        {
            check_orphaned();
            br.inf.portalfiscal.cte.wsdl.cteConsulta.CteConsultaCTResultDocument.CteConsultaCTResult target = null;
            target = (br.inf.portalfiscal.cte.wsdl.cteConsulta.CteConsultaCTResultDocument.CteConsultaCTResult)get_store().find_element_user(CTECONSULTACTRESULT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "cteConsultaCTResult" element
     */
    public void setCteConsultaCTResult(br.inf.portalfiscal.cte.wsdl.cteConsulta.CteConsultaCTResultDocument.CteConsultaCTResult cteConsultaCTResult)
    {
        synchronized (monitor())
        {
            check_orphaned();
            br.inf.portalfiscal.cte.wsdl.cteConsulta.CteConsultaCTResultDocument.CteConsultaCTResult target = null;
            target = (br.inf.portalfiscal.cte.wsdl.cteConsulta.CteConsultaCTResultDocument.CteConsultaCTResult)get_store().find_element_user(CTECONSULTACTRESULT$0, 0);
            if (target == null)
            {
                target = (br.inf.portalfiscal.cte.wsdl.cteConsulta.CteConsultaCTResultDocument.CteConsultaCTResult)get_store().add_element_user(CTECONSULTACTRESULT$0);
            }
            target.set(cteConsultaCTResult);
        }
    }
    
    /**
     * Appends and returns a new empty "cteConsultaCTResult" element
     */
    public br.inf.portalfiscal.cte.wsdl.cteConsulta.CteConsultaCTResultDocument.CteConsultaCTResult addNewCteConsultaCTResult()
    {
        synchronized (monitor())
        {
            check_orphaned();
            br.inf.portalfiscal.cte.wsdl.cteConsulta.CteConsultaCTResultDocument.CteConsultaCTResult target = null;
            target = (br.inf.portalfiscal.cte.wsdl.cteConsulta.CteConsultaCTResultDocument.CteConsultaCTResult)get_store().add_element_user(CTECONSULTACTRESULT$0);
            return target;
        }
    }
    /**
     * An XML cteConsultaCTResult(@http://www.portalfiscal.inf.br/cte/wsdl/CteConsulta).
     *
     * This is a complex type.
     */
    public static class CteConsultaCTResultImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements br.inf.portalfiscal.cte.wsdl.cteConsulta.CteConsultaCTResultDocument.CteConsultaCTResult
    {
        private static final long serialVersionUID = 1L;
        
        public CteConsultaCTResultImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        
    }
}
