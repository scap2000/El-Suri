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
 * PayTaxesVoucher.java
 *
 * */
package org.digitall.apps.taxes.interfases;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class PayTaxesVoucher extends BasicPrimitivePanel {

    private BasicPanel content = new BasicPanel();
    private BasicPanel searchPanel = new BasicPanel();
    private BasicPanel footerPanel = new BasicPanel();

    private TFInput tfFecha = new TFInput(DataTypes.DATE, "Date", false);
    private TFInput tfNroComprobante = new TFInput(DataTypes.STRING, "VoucherNumber", false);
    private TFInput tfCodigoBarra = new TFInput(DataTypes.STRING, "Barcode", false);
    
    private TFInput tfAmount = new TFInput(DataTypes.MONEY, "TotalAmount", false);
    private TFInput tfPartialWOTaxes = new TFInput(DataTypes.MONEY, "PartialWOTaxes", false);
    private TFInput tfTaxes = new TFInput(DataTypes.MONEY, "Taxes", false);
    private TFInput tfDeductionAmount = new TFInput(DataTypes.MONEY, "DeductionAmount", false);
    private TFInput tfVATAmount = new TFInput(DataTypes.MONEY, "VATAmount", false);
    
    private FindButton btnFind = new FindButton();
    private SaveDataButton btnSave = new SaveDataButton();
    private CloseButton btnClose = new CloseButton();

    private CBInput cbTaxes = new CBInput(0,"TaxesType",false);
    
    private int[] sizeColumnList = {100, 112, 82, 138, 92, 119 , 82, 82, 95, 75};
    private Vector vouchersHeader = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel vouchersPanel = new GridPanel(50000, sizeColumnList, "Comprobantes", dataRow);
    private String selectedIdVoucher;

    private BorderLayout borderLayout1 = new BorderLayout();
    
    private int error = 0;

    public PayTaxesVoucher() {
	try {

	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(900, 500));
	this.setPreferredSize(new Dimension(900, 500));
	this.setTitle("Nueva Factura");
	content.setBounds(new Rectangle(5, 0, 695, 700));
	content.setLayout(borderLayout1);
	content.setSize(new Dimension(870, 548));
	tfNroComprobante.setBounds(new Rectangle(485, 25, 150, 35));
	tfFecha.setBounds(new Rectangle(375, 25, 85, 35));
	tfAmount.setBounds(new Rectangle(498, 15, 125, 35));
	cbTaxes.setBounds(new Rectangle(10, 25, 340, 35));
	btnSave.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnSave_actionPerformed(e);
				 }

			     }
	);
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	tfCodigoBarra.setBounds(new Rectangle(655, 25, 185, 35));
	searchPanel.setLayout(null);
	searchPanel.setPreferredSize(new Dimension(970, 70));
	vouchersPanel.setSize(new Dimension(970, 315));
	footerPanel.setLayout(null);
	footerPanel.setPreferredSize(new Dimension(970, 60));
        tfPartialWOTaxes.setBounds(new Rectangle(10, 15, 125, 35));
	tfTaxes.setBounds(new Rectangle(152, 15, 95, 35));
	tfDeductionAmount.setBounds(new Rectangle(264, 15, 125, 35));
	tfVATAmount.setBounds(new Rectangle(406, 15, 75, 35));
        footerPanel.add(tfPartialWOTaxes, null);
	footerPanel.add(tfVATAmount, null);
	footerPanel.add(tfDeductionAmount);
	footerPanel.add(tfTaxes, null);
	footerPanel.add(tfAmount, null);
        searchPanel.add(btnFind, null);
        searchPanel.add(cbTaxes, null);
        searchPanel.add(tfCodigoBarra, null);
        searchPanel.add(tfFecha, null);
        searchPanel.add(tfNroComprobante, null);
        content.add(searchPanel, BorderLayout.NORTH);
	content.add(footerPanel, BorderLayout.SOUTH);
	content.add(vouchersPanel, BorderLayout.CENTER);
	this.add(content, null);
	this.addButton(btnClose);
	this.addButton(btnSave);
	cbTaxes.autoSize();
	tfFecha.setValue(Proced.setFormatDate(Environment.currentDate, true));
	
	tfTaxes.getTextField().addKeyListener(new KeyAdapter() {

					   public void keyTyped(KeyEvent e) {
					       if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
						   calculateTotal();
					       }
					   }

				       }
	);
	
	tfAmount.setEnabled(false);
	tfPartialWOTaxes.setEnabled(false);
        tfTaxes.setEnabled(false);
	tfDeductionAmount.setEnabled(false);
	tfVATAmount.setEnabled(false);
	btnClose.setEnabled(false);
        btnFind.setBounds(new Rectangle(855, 30, 35, 30));
        btnSave.setToolTipText("Grabar Factura");
	btnClose.setToolTipText("Cancelar Factura");
        searchPanel.setBorder(BorderPanel.getBorderPanel(("Datos de la Factura")));
        setCadastralHeader();
        searchPanel.setBorder(BorderPanel.getBorderPanel("Buscar Comprobante"));
        cbTaxes.autoSize();
        loadComboTaxes();
        tfFecha.setValue(Proced.setFormatDate(Environment.currentDate, true));
        btnFind.addActionListener(new ActionListener() {

                                  public void actionPerformed(ActionEvent e) {
                                      btnFind_actionPerformed(e);
                                  }

                              }
        );
        tfNroComprobante.getTextField().addKeyListener(new KeyAdapter() {
                    public void keyReleased(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                            refresh();
                    }
                });
        tfVATAmount.setVisible(false);
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
    }

    private void setCadastralHeader() {
	vouchersHeader.removeAllElements();
        vouchersHeader.addElement("*");         // idboletatgs
        vouchersHeader.addElement("Nro. Comp.");
        vouchersHeader.addElement("Impuesto");
        vouchersHeader.addElement("*");         // idaccountingentry
        vouchersHeader.addElement("*");         // fechapago
        vouchersHeader.addElement("Fecha");
        vouchersHeader.addElement("Contribuyente");
        vouchersHeader.addElement("Identificador");
        vouchersHeader.addElement("*");         //nro. cuenta
        vouchersHeader.addElement("Periodo");
        vouchersHeader.addElement("*");         //vencimiento
        vouchersHeader.addElement("Importe");   
        vouchersHeader.addElement("Recargo");
        vouchersHeader.addElement("Deducciones");
        vouchersHeader.addElement("Total");
        vouchersHeader.addElement("*");         // barcode
        vouchersHeader.addElement("*");         // informacion
        vouchersHeader.addElement("*");         // idcat./idcomercio/iddominio/etc..

        vouchersPanel.getTable().addMouseListener(new MouseAdapter() {
        
                                              public void mouseClicked(MouseEvent e) {
                                                  if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                                                        calcTotalAmount();
                                                  } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {

                                                  }
                                              }
        
                                          }
        );
        
        String params = cbTaxes.getSelectedValue() +",'"+ Proced.setFormatDate(tfFecha.getString(), false) +"','" + tfNroComprobante.getString() + "','" + tfCodigoBarra.getString() +"'";
        vouchersPanel.setParams("taxes.getAllBoletas", params, vouchersHeader);
    }

    private void calcTotalAmount(){
        double totalIntereses = 0;
        double totalValor = 0;
        double totalDescuento = 0;
        double amount = 0;
        Vector vector = (Vector)vouchersPanel.getSelectedsVector();
        if (vouchersPanel.getSelectedsVector().size() > 1)  {
            for (int i = 0 ;i <= vouchersPanel.getSelectedsVector().size() -1 ; i++)  {
                Vector aux = (Vector)vector.elementAt(i);
                totalValor += Double.parseDouble(aux.elementAt(11).toString());
                totalIntereses += Double.parseDouble(aux.elementAt(12).toString());
                totalDescuento += Double.parseDouble(aux.elementAt(13).toString());
                amount += Double.parseDouble(aux.elementAt(14).toString());
            }    
        } else {
            if (vouchersPanel.getSelectedsVector().size() == 1) {
                Vector aux = (Vector)vector.elementAt(0);
                 totalValor += Double.parseDouble(aux.elementAt(11).toString());
                 totalIntereses += Double.parseDouble(aux.elementAt(12).toString());
                 totalDescuento += Double.parseDouble(aux.elementAt(13).toString());
                 amount += Double.parseDouble(aux.elementAt(14).toString());
            }
        }
        tfPartialWOTaxes.setValue(totalValor);
        tfTaxes.setValue(totalIntereses);
        tfDeductionAmount.setValue(totalDescuento);
        tfAmount.setValue(amount);
    }

    public void refresh() {
        String params = cbTaxes.getSelectedValue() +",'"+ Proced.setFormatDate(tfFecha.getString(), false) +"','"+ tfNroComprobante.getString().trim() +"','"+ tfCodigoBarra.getString().trim() +"'";
        vouchersPanel.refresh(params);
	vouchersPanel.selectAllItems(false);
	tfPartialWOTaxes.setValue(0.0);
	tfTaxes.setValue(0.0);
	tfDeductionAmount.setValue(0.0);
	tfVATAmount.setValue(0.0);
	tfAmount.setValue(0.0);
    }
    
    private void btnSave_actionPerformed(ActionEvent e) {
        //calculateTotal();
        saveData();
    }
    
    public boolean saveData() {
        boolean returns = false;
        calculateTotal();
        if ( control() ) {
            if (Advisor.question("Grabar factura", "¿Está seguro de registrar el Pago de la/s factura/s por un monto de " + tfAmount.getTextField().getText() + "?")) {
                Vector<Vector> values = vouchersPanel.getSelectedsVector();
                if (values.size() > 0) {
                    String barcode = "'";
                    for (int i = 0; i < values.size()-1; i++) {
                        barcode += (values.elementAt(i)).elementAt(15) +"'',''";
                    }
                    barcode += (values.elementAt(values.size()-1)).elementAt(15) + "'";
                    if (LibSQL.getInt("taxes.registrarPagoBoleta",barcode) > 0)  {
                        refresh(); 
                    } else  {
                        Advisor.messageBox("Acurrió un error al grabar los datos!!","Error");
                    }
                }
            }
        } else {
            switch (error)  {
                case 1: Advisor.messageBox("El monto de la Factura no puede ser menor o igual a cero", "Aviso");
                    break;
                case 2: Advisor.messageBox("No hay datos en la Tabla", "Aviso");
                    break;
            }
        }
        return returns;
    }

    private boolean control() {
        boolean result = true;
        if (tfAmount.getAmount() <= 0) {
            error = 1;
            result = false;
        } else if ( vouchersPanel.getTable().getRowCount() == 0 ){
            error = 2;
            result = false;
        }
        return result;
    }

    
    private void btnClose_actionPerformed(ActionEvent e) {
	if (Advisor.question("Cancelar Pago", "¿Está seguro de cancelar el Pago?")) {
	    selectedIdVoucher = "-1";
	    btnClose.setEnabled(false);
	    refresh();
	}
    }

    private void calculateTotal() {
	vouchersPanel.calculate();
	Vector<Vector> values = vouchersPanel.getSelectedsVector();
	if (values.size() > 0) {
	    double amount = 0;
	    double totalValor = 0;
	    double totalDescuento = 0;
	    double totalIntereses = 0;
	    for (int i = 0; i < values.size(); i++) {
	        
	        totalValor += Double.parseDouble("" + (values.elementAt(i)).elementAt(11));
	        totalIntereses += Double.parseDouble("" + (values.elementAt(i)).elementAt(12));
	        totalDescuento += Double.parseDouble("" + (values.elementAt(i)).elementAt(13));
	        amount += Double.parseDouble("" + (values.elementAt(i)).elementAt(14));
	    }
	    tfPartialWOTaxes.setValue(totalValor);
	    tfDeductionAmount.setValue(totalDescuento);
	    tfTaxes.setValue(totalIntereses);
	    tfAmount.setValue(amount);
	} else {
	    tfPartialWOTaxes.setValue(0.0);
	    tfTaxes.setValue(0.0);
	    tfDeductionAmount.setValue(0.0);
	    tfVATAmount.setValue(0.0);
	    tfAmount.setValue(0.0);
	}
    }

    private void loadComboTaxes() {
        String params = "''";
        cbTaxes.loadJCombo("taxes.getAllTaxesTypes",params);
    }
    
    private void btnFind_actionPerformed(ActionEvent e) {
        if (tfFecha.getString().length() > 0) {
            refresh();
        } else {
            Advisor.messageBox("Debe ingresar una Fecha","Aviso");
        }
    }
}
