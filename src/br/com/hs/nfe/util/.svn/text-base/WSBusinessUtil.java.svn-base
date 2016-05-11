package br.com.hs.nfe.util;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.rpc.ServiceException;

import org.apache.axis.EngineConfiguration;
import org.apache.axis.message.MessageElement;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import br.gov.sp.fazenda.dsen.common.enumeration.ServicoSefazEnum;
import br.gov.sp.fazenda.dsen.common.util.DSENProperties;
import br.gov.sp.fazenda.dsen.model.business.WSBusiness;
import br.gov.sp.fazenda.dsen.util.DSENUtilHelper;
import br.gov.sp.fazenda.dsen.ws.cadastro.CadConsultaCadastro2Locator;
import br.gov.sp.fazenda.dsen.ws.cadastro.CadConsultaCadastro2Soap;
import br.gov.sp.fazenda.dsen.ws.cadastro.CadConsultaCadastroNF2Result;
import br.gov.sp.fazenda.dsen.ws.cadastro.NfeDadosMsg;
import br.gov.sp.fazenda.dsen.ws.cancelamento.NfeCancelamento2Locator;
import br.gov.sp.fazenda.dsen.ws.cancelamento.NfeCancelamento2Soap;
import br.gov.sp.fazenda.dsen.ws.cancelamento.NfeCancelamentoNF2Result;
import br.gov.sp.fazenda.dsen.ws.consulta.NfeConsulta2Locator;
import br.gov.sp.fazenda.dsen.ws.consulta.NfeConsulta2Soap;
import br.gov.sp.fazenda.dsen.ws.consulta.NfeConsultaNF2Result;
import br.gov.sp.fazenda.dsen.ws.dpec.consulta.SCEConsultaRFBLocator;
import br.gov.sp.fazenda.dsen.ws.dpec.consulta.SCEConsultaRFBSoap;
import br.gov.sp.fazenda.dsen.ws.dpec.consulta.SceConsultaDPECResult;
import br.gov.sp.fazenda.dsen.ws.dpec.consulta.SceDadosMsg;
import br.gov.sp.fazenda.dsen.ws.dpec.envio.SCERecepcaoRFBLocator;
import br.gov.sp.fazenda.dsen.ws.dpec.envio.SCERecepcaoRFBSoap;
import br.gov.sp.fazenda.dsen.ws.dpec.envio.SceRecepcaoDPECResult;
import br.gov.sp.fazenda.dsen.ws.evento.NfeRecepcaoEventoResult;
import br.gov.sp.fazenda.dsen.ws.evento.RecepcaoEventoLocator;
import br.gov.sp.fazenda.dsen.ws.evento.RecepcaoEventoSoap;
import br.gov.sp.fazenda.dsen.ws.inutilizacao.NfeInutilizacao2Locator;
import br.gov.sp.fazenda.dsen.ws.inutilizacao.NfeInutilizacao2Soap;
import br.gov.sp.fazenda.dsen.ws.inutilizacao.NfeInutilizacaoNF2Result;
import br.gov.sp.fazenda.dsen.ws.recepcao.NfeRecepcao2Locator;
import br.gov.sp.fazenda.dsen.ws.recepcao.NfeRecepcao2Soap;
import br.gov.sp.fazenda.dsen.ws.recepcao.NfeRecepcaoLote2Result;
import br.gov.sp.fazenda.dsen.ws.recepcao.retorno.NfeRetRecepcao2Locator;
import br.gov.sp.fazenda.dsen.ws.recepcao.retorno.NfeRetRecepcao2Result;
import br.gov.sp.fazenda.dsen.ws.recepcao.retorno.NfeRetRecepcao2Soap;
import br.gov.sp.fazenda.dsen.ws.status.NfeStatusServico2Locator;
import br.gov.sp.fazenda.dsen.ws.status.NfeStatusServico2Soap;
import br.gov.sp.fazenda.dsen.ws.status.NfeStatusServicoNF2Result;
import br.gov.sp.fazenda.dsge.util.EstadoTO;
import br.gov.sp.fazenda.dsge.util.exception.DSGEUtilException;
import br.gov.sp.fazenda.dsge.ws.exception.DSGEConnectionClientException;
import br.gov.sp.fazenda.dsge.ws.exception.DSGEConnectionServerException;
import br.gov.sp.fazenda.dsge.ws.exception.DSGEProxyException;
import br.gov.sp.fazenda.dsge.ws.exception.DSGESocketException;
import br.gov.sp.fazenda.dsge.ws.exception.DSGETimeoutException;
import br.gov.sp.fazenda.dsge.ws.exception.DSGEWebServiceException;

