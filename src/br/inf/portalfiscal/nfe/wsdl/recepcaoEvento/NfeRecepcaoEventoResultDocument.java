/*
 * An XML document type.
 * Localname: nfeRecepcaoEventoResult
 * Namespace: http://www.portalfiscal.inf.br/nfe/wsdl/RecepcaoEvento
 * Java type: br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument
 *
 * Automatically generated - do not modify.
 */
package br.inf.portalfiscal.nfe.wsdl.recepcaoEvento;


/**
 * A document containing one nfeRecepcaoEventoResult(@http://www.portalfiscal.inf.br/nfe/wsdl/RecepcaoEvento) element.
 *
 * This is a complex type.
 */
public interface NfeRecepcaoEventoResultDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(NfeRecepcaoEventoResultDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sC45E20710C64A6EA1EE6174BA89A6175").resolveHandle("nferecepcaoeventoresult1661doctype");
    
    /**
     * Gets the "nfeRecepcaoEventoResult" element
     */
    br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument.NfeRecepcaoEventoResult getNfeRecepcaoEventoResult();
    
    /**
     * Sets the "nfeRecepcaoEventoResult" element
     */
    void setNfeRecepcaoEventoResult(br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument.NfeRecepcaoEventoResult nfeRecepcaoEventoResult);
    
    /**
     * Appends and returns a new empty "nfeRecepcaoEventoResult" element
     */
    br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument.NfeRecepcaoEventoResult addNewNfeRecepcaoEventoResult();
    
    /**
     * An XML nfeRecepcaoEventoResult(@http://www.portalfiscal.inf.br/nfe/wsdl/RecepcaoEvento).
     *
     * This is a complex type.
     */
    public interface NfeRecepcaoEventoResult extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(NfeRecepcaoEventoResult.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sC45E20710C64A6EA1EE6174BA89A6175").resolveHandle("nferecepcaoeventoresult1d60elemtype");
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument.NfeRecepcaoEventoResult newInstance() {
              return (br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument.NfeRecepcaoEventoResult) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument.NfeRecepcaoEventoResult newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument.NfeRecepcaoEventoResult) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument newInstance() {
          return (br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
