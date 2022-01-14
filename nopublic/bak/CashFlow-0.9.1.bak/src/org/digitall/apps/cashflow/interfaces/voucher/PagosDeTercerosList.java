package org.digitall.apps.cashflow.interfaces.voucher;

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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import org.digitall.apps.cashflow.classes.PagoDeTercero;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;

public class PagosDeTercerosList extends BasicPrimitivePanel{

    private BasicPanel content = new BasicPanel();
    private BasicPanel northPanel = new BasicPanel();

    private int[] sizeColumnList = {103, 88, 88, 150, 180, 88, 62, 67, 104, 104};
    private Vector dataRow = new Vector();
    private GridPanel chequesDeTercerosPanel = new GridPanel(500, sizeColumnList, "Pagos", dataRow);
    private Vector headerList = new Vector();

    private PrintButton btnReport = new PrintButton();
    private FindButton btnBuscar = new FindButton();

    private TFInput tfFechaCobro = new TFInput(DataTypes.DATE,"Fecha de Cobro", false);
    private TFInput tfBanco = new TFInput(DataTypes.STRING,"Banco", false);
    private TFInput tfNombre = new TFInput(DataTypes.STRING,"Nombre", false);

    private CBInput cbCobrados = new CBInput(0,"Tipo");

    private BorderLayout borderLayout1 = new BorderLayout();
    private PagoDeTercero pagoDeTercero;
    private PagosDeTercerosMain parentMain;

    public PagosDeTercerosList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(800, 460));
	content.setLayout(borderLayout1);
	btnReport.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnReport_actionPerformed(e);
				 }

			     }
	);
	northPanel.setLayout(null);
	northPanel.setBounds(new Rectangle(0, 0, 800, 80));
	northPanel.setPreferredSize(new Dimension(800, 80));
	addButton(btnReport);
	northPanel.add(btnBuscar, null);
	northPanel.add(cbCobrados, null);
	northPanel.add(tfNombre, null);
	northPanel.add(tfBanco, null);
	northPanel.add(tfFechaCobro, null);
	content.add(northPanel, BorderLayout.NORTH);
	content.add(chequesDeTercerosPanel, BorderLayout.CENTER);
	this.add(content, BorderLayout.CENTER);
	setHeaderList();
	btnReport.setToolTipText("Imprimir Listado de Pagos de Terceros");
	btnReport.setEnabled(false);
	tfFechaCobro.setBounds(new Rectangle(30, 25, 100, 35));
	tfBanco.setBounds(new Rectangle(150, 25, 180, 35));
	tfBanco.getTextField().addKeyListener(new KeyAdapter() {
		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			refresh();
		    }
		}
	    }
	);
	tfNombre.setBounds(new Rectangle(355, 25, 215, 35));
	tfNombre.getTextField().addKeyListener(new KeyAdapter() {
		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			refresh();
		    }
		}
	    }
	);
	cbCobrados.setBounds(new Rectangle(590, 25, 120, 35));
	btnBuscar.setToolTipText("Buscar Pago de Tercero");
	btnBuscar.setBounds(new Rectangle(750, 35, 30, 25));
	btnBuscar.setSize(new Dimension(30, 25));
	btnBuscar.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnBuscar_actionPerformed(e);
		}
	    }
	);
	northPanel.setBorder(BorderPanel.getBorderPanel("Buscar Pago"));
	cbCobrados.autoSize();
	loadCombo();
	tfFechaCobro.setValue(Proced.setFormatDate(Environment.currentDate, true));
    }

    private void loadCombo() {
	cbCobrados.getCombo().removeAllItems();
	cbCobrados.getCombo().addItem("Todos","0");
	cbCobrados.getCombo().addItem("Cobrados","1");
	cbCobrados.getCombo().addItem("No Cobrados","2");
	cbCobrados.setSelectedID(0);
    }

    private void setHeaderList(){
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("Fecha de Cobro");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Type"));
	headerList.addElement(Environment.lang.getProperty("Number"));
	headerList.addElement("Banco");
	headerList.addElement(Environment.lang.getProperty("Name"));
	headerList.addElement(Environment.lang.getProperty("Amount"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Cobrado"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Rebotado"));
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("Fecha Recepción");
	headerList.addElement("*");
	headerList.addElement("Pago Nº");

	chequesDeTercerosPanel.getTable().addMouseListener(new MouseAdapter() {
	     public void mouseClicked(MouseEvent e) {
		 loadObjectSelected();
		 if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {

		 } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
		    verPago();
		 } else if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON3) {
		    
		} 
	     }
	 }
	);
	
	chequesDeTercerosPanel.setParams("cashflow.getAllPagosDeTerceros", "'','','',0", headerList);    
    }
    
    public void refresh() {
	String fecha = "";
	if (tfFechaCobro.getString().length() > 2)  {
	    fecha = Proced.setFormatDate(tfFechaCobro.getString(), false);
	}
	String params = "'"+ fecha +"','"+ tfBanco.getString() +"','"+ tfNombre.getString() +"',"+ cbCobrados.getSelectedValue();
	chequesDeTercerosPanel.refresh(params);
    }
    
    private void loadObjectSelected(){
	if (pagoDeTercero == null)  {
	    pagoDeTercero = new PagoDeTercero();
	}
	pagoDeTercero.setIdpagodetercero(Integer.parseInt(""+ dataRow.elementAt(0)));
	
    }
    
    private void btnReport_actionPerformed(ActionEvent e) {
	e.toString();
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Listado de Pagos de Terceros");
    }

    private void btnBuscar_actionPerformed(ActionEvent e) {
	refresh();
    }
    
    private void verPago(){
	parentMain.getPagosCajeroList().setPagoDeTercero(pagoDeTercero);
	parentMain.setSelectedTab(1);
    }

    public void setParentMain(PagosDeTercerosMain _parentMain) {
	parentMain = _parentMain;
    }
}