public class WSBusinessUtil extends WSBusiness {
	private static final Logger logger = Logger.getLogger("WSBusinessUtil");
	private X509Certificate a_java_security_cert_X509Certificate_fld;
    private PrivateKey a_java_security_PrivateKey_fld;
    private Document a(InputStream message)
	    throws Exception
	{
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    DocumentBuilder db = dbf.newDocumentBuilder();
	    return db.parse(message);
	}
    static class _cls1
    {

        static final int a[];

        static 
        {
            a = new int[ServicoSefazEnum.values().length];
            try
            {
                a[ServicoSefazEnum.StatusServico.ordinal()] = 1;
            }
            catch(NoSuchFieldError ex) { }
            try
            {
                a[ServicoSefazEnum.Cancelamento.ordinal()] = 2;
            }
            catch(NoSuchFieldError ex) { }
            try
            {
                a[ServicoSefazEnum.Inutilizacao.ordinal()] = 3;
            }
            catch(NoSuchFieldError ex) { }
            try
            {
                a[ServicoSefazEnum.Recepcao.ordinal()] = 4;
            }
            catch(NoSuchFieldError ex) { }
            try
            {
                a[ServicoSefazEnum.RetRecepcao.ordinal()] = 5;
            }
            catch(NoSuchFieldError ex) { }
            try
            {
                a[ServicoSefazEnum.ConsultaNf.ordinal()] = 6;
            }
            catch(NoSuchFieldError ex) { }
            try
            {
                a[ServicoSefazEnum.ConsultaCadastro.ordinal()] = 7;
            }
            catch(NoSuchFieldError ex) { }
            try
            {
                a[ServicoSefazEnum.EnvioDpec.ordinal()] = 8;
            }
            catch(NoSuchFieldError ex) { }
            try
            {
                a[ServicoSefazEnum.ConsultaDpec.ordinal()] = 9;
            }
            catch(NoSuchFieldError ex) { }
            try
            {
                a[ServicoSefazEnum.RecepcaoEvento.ordinal()] = 10;
            }
            catch(NoSuchFieldError ex) { }
        }
    }
    
