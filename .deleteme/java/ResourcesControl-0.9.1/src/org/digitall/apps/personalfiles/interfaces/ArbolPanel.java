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
 * ArbolPanel.java
 *
 * */
package org.digitall.apps.personalfiles.interfaces;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.apps.personalfiles.classes.Dependencia;
import org.digitall.apps.personalfiles.classes.VectorDependencia;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.inputpanels.TFInput;

public class ArbolPanel extends BasicPanel{

    private BasicRadioButton rbExpandir = new BasicRadioButton();
    private BasicRadioButton rbContraer = new BasicRadioButton();
    private ButtonGroup rbGroup = new ButtonGroup();
    private TFInput tfBuscarDependencia = new TFInput();
    private FindButton bBuscar = new FindButton();
    private JTree dependenciaTree = null;
    private CBInput cbiResultadoBusqueda = new CBInput(0,"",false);
    private int idDep;
    private String nombreDep;   

    public ArbolPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setPreferredSize(new Dimension(790, 174));
	this.setSize(new Dimension(790, 174));
	this.setLayout(null);
	this.setBorder(BorderPanel.getBorderPanel("Operaciones varias"));
	rbExpandir.setText("Expandir");
	rbExpandir.setBounds(new Rectangle(25, 40, 93, 18));
	rbExpandir.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      rbExpandir_actionPerformed(e);
				  }

			      }
	);
	rbContraer.setText("Contraer");
	rbContraer.setBounds(new Rectangle(160, 40, 93, 18));
	rbContraer.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      rbContraer_actionPerformed(e);
				  }

			      }
	);
	rbGroup.add(rbExpandir);
	rbGroup.add(rbContraer);
	tfBuscarDependencia.setBounds(new Rectangle(30, 75, 180, 35));
	tfBuscarDependencia.getTextField().addKeyListener(new KeyAdapter() {

					public void keyReleased(KeyEvent e) {
					    tfBuscarDependencia_keyReleased(e);
					}

				    }
	);
	bBuscar.setBounds(new Rectangle(580, 90, 30, 20));
	bBuscar.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   bBuscar_actionPerformed(e);
			       }

			   }
	);
	cbiResultadoBusqueda.setBounds(new Rectangle(220, 75, 350, 35));
	cbiResultadoBusqueda.addItemListener(new ItemListener() {

					  public void itemStateChanged(ItemEvent e) {
					      cbiResultadoBusqueda_itemStateChanged(e);
					  }

				      }
	);
	add(cbiResultadoBusqueda, null);
	add(bBuscar, null);	
	add(tfBuscarDependencia, null);
	add(rbContraer, null);
	add(rbExpandir, null);
	cbiResultadoBusqueda.autoSize();
    }
    
   public void setArbol(JTree _arbol) {
	dependenciaTree = _arbol;
    }

    private void rbExpandir_actionPerformed(ActionEvent e) {
	DefaultMutableTreeNode node = (DefaultMutableTreeNode) dependenciaTree.getLastSelectedPathComponent();
       	expanderNodoEHijos(node);
    }    
    
    private void expanderNodoEHijos(DefaultMutableTreeNode _node){	
	for (int i = 0; i < _node.getChildCount(); i++) {
	    DefaultMutableTreeNode nodeHijo = (DefaultMutableTreeNode) _node.getChildAt(i);
	    expanderNodoEHijos(nodeHijo);
	}
	dependenciaTree.expandPath(new TreePath(_node.getPath()));
    }

    private void rbContraer_actionPerformed(ActionEvent e) {	
	//int row = dependenciaTree.getRowCount() - 1;
	//int[] rowSelectedArray = dependenciaTree.getSelectionRows();
	//int rowSelected = rowSelectedArray[0];
	//dependenciaTree.collapseRow(rowSelected);	
	
	DefaultMutableTreeNode node = (DefaultMutableTreeNode) dependenciaTree.getLastSelectedPathComponent();	
	dependenciaTree.collapsePath(new TreePath(node.getPath()));
	contraerNodoEHijos(node);
    }
    
    private void contraerNodoEHijos(DefaultMutableTreeNode _node){	
	for (int i = 0; i < _node.getChildCount(); i++) {
	    DefaultMutableTreeNode nodeHijo = (DefaultMutableTreeNode) _node.getChildAt(i);
	    contraerNodoEHijos(nodeHijo);
	}
	dependenciaTree.collapsePath(new TreePath(_node.getPath()));
    }

    private void bBuscar_actionPerformed(ActionEvent e) {
	DefaultMutableTreeNode nodoRoot = (DefaultMutableTreeNode) dependenciaTree.getModel().getRoot();
	DefaultMutableTreeNode nodoNext = nodoRoot.getNextNode();		
	idDep = -1;
	nombreDep = ""; 
	try {
	    idDep = Integer.valueOf(tfBuscarDependencia.getValue().toString());
	    buscarNodo(nodoNext, idDep);
	}
	catch (Exception ex) {
	    idDep = -1;
	    nombreDep = tfBuscarDependencia.getValue().toString();
	}
	loadCombo();	
	int idDepSelect = Integer.valueOf(cbiResultadoBusqueda.getCombo().getSelectedValue()+"");	
	buscarNodo(nodoNext, idDepSelect);	
    }
    
    private void loadCombo() {	
	String params = "'"+nombreDep+"',"+idDep;
	cbiResultadoBusqueda.removeAllItems();
	cbiResultadoBusqueda.loadJCombo("personalfiles.getAllDependencySearch", params);	
	cbiResultadoBusqueda.getCombo().setSelectedIndex(0);		
    }

    private void buscarNodo(DefaultMutableTreeNode nodo, int _idDepSelect){
	VectorDependencia vectorDependencia = (VectorDependencia)nodo.getUserObject();	
	Dependencia dependencia = vectorDependencia.getDependencia();	
	if (dependencia.getIdDep() == _idDepSelect) {
	    dependenciaTree.setSelectionPath(new TreePath(nodo.getPath()));
	    dependenciaTree.scrollPathToVisible(new TreePath(nodo.getPath()));
	}else {
	    DefaultMutableTreeNode nodoNext = nodo.getNextNode();
	    buscarNodo(nodoNext, _idDepSelect); 
	}
    }

    private void cbiResultadoBusqueda_itemStateChanged(ItemEvent e) {	
	DefaultMutableTreeNode nodoRoot = (DefaultMutableTreeNode) dependenciaTree.getModel().getRoot();
	DefaultMutableTreeNode nodoNext = nodoRoot.getNextNode();               
	int idDepSelect = Integer.valueOf(cbiResultadoBusqueda.getCombo().getSelectedValue()+"");       	
	buscarNodo(nodoNext, idDepSelect);
    }

    private void tfBuscarDependencia_keyReleased(KeyEvent e) {	
	 if (e.getKeyCode() == KeyEvent.VK_ENTER){
	    DefaultMutableTreeNode nodoRoot = (DefaultMutableTreeNode) dependenciaTree.getModel().getRoot();
	    DefaultMutableTreeNode nodoNext = nodoRoot.getNextNode();               
	    idDep = -1;
	    nombreDep = ""; 
	    try {
	        idDep = Integer.valueOf(tfBuscarDependencia.getValue().toString());
	        buscarNodo(nodoNext, idDep);
	    }
	    catch (Exception ex) {
	        idDep = -1;
	        nombreDep = tfBuscarDependencia.getValue().toString();
	    }
	    loadCombo();    
	    int idDepSelect = Integer.valueOf(cbiResultadoBusqueda.getCombo().getSelectedValue()+"");       
	    buscarNodo(nodoNext, idDepSelect); 
	}
    }

}
