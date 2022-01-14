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
 * TaxesCommerce.java
 *
 * */
package org.digitall.apps.taxes.interfases;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.apps.taxes.classes.Commerce;
import org.digitall.apps.taxes.classes.CuotaActVarias;
import org.digitall.apps.taxes.interfases.commercesadmin.CommerceFeesMgmt;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicTabbedPane;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.PrintSaveButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;

public class TaxesCommerce extends BasicPrimitivePanel {

    private BasicTabbedPane tabbedPane = new BasicTabbedPane();
    private BasicPanel findPanel = new BasicPanel("Buscar");
    private BasicPanel commercePanel = new BasicPanel();
    private BasicPanel comercioPanel = new BasicPanel();
    private BasicPanel content = new BasicPanel();
    
    private TFInput tfRazonSocial = new TFInput(DataTypes.STRING, "TradeName", false);
    private TFInput tfPadron = new TFInput(DataTypes.STRING, "PollTax", false);
    private TFInput tfContribuyente = new TFInput(DataTypes.STRING, "TaxPayer", false);
    
    private FindButton btnSearch = new FindButton();
    private PrintSaveButton btnSavePrint = new PrintSaveButton();
    private AcceptButton btnCommerceMgmt = new AcceptButton();
    private CloseButton btnClose = new CloseButton();
    
    private int[] propertiesColumnSize = { 118, 349, 266 };
    private Vector propertiesDataRow = new Vector();
    private GridPanel propertiesPanel = new GridPanel(100, propertiesColumnSize, "Propiedades", propertiesDataRow);
    private Vector propertiesHeader = new Vector();

    private int[] commerceFeesColumnsSize = { 28,37,80,82,44,57,57,57,57,80,52,52};
    private Vector commerceFeesDataRow = new Vector();
    private GridPanel commerceGridPanel = new GridPanel(50000, commerceFeesColumnsSize, "Anticipos", commerceFeesDataRow);
    private Vector commerceFeesHeader = new Vector();
    
    private int idcomercio = -1;
    private BorderLayout borderLayout1 = new BorderLayout();
    private BorderLayout borderLayout3 = new BorderLayout();
    private BorderLayout borderLayout4 = new BorderLayout();
    
