package org.digitall.common.systemmanager;

import java.awt.Dimension;

import org.digitall.common.systemmanager.PanelNewStreet;
import org.digitall.lib.components.basic.BasicDialog;

//

public class NewStreet extends BasicDialog {

    private PanelNewStreet panelNuevaCalle;
    private String idlocalidad = "";
    final String TEXTO = "New Street";

    public NewStreet(String _idlocalidad) {
	try {
	    idlocalidad = _idlocalidad;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(292, 220));
	this.getContentPane().setLayout(null);
	panelNuevaCalle = new PanelNewStreet(this, idlocalidad);
	this.setTitle("New Street");
	this.getContentPane().add(panelNuevaCalle, null);
	panelNuevaCalle.setBounds(0, 35, 286, 159);
	org.digitall.lib.components.ComponentsManager.centerWindow(this);
    }

}
