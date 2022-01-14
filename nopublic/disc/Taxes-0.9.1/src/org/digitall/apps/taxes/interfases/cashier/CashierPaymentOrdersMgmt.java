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
 * CashierPaymentOrdersMgmt.java
 *
 * */
package org.digitall.apps.taxes.interfases.cashier;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.JMoneyEntry;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;

public class CashierPaymentOrdersMgmt extends BasicPrimitivePanel {

    private BasicPanel content = new BasicPanel();
    private BasicPanel jpPanelNorte = new BasicPanel();
    private BasicPanel jpPanelCentral = new BasicPanel();
    private BasicPanel jpPanelSur = new BasicPanel();
    
    private int[] ordenesDePagosColumnSize = { 26, 80, 300, 182 };
    private Vector ordenesDePagosDataRow = new Vector();
    private GridPanel ordenesDePagosListPanel = new GridPanel(1000, ordenesDePagosColumnSize, "Órdenes de Pago", ordenesDePagosDataRow);
    private Vector pagosHeader = new Vector();

    private int idPayment = -1;

    private TFInput tfNroOrdenPago = new TFInput(DataTypes.INTEGER, "Nº Orden de Pago", false);
    private TFInput tfFecha = new TFInput(DataTypes.SIMPLEDATE, "Fecha", false);
    private TFInput tfProveedor = new TFInput(DataTypes.STRING, "Proveedor", false);
    
    private FindButton btnBusqueda = new FindButton();
    private SaveDataButton btnRegistrarPagos = new SaveDataButton();

    private String error = "";
    
    private JMoneyEntry tfTotalEfectivo = new JMoneyEntry();
    private JMoneyEntry tfTotalCheques = new JMoneyEntry();
    private JMoneyEntry tfMontoTotal = new JMoneyEntry();
    
    private JLabel lblEfectivo = new JLabel();
    private JLabel lblCheques = new JLabel();
    private JLabel lblMontoTotal = new JLabel();

