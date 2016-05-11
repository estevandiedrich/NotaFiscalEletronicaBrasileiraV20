/*
 * An XML document type.
 * Localname: sceConsultaDPECResult
 * Namespace: http://www.portalfiscal.inf.br/nfe/wsdl/SCEConsultaRFB
 * Java type: br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument
 *
 * Automatically generated - do not modify.
 */
package br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB;


/**
 * A document containing one sceConsultaDPECResult(@http://www.portalfiscal.inf.br/nfe/wsdl/SCEConsultaRFB) element.
 *
 * This is a complex type.
 */
public interface SceConsultaDPECResultDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(SceConsultaDPECResultDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sEC17F76D8C7E209E570C1F1B61F2FA0C").resolveHandle("sceconsultadpecresult45fedoctype");
    
    /**
     * Gets the "sceConsultaDPECResult" element
     */
    br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument.SceConsultaDPECResult getSceConsultaDPECResult();
    
    /**
     * Sets the "sceConsultaDPECResult" element
     */
    void setSceConsultaDPECResult(br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument.SceConsultaDPECResult sceConsultaDPECResult);
    
    /**
     * Appends and returns a new empty "sceConsultaDPECResult" element
     */
    br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument.SceConsultaDPECResult addNewSceConsultaDPECResult();
    
    /**
     * An XML sceConsultaDPECResult(@http://www.portalfiscal.inf.br/nfe/wsdl/SCEConsultaRFB).
     *
     * This is a complex type.
     */
    public interface SceConsultaDPECResult extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(SceConsultaDPECResult.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sEC17F76D8C7E209E570C1F1B61F2FA0C").resolveHandle("sceconsultadpecresult3261elemtype");
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument.SceConsultaDPECResult newInstance() {
              return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument.SceConsultaDPECResult) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument.SceConsultaDPECResult newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument.SceConsultaDPECResult) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument newInstance() {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
