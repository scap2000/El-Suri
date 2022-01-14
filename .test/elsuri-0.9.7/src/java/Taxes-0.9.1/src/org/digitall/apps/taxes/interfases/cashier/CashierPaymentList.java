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
 * CashierPaymentList.java
 *
 * */
package org.digitall.apps.taxes.interfases.cashier;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.apps.taxes.classes.BoletaContribucion;
import org.digitall.apps.taxes.interfases.TaxesTGS;
import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.misc.BarCode;


public class CashierPaymentList extends BasicPrimitivePanel {

    private BasicPanel content = new BasicPanel();
    private BasicPanel northPanel = new BasicPanel();

    private BasicPanel depositoPanel = new BasicPanel();
    private BasicPanel northWestPanel = new BasicPanel();
    private BasicPanel centerPanel = new BasicPanel();

    private int[] pagosColumnSize = { 226, 242, 182 };
    private Vector pagosDataRow = new Vector();
    private GridPanel pagosListPanel = new GridPanel(1000, pagosColumnSize, "Pagos", pagosDataRow);
    private Vector pagosHeader = new Vector();

    private int[] detallePagosColumnSize = {  505, 345, 142 };
    private Vector detallePagosDataRow = new Vector();
    private GridPanel detallePagosListPanel = new GridPanel(50000, detallePagosColumnSize, "Detalle de los pagos", detallePagosDataRow);
    private Vector detallePagosHeader = new Vector();

    private PrintButton btnImprimirComprobantes = new PrintButton();
    
    private BorderLayout borderLayout1 = new BorderLayout();

    private GridLayout gridLayout1 = new GridLayout();
    private GridLayout gridLayout2 = new GridLayout();

    private int idPayment = -1;

    private BasicPanel pContenedorBusqueda = new BasicPanel();
    private BasicPanel pBusqueda = new BasicPanel();

    private TFInput tfUsuario = new TFInput(DataTypes.STRING, "Usuario", false);
    private TFInput tfFechaInicio = new TFInput(DataTypes.SIMPLEDATE, "Fecha Inicio", false);
    private TFInput tfFechaFin = new TFInput(DataTypes.SIMPLEDATE, "Fecha Fin", false);
    private FindButton btnBusqueda = new FindButton();

    private BorderLayout borderLayout2 = new BorderLayout();

    private BorderLayout borderLayout3 = new BorderLayout();
    private String error = "";

