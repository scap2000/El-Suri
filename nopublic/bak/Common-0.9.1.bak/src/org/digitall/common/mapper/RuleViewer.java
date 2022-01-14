package org.digitall.common.mapper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;

public class RuleViewer extends JInternalFrame {

    private RulePanel rulePanel = new RulePanel();

    public RuleViewer() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setTitle("Regla");
	setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
	setClosable(true);
	setSize(new Dimension(167, 129));
	getContentPane().add(rulePanel, BorderLayout.CENTER);
	setBorder(BorderFactory.createLineBorder(Color.black, 1));
	rulePanel.setBounds(new Rectangle(0, 0, 165, 110));
    }

    public void setData(double _partial, double _total, double _area, double _angle) {
	rulePanel.setData(_partial, _total, _area, _angle);
    }
}
