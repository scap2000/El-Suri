package org.digitall.lib.components.basic;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BasicLabel extends JLabel {

    public BasicLabel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicLabel(ImageIcon _icon) {
	super(_icon);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicLabel(String _string, ImageIcon _icon) {
	super(_string, _icon, BasicLabel.LEFT);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicLabel(String _string) {
	super(_string);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicLabel(String _string, int _int) {
	super(_string, _int);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }


    private void jbInit() throws Exception {
	setOpaque(false);
	setFont(new Font("Lucida Sans", Font.PLAIN, 12));
	setForeground(BasicConfig.LABEL_FOREGROUND_COLOR);
    }

}
