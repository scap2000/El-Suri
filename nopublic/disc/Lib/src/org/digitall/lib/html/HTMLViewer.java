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
 * HTMLViewer.java
 *
 * */
package org.digitall.lib.html;

import java.io.IOException;

import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JViewport;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;

import org.digitall.lib.components.basic.BasicScrollPane;

/*
  * @(#)HtmlDemo.java	1.4 99/07/23
  *
  * Copyright (c) 1997-1999 by Sun Microsystems, Inc. All Rights Reserved.
  *
  * Sun grants you ("Licensee") a non-exclusive, royalty free, license to use,
  * modify and redistribute this software in source and binary code form,
  * provided that i) this copyright notice and license appear on all copies of
  * the software; and ii) Licensee does not utilize the software in a manner
  * which is disparaging to Sun.
  *
  * This software is provided "AS IS," without a warranty of any kind. ALL
  * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
  * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
  * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE
  * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
  * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS
  * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
  * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
  * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
  * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
  * POSSIBILITY OF SUCH DAMAGES.
  *
  * This software is not designed or intended for use in on-line control of
  * aircraft, air traffic, aircraft navigation or aircraft communications; or in
  * the design, construction, operation or maintenance of any nuclear
  * facility. Licensee represents and warrants that it will not use or
  * redistribute the Software for such purposes.
  */

/**
 * Html Demo
 *
 * @version 1.4 99/07/23
 * @author Jeff Dinkins
 */
public class HTMLViewer extends BasicScrollPane {

    private JEditorPane html;

    public HTMLViewer(URL url) {
	/*  try {*/
	String path = null;
	try {
	    //path = "http://172.16.4.253/jnlp.html";
	    //url = getClass().getResource(path);
	    html = new JEditorPane(url);
	    //html.setPage(url);
	    html.setEditable(false);
	    html.addHyperlinkListener(createHyperLinkListener());
	    JViewport vp = getViewport();
	    vp.add(html);
	    //setBounds(0,0,200,200);
	    // getDemoPanel().add(scroller, BorderLayout.CENTER);
	} catch (Exception e) {
	    e.printStackTrace();
	    System.err.println("Failed to open " + url);
	    url = null;
	}
	/* } catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }*/
    }

    public HyperlinkListener createHyperLinkListener() {
	return new HyperlinkListener() {

		public void hyperlinkUpdate(HyperlinkEvent e) {
		    if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			if (e instanceof HTMLFrameHyperlinkEvent) {
			    ((HTMLDocument)html.getDocument()).processHTMLFrameHyperlinkEvent((HTMLFrameHyperlinkEvent)e);
			} else {
			    try {
				html.setPage(e.getURL());
			    } catch (IOException ioe) {
				System.out.println("IOE: " + ioe);
			    }
			}
		    }
		}

	    };
    }

}
