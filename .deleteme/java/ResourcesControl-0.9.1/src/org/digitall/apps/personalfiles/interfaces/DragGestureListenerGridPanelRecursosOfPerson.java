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
 * DragGestureListenerGridPanelRecursosOfPerson.java
 *
 * */
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
