package org.digitall.apps.taxes.interfases.cashier;

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

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import org.digitall.apps.taxes.classes.BoletaAutomotor;
import org.digitall.apps.taxes.classes.BoletaInmob;
import org.digitall.apps.taxes.classes.BoletaPlanesDePago;
import org.digitall.apps.taxes.classes.BoletaTgs;
import org.digitall.apps.taxes.classes.ContratoPlanDePago;
import org.digitall.apps.taxes.classes.CurrentPaymentWay;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.JMoneyEntry;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicTabbedPane;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.PrintSaveButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;


public class CashierMgmt extends BasicPrimitivePanel {

    private BasicTabbedPane southTabbedPane = new BasicTabbedPane();

    private BasicPanel content = new BasicPanel();
    private BasicPanel northPanel = new BasicPanel();
    private BasicPanel northEastPanel = new BasicPanel();

    private BasicPanel contentCashPanel = new BasicPanel();
    private BasicPanel eastCashPanel = new BasicPanel();
    private BasicPanel westCashPanel = new BasicPanel();
    
    private BasicPanel chequePanel = new BasicPanel();
    private BasicPanel depositoPanel = new BasicPanel();
    private BasicPanel northWestPanel = new BasicPanel();
    private BasicPanel centerPanel = new BasicPanel();
    
    private BasicPanel contentChequePanel = new BasicPanel();
    private BasicPanel eastChequePanel = new BasicPanel();
    
    private BasicPanel panel1 = new BasicPanel();
    private BasicPanel panel2 = new BasicPanel();
    private BasicPanel panel3 = new BasicPanel();
    private BasicPanel panel4 = new BasicPanel();
    
    private JMoneyEntry tfTotalAmount = new JMoneyEntry();
    private JMoneyEntry tfCash = new JMoneyEntry();
    private JMoneyEntry tfSumaPagos = new JMoneyEntry();
    private JMoneyEntry tfSaldo = new JMoneyEntry();
    private JMoneyEntry tfVuelto = new JMoneyEntry();
    private JMoneyEntry tfMontoActualizacion = new JMoneyEntry();
    
    private TFInput tfBarcode = new TFInput(DataTypes.STRING, "Barcode", false);
    private TFInput tfNumero = new TFInput(DataTypes.STRING,"Number",true);
    private TFInput tfNombre = new TFInput(DataTypes.STRING,"Name",true);
    private TFInput tfFechaCobro = new TFInput(DataTypes.SIMPLEDATE,"Fecha de Cobro",true);
    private TFInput tfBanco = new TFInput(DataTypes.STRING,"Banco",true);
    private TFInput tfMonto = new TFInput(DataTypes.MONEY,"Monto",true);
    
    private int[] boletasColumnSize = { 118, 142, 203 };
    private Vector boletasDataRow = new Vector();
    private GridPanel boletasListPanel = new GridPanel(1000, boletasColumnSize, "Boletas existentes", boletasDataRow);
    private Vector boletasHeader = new Vector();

    private int[] currentPaymentsColumnSize = { 190, 128, 142 };
    private Vector currentPaymentsDataRow = new Vector();
    private GridPanel currentPaymentsListPanel = new GridPanel(50000, currentPaymentsColumnSize, "Boletas a pagar", currentPaymentsDataRow) {
	public void calculate(){
	    calculateTotal();
	}
    };
    private Vector currentPaymentsHeader = new Vector();
    
    private int[] currentPaymentWaysColumnSize = { 190, 128, 142 };
    private Vector currentPaymentWaysDataRow = new Vector();
    private GridPanel currentPaymentWaysListPanel = new GridPanel(50000, currentPaymentWaysColumnSize, "Detalle de sus pagos", currentPaymentWaysDataRow) {
	public void calculate(){
	    calculateTotal();
	}
    };
    private Vector currentPaymentsWaysHeader = new Vector();

    private PrintSaveButton btnPrintSave = new PrintSaveButton();
    private JButton btnAddEfectivo = new JButton();
    private JButton btnAddCheque = new JButton();
   
    private BorderLayout borderLayout1 = new BorderLayout();
    private BorderLayout borderLayout2 = new BorderLayout();
    private BorderLayout borderLayout3 = new BorderLayout();
    private BorderLayout borderLayout4 = new BorderLayout();
    private BorderLayout borderLayout5 = new BorderLayout();
    private BorderLayout borderLayout6 = new BorderLayout();
    private BorderLayout borderLayout7 = new BorderLayout();
    private BorderLayout borderLayout8 = new BorderLayout();
    
    private GridLayout gridLayout1 = new GridLayout();
    private GridLayout gridLayout2 = new GridLayout();
    private GridLayout gridLayout3 = new GridLayout();
    
    private String currentBarCode = "";
    private String paymentBarCode = "";
    private int idCurrentPaymentWay = -1; 
    private int error = 0;

