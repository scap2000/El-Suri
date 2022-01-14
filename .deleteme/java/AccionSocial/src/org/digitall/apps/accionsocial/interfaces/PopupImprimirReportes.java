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
 * PopupImprimirReportes.java
 *
 * */
package org.digitall.apps.accionsocial.interfaces;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.border.EtchedBorder;

import org.digitall.apps.accionsocial.classes.Coordinador;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;


public class PopupImprimirReportes extends JPopupMenu{

    // se ocupan items para cada menu o columna

    private JMenuItem miEntregasDia  = new JMenuItem("Imprimir Entregas del Día");
    private JMenuItem miListadoPlanSocialPancitas  = new JMenuItem("Imprimir Listado de Beneficiarios de Plan Social...");
    private JMenuItem miListadoBeneficiarios  = new JMenuItem("Imprimir Listado de Beneficiarios de Planes Sociales");
    private JMenuItem miExit = new JMenuItem("Salir");
    Coordinador coordinador;
    
    public PopupImprimirReportes(Coordinador _coordinador) {
        coordinador = _coordinador;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setBackground(new Color(0, 132, 198));
        this.add(miListadoBeneficiarios);
        this.add(miListadoPlanSocialPancitas);
	this.add(miEntregasDia);
        this.add(miExit);
	miEntregasDia.setForeground(Color.white);
	miListadoBeneficiarios.setForeground(Color.white);
        miListadoPlanSocialPancitas.setForeground(Color.white);
	miExit.setForeground(Color.white);
	this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	miEntregasDia.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    clickEntregasDia();
		}
	    });
	miListadoBeneficiarios.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    clickListadoBeneficiarios();
		}
	    });
        
        miListadoPlanSocialPancitas.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    clickListadoBeneficiariosPlanSocialPancitas();
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
    
    private void clickEntregasDia() {
        BasicReport report = new BasicReport(PanelBusquedaPersona.class.getResource("xml/EntregasDelDia.xml"));
        report.addTableModel("accionsocial.xmlGetEntregasDelDia", "");
        report.setProperty("title", "ENTREGAS DEL DÍA");
        report.doReport();
    }
    
    private void clickListadoBeneficiarios() {
        BasicReport report = new BasicReport(PanelBusquedaPersona.class.getResource("xml/ListadoBeneficiarios.xml"));
        report.addTableModel("accionsocial.xmlgetbeneficiariosplanessociales", "");
        report.setProperty("title", "Listado de Beneficiarios");
        report.doReport();
    }
    
    private void clickListadoBeneficiariosPlanSocialPancitas() {
        CBInput cbPlanesSociales = new CBInput(0, "Plan Social", false);
        cbPlanesSociales.loadJCombo(LibSQL.exFunction("accionsocial.getallplanessociales", ""));
        Vector<String> _columns = new Vector<String>();
        for (int i = 0; i < cbPlanesSociales.getCombo().getItemsVector().size(); i++) {
            if (cbPlanesSociales.getCombo().getItemsVector().elementAt(i).toString().length() > 0) {
                _columns.add(cbPlanesSociales.getCombo().getItemsVector().elementAt(i).toString());
            }
        }
        String _planSocialSeleccionado = ((String)JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Seleccione el Plan Social", "Planes Sociales", JOptionPane.QUESTION_MESSAGE, null, _columns.toArray(), cbPlanesSociales.getSelectedItem()));
        String xml = "";
        String consulta = "";
        if (_planSocialSeleccionado != null) {
            cbPlanesSociales.setSelectedValue(_planSocialSeleccionado);
            if (Integer.parseInt(cbPlanesSociales.getSelectedValue().toString()) == 1) { //1: id plan social NUTRIVIDA
                xml = "xml/ListadoBeneficiariosNutrivida.xml"; 
                consulta = "accionsocial.xmlgetbeneficiariosplansocialnutrivida";
            } else if (Integer.parseInt(cbPlanesSociales.getSelectedValue().toString()) == 2) { //2: id plan social PANCITAS
                xml = "xml/ListadoBeneficiariosPlanSocialPancitas.xml";
                consulta = "accionsocial.xmlgetbeneficiariosplansocialpancitas";
            } else if (Integer.parseInt(cbPlanesSociales.getSelectedValue().toString()) == 3) { //3: id plan social CELIACOS
                xml = "xml/ListadoBeneficiariosPlanSocialCeliacos.xml";
                consulta = "accionsocial.xmlgetbeneficiariosplansocialceliacos";
            } else if (Integer.parseInt(cbPlanesSociales.getSelectedValue().toString()) == 4) { //4: id plan social TBC
                xml = "xml/ListadoBeneficiariosPlanSocialTBC.xml";
                consulta = "accionsocial.xmlgetbeneficiariosplansocialtbc";
            } else if (Integer.parseInt(cbPlanesSociales.getSelectedValue().toString()) == 5) { //5: id plan social TARJETA SOCIAL
                xml = "xml/ListadoBeneficiariosPlanSocialTarjetaSocial.xml";
                consulta = "accionsocial.xmlgetbeneficiariosplansocialtarjetasocial";
            }
            BasicReport report = new BasicReport(PanelBusquedaPersona.class.getResource(xml));
            report.addTableModel(consulta, cbPlanesSociales.getSelectedValue());
            report.setProperty("title", "Beneficiarios del Plan Social");
            report.setProperty("title1", _planSocialSeleccionado);
            report.doReport();
        }
    }
    
    private void clickExit(MouseEvent e) {
	
    }
    
    public int getAlto() {
	return(this.getComponentCount() * 21);
    }
}

