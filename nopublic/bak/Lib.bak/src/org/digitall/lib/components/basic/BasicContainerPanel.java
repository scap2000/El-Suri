package org.digitall.lib.components.basic;

public class BasicContainerPanel extends BasicPanel {

    public BasicContainerPanel() {
	super();
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setBackground(BasicConfig.PANELCONTAINER_BACKGROUND_COLOR);
    }

    public boolean saveData() {
	return true;
    }

}
