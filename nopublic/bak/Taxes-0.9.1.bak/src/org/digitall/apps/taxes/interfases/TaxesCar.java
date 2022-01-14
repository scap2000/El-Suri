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

import org.digitall.apps.taxes.classes.BoletaAutomotor;
import org.digitall.apps.taxes.classes.Car;
import org.digitall.apps.taxes.interfases.cadastraladmin.CadastralMgmt;
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
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.misc.BarCode;

public class TaxesCar extends BasicPrimitivePanel {

    private BasicTabbedPane tabbedPane = new BasicTabbedPane();
    private BasicPanel findPanel = new BasicPanel("Buscar");
    private BasicPanel carPanel = new BasicPanel();
    private BasicPanel carDataPanel = new BasicPanel();
    private BasicPanel vehiclePanel = new BasicPanel();
    private BasicPanel content = new BasicPanel();

    private BorderLayout borderLayout1 = new BorderLayout();
    private BorderLayout borderLayout3 = new BorderLayout();
    private BorderLayout borderLayout4 = new BorderLayout();
    
    private TFInput tfDomain = new TFInput(DataTypes.STRING, "Domain", false);
    private TFInput tfOwner = new TFInput(DataTypes.STRING, "Name", false);
    private TFInput tfIdentificationNumber = new TFInput(DataTypes.INTEGER, "DNI", false);
    private TFInput tfAmount = new TFInput(DataTypes.MONEY,"PartialWOTaxes",false);
    private TFInput tfAccruedInterest = new TFInput(DataTypes.MONEY,"AccruedInterest",false);
    private TFInput tfTotalAmount = new TFInput(DataTypes.MONEY,"TotalAmount",false);
    
    private int[] propertiesColumnSize = { 118, 528 };
    private Vector propertiesDataRow = new Vector();
    private GridPanel propertiesPanel = new GridPanel(100, propertiesColumnSize, "Propiedades", propertiesDataRow);
    private Vector propertiesHeader = new Vector();

    private int[] carFeesColumnsSize = { 28,37,80,82,44,57,57,57,57,80,52,52};
    private Vector xcarFeesDataRow = new Vector();
    private GridPanel carGridPanel = new GridPanel(500000, carFeesColumnsSize, "Anticipos", xcarFeesDataRow);
    private Vector carFeesHeader = new Vector();
    
    private FindButton btnSearch = new FindButton();
    private PrintSaveButton btnSavePrint = new PrintSaveButton();
    private AcceptButton btnCadastralMgmt = new AcceptButton();
    private PrintButton btnPrint = new PrintButton();
    private CloseButton btnClose = new CloseButton();
    
    private int iddominio = -1;
    private int idautomotor = -1;
    
    private BoletaAutomotor boletaAutomotor; 
    private Car car;
    
