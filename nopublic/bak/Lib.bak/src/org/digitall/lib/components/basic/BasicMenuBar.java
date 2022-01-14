package org.digitall.lib.components.basic;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JMenuBar;

public class BasicMenuBar extends JMenuBar {

    public BasicMenuBar() {
	super();
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
    }

    public boolean saveData() {
	return true;
    }

    public void paint(Graphics g) {
	Graphics2D g2 = (Graphics2D)g;
	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	int w = getWidth();
	int h = getHeight();
	GradientPaint gradient = new GradientPaint(0, 0, BasicConfig.PANEL_GRADIENT_START_COLOR, 0, h, BasicConfig.PANEL_GRADIENT_END_COLOR, false);
	g2.setPaint(gradient);
	g2.fillRect(0, 0, w, h);
	super.paintComponents(g);
    }

}
