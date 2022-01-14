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
 * ReportByUser.java
 *
 * */
package org.digitall.apps.projecttask.interfaces;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.digitall.apps.projecttask.classes.Person;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.lib.components.grid.PanelGrid;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class ReportByUser extends BasicContainerPanel {
    private final int FRAME = 1;
    private final int INTERNALFRAME = 2;
    private final int DIALOG = 3;
    private int parentType = -1;
    private Component parent;

    
    private CBInput cbUsers = new CBInput(CachedCombo.USERS, "Users", false);
    
       
    private int idUserSelecter = - 1;
    private BasicPanel jpUser = new BasicPanel();
    private TFInput tfName = new TFInput(DataTypes.STRING,"Employee",false);
    
    private int[] sizeColumnList = {174,80,80,66,111,60};
    // private int[] sizeColumnList = {175,80,80,100,80};

    private PanelGrid listPanel = new PanelGrid(30, sizeColumnList, "Tareas desarrollada del día", false);
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private ListSelectionModel listSelectionModel;
    private int idRowSelected = -1;
    private Person person = new Person();
    private ReportDetailJDialog reportDetailBasicDialog;
    private int idTask = -1;
    private int idTaskByUser = -1;

    public ReportByUser() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public ReportByUser(BasicInternalFrame _parent) {
	try {
	    parent = _parent;
	    parentType = INTERNALFRAME;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public ReportByUser(BasicDialog _parent) {
	try {
	    parent = _parent;
	    parentType = DIALOG;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public ReportByUser(JFrame _parent) {
	try {
	    parent = _parent;
	    parentType = FRAME;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout( null );
	this.setSize(new Dimension(655, 375));
	jpUser.setBounds(new Rectangle(20, 30, 605, 65));
	jpUser.setLayout(null);
	tfName.setBounds(new Rectangle(290, 20, 265, 35));
	tfName.setSize(new Dimension(265, 35));
	listPanel.setBounds(new Rectangle(20, 100, 610, 260));
	listPanel.setSize(new Dimension(610, 200));
	cbUsers.setBounds(new Rectangle(10, 20, 190, 40));
	//cbUser.setSize(new Dimension(190, 40));
	cbUsers.autoSize();
	
	cbUsers.getCombo().addItemListener(new ItemListener() {
		    public void itemStateChanged(ItemEvent evt) {

			if (evt.getStateChange() == ItemEvent.SELECTED) {
			    person.loadDataUser(Integer.parseInt(cbUsers.getSelectedValue().toString()));
			    tfName.setValue(person.getLastName() + ", " + person.getName());
			    loadList();
			    //cargar los datos de la persona
			    //tfName.setText(cbUsers.g .toString());
			    //System.out.println("Id User: "+ cbUsers.getSelectedValue().toString());
			    //loadData(Integer.parseInt(cbUsers.getSelectedValue().toString())); 
						
			}
		    }

		});
	jpUser.setBorder(BorderPanel.getBorderPanel("Seleccione Usuario",Color.BLUE,Color.BLACK));
	jpUser.setSize(new Dimension(610, 65));
	jpUser.add(cbUsers, null);
	jpUser.add(tfName, null);
	this.add(jpUser, null);
	this.add(listPanel, null);
	listPanel.autoSize();
	setHeaderList();
	loadList();
	person.loadDataUser(Integer.parseInt(cbUsers.getSelectedValue().toString()));
	tfName.setValue(person.getLastName() + ", " + person.getName());
	
    }
    
    private void setHeaderList() {         
     /*
	    headerList.removeAllElements();  
	    headerList.addElement("*");
	    headerList.addElement("*");
	    headerList.addElement("Nombre");
	    headerList.addElement("Fecha");
	    headerList.addElement("Hora Inicio");
	    headerList.addElement("Hora Fin");
	    headerList.addElement("Horas Trabajadas");
	    headerList.addElement("*");
*/				     
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
						       //Debe Mostrar el detalle                                 idTask,idTaskByUser
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
    
    public void loadList() {
	StringBuffer params = new StringBuffer();
      
	params.append(Integer.parseInt(cbUsers.getSelectedValue().toString()));
	listPanel.setTable(null, "task.gettaskbyuser", params.toString(), headerList);
	
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
