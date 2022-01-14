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
 * ApplicationConfigPanel.java
 *
 * */
package org.digitall.common.legalprocs.manager;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import org.digitall.lib.components.JIntEntry;
import org.digitall.lib.components.Tree;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.inputpanels.TFInputPanel;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.icons.IconTypes;
import org.digitall.lib.sql.LibSQL;

//

public class ApplicationConfigPanel extends BasicContainerPanel {

    private Tree treeMan = new Tree();
    private JTree appsTree;
    private BasicScrollPane jsptree = new BasicScrollPane();
    private BasicPanel jpShowApplications = new BasicPanel();
    private BasicPanel jpAddApplication = new BasicPanel();
    private BasicPanel jpVencimientos = new BasicPanel();
    private CloseButton btnClose = new CloseButton();
    private AddButton btnAdd = new AddButton();
    private BasicLabel lblRojo = new BasicLabel(IconTypes.CRFileRed);
    private BasicLabel lblBlack = new BasicLabel(IconTypes.CRFileBlack);
    private BasicLabel lblBlue = new BasicLabel(IconTypes.CRFileBlue);
    private BasicLabel lblGreen = new BasicLabel(IconTypes.CRFileGreen);
    private BasicLabel lblYellow = new BasicLabel(IconTypes.CRFileYellow);
    private BasicLabel lblBlackDays = new BasicLabel();
    private BasicLabel lblRedDays = new BasicLabel();
    private BasicLabel lblYellowDays = new BasicLabel();
    private BasicLabel lblBlueDays = new BasicLabel();
    private BasicLabel lblGreenDays = new BasicLabel();
    private BasicLabel lblVencimientos = new BasicLabel();
    private JIntEntry tfBlackDays = new JIntEntry();
    private JIntEntry tfRedDays = new JIntEntry();
    private JIntEntry tfBlueDays = new JIntEntry();
    private JIntEntry tfYellowDays = new JIntEntry();
    private JIntEntry tfGreenDays = new JIntEntry();
    private TFInputPanel tfipApplicationName = new TFInputPanel(DataTypes.STRING, "ApplicationName", true, true);
    private TFInputPanel tfipTermDays = new TFInputPanel(DataTypes.INTEGER, "TermDays", true, true);
    private final int FRAME = 1;
    private final int INTERNALFRAME = 2;
    private final int DIALOG = 3;
    private int parentType = -1;
    private Component parent;
    private BasicPanel jpArbol = new BasicPanel();
    private BasicLabel lblVencimientos1 = new BasicLabel();
    private AcceptButton btnUpdateDays = new AcceptButton();
    private BasicCheckBox chkUpdateDays = new BasicCheckBox();
    private int error = 0, idApplicationTab = 0;
    private String appTabName;
    private BasicCheckBox chkEmailBlack = new BasicCheckBox();
    private BasicCheckBox chkMailRed = new BasicCheckBox();
    private BasicCheckBox chkMailYellow = new BasicCheckBox();
    private BasicCheckBox chkMailGreen = new BasicCheckBox();
    private BasicCheckBox chkMailBlue = new BasicCheckBox();

