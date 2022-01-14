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
 * PanelBusquedaBien.java
 *
 * */
package org.digitall.apps.taxes092.interfases;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.digitall.apps.taxes.interfases.cadastraladmin.PanelDesvincularTitulares;
import org.digitall.apps.taxes092.classes.Bien;
import org.digitall.apps.taxes092.classes.Coordinador;
import org.digitall.apps.taxes092.classes.Printer;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicTextArea;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;

public class PanelBusquedaBien extends BasicPanel {

    private BasicPanel panelAyuda = new BasicPanel();
    private BasicPanel panelCentroBusqueda = new BasicPanel();
    private BasicTextArea taAyuda = new BasicTextArea();
    private TFInput tfContribuyente = new TFInput(DataTypes.STRING,"Contribuyente",false);
    private TFInput tfDni = new TFInput(DataTypes.INTEGER,"D.N.I.",false);
    private TFInput tfCatastroDominio = new TFInput(DataTypes.STRING,"Catastro/Dominio",false);
    private BasicLabel lblTitulo = new BasicLabel();
    private int[] sizeColumnList = { 118, 126, 400, 130,60 };
    private Vector dataRow = new Vector();
    private Vector headerList = new Vector();
    private GridPanel grillaCatastrosDominios = new GridPanel(50000, sizeColumnList, "Catastros/Dominios", dataRow){
	 public void finishLoading() {
	     controlBotones();
	 }
     }
     ;
    private FindButton btnBuscar = new FindButton();
    private PrintButton btnImpPP = new PrintButton();
    private JSeparator jsSeparador = new JSeparator();
    private Bien bien;
    private int idBien = -1;
    private int tipoBien = -1;
    private Coordinador coordinador;
    private Printer printer = new Printer();
    private PrintButton btnImprimirHistorial = new PrintButton();
    
    private String tipoBienSel = "";
    private String numeroBien = "";

