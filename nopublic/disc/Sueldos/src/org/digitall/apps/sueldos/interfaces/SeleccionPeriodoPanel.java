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
 * SeleccionPeriodoPanel.java
 *
 * */
package org.digitall.apps.sueldos.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import java.awt.event.ItemListener;

import java.util.Vector;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class SeleccionPeriodoPanel extends BasicPrimitivePanel{
    
    private BasicPanel content = new BasicPanel();
    private CBInput cbMesesLiquidaciones = new CBInput(0,"Mes",false);
    private CBInput cbAniosLiquidaciones= new CBInput(0,"Año",false);
    private PrintButton btnPrintNota = new PrintButton();
    private CloseButton btnClose = new CloseButton();
    private Vector mesesVector = new Vector();
    private Vector aniosVector = new Vector();


    public SeleccionPeriodoPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(310, 97));
	content.setLayout(null);
	content.setBorder(BorderPanel.getBorderPanel(""));
	cbMesesLiquidaciones.setBounds(new Rectangle(25, 10, 135, 40));
        cargarVectorAnios();
	cargarAnios();
        cargarVectorMeses();
        cargarMeses();
	cbMesesLiquidaciones.autoSize();
	
	btnPrintNota.setToolTipText("Imprimir Liquidación");   
	btnClose.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
					    btnClose_actionPerformed(e);
					}

				    }
	);
	cbAniosLiquidaciones.setBounds(new Rectangle(190, 10, 95, 40));
	btnPrintNota.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
					    btnPrintNota_actionPerformed(e);
					}

				    }
	);
	content.add(cbAniosLiquidaciones, null);
	content.add(cbMesesLiquidaciones, null);
	addButton(btnClose);
	addButton(btnPrintNota);
    	this.add(content, BorderLayout.CENTER);
	loadDefaultDate();
        cbAniosLiquidaciones.getCombo().addItemListener(new ItemListener() {
            public void itemStateChanged (ItemEvent evt) {
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                    cargarMeses();
                }
            }
        });
    }
    
    private void cargarMeses(){
        cbMesesLiquidaciones.loadJCombo("sueldos.getMesesLiquidaciones","" + cbAniosLiquidaciones.getSelectedItem());
    }
    
    private void cargarAnios(){
        for (int i = 0; i < aniosVector.size(); i++) {
            cbAniosLiquidaciones.addItem(aniosVector.elementAt(i).toString());
        }
    }
    
    private void cargarVectorAnios(){
        aniosVector = LibSQL.getVector("sueldos.getAniosLiquidaciones","");
    }
    
    private void loadDefaultDate (){
        cbAniosLiquidaciones.getCombo().setSelectedIndex(cbAniosLiquidaciones.getCombo().getItemCount() - 1);
	cbMesesLiquidaciones.getCombo().setSelectedIndex(cbMesesLiquidaciones.getCombo().getItemCount() - 1);
    }
    
    private void cargarVectorMeses(){
        //cbMesesLiquidaciones.loadJCombo("sueldos.getMesesLiquidaciones","" + cbAniosLiquidaciones.getSelectedItem());
    }
    
    private void btnPrintNota_actionPerformed(ActionEvent e) {
	imprimir();
	this.getParentInternalFrame().dispose();
    }
    
    private void imprimir(){
	BasicReport report = new BasicReport(LiquidacionSueldosMain.class.getResource("xml/RecibosDeSueldo.xml"));
	int mesSelected = Integer.parseInt(cbMesesLiquidaciones.getSelectedValue().toString());
	int anio = Integer.parseInt(cbAniosLiquidaciones.getSelectedItem().toString());
	String params = ""+ anio +","+ mesSelected;
	report.addTableModel("sueldos.xmlGetLiquidacionMensual", params);
	report.setProperty("periodo",cbMesesLiquidaciones.getSelectedItem().toString()  + " " + cbAniosLiquidaciones.getSelectedItem().toString());
	report.doReport();
    }
    
    private int mesSeleccionado(){
	boolean encontrado = false;
	int i = 0;
	while ((!encontrado) && (i < mesesVector.size())){
	    if (mesesVector.elementAt(i).toString().toUpperCase().equals(cbMesesLiquidaciones.getSelectedItem().toString().toUpperCase())) {
		encontrado = true;
	    } else {
		i++;
	    }
	}
	
	if (!encontrado) {
	    i = -2;
	}
	
	return (i + 1);
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }
}


