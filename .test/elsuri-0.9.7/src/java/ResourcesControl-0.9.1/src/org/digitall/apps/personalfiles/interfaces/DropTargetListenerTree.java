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
 * DropTargetListenerTree.java
 *
 * */
package org.digitall.apps.personalfiles.interfaces;

import java.awt.Color;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetContext;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

import java.io.IOException;

import java.util.Vector;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import org.digitall.apps.personalfiles.classes.Dependencia;
import org.digitall.apps.personalfiles.classes.VectorDependencia;
import org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin.AsignacionPatrimonioRecursoPanel;
import org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin.ListadoRecursosPatrimonialesPanel;
import org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin.Patrimonio;
import org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin.RecursosVariosPanel;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.sql.LibSQL;

public class DropTargetListenerTree implements DropTargetListener{
    private JTree dependenciaTree;// = new JTree(raiz);
    private Dependencia dependencia = null;
    private int idPersona = 0;
    private PersonalPanel personalPanel;
    private AsignacionPatrimonioRecursoPanel asignacionPatrimonioRecursoPanel;
    private RecursosVariosPanel recursosVariosPanel;
    private ListadoRecursosPatrimonialesPanel listadoRecursosPatrimonialesPanel;
    
    public DropTargetListenerTree(JTree _dependenciaTree, PersonalPanel _personalPanel, RecursosVariosPanel _recursosVariosPanel) {
	this.dependenciaTree = _dependenciaTree;	
	this.personalPanel = _personalPanel;
	this.recursosVariosPanel = _recursosVariosPanel;
    }

    /**
    * Este método se invoca cuando el ratón ha entrado en el destino de
    * soltar. Cambiamos el color de fondo de nuestra lista a verde
    */
    public void dragEnter(DropTargetDragEvent dtde) {
	this.dependenciaTree.setBackground( new Color(255, 243, 239));	
    }

    /**
    * Este método se invoca cuando el ratón se ha movido dentro del
    * destino de soltar. Cambiamos el color de fondo de nuestra lista a verde
    */
    public void dragOver(DropTargetDragEvent dtde) {
    
    }
    
    /**
    * Este método se invoca cuando el usuario ha cambiado el tipo de
    * accion (mover, copiar, enlazar), soltando o pulsando alguna de las
    * teclas SHIFT o CONTROL
    */
    public void dropActionChanged(DropTargetDragEvent dtde) {

    }
    
    /**
    * Este método se invoca cuando el ratón ha salido en el destino de
    * soltar. Cambiamos el color de fondo de nuestra lista a verde
    */
    public void dragExit(DropTargetEvent dte) {
	this.dependenciaTree.setBackground( Color.WHITE );
    }
    
