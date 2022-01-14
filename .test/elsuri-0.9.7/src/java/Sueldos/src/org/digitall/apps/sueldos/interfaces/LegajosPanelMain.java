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
 * LegajosPanelMain.java
 *
 * */
package org.digitall.apps.sueldos.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import org.digitall.common.personalfiles.classes.Legajo;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.AddComboButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.EditButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.image.ImageLabel;
import org.digitall.lib.image.LibIMG;

public class LegajosPanelMain extends BasicPrimitivePanel{
    
    private BasicPanel panelNorte = new BasicPanel();
    private BasicPanel panelCentro = new BasicPanel();
    private LegajosSearchPanel legajosSearchPanel = new LegajosSearchPanel();
    private BasicPanel pPrincipalData = new BasicPanel();
    private BasicPanel pJobData = new BasicPanel();
    private BasicPanel pAddressData = new BasicPanel();
    private BasicPanel pOtherData = new BasicPanel();
    private BasicPanel pRetirementData = new BasicPanel();
    
    private TFInput tfLegajo = new TFInput(DataTypes.INTEGER, "Nº Legajo",false);
    private TFInput tfNombre = new TFInput(DataTypes.STRING, "Nombres",false);
    private TFInput tfApellido = new TFInput(DataTypes.STRING, "Apellido",false);
    private TFInput tfApellidoCasada = new TFInput(DataTypes.STRING, "Apellido Cónyuge",false);
    
    private TFInput tfFechaAlta = new TFInput(DataTypes.DATE, "Fecha de Alta",false);
    private TFInput tfFechaBaja = new TFInput(DataTypes.DATE, "Fecha de Baja",false);
    private TFInput tfAntiguedad = new TFInput(DataTypes.STRING, "Antigüedad",false);
    private TFInput tfCuil = new TFInput(DataTypes.STRING, "C.U.I.L.",false);
    
    private TFInput tfCalle = new TFInput(DataTypes.STRING, "Calle",false);
    private TFInput tfNumero = new TFInput(DataTypes.STRING, "Número",false);
    private TFInput tfPiso = new TFInput(DataTypes.STRING, "Piso",false);
    private TFInput tfDepto = new TFInput(DataTypes.STRING, "Departamento",false);
    private TFInput tfLocalidad = new TFInput(DataTypes.STRING, "Localidad",false);
    private TFInput tfProvincia = new TFInput(DataTypes.STRING, "Provincia",false);
    private TFInput tfCodPostal = new TFInput(DataTypes.STRING, "Código Postal",false);
    private TFInput tfTel = new TFInput(DataTypes.STRING, "Teléfono",false);
    
    private CBInput cbCivilState = new CBInput(CachedCombo.CIVILSTATE_TABS,"Estado Civil",false);
    private CBInput cbBornCountry = new CBInput(CachedCombo.COUNTRY_TABS, "Nationality");
    private TFInput tfFechaNac = new TFInput(DataTypes.DATE, "Fecha de Nacimiento",false);
    private TFInput tfTarea = new TFInput(DataTypes.STRING, "Tarea",false);
    private TFInput tfObraSocial = new TFInput(DataTypes.STRING, "Obra Social",false);
    private TFInput tfPlanOS = new TFInput(DataTypes.STRING, "Plan Obra Social",false);
    private TFInput tfSeguro = new TFInput(DataTypes.STRING, "Seguro",false);
    
    private CBInput cbCodigos = new CBInput(0,"Código",false);
    private CBInput cbCategorias = new CBInput(0,"Categoria",false);
    private TFInput tfNumCuentaBancaria = new TFInput(DataTypes.STRING, "Nº Cuenta Bancaria",false);
    private TFInput tfDocumento = new TFInput(DataTypes.STRING, "Documento",false);
    private TFInput tfSueldo = new TFInput(DataTypes.MONEY, "Sueldo",false);
    private ImageLabel lblPhoto = new ImageLabel();   
    
    private CloseButton btnClose = new CloseButton();  
    private AddComboButton btnAddConcept = new AddComboButton();
    private AddButton btnNew = new AddButton();
    private EditButton btnEdit= new EditButton();
    private SaveDataButton btnSave = new SaveDataButton();
    
    private Vector fields = new Vector();
    private Vector combos = new Vector();
    private Legajo legajo = new Legajo();

