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
 * InformeBajasPanel.java
 *
 * */
package org.digitall.apps.personalfiles.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import org.digitall.common.personalfiles.classes.Persona;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.environment.Environment;

public class InformeBajasPanel extends BasicPrimitivePanel{
    
    private BasicPanel content = new BasicPanel();
    private CBInput meses = new CBInput(0,"Mes",false);
    private CBInput cbAnios = new CBInput(0,"Año",false);
    private PrintButton btnPrintNota = new PrintButton();
    private CloseButton btnClose = new CloseButton();
    private Vector mesesVector = new Vector();


    public InformeBajasPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(310, 139));
	content.setLayout(null);
	content.setBorder(BorderPanel.getBorderPanel(""));
	meses.setBounds(new Rectangle(25, 10, 265, 35));
	cargarVectorMeses();
	cargarMeses();
	cargarAnios();
	meses.autoSize();
	
	btnPrintNota.setToolTipText("Imprimir Informe de bajas");   
	btnClose.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
					    btnClose_actionPerformed(e);
					}

				    }
	);
	cbAnios.setBounds(new Rectangle(25, 50, 265, 35));
	btnPrintNota.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
					    btnPrintNota_actionPerformed(e);
					}

				    }
	);
	content.add(cbAnios, null);
	content.add(meses, null);
	addButton(btnClose);
	addButton(btnPrintNota);
    	this.add(content, BorderLayout.CENTER);
	cbAnios.autoSize();
    }
    
    private void cargarMeses(){
	for(int i = 0; i < mesesVector.size(); i++){
	    meses.addItem(mesesVector.elementAt(i).toString());
	}
    }
    
    private void cargarAnios(){
	for(int i = 1; i < 120; i++){
	    cbAnios.addItem(""+(2008 + i));
	}
    }
    
    private void cargarVectorMeses(){
	mesesVector.add("Enero");
	mesesVector.add("Febrero");
	mesesVector.add("Marzo");
	mesesVector.add("Abril");
	mesesVector.add("Mayo");
	mesesVector.add("Junio");
	mesesVector.add("Julio");
	mesesVector.add("Agosto");
	mesesVector.add("Septiembre");
	mesesVector.add("Octubre");
	mesesVector.add("Noviembre");
	mesesVector.add("Diciembre");
    }
    private void btnPrintNota_actionPerformed(ActionEvent e) {
	imprimir();
    }
    
    private void imprimir(){
	BasicReport report = new BasicReport(DependenciaTree.class.getResource("xml/InformeBajas.xml"));
	report.setProperty("anio",cbAnios.getSelectedItem().toString());
	report.setProperty("mes",meses.getSelectedItem().toString());
	int mesSelected = meses.getSelectedIndex() + 1;
	int anio = Integer.parseInt(cbAnios.getSelectedItem().toString());
	String params = ""+mesSelected + ","+anio;
	report.addTableModel("resourcescontrol.xmlgetrecursosbaja",params);
	report.doReport();
    }
    
    
    private String getNameIntendente(){
	String resultado = "";
	Persona persona = new Persona();
	persona.setIdPerson(290);
	persona.retrieveData();
	resultado = "" + persona.getFirstName() + " " + persona.getLastName();
	System.out.println("intendente: "+resultado);
	return(resultado);
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }
}
