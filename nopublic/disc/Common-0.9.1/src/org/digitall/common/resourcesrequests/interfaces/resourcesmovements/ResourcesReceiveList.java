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
 * ResourcesReceiveList.java
 *
 * */
package org.digitall.common.resourcesrequests.interfaces.resourcesmovements;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.Vector;

import org.digitall.common.calendar.classes.News;
import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.classes.EntityTypes;
import org.digitall.common.cashflow.classes.Voucher;
import org.digitall.common.cashflow.classes.VoucherType;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.common.resourcescontrol.classes.Brands;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.common.resourcescontrol.classes.ResourceBrands;
import org.digitall.common.resourcesrequests.classes.ResourceMovements;
import org.digitall.common.resourcesrequests.classes.ResourcesMovementDetail;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.PrintSaveButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class ResourcesReceiveList extends BasicPrimitivePanel {

    private BasicPanel dataPanel = new BasicPanel("");
    private BasicPanel contentPanel = new BasicPanel();
    private BasicPanel jPanel1 = new BasicPanel();
    private GridBagLayout gridBagLayout1 = new GridBagLayout();
    private BasicPanel jPanel2 = new BasicPanel();
    private GridBagLayout gridBagLayout2 = new GridBagLayout();
    
    private TFInput tfDespatchNoteNumber = new TFInput(DataTypes.INTEGER, "DespatchNoteNumber", true);
    private TFInput tfProvider = new TFInput(DataTypes.STRING, "Provider", false);
    private TFInput tfSearchPurchaseOrder = new TFInput(DataTypes.STRING, "SearchPurchaseOrders", false);
    private TFInput tfDespatchNoteDate = new TFInput(DataTypes.SIMPLEDATE, "Date", true);
    private CBInput cbPurchaseOrders = new CBInput(0, "PurchaseOrders", false);
    
    private int[] sizeColumnList = { 195, 130, 105, 90, 90, 60 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(50000, sizeColumnList, "Listado de Recursos de la O. C.", dataRow);
    private Vector headerList = new Vector();
    private int idprovider = -1;
    
    private int[] RNsizeColumnList = { 125, 410, 135 };
    private Vector RNdataRow = new Vector();
    private GridPanel receptionNotePanel = new GridPanel(50000, RNsizeColumnList, "Detalle del Remito", RNdataRow);
    private Vector RNheaderList = new Vector();

    private AssignButton btnAssign = new AssignButton(true);
    private PrintSaveButton btnPrintSave = new PrintSaveButton();

    private BasicLabel jLabel1 = new BasicLabel();
    //OBJECTS
    private ResourcesReceiveMain parentMain;
    private ResourceMovements resourceMovements;
    private Voucher voucher = new Voucher();
    private ResourcesMovementDetail resourcesMovementDetail = new ResourcesMovementDetail();
    private CostsCentre costsCentre = new CostsCentre();
    private ResourceBrands resourceBrands;
    private News notification;
    private Voucher voucherToDelivering;

    private int idBudget = -1;
    private CloseButton btnCancelReceptionNote = new CloseButton();  // Botón de prueba que servirá para cancelar una recepcion de materiales

    public ResourcesReceiveList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public ResourcesReceiveList(ResourcesReceiveMain _parentMain) {
	try {
	    parentMain = _parentMain;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(730, 515));
	tfDespatchNoteDate.setBounds(new Rectangle(600, 0, 115, 35));
	cbPurchaseOrders.setBounds(new Rectangle(130, 40, 155, 35));
	dataPanel.setLayout(null);
	dataPanel.setPreferredSize(new Dimension(1, 80));
	btnPrintSave.setBounds(new Rectangle(660, 513, 35, 25));
	cbPurchaseOrders.autoSize();
	contentPanel.setLayout(gridBagLayout2);
	jPanel1.setSize(new Dimension(730, 43));
	jPanel1.setLayout(gridBagLayout1);
	jPanel1.setPreferredSize(new Dimension(38, 30));
	jPanel1.setMinimumSize(new Dimension(38, 30));
	jPanel1.setBackground(new Color(247, 247, 247));
	jPanel2.setOpaque(false);
	jLabel1.setText("Seleccione (tilde) los ítems, ajuste las cantidades a recibir y presione el botón \"Asignar\" -->");
	jLabel1.setFont(new Font("Dialog", 1, 12));
	jLabel1.setForeground(new Color(198, 0, 0));
	tfProvider.setBounds(new Rectangle(395, 40, 310, 35));
	tfSearchPurchaseOrder.setBounds(new Rectangle(10, 40, 95, 35));
	tfDespatchNoteNumber.setBounds(new Rectangle(10, 0, 105, 35));
	tfProvider.setEnabled(false);
	dataPanel.add(tfDespatchNoteDate, null);
	dataPanel.add(tfProvider, null);
        dataPanel.add(cbPurchaseOrders, null);
        dataPanel.add(tfSearchPurchaseOrder, null);
	dataPanel.add(tfDespatchNoteNumber, null);
	tfDespatchNoteDate.setValue(Proced.setFormatDate(Environment.currentDate, true));
	setHeaderList();
	tfSearchPurchaseOrder.getTextField().addKeyListener(new KeyAdapter() {

							 public void keyTyped(KeyEvent e) {
							     if (e.getKeyChar() == KeyEvent.VK_ENTER) {
								 if (!tfProvider.getString().equals("")) {
								     setData();
								 } else {
								     Advisor.messageBox("Debes seleccionar un Proveedor", "Mensaje");
								 }
							     }
							 }

						     }
	);
	listPanel.setCellEditor(Types.DOUBLE, 4);
	/**
	 * ESTE ES EL CONTROL DE LA GRILLA QUE NO PERMITE QUE SE INGRESEN MAS MATERIALES DE LOS QUE SE COMPRARON
	listPanel.getTable().addKeyListener(new KeyAdapter() {

		 public void keyReleased(KeyEvent e) {
		     double bougthQty = Double.parseDouble(listPanel.getTable().getValueAt(listPanel.getTable().getSelectedRow(), 3).toString());
		     double receivedQty = Double.parseDouble(listPanel.getTable().getValueAt(listPanel.getTable().getSelectedRow(), 4).toString());
		     double toReceivedQty = Double.parseDouble(listPanel.getTable().getValueAt(listPanel.getTable().getSelectedRow(), 5).toString());
		     if (toReceivedQty > (bougthQty - receivedQty)) {
			 listPanel.getTable().getCellEditor(listPanel.getTable().getSelectedRow(), 5).cancelCellEditing();
			 Advisor.messageBox("No esta permitido recibir mas cantidad de la que se compro", "Error");
			 listPanel.getTable().setValueAt((bougthQty - receivedQty), listPanel.getTable().getSelectedRow(), 5);
		     }
		 }

	     }
	);
	*/
	btnPrintSave.addActionListener(new ActionListener() {

			     public void actionPerformed(ActionEvent e) {
				 btnPrintSave_actionPerformed(e);
			     }

			 }
	);
        btnCancelReceptionNote.addActionListener(new ActionListener() {

                             public void actionPerformed(ActionEvent e) {
                                 btnCancelReceptionNote_actionPerformed(e);
                             }

                         }
        );
	btnAssign.setBounds(new Rectangle(0, 5, 28, 33));
	btnAssign.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAssign_actionPerformed(e);
				 }

			     }
	);
	cbPurchaseOrders.addItemListener(new ItemListener() {

				      public void itemStateChanged(ItemEvent evt) {
					  if (evt.getStateChange() == ItemEvent.SELECTED) {
					      refresh();
					      costsCentre.setIdCostCentre(Integer.parseInt(cbPurchaseOrders.getSelectedValueRef().toString().split("#")[0]));
					  }
				      }

				  }
	);
        this.addButton(btnCancelReceptionNote);
	this.addButton(btnPrintSave);
	jPanel1.add(btnAssign, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
	jPanel2.add(jLabel1, null);
	jPanel1.add(jPanel2, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
	contentPanel.add(listPanel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
	contentPanel.add(jPanel1, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 692, 0));
	contentPanel.add(receptionNotePanel, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
	this.add(contentPanel, BorderLayout.CENTER);
	this.add(dataPanel, BorderLayout.NORTH);
	setRNHeaderList();
	btnAssign.setToolTipText("Asignar recurso");
	btnPrintSave.setToolTipText("Grabar e Imprimir Nota de Recepción");
        btnCancelReceptionNote.setToolTipText("Anular Nota de Recepción");
    }

    public void setData() {
        if (cbPurchaseOrders != null && cbPurchaseOrders.getSelectedIndex() > 0 && idBudget == -1 ) {
              idBudget = Integer.parseInt(cbPurchaseOrders.getSelectedValueRef().toString().split("#")[1]);
        } else {
            //System.out.println("se fué por el else");
        }
	loadComboPurchaseOrders();
	costsCentre.setIdCostCentre(Integer.parseInt(cbPurchaseOrders.getSelectedValueRef().toString().split("#")[0]));
	refresh();
        if (idBudget != -1) {
            parentMain.getResourcesMovementsMgmt().setEnabledProviders(false);
        }
    }

    private void loadComboPurchaseOrders() {
	String params = "'" + tfSearchPurchaseOrder.getString() + "'," + idprovider +","+ idBudget;
	cbPurchaseOrders.loadJCombo(LibSQL.exFunction("resourcescontrol.getAllPurchaseOrdersByFilter", params));
        if (cbPurchaseOrders.getSelectedIndex() != -1)  {
            costsCentre.setIdCostCentre(Integer.parseInt(cbPurchaseOrders.getSelectedValueRef().toString().split("#")[0]));
        }
    }

    public void setProvider(int _idprovider, String _provider) {
	idprovider = _idprovider;
	tfProvider.setValue(_provider);
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");	// 0) purchaseorderdetail.idpurchaseorderdetail
	headerList.addElement("*");	// 1) purchaseorderdetail.idresource
	headerList.addElement(Environment.lang.getProperty("Resource"));	    // 2) resources.name
	headerList.addElement("*");	                                            // 3) purchaseorderdetail.idbrand
	headerList.addElement(Environment.lang.getProperty("Brand"));	            // 4) brands.name
	headerList.addElement(Environment.lang.getProperty("PurchasedQty"));	    // 5) lackingQty
	headerList.addElement(Environment.lang.getProperty("Recibió antes"));	    // 6) purchaseorderdetail.approvedqty AS receivedQty
	headerList.addElement(Environment.lang.getProperty("Recibe ahora"));    // 7) purchaseorderdetail.approvedqty AS QtyToRecive
	headerList.addElement("*");	                                            // 8) resources.idunit
	headerList.addElement(Environment.lang.getProperty("Unit"));	            // 9) units_tabs.name
	headerList.addElement("*");	//10) purchaseorderdetail.price
	headerList.addElement("*");	//11) purchaseorderdetail.amount
	headerList.addElement("*");	//12) purchaseorderdetail.description
	headerList.addElement("*");	//13) purchaseorderdetail.idresourcesrequestauthdetail
	headerList.addElement("*");	//14) resourcescontrol.stock
	headerList.addElement("*");	//15) idCostCentre
	headerList.addElement("*");	//16) idsolicitor
	headerList.addElement("*");	//16) purchaseorderdetail.approvedqty AS boughtQty 
	String params = cbPurchaseOrders.getSelectedValue().toString();
	listPanel.setParams("resourcescontrol.getAllPurchaseOrderDetailToReceive", params, headerList);
    }

    public void refresh() {
	String params = cbPurchaseOrders.getSelectedValue().toString();
	listPanel.refresh(params);
	listPanel.selectAllItems(false);
    }

    public boolean saveAll() {
	boolean status = false;
	loadMovements();
	if (resourceMovements.getIdmovement() == -1) {
	    if (resourceMovements.saveData() != -1) {
		loadVoucherData();
		if (voucher.getIdVoucher() == -1) {
		    voucher.setClosed(true);
		    voucher.addData();
		}
		loadMovementDetail();
	        resourcesMovementDetail.setVoucher(voucher);
		if (resourcesMovementDetail.getIdmovementdetail() == -1) {
		    if (resourcesMovementDetail.saveData() != -1) {
			status = true;
		    }
		}
		if (saveVoucherDetail()) {
		    status = true;
		}
	    }
	} else {
	    /** se trata del mismo movimiento, lo que debe hacerse es 
	     *  grabar el detalle de voucher
	     *  */
	    if (saveVoucherDetail()) {
		status = true;
	    }
	}
	return status;
    }

    public boolean saveData() {
	boolean status = false;
	if (control()) {
	    if (Advisor.question("Advertencia", "¿Esta seguro de la registración de los materiales?.\nEsta operación es irreversible.")) {
                
		Environment.jpStatusBar.setAction("Saving data");
		if (saveAll()) {
		    setData();
		    Environment.jpStatusBar.setAction("Data saved...");
		    status = true;
		} else {
		    Environment.jpStatusBar.setAction("Error while saving data");
		}
	    } else {
		Environment.jpStatusBar.setAction("Data not saved...");
	    }
	}
	return status;
    }

    private void loadMovements() {
	if (resourceMovements.getIdmovement() != -1) {
	    if (voucher.getNumber() != Long.parseLong("0"+ tfDespatchNoteNumber.getString())) {
		if (Advisor.question("Aviso", "Se generará un nuevo Movimiento.\n¿Deséa imprimir la Nota de Recepción anterior?")) {
		    doReport(resourceMovements);
		}
		resourceMovements = new ResourceMovements();
	    }
	}
	resourceMovements.setIdmovementtype(parentMain.getResourcesMovementsMgmt().getIdMovementType());
	resourceMovements.setMovementtype(parentMain.getResourcesMovementsMgmt().getMovementType());
	resourceMovements.setDate(parentMain.getResourcesMovementsMgmt().getMovementDate());
	resourceMovements.setIddepot(parentMain.getResourcesMovementsMgmt().getIdDepot());
	resourceMovements.setDepot(parentMain.getResourcesMovementsMgmt().getDepot());
	resourceMovements.setDispatcher(parentMain.getResourcesMovementsMgmt().getDispatcher());
	resourceMovements.setReceiver(parentMain.getResourcesMovementsMgmt().getReceiver());
	resourceMovements.setDelivery(parentMain.getResourcesMovementsMgmt().getDelivery());
	resourceMovements.setRecipient(parentMain.getResourcesMovementsMgmt().getRecipient());
	//resourceMovements.setObservations(parentMain.getResourcesMovementsMgmt().getObservations());
	resourceMovements.setObservations(parentMain.getResourcesMovementsMgmt().getTaObservations());
    }
    
    private void grabarRecursosDistinguiblesPorUnit(){//Graba todos los recursos distinguibles de una recepcion 
	//DistinguishableResource distinguishableResource = DistinguishableResource();
	Vector values = new Vector();
	values = receptionNotePanel.getAllValues();
	for(int i = 0; i < values.size(); i++){
	    Resource resource = new Resource();
	    Vector recepcion = (Vector)values.elementAt(i);
	    resource.setIdResource(Integer.parseInt(recepcion.elementAt(6).toString()));
	    resource.retrieveData();
	    int idResource = resource.getIdResource();
	    String idBrand = recepcion.elementAt(8).toString();
	    String  cantidad  = recepcion.elementAt(11).toString();
	    cantidad = cantidad.substring(0,cantidad.indexOf("."));
	    String precio = recepcion.elementAt(12).toString();
	    String idVoucherDetail = recepcion.elementAt(1).toString();
	    if(resource.isDistinguishable()){
		String params = ""+  idResource+ "," + idBrand +
		"," +  cantidad + "," + precio + "," + idVoucherDetail;
		LibSQL.getInt("resourcescontrol.addresourceunitsdistbyreceptionnote",params);
	    }
	}
    }
    

    private void loadVoucherData() {
	if (voucher.getNumber() != Long.parseLong("0" + tfDespatchNoteNumber.getString())) {
	    voucher.setIdVoucher(-1);
	    voucher.setIdVoucherType(VoucherType.IDDESPATCHNOTES);
	    voucher.setNumber(Long.parseLong("0"+ tfDespatchNoteNumber.getString()));
	    voucher.setDate(Proced.setFormatDate(tfDespatchNoteDate.getString(), false));
	    voucher.setIdEntitytype(EntityTypes.PROVIDER);
	    voucher.setIdEntity(idprovider);
	    voucher.setCostsCentre(costsCentre);
	    resourcesMovementDetail.setIdmovementdetail(-1);
	} else {
	    //System.out.println("aca deberia seguir el mismo voucher (Remito externo");
	}
    }

    private void loadMovementDetail() {
	resourcesMovementDetail.setResourceMovements(resourceMovements);
	resourcesMovementDetail.setVoucher(voucher);
    }

    private boolean saveVoucherDetail() {
	boolean band = true;
	int result = -1;
	Vector rows = listPanel.getSelectedsVector();
	String params = "";
	for (int i = 0; i < rows.size(); i++) {
	    Vector selectedDataRow = (Vector)rows.elementAt(i);
	    params = "" + resourcesMovementDetail.getVoucher().getIdVoucher() + "," + selectedDataRow.elementAt(0) + "," + Double.parseDouble(selectedDataRow.elementAt(7).toString());
	    result = LibSQL.getInt("cashflow.addDespatchNoteDetail", params);
	    resourceBrands = new ResourceBrands();
	    resourceBrands.setResource(new Resource(EntityTypes.RESOURCE, Integer.parseInt(selectedDataRow.elementAt(1).toString())));
	    resourceBrands.setBrands(new Brands(Integer.parseInt(selectedDataRow.elementAt(3).toString())));
	    resourceBrands.setStock(Double.parseDouble(selectedDataRow.elementAt(14).toString()));
	    resourceBrands.setProduceable(true);
	    resourceBrands.produce(Double.parseDouble(selectedDataRow.elementAt(7).toString()));
	    resourceBrands.updateStock();
	    resourceBrands.getResource().updateTotalStock();
	    /** Aá se tendrá que registrar cada tupla en la nueva tabla con  los campos idMovDet,idMov, idVoucher, idVoucherDetail, idPurchaseOrder  */
	    if (result == -1) {
		band = false;
	    }
	}
        /** Generación de Notificaciones */
	/*if (addInternalDespatchNote(rows)) {
	    registerNotifications(rows);
	}*/
	return band;
    }

    private boolean addInternalDespatchNote(Vector _rows) {
	boolean resultInsert = true;
	addVoucherDataToDelivering(_rows);
	//Registro el detalle del Remito Interno
	int result = -1;
	String params = "";
	for (int i = 0; i < _rows.size(); i++) {
	    Vector selectedDataRow = (Vector)_rows.elementAt(i);
	    params = "" + voucherToDelivering.getIdVoucher() + "," + selectedDataRow.elementAt(0) + "," + selectedDataRow.elementAt(7);
	    result = LibSQL.getInt("cashflow.addInternalDespatchNoteDetail", params);
	    if (result == -1) {
		resultInsert = false;
	    }
	}
	return resultInsert;
    }

    private boolean registerNotifications(Vector _rows) {
	Vector selectedDataRow = (Vector)_rows.elementAt(0);
	try {
	    boolean result = false;
	    //ResultSet rsUser = LibSQL.exFunction("org.getAllUsersByPerson", "" + Integer.parseInt(selectedDataRow.elementAt(16).toString()));
	    ResultSet rsUser = LibSQL.exFunction("org.getPerson", "" + Integer.parseInt(selectedDataRow.elementAt(16).toString()));
	    while (rsUser.next()) {
		notification = new News();
		//notification.setIdrecipientuser(rsUser.getInt("iduser"));
	        notification.setIdrecipientuser(rsUser.getInt("idperson"));
		notification.setIdpriority(2);
		notification.setIdstatus(2);
		String notificationText = "Se generó el Remito Interno Nº " + voucherToDelivering.getNumber() + ", referido la Orden de Compra " + cbPurchaseOrders.getSelectedItem().toString() + ".\n Por favor pasar a retirar los siguientes materiales:\n";
		String params = "" + voucherToDelivering.getIdVoucher() + ",0,0";
		ResultSet voucherDetail = LibSQL.exFunction("cashflow.getAllVoucherDetail", params);
		try {
		    while (voucherDetail.next()) {
			notificationText += "* " + voucherDetail.getDouble("finalqty") + " x " + voucherDetail.getString("resource") + " (" + voucherDetail.getString("brand") + ")\n";
		    }
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		notification.setText(notificationText);
		notification.setSubject("LLegada de Materiales");
		if (notification.saveData() != -1) {
		    Advisor.messageBox("Se realizaron las notificaciones correspondientes,\ncon respecto a la llegada de Materiales.", "Notificación realizada");
		    result = true;
		} else {
		    result = false;
		    break;
		}
	    }
	    /*if (result) {
		Advisor.messageBox("Se realizaron las notificaciones correspondientes,\ncon respecto a la llegada de Materiales.", "Notificación realizada");
	    }*/
	    return result;
	} catch (SQLException e) {
	    e.printStackTrace();
	    return false;
	}
    }

    private void addVoucherDataToDelivering(Vector _rows) {
	voucherToDelivering = new Voucher();
	voucherToDelivering.setIdVoucherType(VoucherType.IDINTERNALDESPATCHNOTES);
	voucherToDelivering.setDate(Environment.currentDate);
	voucherToDelivering.setIdEntitytype(EntityTypes.PROVIDER);
	voucherToDelivering.setIdEntity(3);
	//Ojo siempre tiene que ser el id de Sermaq
	Vector selectedDataRow = (Vector)_rows.elementAt(0);
	voucherToDelivering.setCostsCentre(new CostsCentre(Integer.parseInt(selectedDataRow.elementAt(15).toString())));
	voucherToDelivering.setClosed(false);
	voucherToDelivering.addData();
	voucherToDelivering.retrieveData();
    }

    private boolean control() {
	boolean _return = false;
	Vector rowsSelected = listPanel.getSelectedsID();
	if (rowsSelected.size() == 0){
	    Advisor.messageBox("Debe seleccionar (tildar) al menos un ítem de la Orden de Compra","Mensaje");    
	} else if (tfDespatchNoteNumber.getInteger() <= 0)  {
	    Advisor.messageBox("El Número de Remito debe ser mayor que cero","Mensaje");	
	} else if (tfDespatchNoteDate.getDate().length() <= 0) {
	    Advisor.messageBox("Debe ingresar la fecha del Remito", "Mensaje");
	} else if (listPanel.getTable().isEditing()) {
	    Advisor.messageBox("Hay una celda en edición\nTermine la edición presionando enter", "Mensaje");
	} else if (LibSQL.getBoolean("cashflow.existVoucherNumber",""+ tfDespatchNoteNumber.getValue() +",7,"+ idprovider)
	&&  resourceMovements.getIdmovement() == -1) {
            Advisor.messageBox("El Número de Remito que desea ingresar ya existe", "Mensaje");
	} else if (Double.parseDouble(dataRow.elementAt(7).toString()) == 0) {
		Advisor.messageBox("La cantidad recibida debe ser mayor que cero", "Mensaje");
        } else {
	    _return = true;
	}
	return _return;
    }

    public void setResourceMovements(ResourceMovements _resourceMovements) {
	resourceMovements = _resourceMovements;
    }

    public void clearTfDespatchNoteNumber() {
	tfDespatchNoteNumber.setValue(0);
        if (resourceMovements == null || resourceMovements.getIdmovement() != -1) {
            resourceMovements = new ResourceMovements();
            voucher = new Voucher();
        } else {
            if (resourceMovements.getIdmovement() != -1) {
                Advisor.messageBox("Debe terminar la Nota de Recepción","Aviso");
            }
        }
    }

    private void btnPrintSave_actionPerformed(ActionEvent e) {
	if (resourceMovements != null) {
	    if (resourceMovements.getIdmovement() != -1) {
	        // Enviar Notificaciones
	        if (resourceMovements.notificar()) {
                    Advisor.messageBox("Se realizaron las Notificaciones correspondientes", "Aviso");
                } else {
                    Advisor.messageBox("Ocurrió un error al enviar las Notificaciones", "Aviso");
                }
                //Grabar los recursos materiales distinguibles por unidad
	        grabarRecursosDistinguiblesPorUnit();
                
	        doReport(resourceMovements);
		setNewMovement();
	    } else {
		Advisor.messageBox("Antes de generar un Informe, debe realizar un movimiento", "Aviso");
	    }
	} else {
	    Advisor.messageBox("Antes de generar un Informe, debe realizar un movimiento", "Aviso");
	}
    }

    private void setNewMovement() {
	resourceMovements = new ResourceMovements();
	resourcesMovementDetail.setVoucher(new Voucher());
	resourcesMovementDetail.setResourceMovements(resourceMovements);
        idBudget = -1;
        parentMain.getResourcesMovementsMgmt().setEnabledProviders(true);
	clearFields();
	refreshRNPanel();
	parentMain.setTAObservations("");
	parentMain.setSelectedTab(0);
    }

    private void clearFields() {
	loadComboPurchaseOrders();
	tfDespatchNoteNumber.setValue(0);
	refreshRNPanel();
    }

    private void setRNHeaderList() {
	RNheaderList.removeAllElements();
	RNheaderList.addElement("*");	// 0) voucherdetail.idvoucherdetail,
	RNheaderList.addElement("*");	// 1) voucherdetail.idvoucher,
	RNheaderList.addElement("*");	// 2) voucherdetail.idaccount,
	RNheaderList.addElement(Environment.lang.getProperty("PurchaseOrderNumber"));	// 3) ponumber
	RNheaderList.addElement("*");	// 4) accounts.name,
	RNheaderList.addElement("*");	// 5) voucherdetail.idresource,
	RNheaderList.addElement(Environment.lang.getProperty("Resource"));	        // 6) resource,
	RNheaderList.addElement("*");	// 7) voucherdetail.idbrand,
	RNheaderList.addElement("*");	// 8) brand,
	RNheaderList.addElement("*");	// 9) voucherdetail.originalqty,
	RNheaderList.addElement(Environment.lang.getProperty("Quantity"));	        //10) voucherdetail.finalqty,
	RNheaderList.addElement("*");	//11) voucherdetail.price,
	RNheaderList.addElement("*");	//12) voucherdetail.partialwotaxes,
	RNheaderList.addElement("*");	//13) voucherdetail.taxes,
	RNheaderList.addElement("*");	//14) voucherdetail.partialwtaxes,
	RNheaderList.addElement("*");	//15) voucherdetail.vatamount,
	RNheaderList.addElement("*");	//16) voucherdetail.vatp,
	RNheaderList.addElement("*");	//17) voucherdetail.amount,
	RNheaderList.addElement("*");	//18) voucherdetail.description,
	RNheaderList.addElement("*");	//19) idunit,
	RNheaderList.addElement("*");	//20) unit,
	RNheaderList.addElement("*");	//21) voucherdetail.estado,
	RNheaderList.addElement("*");	//22) voucherdetail.idvoucherresourcestatus
	String params = "-1";
	receptionNotePanel.setParams("cashflow.getAllVoucherDetailToReceiveNote", params, RNheaderList);
    }

    public void refreshRNPanel() {
	if (resourcesMovementDetail.getVoucher() != null) {
	    String params = "" + resourcesMovementDetail.getVoucher().getIdVoucher();
            
	    receptionNotePanel.refresh(params);
	}
    }

    private void btnAssign_actionPerformed(ActionEvent e) {
	saveData();
	refreshRNPanel();
    }

    private void doReport(ResourceMovements resourceMovements) {
	BasicReport report = new BasicReport(ResourcesReceiveList.class.getResource("xml/receptionNote.xml"));
	int idvoucher = LibSQL.getInt("cashflow.getIdVoucher", resourceMovements.getIdmovement());
	report.addTableModel("cashflow.getVoucherToReceptionNote", idvoucher  + ",'" + resourceMovements.getDispatcher() + "',"+ idprovider);
	report.addTableModel("cashflow.getallvoucherdetailtoreceivenote", idvoucher + ",0,0");
	report.doReport();
    }

    public int getIdBudget() {
        return idBudget;
    }
    
    private void btnCancelReceptionNote_actionPerformed(ActionEvent e) {
        if (resourceMovements != null && resourceMovements.getIdmovement() != -1) {
        } else {
            //System.out.println("xxxxxxxxx");
            //idBudget = -1;
        }
    }
    
}
