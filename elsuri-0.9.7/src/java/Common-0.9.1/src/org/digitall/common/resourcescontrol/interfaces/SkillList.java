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
 * SkillList.java
 *
 * */
package org.digitall.common.resourcescontrol.interfaces;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.resourcescontrol.classes.Skills;
import org.digitall.common.resourcescontrol.interfaces.SkillMgmt;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;

public class SkillList extends BasicPrimitivePanel {

    private TFInput tfName = new TFInput(DataTypes.STRING, "FindSkill", false);
    private BasicCheckBox chkCompany = new BasicCheckBox();
    private BasicCheckBox chkPerson = new BasicCheckBox();
    private FindButton btnFind = new FindButton();
    private int[] sizeColumnList = { 300, 68, 84, 81 };
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Habilidades", dataRow){
        public void finishLoading() {
            habilitarBotones(false);
        }
    };
    private BasicPanel panelData = new BasicPanel();
    private AddButton btnNew = new AddButton();
    private ModifyButton btnModify = new ModifyButton();
    private DeleteButton btnDelete = new DeleteButton();
    private BasicCheckBox chkProvider = new BasicCheckBox();
    private Skills skill;
    private SkillMgmt skillMgmt;
    private BasicPanel content = new BasicPanel();

    public SkillList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(605, 485));
	this.setPreferredSize(new Dimension(605, 485));
	tfName.setBounds(new Rectangle(5, 10, 185, 35));
	chkCompany.setText("Compañía");
	chkCompany.setBounds(new Rectangle(210, 20, 105, 25));
	chkPerson.setText("Persona");
	chkPerson.setBounds(new Rectangle(325, 20, 105, 25));
	btnFind.setBounds(new Rectangle(555, 20, 30, 25));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);
	btnNew.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnNew_actionPerformed(e);
			      }

			  }
	);
	btnModify.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnModify_actionPerformed(e);
				 }

			     }
	);
        btnDelete.addActionListener(new ActionListener() {

                              public void actionPerformed(ActionEvent e) {
                                  btnDelete_actionPerformed(e);
                              }

                          }
        );
	listPanel.setBounds(new Rectangle(5, 60, 595, 370));
	panelData.setBounds(new Rectangle(5, 0, 595, 55));
	panelData.setLayout(null);
	chkProvider.setText("Proveedor");
	chkProvider.setBounds(new Rectangle(430, 20, 105, 25));
	content.setLayout(null);
	panelData.add(chkProvider, null);
	panelData.add(tfName, null);
	panelData.add(chkCompany, null);
	panelData.add(chkPerson, null);
	panelData.add(btnFind, null);
	content.add(panelData, null);
	content.add(listPanel, null);
	this.add(content, null);
        this.addButton(btnDelete);
	this.addButton(btnModify);
	this.addButton(btnNew);
	tfName.getTextField().addKeyListener(new KeyAdapter() {

					  public void keyReleased(KeyEvent e) {
					      if (e.getKeyCode() == KeyEvent.VK_ENTER)
						  refresh();
					  }

				      }
	);
	setHeaderList();
	btnModify.setEnabled(false);
        btnDelete.setEnabled(false);
	btnModify.setToolTipText("Modificar la Habilidad seleccionada");
        btnDelete.setToolTipText("Eliminar la Habilidad seleccionada");
	btnNew.setToolTipText("Agregar nueva habilidad");
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Name"));
        headerList.addElement(Environment.lang.getProperty("ToCompany"));
	headerList.addElement(Environment.lang.getProperty("ToPerson"));
	headerList.addElement(Environment.lang.getProperty("ToProvider"));
	headerList.addElement("*");
	listPanel.setParams("tabs.getallSkills", "'', 'false', 'false', 'false'", headerList);
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent e) {
					       loadObject();
					       if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						   loadSkillMgmt();
					       }
					   }

				       }
	);
    }

    public void refresh() {
	String params = "'" + tfName.getString() + "','" + chkPerson.isSelected() + "','" + chkProvider.isSelected() + "','" + chkCompany.isSelected() + "'";
	listPanel.refresh(params);
	btnModify.setEnabled(false);
	btnModify.setToolTipText("Modificar la Habilidad seleccionada");
    }

    private void loadObject() {
	if (!dataRow.isEmpty()) {
	    skill = new Skills(Integer.parseInt("" + dataRow.elementAt(0)));
	    skill.setName("" + dataRow.elementAt(1));
	    skill.setToCompany((dataRow.elementAt(2).equals("SI") ? true : false));
	    skill.setToPerson((dataRow.elementAt(3).equals("SI") ? true : false));
	    skill.setToProvider((dataRow.elementAt(4).equals("SI") ? true : false));
	    habilitarBotones(true);
	    btnModify.setToolTipText("<html><center><u>Modificar Habilidad</u><br>"+skill.getName()+"</center></html>");
	    btnDelete.setToolTipText("<html><center><u>Borrar Habilidad</u><br>"+skill.getName()+"</center></html>");
	} else {
	    habilitarBotones(false);
	    btnModify.setToolTipText("Modificar la Habilidad seleccionada");
	    btnDelete.setToolTipText("Borrar la Habilidad seleccionada");
	}
    }

    private void loadSkillMgmt() {
	skillMgmt = new SkillMgmt();
	skillMgmt.setSkillList(this);
	skillMgmt.setSkill(skill);
	ExtendedInternalFrame skillMgmtContainer = new ExtendedInternalFrame("Modificar Habilidad");
	skillMgmtContainer.setCentralPanel(skillMgmt);
	skillMgmtContainer.show();
    }

    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void btnNew_actionPerformed(ActionEvent e) {
	skillMgmt = new SkillMgmt();
	skillMgmt.setSkillList(this);
	ExtendedInternalFrame skillMgmtContainer = new ExtendedInternalFrame("Nueva habilidad");
	skillMgmtContainer.setCentralPanel(skillMgmt);
	skillMgmtContainer.show();
    }

    private void btnModify_actionPerformed(ActionEvent e) {
	loadSkillMgmt();
    }
    
    private void btnDelete_actionPerformed(ActionEvent e) {
        eliminarHabilidadSeleccionada();
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Puede agregar o modificar una Habilidad");
    }
    
    private void habilitarBotones(boolean _enabled) {
        btnModify.setEnabled(_enabled);
        btnDelete.setEnabled(_enabled);
    }

    private void eliminarHabilidadSeleccionada() {
        if (Advisor.question("Aviso", "¿Está seguro de borrar la Habilidad \""+ dataRow.elementAt(1) +"\"?")) {
            if (skill.deleteSkill()) {
                refresh();
            } else {
                Advisor.messageBox("No se pudo borrar la habilidad seleccionada", "Error");
            }
        }
    }
}
