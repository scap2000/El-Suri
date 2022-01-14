package org.digitall.lib.print;

import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JDialog;

public class GrillaTest extends JDialog {

    public GrillaTest() {
	this(null, "", false);
    }

    public GrillaTest(Frame parent, String title, boolean modal) {
	super(parent, title, modal);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize( new Dimension( 400, 300 ) );
	this.getContentPane().setLayout( null );
    }

}
