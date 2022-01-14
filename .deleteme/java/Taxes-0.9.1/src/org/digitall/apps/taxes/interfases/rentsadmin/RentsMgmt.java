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
 * RentsMgmt.java
 *
 * */
package org.digitall.apps.taxes.interfases.rentsadmin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Vector;

import javax.swing.JComponent;

import org.digitall.apps.taxes.classes.Rent;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class RentsMgmt extends BasicPrimitivePanel {

    private BasicPanel dataPanel = new BasicPanel("Agregar/Modificar una Empresa");
    
    private CloseButton btnClose = new CloseButton();
    private SaveDataButton btnAccept = new SaveDataButton();
    
    private TFInput tfEmpresa = new TFInput(DataTypes.STRING,"Enterprise",true);
    private TFInput tfResponsable = new TFInput(DataTypes.STRING,"PersonCharge",true);
    private TFInput tfPredio = new TFInput(DataTypes.STRING,"Predio",false);
    private TFInput tfContrato = new TFInput(DataTypes.STRING,"Comodato",true);
    private TFInput tfFecha = new TFInput(DataTypes.DATE,"StartDate",true);
    private TFInput tfEndDate = new TFInput(DataTypes.DATE,"DropOffDate",true);
    private TFInput tfDuracion = new TFInput(DataTypes.STRING,"Duracion",true);
    private TFInput tfMonthlyFee = new TFInput(DataTypes.MONEY,"MonthlyFee",false);
    private TFInput tfObservaciones = new TFInput(DataTypes.STRING,"Observations",false);
    private TFInput tfCuit = new TFInput(DataTypes.STRING,"Cuit",false);
    private TFInput tfDni = new TFInput(DataTypes.STRING,"DNI",false);

    private Rent rent;
    private BasicPrimitivePanel parentList;
    
    int error = 0;
    private static final int ok = 0;
    private static final int empresa = 1;
    private static final int responsable = 2;
    private static final int predio = 3;
    private static final int vigencia = 4;
    private static final int fechaInicio = 5;
    private static final int fechaBajaInvalida = 6;
    
    private Vector components = new Vector();

    public RentsMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout( null );
	this.setSize(new Dimension(557, 304));
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
	tfEmpresa.setBounds(new Rectangle(10, 25, 330, 35));
	components.add(tfEmpresa);
	dataPanel.setBounds(new Rectangle(5, 0, 555, 220));
	dataPanel.setLayout(null);
	tfResponsable.setBounds(new Rectangle(10, 70, 330, 35));
	components.add(tfResponsable);
	tfContrato.setBounds(new Rectangle(10, 165, 145, 35));
	components.add(tfContrato);
	tfDuracion.setBounds(new Rectangle(430, 165, 115, 35));
	components.add(tfDuracion);
	tfObservaciones.setBounds(new Rectangle(170, 220, 375, 35));
	components.add(tfObservaciones);
        tfCuit.setBounds(new Rectangle(400, 25, 145, 35));
	components.add(tfCuit);
        tfDni.setBounds(new Rectangle(400, 70, 145, 35));
	components.add(tfDni);
        tfEndDate.setBounds(new Rectangle(305, 165, 100, 35));
	components.add(tfEndDate);
        dataPanel.add(tfEndDate, null);
        dataPanel.add(tfDni, null);
        dataPanel.add(tfCuit, null);
        dataPanel.add(tfPredio, null);
        dataPanel.add(tfResponsable, null);
        dataPanel.add(tfEmpresa, null);
        dataPanel.add(tfObservaciones, null);
        dataPanel.add(tfDuracion, null);
        dataPanel.add(tfContrato, null);
        dataPanel.add(tfFecha, null);
        dataPanel.add(tfMonthlyFee, null);
        this.add(dataPanel, BorderLayout.CENTER);
	this.addButton(btnClose);
	this.addButton(btnAccept);
	
        tfMonthlyFee.setBounds(new Rectangle(10, 220, 145, 35));
	components.add(tfMonthlyFee);
	tfFecha.setBounds(new Rectangle(170, 165, 115, 35));
	components.add(tfFecha);
        tfPredio.setBounds(new Rectangle(10, 115, 535, 35));
	components.add(tfPredio);
        tfEndDate.setEnabled(false);
    }
    
    private void loadData(){
	 if (rent.getIdempresa() != -1) {
            tfEmpresa.setValue(rent.getEmpresa());
            tfResponsable.setValue(rent.getResponsable());
            tfPredio.setValue(rent.getPredio());
            tfContrato.setValue(rent.getContratocomodato());
	    //if (!rent.getVigencia().equals(""))  {
	    if (rent.getVigencia() != null)  {
	          tfFecha.setValue("" + Proced.setFormatDate(rent.getVigencia(),true));
	    }
            tfDuracion.setValue(rent.getDuracion());
            tfMonthlyFee.setValue(rent.getImportemensual());
            tfObservaciones.setValue(rent.getObservacion());
            tfCuit.setValue(rent.getCuit());
            tfDni.setValue(rent.getDni());
            tfEndDate.setEnabled(true);
	     if (rent.getFechaBaja() != null)  {
	           tfEndDate.setValue("" + Proced.setFormatDate(rent.getFechaBaja(),true));
	           setEnabledAll(false);
	     }
	 } else {
             tfEndDate.setEnabled(false);
         }
    } 
    
    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    private void setData(){
        rent.setEmpresa(tfEmpresa.getString().toUpperCase());
        rent.setResponsable(tfResponsable.getString().toUpperCase());
        rent.setPredio(tfPredio.getString());
        rent.setContratocomodato(tfContrato.getString());
        if (!tfFecha.getString().equals(""))  {
            rent.setVigencia("" + Proced.setFormatDate(tfFecha.getString(),false));    
        }
        if (!tfEndDate.getString().equals(""))  {
            rent.setFechaBaja("" + Proced.setFormatDate(tfEndDate.getString(),false));    
        }else{
	    rent.setFechaBaja("");
	}
        rent.setDuracion(tfDuracion.getString());
        rent.setImportemensual(tfMonthlyFee.getAmount());
        rent.setObservacion(tfObservaciones.getString());
        rent.setCuit(tfCuit.getValue().toString());
        rent.setDni(tfDni.getValue().toString());
    }

    public boolean saveData(){
	if (control() == ok)  {
	    if (rent == null){
	        rent = new Rent();
	    }
            setData();
	    if (rent.saveData() >= 0) {
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

    public void setRent(Rent _rent) {
	rent = _rent;
	loadData();
    }

    private int control() {
	error = ok;
	if (tfEmpresa.getString().equals(""))  {
	    error = empresa;
	} else if (tfResponsable.getString().equals("")) {
	    error = responsable;
	} else if(tfContrato.getString().equals("")) {
	    error = predio;
	} else if(tfFecha.getString().equals("")) {
	        error = fechaInicio;
	} else if(tfDuracion.getString().equals("")) {
	    error = vigencia;
	} else if (Proced.compareDates(Proced.setFormatDate(tfFecha.getString(),false),Proced.setFormatDate(tfEndDate.getString(),false)) == 2) {
	        error = fechaBajaInvalida;
	    }
	return error;
    }

    private void showMessage() {
	switch (error)  {
	    case empresa: 
		    Advisor.messageBox("Debe ingresar el nombre de la Empresa", "Mensaje");
		break;
	    case responsable: 
		    Advisor.messageBox("Debe ingresar el nombre del Responsable", "Mensaje");
		break;
	    case predio: 
		    Advisor.messageBox("El campo Comodato no debe estar vacío", "Mensaje");           
		break;
	    case vigencia: 
	            Advisor.messageBox("El campo Duración no puede estar vacío", "Mensaje");           
	        break;
	    case fechaInicio: 
	            Advisor.messageBox("El campo Fecha de Inicio no puede estar vacío", "Mensaje");           
	        break;
	    case fechaBajaInvalida: 
		    Advisor.messageBox("La fecha de baja debe ser mayor o igual a la fecha de inicio.", "Mensaje");           
	        break;
	}
    }
    
    private void setEnabledAll(boolean _test){
	for(int i = 0; i < components.size(); i++){
	    ((JComponent)components.elementAt(i)).setEnabled(_test);
	}
    }
   
}
