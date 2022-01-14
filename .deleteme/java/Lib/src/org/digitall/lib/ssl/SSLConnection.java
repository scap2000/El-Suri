/**
 * SSLConnection.java
 *
 * */
package org.digitall.lib.ssl;

import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/**********************************************************************
 * Disclaimer
 * This example code is provided "AS IS" without warranties of any kind.
 * Use it at your Risk!
 *
 * SSLConnection class that will holds the common traits for both the
 * client and the serverURL relay. The client and serverURL proxy will inherit from this
 * class
 *
 * @author Chianglin Jan 2003
 *
 * @version Modified by Santiago Cassina August 2007
 */
public class SSLConnection extends Thread {

    private SSLContext sslContext;
    private KeyStore keyStore, trustStore;
    private String keyFilePath, trustFilePath;
    //Default constructor takes the filename of the keystore and truststore ,

    public SSLConnection() {
	keyFilePath = SSLConfig.KEY_FILE_PATH;
	trustFilePath = SSLConfig.TRUST_FILE_PATH;
	initSSLContext(SSLConfig.STORE_PASS, SSLConfig.KEY_PASS);
    }

    public void initKeyStores(String _keyFilePath, String _trustFilePath, char[] _storePass) {
	/* mykey holding my own certificate and private key, mytrust holding all the certificates that I trust */
	try {
	    //get instances of the Sun JKS keystore
	    keyStore = KeyStore.getInstance("JKS", "SUN");
	    trustStore = KeyStore.getInstance("JKS", "SUN");
	    //load the keystores
	    keyStore.load(SSLConfig.class.getResourceAsStream(_keyFilePath), _storePass);
	    trustStore.load(SSLConfig.class.getResourceAsStream(_trustFilePath), _storePass);
	} catch (Exception e) {
	    e.printStackTrace();
	    //System.exit(1);
	}
    }

    public void initSSLContext(char[] _storePass, char[] _keyPass) {
	try {
	    sslContext = SSLContext.getInstance("TLSv1", "SunJSSE");
	    initKeyStores(keyFilePath, trustFilePath, _storePass);
	    //Create the key and trust manager factories for handing the cerficates
	    //in the key and trust stores
	    TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509", "SunJSSE");
	    tmf.init(trustStore);
	    KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509", "SunJSSE");
	    kmf.init(keyStore, _keyPass);
	    sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
	} catch (Exception e) {
	    e.printStackTrace();
	    //System.exit(1);
	}
    }

    public SSLContext getMySSLContext() {
	return sslContext;
    }

}
