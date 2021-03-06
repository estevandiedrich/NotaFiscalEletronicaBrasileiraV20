
/*
 * 
 */

package br.com.hs.nfe.ws.br.prod.nfeconsultascan;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

/**
 * This class was generated by Apache CXF 2.2.1
 * Mon Sep 05 15:04:57 BRT 2011
 * Generated source version: 2.2.1
 * 
 */


@WebServiceClient(name = "NfeConsulta2", 
                  wsdlLocation = "file:NfeConsulta2.wsdl",
                  targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2") 
public class NfeConsulta2 extends Service {

    public final static URL WSDL_LOCATION = Thread.currentThread().getContextClassLoader().getResource("wsdl/br/prod/NfeConsulta2.wsdl");
    public final static QName SERVICE = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2", "NfeConsulta2");
    public final static QName NfeConsulta2Soap = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2", "NfeConsulta2Soap");
    public final static QName NfeConsulta2Soap12 = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2", "NfeConsulta2Soap12");

    public NfeConsulta2(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public NfeConsulta2(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public NfeConsulta2() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns NfeConsulta2Soap
     */
    @WebEndpoint(name = "NfeConsulta2Soap")
    public NfeConsulta2Soap getNfeConsulta2Soap() {
        return super.getPort(NfeConsulta2Soap, NfeConsulta2Soap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns NfeConsulta2Soap
     */
    @WebEndpoint(name = "NfeConsulta2Soap")
    public NfeConsulta2Soap getNfeConsulta2Soap(WebServiceFeature... features) {
        return super.getPort(NfeConsulta2Soap, NfeConsulta2Soap.class, features);
    }
    /**
     * 
     * @return
     *     returns NfeConsulta2Soap
     */
    @WebEndpoint(name = "NfeConsulta2Soap12")
    public NfeConsulta2Soap getNfeConsulta2Soap12() {
        return super.getPort(NfeConsulta2Soap12, NfeConsulta2Soap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns NfeConsulta2Soap
     */
    @WebEndpoint(name = "NfeConsulta2Soap12")
    public NfeConsulta2Soap getNfeConsulta2Soap12(WebServiceFeature... features) {
        return super.getPort(NfeConsulta2Soap12, NfeConsulta2Soap.class, features);
    }

}
