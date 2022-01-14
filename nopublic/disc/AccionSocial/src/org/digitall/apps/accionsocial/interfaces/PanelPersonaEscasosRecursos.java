package org.digitall.apps.accionsocial.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.text.MaskFormatter;

import org.digitall.apps.accionsocial.classes.Coordinador;
import org.digitall.apps.accionsocial.classes.PersonaEscasosRecursos;
import org.digitall.apps.accionsocial.classes.errclasses.ErrorPanelPersonaEscasosRecursos;
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


public class PanelPersonaEscasosRecursos extends BasicPrimitivePanel {

    private BasicPanel container = new BasicPanel();
    private BasicPanel jpDatosPersona = new BasicPanel();
    private BasicPanel jpDatosMenor = new BasicPanel();
    
    private TFInput tfNombre = new TFInput(DataTypes.STRING, "Apellido y Nombres", false);
    private TFInput tfDNI = new TFInput(DataTypes.INTEGER, "D.N.I.", false);
    private TFInput tfCuil = new TFInput(DataTypes.STRING, "CUIL", false);
    private TFInput tfDomicilio = new TFInput(DataTypes.STRING, "Domicilio", true);
    private TFInput tfFechaAlta = new TFInput(DataTypes.SIMPLEDATE, "Fecha de Alta", true);
    
    private SaveDataButton btnSaveData = new SaveDataButton();
    private CloseButton btnClose = new CloseButton();

    private PersonaEscasosRecursos personaEscasosRecursos;
    private ErrorPanelPersonaEscasosRecursos errorPanelPersonaEscasosRecursos;
    private Coordinador coordinador;
    
    private MaskFormatter formatoCuil;
    
    public PanelPersonaEscasosRecursos(Coordinador _coordinador) {
	try {
	    coordinador = _coordinador;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(533, 193));
	this.setPreferredSize(new Dimension(565, 330));
	container.setLayout(null);
	btnSaveData.setBounds(new Rectangle(465, 330, 40, 25));
	btnSaveData.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       btnSaveData_actionPerformed(e);
				   }

			       }
	);
        tfNombre.setBounds(new Rectangle(10, 20, 265, 35));
        tfCuil.setBounds(new Rectangle(405, 20, 105, 35));

	tfDNI.setBounds(new Rectangle(285, 20, 110, 35));
	btnClose.setBounds(new Rectangle(515, 330, 40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
        jpDatosMenor.add(tfFechaAlta, null);
        jpDatosMenor.add(tfDomicilio, null);
        jpDatosPersona.add(tfDNI, null);
        jpDatosPersona.add(tfNombre, null);
        jpDatosPersona.add(tfCuil, null);
        container.add(jpDatosMenor, null);
        container.add(jpDatosPersona, null);
        this.add(container, BorderLayout.CENTER);
	this.addButton(btnClose);
	this.addButton(btnSaveData);
        btnClose.setToolTipText("Cancelar");
        formatoCuil = new MaskFormatter("##-########-#");
        formatoCuil.setPlaceholderCharacter('_');
        jpDatosPersona.setBounds(new Rectangle(5, 5, 520, 70));
        jpDatosPersona.setLayout(null);
        jpDatosPersona.setBorder(BorderPanel.getBorderPanel("Datos de la Persona"));
        jpDatosMenor.setBounds(new Rectangle(5, 80, 520, 70));
        jpDatosMenor.setLayout(null);
        jpDatosMenor.setBorder(BorderPanel.getBorderPanel("Datos para la Registración"));
        tfDomicilio.setBounds(new Rectangle(110, 20, 400, 35));
        tfFechaAlta.setBounds(new Rectangle(10, 20, 90, 35));
        tfCuil.setEditable(false);
        tfDNI.setEditable(false);
        tfNombre.setEditable(false);
        tfFechaAlta.setValue(Proced.setFormatDate(Environment.currentDate, true));
        loadData();
    }
    
    private void loadComboPersonas() {
    }
    
    private void formatoCuil() {
        formatoCuil.uninstall();
        tfCuil.setValue((coordinador.getPersona()!=null)?coordinador.getPersona().getCuil().replaceAll(" ", "-"):"00-00000000-0");
        formatoCuil.install(tfCuil.getTextField()); 
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Complete los datos y presione el botón \"Grabar\"");
    }
    
    private void loadData() {
        tfNombre.setValue(coordinador.getPersona().getApellidos() + " " + coordinador.getPersona().getNombres());
        tfDNI.setValue(coordinador.getPersona().getNroDocumento());
        tfCuil.setValue(coordinador.getPersona().getCuil());
    }
    
    private void clearFields() {
        tfFechaAlta.getTextField().setText(Proced.setFormatDate(Environment.currentDate, true));
        tfDomicilio.setValue("");
    }
    
    public boolean saveData() {
        if (control()) {
            if (personaEscasosRecursos == null) {
                personaEscasosRecursos = new PersonaEscasosRecursos();
            }
            personaEscasosRecursos.setIdPersona(coordinador.getPersona().getIdPersona());
            personaEscasosRecursos.setFechaAlta(Proced.setFormatDate(tfFechaAlta.getDate(),false));
            personaEscasosRecursos.setDomicilio(tfDomicilio.getString());
            if (personaEscasosRecursos.saveData() > 0) {
                return true;
            } else {
                Advisor.messageBox("Ocurrió un error al grabar los datos","Aviso");
                return false;
            }
        } else {
            errorPanelPersonaEscasosRecursos.showMessage();
            return false;
        }
    }

    private boolean control() {        
        boolean valor = true;
        errorPanelPersonaEscasosRecursos = new ErrorPanelPersonaEscasosRecursos();
        if (tfFechaAlta.getDate().equals("")) {
            valor = false;
            errorPanelPersonaEscasosRecursos.setError(errorPanelPersonaEscasosRecursos.fechaAlta);
        } else if (tfDomicilio.getString().equals("")) {
            valor = false;
            errorPanelPersonaEscasosRecursos.setError(errorPanelPersonaEscasosRecursos.domicilio);
        }
        return valor;
    }

    private void btnSaveData_actionPerformed(ActionEvent e) {
	if (saveData()) {
	    getParentInternalFrame().close();
	}
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }
}
