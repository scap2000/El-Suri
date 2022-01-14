package org.digitall.lib.components.basic;

import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BasicTitleLabel extends JLabel {

    public BasicTitleLabel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicTitleLabel(ImageIcon _icon) {
	super(_icon);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicTitleLabel(String _string) {
	super(_string);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicTitleLabel(String _string, int _int) {
	super(_string, _int);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicTitleLabel(String _string, Icon _icon) {
	super(_string, _icon, BasicTitleLabel.LEFT);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicTitleLabel(String _string, Icon _icon, int _int) {
	super(_string, _icon, _int);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setOpaque(false);
	setFont(new Font("Lucida Sans", Font.PLAIN, 13));
	setForeground(BasicConfig.TITLELABEL_FOREGROUND_COLOR);
	setBackground(BasicConfig.TITLELABEL_BACKGROUND_COLOR);
    }

}
