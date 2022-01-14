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
 * ClearCommerceTaxes.java
 *
 * */
package org.digitall.apps.taxes.interfases.clearfees;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.SwingConstants;

import org.digitall.apps.taxes.classes.Cadastral;
import org.digitall.apps.taxes.classes.Commerce;
import org.digitall.apps.taxes.interfases.commercesadmin.CommercesMgmt;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicTitleLabel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.buttons.RefreshGridButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class ClearCommerceTaxes extends BasicPrimitivePanel {

    private BasicPanel content = new BasicPanel();
    private BasicPanel findPanel = new BasicPanel("Buscar");
    private BasicPanel impuestosPanel = new BasicPanel();
    
    private BorderLayout borderLayout4 = new BorderLayout();
    
    private TFInput tfPadron = new TFInput(DataTypes.STRING, "PollTax", false);
    private TFInput tfContribuyente = new TFInput(DataTypes.STRING,"TaxPayer", false);
    private TFInput tfRazonSocial = new TFInput(DataTypes.STRING, "TradeName", false);
    
    private int[] propertiesColumnSize = { 80, 255, 208, 90};
    private Vector dataRow = new Vector();
    private GridPanel propertiesPanel = new GridPanel(50000, propertiesColumnSize, "Propiedades", dataRow) {
	    public void finishLoading() {
		    controlBotones();
	    }
    };
    private Vector propertiesHeader = new Vector();

    private FindButton btnSearch = new FindButton();
    private DeleteButton btnDeleteCommerceFees = new DeleteButton();
    private RefreshGridButton btnRecalcCommerceFees = new RefreshGridButton();
    private PrintButton btnPrint = new PrintButton();
    private AddButton btnAddCommerce = new AddButton();
    private ModifyButton btnModifyCommerce = new ModifyButton();
    
    private CBInput cbCommerceMonth = new CBInput(0,"Advance",false);
    private CBInput cbCommerceYear = new CBInput(0,"FileYear",false);
    
    private int idcomercio = -1;
    
    private BasicPanel commercePanel = new BasicPanel();
    private GridLayout gridLayout1 = new GridLayout();
    private BasicTitleLabel lblCommerceTitle = new BasicTitleLabel();
    private Cadastral cadastral;
    private CommercesMgmt commercesMgmt;
    private Commerce commerce;

    public ClearCommerceTaxes() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(700, 479));
	tfRazonSocial.setBounds(new Rectangle(465, 15, 110, 35));
	tfPadron.setBounds(new Rectangle(70, 15, 125, 35));
        
	tfContribuyente.setBounds(new Rectangle(220, 15, 220, 35));
	btnSearch.setBounds(new Rectangle(645, 20, 30, 30));
	btnSearch.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnSearch_actionPerformed(e);
				 }

			     }
	);
        impuestosPanel.setLayout(gridLayout1);
        cbCommerceMonth.setBounds(new Rectangle(40, 30, 85, 35));
        cbCommerceMonth.setOpaque(false);
        cbCommerceYear.setBounds(new Rectangle(140, 30, 100, 35));
        cbCommerceYear.setOpaque(false);
        commercePanel.setLayout(null);
        commercePanel.setBackground(new Color(58, 115, 176));
        lblCommerceTitle.setText("IMPUESTO ACT. VARIAS");
        lblCommerceTitle.setBounds(new Rectangle(0, 5, 700, 25));
        lblCommerceTitle.setFont(new Font("Lucida Sans", 1, 15));
        lblCommerceTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblCommerceTitle.setHorizontalTextPosition(SwingConstants.CENTER);
        impuestosPanel.setBounds(new Rectangle(5, 300, 405, 200));
        impuestosPanel.setPreferredSize(new Dimension(405, 80));
        commercePanel.add(lblCommerceTitle, null);
        commercePanel.add(cbCommerceYear, null);
        commercePanel.add(cbCommerceMonth, null);
        commercePanel.add(btnDeleteCommerceFees, null);
        commercePanel.add(btnRecalcCommerceFees,null);
        cbCommerceMonth.autoSize();
        cbCommerceYear.autoSize();
        findPanel.setBounds(new Rectangle(5, 5, 685, 45));
        findPanel.setLayout(null);
        findPanel.setPreferredSize(new Dimension(1, 65));
        propertiesPanel.setBounds(new Rectangle(5, 55, 685, 155));
        propertiesPanel.setPreferredSize(new Dimension(400, 400));
        btnRecalcCommerceFees.setBounds(new Rectangle(430, 45, 235, 25));
        btnRecalcCommerceFees.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnRecalcCommerceFees_actionPerformed(e);
                }
            }
        );
        btnDeleteCommerceFees.setBounds(new Rectangle(285, 45, 90, 25));
        btnDeleteCommerceFees.setHorizontalAlignment(SwingConstants.LEFT);
        btnDeleteCommerceFees.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnDeleteCommerceFees.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnDeleteCommerceFees_actionPerformed(e);
                }
            }
        );
        content.setLayout(borderLayout4);
        findPanel.add(tfContribuyente, null);
        findPanel.add(btnSearch, null);
        findPanel.add(tfPadron, null);
        findPanel.add(tfRazonSocial, null);
        this.add(content, null);
        content.add(findPanel, BorderLayout.NORTH);
	content.add(propertiesPanel, BorderLayout.CENTER);
        impuestosPanel.add(commercePanel, null);
        content.add(impuestosPanel, BorderLayout.SOUTH);
	tfPadron.getTextField().addKeyListener(new KeyAdapter() {

					   public void keyTyped(KeyEvent e) {
					       char caracter = e.getKeyChar();
					       if ((caracter == KeyEvent.VK_ENTER)) {
						   refresh();
					       }
					   }

				       }
	);
	tfContribuyente.getTextField().addKeyListener(new KeyAdapter() {

							  public void keyTyped(KeyEvent e) {
							      char caracter = e.getKeyChar();
							      if ((caracter == KeyEvent.VK_ENTER)) {
								  refresh();
							      }
							  }

						      }
	);
	tfRazonSocial.getTextField().addKeyListener(new KeyAdapter() {

					       public void keyTyped(KeyEvent e) {
						   char caracter = e.getKeyChar();
						   if ((caracter == KeyEvent.VK_ENTER)) {
						       refresh();
						   }
					       }

					   }
	);
        btnRecalcCommerceFees.setToolTipText("Recalcular los anticipos de Act. Varias\npara el comercio seleccionado");
        btnRecalcCommerceFees.setHorizontalAlignment(SwingConstants.LEFT);
        btnRecalcCommerceFees.setHorizontalTextPosition(SwingConstants.RIGHT);
        setPropertiesHeader();
        loadCombos();
        setEnabledCombosAndButtons(false);
        this.addButton(btnPrint);
        this.addButton(btnModifyCommerce);
        this.addButton(btnAddCommerce);
        btnPrint.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnPrint_actionPerformed(e);
                }
            }
        );
        
        btnAddCommerce.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnAddCommerce_actionPerformed(e);
                }
            }
        );

        btnModifyCommerce.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnModifyCommerce_actionPerformed(e);
                }
            }
        );
        
        btnPrint.setToolTipText("<html><center>Imprimir los anticipos de las Actividades Varias<br>para el comercio seleccionado</center></html>");
        btnDeleteCommerceFees.setText("Borrar anticipos");
        btnRecalcCommerceFees.setText("Restaurar anticipos");
        btnRecalcCommerceFees.setToolTipText("<html><center>Restaurar los anticipos del Impuesto a las Act. Varias<br>para el comercio seleccionado</center></html>");
        btnAddCommerce.setToolTipText("<html><center>Agregar nuevo Comercio</center></html>");
        btnModifyCommerce.setToolTipText("<html><center>Modificar el Comercio seleccionado</center></html>");
        
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
    }

    private void loadCombos(){
        int actualYear = Integer.parseInt("0" + Environment.currentYear);
        int cont = 0;
        for (int i = 2000 ; i <= (actualYear) ; i++)  {
            cont++;
            cbCommerceYear.getCombo().addItem(i,cont);
        }
        cbCommerceYear.setSelectedID(cont);
        cont = 0;
        for (int i = 1 ; i <= (12) ; i++)  {
            cont++;
            cbCommerceMonth.getCombo().addItem(i,cont);
        }
    }

    private void setEnabledCombosAndButtons(boolean _valor){
        cbCommerceMonth.setEnabled(_valor);
        cbCommerceYear.setEnabled(_valor);
        btnDeleteCommerceFees.setEnabled(_valor);
        btnRecalcCommerceFees.setEnabled(_valor);
        btnPrint.setEnabled(_valor);
        btnModifyCommerce.setEnabled(_valor);
    }

    private void setPropertiesHeader() {
	propertiesHeader.removeAllElements();
	propertiesHeader.addElement("*"); //idcomercio
        propertiesHeader.addElement("*"); //idpadron
	propertiesHeader.addElement(Environment.lang.getProperty("PollTax"));
	propertiesHeader.addElement(Environment.lang.getProperty("TaxPayer"));
        propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");
        propertiesHeader.addElement(Environment.lang.getProperty("TradeName"));
        propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");
        propertiesHeader.addElement("Ult. Anticipo");
        
	propertiesPanel.getTable().addMouseListener(new MouseAdapter() {

						 public void mouseClicked(MouseEvent e) {
						     loadObjectSelected();
						     if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
                                                     
						     } else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                                                        setEnabledCombosAndButtons(true);
						     }
						 }

					     }
	);
	String params = "'"+ tfPadron.getString() +"','" + tfContribuyente.getString() + "','" + tfRazonSocial.getString() +"'";
	propertiesPanel.setParams("taxes.getAllComercios", params, propertiesHeader);
    }

    public void refresh() {
	String params = "'"+ tfPadron.getString() +"','"+ tfContribuyente.getString() +"','"+ tfRazonSocial.getString() +"'";
	propertiesPanel.refresh(params);
        setEnabledCombosAndButtons(false);
    }

    private void loadObjectSelected() {
	if (!dataRow.isEmpty()) {
	    commerce = new Commerce();
	    commerce.setIdpadron(Integer.parseInt("" + dataRow.elementAt(1)));
	    commerce.retrieveData();
	}
    }

    private void btnSearch_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void btnDeleteCommerceFees_actionPerformed(ActionEvent e) {
        if (Advisor.question("Aviso!","¿Está seguro de borrar los anticipos?")) {
	    idcomercio = Integer.parseInt(dataRow.elementAt(0).toString());
	    String params =  commerce.getIdComercio() + ","+ cbCommerceMonth.getSelectedItem() +","+ cbCommerceYear.getSelectedItem();
            if (LibSQL.getInt("taxes.deleteCommerceFees",params) > 0) {
                refresh();
            }
        }
    }

    private void btnRecalcCommerceFees_actionPerformed(ActionEvent e) {
            if (Advisor.question("Aviso!","¿Está seguro de regenerar los anticipos de Act. Varias\npara el comercio seleccionado?")) {
		if (LibSQL.getInt("taxes.generarCuotaComercio",commerce.getIdComercio()) == 0) {
                    refresh();
                }
            }
    }
    
    private void btnPrint_actionPerformed(ActionEvent e) {
        if (!dataRow.isEmpty()) {
            idcomercio = Integer.parseInt(dataRow.elementAt(0).toString());
            String idpadron = dataRow.elementAt(1).toString();
            BasicReport report = new BasicReport(ClearCommerceTaxes.class.getResource("xml/CommerceFeesReport.xml"));
            String param = ""+ idcomercio;
            report.addTableModel("taxes.xmlgetCommerce",idpadron);
            report.addTableModel("taxes.xmlGetCommerceFees", param);
            report.doReport();
        } else {
            Advisor.messageBox("Debe seleccionar un Comercio", "Error");
        }
    }
    
    private void loadMgmt(boolean _addAction){
            if (_addAction){
                commerce = new Commerce();
            }
            commercesMgmt = new CommercesMgmt();
            commercesMgmt.setCommerce(commerce);
            commercesMgmt.setParentList(this);

            ExtendedInternalFrame commerceMgmtContainer = new ExtendedInternalFrame("Agregar/Modificar");
            commerceMgmtContainer.setCentralPanel(commercesMgmt);
            commerceMgmtContainer.show();
    }
    
    private void btnAddCommerce_actionPerformed(ActionEvent e) {
        loadMgmt(true);
    }
    
    private void btnModifyCommerce_actionPerformed(ActionEvent e) {
        loadMgmt(false);
    }
    
    private void controlBotones(){
	setEnabledCombosAndButtons(false);
    }
    
}