    public PanelBusquedaBien(Coordinador _coordinador) {
	coordinador = _coordinador;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout( null );
	this.setSize(new Dimension(845, 475));
	this.setPreferredSize(new Dimension(845, 475));
	panelAyuda.setBounds(new Rectangle(0, 0, 215, 475));
	panelAyuda.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	panelAyuda.setLayout(null);
	panelAyuda.setSize(new Dimension(215, 475));
	lblTitulo.setText("Seleccion de Catastro/Dominio");
	lblTitulo.setBounds(new Rectangle(5, 5, 205, 20));
	lblTitulo.setBackground(Color.black);
	lblTitulo.setOpaque(true);
	lblTitulo.setFont(new Font("Dialog", 1, 11));
	lblTitulo.setHorizontalTextPosition(SwingConstants.CENTER);
	lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	taAyuda.setBounds(new Rectangle(5, 30, 205, 440));
	taAyuda.setSize(new Dimension(205, 440));
	taAyuda.setText("* Busque el Catastro o Dominio escribiendo el nombre del contribuyente, el DNI del mismo o el número de Catastro o Dominio.\n \n* Seleccione el Catastro o Dominio deseado y presione el botón siguiente.");
	panelCentroBusqueda.setBounds(new Rectangle(220, 0, 620, 75));
	panelCentroBusqueda.setLayout(null);
	tfContribuyente.setBounds(new Rectangle(10, 20, 270, 35));
	tfDni.setBounds(new Rectangle(300, 20, 120, 35));
	tfCatastroDominio.setBounds(new Rectangle(440, 20, 115, 35));
	grillaCatastrosDominios.setBounds(new Rectangle(220, 80, 620, 345));
	grillaCatastrosDominios.getTable().setSelectionForeground(new Color(255, 247, 214));
	btnBuscar.setBounds(new Rectangle(575, 30, 30, 25));
	btnImpPP.setBounds(new Rectangle(220, 445, 30, 25));
        btnImpPP.setToolTipText("Imprimir listado de Planes de Pago");
	btnImpPP.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    btnImpPP_actionPerformed(e);
		}
	    }
	);
        btnImprimirHistorial.setToolTipText("Ver el historial de Pagos");
        btnImprimirHistorial.setBounds(new Rectangle(260, 445, 30, 25));
        btnImprimirHistorial.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnImprimirHistorial_actionPerformed(e);
                }
            }
        );
	jsSeparador.setBounds(new Rectangle(220, 435, 615, 2));
	panelAyuda.add(taAyuda, null);
	panelAyuda.add(lblTitulo, null);
	panelCentroBusqueda.add(btnBuscar, null);
	panelCentroBusqueda.add(tfCatastroDominio, null);
	panelCentroBusqueda.add(tfDni, null);
	panelCentroBusqueda.add(tfContribuyente, null);
	this.add(jsSeparador, null);
	this.add(grillaCatastrosDominios, null);
	this.add(btnImpPP, null);
        this.add(btnImprimirHistorial, null);
        this.add(panelCentroBusqueda, null);
	this.add(panelAyuda, null);
	panelCentroBusqueda.setBorder(BorderPanel.getBorderPanel("Buscar Catastros/Dominios"));
	taAyuda.setEditable(false);
	setHeaderList();
	tfContribuyente.getTextField().addKeyListener(new KeyAdapter() {

						   public void keyTyped(KeyEvent e) {
						       if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
							   refresh();
						       }
						   }

					       }
	);
	tfDni.getTextField().addKeyListener(new KeyAdapter() {

						   public void keyTyped(KeyEvent e) {
						       if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
							   refresh();
						       }
						   }

					       }
	);
	tfCatastroDominio.getTextField().addKeyListener(new KeyAdapter() {

						   public void keyTyped(KeyEvent e) {
						       if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
							   refresh();
						       }
						   }

					       }
	);
	btnBuscar.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnBuscar_actionPerformed(e);
			      }

			  }
	);
        btnImprimirHistorial.setEnabled(false);
        btnImprimirHistorial.setToolTipText("Seleccione un Catastro/Dominio para ver el historial de pagos");
    }
    
    public void refresh() {
	grillaCatastrosDominios.refresh("'"+ tfContribuyente.getValue() +"','"+ tfDni.getValue() +"','"+ tfCatastroDominio.getValue() +"'");
    }
    
    private void setHeaderList() {
	headerList.removeAllElements();	
	headerList.addElement("*");
	headerList.addElement("*");
        headerList.addElement("Tipo");
        headerList.addElement("Catastro/Dominio");
        headerList.addElement("Titular");
        headerList.addElement("DNI");
	headerList.addElement("Es Rural");
	
	grillaCatastrosDominios.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent e) {
					       if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						   //Cargar el bien
						   seleccionarBien();
                                                   btnImprimirHistorial.setEnabled(true);
                                                   tipoBienSel = "";
					           numeroBien = "";
                                                   if (coordinador.getBien().esCatastro()) {
                                                       tipoBienSel = "Catastro";
                                                       numeroBien = "" + coordinador.getBien().getCatastro().getCatastro();
                                                   } else {
                                                       tipoBienSel = "Dominio";
                                                       numeroBien = coordinador.getBien().getAutomotor().getDominio();
                                                   }
					           btnImprimirHistorial.setToolTipText("Ver el historial de Pagos del " + tipoBienSel + ": " + numeroBien);
					           coordinador.permitirAvance(true);
					       } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
					       //Cargar el bien y pasar decirle al coordinador que pase al siguiente paso
						seleccionarBien();
						coordinador.siguiente();
					       }
					   }

				       }
	);
	grillaCatastrosDominios.getTable().addKeyListener(new KeyListener() {

		public void keyTyped(KeyEvent e) {
		    seleccionarBien();
		}

		public void keyPressed(KeyEvent e) {
		    seleccionarBien();
		}

		public void keyReleased(KeyEvent e) {
		    seleccionarBien();
		}
		
	        public void seleccionarBien() {
	            setBien();
                    coordinador.getTaxesMgmt().mostrarProblema();
	            coordinador.setCabecera();
	        }
	    }
	);
	grillaCatastrosDominios.setParams("taxes.getAllCatastrosDominios", "'',-1,''", headerList);
    }
    
    public void setBien(){
	idBien = Integer.parseInt(dataRow.elementAt(0).toString());
	tipoBien = Integer.parseInt(dataRow.elementAt(1).toString());
	 if (tipoBien == 3){
	     tipoBien = bien.TIPO_AUTOMOTOR;  
	 }else {
	     tipoBien = bien.TIPO_CATASTRO;  
	 }
	 coordinador.getBien().cargarse(idBien,tipoBien,true);
    }
    
    /**
    * Funcion: busqueda de catastros/dominios segun filtros aplicados
    * */
    private void btnBuscar_actionPerformed(ActionEvent e) {
	refresh();
    }

    /**
     * Funcion: Imprime reporte con todos los planes de pago actuales
     * */
    private void btnImpPP_actionPerformed(ActionEvent e) {
	printer.imprimirTodosPlanesDePagos();
    }
    
    private void btnImprimirHistorial_actionPerformed(ActionEvent e) {
        PanelPagosBienes panelHistorial = new PanelPagosBienes(numeroBien, coordinador.getBien().esCatastro());
        ExtendedInternalFrame container = new ExtendedInternalFrame("Historial de pagos");
        container.setCentralPanel(panelHistorial);
        container.show();
    }
    
    private void controlBotones(){
	coordinador.permitirAvance(false);
        btnImprimirHistorial.setEnabled(false);
        btnImprimirHistorial.setToolTipText("Seleccione un Catastro/Dominio para ver el historial de pagos");
    }

    public void setBien(Bien bien) {
	this.bien = bien;
    }

    public Bien getBien() {
	return bien;
    }
    
    public void iniciarPanel(){
	coordinador.permitirAvance(false);
	tfCatastroDominio.getTextField().requestFocus();
    }
    
    private void seleccionarBien(){
        setBien();
        coordinador.mostrarProblema();
        coordinador.setCabecera();
    }
}
