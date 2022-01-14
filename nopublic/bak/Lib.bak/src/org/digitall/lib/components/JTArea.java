package org.digitall.lib.components;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import java.io.IOException;

import java.net.URL;

import javax.swing.JTextPane;

public class JTArea extends JTextPane {

    public JTArea() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.addFocusListener(new FocusAdapter() {

		    public void focusGained(FocusEvent evt) {
			/*JTextPane jt = (JTextPane)evt.getSource();
			jt.selectAll();*/
		    }

		    public void focusLost(FocusEvent evt) {
		    }

		});
	//this.setLineWrap(true);
	//this.setWrapStyleWord(true);
    }

    

    @Override
    public String getText() {
	return super.getText().replaceAll("\'", "\\\\'").trim().replaceAll("  ", " ");
    }

    @Override
    public void setPage(URL uRL) throws IOException {
	super.setPage(uRL);
    }
}
