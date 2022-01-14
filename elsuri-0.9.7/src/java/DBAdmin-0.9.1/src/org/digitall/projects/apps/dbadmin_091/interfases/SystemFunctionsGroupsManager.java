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
 * SystemFunctionsGroupsManager.java
 *
 * */
package org.digitall.projects.apps.dbadmin_091.interfases;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ButtonGroup;

import org.digitall.common.components.combos.JCombo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.ComponentsManager;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.buttons.AddGroupButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.sql.LibSQL;
import org.digitall.projects.apps.dbadmin_091.DBAdminMain;
import org.digitall.projects.apps.dbadmin_091.classes.DBControl;
import org.digitall.projects.apps.dbadmin_091.classes.Funciones;
import org.digitall.projects.apps.dbadmin_091.classes.errClasses.ErrDBAdminMain;
import org.digitall.projects.apps.dbadmin_091.interfases.FuncionesMgmt;
import org.digitall.projects.apps.dbadmin_091.interfases.SearchPanel;

public class SystemFunctionsGroupsManager extends BasicPanel {

    private AddGroupButton btnNewFunction = new AddGroupButton();
    private ModifyButton btnModifyFunction = new ModifyButton();
    private DeleteButton btnDeleteFunction = new DeleteButton();
    private SaveDataButton btnSaveGroupByFunction = new SaveDataButton();
    
    private BasicPanel panelGrupo = new BasicPanel();
    
    private ButtonGroup grupoCheck = new ButtonGroup(); 
    private BasicRadioButton rbtnSinGrupos = new BasicRadioButton();
    private BasicRadioButton rbtnConGrupos = new BasicRadioButton();
    
    private BasicLabel lblGrupos = new BasicLabel();
    
    private JCombo cbGrupos = new JCombo();
    
    private DBAdminMain manager;
    private SearchPanel listPanel = new SearchPanel();
    private Funciones funcion = new Funciones();
    private ErrDBAdminMain errorMessage = new ErrDBAdminMain();
    private DBAdminPanel dbAdminPanel;

