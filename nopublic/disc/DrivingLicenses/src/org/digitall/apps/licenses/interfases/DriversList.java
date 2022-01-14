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
 * DriversList.java
 *
 * */
package org.digitall.apps.licenses.interfases;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.apps.licenses.classes.Driver;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;

public class DriversList extends BasicPrimitivePanel {

    private int[] sizeColumnList = { 205, 95, 285, 120 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Listado de Conductores", dataRow){
	public void calculate() {
	    controlBotones();
	}
    }
    ;
    private Vector headerList = new Vector();
    
    private BasicPanel searchPanel = new BasicPanel("Buscar");
    
    private TFInput tfLastName = new TFInput(DataTypes.STRING, "LastName", false);
    private TFInput tfName = new TFInput(DataTypes.STRING, "Name", false);
    private TFInput tfDNI = new TFInput(DataTypes.INTEGER, "DNI", false);
    private TFInput tfLicenseNumber = new TFInput(DataTypes.INTEGER, "LicenseNumber", false);

    private FindButton btnSearch = new FindButton();
    private CloseButton btnClose = new CloseButton();
    private AddButton btnNew = new AddButton();
    private ModifyButton btnEdit = new ModifyButton();

    private Driver driverSelected;
    private DriversFormMgmt driversFormMgmt;

    public DriversList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(760, 416));
	this.setPreferredSize(new Dimension(760, 512));
	searchPanel.setBounds(0, 0, 750, 200);
        searchPanel.add(tfLicenseNumber, null);
        searchPanel.add(btnSearch, null);
        searchPanel.add(tfDNI, null);
        searchPanel.add(tfName, null);
        searchPanel.add(tfLastName, null);
        this.add(searchPanel, BorderLayout.NORTH);
        this.add(listPanel, BorderLayout.CENTER);
        addButton(btnClose);
        addButton(btnNew);
        addButton(btnEdit);
	searchPanel.setBounds(new Rectangle(5, 0, 750, 85));
	searchPanel.setLayout(null);
	searchPanel.setMinimumSize(new Dimension(1, 100));
	searchPanel.setPreferredSize(new Dimension(1, 70));
	tfLastName.setBounds(new Rectangle(20, 25, 195, 35));
	tfName.setBounds(new Rectangle(235, 25, 195, 35));
	tfDNI.setBounds(new Rectangle(450, 25, 115, 35));
	btnSearch.setBounds(new Rectangle(720, 35, 30, 25));
        btnSearch.setSize(new Dimension(30, 25));
        btnSearch.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnSearch_actionPerformed(e);
				 }

			     }
	);
        tfLicenseNumber.setBounds(new Rectangle(585, 25, 115, 35));
        setHeaderList();
        btnClose.addActionListener(new ActionListener() {

                                 public void actionPerformed(ActionEvent e) {
                                     btnClose_actionPerformed(e);
                                 }

                             }
        );
        btnNew.addActionListener(new ActionListener() {

                                 public void actionPerformed(ActionEvent e) {
                                     btnNew_actionPerformed(e);
                                 }

                             }
        );
        btnEdit.addActionListener(new ActionListener() {

                                 public void actionPerformed(ActionEvent e) {
                                     btnEdit_actionPerformed(e);
                                 }

                             }
        );
	tfLastName.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});
	tfName.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});
	tfDNI.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});
	tfLicenseNumber.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});
	listPanel.getTable().addMouseListener(new MouseAdapter() {

						 public void mouseClicked(MouseEvent e) {
						     loadObjectSelected();
						     if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
							     loadDriverFormsMgmt();
						     } else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						     }
						 }

					     }
	);
	btnEdit.setEnabled(false);
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
        super.setParentInternalFrame(_e);
        getParentInternalFrame().setInfo("Seleccione un conductor para modificar sus datos o agregue uno nuevo");
    }
    
    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");         //iddriver
        headerList.addElement(Environment.lang.getProperty("Name"));
        headerList.addElement("*");         //ididentificationtype
        headerList.addElement("*");         //identification_tabs.name
        headerList.addElement(Environment.lang.getProperty("DNI"));
        headerList.addElement(Environment.lang.getProperty("Address"));
	headerList.addElement("*");         //birthdate
        headerList.addElement("*");         //sex
        headerList.addElement(Environment.lang.getProperty("Nationality"));
        headerList.addElement("*");         //Kind
        headerList.addElement("*");         //boodgroup
        headerList.addElement("*");         //rhfactor
        headerList.addElement("*");         //photo
        headerList.addElement("*");         //estado
        
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent e) {
					       if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						   loadObjectSelected();
					       } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON3) {
						   int index = listPanel.getTable().rowAtPoint(e.getPoint());
						   listPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
					       }
					   }

				       }
	);
        
	String params = "'','',-1,-1";
	listPanel.setParams("licenses.getAlldrivers", params, headerList);
    }

    public void refresh() {
        int dni = -1;
        if (!tfDNI.getString().equals("")) {
            dni = Integer.parseInt("" + tfDNI.getString());
        }
        String params = "'"+ tfName.getString() +"','"+ tfLastName.getString() +"',"+ dni +",-1";
	listPanel.refresh(params);
        btnEdit.setEnabled(false);
    }

    private void loadObjectSelected() {
	if (!dataRow.isEmpty()) {
	    driverSelected = new Driver();
	    driverSelected.setIdDriver(Integer.parseInt("" + dataRow.elementAt(0)));
	    driverSelected.retrieveData();
	    btnEdit.setEnabled(true);
	} else {
	    driverSelected = new Driver();
            btnEdit.setEnabled(false);
        }
    }

    private void btnSearch_actionPerformed(ActionEvent e) {
	refresh();
    }
   
    private void btnClose_actionPerformed(ActionEvent e) {
        getParentInternalFrame().setIcon(true);
    }
    
    private void btnNew_actionPerformed(ActionEvent e) {
        driverSelected = new Driver();
        loadDriverFormsMgmt();
    }
    
    private void btnEdit_actionPerformed(ActionEvent e) {
	driverSelected = new Driver();
	loadObjectSelected();
        loadDriverFormsMgmt();
    }
   
    private void loadDriverFormsMgmt() {
        driversFormMgmt = new DriversFormMgmt();
        driversFormMgmt.setDriverSelected(driverSelected);
        driversFormMgmt.setParentList(this);
        ExtendedInternalFrame driversFormsMgmtContainer = new ExtendedInternalFrame("Ficha de Conductor");
        driversFormsMgmtContainer.setCentralPanel(driversFormMgmt);
        driversFormsMgmtContainer.show();
    }
    
    private void controlBotones(){
	btnEdit.setEnabled(false);
    }
   
}