    public CashierPaymentOrdersMgmt() {
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
	jpPanelCentral.setPreferredSize(new Dimension(700, 170));
	jpPanelNorte.setLayout(null);
	jpPanelNorte.setBorder(BorderPanel.getBorderPanel("Búsqueda"));
        jpPanelSur.setLayout(null);
        jpPanelSur.setBorder(BorderPanel.getBorderPanel("Totales"));
	tfNroOrdenPago.setBounds(new Rectangle(55, 20, 115, 35));
	tfProveedor.setBounds(new Rectangle(285, 20, 335, 35));
	btnBusqueda.setBounds(new Rectangle(625, 35, 35, 20));
	btnBusqueda.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnBusqueda_actionPerformed(e);
		}
	    }
	);
	content.setLayout(new BorderLayout());
	content.setPreferredSize(new Dimension(700, 500));
	content.setSize(new Dimension(700, 500));
	
        jpPanelNorte.setPreferredSize(new Dimension(700, 65));
	jpPanelNorte.setSize(new Dimension(700, 60));
	
        jpPanelSur.setPreferredSize(new Dimension(700, 200));
	jpPanelSur.setSize(new Dimension(700, 200));
	
	btnRegistrarPagos.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnRegistrarPagos_actionPerformed(e);
		}

	    }
	);

	this.setContent(content);
	addButton(btnRegistrarPagos);

	btnRegistrarPagos.setToolTipText("Confirmar Orden de Pago");

	content.add(jpPanelNorte, BorderLayout.NORTH);
	content.add(ordenesDePagosListPanel, BorderLayout.CENTER);
        content.add(jpPanelSur, BorderLayout.SOUTH);
	btnRegistrarPagos.setEnabled(false);

        tfTotalEfectivo.setBounds(new Rectangle(300, 30, 345, 35));
        tfTotalEfectivo.setFont(new Font("Dialog", 1, 20));
        tfTotalEfectivo.setForeground(Color.black);
	tfTotalEfectivo.setEditable(false);
        
        tfTotalCheques.setBounds(new Rectangle(300, 85, 345, 35));
        tfTotalCheques.setFont(new Font("Dialog", 1, 20));
        tfTotalCheques.setForeground(Color.black);
	tfTotalCheques.setEditable(false);
        
        tfMontoTotal.setBounds(new Rectangle(300, 140, 345, 35));
        tfMontoTotal.setFont(new Font("Dialog", 1, 25));
        tfMontoTotal.setForeground(Color.black);
	tfMontoTotal.setEditable(false);
        
        
        lblEfectivo.setText("EFECTIVO");
        lblEfectivo.setBounds(new Rectangle(80, 30, 180, 35));
        lblEfectivo.setBorder(BorderFactory.createLineBorder(new Color(12, 12, 33), 1));
        lblEfectivo.setOpaque(true);
        lblEfectivo.setFont(new Font("Dialog", 1, 20));
        lblEfectivo.setBackground(Color.white);
        lblEfectivo.setHorizontalAlignment(SwingConstants.CENTER);
        lblEfectivo.setHorizontalTextPosition(SwingConstants.CENTER);

        
        lblCheques.setText("CHEQUES");
        lblCheques.setBorder(BorderFactory.createLineBorder(new Color(12, 12, 33), 1));
        lblCheques.setBounds(new Rectangle(80, 85, 180, 35));
        lblCheques.setOpaque(true);
        lblCheques.setBackground(Color.white);
        lblCheques.setFont(new Font("Dialog", 1, 20));
        lblCheques.setHorizontalAlignment(SwingConstants.CENTER);
        lblCheques.setHorizontalTextPosition(SwingConstants.CENTER);
        
        lblMontoTotal.setText("MONTO TOTAL");
        lblMontoTotal.setBorder(BorderFactory.createLineBorder(new Color(12, 12, 33), 1));
        lblMontoTotal.setBounds(new Rectangle(80, 140, 180, 35));
        lblMontoTotal.setOpaque(true);
        lblMontoTotal.setBackground(Color.white);
        lblMontoTotal.setFont(new Font("Dialog", 1, 20));
        lblMontoTotal.setHorizontalAlignment(SwingConstants.CENTER);
        lblMontoTotal.setHorizontalTextPosition(SwingConstants.CENTER);
        
        
        tfNroOrdenPago.addKeyListener(new KeyAdapter() {
                
                public void keyTyped(KeyEvent e) {
                    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                        busqueda();
                    }
                }
            }
        );
        tfProveedor.addKeyListener(new KeyAdapter() {
                
                public void keyTyped(KeyEvent e) {
                    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                        busqueda();
                    }
                }
            }
        );

	jpPanelSur.add(lblEfectivo, null);
	jpPanelSur.add(tfTotalEfectivo,null);
	jpPanelSur.add(lblCheques, null);
	jpPanelSur.add(tfTotalCheques,null);
	jpPanelSur.add(lblMontoTotal, null);

	jpPanelSur.add(tfMontoTotal,null);
	jpPanelNorte.add(tfNroOrdenPago);
	jpPanelNorte.add(tfFecha);
	jpPanelNorte.add(tfProveedor);
	jpPanelNorte.add(btnBusqueda);
	tfFecha.setValue(Proced.setFormatDate(Environment.currentDate,true));
	tfFecha.setBounds(new Rectangle(185, 20, 85, 35));
	setPagosHeader();
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
    }

 
    private void setPagosHeader() {
	pagosHeader.removeAllElements();
	pagosHeader.addElement("*"); 
	pagosHeader.addElement("Nº O.P.");
	pagosHeader.addElement("Fecha");
        pagosHeader.addElement("Proveedor"); 
        pagosHeader.addElement("Monto"); 
	pagosHeader.addElement("*"); 
	pagosHeader.addElement("*"); 
	pagosHeader.addElement("*"); 
	pagosHeader.addElement("*"); 

	ordenesDePagosListPanel.getTable().addMouseListener(new MouseAdapter() {

						 public void mouseClicked(MouseEvent e) {
						     loadSelectedObject();
						     if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {

						     } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {

						     }
						 }

					     }
	);
	/*String params = tfNroOrdenPago.getInteger() + "'" + Proced.setFormatDate(tfFecha.getDate(),false) + "','" + tfProveedor.getString() + "'"; 
	ordenesDePagosListPanel.setParams("cashier.getAllOrdenesDePago", params, pagosHeader);*/
    }

    
    private void refreshPagos(String _params) {
	ordenesDePagosListPanel.refresh(_params);
	btnRegistrarPagos.setEnabled(false);
    }
    
    private void loadSelectedObject() {
	if (!ordenesDePagosDataRow.isEmpty()) {
	    idPayment =  Integer.parseInt(ordenesDePagosDataRow.elementAt(0).toString());
	    btnRegistrarPagos.setEnabled(true);
	}
    }

    private void btnRegistrarPagos_actionPerformed(ActionEvent e) {
	CashierPrinter.printVoucher(idPayment,true);
    }

    private void busqueda(){
        if(control()){
            String params = "";
            if((tfFecha.getDate().equals(""))&& (tfProveedor.getDate().equals(""))){ // '' AND ''
                params = "'',''";
            }else{
                if(tfProveedor.getDate().equals("")){ // 'FECHAINICIO' AND ''
                    params = "'"+tfFecha.getDate()+"','"+tfFecha.getDate()+"'";
                    params = "'"+Proced.setFormatDate(tfFecha.getDate(),false)+"','"+Proced.setFormatDate(tfFecha.getDate(),false)+"'"; 
                }else{// 'FECHAINICIO' AND 'FECHAFIN'
                    params = "'"+Proced.setFormatDate(tfFecha.getDate(),false)+"','"+Proced.setFormatDate(tfProveedor.getDate(),false)+"'";
                }
            }
            refreshPagos(params);
        }else{
            Advisor.messageBox(error, "Error");
        }
    }

    private void btnBusqueda_actionPerformed(ActionEvent e) {
	//busqueda();
        controlColores();
    }
    
    private boolean control(){
	boolean retorno = true;
	if((tfFecha.getDate().equals(""))&& (!tfProveedor.getDate().equals(""))){
	    error = "El campo \"Fecha Inicio\" no debe ser vacío";
	    retorno = false;
	}else{
	    if(Proced.compareDates(Proced.setFormatDate(tfFecha.getDate(),false),Proced.setFormatDate(tfProveedor.getDate(),false)) == 2){
	        error = "La Fecha de Inicio no puede ser mayor que la Fecha de Fin";
	        retorno = false;
	    }
	}
	return retorno;
    }
    
    private void controlColores(){
        if (tfTotalEfectivo.getAmount() > 0) {
            tfTotalEfectivo.setBackground(Color.green);
        } else {
            tfTotalEfectivo.setBackground(Color.yellow);
        }

        if (tfTotalCheques.getAmount() > 0) {
            tfTotalCheques.setBackground(Color.green);
        } else {
            tfTotalCheques.setBackground(Color.yellow);
        }
        
        if (tfMontoTotal.getAmount() > 0) {
            tfMontoTotal.setBackground(Color.red);
        } else {
            tfMontoTotal.setBackground(Color.yellow);
        }
    }
    
    
}


