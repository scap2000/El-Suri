package org.digitall.common.drilling;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class DrillTableModel extends DefaultTableModel {

    private int colFrom = -1;
    private int colTo = -1;

    public DrillTableModel() {
    }

    public void addFila() {
	//Preguntar FROM: TO:
	//SI ACEPTA LOS DATOS
	//INSERTAR LOS DATOS EN LA BASE DE DATOS!!!
	//LEER LA BASE DE DATOS para la fila reciÃ©n cargada
	/** addRow(filareciencargada);*/
	Vector fila = new Vector();
	for (int i = 0; i < getColumnCount(); i++) {
	    fila.add("");
	}
	addRow(fila);
	//SI CANCELA
	//No agrego nada
    }

    public boolean isCellEditable(int r, int c) {
	if ((c == colFrom) || (c == colTo)) {
	    //System.out.println("entro: return false!!");
	    return false;
	} else {
	    return true;
	}
    }

    public void setColFrom(int _colFrom) {
	this.colFrom = _colFrom;
    }

    public void setColTo(int _colTo) {
	this.colTo = _colTo;
    }

    public Class getColumnClass(int c) {
	return getValueAt(0, c).getClass();
    }

    public void deleteRow(int _rowNumber) {
	removeRow(_rowNumber);
    }

}
