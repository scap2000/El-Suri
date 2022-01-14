package org.digitall.apps.logistics.interfaces;

import java.awt.Dimension;

import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicPanel;

public class LFrameContainer extends BasicInternalFrame {
    private BasicPanel panel;
    private String title;
    
    public LFrameContainer(String _Title) {
	try {
	    title = _Title;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    private void jbInit() throws Exception {
	this.getContentPane().setLayout(null);
	this.setSize(new Dimension(763, 615));
	this.setClosable(true);
	this.setIconifiable(true);
	this.setTitle(title);   
    }

    public BasicPanel getPanel() {
	return panel;
    }

    public void setPanel(BasicPanel panel) {
	this.panel = panel;
	this.setSize(panel.getWidth() + 10, panel.getHeight() + 40);
	this.getContentPane().removeAll();
	this.getContentPane().add(panel, null);
    }

}
