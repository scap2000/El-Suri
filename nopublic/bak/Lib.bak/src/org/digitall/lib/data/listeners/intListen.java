//Validador de Enteros
package org.digitall.lib.data.listeners;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.HashSet;
import java.util.Set;

public class intListen implements KeyListener {

    private Set habil = new HashSet();
    private int tecla = 0;

    public intListen() {
	Keyboard.keyboardInit();
	habil.addAll(Keyboard.getKeyFun());
	habil.addAll(Keyboard.getKeyInit());
    }

    public void keyTyped(KeyEvent key) {
	if (!habil.contains("" + tecla) || key.getModifiers()!=0) {
	    if (tecla != KeyEvent.VK_BACK_SPACE)
		key.consume();
	} else {
	}
    }

    public void keyReleased(KeyEvent key) {
    }

    public void keyPressed(KeyEvent key) {
	tecla = key.getKeyCode();
	if (!habil.contains("" + tecla) || key.getModifiers()!=0) {
	    key.consume();
	} else {
	}
	if (tecla == KeyEvent.VK_ENTER) {
	    //((Component)key.getSource()).transferFocus();
	}
    }

}
