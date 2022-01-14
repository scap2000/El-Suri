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
 * JScrollList.java
 *
 * */
package org.digitall.lib.components;

import java.awt.Color;
import java.awt.Font;

import java.awt.Point;

import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import org.digitall.lib.components.basic.BasicList;
import org.digitall.lib.components.basic.BasicScrollPane;

public class JScrollList extends BasicScrollPane{
    private BasicList lista = new BasicList();

    public JScrollList() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.getViewport().add(lista, null);
    }
    
    public void cargarLista(Vector _elementos){
        vaciarLista();
        DefaultListModel listModel = new DefaultListModel();
        for(int i = 0; i < _elementos.size(); i++){
            listModel.addElement(_elementos.elementAt(i).toString());
        }
        lista.setModel(listModel);
    }
    
    public void vaciarLista(){
        DefaultListModel listModel = new DefaultListModel();
        lista.setModel(listModel);
    }
    public String getSelected(){
        return(lista.getSelectedValue().toString());
    }
    
    public void setSelectedIndex(int _index){
        lista.setSelectedIndex(_index);
    }
    
    public JList getList(){
        return(lista);
    }
    
    public String getElement(int _index){
        String resultado = "";
        if(_index > -1){
            resultado = lista.getModel().getElementAt(_index).toString();
        }
        return(resultado);
    }
    
    public void setColorLista(Color _color){
        lista.setBackground(_color);
    }
    
    public void setFuente(Font _font){
        lista.setFont(_font);
    }
    
    public void setSelectionColor(Color _color){
        lista.setSelectionForeground(_color);
    }
    
    public void setSelectionModel(String _modo){
        if(_modo.equals("UNO")){
            lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }else{
            if(_modo.equals("INTERVALO_SIMPLE")){
                lista.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            }else{
                if(_modo.equals("INTERVALO_MULTIPLE")){
                    lista.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                }
            }
        }
    }
    
    public void setAltoCelda(int _alto){
        lista.setFixedCellHeight(_alto);
    }
    
    public void setFoco(boolean _test){
        lista.requestFocus(_test);
    }
    
    public Vector getSelecteds(){
        Vector resultado = new Vector();
        cargarVectorToArray(lista.getSelectedValues(), resultado);
        return(resultado);
    }
    
    private void cargarVectorToArray(Object[] _array, Vector _vector){
	_vector.removeAllElements();
	int tamanio = _array.length;
	for(int i = 0; i < tamanio; i++){
	    _vector.addElement(_array[i]);
	 }
    }
    
    public ListModel getModel(){
	return(lista.getModel());
    }
    
    public void setModel(DefaultListModel _listModel){
	lista.setModel(_listModel);
    }
    
    public int locationToIndex(Point _point){
	return(lista.locationToIndex(_point));
    }
    
    public Vector getAllElementsToString(){
	Vector resultado = new Vector();
	int fin = lista.getModel().getSize();
	for(int i= 0; i < fin ; i++){
	    resultado.add(lista.getModel().getElementAt(i));
	}
	return(resultado);
    }
}
