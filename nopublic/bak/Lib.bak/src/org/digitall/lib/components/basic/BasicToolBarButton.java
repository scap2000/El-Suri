package org.digitall.lib.components.basic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import java.awt.event.FocusEvent;

import java.awt.event.FocusListener;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class BasicToolBarButton extends JButton {

    public BasicToolBarButton() {
	super();
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicToolBarButton(String _string) {
	super(_string);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicToolBarButton(ImageIcon _icon) {
	super(_icon);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicToolBarButton(Icon _icon) {
	super(_icon);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicToolBarButton(String _text, Icon _icon) {
	super(_text, _icon);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicToolBarButton(Action _action) {
	super(_action);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setContentAreaFilled(false);
	this.setBorderPainted(false);
	this.setOpaque(false);
	this.setFocusPainted(false);
	setFont(new Font("Dialog", 1, 11));
	setForeground(Color.BLUE.darker());
	setHorizontalAlignment(SwingConstants.LEFT);
	setHorizontalTextPosition(SwingConstants.RIGHT);
	setIconTextGap(3);
	setAlignmentY(JButton.CENTER_ALIGNMENT);
	setAlignmentX(JButton.CENTER_ALIGNMENT);
	setMargin(new Insets(1, 1, 1, 1));
	setRolloverEnabled(true);
	setVerticalTextPosition(SwingConstants.BOTTOM);
	setHorizontalTextPosition(SwingConstants.CENTER);
	setForeground(BasicConfig.LABEL_FOREGROUND_COLOR);
	addFocusListener(new FocusListener() {

	    public void focusGained(FocusEvent focusEvent) {
	        setBorderPainted(true);
		setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0), 1));
	    }

	    public void focusLost(FocusEvent focusEvent) {
		setBorder(null);
	    }

	});
    }
    /*protected void paintComponent(Graphics g) {
	Graphics2D g2 = (Graphics2D)g;
	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	int w = getWidth();
	int h = getHeight();
	GradientPaint gradient = new GradientPaint(20, 0, BasicConfig.BUTTON_GRADIENT_START_COLOR, 20, h, BasicConfig.BUTTON_GRADIENT_END_COLOR, false);
	g2.setPaint(gradient);
	g2.fillRect(0, 0, w, h);
	super.paintComponent(g);
    }*/

}
