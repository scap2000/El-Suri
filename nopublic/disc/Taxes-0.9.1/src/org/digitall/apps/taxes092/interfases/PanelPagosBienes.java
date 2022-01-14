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
 * PanelPagosBienes.java
 *
 * */
package org.digitall.apps.taxes092.interfases;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.Vector;
import javax.swing.ButtonGroup;

import org.digitall.apps.taxes.interfases.cashier.CashierPrinter;
import org.digitall.common.reports.BasicReport;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;

public class PanelPagosBienes extends BasicPrimitivePanel {
    private BasicPanel panelCentro = new BasicPanel();
    private BasicPanel panelContenedor = new BasicPanel();
    private BorderLayout borderLayout1 = new BorderLayout();
    private ButtonGroup grupo = new ButtonGroup();
    private CloseButton btnClose= new CloseButton();
    private PrintButton btnImprimirPagos = new PrintButton();
    private PrintButton btnImprimirBoleta = new PrintButton();
    
    private int[] sizeColumnList = {89, 238, 321, 216, 107, 96, 123, 101};
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(50000, sizeColumnList, "Historial de Pagos Realizados", dataRow){
	 public void finishLoading() {
	     btnImprimirBoleta.setEnabled(false);
	 }
     }
     ;
    private Vector headerList = new Vector();
    private String numeroBien;
    private boolean esCatastro;
    private String tipoBien = "";
    public PanelPagosBienes(String _numeroBien, boolean _esCatastro) {
        try {
            numeroBien = _numeroBien;
            esCatastro = _esCatastro;
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        this.setSize(new Dimension(713, 367));
        this.setPreferredSize(new Dimension(660, 512));
        listPanel.setPreferredSize(new Dimension(400, 250));
        listPanel.setBounds(new Rectangle(5, 15, 700, 310));
        panelCentro.setLayout(null);
        panelCentro.setBounds(new Rectangle(0, 1, 660, 300));
        panelCentro.setBorder(BorderPanel.getBorderPanel(""));
        panelContenedor.setLayout(borderLayout1);
        panelCentro.add(listPanel, BorderLayout.CENTER);
        panelContenedor.add(panelCentro, BorderLayout.CENTER);
        this.add(panelContenedor, BorderLayout.CENTER);
        addButton(btnClose);
        addButton(btnImprimirPagos);
        addButton(btnImprimirBoleta);
        btnImprimirBoleta.setToolTipText("Reimprimir boleta");
        btnClose.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    btnClose_actionPerformed(e);
                                }
                            }
        );
        btnImprimirPagos.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    btnImprimirPagos_actionPerformed(e);
                                }
                            }
        );
        btnImprimirBoleta.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    btnImprimirBoleta_actionPerformed(e);
                                }
                            }
        );
        listPanel.getTable().addMouseListener(new MouseAdapter() {

                                           public void mouseClicked(MouseEvent e) {
                                               if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                                                   btnImprimirBoleta.setEnabled(true);
                                               } 
                                           }

                                       }
        );
        setheaderList();
        btnImprimirPagos.setToolTipText("Imprimir historial de pagos realizados por el " + tipoBien + ": " + numeroBien);
        buscarPagos();
    }
    
    private void setheaderList() {
        headerList.removeAllElements();
        headerList.addElement("*"); //idBoleta
        headerList.addElement("*"); //idTipoImpuesto
        headerList.addElement("Fecha"); 
        headerList.addElement("Boleta");
        headerList.addElement("Tipo");
        headerList.addElement("Concepto");
        headerList.addElement("($) Importe");
        headerList.addElement("($) Interés");
        headerList.addElement("($) Descuento");
        headerList.addElement("($) Total");
        if (esCatastro) {
            tipoBien = "Catastro";
        } else {
            tipoBien = "Dominio";
        }
        listPanel.setParams("taxes.getAllPagosByBien","'" +  numeroBien + "'," + esCatastro, headerList);
    }
    
    private void buscarPagos(){
        String tipoBien = "";
        boolean buscar = false;
        if (!numeroBien.equals("")) {
            if (esCatastro) {
                tipoBien = "Catastro";
            } else {
                tipoBien = "Dominio";
            }
            
            if (existeBien()) {
                listPanel.refresh("'" + numeroBien + "'," + esCatastro);
            } else {
                    Advisor.messageBox("No existe el " + tipoBien + " ingresado ", "Error");
            }
            
        } else {
            Advisor.messageBox("Debe ingresar un catastro o dominio para la búsqueda de sus pagos", "Error");
        }
    }

    private void btnBuscar_actionPerformed(ActionEvent e) {
        buscarPagos();
    }
    
    
    private void btnImprimirPagos_actionPerformed(ActionEvent e) {
        if (existeBien()) {
	    ResultSet _result = LibSQL.exFunction("taxes.xmlgetDatosBien", "'" + numeroBien + "'" + "," + esCatastro);
	    try {
		if (_result.next()) {
		    BasicReport report = new BasicReport(PanelPagosBienes.class.getResource("xml/ListadoPagosDeBien.xml"));
		    report.addTableModel("taxes.xmlgetallpagosbybien", "'" + numeroBien + "'" + "," + esCatastro);
		    String tipoBien = "";
		    if (esCatastro) {
		        tipoBien = "Catastro:";
		    } else {
		        tipoBien = "Dominio:";
		    }
		    report.setProperty("tipobien",tipoBien);
		    report.setProperty("nrobien",_result.getString("numero"));
		    report.setProperty("titular",_result.getString("titular"));
		    report.setProperty("apoderado",_result.getString("apoderado"));
		    report.setProperty("domicilio",_result.getString("domicilio"));
		    report.setProperty("cuentacorriente",_result.getString("cuentacorriente"));
		    report.doReport();    
		}
	    } catch (SQLException f) {
		f.printStackTrace();
	    }
        } else {
            Advisor.messageBox("No existe el Bien para la impresión de sus pagos ", "Error");
        }
        
    }
    
    private boolean existeBien(){
        boolean _returns = false;
        boolean buscar = false;
        if (esCatastro) {
            try {
                Integer.parseInt(numeroBien);
                buscar = true;
            } catch (Exception e) {
                // TODO: Add catch code
            }    
        } else {
            buscar = true;
        }
        
        if (buscar && LibSQL.getBoolean("taxes.existeBien", "'" + numeroBien + "'" + "," + esCatastro)) {
            _returns = true;
        } else {
            if (buscar) {
                _returns = false;
            }
        }
        return _returns;
    }
    private void btnImprimirBoleta_actionPerformed(ActionEvent e) {
        reimprimirBoleta();
    }
    
    private void reimprimirBoleta(){
        CashierPrinter.imprimirBoleta(Integer.parseInt(dataRow.elementAt(0).toString()), Integer.parseInt(dataRow.elementAt(1).toString()));
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
        this.getParentInternalFrame().close();
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {      
        super.setParentInternalFrame(_e);
        getParentInternalFrame().setInfo("Historial de pagos del "+tipoBien + " " + numeroBien);
    }
    
}