    public SystemFunctionsGroupsManager(DBAdminMain _manager) {
	//manager = _manager;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    

    public SystemFunctionsGroupsManager(DBAdminPanel _manager) {
        dbAdminPanel = _manager;
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(660, 472));
	panelGrupo.add(lblGrupos, null);
	panelGrupo.add(cbGrupos, null);
	this.add(panelGrupo, null);
        this.add(rbtnConGrupos, null);
        this.add(listPanel, null);
        this.add(rbtnSinGrupos, null);
        this.add(btnModifyFunction, null);
        this.add(btnNewFunction, null);
        add(btnSaveGroupByFunction, null);
        this.add(btnDeleteFunction, null);
        lblGrupos.setBounds(new Rectangle(20, 20, 75, 15));
	btnNewFunction.setBounds(new Rectangle(285, 400, 95, 75));
	btnNewFunction.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnNuevaFuncion_actionPerformed(e);
		}
	    }
	);
	btnNewFunction.setToolTipText("Agregar funcionalidad");
	btnNewFunction.setText("Agregar \nfuncionalidad");
	btnModifyFunction.setBounds(new Rectangle(385, 400, 95, 75));
	btnModifyFunction.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnModifyFunction_actionPerformed(e);
		}
	    }
	);
	btnModifyFunction.setToolTipText("Modificar datos de funcionalidad");
	btnModifyFunction.setText("Modificar\nfuncionalidad");
	rbtnSinGrupos.setText("Sin Grupo");
	rbtnSinGrupos.setBounds(new Rectangle(560, 325, 90, 20));
	rbtnSinGrupos.setToolTipText("null");
	rbtnSinGrupos.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    rbtnSinGrupos_actionPerformed(e);
		}
	    }
	);
	btnDeleteFunction.setBounds(new Rectangle(485, 400, 95, 75));
	btnDeleteFunction.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnDeleteFunction_actionPerformed(e);
		}
	    }
	);
	btnDeleteFunction.setToolTipText("Eliminar funcionalidad");
	btnDeleteFunction.setText("Eliminar\nfuncionalidad");
	listPanel.setBounds(new Rectangle(5, 0, 580, 268));
	rbtnConGrupos.setText("Con Grupo");
	rbtnConGrupos.setBounds(new Rectangle(465, 325, 95, 20));
	rbtnConGrupos.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    rbtnConGrupos_actionPerformed(e);
		}
	    }
	);
	cbGrupos.setBounds(new Rectangle(15, 25, 210, 20));
	panelGrupo.setBounds(new Rectangle(5, 345, 650, 60));
	panelGrupo.setBorder(BorderPanel.getBorderPanel("Grupos"));
	panelGrupo.setLayout(null);
	listPanel.setBounds(new Rectangle(5, 0, 655, 315));
	btnSaveGroupByFunction.setBounds(new Rectangle(575, 400, 85, 75));
	btnSaveGroupByFunction.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
					    saveGroupByFunction_actionPerformed(e);
					}

				    }
	);
	btnSaveGroupByFunction.setToolTipText("Grabar");
	btnSaveGroupByFunction.setText("Guardar\ncambios");
	grupoCheck.add(rbtnConGrupos);
	grupoCheck.add(rbtnSinGrupos);
	rbtnSinGrupos.setSelected(true);
    }

    public void boot() {
	loadComboGrupos();
	listPanel.setConGrupo(mostrarConGrupo());
	listPanel.load();
	listPanel.setCombo(cbGrupos);
    }
    
    public void setEnabledTab(boolean _enabled){
	cbGrupos.setEnabled(_enabled);
	listPanel.setEnabled(_enabled);
	btnModifyFunction.setEnabled(_enabled);
	btnDeleteFunction.setEnabled(_enabled);
	btnNewFunction.setEnabled(_enabled);
	btnSaveGroupByFunction.setEnabled(_enabled);
    }
    
    private void loadComboGrupos(){
	String query = "";
	String filter = "";
	//if(DBControl.existTable("admin","funciones")){
	    if(rbtnSinGrupos.isSelected()){
		filter = "NOT";
	    }
	    query = "SELECT distinct grosysid as gid, groname as name, 0 FROM pg_group WHERE pg_group.groname " + filter + " IN (SELECT admin.funciones.grupo FROM admin.funciones WHERE funciones.estado <> '*') order by groname";
	    cbGrupos.loadJCombo(query);    
	//}
    }
    
    private void btnNuevaFuncion_actionPerformed(ActionEvent e) {
	FuncionesMgmt nuevaFuncion = new FuncionesMgmt(true);
	ComponentsManager.centerWindow(nuevaFuncion);
	nuevaFuncion.setModal(true);
	nuevaFuncion.setVisible(true);
	listPanel.loadComboModulos();
	listPanel.refresh();
	loadComboGrupos();
    }
    
    private void btnModifyFunction_actionPerformed(ActionEvent e) {
	Vector row = listPanel.getSelectedRow();
	if(control(0)){
	    int idFuncion = Integer.parseInt(row.elementAt(0).toString());
	    FuncionesMgmt newFunctionPanel = new FuncionesMgmt(false);
	    if (newFunctionPanel.loadData(idFuncion)) {
		ComponentsManager.centerWindow(newFunctionPanel);
		newFunctionPanel.setModal(true);
		newFunctionPanel.setVisible(true);
		listPanel.refresh();
	    }    
	}
	else{
	    errorMessage.showMessage();
	}
    }
    
    private void saveGroupByFunction_actionPerformed(ActionEvent e) {
	if(control(1)){
	    saveData();
	    loadComboGrupos();
	    listPanel.refresh();
	    listPanel.requestFocus();
	}
	else{
	    errorMessage.showMessage();
	}
    }
    
    private void btnDeleteFunction_actionPerformed(ActionEvent e) {
	if(control(0)){
	    if(Advisor.question("Eliminar funcionalidad","¿Desea eliminar la funcionalidad seleccionada?")){
		desvincularUsuarios();    
		listPanel.refresh();
	    }
	}
	else{
	    errorMessage.showMessage();
	}
    }
    
    private void rbtnSinGrupos_actionPerformed(ActionEvent e) {
	listPanel.setConGrupo(false);
	btnSaveGroupByFunction.setEnabled(true);
	listPanel.refresh();
	loadComboGrupos();
	listPanel.setCombo(cbGrupos);
	cbGrupos.setEnabled(true);
    }
    
    private void rbtnConGrupos_actionPerformed(ActionEvent e) {
	listPanel.setConGrupo(true);
	btnSaveGroupByFunction.setEnabled(false);
	listPanel.refresh();
	//loadComboGrupos();
	listPanel.setCombo(cbGrupos);
	cbGrupos.setEnabled(false);
    }

    private void saveData(){
	Vector rowSelected = listPanel.getSelectedRow();
	funcion.setIdFuncion(Integer.parseInt(rowSelected.elementAt(0).toString()));
	funcion.retrieveData();
	funcion.setGrupo(cbGrupos.getSelectedItem().toString());
	funcion.saveData();
    }
    
    private void desvincularUsuarios(){
	Vector rowSelected = listPanel.getSelectedRow();
	if(rowSelected.size()>0){
	    funcion.setIdFuncion(Integer.parseInt(rowSelected.elementAt(0).toString()));
	    funcion.retrieveData();
	    String consultaSelect = "SELECT usename as name, usesysid as uid, usesuper, groname, usesysid=any(pg_group.grolist) as belongs FROM pg_user, pg_group  WHERE groname = '" + funcion.getGrupo() + "' order by usename";
	    String consultaRevoke = "";
	    ResultSet result = LibSQL.exQuery(consultaSelect);
	    try {
	        while(result.next()){
	            if(result.getBoolean("belongs")){
	                consultaRevoke += "REVOKE " + funcion.getGrupo() + " FROM " + result.getString("name") + ";\n";
	            }
	        }
	        if (LibSQL.exActualizar('a', consultaRevoke)) {
	                
	        } else {
	            Advisor.messageBox("Error al intentar asignar los privilegios", "Error");
	        }
	    } catch (SQLException e) {
	        // TODO
	    }
	    funcion.setEstado("*");
	    funcion.saveData();    
	    loadComboGrupos();
	}
	else{
	    Advisor.messageBox("Debe tildar una funcionalidad para eliminar", "Mensaje");
	}
    }
    
    private boolean control(int _opcion){
	boolean retorno = true;
	errorMessage = new ErrDBAdminMain();
	switch (_opcion) {
	    case 0: {
		    Vector row = listPanel.getSelectedRow();
		    if(row.size()==0){
		        errorMessage.setError(errorMessage.FILANOSELECCIONADA);
			retorno = false;
		    }
		}
		break;
	    case 1: {
		    Vector rowSelected = listPanel.getSelectedRow();
		    if(rowSelected.size()<1){
		        errorMessage.setError(errorMessage.FILANOSELECCIONADA);
		        retorno = false;
		    }
		    else{
		        if(cbGrupos.getSelectedIndex()==-1){
		            errorMessage.setError(errorMessage.GRUPONOSELECCIONADO);
		            retorno = false;
		        }	
		    }
		}
	}
	return retorno;
    }
    
    private boolean mostrarConGrupo(){
	boolean retorno = false;
	if(rbtnConGrupos.isSelected()){
	    retorno = true;
	}
	return retorno;
    }
    
    private String isFilter() {
	String retorno= "";
	if(rbtnSinGrupos.isSelected()){
	    retorno = "todas";
	}
	return retorno;
    }
}


