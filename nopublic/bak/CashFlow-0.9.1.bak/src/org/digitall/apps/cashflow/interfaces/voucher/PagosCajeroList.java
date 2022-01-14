package org.digitall.apps.cashflow.interfaces.voucher;

import java.awt.BorderLayout;
import java.awt.Color;
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

import org.digitall.apps.cashflow.classes.PagoDeTercero;
import org.digitall.common.cashier.Payment;
import org.digitall.common.cashier.PaymentWay;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class PagosCajeroList extends BasicPrimitivePanel{

    private BasicPanel content = new BasicPanel();
    private BasicPanel northPanel = new BasicPanel();
    private BasicPanel centerPanel = new BasicPanel();

    private int[] currentPaymentsColumnSize = { 258, 93 };
    private Vector currentPaymentsDataRow = new Vector();
    private GridPanel currentPaymentsListPanel = new GridPanel(500, currentPaymentsColumnSize, "Boletas Pagadas", currentPaymentsDataRow) {
	public void calculate(){
	    //calculateTotal();
	}
    };
    private Vector currentPaymentsHeader = new Vector();
    
    private int[] currentPaymentWaysColumnSize = { 232, 118 };
    private Vector currentPaymentWaysDataRow = new Vector();
    private GridPanel currentPaymentWaysListPanel = new GridPanel(500, currentPaymentWaysColumnSize, "Forma de Pago", currentPaymentWaysDataRow) {
	public void calculate(){
	    //calculateTotal();
	}
    };
    private Vector currentPaymentsWaysHeader = new Vector();

    private PrintButton btnReport = new PrintButton();

    private TFInput tfFecha = new TFInput(DataTypes.DATE,"Fecha de Pago", false);
    private TFInput tfNroOperacion = new TFInput(DataTypes.STRING,"Pago Nº", false);
    private TFInput tfCaja = new TFInput(DataTypes.STRING,"Caja Nº", false);
    private TFInput tfMonto = new TFInput(DataTypes.DOUBLE,"Amount", false);

    private BorderLayout borderLayout1 = new BorderLayout();
    private GridLayout gridLayout1 = new GridLayout();
    
    private PagosDeTercerosMain parentMain;
    private PagoDeTercero pagoDeTercero;
    private Payment payment;
    private PaymentWay paymentWay;
    

    public PagosCajeroList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(800, 460));
	content.setLayout(borderLayout1);
	northPanel.setLayout(null);
	northPanel.setBounds(new Rectangle(0, 0, 800, 80));
	northPanel.setPreferredSize(new Dimension(800, 80));
	northPanel.add(tfMonto, null);
	northPanel.add(tfCaja, null);
	northPanel.add(tfNroOperacion, null);
	northPanel.add(tfFecha, null);
	centerPanel.setLayout(gridLayout1);
	gridLayout1.setColumns(2);
	tfMonto.setBounds(new Rectangle(585, 25, 140, 35));
	centerPanel.setBounds(new Rectangle(0, 0, 800, 400));
	centerPanel.setPreferredSize(new Dimension(800, 400));
	centerPanel.add(currentPaymentsListPanel);
	centerPanel.add(currentPaymentWaysListPanel);
	btnReport.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnReport_actionPerformed(e);
				 }

			     }
	);
	addButton(btnReport);

	content.add(northPanel, BorderLayout.NORTH);
	content.add(centerPanel, BorderLayout.CENTER);
	this.add(content, BorderLayout.CENTER);
	
	btnReport.setToolTipText("Imprimir Listado de Pagos de Terceros");
	tfFecha.setBounds(new Rectangle(405, 25, 100, 35));
	tfNroOperacion.setBounds(new Rectangle(110, 25, 115, 35));
	tfNroOperacion.getTextField().addKeyListener(new KeyAdapter() {
		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			refresh();
		    }
		}
	    }
	);
	tfCaja.setBounds(new Rectangle(280, 25, 55, 35));
	tfCaja.getTextField().addKeyListener(new KeyAdapter() {
		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			refresh();
		    }
		}
	    }
	);
	btnReport.setEnabled(true);
	tfNroOperacion.setEditable(false);
	tfFecha.setEditable(false);
	tfCaja.setEditable(false);
	tfMonto.setEditable(false);
	tfMonto.setOpaque(true);
	tfMonto.getTextField().setBackground(Color.yellow);
	
	northPanel.setBorder(BorderPanel.getBorderPanel("Datos del Pago"));
	setcurrentPaymentsHeaderList();
	setCurrentPaymentsWaysHeader();
    }


    private void setcurrentPaymentsHeaderList(){
	 currentPaymentsHeader.removeAllElements();
	 currentPaymentsHeader.addElement("*");
	 currentPaymentsHeader.addElement("*");
	 currentPaymentsHeader.addElement("Concepto");
	 currentPaymentsHeader.addElement("Importe");

	 currentPaymentsListPanel.getTable().addMouseListener(new MouseAdapter() {

	         public void mouseClicked(MouseEvent e) {
	             //loadSelectedObject();
	             //checkRows();
	             if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
	                 //LOAD DATA
	             } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
	                // removeCurrentPayment();
	             }
	         }

	     }
	 );
	 currentPaymentsListPanel.setParams("cashier.getBoletasPagadas", "-1", currentPaymentsHeader);
    }
    
    
    private void setCurrentPaymentsWaysHeader() {

	currentPaymentsWaysHeader.removeAllElements();
	currentPaymentsWaysHeader.addElement("*");
	currentPaymentsWaysHeader.addElement("*");
	currentPaymentsWaysHeader.addElement("*");
	currentPaymentsWaysHeader.addElement("*");
	currentPaymentsWaysHeader.addElement("Forma de Pago");
	currentPaymentsWaysHeader.addElement("Monto");
	currentPaymentsWaysHeader.addElement("*");
	currentPaymentsWaysHeader.addElement("*");
	

	currentPaymentWaysListPanel.getTable().addMouseListener(new MouseAdapter() {

		public void mouseClicked(MouseEvent e) {
		    // LOAD OBJECT
		    if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
			//LOAD DATA 
		    } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
			// ACTION
		    }
		}

	    }
	);
	currentPaymentWaysListPanel.setParams("cashier.getallpaymentways", "-1", currentPaymentsWaysHeader);
    }
    
    public void refresh() {
	String params = ""+ payment.getIdpayment();
	currentPaymentsListPanel.refresh(params);
	currentPaymentWaysListPanel.refresh(params);
    }
    
    private void btnReport_actionPerformed(ActionEvent e) {
	e.toString();
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Listado de Pagos de Terceros");
    }
    
    public void setParentMain(PagosDeTercerosMain _parentMain) {
	parentMain = _parentMain;
    }
    
    public void setPagoDeTercero(PagoDeTercero _pagoDeTercero) {
	pagoDeTercero = _pagoDeTercero;
	loadPago();
    }
     private void loadPago(){
	 pagoDeTercero.retrieveData();
	 paymentWay = new PaymentWay();
	 paymentWay.setIdpagodetercero(pagoDeTercero.getIdpagodetercero());
	 paymentWay.retrieveDataByIdPagoDeTercero();
	 payment = new Payment();
	 payment.setIdpayment(paymentWay.getIdpayment());
	 payment.retrieveData();
	 loadFormPago();
     }
     
     private void loadFormPago(){
	tfNroOperacion.setValue(payment.getNroOperacion());
	tfFecha.setValue(Proced.setFormatDate(payment.getFecha(),true));
	tfCaja.setValue(1);
	tfMonto.setValue(payment.getMonto());
	refresh();
     }
}
