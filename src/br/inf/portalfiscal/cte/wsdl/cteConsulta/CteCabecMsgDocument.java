/*
 * An XML document type.
 * Localname: cteCabecMsg
 * Namespace: http://www.portalfiscal.inf.br/cte/wsdl/CteConsulta
 * Java type: br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument
 *
 * Automatically generated - do not modify.
 */
package br.inf.portalfiscal.cte.wsdl.cteConsulta;


/**
 * A document containing one cteCabecMsg(@http://www.portalfiscal.inf.br/cte/wsdl/CteConsulta) element.
 *
 * This is a complex type.
 */
public interface CteCabecMsgDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CteCabecMsgDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD024208DCFCAF6B737594BCC6FB07EE3").resolveHandle("ctecabecmsgd92adoctype");
    
    /**
     * Gets the "cteCabecMsg" element
     */
    br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsg getCteCabecMsg();
    
    /**
     * Sets the "cteCabecMsg" element
     */
    void setCteCabecMsg(br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsg cteCabecMsg);
    
    /**
     * Appends and returns a new empty "cteCabecMsg" element
     */
    br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsg addNewCteCabecMsg();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument newInstance() {
          return (br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (br.inf.portalfiscal.cte.wsdl.cteConsulta.CteCabecMsgDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
