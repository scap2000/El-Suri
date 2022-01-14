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
 * FormCarteles.java
 *
 * */
package org.digitall.apps.gaia.relevamientos.comercial_2009.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import org.digitall.apps.gaia.relevamientos.comercial_2009.clases.ClassDetalleRelevamientoPublicidad;
import org.digitall.apps.gaia.relevamientos.comercial_2009.clases.ClassRelevamientoPublicidad2009Desglosada;
import org.digitall.apps.gaia.relevamientos.comercial_2009.clases.errclasses.ErrDesgloseCarteles;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.resourcescontrol.interfaces.providers.ProvidersMain;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddComboButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class FormCarteles extends BasicPrimitivePanel{

    private BasicPanel contentPanel = new BasicPanel("");
    private CBInput cbTextoCartel = new CBInput(0, "Texto del anuncio", false);
    private TFInput tfAncho = new TFInput(DataTypes.DOUBLE, "Ancho", false);
    private TFInput tfAlto = new TFInput(DataTypes.DOUBLE, "Alto", false);
    private TFInput tfSuperficie = new TFInput(DataTypes.DOUBLE, "Superficie", false);
    private CBInput cbEmpresas = new CBInput(0, "Empresa", false);
    private CBInput cbFormas = new CBInput(0, "Forma", false);
    private CBInput cbIluminacion = new CBInput(0, "Iluminación", false);
    private CBInput cbFaz = new CBInput(0, "Faz", false);
    private AddComboButton btnAddEmpresa = new AddComboButton();
    private SaveDataButton btnGuardarDesglose = new SaveDataButton();
    private CloseButton btnClose = new CloseButton();
    private AddComboButton btnAddTexto = new AddComboButton();
    
    private ClassDetalleRelevamientoPublicidad detalleRelevamientoPublicidad;
    private ClassRelevamientoPublicidad2009Desglosada relevamientoDesglosado = new ClassRelevamientoPublicidad2009Desglosada();
    private ErrDesgloseCarteles errorForm;

    public FormCarteles() {

	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public FormCarteles(ClassDetalleRelevamientoPublicidad _idDetalleRelevamiento) {
        detalleRelevamientoPublicidad = _idDetalleRelevamiento;
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
	contentPanel.setLayout(null);
	this.setSize(new Dimension(339, 226));
	cbTextoCartel.setBounds(new Rectangle(20, 15, 275, 35));
        cbTextoCartel.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    cbTextoCartel_itemStateChanged(e);
                }
            });
        tfAncho.setBounds(new Rectangle(20, 60, 65, 35));
	tfAlto.setBounds(new Rectangle(95, 60, 65, 35));
	tfSuperficie.setBounds(new Rectangle(10, 135, 115, 35));
	tfSuperficie.setBounds(new Rectangle(170, 60, 65, 35));
        tfSuperficie.setEditable(false);
	cbEmpresas.setBounds(new Rectangle(20, 145, 275, 35));
	cbFormas.setBounds(new Rectangle(20, 105, 135, 35));
	cbIluminacion.setBounds(new Rectangle(160, 105, 160, 35));
	btnAddEmpresa.setBounds(new Rectangle(295, 160, 25, 20));
        btnAddEmpresa.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnAddEmpresa_actionPerformed(e);
                }
            });
        btnGuardarDesglose.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnGuardarDesglose_actionPerformed(e);
                }
            }); 
        btnGuardarDesglose.setToolTipText("Grabar");
        btnClose.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnClose_actionPerformed(e);
                }
            });
        btnAddTexto.setBounds(new Rectangle(295, 30, 25, 20));
        btnAddTexto.setToolTipText("Agregar texto");
        btnAddTexto.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnAddTexto_actionPerformed(e);
                }
            });
        cbFaz.setBounds(new Rectangle(255, 60, 65, 35));
        tfAncho.getTextField().addKeyListener(new KeyAdapter() {
		
		public void keyTyped(KeyEvent e) {
                        calcularSuperficie();
		}

	    }
	);        
        tfAlto.getTextField().addKeyListener(new KeyAdapter() {
		
		public void keyTyped(KeyEvent e) {
                        calcularSuperficie();
		}
	    }
	);


        tfAncho.getTextField().addFocusListener(new FocusAdapter() {
                
                public void focusLost(FocusEvent e) {
                        calcularSuperficie();
                }

            }
        );        

        tfAlto.getTextField().addFocusListener(new FocusAdapter() {
                
                public void focusLost(FocusEvent e) {
                        calcularSuperficie();
                }

            }
        );        

        contentPanel.add(cbTextoCartel,null);
        contentPanel.add(btnAddTexto, null);
        contentPanel.add(tfAncho,null);
        contentPanel.add(tfAlto,null);
        contentPanel.add(tfSuperficie,null);
        contentPanel.add(cbFaz, null);
        contentPanel.add(cbFormas,null);
        contentPanel.add(cbIluminacion,null);
        contentPanel.add(cbEmpresas,null);
        contentPanel.add(btnAddEmpresa,null);
        addButton(btnClose);
        addButton(btnGuardarDesglose);
	cbEmpresas.autoSize();
	cbFormas.autoSize();
	cbIluminacion.autoSize();
	this.add(contentPanel, BorderLayout.CENTER);
        loadCombos();
        btnAddEmpresa.setToolTipText("Agregar Empresa");
        seleccionarEmpresa();

    }

    private void btnClose_actionPerformed(ActionEvent e) {
        if (Advisor.question("Aviso", "¿Desea cancelar el registro?")) {
            this.getParentInternalFrame().close();    
        }
    }

    private void btnGuardarDesglose_actionPerformed(ActionEvent e) {
        saveData();
    }
    
    public boolean saveData() {
        boolean returns = false;
        calcularSuperficie();
        if(control()){
            setData();
            if(relevamientoDesglosado.saveData() > -1 ){
                //Advisor.messageBox("Los datos se guardaron exitosamente","Mensaje");
                returns = true;
                this.getParentInternalFrame().close();
            } else {
                Advisor.messageBox("Ocurrió un error al grabar los datos","Error");
            }
        } else {
            errorForm.showMessage();
        }
        return returns;
    }
    
    private boolean control(){
        boolean valor = true;
        errorForm = new ErrDesgloseCarteles();
        if (cbTextoCartel.getSelectedItem() != null) {
            if (cbTextoCartel.getSelectedItem().equals("")) {
                valor = false;
                errorForm.setError(errorForm.textoCartel);
            } else {
                if (tfSuperficie.getDouble() == 0) {
                    valor = false;
                    errorForm.setError(errorForm.superficie);
                }   
            }
        } else {
            valor = false;
            errorForm.setError(errorForm.textoCartel);
        }
        return valor;
    }
    
    private void setData(){
        relevamientoDesglosado.setIddetallerelevamiento(detalleRelevamientoPublicidad.getIddetallerelevamiento());
        relevamientoDesglosado.setTexto(cbTextoCartel.getSelectedItem().toString());
        relevamientoDesglosado.setAncho(tfAncho.getDouble());
        relevamientoDesglosado.setAlto(tfAlto.getDouble());
        relevamientoDesglosado.setForma(cbFormas.getSelectedItem().toString());
        relevamientoDesglosado.setSuperficie(tfSuperficie.getDouble());
        relevamientoDesglosado.setIluminacion(cbIluminacion.getSelectedItem().toString());
        relevamientoDesglosado.setIdempresa(Integer.parseInt(cbEmpresas.getSelectedValue().toString()));
        relevamientoDesglosado.setX(detalleRelevamientoPublicidad.getX());
        relevamientoDesglosado.setY(detalleRelevamientoPublicidad.getY());
    }
    
    private void loadComboForma() {
        cbFormas.loadJCombo(LibSQL.exFunction("gea_salta.getallformasbyfilter", ""));
        cbFormas.getCombo().updateUI();
    }
    
    private void loadComboIluminacion() {
        cbIluminacion.loadJCombo(LibSQL.exFunction("gea_salta.getallcaracteristicasanunciobyfilter", ""));
        cbIluminacion.getCombo().updateUI();
    }
    
    private void loadComboFaz() {
        cbFaz.loadJCombo(LibSQL.exFunction("gea_salta.getallfazbyfilter", ""));
        cbFaz.getCombo().updateUI();
    }
    
    private void loadComboEmpresas() {
        cbEmpresas.loadJCombo(" SELECT companies.idcompany, companies.name, 0 FROM org.companies" +
                              " WHERE companies.estado<>'*' " 
                             +" ORDER BY companies.name");
        cbEmpresas.getCombo().updateUI();
    }

    private void loadCombos() {
        loadComboForma();
        loadComboIluminacion();
        loadComboFaz();
        loadComboEmpresas();
        loadComboTextoCarteles();
    }
    
    private void loadComboTextoCarteles() {
        cbTextoCartel.loadJCombo(LibSQL.exFunction("gea_salta.getalltextoanunciobyfilter", ""));
        cbTextoCartel.getCombo().updateUI();
    }

    private void btnAddEmpresa_actionPerformed(ActionEvent e) {
        ProvidersMain formEmpresas = new ProvidersMain();
        ExtendedInternalFrame empresaContainer = new ExtendedInternalFrame("Empresas");
        empresaContainer.setCentralPanel(formEmpresas);
        empresaContainer.show();
    }

    private void btnAddTexto_actionPerformed(ActionEvent e) {
        String _title = JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Ingrese texto del cartel ", "Nuevo texto del cartel", JOptionPane.QUESTION_MESSAGE);
        if (_title != null) {
            _title = _title.toUpperCase();
            cbTextoCartel.addItem(_title);
            cbTextoCartel.setSelectedValue(_title);
        }
    }
    
    private void calcularSuperficie(){
        tfSuperficie.setValue((tfAncho.getAmount() * tfAlto.getAmount()));
    }

    private void cbTextoCartel_itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED){
            seleccionarEmpresa();
        }
    }
    
    private void seleccionarEmpresa(){
        if (cbTextoCartel.getCombo().getItemCount() > 0) {
            if (cbTextoCartel.getCombo().getValuesVector().size() > 0) {
                if (cbTextoCartel.getSelectedValueRef() != "" && cbTextoCartel.getSelectedValueRef() != "0") {
                    cbEmpresas.setSelectedID(cbTextoCartel.getSelectedValueRef());
                }
            }
        }
        cbEmpresas.getCombo().updateUI();
    }
}

