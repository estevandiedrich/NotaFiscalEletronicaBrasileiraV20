package br.com.hs.nfe.util;  
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.SocketFactory;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.log4j.Logger;
  
  
public class HSProtocolSocketFactory implements ProtocolSocketFactory {  
  
   private static final String TRUSTSTORE = System.getProperty("javax.net.ssl.trustStore");
   private static final String TRUSTSTOREKEY = System.getProperty("javax.net.ssl.trustStoreType");
   private static final String TRUSTSTOREPASSWORD = System.getProperty("javax.net.ssl.trustStorePassword");
   private static final Logger logger = Logger.getLogger("HSProtocolSocketFactory");
   private SSLContext ssl = null;  
   private X509Certificate certificate;  
   private PrivateKey privateKey;  
  
  
   public HSProtocolSocketFactory(X509Certificate certificate, PrivateKey privateKey) {  
      this.certificate = certificate;  
      this.privateKey = privateKey;  
   }  
  
  
   private SSLContext createSSLContext() {  
      try {  
         KeyManager[] keyManagers = createKeyManagers();  
         TrustManager[] trustManagers = createTrustManagers();  
         SSLContext sslContext = SSLContext.getInstance("TLS");  
         sslContext.init(keyManagers, trustManagers, null);  
  
  
         return sslContext;  
      } catch (KeyManagementException e) {
    	  logger.error(e);  
      } catch (KeyStoreException e) {  
    	  logger.error(e);  
      } catch (NoSuchAlgorithmException e) {  
    	  logger.error(e);  
      } catch (CertificateException e) {  
    	  logger.error(e);  
      } catch (IOException e) {  
    	  logger.error(e);  
      }  
      return null;  
   }  
  
  
   private SSLContext getSSLContext() {  
      if (ssl == null) {  
         ssl = createSSLContext();  
      }  
      return ssl;  
   }  
  
   public Socket createSocket(String host, int port, InetAddress localAddress, int localPort, HttpConnectionParams params) throws IOException, UnknownHostException, ConnectTimeoutException {  
      if (params == null) {  
         throw new IllegalArgumentException("Parameters may not be null");  
      }  
      int timeout = params.getConnectionTimeout();  
      SocketFactory socketfactory = getSSLContext().getSocketFactory();  
      if (timeout == 0) {  
         return socketfactory.createSocket(host, port, localAddress, localPort);  
      }  
  
      Socket socket = socketfactory.createSocket();  
      SocketAddress localaddr = new InetSocketAddress(localAddress, localPort);  
      SocketAddress remoteaddr = new InetSocketAddress(host, port);  
      socket.bind(localaddr);  
      try {  
         socket.connect(remoteaddr, timeout);  
      } catch (Throwable t) {
    	  logger.error(t);  
         throw new ConnectTimeoutException("Erro na conexao", t);  
      }  
  
      return socket;  
   }  
  
   public Socket createSocket(String host, int port, InetAddress clientHost, int clientPort) throws IOException, UnknownHostException {  
      return getSSLContext().getSocketFactory().createSocket(host, port, clientHost, clientPort);  
   }  
  
   public Socket createSocket(String host, int port) throws IOException, UnknownHostException {  
      return getSSLContext().getSocketFactory().createSocket(host, port);  
   }  
  
   public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {  
      return getSSLContext().getSocketFactory().createSocket(socket, host, port, autoClose);  
   }  
  
   public KeyManager[] createKeyManagers() {  
      HSKeyManager keyManager = new HSKeyManager(certificate, privateKey);  
  
      return new KeyManager[]{keyManager};  
   }  
  
   public TrustManager[] createTrustManagers() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {  
      KeyStore trustStore = KeyStore.getInstance(TRUSTSTOREKEY);  
  
      trustStore.load(new FileInputStream(TRUSTSTORE), TRUSTSTOREPASSWORD.toCharArray());  
      TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());  
      trustManagerFactory.init(trustStore);  
      return trustManagerFactory.getTrustManagers();  
   }  
}  