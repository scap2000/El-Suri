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
 * ABMDependenciasPanel.java
 *
 * */
package org.digitall.apps.personalfiles.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;

import java.util.Vector;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.apps.personalfiles.classes.Dependencia;
import org.digitall.apps.personalfiles.classes.VectorDependencia;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.EditButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;

public class ABMDependenciasPanel extends BasicPanel{

    private TFInput tfDependencia = new TFInput(DataTypes.STRING,"Dependency",false);
    private CBInput cbTiposDependenciaSubDependencia = new CBInput(0, "DependencyType",false);
    private TFInput tfSubdependencia = new TFInput(DataTypes.STRING,"Name",true);
    private EditButton bEdit = new EditButton();
    private DeleteButton bDelete = new DeleteButton();
    private Dependencia dependencia = null;
    private AcceptButton bAccept = new AcceptButton();
    private DependenciaTree parent = null;
    private VectorDependencia vectorDependencia = null;
    private CBInput cbTiposDependenciaDependencia = new CBInput(0, "DependencyType",false);
    private Vector vecTiposDependenciaNombre = new Vector();
    private Vector vecTiposDependenciaId = new Vector();
    private BasicPanel bpAgregarSubDependencia = new BasicPanel();
    private BasicPanel bpDependenciaSeleccionada = new BasicPanel(); 
    private BorderLayout borderLayout1 = new BorderLayout();
    private BasicLabel blNroPersonasDep = new BasicLabel();
    private BasicLabel blNroPersonasSubdep = new BasicLabel();
    private BasicLabel blNroPersonasDepDat = new BasicLabel();
    private BasicLabel blNroPersonasSubdepDat = new BasicLabel();
    private String paramsIn = "";
    private BasicCheckBox bcbMostrarNombreTipoDep = new BasicCheckBox();

