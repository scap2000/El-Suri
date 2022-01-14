package org.digitall.apps.personalfiles.interfaces;

import java.awt.Point;
import java.awt.dnd.DropTargetContext;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.util.Vector;
import javax.swing.JTable;

import org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin.ListadoRecursosPatrimonialesPanel;
import org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin.RecursosPorPersona;
import org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin.RecursosVariosPanel;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.ExtendedInternalFrame;

public class DropTargetListenerGrillaRxP implements DropTargetListener {

    private RecursosPorPersona recursosPorPersona;
    private PersonalPanel personalPanel = new PersonalPanel();
    
    public DropTargetListenerGrillaRxP(PersonalPanel _datosPersonal,RecursosPorPersona _recursosPorPersona) {		
	recursosPorPersona = _recursosPorPersona;
	personalPanel = _datosPersonal;  
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
	System.out.println("haber si esta arrastrabdo recursos a personas: "+recursosPorPersona.getGrilla().getSelectedsVector());    
    }

}
