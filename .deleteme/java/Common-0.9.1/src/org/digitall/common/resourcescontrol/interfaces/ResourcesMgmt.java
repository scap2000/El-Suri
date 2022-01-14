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
 * ResourcesMgmt.java
 *
 * */
package org.digitall.common.resourcescontrol.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.ButtonGroup;

import org.digitall.common.cashflow.classes.EntityTypes;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.resourcescontrol.classes.Brands;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.common.resourcescontrol.classes.ResourceBrands;
import org.digitall.common.resourcescontrol.classes.Skills;
import org.digitall.common.resourcescontrol.interfaces.ResourcesList;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;

public class ResourcesMgmt extends BasicPrimitivePanel {

    private BasicPanel dataPanel = new BasicPanel();
    private BasicPanel brandsPanel = new BasicPanel();
    private BasicPanel content = new BasicPanel();

    private CBInput cbUnit = new CBInput(CachedCombo.UNITS_TABS, "Unit", true);
    private CBInput cbSkillToProvider = new CBInput(CachedCombo.SKILLPROVIDER_TABS, "ResourceTypes", true);
    private CBInput cbLifeTimeTypes = new CBInput(CachedCombo.LIFETIMETYPES, "LifeTimeTypes");
    private CBInput cbBrands = new CBInput(CachedCombo.BRANDS, "Brand", true);
    
    private TFInput tfDescription = new TFInput(DataTypes.STRING, "Description", false);
    private TFInput tfExpenditureAccount = new TFInput(DataTypes.STRING, "ExpenditureAccount", false);
    private TFInput tfLifeTime = new TFInput(DataTypes.INTEGER, "LifeTime", false);
    private TFInput tfName = new TFInput(DataTypes.STRING, "Name", false);
    private TFInput tfPrice = new TFInput(DataTypes.MONEY_EXTENDED, "Price", false);
    private TFInput tfStock = new TFInput(DataTypes.DOUBLE_EXTENDED, "Quantity", false);
    private TFInput tfMinStock = new TFInput(DataTypes.DOUBLE_EXTENDED, "MinStock", false);
    
