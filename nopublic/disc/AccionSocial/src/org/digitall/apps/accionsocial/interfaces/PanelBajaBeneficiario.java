package org.digitall.apps.accionsocial.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.text.MaskFormatter;

import org.digitall.apps.accionsocial.classes.Coordinador;
import org.digitall.apps.accionsocial.classes.Persona;
import org.digitall.apps.accionsocial.classes.errclasses.ErrorFormPersonasMgmt;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;


public class PanelBajaBeneficiario extends BasicPrimitivePanel {

    private BasicPanel panelData = new BasicPanel();


    private SaveDataButton btnSaveData = new SaveDataButton();
    private CloseButton btnClose = new CloseButton();

    private Persona persona;
    private ErrorFormPersonasMgmt errorFormPersonasMgmt;
    private Coordinador coordinador;
    
    private MaskFormatter formatoCuil;
    private BasicPanel pDatosTBC = new BasicPanel();
    private TFInput tfFechaBaja = new TFInput(DataTypes.STRING, "Fecha de Baja", false);
    private TFInput tfMotivoBaja = new TFInput(DataTypes.STRING, "Motivo de Baja", false);

    public PanelBajaBeneficiario(Coordinador _coordinador) {
	try {
	    coordinador = _coordinador;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(533, 130));
	this.setPreferredSize(new Dimension(565, 330));
	panelData.setLayout(null);
	btnSaveData.setBounds(new Rectangle(465, 330, 40, 25));
	btnSaveData.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       btnSaveData_actionPerformed(e);
				   }

			       }
	);

        btnClose.setBounds(new Rectangle(515, 330, 40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);


        pDatosTBC.add(tfMotivoBaja, null);
        pDatosTBC.add(tfFechaBaja, null);
        panelData.add(pDatosTBC, null);
        this.add(panelData, BorderLayout.CENTER);
	this.addButton(btnClose);
	this.addButton(btnSaveData);
        btnClose.setToolTipText("Cancelar");
        formatoCuil = new MaskFormatter("##-########-#");
        formatoCuil.setPlaceholderCharacter('_');
        pDatosTBC.setBounds(new Rectangle(5, 5, 520, 75));
        pDatosTBC.setLayout(null);
        pDatosTBC.setBorder(BorderPanel.getBorderPanel("Datos de Baja de Beneficio"));
        tfFechaBaja.setBounds(new Rectangle(10, 20, 85, 35));
        tfMotivoBaja.setBounds(new Rectangle(105, 20, 405, 35));
        loadData();
        habilitarComponentes(false);
    }
    
    private void formatoCuil() {
        
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Complete los datos y presione el botón \"Grabar\"");
    }
    
    private void loadData() {
        
    }

    public boolean saveData() {
        if (control()) {
            
            if (persona.saveData() > 0) {
                return true;
            } else {
                Advisor.messageBox("Ocurrió un error al grabar los datos","Aviso");
                return false;
            }
        } else {
            errorFormPersonasMgmt.showMessage();
            return false;
        }
    }

    private boolean control() {        
        boolean valor = true;
        return valor;
    }

    private void btnSaveData_actionPerformed(ActionEvent e) {
	if (saveData()) {
            coordinador.inicio();
	    getParentInternalFrame().close();
	}
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    private void habilitarComponentes(boolean _enabled) {
        tfFechaBaja.setEditable(_enabled);
        tfMotivoBaja.setEditable(_enabled);
    }
}
