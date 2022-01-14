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
 * NewSystem.java
 *
 * */
package org.digitall.apps.systemmanager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import org.digitall.lib.components.JEntry;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.BasicTextField;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.sql.LibSQL;

//

public class NewSystem extends BasicDialog {

    private BasicPanel jPanel1 = new BasicPanel();
    private BasicLabel lblSystem = new BasicLabel();
    private BasicLabel lblDescription = new BasicLabel();
    private AcceptButton bAccept = new AcceptButton();
    private CloseButton bCancel = new CloseButton();
    private BasicTextField tfCr = new BasicTextField();
    private JEntry tfSystem = new JEntry();
    private BasicScrollPane spDescription = new BasicScrollPane();
    private JTextArea taDescription = new JTextArea();

    public NewSystem() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(347, 175));
	this.getContentPane().setLayout(null);
	this.setTitle("New System");
	this.setFont(new Font("Dialog", 1, 11));
	bAccept.setBounds(new Rectangle(290, 115, 45, 25));
	bAccept.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bAccept_actionPerformed(e);
		    }

		});
	bCancel.setBounds(new Rectangle(10, 115, 45, 25));
	bCancel.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bCancel_actionPerformed(e);
		    }

		});
	jPanel1.setBounds(new Rectangle(10, 10, 325, 100));
	jPanel1.setLayout(null);
	jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	lblSystem.setText("System:");
	lblSystem.setBounds(new Rectangle(30, 15, 50, 15));
	lblSystem.setFont(new Font("Dialog", 1, 11));
	lblSystem.setHorizontalAlignment(SwingConstants.RIGHT);
	lblDescription.setText("Description:");
	lblDescription.setBounds(new Rectangle(10, 35, 70, 15));
	lblDescription.setFont(new Font("Dialog", 1, 11));
	lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
	tfCr.setBounds(new Rectangle(85, 10, 30, 20));
	tfCr.setText("CR");
	tfCr.setFont(new Font("Dialog", 1, 11));
	tfCr.setEditable(false);
	tfCr.setBackground(Color.white);
	tfCr.setForeground(Color.red);
	tfCr.setHorizontalAlignment(BasicTextField.CENTER);
	tfSystem.setBounds(new Rectangle(115, 10, 195, 20));
	tfSystem.setFont(new Font("Dialog", 1, 11));
	tfSystem.setForeground(Color.red);
	spDescription.setBounds(new Rectangle(85, 35, 225, 50));
	spDescription.getViewport().setLayout(null);
	taDescription.setBounds(new Rectangle(0, 0, 225, 50));
	taDescription.setWrapStyleWord(true);
	taDescription.setLineWrap(true);
	taDescription.setFont(new Font("Dialog", 1, 11));
	taDescription.setForeground(Color.red);
	spDescription.getViewport().add(taDescription, null);
	jPanel1.add(spDescription, null);
	jPanel1.add(tfSystem, null);
	jPanel1.add(tfCr, null);
	jPanel1.add(lblDescription, null);
	jPanel1.add(lblSystem, null);
	this.getContentPane().add(jPanel1, null);
	this.getContentPane().add(bCancel, null);
	this.getContentPane().add(bAccept, null);
	org.digitall.lib.components.ComponentsManager.centerWindow(this);
    }

    private void bCancel_actionPerformed(ActionEvent e) {
	this.dispose();
    }

    private void bAccept_actionPerformed(ActionEvent e) {
	createSystem();
    }

    private void createSystem() {
	if (!tfSystem.getText().equals("")) {
	    String cr = tfCr.getText().toLowerCase().trim();
	    String sistema = tfSystem.getText().toLowerCase().trim();
	    String consultaInsert = "INSERT INTO admin.systems VALUES ((Select max(idsist)+1 From admin.systems),'" + cr + sistema + "','" + taDescription.getText().trim() + "','');";
	    //System.out.println("consultaInsert--> "+consultaInsert);
	    //            LibSQL.exActualizar('a', consultaInsert);
	    String createGroups = " CREATE GROUP " + cr + sistema + "_admin; CREATE GROUP " + cr + sistema + "_user; CREATE GROUP " + cr + sistema + "_query;";
	    if (LibSQL.exActualizar('a', consultaInsert + createGroups)) {
		org.digitall.lib.components.Advisor.messageBox("Create System Success!", "Message");
		this.dispose();
	    } else {
		org.digitall.lib.components.Advisor.messageBox("Error al intentar crear el nuevo sistema", "Error");
	    }
	} else {
	    org.digitall.lib.components.Advisor.messageBox("System field is Empty", "Error");
	}
    }

}
