package org.digitall.apps.personalfiles.interfaces;

import java.awt.datatransfer.StringSelection;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragSource;

import java.util.Vector;
import javax.swing.JTable;

import org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin.DragSourceListenerGridPanel;
import org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin.DragSourceListenerGridPanelRxP;
import org.digitall.lib.components.grid.GridPanel;

/**
 *  Clase encargada de observar los "gestos" del usuario para decidir si ha iniciado
 *  una operacion de arrastre. 
 */
public class DragGestureListenerGridPanelRecursosOfPerson implements java.awt.dnd.DragGestureListener {
    private DragSourceListenerGridPanelRxP dragSourceGridPanelListenerRxP;    
    private GridPanel grilla;
    private GridPanel gridPanelPersonal;    
    /**
    * Crea  un nuevo objeto de la clase DragGestureListener
    */
    public DragGestureListenerGridPanelRecursosOfPerson(GridPanel _grilla,  DragSourceListenerGridPanelRxP _dragSourceGridPanelListenerRxP, GridPanel _gridPanelPersonal) {
	this.grilla = _grilla;
	this.gridPanelPersonal = _gridPanelPersonal;
	this.dragSourceGridPanelListenerRxP = _dragSourceGridPanelListenerRxP;
}

    /**
    * Este listener tiene un unico metodo, invocado cuando AWT considera
    * que lo  que está haciendo el usuario es una operación de arrastre.
    */
    public void dragGestureRecognized(DragGestureEvent dge) {
    //La clase que implementa Transferable, para el caso de una cadena
    //ya la proporciona java: StringSelection
    //Aqui mostramos que el nombre del elemento que fue movido     
	
    int filaSeleccionada = gridPanelPersonal.getTable().getSelectedRow();
    Vector idPersonal = gridPanelPersonal.getValuesAt(0);
    //System.out.println("tuplas: "+ gridPanelPersonal.getr);
    Vector vector = gridPanelPersonal.getSelectedsID();	 
	
    StringSelection seleccion = new StringSelection("GridPanelPersonal");
    //Con este metodo del evento comenzamos "oficialmente" la operacion de arrastre
    dge.startDrag(
    //Aqui indicamos el tipo de cursor que queremos durante el arrastre, null si nos vale el proporcionado por defecto
    null,
    //Aqui va el Transferable que queremos enviar 
    seleccion,
    //Aqui va el listener del proceso de arrastre
    this.dragSourceGridPanelListenerRxP );         
    }   
}
