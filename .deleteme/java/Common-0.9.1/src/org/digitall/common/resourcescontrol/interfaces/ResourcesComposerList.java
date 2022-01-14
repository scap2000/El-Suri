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
 * ResourcesComposerList.java
 *
 * */
package org.digitall.common.resourcescontrol.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.JPanel;

import org.digitall.common.resourcescontrol.interfaces.ResourcesComposerMgmt;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.common.resourcescontrol.classes.ResourceComponents;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.common.components.combos.JCombo;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.common.resourcescontrol.interfaces.SkillList;
import org.digitall.lib.sql.LibSQL;

public class ResourcesComposerList extends BasicPrimitivePanel {

    private BasicPanel jpSearchResources = new BasicPanel();
    private CBInput cbComponentResources = new CBInput(0, "Resources");
    private TFInput tfFindComponentResource = new TFInput(DataTypes.STRING, "FindResource", false);
    private AssignButton btnAssignComponentResource = new AssignButton(true);
    private BasicPanel jpSearchSkill = new BasicPanel();
    private CBInput cbSkill = new CBInput(0, "Skill");
    private TFInput tfFindSkill = new TFInput(DataTypes.STRING, "FindSkill", false);
    private AssignButton btnAssignSkill = new AssignButton(true);
    private FindButton btnFindSkill = new FindButton();
    private int[] sizeColumnList = {345, 72, 75, 91};
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Componentes", dataRow);
    private Vector headerList = new Vector();
    private Resource resource;
    private Resource componentResource;
    private ResourceComponents selectedResourceComponents = new ResourceComponents();
    private ResourcesComposerMgmt resourcesComposerMgmt;
    private BasicPanel jpNorth = new BasicPanel();
    private GridLayout gridLayout1 = new GridLayout();

