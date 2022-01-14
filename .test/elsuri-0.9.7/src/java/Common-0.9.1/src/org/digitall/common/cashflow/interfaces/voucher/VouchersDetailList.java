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
 * VouchersDetailList.java
 *
 * */
package org.digitall.common.cashflow.interfaces.voucher;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import org.digitall.common.cashflow.classes.EntityTypes;
import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.cashflow.classes.VoucherDetail;
import org.digitall.common.cashflow.classes.Voucher;
import org.digitall.common.resourcescontrol.classes.Brands;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.common.resourcescontrol.classes.ResourceBrands;
import org.digitall.common.resourcescontrol.classes.Units;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.buttons.UnAssignButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;

public class VouchersDetailList extends BasicPrimitivePanel {

    private int[] sizeColumnList = {  195, 118, 92, 90, 89, 119, 197, 90 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(500000, sizeColumnList, "Detalle de Facturas para Corregir", dataRow);
    private Vector headerList = new Vector();
    private CloseButton btnClose = new CloseButton();
    private Voucher voucher;
    private VoucherDetail selectedVoucherDetail;
    private VoucherDetailMgmt voucherDetailMgmt;
    private Resource resource;
    private ResourceBrands resourceBrand;
    private VouchersList parentList;
    private BasicPanel panelContenedor = new BasicPanel();
    private BasicPanel panelModificar = new BasicPanel();
    private TFInput tfCantidad = new TFInput(DataTypes.DOUBLE_EXTENDED, "Cantidad", false);
    private TFInput tfPrecioUnitario = new TFInput(DataTypes.MONEY_EXTENDED, "$ Precio Unit.", false);
    private TFInput tfTotal = new TFInput(DataTypes.MONEY, "$ Total", false);
    private AssignButton btnAsignarCantidad = new AssignButton();
    private UnAssignButton btnAsignarPrecioUnit = new UnAssignButton();
    private SaveDataButton btnGrabarDetalle = new SaveDataButton();
    private TFInput tfNumFactura = new TFInput("Nº Factura", true, "####-########");
    private TFInput tfDescripcion = new TFInput(DataTypes.STRING, "Observaciones", false);
    private TFInput tfNumViejoFactura = new TFInput(DataTypes.INTEGER, "Número", false);
    private SaveDataButton btnGrabarEncabezado = new SaveDataButton();

    public VouchersDetailList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
        panelContenedor.setLayout(null);
        panelModificar.setLayout(null);
        panelModificar.setBounds(new Rectangle(5, 295, 720, 130));
        panelModificar.setBorder(BorderPanel.getBorderPanel(""));
        tfCantidad.setBounds(new Rectangle(5, 5, 75, 35));
        tfPrecioUnitario.setBounds(new Rectangle(98, 5, 85, 35));
        tfTotal.setBounds(new Rectangle(200, 5, 95, 35));
        tfTotal.setEnabled(false);
        tfNumViejoFactura.setEnabled(false);
        btnAsignarCantidad.setBounds(new Rectangle(22, 50, 40, 25));
        btnAsignarCantidad.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnAsignarCantidad_actionPerformed(e);
                }
            });
        btnAsignarPrecioUnit.setBounds(new Rectangle(115, 50, 40, 25));
        btnAsignarPrecioUnit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnAsignarPrecioUnit_actionPerformed(e);
                }
            });
        btnGrabarDetalle.setBounds(new Rectangle(300, 20, 40, 25));
        btnGrabarDetalle.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnGrabarDetalle_actionPerformed(e);
                }
            });
        btnGrabarDetalle.setToolTipText("Grabar detalle de factura");
        btnGrabarEncabezado.setToolTipText("Grabar encabezado de factura");
        tfNumFactura.setBounds(new Rectangle(100, 80, 125, 35));
        tfDescripcion.setBounds(new Rectangle(315, 80, 300, 35));
        tfNumViejoFactura.setBounds(new Rectangle(5, 80, 75, 35));
        btnGrabarEncabezado.setBounds(new Rectangle(615, 95, 40, 25));
        btnGrabarEncabezado.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnGrabarEncabezado_actionPerformed(e);
                }
            });
        btnAsignarCantidad.setToolTipText("Recalcular Precio unitario");
        btnAsignarPrecioUnit.setToolTipText("Recalcular Cantidad");
        this.setSize(new Dimension(730, 465));
	this.setPreferredSize(new Dimension(730, 290));
	listPanel.setBounds(new Rectangle(5, 5, 720, 280));
        btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
        
        tfCantidad.getTextField().addKeyListener(new KeyAdapter() {

                                                   public void keyTyped(KeyEvent e) {
                                                       if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                                                           recalcularPrecioUnitario();
                                                       }
                                                   }

                                               }
        );
        
        tfPrecioUnitario.getTextField().addKeyListener(new KeyAdapter() {

                                                   public void keyTyped(KeyEvent e) {
                                                       if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                                                           recalcularCantidad();
                                                       }
                                                   }

                                               }
        );
	
	this.addButton(btnClose);
        panelContenedor.add(panelModificar, null);
        panelContenedor.add(listPanel, null);
        panelModificar.add(tfCantidad,null);
        panelModificar.add(tfPrecioUnitario,null);
        panelModificar.add(tfTotal,null);
        panelModificar.add(btnAsignarCantidad,null);
        panelModificar.add(btnAsignarPrecioUnit,null);
        panelModificar.add(btnGrabarDetalle,null);
        panelModificar.add(tfNumFactura,null);
        panelModificar.add(tfDescripcion,null);
        panelModificar.add(tfNumViejoFactura,null);
        panelModificar.add(btnGrabarEncabezado,null);
        this.add(panelContenedor, BorderLayout.CENTER);
	setHeaderList();
        refresh();
        tfNumFactura.setValue("0000-00000000");
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Seleccione un ítem para modificar");
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("Recurso");
	headerList.addElement("*");
	headerList.addElement("Marca");
	headerList.addElement("*");
	headerList.addElement("Cantidad");
	headerList.addElement("Precio/Unit");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("($) Total");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("Nº Factura");
        headerList.addElement("*");
        headerList.addElement("Proveedor");
        headerList.addElement("*");
        headerList.addElement("Fecha");
        headerList.addElement("*");
        headerList.addElement("*");
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent me) {
					       loadObjectDetail();
					       if (me.getButton() == MouseEvent.BUTTON1 && me.getClickCount() == 1) {
					           // selecciona la fila donde se hizo el click
					           int index = listPanel.getTable().rowAtPoint(me.getPoint());
					           listPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
                                                   setData();
					       } 
					   }

				       }
	);
        listPanel.getTable().addKeyListener(new KeyListener() {

                public void keyTyped(KeyEvent e) {
                    setData();
                }

                public void keyPressed(KeyEvent e) {
                    setData();
                }

                public void keyReleased(KeyEvent e) {
                    setData();
                }
                
            }
        );
	listPanel.setParams("cashflow.getAllVouchersDetailParaCorregir", "", headerList);
    }

    public void refresh() {
	String params = "";
	listPanel.refresh(params);
    }

    public void clearList() {
	listPanel.getTable().clearSelection();
    }
    
    private void setData(){
        loadObjectDetail();
        loadObjectEncabezado();
        tfCantidad.setValue(selectedVoucherDetail.getFinalQty());
        tfPrecioUnitario.setValue(selectedVoucherDetail.getPrice());
        tfTotal.setValue(selectedVoucherDetail.getAmount());
        tfNumFactura.setValue(voucher.getNroFactura());
        tfDescripcion.setValue(voucher.getDescription());
        tfNumViejoFactura.setValue(voucher.getNumber());
    }
    private void loadObjectDetail() {
	if (!dataRow.isEmpty()) {
	    selectedVoucherDetail = new VoucherDetail();
	    selectedVoucherDetail.setIdVoucherDetail(Integer.parseInt("" + dataRow.elementAt(0)));
	    selectedVoucherDetail.setVoucher(voucher);
	    selectedVoucherDetail.setExpenditureAccount(new ExpenditureAccount.CCItem(Integer.parseInt("" + dataRow.elementAt(2)), "" + dataRow.elementAt(3)));
	    resourceBrand = new ResourceBrands();
	    resource = new Resource(EntityTypes.RESOURCE, Integer.parseInt("" + dataRow.elementAt(4)));
	    resource.setName("" + dataRow.elementAt(5));
	    resourceBrand.setResource(resource);
	    resourceBrand.setBrands(new Brands(Integer.parseInt("" + dataRow.elementAt(6))));
	    resourceBrand.setPrice(Double.parseDouble("" + dataRow.elementAt(10)));
	    resourceBrand.setUnit(new Units(Integer.parseInt("" + dataRow.elementAt(18)), dataRow.elementAt(19).toString()));
	    selectedVoucherDetail.setResourceBrand(resourceBrand);
	    selectedVoucherDetail.setOriginalQty(Double.parseDouble("" + dataRow.elementAt(8)));
	    selectedVoucherDetail.setFinalQty(Double.parseDouble("" + dataRow.elementAt(9)));
	    selectedVoucherDetail.setPrice(Double.parseDouble("" + dataRow.elementAt(10)));
	    selectedVoucherDetail.setPartialWOTaxes(Double.parseDouble("" + dataRow.elementAt(11)));
	    selectedVoucherDetail.setTaxes(Double.parseDouble("" + dataRow.elementAt(12)));
	    selectedVoucherDetail.setPartialWTaxes(Double.parseDouble("" + dataRow.elementAt(13)));
	    selectedVoucherDetail.setVATAmount(Double.parseDouble("" + dataRow.elementAt(14)));
	    selectedVoucherDetail.setVATp(Double.parseDouble("" + dataRow.elementAt(15)));
	    selectedVoucherDetail.setAmount(Double.parseDouble("" + dataRow.elementAt(16)));
	    selectedVoucherDetail.setDescription("" + dataRow.elementAt(17));
	} else {
	}
    }
    
    private void loadObjectEncabezado(){
        voucher = new Voucher();
        voucher.setIdVoucher(Integer.parseInt("" + dataRow.elementAt(1)));
        voucher.retrieveData();
    }

    private void loadVoucherDetailMgmt() {
	voucherDetailMgmt = new VoucherDetailMgmt();
	voucherDetailMgmt.setVoucherDetail(selectedVoucherDetail);
	//voucherDetailMgmt.setParentList(this);
	voucherDetailMgmt.setVouchersList(parentList);
	ExtendedInternalFrame voucherDetailMgmtContainer;
	if (selectedVoucherDetail.getIdVoucherDetail() != -1) {
	    voucherDetailMgmtContainer = new ExtendedInternalFrame("Modificar ítem");
	} else {
	    voucherDetailMgmtContainer = new ExtendedInternalFrame("Agregar nuevo item");
	}
	voucherDetailMgmtContainer.setCentralPanel(voucherDetailMgmt);
	voucherDetailMgmtContainer.show();
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	parentList.refresh();
	getParentInternalFrame().close();
    }

    public void setParentList(VouchersList parentList) {
	this.parentList = parentList;
    }
    
    private void recalcularCantidad(){
        if (tfPrecioUnitario.getAmount() != 0.0) {
            tfCantidad.setValue(Math.abs(tfTotal.getAmount()/tfPrecioUnitario.getAmount()));
        } else {
            Advisor.messageBox("El precio unitario no puede ser cero.", "Error");
            recalcularPrecioUnitario();
        }
    }
    
    private void recalcularPrecioUnitario(){

        if (tfCantidad.getDouble() != 0.0) {
            tfPrecioUnitario.setValue(Math.abs(tfTotal.getAmount()/tfCantidad.getDouble()));
        } else {
            Advisor.messageBox("La cantidad ingresada no puede ser cero.", "Error");
            recalcularCantidad();
        }
        
    }

    private void btnAsignarCantidad_actionPerformed(ActionEvent e) {
        recalcularPrecioUnitario();
    }

    private void btnAsignarPrecioUnit_actionPerformed(ActionEvent e) {
        recalcularCantidad();
    }

    private void btnGrabarDetalle_actionPerformed(ActionEvent e) {
        corregirDetalle();
    }

    private void btnGrabarEncabezado_actionPerformed(ActionEvent e) {
        corregirEncabezado();
    }
    
    private void corregirDetalle(){
        if (Advisor.question("Pregunta", "¿Está seguro de corregir el detalle de la factura?")) {
            String params = "" + selectedVoucherDetail.getIdVoucherDetail() + "," + tfCantidad.getDouble() + "," + tfPrecioUnitario.getAmount();
            if (LibSQL.getBoolean("cashflow.corregirDetalleFactura",""  +  params)) {
                Advisor.messageBox("Se grabó con éxito el detalle de la factura", "Aviso");
                //refresh();
            } else {
                Advisor.messageBox("Ocurrió un error al grabar los datos.", "Error");
            }    
        }
    }
    
    private void corregirEncabezado(){
        if (Advisor.question("Pregunta", "¿Está seguro de corregir el encabezado de la factura?")) {
            if (tfNumFactura.getValue().toString().length() == 13 && !tfNumFactura.getValue().toString().startsWith("0000-") && !tfNumFactura.getValue().toString().equals("0000-00000000") && !tfNumFactura.getValue().toString().equals("0") )  {
                String params = "" + voucher.getIdVoucher() + ",'" + tfNumFactura.getValue() + "','" + tfDescripcion.getValue() +"'";
                if (LibSQL.getBoolean("cashflow.corregirEncabezadoFactura",""  +  params)) {
                    Advisor.messageBox("Se grabó con éxito el encabezado de la factura", "Aviso");
                    //refresh();
                } else {
                    Advisor.messageBox("Ocurrió un error al grabar los datos.", "Error");
                }           
            } else {
                Advisor.messageBox("Debe ingresar un numero de factura con formato válido en el campo Nº Factura.", "Error");
            }    
        }
    }
}
