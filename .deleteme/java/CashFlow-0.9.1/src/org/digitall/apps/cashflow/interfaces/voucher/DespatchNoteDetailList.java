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
 * DespatchNoteDetailList.java
 *
 * */
package org.digitall.apps.cashflow.interfaces.voucher;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;

import java.util.Vector;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.classes.EntityTypes;
import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.cashflow.classes.VoucherDetail;
import org.digitall.common.cashflow.classes.Voucher;
import org.digitall.apps.cashflow.interfaces.CFFrameContainer;
import org.digitall.apps.cashflow.reports.XMLSheetDespatchNoteList;
import org.digitall.common.cashflow.classes.ExpenditureAccount.CCItem;
import org.digitall.common.cashflow.interfaces.voucher.VouchersList;
import org.digitall.common.resourcescontrol.classes.Brands;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.common.resourcescontrol.classes.ResourceBrands;
import org.digitall.common.resourcescontrol.classes.Units;
import org.digitall.common.resourcesrequests.classes.ResourcesMovementDetail;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.buttons.ReportButton;
import org.digitall.lib.components.grid.PanelGrid;
import org.digitall.lib.sql.LibSQL;

public class DespatchNoteDetailList extends BasicPanel {

    private int[] sizeColumnList = {245, 210, 150, 95};
    private PanelGrid listPanel = new PanelGrid(30, sizeColumnList, "Detalle del Remito", false);
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private CloseButton btnClose = new CloseButton();
    private AddButton btnAdd = new AddButton();
    private ModifyButton btnModify = new ModifyButton();
    private CFFrameContainer parentContainer;
    private Voucher voucher = new Voucher();
    private VoucherDetail selectedVoucherDetail;
    private DespatchNoteDetailListMgmt despatchNoteListMgmt;
    
    private Resource resource;
    private ResourceBrands resourceBrand;
    private VouchersList parentList;
    
    private ResourcesMovementDetail resourcesMovementDetail;
    private ReportButton btnReport = new ReportButton();
    private String param = "";

