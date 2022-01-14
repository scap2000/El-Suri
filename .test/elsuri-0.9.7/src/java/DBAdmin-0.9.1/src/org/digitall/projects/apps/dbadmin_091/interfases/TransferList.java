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
 * TransferList.java
 *
 * */

package org.digitall.projects.apps.dbadmin_091.interfases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.digitall.lib.components.basic.BasicList;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.buttons.BackGridButton;
import org.digitall.lib.components.buttons.FirstGridButton;
import org.digitall.lib.components.buttons.LastGridButton;
import org.digitall.lib.components.buttons.NextGridButton;
import org.digitall.lib.sql.LibSQL;

public class TransferList extends BasicPanel {

    private BasicScrollPane scrollMadre = new BasicScrollPane();
    private BasicScrollPane scrollHija = new BasicScrollPane();
    private BasicList listMadre = new BasicList();
    private BasicList listHija = new BasicList();
    private JLabel nombreMadre = new JLabel();
    private JLabel nombreHija = new JLabel();
    
    private NextGridButton bAdd = new NextGridButton();
    private BackGridButton bDelete = new BackGridButton();
    private FirstGridButton bDeleteAll = new FirstGridButton();
    private LastGridButton bAddAll = new LastGridButton();
    
    private String tabla = "";
    private String dataBase =  "";
    private String madre = "Madre";
    private String hija = "Hija";
    private String where = "";
    private String campo = "";
    private String consulta = "";
    private Vector elementos = new Vector();
    private Vector elementosMadre = new Vector();
    private Vector elementosHija = new Vector();
    private String storedAll = "";
    private String paramsAll = "";
    private String storedHija = "";
    private String paramsHija = "";

    private BasicPanel panelButtons = new BasicPanel();
    private GridLayout gridLayout1 = new GridLayout();
    private BasicPanel panelSun = new BasicPanel();
    private BasicPanel panelMother = new BasicPanel();

    private boolean order = false;
    private String queryMadre = "";
    private String queryHija = "";
    private Vector listadoMadreOriginal = new Vector();
    private Vector listadoHijaOriginal = new Vector();

