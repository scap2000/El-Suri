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
 * PanelDictionary.java
 *
 * */
package org.digitall.common.data;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DecimalFormat;

import java.text.DecimalFormatSymbols;

import java.text.NumberFormat;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.Vector;

import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

import org.digitall.lib.common.ConfigFile;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.JEntry;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;
//

public class PanelDictionary extends BasicPrimitivePanel {

    private TFInput tfReferenceName = new TFInput(DataTypes.STRING, "ReferenceName", true);
    private TFInput tfEnglishName = new TFInput(DataTypes.STRING, "English", true);
    private TFInput tfSpanishName = new TFInput(DataTypes.STRING, "Spanish", true);
    private TFInput tfApplication = new TFInput(DataTypes.STRING, "Application", true);
    private TFInput tfWordFind = new TFInput(DataTypes.STRING, "WordFind", true);
    private BasicPanel findPanel = new BasicPanel("Buscar Etiqueta");
    private BasicPanel dataPanel = new BasicPanel("Agregar/Modificar Etiqueta");
    private int[] sizeColumnList = { 150, 150, 150, 97 };
    private Vector headerList = new Vector();
    private Vector datos = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Listado de Etiquetas", datos);
    private int error = 0;
    private AssignButton btnAdd = new AssignButton(true);
    private DeleteButton btnDelete = new DeleteButton();
    private AcceptButton btnGenerar = new AcceptButton();
    private AddButton btnNew = new AddButton();
    private FindButton btnFind = new FindButton();
    private CBInput cbChecked = new CBInput(0, "Checked", false);
    private boolean add = true;
    private BasicPanel contain = new BasicPanel();
    private AcceptButton btnAccept = new AcceptButton();
    Vector<Vector> vectorsSelected;

