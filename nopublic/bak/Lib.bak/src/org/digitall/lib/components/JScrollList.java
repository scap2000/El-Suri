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
