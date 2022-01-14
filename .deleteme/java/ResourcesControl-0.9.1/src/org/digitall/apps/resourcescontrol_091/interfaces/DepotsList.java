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
 * DepotsList.java
 *
 * */
package org.digitall.apps.resourcescontrol_091.interfaces;

import java.awt.Dimension;
import java.awt.Rectangle;

import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class DepotsList extends BasicContainerPanel {

    private BasicPanel jpPurchaseOrders = new BasicPanel();
    private CBInput cbDepots = new CBInput(0, "Depots", false);
    private TFInput tfNumber = new TFInput(DataTypes.STRING, "Number", false);
    private TFInput tfStartDate = new TFInput(DataTypes.DATE, "StartDate", false);
    private TFInput tfEndDate = new TFInput(DataTypes.DATE, "EndDate", false);
    private FindButton btnSearch = new FindButton();

    public DepotsList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout( null );
	this.setSize(new Dimension(609, 495));
	jpPurchaseOrders.setBounds(new Rectangle(20, 25, 575, 125));
	jpPurchaseOrders.setLayout(null);
	jpPurchaseOrders.setBorder(BorderPanel.getBorderPanel("Buscar Orden de Compra"));
	cbDepots.setBounds(new Rectangle(15, 25, 235, 35));
	tfNumber.setBounds(new Rectangle(20, 70, 100, 35));
	tfNumber.setSize(new Dimension(100, 35));
	tfStartDate.setBounds(new Rectangle(135, 70, 85, 35));
	tfStartDate.setSize(new Dimension(85, 35));
	tfEndDate.setBounds(new Rectangle(245, 70, 85, 35));
	tfEndDate.setSize(new Dimension(85, 35));
	btnSearch.setText("Buscar");
	btnSearch.setBounds(new Rectangle(435, 85, 95, 25));
	btnSearch.setSize(new Dimension(95, 25));
	cbDepots.autoSize();
	jpPurchaseOrders.add(btnSearch, null);
	jpPurchaseOrders.add(tfEndDate, null);
	jpPurchaseOrders.add(tfStartDate, null);
	jpPurchaseOrders.add(tfNumber, null);
	jpPurchaseOrders.add(cbDepots, null);
	this.add(jpPurchaseOrders, null);
    }

}
