package org.digitall.apps.dictionary;

import org.digitall.lib.components.basic.BasicContainerPanel;

public class FileSelectorPanel extends BasicContainerPanel {

    public FileSelectorPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
    }

}
