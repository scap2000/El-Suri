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
 * BrowserLauncher.java
 *
 * */
package org.digitall.lib.html;

import java.lang.reflect.Method;

import javax.swing.JOptionPane;

public class BrowserLauncher {
    /////////////////////////////////////////////////////////
    //  Bare Bones Browser Launch                          //
    //  Version 1.5 (December 10, 2005)                    //
    //  By Dem Pilafian                                    //
    //  Supports: Mac OS X, GNU/Linux, Unix, Windows XP    //
    //  Example Usage:                                     //
    //     String url = "http://www.centerkey.com/";       //
    //     BareBonesBrowserLaunch.openURL(url);            //
    //  Public Domain Software -- Free to Use as You Like  //
    /////////////////////////////////////////////////////////
    private static final String errMsg = "Error attempting to launch web browser";

    public static void openURL(String url) {
	String osName = System.getProperty("os.name");
	try {
	    if (osName.startsWith("Mac OS")) {
		Class fileMgr = Class.forName("com.apple.eio.FileManager");
		Method openURL = fileMgr.getDeclaredMethod("openURL", new Class[] { String.class });
		openURL.invoke(null, new Object[] { url });
	    } else if (osName.startsWith("Windows"))
		Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler " + url);
	    else {
		//assume Unix or Linux
		 String browser = null;
		if (url.endsWith(".xls")) {
		    String[] browsers = { "oocalc", "kcalc"};
		    for (int count = 0; count < browsers.length && browser == null; count++)
		        if (Runtime.getRuntime().exec(new String[] { "which", browsers[count] }).waitFor() == 0)
		            browser = browsers[count];
		} else {
		    String[] browsers = { "firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape" };
		    for (int count = 0; count < browsers.length && browser == null; count++)
		        if (Runtime.getRuntime().exec(new String[] { "which", browsers[count] }).waitFor() == 0)
		            browser = browsers[count];
		}
		if (browser == null)
		    throw new Exception("Could not find web browser");
		else
		    Runtime.getRuntime().exec(new String[] { browser, url });
	    }
	} catch (Exception e) {
	    JOptionPane.showMessageDialog(null, errMsg + ":\n" + e.getLocalizedMessage());
	}
    }

}
