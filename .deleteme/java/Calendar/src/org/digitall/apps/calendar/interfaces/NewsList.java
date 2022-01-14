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
 * NewsList.java
 *
 * */
package org.digitall.apps.calendar.interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.BasicTextField;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class NewsList extends BasicPrimitivePanel {

    public static final int READ = 1;
    public static final int UNREAD = 0;
    public static final int ALL = 2;
    public static final int HIGH = 1;
    public static final int MIDDLE = 2;
    public static final int LOW = 3;
    private BasicPanel priorityPanel = new BasicPanel("Total de Novedades");
    private int[] sizeColumnList = {85, 397, 76, 170};
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Novedades", dataRow);
    private Vector headerList = new Vector();
    private BasicPanel jpNews = new BasicPanel();
    private CBInput cbSee = new CBInput(0, "See", false);
    private AddButton btnAdd = new AddButton();
    private DeleteButton btnDelete = new DeleteButton();
    private AcceptButton btnAccept = new AcceptButton();
    private BasicScrollPane jScrollPane1 = new BasicScrollPane();
    private JTextArea taNew = new JTextArea();
    private BasicLabel lblPriority = new BasicLabel();
    private BasicTextField tfHigh = new BasicTextField();
    private BasicLabel lblHigh = new BasicLabel();
    private BasicLabel lblMiddle = new BasicLabel();
    private BasicTextField tfMiddle = new BasicTextField();
    private BasicTextField tfLow = new BasicTextField();
    private BasicLabel lblLow = new BasicLabel();
    private TFInput tfDate = new TFInput(DataTypes.DATE, "Date", false);
    private BasicCheckBox chkRead = new BasicCheckBox();
    private int high, low, middle = 0;
    private BasicPanel content = new BasicPanel();

    public NewsList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(808, 573));
	this.setPreferredSize(new Dimension(570, 605));
	priorityPanel.setBounds(new Rectangle(0, 0, 795, 55));
	priorityPanel.setLayout(null);
	listPanel.setBounds(new Rectangle(5, 100, 790, 265));
	jpNews.setBounds(new Rectangle(5, 370, 790, 155));
	jpNews.setBorder(BorderPanel.getBorderPanel("Mensaje"));
	jpNews.setLayout(null);
	cbSee.setBounds(new Rectangle(600, 55, 195, 35));
	btnAdd.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAdd_actionPerformed(e);
			      }

			  }
	);
	btnDelete.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnDelete_actionPerformed(e);
				 }

			     }
	);
	btnAccept.setToolTipText("Responder");
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	jScrollPane1.setBounds(new Rectangle(10, 20, 770, 130));
	taNew.setEditable(false);
	taNew.setWrapStyleWord(true);
	taNew.setLineWrap(true);
	lblPriority.setBounds(new Rectangle(15, 40, 80, 20));
	tfHigh.setBounds(new Rectangle(140, 25, 30, 20));
	tfHigh.setEnabled(false);
	tfHigh.setHorizontalAlignment(BasicTextField.CENTER);
	tfHigh.setBorder(BorderFactory.createLineBorder(new Color(12, 12, 33), 1));
	tfHigh.setDisabledTextColor(Color.black);
	tfHigh.setBackground(Color.white);
	lblHigh.setBounds(new Rectangle(170, 25, 70, 20));
	lblHigh.setText("ALTA");
	lblHigh.setHorizontalAlignment(BasicTextField.CENTER);
	lblHigh.setBackground(new Color(255, 12, 31));
	lblHigh.setOpaque(true);
	lblHigh.setBorder(BorderFactory.createLineBorder(new Color(12, 12, 33), 1));
	lblMiddle.setBounds(new Rectangle(310, 25, 70, 20));
	lblMiddle.setText("MEDIA");
	lblMiddle.setHorizontalAlignment(BasicTextField.CENTER);
	lblMiddle.setBackground(Color.orange);
	lblMiddle.setOpaque(true);
	lblMiddle.setBorder(BorderFactory.createLineBorder(new Color(12, 12, 33), 1));
	tfMiddle.setBounds(new Rectangle(280, 25, 30, 20));
	tfMiddle.setEnabled(false);
	tfMiddle.setHorizontalAlignment(BasicTextField.CENTER);
	tfMiddle.setBorder(BorderFactory.createLineBorder(new Color(12, 12, 33), 1));
	tfMiddle.setDisabledTextColor(Color.black);
	tfMiddle.setBackground(Color.white);
	tfLow.setBounds(new Rectangle(420, 25, 30, 20));
	tfLow.setHorizontalAlignment(BasicTextField.CENTER);
	tfLow.setBorder(BorderFactory.createLineBorder(new Color(12, 12, 33), 1));
	tfLow.setEnabled(false);
	tfLow.setDisabledTextColor(Color.black);
	tfLow.setBackground(Color.white);
	lblLow.setBounds(new Rectangle(450, 25, 70, 20));
	lblLow.setText("BAJA");
	lblLow.setHorizontalAlignment(BasicTextField.CENTER);
	lblLow.setBackground(new Color(0, 201, 0));
	lblLow.setOpaque(true);
	lblLow.setBorder(BorderFactory.createLineBorder(new Color(12, 12, 33), 1));
	tfDate.setBounds(new Rectangle(710, 10, 85, 35));
	tfDate.setDisabledTextColor(Color.black);
	tfDate.setValue(Proced.setFormatDate(Environment.currentDate, true));
	tfDate.setEditable(false);
	chkRead.setText("Leido");
	chkRead.setBounds(new Rectangle(5, 70, 65, 20));
	chkRead.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   chkRead_actionPerformed(e);
			       }

			   }
	);
	content.setLayout(null);
	cbSee.autoSize();
	content.add(listPanel, null);
	content.add(chkRead, null);
	content.add(cbSee, null);
	jScrollPane1.getViewport().add(taNew, null);
	jpNews.add(jScrollPane1, null);
	content.add(jpNews, null);
	content.add(priorityPanel, null);
	priorityPanel.add(lblLow, null);
	priorityPanel.add(tfLow, null);
	priorityPanel.add(tfMiddle, null);
	priorityPanel.add(lblMiddle, null);
	priorityPanel.add(lblHigh, null);
	priorityPanel.add(tfHigh, null);
	priorityPanel.add(lblPriority, null);
	priorityPanel.add(tfDate, null);
	this.addButton(btnDelete);
	this.addButton(btnAccept);
	this.addButton(btnAdd);
	this.add(content, null);
	
	setHeaderList();
	
	cbSee.getCombo().addItem("No Leido", "0");
	cbSee.getCombo().addItem("Leidos", "1");
	cbSee.getCombo().addItem("Todos", "2");
	cbSee.addItemListener(new ItemListener() {

			   public void itemStateChanged(ItemEvent evt) {
			       if (evt.getStateChange() == ItemEvent.SELECTED) {
				   taNew.setText("");
				   chkRead.setSelected(false);
				   refresh();
                                   loadMain();
                                   updateInfo();
			       }
			   }

		       }
	);
	refresh();
    }
    
    private void updateInfo() {
        getParentInternalFrame().setInfo("Tiene un total de " + (high + low + middle) + " novedades");
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	updateInfo();
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");		// 0) news.idnew,
	headerList.addElement("*");		// 1) news.idsenderuser,
	headerList.addElement("*");		// 2) news.idrecipientuser,
	headerList.addElement("*");		// 3) news.idpriority,
	headerList.addElement("*");		// 4) news.idstatus,  
	headerList.addElement("Fecha");		// 5) news.date,      
	headerList.addElement("Tema");		// 6) news.subject,
	headerList.addElement("Estado");	// 7) newsstatus.name,
	headerList.addElement("*");		// 8) news.text,
	headerList.addElement("Remitente");	// 9) nombre remitente
	headerList.addElement("*");		// 10) news.statusread,
	headerList.addElement("*");		// 11)news.statusanswer,
	headerList.addElement("*");		// 12) news.estado
	
	listPanel.getTable().addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
			     if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
				  loadObject();
			     } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
				  callNewsMgmt();
			     }
			 }
	    
		     }
		);
	
	String params = "" + cbSee.getSelectedValue();
	listPanel.setParams("calendar.getAllNewsByFilter", params, headerList);
	btnDelete.setEnabled(false);
	btnAccept.setEnabled(false);
	
    }

    public void loadObject() {
	taNew.setText(dataRow.elementAt(8).toString());
	if (dataRow.get(10).toString().equals("SI")) {
	    chkRead.setSelected(true);
	} else {
	    chkRead.setSelected(false);
	}
	btnDelete.setEnabled(true);
	btnAccept.setEnabled(true);
    }

    public void refresh() {
	String params = "" + cbSee.getSelectedValue();
	listPanel.refresh(params);
	loadMain();
	btnDelete.setEnabled(false);
	btnAccept.setEnabled(false);
    }

    private void loadMain() {
	high = 0;
	middle = 0;
	low = 0;
	String params = ""+ cbSee.getSelectedValue();
	ResultSet newsStatus = LibSQL.exFunction("calendar.getAllNewsStatus",params);
	if (newsStatus != null)  {
	    try  {
		while (newsStatus.next())  {
		    switch (newsStatus.getInt(1)){
			case 1: high = newsStatus.getInt(2);
			    break;
			case 2:  middle = newsStatus.getInt(2);
			    break;
			case 3:  low = newsStatus.getInt(2);
			    break;	
		    }
		} 
	    } catch (Exception ex)  {
		ex.printStackTrace();
	    }
	}
	tfHigh.setText("" + high);
	tfLow.setText("" + low);
	tfMiddle.setText("" + middle);
    }

    private void btnAdd_actionPerformed(ActionEvent e) {
        ExtendedInternalFrame panelNewsMgmtContainer = new ExtendedInternalFrame("Respuesta");
        NewsMgmt newsMgmt = new NewsMgmt();
        panelNewsMgmtContainer.setCentralPanel(newsMgmt);
        panelNewsMgmtContainer.show();
	panelNewsMgmtContainer.setInfo("Enviando nuevo mensaje");
    }

    private void btnAccept_actionPerformed(ActionEvent e) {
	//agrega uno nuevo con un tema y receptor definido
	if (dataRow != null) {
	    callNewsMgmt();
	}
    }

    private void chkRead_actionPerformed(ActionEvent e) {
	int result = -1;
	if (chkRead.isSelected()) {
	    Vector<Vector> values = listPanel.getSelectedsVector();
	    if (values.size() > 0){
		for (int i=0 ; i<values.size(); i++)  {
		    Vector row = values.elementAt(i);
		    String params = "" + row.elementAt(0) + "," + READ + ",true";
		    result = LibSQL.getInt("calendar.setNewsStatus", params);
		    if (result < 0){
			break;
		    } 
		}
	    } else{
		Advisor.messageBox("Seleccionar novedad","Seleccionar novedad");
	    }
	}
	if (result >= 0){
	    refresh();
	    chkRead.setSelected(false);
	    taNew.setText("");
	} else {
	    Advisor.messageBox("Ocurrio un error al marcar las novedades como leidas","Error");
	}
        loadMain();
        updateInfo();
    }

    private void btnDelete_actionPerformed(ActionEvent e) {
	String params = "";
	int res = -1;
	if (dataRow != null) {
	    if (Advisor.question("Atencion", "¿Desea eliminar la novedad selecionada?")) {
		params = "" + dataRow.get(0).toString();
		res = LibSQL.getInt("calendar.delNews", params);
		refresh();
		loadMain();
		taNew.setText("");
	    }
	}
    }

    private void callNewsMgmt() {
	NewsMgmt newsMgmtContainer = new NewsMgmt();
	newsMgmtContainer.setIdNews(Integer.parseInt(dataRow.get(0).toString()));
	newsMgmtContainer.setText(dataRow.get(8).toString());
	newsMgmtContainer.setSubject(dataRow.get(6).toString());
	newsMgmtContainer.setIdRecipientUser(Integer.parseInt(dataRow.get(1).toString()));
	newsMgmtContainer.loadAnswer();
	
	ExtendedInternalFrame answer = new ExtendedInternalFrame("Respuesta");
	answer.setCentralPanel(newsMgmtContainer);
	answer.show();
	answer.setInfo("Enviando respuesta");
    }

}
