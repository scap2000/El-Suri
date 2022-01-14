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
 * PopupPrinterCars.java
 *
 * */
package org.digitall.apps.taxes.interfases.carsadmin;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.border.EtchedBorder;

import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.environment.Environment;

public class PopupPrinterCars extends JPopupMenu{

    // se ocupan items para cada menu o columna

    private JMenuItem miAutomotoresVigentes  = new JMenuItem("Imprimir Padrón Automotor");
    private JMenuItem miAutomotoresBaja  = new JMenuItem("Imprimir Dominios con Baja");
    private JMenuItem miAutomotoresApartirDe   = new JMenuItem("Imprimir Dominios registrados a partir de...");
    private JMenuItem miExit = new JMenuItem("Salir");
    
    public PopupPrinterCars() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setBackground(new Color(0, 132, 198));
	this.add(miAutomotoresVigentes);
	this.add(miAutomotoresBaja);
	this.add(miAutomotoresApartirDe);
	this.add(miExit);
	miAutomotoresVigentes.setForeground(Color.white);
	miAutomotoresBaja.setForeground(Color.white);
	miAutomotoresApartirDe.setForeground(Color.white);
	miExit.setForeground(Color.white);
	this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	miAutomotoresVigentes.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
		    clickAutomotoresVigentes();
		}
	    });
	miAutomotoresBaja.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
		    clickAutomotoresBaja();
		}
	    });
	miAutomotoresApartirDe.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
		    clickAutomotoresApartirDe();
		}
	    });
	miExit.addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1 && e.getButton() == e.BUTTON3) {
		    clickExit(e);              
		}
	    }
	});
    }
    
    private void clickAutomotoresVigentes(){
	getReporteAutomotores(true);
    }
    
    private void clickAutomotoresBaja(){
	getReporteAutomotores(false);
    }
    
    private void clickAutomotoresApartirDe() {
	String _title = JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "A partir del año ", "Informe", JOptionPane.QUESTION_MESSAGE);
	if (_title != null) {
	    try {
		int anio = Integer.parseInt(_title);
	        BasicReport report = new BasicReport(CarsList.class.getResource("xml/PadronAutomotorApartirDe.xml"));
	        report.addTableModel("taxes.xmlgetpadronautomotorapartirde", ""+ anio );
	        report.setProperty("title", "Dominios registrados a partir del año "+ anio);
	        report.doReport();	
	    } catch(NumberFormatException e) {
	        Advisor.messageBox("El año ingresado debe ser un número entero","Error");
	    }
	    
	}
    }
    
    private void getReporteAutomotores(boolean _vigente){
	BasicReport report = new BasicReport(CarsList.class.getResource("xml/PadronAutomotor.xml"));
	report.addTableModel("taxes.xmlGetPadronAutomotor", ""+ _vigente );
	if (_vigente) {
	    report.setProperty("title", "Padrón Automotor Vigente");
	} else {
	    report.setProperty("title", "Padrón Automotor con baja");
	}
	report.doReport();
    }
    
    private void clickExit(MouseEvent e){
	
    }
    
    public int getAlto(){
	return(this.getComponentCount() * 21);
    }
}

