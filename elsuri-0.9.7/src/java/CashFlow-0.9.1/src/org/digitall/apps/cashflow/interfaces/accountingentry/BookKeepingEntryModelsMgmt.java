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
 * BookKeepingEntryModelsMgmt.java
 *
 * */
package org.digitall.apps.cashflow.interfaces.accountingentry;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import org.digitall.apps.cashflow.classes.BookKeppingEntriesModels;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;

public class BookKeepingEntryModelsMgmt extends BasicPrimitivePanel{

    private BasicPanel modelPanel = new BasicPanel();
    private SaveDataButton btnSave = new SaveDataButton();
    private CloseButton btnCancel = new CloseButton();
    private TFInput tfModel = new TFInput(0,"ModelName",false);
    private BookKeppingEntriesModels model;
    private BookKeepingEntryModelsList parentMain;
    private CBInput cbCashMovementType = new CBInput(0,"MovementType",false);

    public BookKeepingEntryModelsMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(376, 148));
	modelPanel.setLayout(null);
	modelPanel.setPreferredSize(new Dimension(1, 115));
	modelPanel.setSize(new Dimension(739, 70));
	btnSave.addActionListener(new ActionListener() {

					 public void actionPerformed(ActionEvent e) {
					     btnSave_actionPerformed(e);
					 }

				     }
	);
        btnCancel.addActionListener(new ActionListener() {

                                         public void actionPerformed(ActionEvent e) {
                                             btnCancel_actionPerformed(e);
                                         }

                                     }
        );
        
        tfModel.setBounds(new Rectangle(40, 60, 285, 35));
        cbCashMovementType.setBounds(new Rectangle(40, 25, 285, 35));
        addButton(btnCancel);
        addButton(btnSave);
        modelPanel.add(cbCashMovementType, null);
        modelPanel.add(tfModel, null);
        this.add(modelPanel, BorderLayout.CENTER);
        btnSave.setToolTipText("Agregar/modificar un Modelo");
        cbCashMovementType.setBounds(new Rectangle(40, 20, 285, 35));
        modelPanel.add(cbCashMovementType, null);
        cbCashMovementType.addItemListener(new ItemListener() {

                                public void itemStateChanged(ItemEvent evt) {
                                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                                        refresh();
                                    }
                                }

                            }
        );
        cbCashMovementType.autoSize();
        loadComboCashMovementTypes();
        modelPanel.setBorder(BorderPanel.getBorderPanel("Agregar/Modificar un Modelo"));
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Puede agregar/modificar un Modelo de Asiento Contable");
    }
    
    private void btnSave_actionPerformed(ActionEvent e) {
	if (!tfModel.getString().equals(""))  {
            model.setName(tfModel.getString());
            model.setIdCashMovementType(Integer.parseInt(cbCashMovementType.getSelectedValue().toString()));
	    if (model.saveData() > 0) { 
                parentMain.loadComboModels();
                parentMain.refresh();
                getParentInternalFrame().close();
	    } else {
                Advisor.messageBox("Ocurrió un error al grabar los datos","Error");
            }
	} else  {
	    Advisor.messageBox("Debe ingresar el nombre del Modelo","Aviso");
	}
    }

    public void setModel(BookKeppingEntriesModels _model){
        model = _model;
        if (model.getIdmodel() != -1) {
            tfModel.setValue(model.getName());
            cbCashMovementType.setSelectedID(model.getIdCashMovementType());
        }
    }
    
    private void btnCancel_actionPerformed(ActionEvent e) {
        getParentInternalFrame().close();
    }
    
    public void setParentMain(BookKeepingEntryModelsList _parentMain){
        parentMain = _parentMain;
    }

    private void loadComboCashMovementTypes() {
        cbCashMovementType.getCombo().addItem("Ingreso Directo",1);
        cbCashMovementType.getCombo().addItem("Egreso Directo",2);
    }
}
