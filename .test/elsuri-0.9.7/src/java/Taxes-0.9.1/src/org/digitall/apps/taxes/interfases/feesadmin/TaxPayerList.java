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
 * TaxPayerList.java
 *
 * */
package org.digitall.apps.taxes.interfases.feesadmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.apps.taxes.classes.BoletaPlanesDePago;
import org.digitall.apps.taxes.classes.Coordinador;
import org.digitall.apps.taxes.interfases.TaxesTGS;
import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.FirstGridButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.buttons.UnAssignButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.misc.BarCode;

public class TaxPayerList extends BasicPrimitivePanel {

    private BasicPanel content = new BasicPanel();
    private BasicPanel findPanel = new BasicPanel();
    private BasicPanel southPanel = new BasicPanel();    
    private BorderLayout borderLayout1 = new BorderLayout();
    private BasicPanel wizardPanel = new BasicPanel();

    private int[] sizeColumnList = { 118, 126, 400, 130,60 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(50000, sizeColumnList, "Catastros/Dominios", dataRow){
	public void finishLoading() {
	    controlBotones();
	}
    }
    ;
    private Vector headerList = new Vector();
    
    private AssignButton btnNext = new AssignButton();
    private FirstGridButton btnFirst = new FirstGridButton();
    private UnAssignButton btnBack = new UnAssignButton();
    private CloseButton btnClose = new CloseButton();
    private FindButton btnFind = new FindButton();
    private PrintButton btnPrint = new PrintButton();
    private PrintButton btnReport = new PrintButton();
    
    private TFInput tfName = new TFInput(DataTypes.STRING,"TaxPayer",false);
    private TFInput tfDni = new TFInput(DataTypes.INTEGER,"DNI",false);
    private TFInput tfCadastralDomain = new TFInput(DataTypes.STRING,"CadastralDomain",false);
    
    private Coordinador coodinador;
    
    public TaxPayerList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(900, 470));
	this.setPreferredSize(new Dimension(900, 500));
	this.setTitle("Catastros/Dominios");
	listPanel.setSize(new Dimension(860, 400));
	btnNext.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnNext_actionPerformed(e);
				 }

			     }
	);
	
	southPanel.setLayout(null);
	southPanel.setPreferredSize(new Dimension(860, 50));
	content.setLayout(borderLayout1);
	findPanel.setLayout(null);
	findPanel.setPreferredSize(new Dimension(860, 75));
        btnFind.setBounds(new Rectangle(845, 30, 30, 25));
	btnFind.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnFind_actionPerformed(e);
			      }

			  }
	);
	findPanel.add(tfName, null);
	findPanel.add(tfDni, null);
	findPanel.add(tfCadastralDomain, null);
	findPanel.add(btnFind, null);
	content.add(findPanel, BorderLayout.NORTH);
	content.add(listPanel, BorderLayout.CENTER);
	wizardPanel.add(btnNext, null);
	wizardPanel.add(btnBack, null);
	wizardPanel.add(btnFirst, null);
	southPanel.add(wizardPanel, null);
	content.add(southPanel, BorderLayout.SOUTH);
	btnReport.setToolTipText("Imprimir Planes de pago");
	add(content, null);
	addButton(btnClose);
	addButton(btnPrint);
	addButton(btnReport);
	btnClose.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnClose_actionPerformed(e);
			      }

			  }
	);
	btnPrint.addActionListener(new ActionListener() {
			      public void actionPerformed(ActionEvent e) {
				  btnPrint_actionPerformed(e);
			      }
			  }
	);
	btnReport.addActionListener(new ActionListener() {
			      public void actionPerformed(ActionEvent e) {
				  btnReport_actionPerformed(e);
			      }
			  }
	);
        setHeaderList();
	btnNext.setEnabled(false);
	btnNext.setToolTipText("Ir a la pestaña siguiente");
	btnBack.setToolTipText("");
	btnNext.setBounds(new Rectangle(80, 15, 25, 20));
	tfName.setBounds(new Rectangle(100, 20, 230, 35));
        tfDni.setBounds(new Rectangle(405, 20, 120, 35));
        tfCadastralDomain.setBounds(new Rectangle(595, 20, 120, 35));
	btnFirst.setBounds(new Rectangle(10, 15, 25, 20));
	btnFirst.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnFirst_actionPerformed(e);
		}
	    }
	);
	btnBack.setBounds(new Rectangle(40, 15, 25, 20));
	btnBack.setSize(new Dimension(25, 20));
	btnBack.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnBack_actionPerformed(e);
		}
	    }
	);
	wizardPanel.setBounds(new Rectangle(790, 0, 110, 50));
	wizardPanel.setLayout(null);
	wizardPanel.setBackground(new Color(185, 96, 0));
	tfName.getTextField().addKeyListener(new KeyAdapter() {

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
	tfCadastralDomain.getTextField().addKeyListener(new KeyAdapter() {

						   public void keyTyped(KeyEvent e) {
						       if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
							   refresh();
						       }
						   }

					       }
	);
        
        findPanel.setBorder(BorderPanel.getBorderPanel("Buscar"));
	btnBack.setEnabled(false);
	btnFirst.setEnabled(false);
	btnPrint.setVisible(false);
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement(Environment.lang.getProperty("Type"));
        headerList.addElement(Environment.lang.getProperty("CadastralDomain"));
        headerList.addElement(Environment.lang.getProperty("TaxPayer"));
        headerList.addElement(Environment.lang.getProperty("DNI"));
	headerList.addElement("Es Rural");
        
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent e) {
					       if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						   btnNext.setEnabled(true);
					       } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						   loadTabs();
					       }
					   }

				       }
	);
	listPanel.setParams("taxes.getAllCatastrosDominios", "'',-1,''", headerList);
        findPanel.setBorder(BorderPanel.getBorderPanel("Buscar Catastros/Dominios"));
    }

    public void refresh() {
	listPanel.refresh("'"+ tfName.getValue() +"',"+ tfDni.getValue() +",'"+ tfCadastralDomain.getValue() +"'");
        btnNext.setEnabled(false);
	((TaxesMain)getTabContainer()).getTaxesMgmt().clearFields();
	((TaxesMain)getTabContainer()).plansOfPaymentMgmt().clearFields();
    }

    public void loadTabs() {
	if (dataRow.size() != 0) {
	    Vector values = dataRow;
	    coodinador = new Coordinador();
	    coodinador.setIdBien(Integer.parseInt(values.elementAt(0).toString()));
	    coodinador.setIdTipoImpuesto(Integer.parseInt(values.elementAt(1).toString()));
	    coodinador.setNroBien(values.elementAt(3).toString());
	    coodinador.setContribuyente(values.elementAt(4).toString());
	    coodinador.setDni(values.elementAt(5).toString());
	    coodinador.loadObject();
	    ((TaxesMain)getTabContainer()).getTaxesMgmt().setCoordinador(coodinador);
	    ((TaxesMain)getTabContainer()).setEnabledPanels(1);
	} else {
	    Advisor.messageBox("No seleccionó ningún Catastro/Dominio", "Error");
	}
    }

    private void btnNext_actionPerformed(ActionEvent e) {
	loadTabs();
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	((TaxesMain)getTabContainer()).getParentInternalFrame().setIcon(true);
    }
    
    private void btnPrint_actionPerformed(ActionEvent e) {
	 String impuesto = "";
	 String subtitulo = "";
	 BoletaPlanesDePago boletaMoratoria = new BoletaPlanesDePago();
	 boletaMoratoria.setIdBoletaPlanDePago(21);
	 boletaMoratoria.retrieveData();
	 if (boletaMoratoria.getIdTipoImpuesto() == 1) {
	     impuesto = "IMPUESTO TGS";
	     subtitulo = "MORATORIA TGS";
	 } else if (boletaMoratoria.getIdTipoImpuesto() == 2) {
	     impuesto = "IMPUESTO INMOBILIARIO";
	     subtitulo = "MORATORIA INMOBILIARIO";
	 } else if (boletaMoratoria.getIdTipoImpuesto() == 3) {
	     impuesto = "IMPUESTO AUTOMOTOR";
	     subtitulo = "MORATORIA AUTOMOTOR";
	 }
	 BasicReport report = new BasicReport(TaxesTGS.class.getResource("xml/PaymentPlanVoucher.xml"));
	 String param = "" + boletaMoratoria.getIdBoletaPlanDePago();
	 report.addTableModel("taxes.getBoletaPlanDePago", param);
	 report.addTableModel("taxes.getDetalleBoletaPlanDePago", param);
	 report.setProperty("nrocomprobante", boletaMoratoria.getNumero());
	 report.setProperty("impuesto", impuesto);
	 report.setProperty("subtitulo", subtitulo);
	 report.setProperty("cantcuotas", boletaMoratoria.getCantCuotas());
	 report.setProperty("nombredescuento", boletaMoratoria.getNombreDescuento());
	 report.setProperty("textamount", "( Son Pesos " + Format.NumberToText.numberToText(boletaMoratoria.getTotal()) + ".- )");
	 BarCode code = new BarCode();
	 report.setProperty("barcode", code.getBarCodeEAN13(boletaMoratoria.getBarcode()));
	 report.doReport();
    }

    private void btnReport_actionPerformed(ActionEvent e) {
	String params = "";
	BasicReport report = new BasicReport(TaxPayerList.class.getResource("xml/PaymentPlanList.xml"));
	report.addTableModel("taxes.xmlGetAllPaymentPlan", params);
	report.doReport();
    }
    
    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void btnFirst_actionPerformed(ActionEvent e) {
    }

    private void btnBack_actionPerformed(ActionEvent e) {
    }
    
    private void controlBotones(){
	btnNext.setEnabled(false);
    }
}


