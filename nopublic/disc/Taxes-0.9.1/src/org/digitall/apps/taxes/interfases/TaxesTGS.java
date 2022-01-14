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
 * TaxesTGS.java
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

import org.digitall.apps.taxes.classes.BoletaTgs;
import org.digitall.apps.taxes.classes.Cadastral;
import org.digitall.apps.taxes.interfases.cadastraladmin.CadastralMgmt;
import org.digitall.common.reports.BasicReport;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
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
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.misc.BarCode;
import org.digitall.lib.sql.LibSQL;

public class TaxesTGS extends BasicPrimitivePanel {

    private TFInput tfCadastral = new TFInput(DataTypes.INTEGER, "Cadastral", false);
    private TFInput tfOwner = new TFInput(DataTypes.STRING, "Name", false);
    private TFInput tfIdentificationNumber = new TFInput(DataTypes.INTEGER, "DNI", false);
    private FindButton btnSearch = new FindButton();
    private BasicTabbedPane tabbedPane = new BasicTabbedPane();
    private int[] propertiesColumnSize = { 118, 528 };
    private Vector propertiesDataRow = new Vector();
    private GridPanel propertiesPanel = new GridPanel(100, propertiesColumnSize, "Propiedades", propertiesDataRow);
    private Vector propertiesHeader = new Vector();
    private BasicPanel findPanel = new BasicPanel();
    private BasicPanel tgsPanel = new BasicPanel();
    private BasicPanel tgsDataPanel = new BasicPanel();
    private BasicPanel vehiclePanel = new BasicPanel();
    private int[] tgsFeesColumnsSize = { 28,37,80,82,44,57,57,57,57,80,52,52};
    private Vector tgsFeesDataRow = new Vector();
    private GridPanel tgsGridPanel = new GridPanel(50000, tgsFeesColumnsSize, "Anticipos", tgsFeesDataRow);
    private Vector tgsFeesHeader = new Vector();
    private PrintSaveButton btnSavePrint = new PrintSaveButton();
    private AcceptButton btnCadastralMgmt = new AcceptButton();
    private int idcatastro = -1;
    private BorderLayout borderLayout1 = new BorderLayout();
    //private BorderLayout borderLayout2 = new BorderLayout();
    private BorderLayout borderLayout3 = new BorderLayout();
    private BasicPanel content = new BasicPanel();
    private BorderLayout borderLayout4 = new BorderLayout();
    private BoletaTgs boletaTgs;
    private Cadastral cadastral;
    private TFInput tfAmount = new TFInput(DataTypes.MONEY,"Amount",false);
    private TFInput tfAccruedInterest = new TFInput(DataTypes.MONEY,"InterestAmount",false);
    private TFInput tfAccruedDiscount = new TFInput(DataTypes.MONEY,"DiscountAmount",false);
    private TFInput tfTotalAmount = new TFInput(DataTypes.MONEY,"AmountToPay",false);
    private PrintButton btnPrint = new PrintButton();
    private CloseButton btnClose = new CloseButton();
    
