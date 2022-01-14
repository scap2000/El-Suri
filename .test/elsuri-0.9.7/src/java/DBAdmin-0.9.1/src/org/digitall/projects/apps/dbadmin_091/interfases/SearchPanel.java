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
 * SearchPanel.java
 *
 * */
package org.digitall.projects.apps.dbadmin_091.interfases;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.components.combos.JCombo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.projects.apps.dbadmin_091.classes.DBControl;

public class SearchPanel extends BasicPanel {

    private AddButton btnClear = new AddButton();
    private FindButton btnFind = new FindButton();
    
    private int[] sizeColumnList = { 183, 167, 122, 126 };
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(50000, sizeColumnList, "Funcionalidades del Sistema", dataRow);
    
    private JCombo cbModulos = new JCombo();
    private BasicLabel lblModulos = new BasicLabel();
    
    private JCombo combo;
    
    private BasicPanel panelData = new BasicPanel();
    
    private TFInput tfName = new TFInput(DataTypes.STRING, "Nombre", false);
    
    private boolean addAction = true;
    private boolean conGrupo = true;

    public SearchPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(660, 318));
	this.setLayout(null);
	listPanel.setBounds(new Rectangle(0, 65, 650, 250));
	panelData.setBounds(new Rectangle(0, 0, 650, 65));
	panelData.setLayout(null);
	tfName.setBounds(new Rectangle(20, 20, 210, 35));
	tfName.setSize(new Dimension(210, 35));
	tfName.getTextField().addKeyListener(new KeyAdapter() {
						  public void keyReleased(KeyEvent key) {
						      if (key.getKeyCode() == KeyEvent.VK_ENTER) {
							  refresh();
						      }
						  }

					      }
	);
	btnFind.setBounds(new Rectangle(475, 30, 40, 25));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);
	cbModulos.setBounds(new Rectangle(260, 35, 195, 20));
	lblModulos.setText("Módulos");
	lblModulos.setBounds(new Rectangle(260, 20, 145, 15));
	btnClear.setBounds(new Rectangle(515, 30, 40, 25));
	btnClear.setToolTipText("Limpiar campo");
	btnClear.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnClear_actionPerformed(e);
		}
	    }
	);
	panelData.add(btnClear, null);
	panelData.add(lblModulos, null);
	panelData.add(cbModulos, null);
	panelData.add(btnFind, null);
	panelData.add(tfName, null);
	this.add(panelData, BorderLayout.NORTH);
	this.add(listPanel, BorderLayout.CENTER);
	panelData.setBorder(BorderPanel.getBorderPanel("Búsqueda"));
	panelData.setMinimumSize(new Dimension(1, 70));
	panelData.setPreferredSize(new Dimension(1, 70));
	//boot();
	cbModulos.addItemListener(new ItemListener() {
	                                public void itemStateChanged(ItemEvent e) {
	                                    if (e.getStateChange() == ItemEvent.SELECTED) {
	                                        if (cbModulos.getSelectedIndex() > -1) {
	                                            refresh();
	                                        }
	                                    }
	                                }
	                             }
	 );
        cbModulos.setGeneralItem(true);//linea agregada
	setHeaderList();
    }
    
    /**2010-03-09(moraless)*/
    public void boot(){
	//if(DBControl.existFunction("admin.getallmodulos","true")){
            cbModulos.setGeneralItem(true);//linea agregada
	    cbModulos.loadJCombo("admin.getallmodulos","false");
	    listPanel.refresh("");
	//}
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("Funcionalidad");
	headerList.addElement("Descripción");
	headerList.addElement("Grupo");
	headerList.addElement("Módulo");
	listPanel.getTable().addMouseListener(new MouseAdapter() {
					   public void mouseClicked(MouseEvent e) {
					       if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
					           int filaSeleccionada = listPanel.getTable().rowAtPoint(e.getPoint());
					           listPanel.selectAllItems(false);
					           listPanel.getTable().setValueAt(true,filaSeleccionada,0);
					           //combo.setSelectedItem(dataRow.elementAt(3));
					       }
					   }

				       }
	);
	String params = "'',"+cbModulos.getSelectedValue()+","+conGrupo+"";
	//System.out.println("ingreso a header");
	listPanel.setParams("admin.getallfunctionsbyfilter", params, headerList);
    }
    
    public void load(){
	if(DBControl.existFunction("admin","getallfunctionsbyfilter","character varying, integer, boolean, integer, integer")){
	    loadComboModulos();
	    String params = "'',"+cbModulos.getSelectedValue()+","+conGrupo;
	    listPanel.refresh(params);    
	}else{
	    //Advisor.messageBox("No existe la función admin.getallfunctionsbyfilter(character varying, integer, boolean, integer, integer)", "Error");
	}
    }

    public void refresh() {
	String params = "";
	if(cbModulos.getSelectedItem().equals("Todos")){
	    params += "'"+tfName.getString()+"',-1";
	}
	else{
	    params += "'"+tfName.getString()+"',"+cbModulos.getSelectedValue();
	}
	params += ","+conGrupo+"";
	listPanel.refresh(params);
    }
    
    /**2010-03-09 (moraless)*/
    public void loadComboModulos(){
	//if(DBControl.existFunction("admin.getallmodulos","true")){
	    cbModulos.loadJCombo("admin.getallmodulos","false");
	//}
    }

    private void clearData() {
	tfName.setValue("");
	addAction = true;
    }

    private void btnNew_actionPerformed(ActionEvent e) {
	clearData();
    }

    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }
    
    private void btnClear_actionPerformed(ActionEvent e) {
	clearData();
    }
    
    /*private void search(){
	String idModulo = "";
	if(!cbModulos.getSelectedItem().equals("Todos")){
	    idModulo = cbModulos.getSelectedValue().toString();
	}
	String params = ""+tfName.getString()+","+idModulo;
	listPanel.setParams("admin.getallfunctionsbyfilter", params, headerList);
    }*/

    public void setConGrupo(boolean conGrupo) {
	this.conGrupo = conGrupo;
    }

    public boolean isConGrupo() {
	return conGrupo;
    }
    
    public Vector getSelectedRow(){
	return dataRow;
    }

    public void setTfName(TFInput tfName) {
	this.tfName = tfName;
    }

    public TFInput getTfName() {
	return tfName;
    }

    public void setCombo(JCombo combo) {
	this.combo = combo;
    }

    public JCombo getCombo() {
	return combo;
    }
}
