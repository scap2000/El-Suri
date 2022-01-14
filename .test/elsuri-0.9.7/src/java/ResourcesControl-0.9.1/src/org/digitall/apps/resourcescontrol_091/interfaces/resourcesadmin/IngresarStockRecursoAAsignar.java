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
 * IngresarStockRecursoAAsignar.java
 *
 * */
package org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;

public class IngresarStockRecursoAAsignar extends BasicPrimitivePanel{

    private BasicPanel bpCentro = new BasicPanel();
    private TFInput tfiStockAsignado = new TFInput(DataTypes.STRING,"",true);
    private SaveDataButton btnSave = new SaveDataButton();    
    private CloseButton btnClose = new CloseButton();
    private int idPerson = -1;
    private int idDependencia = -1;
    private int idResource;
    private RecursosVariosPanel recursosVariosPanel;

    public IngresarStockRecursoAAsignar() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setPreferredSize(new Dimension(370, 113));
	this.setSize(new Dimension(370, 113));
	bpCentro.setLayout(null);
	bpCentro.setBorder(BorderPanel.getBorderPanel("Ingresar cantidad stock"));
	tfiStockAsignado.setBounds(new Rectangle(10, 25, 100, 35));
	bpCentro.add(tfiStockAsignado, null);
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	btnSave.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnSave_actionPerformed(e);
			       }

			   }
	);
	this.add(bpCentro, BorderLayout.CENTER);
	this.addButton(btnClose);
	this.addButton(btnSave);	
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	recursosVariosPanel.getGrillaPanelRecursos().selectAllItems(false);
	getParentInternalFrame().close();       
    }
    
    private void btnSave_actionPerformed(ActionEvent e) {
	//Pregunto para saber que tabla estamos por modificar
	if (idPerson != -1)  {
	    if (saveDataResourcesPerson()) {           
	        recursosVariosPanel.refresh();         
	        getParentInternalFrame().close();
	    }	
	} else {
	    if (saveDataResourcesDependency()) {           
	        recursosVariosPanel.refresh();         
	        getParentInternalFrame().close();
	    }   
	}
		
	
    }
    
    public boolean saveDataResourcesPerson() {
	boolean resul = false;
	String params =  idResource +","+ idPerson+","+tfiStockAsignado.getValue();
	int result = LibSQL.getInt("personalfiles.addResourcesPerson", params);
	if (result > 0){
	    resul =  true;
	} else {	   	    
	    Advisor.messageBox("¡Ocurrio un error al grabar los datos!", "Error");
	}       
	return resul;
    }
    
    public boolean saveDataResourcesDependency() {
	boolean resul = false;
	String params =  idResource +","+ idDependencia +","+tfiStockAsignado.getValue();
	int result = LibSQL.getInt("personalfiles.addResourcesDependency", params);
	if (result > 0){
	    resul =  true;
	} else {                    
	    Advisor.messageBox("¡Ocurrio un error al grabar los datos!", "Error");
	}       
	return resul;
    }
    
    public void setIdPerson(int _idPerson) {
	idPerson = _idPerson;
    }
    
    public void setIdResurce(int _idResource) {
	idResource = _idResource;
    }
    
    public void setIdDependencia(int _idDependencia) {
	idDependencia = _idDependencia;
    }    
    
    public void setRecursosVariosPanel(RecursosVariosPanel _recursosVariosPanel) {
	recursosVariosPanel = _recursosVariosPanel;
    }
}
