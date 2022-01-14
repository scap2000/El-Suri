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
 * AcreditationMgmt.java
 *
 * */
package org.digitall.apps.taxes.interfases.cashier;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import org.digitall.apps.taxes.classes.Acreditacion;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;

public class AcreditationMgmt extends BasicPrimitivePanel {

    private BasicPanel content = new BasicPanel();

    private SaveDataButton btnSaveData = new SaveDataButton();
    private CloseButton btnClose = new CloseButton();
    
    private TFInput tfFecha = new TFInput(DataTypes.DATE,"Date",true);
    private TFInput tfMonto = new TFInput(DataTypes.MONEY,"Amount",true);
    private TFInput tfEntidad = new TFInput(DataTypes.STRING,"Entidad",true);
    private TFInput tfNroCuenta = new TFInput(DataTypes.STRING,"Nro. Cuenta",true);
    
    private CashierButtons parentMain;
    private Acreditacion acreditacion;
    
    private int error = 0;

    public AcreditationMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(436, 175));
	this.setBounds(new Rectangle(10, 10, 436, 175));
	this.setTitle("Caja");
	content.setLayout(null);

	btnSaveData.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					btnSaveData_actionPerformed(e);
				    }

				}
	);
	
	btnClose.addActionListener(new ActionListener() {
		    
			public void actionPerformed(ActionEvent e){
			    btnClose_actionPerformed(e);
			}
	});
	tfFecha.setBounds(new Rectangle(320, 25, 105, 35));
	tfMonto.setBounds(new Rectangle(290, 80, 135, 35));
	
	tfMonto.getTextField().addKeyListener(new KeyAdapter() {
		
		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			save();
		    }
		}
	    }
	);
	
	
	tfEntidad.setBounds(new Rectangle(10, 25, 250, 35));
	tfNroCuenta.setBounds(new Rectangle(10, 80, 160, 35));
	content.add(tfNroCuenta, null);
	content.add(tfEntidad, null);
	content.add(tfMonto, null);
	content.add(tfFecha, null);
	this.add(content, null);
	addButton(btnClose);
	addButton(btnSaveData);
	tfFecha.setEnabled(false);
	tfFecha.setValue(Proced.setFormatDate(Environment.currentDate.toString(),true));
	content.setBorder(BorderPanel.getBorderPanel("Datos"));
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
    }
   
    public void setParentMain(CashierButtons parentMain) {
	this.parentMain = parentMain;
    }

    public CashierButtons getParentMain() {
	return parentMain;
    }

    private void btnSaveData_actionPerformed(ActionEvent e) {
	save();
    }
    
    private void btnClose_actionPerformed(ActionEvent e){
	getParentInternalFrame().close();
    }
    
    private void save(){
	if (control()) {
	    loadObject();
	     if (Advisor.question("Aviso","¿Esta seguro de registrar la acreditacion a nombre de "+ tfEntidad.getValue().toString().toUpperCase() 
		    +",\npor un monto de $"+ tfMonto.getAmount() +" a la Cuenta Nº "+ tfNroCuenta.getValue().toString() +" ?")) {
		 if (acreditacion.saveData() > 0) {
		     getParentInternalFrame().close();
		 } else {
		     Advisor.messageBox("Ocurrio un error, no se grabaron los datos","Mensaje");
		 }
	     }
	} else {
	    printError();
	}
    }
    
    private void loadObject() {
	acreditacion = new Acreditacion();
	acreditacion.setNroCaja(1);
	acreditacion.setFecha(Proced.setFormatDate(tfFecha.getDate(),false));
	acreditacion.setEntidad(tfEntidad.getValue().toString());
	acreditacion.setNroCuenta(tfNroCuenta.getValue().toString());
	acreditacion.setMonto(tfMonto.getAmount());
    }


    private boolean control() {
	boolean valor = false;
	if (tfFecha.getDate().toString().length() < 1)  {
	    error = 1;
	} else if (tfEntidad.getValue().toString().length() == 0) {
	    error = 2;
	} else if (tfNroCuenta.getValue().toString().length() == 0) {
	    error = 3;
	} else if ( Double.parseDouble(tfMonto.getValue().toString())  == 0) {
	    error = 4;
	} else {
	    valor = true;
	}
	return valor;
    }

    private void printError() {
	switch (error)  {
	    case 1:  Advisor.messageBox("El campo Fecha no puede ser vacio","Error");
		break;
	    case 2:  Advisor.messageBox("El campo Entidad no puede ser vacio","Error");
	        break;
	    case 3:  Advisor.messageBox("El campo Nro. Cuenta no puede ser vacio","Error");
	        break;
	    case 4:  Advisor.messageBox("El campo Monto debe ser mayor que cero","Error");
	        break;
	}
	
    }
}

