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
 * RecursosMgmt.java
 *
 * */
package org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.apps.resourcescontrol_091.classes.Resources;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicTabbedPane;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.SaveDataButton;

public class RecursosMgmt extends BasicPrimitivePanel{
    private BasicPanel bpCentro = new BasicPanel();
    private SaveDataButton btnSave = new SaveDataButton();    
    private CloseButton btnClose = new CloseButton();   
    private BasicTabbedPane jTabbedPane1 = new BasicTabbedPane();
    private BorderLayout borderLayout1 = new BorderLayout();
    private RecursosPanel recursosPanel = new RecursosPanel();
    private RecursosDistinguiblesPanel recursosDistinguiblesPanel = new RecursosDistinguiblesPanel();
    private Resources resources = null;
    private RecursosList parentRecursosList = null;

    public RecursosMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public RecursosMgmt(RecursosList recursosList) {
	try {
	    parentRecursosList = recursosList;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(580, 600));
	this.setPreferredSize(new Dimension(580, 600));
	bpCentro.setLayout(borderLayout1);
	this.add(bpCentro, BorderLayout.CENTER);
	jTabbedPane1.addTab("Tipos recursos", recursosPanel);
	jTabbedPane1.addTab("Recursos distinguibles", recursosDistinguiblesPanel);
	//jTabbedPane1.addTab("Historial asignación de recursos", panelDatosFamily);
	bpCentro.add(jTabbedPane1, BorderLayout.CENTER);
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
	this.addButton(btnClose);
	this.addButton(btnSave);	
    }
    
    public void setResource(Resources _resources) {
	resources = _resources;
	loadData();
    }
    
    private void loadData() {
	recursosPanel.setResource(resources);;
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }
    
    private void btnSave_actionPerformed(ActionEvent e) {
	if (recursosPanel.saveData()) {	  
	    parentRecursosList.refresh();
	    getParentInternalFrame().close();
	}else{
	    Advisor.messageBox("Ocurrio un error al grabar los datos", "Aviso");
	}
    }
}
