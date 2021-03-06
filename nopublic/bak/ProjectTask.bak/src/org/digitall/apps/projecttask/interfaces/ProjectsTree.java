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

	//Escucha cuando cambia la selecci????n.
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
	     advisor.messageBox("Debe selecionar una tarea para asignar.", "Atenci????n"); 
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
	     
	         advisor.messageBox("Fallo al asignar la tarea en la base de datos", "Atenci????n"); 
	         return;
	     
	     }	else {		
		
		advisor.messageBox("Asignacion exitosa.", "Atenci????n"); 
		return;
	     }
	    
	 }else {
	 
	     advisor.messageBox("Asignaci????n cancelada", "Atenci????n"); 
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
	 
	     advisor.messageBox("Fallo al asignar la tarea.", "Atenci????n"); 
	     return;
	 
	 } else {
	     
	     advisor.messageBox("Asignaci????n realizada.", "Atenci????n"); 
	     return;
	 
	 }*/
	
    }

    private void btnDelete_actionPerformed(ActionEvent e) {
    
	boolean update = false;  

	if (idTaskSelecter == - 1) {
	   advisor.messageBox("Debe selecionar un elemento antes de continuar.", "Atenci????n"); 
	   return;
	}    
	
	DefaultMutableTreeNode node = (DefaultMutableTreeNode) jtTask.getLastSelectedPathComponent();
	if (node.isLeaf()) { //es un proyecto sin tareas o es una tarea
	     if (LibSQL.getBoolean("task.assignedtask", String.valueOf(idTaskSelecter)) ) {
	     
		 advisor.messageBox("No se puede eliminar. Componente asignado.", "Atenci????n"); 
	
	     
	     } else {
		
		 if (Advisor.question("Precauci????n", "Desea eliminar el componente selecionado?")) {
		      
		     update = LibSQL.getBoolean("task.deletetask", "'" + idTaskSelecter +"'");
		     drawTree();
		     idTaskSelecter = -1;
		     
		 
		 } else {
	 
		     advisor.messageBox("Operaci??n cancelada", "Atenci????n"); 
		     
	         }  

	    }
	}
	else {
	    
	 advisor.messageBox("No se puede eliminar un proyecto con tareas existentes", "Atenci????n"); 
	} 
	 
			
	/*
	if (LibSQL.getBoolean("task.assignedtask", String.valueOf(idTaskSelecter)) ) {
	
	    advisor.messageBox("No se puede eliminar. Tarea asignada.", "Atenci????n"); 
	    return;
	
	}
	
	if (Advisor.question("Precauci????n", "Desea eliminar el componente selecionado?")) {
	     
	    update = LibSQL.getBoolean("task.deletetask", "'" + idTaskSelecter +"'");
	    drawTree();
	    idTaskSelecter = -1;
	    
	
	}*/	
    }

    private void btnAddMore_actionPerformed(ActionEvent e) {

	if (idTaskSelecter == - 1) {
	   advisor.messageBox("Debe selecionar un elemento antes de continuar.", "Atenci????n"); 
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
