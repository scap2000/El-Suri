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
 * CCToolBar.java
 *
 * */
package org.digitall.common.cashflow.interfaces.costscentre;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;

public class CCToolBar extends BasicPrimitivePanel {
    private CostsCentre costscentre;
    private TFInput tfAssignedAmount = new TFInput(DataTypes.DOUBLE,"InitialAmount",false);
    private TFInput tfSpentAmount = new TFInput(DataTypes.DOUBLE,"SpentAmount",false);
    private TFInput tfAvailableAmount = new TFInput(DataTypes.DOUBLE,"AvailableAmount",false);
     
    private TFInput tfSpentAmountP = new TFInput(DataTypes.DOUBLE,"%",false);
    private TFInput tfModifiedAmount = new TFInput(DataTypes.DOUBLE,"ModifiedAmount",false);
    

    public CCToolBar() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(607, 48));
	this.setLayout(null);
	tfAssignedAmount.setBounds(new Rectangle(10, 5, 115, 35));
	tfAssignedAmount.setSize(new Dimension(115, 35));
	tfSpentAmount.setBounds(new Rectangle(155, 5, 115, 35));
	tfAvailableAmount.setBounds(new Rectangle(480, 5, 115, 35));
	tfSpentAmountP.setBounds(new Rectangle(275, 5, 60, 35));
	tfModifiedAmount.setBounds(new Rectangle(360, 5, 115, 35));
	this.add(tfModifiedAmount, null);
	this.add(tfSpentAmountP, null);
	this.add(tfAvailableAmount, null);
	this.add(tfSpentAmount, null);
	this.add(tfAssignedAmount, null);
	
	tfSpentAmount.setEnabled(false);
	tfSpentAmountP.setEnabled(false);
	tfAssignedAmount.setEnabled(false);
	tfAvailableAmount.setEnabled(false);
	tfModifiedAmount.setEnabled(false);
	
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setClosable(true);
    }
    
    public void loadData(){
	if (costscentre != null){
	    tfAssignedAmount.setValue((costscentre.getInitialAmount()));
	    tfAssignedAmount.setDisabledTextColor(Color.BLUE.darker().darker().darker());
	    
	    tfSpentAmountP.setValue((costscentre.getSpentAmountP()));
	    tfSpentAmount.setValue((costscentre.getSpentAmount()));
	    tfSpentAmount.setDisabledTextColor(Color.RED.darker().darker().darker());
	    
	    tfModifiedAmount.setValue((costscentre.getModifiedAmount()));
	    tfAvailableAmount.setValue((costscentre.getAvailableAmount()));
	    if (costscentre.getAvailableAmount() > 0){
		tfAvailableAmount.setDisabledTextColor(new Color(30,170,30).darker().darker().darker());
	    } else {
		tfAvailableAmount.setDisabledTextColor(Color.RED.darker().darker().darker());
	    }
	}
    }

    public void setCostscentre(CostsCentre costscentre) {
	this.costscentre = costscentre;
	loadData();
    }
}