    public TaxesCar() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(700, 566));
	tfDomain.setBounds(new Rectangle(415, 15, 110, 35));
        
	tfOwner.setBounds(new Rectangle(70, 15, 170, 35));
	tfIdentificationNumber.setBounds(new Rectangle(275, 15, 105, 35));
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
	carPanel.setLayout(borderLayout1);
        carDataPanel.setBounds(new Rectangle(5, 55, 685, 155));
        carDataPanel.setPreferredSize(new Dimension(50, 50));
	vehiclePanel.setLayout(borderLayout3);
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
        findPanel.add(tfIdentificationNumber, null);
        findPanel.add(btnSearch, null);
	findPanel.add(tfOwner, null);
        findPanel.add(tfDomain, null);
        this.add(content, null);
	addButton(btnClose);
	addButton(btnSavePrint);
        addButton(btnPrint);
	content.add(findPanel, BorderLayout.NORTH);
	content.add(propertiesPanel, BorderLayout.CENTER);
	content.add(tabbedPane, BorderLayout.SOUTH);
	carPanel.add(carGridPanel, BorderLayout.CENTER);
        carDataPanel.add(tfAmount, null);
        carDataPanel.add(tfAccruedInterest, null);
        carDataPanel.add(tfTotalAmount, null);
        carPanel.add(carDataPanel, BorderLayout.SOUTH);
        carDataPanel.setLayout(null);
        tabbedPane.addTab("Impuesto Automotor", carPanel);
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
	tfDomain.getTextField().addKeyListener(new KeyAdapter() {

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
        btnCadastralMgmt.setText("Administración\nde Vehículos");
        btnCadastralMgmt.setToolTipText("Abre la ventana de Administración de Vehículos");
        btnCadastralMgmt.addActionListener(new ActionListener() {

                                      public void actionPerformed(ActionEvent e) {
                                          btnCadastralMgmt_actionPerformed(e);
                                      }

                                  }
        );
        
        carGridPanel.getTable().addKeyListener(new KeyAdapter() {

                           public void keyPressed(KeyEvent e) {
                               keyTyped(e, carGridPanel.getTable().getSelectedRow());
                           }

                           public void keyReleased(KeyEvent e) {
                               keyTyped(e, carGridPanel.getTable().getSelectedRow());
                           }

                           public void keyTyped(KeyEvent e, int _selectedRow) {
                               try {
                                   if (_selectedRow != -1) {
                                       if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE) {
                                           carGridPanel.getTable().setValueAt(true, _selectedRow, 0);
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
        btnSavePrint.setEnabled(false);
	btnPrint.setEnabled(false);
	btnPrint.setVisible(false);
	btnSavePrint.setVisible(false);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
        getParentInternalFrame().getGeneralButtons().addButton(btnCadastralMgmt);
    }

    private void setTGSFeesHeader() {
	carFeesHeader.removeAllElements();
	carFeesHeader.addElement("*");
	carFeesHeader.addElement("Nº");
	carFeesHeader.addElement("Año");
	carFeesHeader.addElement("Fecha");
	carFeesHeader.addElement("Vence");
	carFeesHeader.addElement("Valor");
	carFeesHeader.addElement("% Mora");
	carFeesHeader.addElement("$ Mora");
	carFeesHeader.addElement("% Desc.");
	carFeesHeader.addElement("$ Desc.");
	carFeesHeader.addElement("Fº Act.");
	carFeesHeader.addElement("$ Total");
	carFeesHeader.addElement("% Pagado");
	carFeesHeader.addElement("*");
        
        carGridPanel.getTable().addMouseListener(new MouseAdapter() {

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
        
	carGridPanel.setParams("taxes.getCuotasAutomotor", "-1", carFeesHeader);
    }
    
    private void checkRows(){
        int fila = carGridPanel.getTable().getSelectedRow();
        if (fila > 1) {
            for (int i = 0 ;i <= fila ; i++)  {
                carGridPanel.getTable().setValueAt(true,i,0);
            }
            for (int i = (fila + 1) ;i <= carGridPanel.getTable().getRowCount()-1 ; i++)  {
                carGridPanel.getTable().setValueAt(false,i,0);
            }        
        } else {
            for (int i = (fila + 1) ;i <= carGridPanel.getTable().getRowCount()-1 ; i++)  {
                carGridPanel.getTable().setValueAt(false,i,0);
            }        
        }
        calcTotalAmount();
    }

    private void calcTotalAmount(){
        double totalIntereses = 0;
        double totalValor = 0;
        double totalDescuento = 0;
        double amount = 0;
        Vector vector = (Vector)carGridPanel.getSelectedsVector();
        if (carGridPanel.getSelectedsVector().size() > 1)  {
            for (int i = 0 ;i <= carGridPanel.getSelectedsVector().size() -1 ; i++)  {
                Vector aux = (Vector)vector.elementAt(i);
                totalValor += Double.parseDouble(aux.elementAt(5).toString());
                totalIntereses += Double.parseDouble(aux.elementAt(7).toString());
                totalDescuento += Double.parseDouble(aux.elementAt(9).toString());
                amount += Double.parseDouble(aux.elementAt(11).toString());
            }    
        } else {
            if (carGridPanel.getSelectedsVector().size() == 1) {
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
	propertiesHeader.addElement("*"); //id
	propertiesHeader.addElement("Dominio");
	propertiesHeader.addElement(Environment.lang.getProperty("Propietario"));
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
	String params = "'" + tfOwner.getString() + "','" + tfIdentificationNumber.getString() + "','" + tfDomain.getString() +"'";
	propertiesPanel.setParams("taxes.getAllCars", params, propertiesHeader);
    }

    public void propertiesrefresh() {
	String params = "'" + tfOwner.getString() + "','" + tfIdentificationNumber.getString() + "','" + tfDomain.getString() +"'";
	propertiesPanel.refresh(params);
        iddominio = -1;
        idautomotor = -1;
        getCuotas();
        calcTotalAmount();
    }

    private void loadObjectSelected() {
	if (!propertiesDataRow.isEmpty()) {
	    iddominio = Integer.parseInt("" + propertiesDataRow.elementAt(0));
            idautomotor = Integer.parseInt("" + propertiesDataRow.elementAt(15));
	}
    }

    private void btnSearch_actionPerformed(ActionEvent e) {
	propertiesrefresh();
    }

    public void getCuotas() {
        //int resul = LibSQL.getInt("taxes.setCarFees",iddominio);
        //int resul = LibSQL.getInt("taxes.setCarFees",idautomotor);
	//carGridPanel.refresh("" + iddominio);
        carGridPanel.refresh("" + idautomotor);
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
        int cantCuotas = (carGridPanel.getSelectedsValuesAt(1).size());
        if ( cantCuotas > 0)  {
            boletaAutomotor = new BoletaAutomotor();
            car = new Car();
            car.setIddominio(iddominio);
            car.retrieveData();
            String concepto = "";
            Vector anticipos = carGridPanel.getSelectedsValuesAt(1);
            Vector anioanticipos = carGridPanel.getSelectedsValuesAt(2);
            
            boletaAutomotor.setTitular(car.getTitular());
            boletaAutomotor.setDominio(car.getDominio());

            if (cantCuotas > 1) {
                concepto = anticipos.elementAt(0).toString() +"/"+ anioanticipos.elementAt(0).toString() +" al "+ anticipos.elementAt(cantCuotas-1).toString() +"/"+ anioanticipos.elementAt(cantCuotas-1).toString();
            } else {
                concepto = anticipos.elementAt(0).toString() +"/"+ anioanticipos.elementAt(0).toString();
            }
            boletaAutomotor.setConcepto(concepto);
            //boletaTgs.setVencimiento(); // De la BD, es el mismo que el vencimiento
            boletaAutomotor.setImporte(Double.parseDouble(tfAmount.getValue().toString()));
            boletaAutomotor.setRecargo(Double.parseDouble(tfAccruedInterest.getValue().toString()));
            boletaAutomotor.setTotal(Double.parseDouble(tfTotalAmount.getValue().toString()));
            boletaAutomotor.setNroimpresiones(1);
            boletaAutomotor.setDomicilio(car.getDomicilio());
            boletaAutomotor.setTipo(car.getTipo());
            boletaAutomotor.setMarca(car.getMarca());
            boletaAutomotor.setMotor(car.getNromotor());
            boletaAutomotor.setCategoria(car.getIdtipocategoria());
            boletaAutomotor.setModelo(""+ car.getModelo());
            boletaAutomotor.setCuota(car.getCuota());
            boletaAutomotor.setValorAnual(car.getValoranual());
            boletaAutomotor.setInformacion("");
            boletaAutomotor.setUsuario(Environment.sessionUser);
            
            if (boletaAutomotor.saveData() > 0)  {
                
                Vector vec = carGridPanel.getSelectedsVector();
                for (int i = 0 ;i <= vec.size() -1 ; i++)  {
                Vector aux = (Vector)vec.elementAt(i);
                    if (car.setIdboletaAutomotorOnCuotaAutomotor(Integer.parseInt(aux.elementAt(1).toString()),Integer.parseInt(aux.elementAt(2).toString()),boletaAutomotor.getIdboletaautomotor()) > 0) {
                        getCuotas();
                    } else {
                        Advisor.messageBox("Ocurrio un error al actualizar las cuotas ","Error");        
                    }
                }  
                
                boletaAutomotor.retrieveData(); 
                BasicReport report = new BasicReport(TaxesTGS.class.getResource("xml/CarVoucher.xml"));
                String param = ""+ boletaAutomotor.getIdboletaautomotor();
                report.addTableModel("taxes.getBoletaAutomotor", param);
                BarCode code = new BarCode(); 
                report.setProperty("barcode", code.getBarCodeEAN13(boletaAutomotor.getBarCode()));
                report.doReport(); 
            } else  {
                Advisor.messageBox("Ocurrió un error al grabar los datos!","Error");
            }
        } else  {
            Advisor.messageBox("Debe seleccionar por lo menos un anticipo!","Mensaje");
        }
    }
    
    private void btnPrint_actionPerformed(ActionEvent e) {
	 boletaAutomotor = new BoletaAutomotor();
	 boletaAutomotor.setIdboletaautomotor(2);
	 boletaAutomotor.retrieveData();
	 BasicReport report = new BasicReport(TaxesTGS.class.getResource("xml/CarVoucher.xml"));
	 String param = ""+ boletaAutomotor.getIdboletaautomotor();
	 report.addTableModel("taxes.getBoletaAutomotor", param);
	 report.addTableModel("taxes.getDetalleBoletaAutomotor", param);
	 BarCode code = new BarCode();
	 report.setProperty("cantperiodos", boletaAutomotor.getCantAnticipos());
	 report.setProperty("textamount","( Son Pesos "+ Format.NumberToText.numberToText(boletaAutomotor.getTotal()) + ".- )");
	 report.setProperty("barcode", code.getBarCodeEAN13(boletaAutomotor.getBarCode()));
	 report.doReport();
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().setIcon(true);
    }
    
}