    public PanelDictionary() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout(null);
	this.setSize(new Dimension(600, 500));
	tfReferenceName.setBounds(new Rectangle(10, 30, 235, 35));
	tfEnglishName.setBounds(new Rectangle(10, 70, 235, 35));
	tfSpanishName.setBounds(new Rectangle(270, 70, 235, 35));
	tfApplication.setBounds(new Rectangle(270, 30, 235, 35));
	btnAdd.setBounds(new Rectangle(555, 75, 30, 25));
	btnAdd.setSize(new Dimension(30, 25));
	btnAdd.setToolTipText("Asignar");
	btnAdd.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnInsert_actionPerformed(e);
		    }

		});
	dataPanel.setBounds(new Rectangle(0, 75, 595, 110));
	dataPanel.setLayout(null);
	listPanel.setBounds(new Rectangle(0, 190, 595, 265));
	btnDelete.setBounds(new Rectangle(520, 490, 50, 50));
	btnDelete.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnDelete_actionPerformed(e);
		    }

		});
	btnGenerar.setText("Generar");
	btnGenerar.setBounds(new Rectangle(455, 495, 50, 50));
	btnGenerar.setSize(new Dimension(50, 50));
	btnGenerar.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnGenerar_actionPerformed(e);
		    }

		});
	findPanel.setBounds(new Rectangle(0, 0, 595, 70));
	findPanel.setLayout(null);
	tfWordFind.setBounds(new Rectangle(35, 25, 275, 35));
	btnFind.setBounds(new Rectangle(560, 35, 30, 25));
	btnFind.setToolTipText("Buscar");
	btnFind.setSize(new Dimension(30, 25));
	btnFind.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnFind_actionPerformed(e);
		    }

		});
	btnNew.setBounds(new Rectangle(520, 75, 30, 25));
	btnNew.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnNew_actionPerformed(e);
		    }

		});
	cbChecked.setBounds(new Rectangle(335, 25, 170, 35));
	dataPanel.add(btnNew, null);
	dataPanel.add(tfReferenceName, null);
	dataPanel.add(tfApplication, null);
	dataPanel.add(tfEnglishName, null);
	//this.add(btnGenerar, null);
	//this.add(btnDelete, null);
	dataPanel.add(tfSpanishName, null);
	dataPanel.add(btnAdd);
	findPanel.add(cbChecked, null);
	findPanel.add(btnFind, null);
	findPanel.add(tfWordFind, null);
	contain.add(findPanel, null);
	contain.add(dataPanel, null);
	contain.add(listPanel, null);
	//this.add(dataPanel, null);
	tfEnglishName.setEnabled(true);
	btnDelete.setEnabled(false);
	btnDelete.setSize(new Dimension(50, 50));
	tfReferenceName.setEnabled(true);
	tfSpanishName.setEnabled(true);
	tfApplication.setEnabled(true);
	tfWordFind.setEnabled(true);
	/*tfWordFind.getTextField().addKeyListener(new KeyAdapter() {

					      public void keyTyped(KeyEvent e) {
						  char caracter = e.getKeyChar();
						  if ((caracter == KeyEvent.VK_ENTER)) {
						      //cargo los datos grilla
						      refresh();
						  }
					      }

					  }
	);*/
	cbChecked.getCombo().addItemListener(new ItemListener() {

		    public void itemStateChanged(ItemEvent evt) {
			if (evt.getStateChange() == ItemEvent.SELECTED) {
			    //refresh();
			}
		    }

		});
	cbChecked.autoSize();
	cbChecked.getCombo().addItem("SI", "0");
	cbChecked.getCombo().addItem("NO", "1");
	cbChecked.getCombo().addItem("Todos", "2");
	cbChecked.setSelectedID("2");
	//contain.setBounds(new Rectangle(0, 0, 595, 455));
	contain.setLayout(null);
	//btnAccept.setBounds(new Rectangle(550, 455, 40, 25));
	btnAccept.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnAccept_actionPerformed(e);
		    }

		});
	this.addButton(btnAccept);
	this.add(contain, BorderLayout.CENTER);
	setHeaderList();
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().getGeneralButtons().addButton(btnGenerar);
	getParentInternalFrame().getGeneralButtons().addButton(btnDelete);
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("Referenced Name");
	headerList.addElement("English");
	headerList.addElement("Spanish");
	headerList.addElement("Aplication");
	headerList.addElement("Checked");
	listPanel.getTable().addMouseListener(new MouseAdapter() {

		    public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
			    setFields();
			    btnDelete.setEnabled(true);
			    add = false;
			}
		    }

		});
	listPanel.setParams("tabs.getAllLanguage", "'',2", headerList);
    }

    public void refresh() {
	//String params = "'" + tfWordFind.getString() + "'," + cbChecked.getSelectedValue();
	//System.out.println("SELECT tabs.getAllLanguage(" + params + ")");
	//listPanel.refresh(params);
	btnDelete.setEnabled(false);
	add = true;
    }

    private void btnInsert_actionPerformed(ActionEvent e) {
	if (control()) {
	    if (add) {
		String insert = "INSERT INTO tabs.language_tabs VALUES((SELECT MAX(idlabel) + 1 FROM tabs.language_tabs)" + ", '" + tfReferenceName.getString() + "' , '" + tfEnglishName.getString() + "' , '" + tfSpanishName.getString() + "' , '" + tfApplication.getString() + "');";
		System.out.println(insert);
		if (LibSQL.exActualizar('a', insert)) {

		} else {
		    Advisor.messageBox("Ocurrió un error, los datos no se registraron", "Error");
		}
	    } else {
		String update = "UPDATE tabs.language_tabs SET name = '" + tfReferenceName.getString() + "' , en_us = '" + tfEnglishName.getString() + "' , es_ar = '" + tfSpanishName.getString() + "' , application = '" + tfApplication.getString() + "' WHERE idlabel = " + datos.elementAt(0);
		System.out.println(update);
		if (LibSQL.exActualizar('m', update)) {
		} else {
		    Advisor.messageBox("Ocurrió en error, los datos no se registraron", "Error");
		}
	    }
	    refresh();
	} else {
	    error();
	}
    }

    private boolean control() {
	if (tfReferenceName.getString().equals("")) {
	    error = 1;
	    return false;
	} else if (tfEnglishName.getString().equals("")) {
	    error = 2;
	    return false;
	} else if (tfSpanishName.getString().equals("")) {
	    error = 3;
	    return false;
	} else
	    return true;
    }

    private void error() {
	if (error == 1) {
	    Advisor.messageBox("El campo Nombre de Referencia esta vacio", "Error");
	}
	if (error == 2) {
	    Advisor.messageBox("El campo Ingles esta vacio", "Error");
	}
	if (error == 3) {
	    Advisor.messageBox("El campo Español esta vacio", "Error");
	}
    }

    private void btnGenerar_actionPerformed(ActionEvent e) {
	String path = "/digitall/desarrollo/jdevhome/mywork/DDesktop/Resources/src/org/digitall/lib/dictionary/";
	ConfigFile lang_en_us = new ConfigFile(path + "en_us/lang.conf");
	ConfigFile lang_es_ar = new ConfigFile(path + "es_ar/lang.conf");
	ResultSet lang = LibSQL.exQuery("SELECT * FROM tabs.language_tabs ORDER BY name");
	try {
	    //lang_en_us.setProperty("#Created by ", Environment.sessionUser + " on " + Environment.currentDate);
	    //lang_es_ar.setProperty("#Created by ", Environment.sessionUser + " on " + Environment.currentDate);
	    while (lang.next()) {
		lang_en_us.setProperty(lang.getString("name"), lang.getString("en_us"));
		lang_es_ar.setProperty(lang.getString("name"), lang.getString("es_ar"));
	    }
	    Advisor.messageBox("El archivo fué generado con éxito!\n" + path, "Aviso");
	} catch (SQLException x) {
	    // TODO
	    x.printStackTrace();
	}
    }

    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void btnDelete_actionPerformed(ActionEvent e) {
	String delete = "DELETE FROM tabs.language_tabs WHERE idlabel = " + datos.elementAt(0).toString();
	if (LibSQL.exActualizar('m', delete)) {
	    Advisor.messageBox("El registro fue borrado", "Message");
	    refresh();
	    btnDelete.setEnabled(false);
	} else {
	    Advisor.messageBox("El registro no fue borrado", "Error");
	}
    }

    private void clearFields() {
	tfWordFind.setValue("");
	tfReferenceName.setValue("");
	tfEnglishName.setValue("");
	tfSpanishName.setValue("");
	add = true;
	btnDelete.setEnabled(false);
    }

    private void setFields() {
	tfReferenceName.setValue(datos.elementAt(1).toString());
	tfEnglishName.setValue(datos.elementAt(2).toString());
	tfSpanishName.setValue(datos.elementAt(3).toString());
	tfApplication.setValue(datos.elementAt(4).toString());
    }

    private void btnNew_actionPerformed(ActionEvent e) {
	clearFields();
    }

    private void btnAccept_actionPerformed(ActionEvent e) {
	if (listPanel.getTable().getRowCount() != 0) {
	    vectorsSelected = new Vector();
	    vectorsSelected = listPanel.getSelectedsVector();
	    Vector vectorId;
	    String params = "";
	    if (vectorsSelected.size() != 0) {
		for (int i = 0; i < vectorsSelected.size(); i++) {
		    vectorId = vectorsSelected.elementAt(i);
		    params = "" + vectorId.elementAt(0);
		    System.out.println("SELECT tabs.setCheckedLanguage(" + params + ")");
		    LibSQL.getBoolean("tabs.setCheckedLanguage", params);
		}
		refresh();
	    } else {
		Advisor.messageBox("No seleccionó ninguna fila", "Mensaje");
	    }
	} else {
	    Advisor.messageBox("No hay datos en la Grilla", "Mensaje");
	}
    }

}
