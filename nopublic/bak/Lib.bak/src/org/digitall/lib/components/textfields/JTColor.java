package org.digitall.lib.components.textfields;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JColorChooser;

import org.digitall.lib.components.basic.BasicTextField;
import org.digitall.lib.components.basic.BasicTextInput;
import org.digitall.lib.data.Format;

public class JTColor extends BasicTextInput {
    private Component component;
    
    public JTColor(Component _component) {
	setValue("#FFFFFF");
	setEditable(false);
	component = _component;
	addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (isEnabled()) {
		    if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
		        Color newColor = JColorChooser.showDialog(component, "Seleccione color", Color.BLACK);
			if (newColor != null){
			    setText("#"+ Format.color2Hex(newColor).toUpperCase());
			    setBackground(newColor);
		        } else {
			    setText("");
			}
		    } else {
			setText("");
		    }
		}
	    }

	});
    }
    
}
