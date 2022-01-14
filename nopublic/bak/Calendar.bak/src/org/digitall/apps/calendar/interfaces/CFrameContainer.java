package org.digitall.apps.calendar.interfaces;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JDesktopPane;

import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicPanel;

public class CFrameContainer extends BasicInternalFrame {

    private BasicPanel panel;
    private int panelType;
    private String title = "";
    private JDesktopPane parentDesktop;
    private Component parentList;
    private Object params;

    public CFrameContainer(String _Title) {
	try {
	    //panelType = _panelType;
	    title = _Title;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.getContentPane().setLayout(null);
	this.setSize(new Dimension(763, 615));
	//this.setClosable(true);
	//this.setIconifiable(true);
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
