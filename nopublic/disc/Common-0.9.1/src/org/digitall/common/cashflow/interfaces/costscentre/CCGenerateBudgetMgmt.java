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
 * CCGenerateBudgetMgmt.java
 *
 * */
package org.digitall.common.cashflow.interfaces.costscentre;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.sql.LibSQL;

public class CCGenerateBudgetMgmt extends BasicPrimitivePanel {

    private BasicPanel jPanel1 = new BasicPanel();
     
    private CloseButton btnClose = new CloseButton();
    private AcceptButton btnAccept = new AcceptButton();
    private TFInput tfName = new TFInput(DataTypes.STRING, "Name", false);
    private TFInput tfStartDate = new TFInput(DataTypes.DATE, "From", false);
    private TFInput tfEndDate = new TFInput(DataTypes.DATE, "To", false);
    private TFInput tfPercentage = new TFInput(DataTypes.PERCENT, "%", false);
    private TFInput tfDescription = new TFInput(DataTypes.STRING, "Description", false);
    private CBInput cbPeriod = new CBInput(CachedCombo.PERIODTYPES_LIST, "PeriodType");
    private CostsCentre costsCentre;
    private CCExpenditureAccountsTree parentTree;

    public CCGenerateBudgetMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(429, 185));
	this.setPreferredSize(new Dimension(435, 185));
	jPanel1.setBounds(new Rectangle(5, 0, 425, 145));
	jPanel1.setLayout(null);
	btnClose.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					btnClose_actionPerformed(e);
				    }

				}
	);
	btnAccept.addActionListener(new ActionListener() {

				     public void actionPerformed(ActionEvent e) {
					 btnAccept_actionPerformed(e);
				     }

				 }
	);
	tfName.setBounds(new Rectangle(15, 10, 325, 35));
	tfStartDate.setBounds(new Rectangle(220, 55, 90, 35));
	tfEndDate.setBounds(new Rectangle(320, 55, 90, 35));
	tfPercentage.setBounds(new Rectangle(350, 10, 60, 35));
	tfDescription.setBounds(new Rectangle(15, 100, 395, 35));
	cbPeriod.setBounds(new Rectangle(15, 55, 195, 35));
	this.addButton(btnClose);
	this.addButton(btnAccept);
	this.add(jPanel1, null);
	jPanel1.add(cbPeriod, null);
	jPanel1.add(tfPercentage, null);
	jPanel1.add(tfEndDate, null);
	jPanel1.add(tfStartDate, null);
	jPanel1.add(tfName, null);
	jPanel1.add(tfDescription, null);
	cbPeriod.autoSize();
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Se generará una Nueva Partida cuando presione \"Aceptar\"");
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    private void btnAccept_actionPerformed(ActionEvent e) {
	/*if (tfName.getString().length()> 0 && tfPercentage.getAmount()>0 && tfStartDate.getString().length()>0 && tfEndDate.getString().length()>0 && tfDescription.getString().length()>0) {
	    double percentage = tfPercentage.getAmount() / 100.0;
	    String params = costsCentre.getIdCostCentre() +",0"+ percentage +",'"+ tfName.getString() +"','"+ 
			    Proced.setFormatDate(tfStartDate.getString(), false) +"','"+ Proced.setFormatDate(tfEndDate.getString(), false) +"',0"+ 
			    cbPeriod.getSelectedValue()  +",'"+ tfDescription.getString()  +"'";
	    if (LibSQL.getInt("cashflow.generateBudgetByCostCentre", params) == 0){
		Advisor.messageBox("Nueva Partida generada correctamente","Nueva Partida");
		parentTree.drawTree();
	    } else {
		Advisor.messageBox("No es necesario generar una nueva Partida","Saldo Permitido");
	    }
	    getParentInternalFrame().close();
	}
	else {
	  Advisor.messageBox("Debe completar todos los campos", "Aviso");  
	}*/
	Advisor.messageBox("En construcción", "Aviso");  
    }

    public void setCostsCentre(CostsCentre costsCentre) {
	this.costsCentre = costsCentre;
    }

    public void setParentTree(CCExpenditureAccountsTree parentTree) {
	this.parentTree = parentTree;
    }

}
