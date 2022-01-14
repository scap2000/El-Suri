//Validador de Cadenas
package org.digitall.lib.data.listeners;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.digitall.lib.components.basic.BasicTextField;
import org.digitall.lib.components.basic.BasicTextInput;

//public class strListen extends KeyAdapter

public class strListen implements KeyListener {

    private int tecla = 0;

    public void keyTyped(KeyEvent key) {
	BasicTextInput c = (BasicTextInput)key.getSource();
	String info = c.getName();
	int longcampo = Integer.parseInt(info);
	char ch = key.getKeyChar();
	if (ch == '\'') {
	    key.consume();
	} else if (c.getText().length() >= longcampo & tecla != 8) {
	    key.consume();
	}
    }

    public void keyReleased(KeyEvent key) {
    }

    public void keyPressed(KeyEvent key) {
	tecla = key.getKeyCode();
	/*    habil.clear();
    habil.addAll(Principal.teclasFun);
    if (habil.contains("" + tecla))
    {
      key.consume();
    } else
    {
    }*/
	if (tecla == 10) {
	    ((Component)key.getSource()).transferFocus();
	}
    }

}
