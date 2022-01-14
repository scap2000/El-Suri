package org.digitall.lib.components;

import java.awt.Color;

import java.awt.event.FocusEvent;

import java.awt.event.FocusListener;

import java.io.IOException;

import java.net.URL;

import javax.swing.BorderFactory;

import org.digitall.lib.components.basic.BasicScrollPane;

public class JArea extends BasicScrollPane {

    private JTArea jtArea = new JTArea();
    
    public final static String CONTENT_HTML = "text/html";

    public JArea() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.getViewport().add(jtArea, null);
	addFocusListener(new FocusListener() {

	    public void focusGained(FocusEvent focusEvent) {
		setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0), 2));
	    }

	    public void focusLost(FocusEvent focusEvent) {
	        setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 1));
	    }

	});
    }

    public String getText() {
	return jtArea.getText();
    }

    public void setText(String _text) {
	jtArea.setText(_text);
    }
    
    public void setPage(URL uRL) {
	try {
	    jtArea.setPage(uRL);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public JTArea getTextArea() {
	return jtArea;
    }

    public void setDisabledTextColor(Color _color) {
	jtArea.setDisabledTextColor(_color);
    }

    public void setEditable(boolean _editable) {
	jtArea.setEditable(_editable);
    }

    public void setContentType(String _contenType) {
	jtArea.setContentType(_contenType);
    }

}