    private int[] sizeColumnList = { 205, 85, 105, 103 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Marcas", dataRow);
    private Vector headerList = new Vector();

    private ResourcesList parentList;
    private Resource resource;
    private ResourceBrands resourceBrand;

    private AssignButton btnAssignBrand = new AssignButton(true);
    private CloseButton btnClose = new CloseButton();
    private SaveDataButton btnAccept = new SaveDataButton();
    private AddButton btnNewAssign = new AddButton();
    
    private BasicCheckBox chkDistinguishable = new BasicCheckBox();
    private BasicRadioButton rbtnPersons = new BasicRadioButton();
    private BasicRadioButton rbtnDependencies = new BasicRadioButton();
    private BasicRadioButton rbtnPersonsDependencies = new BasicRadioButton();

    public ResourcesMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout( null );
	this.setSize(new Dimension(565, 538));
	this.setPreferredSize(new Dimension(565, 510));
	btnClose.setBounds(new Rectangle(540, 535, 40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	btnAccept.setBounds(new Rectangle(490, 535, 40, 25));
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	tfDescription.setBounds(new Rectangle(15, 180, 525, 35));
	cbUnit.setBounds(new Rectangle(415, 25, 125, 35));
	cbSkillToProvider.setBounds(new Rectangle(15, 65, 290, 35));
	tfExpenditureAccount.setBounds(new Rectangle(15, 105, 525, 35));
	dataPanel.setBounds(new Rectangle(5, 0, 555, 225));
	dataPanel.setLayout(null);
	cbLifeTimeTypes.setBounds(new Rectangle(405, 65, 135, 35));
	tfLifeTime.setBounds(new Rectangle(335, 65, 60, 35));
	brandsPanel.setBounds(new Rectangle(5, 230, 555, 270));
	brandsPanel.setLayout(null);
	brandsPanel.setSize(new Dimension(555, 270));
	tfName.setBounds(new Rectangle(15, 25, 335, 35));
	listPanel.setBounds(new Rectangle(5, 65, 545, 200));
	cbBrands.setBounds(new Rectangle(15, 25, 165, 35));
	btnAssignBrand.setBounds(new Rectangle(520, 35, 30, 25));
	btnAssignBrand.addActionListener(new ActionListener() {

				      public void actionPerformed(ActionEvent e) {
					  btnAssignBrand_actionPerformed(e);
				      }

				  }
	);
	tfPrice.setBounds(new Rectangle(195, 25, 85, 35));
	tfStock.setBounds(new Rectangle(295, 25, 85, 35));
	tfMinStock.setBounds(new Rectangle(395, 25, 85, 35));
	btnNewAssign.setBounds(new Rectangle(490, 35, 30, 25));
	btnNewAssign.setSize(new Dimension(30, 25));
	btnNewAssign.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					btnNew_actionPerformed(e);
				    }

				}
	);
        dataPanel.add(rbtnPersonsDependencies, null);
        dataPanel.add(rbtnDependencies, null);
        dataPanel.add(rbtnPersons,null);
        dataPanel.add(chkDistinguishable, null);
        dataPanel.add(tfName, null);
        dataPanel.add(tfDescription, null);
        dataPanel.add(tfExpenditureAccount, null);
        dataPanel.add(cbUnit, null);
	dataPanel.add(cbSkillToProvider, null);
	dataPanel.add(tfLifeTime, null);
	dataPanel.add(cbLifeTimeTypes, null);
        brandsPanel.add(btnNewAssign, null);
        brandsPanel.add(listPanel, null);
        brandsPanel.add(cbBrands, null);
        brandsPanel.add(tfMinStock, null);
        brandsPanel.add(tfStock, null);
        brandsPanel.add(tfPrice, null);
        brandsPanel.add(btnAssignBrand, null);
        content.add(brandsPanel, null);
        content.add(dataPanel, null);
	this.add(content, BorderLayout.CENTER);
	this.addButton(btnClose);
	this.addButton(btnAccept);
	cbBrands.autoSize();
	cbLifeTimeTypes.autoSize();
	cbSkillToProvider.autoSize();
	cbUnit.autoSize();
	tfExpenditureAccount.setEnabled(false);
	btnNewAssign.setEnabled(false);
	content.setLayout(null);
        chkDistinguishable.setText("Distinguible");
        chkDistinguishable.setBounds(new Rectangle(15, 155, 105, 20));
        rbtnPersons.setText("Personas");
        rbtnPersons.setBounds(new Rectangle(140, 153, 95, 25));
        rbtnDependencies.setText("Dependencias");
        rbtnDependencies.setBounds(new Rectangle(235, 153, 120, 25));
        rbtnPersonsDependencies.setText("Personas y Dependencias");
        rbtnPersonsDependencies.setBounds(new Rectangle(355, 153, 185, 25));
        ItemListener itemListener = new ItemListener() {

		public void itemStateChanged(ItemEvent evt) {
		    if (evt.getStateChange() == ItemEvent.SELECTED) {
			disableBrandPanel();
		    }
		}

	    }
	;
	cbSkillToProvider.addItemListener(itemListener);
	setHeaderList();
	btnAccept.setToolTipText("Grabar Recurso");
	btnClose.setToolTipText("Cancelar");
	btnAssignBrand.setToolTipText("Asignar");
	btnNewAssign.setToolTipText("Limpiar campos");
        ButtonGroup rbtn = new ButtonGroup();
        rbtn.add(rbtnPersons);
        rbtn.add(rbtnDependencies);
        rbtn.add(rbtnPersonsDependencies);
        rbtnPersons.setSelected(true);
        dataPanel.setBorder(BorderPanel.getBorderPanel("Agregar/Modificar un Recurso"));
        brandsPanel.setBorder(BorderPanel.getBorderPanel("Stock del Recurso clasificado por Marcas"));
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Complete los datos y presione el botón \"Grabar Recurso\"");
    }

    private void disableBrandPanel() {
	if (cbSkillToProvider.getSelectedValue().equals("3")) {
	    cbBrands.setEnabled(false);
	    btnAssignBrand.setEnabled(false);
	    listPanel.setEnabled(false);
	} else {
	    cbBrands.setEnabled(true);
	    btnAssignBrand.setEnabled(true);
	    listPanel.setEnabled(true);
	}
    }

    private void loadData() {
	tfExpenditureAccount.setValue(resource.getExpenditureAccount().getName());
	if (resource.getIdResource() != -1) {
	    tfName.setValue(resource.getName());
	    tfDescription.setValue(resource.getDescription());
	    tfLifeTime.setValue("" + resource.getLifetime());
	    cbLifeTimeTypes.setSelectedID("" + resource.getIdLifeTimeType());
	    cbSkillToProvider.setSelectedID("" + resource.getSkillToProvider().getIdSkill());
	    cbUnit.setSelectedID("" + resource.getUnit().getIdUnit());
            chkDistinguishable.setSelected(resource.isDistinguishable());
            if (chkDistinguishable.isSelected())  {
                chkDistinguishable.setEnabled(false);
            }
            if (resource.getDestined().equals("p"))  {
                rbtnPersons.setSelected(true);
            } else if (resource.getDestined().equals("d")) {
                rbtnDependencies.setSelected(true);
            } else {
                rbtnPersonsDependencies.setSelected(true);
            }
            
	}
	//disableBrandPanel();
    }

    private void loadResourceBrandData() {
	cbBrands.setSelectedID("" + resourceBrand.getBrands().getIdBrand());
	tfPrice.setValue(resourceBrand.getPrice());
	tfStock.setValue(resourceBrand.getStock());
        tfStock.setEnabled(false);
        //tfStock.setValue(0);
	tfMinStock.setValue(resourceBrand.getMinStock());
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Brand"));
	headerList.addElement(Environment.lang.getProperty("Stock"));
	headerList.addElement(Environment.lang.getProperty("MinStock"));
	headerList.addElement(Environment.lang.getProperty("Price"));
	headerList.addElement("*");
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent e) {
					       loadObjectSelected();
					       if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
					       } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
					       }
					   }

				       }
	);
	listPanel.setParams("resourcescontrol.getAllResourceBrands", "-1", headerList);
    }

    public void refresh() {
	String params = "" + resource.getIdResource();
	listPanel.refresh(params);
    }

    private void loadObjectSelected() {
	if (!dataRow.isEmpty()) {
	    resourceBrand = new ResourceBrands(Integer.parseInt("" + dataRow.elementAt(0)));
	    resourceBrand.setResource(new Resource(EntityTypes.RESOURCE, Integer.parseInt("" + dataRow.elementAt(1))));
	    resourceBrand.setBrands(new Brands(Integer.parseInt("" + dataRow.elementAt(2))));
	    resourceBrand.setStock(Double.parseDouble("" + dataRow.elementAt(4)));
	    resourceBrand.setMinStock(Double.parseDouble("" + dataRow.elementAt(5)));
	    resourceBrand.setPrice(Double.parseDouble("" + dataRow.elementAt(6)));
	    loadResourceBrandData();
	}
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    public boolean saveData() {
	if (resource == null) {
	    resource = new Resource(EntityTypes.RESOURCE);
	}
	resource.setName(tfName.getString());
	resource.setDescription(tfDescription.getString());
	resource.setLifetime(Integer.parseInt("0" + tfLifeTime.getString()));
	resource.setIdLifeTimeType(Integer.parseInt("" + cbLifeTimeTypes.getSelectedValue()));
	resource.setSkillToProvider(new Skills(Integer.parseInt("" + cbSkillToProvider.getSelectedValue())));
	resource.setIdUnit(Integer.parseInt("" + cbUnit.getSelectedValue()));
        if (rbtnPersons.isSelected())  {
            resource.setDestined("p");
        } else if (rbtnDependencies.isSelected()) {
            resource.setDestined("d");
        } else {
            resource.setDestined("t");
        }
        resource.setDistinguishable(chkDistinguishable.isSelected());
	if (resource.saveData() >= 0) {
	    return true;
	} else {
	    return false;
	}
    }

    private void btnAccept_actionPerformed(ActionEvent e) {
	if (saveData()) {
	    parentList.refresh();
	    getParentInternalFrame().close();
	}
    }

    public void setResource(Resource resource) {
	this.resource = resource;
	btnNewAssign.setEnabled(true);
	if (resource.getIdResource() != -1) {
	    refresh();
	}
	loadData();
    }

    private void btnAssignBrand_actionPerformed(ActionEvent e) {
	if (resource.getIdResource() == -1) {
	    saveData();
	}
	if (resourceBrand == null) {
	    resourceBrand = new ResourceBrands(resource);
	}
	resourceBrand.setBrands(new Brands(Integer.parseInt("" + cbBrands.getSelectedValue())));
	resourceBrand.setPrice(tfPrice.getAmount());
	resourceBrand.setMinStock(tfMinStock.getDouble());
	/** VALIDAR USUARIO ADMINISTRADOR P/MODIFICAR STOCK DIRECTAMENTE **/
	if (tfStock.getDouble() != resourceBrand.getStock()) {
	    resourceBrand.setConsumable(true);
	    resourceBrand.consume(resourceBrand.getStock());
	    resourceBrand.setProduceable(true);
	    resourceBrand.produce(tfStock.getDouble());
	}
	/*****************************************************************/
	if (resourceBrand.saveData() >= 0) {
	    refresh();
	    resourceBrand.setIdResourceBrand(-1);
	} else {
	    Advisor.messageBox("Ya tiene asignada esta Marca", "Marca no válida");
	}
    }

    public void setParentList(ResourcesList parentList) {
	this.parentList = parentList;
    }

    private void btnNew_actionPerformed(ActionEvent e) {
	resourceBrand = new ResourceBrands(resource);
	tfMinStock.setValue(0);
	tfPrice.setValue(0);
	tfStock.setValue(0);
        tfStock.setEnabled(true);
    }

}
