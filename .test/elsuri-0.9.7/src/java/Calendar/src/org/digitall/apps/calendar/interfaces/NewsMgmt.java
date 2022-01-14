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
 * NewsMgmt.java
 *
 * */
package org.digitall.apps.calendar.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.combos.JCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;

public class NewsMgmt extends BasicPrimitivePanel {

    public static final int ANSWER = 2;
    
    private BasicPanel jpDataReceptor = new BasicPanel();
    private BasicPanel jpDataRegister = new BasicPanel();
    private BasicPanel jpDataTitle = new BasicPanel();
    private BasicPanel jpText = new BasicPanel();
    private AcceptButton btnAccept = new AcceptButton();
    private CloseButton btnClose = new CloseButton();
    private CBInput cbUsers = new CBInput(0, "Users", false);
    private CBInput cbPriority = new CBInput(CachedCombo.PRIORITY, "Priority", false);
    private CBInput cbStatus = new CBInput(CachedCombo.STATUS, "Status", false);
    private TFInput tfSubject = new TFInput(DataTypes.STRING,"Subject",true);
    private TFInput tfSearchPerson = new TFInput(DataTypes.STRING,"FindPerson",false);
    private BasicScrollPane jScrollPane1 = new BasicScrollPane();
    private JTextArea taNews = new JTextArea();
    //solo se utilizan para responder un mensaje
    private int idRecipientUser = -1;
    private String text, subject = "";
    private int idNews = -1;
    private BasicPanel pContenedor = new BasicPanel();
    private JButton jButton1 = new JButton();

