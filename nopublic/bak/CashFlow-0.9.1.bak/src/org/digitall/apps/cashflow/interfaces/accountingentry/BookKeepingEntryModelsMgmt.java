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