    private BasicLabel lblCash = new BasicLabel();
    private BasicLabel lblMontoAPagar = new BasicLabel();
    private BasicLabel lblSumaDePagos = new BasicLabel();
    private BasicLabel lblVuelto = new BasicLabel();
    private BasicLabel lblSaldo = new BasicLabel();
    private BasicLabel lblMontoActualizacion = new BasicLabel();
    
    BoletaPlanesDePago boletaMoratoria;
    BoletaTgs boletaTgs;
    BoletaInmob boletaInmob;
    BoletaAutomotor boletaAutomotor;
    CurrentPaymentWay currentPaymentWay;
    ContratoPlanDePago contratoPlanDePago;

    public CashierMgmt() {
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
        this.add(content, null);
	panel1.setLayout(borderLayout2);
	panel2.setLayout(borderLayout3);
	panel3.setLayout(borderLayout5);
	panel4.setLayout(borderLayout6);
	panel1.add(lblMontoAPagar, borderLayout2.WEST);
	panel1.add(tfTotalAmount,borderLayout2.CENTER);
	panel2.add(lblSumaDePagos, borderLayout3.WEST);
	panel2.add(tfSumaPagos,borderLayout3.CENTER);
	panel3.add(lblSaldo, borderLayout5.WEST);
	panel3.add(tfSaldo,borderLayout5.CENTER);
	panel4.add(lblVuelto, borderLayout6.WEST);
	panel4.add(tfVuelto,borderLayout6.CENTER);
	northWestPanel.setLayout(borderLayout1);
	gridLayout3.setRows(4);
	northEastPanel.setLayout(gridLayout3);
	centerPanel.setLayout(gridLayout1);
	gridLayout1.setColumns(2);
	gridLayout2.setColumns(2);
	northPanel.setLayout(gridLayout2);
	northEastPanel.add(panel1);
	northEastPanel.add(panel2);
	northEastPanel.add(panel3);
	northEastPanel.add(panel4);
	
	/* Panel Sur*/
	contentCashPanel.setLayout(borderLayout7);
	contentChequePanel.setLayout(borderLayout8);
	
	/* Panel Efectivo Sur-Este*/
	eastCashPanel.setSize(new Dimension(100, 72));
	eastCashPanel.setBackground(Color.yellow);
	eastCashPanel.setPreferredSize(new Dimension(160, 72));
	eastCashPanel.setLayout(null);
	westCashPanel.setSize(new Dimension(600, 72));

	eastCashPanel.add(btnAddEfectivo, null);
	contentCashPanel.add(eastCashPanel, BorderLayout.EAST);
	contentCashPanel.add(westCashPanel, BorderLayout.CENTER);
	
	/* Panel Cheque Sur-Este*/
	eastChequePanel.setSize(new Dimension(100, 72));
	eastChequePanel.setBackground(Color.yellow);
	eastChequePanel.setPreferredSize(new Dimension(160, 72));
	eastChequePanel.setLayout(null);

        eastChequePanel.add(btnAddCheque, null);
        contentChequePanel.add(eastChequePanel, BorderLayout.EAST);

        contentChequePanel.add(chequePanel, BorderLayout.CENTER);
        btnAddEfectivo.setToolTipText("Agregar Pago en Efectivo");
	//btnAddEfectivo.setText("REGISTRAR PAGO EN EFECTIVO");
	btnAddEfectivo.setText("<html><p align=center>REGISTRAR PAGO<br><p align=center>EN EFECTIVO</html>");
	btnAddEfectivo.setFont(new Font("Dialog", 1, 12));
	btnAddEfectivo.setBackground(new Color(165, 41, 0));
	btnAddEfectivo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	btnAddEfectivo.setForeground(Color.white);
	btnAddEfectivo.setBounds(new Rectangle(10, 20, 140, 40));
	btnAddEfectivo.setSize(new Dimension(140, 40));
	btnAddEfectivo.setPreferredSize(new Dimension(140, 40));
	btnAddEfectivo.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnAddEfectivo_actionPerformed(e);
		}
	    }
	);
	tfNumero.setBounds(new Rectangle(10, 0, 145, 35));
	tfNombre.setBounds(new Rectangle(305, 0, 225, 35));
	tfFechaCobro.setBounds(new Rectangle(180, 0, 105, 35));
	tfBanco.setBounds(new Rectangle(10, 35, 275, 35));
	tfMonto.setBounds(new Rectangle(305, 35, 120, 35));
	tfMonto.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			agregarPagoConCheque();
		    }
		}
	    }
	);
	btnAddCheque.setBounds(new Rectangle(10, 20, 140, 40));
	btnAddCheque.setToolTipText("Agregar Cheque");
	btnAddCheque.setText("<html><p align=center>REGISTRAR PAGO<br><p align=center>CON CHEQUE</html>");
	btnAddCheque.setSize(new Dimension(140, 40));
	btnAddCheque.setFont(new Font("Dialog", 1, 12));
	btnAddCheque.setBackground(new Color(165, 41, 0));
	btnAddCheque.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	btnAddCheque.setForeground(Color.white);
	btnAddCheque.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnAddCheque_actionPerformed(e);
		}
	    }
	);
	tfSumaPagos.setBounds(new Rectangle(130, 0, 210, 35));
	tfSumaPagos.setFont(new Font("Dialog", 1, 25));
	tfSumaPagos.setHorizontalAlignment(JTextField.CENTER);
	tfSaldo.setBounds(new Rectangle(130, 0, 210, 35));
	tfSaldo.setFont(new Font("Dialog", 1, 25));
	tfSaldo.setHorizontalAlignment(JTextField.CENTER);
	tfSaldo.setForeground(Color.red);
	tfVuelto.setBounds(new Rectangle(130, 0, 210, 35));
	tfVuelto.setFont(new Font("Dialog", 1, 25));
	tfVuelto.setHorizontalAlignment(JTextField.CENTER);
	tfVuelto.setBackground(new Color(0, 82, 255));
	
	westCashPanel.setLayout(null);
	westCashPanel.setBackground(Color.yellow);
	chequePanel.setLayout(null);
	chequePanel.setBackground(Color.yellow);
	depositoPanel.setLayout(null);
	content.setLayout(borderLayout4);

	tfTotalAmount.setBounds(new Rectangle(130, 0, 210, 35));
	tfTotalAmount.setFont(new Font("Dialog", 1, 30));
	tfTotalAmount.setEditable(false);
	tfTotalAmount.setForeground(Color.white);
	northPanel.setBounds(new Rectangle(0, 0, 685, 210));
	northPanel.setPreferredSize(new Dimension(685, 210));
	centerPanel.setBounds(new Rectangle(0, 0, 685, 400));
	centerPanel.setPreferredSize(new Dimension(685, 400));
	southTabbedPane.setBounds(new Rectangle(5, 300, 405, 100));
	southTabbedPane.setPreferredSize(new Dimension(405, 100));
	btnPrintSave.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnPrintSave_actionPerformed(e);
		}

	    }
	);


        tfCash.setBounds(new Rectangle(225, 15, 175, 40));
        tfCash.setFont(new Font("Dialog", 1, 15));
        tfCash.addKeyListener(new KeyAdapter() {
                
                public void keyTyped(KeyEvent e) {
                    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                        agregarPagoEnEfectivo();
                    }
                }
            }
        );
        
        tfMontoActualizacion.setBounds(new Rectangle(225, 15, 175, 40));
        tfMontoActualizacion.setFont(new Font("Dialog", 1, 15));
        tfMontoActualizacion.addKeyListener(new KeyAdapter() {
                
                public void keyTyped(KeyEvent e) {
                    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                        agregarPagoActualizacion();
                    }
                }
            }
        );
        
        tfMontoActualizacion.setBounds(new Rectangle(115, 20, 210, 40));
        tfMontoActualizacion.setFont(new Font("Dialog", 1, 25));
        tfMontoActualizacion.setHorizontalAlignment(JTextField.CENTER);
        tfMontoActualizacion.setBackground(Color.yellow);
        

	northPanel.add(northWestPanel);
	northPanel.add(northEastPanel);
	centerPanel.add(currentPaymentsListPanel);
	centerPanel.add(currentPaymentWaysListPanel);
	northWestPanel.add(tfBarcode, BorderLayout.NORTH);
	northWestPanel.add(boletasListPanel, BorderLayout.CENTER);
	content.add(northPanel, BorderLayout.NORTH);
	content.add(centerPanel, BorderLayout.CENTER);
	content.add(southTabbedPane, BorderLayout.SOUTH);
	westCashPanel.add(lblCash, null);
	westCashPanel.add(tfCash, null);
        chequePanel.add(tfMonto, null);
        chequePanel.add(tfBanco, null);
        chequePanel.add(tfFechaCobro, null);
        chequePanel.add(tfNombre, null);
        chequePanel.add(tfNumero, null);
        
        southTabbedPane.addTab("Efectivo", contentCashPanel);
        southTabbedPane.addTab("Cheques", contentChequePanel);

	northEastPanel.setBorder(BorderPanel.getBorderPanel("Cobrar"));
	northEastPanel.setSize(new Dimension(350, 240));
	northEastPanel.setBounds(new Rectangle(350, 0, 350, 300));
	tfBarcode.setEditable(false);
	tfBarcode.setPreferredSize(new Dimension(91, 40));
	tfCash.setBounds(new Rectangle(115, 20, 210, 40));
	tfCash.setFont(new Font("Dialog", 1, 25));
	tfCash.setHorizontalAlignment(JTextField.CENTER);
	tfCash.setBackground(Color.yellow);
	lblCash.setText("Efectivo");
	lblCash.setBounds(new Rectangle(30, 20, 90, 40));
	lblCash.setFont(new Font("Dialog", 1, 20));
	lblCash.setBackground(Color.white);
	lblCash.setBorder(BorderFactory.createLineBorder(new Color(12, 12, 33), 1));
	lblCash.setHorizontalAlignment(SwingConstants.CENTER);
	lblCash.setOpaque(true);
	lblCash.setForeground(Color.black);
	lblMontoAPagar.setText(" Monto a Pagar");
	lblMontoAPagar.setBounds(new Rectangle(6, 0, 125, 35));
	lblMontoAPagar.setSize(125, 35);
	lblMontoAPagar.setBounds(new Rectangle(0, 0, 122, 46));
	lblMontoAPagar.setBorder(BorderFactory.createLineBorder(new Color(12, 12, 33), 1));
	lblMontoAPagar.setFont(new Font("Dialog", 1, 12));
	lblMontoAPagar.setHorizontalAlignment(SwingConstants.CENTER);
	lblMontoAPagar.setOpaque(true);
	lblMontoAPagar.setBackground(Color.white);
	lblMontoAPagar.setForeground(Color.black);
	lblMontoAPagar.setHorizontalTextPosition(SwingConstants.CENTER);
	lblMontoAPagar.setPreferredSize(new Dimension(122, 46));
	
	lblSumaDePagos.setText("Suma de Pagos");
	lblSumaDePagos.setSize(125,35);
	lblSumaDePagos.setBounds(new Rectangle(0, 0, 122, 46));
	lblSumaDePagos.setBorder(BorderFactory.createLineBorder(new Color(12, 12, 33), 1));
	lblSumaDePagos.setFont(new Font("Dialog", 1, 12));
	lblSumaDePagos.setHorizontalAlignment(SwingConstants.CENTER);
	lblSumaDePagos.setOpaque(true);
	lblSumaDePagos.setBackground(Color.white);
	lblSumaDePagos.setForeground(Color.black);
	lblSumaDePagos.setHorizontalAlignment(SwingConstants.CENTER);
	lblSumaDePagos.setPreferredSize(new Dimension(122, 46));
	lblVuelto.setText("Vuelto");
	lblVuelto.setSize(125,35);
	lblVuelto.setBounds(new Rectangle(0, 0, 122, 46));
	lblVuelto.setBorder(BorderFactory.createLineBorder(new Color(12, 12, 33), 1));
	lblVuelto.setFont(new Font("Dialog", 1, 12));
	lblVuelto.setHorizontalAlignment(SwingConstants.CENTER);
	lblVuelto.setOpaque(true);
	lblVuelto.setBackground(Color.white);
	lblVuelto.setForeground(Color.black);
	lblVuelto.setHorizontalAlignment(SwingConstants.CENTER);
	lblVuelto.setPreferredSize(new Dimension(122, 46));
	lblSaldo.setText("Saldo");
	lblSaldo.setBounds(new Rectangle(6, 0, 122, 46));
	lblSaldo.setBorder(BorderFactory.createLineBorder(new Color(12, 12, 33), 1));
	lblSaldo.setFont(new Font("Dialog", 1, 12));
	lblSaldo.setHorizontalAlignment(SwingConstants.CENTER);
	lblSaldo.setOpaque(true);
	lblSaldo.setBackground(Color.white);
	lblSaldo.setForeground(Color.black);
	lblSaldo.setHorizontalAlignment(SwingConstants.CENTER);
	lblSaldo.setPreferredSize(new Dimension(122, 46));

        lblMontoActualizacion.setText("Monto");
        lblMontoActualizacion.setBounds(new Rectangle(30, 20, 90, 40));
        lblMontoActualizacion.setFont(new Font("Dialog", 1, 20));
        lblMontoActualizacion.setBackground(Color.white);
        lblMontoActualizacion.setBorder(BorderFactory.createLineBorder(new Color(12, 12, 33), 1));
        lblMontoActualizacion.setHorizontalAlignment(SwingConstants.CENTER);
        lblMontoActualizacion.setOpaque(true);
        lblMontoActualizacion.setForeground(Color.black);

        tfMontoActualizacion.setBounds(new Rectangle(120, 20, 200, 40));
        tfCash.addKeyListener(new KeyAdapter() {

					   public void keyTyped(KeyEvent e) {
					       char caracter = e.getKeyChar();
					       if ((caracter == KeyEvent.VK_ENTER)) {
						   calculateTotal();
					       }
					   }

				       }
	);

        tfMontoActualizacion.addKeyListener(new KeyAdapter() {

                                           public void keyTyped(KeyEvent e) {
                                               char caracter = e.getKeyChar();
                                               if ((caracter == KeyEvent.VK_ENTER)) {
                                                   //calculateTotal();
                                               }
                                           }

                                       }
        );

	btnPrintSave.setEnabled(false);
	tfTotalAmount.setEditable(false);
	tfTotalAmount.setBackground(Color.red);
	tfTotalAmount.setTextColor(Color.white);
	tfTotalAmount.setHorizontalAlignment(JTextField.CENTER);
	tfSumaPagos.setEditable(false);
	tfSumaPagos.setBackground(Color.yellow);
	tfSumaPagos.setTextColor(Color.black);
	tfSaldo.setEditable(false);
	tfVuelto.setEditable(false);
	tfVuelto.setBackground(Color.blue);
	tfVuelto.setForeground(Color.white);
	tfFechaCobro.setOpaque(false);
	tfNumero.setOpaque(false);
	tfNombre.setOpaque(false);
	tfBanco.setOpaque(false); 
	tfMonto.setOpaque(false);

        tfNumero.getLabel().setForeground(Color.black);
        tfFechaCobro.getLabel().setForeground(Color.black);
        tfNombre.getLabel().setForeground(Color.black);
        tfBanco.getLabel().setForeground(Color.black);
        tfMonto.getLabel().setForeground(Color.black);
        btnPrintSave.setToolTipText("Grabar pago e imprimir comprobantes");
        
	southTabbedPane.setBackground(Color.yellow);
	southTabbedPane.setBackgroundAt(0,Color.yellow);
	southTabbedPane.setForegroundAt(0,Color.black);
	southTabbedPane.setBackgroundAt(1,Color.yellow);
	southTabbedPane.setForegroundAt(1,Color.black);
	southTabbedPane.setFont(new Font("Dialog", 1, 12));

        boletasListPanel.getTable().addMouseListener(new MouseAdapter() {

                                                 public void mouseClicked(MouseEvent e) {
                                                     loadSelectedObject();
                                                     if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                                                         //LOAD DATA
                                                     } else 
                                                            if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
                                                                addCurrentPayment();
                                                            }else{
                                                                if (e.getClickCount() == 2 && e.getButton() == e.BUTTON3) {
                                                                    eliminarBoleta();
                                                                }
                                                            }
                                                 }

                                             }
        );
        
        currentPaymentsListPanel.getTable().addMouseListener(new MouseAdapter() {

                public void mouseClicked(MouseEvent e) {
                    loadSelectedObject();
                    checkRows();
                    if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                        //LOAD DATA
                    } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
                        removeCurrentPayment();
                    }
                }

            }
        );
        
        currentPaymentWaysListPanel.getTable().addMouseListener(new MouseAdapter() {

                public void mouseClicked(MouseEvent e) {
                    loadSelectedWayObject();
                    if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                        //LOAD DATA
                    } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
                        removeCurrentPaymentWay();
                    }
                }

            }
        );

        setBoletasHeader();
        setCurrentPaymentsHeader();
        setCurrentPaymentsWaysHeader();
        callPaintFields();
        addButton(btnPrintSave);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
    }

 
    private void setBoletasHeader() {
	boletasHeader.removeAllElements();
	boletasHeader.addElement("*"); 
	boletasHeader.addElement("Boleta");
	boletasHeader.addElement(Environment.lang.getProperty("Impuesto"));
	boletasHeader.addElement("*"); 
	boletasHeader.addElement("*"); 
	boletasHeader.addElement("*"); 
	boletasHeader.addElement("Contribuyente"); 
	boletasHeader.addElement("*"); 
	boletasHeader.addElement("*"); 
	boletasHeader.addElement("*"); 
	boletasHeader.addElement("*"); 
	boletasHeader.addElement("*"); 
	boletasHeader.addElement("*"); 
	boletasHeader.addElement("*"); 
	boletasHeader.addElement("*"); 
	boletasHeader.addElement("*"); 
	boletasHeader.addElement("*"); 
	boletasHeader.addElement("*"); 
	boletasHeader.addElement("*"); 

	//String params = "'" + tfBarcode.getString() + "'";
	boletasListPanel.setParams("cashier.getallboletas", tfBarcode.getStringForSQL(), boletasHeader);
    }

    private void setCurrentPaymentsHeader() {
	currentPaymentsHeader.removeAllElements();
	currentPaymentsHeader.addElement("*");
	currentPaymentsHeader.addElement("Boleta");
	currentPaymentsHeader.addElement(Environment.lang.getProperty("Impuesto"));
	currentPaymentsHeader.addElement("*");
	currentPaymentsHeader.addElement("*");
	currentPaymentsHeader.addElement("*");
	currentPaymentsHeader.addElement("*");
	currentPaymentsHeader.addElement("*");
	currentPaymentsHeader.addElement("*");
	currentPaymentsHeader.addElement("*");
	currentPaymentsHeader.addElement("*");
	currentPaymentsHeader.addElement("*");
	currentPaymentsHeader.addElement("*");
	currentPaymentsHeader.addElement("*");
	currentPaymentsHeader.addElement("Importe");
	currentPaymentsHeader.addElement("*");
	currentPaymentsHeader.addElement("*");
	currentPaymentsHeader.addElement("*");
	currentPaymentsHeader.addElement("*");
	currentPaymentsHeader.addElement("*");

	
	currentPaymentsListPanel.setParams("cashier.getallcurrentpayments", "", currentPaymentsHeader);
    }

    private void setCurrentPaymentsWaysHeader() {

	currentPaymentsWaysHeader.removeAllElements();
	currentPaymentsWaysHeader.addElement("*");
	currentPaymentsWaysHeader.addElement("*");
	currentPaymentsWaysHeader.addElement("*");
	currentPaymentsWaysHeader.addElement("Tipo de Pago");
	currentPaymentsWaysHeader.addElement("*");
	currentPaymentsWaysHeader.addElement("*");
	currentPaymentsWaysHeader.addElement("Fecha de Cobro");
	currentPaymentsWaysHeader.addElement("Monto");
	currentPaymentsWaysHeader.addElement("*");
	

	
	currentPaymentWaysListPanel.setParams("cashier.getAllCurrentPaymentsWays", "", currentPaymentsWaysHeader);
    }


    private void addCurrentPayment() {
	String params = "'" + currentBarCode + "'";
	try {
	    if (LibSQL.getBoolean("cashier.addCurrentPayment", params)) {
		refreshBoletas();
		refreshCurrentPayments();
	    } else {
		String usuario = "";
		String caja = "";
	        String cajero = "";
	        ResultSet result = LibSQL.exFunction("taxes.getCajeroConBoleta", params);
	         try {
	             if (result.next()) {
	                 
	                 usuario = result.getString("usuario");
	                 caja = result.getString("nrocaja");
	                 cajero = result.getString("cajero");
	             }
	         } catch (NullPointerException e) {
	             // TODO
	         } catch (SQLException e) {
	             // TODO
	              System.out.println("error");
	         }
		 Advisor.messageBox("La boleta que intenta grabar ya se encuentra\nen la Caja Nº "+ caja +", con el usuario "+ usuario ,"Error");
		refreshBoletas();
	    }
	} catch (Exception x) {
	    Advisor.messageBox("Ocurrió un error al intentar seleccionar la boleta", "Error");
	}
    }
    
    private void refreshBoletas() {
	currentBarCode = "";
	boletasListPanel.refresh("''");
    }
    
    private void refreshCurrentPayments() {
	paymentBarCode = "";
	currentPaymentsListPanel.refresh("");
    }
    
    private void refreshCurrentPaymentsWays() {
	idCurrentPaymentWay = -1;
	currentPaymentWaysListPanel.refresh("");
    }
    
    private void loadSelectedObject() {
	if (!boletasDataRow.isEmpty()) {
	    currentBarCode = boletasDataRow.elementAt(15).toString();
	}
	if (!currentPaymentsDataRow.isEmpty()) {
	    paymentBarCode = currentPaymentsDataRow.elementAt(15).toString();
	}
	//checkRows();
    }

    private void loadSelectedWayObject() {
	if (!currentPaymentWaysDataRow.isEmpty()) {
	    currentPaymentWay.setIdcurrentpaymentway(Integer.parseInt(currentPaymentWaysDataRow.elementAt(0).toString()));
	}
    }

    private void checkRows(){
	currentPaymentsListPanel.selectAllItems(true);
    }
    
    private void btnPrintSave_actionPerformed(ActionEvent e) {
	if (currentPaymentsListPanel.getTable().getRowCount() > 0) {
	//if (currentPaymentsListPanel.getSelectedsValuesAt(15).size() > 0) {
	     if (Advisor.question("Registrar un pago" , "¿Está seguro de registrar el pago por un monto de " + tfTotalAmount.getText() + "?")) {
	         payVouchers();
	     }
	} else {
	     Advisor.messageBox("Debe seleccionar una o varias boletas para registrar el pago","Mensaje");
	}
    }
    
    private void removeCurrentPayment() {
	if (paymentBarCode.length() > 0) {
	    if (Advisor.question("Borrar item de los pagos", "¿Desea borrar el item a pagar?")) {
	        String params = "'" + paymentBarCode + "'";
	        if (LibSQL.getBoolean("cashier.delcurrentpayment", params)) {
		    refreshBoletas();
		    refreshCurrentPayments();
		    refreshCurrentPaymentsWays();
		}
	    }
	} else {
	    Advisor.messageBox("No ha seleccionado nada para borrar", "Aviso");
	}
    }

    private void removeCurrentPaymentWay() {
	if (currentPaymentWay.getIdcurrentpaymentway() > 0) {
	    if (Advisor.question("Borrar item de los pagos", "¿Está seguro de borrar el Pago seleccionado?")) {
		if ( currentPaymentWay.deletePaid()) {
		    refreshCurrentPaymentsWays();
		}
	    }
	} else {
	    Advisor.messageBox("No ha seleccionado ningún Pago para borrar", "Aviso");
	}
    }


    public void calculateTotal(){
	//currentPaymentsListPanel.selectAllItems(true);
	Vector _values = currentPaymentsListPanel.getValuesAt(14);
	double total = 0;
	for (int i = 0; i < _values.size(); i++) {
	    total += Double.parseDouble(_values.elementAt(i).toString());
	}
	tfTotalAmount.setValue(total);
	
	/** cálculo del valor del campo Suma de sus Pagos */
//	 currentPaymentWaysListPanel.selectAllItems(true);
	 Vector _waysValues = currentPaymentWaysListPanel.getValuesAt(7);
	 double totalPagos = 0;
	 for (int i = 0; i < _waysValues.size(); i++) {
	     totalPagos += Double.parseDouble(_waysValues.elementAt(i).toString());
	 }
	 tfSumaPagos.setValue(totalPagos);
	 
	/** cálculo del valor del campo Saldo y Vuelto */
	if ((total - totalPagos) > 0)  {
	    tfSaldo.setValue(total - totalPagos);
	    tfVuelto.setValue(0.0);
	} else  {
	    tfSaldo.setValue(0.0);
	    tfVuelto.setValue(totalPagos - total);
	}
	
	callPaintFields();
    }
    
    private void callPaintFields(){
	if (tfSumaPagos.getAmount() >= tfTotalAmount.getAmount() && tfSaldo.getAmount() == 0) {
	    tfSaldo.setForeground(Color.black);
	    tfSaldo.setBackground(Color.green);
	    btnPrintSave.setEnabled(true);
	} else {
	    tfSaldo.setForeground(Color.white);
	    tfSaldo.setBackground(Color.red);
	    btnPrintSave.setEnabled(false);
	}
	/*if (tfTotalAmount.getAmount() == 0.0)  {
	    tfSaldo.setForeground(Color.white);
	    tfSaldo.setBackground(Color.red);
	    btnPrintSave.setEnabled(false);
	} else if (tfTotalAmount.getAmount() > 0 && tfSumaPagos.getAmount() == 0 ) {
	    tfSaldo.setForeground(Color.white);
	    tfSaldo.setBackground(Color.red);
	    btnPrintSave.setEnabled(false);
	} else if (tfTotalAmount.getAmount() > 0 && tfSumaPagos.getAmount() > 0 && tfSaldo.getAmount() > 0 ) {
	    tfSaldo.setForeground(Color.white);
	    tfSaldo.setBackground(Color.red);
	    btnPrintSave.setEnabled(false);
	} else if (tfTotalAmount.getAmount() > 0 && tfSumaPagos.getAmount() > 0 && tfSaldo.getAmount() == 0 ) {
	    tfSaldo.setForeground(Color.black);
	    tfSaldo.setBackground(Color.green);
	    btnPrintSave.setEnabled(true);
	}*/	
    }

    private void clearData() {
	/* Campos de la pestaña Efectivo */
	tfTotalAmount.setValue(0);
	tfCash.setValue(0);
	/* Campos de la pestaña Cheques */
	tfNumero.setValue("");
	tfFechaCobro.setValue("");
	tfNombre.setValue("");
	tfBanco.setValue("");
	tfMonto.setValue(0.0);
        /* Campos de la pestaña Actualizaciones */
        tfMontoActualizacion.setValue(0);
    }

    private void payVouchers() {
	Vector _boletas = currentPaymentsListPanel.getAllValues();
	String cadenaBarcode = "";
	for (int i = 0; i < _boletas.size(); i++) {
	    cadenaBarcode += ((Vector)_boletas.elementAt(i)).elementAt(16).toString()  + ", ";
	}
	/*String cadenaBarcode = "";
	for(int i = 0 ; i < currentPaymentsListPanel.getSelectedsValuesAt(15).size(); i++) {
	    cadenaBarcode += currentPaymentsListPanel.getSelectedsValuesAt(15).elementAt(i).toString() + ",";            
	}*/
	int idpayment = LibSQL.getInt("cashier.addPaymentByCaja", "" + Format.toDouble(tfVuelto.getAmount()));
	if ( idpayment < 0 ) {
	    Advisor.messageBox("Ocurrió un error al registrar el Pago ", "Error");
	} else {
	    CashierPrinter.printVoucher(idpayment,false);
	}
	
	clearData();
	refreshBoletas();
	refreshCurrentPayments();
	refreshCurrentPaymentsWays();
    }
    
    private void btnAddEfectivo_actionPerformed(ActionEvent e) {
	agregarPagoEnEfectivo();
    }

    private void loadCurrentPaymentWayObject(int _tipoPago){
	currentPaymentWay = new CurrentPaymentWay();
	currentPaymentWay.setIdtipopago(_tipoPago);
	if (_tipoPago == 1)  {  // Pago en Efectivo
	    currentPaymentWay.setMonto(tfCash.getAmount());
	} else if (_tipoPago == 2)  { // Pago con Cheques
	    currentPaymentWay.setMonto(tfMonto.getAmount());
	    currentPaymentWay.setNumero(tfNumero.getString());
	    currentPaymentWay.setBanco(tfBanco.getString());
	    currentPaymentWay.setNombre(tfNombre.getString());
	    currentPaymentWay.setFechacobro(Proced.setFormatDate(tfFechaCobro.getDate(),false));
        } else if (_tipoPago == 5)  { // Pago por Actualizacion
            currentPaymentWay.setMonto(tfMontoActualizacion.getAmount());
        }
	
    }

    private void agregarPagoEnEfectivo(){
	if (tfCash.getAmount() > 0)  {
	    loadCurrentPaymentWayObject(1);
	    if (currentPaymentWay.saveData() > 0)  {
		refreshCurrentPaymentsWays();
		clearData();
	    } else  {
		Advisor.messageBox("Ocurrió un error al grabar los datos","Mensaje");
	    }
	} else  {
	    Advisor.messageBox("El Monto debe ser mayor o igual a cero","Mensaje");
	}
	
    }
   
    private void agregarPagoActualizacion() {
        if (tfMontoActualizacion.getAmount() > 0)  {
            loadCurrentPaymentWayObject(5);
            if (currentPaymentWay.saveData() > 0)  {
                refreshCurrentPaymentsWays();
                clearData();
            } else  { 
                Advisor.messageBox("Ocurrió un error al grabar los datos","Mensaje");
            }
        } else  {
            Advisor.messageBox("El Monto debe ser mayor que cero","Mensaje");
        }
    }
    
    private void btnAddCheque_actionPerformed(ActionEvent e) {
	agregarPagoConCheque();
    }

    private void agregarPagoConCheque() {
	if (checksControl())  {
	    loadCurrentPaymentWayObject(2);
	    if (currentPaymentWay.saveData() > 0)  {
		refreshCurrentPaymentsWays();
		clearData();
	    } else  {
		Advisor.messageBox("Ocurrió un error al grabar los datos","Error");
	    }
	} else  {
	    showCheckMessage();
	}
    }

    private boolean checksControl(){
	boolean valor = false;
	if (tfNumero.getString().length() == 0)  {
	    error = 1;
	} else if (tfFechaCobro.getDate().length() == 0)  {
	    error = 2;
	} else if (tfNombre.getString().length() == 0)  {
	    error = 3;
	} else if (tfBanco.getString().length() == 0)  {
	    error = 4;
	} else if (tfMonto.getAmount() == 0)  {
	    error = 5;
	} else {
	    valor = true;
	}
	return valor;
    }
    
    private void showCheckMessage(){
	switch (error)  {
	    case 1: Advisor.messageBox("El campo Número no debe ser vacio","Mensaje");
		break;
	    case 2: Advisor.messageBox("El campo Fecha de Cobro no debe ser vacio","Mensaje");
	        break;
	    case 3: Advisor.messageBox("El campo Nombre no debe ser vacio","Mensaje");
	        break;
	    case 4: Advisor.messageBox("El campo Banco no debe ser vacio","Mensaje");
	        break;
	    case 5: Advisor.messageBox("El campo Monto debe ser mayor que cero","Mensaje");
	        break;
	}
    }
    
    /**2010-01-21(moraless)*/
    private void eliminarBoleta(){
	String tipoImpuesto = "";
	int encontro = boletasDataRow.elementAt(2).toString().indexOf("CONTRIBUCION");
	if(encontro !=-1){
	    tipoImpuesto = "a Contribución - Libre Deuda";
	}else{
	    encontro = boletasDataRow.elementAt(2).toString().indexOf("PAGO");
	    if(encontro != -1){
		tipoImpuesto = "a la "+boletasDataRow.elementAt(2);
	    }else{
		encontro = boletasDataRow.elementAt(2).toString().indexOf("T.G.S");
		if(encontro != -1){
		    tipoImpuesto = "a la T.G.S.";
		}else{
		    encontro = boletasDataRow.elementAt(2).toString().indexOf("Inmobiliario");
		    if(encontro != -1){
			tipoImpuesto = "al Impuesto Inmobiliario";
		    }else{
			encontro = boletasDataRow.elementAt(2).toString().indexOf("Automotor");
			if(encontro != -1){
			    tipoImpuesto = "al Impuesto Automotor";
			}else{
			    
			}
		    }
		}
	    }
	}
	if (Advisor.question("Atención", "¿Está seguro de Eliminar la Boleta Nº \""+ boletasDataRow.elementAt(1) +"\"" +" \ncorrespondiente "+ tipoImpuesto +"?")) {
	    String params = ""+ boletasDataRow.elementAt(0) +",'"+ boletasDataRow.elementAt(15) +"'";
	    if(LibSQL.getInt("cashier.delboleta",params) > 0){
	        refreshBoletas();
	    }else{
	        Advisor.messageBox("Ocurrió un error al grabar los datos","Error");
	    }
	}
	
    }

}