    /**
    * Este método se invoca cuando el usuario ha soltado el boton del
    * raton dentro del destino de arrastre, es decir se ha completado la
    * accion de arrastre
    */
    public void drop(DropTargetDropEvent dtde) {
	System.out.println("solto en arbol......");
	int cantSeleccionados = recursosVariosPanel.getGrillaPanelRecursos().getSelectedsID().size();
	//if(cantSeleccionados == 1){
	    //int disponibles = Integer.parseInt(recursosVariosPanel.getGrillaPanelRecursos().getSelectedsValuesAt(4).elementAt(0).toString());
	    //if(disponibles > 0){
	    if(10 > 0){
		//Obtengo la dependencia    
		Point p = dtde.getLocation();  
		DropTargetContext dtc = dtde.getDropTargetContext();
		JTree tree = (JTree)dtc.getComponent();
		TreePath path = tree.getPathForLocation(p.x, p.y);	
		if (path != null) {
		    DefaultMutableTreeNode node = (DefaultMutableTreeNode)(TreeNode)path.getLastPathComponent();
		    VectorDependencia vectorDependencia = (VectorDependencia)node.getUserObject();
		    dependencia = vectorDependencia.getDependencia();       
		}
		
		//Obtengo el nodo que estoy por guardar
		//Se comprueba que el tipo de arrastre ocurrido es de los que acepta este objetivo
		if( ( dtde.getDropAction(  ) == DnDConstants.ACTION_MOVE ) ||
		  ( dtde.getDropAction(  ) == DnDConstants.ACTION_COPY ) ||
		  ( dtde.getDropAction(  ) == DnDConstants.ACTION_LINK ) )
		{
		  //Se acepta el soltar
		  dtde.acceptDrop( dtde.getDropAction(  ) );
		
		  //Se obtiene el Transferable que contiene el objeto arrastrado
		  Transferable transferable = dtde.getTransferable(  );
		
		  //Se comprueba si el tipo de datos que contiene el transferable se
		  //puede extraer como un tipo valido para nosotros, en este caso
		  //se trata de extraer un String
		  if( transferable.isDataFlavorSupported( DataFlavor.stringFlavor ) )
		  {	
		      //Desde aqui original
		      //Se obtiene el dato como String             
		      String texto = "";
			try {
			    texto = (String)transferable.getTransferData( 
			   DataFlavor.stringFlavor );
			} catch (UnsupportedFlavorException e) {
			    // TODO
			} catch (IOException e) {
			    // TODO
			}
			if (texto.equals("GridPanelPersonal"))  {
			    Patrimonio patrimonio = new Patrimonio();
			    if(patrimonio.getPersonasSinLiberacion(personalPanel.getGridPanelPersonal().getSelectedsID()).size() == 0){
				 Vector vectorID = personalPanel.getGridPanelPersonal().getSelectedsID();   
				 Vector vectorCargo = personalPanel.getGridPanelPersonal().getSelectedsValuesAt(7);   
				 int tamVec = vectorID.size();
				 for (int i = 0; i < tamVec; i++) { 
				     savePersonaDependencia(Integer.valueOf(vectorID.get(i)+""), Integer.valueOf(vectorCargo.get(i)+""));  
				 }
			    }else{
				PersonInError personInError = new PersonInError();
				personInError.setMensaje("Las siguientes personas no pueden ser trasladadas a otra dependencia hasta la liberación de los recursos correspondientes.\n\n Tramitar Liberación en Patrimonio.");
			        personInError.cargarListaPersonas(patrimonio.getPersonasSinLiberacion(personalPanel.getGridPanelPersonal().getSelectedsID()));
			        ExtendedInternalFrame errorContainer = new ExtendedInternalFrame("Error de movimiento");
			        errorContainer.setCentralPanel(personInError);   
			        errorContainer.show();             
			    }
			} else if (texto.equals("GridPanelPatrimonio")){
			    int rowSeleccionadaRecursosVarios = recursosVariosPanel.getGrillaPanelRecursos().getTable().getSelectedRow();   
			    String isRecursoParaDependencia = recursosVariosPanel.getGrillaPanelRecursos().getValuesAt(3).get(rowSeleccionadaRecursosVarios)+"";            
			    if (isRecursoParaDependencia.equals("d")||isRecursoParaDependencia.equals("t"))  {//Igual a NO
									     
			      AsignacionPatrimonioRecursoPanel asignacionPatrimonioRecursoPanel = new AsignacionPatrimonioRecursoPanel(false);
			      asignacionPatrimonioRecursoPanel.setRecursosVariosPanel(recursosVariosPanel);           
			      asignacionPatrimonioRecursoPanel.loadDataRecurso();
			      asignacionPatrimonioRecursoPanel.setIdDependencia(dependencia.getIdDep());
			      asignacionPatrimonioRecursoPanel.setNombreDependencia(dependencia.getNombre());
			      
			      ExtendedInternalFrame stockMgmtContainer = new ExtendedInternalFrame("Asignación de patrimonio");
			      stockMgmtContainer.setCentralPanel(asignacionPatrimonioRecursoPanel);   
			      stockMgmtContainer.show();              
			      
			      recursosVariosPanel.getListadoRecursosPatrimonialesPanel().getParentInternalFrame().close();
			  }else {
			      Advisor.messageBox("¡El recurso que desea asignar es para un personal!", "Error");      
			  }
		      }
					      
		      //Hasta aqui original
		  if ((dependencia != null) && !texto.equals("GridPanelPersonal")){
		     try
		     {
			 ExtendedInternalFrame stockMgmtContainer = new ExtendedInternalFrame("Asignacíon de patrimonio");
			 listadoRecursosPatrimonialesPanel = new ListadoRecursosPatrimonialesPanel(false);
			 stockMgmtContainer.setCentralPanel(listadoRecursosPatrimonialesPanel);
			 stockMgmtContainer.setIconifiable(false);
			 listadoRecursosPatrimonialesPanel.setRecursosVariosPanel(recursosVariosPanel);
			 listadoRecursosPatrimonialesPanel.setPersonalPanel(personalPanel);
			 listadoRecursosPatrimonialesPanel.setDependencia(dependencia);
			 listadoRecursosPatrimonialesPanel.refresh();
			 stockMgmtContainer.show(); 
		     }
		     catch( Exception excepcion )
		     {
			System.out.println( excepcion );
		     }
		  }
		  }
		
		  //Se informa de que la operacion se ha completado
		  dtde.dropComplete( true );
		  dtde.getDropTargetContext().getComponent(  )
			.setBackground( Color.WHITE );
		}
	    }else{
		Advisor.messageBox("El recurso no tienen unidades disponibles","Sin Disponibilidad");
	    }
	/*}else{
		Advisor.messageBox("Debe seleccionar un solo recurso a asignar","Error");
	    }*/
    }	

    private void savePersonaDependencia(int _idPersona, int _idCargo){
	String params = _idPersona +","+dependencia.getIdDep()+","+_idCargo;
	int resultado = LibSQL.getInt("personalFiles.setDependenciaPersona",params);
	if (resultado == -1)  {
	    Advisor.messageBox("Ocurrio un error al guardar los datos!", "Error");
	}else {
	    if (resultado == -2)  {
	        Advisor.messageBox("No se pueden agregar mas personas a esta dependencia.", "Cupo máximo alcanzado");
	    }else{
		personalPanel.refresh();        
	    }
	}    
    }
        
}