    public LegajosPanelMain() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	legajosSearchPanel.setPreferredSize(new Dimension(185, 35));
	legajosSearchPanel.setBounds(new Rectangle(5, 5, 185, 35));
	legajosSearchPanel.setBounds(new Rectangle(5, 5, 185, 35));
	pJobData.setBounds(new Rectangle(190, 5, 165, 175));
	pAddressData.setBounds(new Rectangle(375, 5, 165, 350));
	pOtherData.setBounds(new Rectangle(560, 5, 165, 350));
	pRetirementData.setBounds(new Rectangle(5, 190, 350, 165));
	pPrincipalData.setBorder(BorderPanel.getBorderPanel(""));
	pPrincipalData.setLayout(null);
	pPrincipalData.setBounds(new Rectangle(5, 5, 165, 175));
	pJobData.setBorder(BorderPanel.getBorderPanel(""));
	pJobData.setLayout(null);
	pAddressData.setBorder(BorderPanel.getBorderPanel(""));
	pAddressData.setLayout(null);
	pOtherData.setBorder(BorderPanel.getBorderPanel(""));
	pOtherData.setLayout(null);
	pRetirementData.setBorder(BorderPanel.getBorderPanel(""));
	pRetirementData.setLayout(null);
	
	tfLegajo.setPreferredSize(new Dimension(61, 35));
	tfLegajo.setBounds(new Rectangle(10, 5, 70, 35));
	tfLegajo.setSize(new Dimension(70, 35));
	tfApellido.setPreferredSize(new Dimension(145, 35));
	tfApellido.setBounds(new Rectangle(10, 90, 145, 35));
	tfApellido.setMinimumSize(new Dimension(145, 35));
	tfApellidoCasada.setPreferredSize(new Dimension(145, 35));
	tfApellidoCasada.setBounds(new Rectangle(10, 130, 145, 35));
	tfApellidoCasada.setMinimumSize(new Dimension(145, 35));
	tfNombre.setPreferredSize(new Dimension(145, 35));
	tfNombre.setBounds(new Rectangle(10, 45, 145, 35));
	tfNombre.setMinimumSize(new Dimension(145, 35));
	
	tfFechaAlta.setPreferredSize(new Dimension(61, 35));
	tfFechaAlta.setBounds(new Rectangle(10, 5, 90, 35));
	tfFechaBaja.setPreferredSize(new Dimension(145, 35));
	tfFechaBaja.setBounds(new Rectangle(10, 47, 145, 35));
	tfFechaBaja.setMinimumSize(new Dimension(145, 35));
	tfAntiguedad.setPreferredSize(new Dimension(145, 35));
	tfAntiguedad.setBounds(new Rectangle(10, 130, 145, 35));
	tfAntiguedad.setMinimumSize(new Dimension(145, 35));
	tfCuil.setPreferredSize(new Dimension(145, 35));
	tfCuil.setBounds(new Rectangle(10, 88, 145, 35));
	tfCuil.setMinimumSize(new Dimension(145, 35));
	
	tfCalle.setPreferredSize(new Dimension(145, 35));
	tfCalle.setBounds(new Rectangle(10, 5, 145, 35));
	tfCalle.setMinimumSize(new Dimension(145, 35));
	tfNumero.setPreferredSize(new Dimension(145, 35));
	tfNumero.setBounds(new Rectangle(10, 47, 145, 35));
	tfNumero.setMinimumSize(new Dimension(145, 35));
	tfPiso.setPreferredSize(new Dimension(145, 35));
	tfPiso.setBounds(new Rectangle(10, 88, 145, 35));
	tfPiso.setMinimumSize(new Dimension(145, 35));
	tfDepto.setPreferredSize(new Dimension(145, 35));
	tfDepto.setBounds(new Rectangle(10, 130, 145, 35));
	tfDepto.setMinimumSize(new Dimension(145, 35));
	tfLocalidad.setPreferredSize(new Dimension(145, 35));
	tfLocalidad.setBounds(new Rectangle(10, 185, 145, 35));
	tfProvincia.setPreferredSize(new Dimension(145, 35));
	tfProvincia.setBounds(new Rectangle(10, 227, 145, 35));
	tfProvincia.setMinimumSize(new Dimension(145, 35));
	tfCodPostal.setMinimumSize(new Dimension(145, 35));
	tfCodPostal.setPreferredSize(new Dimension(145, 35));
	tfCodPostal.setBounds(new Rectangle(10, 268, 145, 35));
	tfTel.setMinimumSize(new Dimension(145, 35));
	tfTel.setBounds(new Rectangle(10, 310, 145, 35));
	tfTel.setMinimumSize(new Dimension(145, 35));
	
