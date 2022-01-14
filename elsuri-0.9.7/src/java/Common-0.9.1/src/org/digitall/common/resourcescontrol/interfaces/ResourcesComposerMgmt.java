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
 * ResourcesComposerMgmt.java
 *
 * */
package org.digitall.common.resourcescontrol.interfaces;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.common.resourcescontrol.classes.ResourceComponents;
import org.digitall.common.resourcescontrol.interfaces.ResourcesComposerList;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class ResourcesComposerMgmt extends BasicPrimitivePanel {

    private TFInput tfResourceName = new TFInput(DataTypes.STRING,"Resource",false);
    private TFInput tfQuantity = new TFInput(DataTypes.DOUBLE,"Quantity",false);
    private BasicPanel jPanel1 = new BasicPanel("Asignar");
     
    private CloseButton btnClose = new CloseButton();
    private AcceptButton btnAccept = new AcceptButton();
    private ResourcesComposerList parentList;
    private ResourceComponents resourceComponents;
    private TFInput tfValue = new TFInput(DataTypes.INTEGER,"Value",false);

    public ResourcesComposerMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout( null );
	this.setSize(new Dimension(485, 80));
	tfResourceName.setBounds(new Rectangle(15, 25, 280, 35));
	tfQuantity.setBounds(new Rectangle(315, 25, 65, 35));
	jPanel1.setBounds(new Rectangle(5, 5, 475, 70));
	jPanel1.setLayout(null);
	btnClose.setBounds(new Rectangle(440, 90, 40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	btnAccept.setBounds(new Rectangle(390, 90, 40, 25));
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	tfValue.setBounds(new Rectangle(395, 25, 65, 35));
	this.add(btnAccept, null);
	this.add(btnClose, null);
	 
	this.add(jPanel1, null);
	jPanel1.add(tfValue, null);
	jPanel1.add(tfResourceName, null);
	jPanel1.add(tfQuantity, null);
	tfResourceName.setEnabled(false);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().getGeneralButtons().addButton(btnAccept);
	getParentInternalFrame().getGeneralButtons().addButton(btnClose);
	getParentInternalFrame().setClosable(true);
    }
    
    private void loadData(){
	if (resourceComponents.getIdSkill() == -1){
	    tfResourceName.setValue(resourceComponents.getComponentResource().getName());    
	} else {
	    tfResourceName.setValue(resourceComponents.getSkillName());
	}
	
	tfQuantity.setValue(resourceComponents.getQuantity());
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    public void setParentList(ResourcesComposerList parentList) {
	this.parentList = parentList;
    }

    public void setResourceComponents(ResourceComponents resourceComponents) {
	this.resourceComponents = resourceComponents;
	loadData();
    }

    public boolean saveData(){
	resourceComponents.setQuantity(tfQuantity.getDouble());
	resourceComponents.setValue(Integer.parseInt("0"+ tfValue.getString()));
	if (resourceComponents.saveData()>=0){
	    parentList.refresh();
	    getParentInternalFrame().close();
	    return true;
	} else {
	    return false;
	}
    }
    
    private void btnAccept_actionPerformed(ActionEvent e) {
	saveData();
    }

}
