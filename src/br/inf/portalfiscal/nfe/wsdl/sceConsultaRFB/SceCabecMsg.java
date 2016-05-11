/*
 * XML Type:  sceCabecMsg
 * Namespace: http://www.portalfiscal.inf.br/nfe/wsdl/SCEConsultaRFB
 * Java type: br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg
 *
 * Automatically generated - do not modify.
 */
package br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB;


/**
 * An XML sceCabecMsg(@http://www.portalfiscal.inf.br/nfe/wsdl/SCEConsultaRFB).
 *
 * This is a complex type.
 */
public interface SceCabecMsg extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(SceCabecMsg.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sEC17F76D8C7E209E570C1F1B61F2FA0C").resolveHandle("scecabecmsg3b6btype");
    
    /**
     * Gets the "versaoDados" element
     */
    java.lang.String getVersaoDados();
    
    /**
     * Gets (as xml) the "versaoDados" element
     */
    org.apache.xmlbeans.XmlString xgetVersaoDados();
    
    /**
     * True if has "versaoDados" element
     */
    boolean isSetVersaoDados();
    
    /**
     * Sets the "versaoDados" element
     */
    void setVersaoDados(java.lang.String versaoDados);
    
    /**
     * Sets (as xml) the "versaoDados" element
     */
    void xsetVersaoDados(org.apache.xmlbeans.XmlString versaoDados);
    
    /**
     * Unsets the "versaoDados" element
     */
    void unsetVersaoDados();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg newInstance() {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}