    public CashierPaymentList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(700, 507));
	this.setBounds(new Rectangle(10, 10, 700, 537));
	this.setTitle("Caja");
	northWestPanel.setLayout(borderLayout1);
	northWestPanel.setPreferredSize(new Dimension(700, 170));
	centerPanel.setLayout(gridLayout1);
	gridLayout1.setColumns(1);
	gridLayout2.setColumns(1);
	pContenedorBusqueda.setLayout(borderLayout2);
	pBusqueda.setLayout(null);
	pBusqueda.setBorder(BorderPanel.getBorderPanel("Búsqueda"));
	pBusqueda.setPreferredSize(new Dimension(700, 65));
	tfUsuario.setBounds(new Rectangle(15, 20, 140, 35));
	tfFechaInicio.setBounds(new Rectangle(195, 20, 140, 35));
	tfFechaInicio.setSize(new Dimension(120, 35));
	tfFechaFin.setBounds(new Rectangle(355, 20, 120, 35));
        tfFechaInicio.getTextField().addKeyListener(new KeyAdapter() {

                                                   public void keyTyped(KeyEvent e) {
                                                       if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                                                           buscar();
                                                       }
                                                   }

                                               }
        );
        tfFechaFin.getTextField().addKeyListener(new KeyAdapter() {

                                                   public void keyTyped(KeyEvent e) {
                                                       if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                                                           buscar();
                                                       }
                                                   }

                                               }
        );
	btnBusqueda.setBounds(new Rectangle(505, 35, 35, 20));
	btnBusqueda.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnBusqueda_actionPerformed(e);
		}
	    }
	);
	northPanel.setLayout(gridLayout2);

	depositoPanel.setLayout(null);
	content.setLayout(borderLayout3);

	content.setPreferredSize(new Dimension(700, 500));
	northPanel.setPreferredSize(new Dimension(700, 170));
	//northPanel.setMinimumSize(new Dimension(170, 100));
	centerPanel.setPreferredSize(new Dimension(700, 265));

	detallePagosListPanel.setPreferredSize(new Dimension(700, 265));
	btnImprimirComprobantes.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnImprimirComprobantes_actionPerformed(e);
		}

	    }
	);

	this.add(content, null);
	addButton(btnImprimirComprobantes);

	btnImprimirComprobantes.setToolTipText("Reimprimir Comprobantes");

	centerPanel.add(detallePagosListPanel);
	northWestPanel.add(pagosListPanel, BorderLayout.CENTER);
	northPanel.add(northWestPanel);
	pBusqueda.add(btnBusqueda, null);
	pBusqueda.add(tfFechaFin, null);
	pBusqueda.add(tfFechaInicio, null);
	pBusqueda.add(tfUsuario, null);
	pContenedorBusqueda.add(pBusqueda, BorderLayout.NORTH);
	pContenedorBusqueda.add(northPanel, BorderLayout.CENTER);
	content.add(pContenedorBusqueda, BorderLayout.NORTH);
	content.add(centerPanel, BorderLayout.CENTER);

	setPagosHeader();
	setDetallePagosHeader();
	btnImprimirComprobantes.setEnabled(false);
	tfUsuario.getTextField().setEditable(false);
	tfUsuario.setValue(Environment.sessionUser);
	tfFechaInicio.setValue(Proced.setFormatDate(Environment.currentDate,true));
	/*String params = "'"+Proced.setFormatDate(tfFechaInicio.getDate(),false)+"','"+Proced.setFormatDate(tfFechaInicio.getDate(),false)+"'"; 
	refreshPagos(params);*/
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
    }

 
    private void setPagosHeader() {
	pagosHeader.removeAllElements();
	pagosHeader.addElement("*"); 
	pagosHeader.addElement("Nº Pago");
	pagosHeader.addElement("Fecha");
	pagosHeader.addElement("Monto"); 
	pagosHeader.addElement("*"); 
	pagosHeader.addElement("*"); 
	pagosHeader.addElement("*"); 
	pagosHeader.addElement("*"); 
	pagosHeader.addElement("*"); 

	pagosListPanel.getTable().addMouseListener(new MouseAdapter() {

						 public void mouseClicked(MouseEvent e) {
						     loadSelectedObject();
						     if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
							  refreshDetallePagos();
						     } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {

						     }
						 }

					     }
	);
	String params = "'"+Proced.setFormatDate(tfFechaInicio.getDate(),false)+"','"+Proced.setFormatDate(tfFechaInicio.getDate(),false)+"'"; 
	pagosListPanel.setParams("cashier.getallpagosbyfilter", params, pagosHeader);
	//pagosListPanel.setParams("cashier.getAllPagosDiario", "", pagosHeader);
    }

    
    private void setDetallePagosHeader() {

	detallePagosHeader.removeAllElements();
	detallePagosHeader.addElement("*");
	detallePagosHeader.addElement("*");
	detallePagosHeader.addElement("Comprobante");
        detallePagosHeader.addElement("Contribuyente");
	detallePagosHeader.addElement("Monto");
	

	detallePagosListPanel.getTable().addMouseListener(new MouseAdapter() {

		public void mouseClicked(MouseEvent e) {
		    
		    if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
			//LOAD DATA
		    } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
			
		    }
		}

	    }
	);
	detallePagosListPanel.setParams("cashier.getBoletasPagadas", "-1", detallePagosHeader);
    }


    private void refreshPagos(String _params) {
	pagosListPanel.refresh(_params);
	btnImprimirComprobantes.setEnabled(false);
	detallePagosListPanel.refresh("-1");
    }
    
    private void refreshDetallePagos() {
	detallePagosListPanel.refresh(""+ idPayment);
    }
    
    
    private void loadSelectedObject() {
	if (!pagosDataRow.isEmpty()) {
	    idPayment =  Integer.parseInt(pagosDataRow.elementAt(0).toString());
	    btnImprimirComprobantes.setEnabled(true);
	}
    }

    private void btnImprimirComprobantes_actionPerformed(ActionEvent e) {
	CashierPrinter.printVoucher(idPayment,true);
	// btnTest.setEnabled(true);
    }

    private void btnBusqueda_actionPerformed(ActionEvent e) {
	buscar();
    }
    
    private void buscar(){
        if(control()){
            String params = "";
            if((tfFechaInicio.getDate().equals(""))&& (tfFechaFin.getDate().equals(""))){ // '' AND ''
                params = "'',''";
            }else{
                if(tfFechaFin.getDate().equals("")){ // 'FECHAINICIO' AND ''
                    params = "'"+tfFechaInicio.getDate()+"','"+tfFechaInicio.getDate()+"'";
                    params = "'"+Proced.setFormatDate(tfFechaInicio.getDate(),false)+"','"+Proced.setFormatDate(tfFechaInicio.getDate(),false)+"'"; 
                }else{// 'FECHAINICIO' AND 'FECHAFIN'
                    params = "'"+Proced.setFormatDate(tfFechaInicio.getDate(),false)+"','"+Proced.setFormatDate(tfFechaFin.getDate(),false)+"'";
                }
            }
            refreshPagos(params);
        }else{
            Advisor.messageBox(error, "Error");
        }
    }
    
    private boolean control(){
	boolean retorno = true;
	if((tfFechaInicio.getDate().equals(""))&& (!tfFechaFin.getDate().equals(""))){
	    error = "El campo \"Fecha Inicio\" no debe ser vacío";
	    retorno = false;
	}else{
	    if(Proced.compareDates(Proced.setFormatDate(tfFechaInicio.getDate(),false),Proced.setFormatDate(tfFechaFin.getDate(),false)) == 2){
	        error = "La Fecha de Inicio no puede ser mayor que la Fecha de Fin";
	        retorno = false;
	    }
	}
	return retorno;
    }
}