    public NewsMgmt() {
	try {
	    
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(498, 370));
        //this.setPreferredSize(new Dimension(498, 375));
	jpDataReceptor.setBounds(new Rectangle(5, 0, 480, 60));
	jpDataReceptor.setSize(new Dimension(480, 60));
	jpDataReceptor.setBorder(BorderPanel.getBorderPanel("Nombre Receptor"));
	jpDataReceptor.setLayout(null);
	jpDataRegister.setBounds(new Rectangle(5, 60, 480, 60));
	jpDataRegister.setSize(new Dimension(480, 60));
	jpDataRegister.setBorder(BorderPanel.getBorderPanel("Datos Registro"));
	jpDataRegister.setLayout(null);
	jpDataTitle.setBounds(new Rectangle(5, 120, 480, 60));
	jpDataTitle.setSize(new Dimension(480, 60));
	jpDataTitle.setBorder(BorderPanel.getBorderPanel("Título"));
	jpDataTitle.setLayout(null);
	jpText.setBounds(new Rectangle(5, 180, 480, 145));
        jpText.setSize(new Dimension(480, 145));
	jpText.setBorder(BorderPanel.getBorderPanel("Mensaje"));
	jpText.setLayout(null);
	cbUsers.setBounds(new Rectangle(20, 20, 200, 20));
	cbUsers.setBounds(new Rectangle(245, 15, 225, 35));
	cbUsers.autoSize();
	cbUsers.getCombo().addItemListener(new ItemListener() {

		    public void itemStateChanged(ItemEvent evt) {

			if (evt.getStateChange() == ItemEvent.SELECTED) {
			    //tfName.setText(cbUsers.toString());
			    //loadData(Integer.parseInt(cbUsers.getSelectedValue().toString())); 
						
			}
		    }

		});
	cbPriority.setBounds(new Rectangle(10, 15, 220, 35));
	cbPriority.setSize(new Dimension(215, 35));
	cbPriority.getCombo().addItemListener(new ItemListener() {

		    public void itemStateChanged(ItemEvent evt) {

			if (evt.getStateChange() == ItemEvent.SELECTED) {
			    //tfName.setText(cbUsers.toString());
			    //loadData(Integer.parseInt(cbUsers.getSelectedValue().toString())); 
						
			}
		    }

		});

	cbStatus.setBounds(new Rectangle(245, 15, 225, 35));
	cbStatus.setSize(new Dimension(215, 35));
	cbStatus.getCombo().addItemListener(new ItemListener() {

		    public void itemStateChanged(ItemEvent evt) {

			if (evt.getStateChange() == ItemEvent.SELECTED) {
			    //tfName.setText(cbUsers.toString());
			    //loadData(Integer.parseInt(cbUsers.getSelectedValue().toString())); 
						
			}
		    }

		});

	tfSubject.setBounds(new Rectangle(10, 15, 460, 35));
	tfSubject.setSize(new Dimension(455, 35));
	
	jScrollPane1.setBounds(new Rectangle(5, 15, 470, 125));
	taNews.setWrapStyleWord(true);
	taNews.setLineWrap(true);
        pContenedor.setLayout(null);
        jButton1.setText("jButton1");
        jButton1.setBounds(new Rectangle(285, 145, 85, 25));
        tfSearchPerson.setBounds(new Rectangle(10, 15, 220, 35));
	tfSearchPerson.setSize(new Dimension(230, 35));
	cbStatus.autoSize();
	cbPriority.autoSize();
        jpDataRegister.add(cbStatus, null);
        jpDataRegister.add(cbPriority, null);
        jpDataReceptor.add(cbUsers, null);
        jpDataReceptor.add(cbUsers, null);
        jpDataReceptor.add(tfSearchPerson, null);
        pContenedor.add(jpDataReceptor, null);
        pContenedor.add(jpDataRegister, null);
        jpDataTitle.add(tfSubject, null);
        pContenedor.add(jpDataTitle, null);
        jScrollPane1.getViewport().add(taNews, null);
        jpText.add(jButton1, null);
        jpText.add(jScrollPane1, null);
        pContenedor.add(jpText, null);
	btnAccept.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAccept_actionPerformed(e);
			      }

			  }
	);
	
	btnClose.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
			          btnClose_actionPerformed(e);
			      }

			  }
	);
	
	tfSearchPerson.setKeyListener(new KeyAdapter() {

				     public void keyTyped(KeyEvent e) {
					 char caracter = e.getKeyChar();
					 if ((caracter == KeyEvent.VK_ENTER)) {
					     loadComboUsers();
					     
					 }
				     }

				 }
	);
        this.add(pContenedor, null);
        this.addButton(btnClose);
        this.addButton(btnAccept);
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().getGeneralButtons().addButton(btnAccept);
	getParentInternalFrame().getGeneralButtons().addButton(btnClose);
    }
    
    
    public void loadComboUsers() {
	JCombo combo = new JCombo();
	String param = "'" + tfSearchPerson.getString() + "'";
	combo.loadJCombo(LibSQL.exFunction("calendar.getAllUsersByFilter", param));
	ItemListener resourceItemListener = new ItemListener() {

		public void itemStateChanged(ItemEvent evt) {
		    if (evt.getStateChange() == ItemEvent.SELECTED) {
			//loadResource();
		    }
		}

	    }
	;
	cbUsers.setCombo(combo);
	cbUsers.setItemListener(resourceItemListener);
	cbUsers.updateUI();
    }
    
    public void loadComboDataRecipient () {
	JCombo combo = new JCombo();
	String param = "" + idRecipientUser;
	combo.loadJCombo(LibSQL.exFunction("calendar.getDataRecipient", param));
	ItemListener resourceItemListener = new ItemListener() {

		public void itemStateChanged(ItemEvent evt) {
		    if (evt.getStateChange() == ItemEvent.SELECTED) {
			//loadResource();
		    }
		}

	    }
	;
	cbUsers.setCombo(combo);
	cbUsers.setItemListener(resourceItemListener);
	cbUsers.updateUI();
    }

    private void btnAccept_actionPerformed(ActionEvent e) {
	int res = -1;
	String params = "";

	if (tfSubject.getString().equals("") || taNews.getText().trim().equals("")
	    || cbUsers.getSelectedValue().toString().equals("0") )  {
	    Advisor.messageBox("Controles que los campos esten correctamente completados.","Atención");
	} else {
	    params = cbUsers.getSelectedValue().toString() + ","
		    + cbPriority.getSelectedValue().toString() + ","
		    + cbStatus.getSelectedValue().toString() + ",'"
		    + taNews.getText() + "','"
		    + tfSubject.getString() + "'";
		    
	    res = LibSQL.getInt("calendar.addnews",params);
	    
	    if ( idNews != -1) {    //CAMBIO EL ESTADO A RESPONDIDO
	        params = "" + idNews + "," 
	                + ANSWER +",true";
	        res = LibSQL.getInt("calendar.setNewsStatus",params);
	        idRecipientUser = -1;
	    }
	    if ( res != -1)  {
		getParentInternalFrame().close();
	    } else {
		Advisor.messageBox("Ocurrio un error, puede que algunos datos \nno se hayan grabado correctamente", "Error");
	    }
	}
	
    }

    public void setIdRecipientUser(int _idRecipientUser) {
	idRecipientUser = _idRecipientUser;
    }

    public void setText(String _text) {
	text = _text;
    }

    public void setSubject(String _subject) {
	subject = _subject;
    }
    public void loadAnswer(){
	tfSearchPerson.setEditable(false);
	taNews.setText(text);
	tfSubject.setValue("Re: " +subject);
	loadComboDataRecipient();
    }

    public void setInfo(){
	getParentInternalFrame().setInfo("Enviando Mensaje");
    }

    public void setIdNews(int _idNews) {
	idNews = _idNews;
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

}
