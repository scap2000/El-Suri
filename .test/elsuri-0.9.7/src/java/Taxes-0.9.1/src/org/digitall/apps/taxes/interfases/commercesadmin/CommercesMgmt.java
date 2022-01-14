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
 * CommercesMgmt.java
 *
 * */
package org.digitall.apps.taxes.interfases.commercesadmin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.Vector;

import javax.swing.JComponent;

import org.digitall.apps.taxes.classes.Commerce;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class CommercesMgmt extends BasicPrimitivePanel {

    private BasicPanel dataPanel = new BasicPanel("Agregar/Modificar un Comercio");
    
    private CloseButton btnClose = new CloseButton();
    private SaveDataButton btnAccept = new SaveDataButton();
    
    private TFInput tfPollTax = new TFInput(DataTypes.STRING,"PollTax",true);
    private TFInput tfTaxPayer = new TFInput(DataTypes.STRING,"TaxPayer",true);
    private TFInput tfDni = new TFInput(DataTypes.STRING,"DNI",false);
    private TFInput tfTradingName = new TFInput(DataTypes.STRING,"TradingName",true);
    private TFInput tfTradeName = new TFInput(DataTypes.STRING,"TradeName",true);
    private TFInput tfAddressReal = new TFInput(DataTypes.STRING,"AddressReal",false);
    private TFInput tfFile = new TFInput(DataTypes.STRING,"File",false);
    private TFInput tfMonthlyFee = new TFInput(DataTypes.MONEY,"MonthlyFee",false);
    private TFInput tfStartDate = new TFInput(DataTypes.DATE,"StartDate",true);
    private TFInput tfEndDate = new TFInput(DataTypes.DATE,"DropOffDate",true);
    private TFInput tfCuit = new TFInput(DataTypes.STRING,"Cuit",false);
    
    private Commerce commerce;
    private BasicPrimitivePanel parentList;
    
    int error = 0;
    private static final int ok = 0;
    private static final int padron = 1;
    private static final int contribuyente = 2;
    private static final int rubro = 3;
    private static final int razonsocial = 4;
    private static final int fechaInicio = 5;
    private static final int fechaBajaInvalida = 6;
    
    private Vector components = new Vector();

    public CommercesMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout( null );
	this.setSize(new Dimension(565, 294));
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
	tfPollTax.setBounds(new Rectangle(10, 25, 145, 35));
	components.add(tfPollTax);
	dataPanel.setBounds(new Rectangle(5, 0, 555, 220));
	dataPanel.setLayout(null);
	tfTaxPayer.setBounds(new Rectangle(170, 25, 220, 35));
	components.add(tfTaxPayer);
	tfTradingName.setBounds(new Rectangle(10, 75, 145, 35));
	components.add(tfTradingName);
	tfTradeName.setBounds(new Rectangle(170, 75, 205, 35));
	components.add(tfTradeName);
	tfAddressReal.setBounds(new Rectangle(10, 120, 535, 35));
	components.add(tfAddressReal);
        dataPanel.add(tfEndDate, null);
        dataPanel.add(tfCuit, null);
        dataPanel.add(tfDni, null);
        dataPanel.add(tfTaxPayer, null);
        dataPanel.add(tfPollTax, null);
        dataPanel.add(tfAddressReal, null);
        dataPanel.add(tfTradeName, null);
	dataPanel.add(tfTradingName, null);
        dataPanel.add(tfStartDate, null);
        dataPanel.add(tfFile, null);
        dataPanel.add(tfMonthlyFee, null);
        this.add(dataPanel, BorderLayout.CENTER);
	this.addButton(btnClose);
	this.addButton(btnAccept);
	
	tfFile.setBounds(new Rectangle(10, 210, 230, 35));
	components.add(tfFile);
        tfFile.getTextField().addKeyListener(new KeyAdapter() {

                                                  public void keyTyped(KeyEvent e) {
                                                      char caracter = e.getKeyChar();
                                                      if ((caracter == KeyEvent.VK_ENTER)) {
                                                          calcPayment();
                                                      }
                                                  }

                                              }
        );
        tfMonthlyFee.setBounds(new Rectangle(10, 165, 145, 35));
	components.add(tfMonthlyFee);
	tfStartDate.setBounds(new Rectangle(235, 165, 115, 35));
	components.add(tfStartDate);
        tfCuit.setBounds(new Rectangle(400, 75, 145, 35));
	components.add(tfCuit);
        tfEndDate.setBounds(new Rectangle(445, 165, 115, 35));
	components.add(tfEndDate);
        tfDni.setBounds(new Rectangle(400, 25, 145, 35));
	components.add(tfDni);
        tfEndDate.setEnabled(false);
    }
    
    private void loadData(){
         if (commerce.getIdpadron() != -1) {
            tfEndDate.setEnabled(true);
            tfPollTax.setValue(commerce.getPadron());
            tfTaxPayer.setValue(commerce.getContribuyente());
            tfDni.setValue(commerce.getDni());
            tfTradingName.setValue(commerce.getRubro());
            tfTradeName.setValue(commerce.getRazonsocial());
            tfAddressReal.setValue(commerce.getDomicilio());
            tfMonthlyFee.setValue(commerce.getCuotamensual());
	    if (commerce.getFechainicio() != null)  {
	         tfStartDate.setValue("" + Proced.setFormatDate(commerce.getFechainicio(),true));
	    }
	     if (commerce.getFechaBaja() != null)  {
	          tfEndDate.setValue("" + Proced.setFormatDate(commerce.getFechaBaja(),true));
	         setEnabledAll(false);
	     }
            tfFile.setValue(commerce.getExpte()); 
            tfCuit.setValue(commerce.getCuit());
	 } else {
             tfEndDate.setEnabled(false);
         }
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    private void setData() {
        commerce.setPadron(tfPollTax.getString());
        commerce.setContribuyente(tfTaxPayer.getString());
        commerce.setDni(tfDni.getString());
        commerce.setRubro(tfTradingName.getString());
        commerce.setRazonsocial(tfTradeName.getString());
        commerce.setDomicilio(tfAddressReal.getString());
        commerce.setCuotamensual(tfMonthlyFee.getAmount());
        if (!tfStartDate.getString().equals(""))  {
            commerce.setFechainicio("" + Proced.setFormatDate(tfStartDate.getString(),false));    
        }
        if (!tfEndDate.getString().equals(""))  {
            commerce.setFechaBaja("" + Proced.setFormatDate(tfEndDate.getString(),false));    
        }else {
	    commerce.setFechaBaja("");
	}
        commerce.setExpte(tfFile.getString());
        commerce.setCuit(tfCuit.getValue().toString());
    }

    public boolean saveData(){
	if (control() == ok)  {
	    if (commerce == null){
	        commerce = new Commerce();
	    }
            setData();
	    if (commerce.saveData() >= 0) {
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
	    parentList.refresh();
	    getParentInternalFrame().close();
	}
    }
    
     public void setParentList(BasicPrimitivePanel _parentList) {
         parentList = _parentList;
     }

    private void calcPayment() {
	 tfMonthlyFee.setValue(tfFile.getAmount() / 6);
    }

    public void mgmtRefresh(){
	calcPayment();
    }

    public void setCommerce(Commerce _commerce) {
	commerce = _commerce;
	loadData();
    }

    private int control() {
	error = ok;
	if (tfPollTax.getString().equals(""))  {
	    error = padron;
	} else if (tfTaxPayer.getString().equals("")) {
	    error = contribuyente;
	} else if(tfTradingName.getString().equals("")) {
	    error = rubro;
	} else if(tfTradeName.getString().equals("")) {
	    error = razonsocial;
	}else if(tfStartDate.getString().equals("")) {
	    error = fechaInicio;
	}else if (Proced.compareDates(Proced.setFormatDate(tfStartDate.getString(),false),Proced.setFormatDate(tfEndDate.getString(),false)) == 2) {
	        error = fechaBajaInvalida;
	}     
	return error;
    }

    private void showMessage() {
	switch (error)  {
	    case 1: 
		    Advisor.messageBox("Debe ingresar el número de Padron", "Mensaje");
		break;
	    case 2: 
		    Advisor.messageBox("Debe ingresar el nombre del Contribuyente", "Mensaje");
		break;
	    case 3: 
		    Advisor.messageBox("Debe ingresar el Rubro del Comercio", "Mensaje");           
		break;
	    case 4: 
	            Advisor.messageBox("Debe ingresar la Razón Social del Comercio", "Mensaje");           
	        break;
	    case 5: 
	            Advisor.messageBox("El campo fecha de inicio no puede estar vacio", "Mensaje");           
	        break;
	    case 6: 
	            Advisor.messageBox("La fecha de de baja no puede ser meno a la fecha de inicio", "Mensaje");
	        break;
	}
    }
    
    private void setEnabledAll(boolean _test){
	for(int i = 0; i < components.size(); i++){
	    ((JComponent)components.elementAt(i)).setEnabled(_test);
	}
    }
}
