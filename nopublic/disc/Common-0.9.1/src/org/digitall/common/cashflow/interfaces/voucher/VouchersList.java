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
 * VouchersList.java
 *
 * */
package org.digitall.common.cashflow.interfaces.voucher;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import javax.swing.JOptionPane;

import org.digitall.common.cashflow.classes.CashMovement;
import org.digitall.common.cashflow.classes.CashMovementType;
import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.cashflow.classes.Voucher;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.common.resourcescontrol.classes.Provider;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.buttons.RefreshGridButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;
import org.digitall.lib.sql.LibSQL;

public class VouchersList extends BasicPrimitivePanel {

    private BasicPanel findPanel = new BasicPanel();
    private BasicPanel content = new BasicPanel();
    private BorderLayout borderLayout1 = new BorderLayout();
    
    private int[] sizeColumnList = { 184, 90, 158, 156, 110 };
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(50000, sizeColumnList, "Comprobantes de Gastos", dataRow);
    private TFInput tfStartDate = new TFInput(DataTypes.SIMPLEDATE, "From", false);
    private TFInput tfEndDate = new TFInput(DataTypes.SIMPLEDATE, "To", false);
    private TFInput tfProvider = new TFInput(DataTypes.STRING, "Buscar Proveedor", false);
    private TFInput tfVoucherNumber = new TFInput(DataTypes.STRING, "Nº Comprobante", false);
    private VouchersMgmt voucherMgmt;
    private Voucher voucher;
    private CashMovement selectedCashMovement = null;
    private String entityName;
    private VoucherDetailList voucherDetailList;
    private ModifyButton btnModify = new ModifyButton();
    private AddButton btnAdd = new AddButton();
    private FindButton btnFind = new FindButton();
    private AcceptButton btnDetail = new AcceptButton();
    private AssignButton btnAddSpent = new AssignButton();
    private BasicPrimitivePanel parentPanel;
    private AcceptButton btnSelect = new AcceptButton();
    private CloseButton btnClose = new CloseButton();
    private PrintButton btnInvoicesNotPaidReport = new PrintButton();
    private RefreshGridButton btnCorregirFacturas = new RefreshGridButton();
    
    private CBInput cbVoucherType = new CBInput(CachedCombo.VOUCHERTYPE, "VoucherType");
    private CBInput cbProvider = new CBInput(0, "Proveedor", false);
    

