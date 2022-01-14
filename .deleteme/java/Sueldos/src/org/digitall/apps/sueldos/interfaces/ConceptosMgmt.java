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
 * ConceptosMgmt.java
 *
 * */
package org.digitall.apps.sueldos.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import java.awt.event.ItemListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;

import org.digitall.apps.sueldos.classes.Conceptos;
import org.digitall.apps.sueldos.classes.errorclasses.ErrorFormConceptosMgmt;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.sql.LibSQL;

public class ConceptosMgmt extends BasicPrimitivePanel {
    
    private BasicPanel content = new BasicPanel();
    private SaveDataButton btnSave = new SaveDataButton();
    private CloseButton btnClose = new CloseButton();
    private TFInput tfNombre = new TFInput(DataTypes.STRING, "Nombre de Concepto",true);
    private TFInput tfValorDefecto = new TFInput(DataTypes.MONEY, "Monto por defecto",false);
    private TFInput tfPorcentajeDefecto = new TFInput(DataTypes.PERCENT, "Porcentaje por defecto",false);
    private BasicRadioButton rbtnHaber = new BasicRadioButton("Haber");
    private BasicRadioButton rbtnDescuento = new BasicRadioButton("Descuento");
    private ButtonGroup grupo = new ButtonGroup();
    private CBInput cbConceptos = new CBInput(0,"Después de");
    private CBInput cbCuentas = new CBInput(0,"Cuenta");
    private CBInput cbTipos = new CBInput(0,"Tipo");
    private BasicPanel pTipo = new BasicPanel();
    private TFInput tfFormula = new TFInput(DataTypes.STRING, "Fórmula",false);
    private TFInput tfBuscarCuenta = new TFInput(DataTypes.STRING, "Buscar",false);
    private BasicCheckBox chGenerico = new BasicCheckBox("Carga genérica");
    private BasicCheckBox chPorlegajo = new BasicCheckBox("Carga por legajo");
    private BasicCheckBox chNinguno = new BasicCheckBox("Ninguno");
    private Conceptos concepto = new Conceptos();
    private ErrorFormConceptosMgmt errorFormConceptosMgmt;
    private BasicPrimitivePanel parentList;

    
    public ConceptosMgmt(Conceptos _concepto) {
        super();
        concepto = _concepto;
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        content.setLayout(null);
        tfNombre.setBounds(new Rectangle(10, 10, 305, 35));
        tfValorDefecto.setBounds(new Rectangle(10, 290, 115, 35));
        tfPorcentajeDefecto.setBounds(new Rectangle(175, 290, 140, 35));
        rbtnHaber.setBounds(new Rectangle(15, 50, 115, 35));
        rbtnDescuento.setBounds(new Rectangle(195, 50, 115, 35));
        grupo.add(rbtnHaber);
        grupo.add(rbtnDescuento);
        cbConceptos.setBounds(new Rectangle(10, 130, 300, 35));
        cbConceptos.setPreferredSize(new Dimension(250, 50));
        cbCuentas.setBounds(new Rectangle(90, 335, 225, 35));
        cbCuentas.setPreferredSize(new Dimension(250, 50));
        cbTipos.setBounds(new Rectangle(10, 85, 300, 35));
        cbTipos.setPreferredSize(new Dimension(250, 50));
        pTipo.setBounds(new Rectangle(10, 185, 305, 90));
        pTipo.setBorder(BorderPanel.getBorderPanel(""));
        tfFormula.setBounds(new Rectangle(15, 335, 300, 70));
        tfFormula.setEditable(false);
        tfBuscarCuenta.setBounds(new Rectangle(5, 415, 75, 35));
        chGenerico.setBounds(new Rectangle(70, 5, 185, 25));
        chPorlegajo.setBounds(new Rectangle(70, 30, 185, 25));
        chNinguno.setBounds(new Rectangle(70, 55, 185, 25));
        cbConceptos.autoSize();
        cbCuentas.autoSize();    
        cbTipos.autoSize();
        cbTipos.getCombo().addItemListener(new ItemListener() {
            public void itemStateChanged (ItemEvent evt) {
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                    int debeHaber = 0;
                    if (rbtnDescuento.isSelected()) {
                        debeHaber = -1;
                    } else {
                        debeHaber = 1;
                    }
                    loadComboConceptos(debeHaber);
                }
            }
        });
        rbtnHaber.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    rbtnHaber_actionPerformed(e);
                }
            }
        );
        
        rbtnDescuento.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    rbtnDescuento_actionPerformed(e);
                }
            }
        );
        chNinguno.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    chNinguno_actionPerformed(e);
                }
            }
        );
        chGenerico.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    chGenerico_actionPerformed(e);
                }
            }
        );
        chPorlegajo.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    chPorLegajo_actionPerformed(e);
                }
            }
        );
        btnClose.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    btnClose_actionPerformed(e);
                                }

                            }   
        );
        btnSave.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    btnSave_actionPerformed(e);
                                }

                            }   
        );
        tfBuscarCuenta.getTextField().addKeyListener(new KeyAdapter() {
                    public void keyReleased(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER)
                            buscar();
                    }
                });
        pTipo.setLayout(null);
	tfFormula.setBounds(new Rectangle(10, 380, 305, 70));
	tfBuscarCuenta.setBounds(new Rectangle(10, 335, 75, 35));
	pTipo.add(chGenerico, null);
        pTipo.add(chPorlegajo, null);
        pTipo.add(chNinguno, null);
        content.add(tfNombre,null);
	content.add(tfValorDefecto,null);
	content.add(tfPorcentajeDefecto,null);
        content.add(rbtnHaber, null);
        content.add(rbtnDescuento, null);
        content.add(cbConceptos, null);
	content.add(cbCuentas, null);
	content.add(cbTipos, null);
        content.add(pTipo, null);
	//content.add(tfFormula, null);
	content.add(tfBuscarCuenta, null);
	this.setSize(new Dimension(328, 495));
        this.add(content, BorderLayout.CENTER);
        addButton(btnClose);
        addButton(btnSave);
        load();
    }
    
    public void load(){
        if (concepto.getIdconcepto() == -1) {
            //Cargar la ventana con los datos por defecto
            rbtnHaber.setSelected(true);
            loadComboTipos();
            loadComboConceptos(1);
            chNinguno.setSelected(true);
            loadCuentas(-1,"");
        } else {
            //Cargar la ventana con los datos del concepto
            rbtnHaber.setSelected(concepto.getDebehaber() == 1);
            rbtnDescuento.setSelected(concepto.getDebehaber() == -1);
            if (rbtnHaber.isSelected()) {
                loadComboConceptos(1);
            } else {
                loadComboConceptos(-1);
            }
            loadComboTipos();
            cbTipos.setSelectedID(concepto.getIdtipoconcepto());
            cbConceptos.setSelectedID(LibSQL.getInt("sueldos.getIdConceptoAnterior", "" + concepto.getIdconcepto()));
            chGenerico.setSelected(concepto.getTipoConcepto().isSetgeneral());
            chPorlegajo.setSelected(concepto.getTipoConcepto().isIssetforlegajo());
            tfPorcentajeDefecto.setValue(concepto.getTipoConcepto().getPercentage());
            tfValorDefecto.setValue(concepto.getTipoConcepto().getValue());
            tfNombre.setValue(concepto.getTipoConcepto().getName());
            chNinguno.setSelected(!concepto.getTipoConcepto().isSetgeneral() && !concepto.getTipoConcepto().isIssetforlegajo());
            loadCuentas(-1,"");
            cbCuentas.setSelectedID(concepto.getIdaccount());
        }
    }
    
    private void loadCuentas(int _idTipo, String _filtro){
        String params = "" + _idTipo + ",'" +_filtro  + "'";
        cbCuentas.loadJCombo(" accounting.getallaccountsbyfilter2",params);
    }
    
    private void rbtnDescuento_actionPerformed(ActionEvent e) {
        loadComboConceptos(-1);
    }
    
    private void rbtnHaber_actionPerformed(ActionEvent e) {
        loadComboConceptos(1);
    }
    
    private void loadComboConceptos(int _debeHaber){
        int idTipo = Integer.parseInt(cbTipos.getSelectedValue().toString());
        cbConceptos.loadJCombo("sueldos.getAllConceptosByTipo","" + _debeHaber + "," + idTipo);
    }
    
    private void loadComboTipos(){
        cbTipos.loadJCombo("tabs.getAllTiposConceptos","" );
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {      
        getParentInternalFrame().close();
    }
    
    private void btnSave_actionPerformed(ActionEvent e) {      
        grabarConcepto();
    }
    
    private void grabarConcepto() {
        String params = "";
        int debeHaber = 0;
        String accion = "";
        if (concepto.getIdconcepto() == -1) {
            accion = "agregar";
        } else {
            accion = "modificar";
        }
        
        if (control()) {
            if (Advisor.question("Pregunta", "¿Está seguro de " + accion + " el concepto " + tfNombre.getValue() + "?")) {

                if (rbtnDescuento.isSelected()) {
                    debeHaber = -1;
                } else {
                    debeHaber = 1;
                }

                if (concepto.getIdconcepto() == -1) {
                    params =
                            "'" + tfNombre.getValue() + "', " + debeHaber + "," +
                            Integer.parseInt(cbConceptos.getSelectedValueRef().toString()) +
                            "," + chPorlegajo.isSelected() + "," +
                            chGenerico.isSelected() + "," +
                            tfValorDefecto.getValue() + "," +
                            tfPorcentajeDefecto.getValue() + "," +
                            Integer.parseInt(cbTipos.getSelectedValue().toString()) +
                            "," + cbCuentas.getSelectedValue() + ",'" +
                            tfFormula.getValue() + "'";
                    if (LibSQL.getInt("sueldos.addconcepto", params) != -1) {
                        Advisor.messageBox("Se grabó correctamente el concepto.", "Aviso");
                        parentList.refresh();
                        getParentInternalFrame().close();
                    } else {
                        Advisor.messageBox("Ocurrió un problema al grabar los datos.", "Aviso");
                    }
                } else {
                    //Modificar concepto
                    System.out.println("!!Modificar concepto");
                    params = "" + concepto.getIdconcepto() + ","   
                                + Integer.parseInt(cbTipos.getSelectedValue().toString()) + ",'"  
                                + tfNombre.getValue() + "', " 
                                + debeHaber + "," 
                                + Integer.parseInt(cbConceptos.getSelectedValueRef().toString()) + ","
                                + chPorlegajo.isSelected() + "," 
                                + chGenerico.isSelected() + "," 
                                + tfValorDefecto.getValue() + "," 
                                + tfPorcentajeDefecto.getValue() + ","
                                + Integer.parseInt(cbTipos.getSelectedValue().toString()) + ","
                                + cbCuentas.getSelectedValue() + ",'"
                                + tfFormula.getValue() + "'";
                        
                    if (LibSQL.getInt("sueldos.setConcepto", params) != -1) {
                        Advisor.messageBox("Se grabó correctamente el concepto.", "Aviso");
                        parentList.refresh();
                        getParentInternalFrame().close();
                    } else {
                        Advisor.messageBox("Ocurrió un problema al grabar los datos.", "Aviso");
                    }
                }
            }
        } else {
            errorFormConceptosMgmt.showMessage();
        }
    }
    
    private boolean control(){
        boolean valor = true;
        errorFormConceptosMgmt = new ErrorFormConceptosMgmt();
        if (tfNombre.getValue().equals("")) {
            errorFormConceptosMgmt.setError(errorFormConceptosMgmt.FNOMBRE);
            valor = false;
        } else {
            valor = true;
        }
        return valor;
    }
    
    private void chNinguno_actionPerformed(ActionEvent e) {
        if (chNinguno.isSelected()) {
            chGenerico.setSelected(false);
            chPorlegajo.setSelected(false);
        } 
    }
    
    private void chGenerico_actionPerformed(ActionEvent e) {
        if (chGenerico.isSelected()) {
            chNinguno.setSelected(false);
        } else {
            if (!chPorlegajo.isSelected()) {
                chNinguno.setSelected(true);
            }
        }
    }
    
    private void chPorLegajo_actionPerformed(ActionEvent e) {
        if (chPorlegajo.isSelected()) {
            chNinguno.setSelected(false);
        } else {
            if (!chGenerico.isSelected()) {
                chNinguno.setSelected(true);
            }
        }
    }
    
    private void buscar(){
        loadCuentas(-1,tfBuscarCuenta.getString());
    }
    
    private void clearData(){
        tfNombre.setValue("");
        tfValorDefecto.setValue(0.00);
        tfPorcentajeDefecto.setValue(0.00);
    }
    
    public void setParentList(BasicPrimitivePanel _parentList) {
        parentList = _parentList;
    }
    
}
