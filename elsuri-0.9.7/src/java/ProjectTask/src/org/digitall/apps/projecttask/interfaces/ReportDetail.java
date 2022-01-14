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
 * ReportDetail.java
 *
 * */
package org.digitall.apps.projecttask.interfaces;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.digitall.apps.projecttask.classes.Task;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.grid.PanelGrid;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class ReportDetail extends BasicContainerPanel {
    private final int FRAME = 1;
    private final int INTERNALFRAME = 2;
    private final int DIALOG = 3;
    private int parentType = -1;
    private Component parent;


    private BasicPanel jpTask = new BasicPanel();
    private TFInput tfTaskName = new TFInput(DataTypes.STRING,"Task",false);;
    private TFInput tfAssignedDate = new TFInput(DataTypes.STRING,"AssignedDate",false);;
    private TFInput tfPriority = new TFInput(DataTypes.STRING,"Priority",false);
    private TFInput tfEstimationTime = new TFInput(DataTypes.STRING,"EstimationTime",false);
    
    private int[] sizeColumnList = {175,80,80,100,80};
    // private PanelGrid listPanel = new PanelGrid(10, sizeColumnList, "Tareas", false);
    private PanelGrid listPanel = new PanelGrid(30, sizeColumnList, "Detalle de la Tarea Asignada", false);
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private ListSelectionModel listSelectionModel;
    private int idRowSelected = -1;
    private Task task;
    private int idTask = -1;
    private int idTaskByUser = -1;
    private CloseButton btnClose = new CloseButton();
     
    

    public ReportDetail() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public ReportDetail(BasicInternalFrame _parent, int _idTask, int _idTaskByUser) {
	try {
	    idTask = _idTask;
	    idTaskByUser = _idTaskByUser;
	    parent = _parent;
	    parentType = INTERNALFRAME;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public ReportDetail(BasicDialog _parent, int _idTask, int _idTaskByUser) {
	try {
	    idTask = _idTask;
	    idTaskByUser = _idTaskByUser;
	    parent = _parent;
	    parentType = DIALOG;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public ReportDetail(JFrame _parent, int _idTask, int _idTaskByUser) {
	try {
	    idTask = _idTask;
	    idTaskByUser = _idTaskByUser;
	    parent = _parent;
	    parentType = FRAME;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }


    private void jbInit() throws Exception {
	this.setLayout( null );
	this.setSize(new Dimension(575, 525));
	jpTask.setBounds(new Rectangle(20, 15, 540, 115));
	jpTask.setBorder(BorderPanel.getBorderPanel("Tarea",Color.BLUE,Color.BLACK));
	jpTask.setLayout(null);
	tfTaskName.setBounds(new Rectangle(25, 25, 290, 35));
	tfTaskName.setEditable(false);
	tfAssignedDate.setBounds(new Rectangle(25, 65, 90, 35));
	tfAssignedDate.setSize(new Dimension(90, 35));
	tfAssignedDate.setEditable(false);
	tfPriority.setBounds(new Rectangle(145, 65, 170, 35));
	tfPriority.setSize(new Dimension(170, 35));
	tfPriority.setEditable(false);
	tfEstimationTime.setBounds(new Rectangle(335, 65, 175, 35));
	tfEstimationTime.setSize(new Dimension(175, 35));
	tfEstimationTime.setEditable(false);
	listPanel.setBounds(new Rectangle(20, 135, 540, 335));
	btnClose.setBounds(new Rectangle(510, 480, 40, 25));
	btnClose.setSize(new Dimension(40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	//new Rectangle(0, 475, 575, 2));

	jpTask.add(tfEstimationTime, null);
	jpTask.add(tfPriority, null);
	jpTask.add(tfAssignedDate, null);
	jpTask.add(tfTaskName, null);
	
	 
	this.add(btnClose, null);
	this.add(jpTask, null);
	this.add(listPanel);
	listPanel.autoSize();
	task = new Task (idTask);
	tfTaskName.setValue(task.getName());
	tfAssignedDate.setValue("" + Proced.setFormatDate(task.getStartDate().toString(),true));
	tfPriority.setValue("" + task.getPriority());
	tfEstimationTime.setValue("" + task.getEstimationTime());
	setHeaderList();
	loadList();
	
    }
    
    private void setHeaderList() {           
	    headerList.removeAllElements();  
	    headerList.addElement("*");
	    headerList.addElement("*");
	    headerList.addElement("Nombre");
	    headerList.addElement("Fecha");
	    headerList.addElement("Hora Inicio");
	    headerList.addElement("Hora Fin");
	    headerList.addElement("Horas Trabajadas");
	    headerList.addElement("*");
			
	    				     
	    //se aÃ±ade un escuhcador para los eventos del mouse
	    listPanel.getTable().addMouseListener(new MouseAdapter() { 
					       public void mouseClicked(MouseEvent e) {
						   if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						       dataRow = listPanel.getDataRow();
						       idRowSelected = Integer.parseInt(dataRow.get(0).toString());
						       
						       //dataRow.get(0); elemento a eliminar
						       
												       
						   } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON3) {
						      // loadTransactionsDetail();
						   } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						       //Debe Mostrar el detalle
						   }
					       }

					   }
	    );
	    listSelectionModel = listPanel.getTable().getSelectionModel();
	    listSelectionModel.addListSelectionListener(
					new RowChange());
	    listPanel.getTable().setSelectionModel(listSelectionModel);
	    
    
    }
    
    public void loadList() {
	StringBuffer params = new StringBuffer();
      
	params.append(idTaskByUser);
	//task.getdetailbytask(integer, integer, integer)
	//getdetailbytask
	listPanel.setTable(parent, "task.getdetailbytask", params.toString(), headerList);
	
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

    class RowChange implements ListSelectionListener {
	public void valueChanged(ListSelectionEvent e) {
	    ListSelectionModel lsm = (ListSelectionModel)e.getSource();

	    if (lsm.isSelectionEmpty()) {
	
	    } else {
		// Find out which indexes are selected.
		int minIndex = lsm.getMinSelectionIndex();
		int maxIndex = lsm.getMaxSelectionIndex();
		for (int i = minIndex; i <= maxIndex; i++) {
		    if (lsm.isSelectedIndex(i)) {
			idRowSelected = Integer.parseInt(listPanel.getTable().getModel().getValueAt(i,0).toString());
			
		    }
		}
	    }
	}

    }

}
