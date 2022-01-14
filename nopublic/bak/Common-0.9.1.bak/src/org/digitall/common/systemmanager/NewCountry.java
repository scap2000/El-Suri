package org.digitall.common.systemmanager;

import java.awt.Dimension;
import java.awt.Rectangle;

import org.digitall.lib.components.basic.BasicDialog;

//

public class NewCountry extends BasicDialog {

    private PanelNewCountry panelNuevoPais;
    private String iddependiente = "";
    final String TEXTO = "New Country";

    /**
     * @deprecated
     * @param _iddependiente
     * @since 27-07-2007
     */
    public NewCountry(String _iddependiente) {
	try {
	    iddependiente = _iddependiente;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public NewCountry() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(293, 180));
	this.getContentPane().setLayout(null);
	panelNuevoPais = new PanelNewCountry(this);
	this.setTitle("New Country");
	panelNuevoPais.setBounds(new Rectangle(1, 35, 286, 118));
	this.getContentPane().add(panelNuevoPais, null);
	panelNuevoPais.setBounds(0, 35, 286, 118);
	org.digitall.lib.components.ComponentsManager.centerWindow(this);
    }

}
