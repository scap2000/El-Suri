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
 * DistinguishableResourcesList.java
 *
 * */
package org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.apps.resourcescontrol_091.classes.DistinguishableResource;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.grid.TableTransferHandler;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class DistinguishableResourcesList extends BasicPrimitivePanel {

    private BasicPanel contentPanel = new BasicPanel();
    private BasicPanel searchPanel = new BasicPanel();

    private TFInput tfSearchResource = new TFInput(DataTypes.STRING, "FindResource",false);
    private CBInput cbDistinguishableResources = new CBInput(0,"DistinguishableResources",false);
    
    private int[] sizeColumnList = {205, 110, 90, 90, 105, 80 , 205};
    private Vector resourcesHeader = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel resourcesPanel = new GridPanel(50000, sizeColumnList, "Listado de Recursos Distinguibles", dataRow);
    
    private FindButton btnFind = new FindButton();
    private AddButton btnAdd = new AddButton();
    private ModifyButton btnEdit = new ModifyButton();
    
    private DistinguishableResource distinguishableResource;
    private DistinguishableResourcesMgmt distinguishableResourcesMgmt;
    private CloseButton btnClose = new CloseButton();
    private Vector distinResourcesSelected = new Vector();
    
    public DistinguishableResourcesList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout(null);
	this.setSize(new Dimension(710, 388));
	this.setPreferredSize(new Dimension(710, 515));
	tfSearchResource.setBounds(new Rectangle(50, 10, 185, 35));
	resourcesPanel.setBounds(new Rectangle(5, 75, 700, 270));
	contentPanel.setBounds(new Rectangle(5, 5, 700, 500));
	contentPanel.setLayout(null);
	contentPanel.setSize(new Dimension(700, 515));
	searchPanel.setBounds(new Rectangle(5, 10, 690, 50));
	searchPanel.setLayout(null);
        cbDistinguishableResources.setBounds(new Rectangle(290, 10, 335, 35));
	btnFind.setBounds(new Rectangle(660, 20, 30, 25));
	btnFind.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      btnFind_actionPerformed(e);
				  }

			      }
	);
	btnAdd.setBounds(new Rectangle(560, 525, 40, 25));
	btnAdd.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAdd_actionPerformed(e);
			      }

			  }
	);
        searchPanel.add(cbDistinguishableResources, null);
        searchPanel.add(tfSearchResource, null);
        searchPanel.add(btnFind, null);
        btnEdit.setBounds(new Rectangle(610, 525, 40, 25));
	btnClose.setBounds(new Rectangle(610, 525, 40, 25));
	btnClose.setToolTipText("Dar de baja a recurso");
	btnEdit.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnEdit_actionPerformed(e);
			       }

			   }
	);
	btnClose.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnClose_actionPerformed(e);
			       }

			   }
	);
	contentPanel.add(resourcesPanel, null);
	contentPanel.add(searchPanel, null);
	this.add(contentPanel, null);
	this.addButton(btnEdit);
	this.addButton(btnClose);
	this.addButton(btnAdd);
	resourcesPanel.getTable().setDragEnabled(true);
	resourcesPanel.getTable().setTransferHandler(new TableTransferHandler());
        tfSearchResource.getTextField().addKeyListener(new KeyAdapter() {

                                     public void keyReleased(KeyEvent e) {
                                         tfSearchResource_keyReleased(e);
                                     }

                                 }
        );
        cbDistinguishableResources.autoSize();
        btnEdit.setEnabled(false);
	btnClose.setEnabled(false);
	setResourcesHeader();
        btnAdd.setEnabled(false);
        btnAdd.setVisible(false);
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
    }
    
    public void refresh() {
	String params = ""+ cbDistinguishableResources.getSelectedValue();
	resourcesPanel.refresh(params);
        btnEdit.setEnabled(false);
    }

    private void setResourcesHeader() {
	resourcesHeader.removeAllElements();
        resourcesHeader.addElement("*"); //iddistinguishableresource
        resourcesHeader.addElement("*"); //idresource
        resourcesHeader.addElement(Environment.lang.getProperty("Resource"));
        resourcesHeader.addElement("*");
        resourcesHeader.addElement(Environment.lang.getProperty("Brand"));
        resourcesHeader.addElement("*");
        resourcesHeader.addElement("Identificación");
        resourcesHeader.addElement("Nº Patrimonio");
        resourcesHeader.addElement("Estado");
        resourcesHeader.addElement("Fecha Adq.");
        resourcesHeader.addElement("*");
        resourcesHeader.addElement("*");
        resourcesHeader.addElement("*");
        resourcesHeader.addElement("*");
        resourcesHeader.addElement("Asignado a");
        resourcesHeader.addElement("*");
        resourcesHeader.addElement("*");
        resourcesHeader.addElement("*");
        resourcesHeader.addElement("*");
	 
        resourcesPanel.getTable().addMouseListener(new MouseAdapter() {
                                              public void mouseClicked(MouseEvent e) {
                                                  if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                                                      loadObjectSelected();    
                                                      btnEdit.setEnabled(true);
						      btnClose.setEnabled(true);
                                                  } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
                                                      loadMgmt(false);
                                                  }
                                              }
                                          }
        );
        String params = "-1";
        resourcesPanel.setParams("resourcescontrol.getAllDistinguishableResources", params, resourcesHeader);
    }

    
    private void loadObjectSelected(){
	if (!dataRow.isEmpty()){
            distinguishableResource = new DistinguishableResource();
            distinguishableResource.setIdDistinguishableResource(Integer.parseInt("" + dataRow.elementAt(0)));
            distinguishableResource.retrieveData();
	}
    }
    
    private void btnFind_actionPerformed(ActionEvent e) {
	if(cbDistinguishableResources.getCombo().getItemCount() > 0){
	    refresh();
	}
    }

    private void loadMgmt(boolean _addAction){
	if (_addAction){
            distinguishableResource = new DistinguishableResource();
	}
        distinguishableResourcesMgmt = new DistinguishableResourcesMgmt();
	ExtendedInternalFrame distinguishableResourcesMgmtContainer = new ExtendedInternalFrame("Agregar/Modificar");
	distinguishableResourcesMgmtContainer.setCentralPanel(distinguishableResourcesMgmt);
        distinguishableResourcesMgmt.setDistinguishableResource(distinguishableResource);
        distinguishableResourcesMgmt.setParent(this);
	distinguishableResourcesMgmtContainer.show();
    }
    
    private void btnAdd_actionPerformed(ActionEvent e) {
	loadMgmt(true);
    }

    private void btnEdit_actionPerformed(ActionEvent e) {
	loadMgmt(false);
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	//Dar de baja a uno o mas recurso
	distinResourcesSelected = resourcesPanel.getSelectedsID();
	if(distinResourcesSelected.size() > 0){
	    if(Advisor.question("Aviso","¿Seguro que quiere dar de baja a " + distinResourcesSelected.size() + " recurso/s?")){
		Patrimonio patrimonio = new Patrimonio();
		Vector resultadoBaja = patrimonio.darBaja(distinResourcesSelected);
		String mensaje = (resultadoBaja).elementAt(0).toString();
		boolean dadoBaja = (Boolean)(resultadoBaja).elementAt(1);
		if(dadoBaja){
		    Advisor.messageBox("Se ha realizado la baja con exito","Exito");
		}else{
		    Advisor.messageBox(mensaje,"Error");
		}
	    }
	}else{
	    Advisor.messageBox("Debe seleccionar al menos un recurso","Error");
	}
	refresh();
    }
    
    private void tfSearchResource_keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){         
            String params = "'"+ tfSearchResource.getValue().toString() +"'";
            cbDistinguishableResources.loadJCombo(LibSQL.exFunction("resourcescontrol.getDistinguishableResources", params));
        }
    }
    
}
