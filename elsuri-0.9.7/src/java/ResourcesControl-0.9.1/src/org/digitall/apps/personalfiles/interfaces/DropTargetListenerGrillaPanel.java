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
 * DropTargetListenerGrillaPanel.java
 *
 * */
package org.digitall.apps.personalfiles.interfaces;

import java.awt.Point;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetContext;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.util.Vector;
import javax.swing.JTable;

import org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin.ListadoRecursosPatrimonialesPanel;
import org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin.RecursosVariosPanel;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.grid.GridPanel;

public class DropTargetListenerGrillaPanel implements DropTargetListener {

    private RecursosVariosPanel recursosVariosPanel;
    private PersonalPanel personalPanel = new PersonalPanel();
    
    public DropTargetListenerGrillaPanel(RecursosVariosPanel _recursosVariosPanel, PersonalPanel _datosPersonal) {		
	recursosVariosPanel = _recursosVariosPanel;
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
	//Obtengo la tupla que señala el cursor
	int cantPersonasSeleccionadas = personalPanel.getGridPanelPersonal().getSelectedsID().size();
	if(cantPersonasSeleccionadas == 1){
	    int cantSeleccionados = recursosVariosPanel.getGrillaPanelRecursos().getSelectedsID().size();
	    if(cantSeleccionados == 1){
		int disponibles = Integer.parseInt(recursosVariosPanel.getGrillaPanelRecursos().getSelectedsValuesAt(4).elementAt(0).toString());
		if(disponibles > 0){
		    Point p = dtde.getLocation();  
		    DropTargetContext dtc = dtde.getDropTargetContext();
		    JTable tablaPersonal = (JTable)dtc.getComponent();
		    System.out.println("nombre de tabla arrastrada: " + tablaPersonal.getParent().getParent().getParent().getParent().getParent().getParent().toString());
		    int row = tablaPersonal.rowAtPoint(p);		
		    int rowSeleccionadaRecursosVarios = recursosVariosPanel.getGrillaPanelRecursos().getTable().getSelectedRow();	
		    //Obtener al recurso seleccionado a ser asignado
		    String isRecursoParaDependencia = recursosVariosPanel.getGrillaPanelRecursos().getValuesAt(5).get(rowSeleccionadaRecursosVarios)+"";		
		    System.out.println("id de recurso a ASIGNAR : "+ recursosVariosPanel.getGrillaPanelRecursos().getValuesAt(0).get(rowSeleccionadaRecursosVarios));
		    if (isRecursoParaDependencia.equals("p")||isRecursoParaDependencia.equals("t"))  {//Igual a NO
		    int rowPersonSelected = personalPanel.getGridPanelPersonal().getTable().getSelectedRow();
			//Obtener  a la persona seleccionada
			int idPersonAsignar = Integer.parseInt(personalPanel.getGridPanelPersonal().getSelectedsID().elementAt(0).toString());
			Vector vectorIDPersonal = personalPanel.getGridPanelPersonal().getValuesAt(0);   
			Vector vectorNombrePersonal = personalPanel.getGridPanelPersonal().getValuesAt(4);   
			int idPersonal = Integer.valueOf(vectorIDPersonal.get(row)+"");
			String nombrePersonal = vectorNombrePersonal.get(row).toString();
			
			/*AsignacionPatrimonioRecursoPanel asignacionPatrimonioRecursoPanel = new AsignacionPatrimonioRecursoPanel();
			asignacionPatrimonioRecursoPanel.setIdPerson(idPersonal);
			asignacionPatrimonioRecursoPanel.setNombrePersona(nombrePersonal);
			asignacionPatrimonioRecursoPanel.setRecursosVariosPanel(recursosVariosPanel);	    
			asignacionPatrimonioRecursoPanel.loadDataRecurso();*/
			
			ExtendedInternalFrame stockMgmtContainer = new ExtendedInternalFrame("Asignación de patrimonio");
			//stockMgmtContainer.setCentralPanel(asignacionPatrimonioRecursoPanel); 
			ListadoRecursosPatrimonialesPanel listadoRecursosPatrimoniales = new ListadoRecursosPatrimonialesPanel(true);
			stockMgmtContainer.setCentralPanel(listadoRecursosPatrimoniales);
			stockMgmtContainer.setIconifiable(false);
			listadoRecursosPatrimoniales.setRecursosVariosPanel(recursosVariosPanel);
			listadoRecursosPatrimoniales.setPersonalPanel(personalPanel);
			listadoRecursosPatrimoniales.setIdPerson(idPersonAsignar);
			listadoRecursosPatrimoniales.refresh();
			stockMgmtContainer.show();  	    
			
			//recursosVariosPanel.getListadoRecursosPatrimonialesPanel().getParentInternalFrame().close();
		    } else {
			Advisor.messageBox("¡El recurso que desea asignar es para una dependencia!", "Error");	
		    }
		}else{
		    Advisor.messageBox("El recurso no tienen unidades disponibles","Sin Disponibilidad");
		}
	    }else{
		    Advisor.messageBox("Debe seleccionar un unico recurso a asignar","Error");
		}
	}else{
	    Advisor.messageBox("Debe selecccionar una unica persona","Error");
	}
    }

}
