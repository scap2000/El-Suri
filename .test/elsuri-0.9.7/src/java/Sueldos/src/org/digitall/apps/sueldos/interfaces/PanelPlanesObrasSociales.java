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
 * PanelPlanesObrasSociales.java
 *
 * */
package org.digitall.apps.sueldos.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.JPanel;

import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;

public class PanelPlanesObrasSociales extends BasicPrimitivePanel {

    private JPanel jPanel1 = new JPanel();
    private BasicPanel searchPanel = new BasicPanel("Buscar");
    private BorderLayout borderLayout1 = new BorderLayout();

    private int[] sizeColumnList = { 106, 221, 211, 77};
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Planes", dataRow);
    private Vector headerList = new Vector();
    
    private TFInput tfNumber = new TFInput(DataTypes.STRING, "SearchButton", false);
    
    private FindButton btnSearch = new FindButton();
    private AddButton btnAddPlan = new AddButton();
    private ModifyButton btnEditPlan = new ModifyButton();
    private DeleteButton btnDeletePlan = new DeleteButton();

    public PanelPlanesObrasSociales() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(660, 354));
	this.setPreferredSize(new Dimension(660, 512));
        listPanel.setPreferredSize(new Dimension(400, 250));
        jPanel1.setLayout(borderLayout1);
        searchPanel.add(btnSearch, null);
        searchPanel.add(tfNumber, null);
        jPanel1.add(searchPanel, BorderLayout.NORTH);
        jPanel1.add(listPanel, BorderLayout.CENTER);
        this.add(jPanel1, BorderLayout.CENTER);
        searchPanel.setLayout(null);
        searchPanel.setPreferredSize(new Dimension(1, 70));
        tfNumber.setBounds(new Rectangle(75, 15, 295, 35));
        btnSearch.setBounds(new Rectangle(410, 25, 35, 30));
	btnSearch.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnSearch_actionPerformed(e);
				 }

			     }
	);
	setHeaderList();
        addButton(btnDeletePlan);
        addButton(btnEditPlan);
        addButton(btnAddPlan);
	btnAddPlan.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnAddPlan_actionPerformed(e);
				}

			    }
	);
        btnEditPlan.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e) {
                                    btnEditPlan_actionPerformed(e);
                                }

                            }
        );
        btnDeletePlan.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e) {
                                    btnDeletePlan_actionPerformed(e);
                                }

                            }
        );
	
        btnAddPlan.setToolTipText("Agregar");
        
        btnAddPlan.setEnabled(false);
        btnEditPlan.setEnabled(false);
        btnDeletePlan.setEnabled(false);
  }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("Código");
	headerList.addElement("Obra Social");
	headerList.addElement("Plan");
	headerList.addElement("$ Importe");
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent e) {
					       if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						   
					       } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON3) {
						   int index = listPanel.getTable().rowAtPoint(e.getPoint());
						   listPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
					       }
					   }

				       }
	);
	String params = "";
	listPanel.setParams("sueldos.getallplanesobrassociales", params, headerList);
	listPanel.refresh(params);
    }

    public void refresh() {
	String params = "";
	listPanel.refresh(params);
    }

    private void btnSearch_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void btnAddPlan_actionPerformed(ActionEvent e) {
	
    }

    private void btnEditPlan_actionPerformed(ActionEvent e) {
        
    }
    private void btnDeletePlan_actionPerformed(ActionEvent e) {
        
    }


}
