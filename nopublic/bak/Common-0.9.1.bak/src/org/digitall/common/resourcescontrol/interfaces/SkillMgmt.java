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
