package org.digitall.apps.systemmanager;

import java.awt.Dimension;

import org.digitall.lib.components.basic.BasicDialog;

//

public class PersonalData extends BasicDialog {

    private PanelPersonalData datosPersonales;
    private String idpersona = "";
    final String TEXTO = "Personal Data";

    public PersonalData(String _idpersona) {
	try {
	    idpersona = _idpersona;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(545, 445));
	datosPersonales = new PanelPersonalData(this, idpersona);
	this.getContentPane().setLayout(null);
	this.setTitle("Personal Data");
	this.getContentPane().add(datosPersonales, null);
	org.digitall.lib.components.ComponentsManager.centerWindow(this);
    }

}
