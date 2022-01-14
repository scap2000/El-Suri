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
 * LiquidacionSueldosMain.java
 *
 * */
package org.digitall.apps.sueldos.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.sql.ResultSet;

import javax.imageio.ImageIO;

import org.digitall.apps.sueldos.classes.LiquidacionSueldos;
import org.digitall.common.reports.BasicReport;
import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddComboButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;


public class LiquidacionSueldosMain extends BasicPrimitivePanel{

    private ReciboSueldoPanel reciboPanel = new ReciboSueldoPanel();
    private CloseButton btnClose = new CloseButton();       
    private AddComboButton btnAddConcept = new AddComboButton();
    private SaveDataButton btnSave = new SaveDataButton();
    private BasicPanel content = new BasicPanel();
    private BorderLayout border1 = new BorderLayout();
    private LiquidacionSueldos liquidacionSueldo = new LiquidacionSueldos();
    private PrintButton btnLiquidarSueldos = new PrintButton();
    private SeleccionPeriodoPanel panelSeleccionPeriodo;

    public LiquidacionSueldosMain() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	content.setPreferredSize(new Dimension(935, 545));
	content.setSize(new Dimension(935, 545));
	this.setSize(new Dimension(937, 585));
	this.setPreferredSize(new Dimension(937, 600));
	reciboPanel.setSize(new Dimension(935, 545));
	reciboPanel.setPreferredSize(new Dimension(935, 545));
	btnClose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					    btnClose_actionPerformed(e);
					}
				    }
	);
	btnAddConcept.setToolTipText("Agregar conceptos a Legajos");
        btnLiquidarSueldos.setToolTipText("Imprimir recibos de sueldos");
	btnSave.setToolTipText("Grabar liquidación mensual del Recibo");
	btnAddConcept.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					    btnAddConcept_actionPerformed(e);
					}
				    }
	);
        btnLiquidarSueldos.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            btnLiquidarSueldos_actionPerformed(e);
                                        }
                                    }
        );
	btnSave.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
				   btnSave_actionPerformed(e);
			       }
			   }
	);
	content.add(reciboPanel, border1.CENTER);
	add(content, BorderLayout.CENTER);
	addButton(btnClose);
	addButton(btnAddConcept);
	addButton(btnSave);
        addButton(btnLiquidarSueldos);
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().setIcon(true);
    }
    
    private void btnAddConcept_actionPerformed(ActionEvent e) {
	AsignacionConceptosPanel asignacionConceptos = new AsignacionConceptosPanel(reciboPanel.getLegajo());
	ExtendedInternalFrame conceptosContainer = new ExtendedInternalFrame("Asignación de Conceptos");
	conceptosContainer.setCentralPanel(asignacionConceptos);
	conceptosContainer.show();
    }
    
    
    public void setLegajo(){
	
    }
    
    private void btnSave_actionPerformed(ActionEvent e) {
	// Grabar Recibo de sueldo final
	grabarLiquidacionFinal();
    }
    
    private void grabarLiquidacionFinal(){
	//1 ver si se puede volver a hacer una liquidacion final **** 
	//2 si se puede, verificar que se encuentren todas las liquidaciones parciales correspondientes a todos los legajos en el mes
	//3 si todo esta bien entonces pasar todas las liquidaciones parciales a finales
	if(Advisor.question("Grabar","¿Desea grabar la Liquidación Mensual?")){
	    //if(liquidacionSueldo.puedeHacerseLiquidacionMensual(reciboPanel.getFecha())){
	     if(true){
		//if (liquidacionSueldo.estanTodasLiquidacionesParciales(reciboPanel.getFecha())) {
		 if (true) {
		    if (liquidacionSueldo.grabarLiquidacionesFinales(reciboPanel.getFecha())) {
		        Advisor.messageBox("Se grabó con éxito la Liquidación Mensual\ncorrespondiente al período "+Environment.currentMonth+"/"+Environment.currentYear+".","Liquidación grabada");
		    } else {
		        Advisor.messageBox("Se produjo un error al grabar los datos.","Error");
		    }
		} else {
		    Advisor.messageBox("Para realizar las liquidación final deben estar todos las liquidaciones parciales grabadas.","Aviso");
		}
	    } else {
	        Advisor.messageBox("No se puede realizar la liquidación\nya se realizó la liquidación del mes actual.","Aviso");
	    }
	}
    }
    
    private void btnLiquidarSueldos_actionPerformed(ActionEvent e) {
	panelSeleccionPeriodo = new SeleccionPeriodoPanel();
	ExtendedInternalFrame seleccionIntFrame = new ExtendedInternalFrame("Periodo");
	seleccionIntFrame.setCentralPanel(panelSeleccionPeriodo);
	seleccionIntFrame.show();
    }
}
