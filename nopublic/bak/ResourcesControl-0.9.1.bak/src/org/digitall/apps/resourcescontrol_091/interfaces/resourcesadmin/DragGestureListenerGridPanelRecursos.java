package org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin;

import java.awt.datatransfer.StringSelection;
import java.awt.dnd.DragGestureEvent;


import javax.swing.JTable;

/**
 *  Clase encargada de observar los "gestos" del usuario para decidir si ha iniciado
 *  una operacion de arrastre. 
 */
public class DragGestureListenerGridPanelRecursos implements java.awt.dnd.DragGestureListener {
    private DragSourceListenerGridPanel dragSourceGridPanelListener;    
    private JTable jpList;    
    /**
    * Crea  un nuevo objeto de la clase DragGestureListener
    */
    public DragGestureListenerGridPanelRecursos(JTable _jpList, DragSourceListenerGridPanel _dragSourceGridPanelListener) {
	this.jpList = _jpList;
	this.dragSourceGridPanelListener = _dragSourceGridPanelListener;		
}

    /**
    * Este listener tiene un unico metodo, invocado cuando AWT considera
    * que lo  que está haciendo el usuario es una operación de arrastre.
    */
    public void dragGestureRecognized(DragGestureEvent dge) {
    //La clase que implementa Transferable, para el caso de una cadena
    //ya la proporciona java: StringSelection
    //Aqui mostramos que el nombre del elemento que fue movido     
	    	
    StringSelection seleccion = new StringSelection("GridPanelRecursos");
    //Con este metodo del evento comenzamos "oficialmente" la operacion de arrastre
    dge.startDrag(
    //Aqui indicamos el tipo de cursor que queremos durante el arrastre, null si nos vale el proporcionado por defecto
    null,
    //Aqui va el Transferable que queremos enviar 
    seleccion,
    //Aqui va el listener del proceso de arrastre
    this.dragSourceGridPanelListener );         
    }   
}
