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
 * SkillMgmt.java
 *
 * */
package org.digitall.common.resourcescontrol.interfaces;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.common.resourcescontrol.classes.Skills;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.common.resourcescontrol.interfaces.SkillList;
import org.digitall.lib.components.Advisor;

public class SkillMgmt extends BasicPrimitivePanel{

    private TFInput tfName = new TFInput(DataTypes.STRING, "Skill", false);
    private BasicCheckBox chkCompany = new BasicCheckBox();
    private BasicCheckBox chkPerson = new BasicCheckBox();
    private SaveDataButton btnAdd = new SaveDataButton();
    private BasicPanel panelData = new BasicPanel();
    private BasicCheckBox chkProvider = new BasicCheckBox();
    private Skills skill;
    private SkillList parentList;
    private CloseButton btnClose = new CloseButton();
    
    public SkillMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(415, 155));
	this.setPreferredSize(new Dimension(415, 145));
	tfName.setBounds(new Rectangle(20, 20, 275, 35));
	tfName.setSize(new Dimension(275, 35));
	chkCompany.setText("Compañía");
	chkCompany.setBounds(new Rectangle(15, 65, 105, 25));
	chkPerson.setText("Persona");
	chkPerson.setBounds(new Rectangle(150, 65, 95, 25));
	btnAdd.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAdd_actionPerformed(e);
			      }

			  }
	);
	panelData.setBounds(new Rectangle(5, 5, 405, 125));
	panelData.setLayout(null);
	chkProvider.setText("Proveedor");
	chkProvider.setBounds(new Rectangle(285, 65, 105, 25));
	btnClose.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnClose_actionPerformed(e);
			      }

			  }
	);
	panelData.add(chkProvider, null);
	panelData.add(tfName, null);
	panelData.add(chkCompany, null);
	panelData.add(chkPerson, null);
	this.add(panelData, null);
	this.addButton(btnClose);
	this.addButton(btnAdd);
	btnClose.setToolTipText("Cancelar");
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Complete los datos y presione el botón \"Grabar\"");
    }    
    
    private void loadData(){
	tfName.setValue(skill.getName());        
	chkPerson.setSelected(skill.isToPerson());
	chkProvider.setSelected(skill.isToProvider());
	chkCompany.setSelected(skill.isToCompany());
    }
    
    public boolean saveData() {
        if (control()) {
            if (skill == null) {
                skill = new Skills();
            }
            skill.setName(tfName.getString());
            skill.setToPerson(chkPerson.isSelected());
            skill.setToProvider(chkProvider.isSelected());
            skill.setToCompany(chkCompany.isSelected());
            
            if (skill.saveData() >= 0) {
                parentList.refresh();
                getParentInternalFrame().close();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    private void btnAdd_actionPerformed(ActionEvent e) {
	saveData();
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    public void setSkill(Skills skill) {
	this.skill = skill;
	loadData();
    }

    public void setSkillList(SkillList skillList) {
	this.parentList = skillList;
    }

    private boolean control() {
        boolean retorno = true;
        if (tfName.getString().equals("")) {
            Advisor.messageBox("El campo \"Habilidad\" no debe ser vacío","Mensaje");
            retorno = false;
        }
        return retorno;
    }
}
