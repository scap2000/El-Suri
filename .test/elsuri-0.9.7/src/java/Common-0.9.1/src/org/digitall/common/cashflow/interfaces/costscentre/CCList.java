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
 * CCList.java
 *
 * */
package org.digitall.common.cashflow.interfaces.costscentre;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.interfaces.account.ChecksList;
import org.digitall.common.cashflow.interfaces.costscentre.CCToolBar;
import org.digitall.common.cashflow.reports.costscentre.CCListReport;
import org.digitall.common.reports.BasicReport;
import org.digitall.common.resourcesrequests.interfaces.resourcesmovements.ResourcesDeliverList;
import org.digitall.common.resourcesrequests.interfaces.resourcesrequests.ResourcesRequestsAuthMain;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.buttons.ReportButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;

public class CCList extends BasicPrimitivePanel {

    private int[] sizeColumnList = { 43, 155, 297, 192 };
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Centros de Costos", dataRow);
    private BasicPanel dataPanel = new BasicPanel("Nuevo Centro de Costo");
    private TFInput tfName = new TFInput(DataTypes.STRING, "Name", false);
    private TFInput tfDescription = new TFInput(DataTypes.STRING, "Description", false);
    private TFInput tfCode = new TFInput(DataTypes.STRING, "Code", false);
    private CostsCentre costsCentre;
    private boolean addAction = true;
    private CCToolBar ccToolBar;
    private CCExpenditureAccountsTree ccExpenditureAccountsTree;
    private AcceptButton btnAssignedANDSpent = new AcceptButton();
    private AddButton btnNew = new AddButton();
    private DeleteButton btnDelete = new DeleteButton();
    private AcceptButton btnResourcesDeliver = new AcceptButton();
    private AcceptButton btnRequest = new AcceptButton();
    private AcceptButton btnAssignBudget = new AcceptButton();
    private PrintButton btnReport = new PrintButton();
    private ModifyButton btnModify = new ModifyButton();
    private FindButton btnSearch = new FindButton();
    private CCMgmt ccMgmt = new CCMgmt();

