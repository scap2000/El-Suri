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
 * StreetList.java
 *
 * */
package org.digitall.common.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.resourcescontrol.classes.Street;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class StreetList extends BasicPrimitivePanel {

    private int[] sizeColumnList = { 270, 95 };
    private Vector headerList = new Vector(); 
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Calles", dataRow);
    private BasicPanel panelData = new BasicPanel();
    private TFInput tfName = new TFInput(DataTypes.STRING, "Name", false);
    private AssignButton btnAssign = new AssignButton(true);
    private AddButton btnNew = new AddButton();
    private boolean addAction = true;
    private FindButton btnFind = new FindButton();
    private Street street;
    private CBInput cbStreetTypes = new CBInput(0,"Type",false);

    public StreetList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(432, 308));
	listPanel.setBounds(new Rectangle(5, 80, 305, 310));
	panelData.setBounds(new Rectangle(5, 0, 305, 70));
	panelData.setLayout(null);
	tfName.setBounds(new Rectangle(10, 25, 265, 35));
	btnAssign.setBounds(new Rectangle(310, 35, 40, 25));
	btnAssign.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAssign_actionPerformed(e);
			      }

			  }
	);
	btnNew.setBounds(new Rectangle(345, 35, 40, 25));
	btnNew.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnNew_actionPerformed(e);
				 }

			     }
	);
	btnFind.setBounds(new Rectangle(380, 35, 40, 25));
        btnFind.setBounds(new Rectangle(380, 35, 40, 25));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);
        cbStreetTypes.setBounds(new Rectangle(10, 65, 265, 35));
        panelData.add(btnFind, null);
        panelData.add(btnNew, null);
        panelData.add(tfName, null);
        panelData.add(cbStreetTypes, null);
        panelData.add(btnAssign, null);
        this.add(panelData, BorderLayout.NORTH);
	this.add(listPanel, BorderLayout.CENTER);
	panelData.setBorder(BorderPanel.getBorderPanel("Agregar nueva Calle", Color.blue, Color.black));
	panelData.setMinimumSize(new Dimension(1, 100));
	panelData.setPreferredSize(new Dimension(1, 110));
	setHeaderList();
	btnAssign.setToolTipText("Agregar/Modificar un Calle");
	btnNew.setToolTipText("Limpiar campos");
        cbStreetTypes.autoSize();
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
        headerList.addElement("*");
	headerList.addElement("Calle");
        headerList.addElement("*");
	headerList.addElement("Tipo");
        headerList.addElement("*");
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent e) {
					       if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						   loadStreet();
						   loadData();
					       }
					   }

				       }
	);
	listPanel.setParams("tabs.getAllStreets", "''", headerList);
        loadCombo();
    }

    public void refresh() {
	String params = "'" + tfName.getString() + "'";
	listPanel.refresh(params);
    }

    private void clearData() {
	tfName.setValue("");
        cbStreetTypes.setSelectedID(0);
	addAction = true;
    }

    private void btnNew_actionPerformed(ActionEvent e) {
	clearData();
    }

    private void btnAssign_actionPerformed(ActionEvent e) {
	if (addAction) {
            street.setIdstreet(-1);
	}
	street.setName(tfName.getString());
        street.setType(Integer.parseInt(cbStreetTypes.getSelectedValue().toString()));
	if (street.saveData() > 0) {
	    refresh();        
	    clearData();
	} else {
            Advisor.messageBox("Ocurrió un error al grabar los datos","Error");
        }
    }

    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Puede Agregar/Modificar/Buscar Calles");
    }

    
    public void setStreetObject(Street _street){
        street = _street;
        cbStreetTypes.setSelectedID(street.getType());
    }

    private void loadStreet(){
        street.setIdstreet(Integer.parseInt(dataRow.elementAt(0).toString()));
        street.setIdLocation(Integer.parseInt(dataRow.elementAt(1).toString()));
        street.setName(dataRow.elementAt(2).toString());
        street.setType(Integer.parseInt(dataRow.elementAt(3).toString()));
        addAction = false;
    }
    
    private void loadData(){
        tfName.setValue(street.getName());
        cbStreetTypes.setSelectedID(street.getType());
    }

    private void loadCombo() {
        String params = "";
        cbStreetTypes.loadJCombo("tabs.getAllStreetTypes",params);
    }
}