    public DespatchNoteDetailList(CFFrameContainer _parentContainer) {
	try {
	    parentContainer = _parentContainer;
	    jbInit();
	    parentContainer.setPanel(this);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout( null );
	this.setSize(new Dimension(730, 469));
	listPanel.setBounds(new Rectangle(5, 0, 720, 420));
	btnClose.setBounds(new Rectangle(685, 440, 40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	btnAdd.setBounds(new Rectangle(580, 440, 40, 25));
	btnAdd.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAdd_actionPerformed(e);
			      }

			  }
	);
	btnModify.setBounds(new Rectangle(633, 440, 40, 25));
	btnModify.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnModify_actionPerformed(e);
				 }

			     }
	);
	this.add(btnReport, null);
	this.add(btnModify, null);
	this.add(btnAdd, null);
	this.add(btnClose, null);
	this.add(listPanel, null);
	
	listPanel.autoSize();   
	setHeaderList();
	btnModify.setEnabled(false);
	btnReport.setBounds(new Rectangle(5, 440, 40, 25));
	btnReport.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnReport_actionPerformed(e);
				 }

			     }
	);
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("Tipo de Gasto");
	headerList.addElement("*");
	headerList.addElement("Recurso");
	headerList.addElement("*");
	headerList.addElement("Marca");
	headerList.addElement("*");
	headerList.addElement("Cantidad");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");	 
	headerList.addElement("*");
	headerList.addElement("*");
	 
	listPanel.getTable().addMouseListener(new MouseAdapter(){
	     public void mouseClicked(MouseEvent me) {
		 if (me.getButton() == MouseEvent.BUTTON1 && me.getClickCount() == 2) {
		      loadVoucherDetailMgmt();
		      
		 } else if (me.getButton() == MouseEvent.BUTTON3 && me.getClickCount() == 2) {
		     // selecciona la fila donde se hizo el doble click
		     int index = listPanel.getTable().rowAtPoint(me.getPoint());
		     listPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
		 }
	     }
	 });
	 
	listPanel.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {
		    if (!e.getValueIsAdjusting()) {
			dataRow = listPanel.getDataRow();
			/** evento que cargha el objecto al seleccionar una fila **/
			loadObject();
			btnModify.setEnabled(true);
		    }
		}
	     }
	 );
    }

    public void loadList() {
	param = ""+ voucher.getIdVoucher();
	listPanel.setTable(parentContainer, "cashflow.getAllVoucherDetail", param, headerList);
	btnModify.setEnabled(false);
    }

    public void clearList() {
	listPanel.getTable().clearSelection();
    }
    
    private void loadObject(){
	selectedVoucherDetail = new VoucherDetail();
	selectedVoucherDetail.setIdVoucherDetail(Integer.parseInt(""+ dataRow.elementAt(0)));
	selectedVoucherDetail.setVoucher(voucher);
	selectedVoucherDetail.setExpenditureAccount(new ExpenditureAccount.CCItem(Integer.parseInt(""+ dataRow.elementAt(2)), ""+ dataRow.elementAt(3)));
	
	resourceBrand = new ResourceBrands();
	resource = new Resource(EntityTypes.RESOURCE, Integer.parseInt(""+ dataRow.elementAt(4)));
	resource.setName(""+ dataRow.elementAt(5));
	resourceBrand.setResource(resource);
	resourceBrand.setBrands(new Brands(Integer.parseInt(""+ dataRow.elementAt(6))));
	resourceBrand.setPrice(Double.parseDouble(""+ dataRow.elementAt(10)));
	resourceBrand.setUnit(new Units(Integer.parseInt(""+ dataRow.elementAt(18)),dataRow.elementAt(19).toString()));
	selectedVoucherDetail.setResourceBrand(resourceBrand);
	
	selectedVoucherDetail.setOriginalQty(Double.parseDouble(""+ dataRow.elementAt(8)));
	selectedVoucherDetail.setFinalQty(Double.parseDouble(""+ dataRow.elementAt(9)));
	selectedVoucherDetail.setPartialWOTaxes(Double.parseDouble(""+ dataRow.elementAt(11)));
	selectedVoucherDetail.setTaxes(Double.parseDouble(""+ dataRow.elementAt(12)));
	selectedVoucherDetail.setPartialWTaxes(Double.parseDouble(""+ dataRow.elementAt(13)));
	selectedVoucherDetail.setVATAmount(Double.parseDouble(""+ dataRow.elementAt(14)));
	selectedVoucherDetail.setVATp(Double.parseDouble(""+ dataRow.elementAt(15)));
	selectedVoucherDetail.setAmount(Double.parseDouble(""+ dataRow.elementAt(16)));
	selectedVoucherDetail.setDescription(""+ dataRow.elementAt(17));	
    }

    private void loadVoucherDetailMgmt(){
	despatchNoteListMgmt = new DespatchNoteDetailListMgmt();
	despatchNoteListMgmt.setVoucherDetail(voucher,selectedVoucherDetail);
	despatchNoteListMgmt.setDespatchNoteList(this);
	
	ExtendedInternalFrame despatchNoteListMgmtContainer = new ExtendedInternalFrame("Detalle");
	despatchNoteListMgmtContainer.setCentralPanel(despatchNoteListMgmt);
	despatchNoteListMgmtContainer.show();
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	//parentList.loadList();	
	parentContainer.close();
    }

    private void btnModify_actionPerformed(ActionEvent e) {
	loadVoucherDetailMgmt();
    }

    private void btnAdd_actionPerformed(ActionEvent e) {
	selectedVoucherDetail = new VoucherDetail();
	selectedVoucherDetail.setVoucher(voucher);
	loadVoucherDetailMgmt();
    }

    public void setParentList(VouchersList _parentList) {
	parentList = _parentList;
    }

    public void setResourcesMovementDetail(ResourcesMovementDetail _resourcesMovementDetail){
	resourcesMovementDetail = _resourcesMovementDetail;
    }
    
    
    private void loadVoucher() {
	String params = String.valueOf(resourcesMovementDetail.getVoucher().getIdVoucher());
	ResultSet voucherData = LibSQL.exFunction("cashflow.getVoucher",params);
	try  {
	    if (voucherData.next())  {
	        voucher.setIdVoucher(voucherData.getInt("idvoucher"));
	        voucher.setIdVoucherType(voucherData.getInt("idvouchertype"));
	        voucher.setNumber(voucherData.getLong("number"));
	        voucher.setDate(voucherData.getString("date"));
	        voucher.setIdEntity(voucherData.getInt("identity"));
	        voucher.setIdEntitytype(voucherData.getInt("identitytype"));
		voucher.setPartialWOTaxes(voucherData.getDouble("partialwotaxes"));
	        voucher.setTaxes(voucherData.getDouble("taxes"));
	        voucher.setPartialWTaxes(voucherData.getDouble("partialwtaxes"));
	        voucher.setVATAmount(voucherData.getDouble("vatamount"));
	        voucher.setVATp(voucherData.getDouble("vatp"));
	        voucher.setAmount(voucherData.getDouble("amount"));
	        voucher.setWithDetail(voucherData.getBoolean("amount"));
	        voucher.setDescription(voucherData.getString("description"));
	        voucher.setCostsCentre(new CostsCentre(voucherData.getInt("idcostcentre")));
	    }
	       
	} catch (Exception ex)  {
	    ex.printStackTrace();
	}
	
    }

    private void btnReport_actionPerformed(ActionEvent e) {
	new XMLSheetDespatchNoteList(voucher,param);
    }

}