    public CCList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout(null);
	this.setSize(new Dimension(738, 491));
	this.setPreferredSize(new Dimension(738, 491));
	dataPanel.setLayout(null);
	dataPanel.setPreferredSize(new Dimension(1, 70));
	tfName.setBounds(new Rectangle(75, 25, 265, 35));
	btnSearch.setBounds(new Rectangle(680, 35, 30, 25));
	btnSearch.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnSearch_actionPerformed(e);
				 }

			     }
	);
	btnNew.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnNew_actionPerformed(e);
			      }

			  }
	);
	btnAssignedANDSpent.setText("Asignaciones\ny Gastos");
	btnAssignedANDSpent.setToolTipText("Abre la ventana para asignar Gastos al Centro de Costos");
	btnAssignedANDSpent.addActionListener(new ActionListener() {

					   public void actionPerformed(ActionEvent e) {
					       btnAssignedANDSpent_actionPerformed(e);
					   }

				       }
	);
	tfDescription.setBounds(new Rectangle(345, 25, 325, 35));
	tfCode.setBounds(new Rectangle(10, 25, 60, 35));
        dataPanel.add(tfDescription, null);
        dataPanel.add(tfName, null);
        dataPanel.add(tfCode, null);
        dataPanel.add(btnSearch, null);
        btnAssignedANDSpent.setEnabled(false);
	btnAssignBudget.setEnabled(false);
	btnRequest.setText("Pedidos\nde Recursos");
	btnRequest.setToolTipText("Abre la ventana para solicitar recursos");
	btnRequest.setEnabled(false);
	btnRequest.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      btnRequest_actionPerformed(e);
				  }

			      }
	);
	btnAssignBudget.setText("Asignar Partida\nPresupuestaria");
	btnAssignBudget.setToolTipText("Abre la ventana para asignar una Partida Presupuestaria al Centro de Costos");
	btnAssignBudget.addActionListener(new ActionListener() {

				       public void actionPerformed(ActionEvent e) {
					   btnAssignBudget_actionPerformed(e);
				       }

				   }
	);
	btnReport.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnReport_actionPerformed(e);
				 }

			     }
	);
	btnModify.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnModify_actionPerformed(e);
				 }

			     }
	);
	btnResourcesDeliver.setText("Entrega\nde Recursos");
	btnResourcesDeliver.setToolTipText("Abre la ventana para entregar recursos al Centro de Costos");
	btnResourcesDeliver.setEnabled(false);
	btnResourcesDeliver.addActionListener(new ActionListener() {

					   public void actionPerformed(ActionEvent e) {
					       btnResourcesDeliver_actionPerformed(e);
					   }

				       }
	);
	this.add(dataPanel, BorderLayout.NORTH);
	this.add(listPanel, BorderLayout.CENTER);
	btnDelete.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnDelete_actionPerformed(e);
				 }

			     }
	);
	addButton(btnDelete);
	addButton(btnModify);
	addButton(btnNew);
	addButton(btnReport);
	setHeaderList();
	btnNew.setToolTipText("Agregar un nuevo Centro de Costos");
	btnReport.setToolTipText("Listado de los Centros de Costos");
	btnDelete.setToolTipText("Borrar el Centro de Costos seleccionado");
	btnModify.setToolTipText("Modificar de los Centros de Costos seleccionado");
	btnModify.setEnabled(false);
	btnDelete.setEnabled(false);
        dataPanel.setBorder(BorderPanel.getBorderPanel("Agregar nuevo Tipo de Movimiento de Fondos", Color.blue, Color.WHITE));
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().getGeneralButtons().addButton(btnAssignBudget);
	getParentInternalFrame().getGeneralButtons().addButton(btnAssignedANDSpent);
	getParentInternalFrame().setInfo("Seleccione un Centro de Costos para trabajar");
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("Cod.");
	headerList.addElement("Nombre");
	headerList.addElement("Descripcion");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("Partida");
	headerList.addElement("*");
	headerList.addElement("*");
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent me) {
					       loadCostCentre();
					       selected(me);
					       if (me.getButton() == MouseEvent.BUTTON3 && me.getClickCount() == 2) {
						   int index = listPanel.getTable().rowAtPoint(me.getPoint());
						   listPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
						   loadCCToolBar();
					       } else if (me.getButton() == MouseEvent.BUTTON1 && me.getClickCount() == 2) {
						   loadAssignedANDSpent();
					       }
					   }

				       }
	);
	listPanel.setParams("cashflow.getAllCostsCentresByFilter", "'','','',null", headerList);
    }
    
    private void selected(MouseEvent e){
	int filaSeleccionada = listPanel.getTable().rowAtPoint(e.getPoint());
	listPanel.selectAllItems(false);
	listPanel.getTable().setValueAt(true,filaSeleccionada,0);
	listPanel.getTable().getSelectionModel().setSelectionInterval(filaSeleccionada,filaSeleccionada);
    }

    public void refresh() {
	String params = "'" + tfCode.getString() + "','" + tfName.getString() + "','" + tfDescription.getString() + "',null";
	listPanel.refresh(params);
	btnModify.setEnabled(false);
	btnDelete.setEnabled(false);
	btnAssignedANDSpent.setEnabled(false);
	btnAssignBudget.setEnabled(false);
	btnRequest.setEnabled(false);
	btnResourcesDeliver.setEnabled(false);
    }

    private void loadCostCentre() {
	if (!dataRow.isEmpty()) {
	    costsCentre = new CostsCentre(Integer.parseInt(dataRow.elementAt(0).toString()));
	    costsCentre.setCode(dataRow.elementAt(1).toString());
	    costsCentre.setName(dataRow.elementAt(2).toString());
	    costsCentre.setDescription(dataRow.elementAt(3).toString());
	    costsCentre.setInitialAmount(Double.parseDouble(dataRow.elementAt(4).toString()));
	    costsCentre.setSpentAmount(Double.parseDouble(dataRow.elementAt(5).toString()));
	    costsCentre.setSpentAmountP(Double.parseDouble(dataRow.elementAt(6).toString()));
	    costsCentre.setAvailableAmount(Double.parseDouble(dataRow.elementAt(7).toString()));
	    costsCentre.setModifiedAmount(Double.parseDouble(dataRow.elementAt(8).toString()));
	    costsCentre.setIsProvisionOrder(dataRow.elementAt(11).toString().equalsIgnoreCase("SI"));
	    btnModify.setEnabled(true);
	    btnDelete.setEnabled(true);
	    btnAssignBudget.setEnabled(true);
	    btnAssignedANDSpent.setEnabled(true);
	    btnDelete.setToolTipText("<html><center><u>Borrar Centro de Costos</u><br>" + dataRow.elementAt(2).toString() + "</center></html>");
	    btnModify.setToolTipText("<html><center><u>Modificar Centros de Costos</u><br>" + dataRow.elementAt(2).toString() + "</center></html>");
	} else {
	    btnDelete.setToolTipText("Borrar el Centro de Costos seleccionado");
	    btnModify.setToolTipText("Modificar de los Centros de Costos seleccionado");
	    btnModify.setEnabled(false);
	    btnDelete.setEnabled(false);
	    btnAssignBudget.setEnabled(false);
	    btnAssignedANDSpent.setEnabled(false);
	}
    }

    private void loadAssignedANDSpent() {
	ccExpenditureAccountsTree = new CCExpenditureAccountsTree();
	ccExpenditureAccountsTree.setParentList(this);
	ccExpenditureAccountsTree.setCostsCentre(costsCentre);
	ExtendedInternalFrame ccExpenditureAccountsTreeConatiner = new ExtendedInternalFrame("Asignaciones y Gastos");
	ccExpenditureAccountsTreeConatiner.setCentralPanel(ccExpenditureAccountsTree);
	ccExpenditureAccountsTreeConatiner.show();
    }

    private void loadCCToolBar() {
	ccToolBar = new CCToolBar();
	ccToolBar.setCostscentre(costsCentre);
	ExtendedInternalFrame ccToolBarContainer = new ExtendedInternalFrame(costsCentre.getName());
	ccToolBarContainer.setCentralPanel(ccToolBar);
	ccToolBarContainer.show();
    }

    /*private void clearData() {
	tfName.setValue("");
	tfCode.setValue("");
	tfDescription.setValue("");
	addAction = true;
	//dataPanel.setTitle("Nuevo Centro de Costos");
        
    }
*/
    private void btnAssignedANDSpent_actionPerformed(ActionEvent e) {
	loadAssignedANDSpent();
    }

    private void btnRequest_actionPerformed(ActionEvent e) {
	ResourcesRequestsAuthMain resourcesRequestsAuthMain = new ResourcesRequestsAuthMain();
	resourcesRequestsAuthMain.getRequestsForAuthList().setCostsCentre(costsCentre.getIdCostCentre());
	ExtendedInternalFrame resourcesRequestsAuthMainContainer = new ExtendedInternalFrame("Pedidos de Recursos Solicitados");
	resourcesRequestsAuthMainContainer.setCentralPanel(resourcesRequestsAuthMain);
	resourcesRequestsAuthMainContainer.show();
    }

    private void btnResourcesDeliver_actionPerformed(ActionEvent e) {
	ResourcesDeliverList resourcesDeliverMain = new ResourcesDeliverList();
	resourcesDeliverMain.setCostCentre(costsCentre);
	ExtendedInternalFrame internalDespatchNotesMainContainer = new ExtendedInternalFrame("Entrega de Recursos Interno");
	internalDespatchNotesMainContainer.setCentralPanel(resourcesDeliverMain);
	internalDespatchNotesMainContainer.show();
    }

    private void btnAssignBudget_actionPerformed(ActionEvent e) {
	CCAssignBudgetMgmt ccAssignBudgetMgmt = new CCAssignBudgetMgmt();
	ccAssignBudgetMgmt.setCostsCentre(costsCentre);
	ExtendedInternalFrame ccAssignBudgetMgmtContainer = new ExtendedInternalFrame("Asignar Partida Presupuestaria");
	ccAssignBudgetMgmtContainer.setCentralPanel(ccAssignBudgetMgmt);
	ccAssignBudgetMgmtContainer.show();
    }

    public void btnReport_actionPerformed(ActionEvent e) {
	/*if (listPanel.getTable().getRowCount() != 0) {
	    new CCListReport();
	} else {
	    Advisor.messageBox("El Reporte no se generó\nporque no hay datos en la Grilla", "Aviso");
	}*/
	if (listPanel.getTable().getRowCount() != 0) {
	     BasicReport report = new BasicReport(CCList.class.getResource("xml/CostCentreList.xml"));
             String params = "'"+ tfCode.getValue() + "','"+ tfName.getValue() +"','"+ tfDescription.getValue() +"',null,0,0";
	     report.addTableModel("cashflow.getAllCostsCentresByFilter", params);
	     report.doReport();
	} else {
	    Advisor.messageBox("El Reporte no se generó\nporque no hay datos en la Grilla", "Aviso");
	}
    }

    private void btnDelete_actionPerformed(ActionEvent e) {
	if (costsCentre.getIdCostCentre() != -1) {
	    if(Advisor.question("Borrar Centro de Costos", "¿Está seguro de borrar el Centro de Costos " + costsCentre.getName() + "?")) {
		if (LibSQL.getBoolean("cashflow.delcostcentre", costsCentre.getIdCostCentre())) {
		    refresh();
		} else {
		    //Advisor.messageBox("Error al borrar el Centro de Costos " + costsCentre.getName(), "Aviso");
		     Advisor.messageBox("El Centro de Costos " + costsCentre.getName()+" está asociado a una Partida Presupuestaria \n No puede ser borrado", "Aviso");
		}
	    }
	} else {
	    Advisor.messageBox("Debe seleccionar un Centro de Costos", "Aviso");
	}
    }

    private void btnModify_actionPerformed(ActionEvent e) {
	if (costsCentre != null) {
	    loadMgmt();
	}
    }

    private void loadMgmt() {
	if (costsCentre.getIdCostCentre() != -1) {
	    ccMgmt = new CCMgmt();
	    ccMgmt.setCostCentre(costsCentre);
	    ccMgmt.setParentList(this);
	    ExtendedInternalFrame ccMgmtContainer = new ExtendedInternalFrame("Modificar C. C.: " + dataRow.elementAt(2));
	    ccMgmtContainer.setCentralPanel(ccMgmt);
	    ccMgmtContainer.show();
	} else {
	    Advisor.messageBox("Debe seleccionar un Centro de Costos", "Aviso");
	}
    }

    private void btnNew_actionPerformed(ActionEvent e) {
	//clearData();
	costsCentre = new CostsCentre();
	ccMgmt = new CCMgmt();
	ccMgmt.setCostCentre(costsCentre);
	ccMgmt.setParentList(this);
	ExtendedInternalFrame ccMgmtContainer = new ExtendedInternalFrame("Agregar Centro de Costos");
	ccMgmtContainer.setCentralPanel(ccMgmt);
	ccMgmtContainer.show();
    }

    private void btnSearch_actionPerformed(ActionEvent e) {
	refresh();
    }

}
