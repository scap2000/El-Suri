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
