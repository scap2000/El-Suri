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
 * PanelEmbarazo.java
 *
 * */
package org.digitall.apps.accionsocial.interfaces;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.text.MaskFormatter;

import org.digitall.apps.accionsocial.classes.Coordinador;
import org.digitall.apps.accionsocial.classes.Embarazo;
import org.digitall.apps.accionsocial.classes.errclasses.ErrorPanelEmbarazo;
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


public class PanelEmbarazo extends BasicPrimitivePanel {

    private BasicPanel panelData = new BasicPanel();
    private BasicPanel pDatosEmbarazada = new BasicPanel();
    private BasicPanel pDatosEmbarazo = new BasicPanel();
    
    private TFInput tfNombre = new TFInput(DataTypes.STRING, "Apellido y Nombres", false);
    private TFInput tfDNI = new TFInput(DataTypes.INTEGER, "D.N.I.", false);
    private TFInput tfCuil = new TFInput(DataTypes.STRING, "CUIL", false);
    private TFInput tfFechaAlta = new TFInput(DataTypes.SIMPLEDATE, "Fecha de Alta", true);
    private TFInput tfFPP = new TFInput(DataTypes.SIMPLEDATE, "FPP", true);
    private TFInput tfFUM = new TFInput(DataTypes.SIMPLEDATE, "FUM", true);
    private TFInput tfBarrio = new TFInput(DataTypes.STRING, "Barrio", true);

    private SaveDataButton btnSaveData = new SaveDataButton();
    private CloseButton btnClose = new CloseButton();

    private Embarazo embarazo;
    private ErrorPanelEmbarazo errorPanelEmbarazo;
    private Coordinador coordinador;
    
    private MaskFormatter formatoCuil;

    public PanelEmbarazo(Coordinador _coordinador) {
	try {
	    coordinador = _coordinador;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(533, 239));
	this.setPreferredSize(new Dimension(565, 330));
	panelData.setLayout(null);
	btnSaveData.setBounds(new Rectangle(465, 330, 40, 25));
	btnSaveData.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       btnSaveData_actionPerformed(e);
				   }

			       }
	);
        tfNombre.setBounds(new Rectangle(10, 20, 265, 35));
	tfFPP.setBounds(new Rectangle(220, 20, 85, 35));
	tfCuil.setBounds(new Rectangle(405, 20, 105, 35));

	tfDNI.setBounds(new Rectangle(285, 20, 110, 35));
	btnClose.setBounds(new Rectangle(515, 330, 40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);


        tfBarrio.setBounds(new Rectangle(10, 65, 500, 35));
        tfFUM.setBounds(new Rectangle(415, 20, 95, 35));
        pDatosEmbarazo.add(tfFechaAlta, null);
        pDatosEmbarazo.add(tfFPP, null);
        pDatosEmbarazo.add(tfFUM, null);
        pDatosEmbarazo.add(tfBarrio, null);
        pDatosEmbarazada.add(tfDNI, null);
        pDatosEmbarazada.add(tfNombre, null);
        pDatosEmbarazada.add(tfCuil, null);
        panelData.add(pDatosEmbarazo, null);
        panelData.add(pDatosEmbarazada, null);
        this.add(panelData, null);
	this.addButton(btnClose);
	this.addButton(btnSaveData);
        btnClose.setToolTipText("Cancelar");
        formatoCuil = new MaskFormatter("##-########-#");
        formatoCuil.setPlaceholderCharacter('_');
        pDatosEmbarazada.setBounds(new Rectangle(5, 5, 520, 70));
        pDatosEmbarazada.setLayout(null);
        pDatosEmbarazada.setBorder(BorderPanel.getBorderPanel("Datos de la Embarazada"));
        pDatosEmbarazo.setBounds(new Rectangle(5, 80, 520, 110));
        pDatosEmbarazo.setLayout(null);
        pDatosEmbarazo.setBorder(BorderPanel.getBorderPanel("Datos para la Registración"));
        tfFechaAlta.setBounds(new Rectangle(10, 20, 85, 35));
        tfCuil.setEditable(false);
        tfDNI.setEditable(false);
        tfNombre.setEditable(false);
        tfFechaAlta.getTextField().setText(Proced.setFormatDate(Environment.currentDate, true));
        loadData();
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
        tfFPP.setValue("");
        tfFUM.setValue("");
        tfFechaAlta.getTextField().setText(Proced.setFormatDate(Environment.currentDate, true));
        tfBarrio.setValue("");
    }

    public boolean saveData() {
        if (control()) {
            if (embarazo == null) {
                embarazo = new Embarazo();
            }
            embarazo.setIdPersona(coordinador.getPersona().getIdPersona());
            embarazo.setFechaAlta(Proced.setFormatDate(tfFechaAlta.getDate(),false));
            embarazo.setBarrio(tfBarrio.getString());
            embarazo.setFum(Proced.setFormatDate(tfFUM.getDate(),false));
            embarazo.setFpp(Proced.setFormatDate(tfFPP.getDate(),false));
            if (embarazo.saveData() > 0) {
                return true;
            } else {
                Advisor.messageBox("Ocurrió un error al grabar los datos","Aviso");
                return false;
            }
        } else {
            errorPanelEmbarazo.showMessage();
            return false;
        }
    }

    private boolean control() {        
        boolean valor = true;
        errorPanelEmbarazo = new ErrorPanelEmbarazo();
        if (tfFechaAlta.getDate().equals("")) {
            valor = false;
            errorPanelEmbarazo.setError(errorPanelEmbarazo.fechaAlta);
        } else if (tfFPP.getDate().equals("")) {
            valor = false;
            errorPanelEmbarazo.setError(errorPanelEmbarazo.fechaFPP);
        } else if (tfFUM.getDate().equals("")) {
            valor = false;
            errorPanelEmbarazo.setError(errorPanelEmbarazo.fechaFUM);
        } else if (tfBarrio.getString().equals("")) {
            valor = false;
            errorPanelEmbarazo.setError(errorPanelEmbarazo.barrio);
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
