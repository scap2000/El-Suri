package org.digitall.common.geo.mapping.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import org.digitall.common.geo.mapping.BasicDrawEngine;
import org.digitall.lib.components.basic.BasicInternalFrame;

public class MapBasicTools extends JInternalFrame {

    private MapBasicToolsPanel toolsPanel = new MapBasicToolsPanel();
    private JPanel jpanel = new JPanel();

    public MapBasicTools() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setTitle("Herramientas");
	//putClientProperty("JInternalFrame.isPalette",Boolean.TRUE);
	setDefaultCloseOperation(BasicInternalFrame.HIDE_ON_CLOSE);
	setClosable(true);
	setSize(toolsPanel.getSize().width+10, toolsPanel.getSize().height+35);
	add(toolsPanel, BorderLayout.CENTER);
    }

    public void setDrawPanel(BasicDrawEngine _panel) {
	toolsPanel.setDrawPanel(_panel);
    }

    public void setHorizontal() {
	toolsPanel.setHorizontal();
	setSize(toolsPanel.getSize().width+10, toolsPanel.getSize().height+35);
    }

    public void setVertical() {
	toolsPanel.setVertical();
	setSize(toolsPanel.getSize().width+10, toolsPanel.getSize().height+35);
    }

    public void addTool(int _tool) {
	toolsPanel.addTool(_tool);
	setSize(toolsPanel.getSize().width+10, toolsPanel.getSize().height+35);
    }

}
