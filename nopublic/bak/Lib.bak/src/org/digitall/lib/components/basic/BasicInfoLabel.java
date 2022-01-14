package org.digitall.lib.components.basic;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class BasicInfoLabel extends JLabel {

    public BasicInfoLabel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicInfoLabel(ImageIcon _icon) {
	super(_icon);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicInfoLabel(String _string) {
	super(_string);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicInfoLabel(String _string, int _int) {
	super(_string, _int);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setOpaque(true);
	setBackground(BasicConfig.LABEL_BACKGROUND_COLOR);
	setFont(new Font("Lucida Sans", Font.PLAIN, 12));
	setForeground(BasicConfig.LABEL_FOREGROUND_COLOR);
	setHorizontalAlignment(SwingConstants.CENTER);
	setHorizontalTextPosition(SwingConstants.CENTER);
    }

}