/***
 * 
 * SELECT admin.test2();

FETCH ALL IN "<unnamed portal 1>"
*/

 /*private int control(){
     int error = 0;
     Vector rowSelected = listPanel.getSelectedRow();
     if(rowSelected.size()==0){
	 error = 1;
     }
     if(cbGrupos.getSelectedIndex()==-1){
	 error = 2;
     }
     return error;
 }*/
 
 /*private void showMessage(int _error){
     switch(_error){
	 case 1:
	     Advisor.messageBox("Debe tildar una función", "Mensaje");
	 break;
	 case 2:
	     Advisor.messageBox("Debe seleccionar un grupo", "Mensaje");
	 break;
     }
 }*/
 
  /*private void btnModifyFunction_actionPerformed(ActionEvent e) {
      Vector row = listPanel.getSelectedRow();
      if(row.size()>0){
	  int idFuncion = Integer.parseInt(row.elementAt(0).toString());
	  FuncionesMgmt newFunctionPanel = new FuncionesMgmt(false);
	  if (newFunctionPanel.loadData(idFuncion)) {
	      ComponentsManager.centerWindow(newFunctionPanel);
	      newFunctionPanel.setModal(true);
	      newFunctionPanel.setVisible(true);
	      listPanel.refresh();
	  }    
      }
      else{
	  Advisor.messageBox("Debe tildar una función para modificar", "Mensaje");
      }
  }*/
  
  
  /*private void saveGroupByFunction_actionPerformed(ActionEvent e) {
      int error = control(); 
      if(error ==0){
	  saveData();
	  loadComboGrupos();
	  listPanel.refresh();
      }
      else{
	  showMessage(error);
      }
  }*/
  
   /*private void btnDeleteFunction_actionPerformed(ActionEvent e) {
       int error = control(); 
       if(error ==0){
	   if(Advisor.question("Eliminar función","¿Desea eliminar la función seleccionada?")){
	       desvincularUsuarios();    
	       listPanel.refresh();
	   }
       }
       else{
	   showMessage(error);
       }
   }*/
   
   
    /*SELECT org.getpersonnamebyusername(usename::character varying) as name, usename, usesysid as uid, usesuper, groname, usesysid=any(pg_group.grolist) as belongs FROM pg_user, pg_group  WHERE groname = 'giya' order by usename*/
    
    