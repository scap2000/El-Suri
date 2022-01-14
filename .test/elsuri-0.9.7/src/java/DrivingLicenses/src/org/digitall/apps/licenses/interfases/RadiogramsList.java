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
 * RadiogramsList.java
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
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextArea;

import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicTextArea;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.apps.licenses.classes.Driver;
import org.digitall.apps.licenses.classes.Radiogram;


public class RadiogramsList extends BasicPrimitivePanel {

    private BasicPanel mainPanel = new BasicPanel();
    private BasicPanel northPanel = new BasicPanel("Buscar Conductores");
    private BasicPanel dataPanel = new BasicPanel();

    private int[] sizeColumnList = { 55, 180, 85, 100, 100 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Radiogramas recibidos", dataRow);
    private Vector headerList = new Vector();

    private BasicTextArea taReason = new BasicTextArea();
    
    private CloseButton btnClose = new CloseButton();
    private ModifyButton btnModifyRadiogram = new ModifyButton();
    private AddButton btnAddRadiogram = new AddButton();
    private FindButton btnFindDriver = new FindButton();

    private TFInput tfLastName = new TFInput(DataTypes.STRING,"LastName",false);
    private TFInput tfName = new TFInput(DataTypes.STRING,"Name",false);
    private TFInput tfIdentificationNumber = new TFInput(DataTypes.INTEGER,"IdentificationNumber",false);

    private Driver driverSelected;
    private Radiogram radiogram;

    public RadiogramsList() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        this.setSize(new Dimension(582, 498));
        //this.setPreferredSize(new Dimension(760, 512));
        mainPanel.setLayout(null);
        listPanel.setSize(new Dimension(505, 100));
        listPanel.setPreferredSize(new Dimension(400, 100));
        listPanel.setBounds(new Rectangle(5, 0, 565, 235));
        northPanel.setLayout(null);
        dataPanel.setBounds(new Rectangle(0, 65, 580, 395));
        
        dataPanel.setLayout(null);
        //mainPanel.add(listPanel, BorderLayout.CENTER);
        dataPanel.add(taReason, null);
        dataPanel.add(listPanel, BorderLayout.NORTH);
        dataPanel.add(taReason, BorderLayout.CENTER);
        northPanel.add(btnFindDriver, null);
        northPanel.add(tfIdentificationNumber, null);
        northPanel.add(tfName, null);
        northPanel.add(tfLastName, null);
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(dataPanel, BorderLayout.CENTER);
        this.add(mainPanel, BorderLayout.CENTER);
        btnClose.addActionListener(new ActionListener() {

                                 public void actionPerformed(ActionEvent e) {
                                     btnClose_actionPerformed(e);
                                 }

                             }
        );
        btnAddRadiogram.addActionListener(new ActionListener() {

                                 public void actionPerformed(ActionEvent e) {
                                     btnAddRadiogram_actionPerformed(e);
                                 }

                             }
        );
        btnFindDriver.addActionListener(new ActionListener() {

                                 public void actionPerformed(ActionEvent e) {
                                     btnFindDriver_actionPerformed(e);
                                 }

                             }
        );
        northPanel.setBounds(new Rectangle(0, 0, 580, 60));
        tfLastName.setBounds(new Rectangle(45, 20, 145, 35));
        tfLastName.setSize(new Dimension(145, 35));
        tfName.setBounds(new Rectangle(215, 20, 145, 35));
        tfIdentificationNumber.setBounds(new Rectangle(380, 20, 115, 35));
	tfIdentificationNumber.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refreshRadiograms();
		    }
		});
	tfLastName.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refreshRadiograms();
		    }
		});
	tfName.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refreshRadiograms();
		    }
		});
        btnFindDriver.setBounds(new Rectangle(515, 30, 25, 25));
        taReason.setBounds(new Rectangle(5, 245, 565, 140));
        btnModifyRadiogram.addActionListener(new ActionListener() {

                                 public void actionPerformed(ActionEvent e) {
                                     btnModifyRadiogram_actionPerformed(e);
                                 }

                             }
        );
        addButton(btnClose);
        addButton(btnAddRadiogram);
        addButton(btnModifyRadiogram);
        taReason.setEnabled(false);
        setHeaderList();
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
        super.setParentInternalFrame(_e);
        getParentInternalFrame().setInfo("Radiogramas recibidos");
    }
    
    private void setHeaderList() {
        headerList.removeAllElements();
        headerList.addElement("*");         //idradiogram
        headerList.addElement(Environment.lang.getProperty("Number"));
        headerList.addElement("*");         //lastname
        headerList.addElement("*");         //name
        headerList.addElement(Environment.lang.getProperty("Name"));
        headerList.addElement("*");         //identificationtype
        headerList.addElement(Environment.lang.getProperty("DNI"));
        headerList.addElement(Environment.lang.getProperty("StartDate"));
        headerList.addElement(Environment.lang.getProperty("ExpirationDate"));
        headerList.addElement("*");         //reason
        headerList.addElement("*");         //estado
        
        listPanel.getTable().addMouseListener(new MouseAdapter() {

                                           public void mouseClicked(MouseEvent e) {
                                               if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                                                   loadObjectSelected();
                                                   taReason.setText(radiogram.getReason());
                                               } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON3) {
                                                   int index = listPanel.getTable().rowAtPoint(e.getPoint());
                                                   listPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
                                               }
                                           }

                                       }
        );
        String params = "'','',-1";
        listPanel.setParams("licenses.getAllRadiograms", params, headerList);
    }

    private void btnFindDriver_actionPerformed(ActionEvent e) {
        refreshRadiograms();
    }


    private void loadObjectSelected() {
        if (!dataRow.isEmpty()) {
            radiogram = new Radiogram();
            radiogram.setIdradiogram(Integer.parseInt("" + dataRow.elementAt(0)));
            radiogram.setRadiogramnumber(Integer.parseInt("" + dataRow.elementAt(1)));
            radiogram.setLastname(dataRow.elementAt(2).toString());
            radiogram.setName(dataRow.elementAt(3).toString());
            radiogram.setIdidentificationtype(Integer.parseInt(""+ dataRow.elementAt(5).toString()));
            radiogram.setIdentificationnumber(Integer.parseInt(""+ dataRow.elementAt(6).toString()));
            radiogram.setDatefrom(Proced.setFormatDate(dataRow.elementAt(7).toString(),false));
            radiogram.setDateto(Proced.setFormatDate(dataRow.elementAt(8).toString(),false));
            radiogram.setReason(dataRow.elementAt(9).toString());
        }
    }

    public void refreshRadiograms() {
        int dni = -1;
	System.out.println("dni: "+tfIdentificationNumber.getString());
        if (!tfIdentificationNumber.getString().equals("0")) {
             dni = Integer.parseInt(tfIdentificationNumber.getString());
        }
        
        String params = "'"+ tfLastName.getString() +"','" + tfName.getString() +"',"+ dni;
        listPanel.refresh(params);
        taReason.setText("");
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
        getParentInternalFrame().setIcon(true);
    }
    
    private void btnAddRadiogram_actionPerformed(ActionEvent e) {
        RadiogramsMgmt radiogramsMgmt = new RadiogramsMgmt();
        radiogramsMgmt.setParentList(this);
        radiogramsMgmt.setRadiogram(new Radiogram());
        ExtendedInternalFrame RadiogramsMgmtContainer = new ExtendedInternalFrame("Nuevo Radiograma");
        RadiogramsMgmtContainer.setCentralPanel(radiogramsMgmt);
        RadiogramsMgmtContainer.show();
    }
    private void btnModifyRadiogram_actionPerformed(ActionEvent e) {
        RadiogramsMgmt radiogramsMgmt = new RadiogramsMgmt();
        radiogramsMgmt.setParentList(this);
        radiogramsMgmt.setRadiogram(radiogram);
        ExtendedInternalFrame RadiogramsMgmtContainer = new ExtendedInternalFrame("Modificar Radiograma");
        RadiogramsMgmtContainer.setCentralPanel(radiogramsMgmt);
        RadiogramsMgmtContainer.show();
    }

}
