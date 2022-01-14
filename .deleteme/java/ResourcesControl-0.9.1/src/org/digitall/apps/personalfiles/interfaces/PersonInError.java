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
 * PersonInError.java
 *
 * */
package org.digitall.apps.personalfiles.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Vector;

import javax.swing.JButton;

import org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin.Patrimonio;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.JScrollList;
import org.digitall.lib.components.JTArea;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.icons.IconTypes;

public class PersonInError extends BasicPrimitivePanel{
    private String defaultMje = "Error las personas siguientes no cumplen la condicion esperada.";
    private String mensaje = defaultMje;
    private BasicPanel content = new BasicPanel();
    private BasicPanel panelNorte = new BasicPanel();
    private BasicPanel panelCentro = new BasicPanel();
    private BasicLabel label = new BasicLabel(IconTypes.close_16x16);
    private JTArea taMesajeError = new JTArea();
    private CloseButton btnClose = new CloseButton();
    private JScrollList listPerson = new JScrollList();
    private Vector listado = new Vector();

    public PersonInError(String _mje,Vector _listado) {
	mensaje = _mje;
	listado = _listado;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public PersonInError() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public PersonInError(Vector _listado) {
	try {
	    listado = _listado;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    private void jbInit() throws Exception {
	content.setLayout(new BorderLayout());
	content.setSize(new Dimension(500, 300));
	content.setPreferredSize(new Dimension(500, 300));
	panelNorte.setSize(new Dimension(500, 100));
	panelNorte.setBounds(new Rectangle(0, 0, 500, 100));
	panelNorte.setPreferredSize(new Dimension(500, 100));
	panelNorte.setLayout(null);
	panelCentro.setLayout(null);
	panelNorte.setBorder(BorderPanel.getBorderPanel(""));
	panelCentro.setBorder(BorderPanel.getBorderPanel(""));
	panelCentro.setSize(new Dimension(500, 209));
	label.setBounds(new Rectangle(5, 30, 40, 35));
	label.setText("");
	taMesajeError.setText(mensaje);
	taMesajeError.setBounds(new Rectangle(50, 10, 490, 75));
	taMesajeError.setBackground(new Color(64, 64, 64));
	taMesajeError.setFont(new Font("Dialog", 1, 12));
	taMesajeError.setForeground(Color.white);
	taMesajeError.setEditable(false);
	listPerson.setBounds(new Rectangle(10, 5, 530, 195));
	panelNorte.add(taMesajeError, null);
	panelNorte.add(label,null);
	btnClose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					    btnClose_actionPerformed(e);
					}
				    }
	);
	this.setSize(new Dimension(553, 346));
	content.add(panelNorte,BorderLayout.NORTH);
	panelCentro.add(listPerson, null);
	content.add(panelCentro, BorderLayout.CENTER);
	this.add(content, BorderLayout.CENTER);
	if(listado.size() > 0){
	    cargarListaPersonas(listado);   
	}
	
	addButton(btnClose);
    }
    
    public void cargarListaPersonas(Vector _listado){
	listPerson.cargarLista(_listado);
    }

    public void setMensaje(String mensaje) {
	this.mensaje = mensaje;
	taMesajeError.setText(mensaje);
    }

    public String getMensaje() {
	return mensaje;
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }
    
    
}
