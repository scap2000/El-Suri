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
 * List.java
 *
 * */
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
