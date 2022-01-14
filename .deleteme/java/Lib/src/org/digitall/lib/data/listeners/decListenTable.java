/**
 LIMITACIÓN DE RESPONSABILIDAD - COPYRIGHT - [Español]
 ================================================================================
 El Suri - Entorno JAVA de Trabajo y Desarrollo para Gobierno Electrónico
 ================================================================================

 Información del Proyecto:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 por D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO es propiedad de
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) y
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los términos de la Licencia Pública General GNU publicada
 por la Fundación para el Software Libre, ya sea la versión 3
 de la Licencia, o (a su elección) cualquier versión posterior.

 Este programa se distribuye con la esperanza de que sea útil, pero
 SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita
 MERCANTIL o de APTITUD PARA UN PROPÓSITO DETERMINADO.
 Consulte los detalles de la Licencia Pública General GNU para obtener
 una información más detallada.

 Debería haber recibido una copia de la Licencia Pública General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
 El Suri - JAVA Management & Development Environment for Electronic Government
 =====================================================================================

 Project Info:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 by D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO is owned by
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) and
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/
/**
 * decListenTable.java
 *
 * */
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
