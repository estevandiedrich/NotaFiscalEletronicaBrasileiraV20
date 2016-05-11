
package br.com.hs.nfe.ws.sc.homo.nfeinutilizacao2;

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
 * Tue May 31 22:19:18 BRT 2011
 * Generated source version: 2.2.1
 * 
 */

public final class NfeInutilizacao2Helper {

	private static final Logger logger = Logger.getLogger("NfeInutilizacao2Helper");
    private ClientConfig clientConfig = null;

    public NfeInutilizacao2Helper(Estabelecimento config) {
    	this.clientConfig = new ClientConfig(config);
    }

    public String nfeInutilizacao2(String nome,String xml){
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
        factory.setServiceClass(NfeInutilizacao2Soap12.class);
        factory.setAddress("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx");
        factory.setWsdlURL(NfeInutilizacao2.WSDL_LOCATION.toString());
        factory.setServiceName(NfeInutilizacao2.SERVICE);
        factory.setEndpointName(NfeInutilizacao2.NfeInutilizacao2Soap12);
        factory.getServiceFactory().setDataBinding(new XmlBeansDataBinding());
        NfeInutilizacao2Soap12 port = (NfeInutilizacao2Soap12)factory.create();
        Client client = ClientProxy.getClient(port);
        HTTPConduit http = (HTTPConduit)client.getConduit();
        http.setTlsClientParameters(tls);
        br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeInutilizacaoNF2ResultDocument _nfeInutilizacao2__return = null;
        try{
	        logger.info("Invoking nfeInutilizacao2...");
	        xml = xml.substring(xml.indexOf("?>")+2);
	        xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><nfeDadosMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2\">"+xml+"</nfeDadosMsg>";
	        br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeDadosMsgDocument _nfeInutilizacao2_nfeDadosMsg = br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeDadosMsgDocument.Factory.parse(xml);
	        br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeCabecMsgDocument nfeInutilizacao2_nfeCabecMsg = br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeCabecMsgDocument.Factory.newInstance();
	        br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeCabecMsg _nfeInutilizacao2_nfeCabecMsgVal = br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeCabecMsg.Factory.newInstance();
	        _nfeInutilizacao2_nfeCabecMsgVal.setVersaoDados("2.00");
	        _nfeInutilizacao2_nfeCabecMsgVal.setCUF("42");
	        nfeInutilizacao2_nfeCabecMsg.setNfeCabecMsg(_nfeInutilizacao2_nfeCabecMsgVal);
	        javax.xml.ws.Holder<br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeCabecMsgDocument> _nfeInutilizacao2_nfeCabecMsg = new javax.xml.ws.Holder<br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeCabecMsgDocument>(nfeInutilizacao2_nfeCabecMsg);
	        _nfeInutilizacao2__return = port.nfeInutilizacaoNF2(_nfeInutilizacao2_nfeDadosMsg, _nfeInutilizacao2_nfeCabecMsg);
	        logger.info("nfeInutilizacao2.result=" + _nfeInutilizacao2__return);
	
	        logger.info("nfeInutilizacao2._nfeInutilizacao2_nfeCabecMsg=" + _nfeInutilizacao2_nfeCabecMsg.value);
        
	    }
	    catch(Exception e)
	    {
	    	logger.error("error ",e);
	    }
	    return _nfeInutilizacao2__return.toString();
    }
}
