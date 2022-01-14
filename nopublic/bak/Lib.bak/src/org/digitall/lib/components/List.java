package org.digitall.lib.components;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import org.digitall.lib.sql.LibSQL;

//

public class List {

    Vector lista = new Vector();

    public List() {
    }

    public Vector getListFromQuery(String _query) {
	lista.removeAllElements();
	ResultSet resul = LibSQL.exQuery(_query);
	try {
	    while (resul.next()) {
		ListItem item = new ListItem(resul.getInt("id"), resul.getString("dato"));
		lista.addElement(item);
	    }
	} catch (SQLException ex) {
	    ex.printStackTrace();
	}
	return lista;
    }

    public int getIdFromString(String _dato) {
	int i = 0;
	int myId = -1;
	boolean found = false;
	while ((i < lista.size()) && !found) {
	    ListItem item = (ListItem)lista.elementAt(i);
	    if (item.getDato().equalsIgnoreCase(_dato)) {
		myId = item.getId();
		found = true;
	    }
	    i++;
	}
	return myId;
    }

    public ListItem getItemFromString(String _dato) {
	int i = 0;
	ListItem dataItem = new ListItem();
	boolean found = false;
	while ((i < lista.size()) && !found) {
	    ListItem item = (ListItem)lista.elementAt(i);
	    if (item.getDato().equalsIgnoreCase(_dato)) {
		dataItem = item;
		found = true;
	    }
	    i++;
	}
	return dataItem;
    }

    public ListItem getItemFromInt(int _id) {
	int i = 0;
	ListItem dataItem = new ListItem();
	boolean found = false;
	while ((i < lista.size()) && !found) {
	    ListItem item = (ListItem)lista.elementAt(i);
	    if (item.getId() == _id) {
		dataItem = item;
		found = true;
	    }
	    i++;
	}
	return dataItem;
    }

    public Vector getNombres() {
	Vector nombres = new Vector();
	for (int i = 0; i < lista.size(); i++) {
	    ListItem item = (ListItem)lista.elementAt(i);
	    nombres.add(item.getDato());
	}
	return nombres;
    }

    public Vector getItems() {
	return lista;
    }

    public void setItems(Vector _lista) {
	lista = new Vector();
	lista = _lista;
    }

    public Vector getIds() {
	Vector ids = new Vector();
	for (int i = 0; i < lista.size(); i++) {
	    ListItem item = (ListItem)lista.elementAt(i);
	    ids.add(new Integer(item.getId()));
	}
	return ids;
    }

    public void addElement(ListItem _item) {
	lista.addElement(_item);
    }

    public void swapItem(List _newlist, int _index) {
	ListItem _item = (ListItem)lista.elementAt(_index);
	_newlist.addElement(_item);
	lista.removeElementAt(_index);
    }

    public void removeList(Vector _vector) {
	int tamanio = _vector.size();
	/*System.out.println("_vector.size(): "+_vector.size());
        System.out.println("_vector.(0): "+_vector.elementAt(0));
        System.out.println("_vector.(1): "+_vector.elementAt(1));
        System.out.println("_vector.(2): "+_vector.elementAt(2));
        System.out.println("_vector.(3): "+_vector.elementAt(3));*/
	for (int i = 0; i < tamanio; i++) {
	    System.out.println("_vector[" + i + "]: " + _vector.elementAt(i));
	    lista.removeElementAt(Integer.parseInt(_vector.elementAt(i).toString()));
	}
    }

    public int getIndexFromString(String _dato) {
	int i = 0;
	int idx = -1;
	boolean found = false;
	while ((i < lista.size()) && !found) {
	    ListItem item = (ListItem)lista.elementAt(i);
	    if (item.getDato().equalsIgnoreCase(_dato)) {
		idx = i;
		found = true;
	    }
	    i++;
	}
	return idx;
    }

    public int getSize() {
	return lista.size();
    }

    public void removeItem(int _id) {
	boolean encontro = false;
	int i = 0;
	ListItem item = new ListItem();
	while ((!encontro) && (i < getSize())) {
	    item = (ListItem)(lista.elementAt(i));
	    if (item.getId() == _id) {
		encontro = true;
	    }
	    i++;
	}
	if (encontro) {
	    lista.remove(i - 1);
	}
    }

}
