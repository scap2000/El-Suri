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
 * PayMoratoriumFeesMgmt.java
 *
 * */
package org.digitall.apps.taxes.interfases.feesadmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.math.BigDecimal;

import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.digitall.apps.taxes.classes.BoletaPlanesDePago;
import org.digitall.apps.taxes.classes.Cadastral;
import org.digitall.apps.taxes.classes.Car;
import org.digitall.apps.taxes.classes.Coordinador;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.FirstGridButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.buttons.UnAssignButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class PayMoratoriumFeesMgmt extends BasicPrimitivePanel {

    private BorderLayout borderLayout1 = new BorderLayout();
    private BorderLayout borderLayout2 = new BorderLayout();
    private GridLayout gridLayout1 = new GridLayout();
    
    private BasicPanel content = new BasicPanel();
    private BasicPanel findPanel = new BasicPanel();
    private BasicPanel centralPanel = new BasicPanel();
    private BasicPanel grillaPanel = new BasicPanel();
    private BasicPanel grillaSouthPanel = new BasicPanel();
    
    private BasicPanel southPanel = new BasicPanel();
    private BasicPanel wizardPanel = new BasicPanel();

    private int[] sizeColumnList = { 70 , 150 , 179};
    private Vector dataRow = new Vector();
    private GridPanel listWestPanel = new GridPanel(1000, sizeColumnList, "Cuotas", dataRow) {
	    public void calculate() {
		calcAmountToPay();
	    }
    };
    private Vector headerList = new Vector();
    
    private int[] sizeColumnListEast = { 70 , 150 , 179};
    private Vector dataRowEast = new Vector();
    private GridPanel listEastPanel = new GridPanel(50000, sizeColumnListEast, "Cuotas Pagadas", dataRowEast);
    private Vector headerListEast = new Vector();

    private TFInput tfAmount = new TFInput(DataTypes.MONEY,"Amount",false);
    private TFInput tfContribuyente = new TFInput(DataTypes.STRING,"TaxPayer",false);
    private TFInput tfCatastro = new TFInput(DataTypes.STRING,"Catastro/Dominio",false);
    private TFInput tfDescuentoAplicado = new TFInput(DataTypes.STRING,"Descuento",false);
    private TFInput tfNumPlanDePago = new TFInput(DataTypes.INTEGER,"Nº Plan de Pago",false);

    private AssignButton btnNext = new AssignButton();
    private FirstGridButton btnFirst = new FirstGridButton();
    private UnAssignButton btnBack = new UnAssignButton();
    private SaveDataButton btnSaveData = new SaveDataButton();

    private CBInput cbCantCuotas = new CBInput(0,"Quantity",false);

    private JLabel lblMoratoriaAutomotor = new JLabel();
    private BasicLabel lblMoratoriaCaida = new BasicLabel();

    private BoletaPlanesDePago boletaPlanesDePago;
    private Coordinador coordinador = new Coordinador();
    private Cadastral catastro;
    private Car automotor;

    public PayMoratoriumFeesMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(900, 470));
	this.setPreferredSize(new Dimension(900, 500));
	this.setTitle("Pago Cuotas");
	listWestPanel.setSize(new Dimension(860, 350));
	listWestPanel.setSortable(false);
	listEastPanel.setSize(new Dimension(860, 350));
	grillaSouthPanel.setSize(new Dimension(860, 50));
	btnNext.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnNext_actionPerformed(e);
				 }

			     }
	);
	btnBack.setBounds(new Rectangle(40, 15, 25, 20));
	btnBack.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnBack_actionPerformed(e);
				 }

			     }
	);
	content.setLayout(borderLayout1);
	grillaPanel.setLayout(gridLayout1);
	centralPanel.setLayout(borderLayout2);
	
	findPanel.setLayout(null);
        grillaSouthPanel.setLayout(null);
	southPanel.setLayout(null);
	findPanel.setPreferredSize(new Dimension(860, 75));
        grillaSouthPanel.setPreferredSize(new Dimension(860, 45));
	southPanel.setPreferredSize(new Dimension(860, 50));
        btnSaveData.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnSaveData_actionPerformed(e);
				}

			    }
	);
        tfAmount.setBounds(new Rectangle(310, 5, 130, 35));
	findPanel.add(tfDescuentoAplicado, null);
	findPanel.add(tfCatastro, null);
	findPanel.add(tfContribuyente, null);
	findPanel.add(tfNumPlanDePago, null);
	findPanel.add(lblMoratoriaAutomotor, null);
	grillaPanel.add(listWestPanel,BorderLayout.WEST);
	grillaPanel.add(listEastPanel,BorderLayout.EAST);
	centralPanel.add(grillaPanel,BorderLayout.CENTER);
	grillaSouthPanel.add(lblMoratoriaCaida, null);
	grillaSouthPanel.add(cbCantCuotas, null);
	grillaSouthPanel.add(tfAmount, null);
	centralPanel.add(grillaSouthPanel,BorderLayout.SOUTH);
	content.add(findPanel, BorderLayout.NORTH);
	content.add(centralPanel, BorderLayout.CENTER);
	wizardPanel.add(btnFirst, null);
	wizardPanel.add(btnNext, null);
	wizardPanel.add(btnBack, null);
	southPanel.add(wizardPanel, null);
	content.add(southPanel, BorderLayout.SOUTH);
	this.add(content, null);
	addButton(btnSaveData);
        setHeaderList();
	setHeaderListEast();
	btnNext.setToolTipText("Ir a la pestaña siguiente");
	btnBack.setToolTipText("Volver a la pestaña anterior");
	btnBack.setSize(new Dimension(25, 20));
	btnNext.setBounds(new Rectangle(80, 15, 25, 20));
	btnNext.setSize(new Dimension(25, 20));
	btnSaveData.setToolTipText("Registrar e Imprimir Boleta");
	lblMoratoriaAutomotor.setText("M");
        lblMoratoriaAutomotor.setBounds(new Rectangle(580, 30, 20, 20));
        lblMoratoriaAutomotor.setForeground(Color.red);
        lblMoratoriaAutomotor.setHorizontalAlignment(SwingConstants.CENTER);
        lblMoratoriaAutomotor.setVisible(false);

	cbCantCuotas.setBounds(new Rectangle(10, 5, 100, 35));
	wizardPanel.setBounds(new Rectangle(790, 0, 110, 50));
	wizardPanel.setLayout(null);
	wizardPanel.setBackground(new Color(185, 96, 0));
	wizardPanel.setSize(new Dimension(110, 50));
	tfDescuentoAplicado.setBounds(new Rectangle(625, 20, 255, 35));
	tfContribuyente.setBounds(new Rectangle(30, 20, 270, 35));
	tfNumPlanDePago.setBounds(new Rectangle(480, 20, 105, 35));
	tfCatastro.setBounds(new Rectangle(335, 20, 110, 35));
	findPanel.setBorder(BorderPanel.getBorderPanel("Pago de Cuotas - Moratoria/Plan de pago"));
	grillaSouthPanel.setBorder(BorderPanel.getBorderPanel(""));
	cbCantCuotas.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent evt) {
		    if (evt.getStateChange() == ItemEvent.SELECTED) {
			calcAmountToPay();
		    }
		}

	    }
	);
	lblMoratoriaCaida.setText("¡¡Plan de Pago caido!!");
	lblMoratoriaCaida.setBounds(new Rectangle(450, 5, 445, 35));
	lblMoratoriaCaida.setFont(new Font("Dialog", 1, 25));
	lblMoratoriaCaida.setBackground(Color.RED);
	lblMoratoriaCaida.setHorizontalAlignment(SwingConstants.CENTER);
	lblMoratoriaCaida.setOpaque(true);
	tfAmount.setEnabled(false);
	tfContribuyente.setEditable(false);
	tfNumPlanDePago.setEditable(false);
	tfCatastro.setEditable(false);
	tfDescuentoAplicado.setEditable(false);
	//cbCantCuotas.autoSize();
	lblMoratoriaCaida.setVisible(false);
	btnFirst.setBounds(new Rectangle(10, 15, 25, 20));
	btnFirst.setSize(new Dimension(25, 20));
	btnFirst.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnFirst_actionPerformed(e);
		}
	    }
	);
    }

    private void setHeaderList() {
	headerList.removeAllElements();
        headerList.removeAllElements();
        headerList.addElement("Nº Cuota");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
        headerList.addElement("Fecha Vto");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
        headerList.addElement("$ Monto");
	headerList.addElement("*");
	headerList.addElement("*");
        
        listWestPanel.getTable().addMouseListener(new MouseAdapter() {

                                                 public void mouseClicked(MouseEvent e) {

                                                     if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {

                                                     } else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                                                        checkRows();
                                                     }
                                                 }

                                             }
        );
        listWestPanel.setParams("taxes.getCuotasPlanDePago", "-1", headerList);
    }

    private void setHeaderListEast() {
	headerListEast.removeAllElements();
	headerListEast.removeAllElements();
	headerListEast.addElement("Nº Cuota");
	headerListEast.addElement("*");
	headerListEast.addElement("*");
	headerListEast.addElement("*");
	headerListEast.addElement("Fecha Pago");
	headerListEast.addElement("*");
	headerListEast.addElement("*");
	headerListEast.addElement("*");
	headerListEast.addElement("*");
	headerListEast.addElement("*");
	headerListEast.addElement("$ Monto");
	headerListEast.addElement("*");
	
	listEastPanel.getTable().addMouseListener(new MouseAdapter() {

						 public void mouseClicked(MouseEvent e) {
						     //loadObjectSelected();
						     
						     if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
							 //getCuotas();
						     } else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
							//checkRows();
						     }
						 }

					     }
	);
	listEastPanel.setParams("taxes.getCuotasPlanDePagoPagadas", "-1", headerListEast);
    }

