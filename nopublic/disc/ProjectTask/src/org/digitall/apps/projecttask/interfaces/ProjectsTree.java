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
 * ProjectsTree.java
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
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.digitall.apps.projecttask.classes.Task;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.Tree;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.EditButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class ProjectsTree extends BasicContainerPanel {
    private final int FRAME = 1;
    private final int INTERNALFRAME = 2;
    private final int DIALOG = 3;
    private int parentType = -1;
    private Component parent;

    private JTree jtTask = new JTree();
    private Tree tree = new Tree();
    private BasicButton btnAssignAll = new BasicButton();

    private BasicScrollPane jScrollPane1 = new BasicScrollPane();
 
    public static int PROJECT = 0; 
    public  String taskName = "";
    private int idTaskSelecter = -1;
     
    private BasicPanel treePanel = new BasicPanel();
    private AddButton btnAddMore = new AddButton();
    private DeleteButton btnDelete = new DeleteButton();
    private EditButton btnEdit = new EditButton();
    private CloseButton btnClose = new CloseButton();
    private BasicPanel detaliPanel = new BasicPanel();
    private TFInput tfName = new TFInput(DataTypes.STRING,"Name",false);
    private TFInput tfEstimationTime = new TFInput(DataTypes.INTEGER,"EstimationTime",false);
    private TFInput tfPriority = new TFInput(DataTypes.INTEGER,"Priority", false);
    
    private Advisor advisor;
    private Task taskClass;
    private String date = Environment.currentDate;
        
    
    public ProjectsTree() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public ProjectsTree(BasicInternalFrame _parent) {
	try {
	    parent = _parent;
	    parentType = INTERNALFRAME;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public ProjectsTree(BasicDialog _parent) {
	try {
	    parent = _parent;
	    parentType = DIALOG;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public ProjectsTree(JFrame _parent) {
	try {
	    parent = _parent;
	    parentType = FRAME;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(560, 470));
	//createNodes (treeTask);
	//Create a tree that allows one selection at a time.
	jtTask.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

	//Escucha cuando cambia la selecciÃ³n.
	jtTask.addTreeSelectionListener(new TreeSelectionListener() {

				     public void valueChanged(TreeSelectionEvent e) {

		 /*     
                Object nodeInfo = selectNode.getUserObject();
                tfName.setText(nodeInfo.toString());


                //obtengo el nodo padre
                DefaultMutableTreeNode parent = (DefaultMutableTreeNode)
                                   selectNode.getParent();
	        
                //int index = parent.getIndex(selectNode);
                //tfPriority.setText(parent.toString() +", " +index);

                //Obtengo el path que me sirve para obtener el indice de relacion
                //tfEstimation.setText(e.getPath().toString());

                
                /*
                if (e.getPath().getPathCount() == 1) {
                    tfName.setText("");
                    tfPriority.setText("");
                }
                
                if (selectNode.isLeaf()) {
                    tfChild.setText("Hijo");
                } else {
                    tfChild.setText("Padre");
                                        
                }
*/
				     }

				 }
	);
	btnAssignAll.setText("Asignar Tarea Todos");
	btnAssignAll.setBounds(new Rectangle(155, 420, 180, 25));
	btnAssignAll.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     //jbtAssign_actionPerformed(e);
				     btnAssignAll_actionPerformed(e);				     
				 }

			     }
	);
	
	jScrollPane1.setBounds(new Rectangle(20, 30, 495, 245));
	//new Rectangle(0, 410, 565, 10));
	treePanel.setBounds(new Rectangle(10, 15, 535, 300));
	treePanel.setLayout(null);
	btnAddMore.setBounds(new Rectangle(350, 420, 40, 25));
	btnAddMore.setSize(new Dimension(40, 25));
	btnAddMore.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      btnAddMore_actionPerformed(e);
				  }

			      }
	);
	btnDelete.setBounds(new Rectangle(450, 420, 40, 25));
	btnDelete.setSize(new Dimension(40, 25));
	btnDelete.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnDelete_actionPerformed(e);
				 }

			     }
	);
	btnEdit.setBounds(new Rectangle(400, 420, 40, 25));

	btnEdit.setSize(new Dimension(40, 25));
	btnEdit.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnEdit_actionPerformed(e);
			       }

			   }
	);
	btnClose.setBounds(new Rectangle(505, 420, 40, 25));
	btnClose.setSize(new Dimension(40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	detaliPanel.setBounds(new Rectangle(10, 320, 535, 75));
	detaliPanel.setLayout(null);
	tfName.setBounds(new Rectangle(20, 25, 260, 35));
	tfName.setEditable(false);
	tfEstimationTime.setBounds(new Rectangle(285, 25, 115, 35));
	tfEstimationTime.setEditable(false);
	tfPriority.setBounds(new Rectangle(405, 25, 115, 35));
	tfPriority.setSize(new Dimension(115, 35));
	tfPriority.setEditable(false);
	treePanel.setBorder(BorderPanel.getBorderPanel("Tareas por Projectos",Color.BLUE,Color.BLACK));
	detaliPanel.add(tfEstimationTime, null);
	detaliPanel.add(tfName, null);
	detaliPanel.add(tfPriority, null);
	detaliPanel.setBorder(BorderPanel.getBorderPanel("Detalle",Color.BLUE,Color.BLACK));
	this.add(detaliPanel, null);
	this.add(btnClose, null);
	jScrollPane1.getViewport().add(jtTask,null);
	treePanel.add(jScrollPane1, null);
	this.add(treePanel, null);
	 
	this.add(btnAssignAll, null);
	this.add(btnDelete, null);
	this.add(btnEdit, null);
	this.add(btnAddMore, null);
	taskClass = new Task();
	
	drawTree();
    }

      private void drawTree() {
	try {
	    jtTask = tree.createTree("task.task", "idtask", "name", "0");
	    jtTask.addTreeSelectionListener(new TreeSelectionListener() {

	                public void valueChanged(TreeSelectionEvent evt) {
	                    TreePath paths = evt.getPath();
	                    String node = paths.getLastPathComponent().toString();
	                    idTaskSelecter = Integer.parseInt(node.substring(0, node.indexOf("-") - 1).trim());
	                    taskName = node.substring(node.indexOf("-") + 1, node.length()).trim();
			    taskClass = new Task(idTaskSelecter);
			    tfName.setValue(taskClass.getName());
			    tfEstimationTime.setValue(taskClass.getEstimationTime());
			    tfPriority.setValue(taskClass.getPriority());

	                }

	            });
	} catch (Exception e) {
	    // TODO
	}
	jScrollPane1.getViewport().add(jtTask, null);
    }

    private void btnAdd_actionPerformed(ActionEvent e) {
       
    }

    private void btnAssignAll_actionPerformed(ActionEvent e) {
    
	
	 StringBuffer param = new StringBuffer();
	 String cartel;
	 
	 //asigna una tarea a todos los usuarios
	 if (idTaskSelecter == - 1) {
	     advisor.messageBox("Debe selecionar una tarea para asignar.", "AtenciÃ³n"); 
	     return;
	 }
	
	DefaultMutableTreeNode node = (DefaultMutableTreeNode) jtTask.getLastSelectedPathComponent();
	if (node.isLeaf()) {
	    cartel ="Esta por asignar esta tarea a todoslos usuarios.\n Desea continuar?";

	}
	else {
	    
	    cartel ="Esta por asignar este proyectos a todoslos usuarios.\n Desea continuar?";
	} 
	 
	 if (Advisor.question("Cuidado", cartel)) {
	    
	     param.append(String.valueOf(taskClass.getIdtask()) + ",'" +   //_idtask
	                   date + "'," +                                   //_startdate
	                   "''," +                                   //_enddate
	                   "''," +                                   //_workshours interval
	                   "5,"+                                     //_idtaskstate
	                   String.valueOf(taskClass.getPriority()));      //_priority
	     
	     if(!LibSQL.getBoolean("task.assigntaskall", param.toString())){
	     
	         advisor.messageBox("Fallo al asignar la tarea en la base de datos", "AtenciÃ³n"); 
	         return;
	     
	     }	else {		
		
		advisor.messageBox("Asignacion exitosa.", "AtenciÃ³n"); 
		return;
	     }
	    
	 }else {
	 
	     advisor.messageBox("AsignaciÃ³n cancelada", "AtenciÃ³n"); 
	     return;
	     
	 
	 }
	 
	 /*
	 param.append(String.valueOf(taskClass.getIdtask()) + ",'" +   //_idtask
	               date + "'," +                                   //_startdate
	               "''," +                                   //_enddate
	               "''," +                                   //_workshours interval
	               "5,"+                                     //_idtaskstate
	               String.valueOf(taskClass.getPriority()));      //_priority
	 
	 if(!LibSQL.getBoolean("task.assigntaskall", param.toString())){
	 
	     advisor.messageBox("Fallo al asignar la tarea.", "AtenciÃ³n"); 
	     return;
	 
	 } else {
	     
	     advisor.messageBox("AsignaciÃ³n realizada.", "AtenciÃ³n"); 
	     return;
	 
	 }*/
	
    }

    private void btnDelete_actionPerformed(ActionEvent e) {
    
	boolean update = false;  

	if (idTaskSelecter == - 1) {
	   advisor.messageBox("Debe selecionar un elemento antes de continuar.", "AtenciÃ³n"); 
	   return;
	}    
	
	DefaultMutableTreeNode node = (DefaultMutableTreeNode) jtTask.getLastSelectedPathComponent();
	if (node.isLeaf()) { //es un proyecto sin tareas o es una tarea
	     if (LibSQL.getBoolean("task.assignedtask", String.valueOf(idTaskSelecter)) ) {
	     
		 advisor.messageBox("No se puede eliminar. Componente asignado.", "AtenciÃ³n"); 
	
	     
	     } else {
		
		 if (Advisor.question("PrecauciÃ³n", "Desea eliminar el componente selecionado?")) {
		      
		     update = LibSQL.getBoolean("task.deletetask", "'" + idTaskSelecter +"'");
		     drawTree();
		     idTaskSelecter = -1;
		     
		 
		 } else {
	 
		     advisor.messageBox("Operación cancelada", "AtenciÃ³n"); 
		     
	         }  

	    }
	}
	else {
	    
	 advisor.messageBox("No se puede eliminar un proyecto con tareas existentes", "AtenciÃ³n"); 
	} 
	 
			
	/*
	if (LibSQL.getBoolean("task.assignedtask", String.valueOf(idTaskSelecter)) ) {
	
	    advisor.messageBox("No se puede eliminar. Tarea asignada.", "AtenciÃ³n"); 
	    return;
	
	}
	
	if (Advisor.question("PrecauciÃ³n", "Desea eliminar el componente selecionado?")) {
	     
	    update = LibSQL.getBoolean("task.deletetask", "'" + idTaskSelecter +"'");
	    drawTree();
	    idTaskSelecter = -1;
	    
	
	}*/	
    }

    private void btnAddMore_actionPerformed(ActionEvent e) {

	if (idTaskSelecter == - 1) {
	   advisor.messageBox("Debe selecionar un elemento antes de continuar.", "AtenciÃ³n"); 
	   return;
	}
	
	AddWorkJDialog addWorkBasicDialog = new AddWorkJDialog(idTaskSelecter, taskClass);
	
	addWorkBasicDialog.setModal(true);
	addWorkBasicDialog.setVisible(true);
	
	drawTree();   
	idTaskSelecter = -1;


    }

    private void btnClose_actionPerformed(ActionEvent e) {
	dispose();
    }

    private void btnEdit_actionPerformed(ActionEvent e) {
	
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

}
