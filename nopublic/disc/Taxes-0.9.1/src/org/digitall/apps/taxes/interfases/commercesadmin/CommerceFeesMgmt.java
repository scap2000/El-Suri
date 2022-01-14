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
 * CommerceFeesMgmt.java
 *
 * */
package org.digitall.apps.taxes.interfases.commercesadmin;

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

import java.util.Vector;

import org.digitall.apps.taxes.classes.BoletaActVarias;
import org.digitall.apps.taxes.classes.Commerce;
import org.digitall.apps.taxes.classes.CuotaActVarias;
import org.digitall.apps.taxes.classes.DetalleBoletaActVarias;
import org.digitall.apps.taxes.interfases.TaxesCommerce;
import org.digitall.apps.taxes.interfases.TaxesTGS;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.image.LibIMG;
import org.digitall.lib.misc.BarCode;
import org.digitall.lib.sql.LibSQL;

public class CommerceFeesMgmt extends BasicPrimitivePanel {

    private BasicPanel jpCabecera = new BasicPanel();
    private BasicPanel jpDetalle = new BasicPanel();
    private BasicPanel jpMontoTotal = new BasicPanel();
    private BasicPanel content = new BasicPanel();
    private BasicPanel jpAgregarActividad = new BasicPanel();
    
    private BorderLayout borderLayout1 = new BorderLayout();
    private BorderLayout borderLayout2 = new BorderLayout();
    
    private TFInput tfVencimiento = new TFInput(DataTypes.DATE, "ExpirationDate", false);
    private TFInput tfRazonSocial = new TFInput(DataTypes.STRING, "TradeName", false);
    private TFInput tfPadron = new TFInput(DataTypes.STRING, "PollTax", false);
    private TFInput tfBuscar = new TFInput(DataTypes.STRING, "SearchButton", false);
    private TFInput tfMonto = new TFInput(DataTypes.DOUBLE, "Amount/Unit", false);
    private TFInput tfResponsable = new TFInput(DataTypes.STRING,"PersonCharge",false);
    private TFInput tfDni = new TFInput(DataTypes.STRING,"DNI",false);
    private TFInput tfDireccion = new TFInput(DataTypes.STRING,"Address",false);
    private TFInput tfAnticipo = new TFInput(DataTypes.STRING,"Advance",false);
    private TFInput tfAlicuota = new TFInput(DataTypes.PERCENT,"Alicuota",false);
    private TFInput tfMontoBase = new TFInput(DataTypes.MONEY,"BaseAmount",false);
    private TFInput tfMontoAlicuota = new TFInput(DataTypes.MONEY,"AmountxAlicuota",false);
    private TFInput tfMontoTotal = new TFInput(DataTypes.MONEY, "TotalAmount", false);
    private TFInput tfTasa = new TFInput(DataTypes.DOUBLE,"InterestRate",false);
    private TFInput tfDiasVencidos = new TFInput(DataTypes.INTEGER,"Days",false);
    private TFInput tfInteres = new TFInput(DataTypes.MONEY,"Interest",false);
    private TFInput tfSubTotal = new TFInput(DataTypes.MONEY,"PartialAmount",false);

    private int[] sizeColumnList = { 338, 93, 64, 104 };
    private Vector dataRow = new Vector();
    private GridPanel panelActividades = new GridPanel(50000, sizeColumnList, "", dataRow) {

	    public void calculate() {
                table.selectAll();
		if (table.getSelectedRow() > -1) {
		    
		    double subtotal = 0.0;
                    //panelActividades.selectAllItems(true);
		    Vector vector = (Vector) panelActividades.getSelectedsVector();
		    if (panelActividades.getSelectedsVector().size() > 1)  {
		        for (int i = 0 ;i <= panelActividades.getSelectedsVector().size() -1 ; i++)  {
		            Vector aux = (Vector)vector.elementAt(i);
		            subtotal += Double.parseDouble(aux.elementAt(9).toString());
		        }    
		        tfMontoTotal.setValue(tfInteres.getAmount() + subtotal);
		    }  else if (panelActividades.getSelectedsVector().size() == 1) {
                        Vector aux = (Vector)vector.elementAt(0);
                        subtotal += Double.parseDouble(aux.elementAt(9).toString());
                        tfMontoTotal.setValue(tfInteres.getAmount() + subtotal);
                    } else {
                        tfMontoTotal.setValue(0.0);
                    }
		    tfSubTotal.setValue(subtotal);
		}
	    }

	}
    ;
    private Vector headerList = new Vector();
    
