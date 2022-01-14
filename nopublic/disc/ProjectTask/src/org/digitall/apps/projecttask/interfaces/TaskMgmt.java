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
 * TaskMgmt.java
 *
 * */
package org.digitall.apps.projecttask.interfaces;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import org.digitall.apps.projecttask.classes.Task;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;

public class TaskMgmt extends BasicContainerPanel {
    private final int FRAME = 1;
    private final int INTERNALFRAME = 2;
    private final int DIALOG = 3;
    private int parentType = -1;
    private Component parent;
    private int idTaskSelecter = -1;   
    private String date = Environment.currentDate;

    private AcceptButton btnAccept = new AcceptButton();
    private CloseButton btnClose = new CloseButton();
    private BasicPanel dataPanel = new BasicPanel();
    
    private TFInput tfName = new TFInput(DataTypes.STRING,"Name",true);
    private TFInput tfEstimationTime = new TFInput(DataTypes.INTEGER,"EstimationTime",false);
    private TFInput tfPriority = new TFInput(DataTypes.INTEGER,"Priority", false);
     
    
    private Task taskClass;
    private Advisor advisor;

    public TaskMgmt(BasicInternalFrame _parent, int _idTaskSelecter, Task _taskClass) {
	try {
	    taskClass = _taskClass; 
	    idTaskSelecter = _idTaskSelecter;
	    parent = _parent;
	    parentType = INTERNALFRAME;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public TaskMgmt(BasicDialog _parent, int _idTaskSelecter, Task _taskClass) {
	try {
	    idTaskSelecter = _idTaskSelecter;
	    taskClass = _taskClass;
	    parent = _parent;
	    parentType = DIALOG;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public TaskMgmt(JFrame _parent,int _idTaskSelecter, Task _taskClass) {
	try {
	    idTaskSelecter = _idTaskSelecter;
	    taskClass = _taskClass;
	    parent = _parent;
	    parentType = FRAME;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    private void jbInit() throws Exception {
	this.setLayout( null );
	this.setSize(new Dimension(300, 235));

	if ( idTaskSelecter != 0  ) {
	    dataPanel.setBorder(BorderPanel.getBorderPanel("Agregar Tarea",Color.BLUE,Color.BLACK));

	}
	else {
	    dataPanel.setBorder(BorderPanel.getBorderPanel("Agregar Proyecto",Color.BLUE,Color.BLACK));

	} 
	
	
	btnAccept.setBounds(new Rectangle(190, 185, 40, 25));
	btnAccept.setSize(new Dimension(40, 25));
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	btnClose.setBounds(new Rectangle(245, 185, 40, 25));
	btnClose.setSize(new Dimension(40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	dataPanel.setBounds(new Rectangle(15, 5, 270, 165));
	dataPanel.setLayout(null);
	
	tfName.setBounds(new Rectangle(15, 25, 245, 35));

	tfEstimationTime.setBounds(new Rectangle(15, 70, 245, 35));

	tfPriority.setBounds(new Rectangle(15, 115, 245, 35));
	tfPriority.setSize(new Dimension(245, 35));
	//new Rectangle(0, 175, 295, 2));
	dataPanel.add(tfName, null);
	dataPanel.add(tfEstimationTime, null);
	dataPanel.add(tfPriority, null);
	 
	this.add(dataPanel, null);
	this.add(btnClose, null);
	this.add(btnAccept, null);
    }


    private void dispose() {
	    switch (parentType) {
		case DIALOG:
		    ((BasicDialog)parent).dispose();
		    break;
		case INTERNALFRAME:
		    ((BasicInternalFrame)parent).setVisible(false);
		    ((BasicInternalFrame)parent).hide();
		    break;
		case FRAME:
		    ((JFrame)parent).dispose();
		    break;
	    }
    }

  

    private void btnClose_actionPerformed(ActionEvent e) {
	
	dispose();

    }

    private void btnAccept_actionPerformed(ActionEvent e) {
    
	if (tfName.getString().equals("")){
	    advisor.messageBox("El nombre es obligatorio","Atención");
	}else {
	   taskClass.setName(tfName.getString());
	   taskClass.setEstimationTime(Integer.parseInt("0"+tfEstimationTime.getString()));
	   taskClass.setPriority(Integer.parseInt("0"+tfPriority.getString()));
	   taskClass.setIdParent(idTaskSelecter);
	   taskClass.setStartDate(date);
	   taskClass.saveData();
	    dispose();
	}
	   
	
    }

}
