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
 * FeesMgmt.java
 *
 * */
package org.digitall.apps.taxes.interfases.carsadmin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.sql.ResultSet;

import org.digitall.apps.taxes.classes.Payment;
import org.digitall.apps.taxes.interfases.TaxesTGS;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class FeesMgmt extends BasicPrimitivePanel {

    private BasicPanel dataPanel = new BasicPanel("Agregar/Modificar");
    private CloseButton btnClose = new CloseButton();
    private SaveDataButton btnAccept = new SaveDataButton();
    
    private TFInput tfBien = new TFInput(DataTypes.STRING,"Domain",true);
    private TFInput tfInteres = new TFInput(DataTypes.DOUBLE,"Interest",false);
    private TFInput tfDescuento = new TFInput(DataTypes.DOUBLE,"Discount",false);
    private TFInput tfMonto = new TFInput(DataTypes.MONEY,"Amount",false);
    private TFInput tfCuota = new TFInput(DataTypes.MONEY,"MonthlyFee",false);
    private TFInput tfFechaVto = new TFInput(DataTypes.DATE,"ExpirationDate",false);
    private TFInput tfFechaPago = new TFInput(DataTypes.DATE,"PaymentDate",true);
    
    private CBInput cbYears = new CBInput(0,"FileYear",false);
    private CBInput cbFee = new CBInput(0,"Fee", false);

    private Payment payment;
    private TaxesTGS parentList;
    
    private boolean modificar = false;
    String tipoImpuesto = "";
    
    int error = 0;
    private static final int OK = 0;
    private static final int DOMINIO = 1;
    private static final int FECHAPAGO = 2;
    private static final int CUOTA = 3;
    private static final int MONTO = 4;
    private static final int CANTCUOTAS = 5;
    
    public FeesMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout( null );
	this.setSize(new Dimension(388, 274));
	this.setPreferredSize(new Dimension(565, 300));
	btnClose.setBounds(new Rectangle(540, 535, 40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	btnAccept.setBounds(new Rectangle(490, 535, 40, 25));
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	tfBien.setBounds(new Rectangle(10, 25, 125, 35));
	dataPanel.setBounds(new Rectangle(5, 0, 555, 220));
	dataPanel.setLayout(null);
	cbFee.setBounds(new Rectangle(155, 25, 80, 35));
	tfFechaVto.setBounds(new Rectangle(10, 75, 110, 35));
	tfFechaPago.setBounds(new Rectangle(255, 75, 120, 35));
	cbYears.setBounds(new Rectangle(255, 25, 105, 35));
	tfInteres.setBounds(new Rectangle(10, 180, 110, 35));
	tfDescuento.setBounds(new Rectangle(255, 180, 110, 35));
	dataPanel.add(tfFechaPago, null);
	dataPanel.add(tfFechaVto, null);
	dataPanel.add(cbYears, null);
	dataPanel.add(tfBien, null);
	dataPanel.add(tfDescuento, null);
	dataPanel.add(tfInteres, null);
	dataPanel.add(cbFee, null);
	dataPanel.add(tfMonto, null);
	dataPanel.add(tfCuota, null);
	this.add(dataPanel, BorderLayout.CENTER);
	this.addButton(btnClose);
	this.addButton(btnAccept);
	tfMonto.setBounds(new Rectangle(255, 130, 110, 35));
	tfCuota.setBounds(new Rectangle(10, 130, 110, 35));
	cbFee.autoSize();
	cbYears.autoSize();
	loadFeeCombo();
	
	cbYears.addItemListener(new ItemListener() {
	      public void itemStateChanged(ItemEvent evt) {
		  if (evt.getStateChange() == ItemEvent.SELECTED) {
		      //calcPayment();;
		      setFechaVto();
		  }
	      }
	});
	cbFee.addItemListener(new ItemListener() {
	    public void itemStateChanged(ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED) {
		    setFechaVto();
		}
	    }
	    });
	tfBien.setEnabled(false);
	tfFechaVto.setEnabled(false);
    }
    
    
    private void setFechaVto() {
	String params = "" + cbFee.getSelectedItem() +","+cbYears.getSelectedItem() +","+ payment.getIdTipoImpuesto();
	String fechavto = "" + LibSQL.getDate("impuestos.getFechaInicioDeudaByDate",params);
	tfFechaVto.setValue(Proced.setFormatDate(fechavto,true));
    }

    private void loadFeeCombo() {
	int cont = 0;
	for (int i = 1 ; i <= 6 ; i++)  {
	    cont++;
	    cbFee.getCombo().addItem(i,cont);
	}
	cbFee.setSelectedID(1);
    }

    private void loadCombos(){
	int yearPayFrom = LibSQL.getInt("impuestos.getpayfrom","" + payment.getIdTipoImpuesto() +"," + payment.getIdBien());
	int yearPayTo = LibSQL.getInt("impuestos.getPayTo","" + payment.getIdTipoImpuesto() +"," + payment.getIdBien());
	
	if (yearPayTo == 0)  {
	    yearPayTo = Integer.parseInt(Environment.currentYear) + 1;
	}
	if (yearPayFrom == 0)  {
	    yearPayFrom = 1940;
	} else {
	    yearPayFrom = yearPayFrom - 1;
	}
	
	int cont = 0;
	for (int i = yearPayFrom ; i <= yearPayTo ; i++)  {
	    cont++;
	    cbYears.getCombo().addItem(i,cont);
	}
	cbYears.setSelectedID(cont - 1);
    }

    private void loadData(){
	 if (payment.getIdPago() == -1) {
	     setFechaVto();
	     tfBien.setValue(payment.getBien());
	 }
	 tipoImpuesto = "";
	 if (payment.getIdTipoImpuesto() == 1) {
	     tipoImpuesto = "T.G.S.";
	 } else if (payment.getIdTipoImpuesto() == 2) {
	     tipoImpuesto = "Imp. Inmobiliario";
	 } else {
	     tipoImpuesto = "Imp. Automotor";
	 }
	 dataPanel.setTitle("Ultimo Pago " + tipoImpuesto);
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    public boolean saveData(){
	
	if (control() == OK)  {
	    if (payment == null){
	        payment = new Payment();
	    }
	    payment.setAnio(Integer.parseInt("" + cbYears.getSelectedItem()));
	    payment.setIdTipoCuota((Integer.parseInt("" + cbFee.getSelectedItem())));
	    
	    payment.setFechaVto("" + Proced.setFormatDate(tfFechaVto.getString(),false));
	    payment.setFechaPago("" + Proced.setFormatDate(tfFechaPago.getString(),false));
	    payment.setValorReal(tfCuota.getAmount());
	    payment.setMonto(tfMonto.getAmount());
	    payment.setInteres(tfInteres.getAmount());
	    payment.setDescuento(tfDescuento.getAmount());
	    
	    if (payment.isDelBookKeepingEntry())  {
		int borrado = LibSQL.getInt("impuestos.delPagos","" + payment.getIdTipoImpuesto() +","+ payment.getIdBien());
	    }
	    
	    if (payment.saveData() >= 0) {
	        return true;
	    } else {
	        return false;
	    }	
	} else {
	    showMessage();
	    return false;
	}
    }
    
    private void btnAccept_actionPerformed(ActionEvent e) {
	if (saveData()){
	    //parentList.refresh();
	    getParentInternalFrame().close();
	}
    }
    
    public void setParentList(TaxesTGS _parentList) {
	parentList = _parentList;
    }

    private int control() {
	error = OK;
	if (tfBien.getString().equals(""))  {
	    error = DOMINIO;
	} else if(tfFechaPago.getString().equals("")) {
	    error = FECHAPAGO;
	} else if(tfCuota.getString().equals("")) {
	    error = CUOTA;
	} else if(tfMonto.getString().equals("")) {
	    error = MONTO;
	} 
	return error;
    }

    private void showMessage() {
	switch (error)  {
	    case DOMINIO: 
		    Advisor.messageBox("Debe ingresar el número de Dominio", "Mensaje");
		break;
	    case FECHAPAGO: 
		    Advisor.messageBox("Debe ingresar la fecha de Pago", "Mensaje");
		break;
	    case CUOTA: 
		    Advisor.messageBox("Debe el valor de la Cuota", "Mensaje");           
		break;
	    case MONTO: 
	            Advisor.messageBox("Debe el valor del monto a Pagar", "Mensaje");           
	        break;
	    
	}
    }

    public void setPayment(Payment payment) {
	this.payment = payment;
	if (cantCuotas() > 0)  {
	    if (Advisor.question("Aviso","El último pago del Catastro/Dominio ya está vinculado al módulo contable,\nmodificarlo eliminaria los asientos registrados. ¿Desea modificarlo?"))  {
	        loadCombos();   
	        loadData();
	        payment.setDelBookKeepingEntry(true);
	    } else {
	        getParentInternalFrame().close();           
	    }
	    payment.setDelBookKeepingEntry(true);
	} else {
	    loadCombos();   
	    loadData();
	    payment.setDelBookKeepingEntry(true);
	}
    }

    private int cantCuotas() {
	int qty = LibSQL.getInt("impuestos.getCantCuotasPagadas","" + payment.getIdTipoImpuesto() +","+ payment.getIdBien());
	return qty;
    }

}
