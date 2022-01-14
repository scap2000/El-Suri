package org.digitall.apps.systemmanager;

import java.awt.Dimension;

import org.digitall.lib.components.basic.BasicDialog;

//

public class AddTablesToSystem extends BasicDialog {

    PanelAddTablesToSystem panelAddTablesToSystem;
    private String idsist = "";
    final String TEXTO = "Add Tables to Systems";

    public AddTablesToSystem(String _idsist) {
	try {
	    idsist = _idsist;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(554, 453));
	panelAddTablesToSystem = new PanelAddTablesToSystem(this, idsist);
	panelAddTablesToSystem.setBounds(0, 35, 552, 393);
	this.getContentPane().setLayout(null);
	this.getContentPane().add(panelAddTablesToSystem, null);
	this.setTitle("Add Tables to System");
	org.digitall.lib.components.ComponentsManager.centerWindow(this);
    }

}
