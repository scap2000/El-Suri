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
 * AddGroup.java
 *
 * */
package org.digitall.apps.systemmanager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.ComponentsManager;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.sql.LibSQL;

//

public class AddGroup extends BasicDialog {

    private AcceptButton bAcept = new AcceptButton();
    private CloseButton bCancel = new CloseButton();
    private BasicLabel jLabel1 = new BasicLabel();
    private String groupName = "", idsist = "", sistema = "";
    private int error = 0;
    private PanelNewGroup jPanel1 = new PanelNewGroup();
    final String TEXTO = "New Group";

    public AddGroup(String _idsist) {
	try {
	    idsist = _idsist;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(287, 167));
	this.getContentPane().add(jPanel1, null);
	this.getContentPane().add(jLabel1, null);
	this.getContentPane().add(bCancel, null);
	this.getContentPane().add(bAcept, null);
	this.getContentPane().setLayout(null);
	bAcept.setBounds(new Rectangle(345, 150, 40, 25));
	bAcept.setSize(new Dimension(40, 25));
	bCancel.setBounds(new Rectangle(10, 150, 40, 25));
	bCancel.setMnemonic('C');
	bCancel.setSize(new Dimension(40, 25));
	jLabel1.setText("Group data:");
	jLabel1.setBounds(new Rectangle(10, 0, 70, 15));
	jLabel1.setFont(new Font("Dialog", 1, 11));
	this.getContentPane().setLayout(null);
	this.setTitle("New Group");
	bAcept.setBounds(new Rectangle(235, 110, 40, 25));
	bAcept.setSize(new Dimension(40, 25));
	bAcept.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bAcept_actionPerformed(e);
		    }

		});
	bCancel.setBounds(new Rectangle(5, 110, 40, 25));
	bCancel.setMnemonic('C');
	bCancel.setSize(new Dimension(40, 25));
	bCancel.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bCancel_actionPerformed(e);
		    }

		});
	jLabel1.setText("Enter the Group name:");
	jLabel1.setBounds(new Rectangle(5, 40, 135, 15));
	jLabel1.setFont(new Font("Dialog", 1, 11));
	jPanel1.setBounds(new Rectangle(5, 55, 271, 47));
	jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	this.getContentPane().add(jLabel1, null);
	this.getContentPane().add(bCancel, null);
	this.getContentPane().add(bAcept, null);
	ComponentsManager.centerWindow(this);
	loadData();
	setFields();
    }

    public String getSystem() {
	return sistema;
    }

    private void setFields() {
	jPanel1.setSystem(sistema);
    }

    private boolean control() {
	boolean valido = true;
	if (jPanel1.getGroupName().equals("")) {
	    valido = false;
	    error = 1;
	}
	if (jPanel1.getGroupName().equals("admin")) {
	    valido = false;
	    error = 2;
	} else if (jPanel1.getGroupName().equals("user")) {
	    valido = false;
	    error = 3;
	} else if (jPanel1.getGroupName().equals("query")) {
	    valido = false;
	    error = 4;
	}
	if (jPanel1.getGroupName().length() > 8) {
	    valido = false;
	    error = 5;
	}
	return valido;
    }

    private void bAcept_actionPerformed(ActionEvent e) {
	if (control()) {
	    groupName = sistema + "_" + jPanel1.getGroupName();
	    String cadena = "CREATE GROUP " + groupName.trim();
	    if (LibSQL.exActualizar('a', cadena)) {
		//******** METODO PARA ASIGNARLE, AL GRUJPO CREADO, PERMISO PARA USAR LOS ESQUEMAS DEL SISTEMA ********
		cadena = "SELECT DISTINCT(esquema), idtabla FROM admin.tables WHERE idtabla IN" + " (Select idtabla From admin.syst_tables, admin.systems Where" + " syst_tables.idsist = systems.idsist AND systems.idsist = " + idsist + ")";
		ResultSet esquemas = org.digitall.lib.sql.LibSQL.exQuery(cadena);
		try {
		    if (esquemas.next()) {
			String usageSchema = "GRANT USAGE ON SCHEMA " + esquemas.getString("esquema") + " TO " + groupName;
			LibSQL.exActualizar('a', usageSchema);
		    }
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}
		//*************************************************************
		Advisor.messageBox("The Group was registered", "Success");
		this.dispose();
	    }
	} else {
	    errores();
	}
    }

    private void errores() {
	switch (error) {
	    case 1:
		Advisor.messageBox("Enter the name of the group", "Error");
		break;
	    case 2:
		Advisor.messageBox("No puede usar para el grupo el nombre ADMIN", "Error");
		break;
	    case 3:
		Advisor.messageBox("No puede usar para el grupo el nombre USER", "Error");
		break;
	    case 4:
		Advisor.messageBox("No puede usar para el grupo el nombre QUERY", "Error");
		break;
	    case 5:
		Advisor.messageBox("El nombre del Grupo debe tener a lo sumo 8 caracteres", "Error");
		break;
	}
    }

    private void bCancel_actionPerformed(ActionEvent e) {
	this.dispose();
    }

    private void loadData() {
	sistema = org.digitall.lib.sql.LibSQL.getCampo("SELECT nombre FROM admin.systems WHERE idsist = " + idsist);
    }

}
