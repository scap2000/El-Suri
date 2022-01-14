package org.digitall.common.systemmanager;

import java.awt.Dimension;

import org.digitall.common.systemmanager.PanelNewProvince;
import org.digitall.lib.components.basic.BasicDialog;

//

public class NewProvince extends BasicDialog {

    private PanelNewProvince panelNuevoProvincia;
    private String idcountry = "";
    final String TEXTO = "New Province/State";

    public NewProvince(String _idcountry) {
	try {
	    idcountry = _idcountry;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(292, 223));
	this.getContentPane().setLayout(null);
	panelNuevoProvincia = new PanelNewProvince(this, idcountry);
	this.setTitle("New Province");
	this.getContentPane().add(panelNuevoProvincia, null);
	panelNuevoProvincia.setBounds(0, 35, 286, 163);
	org.digitall.lib.components.ComponentsManager.centerWindow(this);
    }

}
