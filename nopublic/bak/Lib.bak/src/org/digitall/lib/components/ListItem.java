package org.digitall.lib.components;

public class ListItem {

    private int id;
    private String dato;

    public ListItem() {
    }

    public ListItem(int _id, String _dato) {
	id = _id;
	dato = _dato;
    }

    public void setId(int _id) {
	this.id = _id;
    }

    public int getId() {
	return id;
    }

    public void setDato(String _dato) {
	this.dato = _dato;
    }

    public String getDato() {
	return dato;
    }

}