/**
 * 21/09/2009 (cesar)
 * No es necesaria esta funcion, ya que, las cuotas se calculan en base al combo 
 * */
/*    private void checkRows(){
        int fila = listWestPanel.getTable().getSelectedRow();
        if (fila > 1) {
            for (int i = 0 ;i <= fila ; i++)  {
                listWestPanel.getTable().setValueAt(true,i,0);
            }
            for (int i = (fila + 1) ;i <= listWestPanel.getTable().getRowCount()-1 ; i++)  {
                listWestPanel.getTable().setValueAt(false,i,0);
            }        
        } else {
            for (int i = (fila + 1) ;i <= listWestPanel.getTable().getRowCount()-1 ; i++)  {
                listWestPanel.getTable().setValueAt(false,i,0);
            }        
        }
    }
*/

 private void checkRows() {
     int fila = listWestPanel.getTable().getSelectedRow();
     for (int i = 0 ;i < listWestPanel.getTable().getRowCount() ; i++)  {
	 listWestPanel.getTable().setValueAt(false,i,0);
     }        
     //calcTotalAmount();
 }

    public void refresh() {
	String params = ""+ coordinador.getMoratoria().getIdPlanDePago();
	listWestPanel.refresh(params);
	listEastPanel.refresh(params);
        clearFields();
	setComboCuotas();
    }

    public void setComboCuotas() {
	int cantCuotas = LibSQL.getInt("taxes.getCantCuotasAPagar",""+ coordinador.getMoratoria().getIdPlanDePago());
	cbCantCuotas.removeAllItems();
	if (cantCuotas > 0)  {
	    for (int i = 1 ; i <= cantCuotas ; i++ )  {
	        cbCantCuotas.getCombo().addItem(i);
	    }    
	} else  {
	    cbCantCuotas.getCombo().addItem(0);
	}
	calcAmountToPay();
	if (coordinador.getMoratoria().isPlanDePagoCaido())  {
	    lblMoratoriaCaida.setVisible(true);
	    btnSaveData.setEnabled(false);
	    cbCantCuotas.setEnabled(false);
	} else{
	    lblMoratoriaCaida.setVisible(false);
	    if ( Integer.parseInt(cbCantCuotas.getSelectedItem().toString()) > 0) {
		btnSaveData.setEnabled(true);
	        cbCantCuotas.setEnabled(true);
	    } else {
	        btnSaveData.setEnabled(false);
		cbCantCuotas.setEnabled(false);
	    }
	}
    }
    
    private void calcAmountToPay(){
	if (listWestPanel.getValuesInTableAt(0).size() > 0)  {
	    int _cantCuotas = Integer.parseInt(cbCantCuotas.getSelectedItem().toString());
	    BigDecimal totalAPagar = new BigDecimal("0.0");
	    Vector _aux = listWestPanel.getValuesInTableAt(3);
	    for (int i = 0; i < _cantCuotas; i++)  {
	        totalAPagar = totalAPagar.add(new BigDecimal(_aux.elementAt(i).toString()));
	    }
	     tfAmount.setValue(totalAPagar);    
	} else  {
	    clearFields();
	}
    }
    
    private void btnNext_actionPerformed(ActionEvent e) {
	if (!coordinador.getMoratoria().isPlanDePagoCaido() && coordinador.getMoratoria().getIdPlanDePago() != -1) {
	    if ((!LibSQL.getBoolean("taxes.adeudaCuotas",""+ coordinador.getMoratoria().getIdPlanDePago())) && (LibSQL.getBoolean("taxes.pagoPrimerCuota",""+coordinador.getMoratoria().getIdPlanDePago()))) {
		((TaxesMain)getTabContainer()).getPayFeesMgmt().setCoordinador(coordinador);
		((TaxesMain)getTabContainer()).setEnabledPanels(4);
	    } else {
		Advisor.messageBox("Hay cuotas de la Moratoria/Plan de Pago que deben ser \ncanceladas para poder pagar los anticipos regulares","Mensaje");
	    }
	}
	else {
	    ((TaxesMain)getTabContainer()).getPayFeesMgmt().setCoordinador(coordinador);
	    ((TaxesMain)getTabContainer()).setEnabledPanels(4);
	}
    }

    private void btnFin_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void btnSaveData_actionPerformed(ActionEvent e) {
	int cantCuotas = Integer.parseInt(cbCantCuotas.getSelectedItem().toString());
	if (cantCuotas > 0) {
	    loadBoletaMoratoria(cantCuotas);
	    if (boletaPlanesDePago.saveData() > 0) {
		refresh();
	    } else {
		Advisor.messageBox("Ocurrió un error al grabar los datos!", "Error");
	    }
	} else {
	    Advisor.messageBox("No hay cuotas a pagar", "Aviso");
	}
    }

    public void clearFields() {
        tfAmount.setValue(0.0);
	cbCantCuotas.setEnabled(true);
	lblMoratoriaCaida.setVisible(false);
    }
    
    private void btnBack_actionPerformed(ActionEvent e) {
	((TaxesMain)getTabContainer()).plansOfPaymentMgmt().clearFields();
	clearFields();
	((TaxesMain)getTabContainer()).getTaxesMgmt().refresh();
	((TaxesMain)getTabContainer()).setEnabledPanels(1);
    }

    private void loadBoletaMoratoria(int _cantCuotas) {
	boletaPlanesDePago = new BoletaPlanesDePago();
	String concepto = "";
	String nro="";
	String apoderado = "";
	boletaPlanesDePago.setCantCuotas(_cantCuotas);
	boletaPlanesDePago.setIdTipoImpuesto(coordinador.getIdTipoImpuesto());
	boletaPlanesDePago.setIdPlanDePago(coordinador.getMoratoria().getIdPlanDePago());
	boletaPlanesDePago.setPorcentajeIntFin(coordinador.getMoratoria().getPorcentajeInteresFinanciacion());
	boletaPlanesDePago.setMontoIntFin(coordinador.getMoratoria().getMontoInteresFinanciacion());
	if (coordinador.getIdTipoImpuesto() == 1 || coordinador.getIdTipoImpuesto() == 2)  {	// Impuesto TGS ó Inmobiliario
	    catastro = new Cadastral();
	    catastro.setIdCatastro(coordinador.getMoratoria().getIdBien());
	    catastro.retrieveData();
	    catastro.retrieveApoderadoData();
	    boletaPlanesDePago.setIdBien(catastro.getIdCatastro());
	    boletaPlanesDePago.setContribuyente(catastro.getDomape());
	    boletaPlanesDePago.setSeccion(catastro.getTecsec());
	    boletaPlanesDePago.setManzana(catastro.getTecman());
	    boletaPlanesDePago.setParcela(catastro.getTecpar());
	    boletaPlanesDePago.setLocalidad(catastro.getLocalidad());
	    boletaPlanesDePago.setZona("");
	    boletaPlanesDePago.setDomicilio(catastro.getDcalle() +" "+ nro);
	    boletaPlanesDePago.setTerreno(catastro.getTerreno());
	    boletaPlanesDePago.setCatastro(catastro.getCatastro());
	    boletaPlanesDePago.setCategoria("" + catastro.getIdCategoria());
	    
	    String tervMej = catastro.getTervmej().toString();
	    if (!tervMej.equals(""))  {
		boletaPlanesDePago.setValedificacion(Double.parseDouble(catastro.getTervmej().toString()));	
	    } else  {
	        boletaPlanesDePago.setValedificacion(0.0);
	    }
	    String valFis = catastro.getValfis().toString();
	    if (!tervMej.equals(""))  {
	        boletaPlanesDePago.setValfiscal(Double.parseDouble(valFis));
	    } else  {
	        boletaPlanesDePago.setValfiscal(0.0);
	    }
	    
	    boletaPlanesDePago.setNroCuenta(catastro.getNroCuenta());
	    
	     if (coordinador.getIdTipoImpuesto() == 1)  {
	        concepto = coordinador.getTipoPlanDePago().getNombre() + " Nº " + coordinador.getMoratoria().getIdPlanDePago() ;
	    } else  {
	        concepto = coordinador.getTipoPlanDePago().getNombre() + " Nº " + coordinador.getMoratoria().getIdPlanDePago();
	    }
	    
	    
	     /*if (coordinador.getIdTipoImpuesto() == 1)  {
	         concepto = coordinador.getTipoPlanDePago().getNombre() + " Nº " + coordinador.getMoratoria().getIdPlanDePago() + " " + coordinador.getNombreTipoImpuesto();
	     } else  {
	         concepto = coordinador.getTipoPlanDePago().getNombre() + " Nº " + coordinador.getMoratoria().getIdPlanDePago() + " " + coordinador.getNombreTipoImpuesto();
	     }*/
	     
	    if (!catastro.getApoderadoLastName().trim().equals(""))  {
	     apoderado = catastro.getApoderadoLastName() +" "+ catastro.getApoderadoName();
	    }
	    if (catastro.getDnumro().trim().equals("0") || catastro.getDnumro().trim().equals(""))  {
		nro = "S/N";
	    } else  {
		nro = "Nº "+ catastro.getDnumro();
	    }	

	} else  { /** Cargar datos para Automotor */
	    automotor = new Car();
	    automotor.setIdAutomotor(coordinador.getIdBien());
	    automotor.retrieveIdDominio();
	    automotor.retrieveData();
	    boletaPlanesDePago.setIdBien(automotor.getIdAutomotor());
	    boletaPlanesDePago.setContribuyente(automotor.getTitular());
	    boletaPlanesDePago.setDomicilio(automotor.getDomicilio());
	    boletaPlanesDePago.setDominio(automotor.getDominio());
	    boletaPlanesDePago.setCategoria(automotor.getCategoria());
	    boletaPlanesDePago.setDni(automotor.getDni());
	    boletaPlanesDePago.setMarca(automotor.getMarca());
	    boletaPlanesDePago.setModelo(automotor.getModelo());
	    boletaPlanesDePago.setNromotor(automotor.getNromotor());
	    boletaPlanesDePago.setTipoautomotor(automotor.getTipo());
	    boletaPlanesDePago.setDni(automotor.getDni());
	    //boletaPlanesDePago.setNroCuenta(catastro.getNroCuenta());
	     concepto = coordinador.getTipoPlanDePago().getNombre() + " Nº " + coordinador.getMoratoria().getIdPlanDePago();
	}
	
	boletaPlanesDePago.setConcepto(concepto);
	boletaPlanesDePago.setImporte(Double.parseDouble(tfAmount.getValue().toString()));
	boletaPlanesDePago.setUsuario(Environment.sessionUser);
	boletaPlanesDePago.setApoderado(apoderado);
    }
    
    public void setCoordinador(Coordinador _coordinador){
	coordinador = _coordinador;
	coordinador.retrieveData();
	loadForm();
    }
    
    private void loadForm(){
	tfContribuyente.setValue(coordinador.getContribuyente());
	tfDescuentoAplicado.setValue(coordinador.getDescuento());
	tfNumPlanDePago.setValue(coordinador.getMoratoria().getIdPlanDePago());
	
	if (coordinador.getIdTipoImpuesto() == 1)  {
	    tfCatastro.setValue(coordinador.getCatastro().getCatastro());
	} else if (coordinador.getIdTipoImpuesto() == 2)  {
	    tfCatastro.setValue(coordinador.getCatastro().getCatastro());
	} else if (coordinador.getIdTipoImpuesto() == 3)  {
	    tfCatastro.setValue(coordinador.getAutomotor().getDominio());
	}
	findPanel.setBorder(BorderPanel.getBorderPanel("Pago de cuotas - " + coordinador.getTipoPlanDePago().getNombre() + " Nº " + coordinador.getMoratoria().getIdPlanDePago()));
	listWestPanel.setTitle("Cuotas " + coordinador.getTipoPlanDePago().getNombre() + " Nº " + coordinador.getMoratoria().getIdPlanDePago());
    }

    private void btnFirst_actionPerformed(ActionEvent e) {
	((TaxesMain)getTabContainer()).plansOfPaymentMgmt().clearFields();
	clearFields();
	((TaxesMain)getTabContainer()).getTaxesMgmt().refresh();
	((TaxesMain)getTabContainer()).setEnabledPanels(0);
    }
}


