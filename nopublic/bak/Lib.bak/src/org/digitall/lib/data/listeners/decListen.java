//Validador de Moneda
package org.digitall.lib.data.listeners;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.digitall.lib.components.basic.BasicTextInput;
import org.digitall.lib.data.DataTypes;

public class decListen implements KeyListener {

    private int tecla = 0;

    public decListen() {
	Keyboard.keyboardInit();
    }

    public void keyTyped(KeyEvent evt) {
	BasicTextInput c = (BasicTextInput)evt.getSource();
	String Anterior = c.getText();
	char ch = evt.getKeyChar();
	if (ch == '\'') {
	    evt.consume();
	} else if (!Character.isDigit(ch)) {
	    if (ch == '.' & c.getText().indexOf(".") == -1) {
	    } else {
		if (tecla != 8)
		    evt.consume();
	    }
	}
	String valida = DataTypes.validateType(DataTypes.DOUBLE, c.getText());
	if (valida.equals("*"))
	    c.setText(Anterior);
    }

    public void keyReleased(KeyEvent key) {
    }

    public void keyPressed(KeyEvent key) {
	int teclas[] = new int[] { 127, 27, 32, 16, 17, 18, 65406, 33, 34, 35, 36, 37, 38, 39, 40, 224, 225, 226, 227 };
	boolean isHere = false;
	int i = 0;
	tecla = key.getKeyCode();
	while (!isHere & i < teclas.length) {
	    if (teclas[i] == tecla) {
		isHere = true;
		key.consume();
	    }
	    if (tecla == KeyEvent.VK_ENTER) {
		((Component)key.getSource()).transferFocus();
	    }
	    i++;
	}
    }

}