    public TransferList(String _campo,String _madre,String _hija,String _tabla, String _dataBase,String _where) {
        try {
            madre = _madre;
            hija = _hija;
            tabla = _tabla;
            dataBase = _dataBase;
            where = _where;
            campo = _campo;
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public TransferList(String _madre,String _hija,String _stored,String _params,boolean _order) {
        try {
	    order = _order;
            madre = _madre;
            hija = _hija;
	    paramsAll = _params;
	    storedAll = _stored;
            consulta = _stored;
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public TransferList(String _madre,String _hija,Vector _elementosMadre,Vector _elementosHija,boolean _order) {
        try {
	    order = _order;
            madre = _madre;
            hija = _hija;
            elementosMadre = _elementosMadre;
            elementosHija = _elementosHija;
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public TransferList(boolean _order,String _storedAll,String _paramsAll,String _storedHija,String _paramsHija) {
	try {
	    storedAll = _storedAll;
	    paramsAll = _paramsAll;
	    storedHija = _storedHija;
	    paramsHija = _paramsHija;
	    order = _order;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public TransferList() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//********************************
    public TransferList(String _madre, String _hija) {
	try {
	    madre = _madre;
	    hija = _hija;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	try {
	    bAdd.setEnabled(false);
	    bAddAll.setEnabled(false);
	    bDelete.setEnabled(false);
	    bDeleteAll.setEnabled(false);
	    consulta = "-1";
	} catch (Exception e) {
	    e.printStackTrace();
	}
        this.setLayout(null);
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        this.setSize(new Dimension(632, 350));
	scrollMadre.setBounds(new Rectangle(0, 15, 285, 315));
	scrollMadre.setSize(new Dimension(285, 315));
	scrollHija.setBounds(new Rectangle(0, 15, 285, 315));
	scrollHija.setSize(new Dimension(285, 315));
	listMadre.addListSelectionListener(new ListSelectionListener() {
                                               public void valueChanged(ListSelectionEvent e) {
						   if (!e.getValueIsAdjusting()) {
							listMadre_valueChanged(e);
						   }
                                               }
                                           }
        );
        listHija.addListSelectionListener(new ListSelectionListener() {
                                               public void valueChanged(ListSelectionEvent e) {
						   if (!e.getValueIsAdjusting()) {
							listHija_valueChanged(e);
						   }
                                               }
                                           }
        );
	nombreMadre.setText(madre);
	nombreMadre.setHorizontalAlignment(SwingConstants.CENTER);
        nombreMadre.setHorizontalTextPosition(SwingConstants.CENTER);
        nombreMadre.setFont(new Font("Dialog", 1, 11));
	nombreMadre.setForeground(Color.white);
	nombreMadre.setBounds(new Rectangle(0, 5, 225, 15));
	nombreHija.setText(hija);
	nombreHija.setHorizontalAlignment(SwingConstants.CENTER);
        nombreHija.setHorizontalTextPosition(SwingConstants.CENTER);
        nombreHija.setFont(new Font("Dialog", 1, 11));
	nombreHija.setForeground(Color.white);
	//bAdd.setIcon(iconos.getPath(21));
	nombreHija.setBounds(new Rectangle(0, 5, 210, 15));
	bAdd.addActionListener(new ActionListener() {
                                   public void actionPerformed(ActionEvent e) {
                                       bAdd_actionPerformed(e);
                                   }
                               }
        );
	//bAddAll.setIcon(iconos.getPath(22));
        bAddAll.addActionListener(new ActionListener() {
                                      public void actionPerformed(ActionEvent e) {
                                          bAddAll_actionPerformed(e);
                                      }
                                  }
        );
	//bDelete.setIcon(iconos.getPath(19));
        bDelete.addActionListener(new ActionListener() {
                                      public void actionPerformed(ActionEvent e) {
                                          bDelete_actionPerformed(e);
                                      }
                                  }
        );
	//bDeleteAll.setIcon(iconos.getPath(20));
        bDeleteAll.addActionListener(new ActionListener() {
                                         public void actionPerformed(ActionEvent e) {
                                             bDeleteAll_actionPerformed(e);
                                         }
                                     }
        );
	panelButtons.setLayout(gridLayout1);
	panelButtons.setBounds(new Rectangle(300, 110, 25, 135));
	gridLayout1.setRows(4);
	panelSun.setLayout(null);
	panelSun.setBounds(new Rectangle(335, 0, 290, 335));
	panelSun.setSize(new Dimension(290, 335));
	panelMother.setLayout(null);
	//cargarElementos(elementos);
	panelMother.setBounds(new Rectangle(5, 0, 290, 335));
	panelMother.setSize(new Dimension(290, 335));
	cargarAll(listMadre,listHija);
        listMadre.setSelectedIndex(0);
	listMadre.addMouseListener(new MouseAdapter() {
                                       public void mouseClicked(MouseEvent e) {
                                           listMadre_mouseClicked(e);
                                       }
                                   }
        );
        listHija.addMouseListener(new MouseAdapter() {
                                       public void mouseClicked(MouseEvent e) {
                                           listHija_mouseClicked(e);
                                       }
                                   }
        );
	//panelSun.add(scrollHija, BorderLayout.CENTER);
        panelButtons.add(bAdd, null);
        panelButtons.add(bDelete, null);
        panelButtons.add(bAddAll, null);
        panelButtons.add(bDeleteAll, null);
        panelSun.add(nombreHija, BorderLayout.NORTH);
        scrollHija.getViewport().add(listHija, null);
        panelSun.add(scrollHija);
        scrollMadre.getViewport().add(listMadre, null);
	//panelMother.add(scrollMadre, BorderLayout.CENTER);
        panelMother.add(nombreMadre, BorderLayout.NORTH);
        panelMother.add(scrollMadre);
	this.add(panelMother, new GridBagConstraints(0, 0, 1, 1, 2.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        this.add(panelSun, new GridBagConstraints(2, 0, 1, 1, 2.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        this.add(panelButtons, new GridBagConstraints(1, 0, 1, 1, 0.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
    }
    
    ///***************************
    private void pasar(JList listaDe,JList listaA){
	//int[] itemSeleccionados = listaDe.getSelectedIndices();
	Object[] itemSeleccionados = listaDe.getSelectedValues();
	for(int i=0;i<itemSeleccionados.length;i++){
	    String seleccionado = itemSeleccionados[i].toString();
	    ////eliminar(seleccionado,listaDe);
	    ////agregar(seleccionado,listaA);
	    //System.out.println("posi = "+sel);
	    Object objeto = itemSeleccionados[i];
	    //System.out.println("posi = "+objeto);
	    eliminar(objeto,listaDe);
	    agregar(objeto,listaA);
	    listaA.setSelectedValue(seleccionado,true);
	    listaDe.setSelectedIndex(0);
	    if(order){
		ordenarList(listaA);
	    }   
	}
    }
    
    private void agregar(Object _aAgregar,JList _lista){
	//System.out.println("INGRESO ");
	DefaultListModel listModel = new DefaultListModel();
	//System.out.println("tamanio = "+_lista.getModel().getSize());
	for(int i= 0; i < _lista.getModel().getSize(); i++){
	    listModel.addElement(_lista.getModel().getElementAt(i));
	}
	listModel.addElement((TransferListModel)_aAgregar);
	_lista.removeAll();
	_lista.setModel(listModel);
    }
    
    private void eliminar(Object _aEliminar,JList _lista){
	DefaultListModel listModel = new DefaultListModel();
	//System.out.println("objeto = "+_aEliminar);
	TransferListModel elemento = (TransferListModel)(_aEliminar);
	String nombre = "";
	for(int i= 0; i < _lista.getModel().getSize(); i++){
	    //System.out.println("elemento.getName = "+elemento.getName());
	    nombre = ((TransferListModel)(_lista.getModel().getElementAt(i))).getName();
	    //System.out.println("nombre = "+nombre);
	    if(!elemento.getName().equals(nombre)){
		listModel.addElement(_lista.getModel().getElementAt(i));
	    }
	}
	_lista.removeAll();
	_lista.setModel(listModel);
    }
    
    public void cargarMadre(Vector _madre){
	elementosMadre = _madre;
	cargarLista(listMadre,_madre);
	listMadre.setSelectedIndex(0);
	if(listHija.getModel().getSize() == 0){
	    bDelete.setEnabled(false);
	    bDeleteAll.setEnabled(false);
	}
    }
    
    public void cargarListas(Vector _madre,Vector _hija){
	listadoHijaOriginal = _hija;
	listadoMadreOriginal = _madre;
	cargarMadre(_madre);
	cargarHija(_hija);
    }
    
    public void cargarHija(Vector _hija){
	elementosHija = _hija;
	cargarLista(listHija,_hija);
	listHija.setSelectedIndex(0);
	if(listHija.getModel().getSize() == 0){
	    bDelete.setEnabled(false);
	    bDeleteAll.setEnabled(false);
	}
    }
    
    //Metodo que retorna listado con los items transferidos de la lista Hija a la lista Madre
    public Vector getTransferListHija(){
	Vector listadoFinal = getListModelHija();
	Vector resultado = new Vector();
	boolean bandera = false;
	int i = 0;
	for(int j=0;j<listadoHijaOriginal.size();j++){
	    TransferListModel item = ((TransferListModel)listadoHijaOriginal.elementAt(j));
	    i = 0;
	    bandera = false;
	    while((!bandera)&&(i<listadoFinal.size())){
		TransferListModel itemFinal = ((TransferListModel)listadoFinal.elementAt(i));
		if(item.getName().equals(itemFinal.getName())){
		    bandera = true;
		}
		else{
		    i++;
		}
	    }
	    if(!bandera){
		resultado.add(item);
	    }
	}
	return resultado;
    }
    
    //Metodo que retorna listado con los items transferidos de la lista Madre a la lista Hija
    public Vector getTransferListMadre(){
	Vector listadoFinal = getListModelMadre();
	Vector resultado = new Vector();
	boolean bandera = false;
	int i = 0;
	for(int j=0;j<listadoMadreOriginal.size();j++){
	    TransferListModel item = ((TransferListModel)listadoMadreOriginal.elementAt(j));
	    i = 0;
	    bandera = false;
	    while((!bandera)&&(i<listadoFinal.size())){
		TransferListModel itemFinal = ((TransferListModel)listadoFinal.elementAt(i));
		if(item.getName().equals(itemFinal.getName())){
		    bandera = true;
		}
		else{
		    i++;
		}
	    }
	    if(!bandera){
		resultado.add(item);
	    }
	}
	return resultado;
    }
    
    public Vector getListModelHija(){
	Vector listado = new Vector();
	//System.out.println("tamanio lista = "+listHija.getModel().getSize());
	for(int i=0; i< listHija.getModel().getSize();i++){
	    listado.add((TransferListModel)(listHija.getModel().getElementAt(i)));
	}
	return listado;
    }
    
    public Vector getListModelMadre(){
	Vector listado = new Vector();
	//System.out.println("tamanio lista = "+listMadre.getModel().getSize());
	for(int i=0; i< listMadre.getModel().getSize();i++){
	    listado.add((TransferListModel)(listMadre.getModel().getElementAt(i)));
	}
	return listado;
    }

    private void bAdd_actionPerformed(ActionEvent e) {
	pasar(listMadre,listHija);
	listHija.setSelectedIndex(0);
    }

    private void bAddAll_actionPerformed(ActionEvent e) {
	//cargarAll(listHija);
	bAdd.setEnabled(false);
	bAddAll.setEnabled(false);
	bDelete.setEnabled(true);
	bDeleteAll.setEnabled(true);
	cargarTodos(listHija);
	eliminarAll(listMadre);
	listHija.setSelectedIndex(0);
    }

    private void bDelete_actionPerformed(ActionEvent e) {
	pasar(listHija,listMadre);
	listMadre.setSelectedIndex(0);
    }

    private void bDeleteAll_actionPerformed(ActionEvent e) {
	//cargarAll(listMadre);
	bAdd.setEnabled(true);
	bAddAll.setEnabled(true);
	bDelete.setEnabled(false);
	bDeleteAll.setEnabled(false);
	cargarTodos(listMadre);
	eliminarAll(listHija);
	listMadre.setSelectedIndex(0);
    }
    
    ////*******************************
    
    
    private void cargarElementos(Vector _vector){
        if((elementosMadre.size() == 0)&&(elementosHija.size() == 0)&&(elementos.size() == 0)&&(!storedAll.equals("-1"))){
            _vector.removeAllElements();
            ResultSet result = LibSQL.exFunction(storedAll,paramsAll);
            try {
                while(result.next()){
		    if(order){
			Vector aux = new Vector();
			aux.add(result.getString(1));
		        aux.add(result.getString(2));
		        aux.add(result.getString(3));
			_vector.add(aux);
		    }else{
		        _vector.add(result.getString(1));
		    }
                }
            } catch (SQLException e) {
                // TODO
                e.printStackTrace();
            }
	    
	    
	    
	    //cargar hija
	    result = LibSQL.exFunction(storedHija,paramsHija);
	    try {
		while(result.next()){
		    if(order){
			Vector aux = new Vector();
			aux.add(result.getString(1));
			aux.add(result.getString(2));
		        aux.add(result.getString(3));
			elementosHija.add(aux);
		    }else{
			elementosHija.add(result.getString(1));
		    }
		}
	    } catch (SQLException e) {
		// TODO
		e.printStackTrace();
	    }
        }else{
            _vector = null;
        }
    }
    
    public void cargarElementos(String _queryMadre, String _queryHija){
	queryMadre = _queryMadre;
	queryHija = _queryHija;
	ResultSet result = LibSQL.exQuery(queryMadre);
	try {
	    while(result.next()){
		Vector aux = new Vector();
		aux.add(result.getString(1));
		aux.add(result.getString(2));
		aux.add(result.getString(3));
		elementosMadre.add(aux);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	result = LibSQL.exQuery(queryHija);
	try {
	    while(result.next()){
		Vector aux = new Vector();
		aux.add(result.getString(1));
		aux.add(result.getString(2));
		aux.add(result.getString(3));
		elementosHija.add(aux);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
    
    public void cargarAll(){
	cargarAll(listMadre,listHija);
    }
    
    
    private void cargarAll(JList madre,JList hija){
        if(elementos.size() != 0){
            madre.removeAll();
            cargarLista(madre,elementos);
            madre.setSelectedIndex(0);
	    if(elementosHija.size() != 0){
		for(int i = 0 ; i < elementosHija.size(); i++){
		    Vector aux = (Vector)elementosHija.elementAt(i);
		    listMadre.setSelectedValue(aux.elementAt(0).toString(),true);   
		    pasar(listMadre,listHija);
		}
	    }
        }else{
            cargarLista(madre,elementosMadre);
            cargarLista(hija,elementosHija);
        }
    }
    
    private void cargarLista(JList _lista,Vector _elementos){
        DefaultListModel listModel = new DefaultListModel();
        _lista.removeAll();
	if(order){
	    _elementos = ordenarElementos(_elementos);
	}
	
        for(int i= 0; i < _elementos.size(); i++){
	    if(order){
		Vector aux = (Vector)_elementos.elementAt(i);	    
		listModel.addElement(aux.elementAt(0).toString());
		//System.out.println("--> "+ aux.elementAt(0).toString());
	    }else{
	        listModel.addElement(_elementos.elementAt(i));
	    }
        }
        _lista.setModel(listModel);
    }
    
    private Vector ordenarElementos(Vector _vector){
	Vector resultado = _vector;
	int fin = resultado.size();
	for(int i = 0 ; i < (fin-1); i++){
	    for(int j = i+1; j < fin ; j++){
	        Vector doble = (Vector)resultado.elementAt(i);
	        int observado = Integer.parseInt(doble.elementAt(1).toString());
	        Vector doblePotencial = (Vector)resultado.elementAt(j);
	        int potencial = Integer.parseInt(doblePotencial.elementAt(1).toString());
		if(potencial < observado){
		    resultado = cambiarElementosVector(resultado,i,j,doble,doblePotencial);
		}
	    }
	}
	return(resultado);
    }
    
    private Vector cambiarElementosVector(Vector _original,int _posI,int _posJ,Vector _elementoI, Vector _elementoJ){
	Vector resultado = new Vector();
	for(int i = 0; i < _original.size() ; i++){
	    if(i == _posI){
		resultado.add(_elementoJ);
	    }else{
		if(i == _posJ){
		    resultado.add(_elementoI);
		}else{
		    resultado.add(_original.elementAt(i));
		}
	    }
	}
	return(resultado);
    }
    
    
    /*private void pasar(JList listaDe,JList listaA){
	String seleccionado = listaDe.getSelectedValue().toString();
	////eliminar(seleccionado,listaDe);
	////agregar(seleccionado,listaA);
	int sel = listaDe.getSelectedIndex();
	//System.out.println("posi = "+sel);
	Object objeto = listaDe.getModel().getElementAt(sel);
	//System.out.println("posi = "+objeto);
	eliminar(objeto,listaDe);
	agregar(objeto,listaA);
	listaA.setSelectedValue(seleccionado,true);
	listaDe.setSelectedIndex(0);
	if(order){
	    ordenarList(listaA);
	}
    }*/
    
    private void ordenarList(JList _list){
	Vector aOrdenar = new Vector();
	for(int i= 0; i < _list.getModel().getSize(); i++){
	    String elemento = _list.getModel().getElementAt(i).toString();
	    int order = getOrder(elemento,elementos);
	    Vector aux = new Vector();
	    aux.add(elemento);
	    aux.add(order);
	    aOrdenar.add(aux);
	}
	aOrdenar = ordenarElementos(aOrdenar);
	DefaultListModel listModel = new DefaultListModel();
	for(int i= 0; i < aOrdenar.size(); i++){
	    Vector data = (Vector)aOrdenar.elementAt(i);
	    String elemento = data.elementAt(0).toString();
	    listModel.addElement(elemento);
	}
	_list.setModel(listModel);
    }
    
    private int getOrder(String _elemento, Vector _elementos){
	int fin = _elementos.size();
	boolean encontrado = false;
	int i = 0;
	int resultado = -1;
	while((i < fin )&&(!encontrado)){
	    Vector data = (Vector)_elementos.elementAt(i);
	    if(data.elementAt(0).toString().equals(_elemento)){
		resultado = Integer.parseInt(data.elementAt(1).toString());
		encontrado = true;
	    }else{
		i++;
	    }
	}
	return(resultado);
    }
    
    private void eliminar(String _aEliminar,JList _lista){
        DefaultListModel listModel = new DefaultListModel();
        for(int i= 0; i < _lista.getModel().getSize(); i++){
            String elemento = _lista.getModel().getElementAt(i).toString();
            if(!elemento.equals(_aEliminar)){
                listModel.addElement(elemento);
            }
        }
	_lista.removeAll();
        _lista.setModel(listModel);
    }
    
    private void agregar(String _aAgregar,JList _lista){
        DefaultListModel listModel = new DefaultListModel();
        for(int i= 0; i < _lista.getModel().getSize(); i++){
            String elemento = _lista.getModel().getElementAt(i).toString();
            listModel.addElement(elemento);
        }
        listModel.addElement(_aAgregar);
        _lista.setModel(listModel);
    }
    
    private Vector suma(Vector _vec1, Vector _vec2){
	Vector resultado = new Vector();
	for(int i = 0; i < _vec1.size(); i++){
	    resultado.add(_vec1.elementAt(i).toString());
	}
	for(int i = 0; i < _vec2.size(); i++){
	    resultado.add(_vec2.elementAt(i).toString());
	}
	return(resultado);
    }           
    
    public void cargarTodos(JList _lista){
	Vector listado = new Vector();
	for(int i=0; i< listMadre.getModel().getSize();i++){
	    listado.add((TransferListModel)(listMadre.getModel().getElementAt(i)));
	}
	for(int i=0; i< listHija.getModel().getSize();i++){
	    listado.add((TransferListModel)(listHija.getModel().getElementAt(i)));
	}
	cargarLista(_lista,listado);
    }
    
    private void cargarAll(JList lista){//listamadre
	if(elementos.size() != 0){
	    lista.removeAll();
	    cargarLista(lista,elementos);
	    lista.setSelectedIndex(0);
	}else{
	    Vector elementosTotal = new Vector();
	    elementosTotal = suma(elementosMadre,elementosHija);
	    cargarLista(lista,elementosTotal);
	}
	lista.setSelectedIndex(0);
    }
    
    private void eliminarAll(JList lista){//listahija
	DefaultListModel listModel = new DefaultListModel();
	lista.setModel(listModel);
    }
    
    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getTabla() {
        return tabla;
    }

    public void setDataBase(String dataBase) {
        this.dataBase = dataBase;
    }

    public String getDataBase() {
        return dataBase;
    }

    public void setMadre(String _madre) {
        this.madre = _madre;
        nombreMadre.setText(_madre);
    }

    public String getMadre() {
        return madre;
    }

    public void setHija(String _hija) {
        this.hija = _hija;
        nombreHija.setText(_hija);
    }

    public String getHija() {
        return hija;
    }
    
    public void setEnabledButonsTransfer(boolean _enabled){
	bAdd.setEnabled(_enabled);
	bAddAll.setEnabled(_enabled);
	bDelete.setEnabled(_enabled);
	bDeleteAll.setEnabled(_enabled);
    }

    /**2009-12-09(moraless)*/
    private void listMadre_valueChanged(ListSelectionEvent e) {
	/*if(listHija.getModel().getSize() > 0){
	    bDelete.setEnabled(true);
	    bDeleteAll.setEnabled(true);
	    listMadre.setSelectedIndex(0);
	}*/
        if(listMadre.getModel().getSize() > 0){
	    bAdd.setEnabled(true);
	    bAddAll.setEnabled(true);
        }
	else{
	    bAdd.setEnabled(false);
	    bAddAll.setEnabled(false);
	}
    }
    
    /**2009-12-09(moraless)*/
    private void listHija_valueChanged(ListSelectionEvent e) {
	/*if(listMadre.getModel().getSize() > 0){
	    bAdd.setEnabled(true);
	    bAddAll.setEnabled(true);
	}*/
	if(listHija.getModel().getSize() > 0){
	    bDelete.setEnabled(true);
	    bDeleteAll.setEnabled(true);
	    //listHija.setSelectedIndex(0);
	}else{
	    bDelete.setEnabled(false);
	    bDeleteAll.setEnabled(false);
        }
    }

    public BasicList getListMadre() {
        return listMadre;
    }

    public BasicList getListHija() {
        return listHija;
    }
    
    public Vector getListadoDeReferencia(){
	Vector listado = new Vector();
	for(int i=0; i< listHija.getModel().getSize();i++){
	    listado.add(listHija.getModel().getElementAt(i));
	}
	return listado;
    }
    
    public void setNombreMadre(JLabel nombreMadre) {
        this.nombreMadre = nombreMadre;
    }

    public JLabel getNombreMadre() {
        return nombreMadre;
    }

    public void setNombreHija(JLabel nombreHija) {
        this.nombreHija = nombreHija;
    }

    public JLabel getNombreHija() {
        return nombreHija;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getWhere() {
        return where;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getCampo() {
        return campo;
    }

    public void setElementos(Vector elementos) {
        this.elementos = elementos;
    }

    public Vector getElementos() {
        return elementos;
    }

    public void setElementosMadre(Vector elementosMadre) {
        this.elementosMadre = elementosMadre;
    }

    public Vector getElementosMadre() {
        return elementosMadre;
    }

    public void setElementosHija(Vector elementosHija) {
        this.elementosHija = elementosHija;
    }

    public Vector getElementosHija() {
        return elementosHija;
    }

    private void listMadre_mouseClicked(MouseEvent e) {
        clickPass(listMadre,e);
	listHija.setSelectedIndex(0);	
    }
    
    private void listHija_mouseClicked(MouseEvent e) {
        clickPass(listHija,e);
	listMadre.setSelectedIndex(0);   
    }
    
    private void clickPass(JList _lista,MouseEvent e){
        int index = _lista.locationToIndex(e.getPoint());
        int tamanioLista = _lista.getModel().getSize();
        if((index >= 0)&&(index <= tamanioLista)){
            if((e.getClickCount() == 2 )&&(e.getButton() == 1)){
                if(_lista.equals(listMadre)){
                    pasar(listMadre,listHija);
                }else{
                    pasar(listHija,listMadre);
                }
            }
        }
    }
}

