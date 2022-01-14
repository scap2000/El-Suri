package org.digitall.apps.sueldos.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.buttons.SaveDataButton;

public class RendicionCuentasMain extends BasicPrimitivePanel{
    
    private BasicPanel container = new BasicPanel();
    private CloseButton btnClose = new CloseButton();
    private PrintButton btnPrint = new PrintButton();
    private AddButton btnAdd = new AddButton();
    private SaveDataButton btnSave = new SaveDataButton();
    private CBInput cbInformes = new CBInput(0,"Informe");
    private CBInput cbAños = new CBInput(0,"Año");
    private CBInput cbMeses = new CBInput(0,"Mes");
    
    public RendicionCuentasMain() {
        super();
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        container.setLayout(null);
        container.add(cbInformes, null);
        container.add(cbAños, null);
        container.add(cbMeses, null);
        this.add(container, BorderLayout.CENTER);
        btnAdd.setToolTipText("Crear Informe Rendición de Cuentas");
        btnClose.setToolTipText("Cerrar Ventana");
        btnPrint.setToolTipText("Imprimir Informe Rendición de Cuentas");
        btnSave.setToolTipText("Generar Informe Rendición de Cuentas");
        cbInformes.setBounds(new Rectangle(10, 130, 300, 35));
        cbInformes.setPreferredSize(new Dimension(250, 50));
        cbInformes.setBounds(new Rectangle(10, 5, 320, 35));
        cbInformes.autoSize();
        cbAños.setBounds(new Rectangle(10, 130, 300, 35));
        cbAños.setPreferredSize(new Dimension(250, 50));
        cbAños.setBounds(new Rectangle(348, 5, 85, 35));
        cbAños.autoSize();
        cbMeses.setBounds(new Rectangle(10, 130, 300, 35));
        cbMeses.setPreferredSize(new Dimension(250, 50));
        cbMeses.setBounds(new Rectangle(450, 5, 140, 35));
        cbMeses.autoSize();
        addButton(btnClose);
        addButton(btnPrint);
        addButton(btnAdd);
        addButton(btnSave);
        btnClose.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    btnClose_actionPerformed(e);
                                }

                            }   
        );
        loadComboInformes();
        this.setSize(new Dimension(601, 89));
    }
    
    private void loadComboInformes(){
        //cbInformes.loadJCombo("sueldos.getAllInformesRendiciones","");
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {      
        getParentInternalFrame().close();
    }
}