    private MessageElement[] a(String nfeDadosMsg)
	    throws Exception
	{
	    MessageElement messageElement = new MessageElement(a(((InputStream) (new ByteArrayInputStream(nfeDadosMsg.getBytes("UTF-8"))))).getDocumentElement());
	    return (new MessageElement[] {
	        messageElement
	    });
	}
    private String a(String cabecMsg, String dadosMsg, EstadoTO estadoTO)
    	throws DSGEWebServiceException, DSGEConnectionClientException, DSGEConnectionServerException, DSGETimeoutException, DSGEProxyException, DSGESocketException
	{
	    try
	    {
	        String url = DSENUtilHelper.getInstance().getURLPorUF(estadoTO, ServicoSefazEnum.RecepcaoEvento);
	        RecepcaoEventoLocator eventoLocator = new RecepcaoEventoLocator(getEngineConfiguration());
	        RecepcaoEventoSoap eventoSoap = eventoLocator.getNfeInutilizacao2Soap12(new URL(url), 60000);
	        MessageElement[] messageElements = a(cabecMsg);
	        
	        NfeRecepcaoEventoResult recepcaoEventoResult = eventoSoap.nfeRecepcaoEvento(messageElements[0], new br.gov.sp.fazenda.dsen.ws.evento.NfeDadosMsg(a(dadosMsg)));
	        return recepcaoEventoResult.get_any()[0].getAsString();
	    }
	    catch(DSGEUtilException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(MalformedURLException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(ServiceException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(Exception e)
	    {
	        lancarExceptionWS(e);
	    }
	    return null;
	}
    @Override
    public EngineConfiguration getEngineConfiguration()
    {
//    	String alias = System.getProperty("javax.net.ssl.keyStoreAlias");
//        String password = System.getProperty("javax.net.ssl.keyStorePassword");
//        KeyStore ks;
//		try {
//			ks = KeyStore.getInstance(System.getProperty("javax.net.ssl.keyStoreType"));
//			ks.load(null, password.toCharArray());
//		} catch (KeyStoreException e) {
//			// TODO Auto-generated catch block
//			logger.error(e);
//		} catch(CertificateException e)	{
//			logger.error(e);
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
    	Protocol protocol = new Protocol("https", new HSProtocolSocketFactory(a_java_security_cert_X509Certificate_fld, a_java_security_PrivateKey_fld), 443);
//    	Protocol protocol = new Protocol("https", new DSENProtocolSocketFactory(a_java_security_cert_X509Certificate_fld, a_java_security_PrivateKey_fld), 443);
        Protocol.registerProtocol("https", protocol);
        return null;
    }
    private String g(String nfeCabecMsg, String nfeDadosMsg, EstadoTO estadoTO)
    throws DSGEConnectionClientException, DSGEConnectionServerException, DSGETimeoutException, DSGEProxyException, DSGESocketException, DSGEWebServiceException
	{
	    try
	    {
	        String url = DSENUtilHelper.getInstance().getURLPorUF(estadoTO, ServicoSefazEnum.StatusServico);
	        NfeStatusServico2Locator nfeStatusServicoLocator = new NfeStatusServico2Locator(getEngineConfiguration());
	        NfeStatusServico2Soap nfeStatusServicoSoap = nfeStatusServicoLocator.getNfeStatusServico2Soap12(new URL(url), 60000);
	        MessageElement[] messageElements = a(nfeCabecMsg);
	        NfeStatusServicoNF2Result nfeStatusServicoNF2Result = nfeStatusServicoSoap.nfeStatusServicoNF2(messageElements[0], new br.gov.sp.fazenda.dsen.ws.status.NfeDadosMsg(a(nfeDadosMsg)));
	        return nfeStatusServicoNF2Result.get_any()[0].getAsString();
	    }
	    catch(DSGEUtilException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(MalformedURLException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(ServiceException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(Exception e)
	    {
	    	logger.error(e);
	        lancarExceptionWS(e);
	    }
	    return null;
	}
    private String b(String nfeCabecMsg, String nfeDadosMsg, EstadoTO estadoTO)
	    throws DSGEConnectionClientException, DSGEConnectionServerException, DSGETimeoutException, DSGEProxyException, DSGESocketException, DSGEWebServiceException
	{
	    try
	    {
	        String url = DSENUtilHelper.getInstance().getURLPorUF(estadoTO, ServicoSefazEnum.Cancelamento);
	        NfeCancelamento2Locator nfeCancelamentoLocator = new NfeCancelamento2Locator(getEngineConfiguration());
	        NfeCancelamento2Soap nfeCancelamentoSoap = nfeCancelamentoLocator.getNfeCancelamento2Soap12(new URL(url), 60000);
	        MessageElement[] messageElements = a(nfeCabecMsg);
	        NfeCancelamentoNF2Result nfeCancelamentoNF2Result = nfeCancelamentoSoap.nfeCancelamentoNF2(messageElements[0], new br.gov.sp.fazenda.dsen.ws.cancelamento.NfeDadosMsg(a(nfeDadosMsg)));
	        return nfeCancelamentoNF2Result.get_any()[0].getAsString();
	    }
	    catch(DSGEUtilException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(MalformedURLException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(ServiceException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(Exception e)
	    {
	        lancarExceptionWS(e);
	    }
	    return null;
	}
    private String d(String nfeCabecMsg, String nfeDadosMsg, EstadoTO estadoTO)
	    throws DSGEConnectionClientException, DSGEConnectionServerException, DSGETimeoutException, DSGEProxyException, DSGESocketException, DSGEWebServiceException
	{
	    try
	    {
	        String url = DSENUtilHelper.getInstance().getURLPorUF(estadoTO, ServicoSefazEnum.Inutilizacao);
	        NfeInutilizacao2Locator nfeInutilizacaoLocator = new NfeInutilizacao2Locator(getEngineConfiguration());
	        NfeInutilizacao2Soap nfeInutilizacaoSoap = nfeInutilizacaoLocator.getNfeInutilizacao2Soap12(new URL(url), 60000);
	        MessageElement[] messageElements = a(nfeCabecMsg);
	        NfeInutilizacaoNF2Result nfeInutilizacaoNF2Result = nfeInutilizacaoSoap.nfeInutilizacaoNF2(messageElements[0], new br.gov.sp.fazenda.dsen.ws.inutilizacao.NfeDadosMsg(a(nfeDadosMsg)));
	        return nfeInutilizacaoNF2Result.get_any()[0].getAsString();
	    }
	    catch(DSGEUtilException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(MalformedURLException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(ServiceException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(Exception e)
	    {
	        lancarExceptionWS(e);
	    }
	    return null;
	}
    private String e(String nfeCabecMsg, String nfeDadosMsg, EstadoTO estadoTO)
	    throws DSGEConnectionClientException, DSGEConnectionServerException, DSGETimeoutException, DSGEProxyException, DSGESocketException, DSGEWebServiceException
	{
	    try
	    {
	        String url = DSENUtilHelper.getInstance().getURLPorUF(estadoTO, ServicoSefazEnum.Recepcao);
	        NfeRecepcao2Locator nfeRecepcaoLocator = new NfeRecepcao2Locator(getEngineConfiguration());
	        NfeRecepcao2Soap recepcaoSoap = nfeRecepcaoLocator.getNfeRecepcao2Soap12(new URL(url), 60000);
	        MessageElement[] messageElements = a(nfeCabecMsg);
	        NfeRecepcaoLote2Result nfeRecepcaoLote2Result = recepcaoSoap.nfeRecepcaoLote2(messageElements[0], new br.gov.sp.fazenda.dsen.ws.recepcao.NfeDadosMsg(a(nfeDadosMsg)));
	        return nfeRecepcaoLote2Result.get_any()[0].getAsString();
	    }
	    catch(DSGEUtilException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(MalformedURLException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(ServiceException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(Exception e)
	    {
	        lancarExceptionWS(e);
	    }
	    return null;
	}
    private String f(String nfeCabecMsg, String nfeDadosMsg, EstadoTO estadoTO)
    throws DSGEConnectionClientException, DSGEConnectionServerException, DSGETimeoutException, DSGEProxyException, DSGESocketException, DSGEWebServiceException
	{
	    try
	    {
	        String url = DSENUtilHelper.getInstance().getURLPorUF(estadoTO, ServicoSefazEnum.RetRecepcao);
	        NfeRetRecepcao2Locator nfeRetRecepcaoLocator = new NfeRetRecepcao2Locator(getEngineConfiguration());
	        NfeRetRecepcao2Soap nfeRetRecepcaoSoap = nfeRetRecepcaoLocator.getNfeRetRecepcao2Soap12(new URL(url), 60000);
	        MessageElement[] messageElements = a(nfeCabecMsg);
	        NfeRetRecepcao2Result nfeRetRecepcao2Result = nfeRetRecepcaoSoap.nfeRetRecepcao2(messageElements[0], new br.gov.sp.fazenda.dsen.ws.recepcao.retorno.NfeDadosMsg(a(nfeDadosMsg)));
	        return nfeRetRecepcao2Result.get_any()[0].getAsString();
	    }
	    catch(DSGEUtilException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(MalformedURLException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(ServiceException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(Exception e)
	    {
	        lancarExceptionWS(e);
	    }
	    return null;
	}
    private String c(String nfeCabecMsg, String nfeDadosMsg, EstadoTO estadoTO)
	    throws DSGEConnectionClientException, DSGEConnectionServerException, DSGETimeoutException, DSGEProxyException, DSGESocketException, DSGEWebServiceException
	{
	    try
	    {
	        String url = DSENUtilHelper.getInstance().getURLPorUF(estadoTO, ServicoSefazEnum.ConsultaNf);
	        NfeConsulta2Locator nfeConsultaLocator = new NfeConsulta2Locator(getEngineConfiguration());
	        NfeConsulta2Soap nfeConsultaSoap = nfeConsultaLocator.getNfeConsulta2Soap12(new URL(url), 60000);
	        MessageElement[] messageElements = a(nfeCabecMsg);
	        NfeConsultaNF2Result nfeConsultaNF2Result = nfeConsultaSoap.nfeConsultaNF2(messageElements[0], new br.gov.sp.fazenda.dsen.ws.consulta.NfeDadosMsg(a(nfeDadosMsg)));
	        return nfeConsultaNF2Result.get_any()[0].getAsString();
	    }
	    catch(DSGEUtilException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(MalformedURLException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(ServiceException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(Exception e)
	    {
	        lancarExceptionWS(e);
	    }
	    return null;
	}
    private String h(String nfeCabecMsg, String nfeDadosMsg, EstadoTO estadoTO)
	    throws DSGEConnectionClientException, DSGEConnectionServerException, DSGETimeoutException, DSGEProxyException, DSGESocketException, DSGEWebServiceException
	{
	    try
	    {
	        String url = DSENUtilHelper.getInstance().getURLPorUF(estadoTO, ServicoSefazEnum.ConsultaCadastro);
	        CadConsultaCadastro2Locator cadConsultaCadastroLocator = new CadConsultaCadastro2Locator(getEngineConfiguration());
	        CadConsultaCadastro2Soap cadConsultaCadastroSoap = cadConsultaCadastroLocator.getCadConsultaCadastro2Soap12(new URL(url), 60000);
	        MessageElement[] messageElements = a(nfeCabecMsg);
	        CadConsultaCadastroNF2Result result = cadConsultaCadastroSoap.consultaCadastro2(messageElements[0], new NfeDadosMsg(a(nfeDadosMsg)));
	        return result.get_any()[0].getAsString();
	    }
	    catch(DSGEUtilException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(MalformedURLException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(ServiceException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(Exception e)
	    {
	        lancarExceptionWS(e);
	    }
	    return null;
	}
    private String a(String sceCabecMsg, String sceDadosMsg)
	    throws DSGEConnectionClientException, DSGEConnectionServerException, DSGETimeoutException, DSGEProxyException, DSGESocketException, DSGEWebServiceException
	{
	    try
	    {
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        Document doc = db.parse(new ByteArrayInputStream(sceCabecMsg.getBytes("UTF-8")));
	        Element element = doc.getDocumentElement();
	        MessageElement messageElement = new MessageElement(db.parse(new ByteArrayInputStream(sceDadosMsg.getBytes("UTF-8"))).getDocumentElement());
	        String url = DSENProperties.getInstance().getDpecUrlEnvio();
	        SCERecepcaoRFBLocator recepcaoRFBLocator = new SCERecepcaoRFBLocator(getEngineConfiguration());
	        SCERecepcaoRFBSoap recepcaoRFBSoap = recepcaoRFBLocator.getSCERecepcaoRFBSoap12(new URL(url), 60000);
	        SceRecepcaoDPECResult sceRecepcaoDPECResult = recepcaoRFBSoap.sceRecepcaoDPEC(element, new br.gov.sp.fazenda.dsen.ws.dpec.envio.SceDadosMsg(new MessageElement[] {
	            messageElement
	        }));
	        return sceRecepcaoDPECResult.get_any()[0].getAsString();
	    }
	    catch(MalformedURLException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(ServiceException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(RemoteException e)
	    {
	        lancarExceptionWS(e);
	    }
	    catch(ParserConfigurationException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(FileNotFoundException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(SAXException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(IOException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(Exception e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    return null;
	}
    private String b(String sceCabecMsg, String sceDadosMsg)
	    throws DSGEConnectionClientException, DSGEConnectionServerException, DSGETimeoutException, DSGEProxyException, DSGESocketException, DSGEWebServiceException
	{
	    try
	    {
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        Document doc = db.parse(new ByteArrayInputStream(sceCabecMsg.getBytes("UTF-8")));
	        Element element = doc.getDocumentElement();
	        MessageElement messageElement = new MessageElement(db.parse(new ByteArrayInputStream(sceDadosMsg.getBytes("UTF-8"))).getDocumentElement());
	        String url = DSENProperties.getInstance().getDpecUrlConsulta();
	        SCEConsultaRFBLocator consultaRFBLocator = new SCEConsultaRFBLocator(getEngineConfiguration());
	        SCEConsultaRFBSoap consultaRFBSoap = consultaRFBLocator.getSCEConsultaRFBSoap12(new URL(url), 60000);
	        SceConsultaDPECResult sceConsultaDPECResult = consultaRFBSoap.sceConsultaDPEC(element, new SceDadosMsg(new MessageElement[] {
	            messageElement
	        }));
	        return sceConsultaDPECResult.get_any()[0].getAsString();
	    }
	    catch(MalformedURLException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(ServiceException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(RemoteException e)
	    {
	        lancarExceptionWS(e);
	    }
	    catch(ParserConfigurationException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(FileNotFoundException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(SAXException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(IOException e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    catch(Exception e)
	    {
	        throw new DSGEWebServiceException(e, e.getMessage());
	    }
	    return null;
	}
	@Override
    public String servico(String cabecMsg, String dadosMsg, EstadoTO estadoTO, ServicoSefazEnum servicoSefazEnum, X509Certificate certificate, PrivateKey privateKey)
    throws DSGEConnectionClientException, DSGEConnectionServerException, DSGETimeoutException, DSGEProxyException, DSGESocketException, DSGEWebServiceException
	{
	    a_java_security_cert_X509Certificate_fld = certificate;
	    a_java_security_PrivateKey_fld = privateKey;

	
	    switch(_cls1.a[servicoSefazEnum.ordinal()])
	    {
	    case 1: // '\001'
	        return g(cabecMsg, dadosMsg, estadoTO);
	
	    case 2: // '\002'
	        return b(cabecMsg, dadosMsg, estadoTO);
	
	    case 3: // '\003'
	        return d(cabecMsg, dadosMsg, estadoTO);
	
	    case 4: // '\004'
	        return e(cabecMsg, dadosMsg, estadoTO);
	
	    case 5: // '\005'
	        return f(cabecMsg, dadosMsg, estadoTO);
	
	    case 6: // '\006'
	        return c(cabecMsg, dadosMsg, estadoTO);
	
	    case 7: // '\007'
	        return h(cabecMsg, dadosMsg, estadoTO);
	
	    case 8: // '\b'
	        return a(cabecMsg, dadosMsg);
	
	    case 9: // '\t'
	        return b(cabecMsg, dadosMsg);
	
	    case 10: // '\n'
	        return a(cabecMsg, dadosMsg, estadoTO);
	    }
	    return null;
	}
}
