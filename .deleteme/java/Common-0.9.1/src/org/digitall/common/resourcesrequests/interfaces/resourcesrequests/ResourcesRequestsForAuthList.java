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
 * ResourcesRequestsForAuthList.java
 *
 * */
package org.digitall.common.resourcesrequests.interfaces.resourcesrequests;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.common.resourcesrequests.classes.ResourcesRequest;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.environment.Environment;

public class ResourcesRequestsForAuthList  extends BasicPrimitivePanel {

    private int[] sizeColumnList = {152, 80, 87, 131, 75, 78, 177};
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Listado de Pedidos de Materiales", dataRow) {

	@Override
	public void finishLoading() {
	    parentAuthMain.setTitle("No se ha seleccionado un Pedido de Materiales");
	    parentAuthMain.getRequestsAuthList().setResourcesRequest(new ResourcesRequest(-1));
	}
    };
    private ResourcesRequest resourcesRequest;
    private ResourcesRequestsAuthMain parentAuthMain;
    private BasicPanel findPanel = new BasicPanel();
    private CBInput cbCostsCentre = new CBInput(CachedCombo.COSTSCENTRE, "CostsCentre", false);
    private CBInput cbPriority = new CBInput(CachedCombo.PRIORITYREQUEST, "Priority", false);
    private FindButton btnFind = new FindButton();
    private CBInput cbRequestStatus = new CBInput(CachedCombo.REQUESTSTATUS, "Status", false);
    private PrintButton btnReport = new PrintButton();

    public ResourcesRequestsForAuthList(ResourcesRequestsAuthMain _parentAuthMain) {
	try {
	    parentAuthMain = _parentAuthMain;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    private void jbInit() throws Exception {
	setTitle("Pedidos de Materiales realizados");
	this.setSize(new Dimension(770, 555));
	//Se ocultaron los combos RequestStatus y Priority porque no se usaban nunca
	//findPanel.add(cbRequestStatus, null);
	//findPanel.add(cbPriority, null);
	findPanel.add(btnFind, null);
	findPanel.add(cbCostsCentre, null);
	parentAuthMain.setTitle("No se ha seleccionado un Pedido de Materiales");
	findPanel.setLayout(null);
	findPanel.setMinimumSize(new Dimension(1, 100));
	findPanel.setPreferredSize(new Dimension(1, 70));
	cbCostsCentre.setBounds(new Rectangle(65, 25, 335, 35));
	cbPriority.setBounds(new Rectangle(570, 25, 85, 35));
	btnFind.setBounds(new Rectangle(405, 35, 30, 25));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);
	btnReport.addActionListener(new ActionListener(){
	    public void actionPerformed(ActionEvent e){
		btnReport_actionPerformed(e);
	    }
	}
	);
	cbRequestStatus.setBounds(new Rectangle(345, 25, 205, 35));
	setHeaderList();
	cbRequestStatus.setGeneralItem(true);
	cbPriority.setGeneralItem(true);
	cbCostsCentre.setGeneralItem(true);
	cbCostsCentre.setSelectedID("-1");
	cbPriority.setSelectedID("-1");
	cbRequestStatus.setSelectedID("2"); //2 = Requested?
	addButton(btnReport);
	add(listPanel, BorderLayout.CENTER);
	add(findPanel, BorderLayout.NORTH);
	btnReport.setToolTipText("Listado de Pedidos de Materiales");
        btnReport.setVisible(false);
        findPanel.setBorder(BorderPanel.getBorderPanel("Buscar Pedido de Materiales"));

	listPanel.getTable().addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
		    if (parentAuthMain.getCantItemsAutorizados() == 0) {
			loadSelectedObject();
			if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {

			} else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
			    if (parentAuthMain != null) {
				//parentAuthMain.getNewResourcesList().setResourcesRequest(resourcesRequest);
				parentAuthMain.getRequestsAuthList().setResourcesRequest(resourcesRequest);
				parentAuthMain.setSelectedTab(1);
			    }
			} else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON3) {
			    int index = listPanel.getTable().rowAtPoint(e.getPoint());
			    listPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
			}
		    } else {
			Advisor.messageBox("Debe confirmar la autorización del pedido Nº "+ resourcesRequest.getNumber() +"\nantes de realizar otra operación" ,"Aviso");
		    }
		}
	    }
	);
    }
    
    private void setHeaderList() {
	Vector headerList = new Vector();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("CostsCentre"));
	headerList.addElement(Environment.lang.getProperty("RequestNumber"));
	headerList.addElement(Environment.lang.getProperty("Date"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Solicitor"));
	headerList.addElement(Environment.lang.getProperty("Cost"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Status"));
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Motivo del pedido"));
	headerList.addElement("*");

	String params =  "-1,'','','',2,-1,-1";
	listPanel.setParams("resourcescontrol.getAllResourcesRequests",params,headerList);
        cbPriority.setVisible(false);
    }
    
    public void refresh() {
	//String params = cbCostsCentre.getSelectedValue() + ",'','','',"+ cbRequestStatus.getSelectedValue() +","+ cbPriority.getSelectedValue() +",-1";
	/* Se quitó el valor del combo "prioridad" */
	String params = cbCostsCentre.getSelectedValue() + ",'','','',"+ cbRequestStatus.getSelectedValue() +", -1, -1";
	listPanel.refresh(params);
    }
    
    private void loadSelectedObject() {
	if (!dataRow.isEmpty()) {
	    resourcesRequest = new ResourcesRequest();
	    resourcesRequest.setIdResourcesRequest(Integer.parseInt("" + dataRow.elementAt(0)));
	    resourcesRequest.setCostsCentre(new CostsCentre(Integer.parseInt("" + dataRow.elementAt(1))));
	    resourcesRequest.getCostsCentre().setName("" + dataRow.elementAt(2));
	    resourcesRequest.setNumber(Integer.parseInt("0"+ dataRow.elementAt(3)));
	    resourcesRequest.setDate(Proced.setFormatDate(dataRow.elementAt(4).toString(), false));
	    resourcesRequest.setIdSolicitor(Integer.parseInt("" + dataRow.elementAt(5)));
	    resourcesRequest.setEstimatedAmount(Double.parseDouble("" + dataRow.elementAt(7)));
	    resourcesRequest.setIdRequestStatus(Integer.parseInt("" + dataRow.elementAt(8)));
	    resourcesRequest.setIdPriorityRequest(Integer.parseInt("" + dataRow.elementAt(10)));
	    resourcesRequest.setDescription(dataRow.elementAt(12).toString());
	    resourcesRequest.setEstimatedAmountAuth(Double.parseDouble("" + dataRow.elementAt(13)));
	    setHeaderTitle();
	}
    }
    
    private void setHeaderTitle(){
	if (parentAuthMain != null){		
	    String title = Environment.lang.getProperty("RequestNumber") +": "+ resourcesRequest.getNumber() +" - "+ Proced.setFormatDate(resourcesRequest.getDate(), true);
	    parentAuthMain.setTitle(title);
	}
    }

    private void btnFind_actionPerformed(ActionEvent e) {
        if (parentAuthMain.getCantItemsAutorizados() > 0)  {
            Advisor.messageBox("Debe confirmar la autorización del pedido Nº "+ resourcesRequest.getNumber() +"\nantes de realizar otra operación" ,"Aviso");
        } else  {
            refresh();
        }
    }

    /** Version vieja de Reporte (no funciona) - Hay que modificarla por la nueva */
    private void btnReport_actionPerformed(ActionEvent e) {
        if (listPanel.getItemCount() > 0) {
            String params = cbCostsCentre.getSelectedValue() + ",'','','',"+ cbRequestStatus.getSelectedValue() +","+ cbPriority.getSelectedValue() +",-1";
            BasicReport report = new BasicReport(ResourcesRequestsForAuthList.class.getResource("xml/ResourcesRequestsForAuthListReport.xml"));
            report.addTableModel("resourcescontrol.xmlGetAllResourcesRequests",params); 
            report.setProperty("costcentre",cbRequestStatus.getSelectedItem().toString());
            report.setProperty("estado",cbCostsCentre.getSelectedItem().toString());
            report.setProperty("prioridad",cbPriority.getSelectedItem().toString());
            report.doReport();
        } else {
            Advisor.messageBox("No se pudo generar el Reporte porque no hay datos en la Grilla.-","Aviso");
        }
    }

    public void setCostsCentre(int _idCostsCentre) {
	cbCostsCentre.setSelectedID(_idCostsCentre);
	refresh();	
    }

}