    public TaxesTGS() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(700, 566));
	tfCadastral.setBounds(new Rectangle(360, 15, 110, 35));
        
	tfOwner.setBounds(new Rectangle(70, 15, 125, 35));
	tfIdentificationNumber.setBounds(new Rectangle(220, 15, 105, 35));
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
	tgsPanel.setLayout(borderLayout1);
        tgsDataPanel.setBounds(new Rectangle(5, 55, 685, 155));
        tgsDataPanel.setPreferredSize(new Dimension(50, 50));
        //tgsDataPanel.setLayout(borderLayout1);
	//inmPanel.setLayout(borderLayout2);
	vehiclePanel.setLayout(borderLayout3);
	btnSavePrint.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnSavePrint_actionPerformed(e);
				}

			    }
	);
	propertiesPanel.setBounds(new Rectangle(5, 55, 685, 155));
	propertiesPanel.setPreferredSize(new Dimension(300, 300));
	//btnSaveOnly.setBounds(new Rectangle(585, 505, 20, 20));
	content.setLayout(borderLayout4);
	//inmPanel.setBounds(new Rectangle(5, 5, 670, 245));
        tfAmount.setBounds(new Rectangle(25, 10, 130, 35));
        tfAccruedInterest.setBounds(new Rectangle(290, 10, 130, 35));
        tfAccruedInterest.setBounds(new Rectangle(175, 10, 130, 35));
        tfTotalAmount.setBounds(new Rectangle(530, 10, 130, 35));
        findPanel.add(tfIdentificationNumber, null);
	findPanel.add(btnSearch, null);
	findPanel.add(tfOwner, null);
	findPanel.add(tfCadastral, null);
	this.add(content, null);
	addButton(btnClose);
	addButton(btnSavePrint);
        addButton(btnPrint);
	//addButton(btnSaveOnly);
	content.add(findPanel, BorderLayout.NORTH);
	content.add(propertiesPanel, BorderLayout.CENTER);
	content.add(tabbedPane, BorderLayout.SOUTH);
	tgsPanel.add(tgsGridPanel, BorderLayout.CENTER);
        tgsDataPanel.add(tfAccruedDiscount, null);
        tgsDataPanel.add(tfAmount, null);
        tgsDataPanel.add(tfAccruedInterest, null);
        tgsDataPanel.add(tfTotalAmount, null);
        tgsPanel.add(tgsDataPanel, BorderLayout.SOUTH);
        tgsDataPanel.setLayout(null);
        tabbedPane.addTab("Servicios Retributivos", tgsPanel);
        tfOwner.getTextField().addKeyListener(new KeyAdapter() {

					   public void keyTyped(KeyEvent e) {
					       char caracter = e.getKeyChar();
					       if ((caracter == KeyEvent.VK_ENTER)) {
						   propertiesrefresh();
					       }
					   }

				       }
	);
	tfIdentificationNumber.getTextField().addKeyListener(new KeyAdapter() {

							  public void keyTyped(KeyEvent e) {
							      char caracter = e.getKeyChar();
							      if ((caracter == KeyEvent.VK_ENTER)) {
								  propertiesrefresh();
							      }
							  }

						      }
	);
	tfCadastral.getTextField().addKeyListener(new KeyAdapter() {

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
        btnCadastralMgmt.setText("Administración\nde Catastros");
        btnCadastralMgmt.setToolTipText("Abre la ventana de Administración de Catastros");
        btnCadastralMgmt.addActionListener(new ActionListener() {

                                      public void actionPerformed(ActionEvent e) {
                                          btnCadastralMgmt_actionPerformed(e);
                                      }

                                  }
        );
        
        
        tgsGridPanel.getTable().addKeyListener(new KeyAdapter() {

                           public void keyPressed(KeyEvent e) {
                               keyTyped(e, tgsGridPanel.getTable().getSelectedRow());
                           }

                           public void keyReleased(KeyEvent e) {
                               keyTyped(e, tgsGridPanel.getTable().getSelectedRow());
                           }

                           public void keyTyped(KeyEvent e, int _selectedRow) {
                               try {
                                   if (_selectedRow != -1) {
                                       if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE) {
                                           tgsGridPanel.getTable().setValueAt(true, _selectedRow, 0);
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
         tfAccruedDiscount.setEnabled(false);
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
        tfAccruedDiscount.setBounds(new Rectangle(325, 10, 130, 35));
        findPanel.setBorder(BorderPanel.getBorderPanel("Buscar"));
	btnSavePrint.setEnabled(false);
	btnPrint.setEnabled(false);
	btnSavePrint.setVisible(false);
	btnPrint.setVisible(false);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
        getParentInternalFrame().getGeneralButtons().addButton(btnCadastralMgmt);
        //getParentInternalFrame().setInfo("Complete los datos del pedido, luego los recursos solicitados y presione el botón \"Grabar\"");
    }

    private void setTGSFeesHeader() {
	tgsFeesHeader.removeAllElements();
	tgsFeesHeader.addElement("*");
	tgsFeesHeader.addElement("Nº");
	tgsFeesHeader.addElement("Año");
	tgsFeesHeader.addElement("Fecha");
	tgsFeesHeader.addElement("Vence");
	tgsFeesHeader.addElement("Valor");
	tgsFeesHeader.addElement("% Mora");
	tgsFeesHeader.addElement("$ Mora");
	tgsFeesHeader.addElement("% Desc.");
	tgsFeesHeader.addElement("$ Desc.");
	tgsFeesHeader.addElement("Fº Act.");
	tgsFeesHeader.addElement("$ Total");
	tgsFeesHeader.addElement("% Pagado");
	tgsFeesHeader.addElement("*");
        tgsFeesHeader.addElement("*");
        
        tgsGridPanel.getTable().addMouseListener(new MouseAdapter() {

                                                 public void mouseClicked(MouseEvent e) {
                                                     loadObjectSelected();
                                                     
                                                     if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
                                                         //getCuotas();
                                                     } else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                                                        checkRows();
                                                     }
                                                 }

                                             }
        );
        
	tgsGridPanel.setParams("taxes.getcuotastgsmf", "-1", tgsFeesHeader);
    }
    
    private void checkRows(){
        int fila = tgsGridPanel.getTable().getSelectedRow();
        if (fila > 1) {
            for (int i = 0 ;i <= fila ; i++)  {
                tgsGridPanel.getTable().setValueAt(true,i,0);
            }
            for (int i = (fila + 1) ;i <= tgsGridPanel.getTable().getRowCount()-1 ; i++)  {
                tgsGridPanel.getTable().setValueAt(false,i,0);
            }        
        } else {
            for (int i = (fila + 1) ;i <= tgsGridPanel.getTable().getRowCount()-1 ; i++)  {
                tgsGridPanel.getTable().setValueAt(false,i,0);
            }        
        }
        calcTotalAmount();
    }

    private void calcTotalAmount(){
        double totalIntereses = 0;
        double totalValor = 0;
        double totalDescuento = 0;
        double amount = 0;
        Vector vector = (Vector)tgsGridPanel.getSelectedsVector();
        if (tgsGridPanel.getSelectedsVector().size() > 1)  {
            for (int i = 0 ;i <= tgsGridPanel.getSelectedsVector().size() -1 ; i++)  {
                Vector aux = (Vector)vector.elementAt(i);
                totalValor += Double.parseDouble(aux.elementAt(5).toString());
                totalIntereses += Double.parseDouble(aux.elementAt(7).toString());
                totalDescuento += Double.parseDouble(aux.elementAt(9).toString());
                amount += Double.parseDouble(aux.elementAt(11).toString());
            }    
        } else {
            if (tgsGridPanel.getSelectedsVector().size() == 1) {
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
        tfAccruedDiscount.setValue(totalDescuento);
        tfTotalAmount.setValue( (totalValor + totalIntereses) - totalDescuento);
    }

    private void setPropertiesHeader() {
	propertiesHeader.removeAllElements();
	propertiesHeader.addElement("*"); //idcatastro 46
	propertiesHeader.addElement("Catastro");
	propertiesHeader.addElement(Environment.lang.getProperty("Name"));
        
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");propertiesHeader.addElement("*"); 
        
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
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
	String params = "'" + tfOwner.getString() + "',0" + tfIdentificationNumber.getString() + ",0" + tfCadastral.getString();
	propertiesPanel.setParams("taxes.getallcatastros", params, propertiesHeader);
    }

    public void propertiesrefresh() {
	String params = "'" + tfOwner.getString() + "',0" + tfIdentificationNumber.getString() + ",0" + tfCadastral.getString();
	propertiesPanel.refresh(params);
        idcatastro = -1;
        getCuotas();
        calcTotalAmount();
    }

    private void loadObjectSelected() {
	if (!propertiesDataRow.isEmpty()) {
	    idcatastro = Integer.parseInt("" + propertiesDataRow.elementAt(0));
	}
    }

    private void btnSearch_actionPerformed(ActionEvent e) {
	propertiesrefresh();
    }

    public void getCuotas() {
        int resul = LibSQL.getInt("taxes.setTgsFeesMF",idcatastro);
	tgsGridPanel.refresh("" + idcatastro);
        tfAmount.setValue(0);
        tfAccruedInterest.setValue(0);
        tfTotalAmount.setValue(0);
    }
    
    private void btnCadastralMgmt_actionPerformed(ActionEvent e) {
        CadastralMgmt cadastralMgmt = new CadastralMgmt();
        ExtendedInternalFrame cadastralMgmtContainer = new ExtendedInternalFrame("Agregar/Modificar un Catastro");
        cadastralMgmtContainer.setCentralPanel(cadastralMgmt);
        cadastralMgmtContainer.show();
    }

    
    private void btnSavePrint_actionPerformed(ActionEvent e) {
        int cantCuotas = (tgsGridPanel.getSelectedsValuesAt(1).size()); 
        if ( cantCuotas > 0)  {
            boletaTgs = new BoletaTgs();
            cadastral = new Cadastral();
            cadastral.setIdCatastro(idcatastro);
            cadastral.retrieveData();
            cadastral.retrieveApoderadoData();
            boletaTgs.setIdCatastro(cadastral.getIdCatastro());
            String concepto = "";
            Vector anticipos = tgsGridPanel.getSelectedsValuesAt(1);
            Vector anioanticipos = tgsGridPanel.getSelectedsValuesAt(2);
            
            boletaTgs.setContribuyente(cadastral.getDomape());
            boletaTgs.setSeccion(cadastral.getTecsec());
            boletaTgs.setManzana(cadastral.getTecman());
            boletaTgs.setParcela(cadastral.getTecpar());
            boletaTgs.setZona("");
            boletaTgs.setCatastro(cadastral.getCatastro());
            boletaTgs.setNrocuenta(cadastral.getNroCuenta());
            if (cantCuotas > 1) {
                concepto = anticipos.elementAt(0).toString() +"/"+ anioanticipos.elementAt(0).toString() +" al "+ anticipos.elementAt(cantCuotas-1).toString() +"/"+ anioanticipos.elementAt(cantCuotas-1).toString();
            } else {
                concepto = anticipos.elementAt(0).toString() +"/"+ anioanticipos.elementAt(0).toString();
            }
            boletaTgs.setConcepto(concepto);
            boletaTgs.setImporte(Double.parseDouble(tfAmount.getValue().toString()));
            boletaTgs.setRecargo(Double.parseDouble(tfAccruedInterest.getValue().toString()));
            boletaTgs.setDeducciones(Double.parseDouble(tfAccruedDiscount.getValue().toString()));
            boletaTgs.setTotal(Double.parseDouble(tfTotalAmount.getValue().toString()));
            boletaTgs.setNroimpresiones(1);
            boletaTgs.setLocalidad(cadastral.getLocalidad());
            String nro="";
            if (cadastral.getDnumro().trim().equals("0") ||cadastral.getDnumro().trim().equals(""))  {
                nro = "S/N";
            } else  {
                nro = "Nº "+ cadastral.getDnumro();
            }
            
            boletaTgs.setDomicilio(cadastral.getDcalle() +" "+ nro);
            //boletaTgs.setCategoria("");
            boletaTgs.setTerreno(cadastral.getTerreno());
            boletaTgs.setValedificacion(Double.parseDouble(cadastral.getTervmej().toString()));
            boletaTgs.setValfiscal(Double.parseDouble(cadastral.getValfis().toString()));
            boletaTgs.setUsuario(Environment.sessionUser);
            boletaTgs.setNrocuenta(cadastral.getNroCuenta());
            String apoderado = "";
            if (!cadastral.getApoderadoLastName().trim().equals(""))  {
                apoderado = cadastral.getApoderadoLastName() +" "+ cadastral.getApoderadoName();
            }
            boletaTgs.setApoderado(apoderado);
            Vector vec = tgsGridPanel.getSelectedsVector();
            Vector desde = (Vector)vec.elementAt(0);
            Vector hasta = (Vector)vec.elementAt(vec.size() -1);
            boletaTgs.setAnioDesde(Integer.parseInt(desde.elementAt(2).toString()));
            boletaTgs.setAnticipoDesde(Integer.parseInt(desde.elementAt(1).toString()));
            boletaTgs.setAnioHasta(Integer.parseInt(hasta.elementAt(2).toString()));
            boletaTgs.setAnticipoHasta(Integer.parseInt(hasta.elementAt(1).toString()));
            
            if (boletaTgs.saveData() > 0)  {
                getCuotas();
                boletaTgs.retrieveData(); 
                BasicReport report = new BasicReport(TaxesTGS.class.getResource("xml/TgsVoucher.xml"));
                String param = ""+ boletaTgs.getIdboletatgs();
                report.addTableModel("taxes.getBoletaTgs", param);
                report.addTableModel("taxes.getDetalleBoletaTgs", param);
                report.setProperty("cantperiodos",cantCuotas);
                BarCode code = new BarCode(); 
                report.setProperty("barcode", code.getBarCodeEAN13(boletaTgs.getBarCode()));
                report.doReport(); 
            } else  {
                Advisor.messageBox("Ocurrió un error al grabar los datos!","Error");
            }
        } else  {
            Advisor.messageBox("Debe seleccionar por lo menos un anticipo!","Mensaje");
        }
        
    }
    
    private void btnPrint_actionPerformed(ActionEvent e) {
        boletaTgs = new BoletaTgs(); 
        boletaTgs.setIdboletatgs(27);
        boletaTgs.retrieveData();
        BasicReport report = new BasicReport(TaxesTGS.class.getResource("xml/TgsVoucher.xml"));
        String param = ""+ boletaTgs.getIdboletatgs();
        report.addTableModel("taxes.getBoletaTgs", param);
        report.addTableModel("taxes.getDetalleBoletaTgs", param);
        report.setProperty("cantperiodos",2);
        BarCode code = new BarCode(); 
        report.setProperty("barcode", code.getBarCodeEAN13(boletaTgs.getBarCode()));
	report.setProperty("textamount","( Son Pesos "+ Format.NumberToText.numberToText(boletaTgs.getTotal()) + ".- )");
        report.doReport(); 
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().setIcon(true);
    }
    
}
