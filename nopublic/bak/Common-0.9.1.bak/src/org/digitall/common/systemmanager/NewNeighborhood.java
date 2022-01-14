package org.digitall.common.systemmanager;

import java.awt.Dimension;

import org.digitall.common.systemmanager.PanelNewNeighborhood;
import org.digitall.lib.components.basic.BasicDialog;

//

public class NewNeighborhood extends BasicDialog {

    private PanelNewNeighborhood panelNuevoBarrio;
    private String idlocation = "";
    final String TEXTO = "New Neighborhood";

    public NewNeighborhood(String _idlocation) {
	try {
	    idlocation = _idlocation;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(292, 185));
	this.getContentPane().setLayout(null);
	panelNuevoBarrio = new PanelNewNeighborhood(this, idlocation);
	this.setTitle("New Neighborhood");
	this.getContentPane().add(panelNuevoBarrio, null);
	panelNuevoBarrio.setBounds(0, 35, 286, 124);
	org.digitall.lib.components.ComponentsManager.centerWindow(this);
    }

}
