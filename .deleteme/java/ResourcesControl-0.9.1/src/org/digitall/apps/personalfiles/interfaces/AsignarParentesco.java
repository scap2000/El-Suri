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
 * AsignarParentesco.java
 *
 * */
package org.digitall.apps.personalfiles.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.personalfiles.classes.Relatives;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.sql.LibSQL;

public class AsignarParentesco extends BasicPrimitivePanel{

    private BasicPanel jPanel1 = new BasicPanel();
    private BorderLayout borderLayout1 = new BorderLayout();
    private CBInput cbKinships = new CBInput();
    private SaveDataButton btnSave = new SaveDataButton();    
    private CloseButton btnClose = new CloseButton();
    private Relatives relative = null;
    private SelectRelatives selectRelatives = null;

    public AsignarParentesco() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout(borderLayout1);
	this.setSize(new Dimension(370, 113));	
	jPanel1.setLayout(null);
	jPanel1.setBorder(BorderPanel.getBorderPanel("Asignar parentesco"));
	cbKinships.setBounds(new Rectangle(15, 25, 175, 35));	
	btnSave.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnSave_actionPerformed(e);
			       }

			   }
	);
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	jPanel1.add(cbKinships, null);
	this.add(jPanel1, BorderLayout.CENTER);
	addButton(btnClose);       
	addButton(btnSave);
	cbKinships.autoSize();
	loadCombos();
    }
    
    public void setRelatives(Relatives _relative){
	this.relative = _relative;
    }
    
    public void setParent(SelectRelatives _selectRelatives){
	this.selectRelatives = _selectRelatives;
    }
    
    private void loadCombos() {
	cbKinships.loadJCombo(LibSQL.exFunction("personalfiles.getAllKinships",""));   
	cbKinships.setSelectedID("1");
    }

    private void btnSave_actionPerformed(ActionEvent e) {
	//Asiganamos el parentesco al familiar
	relative.setIdKinships(Integer.parseInt(cbKinships.getSelectedValue().toString()));	
	if (saveData()) {           
	    selectRelatives.refreshDatos();	    
	    getParentInternalFrame().close();
	}
    }
    
    public boolean saveData() {
	boolean resul = false;
	if (relative.saveData() > 0){
	    resul =  true;
	} else {
	    Advisor.messageBox("Ocurrio un error al grabar los datos!", "Error");
	}	
	return resul;
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();	
    }

}
