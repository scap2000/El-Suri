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
 * DragSourceListenerGridPanelRxP.java
 *
 * */
package org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin;

import java.awt.Color;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSourceContext;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;

import java.awt.dnd.DropTargetContext;

import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import org.digitall.lib.components.grid.GridPanel;

/**
 * Este es el listener que nos va a tener informados en todo momento de
 * como evoluciona el proceso de arrastre
 */
public class DragSourceListenerGridPanelRxP implements DragSourceListener{

    public DragSourceListenerGridPanelRxP() {		
    }
    /**
    * Este método se invoca cuando el ratón ha entrado en un posible
    * destino de soltar. Cambiamos el color de fondo de nuestra lista a verde
    */    
    public void dragEnter(DragSourceDragEvent dsde) {
    //Le damos un color al componente desde donde sacamos la fuente de arrastre
	dsde.getDragSourceContext(  ).getComponent(  ).setBackground( Color.WHITE);		    
	//GridPanel gridPanel = (GridPanel) dsde.getSource();
	//gridPanel.setBackground(Color.WHITE);
    }

    /**
    * Este método se invoca cuando el ratón se está moviendo sobre el
    * destino soltar
    */
    public void dragOver(DragSourceDragEvent dsde) {	
    }

    /**
    * Este método se invoca cuando el usuario ha cambiado el tipo de
    * accion (mover, copiar, enlazar), soltando o pulsando alguna de las
    * teclas SHIFT o CONTROL
    */
    public void dropActionChanged(DragSourceDragEvent dsde) {

    }

    /**
    * Este método se invoca cuando el ratón ha salido de un posible
    * destino de soltar. Cambiamos el color de fondo de nuestra lista al
    * habitual
    */
    public void dragExit(DragSourceEvent dse) {
	dse.getDragSourceContext(  ).getComponent(  ).setBackground(Color.WHITE );
	//GridPanel gridPanel = (GridPanel) dse.getSource();
	//gridPanel.setBackground(Color.WHITE);
    }

    /**
     * Este es el metodo mas importante: nos indica que el proceso de
     * arrastar minY soltar ha finalizado, es decir el usuario ha pulsado el
     * raton, lo ha arrastrado con el pulsado minY  lo ha soltado
     */
    public void dragDropEnd(DragSourceDropEvent dsde) {
	/**En primer lugar necesitamos saber si se ha completado con exito el proceso:
	*puede que el destino no fuese valido, no aceptase el tipo de arrastre 
	*(copiar, mover, enlazar) que el usuario ha intentado... */
	 if( dsde.getDropSuccess() ) {
	      if( dsde.getDropAction(  ) == DnDConstants.ACTION_COPY )
	      {
	          System.out.println("ACTION_COPY");
		 //En este caso no hacemos nada porque la lista sigue como estaba
		  //Aqui mostramos que el nombre del elemento que fue movido 
		  
	      }
	      else if( dsde.getDropAction(  ) == DnDConstants.ACTION_MOVE )
	      {
	          System.out.println("ACTION_MOVE___RXP");
		    
	      }
	      else if( dsde.getDropAction(  ) == DnDConstants.ACTION_LINK )
	      {
	           System.out.println("ACTION_LINK");
	         //Mismo comentario que en copiar: en otra circunstancia podríamos pasar 
		 //una referencia al elemento arrastrado o algo así...		
	      }    
	}
	dsde.getDragSourceContext(  ).getComponent(  ).setBackground( Color.WHITE );
    }   
}