    public ResourcesComposerList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(615, 445));
	jpSearchResources.setBorder(BorderPanel.getBorderPanel("Recurso Material"));
	cbComponentResources.setBounds(new Rectangle(145, 25, 395, 35));
	tfFindComponentResource.setBounds(new Rectangle(20, 25, 115, 35));
	btnAssignComponentResource.setBounds(new Rectangle(550, 35, 40, 25));
	btnAssignComponentResource.addActionListener(new ActionListener() {

					 public void actionPerformed(ActionEvent e) {
					     btnAssignResource_actionPerformed(e);
					 }

				     }
	);
	jpSearchResources.setBounds(new Rectangle(675, 35, 40, 25));
	jpSearchResources.setLayout(null);
	jpSearchResources.add(tfFindComponentResource, null);
	jpSearchResources.add(cbComponentResources, null);
	jpSearchResources.add(btnAssignComponentResource, null);
	jpSearchSkill.setBorder(BorderPanel.getBorderPanel("Habilidad"));
	cbSkill.setBounds(new Rectangle(145, 25, 350, 35));
	tfFindSkill.setBounds(new Rectangle(20, 25, 115, 35));
	btnAssignSkill.setBounds(new Rectangle(550, 35, 40, 25));
	btnAssignSkill.addActionListener(new ActionListener() {

				       public void actionPerformed(ActionEvent e) {
					   btnAssignSkill_actionPerformed(e);
				       }

				   }
	);
	btnFindSkill.setBounds(new Rectangle(505, 35, 40, 25));
	btnFindSkill.addActionListener(new ActionListener() {

				     public void actionPerformed(ActionEvent e) {
					 btnFindSkill_actionPerformed(e);
				     }

				 }
	);
	jpSearchSkill.setLayout(null);
	jpSearchSkill.add(btnFindSkill, null);
	jpSearchSkill.add(tfFindSkill, null);
	jpSearchSkill.add(cbSkill, null);
	jpSearchSkill.add(btnAssignSkill, null);
	listPanel.setBounds(new Rectangle(5, 165, 605, 275));
	jpNorth.setBounds(new Rectangle(10, 0, 645, 160));
	jpNorth.setLayout(gridLayout1);
	jpNorth.setMinimumSize(new Dimension(0, 140));
	jpNorth.setPreferredSize(new Dimension(0, 140));
	gridLayout1.setRows(2);
	tfFindComponentResource.setKeyListener(new KeyAdapter()
	{
	   public void keyTyped(KeyEvent e)
	   {
	      if((e.getKeyChar() == KeyEvent.VK_ENTER))
	      {
		loadComboComponentResource();
	      }
	   }
	});
	tfFindSkill.setKeyListener(new KeyAdapter()
	{
	   public void keyTyped(KeyEvent e)
	   {
	      if((e.getKeyChar() == KeyEvent.VK_ENTER))
	      {
		loadComboSkill();
	      }
	   }
	});
	jpNorth.add(jpSearchResources, null);
	jpNorth.add(jpSearchSkill, null);
	this.add(jpNorth, BorderLayout.NORTH);
	this.add(listPanel, null);
	cbComponentResources.autoSize();
	cbSkill.autoSize();
	btnAssignComponentResource.setToolTipText("Asignar recurso material");
	btnAssignSkill.setToolTipText("Asignar habilidad");
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setClosable(true);
	getParentInternalFrame().setInfo("Para componer combine Recursos Materiales y Habilidades");
    }
    
    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("Recurso");
	headerList.addElement("Cantidad");
	headerList.addElement("Valoración");
	headerList.addElement("Tipo Recurso");
	headerList.addElement("*");
	
	listPanel.getTable().addMouseListener(new MouseAdapter(){
	    public void mouseClicked(MouseEvent me) {
	        loadObject();
		if (me.getButton() == MouseEvent.BUTTON1 && me.getClickCount() == 2) {
		    
		} else if (me.getButton() == MouseEvent.BUTTON3 && me.getClickCount() == 2) {
		    int index = listPanel.getTable().rowAtPoint(me.getPoint());
		    listPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
		    
		}
	    }
	});
	
	listPanel.setParams("resourcescontrol.getAllComponentsForResource", "-1", headerList);
    }
			    
    public void refresh() {
	String params = ""+ resource.getIdResource();
	listPanel.refresh(params);
    }
    
    private void loadObject(){
	
    }
    
    private void loadComboComponentResource(){
	JCombo combo = new JCombo();
	String param = resource.getIdResource() +",'" + tfFindComponentResource.getString() + "'";
	combo.loadJCombo(LibSQL.exFunction("resourcescontrol.getAllResourcesForComponent", param));
	ItemListener itemListener = new ItemListener() {
		    public void itemStateChanged (ItemEvent evt) {
			if (evt.getStateChange() == ItemEvent.SELECTED) {
			  //loadData(Integer.parseInt(cbResources.getSelectedValue().toString()));
			}
		    }
		};
	cbComponentResources.setCombo(combo);        
	cbComponentResources.setItemListener(itemListener);
	cbComponentResources.updateUI();         
    }

    private void loadComboSkill(){
	JCombo combo = new JCombo();
	String param = "'" + tfFindSkill.getString() + "'";
	combo.loadJCombo(LibSQL.exFunction("tabs.getAllSkillForPersons", param));
	ItemListener itemListener = new ItemListener() {
		    public void itemStateChanged (ItemEvent evt) {
			if (evt.getStateChange() == ItemEvent.SELECTED) {
			  //loadData(Integer.parseInt(cbResources.getSelectedValue().toString()));
			}
		    }
		};
	cbSkill.setCombo(combo);        
	cbSkill.setItemListener(itemListener);
	cbSkill.updateUI();         
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    private void btnAssignResource_actionPerformed(ActionEvent e) {
	componentResource = new Resource();
	componentResource.setIdResource(Integer.parseInt(""+ cbComponentResources.getSelectedValue()));
	componentResource.setName(""+ cbComponentResources.getSelectedItem());
	selectedResourceComponents.setComponentResource(componentResource);
	selectedResourceComponents.setIdSkill(-1);
	
	resourcesComposerMgmt =  new ResourcesComposerMgmt();
	resourcesComposerMgmt.setResourceComponents(selectedResourceComponents);
	resourcesComposerMgmt.setParentList(this);
	
	ExtendedInternalFrame resourcesComposerMgmtContainer = new ExtendedInternalFrame("Asignar Recurso Material");
	resourcesComposerMgmtContainer.setCentralPanel(resourcesComposerMgmt);
	resourcesComposerMgmtContainer.show();
    }

    private void btnAssignSkill_actionPerformed(ActionEvent e) {
	componentResource = new Resource();
	componentResource.setIdResource(-1);
	selectedResourceComponents.setComponentResource(componentResource);
	selectedResourceComponents.setIdSkill(Integer.parseInt(""+ cbSkill.getSelectedValue()));
	selectedResourceComponents.setSkillName(""+ cbSkill.getSelectedItem());
	
	resourcesComposerMgmt =  new ResourcesComposerMgmt();
	resourcesComposerMgmt.setResourceComponents(selectedResourceComponents);
	resourcesComposerMgmt.setParentList(this);
	
	
	ExtendedInternalFrame resourcesComposerMgmtContainer = new ExtendedInternalFrame("Asignar Recurso Humano");
	resourcesComposerMgmtContainer.setCentralPanel(resourcesComposerMgmt);
	resourcesComposerMgmtContainer.show();
    }

    private void btnFindComponentResource_actionPerformed(ActionEvent e) {

    }

    private void btnFindSkill_actionPerformed(ActionEvent e) {
	SkillList skill = new SkillList();
	ExtendedInternalFrame skillContainer = new ExtendedInternalFrame("Habilidades");
	skillContainer.setCentralPanel(skill);
	skillContainer.show();
	loadComboSkill();
    }

    private void disableSkillPanel(){
	if (resource.getSkillToProvider().getIdSkill() != 3){
	    tfFindSkill.setEnabled(false);
	    cbSkill.setEnabled(false);
	    btnFindSkill.setEnabled(false);
	    btnAssignSkill.setEnabled(false);
	} else {
	    tfFindSkill.setEnabled(true);
	    cbSkill.setEnabled(true);
	    btnFindSkill.setEnabled(true);
	    btnAssignSkill.setEnabled(true);
	}
    }
    
    public void setResource(Resource resource) {
	this.resource = resource;
	selectedResourceComponents.setResource(resource);
	setHeaderList();
	refresh();
	disableSkillPanel();
    }

}
