package org.digitall.lib.components.basic;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import org.digitall.lib.data.Format;

public class BasicButton extends JButton {

    private String text = "";
    private int horizontalInsets = 3;
    private boolean showText = true;
    private int idButton = -1;

    public BasicButton() {
	super();
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicButton(String _text) {
	text = _text;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicButton(ImageIcon _icon) {
	super(_icon);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicButton(Icon _icon) {
	super(_icon);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicButton(String _text, Icon _icon) {
	super(_icon);
	text = _text;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicButton(Action _action) {
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
	setHorizontalTextPosition(SwingConstants.RIGHT);
	setIconTextGap(3);
	setAlignmentY(JButton.CENTER_ALIGNMENT);
	setMargin(new Insets(1, 1, 1, 1));
	setRolloverEnabled(true);
	setVerticalTextPosition(SwingConstants.BOTTOM);
	setHorizontalTextPosition(SwingConstants.CENTER);
	setForeground(BasicConfig.LABEL_FOREGROUND_COLOR);
	setText(text);
	setCursor(new Cursor(Cursor.HAND_CURSOR));
	addFocusListener(new FocusListener() {

	    public void focusGained(FocusEvent focusEvent) {
	        setFocusPainted(true);
	    }

	    public void focusLost(FocusEvent focusEvent) {
	        setFocusPainted(false);
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

    public void setText(String _text) {
        if (_text != null) {
            if (showText) {
                super.setText(Format.toHtmlCentered(_text));
            } else {
                super.setText("");
            }
        }
	text = _text;
    }

    public void setShowText(boolean _showText) {
	showText = _showText;
	setText(text);
    }

    public void setIdButton(int _idButton) {
	idButton = _idButton;
    }

    public int getIdButton() {
	return idButton;
    }

    public void resizeToIconSize() {
	if (getIcon() != null) {
	    setMaximumSize(new Dimension(getIcon().getIconWidth()+horizontalInsets, getIcon().getIconHeight()));
	    setMinimumSize(new Dimension(getIcon().getIconWidth()+horizontalInsets, getIcon().getIconHeight()));
	}
    }

}
