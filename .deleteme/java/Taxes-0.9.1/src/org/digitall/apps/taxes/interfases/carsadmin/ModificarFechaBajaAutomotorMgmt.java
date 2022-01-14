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
 * ModificarFechaBajaAutomotorMgmt.java
 *
 * */
package org.digitall.apps.taxes.interfases.carsadmin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.apps.taxes.classes.Car;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicTextArea;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;


public class ModificarFechaBajaAutomotorMgmt extends BasicPrimitivePanel {

    private BasicPanel content = new BasicPanel("");

    private BasicLabel lblObservaciones = new BasicLabel();
    private BasicTextArea taObservaciones = new BasicTextArea();
    private TFInput tfFechaBaja = new TFInput(DataTypes.SIMPLEDATE,"DropOffDate",false);

    private CloseButton btnCerrar = new CloseButton();
    private SaveDataButton btnGrabar = new SaveDataButton();

    private Car car;
    private CarsMgmt parentList;
    
    public ModificarFechaBajaAutomotorMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    private void jbInit() throws Exception {
	this.setSize(new Dimension(331, 254));
	this.setPreferredSize(new Dimension(565, 300));
	btnCerrar.setBounds(new Rectangle(540, 535, 40, 25));
	btnCerrar.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnCerrar_actionPerformed(e);
				}

			    }
	);
	btnGrabar.setBounds(new Rectangle(490, 535, 40, 25));
	btnGrabar.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnGrabar_actionPerformed(e);
				 }

			     }
	);
	content.setBounds(new Rectangle(5, 0, 555, 220));
	content.setLayout(null);
        tfFechaBaja.setBounds(new Rectangle(15, 25, 115, 35));
        content.add(lblObservaciones, null);
        content.add(taObservaciones, null);
        content.add(tfFechaBaja, null);
        this.add(content, BorderLayout.CENTER);
        addButton(btnCerrar);
	addButton(btnGrabar);
        taObservaciones.setBounds(new Rectangle(15, 85, 300, 105));
        lblObservaciones.setText("Observaciones:");
        lblObservaciones.setBounds(new Rectangle(15, 65, 125, 20));
        content.setBorder(BorderPanel.getBorderPanel("Modificar fecha de Baja"));
    }
    
     public void setParentInternalFrame(ExtendedInternalFrame _e) {
	 super.setParentInternalFrame(_e);
     }
    
    private void btnCerrar_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }
    
    /**2010-03-09 (moraless)*/
    public boolean saveData() {
        String mensaje = "alta";
        boolean darDeBaja = false;
        boolean retorno = false;
        //verificamos si podemos dar de baja
        if (!tfFechaBaja.getDate().equals("")) { //dar de baja un dominio
            darDeBaja = LibSQL.getBoolean("taxes.dardebajaautomotor","" + car.getIddominio());
            mensaje = "baja";
        }
        
        if (Advisor.question("Mensaje", "¿Está seguro de dar de " + mensaje + " el dominio \"" + car.getDominio() + "\"?")) {
            if( (tfFechaBaja.getDate().equals("")) || darDeBaja ){
                car.setObservaciones(taObservaciones.getText());
                car.setFechaBaja(Proced.setFormatDate(tfFechaBaja.getDate(),false));
                if(car.saveData() != -1 ){
                    retorno = true;
                    Advisor.messageBox("El dominio \""+ car.getDominio() + "\" se dio de " + mensaje + " correctamente", "·Mensaje");
                }                    
            } else {
                if (!darDeBaja) {
                    Advisor.messageBox("El dominio \"" + car.getDominio() + "\" no se puede dar de baja debido\n a que tiene asociado boletas y/o planes de pago", "Mensaje");
                }
            }
        }
	return retorno;
    }
    
    private void btnGrabar_actionPerformed(ActionEvent e) {
	if (saveData()) {
	    parentList.recargarDatos();
	    getParentInternalFrame().close();
	}
    }
    
    public void setParentList(CarsMgmt _parentList) {
        parentList = _parentList;
    }

    private void cargarPanel() {
        taObservaciones.setText(car.getObservaciones());
        tfFechaBaja.setValue("" + Proced.setFormatDate(car.getFechaBaja(),true));
    }

    public void setCar(Car _car) {
	car = _car;
        cargarPanel();
    }
}
