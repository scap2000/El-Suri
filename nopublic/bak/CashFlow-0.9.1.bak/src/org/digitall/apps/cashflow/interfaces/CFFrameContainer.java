package org.digitall.apps.cashflow.interfaces;

import java.awt.Dimension;

import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;

public class CFFrameContainer  extends ExtendedInternalFrame {
    private BasicPanel panel;
    private String title;
    
    public CFFrameContainer(String _Title) {
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