	cbCivilState.setPreferredSize(new Dimension(145, 35));
	cbCivilState.setBounds(new Rectangle(10, 5, 145, 35));
	cbCivilState.setMinimumSize(new Dimension(145, 35));
	cbBornCountry.setPreferredSize(new Dimension(145, 35));
	cbBornCountry.setBounds(new Rectangle(10, 47, 145, 35));
	cbBornCountry.setMinimumSize(new Dimension(145, 35));
	tfFechaNac.setPreferredSize(new Dimension(145, 35));
	tfFechaNac.setBounds(new Rectangle(10, 88, 145, 35));
	tfFechaNac.setMinimumSize(new Dimension(145, 35));
	cbCategorias.setPreferredSize(new Dimension(145, 35));
	cbCategorias.setBounds(new Rectangle(195, 85, 145, 35));
	cbCategorias.setMinimumSize(new Dimension(145, 35));
	tfTarea.setPreferredSize(new Dimension(145, 35));
	tfTarea.setBounds(new Rectangle(10, 185, 145, 35));
	tfTarea.setMinimumSize(new Dimension(145, 35));
	tfObraSocial.setPreferredSize(new Dimension(145, 35));
	tfObraSocial.setBounds(new Rectangle(10, 268, 145, 35));
	tfObraSocial.setMinimumSize(new Dimension(145, 35));
	tfPlanOS.setPreferredSize(new Dimension(145, 35));
	tfPlanOS.setBounds(new Rectangle(10, 227, 145, 35));
	tfPlanOS.setMinimumSize(new Dimension(145, 35));
	tfSeguro.setPreferredSize(new Dimension(145, 35));
	tfSeguro.setBounds(new Rectangle(10, 310, 145, 35));
	tfSeguro.setMinimumSize(new Dimension(145, 35));
	
	cbCodigos.setPreferredSize(new Dimension(145, 35));
	cbCodigos.setBounds(new Rectangle(195, 10, 145, 35));
	cbCodigos.setMinimumSize(new Dimension(145, 35));
	tfNumCuentaBancaria.setPreferredSize(new Dimension(145, 35));
	tfNumCuentaBancaria.setBounds(new Rectangle(195, 48, 145, 35));
	tfNumCuentaBancaria.setMinimumSize(new Dimension(145, 35));
	tfDocumento.setPreferredSize(new Dimension(145, 35));
	tfDocumento.setBounds(new Rectangle(10, 130, 145, 35));
	tfDocumento.setMinimumSize(new Dimension(145, 35));
	tfSueldo.setPreferredSize(new Dimension(145, 35));
	tfSueldo.setBounds(new Rectangle(195, 125, 145, 35));
	tfSueldo.setMinimumSize(new Dimension(145, 35));
	lblPhoto.setPreferredSize(new Dimension(155, 155));
	lblPhoto.setBounds(new Rectangle(5, 5, 155, 155));
	lblPhoto.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	lblPhoto.setHorizontalAlignment(SwingConstants.CENTER);
	
	panelNorte.setBorder(BorderPanel.getBorderPanel(""));
	panelCentro.setLayout(null);
	panelNorte.setLayout(null);
	panelNorte.setMinimumSize(new Dimension(725, 45));
	panelNorte.setPreferredSize(new Dimension(725, 45));
	panelCentro.setMinimumSize(new Dimension(730, 400));
	panelCentro.setSize(new Dimension(730, 450));
	panelCentro.setPreferredSize(new Dimension(730, 355));
	panelNorte.add(legajosSearchPanel, null);

	pPrincipalData.add(tfLegajo, null);
	pPrincipalData.add(tfApellido, null);
	pPrincipalData.add(tfApellidoCasada, null);

	pPrincipalData.add(tfNombre, null);
	pJobData.add(tfFechaAlta, null);
	pJobData.add(tfFechaBaja, null);
	pJobData.add(tfAntiguedad, null);

	pJobData.add(tfCuil, null);
	pAddressData.add(tfCalle,null);
	pAddressData.add(tfNumero,null);
	pAddressData.add(tfPiso,null);
	pAddressData.add(tfDepto,null);
	pAddressData.add(tfLocalidad,null);
	pAddressData.add(tfProvincia,null);
	pAddressData.add(tfCodPostal,null);

	pAddressData.add(tfTel,null);
	pAddressData.add(tfCalle,null);
	pAddressData.add(tfNumero,null);
	pAddressData.add(tfPiso,null);
	pAddressData.add(tfDepto,null);
	pAddressData.add(tfLocalidad,null);
	pAddressData.add(tfProvincia,null);
	pAddressData.add(tfCodPostal,null);
	pAddressData.add(tfTel,null);

