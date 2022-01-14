package org.digitall.apps.personalfiles.interfaces;

import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

import org.digitall.lib.components.grid.GridPanel;

public class DropTargetListenerResourcesPerson implements DropTargetListener{
    private GridPanel grillaRxP;
    private GridPanel grillaPersonal;
    public DropTargetListenerResourcesPerson(GridPanel _grillaRxP,GridPanel _grillaPersonal) {
        grillaRxP = _grillaRxP;
	grillaPersonal = _grillaPersonal;
    }

    public void dragEnter(DropTargetDragEvent dtde) {
    }

    public void dragOver(DropTargetDragEvent dtde) {
    }

    public void dropActionChanged(DropTargetDragEvent dtde) {
    }

    public void dragExit(DropTargetEvent dte) {
    }

    public void drop(DropTargetDropEvent dtde) {
        System.out.println("estoy soltando Recurso");
        DropTarget dropTarget = (DropTarget)dtde.getSource();
        System.out.println("persona seleccionada con check donde suelto: "+grillaPersonal.getSelectedsID().elementAt(0));
    }
}
