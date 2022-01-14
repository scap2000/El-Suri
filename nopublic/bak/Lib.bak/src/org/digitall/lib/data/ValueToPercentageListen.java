package org.digitall.lib.data;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.digitall.lib.components.basic.BasicTextField;
import org.digitall.lib.components.basic.BasicTextInput;
import org.digitall.lib.components.inputpanels.TFInput;

public class ValueToPercentageListen implements KeyListener {
    private TFInput tfReference;
    double value = 0;

    public ValueToPercentageListen(TFInput _tfReference, double _value) {
	tfReference = _tfReference;
	value = _value;
    }

    public void keyTyped(KeyEvent _keyEvent) {
    }
    
    public void keyReleased(KeyEvent _keyEvent) {
	BasicTextInput tfSource = (BasicTextInput)_keyEvent.getSource();
	try {
	    tfReference.setValue((new Double(tfSource.getValue().toString()) * 100) / value);
	}
	catch (Exception e) {
	    tfReference.setValue(0.0);
	}
    }
    
    public void keyPressed(KeyEvent _keyEvent) {
    }
}
