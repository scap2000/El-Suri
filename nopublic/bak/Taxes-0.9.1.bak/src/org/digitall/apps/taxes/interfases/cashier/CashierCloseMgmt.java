package org.digitall.apps.taxes.interfases.cashier;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import org.digitall.apps.taxes.classes.Caja;
import org.digitall.apps.taxes.classes.MovimientoCaja;
import org.digitall.apps.taxes.interfases.TaxesTGS;
import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.misc.BarCode;
import org.digitall.lib.sql.LibSQL;

public class CashierCloseMgmt extends BasicPrimitivePanel {

    private BasicPanel content = new BasicPanel();

    private SaveDataButton btnSaveData = new SaveDataButton();
    private CloseButton btnClose = new CloseButton();
    
    private TFInput tfHoraInicio = new TFInput(DataTypes.DATE,"Hora de Apertura",false);
    private TFInput tfMontoApertura = new TFInput(DataTypes.MONEY,"Monto de apertura",false);
    private TFInput tfFechaCierre = new TFInput(DataTypes.DATE,"Fecha",false);
    private TFInput tfMontoCierreSistema = new TFInput(DataTypes.MONEY,"Monto de Cierre del Sist.",false);
    private TFInput tfMontoCierreUsuario = new TFInput(DataTypes.MONEY,"Monto de Cierre del Cajero",false);
    private TFInput tfDiferencia = new TFInput(DataTypes.MONEY,"Diferencia",false);
    
    private CashierButtons parentMain;
    private MovimientoCaja movimientoCierreCaja;
    private MovimientoCaja movimientoAperturaCaja;
    private Caja caja;

    private TFInput tfNroCaja = new TFInput(DataTypes.STRING,"Caja",false);

    public CashierCloseMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(512, 173));
	this.setBounds(new Rectangle(10, 10, 515, 173));
	this.setTitle("Caja");
	content.setLayout(null);

	btnSaveData.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					btnSaveData_actionPerformed(e);
				    }

				}
	);

	btnClose.addActionListener(new ActionListener() {
		    
			public void actionPerformed(ActionEvent e){
			    btnClose_actionPerformed(e);
			}
	});

	tfHoraInicio.setBounds(new Rectangle(195, 30, 120, 35));
	tfMontoApertura.setBounds(new Rectangle(380, 30, 115, 35));
	tfFechaCierre.setBounds(new Rectangle(85, 25, 90, 35));
	tfMontoCierreSistema.setBounds(new Rectangle(10, 80, 165, 35));
	tfMontoCierreUsuario.setBounds(new Rectangle(195, 80, 165, 35));
	tfDiferencia.setBounds(new Rectangle(380, 80, 115, 35));
	tfMontoCierreUsuario.getTextField().addKeyListener(new KeyAdapter() {
		
		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			setMontoCajero();
		    }
		}
	    }
	);
	content.add(tfNroCaja);
	content.add(tfDiferencia, null);
	content.add(tfMontoCierreUsuario, null);
	content.add(tfMontoCierreSistema, null);
	content.add(tfFechaCierre, null);
	content.add(tfMontoApertura, null);
	content.add(tfHoraInicio, null);
	this.add(content, null);
	addButton(btnClose);
	addButton(btnSaveData);
	tfHoraInicio.setEnabled(false);
	tfMontoApertura.setEnabled(false);
	tfFechaCierre.setEnabled(false);
	tfMontoCierreSistema.setEnabled(false);
	tfDiferencia.setEditable(false);
	tfNroCaja.setEditable(false);
	tfNroCaja.setEnabled(false);
	tfDiferencia.setForeground(Color.blue);
	tfNroCaja.setBounds(new Rectangle(15, 25, 60, 35));
	tfFechaCierre.setValue(Proced.setFormatDate(Environment.currentDate,true));
	content.setBorder(BorderPanel.getBorderPanel("Cierre de Caja"));
	tfDiferencia.getTextField().setOpaque(true);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
    }
    private void btnSaveData_actionPerformed(ActionEvent e) {
	save();
    }

    private void btnClose_actionPerformed(ActionEvent e){
	getParentInternalFrame().close();
    }

    private void loadCierreCaja() {
	movimientoCierreCaja = new MovimientoCaja(3);
	movimientoCierreCaja.setNroCaja(caja.getNroCaja());
	movimientoCierreCaja.setMonto1(tfMontoCierreSistema.getAmount());
	movimientoCierreCaja.setMonto2(tfMontoCierreUsuario.getAmount());
	movimientoCierreCaja.setIdCaja(caja.getIdCaja());
    }

    private void save(){
	loadCierreCaja();
	/* La siguiente linea fue modificada [.getTextField().getText() en lugar de .getAmount()] por problemas de formato */
	if (Advisor.question("Aviso","¿Está seguro de hacer el Cierre de Caja\ncon un monto de "+ tfMontoCierreUsuario.getTextField().getText() +" ?")) {
	    if (movimientoCierreCaja.saveData() > 0) {
		parentMain.check();
		getParentInternalFrame().close();
	    } else {
		     Advisor.messageBox("Ocurrió un error, no se grabaron los datos","Mensaje");
	    }
	}
    }
    
    public void setParentMain(CashierButtons parentMain) {
	this.parentMain = parentMain;
    }

    public CashierButtons getParentMain() {
	return parentMain;
    }

    public void setMovimientoCaja(Caja _caja) {
	caja = _caja;
	movimientoAperturaCaja = new MovimientoCaja(1);
	movimientoAperturaCaja.retrieveData();
	loadForm();
    }
    
    private void loadForm(){
	tfHoraInicio.setValue(movimientoAperturaCaja.getHora());
	tfMontoApertura.setValue(movimientoAperturaCaja.getMonto1());
	double recaudacion = LibSQL.getDouble("cashier.getRecaudacion","");
	tfMontoCierreSistema.setValue(recaudacion);
	setMontoCajero();
	tfNroCaja.setText("Caja Nº "+ caja.getNroCaja());
    }
    
    private void controlDiferencia(){
	if (tfDiferencia.getAmount() == 0.0)  {
	    tfDiferencia.getTextField().setBackground(Color.GREEN);
	} else if (tfDiferencia.getAmount() < 0.0) {
	    tfDiferencia.getTextField().setBackground(Color.RED);
	    tfDiferencia.setValue(Math.abs(tfDiferencia.getAmount()));
	} else {
	    tfDiferencia.getTextField().setBackground(Color.YELLOW);
	}
    }
    
    private void setMontoCajero(){
	tfDiferencia.setValue( tfMontoCierreUsuario.getAmount() - tfMontoCierreSistema.getAmount() );
	controlDiferencia();
    }

}
