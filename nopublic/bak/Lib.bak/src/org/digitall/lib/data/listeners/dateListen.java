//Validador de Fechas
package org.digitall.lib.data.listeners;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.HashSet;
import java.util.Set;

import org.digitall.lib.components.basic.BasicTextField;
import org.digitall.lib.components.basic.BasicTextInput;

public class dateListen implements KeyListener {

    private Set habil = new HashSet();
    private int tecla = 0;
    private String separador = "/";
    private int cant_caracter = 0;
    private boolean fecha;

    public dateListen(boolean Fecha) {
	fecha = Fecha;
	if (fecha) {
	    separador = "/";
	    cant_caracter = 8;
	} else {
	    separador = ":";
	    cant_caracter = 5;
	}
    }

    public void keyTyped(KeyEvent key) {
	habil.clear();
	habil.addAll(Keyboard.getKeyInit());
	BasicTextInput c = (BasicTextInput)key.getSource();
	char ch = key.getKeyChar();
	if (c.getText().length() < cant_caracter & habil.contains("" + tecla)) {
	    if (c.getText().length() == 1 | c.getText().length() == 4) {
		if (c.getText().length() == 4 & fecha) {
		    c.setText(c.getText() + ch + separador);
		} else {
		    c.setText(c.getText() + ch);
		}
		key.consume();
	    } else if (c.getText().length() == 2 | c.getText().length() == 5) {
		c.setText(c.getText() + separador + ch);
		key.consume();
	    } else if (c.getText().length() >= cant_caracter & tecla != 8) {
		key.consume();
	    }
	} else if (tecla != 8 && tecla != 10) {
	    c.setText("" + ch);
	    key.consume();
	} else {
	}
    }

    public void keyReleased(KeyEvent key) {
    }

    public void keyPressed(KeyEvent key) {
	tecla = key.getKeyCode();
	habil.clear();
	habil.addAll(Keyboard.getKeyFun());
	if (habil.contains("" + tecla)) {
	    key.consume();
	} else {
	}
	if (tecla == 10) {
	    ((Component)key.getSource()).transferFocus();
	}
    }

}
