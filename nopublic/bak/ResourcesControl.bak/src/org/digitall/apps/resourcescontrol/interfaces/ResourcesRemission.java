package org.digitall.apps.resourcescontrol.interfaces;

import org.digitall.lib.components.basic.BasicContainerPanel;

public class ResourcesRemission extends BasicContainerPanel {

    public ResourcesRemission() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout( null );
    }

}
