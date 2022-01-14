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
 * HTMLBrowser.java
 *
 * */
package org.digitall.lib.misc;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.net.URL;

import java.util.EmptyStackException;

import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.icons.IconTypes;

public class HTMLBrowser extends BasicDialog {

    private PrintableEditorPane htmled;
    private BasicScrollPane htmlpanel = new BasicScrollPane();
    private BasicButton bimprimir = new BasicButton(IconTypes.PRINT_ICON_16x16);
    private CloseButton bcerrar = new CloseButton();
    private String command = "", Archivo = "";
    private BasicButton bvisorexterno = new BasicButton();

    public HTMLBrowser(String file) throws FileNotFoundException {
	try {
	    Archivo = file;
	    //      System.out.println("HTMLBrowser: " + Archivo);
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//htmled = new PrintableEditorPane(new URL("http","192.168.2.6",""));
	htmled = new PrintableEditorPane(new URL("file", "", Archivo));
	htmled.setEditable(false);
	this.setSize(new Dimension(800, 575));
	this.getContentPane().setLayout(null);
	this.setTitle("Visor de Informes");
	htmled.setText("");
	htmlpanel.setBounds(new Rectangle(5, 5, 780, 505));
	bimprimir.setText("Imprimir");
	bimprimir.setBounds(new Rectangle(5, 515, 108, 25));
	bimprimir.setMnemonic('I');
	bimprimir.setEnabled(false);
	bcerrar.setText("Cerrar");
	bcerrar.setBounds(new Rectangle(693, 515, 92, 25));
	bcerrar.setMnemonic('C');
	bvisorexterno.setText("Visor Externo p/imprimir");
	bvisorexterno.setBounds(new Rectangle(120, 515, 210, 25));
	bvisorexterno.setMnemonic('V');
	htmlpanel.getViewport().add(htmled, null);
	this.getContentPane().add(bvisorexterno, null);
	this.getContentPane().add(bcerrar, null);
	this.getContentPane().add(bimprimir, null);
	this.getContentPane().add(htmlpanel, null);
	htmled.addHyperlinkListener(new HyperlinkListener() {

		    public void hyperlinkUpdate(HyperlinkEvent link) {
			if (link.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			    try {
				htmled.setPage(link.getURL());
			    } catch (EmptyStackException e) {
				System.out.println("ErrorSTack01");
			    } catch (IOException x) {
				Advisor.messageBox("Error: no se pudo abrir el archivo " + link.getURL().toExternalForm() + "\n" + x.getMessage(), "true");
			    }
			}
		    }

		});
	bimprimir.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bimprimir_actionPerformed(e);
		    }

		});
	bcerrar.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bcerrar_actionPerformed(e);
		    }

		});
	bvisorexterno.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bvisorexterno_actionPerformed(e);
		    }

		});
	//  panelCenter.add(htmlpanel, null);
	//  this.getContentPane().add(panelCenter, BorderLayout.NORTH);
    }

    private void benviar_actionPerformed(ActionEvent e) {
	this.dispose();
    }

    private void bcerrar_actionPerformed(ActionEvent e) {
	this.dispose();
    }

    private void bimprimir_actionPerformed(ActionEvent e) {
	htmled.doPrintActions();
    }

    private void bvisorexterno_actionPerformed(ActionEvent e) {
	try {
	    if (System.getProperty("os.name").equals("Linux")) {
		command = "konqueror " + Archivo;
	    } else {
		command = "c:" + File.separator + "archivos de programa" + File.separator + "internet explorer" + File.separator + "iexplore " + Archivo;
	    }
	    //      System.out.println(command);
	    Process child = Runtime.getRuntime().exec(command);
	} catch (EmptyStackException x) {
	    System.out.println("ErrorSTack02");
	} catch (IOException x) {
	    Advisor.messageBox("Error al intentar utilizar el visor externo", "true");
	}
    }

}