    public ABMDependenciasPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public ABMDependenciasPanel(DependenciaTree _dependenciaTree) {
	try {
	    parent = _dependenciaTree;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setPreferredSize(new Dimension(790, 174));
	this.setSize(new Dimension(790, 174));
	this.setLayout(borderLayout1);
	this.setBorder(BorderPanel.getBorderPanel("Dependencia seleccionada"));	
	bpDependenciaSeleccionada.setLayout(null);
	blNroPersonasDep.setText("Nº de pers en dep.:");
	blNroPersonasDep.setBounds(new Rectangle(35, 45, 120, 15));
	blNroPersonasSubdep.setText("Nº de pers. en subdep.:");
	blNroPersonasSubdep.setBounds(new Rectangle(205, 45, 145, 15));	
	blNroPersonasDepDat.setBounds(new Rectangle(155, 45, 40, 15));
	blNroPersonasDepDat.setSize(new Dimension(40, 15));
	blNroPersonasSubdepDat.setBounds(new Rectangle(350, 45, 40, 15));
	bcbMostrarNombreTipoDep.setText("Mostrar nombre tip. dep.");
	bcbMostrarNombreTipoDep.setBounds(new Rectangle(30, 55, 175, 20));
	bcbMostrarNombreTipoDep.setSelected(true);
	bpAgregarSubDependencia.setLayout(null);
	bpAgregarSubDependencia.setPreferredSize(new Dimension(780, 85));
	bpAgregarSubDependencia.setBorder(BorderPanel.getBorderPanel("Agregar Subdependencia"));
	bpAgregarSubDependencia.setSize(new Dimension(780, 80));	
	tfDependencia.setBounds(new Rectangle(400, 0, 355, 35));
	tfDependencia.setPreferredSize(new Dimension(355, 35));
	cbTiposDependenciaSubDependencia.setBounds(new Rectangle(35, 15, 355, 35));
	cbTiposDependenciaSubDependencia.setPreferredSize(new Dimension(355, 35));
	cbTiposDependenciaSubDependencia.setSize(new Dimension(355, 35));	
	tfSubdependencia.setBounds(new Rectangle(400, 15, 368, 35));
	tfSubdependencia.setPreferredSize(new Dimension(335, 35));
	tfSubdependencia.setSize(new Dimension(368, 35));	
	bEdit.setBounds(new Rectangle(700, 40, 30, 20));
	bEdit.setPreferredSize(new Dimension(30, 20));
	bEdit.setSize(new Dimension(30, 20));
	bEdit.setToolTipText("Editar Dependencia");
	bEdit.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    bEdit_actionPerformed(e);
				}

			    }
	);
	bDelete.setBounds(new Rectangle(730, 40, 30, 20));
	bDelete.setPreferredSize(new Dimension(30, 20));
	bDelete.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    bDelete_actionPerformed(e);
				}

			    }
	);
	bAccept.setBounds(new Rectangle(730, 55, 30, 20));
	bAccept.setSize(new Dimension(30, 20));
	bAccept.setPreferredSize(new Dimension(30, 20));
	bAccept.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    bAccept_actionPerformed(e);
				}

			    }
	);
	cbTiposDependenciaDependencia.setBounds(new Rectangle(35, 0, 355, 35));
	cbTiposDependenciaDependencia.setSize(new Dimension(355, 35));
	cbTiposDependenciaDependencia.setPreferredSize(new Dimension(355, 35));
	bpDependenciaSeleccionada.add(blNroPersonasDepDat, null);
	bpDependenciaSeleccionada.add(blNroPersonasDep, null);
	bpDependenciaSeleccionada.add(cbTiposDependenciaDependencia, null);
	bpDependenciaSeleccionada.add(bDelete, null);
	bpAgregarSubDependencia.add(bcbMostrarNombreTipoDep, null);
	bpAgregarSubDependencia.add(bAccept, null);
	bpAgregarSubDependencia.add(tfSubdependencia, null);
	bpAgregarSubDependencia.add(cbTiposDependenciaSubDependencia, null);
	bpDependenciaSeleccionada.add(bEdit, null);
	bpDependenciaSeleccionada.add(tfDependencia, null);
	bpDependenciaSeleccionada.add(blNroPersonasSubdep, null);
	bpDependenciaSeleccionada.add(blNroPersonasSubdepDat, null);
	this.add(bpDependenciaSeleccionada, BorderLayout.CENTER);
	this.add(bpAgregarSubDependencia, BorderLayout.SOUTH);
	loadCombo();
	cbTiposDependenciaSubDependencia.autoSize();
	cbTiposDependenciaDependencia.autoSize();
    }

    public void loadData(VectorDependencia _vectorDependencia) {
	vectorDependencia = _vectorDependencia;	
	dependencia = vectorDependencia.getDependencia();	
	tfDependencia.setValue(dependencia.getNombre());
	cbTiposDependenciaDependencia.setSelectedID(dependencia.getNivelJerarquico());
	loadComboTiposDependenciaSubdependencia();	
	
	int result = LibSQL.getInt("personalfiles.getNroPersonsInDependency",dependencia.getIdDep());  	
	paramsIn = "";
	getNroPersonalEnSubdependencia(vectorDependencia);	
	String params = paramsIn.substring(0, paramsIn.length()-1);		
	
	ResultSet data = LibSQL.exFunction("personalfiles.getAllNroPersonsIndependency", "'"+params+"'");
	 	
	
	try {
	     if (data.next()) {      		 
	         int cantPersonalSubDependencia = data.getInt("nroPersonal") - result;  		 
	         blNroPersonasDepDat.setText(result+"");
	         blNroPersonasSubdepDat.setText(cantPersonalSubDependencia+"");
	    }
	 } catch (Exception ex) {
	     ex.printStackTrace();
	}	
	
    }
    
    public void getNroPersonalEnSubdependencia(VectorDependencia _vectorDependencia) {
	int tamVec = _vectorDependencia.size() - 1;
	Dependencia dependencia = _vectorDependencia.getDependencia();    
	paramsIn = dependencia.getIdDep()+","+ paramsIn;
	for (int i = 0; i <= tamVec; i++) {
	    VectorDependencia vectorSubDependencia = (VectorDependencia)_vectorDependencia.get(i);	    
	    getNroPersonalEnSubdependencia(vectorSubDependencia);
	}
    }
    
    public void loadComboTiposDependenciaSubdependencia(){
	int tamVec = vecTiposDependenciaNombre.size();
	String selectElem = String.valueOf(cbTiposDependenciaDependencia.getSelectedItem());	
	cbTiposDependenciaSubDependencia.removeAllItems();			
	int i = 0;
	while (!cbTiposDependenciaDependencia.getCombo().getItemAt(i).equals(selectElem)) {		    
	    i++;
	}
	if (selectElem.equals("(1) INTENDENCIA")) {	 	    
	    for (int j = i; j < tamVec; j++){
	        cbTiposDependenciaSubDependencia.getCombo().addItem(vecTiposDependenciaNombre.get(j),vecTiposDependenciaId.get(j),0);        
	    }
	} else {
	    i++;	  
	    for (int j = i; j < tamVec; j++){
	        cbTiposDependenciaSubDependencia.getCombo().addItem(vecTiposDependenciaNombre.get(j),vecTiposDependenciaId.get(j),0);        
	    }
	}
	
    }
    
    public void loadCombo() {
	ResultSet data = LibSQL.exFunction("personalfiles.getAllTiposDependencia", "");
	try {
	    while (data.next()) {
	        vecTiposDependenciaNombre.add(data.getString("nombre"));
	        vecTiposDependenciaId.add(data.getInt("id"));
	        cbTiposDependenciaSubDependencia.getCombo().addItem(data.getString("nombre"),data.getInt("id"),0);
	        cbTiposDependenciaDependencia.getCombo().addItem(data.getString("nombre"),data.getInt("id"),0);
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	cbTiposDependenciaDependencia.setSelectedID("1");
	cbTiposDependenciaSubDependencia.setSelectedID("1");
    }

    private void bEdit_actionPerformed(ActionEvent e) {
	dependencia.setNombre(tfDependencia.getValue().toString());
	dependencia.savaData();
    }

    private void jButton3_actionPerformed(ActionEvent e) {
	Dependencia dependencia = (Dependencia) cbTiposDependenciaSubDependencia.getCombo().getSelectedValue();
	dependencia.setNombre(tfSubdependencia.getValue().toString());
	dependencia.savaData();
    }

    private void bAccept_actionPerformed(ActionEvent e) {
        agregarSubdependencia();
    }

    private void bDelete_actionPerformed(ActionEvent e) {	
	if (dependencia != null) {
	    if (Advisor.question("Borrar persona", "¿Está seguro de borrar la dependencia seleccionada?")) {
	        parent.deleteNodoHoja();
	        tfDependencia.setValue("");
	    }
	}	
    }

    private void agregarSubdependencia() {
        if (!tfSubdependencia.getString().equals("")) {
            Dependencia dependenciaHijo = new Dependencia();
            dependenciaHijo.setNombre(tfSubdependencia.getValue().toString());
            dependenciaHijo.setPadre(dependencia.getIdDep());       
            dependenciaHijo.setNivelJerarquico(Integer.parseInt(cbTiposDependenciaSubDependencia.getSelectedValue().toString()));   
            dependenciaHijo.setDependenciaPadre(dependencia);
            dependenciaHijo.setMostrarNombreTipDep(bcbMostrarNombreTipoDep.isSelected());
            //SACAMOS EL (NIVEL JERARQUICO) del texto del combo box
            String text = cbTiposDependenciaSubDependencia.getSelectedItem().toString();
            String text1 = text.substring(text.lastIndexOf(")")+1); 
            dependenciaHijo.setNombreNivelJerarquico(text1);
            if (dependenciaHijo.savaData() != -1) {
                dependencia.addDependenciaHijo(dependenciaHijo);
                VectorDependencia vectorDependenciaHijo = new VectorDependencia();
                vectorDependenciaHijo.setDependencia(dependenciaHijo);
                vectorDependencia.add(vectorDependenciaHijo);           
                parent.addNodoHoja(vectorDependenciaHijo);
                tfSubdependencia.setValue("");
            }    
        } else{
            Advisor.messageBox("El campo \"Nombre\" no debe estar vacío", "Mensaje");
        }
          
    }
}
