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
 * TaxesRent.java
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

import org.digitall.apps.taxes.classes.BoletaAlquiler;
import org.digitall.apps.taxes.classes.Rent;
import org.digitall.common.reports.BasicReport;
import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicTabbedPane;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.buttons.PrintSaveButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.misc.BarCode;

public class TaxesRent extends BasicPrimitivePanel {

    private BasicTabbedPane tabbedPane = new BasicTabbedPane();
    private BasicPanel findPanel = new BasicPanel("Buscar");
    private BasicPanel rentPanel = new BasicPanel();
    private BasicPanel rentDataPanel = new BasicPanel();
    private BasicPanel alquileresPanel = new BasicPanel();
    
    private BorderLayout borderLayout1 = new BorderLayout();
    private BorderLayout borderLayout3 = new BorderLayout();
    private BasicPanel content = new BasicPanel();
    private BorderLayout borderLayout4 = new BorderLayout();
    
    private TFInput tfEmpresa = new TFInput(DataTypes.STRING, "Enterprise", false);
    private TFInput tfResponsable = new TFInput(DataTypes.STRING, "PersonCharge", false);
    private TFInput tfPredio = new TFInput(DataTypes.STRING, "Predio", false);
    private TFInput tfAmount = new TFInput(DataTypes.MONEY,"PartialWOTaxes",false);
    private TFInput tfAccruedInterest = new TFInput(DataTypes.MONEY,"AccruedInterest",false);
    private TFInput tfTotalAmount = new TFInput(DataTypes.MONEY,"TotalAmount",false);
    
    private int[] propertiesColumnSize = { 185, 196, 266 };
    private Vector propertiesDataRow = new Vector();
    private GridPanel propertiesPanel = new GridPanel(100, propertiesColumnSize, "Propiedades", propertiesDataRow);
    private Vector propertiesHeader = new Vector();

    private int[] rentFeesColumnsSize = { 28,37,80,82,44,57,57,57,57,80,52,52};
    private Vector rentFeesDataRow = new Vector();
    private GridPanel rentGridPanel = new GridPanel(50000, rentFeesColumnsSize, "Anticipos", rentFeesDataRow);
    private Vector rentFeesHeader = new Vector();
    
    private FindButton btnSearch = new FindButton();
    private PrintSaveButton btnSavePrint = new PrintSaveButton();
    private AcceptButton btnRentMgmt = new AcceptButton();
    private PrintButton btnPrint = new PrintButton();
    private CloseButton btnClose = new CloseButton();
    
    private int idempresa = -1;
    private BoletaAlquiler boletaAlquiler;
    private Rent rent;

