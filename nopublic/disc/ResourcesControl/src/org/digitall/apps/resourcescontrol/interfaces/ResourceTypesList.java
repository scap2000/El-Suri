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
 * ResourceTypesList.java
 *
 * */
package org.digitall.apps.resourcescontrol.interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.apps.resourcescontrol.classes.ResourceTypes;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CancelDataButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class ResourceTypesList extends BasicPrimitivePanel {
    private int[] sizeColumnList = {41, 278};
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Tipos de Recurso", dataRow);
    private BasicPanel panelData = new BasicPanel();
    private TFInput tfName = new TFInput(DataTypes.STRING, "Name", false);
     
    private CloseButton btnClose = new CloseButton();
    private AssignButton btnAdd = new AssignButton(true);
    private CancelDataButton btnCancel = new CancelDataButton();
    private BasicDialog parentContainer;
    private ResourceTypes resourceType;
    private boolean addAction = true;
    private FindButton btnFind = new FindButton();
    
    public ResourceTypesList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(350, 440));
	listPanel.setBounds(new Rectangle(5, 80, 340, 310));
	panelData.setBounds(new Rectangle(5, 0, 340, 70));
	panelData.setLayout(null);
	tfName.setBounds(new Rectangle(15, 25, 165, 35));
	//new Rectangle(0, 400, 315, 2));
	//setSize(new Dimension(350, 2));
	btnClose.setBounds(new Rectangle(305, 410, 40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	btnAdd.setBounds(new Rectangle(190, 35, 40, 25));
	btnAdd.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAdd_actionPerformed(e);
			      }

			  }
	);
	btnCancel.setBounds(new Rectangle(240, 35, 40, 25));
	btnCancel.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnCancel_actionPerformed(e);
				 }

			     }
	);
	btnFind.setBounds(new Rectangle(290, 35, 40, 25));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);
	panelData.add(btnFind, null);
	panelData.add(btnCancel, null);
	panelData.add(tfName, null);
	panelData.add(btnAdd, null);
	this.add(btnClose, null);
	 
	this.add(panelData, null);
	this.add(listPanel, null);
	panelData.setBorder(BorderPanel.getBorderPanel("Agregar nuevo Tipo de Recurso", Color.blue, Color.black));
	setHeaderList();
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
	});
	listPanel.setParams("resourcescontrol.getAllResourceTypes", "''", headerList);
    }

    public void refresh() {
	String params = "''";
	listPanel.refresh(params);
    }
    
    private void loadUnit(){
	if (resourceType == null){
	    resourceType = new ResourceTypes(Integer.parseInt(""+ dataRow.elementAt(0)), dataRow.elementAt(1).toString());
	} else {
	    resourceType.setIdResourceType(Integer.parseInt(""+ dataRow.elementAt(0)));
	    resourceType.setName(dataRow.elementAt(1).toString());
	}
    }
    
    private void loadData(){
	tfName.setValue(resourceType.getName());
	addAction = false;
    }

    private void clearData() {
	tfName.setValue("");
	addAction = true;
    }
    
    private void btnCancel_actionPerformed(ActionEvent e) {
	clearData();
    }

    public boolean saveData(){
	if (addAction){
	    resourceType = new ResourceTypes();
	}
	resourceType.setName(tfName.getString());
	
	if (resourceType.saveData()>=0){
	    refresh();
	    clearData();    
	    return true;
	} else {
	    return false;
	}
    }
    
    private void btnAdd_actionPerformed(ActionEvent e) {
	saveData();
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	parentContainer.setVisible(false);
    }

    public void setParentContainer(BasicDialog parentContainer) {
	this.parentContainer = parentContainer;
	
    }

    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }
}
