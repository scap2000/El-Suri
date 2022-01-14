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
