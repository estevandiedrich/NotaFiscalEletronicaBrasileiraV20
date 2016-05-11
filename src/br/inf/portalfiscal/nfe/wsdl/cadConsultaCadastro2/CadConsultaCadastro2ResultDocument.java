/*
 * An XML document type.
 * Localname: cadConsultaCadastro2Result
 * Namespace: http://www.portalfiscal.inf.br/nfe/wsdl/CadConsultaCadastro2
 * Java type: br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument
 *
 * Automatically generated - do not modify.
 */
package br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2;


/**
 * A document containing one cadConsultaCadastro2Result(@http://www.portalfiscal.inf.br/nfe/wsdl/CadConsultaCadastro2) element.
 *
 * This is a complex type.
 */
public interface CadConsultaCadastro2ResultDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CadConsultaCadastro2ResultDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sBBF0D56A22EC4B6993F56DFD7FD40614").resolveHandle("cadconsultacadastro2resultd09cdoctype");
    
    /**
     * Gets the "cadConsultaCadastro2Result" element
     */
    br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument.CadConsultaCadastro2Result getCadConsultaCadastro2Result();
    
    /**
     * Sets the "cadConsultaCadastro2Result" element
     */
    void setCadConsultaCadastro2Result(br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument.CadConsultaCadastro2Result cadConsultaCadastro2Result);
    
    /**
     * Appends and returns a new empty "cadConsultaCadastro2Result" element
     */
    br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument.CadConsultaCadastro2Result addNewCadConsultaCadastro2Result();
    
    /**
     * An XML cadConsultaCadastro2Result(@http://www.portalfiscal.inf.br/nfe/wsdl/CadConsultaCadastro2).
     *
     * This is a complex type.
     */
    public interface CadConsultaCadastro2Result extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CadConsultaCadastro2Result.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sBBF0D56A22EC4B6993F56DFD7FD40614").resolveHandle("cadconsultacadastro2result33b3elemtype");
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument.CadConsultaCadastro2Result newInstance() {
              return (br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument.CadConsultaCadastro2Result) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument.CadConsultaCadastro2Result newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument.CadConsultaCadastro2Result) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument newInstance() {
          return (br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.CadConsultaCadastro2ResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
