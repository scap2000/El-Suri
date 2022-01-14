package org.digitall.apps.taxes.interfases.cashier;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

import org.digitall.apps.taxes.classes.Caja;
import org.digitall.apps.taxes.classes.ContratoPlanDePago;
import org.digitall.apps.taxes.classes.Coordinador;
import org.digitall.apps.taxes.classes.MovimientoCaja;
import org.digitall.apps.taxes.interfases.TaxesTGS;
import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.misc.BarCode;
import org.digitall.lib.sql.LibSQL;

public class CashierButtons extends BasicPrimitivePanel {

    private BasicPanel content = new BasicPanel();

    private Coordinador coordinador = new Coordinador();

    private JButton btnApertura = new JButton();
    private JButton btnArqueo = new JButton();
    private JButton btnEgresos = new JButton();
    private JButton btnAcreditaciones = new JButton();
    private JButton btnCierre = new JButton();
    private CloseButton btnClose = new CloseButton();
    
    private JLabel lblLogo = new JLabel();
    
    private CashierMain parentMain;
    private MovimientoCaja movimientoCaja;

    public CashierButtons() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(700, 537));
	this.setPreferredSize(new Dimension(700, 537));
	this.setTitle("Opciones");
	content.setLayout(null);
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	content.add(btnAcreditaciones, null);
	content.add(btnEgresos, null);
	content.add(lblLogo, null);
	content.add(btnCierre, null);
	content.add(btnArqueo, null);
	content.add(btnApertura, null);
	this.add(content, BorderLayout.CENTER);
	addButton(btnClose);
	btnClose.setToolTipText("Imprimir");

	btnApertura.setText("Apertura de Caja");
	btnApertura.setBounds(new Rectangle(90, 75, 210, 45));
	btnApertura.setFont(new Font("Dialog", 1, 14));
	btnApertura.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnApertura_actionPerformed(e);
		}
	    }
	);
	btnArqueo.setText("Arqueo de Caja");
	btnArqueo.setBounds(new Rectangle(90, 150, 210, 45));
	btnArqueo.setFont(new Font("Dialog", 1, 14));
	btnArqueo.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnArqueo_actionPerformed(e);
		}
	    }
	);
	btnCierre.setText("Cierre de Caja");
	btnCierre.setBounds(new Rectangle(90, 375, 210, 45));
	btnCierre.setFont(new Font("Dialog", 1, 14));
	btnCierre.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnCierre_actionPerformed(e);
		}
	    }
	);
	lblLogo.setBounds(new Rectangle(395, 100, 275, 300));
	lblLogo.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	btnEgresos.setText("Egresos");
	btnEgresos.setBounds(new Rectangle(90, 225, 210, 45));
	btnEgresos.setFont(new Font("Dialog", 1, 14));
	btnEgresos.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnEgresos_actionPerformed(e);
		}
	    }
	);
	btnAcreditaciones.setText("Acreditaciones");
	btnAcreditaciones.setBounds(new Rectangle(90, 300, 210, 45));
	btnAcreditaciones.setFont(new Font("Dialog", 1, 14));
	btnAcreditaciones.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnAcreditaciones_actionPerformed(e);
		}
	    }
	);
	content.setBorder(BorderPanel.getBorderPanel(""));
	btnClose.setVisible(false);
    }

    

    private void btnClose_actionPerformed(ActionEvent e) {
	parentMain.getParentInternalFrame().setIcon(true);
    }

    public void setCoordinador(Coordinador _coordinador){
	coordinador = _coordinador;
	btnClose.setEnabled(true);
    }

    public void setEnabledButtons(boolean _valor){
	btnApertura.setEnabled(!_valor);
	btnArqueo.setEnabled(_valor);
	btnCierre.setEnabled(_valor);
	btnEgresos.setEnabled(false);
	btnAcreditaciones.setEnabled(_valor);
    }

    private void btnApertura_actionPerformed(ActionEvent e) { /** Apertura de Caja */
/*	MovimientoCaja _apertura = new MovimientoCaja(1);
	_apertura.retrieveData();
	Caja _caja = new Caja(_apertura.getIdCaja());
	_caja.retrieveData();
	MovimientoCaja _cierre = new MovimientoCaja(3);
	_cierre.retrieveData();
	_cierre.getIdCaja();
	BasicReport report = new BasicReport(CashierCloseMgmt.class.getResource("xml/CierreCaja.xml"));
	report.setProperty("caja", "Caja Nº " + _caja.getNroCaja());
	report.setProperty("fechaapertura", Proced.setFormatDate(_apertura.getFecha(), true));
	report.setProperty("horaapertura", _apertura.getHora());
	report.setProperty("montoapertura", _apertura.getMonto1());
	report.setProperty("fechacierre", Proced.setFormatDate(_cierre.getFecha(), true));
	report.setProperty("horacierre", _cierre.getHora());
	report.setProperty("montocierre", _cierre.getMonto2());
	report.setProperty("cajero", LibSQL.getString("cashier.getnombreultimocajeroencaja", _caja.getIdCaja()));
	report.addTableModel("cashier.xmlgetcierrecaja", _caja.getIdCaja());
	report.doReport();
*/
	movimientoCaja = new MovimientoCaja();
	movimientoCaja.setIdtipomovimiento(1);
	movimientoCaja.setNroCaja(1);
	CashierOpenMgmt cashierOpenMgmt = new CashierOpenMgmt();
	cashierOpenMgmt.setParentMain(this);
	cashierOpenMgmt.setMovimientoCaja(movimientoCaja);
	ExtendedInternalFrame cashierOpenMgmtContainer = new ExtendedInternalFrame("Apertura de Caja");
	cashierOpenMgmtContainer.setCentralPanel(cashierOpenMgmt);
	cashierOpenMgmtContainer.setClosable(false);
	cashierOpenMgmtContainer.setIconifiable(false);
	cashierOpenMgmtContainer.show();
    }

    private void btnArqueo_actionPerformed(ActionEvent e) {  /** Arqueo de Caja */
	/*movimientoCaja = new MovimientoCaja();
	movimientoCaja.setIdtipomovimiento(2);
	movimientoCaja.setNroCaja(1);
	CashierOpenMgmt cashierOpenMgmt = new CashierOpenMgmt();
	cashierOpenMgmt.setParentMain(this);
	cashierOpenMgmt.setMovimientoCaja(movimientoCaja);
	ExtendedInternalFrame cashierOpenMgmtContainer = new ExtendedInternalFrame("Arqueo de Caja");
	cashierOpenMgmtContainer.setCentralPanel(cashierOpenMgmt);
	cashierOpenMgmtContainer.show();
	*/
	 Advisor.messageBox("Funcionalidad en desarrollo","Mensaje");
    }

    private void btnCierre_actionPerformed(ActionEvent e) {  /** Cierre de Caja */
	CashierCloseMgmt cashierCloseMgmt = new CashierCloseMgmt();
	cashierCloseMgmt.setParentMain(this);
	cashierCloseMgmt.setMovimientoCaja(parentMain.getCaja());
	ExtendedInternalFrame cashierCloseMgmtContainer = new ExtendedInternalFrame("Cierre de Caja");
	cashierCloseMgmtContainer.setCentralPanel(cashierCloseMgmt);
	cashierCloseMgmtContainer.setClosable(false);
	cashierCloseMgmtContainer.setIconifiable(false);
	cashierCloseMgmtContainer.show();
    }
    
    public void check(){
	parentMain.setEnabledForms();
    }

    public void setParentMain(CashierMain parentMain) {
	this.parentMain = parentMain;
    }

    public CashierMain getParentMain() {
	return parentMain;
    }

    private void btnEgresos_actionPerformed(ActionEvent e) {
	
    }

    private void btnAcreditaciones_actionPerformed(ActionEvent e) {
	/*AcreditationMgmt acreditationMgmt = new AcreditationMgmt();
	acreditationMgmt.setParentMain(this);
	ExtendedInternalFrame acreditationMgmtContainer = new ExtendedInternalFrame("Registrar Acreditaciones");
	acreditationMgmtContainer.setCentralPanel(acreditationMgmt);
	acreditationMgmtContainer.show();*/
	Advisor.messageBox("Funcionalidad en desarrollo","Mensaje");
    }
}