    public ApplicationConfigPanel(BasicInternalFrame _parent) {
	try {
	    parent = _parent;
	    parentType = INTERNALFRAME;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public ApplicationConfigPanel(BasicDialog _parent) {
	try {
	    parent = _parent;
	    parentType = DIALOG;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public ApplicationConfigPanel(JFrame _parent) {
	try {
	    parent = _parent;
	    parentType = FRAME;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	drawTree();
	this.setLayout(null);
	this.setSize(new Dimension(625, 427));
	//appsTree.setBounds(new Rectangle(0, 0, 355, 300));
	jpAddApplication.add(btnAdd, null);
	jpAddApplication.add(tfipTermDays, null);
	jpAddApplication.add(tfipApplicationName, null);
	this.add(btnClose, null);
	this.add(jpAddApplication, null);
	this.add(jpShowApplications, null);
	jpVencimientos.add(chkMailBlue, null);
	jpVencimientos.add(chkMailGreen, null);
	jpVencimientos.add(chkMailYellow, null);
	jpVencimientos.add(chkMailRed, null);
	jpVencimientos.add(chkEmailBlack, null);
	jpVencimientos.add(chkUpdateDays, null);
	jpVencimientos.add(lblGreenDays, null);
	jpVencimientos.add(lblBlueDays, null);
	jpVencimientos.add(lblYellowDays, null);
	jpVencimientos.add(lblRedDays, null);
	jpVencimientos.add(lblBlackDays, null);
	jpVencimientos.add(tfGreenDays, null);
	jpVencimientos.add(tfYellowDays, null);
	jpVencimientos.add(tfBlueDays, null);
	jpVencimientos.add(tfRedDays, null);
	jpVencimientos.add(tfBlackDays, null);
	jpVencimientos.add(lblYellow, null);
	jpVencimientos.add(lblGreen, null);
	jpVencimientos.add(lblBlue, null);
	jpVencimientos.add(lblBlack, null);
	jpVencimientos.add(lblRojo, null);
	jpVencimientos.add(btnUpdateDays, null);
	jpArbol.add(jsptree, null);
	jpShowApplications.add(lblVencimientos1, null);
	jpShowApplications.add(jpArbol, null);
	jpShowApplications.add(lblVencimientos, null);
	jpShowApplications.add(jpVencimientos, null);
	jsptree.setBounds(new Rectangle(10, 10, 340, 250));
	jpShowApplications.setBounds(new Rectangle(5, 80, 610, 300));
	jpShowApplications.setLayout(null);
	jpShowApplications.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	jpAddApplication.setBounds(new Rectangle(5, 5, 610, 60));
	jpAddApplication.setLayout(null);
	jpAddApplication.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	jpVencimientos.setBounds(new Rectangle(395, 15, 200, 270));
	jpVencimientos.setLayout(null);
	jpVencimientos.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	btnClose.setBounds(new Rectangle(580, 390, 35, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	lblRojo.setBounds(new Rectangle(5, 55, 35, 40));
	lblBlack.setBounds(new Rectangle(5, 15, 35, 40));
	lblBlue.setBounds(new Rectangle(5, 175, 35, 40));
	lblGreen.setBounds(new Rectangle(5, 135, 35, 40));
	lblYellow.setBounds(new Rectangle(5, 95, 35, 40));
	tfBlackDays.setBounds(new Rectangle(40, 25, 80, 20));
	tfRedDays.setBounds(new Rectangle(40, 65, 80, 20));
	tfBlueDays.setBounds(new Rectangle(40, 185, 80, 20));
	tfYellowDays.setBounds(new Rectangle(40, 105, 80, 20));
	tfGreenDays.setBounds(new Rectangle(40, 145, 80, 20));
	lblBlackDays.setText("(dias)");
	lblBlackDays.setBounds(new Rectangle(40, 10, 40, 15));
	lblRedDays.setText("(dias)");
	lblRedDays.setBounds(new Rectangle(40, 50, 40, 15));
	lblYellowDays.setText("(dias)");
	lblYellowDays.setBounds(new Rectangle(40, 90, 40, 15));
	lblBlueDays.setText("(dias)");
	lblBlueDays.setBounds(new Rectangle(40, 170, 40, 15));
	lblGreenDays.setText("(dias)");
	lblGreenDays.setBounds(new Rectangle(40, 130, 40, 15));
	lblVencimientos.setText(" Vencimientos");
	lblVencimientos.setBounds(new Rectangle(400, 5, 87, 14));
	lblVencimientos.setOpaque(false);
	lblVencimientos.setSize(new Dimension(87, 14));
	lblVencimientos.setFont(new Font("Default", 1, 11));
	tfipApplicationName.setBounds(new Rectangle(20, 15, 290, 35));
	tfipTermDays.setBounds(new Rectangle(345, 15, 110, 35));
	jpArbol.setBounds(new Rectangle(15, 15, 360, 270));
	jpArbol.setLayout(null);
	jpArbol.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	lblVencimientos1.setText(" Trámites");
	lblVencimientos1.setBounds(new Rectangle(25, 5, 85, 15));
	lblVencimientos1.setOpaque(false);
	lblVencimientos1.setSize(new Dimension(58, 14));
	lblVencimientos1.setFont(new Font("Default", 1, 11));
	btnUpdateDays.setBounds(new Rectangle(145, 230, 40, 25));
	btnUpdateDays.setToolTipText("Actualizar días");
	btnUpdateDays.addActionListener(new ActionListener() {

				     public void actionPerformed(ActionEvent e) {
					 btnUpdateDays_actionPerformed(e);
				     }

				 }
	);
	chkUpdateDays.setText("Editar días");
	chkUpdateDays.setBounds(new Rectangle(15, 235, 105, 15));
	//chkUpdateDays.setSize(new Dimension(90, 14));
	chkUpdateDays.setFont(new Font("Default", 1, 11));
	btnAdd.setText("Agregar");
	btnAdd.setBounds(new Rectangle(490, 20, 110, 25));
	btnAdd.setToolTipText("null");
	btnAdd.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAdd_actionPerformed(e);
			      }

			  }
	);
	chkUpdateDays.addActionListener(new ActionListener() {

				     public void actionPerformed(ActionEvent e) {
					 chkUpdateDays_actionPerformed(e);
				     }

				 }
	);
	chkUpdateDays.setSelected(false);
	chkEmailBlack.setText("E-Mail");
	chkEmailBlack.setBounds(new Rectangle(125, 30, 70, 15));
	chkEmailBlack.addActionListener(new ActionListener() {

				     public void actionPerformed(ActionEvent e) {
					 chkEmailBlack_actionPerformed(e);
				     }

				 }
	);
	chkMailRed.setText("E-Mail");
	chkMailRed.setBounds(new Rectangle(125, 70, 70, 15));
	chkMailRed.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      chkMailRed_actionPerformed(e);
				  }

			      }
	);
	chkMailYellow.setText("E-Mail");
	chkMailYellow.setBounds(new Rectangle(125, 110, 70, 15));
	chkMailYellow.addActionListener(new ActionListener() {

				     public void actionPerformed(ActionEvent e) {
					 chkMailYellow_actionPerformed(e);
				     }

				 }
	);
	chkMailGreen.setText("E-Mail");
	chkMailGreen.setBounds(new Rectangle(125, 150, 70, 15));
	chkMailGreen.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					chkMailGreen_actionPerformed(e);
				    }

				}
	);
	chkMailBlue.setText("E-Mail");
	chkMailBlue.setBounds(new Rectangle(125, 190, 70, 15));
	chkMailBlue.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       chkMailBlue_actionPerformed(e);
				   }

			       }
	);
	enabledFields(false);
	btnUpdateDays.setEnabled(false);
	chkUpdateDays.setSelected(false);
    }

    private void chkUpdateDays_actionPerformed(ActionEvent e) {
	if (chkUpdateDays.isSelected()) {
	    enabledFields(true);
	    btnUpdateDays.setEnabled(true);
	} else {
	    enabledFields(false);
	    btnUpdateDays.setEnabled(false);
	}
    }

    private void dispose() {
	switch (parentType) {
	    case DIALOG :
		((BasicDialog)parent).dispose();
		break;
	    case INTERNALFRAME :
		((BasicInternalFrame)parent).dispose();
		break;
	    case FRAME :
		((JFrame)parent).dispose();
		break;
	}
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	dispose();
    }

    private void enabledFields(boolean _valor) {
	tfBlackDays.setEnabled(_valor);
	tfRedDays.setEnabled(_valor);
	tfYellowDays.setEnabled(_valor);
	tfBlueDays.setEnabled(_valor);
	tfGreenDays.setEnabled(_valor);
	if (!_valor) {
	    tfBlackDays.setDisabledTextColor(Color.black);
	}
    }

    private void setFields(int _idApplicationTab) {
	String consulta = "SELECT idpriority_tab,termdays FROM file.applicationpriorities WHERE idapplication_tab = " + _idApplicationTab + " order by idpriority_tab ";
	ResultSet result = org.digitall.lib.sql.LibSQL.exQuery(consulta);
	try {
	    while (result.next()) {
		if (result.getInt("idpriority_tab") == 1) {
		    tfBlackDays.setText(result.getString("termdays"));
		    if (!tfBlackDays.isEnabled()) {
			tfBlackDays.setDisabledTextColor(Color.BLACK);
		    }
		} else if (result.getInt("idpriority_tab") == 2) {
		    tfRedDays.setText(result.getString("termdays"));
		    if (!tfRedDays.isEnabled()) {
			tfRedDays.setDisabledTextColor(Color.BLACK);
		    }
		} else if (result.getInt("idpriority_tab") == 3) {
		    tfYellowDays.setText(result.getString("termdays"));
		    if (!tfYellowDays.isEnabled()) {
			tfYellowDays.setDisabledTextColor(Color.BLACK);
		    }
		} else if (result.getInt("idpriority_tab") == 4) {
		    tfGreenDays.setText(result.getString("termdays"));
		    if (!tfGreenDays.isEnabled()) {
			tfGreenDays.setDisabledTextColor(Color.BLACK);
		    }
		} else if (result.getInt("idpriority_tab") == 5) {
		    tfBlueDays.setText(result.getString("termdays"));
		    if (!tfBlueDays.isEnabled()) {
			tfBlueDays.setDisabledTextColor(Color.BLACK);
		    }
		}
	    }
	} catch (SQLException ex) {
	    ex.printStackTrace();
	} finally {
	}
    }

    private void btnAdd_actionPerformed(ActionEvent e) {
	if (control()) {
	    int days = Integer.parseInt(tfipTermDays.getText().trim());
	    String superInsert = "SELECT tabs.addapplication('" + idApplicationTab + "','" + tfipApplicationName.getText().trim() + "' ,'" + days + "' );";
	    if (LibSQL.insertQuery(superInsert).equals("0")) {
		org.digitall.lib.components.Advisor.messageBox("Insert success!!", "Message");
		drawTree();
	    } else {
		org.digitall.lib.components.Advisor.messageBox("Ocurrio un error al insertar los datos.\n algunos datos no se registraron", "Message");
	    }
	} else {
	    msjError();
	}
    }

    private void drawTree() {
	try {
	    appsTree = treeMan.createTree("tabs.application_tabs", "idapplication_tab", "name", "0");
	    appsTree.setRowHeight(0);
	    jsptree.getViewport().add(appsTree, null);
	    appsTree.addTreeSelectionListener(new TreeSelectionListener() {

					   public void valueChanged(TreeSelectionEvent evt) {
					       TreePath paths = evt.getPath();
					       String node = paths.getLastPathComponent().toString();
					       idApplicationTab = Integer.parseInt(node.substring(0, node.indexOf("-") - 1).trim());
					       appTabName = node.substring(node.indexOf("-") + 1, node.length()).trim();
					       //System.out.println("id: " + idApplicationTab);
					       //System.out.println("name: " + appTabName);
					       /**
                                                   * A PARTIR DE AQUI TODO TUYO!
                                                   */
					       setFields(idApplicationTab);
					   }

				       }
	    );
	} catch (Exception e) {
	    // TODO
	    e.printStackTrace();
	}
    }

    private boolean control() {
	if (tfipApplicationName.getText().equals("")) {
	    error = 1;
	    return false;
	} else if (tfipTermDays.getText().trim().equals("")) {
	    error = 2;
	    return false;
	} else if (appsTree.getSelectionCount() == 0) {
	    error = 3;
	    return false;
	} else {
	    return true;
	}
    }

    private void msjError() {
	if (error == 1) {
	    org.digitall.lib.components.Advisor.messageBox("El campo Nombre del Trámite está vacio", "Error");
	}
	if (error == 2) {
	    org.digitall.lib.components.Advisor.messageBox("El campo Días de Plazo está vacio", "Error");
	}
	if (error == 3) {
	    org.digitall.lib.components.Advisor.messageBox("Debe Seleccionar el Trámite padre", "Error");
	}
	if (error == 4) {
	    org.digitall.lib.components.Advisor.messageBox("El campo Dias en Negro está vacio", "Error");
	}
	if (error == 5) {
	    org.digitall.lib.components.Advisor.messageBox("El campo Dias en Rojo está vacio", "Error");
	}
	if (error == 6) {
	    org.digitall.lib.components.Advisor.messageBox("El campo Dias en Amarillo está vacio", "Error");
	}
	if (error == 7) {
	    org.digitall.lib.components.Advisor.messageBox("El campo Dias en Verde está vacio", "Error");
	}
	if (error == 8) {
	    org.digitall.lib.components.Advisor.messageBox("El campo Dias en Azul está vacio", "Error");
	}
    }

    private void btnUpdateDays_actionPerformed(ActionEvent e) {
	if (controlColor()) {
	    String update = "";
	    update += "UPDATE file.applicationpriorities SET termdays = " + tfBlackDays.getText().trim() + " WHERE idpriority_tab = 1 AND idapplication_tab = " + idApplicationTab + " ; ";
	    update += "UPDATE file.applicationpriorities SET termdays = " + tfRedDays.getText().trim() + " WHERE idpriority_tab = 2 AND idapplication_tab = " + idApplicationTab + " ; ";
	    update += "UPDATE file.applicationpriorities SET termdays = " + tfYellowDays.getText().trim() + " WHERE idpriority_tab = 3 AND idapplication_tab = " + idApplicationTab + " ; ";
	    update += "UPDATE file.applicationpriorities SET termdays = " + tfGreenDays.getText().trim() + " WHERE idpriority_tab = 4 AND idapplication_tab = " + idApplicationTab + " ; ";
	    update += "UPDATE file.applicationpriorities SET termdays = " + tfBlueDays.getText().trim() + " WHERE idpriority_tab = 5 AND idapplication_tab = " + idApplicationTab + " ; ";
	    if (LibSQL.exActualizar('m', update)) {
		org.digitall.lib.components.Advisor.messageBox("Actulaización exitosa", "Mensaje");
		btnUpdateDays.setEnabled(false);
		chkUpdateDays.setSelected(false);
		enabledFields(false);
	    } else {
		org.digitall.lib.components.Advisor.messageBox("Ocurrió un error, los cambios no se registraron.", "Error");
	    }
	} else {
	    msjError();
	}
    }

    private void chkEmailBlack_actionPerformed(ActionEvent e) {
	if (chkEmailBlack.isSelected()) {
	    Contacts contacts = new Contacts(appTabName, 1);
	    contacts.setModal(true);
	    contacts.setVisible(true);
	    chkEmailBlack.setSelected(false);
	}
    }

    private void chkMailRed_actionPerformed(ActionEvent e) {
	if (chkMailRed.isSelected()) {
	    Contacts contacts = new Contacts(appTabName, 2);
	    contacts.setModal(true);
	    contacts.setVisible(true);
	    chkMailRed.setSelected(false);
	}
    }

    private void chkMailYellow_actionPerformed(ActionEvent e) {
	if (chkMailYellow.isSelected()) {
	    Contacts contacts = new Contacts(appTabName, 3);
	    contacts.setModal(true);
	    contacts.setVisible(true);
	    chkMailYellow.setSelected(false);
	}
    }

    private void chkMailGreen_actionPerformed(ActionEvent e) {
	if (chkMailGreen.isSelected()) {
	    Contacts contacts = new Contacts(appTabName, 4);
	    contacts.setModal(true);
	    contacts.setVisible(true);
	    chkMailGreen.setSelected(false);
	}
    }

    private void chkMailBlue_actionPerformed(ActionEvent e) {
	if (chkMailBlue.isSelected()) {
	    Contacts contacts = new Contacts(appTabName, 5);
	    contacts.setModal(true);
	    contacts.setVisible(true);
	    chkMailBlue.setSelected(false);
	}
    }

    private boolean controlColor() {
	if (tfBlackDays.getText().trim().equals("")) {
	    error = 4;
	    return false;
	} else if (tfRedDays.getText().trim().equals("")) {
	    error = 5;
	    return false;
	} else if (tfYellowDays.getText().trim().equals("")) {
	    error = 6;
	    return false;
	} else if (tfGreenDays.getText().trim().equals("")) {
	    error = 7;
	    return false;
	} else if (tfBlueDays.getText().trim().equals("")) {
	    error = 8;
	    return false;
	} else {
	    return true;
	}
    }

}