    public VouchersList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(829, 392));
	this.setPreferredSize(new Dimension(730, 392));
	findPanel.setLayout(null);
	findPanel.setPreferredSize(new Dimension(1, 70));
	btnAdd.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAdd_actionPerformed(e);
			      }

			  }
	);
	btnDetail.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnDetail_actionPerformed(e);
				 }

			     }
	);
	tfStartDate.setBounds(new Rectangle(10, 25, 90, 35));
        tfStartDate.getTextField().addKeyListener(new KeyAdapter() {
                         public void keyTyped(KeyEvent e) {
                             if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                                 refresh();
                             }
                         }

                     }
                );
	cbVoucherType.setBounds(new Rectangle(200, 25, 140, 35));
	tfEndDate.setBounds(new Rectangle(105, 25, 90, 35));
        tfEndDate.getTextField().addKeyListener(new KeyAdapter() {
                         public void keyTyped(KeyEvent e) {
                             if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                                 refresh();
                             }
                         }

                     }
                );
	btnFind.setBounds(new Rectangle(790, 35, 35, 25));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);
	btnModify.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnModify_actionPerformed(e);
				 }

			     }
	);
	content.setLayout(borderLayout1);
	btnAddSpent.setBounds(new Rectangle(600, 35, 28, 33));
	btnAddSpent.setSize(new Dimension(30, 25));
	btnAddSpent.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       btnAddSpent_actionPerformed(e);
				   }

			       }
	);
	btnAddSpent.setEnabled(false);
	btnAddSpent.setVisible(false);
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	btnSelect.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnSelect_actionPerformed(e);
				 }

			     }
	);
        btnCorregirFacturas.addActionListener(new ActionListener() {

                                 public void actionPerformed(ActionEvent e) {
                                     btnCorregirFacturas_actionPerformed(e);
                                 }

                             }
        );
        btnCorregirFacturas.setToolTipText("<html><center><u>Corregir facturas</u><br></center></html>");
        content.add(findPanel, BorderLayout.NORTH);
	content.add(listPanel, BorderLayout.CENTER);
	btnDetail.setText("Detalle");
	this.add(content, null);
	this.addButton(btnClose);
	this.addButton(btnSelect);
	this.addButton(btnModify);
	this.addButton(btnAdd);
        this.addButton(btnCorregirFacturas);
	addButton(btnInvoicesNotPaidReport);
        findPanel.add(tfVoucherNumber, null);
        findPanel.add(tfProvider, null);
        findPanel.add(cbProvider, null);
        findPanel.add(btnFind, null);
        findPanel.add(tfEndDate, null);
        findPanel.add(cbVoucherType, null);
        findPanel.add(tfStartDate, null);
        cbVoucherType.autoSize();
	cbProvider.autoSize();
        cbProvider.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
					    if (e.getStateChange() == ItemEvent.SELECTED) {
						if (cbProvider.getSelectedIndex() > -1) {
                                                    refresh();
						}
					    }
					}

				    }
	);
        cbVoucherType.addItemListener(new ItemListener() {
                                        public void itemStateChanged(ItemEvent e) {
                                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                                if (cbVoucherType.getSelectedIndex() > -1) {
                                                    refresh();
                                                }
                                            }
                                        }

                                    }
        );
	setHeaderList();
	btnDetail.setEnabled(false);
	btnAddSpent.setEnabled(false);
	btnClose.setVisible(false);
	cbProvider.setBounds(new Rectangle(585, 25, 200, 35));
	cbProvider.autoSize();
	cbProvider.setGeneralItem(true);
        tfVoucherNumber.setBounds(new Rectangle(345, 25, 115, 35));
        tfProvider.setBounds(new Rectangle(470, 25, 110, 35));
	btnInvoicesNotPaidReport.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
						    btnInvoicesNotPaidReport_actionPerformed(e);
						}

					    }
	);
	btnSelect.setVisible(false);
	tfProvider.getTextField().addKeyListener(new KeyAdapter() {

					      public void keyTyped(KeyEvent e) {
						  if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
						      loadComboProvider();
						  }
					      }

					  }
	);
        tfVoucherNumber.getTextField().addKeyListener(new KeyAdapter() {

                                              public void keyTyped(KeyEvent e) {
                                                  if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                                                      refresh();
                                                  }
                                              }

                                          }
        );
	btnModify.setEnabled(false);
	btnInvoicesNotPaidReport.setToolTipText("Listado de Facturas Impagas");
	btnAdd.setToolTipText("Agregar un nuevo comprobante");
	btnModify.setToolTipText("Modificar el comprobante seleccionado");
	btnDetail.setToolTipText("Abre la ventana de Detalle del Comprobante");
        findPanel.setBorder(BorderPanel.getBorderPanel("Buscar"));
        //tfStartDate.setValue(JTDate.NULLDATE);
        //tfEndDate.setValue(JTDate.NULLDATE);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().getGeneralButtons().addButton(btnDetail);
	getParentInternalFrame().setInfo("Puede listar los comprobantes por rango de fechas, tipo y/o proveedor");
    }

    private void loadComboProvider() {
	String param = "'" + tfProvider.getString() + "'";
	cbProvider.loadJCombo(LibSQL.exFunction("org.getAllProvidersByFilter", param));
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Number"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Date"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Type"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Entity"));
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Amount"));
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Closed"));
	headerList.addElement(Environment.lang.getProperty("Paid"));
	headerList.addElement("*");
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent me) {
					       loadObject();
					       if (me.getButton() == me.BUTTON1 && me.getClickCount() == 1) {

					       } else if (me.getButton() == me.BUTTON1 && me.getClickCount() == 2) {
						   if (parentPanel != null) {
						       selectVoucher();
						   } else {
						       loadDetail();
						   }
					       } else if (me.getButton() == me.BUTTON3 && me.getClickCount() == 2) {
						   int index = listPanel.getTable().rowAtPoint(me.getPoint());
						   listPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
						   loadMgmt();
					       }
					   }

				       }
	);
	listPanel.setParams("cashflow.getAllVouchers", "'', '', -1, -1", headerList);
    }

    public void refresh() {
	String params = "'" + Proced.setFormatDate(tfStartDate.getDate(), false) + "','" + Proced.setFormatDate(tfEndDate.getDate(), false) + "'," + cbVoucherType.getSelectedValue() + ",'" + tfVoucherNumber.getString() + "'," + cbProvider.getSelectedValue();
	listPanel.refresh(params);
	btnDetail.setEnabled(false);
	btnModify.setEnabled(false);
	btnAddSpent.setEnabled(false);
        
    }

    private void loadObject() {
	if (!dataRow.isEmpty()) {
	    if (voucher == null) {
		voucher = new Voucher();
	    }
	    voucher.setIdVoucher(Integer.parseInt("" + dataRow.elementAt(0)));
	    voucher.setIdVoucherType(Integer.parseInt("" + dataRow.elementAt(1)));
	    voucher.setFullNumber("" + dataRow.elementAt(2));
	    voucher.setNumber(Long.parseLong("0" + dataRow.elementAt(3).toString()));
	    voucher.setDate(Proced.setFormatDate("" + dataRow.elementAt(4), false));
	    voucher.setIdEntitytype(Integer.parseInt("" + dataRow.elementAt(5)));
	    voucher.setIdEntity(Integer.parseInt("" + dataRow.elementAt(7)));
	    entityName = "" + dataRow.elementAt(8);
	    voucher.setPartialWOTaxes(Double.parseDouble("" + dataRow.elementAt(9)));
	    voucher.setTaxes(Double.parseDouble("" + dataRow.elementAt(10)));
	    voucher.setPartialWTaxes(Double.parseDouble("" + dataRow.elementAt(11)));
	    voucher.setVATAmount(Double.parseDouble("" + dataRow.elementAt(12)));
	    voucher.setVATp(Double.parseDouble("" + dataRow.elementAt(13)));
	    voucher.setAmount(Double.parseDouble("" + dataRow.elementAt(14)));
	    voucher.setWithDetail((dataRow.elementAt(15).toString() == "--" ? false : true));
	    voucher.setDescription("" + dataRow.elementAt(16));
	    CostsCentre costsCentre = new CostsCentre(Integer.parseInt("" + dataRow.elementAt(19)));
	    costsCentre.setName("" + dataRow.elementAt(20));
	    voucher.setCostsCentre(costsCentre);
	    if (!dataRow.elementAt(17).toString().equals("0")) {
		selectedCashMovement = new CashMovement(Integer.parseInt("" + dataRow.elementAt(17)));
		selectedCashMovement.setCashMovementType(new CashMovementType(Integer.parseInt("" + dataRow.elementAt(18))));
	    } else {
		selectedCashMovement = null;
	    }
	    voucher.setTotalAmount(Double.parseDouble("" + dataRow.elementAt(21)));
	    voucher.setClosed((dataRow.elementAt(22).toString() == "SI" ? true : false));
	    if (voucher.isClosed()) {
		btnAddSpent.setEnabled(false);
	    } else {
		btnAddSpent.setEnabled(true);
	    }
	    voucher.setPaid((dataRow.elementAt(23).toString() == "SI" ? true : false));
	    btnDetail.setEnabled(true);
	    btnModify.setEnabled(true);
	    btnModify.setToolTipText("<html><center><u>Modificar comprobante</u><br>" + voucher.getFullNumber() + "</center></html>");
	    btnDetail.setToolTipText("<html><center><u>Abre la ventana de Detalle del Comprobante</u><br>" + voucher.getFullNumber() + "</center></html>");
	} else {
	    btnModify.setToolTipText("Modificar el comprobante seleccionado");
	    btnDetail.setToolTipText("Abre la ventana de Detalle del Comprobante");
	}
    }

    private void loadMgmt() {
	if (voucher != null) {
	    voucherMgmt = new VouchersMgmt();
	    voucherMgmt.setEntityName(entityName);
	    voucherMgmt.setCashMovement(selectedCashMovement);
	    voucherMgmt.setVoucher(voucher);
	    voucherMgmt.setParentList(this);
	    ExtendedInternalFrame voucehrsMgmtContainer = new ExtendedInternalFrame("Modificar datos: " + dataRow.elementAt(2));
	    voucehrsMgmtContainer.setCentralPanel(voucherMgmt);
	    voucehrsMgmtContainer.show();
	}
    }

    private void loadDetail() {
	if (!dataRow.isEmpty()) {
	    voucherDetailList = new VoucherDetailList();
	    voucherDetailList.setVoucher(voucher);
	    voucherDetailList.setParentList(this);
	    ExtendedInternalFrame voucherDetailListContainer = new ExtendedInternalFrame("Detalle: " + dataRow.elementAt(2));
	    voucherDetailListContainer.setCentralPanel(voucherDetailList);
	    voucherDetailListContainer.show();
	}
    }

    private void btnDetail_actionPerformed(ActionEvent e) {
	loadDetail();
    }

    private void btnAdd_actionPerformed(ActionEvent e) {
	voucherMgmt = new VouchersMgmt();
	voucherMgmt.setParentList(this);
	ExtendedInternalFrame voucehrsMgmtContainer = new ExtendedInternalFrame("Agregar Nuevo Comprobante");
	voucehrsMgmtContainer.setCentralPanel(voucherMgmt);
	voucehrsMgmtContainer.show();
    }

    private void btnModify_actionPerformed(ActionEvent e) {
	loadMgmt();
    }

    private void btnFind_actionPerformed(ActionEvent e) {
	if(control()){
	    refresh();   
	}else{
	    Advisor.messageBox("La fecha desde debe ser menor o igual a la fecha hasta.","Aviso");
	}
    }
    
    private boolean control() {
	boolean resultado = true;
	//if (Proced.compareDates(Proced.setFormatDate(tfStartDate.getString(),false),Proced.setFormatDate(tfEndDate.getString(),false)) == 2) {
        if (Proced.compareDates(Proced.setFormatDate(tfStartDate.getDate(),false),Proced.setFormatDate(tfEndDate.getDate(),false)) == 2) {
	    resultado = false;
	}
	return resultado;
    }

    private void btnAddSpent_actionPerformed(ActionEvent e) {
	try {
	    if (voucher != null) {
		if (voucher.getCostsCentre().getIdCostCentre() != -1) {
		    int answer = JOptionPane.showInternalConfirmDialog(this, "¿Desea cargar esta factura como gasto\nal " + voucher.getCostsCentre().getName() + "?", "Cancelar", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, IconTypes.close_ro_16x16);
		    if (answer == JOptionPane.OK_OPTION) {
			ResultSet rs = voucher.getDetailByExpenditureAccount();
			ExpenditureAccount.CCItem ccExpenditureAccount;
			while (rs.next()) {
			    ccExpenditureAccount = new ExpenditureAccount.CCItem(voucher.getCostsCentre());
			    ccExpenditureAccount.setIDExpenditureAccount(rs.getInt("idaccount"));
			    String params = ccExpenditureAccount.getCostCentre().getIdCostCentre() + "," + ccExpenditureAccount.getIDExpenditureAccount() + ", 0, 0, 0, 0, 0, 0";
			    //int idCostCentreDetail = LibSQL.getInt("cashflow.addCostsCentreAmountDetail", params);
			    //ccExpenditureAccount.setIdCostCentreDetail(idCostCentreDetail);
			    //ccExpenditureAccount.updateSpentAmount(rs.getDouble("amount"));
			}
			voucher.setClose(true);
			refresh();
		    }
		} else {
		    Advisor.messageBox("No tiene un centro de costos asignado", "Asignar un gasto");
		}
	    } else {
		Advisor.messageBox("Seleccionar una Factura", "Seleccionar una Factura");
	    }
	} catch (SQLException ex) {
	    ex.printStackTrace();
	}
    }

    private void selectVoucher() {
	if (voucher.getIdVoucher() != -1) {
	    if (!voucher.isPaid()) {
		if (parentPanel != null) {
		    parentPanel.reload();
		}
		getParentInternalFrame().close();
	    } else {
		Advisor.messageBox("Esta Factura ya fue pagada", "Factura incorrecta");
	    }
	} else {
	    Advisor.messageBox("Debe seleccionar una Factura", "Factura requerida");
	}
    }

    public void setVoucher(Voucher voucher) {
	this.voucher = voucher;
	btnClose.setVisible(true);
	btnSelect.setVisible(true);
    }

    private void btnSelect_actionPerformed(ActionEvent e) {
	selectVoucher();
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    public void setParentPanel(BasicPrimitivePanel parentPanel) {
	this.parentPanel = parentPanel;
    }

    public void setVoucherType(int _idVoucherType) {
	cbVoucherType.setSelectedID("" + _idVoucherType);
    }

    public void setProvider(Provider _provider) {
	cbProvider.getCombo().addItem(_provider.getName(), "" + _provider.getIdProvider());
    }

    private void btnInvoicesNotPaidReport_actionPerformed(ActionEvent e) {
	if(listPanel.getTable().getRowCount() > 0){
	    BasicReport report = new BasicReport(VouchersList.class.getResource("xml/providersBook.xml"));
	    report.addTableModel("cashflow.getAllInvoicesNotPaid", "-1");
	    report.doReport();    
	}else{
	    Advisor.messageBox("No existen facturas impagas a imprimir para el Beneficiario/Sujeto","mensaje");
	}
    }
    
    private void btnCorregirFacturas_actionPerformed(ActionEvent e) {
        mostrarDetalleFacturas();
    }
    
    private void mostrarDetalleFacturas(){
        VouchersDetailList vouchersDetailList = new VouchersDetailList();
        vouchersDetailList.setParentList(this);
        ExtendedInternalFrame voucehrsDetailList = new ExtendedInternalFrame("Detalle de Facturas para Corregir" );
        voucehrsDetailList.setCentralPanel(vouchersDetailList);
        voucehrsDetailList.show();
    }

}
