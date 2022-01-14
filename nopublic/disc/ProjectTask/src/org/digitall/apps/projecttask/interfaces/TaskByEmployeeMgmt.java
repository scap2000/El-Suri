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
 * TaskByEmployeeMgmt.java
 *
 * */
package org.digitall.apps.projecttask.interfaces;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.digitall.apps.projecttask.classes.Task;
import org.digitall.lib.org.User;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.common.components.combos.JCombo;
import org.digitall.lib.components.grid.PanelGrid;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class TaskByEmployeeMgmt extends BasicContainerPanel {

    private final int FRAME = 1;
    private final int INTERNALFRAME = 2;
    private final int STATEPLAY = 1;
    private final int DIALOG = 3;
    private final int STATENONINITIATED = 5;
    private final int STATEFINISHED = 4;
    private int parentType = -1;
    private Component parent;
    
    private BasicPanel taskPanel = new BasicPanel();
    private TFInput tfEtimationTime = new TFInput(DataTypes.STRING,"EstimationTime",false);
    private TFInput tfPriority = new TFInput(DataTypes.STRING,"Priority",false);
    private CBInput cbTasks = new CBInput( 0, "Task", false);
    private AssignButton btnDownTask = new AssignButton(true);
    private TFInput tfName = new TFInput(DataTypes.STRING,"Name",false);
    private DeleteButton btnDelete = new DeleteButton();
     
    private CloseButton btnClose = new CloseButton();
    private String namePerson = "";
    private User user;
    private Task task = new Task();
    
    private int[] sizeColumnList = {174,80,80,66,111,60};
   // private PanelGrid listPanel = new PanelGrid(10, sizeColumnList, "Tareas", false);
    private PanelGrid listPanel = new PanelGrid(30, sizeColumnList, "Tareas Asignadas", false);
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private int idTaskByUser;
    private Advisor advisor;
    private ListSelectionModel listSelectionModel;
    private int idRowSelected = -1;
    private String date = Environment.currentDate;
    private ReportDetailJDialog reportDetailBasicDialog;
    private int idTask = -1;
    private AddButton btnFinalizeTask = new AddButton();
    private BasicCheckBox chkFilter = new BasicCheckBox();

    public TaskByEmployeeMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public TaskByEmployeeMgmt(BasicInternalFrame _parent, String _namePerson, User _user) {
	try {
	    namePerson = _namePerson;
	    user = _user;
	    parent = _parent;
	    parentType = INTERNALFRAME;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public TaskByEmployeeMgmt(BasicDialog _parent, String _namePerson, User _user) {
	try {
	    namePerson = _namePerson;
	    user = _user;
	    parent = _parent;
	    parentType = DIALOG;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public TaskByEmployeeMgmt(JFrame _parent, String _namePerson, User _user) {
	try {
	    namePerson = _namePerson;
	    user = _user;
	    parent = _parent;
	    parentType = FRAME;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout( null );
	this.setSize(new Dimension(635, 435));
	taskPanel.setBounds(new Rectangle(15, 10, 605, 115));
	taskPanel.setLayout(null);
	tfEtimationTime.setBounds(new Rectangle(320, 65, 110, 35));
	tfEtimationTime.setSize(new Dimension(110, 35));
	tfEtimationTime.setEditable(false);
	tfPriority.setBounds(new Rectangle(435, 65, 110, 35));
	tfPriority.setSize(new Dimension(110, 35));
	tfPriority.setEditable(false);
	cbTasks.setBounds(new Rectangle(25, 65, 280, 35));
	cbTasks.autoSize();
	cbTasks.getCombo().addItemListener(new ItemListener() {
	            public void itemStateChanged (ItemEvent evt) {
			
			if (evt.getStateChange() == ItemEvent.SELECTED) {
			    //System.out.println("id: " + cbTasks.getSelectedValue().toString());
			    //loadData(Integer.parseInt(cbTasks.getSelectedValue().toString()));
			    
			}
		    }
		});
	
	btnDownTask.setBounds(new Rectangle(555, 75, 40, 25));
	btnDownTask.setSize(new Dimension(40, 25));
	btnDownTask.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       btnDownTask_actionPerformed(e);
				   }

			       }
	);
	taskPanel.add(btnDownTask, null);
	taskPanel.add(cbTasks, null);
	taskPanel.add(tfPriority, null);
	taskPanel.add(tfEtimationTime, null);
	taskPanel.add(tfName, null);
	taskPanel.setBorder(BorderPanel.getBorderPanel("Agregar Tarea", Color.BLUE, Color.BLACK));
	tfName.setBounds(new Rectangle(25, 25, 280, 35));
	tfName.setSize(new Dimension(280, 35));
	tfName.setToolTipText("null");
	tfName.setValue(namePerson);
	tfName.setEditable(false);
	btnDelete.setText("");
	btnDelete.setBounds(new Rectangle(510, 370, 45, 25));
	btnDelete.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnDelete_actionPerformed(e);
				 }

			     }
	);
	//new Rectangle(0, 360, 640, 5));
	btnClose.setText("");
	btnClose.setBounds(new Rectangle(570, 370, 40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	listPanel.setBounds(new Rectangle(10, 155, 610, 195));
	listPanel.setSize(new Dimension(610, 195));
	btnFinalizeTask.setBounds(new Rectangle(380, 370, 125, 25));
	btnFinalizeTask.setSize(new Dimension(125, 25));
	btnFinalizeTask.setText("Finalizar");
	btnFinalizeTask.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      btnFinalizeTask_actionPerformed(e);
				  }

			      }
	);
	chkFilter.setText("No Finalizada");
	chkFilter.setBounds(new Rectangle(495, 130, 120, 15));
	chkFilter.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     chkFilter_actionPerformed(e);
				 }

			     }
	);
	this.add(chkFilter, null);
	this.add(btnFinalizeTask, null);
	this.add(btnClose, null);
	 
	this.add(taskPanel, null);
	this.add(btnDelete, null);
	this.add(listPanel, null);
	listPanel.autoSize();
	setHeaderList();
	loadList();
	
	loadComboTask();
	task = new Task(Integer.parseInt(cbTasks.getSelectedValue().toString())); 

	tfEtimationTime.setValue(String.valueOf(task.getEstimationTime()));
	tfPriority.setValue(String.valueOf(task.getPriority()));
	 
    }

    private void loadComboTask(){
	JCombo combo = new JCombo();

	combo.loadJCombo(LibSQL.exFunction("task.gettasksnotassigntouser", String.valueOf(user.getIduser())));
	ItemListener taskItemListener = new ItemListener() {
		    public void itemStateChanged (ItemEvent evt) {
			
			if (evt.getStateChange() == ItemEvent.SELECTED) {
			    
			   loadData(Integer.parseInt(cbTasks.getSelectedValue().toString()));
			    
			}
		    }
		};
		
	cbTasks.setCombo(combo);	
	cbTasks.setItemListener(taskItemListener);
	cbTasks.updateUI();
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

    public void loadList() {
	StringBuffer params = new StringBuffer();
      
	params.append(String.valueOf(user.getIduser()));
	
	listPanel.setTable(parent, "task.gettaskbyuser", params.toString(), headerList);
    }

    public void loadListFilter() {
	StringBuffer params = new StringBuffer();
      
	params.append(String.valueOf(user.getIduser()));
	
	listPanel.setTable(parent, "task.gettaskbyuserfilter", params.toString(), headerList);
    }
    
    private void setHeaderList() {           
	    headerList.removeAllElements();  
	    headerList.addElement("*");//idtaskbyuser
	    headerList.addElement("*");//iduser
	    headerList.addElement("Nombre");
	    headerList.addElement("Fecha Inicio");
	    headerList.addElement("Fecha Fin");
	    headerList.addElement("Prioridad");
	    headerList.addElement("Hs. Trabajadas");
	    headerList.addElement("Estado");
	    headerList.addElement("*"); //idtaskstate me sirve para determinar si esta iniciada la tarea
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
						       //loadAccountData();
						        idTaskByUser = Integer.parseInt(dataRow.get(0).toString());
						        idTask = Integer.parseInt(dataRow.get(1).toString());
						        reportDetailBasicDialog = new ReportDetailJDialog(idTask,idTaskByUser);
							reportDetailBasicDialog.setModal(true); 
							reportDetailBasicDialog.setVisible(true);
												
						        
						       
						   }
					       }

					   }
	    );
	    listSelectionModel = listPanel.getTable().getSelectionModel();
	    listSelectionModel.addListSelectionListener(
	                                new RowChange());
	    listPanel.getTable().setSelectionModel(listSelectionModel);
	    
    
	}
    private void btnClose_actionPerformed(ActionEvent e) {

	dispose();

    }
    
    private void loadData(int _idTask) {
        
	task.loadData(_idTask); 
	tfEtimationTime.setValue(String.valueOf(task.getEstimationTime()));
	tfPriority.setValue(String.valueOf(task.getPriority()));
	
    }

    private void btnDownTask_actionPerformed(ActionEvent e) {
	
	StringBuffer param = new StringBuffer();
	
	if (task.getIdtask() == - 1) {
	   advisor.messageBox("Debe selecionar un elemento de la lista antes de continuar.", "AtenciÃ³n"); 
	   return;
	}
	
	param.append(String.valueOf(user.getIduser()) + "," +   //_iduser
	             String.valueOf(task.getIdtask()) + ",'" +   //_idtask
	              date + "'," + 					//_startdate
		      "''," + 					//_enddate
		      "''," +                    		//_workshours interval
		      "5,"+					//_idtaskstate
		      String.valueOf(task.getPriority()));	//_priority
		      
		
	idTaskByUser = LibSQL.getInt("task.addtaskbyuser", param.toString());
	
	loadList();
	
	loadComboTask();
	//task.loadData(Integer.parseInt(cbTasks.getSelectedValue().toString()));
	task.loadDataDefault();
    }

    private void btnDelete_actionPerformed(ActionEvent e) {
	boolean update = false;
    
	if ( idRowSelected == - 1) {
	   advisor.messageBox("Debe selecionar un elemento de la tabla antes de continuar.", "AtenciÃ³n"); 
	   return;
	}
	
	if (Integer.parseInt(dataRow.get(8).toString()) == STATENONINITIATED){
	    
	    update = LibSQL.getBoolean("task.deletetaskbyuser", "'" + idRowSelected +"'");
	    loadList();     
	    loadComboTask();
	    idRowSelected = -1;

		    
	}else {
	    
	    if (Integer.parseInt(dataRow.get(8).toString()) == STATEFINISHED){
	             
		     advisor.messageBox("Tarea terminada. No puede eliminarse. ", "AtenciÃ³n"); 
		    
	    } else{            
	    
	            advisor.messageBox("Tarea en desarrollo. No puede eliminarse. ", "AtenciÃ³n"); 
	    
	    }
	
	}

    }

    private void btnAccept_actionPerformed(ActionEvent e) {
    
	System.out.println(listPanel.getTable().getRowCount());

    }
    
    private void btnFinalizeTask_actionPerformed(ActionEvent e) {
	String param;
	
	if ( idRowSelected == - 1) {
	   advisor.messageBox("Debe selecionar un elemento de la tabla antes de continuar.", "AtenciÃ³n"); 
	   return;
	}
	
	if (Integer.parseInt(dataRow.get(8).toString()) == STATEPLAY || 
	    Integer.parseInt(dataRow.get(8).toString()) == STATEFINISHED ) {
	    
		if (Integer.parseInt(dataRow.get(8).toString()) == STATEPLAY) {
	         
		    advisor.messageBox("Se esta trabajando sobre esta tarea. No puede finalizarse. ", "AtenciÃ³n"); 
		 
		}else {
		    
		    advisor.messageBox("Tarea ya finalizada. ", "AtenciÃ³n"); 
		
		}
	   
	
	} else {
		param = "" + idRowSelected + ",'"
			+ date + "'"    ;
		if (!LibSQL.getBoolean("task.finishedtask",param)){
		    advisor.messageBox("Fallo al realizar la finalización. ", "AtenciÃ³n"); 
		    return;
		}
		loadList();
		loadComboTask();
	}

    }

    private void chkFilter_actionPerformed(ActionEvent e) {
    
	if (chkFilter.isSelected()){
	    	
	    loadListFilter();
		
	} else {
	 
	    loadList();
	
	} 

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
