/**
 LIMITACIÓN DE RESPONSABILIDAD - COPYRIGHT - [Español]
 ================================================================================
 El Suri - Entorno JAVA de Trabajo y Desarrollo para Gobierno Electrónico
 ================================================================================

 Información del Proyecto:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 por D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO es propiedad de
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) y
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los términos de la Licencia Pública General GNU publicada
 por la Fundación para el Software Libre, ya sea la versión 3
 de la Licencia, o (a su elección) cualquier versión posterior.

 Este programa se distribuye con la esperanza de que sea útil, pero
 SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita
 MERCANTIL o de APTITUD PARA UN PROPÓSITO DETERMINADO.
 Consulte los detalles de la Licencia Pública General GNU para obtener
 una información más detallada.

 Debería haber recibido una copia de la Licencia Pública General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
 El Suri - JAVA Management & Development Environment for Electronic Government
 =====================================================================================

 Project Info:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 by D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO is owned by
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) and
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/
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
