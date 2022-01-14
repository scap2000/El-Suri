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
 * ResourcesRequestsAuthList.java
 *
 * */
package org.digitall.common.resourcesrequests.interfaces.resourcesrequests;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.SwingConstants;

import org.digitall.common.calendar.classes.News;
import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.classes.EntityTypes;
import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.cashflow.classes.Voucher;
import org.digitall.common.cashflow.classes.VoucherType;
import org.digitall.common.resourcescontrol.classes.Brands;
import org.digitall.common.resourcescontrol.classes.Destinations;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.common.resourcescontrol.classes.ResourceBrands;
import org.digitall.common.resourcescontrol.classes.ResourcesRequestDetail;
import org.digitall.common.resourcescontrol.classes.Units;
import org.digitall.common.resourcesrequests.classes.ResourcesRequest;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class ResourcesRequestsAuthList extends BasicPrimitivePanel {

    private BasicPanel jpSouth = new BasicPanel();

    private int[] sizeColumnList = { 192, 75, 105, 75, 95, 214 };
    private Vector dataRowRequested = new Vector();
    private GridPanel listPanelRequested = new GridPanel(30, sizeColumnList, "Materiales Solicitados", dataRowRequested);
    private int[] sizeColumnListAuth = { 192, 75, 105, 75, 75, 85, 285 };
    private Vector dataRowAuthorized = new Vector();
    private GridPanel listPanelAuth = new GridPanel(30, sizeColumnListAuth, "Materiales Autorizados", dataRowAuthorized) {

	@Override
	public void finishLoading() {
	    btnAccept.setEnabled(hasItems());
	    if (hasItems()) {
		Environment.addUnfinishedTask(getParentInternalFrame());
	    } else {
	        Environment.removeUnfinishedTask(getParentInternalFrame());
	    }
	}
    };

    private ResourcesRequestDetail resourcesRequestDetail;
    private ResourcesRequestDetail resourcesRequestAuthDetail;
    private ResourcesRequest resourcesRequest;
    private ResourcesRequestsAuthMain parentAuthMain;
    private BasicLabel lblInfo = new BasicLabel();

    private AcceptButton btnAccept = new AcceptButton();

    public ResourcesRequestsAuthList(ResourcesRequestsAuthMain _parentAuthMain) {
	try {
	    parentAuthMain = _parentAuthMain;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(775, 555));
	this.setPreferredSize(new Dimension(775, 555));
	btnAccept.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnAccept_actionPerformed(e);
		}
	    });

	listPanelRequested.setPreferredSize(new Dimension(1, 250));
	listPanelAuth.setPreferredSize(new Dimension(1, 250));

	setTitle("Autorizar Pedido de Materiales");
	add(listPanelRequested, BorderLayout.NORTH);
	add(listPanelAuth, BorderLayout.CENTER);
	add(jpSouth, BorderLayout.SOUTH);

	jpSouth.setBounds(new Rectangle(0, 0, 780, 60));
	jpSouth.setPreferredSize(new Dimension(1, 60));
	jpSouth.setSize(new Dimension(780, 60));
	jpSouth.setLayout(null);
	jpSouth.add(lblInfo, null);

	jpSouth.add(btnAccept, null);
	jpSouth.setBorder(BorderPanel.getBorderPanel("Confirmar Autorización"));

	lblInfo.setBounds(new Rectangle(15, 30, 705, 20));

	btnAccept.setToolTipText("Confirmar Autorización del Pedido de Materiales");
	btnAccept.setEnabled(false);

	btnAccept.setBounds(new Rectangle(720, 28, 40, 25));
	lblInfo.setText("Luego de autorizar los materiales que desee, deberá Confirmar la Autorización");
	lblInfo.setBackground(new Color(255, 132, 0));
	lblInfo.setOpaque(true);
	lblInfo.setForeground(Color.black);
	lblInfo.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	lblInfo.setFont(new Font("Lucida Sans", 1, 12));
	lblInfo.setHorizontalAlignment(SwingConstants.CENTER);

	setRequestHeaderList();
	setAuthHeaderList();


	listPanelAuth.getTable().addMouseListener(new MouseAdapter() {

		public void mouseClicked(MouseEvent e) {
		    if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
			loadAuthorizatedObjectSelected();
		    } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
			loadAuthModifyMgmt();
		    }
		}
	    });

	listPanelRequested.getTable().addMouseListener(new MouseAdapter() {

		public void mouseClicked(MouseEvent e) {
		    if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
			loadRequestObjectSelected();
		    } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
			loadAuthMgmt();
		    }
		}

	    });

    }

    private void setRequestHeaderList() {
	Vector requestHeaderList = new Vector();
	requestHeaderList.addElement("*");
	requestHeaderList.addElement("*");
	requestHeaderList.addElement("*");
	requestHeaderList.addElement(Environment.lang.getProperty("Material"));
	requestHeaderList.addElement("*");
	requestHeaderList.addElement(Environment.lang.getProperty("Brand"));
	requestHeaderList.addElement(Environment.lang.getProperty("Quantity"));
	requestHeaderList.addElement("*");
	requestHeaderList.addElement(Environment.lang.getProperty("Unit"));
	requestHeaderList.addElement("*");
	requestHeaderList.addElement("*");
	requestHeaderList.addElement(Environment.lang.getProperty("Autorizado"));
	requestHeaderList.addElement("*");
	requestHeaderList.addElement("*"); //Environment.lang.getProperty("Destination"));
	requestHeaderList.addElement(Environment.lang.getProperty("Actividad/Destino/Concepto"));
	requestHeaderList.addElement("*");
	requestHeaderList.addElement("*");
	requestHeaderList.addElement("*");
	requestHeaderList.addElement("*");
	requestHeaderList.addElement("*");
	requestHeaderList.addElement("*");
	requestHeaderList.addElement("*");
	requestHeaderList.addElement("*");
	requestHeaderList.addElement("*");
	requestHeaderList.addElement("*");
	requestHeaderList.addElement("*");

	listPanelRequested.setParams("resourcescontrol.getAllResourcesRequestDetailForAuth", "-1", requestHeaderList);
    }

    public void refreshRequestList() {
	listPanelRequested.refresh(resourcesRequest.getIdResourcesRequest());
    }

    private void loadRequestObjectSelected() {
	if (!dataRowRequested.isEmpty()) {
	    resourcesRequestDetail = new ResourcesRequestDetail(ResourcesRequest.REQUESTED, resourcesRequest);
	    resourcesRequestDetail.setIdResourcesRequestDetail(Integer.parseInt("" + dataRowRequested.elementAt(0)));
	    resourcesRequestDetail.setQty(Double.parseDouble("" + dataRowRequested.elementAt(6)));

	    resourcesRequestDetail.setResourceBrands(new ResourceBrands());
	    resourcesRequestDetail.getResourceBrands().setResource(new Resource(EntityTypes.RESOURCE, Integer.parseInt("" + dataRowRequested.elementAt(2))));
	    resourcesRequestDetail.getResourceBrands().getResource().setName(dataRowRequested.elementAt(3).toString());
	    resourcesRequestDetail.getResourceBrands().setBrands(new Brands(Integer.parseInt("" + dataRowRequested.elementAt(4)), "" + dataRowRequested.elementAt(5)));
	    resourcesRequestDetail.getResourceBrands().getResource().setUnit(new Units(Integer.parseInt("" + dataRowRequested.elementAt(7)), "" + dataRowRequested.elementAt(8)));

	    resourcesRequestDetail.setActualPrice(Double.parseDouble("" + dataRowRequested.elementAt(9)));
	    resourcesRequestDetail.setAmount(Double.parseDouble("" + dataRowRequested.elementAt(10)));

	    resourcesRequestDetail.setAuthorizatedQty(Double.parseDouble("" + dataRowRequested.elementAt(11)));
	    resourcesRequestDetail.setDestinations(new Destinations(Integer.parseInt("" + dataRowRequested.elementAt(12))));
	    resourcesRequestDetail.getDestinations().setName("" + dataRowRequested.elementAt(13));
	    resourcesRequestDetail.setDescription(dataRowRequested.elementAt(14).toString());

	    resourcesRequestDetail.getResourceBrands().setStock(Double.parseDouble("" + dataRowRequested.elementAt(16)));

	    resourcesRequestDetail.getResourcesRequest().setCostsCentre(new CostsCentre(Integer.parseInt("" + dataRowRequested.elementAt(17))));
	    resourcesRequestDetail.getResourcesRequest().getCostsCentre().setName("" + dataRowRequested.elementAt(18));
	    resourcesRequestDetail.getResourceBrands().getResource().setExpenditureAccount(new ExpenditureAccount(Integer.parseInt("" + dataRowRequested.elementAt(19)), "" + dataRowRequested.elementAt(20)));
	    resourcesRequestDetail.getResourceBrands().getResource().getExpenditureAccount().setInitialAmount(Double.parseDouble("" + dataRowRequested.elementAt(21)));
	    resourcesRequestDetail.getResourceBrands().getResource().getExpenditureAccount().setSpentAmount(Double.parseDouble("" + dataRowRequested.elementAt(22)));
	    resourcesRequestDetail.getResourceBrands().getResource().getExpenditureAccount().setAvailableAmount(Double.parseDouble("" + dataRowRequested.elementAt(23)));
	    resourcesRequestDetail.getResourceBrands().getResource().setTotalStock(Double.parseDouble("" + dataRowRequested.elementAt(24)));
	    resourcesRequestDetail.getResourceBrands().getResource().setAvailableStock(Double.parseDouble("" + dataRowRequested.elementAt(25)));
	}
    }

    private void loadAuthMgmt() {
	if (resourcesRequestDetail.getAuthorizatedQty() < resourcesRequestDetail.getQty()) {
	    ExtendedInternalFrame resourcesRequestAuth = new ExtendedInternalFrame("Autorizar Material: " + resourcesRequestDetail.getResourceBrands().getResource().getName().toUpperCase());
	    resourcesRequestAuth.setDesktop(Environment.getActiveDesktop());
	    ResourcesRequestsAuthMgmt resourcesRequestAuthMgmt = new ResourcesRequestsAuthMgmt();
	    resourcesRequestAuth.setCentralPanel(resourcesRequestAuthMgmt);
	    resourcesRequestAuthMgmt.setParentList(this);
	    resourcesRequestAuth.show();
	    resourcesRequestAuthMgmt.setResourceRequestDetail(resourcesRequestDetail, null);
	} else {
	    Advisor.messageBox("Ya se autorizó toda la cantidad solicitada de este material", "Error");
	}
    }

    private void setAuthHeaderList() {
	Vector authHeaderList = new Vector();
	authHeaderList.addElement("*");
	authHeaderList.addElement("*");
	authHeaderList.addElement("*");
	authHeaderList.addElement(Environment.lang.getProperty("Material"));
	authHeaderList.addElement("*");
	authHeaderList.addElement(Environment.lang.getProperty("Brand"));
	authHeaderList.addElement(Environment.lang.getProperty("Quantity"));
	authHeaderList.addElement("*");
	authHeaderList.addElement(Environment.lang.getProperty("Unit"));
	authHeaderList.addElement("*");
	authHeaderList.addElement(Environment.lang.getProperty("Costo"));
	authHeaderList.addElement("*");
	authHeaderList.addElement("*"); //Environment.lang.getProperty("Destination"));
	authHeaderList.addElement(Environment.lang.getProperty("Para"));
	authHeaderList.addElement(Environment.lang.getProperty("Actividad/Destino/Concepto"));
	authHeaderList.addElement("*");
	authHeaderList.addElement("*");
	authHeaderList.addElement("*");
	authHeaderList.addElement("*");
	authHeaderList.addElement("*");
	authHeaderList.addElement("*");

	listPanelAuth.setParams("resourcescontrol.getAllResourcesRequestDetailAuth", "-1,''", authHeaderList);
    }

    public void refreshAuthList() {
	listPanelAuth.refresh(resourcesRequest.getIdResourcesRequest() + ",''");
    }

    private void loadAuthorizatedObjectSelected() {
	if (!dataRowAuthorized.isEmpty()) {
	    resourcesRequestAuthDetail = new ResourcesRequestDetail(ResourcesRequest.AUTHORIZED, resourcesRequest);
	    resourcesRequestAuthDetail.setIdResourcesRequestDetail(Integer.parseInt("" + dataRowAuthorized.elementAt(0)));
	    resourcesRequestAuthDetail.setResourcesRequest(new ResourcesRequest(Integer.parseInt("" + dataRowAuthorized.elementAt(1))));
	    resourcesRequestAuthDetail.getResourcesRequest().setIdRequestStatus(Integer.parseInt("" + dataRowAuthorized.elementAt(20)));
	    resourcesRequestAuthDetail.setDescription(dataRowAuthorized.elementAt(13).toString());
	    resourcesRequestAuthDetail.setActualPrice(Double.parseDouble("" + dataRowAuthorized.elementAt(9)));
	    resourcesRequestAuthDetail.setQty(Double.parseDouble("" + dataRowAuthorized.elementAt(6)));

	    resourcesRequestAuthDetail.setResourceBrands(new ResourceBrands());
	    resourcesRequestAuthDetail.getResourceBrands().setBrands(new Brands(Integer.parseInt("" + dataRowAuthorized.elementAt(4)), "" + dataRowAuthorized.elementAt(5)));
	    resourcesRequestAuthDetail.getResourceBrands().setResource(new Resource(EntityTypes.RESOURCE, Integer.parseInt("" + dataRowAuthorized.elementAt(2))));
	    resourcesRequestAuthDetail.getResourceBrands().getResource().setName(dataRowAuthorized.elementAt(3).toString());
	    resourcesRequestAuthDetail.getResourceBrands().getResource().setUnit(new Units(Integer.parseInt("" + dataRowAuthorized.elementAt(7)), "" + dataRowAuthorized.elementAt(8)));
	    resourcesRequestAuthDetail.getResourceBrands().setStock(Double.parseDouble("0" + dataRowAuthorized.elementAt(19)));

	    resourcesRequestAuthDetail.setDestinations(new Destinations(Integer.parseInt("" + dataRowAuthorized.elementAt(11))));
	    resourcesRequestAuthDetail.setToBuy((dataRowAuthorized.elementAt(12) == "SI"?true:false));
	    resourcesRequestAuthDetail.getDestinations().setName("" + dataRowAuthorized.elementAt(13));

	    resourcesRequestAuthDetail.setIdResourcesRequestDetailRef(Integer.parseInt("0" + dataRowAuthorized.elementAt(17)));

	    resourcesRequestDetail = new ResourcesRequestDetail(ResourcesRequest.REQUESTED, resourcesRequest);
	    resourcesRequestDetail.setIdResourcesRequestDetail(Integer.parseInt("0" + dataRowAuthorized.elementAt(17)));
	    resourcesRequestDetail.retrieveRequestData();
	    resourcesRequestDetail.getResourceBrands().getResource().setTotalStock(Double.parseDouble("0" + dataRowAuthorized.elementAt(18)));
	}
    }

    private void loadAuthModifyMgmt() {
	if ( resourcesRequestAuthDetail.getResourcesRequest().getIdRequestStatus() <= 3) {
	    ExtendedInternalFrame resourcesRequestAuth = new ExtendedInternalFrame("Autorizar Material: " + resourcesRequestAuthDetail.getResourceBrands().getResource().getName().toUpperCase());
	    resourcesRequestAuth.setDesktop(Environment.getActiveDesktop());
	    ResourcesRequestsAuthMgmt resourcesRequestAuthMgmt = new ResourcesRequestsAuthMgmt();
	    resourcesRequestAuth.setCentralPanel(resourcesRequestAuthMgmt);
	    resourcesRequestAuthMgmt.setParentList(this);
	    resourcesRequestAuth.show();
	    resourcesRequestAuthMgmt.setResourceRequestDetail(resourcesRequestDetail, resourcesRequestAuthDetail);

	} else {
	    Advisor.messageBox("Debido al estado del Pedido,\nes imposible modificar los datos", "Error");
	}
    }

    public void refreshLists() {
	refreshRequestList();
	refreshAuthList();
    }

    public void setResourcesRequest(ResourcesRequest _resourcesRequest) {
	resourcesRequest = _resourcesRequest;
	refreshLists();
    }

    private boolean sendNotifications(int _nroRemitoInterno) {
	try {
	    boolean result = false;
	    ResultSet rsUser = LibSQL.exFunction("org.getPerson", "" + resourcesRequest.getIdSolicitor());
	    while (rsUser.next()) {
		News notification = new News();
		notification.setIdrecipientuser(rsUser.getInt("idperson"));
		notification.setIdpriority(2);
		notification.setIdstatus(2);

		String notificationText = "";
		if (_nroRemitoInterno > 0) {
		    notification.setSubject("Pedido de Materiales Nº " + resourcesRequest.getNumber() + " Autorizado y listo para retirarlo");
		    notificationText = "Se generó el Remito Interno Nº " + _nroRemitoInterno + " (referido al Pedido de Materiales Nº " + resourcesRequest.getNumber() + "). Por favor pasar a retirar los siguientes materiales:\n";
		    ResultSet rsDetail = resourcesRequest.getDetail();
		    while (rsDetail.next()) {
			if (rsDetail.getString("qtyto").equals("ENTREGAR")) {
			    notificationText += rsDetail.getString("qty") + " x " + rsDetail.getString("resource_name") + " (" + rsDetail.getString("brandrequested") + ")\n";
			}
		    }
		    notification.setText(notificationText);
		    if (notification.saveData() != -1) {
		        Advisor.messageBox("Se realizaron las notificaciones correspondientes,\nreferidas al Pedido de Materiales autorizado", "Aviso");
		        result = true;
		    } else {
		        result = false;
		        break;
		    }

		}

	    }
	    return result;
	} catch (SQLException e) {
	    e.printStackTrace();
	    return false;
	}
    }

    private void btnAccept_actionPerformed(ActionEvent e) {
	if (listPanelAuth.getItemCount() > 0) {
	    saveData();
	} else {
	    Advisor.messageBox("Debe autorizar al menos un material", "Aviso");
	}
    }

    @Override
    public boolean saveData() {
	boolean _returns = false;
	if(Advisor.question("Confirmar autorización", "¿Está seguro de confirmar la autorización del Pedido de Materiales?")) {
	    int _nroRemitoInterno = -1;
	    if (resourcesRequest.toDeliver()) {
		_nroRemitoInterno = generarRemitoInterno();
	    }
	    //LibSQL.getInt("resourcescontrol.setResourceRequestByAuthorizated", resourcesRequest.getIdResourcesRequest());
	    if (resourcesRequest.setRequestStatus(3) >= 0) {
                sendNotifications(_nroRemitoInterno);
                btnAccept.setEnabled(false);
                clearGridPanels();
                parentAuthMain.setSelectedTab(0);
                parentAuthMain.setTitle("");
                parentAuthMain.getRequestsForAuthList().refresh();
                Environment.removeUnfinishedTask(getParentInternalFrame());
		_returns = true;
	    }
	}
	return _returns;
    }

    private int generarRemitoInterno() {
	Voucher internalDespatchNote = new Voucher();
	internalDespatchNote.setDate(Environment.currentDate);
	internalDespatchNote.setIdEntitytype(EntityTypes.PERSON);
	internalDespatchNote.setIdEntity(Environment.person.getIdPerson());
	internalDespatchNote.setCostsCentre(resourcesRequest.getCostsCentre());
	internalDespatchNote.setIdVoucherType(VoucherType.IDINTERNALDESPATCHNOTES);
	internalDespatchNote.setNumber(0);
	internalDespatchNote.setClosed(true);
	int result = internalDespatchNote.addData();
	if (result >= 0) {
	    result = internalDespatchNote.generateDetail(resourcesRequest);
	}
	return result;
    }

    private void clearGridPanels() {
	listPanelRequested.refresh("-1");
	listPanelAuth.refresh("-1,''");
    }

    public int getCantItemsAutorizados() {
	return listPanelAuth.getItemCount();
    }

}
