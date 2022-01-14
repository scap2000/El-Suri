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
 * CCSpentAmountResourcesRequestsAuthList.java
 *
 * */
package org.digitall.common.cashflow.interfaces.costscentre;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Vector;

import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.reports.BasicReport;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.environment.Environment;

public class CCSpentAmountResourcesRequestsAuthList extends BasicPrimitivePanel {

    private int sizeColumnList[] = new int[] { 93, 147, 166, 93, 276 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Pedidos de Recursos Autorizados", dataRow);
    private Vector headerList = new Vector();
    private ExpenditureAccount.CCItem ccExpenditureAccount;
    private PrintButton btnReport = new PrintButton();

    public CCSpentAmountResourcesRequestsAuthList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setSize(new Dimension(830, 355));
	setTitle("Pedidos de Recursos Autorizados");
	listPanel.setBounds(new Rectangle(5, 0, 820, 350));
	add(listPanel, null);
	btnReport.addActionListener(new ActionListener(){
	    public void actionPerformed(ActionEvent e){
		btnReport_actionPerformed(e);
	    }
	}
	);
	this.addButton(btnReport);
	setHeaderList();
	btnReport.setToolTipText("Pedidos autorizados con detalle de gastos");
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Date"));
	headerList.addElement(Environment.lang.getProperty("ResourcesRequestNumber"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Solicitor"));
	headerList.addElement(Environment.lang.getProperty("TotalAmount"));
	headerList.addElement(Environment.lang.getProperty("Observations"));
	headerList.addElement("*");
	
	listPanel.setParams("cashflow.getAllCostsCentreResourcesRequestAuth", "-1, -1", headerList);
    }

    public void refresh() {
	listPanel.refresh((new StringBuilder()).append(ccExpenditureAccount.getCostCentre().getIdCostCentre()).append(",").append(ccExpenditureAccount.getIDExpenditureAccount()).toString());
    }

    public void setCCExpenditureAccount(ExpenditureAccount.CCItem ccExpenditureAccount) {
	this.ccExpenditureAccount = ccExpenditureAccount;
	btnReport.setToolTipText("<html><center><u>Pedidos autorizados con detalle de gastos</u><br>Centro de Costos: " + ccExpenditureAccount.getCostCentre().getName() + "</center></html>");
	refresh();
    }
    public void btnReport_actionPerformed(ActionEvent e){
        if (listPanel.getTable().getRowCount() != 0)  {
            BasicReport report = new BasicReport(CCSpentAmountResourcesRequestsAuthList.class.getResource("xml/ResourceRequestAuthListByCostCentre.xml"));
            String params = ""+ ccExpenditureAccount.getCostCentre().getIdCostCentre() +","+ ccExpenditureAccount.getIDExpenditureAccount();
            report.addTableModel("cashflow.xmlGetAllCostsCentreResourcesRequestAuth", params);
            report.setProperty("costcentre",ccExpenditureAccount.getCostCentre().getName());
            report.setProperty("account",ccExpenditureAccount.getName()); 
            report.doReport();
        } else  {
            Advisor.messageBox("El Reporte no se generó\nporque no hay datos en la grilla","Aviso");
        }
        
    }
}
