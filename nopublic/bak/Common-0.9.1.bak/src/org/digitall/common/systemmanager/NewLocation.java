package org.digitall.common.systemmanager;

import java.awt.Dimension;
import java.awt.Rectangle;

import org.digitall.common.systemmanager.PanelNewLocation;
import org.digitall.lib.components.basic.BasicDialog;

//

public class NewLocation extends BasicDialog {

    private PanelNewLocation panelNuevaLocalidad;
    private String idprovince = "";
    final String TEXTO = "New Location";

    public NewLocation(String _idprovince) {
	try {
	    idprovince = _idprovince;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(291, 253));
	this.getContentPane().setLayout(null);
	panelNuevaLocalidad = new PanelNewLocation(this, idprovince);
	this.setTitle("New  Location");
	panelNuevaLocalidad.setBounds(new Rectangle(0, 35, 286, 192));
	this.getContentPane().add(panelNuevaLocalidad, null);
	panelNuevaLocalidad.setBounds(0, 35, 286, 192);
	org.digitall.lib.components.ComponentsManager.centerWindow(this);
    }

}