    private Commerce commerce;
    private CommerceFeesMgmt commerceFeesMgmt;
    private CuotaActVarias cuotasActVarias;
    
    
    public TaxesCommerce() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(700, 566));
	tfRazonSocial.setBounds(new Rectangle(400, 15, 110, 35));
        
	tfPadron.setBounds(new Rectangle(70, 15, 125, 35));
	tfContribuyente.setBounds(new Rectangle(220, 15, 155, 35));
	btnSearch.setBounds(new Rectangle(645, 20, 30, 30));
	btnSearch.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnSearch_actionPerformed(e);
				 }

			     }
	);
	tabbedPane.setBounds(new Rectangle(5, 300, 685, 200));
	tabbedPane.setPreferredSize(new Dimension(405, 250));
	findPanel.setBounds(new Rectangle(5, 5, 685, 45));
	findPanel.setLayout(null);
	findPanel.setPreferredSize(new Dimension(1, 65));
	commercePanel.setLayout(borderLayout1);
        comercioPanel.setLayout(borderLayout3);
	btnSavePrint.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    //btnSavePrint_actionPerformed(e);
				}

			    }
	);
	propertiesPanel.setBounds(new Rectangle(5, 55, 685, 155));
	propertiesPanel.setPreferredSize(new Dimension(300, 300));
	content.setLayout(borderLayout4);
        findPanel.add(tfContribuyente, null);
	findPanel.add(btnSearch, null);
	findPanel.add(tfPadron, null);
        findPanel.add(tfRazonSocial, null);
        this.add(content, null);
        addButton(btnClose);
	addButton(btnSavePrint);
	content.add(findPanel, BorderLayout.NORTH);
	content.add(propertiesPanel, BorderLayout.CENTER);
	content.add(tabbedPane, BorderLayout.SOUTH);
	commercePanel.add(commerceGridPanel, BorderLayout.CENTER);
        tabbedPane.addTab("Actividades Varias", commercePanel);
        tfPadron.getTextField().addKeyListener(new KeyAdapter() {

					   public void keyTyped(KeyEvent e) {
					       char caracter = e.getKeyChar();
					       if ((caracter == KeyEvent.VK_ENTER)) {
						   propertiesrefresh();
					       }
					   }

				       }
	);
	tfContribuyente.getTextField().addKeyListener(new KeyAdapter() {

							  public void keyTyped(KeyEvent e) {
							      char caracter = e.getKeyChar();
							      if ((caracter == KeyEvent.VK_ENTER)) {
								  propertiesrefresh();
							      }
							  }

						      }
	);
	tfRazonSocial.getTextField().addKeyListener(new KeyAdapter() {

					       public void keyTyped(KeyEvent e) {
						   char caracter = e.getKeyChar();
						   if ((caracter == KeyEvent.VK_ENTER)) {
						       propertiesrefresh();
						   }
					       }

					   }
	);
	setPropertiesHeader();
	setTGSFeesHeader();
        btnCommerceMgmt.setText("Administración\nde Comercios");
        btnCommerceMgmt.setToolTipText("Abre la ventana de Administración de Comercios");
        
        commerceGridPanel.getTable().addKeyListener(new KeyAdapter() {

                           public void keyPressed(KeyEvent e) {
                               keyTyped(e, commerceGridPanel.getTable().getSelectedRow());
                           }

                           public void keyReleased(KeyEvent e) {
                               keyTyped(e, commerceGridPanel.getTable().getSelectedRow());
                           }

                           public void keyTyped(KeyEvent e, int _selectedRow) {
                               try {
                                   if (_selectedRow != -1) {
                                       if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE) {
                                           commerceGridPanel.getTable().setValueAt(true, _selectedRow, 0);
                                           e.consume();
                                       }
                                   }
                               } catch (ArrayIndexOutOfBoundsException x) {
                                   e.consume();
                               }
                           }
                       }
         );
         
        btnClose.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e) {
                                    btnClose_actionPerformed(e);
                                }

                            }
        );
        btnClose.setVisible(true);
        btnSavePrint.setVisible(false);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
        //getParentInternalFrame().setInfo("Complete los datos del pedido, luego los recursos solicitados y presione el botón \"Grabar\"");
    }

    private void setTGSFeesHeader() {
	commerceFeesHeader.removeAllElements();
	commerceFeesHeader.addElement("*");
	commerceFeesHeader.addElement("Nº");
	commerceFeesHeader.addElement("Año");
	commerceFeesHeader.addElement("Fecha");
	commerceFeesHeader.addElement("Vence");
	commerceFeesHeader.addElement("Valor");
	commerceFeesHeader.addElement("% Mora");
	commerceFeesHeader.addElement("$ Mora");
	commerceFeesHeader.addElement("% Desc.");
	commerceFeesHeader.addElement("$ Desc.");
	commerceFeesHeader.addElement("Fº Act.");
	commerceFeesHeader.addElement("$ Total");
	commerceFeesHeader.addElement("% Pagado");
	commerceFeesHeader.addElement("*");
        
        commerceGridPanel.getTable().addMouseListener(new MouseAdapter() {

                                                 public void mouseClicked(MouseEvent e) {
                                                     loadObjectSelected();
                                                     if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
                                                         //getCuotas();
                                                         callcommerceFeesMgmt();
                                                     } else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                                                        
                                                     }
                                                 }

                                             }
        );
        
	commerceGridPanel.setParams("taxes.getCuotasActVarias", "-1", commerceFeesHeader);
    }
    
    private void setPropertiesHeader() {
	propertiesHeader.removeAllElements();
	propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");
        propertiesHeader.addElement("Padron");
	propertiesHeader.addElement("Contribuyente");
        propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");
        propertiesHeader.addElement("Razon Social");
        propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");
        
	propertiesPanel.getTable().addMouseListener(new MouseAdapter() {

						 public void mouseClicked(MouseEvent e) {
						     loadObjectSelected();
						     if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
							 getCuotas();
						     } else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                                                        
						     }
						 }

					     }
	);
	String params = "'" + tfPadron.getString() + "','" + tfContribuyente.getString() + "','" + tfRazonSocial.getString() +"'";
	propertiesPanel.setParams("taxes.getAllComercios", params, propertiesHeader);
    }

    public void propertiesrefresh() {
	String params = "'" + tfPadron.getString() + "','" + tfContribuyente.getString() + "','" + tfRazonSocial.getString() +"'";
	propertiesPanel.refresh(params);
        idcomercio = -1;
        getCuotas();
    }

    private void loadObjectSelected() {
	if (!propertiesDataRow.isEmpty()) {
	    idcomercio = Integer.parseInt("" + propertiesDataRow.elementAt(0));
	}
    }

    private void btnSearch_actionPerformed(ActionEvent e) {
	propertiesrefresh();
    }

    public void getCuotas() {
        int resul = LibSQL.getInt("taxes.setCommerceFees",idcomercio);
	commerceGridPanel.refresh("" + idcomercio);
    }
    

    private void btnClose_actionPerformed(ActionEvent e) {
       getParentInternalFrame().setIcon(true);
    }
    
    private void callcommerceFeesMgmt(){
        if (!commerceFeesDataRow.isEmpty())  {
            cuotasActVarias = new CuotaActVarias();
            cuotasActVarias.setIdcomercio(idcomercio);
            cuotasActVarias.setNumber(Integer.parseInt(commerceFeesDataRow.get(1).toString()));
            cuotasActVarias.setYear(Integer.parseInt(commerceFeesDataRow.get(2).toString()));
            cuotasActVarias.retrieveData();
            commerce = new Commerce();
            commerce.setIdpadron(idcomercio);
            commerce.retrieveData();
            commerceFeesMgmt = new CommerceFeesMgmt();
            commerceFeesMgmt.setComercio(commerce);
            commerceFeesMgmt.setCuotaActVarias(cuotasActVarias);
            commerceFeesMgmt.setParentMain(this);
            ExtendedInternalFrame CommerceFeesMgmtContainer = new ExtendedInternalFrame("Registrar anticipo de Actividades Varias");
            CommerceFeesMgmtContainer.setCentralPanel(commerceFeesMgmt);
            CommerceFeesMgmtContainer.show();
            } else  {
                Advisor.messageBox("Error","Error");
        }
        
    }
    
}
