package org.digitall.lib.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.BorderFactory;

import org.digitall.lib.components.basic.BasicTextField;

public class JOutry extends BasicTextField {

    public JOutry() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setBorder(BorderFactory.createLineBorder(Color.blue, 1));
	setEditable(false);
	setBackground(Color.white);
	setForeground(Color.red);
	setFont(new Font("Dialog", Font.BOLD, 11));
	setBounds(new Rectangle(5, 25, 90, 20));
    }

}