	pOtherData.add(cbCivilState,null);
	pOtherData.add(cbBornCountry,null);
	pOtherData.add(tfFechaNac,null);
        pOtherData.add(tfTarea,null);
	pOtherData.add(tfObraSocial,null);
	pOtherData.add(tfPlanOS,null);

	pOtherData.add(tfSeguro,null);
        pOtherData.add(tfDocumento, null);
        pRetirementData.add(lblPhoto, null);
        pRetirementData.add(cbCodigos, null);
        pRetirementData.add(tfNumCuentaBancaria, null);

        pRetirementData.add(tfSueldo,null);
        pRetirementData.add(cbCategorias, null);
        btnNew.setToolTipText("Agregar Legajo");
        btnSave.setToolTipText("Grabar");
        btnSave.setEnabled(false);
        btnEdit.setToolTipText("Modificar Legajo");
	btnAddConcept.setToolTipText("Cargar Valores Conceptos");
	btnClose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					    btnClose_actionPerformed(e);
					}
				    }
	);
	addButton(btnClose);
	addButton(btnAddConcept);
	addButton(btnNew);
        addButton(btnEdit);
        addButton(btnSave);
	cargarFields();
	cargarCombos();
	setEditableFields(false);
	setEnabledCombos(false);
	setEnabledFields(false);
	tfLegajo.setEditable(true);
	tfLegajo.setEnabled(true);
	tfLegajo.getTextField().addKeyListener(new KeyListener(){
		public void keyTyped(KeyEvent e) {
		}

		public void keyPressed(KeyEvent e) {
			keyPress(e);
		}

		public void keyReleased(KeyEvent e) {
		}
	    });
	panelCentro.add(pRetirementData, null);
	panelCentro.add(pOtherData, null);
	panelCentro.add(pAddressData, null);
	panelCentro.add(pJobData, null);
	panelCentro.add(pPrincipalData, null);
	panelCentro.add(pJobData, null);
	this.setSize(new Dimension(735, 445));
	this.setPreferredSize(new Dimension(935, 545));
	this.add(panelNorte,BorderLayout.NORTH);
	this.add(panelCentro,BorderLayout.CENTER);
	btnNew.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
				   btnNew_actionPerformed(e);
			       }
			   }
	);
        btnEdit.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) {
                                   btnEdit_actionPerformed(e);
                               }
                           }
        );
        btnSave.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) {
                                   btnSave_actionPerformed(e);
                               }
                           }
        );
	btnAddConcept.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
				   btnAddConcept_actionPerformed(e);
			       }
			   }
	);
	cbBornCountry.autoSize();
	cbCivilState.autoSize();
        cbCodigos.autoSize();
        loadCombos();
	legajosSearchPanel.setLegajosPanel(this);
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().setIcon(true);
    }
    
    private void btnNew_actionPerformed(ActionEvent e) {       
	AltaLegajoPanel altaLegajo = new AltaLegajoPanel(legajosSearchPanel);
	ExtendedInternalFrame legajoContainer = new ExtendedInternalFrame("Crear Legajo");
	legajoContainer.setCentralPanel(altaLegajo);
	legajoContainer.show();
    }
    
    private void btnEdit_actionPerformed(ActionEvent e) {
        permitirEdicion(true);
    }
    
    private void permitirEdicion(boolean _allowed){
        cbCategorias.setEnabled(_allowed);
        cbCodigos.setEnabled(_allowed);
        tfNumCuentaBancaria.setEnabled(_allowed);
        tfNumCuentaBancaria.setEditable(_allowed);
        btnEdit.setEnabled(!_allowed);
        btnSave.setEnabled(_allowed);
    }
    
    private void btnSave_actionPerformed(ActionEvent e) {
        grabarLegajo();
        permitirEdicion(false);
    }
    
    private void grabarLegajo(){
        if (Advisor.question("Modificación", "¿Está seguro de grabar el legajo Nº " + legajo.getNumero()+"?.")) {
            legajo.setIdCategoria(Integer.parseInt(cbCategorias.getSelectedValue().toString()));
            legajo.setIdCodigoContrato(Integer.parseInt(cbCodigos.getSelectedValue().toString()));
            legajo.setNumeroCuentaBancaria(tfNumCuentaBancaria.getValue().toString());
            if (legajo.saveData() != -1){
                Advisor.messageBox("Se grabó correctamente el Legajo Nº " + legajo.getNumero() + ".", "Éxito");
            } else {
                Advisor.messageBox("Se produjo un error al grabar los datos.", "Error");
            }    
        }
    }
    
    private void btnAddConcept_actionPerformed(ActionEvent e) {       
	MultiManagement multi = new MultiManagement();
	ExtendedInternalFrame multiContainer = new ExtendedInternalFrame("Carga de valores por legajo");
	multiContainer.setCentralPanel(multi);
	multiContainer.show();
    }
    
    private void keyPress(KeyEvent e){
	if(!tfLegajo.getValue().toString().equals("")){
	    if(e.getKeyCode() == KeyEvent.VK_ENTER){ 
	        int numeroLegajo = -1;
	        numeroLegajo = legajosSearchPanel.setActualLegajo(Integer.parseInt(tfLegajo.getTextField().getText().toString().trim()));
	        if(numeroLegajo != tfLegajo.getInteger()){
	            tfLegajo.getTextField().setText(""+numeroLegajo);                
	        }
	    }   
	}else{
	    Advisor.messageBox("Debe ingresar un número de legajo.","Error");
	}
    }
    
    private void cargarFields(){
	fields.removeAllElements();
	fields.add(tfLegajo);
	fields.add(tfApellido);
	fields.add(tfNombre);
	fields.add(tfApellidoCasada);
	fields.add(tfFechaAlta);
	fields.add(tfFechaBaja);
	fields.add(tfAntiguedad);
	fields.add(tfCalle);
	fields.add(tfNumero);
	fields.add(tfPiso);
	fields.add(tfDepto);
	fields.add(tfLocalidad);
	fields.add(tfProvincia);
	fields.add(tfCodPostal);
	fields.add(tfTel);
	fields.add(tfTarea);
	fields.add(tfObraSocial);
	fields.add(tfPlanOS);
	fields.add(tfSeguro);
	fields.add(tfNumCuentaBancaria);
	fields.add(tfDocumento);
	fields.add(tfSueldo);
	fields.add(tfCuil);
	fields.add(tfFechaNac);
    }
    
    private void cargarCombos(){
	combos.removeAllElements();
	combos.add(cbBornCountry);
	combos.add(cbCivilState);
        combos.add(cbCodigos);
        combos.add(cbCategorias);
    }
    
    private void setEditableFields(boolean _editable){
	for(int i = 0; i < fields.size();i++){
	    ((TFInput)fields.elementAt(i)).setEditable(_editable);
	}
    }
    
    private void setEnabledFields(boolean _enabled){
	for(int i = 0; i < fields.size();i++){
	    ((TFInput)fields.elementAt(i)).setEnabled(_enabled);
	}
    }
    
    private void setEnabledCombos(boolean _enabled){
	for(int i = 0; i < combos.size();i++){
	    ((CBInput)combos.elementAt(i)).setEnabled(_enabled);
	}
    }

    public void setLegajo(Legajo legajo) {
	this.legajo = legajo;
	tfLegajo.setValue(legajo.getNumero());
	tfApellido.setValue(legajo.getApellidoPersona());
	tfNombre.setValue(legajo.getNombresPersona());
	tfFechaAlta.setValue(legajo.getFechaAlta());
	tfFechaBaja.setValue(legajo.getFechaBaja());
	tfSueldo.setValue(legajo.getSalario());
	cbCivilState.setSelectedID(legajo.getCivilState().getIdcivilstate());
	cbBornCountry.setSelectedID(legajo.getIdNacionalidad());
	tfFechaNac.setValue(legajo.getFechaNacimiento());
        cbCodigos.setSelectedID(legajo.getIdCodigoContrato());
        cbCategorias.setSelectedID(legajo.getIdCategoria());
        tfNumCuentaBancaria.setValue(legajo.getNumeroCuentaBancaria());
	if(legajo.getPerson().getPhotoImage() != null){
            LibIMG.escalaImg(legajo.getPerson().getPhotoImage(), lblPhoto);
	    lblPhoto.setImage(legajo.getPerson().getPhotoImage());   
	}else{
	    lblPhoto.setImage(null);
	    lblPhoto.setText("No posee foto.");
	}
    }

    public Legajo getLegajo() {
	return legajo;
    }
    
    private void loadCombos(){
        cbCodigos.loadJCombo("tabs.getAllCodigosContratos","");
        cbCategorias.loadJCombo("sueldos.getAllCategorias","");
    }
}
