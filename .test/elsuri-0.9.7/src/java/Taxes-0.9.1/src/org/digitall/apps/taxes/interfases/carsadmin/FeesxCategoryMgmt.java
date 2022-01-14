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
 * FeesxCategoryMgmt.java
 *
 * */
package org.digitall.apps.taxes.interfases.carsadmin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import org.digitall.apps.taxes.classes.FeesxCategory;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class FeesxCategoryMgmt extends BasicPrimitivePanel {

    private BasicPanel dataPanel = new BasicPanel();
    private CloseButton btnClose = new CloseButton();
    private SaveDataButton btnAccept = new SaveDataButton();
    
    private TFInput tfYear = new TFInput(DataTypes.STRING,"FileYear",true);
    private TFInput tfAnnualFee = new TFInput(DataTypes.MONEY,"AnnualFee",true);
    private CBInput cbCategory = new CBInput(CachedCombo.CATEGORYTYPES,"Category",false);
    //private Car car;
    private FeesxCategory feesxCategory;
    private FeesxCategoryList parentList;
    private CarsMgmt_old parentMgmt;
    
    private boolean isList = true;
    
    int error = 0;
    private static final int ok = 0;
    private static final int year = 1;
    private static final int annualFee = 2;
    
    public FeesxCategoryMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout( null );
	this.setSize(new Dimension(324, 113));
	this.setPreferredSize(new Dimension(565, 300));
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
	tfYear.setBounds(new Rectangle(120, 25, 85, 35));
	cbCategory.setBounds(new Rectangle(15, 25, 75, 35));
	dataPanel.setBounds(new Rectangle(5, 0, 555, 220));
	dataPanel.setLayout(null);
	dataPanel.add(tfYear, null);
	dataPanel.add(cbCategory, null);
	dataPanel.add(tfAnnualFee, null);
	this.add(dataPanel, BorderLayout.CENTER);
	this.addButton(btnClose);
	this.addButton(btnAccept);
	cbCategory.autoSize();
	tfAnnualFee.setBounds(new Rectangle(215, 25, 100, 35));
	cbCategory.addItemListener(new ItemListener() {
	      public void itemStateChanged(ItemEvent evt) {
		  if (evt.getStateChange() == ItemEvent.SELECTED) {
		      
		  }
	      }
	});
	dataPanel.setTitle("Agregar/Modificar Cuota");
    }
    
    
    private void loadData(){
	 if (feesxCategory.getAnio() != -1) {
	    tfYear.setValue("" + feesxCategory.getAnio());
	    cbCategory.setSelectedID(feesxCategory.getIdtipocategoria());
	    tfAnnualFee.setValue(feesxCategory.getAnual());
	    tfYear.setEnabled(false);
	    cbCategory.setEnabled(false);
	 } else {
	     tfYear.setEnabled(true);
	     cbCategory.setEnabled(true);
	 }
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	if (!isList)  {
	    parentMgmt.setBtn(true);
	}
	getParentInternalFrame().close();
    }

    public boolean saveData(){
	if (control() == ok)  {
	    feesxCategory.setAnio(Integer.parseInt(tfYear.getValue().toString()));
	    feesxCategory.setIdtipocategoria(Integer.parseInt(cbCategory.getSelectedItem().toString()));
	    feesxCategory.setAnual(tfAnnualFee.getAmount());
	    if (feesxCategory.saveData() >= 0) {
	        return true;
	    } else {
	        return false;
	    }	
	} else {
	    showMessage();
	    return false;
	}
    }
    
    private void btnAccept_actionPerformed(ActionEvent e) {
	if (saveData()){
	    if (isList)  {
		parentList.refresh();	    
	    } else {
	        parentMgmt.recalcCuota();
	    }
	    getParentInternalFrame().close();
	}
    }
    
    public void setParentList(FeesxCategoryList _parentList) {
	parentList = _parentList;
	isList = true;
    }
    
    public void setParentMgmt(CarsMgmt_old _parentMgmt) {
	parentMgmt = _parentMgmt;
	isList = false;
	
    }
    
    private int control() {
	error = ok;
	if (tfYear.getString().equals(""))  {
	    error = year;
	} else if (tfAnnualFee.getAmount() == 0) {
	    error = annualFee;
	}
	return error;
    }

    private void showMessage() {
	switch (error)  {
	    case 1: 
		    Advisor.messageBox("Debe ingresar el año", "Mensaje");
		break;
	    case 2: 
		    Advisor.messageBox("El Monto Anual debe ser mayor que cero", "Mensaje");
		break;
	}
    }

    public void setFeesxCategory(FeesxCategory _feesxCategory) {
	feesxCategory = _feesxCategory;
	loadData();
    }
    
    

}
