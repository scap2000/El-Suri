package org.digitall.apps.taxes.interfases.cashier;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import org.digitall.apps.taxes.classes.Caja;
import org.digitall.apps.taxes.classes.MovimientoCaja;
import org.digitall.common.components.inputpanels.CBInput;
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
import org.digitall.lib.sql.LibSQL;

public class CashierOpenMgmt extends BasicPrimitivePanel {

    private BasicPanel content = new BasicPanel();

    private SaveDataButton btnSaveData = new SaveDataButton();
    private CloseButton btnClose = new CloseButton();

    private CBInput cbCajas = new CBInput(0,"Caja", false);

    private TFInput tfFecha = new TFInput(DataTypes.DATE,"Date",false);
    private TFInput tfMonto = new TFInput(DataTypes.MONEY,"Amount",false);
    
    private CashierButtons parentMain;
    private MovimientoCaja movimientoCaja;
    private Caja caja;

    public CashierOpenMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(375, 121));
	this.setBounds(new Rectangle(10, 10, 395, 135));
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

	tfFecha.setBounds(new Rectangle(10, 25, 90, 35));
	tfMonto.setBounds(new Rectangle(235, 25, 140, 35));
	cbCajas.setBounds(new Rectangle(125, 25, 90, 35));
	tfMonto.getTextField().addKeyListener(new KeyAdapter() {
		
		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			save();
		    }
		}
	    }
	);
	content.add(cbCajas, null);
	content.add(tfMonto, null);
	content.add(tfFecha, null);
	cbCajas.autoSize();
	this.add(content, null);
	addButton(btnClose);
	addButton(btnSaveData);
	tfFecha.setEnabled(false);
	tfFecha.setValue(Proced.setFormatDate(Environment.currentDate.toString(),true));
	content.setBorder(BorderPanel.getBorderPanel("Monto"));
	loadComboCajas();
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
    }
    
    private void loadComboCajas() {
	cbCajas.loadJCombo(LibSQL.exFunction("cashier.getAllCajas",""));
    }
    
    private void btnSaveData_actionPerformed(ActionEvent e) {
	save();
    }

    private void btnClose_actionPerformed(ActionEvent e){
	getParentInternalFrame().close();
    }

    private void save(){
	if (tfMonto.getAmount() > 0) {
	    loadObject();
	    /* La siguiente linea fue modificada [.getTextField().getText() en lugar de .getAmount()] por problemas de formato */
	     if (Advisor.question("Aviso","¿Está seguro de hacer la apertura de Caja\ncon un monto de "+ tfMonto.getTextField().getText() +" ?")) {
		 if (movimientoCaja.saveData() > 0) {
		     parentMain.check();
		     getParentInternalFrame().close();
		 } else {
		     Advisor.messageBox("Ocurrió un error, no se grabaron los datos","Mensaje");
		 }
	     }
	} else {
	    Advisor.messageBox("El Monto debe ser mayor que cero","Mensaje");
	}
    }
    
    private void loadObject() {
	movimientoCaja.setMonto1(tfMonto.getAmount());
	movimientoCaja.setNroCaja(Integer.parseInt(cbCajas.getSelectedValueRef().toString()));
	movimientoCaja.setIdCaja(Integer.parseInt(cbCajas.getSelectedValueRef().toString()));
	caja = new Caja(Integer.parseInt(cbCajas.getSelectedValueRef().toString()));
	caja.retrieveData();
    }

    public void setParentMain(CashierButtons parentMain) {
	this.parentMain = parentMain;
    }

    public CashierButtons getParentMain() {
	return parentMain;
    }

    public void setMovimientoCaja(MovimientoCaja movimientoCaja) {
	this.movimientoCaja = movimientoCaja;
    }
    
}