    public TaxesRent() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(700, 566));
	tfPredio.setBounds(new Rectangle(400, 15, 125, 35));
        
	tfEmpresa.setBounds(new Rectangle(70, 15, 125, 35));
	tfResponsable.setBounds(new Rectangle(220, 15, 155, 35));
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
	rentPanel.setLayout(borderLayout1);
        rentDataPanel.setBounds(new Rectangle(5, 55, 685, 155));
        rentDataPanel.setPreferredSize(new Dimension(50, 50));
	alquileresPanel.setLayout(borderLayout3);
	btnSavePrint.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnSavePrint_actionPerformed(e);
				}

			    }
	);
	propertiesPanel.setBounds(new Rectangle(5, 55, 685, 155));
	propertiesPanel.setPreferredSize(new Dimension(300, 300));
	content.setLayout(borderLayout4);
        tfAmount.setBounds(new Rectangle(105, 10, 130, 35));
        tfAccruedInterest.setBounds(new Rectangle(290, 10, 130, 35));
        tfAccruedInterest.setBounds(new Rectangle(290, 10, 130, 35));
        tfTotalAmount.setBounds(new Rectangle(485, 10, 130, 35));
        findPanel.add(tfResponsable, null);
	findPanel.add(btnSearch, null);
	findPanel.add(tfEmpresa, null);
        findPanel.add(tfPredio, null);
        this.add(content, null);
	addButton(btnClose);
	addButton(btnSavePrint);
        addButton(btnPrint);
	//addButton(btnSaveOnly);
	content.add(findPanel, BorderLayout.NORTH);
	content.add(propertiesPanel, BorderLayout.CENTER);
	content.add(tabbedPane, BorderLayout.SOUTH);
	rentPanel.add(rentGridPanel, BorderLayout.CENTER);
        rentDataPanel.add(tfAmount, null);
        rentDataPanel.add(tfAccruedInterest, null);
        rentDataPanel.add(tfTotalAmount, null);
        rentPanel.add(rentDataPanel, BorderLayout.SOUTH);
        rentDataPanel.setLayout(null);
        tabbedPane.addTab("Alquileres", rentPanel);
        tfEmpresa.getTextField().addKeyListener(new KeyAdapter() {

					   public void keyTyped(KeyEvent e) {
					       char caracter = e.getKeyChar();
					       if ((caracter == KeyEvent.VK_ENTER)) {
						   propertiesrefresh();
					       }
					   }

				       }
	);
	tfResponsable.getTextField().addKeyListener(new KeyAdapter() {

							  public void keyTyped(KeyEvent e) {
							      char caracter = e.getKeyChar();
							      if ((caracter == KeyEvent.VK_ENTER)) {
								  propertiesrefresh();
							      }
							  }

						      }
	);
	tfPredio.getTextField().addKeyListener(new KeyAdapter() {

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
        btnRentMgmt.setText("Administración\nde Empresas");
        btnRentMgmt.setToolTipText("Abre la ventana de Administración de Empresas");
        btnRentMgmt.addActionListener(new ActionListener() {

                                      public void actionPerformed(ActionEvent e) {
                                          btnCadastralMgmt_actionPerformed(e);
                                      }

                                  }
        );
        
        rentGridPanel.getTable().addKeyListener(new KeyAdapter() {

                           public void keyPressed(KeyEvent e) {
                               keyTyped(e, rentGridPanel.getTable().getSelectedRow());
                           }

                           public void keyReleased(KeyEvent e) {
                               keyTyped(e, rentGridPanel.getTable().getSelectedRow());
                           }

                           public void keyTyped(KeyEvent e, int _selectedRow) {
                               try {
                                   if (_selectedRow != -1) {
                                       if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE) {
                                           rentGridPanel.getTable().setValueAt(true, _selectedRow, 0);
                                           checkRows();
                                           e.consume();
                                       }
                                   }
                               } catch (ArrayIndexOutOfBoundsException x) {
                                   e.consume();
                               }
                           }
                       }
         );
         
        tfAmount.setEnabled(false);
        tfAccruedInterest.setEnabled(false);
        tfTotalAmount.setEnabled(false);
         
        btnPrint.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e) {
                                    btnPrint_actionPerformed(e);
                                }

                            }
        );
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	
	btnPrint.setVisible(false);
	btnSavePrint.setVisible(false);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
        getParentInternalFrame().getGeneralButtons().addButton(btnRentMgmt);
        //getParentInternalFrame().setInfo("Complete los datos del pedido, luego los recursos solicitados y presione el botón \"Grabar\"");
    }

    private void setTGSFeesHeader() {
	rentFeesHeader.removeAllElements();
	rentFeesHeader.addElement("*");
	rentFeesHeader.addElement("Nº");
	rentFeesHeader.addElement("Año");
	rentFeesHeader.addElement("Fecha");
	rentFeesHeader.addElement("Vence");
	rentFeesHeader.addElement("Valor");
	rentFeesHeader.addElement("% Mora");
	rentFeesHeader.addElement("$ Mora");
	rentFeesHeader.addElement("% Desc.");
	rentFeesHeader.addElement("$ Desc.");
	rentFeesHeader.addElement("Fº Act.");
	rentFeesHeader.addElement("$ Total");
	rentFeesHeader.addElement("% Pagado");
	rentFeesHeader.addElement("*");
        
        rentGridPanel.getTable().addMouseListener(new MouseAdapter() {

                                                 public void mouseClicked(MouseEvent e) {
                                                     loadObjectSelected();
                                                     if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
                                                         getCuotas();
                                                     } else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                                                        checkRows();
                                                     }
                                                 }

                                             }
        );
        
	rentGridPanel.setParams("taxes.getCuotasAlquiler", "-1", rentFeesHeader);
    }
    
    private void checkRows(){
        int fila = rentGridPanel.getTable().getSelectedRow();
        if (fila > 1) {
            for (int i = 0 ;i <= fila ; i++)  {
                rentGridPanel.getTable().setValueAt(true,i,0);
            }
            for (int i = (fila + 1) ;i <= rentGridPanel.getTable().getRowCount()-1 ; i++)  {
                rentGridPanel.getTable().setValueAt(false,i,0);
            }        
        } else {
            for (int i = (fila + 1) ;i <= rentGridPanel.getTable().getRowCount()-1 ; i++)  {
                rentGridPanel.getTable().setValueAt(false,i,0);
            }        
        }
        calcTotalAmount();
    }

    private void calcTotalAmount(){
        double totalIntereses = 0;
        double totalValor = 0;
        double totalDescuento = 0;
        double amount = 0;
        Vector vector = (Vector)rentGridPanel.getSelectedsVector();
        if (rentGridPanel.getSelectedsVector().size() > 1)  {
            for (int i = 0 ;i <= rentGridPanel.getSelectedsVector().size() -1 ; i++)  {
                Vector aux = (Vector)vector.elementAt(i);
                totalValor += Double.parseDouble(aux.elementAt(5).toString());
                totalIntereses += Double.parseDouble(aux.elementAt(7).toString());
                totalDescuento += Double.parseDouble(aux.elementAt(9).toString());
                amount += Double.parseDouble(aux.elementAt(11).toString());
            }    
        } else {
            if (rentGridPanel.getSelectedsVector().size() == 1) {
                Vector aux = (Vector)vector.elementAt(0);
                String x = aux.elementAt(14).toString();
                totalValor += Double.parseDouble(aux.elementAt(5).toString());
                totalIntereses += Double.parseDouble(aux.elementAt(7).toString());
                totalDescuento += Double.parseDouble(aux.elementAt(9).toString());
                amount += Double.parseDouble(aux.elementAt(11).toString());
            }
        }
        
        tfAmount.setValue(totalValor);
        tfAccruedInterest.setValue(totalIntereses);
        tfTotalAmount.setValue(amount);
    }

    private void setPropertiesHeader() {
	propertiesHeader.removeAllElements();
	propertiesHeader.addElement("*");
        propertiesHeader.addElement("Enterprise");
	propertiesHeader.addElement("PersonCharge");
        propertiesHeader.addElement("Predio");
        propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");
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
	String params = "'" + tfEmpresa.getString() + "','" + tfResponsable.getString() + "','" + tfPredio.getString() +"'";
	propertiesPanel.setParams("taxes.getAllRents", params, propertiesHeader);
    }

    public void propertiesrefresh() {
	String params = "'" + tfEmpresa.getString() + "','" + tfResponsable.getString() + "','" + tfPredio.getString() +"'";
	propertiesPanel.refresh(params);
        idempresa = -1;
        getCuotas();
        calcTotalAmount();
    }

    private void loadObjectSelected() {
	if (!propertiesDataRow.isEmpty()) {
	    idempresa = Integer.parseInt("" + propertiesDataRow.elementAt(0));
	}
    }

    private void btnSearch_actionPerformed(ActionEvent e) {
	propertiesrefresh();
    }

    public void getCuotas() {
        //int resul = LibSQL.getInt("taxes.setRentFees",idempresa);
	rentGridPanel.refresh("" + idempresa);
        tfAmount.setValue(0);
        tfAccruedInterest.setValue(0);
        tfTotalAmount.setValue(0);
    }
    
    private void btnCadastralMgmt_actionPerformed(ActionEvent e) {
        Advisor.messageBox("Módulo en desarrollo","Mensaje");
    }

    private void btnSavePrint_actionPerformed(ActionEvent e) {
        int cantCuotas = (rentGridPanel.getSelectedsValuesAt(1).size());
        if ( cantCuotas > 0)  {
            boletaAlquiler = new BoletaAlquiler();
            rent = new Rent();
            rent.setIdempresa(idempresa);
            rent.retrieveData();
            String concepto = "";
            Vector anticipos = rentGridPanel.getSelectedsValuesAt(1);
            Vector anioanticipos = rentGridPanel.getSelectedsValuesAt(2);
            
            //boletaActVarias.setIdaccountingentry();
            //boletaActVarias.setFechaimpresion(); /* desde la base de datos: now() */
            boletaAlquiler.setEmpresa(rent.getEmpresa());
            boletaAlquiler.setResponsable(rent.getResponsable());
            
            boletaAlquiler.setContratoComodato(rent.getContratocomodato());
            boletaAlquiler.setDuracion(rent.getDuracion());
            boletaAlquiler.setObservacion(rent.getObservacion());
            
            if (cantCuotas > 1) {
                concepto = anticipos.elementAt(0).toString() +"/"+ anioanticipos.elementAt(0).toString() +" al "+ anticipos.elementAt(cantCuotas-1).toString() +"/"+ anioanticipos.elementAt(cantCuotas-1).toString();
            } else {
                concepto = anticipos.elementAt(0).toString() +"/"+ anioanticipos.elementAt(0).toString();
            }
            boletaAlquiler.setConcepto(concepto);
            //boletaActVarias.setVencimiento(); // De la BD, es el mismo que el vencimiento
            boletaAlquiler.setImporte(Double.parseDouble(tfAmount.getValue().toString()));
            boletaAlquiler.setRecargo(Double.parseDouble(tfAccruedInterest.getValue().toString()));
            boletaAlquiler.setTotal(Double.parseDouble(tfTotalAmount.getValue().toString()));
            //boletaActVarias.setIdusuario();
            boletaAlquiler.setNroimpresiones(1);
            //boletaActVarias.setLocalidad(commerce.getLocalidad());
            boletaAlquiler.setDomicilio(rent.getPredio());
            //boletaActVarias.setCategoria("");
            boletaAlquiler.setCuotaMensual(Double.parseDouble(""+ rent.getImportemensual()));
            boletaAlquiler.setUsuario(Environment.sessionUser);
            boletaAlquiler.setCuit(rent.getCuit());
            boletaAlquiler.setDni(rent.getDni());
            
            if (boletaAlquiler.saveData() > 0)  {
                Vector vec = rentGridPanel.getSelectedsVector();
                
                for (int i = 0 ;i <= vec.size() -1 ; i++)  {
                Vector aux = (Vector)vec.elementAt(i);
                    if (rent.setIdboletaAlquilerOnCuotaAlquiler(Integer.parseInt(aux.elementAt(1).toString()),Integer.parseInt(aux.elementAt(2).toString()),boletaAlquiler.getIdboletaALquiler()) > 0) {
                        getCuotas();
                    } else {
                        Advisor.messageBox("Ocurrio un error al actualizar las cuotas ","Error");        
                    }
                }  
                
                boletaAlquiler.retrieveData(); 
                BasicReport report = new BasicReport(TaxesTGS.class.getResource("xml/RentVoucher.xml"));
                String param = ""+ boletaAlquiler.getIdboletaALquiler();
                report.addTableModel("taxes.getBoletaAlquiler", param);
                BarCode code = new BarCode(); 
                report.setProperty("barcode", code.getBarCodeEAN13(boletaAlquiler.getBarCode()));
                report.doReport(); 
            } else  {
                Advisor.messageBox("Ocurrió un error al grabar los datos!","Error");
            }
        } else  {
            Advisor.messageBox("Debe seleccionar por lo menos un anticipo!","Mensaje");
        }
    }
    
    private void btnPrint_actionPerformed(ActionEvent e) {
        boletaAlquiler = new BoletaAlquiler();
        boletaAlquiler.setIdboletaAlquiler(1);
        boletaAlquiler.retrieveData();
        BasicReport report = new BasicReport(TaxesTGS.class.getResource("xml/RentVoucher.xml"));
        String param = ""+ boletaAlquiler.getIdboletaALquiler();
        report.addTableModel("taxes.getBoletaAlquiler", param);
        BarCode code = new BarCode(); 
        report.setProperty("barcode", code.getBarCodeEAN13(boletaAlquiler.getBarCode()));
        report.doReport(); 
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().setIcon(true);
    }
    
}
