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
 * ContactsList.java
 *
 * */
package org.digitall.apps.calendar.interfaces;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.PanelGrid;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class ContactsList extends BasicContainerPanel {

    private CBInput cbSkills = new CBInput(0, "Skill");
    private TFInput tfSearchPerson = new TFInput(DataTypes.STRING, "FindPerson", false);
    private AssignButton btnAssign = new AssignButton(true);
    private BasicPanel jpSearch = new BasicPanel();
    private int[] sizeColumnList = { 62, 155, 197 };
    private PanelGrid listPanel = new PanelGrid(30, sizeColumnList, "Personas encontradas", false);
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private FindButton btnFind = new FindButton();
    private CloseButton btnClose = new CloseButton();
    private AcceptButton btnAccept = new AcceptButton();
    private Resource resourceSelected;
    private CFrameContainer parentContainer;
     
    private AddButton btnAdd = new AddButton();
    private AcceptButton btnLeaveMessage = new AcceptButton();
    private AcceptButton leaveMail = new AcceptButton();

    public ContactsList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(780, 405));
	cbSkills.setBounds(new Rectangle(220, 25, 445, 35));
	tfSearchPerson.setBounds(new Rectangle(20, 25, 190, 40));
	btnAssign.setBounds(new Rectangle(720, 35, 40, 25));
	btnAssign.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAssign_actionPerformed(e);
				 }

			     }
	);
	jpSearch.setBounds(new Rectangle(5, 0, 770, 70));
	jpSearch.setLayout(null);
	jpSearch.setBorder(BorderPanel.getBorderPanel("Buscar persona"));
	listPanel.setBounds(new Rectangle(5, 80, 770, 275));
	btnFind.setBounds(new Rectangle(675, 35, 40, 25));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   findButton_actionPerformed(e);
			       }

			   }
	);
	btnClose.setBounds(new Rectangle(735, 375, 40, 25));
	btnAccept.setBounds(new Rectangle(685, 375, 40, 25));
	//new Rectangle(0, 365, 780, 2));
	btnAdd.setBounds(new Rectangle(635, 375, 40, 25));
	btnAdd.setSize(new Dimension(40, 25));
	btnAdd.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnCompose_actionPerformed(e);
			      }

			  }
	);
	btnLeaveMessage.setBounds(new Rectangle(545, 375, 129, 24));
	btnLeaveMessage.setActionCommand("null");
	btnLeaveMessage.setSize(new Dimension(40, 25));
	btnLeaveMessage.setToolTipText("Leave a message");
	btnLeaveMessage.addActionListener(new ActionListener() {

				       public void actionPerformed(ActionEvent e) {
					   btnLeaveMessage_actionPerformed(e);
				       }

				   }
	);
	leaveMail.setBounds(new Rectangle(595, 375, 40, 25));
	leaveMail.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     leaveMail_actionPerformed(e);
				 }

			     }
	);
	jpSearch.add(btnFind, null);
	jpSearch.add(tfSearchPerson, null);
	jpSearch.add(cbSkills, null);
	jpSearch.add(btnAssign, null);
	this.add(leaveMail, null);
	this.add(btnLeaveMessage, null);
	this.add(btnAdd, null);
	 
	this.add(btnAccept, null);
	this.add(btnClose, null);
	this.add(jpSearch, null);
	this.add(listPanel, null);
	listPanel.autoSize();
	setHeaderList();
	loadList();
	cbSkills.autoSize();
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	//agregar otras columnas
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent e) {
					       if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						   dataRow = listPanel.getDataRow();
						   loadObjectSelected();
					       } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {

					       }
					   }

				       }
	);
    }

    public void loadList() {
	String params = "''";
	//Filtrado por privado/publico/personal/nombreUsuarioLocal
	listPanel.setTable(parentContainer, "contacts.getAllContacts", params, headerList);
    }

    private void loadObjectSelected() {
	if (!dataRow.isEmpty()) {

	}
    }

    private void btnAssign_actionPerformed(ActionEvent e) {

    }

    private void findButton_actionPerformed(ActionEvent e) {
	//Debe abrir una ventana con el listado general de 
	//personas para una busqueda mas precisa
    }

    private void btnCompose_actionPerformed(ActionEvent e) {
	//Debe abrir una ventana con para agregar
	//una persona con todos sus datos
    }

    private void btnLeaveMessage_actionPerformed(ActionEvent e) {
	//Enviar un mensaje/novedad al usuario seleccionado
    }

    private void leaveMail_actionPerformed(ActionEvent e) {
	//Componer y enviar un mail al usuario seleccionado
    }

}
