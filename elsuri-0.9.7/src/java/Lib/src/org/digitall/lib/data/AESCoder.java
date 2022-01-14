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
 * AESCoder.java
 *
 * */
package org.digitall.lib.data;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


    // Esta codificación todavía da error de Padding!!!


public abstract class AESCoder {

    private static KeyGenerator kgen;
    private static SecretKey skey;
    private static SecretKeySpec skeySpec;
    private static Cipher cipher;
    public static String prepare() {
	try {
	    kgen = KeyGenerator.getInstance("AES");
	    kgen.init(128);

	    // Generate the secret key specs.
	    skey = kgen.generateKey();
	    byte[] raw = skey.getEncoded();

	    skeySpec = new SecretKeySpec(raw, "AES");
	    cipher = Cipher.getInstance("AES");

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return "";
    }

    private static String asHex(byte[] buf) {
	StringBuffer strbuf = new StringBuffer(buf.length * 2);
	int i;

	for (i = 0; i < buf.length; i++) {
	    if (((int)buf[i] & 0xff) < 0x10)
		strbuf.append("0");

	    strbuf.append(Long.toString((int)buf[i] & 0xff, 16));
	}

	return strbuf.toString();
    }

    // Esta codificación todavía da error de Padding!!!
    public static String encode(String _string) {
	prepare();
	String _returns = "";
	try {
	    cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
	    byte[] encrypted = cipher.doFinal(Base64Coder.encode(_string).getBytes());
	    _returns = asHex(encrypted);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return _returns;
    }

    // Esta codificación todavía da error de Padding!!!
    public static String decode(String _string) {
	prepare();
	String _returns = "";
	try {
	    cipher.init(Cipher.DECRYPT_MODE, skeySpec);
	    byte[] original = cipher.doFinal(Base64Coder.decode(_string).getBytes());
	    _returns = asHex(original);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return _returns;
    }
}