    private CBInput cbActividad = new CBInput(0, "GainfulEmployment", false);

    private AssignButton btnAddActividad = new AssignButton(true);
    private SaveDataButton btnSave = new SaveDataButton();
    private CloseButton btnClose = new CloseButton();
    private DeleteButton btnDelete = new DeleteButton();
    
    private BoletaActVarias boletaActVarias = new BoletaActVarias();
    private DetalleBoletaActVarias detalleBoletaActVarias = new DetalleBoletaActVarias();
    private TaxesCommerce parentMain;
    private Commerce comercio;
    private CuotaActVarias cuotaActVarias;
    
    private double valorpormil = 0.0;
    private double valormodulo = 0.0;
    private int multiplicador = 0;
    private String fijo = "f";
    private double montofijo = 0.0;
    
    private PrintButton btnPrint = new PrintButton();

    public CommerceFeesMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(662, 496));
	this.setPreferredSize(new Dimension(590, 500));
        this.add(content, null);
	jpCabecera.setPreferredSize(new Dimension(10, 115));
	jpCabecera.setLayout(null);
	jpDetalle.setLayout(borderLayout1);
	jpMontoTotal.setPreferredSize(new Dimension(10, 45));
	jpMontoTotal.setLayout(null);
        jpMontoTotal.setBounds(new Rectangle(0, 461, 662, 45));
        tfVencimiento.setBounds(new Rectangle(560, 65, 90, 35));
	tfRazonSocial.setBounds(new Rectangle(10, 25, 225, 35));
	jpAgregarActividad.setPreferredSize(new Dimension(10, 90));
	jpAgregarActividad.setLayout(null);
        tfBuscar.setBounds(new Rectangle(10, 0, 110, 35));
        tfMonto.setBounds(new Rectangle(10, 40, 120, 35));
	cbActividad.setBounds(new Rectangle(130, 0, 450, 35));
	btnAddActividad.setBounds(new Rectangle(585, 55, 20, 20));
	btnAddActividad.setSize(new Dimension(20, 20));
	btnAddActividad.addActionListener(new ActionListener() {

					      public void actionPerformed(ActionEvent e) {
						  btnAddActividad_actionPerformed(e);
					      }

					  }
	);
	btnSave.setBounds(new Rectangle(620, 10, 28, 33));
	btnSave.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnSave_actionPerformed(e);
			       }

			   }
	);
	btnClose.setBounds(new Rectangle(670, 10, 20, 20));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
        jpCabecera.add(tfAnticipo, null);
        jpCabecera.add(tfDireccion, null);
        jpCabecera.add(tfDni, null);
        jpCabecera.add(tfResponsable, null);
        jpCabecera.add(tfRazonSocial, null);
        jpCabecera.add(tfVencimiento, null);
        jpCabecera.add(tfPadron, null);
        jpAgregarActividad.add(tfMontoAlicuota, null);
        jpAgregarActividad.add(tfMontoBase, null);
        jpAgregarActividad.add(tfAlicuota, null);
        jpAgregarActividad.add(btnDelete, null);
        jpAgregarActividad.add(btnAddActividad, null);
        jpAgregarActividad.add(cbActividad, null);
        jpAgregarActividad.add(tfMonto, null);
        jpAgregarActividad.add(tfBuscar, null);
        jpDetalle.add(jpAgregarActividad, BorderLayout.NORTH);
        jpDetalle.add(panelActividades, BorderLayout.CENTER);
        jpMontoTotal.add(tfSubTotal, null);
        jpMontoTotal.add(tfInteres, null);
        jpMontoTotal.add(tfDiasVencidos, null);
        jpMontoTotal.add(tfTasa, null);
        content.setLayout(borderLayout2);
        btnDelete.setBounds(new Rectangle(620, 55, 20, 20));
        btnDelete.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnDelete_actionPerformed(e);
                }
            }
        );
        content.add(jpCabecera, BorderLayout.NORTH);
        content.add(jpDetalle, BorderLayout.CENTER);
        content.add(jpMontoTotal, BorderLayout.SOUTH);
        addButton(btnClose);
        addButton(btnSave);
        cbActividad.autoSize();
        tfBuscar.getTextField().addKeyListener(new KeyAdapter() {

                public void keyTyped(KeyEvent e) {
                    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                        loadComboActividades();
                        calcularMontos();
                    }
                }
            }
        );
        setHeaderList();
        tfVencimiento.setValue(Proced.setFormatDate(Environment.currentDate, true));
        btnSave.setToolTipText("Grabar datos");
        btnClose.setToolTipText("Cancelar asiento");
        btnDelete.setToolTipText("Borrar un ítem del asiento");
        tfPadron.setBounds(new Rectangle(240, 25, 80, 35));
        btnAddActividad.setToolTipText("Agregar un ítem al asiento");
        tfResponsable.setBounds(new Rectangle(330, 25, 225, 35));
        tfDni.setBounds(new Rectangle(560, 25, 90, 35));
        tfDireccion.setBounds(new Rectangle(10, 65, 310, 35));
        tfMontoTotal.setBounds(new Rectangle(535, 5, 115, 35));
        tfMonto.getTextField().addKeyListener(new KeyAdapter() {

                public void keyTyped(KeyEvent e) {
                    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                        if (Integer.parseInt(cbActividad.getSelectedValue().toString())  > 0) {
                            calcularMontos();
                        } else {
                            Advisor.messageBox("No selecciono ninguna Actividad", "Aviso");
                        }
                    }
                }
            }
        );
        jpMontoTotal.add(tfMontoTotal, null);
	btnDelete.setEnabled(false);
        tfSubTotal.setBounds(new Rectangle(355, 5, 115, 35));
        tfInteres.setBounds(new Rectangle(240, 5, 100, 35));
        tfDiasVencidos.setBounds(new Rectangle(125, 5, 100, 35));
        tfTasa.setBounds(new Rectangle(10, 5, 100, 35));
        jpCabecera.setBorder(BorderPanel.getBorderPanel(Environment.lang.getProperty("MainData")));
        jpCabecera.setBounds(new Rectangle(0, 0, 662, 115));
        jpCabecera.setSize(new Dimension(662, 125));
        jpDetalle.setBorder(BorderPanel.getBorderPanel("Agregar Actividad"));
        jpDetalle.setBounds(new Rectangle(0, 145, 662, 290));
        tfRazonSocial.setEnabled(false);
        tfPadron.setEnabled(false);
        tfResponsable.setEnabled(false);
        tfDni.setEnabled(false);
        tfDireccion.setEnabled(false);
        tfAnticipo.setEnabled(false);
        tfVencimiento.setEnabled(false);
        tfAnticipo.setBounds(new Rectangle(330, 65, 225, 35));
        tfAlicuota.setBounds(new Rectangle(175, 40, 110, 35));
        tfMontoBase.setBounds(new Rectangle(470, 40, 110, 35));
        tfVencimiento.setEnabled(false);
        tfAlicuota.setEnabled(false);
        tfMontoBase.setEnabled(false);
        tfMontoAlicuota.setEnabled(false);
        tfMontoAlicuota.setBounds(new Rectangle(325, 40, 120, 35));
        cbActividad.getCombo().addItemListener(new ItemListener() {

                                             public void itemStateChanged(ItemEvent evt) {
                                                 if (evt.getStateChange() == ItemEvent.SELECTED) {
                                                     
                                                     calcularMontos();
                                                 }
                                             }

                                         }
        );
        tfTasa.setEnabled(false);
        tfDiasVencidos.setEnabled(false);
        tfInteres.setEnabled(false);
        tfSubTotal.setEnabled(false);
        tfMontoTotal.setEnabled(false);
        btnPrint.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnPrint_actionPerformed(e);
                }
            }
        );
        addButton(btnPrint);
        btnPrint.setVisible(false);
    }


    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	//getParentInternalFrame().setInfo("Complete los datos y presione el botón \"Grabar datos\". Recuerde que el asiento debe estar balanceado");
	 getParentInternalFrame().setInfo("");
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");     // iddetalleboletaactvarias
	headerList.addElement("*");     // idboletaactvarias
	headerList.addElement("*");     // idactividad
	headerList.addElement("Actividad");
        headerList.addElement("*");     // fechavto
        headerList.addElement("*");    // dias
	headerList.addElement(Environment.lang.getProperty("Amount/Unit"));
	headerList.addElement("alícuota");
        headerList.addElement("*");     // deducciones
	headerList.addElement(Environment.lang.getProperty("TotalAmount"));
	headerList.addElement("*");     // estado
	headerList.addElement("*");     // fechaimpresion
	headerList.addElement("*");     // fechapago
        
	panelActividades.getTable().addMouseListener(new MouseAdapter() {

								 public void mouseClicked(MouseEvent e) {
								    btnDelete.setEnabled(true);
                                                                    loadItemDetalle();
								     if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {

								     } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON3) {
									 int index = panelActividades.getTable().rowAtPoint(e.getPoint());
									 panelActividades.getTable().getSelectionModel().setSelectionInterval(index, index);
								     }
								 }

							     }
	);
	panelActividades.setParams("taxes.getDetalleBoletaActVarias", "-1", headerList);
    }

    private void loadItemDetalle() {
        detalleBoletaActVarias.setIddetalleboletaactvarias(Integer.parseInt(dataRow.get(0).toString()));
    }
 
    public void refresh() {
	String params = "" + boletaActVarias.getIdboletaactvarias();
        panelActividades.refresh(params);
        btnDelete.setEnabled(false);
        panelActividades.selectAllItems(true);
        panelActividades.selectAllItems(true);
        panelActividades.calculate();
        
    }

    public boolean addActividad() {
	// 1) dar de alta la boleta
        boolean valor = false;
        if (boletaActVarias.getIdboletaactvarias() == -1) {
            boletaActVarias.setIdcomercio(cuotaActVarias.getIdcomercio());
            boletaActVarias.setContribuyente(tfResponsable.getString());
            boletaActVarias.setPadron(tfPadron.getString());
            boletaActVarias.setDni(tfDni.getString());
            boletaActVarias.setRubro(comercio.getRubro());
            boletaActVarias.setRazonsocial(tfRazonSocial.getString());
            boletaActVarias.setExpte(comercio.getExpte());
            boletaActVarias.setNrocuenta(comercio.getNroCuenta());
            boletaActVarias.setConcepto(cuotaActVarias.getNumber() +" / "+ cuotaActVarias.getYear());
            boletaActVarias.setImporte(0);
            boletaActVarias.setRecargo(tfInteres.getAmount());
            boletaActVarias.setDeducciones(0);
            boletaActVarias.setTotal(0);
            boletaActVarias.setDomicilio(comercio.getDomicilio());
            if (boletaActVarias.saveData() > 0)  {
                valor = true;
            } else  {
                Advisor.messageBox("Ocurrió un error al grabar el encabezado de la Boleta","Error");
            }
            
        }
        double monto = 0.0;
        if ( tfMontoAlicuota.getAmount() > tfMontoBase.getAmount() )  {
            monto = tfMontoAlicuota.getAmount();
        } else  {
            monto = tfMontoBase.getAmount();
        }
        
        // 2) Agregar detalle de la boleta
        if (detalleBoletaActVarias.getIddetalleboletaactvarias() == -1)  {
            detalleBoletaActVarias.setIdboletaactvarias(boletaActVarias.getIdboletaactvarias());
            detalleBoletaActVarias.setIdactividad(Integer.parseInt(cbActividad.getSelectedValue().toString()));
            detalleBoletaActVarias.setActividad(cbActividad.getSelectedItem().toString());
            detalleBoletaActVarias.setMonto(tfMonto.getAmount());
            detalleBoletaActVarias.setAlicuota(Double.parseDouble(tfAlicuota.getValue().toString()));
            detalleBoletaActVarias.setDias(Integer.parseInt(tfDiasVencidos.getString()));
            detalleBoletaActVarias.setMontototal(monto);
            detalleBoletaActVarias.setMontoxAlicuota(tfMontoAlicuota.getAmount());
            detalleBoletaActVarias.setMontoBase(tfMontoBase.getAmount());
            detalleBoletaActVarias.setValorModulo(valormodulo);
            detalleBoletaActVarias.setMultiplicador(multiplicador);
            
            if (detalleBoletaActVarias.saveData() > 0) {
                    valor = true;
                    refresh();
                    detalleBoletaActVarias.setIddetalleboletaactvarias(-1);
            } else {
                Advisor.messageBox("Ocurrio un error al regisrar la Actividad","Error");
            }
        } else  {
            Advisor.messageBox("Ocurrió un error","Error");
        }
        return valor;
    }


    private void btnAddActividad_actionPerformed(ActionEvent e) {
        if (Integer.parseInt(cbActividad.getSelectedValue().toString()) > 0 )  {
            addActividad();
        } else  {
            Advisor.messageBox("Debe seleccionar una Actividad", "Error");
        }
    }

    private void btnSave_actionPerformed(ActionEvent e) {
	saveData();
    }

    public boolean saveData() {
        if (tfMontoTotal.getAmount() > 0 )  {
            if (Advisor.question("Aviso", "¿Está seguro de generar la boleta por un monto de $ "+ Format.toDouble(tfMontoTotal.getAmount()) +"?")) {
                if (LibSQL.getInt("taxes.updateBoletaActVarias",""+ cuotaActVarias.getIdcomercio() +","+  boletaActVarias.getIdboletaactvarias() +","+ cuotaActVarias.getNumber() +","+ cuotaActVarias.getYear() ) > 0)  {

                    boletaActVarias.retrieveData();
                    BasicReport report = new BasicReport(TaxesTGS.class.getResource("xml/ActVariasVoucher.xml"));
                    String param = ""+ boletaActVarias.getIdboletaactvarias();
                    report.addTableModel("taxes.getDetalleBoletaActVarias", param);
                    BarCode code = new BarCode();
                    report.setProperty("barcode", code.getBarCodeEAN13(boletaActVarias.getBarCode()));
                    report.setProperty("anio", boletaActVarias.getAnio());
                    report.setProperty("numero", boletaActVarias.getNumero()); 
                    report.setProperty("razonsocial", boletaActVarias.getRazonsocial());
                    report.setProperty("contribuyente", boletaActVarias.getContribuyente());
                    report.setProperty("domicilio", boletaActVarias.getDomicilio());
                    report.setProperty("nrocuenta", boletaActVarias.getNrocuenta());
                    report.setProperty("padron", boletaActVarias.getPadron());
                    report.setProperty("montototal", boletaActVarias.getTotal());
                    report.setProperty("concepto", boletaActVarias.getConcepto());
                    report.setProperty("importe", boletaActVarias.getImporte());
                    report.setProperty("recargo", boletaActVarias.getRecargo());
                    report.setProperty("deduccion", boletaActVarias.getDeducciones());
                    report.setProperty("nombredescuento", boletaActVarias.getNombredescuento());
                    report.setProperty("montoletras", "Son pesos "+ Format.NumberToText.numberToText(tfMontoTotal.getAmount()) +" .-");
                    report.doReport(); 
                    parentMain.getCuotas();
                    getParentInternalFrame().close();
                    
                } else  {
                    Advisor.messageBox("Ocurrió un error al grabar la Boleta","Error");
                }
            }    
        } else  {
            Advisor.messageBox("El campo \"($) Monto Total\" debe ser superior a cero", "Aviso");
        }
        
         
	return false;
    }

    private void clearData() {
	tfBuscar.setValue("");
	tfMonto.setValue(0.0);
        tfMontoTotal.setValue(0.0);
        tfMontoAlicuota.setValue(0.0);
        tfMontoBase.setValue(0.0);
        detalleBoletaActVarias.setIddetalleboletaactvarias(-1);
    }

    private void cancelarBoleta() {
	if (Advisor.question("Anular Boleta", "¿Desea anular la Boleta?.")) {
	    boletaActVarias.delBoletaActVarias();
	    clearData();
	    refresh();
	    getParentInternalFrame().close();
	}
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	//tfAvailableAmount.setBackground(Color.GREEN.brighter());
	if (boletaActVarias != null) {
	    cancelarBoleta();
	} else {
	    getParentInternalFrame().close();
	}
    }

    private void btnDelete_actionPerformed(ActionEvent e) {
	if (dataRow.size() != 0 && detalleBoletaActVarias.getIddetalleboletaactvarias() != -1) {
            if(Advisor.question("Aviso","Está suguro de borrar el ítem seleccionado?")){
                if (detalleBoletaActVarias.deleteItem() > 0) {
                    clearData();
                    refresh();
                } else {
                    Advisor.messageBox("Ocurrió un error al eliminar el ítem seleccionado","Error");
                }
            }
	    
	} else {
	    Advisor.messageBox("Debe seleccionar el ítem que desea borrar ", "Error");
	}
    }

    public void setParentMain(TaxesCommerce _parentMain) {
        parentMain = _parentMain;
    }

    public void setComercio(Commerce _comercio) {
        comercio = _comercio;
        if (comercio.getIdpadron() > 0)  {
            tfRazonSocial.setValue(comercio.getRazonsocial());
            tfPadron.setValue(comercio.getPadron());
            tfResponsable.setValue(comercio.getContribuyente());
            tfDni.setValue(comercio.getDni());
            tfDireccion.setValue(comercio.getDomicilio());
        } else  {
            System.out.println("error");
        }
        
    }

    public void setCuotaActVarias(CuotaActVarias _cuotaActVarias) {
        cuotaActVarias = _cuotaActVarias;
        if (cuotaActVarias.getNumber() > 0) {
            tfAnticipo.setValue(cuotaActVarias.getNumber() +"º ANTICIPO DEL AÑO "+ cuotaActVarias.getYear());
            tfVencimiento.setValue(Proced.setFormatDate(cuotaActVarias.getExpiration(), true));
            loadComboActividades();
            double interes = cuotaActVarias.getInteresMensual();
            tfTasa.setValue(interes);
            tfDiasVencidos.setValue(cuotaActVarias.getDiasMora());
            tfInteres.setValue(((interes * 12.0)/365.0) * cuotaActVarias.getDiasMora());
        }
    }

    private void loadComboActividades() {
        String param = "'"+ cuotaActVarias.getDate() +"','" + tfBuscar.getString() + "'";
        cbActividad.getCombo().loadJCombo(LibSQL.exFunction("taxes.getAllActividades", param));
    }
    
    private void calcularMontos(){
        String cadena = cbActividad.getSelectedValueRef().toString();
        valorpormil = Double.parseDouble(cadena.substring(cadena.indexOf("#")+1,cadena.indexOf("@")));
        valormodulo = Double.parseDouble(cadena.substring(cadena.indexOf("@")+1,cadena.indexOf("$")));
        multiplicador = Integer.parseInt(cadena.substring(cadena.indexOf("$")+1, cadena.indexOf("&")));
        fijo = cadena.substring(cadena.indexOf("&")+1,cadena.indexOf("%"));
        montofijo = Double.parseDouble(cadena.substring(cadena.indexOf("%")+1,cadena.length()));
        double monto = tfMonto.getAmount();
        if (fijo.equals("f"))  {
            tfAlicuota.setValue(valorpormil/10.0); 
            tfMontoAlicuota.setValue(monto * (valorpormil/1000.0));
            tfMontoBase.setValue(multiplicador * valormodulo);    
        } else  {
            tfAlicuota.setValue(montofijo); 
            tfMontoAlicuota.setValue(monto * montofijo);
            tfMontoBase.setValue(0);
            valormodulo = 0.0;
            multiplicador = 0;
        }
    }
    
    private void btnPrint_actionPerformed(ActionEvent e) {
        boletaActVarias = new BoletaActVarias(); 
        boletaActVarias.setIdboletaactvarias(10);
        boletaActVarias.retrieveData();
        BasicReport report = new BasicReport(TaxesTGS.class.getResource("xml/ActVariasVoucher.xml"));
        String param = ""+ boletaActVarias.getIdboletaactvarias();
        report.addTableModel("taxes.getDetalleBoletaActVarias", param);
        BarCode code = new BarCode();
        System.out.println("barcode: "+ boletaActVarias.getBarCode());
        report.setProperty("barcode", code.getBarCodeEAN13(boletaActVarias.getBarCode()));
        report.setProperty("anio", boletaActVarias.getAnio());
        report.setProperty("numero", boletaActVarias.getNumero()); 
        report.setProperty("razonsocial", boletaActVarias.getRazonsocial());
        report.setProperty("contribuyente", boletaActVarias.getContribuyente());
        report.setProperty("domicilio", boletaActVarias.getDomicilio());
        report.setProperty("nrocuenta", comercio.getNroCuenta());
        report.setProperty("padron", boletaActVarias.getPadron());
        report.setProperty("montototal", boletaActVarias.getTotal());
        report.setProperty("concepto", boletaActVarias.getConcepto());
        report.setProperty("importe", boletaActVarias.getImporte());
        report.setProperty("recargo", boletaActVarias.getRecargo());
        report.setProperty("deduccion", boletaActVarias.getDeducciones());
        report.setProperty("nombredescuento", boletaActVarias.getNombredescuento()); 
        report.setProperty("montoletras", "Son pesos "+ Format.NumberToText.numberToText(1700.86) +" .-");
        report.doReport(); 
    }
    
}
