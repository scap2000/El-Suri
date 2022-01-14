package org.digitall.apps.sueldos.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;

public class ConfiguracionBaseMain extends BasicPrimitivePanel{
    
    private ConfiguracionBase configuracionBase = new ConfiguracionBase();
    private SaveDataButton btnSave = new SaveDataButton();
    private CloseButton btnClose = new CloseButton();
    private BasicPanel pContainer = new BasicPanel();

    public ConfiguracionBaseMain() {

	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(692, 457));
        pContainer.setPreferredSize(new Dimension(692, 457));
        pContainer.add(configuracionBase, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(692, 447));
        configuracionBase.setBounds(new Rectangle(0, 0, 690, 425));
        this.add(pContainer, BorderLayout.CENTER);
	addButton(btnClose);
	addButton(btnSave);
	btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    btnSave_actionPerformed(e);
				}
			    }
	);
	btnClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}
			    }
	);
	
	btnClose.setEnabled(true);
        pContainer.setLayout(null);
        btnSave.setEnabled(true);
	/*configuracionBase.getPanelEmpleados().refresh();
	configuracionBase.getPanelSindicatos().refresh();
	configuracionBase.getPanelCategorias().refresh();
	configuracionBase.getPanelArticulos().refresh();
	configuracionBase.getPanelNovedades().refresh();
	configuracionBase.getPanelObrasSociales().refresh();
	configuracionBase.getPanelFamiliares().refresh();
	configuracionBase.getPanelAportes().refresh();
	configuracionBase.getPanelSociales().refresh();*/
	//configuracionBase.getPanelEmpleados().started();
    }
    
    /*private void btnAccept_actionPerformed(ActionEvent e) {
	configuracionBase.getPanelEmpleados().started();
	configuracionBase.getPanelSindicatos().started();
	configuracionBase.getPanelCategorias().started();
	configuracionBase.getPanelArticulos().started();
	configuracionBase.getPanelNovedades().started();
	configuracionBase.getPanelObrasSociales().started();
	configuracionBase.getPanelFamiliares().started();
	configuracionBase.getPanelAportes().started();
	configuracionBase.getPanelSociales().started();
	btnClose.setEnabled(true);
	btnSave.setEnabled(true);
	btnAccept.setEnabled(false);
    }*/
    
    private void btnSave_actionPerformed(ActionEvent e) {
	saveAll(true);
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	saveAll(true);
	this.getParentInternalFrame().setIcon(true);
    }
    
    private void saveAll(boolean _exit){
	configuracionBase.getPanelEmpleados().saveEmpleados(_exit);
	configuracionBase.getPanelSindicatos().saveSindicatos(_exit);
	configuracionBase.getPanelCategorias().saveCategorias(_exit);
	configuracionBase.getPanelArticulos().saveArticulos(_exit);
	configuracionBase.getPanelNovedades().saveNovedades(_exit);
	configuracionBase.getPanelObrasSociales().saveObrasSociales(_exit);
	configuracionBase.getPanelFamiliares().saveFamiliar(_exit);
	configuracionBase.getPanelAportes().saveAportes(_exit);
	configuracionBase.getPanelSociales().saveSociales(_exit);
    }
}
