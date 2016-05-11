
package br.com.hs.nfe.ws.sc.prod.nferecepcao2;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.xmlbeans.XmlBeansDataBinding;
import org.apache.log4j.Logger;

import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.util.ClientConfig;

/**
 * This class was generated by Apache CXF 2.2.1
 * Tue May 31 22:04:03 BRT 2011
 * Generated source version: 2.2.1
 * 
 */

public final class NfeRecepcao2Helper {

    private static final Logger logger = Logger.getLogger("NfeRecepcao2Helper");
    private ClientConfig clientConfig = null;
    public NfeRecepcao2Helper(Estabelecimento config) {
    	clientConfig = new ClientConfig(config);
    }

    public String nfeRecepcaoLote2(String nome,String xml) throws Exception {
    	//System.setProperty("javax.net.debug", "all");
        TLSClientParameters tls = new TLSClientParameters();
        try
        {
        	this.clientConfig.configTLS(tls);
        }
        catch(Exception e)
        {
        	logger.error("error ",e);
        }
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(NfeRecepcao2Soap12.class);
        factory.setAddress("https://nfe.sefazvirtual.rs.gov.br/ws/Nferecepcao/NFeRecepcao2.asmx");
        factory.setWsdlURL(NfeRecepcao2.WSDL_LOCATION.toString());
        factory.setServiceName(NfeRecepcao2.SERVICE);
        factory.setEndpointName(NfeRecepcao2.NfeRecepcao2Soap12);
        factory.getServiceFactory().setDataBinding(new XmlBeansDataBinding());
        NfeRecepcao2Soap12 port = (NfeRecepcao2Soap12)factory.create();
        Client client = ClientProxy.getClient(port);
        HTTPConduit http = (HTTPConduit)client.getConduit();
        http.setTlsClientParameters(tls);
        br.inf.portalfiscal.nfe.wsdl.nfeRecepcao2.NfeRecepcaoLote2ResultDocument _nfeRecepcaoLote2__return = null;
        //try{
	        logger.info("Invoking nfeRecepcaoLote2...");
	        //br.inf.portalfiscal.nfe.wsdl.nfeRecepcao2.NfeDadosMsgDocument _nfeRecepcaoLote2_nfeDadosMsg = br.inf.portalfiscal.nfe.wsdl.nfeRecepcao2.NfeDadosMsgDocument.Factory.newInstance();
	        xml = xml.substring(xml.indexOf("?>")+2);
	        xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><nfeDadosMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeRecepcao2\">"+xml+"</nfeDadosMsg>";
	        br.inf.portalfiscal.nfe.wsdl.nfeRecepcao2.NfeDadosMsgDocument _nfeRecepcaoLote2_nfeDadosMsg = br.inf.portalfiscal.nfe.wsdl.nfeRecepcao2.NfeDadosMsgDocument.Factory.parse(xml);
	        br.inf.portalfiscal.nfe.wsdl.nfeRecepcao2.NfeCabecMsgDocument nfeRecepcaoLote2_nfeCabecMsg = br.inf.portalfiscal.nfe.wsdl.nfeRecepcao2.NfeCabecMsgDocument.Factory.newInstance();
	        br.inf.portalfiscal.nfe.wsdl.nfeRecepcao2.NfeCabecMsg _nfeRecepcaoLote2_nfeCabecMsgVal = br.inf.portalfiscal.nfe.wsdl.nfeRecepcao2.NfeCabecMsg.Factory.newInstance();
	        _nfeRecepcaoLote2_nfeCabecMsgVal.setVersaoDados("2.00");
	        _nfeRecepcaoLote2_nfeCabecMsgVal.setCUF("42");
	        nfeRecepcaoLote2_nfeCabecMsg.setNfeCabecMsg(_nfeRecepcaoLote2_nfeCabecMsgVal);
	        javax.xml.ws.Holder<br.inf.portalfiscal.nfe.wsdl.nfeRecepcao2.NfeCabecMsgDocument> _nfeRecepcaoLote2_nfeCabecMsg = new javax.xml.ws.Holder<br.inf.portalfiscal.nfe.wsdl.nfeRecepcao2.NfeCabecMsgDocument>(nfeRecepcaoLote2_nfeCabecMsg);
	        _nfeRecepcaoLote2__return = port.nfeRecepcaoLote2(_nfeRecepcaoLote2_nfeDadosMsg, _nfeRecepcaoLote2_nfeCabecMsg);
	        logger.info("nfeRecepcaoLote2.result=" + _nfeRecepcaoLote2__return);
	
	        logger.info("nfeRecepcaoLote2._nfeRecepcaoLote2_nfeCabecMsg=" + _nfeRecepcaoLote2_nfeCabecMsg.value);
	        
//        }
//        catch(Exception e)
//        {
//        	logger.error("error ",e);
//        }
        return  _nfeRecepcaoLote2__return.toString();
    }

}
