package org.digitall.lib.components.basic;

import java.awt.Container;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class GeneralButtonsContainer extends Container {

    public GeneralButtonsContainer() {
	super();
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
    }

    public boolean saveData() {
	return true;
    }

    public void paint(Graphics g) {
	Graphics2D g2 = (Graphics2D)g;
	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	int w = getWidth();
	int h = getHeight();
	int top = (int)(h*.05);
	GradientPaint gradient = new GradientPaint(0, 0, BasicConfig.GENERALBUTTONS_CONTAINER_GRADIENT_START_COLOR, 0, top, BasicConfig.GENERALBUTTONS_CONTAINER_GRADIENT_END_COLOR, false);
	g2.setPaint(gradient);
	g2.fillRect(0, 0, w, top);
	gradient = new GradientPaint(0, top, BasicConfig.GENERALBUTTONS_CONTAINER_GRADIENT_END_COLOR, 0, h, BasicConfig.GENERALBUTTONS_CONTAINER_GRADIENT_END_COLOR, false);
	g2.setPaint(gradient);
	g2.fillRect(0, top, w, h);
	super.paintComponents(g);
    }

}
