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
 * PanelBajaBeneficiario.java
 *
 * */
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
