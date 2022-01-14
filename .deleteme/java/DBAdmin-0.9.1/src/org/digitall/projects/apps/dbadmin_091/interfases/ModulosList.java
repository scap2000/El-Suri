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
 * ModulosList.java
 *
 * */
package org.digitall.projects.apps.dbadmin_091.interfases;
	
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.common.resourcescontrol.classes.Brands;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.projects.apps.dbadmin_091.classes.Modulos;

public class ModulosList extends BasicDialog {
//public class ModulosList extends JDialog {

    private FindButton btnFind = new FindButton();
    private AssignButton btnAssign = new AssignButton(true);
    private AddButton btnNew = new AddButton();
    
    private int[] sizeColumnList = { 41, 292 };
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(50000, sizeColumnList, "Módulos", dataRow);
    
    private BasicPanel panelData = new BasicPanel();
    private TFInput tfName = new TFInput(DataTypes.STRING, "Nombre", false);
    
    private Modulos modulo;
    private boolean addAction = true;

    public ModulosList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(383, 295));
	this.getContentPane().setLayout(null);
	listPanel.setBounds(new Rectangle(0, 70, 380, 200));
	panelData.setBounds(new Rectangle(0, 0, 380, 70));
	panelData.setLayout(null);
	tfName.setBounds(new Rectangle(20, 20, 210, 35));
	tfName.setSize(new Dimension(210, 35));
	btnAssign.setBounds(new Rectangle(240, 30, 40, 25));
	btnAssign.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAssign_actionPerformed(e);
				 }

			     }
	);
        btnNew.setBounds(new Rectangle(290, 30, 40, 25));
	btnNew.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnNew_actionPerformed(e);
			      }

			  }
	);
        btnFind.setBounds(new Rectangle(335, 30, 40, 25));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);
	panelData.add(btnFind, null);
	panelData.add(btnNew, null);
	panelData.add(tfName, null);
	panelData.add(btnAssign, null);
	this.add(panelData, BorderLayout.NORTH);
	this.add(listPanel, BorderLayout.CENTER);
	this.setTitle("Nuevo Módulo");
	panelData.setBorder(BorderPanel.getBorderPanel("Datos de Módulo"));
	panelData.setMinimumSize(new Dimension(1, 70));
	panelData.setPreferredSize(new Dimension(1, 70));
	setHeaderList();
	btnAssign.setToolTipText("Agregar/Modificar módulo");
	btnNew.setToolTipText("Limpiar campos");
	listPanel.refresh("");
}

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("ID");
	headerList.addElement("Nombre");
	headerList.addElement("*");
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent e) {
					       if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						   loadUnit();
						   loadData();
					       }
					   }

				       }
	);
	String params = "" ;
	//listPanel.setParams("admin.getallmodulosbyoffset", params, headerList);
	listPanel.setParams("admin.getallmodulos", params, headerList);
    }

    public void refresh() {
	String params = "";
	listPanel.refresh(params);
    }

    private void loadUnit() {
	if (modulo == null) {
	    modulo = new Modulos(Integer.parseInt("" + dataRow.elementAt(0)), dataRow.elementAt(1).toString());
	} else {
	    modulo.setIdModulo(Integer.parseInt("" + dataRow.elementAt(0)));
	    modulo.setNombre(dataRow.elementAt(1).toString());
	}
    }

    private void loadData() {
	tfName.setValue(modulo.getNombre());
	addAction = false;
    }

    private void clearData() {
	tfName.setValue("");
	addAction = true;
	tfName.getTextField().requestFocus();
    }

    private void btnNew_actionPerformed(ActionEvent e) {
	clearData();
    }

    private void btnAssign_actionPerformed(ActionEvent e) {
	if (addAction) {
	    modulo = new Modulos();
	}
	if(!tfName.getString().equals("")){
	    modulo.setNombre(tfName.getString());
	    modulo.saveData();
	    refresh();
	    clearData();
	}
	else{
	    Advisor.messageBox("Debe ingresar nombre de modulo", "Mensaje");
	}
	//refresh();
    }

    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }
}
