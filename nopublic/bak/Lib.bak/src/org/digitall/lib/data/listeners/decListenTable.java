package org.digitall.lib.data.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.digitall.lib.components.basic.BasicTable;
import org.digitall.lib.components.basic.BasicTextField;
import org.digitall.lib.data.DataTypes;

//Validador de Moneda PARA JTABLES

public class decListenTable implements KeyListener {

    private int tecla = 0;
    private boolean isEditing = false;

    public void keyTyped(KeyEvent evt) {
	isEditing = true;
	BasicTable t = (BasicTable)evt.getSource();
	BasicTextField c = new BasicTextField(t.getValueAt(t.getSelectedRow(), t.getSelectedColumn()).toString());
	String Anterior = c.getText();
	int longcampo = 7;
	char ch = evt.getKeyChar();
	if (ch == '\'') {
	    evt.consume();
	} else if (c.getText().length() >= longcampo) {
	    if (tecla != 8)
		evt.consume();
	} else if (!Character.isDigit(ch)) {
	    if (ch == '.' & c.getText().indexOf(".") == -1) {
	    } else {
		if (tecla != 8) {
		    evt.consume();
		}
	    }
	}
	String valida = DataTypes.validateType(DataTypes.DOUBLE, c.getText());
	if (valida.equals("*"))
	    c.setText(Anterior);
    }

    public void keyReleased(KeyEvent key) {
    }

    public void keyPressed(KeyEvent key) {
	int teclas[] = new int[] { 113 };
	boolean isHere = false;
	int i = 0;
	tecla = key.getKeyCode();
	System.out.println(tecla);
	while (!isHere & i < teclas.length) {
	    if (teclas[i] == tecla) {
		isHere = true;
		key.consume();
	    }
	    switch (tecla) {
		case 10:
		    //Enter
		    isEditing = false;
		    BasicTable t = (BasicTable)key.getSource();
		    key.setKeyCode(39);
		    break;
		case 127:
		    //Delete
		    t = (BasicTable)key.getSource();
		    t.setValueAt("", t.getSelectedRow(), t.getSelectedColumn());
		    break;
	    }
	    i++;
	}
    }

}
