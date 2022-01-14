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
 * DependenciaTree.java
 *
 * */
package org.digitall.apps.personalfiles.interfaces;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.sql.ResultSet;

import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.digitall.apps.personalfiles.classes.Dependencia;
import org.digitall.apps.personalfiles.classes.VectorDependencia;
import org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin.DragGestureListenerGridPanelRecursos;
import org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin.DragSourceListenerGridPanel;
import org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin.RecursosPorDependencia;
import org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin.RecursosPorPersona;
import org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin.RecursosVariosPanel;
import org.digitall.common.personalfiles.classes.Persona;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.BasicTabbedPane;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.org.Persons;
import org.digitall.lib.sql.LibSQL;

public class DependenciaTree extends BasicPrimitivePanel {

    private JTree dependenciaTree;
    
    private BasicPanel content = new BasicPanel();    
    private BasicScrollPane bspTree = new BasicScrollPane();
    private BasicPanel jpCentro = new BasicPanel();
    private BasicPanel jpSur = new BasicPanel();
    private BasicPanel jpEast = new BasicPanel();
    private RecursosVariosPanel recursosVarios = new RecursosVariosPanel();
    private RecursosPorPersona recursosPorPersona = new RecursosPorPersona();
    private RecursosPorDependencia recursosPorDependencia = new RecursosPorDependencia();
    
    private BorderLayout borderLayout1 = new BorderLayout();
    private BorderLayout borderLayout3 = new BorderLayout();  
    
    private DependenciaCellRenderer depencenciaCellRenderer = new DependenciaCellRenderer();
    private VectorDependencia dependenciasVector = new VectorDependencia();    
    private Vector <Dependencia> dependenciasVector_;
    private int tamVector;
    private int idDependenciaSelect = -1;
    private BorderLayout borderLayout2 = new BorderLayout();
    private ABMDependenciasPanel aBMDependenciasPanel =new ABMDependenciasPanel(this);
    private PersonalPanel datosPersonal = new PersonalPanel();
    private ArbolPanel datosArbol = new ArbolPanel();
    private DefaultTreeModel model;
    private CloseButton btnClose = new CloseButton();       
    private VectorDependencia vectorDependencia;
    private VectorDependencia vectorDependenciaSeleccionado;
    private MouseListenerTree mouseListenerTree = new MouseListenerTree();
    private KeyListenerTree keyListenerTree = new KeyListenerTree();    
    private SelectionListenerTree selectionListenerTree = new SelectionListenerTree();
    private MisionesYFuncionesPanel misionesYFuncionesPanel = new MisionesYFuncionesPanel();
    private BasicTabbedPane tabbedPanel = new BasicTabbedPane();    
    private BasicTabbedPane tabbedPaneAsignacion = new BasicTabbedPane();    
    private Dependencia dependencia;
    private Persona persona;
    private PrintButton btnPrintRxP = new PrintButton();
    private PrintButton btnPrintRxD = new PrintButton();
    private PrintButton btnPrintInvGral = new PrintButton();
    private PrintButton btnPrintInvGralValorado = new PrintButton();
    private PrintButton btnPrintBajas = new PrintButton();
    private PrintButton btnPrintPersonDependency = new PrintButton();
    private AddButton btnPrintNota = new AddButton();
    
    public DependenciaTree() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(810, 600));
	this.setPreferredSize(new Dimension(810, 600)); 	
	
        content.setPreferredSize(new Dimension(810, 600));
	content.setSize(new Dimension(810, 600));
	content.setLayout(borderLayout1);
	content.setBorder(BorderPanel.getBorderPanel("Dependencias"));		
	content.add(jpCentro, BorderLayout.CENTER);
        
	tabbedPanel.setPreferredSize(new Dimension(794, 200));
	tabbedPanel.setSize(new Dimension(794, 200));		
	tabbedPanel.addTab("Organigrama", aBMDependenciasPanel);		
	tabbedPanel.addTab("Misiones y Funciones", misionesYFuncionesPanel);
	tabbedPanel.addTab("Personal", datosPersonal);
	//tabbedPanel.addTab("Arbol", datosArbol);
        
	aBMDependenciasPanel.setSize(790, 174);
	aBMDependenciasPanel.setPreferredSize(new Dimension(790, 174));
	aBMDependenciasPanel.setSize(new Dimension(790, 174));
	datosPersonal.setPreferredSize(new Dimension(790, 174));        
	datosPersonal.setSize(790, 174);
	datosArbol.setPreferredSize(new Dimension(790, 174));	
	datosPersonal.setSize(790, 174);
	jpSur.add(tabbedPanel, BorderLayout.CENTER);
	jpSur.setPreferredSize(new Dimension(800, 200));
	jpSur.setLayout(borderLayout2);
	jpSur.setSize(new Dimension(800, 200));   
	content.add(jpSur, BorderLayout.SOUTH);
        
	/*recursosVarios.setPreferredSize(new Dimension(300, 336));
	recursosVarios.setSize(new Dimension(300, 336)); 
	content.add(recursosVarios, BorderLayout.EAST);*/
        
	tabbedPaneAsignacion.setPreferredSize(new Dimension(300, 329));
	tabbedPaneAsignacion.setSize(new Dimension(300, 329));
	tabbedPaneAsignacion.addTab("General", recursosVarios);         
	tabbedPaneAsignacion.addTab("Por Persona", recursosPorPersona);
	tabbedPaneAsignacion.addTab("Por Dependencia", recursosPorDependencia);
	
        jpEast.add(tabbedPaneAsignacion, BorderLayout.CENTER);
        jpEast.setPreferredSize(new Dimension(300, 329));
        jpEast.setSize(new Dimension(300, 329));

	content.add(jpEast, BorderLayout.EAST);
	
	//Redefinimos el renderer(manejador de datos) que posee el arbol	
	getAllDependencias();
	tamVector = dependenciasVector_.size();
	builtVectorHijosDeDependencias(dependenciasVector_.get(1));
	modelarTree(dependenciasVector, dependenciasVector_.get(1));
	dependenciaTree = new JTree(dependenciasVector);
	model = (DefaultTreeModel)dependenciaTree.getModel();
		
		
	datosArbol.setArbol(dependenciaTree);	
	btnPrintRxP.setToolTipText("Imprimir Informes");	
	btnPrintRxD.setToolTipText("Imprimir Recursos de la Dependencia");   
	btnPrintInvGral.setToolTipText("Imprimir Inventario Gral.");   
	btnPrintInvGralValorado.setToolTipText("Imprimir Inventario Gral. Valorado");   
	btnPrintBajas.setToolTipText("Imprimir Bajas de Recursos");   
	btnPrintPersonDependency.setToolTipText("Imprimir Personas por Dependencias");   
	btnPrintNota.setToolTipText("Generar Nueva Nota para Intendente");   
	
	dependenciaTree.setCellRenderer(depencenciaCellRenderer);	
	dependenciaTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
	
	jpCentro.setLayout(borderLayout3);
	bspTree.getViewport().add(dependenciaTree);
	jpCentro.add(bspTree, BorderLayout.CENTER);	
	this.add(content, BorderLayout.CENTER);
	addButton(btnClose);
	addButton(btnPrintNota);
	addButton(btnPrintRxP);
	/*addButton(btnPrintRxD);
	addButton(btnPrintInvGral);
	addButton(btnPrintInvGralValorado);
	addButton(btnPrintBajas);
	addButton(btnPrintPersonDependency);*/
	btnClose.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
					    btnClose_actionPerformed(e);
					}

				    }
	);
	/*btnPrintRxP.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
					    btnPrintRxP_actionPerformed(e);
					}

				    }
	);*/
	 btnPrintRxP.addMouseListener(new MouseAdapter() {
	     public void mouseClicked(MouseEvent e) {
	         if (e.getClickCount() == 1 && e.getButton() == e.BUTTON3) {
	             clickPrint(e);              
	         }
	     }
	 });
	btnPrintRxD.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					    btnPrintRxD_actionPerformed(e);
					}
				    }
	);
	
	btnPrintNota.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					    btnPrintNota_actionPerformed(e);
					}
				    }
	);
	btnPrintInvGral.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					    btnPrintInvGral_actionPerformed(e);
					}
				    }
	);
	btnPrintInvGralValorado.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					    btnPrintInvGralValorado_actionPerformed(e);
					}
				    }
	);
	
	btnPrintBajas.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					    btnPrintBajas_actionPerformed(e);
					}
				    }
	);
	btnPrintPersonDependency.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					    btnPrintPersonDependency_actionPerformed(e);
					}
				    }
	);
		
	//Drag and drop definiciones de propiedades de los componentes
	
	 //Target tree
	 
	//Creamos un listener al que se le informará de como va evolucionando el proceso de arrastre
	DropTargetListenerTree dropTargetListenerTree =	new DropTargetListenerTree( dependenciaTree, datosPersonal, recursosVarios);
	//Se crea el el objetivo del arrastre y se configura
	DropTarget dropTarget =	new DropTarget( dependenciaTree,dropTargetListenerTree );
	//Se le dice que es un destino activo de arrastre (que puede recibir objetos)
	dropTarget.setActive( true );
	//Se establecen los tipos de arrastre que admite
	dropTarget.setDefaultActions(DnDConstants.ACTION_COPY | DnDConstants.ACTION_MOVE | DnDConstants.ACTION_LINK );
	
	//Target GridPanel
	
	//Creamos un listener al que se le informará de como va evolucionando el proceso de arrastre
	DropTargetListenerGrillaPanel dropTargetListenerGrillaPanel = new DropTargetListenerGrillaPanel(recursosVarios, datosPersonal);
	//Se crea el el objetivo del arrastre y se configura
	DropTarget dropTargetRecurso = new DropTarget( datosPersonal.getGrilla(),dropTargetListenerGrillaPanel );
	//Se le dice que es un destino activo de arrastre (que puede recibir objetos)
	dropTargetRecurso.setActive( true );
	//Se establecen los tipos de arrastre que admite
	dropTargetRecurso.setDefaultActions(DnDConstants.ACTION_COPY | DnDConstants.ACTION_MOVE | DnDConstants.ACTION_LINK );
	
	
	//Source GridPanel Personal
	
	//Creamos nuestro listener para conocer que  esta teniendo
	//lugar durante el proceso de arrastre
	DragSourceListenerGridPanel dragSourceGridPanelListener = new DragSourceListenerGridPanel();
	//Obtenemos una fuente de arrastre: lo habitual es obtener la asociada por defecto a la plataforma.
	DragSource dragSource = DragSource.getDefaultDragSource(  );	
	//Creamos un listener de "gestos" de arrastre, para ser informados
	//de cuando, la actividad del usuario se interpreta como un intento de arrastre
	DragGestureListenerGridPanelPersonal dragGestureGridPanelListener = new DragGestureListenerGridPanelPersonal( datosPersonal.getGrilla(),dragSourceGridPanelListener, datosPersonal.getGridPanelPersonal());
	//Se anhade dicho listener de "gestos" a la fuente de arrastre
	dragSource.createDefaultDragGestureRecognizer(datosPersonal.getGrilla(),
	//Aqui indicamos los tipos de acciones posibles que queremos: descritas en la clase DndConstants
	DnDConstants.ACTION_COPY | DnDConstants.ACTION_MOVE | DnDConstants.ACTION_LINK,	dragGestureGridPanelListener );	
	
	//Source GridPanel Recursos
	
	 //Obtenemos una fuente de arrastre: lo habital es obtener la asociada por defecto a la plataforma.
	DragSource dragSourceRecursos = DragSource.getDefaultDragSource(  );
	//Creamos un listener de "gestos" de arrastre, para ser informados
	//de cuando, la actividad del usuario se interpreta como un intento de arrastre
	DragGestureListenerGridPanelRecursos dragGestureListenerGridPanelRecursos = new DragGestureListenerGridPanelRecursos( recursosVarios.getGrilla(),dragSourceGridPanelListener);
	//Se anhade dicho listener de "gestos" a la fuente de arrastre
	dragSourceRecursos.createDefaultDragGestureRecognizer(
	recursosVarios.getGrilla(),
	//Aqui indicamos los tipos de acciones posibles que queremos: descritas en la clase DndConstants
	DnDConstants.ACTION_COPY | DnDConstants.ACTION_MOVE | DnDConstants.ACTION_LINK,
	dragGestureListenerGridPanelRecursos ); 
	//
	//recursosPorPersona.getGrilla().getTable().setDragEnabled(true);
	//
	
	//Source GridPanel Patrimonio
	
	 //Obtenemos una fuente de arrastre: lo habital es obtener la asociada por defecto a la plataforma.
	DragSource dragSourcePatrimonio = DragSource.getDefaultDragSource(  );	    
	//Creamos un listener de "gestos" de arrastre, para ser informados
	//Creamos un listener de "gestos" de arrastre, para ser informados
	//de cuando, la actividad del usuario se interpreta como un intento de arrastre
	DragGestureListenerGridPanelPatrimonio dragGestureListenerGridPanelPatrimonio = new DragGestureListenerGridPanelPatrimonio(recursosVarios.getGrillaPanelPatrmonio().getTable(), dragSourceGridPanelListener);
	//Se anhade dicho listener de "gestos" a la fuente de arrastre
	//Aqui indicamos los tipos de acciones posibles que queremos: descritas en la clase DndConstants
	dragSourcePatrimonio.createDefaultDragGestureRecognizer(recursosVarios.getGrillaPanelPatrmonio().getTable(),
	//Aqui indicamos los tipos de acciones posibles que queremos: descritas en la clase DndConstants
	DnDConstants.ACTION_COPY | DnDConstants.ACTION_MOVE | DnDConstants.ACTION_LINK,	dragGestureListenerGridPanelPatrimonio ); 
	
	dependenciaTree.setSelectionPath(dependenciaTree.getPathForRow(0));//Siempre debe estar un nodo del arbol seleccionado
	dependenciaTree.addMouseListener(mouseListenerTree);	
	dependenciaTree.addKeyListener(keyListenerTree);
        datosPersonal.setParentMain(this);
    }
    
    private void clickPrint(MouseEvent e){
	PopupPrinter popupPrint = new  PopupPrinter();
	int x = (int)e.getPoint().getX();
	int y = (int)e.getPoint().getY() - popupPrint.getAlto();
	popupPrint.setIDsPersonSelected(datosPersonal.getgrillaPersonal().getSelectedsID());
	popupPrint.setVectorDependenciaSeleccionado(vectorDependenciaSeleccionado);
	popupPrint.show(btnPrintRxP,x,y);
    }
    
    //Construyo un vector dependencia que contiene todas las dependencia que existen en 
    //la institucion
    public void getAllDependencias(){
	dependenciasVector_ =  new Vector <Dependencia> ();
	ResultSet data = LibSQL.exFunction("personalfiles.getAllDependencias", "");
	try {
	    while (data.next()) {
		Dependencia dependencia = new Dependencia();
	        dependencia.setIdDep(data.getInt("iddep"));
		dependencia.setNivelJerarquico(data.getInt("niveljerarquico"));
	        dependencia.setNombre(data.getString("nombre"));
		dependencia.setPadre(data.getInt("padre"));	
	        dependencia.setNombreNivelJerarquico(data.getString("nombreniveljerarquico"));
	        dependencia.setMisiones(data.getString("misiones"));
	        dependencia.setFunciones(data.getString("funciones"));
		dependenciasVector_.add(dependencia);
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
      
    //Construimos un vector con el cual se construira el arbol y por otro lado a los 
    //objetos dependencia le asignamos los nodos hijos
    public void builtVectorHijosDeDependencias(Dependencia dependencia){                	    
	//Iniciamos en 2 por que en la posicion 1 del vector se encuentra la dependencia 'semilla' 
	for (int l = 2; l < tamVector; l++){
	    if (dependenciasVector_.elementAt(l).getPadre() == dependencia.getIdDep()){
		dependencia.addDependenciaHijo(dependenciasVector_.elementAt(l));
	        dependenciasVector_.elementAt(l).setDependenciaPadre(dependencia);	        
	        builtVectorHijosDeDependencias(dependenciasVector_.elementAt(l));		
	    }
	}
    }
    
   public void modelarTree(VectorDependencia dependenciasVector, Dependencia dependencia) {
	VectorDependencia dependenciaHijoVector = new VectorDependencia();      
	    dependenciaHijoVector.setDependencia(dependencia);
	    dependenciasVector.add(dependenciaHijoVector);	    
	    Vector<Dependencia> dependenciaHijosVector = dependencia.getDependenciaHijosVector();
	    int tamVec = dependenciaHijosVector.size();
	    for (int i = 0; i < tamVec; i++) {                          
		modelarTree(dependenciaHijoVector, dependenciaHijosVector.elementAt(i));
	    }
    }

    private void dependenciaTree_valueChanged(TreeSelectionEvent e) {
    }

    public void setRecursosPorPersona(RecursosPorPersona recursosPorPersona) {
	this.recursosPorPersona = recursosPorPersona;
    }

    public RecursosPorPersona getRecursosPorPersona() {
	return recursosPorPersona;
    }

    public void setRecursosPorDependencia(RecursosPorDependencia recursosPorDependencia) {
	this.recursosPorDependencia = recursosPorDependencia;
    }

    public RecursosPorDependencia getRecursosPorDependencia() {
	return recursosPorDependencia;
    }

    //REDEFINIMOS EL RENDERER DEL ARBOL PARA PERSONALIZARLO A NUESTRA NECESIDAD
    class DependenciaCellRenderer implements TreeCellRenderer {      
      DatosDependenciaPanel renderer;      
      DefaultTreeCellRenderer defaultRenderer = new DefaultTreeCellRenderer();
      
      public DependenciaCellRenderer() {		
      }

      public Component getTreeCellRendererComponent(JTree tree, Object value,
	  boolean selected, boolean expanded, boolean leaf, int row,
	  boolean hasFocus) {
	Component returnValue = null;	
	if ((value != null) && (value instanceof DefaultMutableTreeNode)) {
	  Object userObject = ((DefaultMutableTreeNode) value).getUserObject();
	  if (userObject instanceof VectorDependencia) {
	    //Obtengo la dependencia que fue seleccionada
	    vectorDependencia = (VectorDependencia)userObject;
	    Dependencia dependencia = vectorDependencia.getDependencia();		    	    		
	    /* Parche, la siguiente linea se comentó porque se llamaba muchas veces al método setDependencia y daba problemas */
	    //recursosPorDependencia.setDependencia(dependencia);
	    //Si la hoja que toma el evento esta seleccionado
	    if (selected) {
	      vectorDependenciaSeleccionado = (VectorDependencia)userObject;
	      //aBMDependenciasPanel.loadData(vectorDependencia);//*********//*********
	      //datosPersonal.setDependencia(dependencia);	      
	      renderer = new DatosDependenciaPanel(dependencia); 
	      renderer.paintSelectedPanel();
	      idDependenciaSelect = dependencia.getIdDep();	     
	    } else {
	      renderer = new DatosDependenciaPanel(dependencia); 	      
	    }	    	    
	    renderer.setPreferredSize();     
	    renderer.loaddata();        	    
	    renderer.setEnabled(tree.isEnabled());
	    returnValue = renderer;      	    
	  }
	  
	}
	if (returnValue == null) {
	  returnValue = defaultRenderer.getTreeCellRendererComponent(tree,
	      value, selected, expanded, leaf, row, hasFocus);
	}
	return returnValue;
      }
    }
     
    public JTree getTree() {
	return dependenciaTree;
    }
    
    public void addNodoHoja(Object child) {	
	//TreePath parentPath = dependenciaTree.getSelectionPath();
	//DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
	DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) dependenciaTree.getLastSelectedPathComponent();
	DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);	
	model.insertNodeInto(childNode, parentNode, 0);	
	// Make sure the user can see the lovely new node.			
	dependenciaTree.expandPath(new TreePath(parentNode.getPath()));
	dependenciaTree.scrollPathToVisible(new TreePath(childNode.getPath()));		
    }
    
    public void deleteNodoHoja() {     
	//Obtengo el nodo seleccionado
	DefaultMutableTreeNode node = (DefaultMutableTreeNode) dependenciaTree.getLastSelectedPathComponent();	
	
	if (node.getChildCount() == 0) {
	    //Obtengo el padre del nodo seleccionado y elimino de su vector el 
	    //nodo que se elimino	     
	    VectorDependencia vectorDependencia = (VectorDependencia)node.getUserObject();
	    if (LibSQL.getInt("personalfiles.delDependencia", "" + vectorDependencia.getDependencia().getIdDep()) != 0) {                                                              
	        JOptionPane.showMessageDialog(null,
	        "Ocurrio un error al eliminar la dependencia",
	        "Error",
	        JOptionPane.ERROR_MESSAGE);
	    }
	    Dependencia dependenciaPadre = vectorDependencia.getDependencia().getDependenciaPadre();
	    dependenciaPadre.delDependenciaHijo(vectorDependencia.getDependencia());
	    model.removeNodeFromParent(node);       
	} else {	    
	    JOptionPane.showMessageDialog(null,
	    "No se puede eliminar esta dependencia ya que contiene subdependencia(s)",
	    "Error",
	    JOptionPane.ERROR_MESSAGE);
	}	
    }
       
    private void jButton1_actionPerformed(ActionEvent e) {
	dependenciasVector.clear();
	modelarTree(dependenciasVector, dependenciasVector_.get(1));	
	dependenciaTree.updateUI();
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().setIcon(true);
    }
    
    private void btnPrintRxP_actionPerformed(ActionEvent e) {
	/*int seleccionados = datosPersonal.getgrillaPersonal().getSelectedsID().size();
	String empleado = "";
	String dependencia = "";
	String responsable = "";
	int idPersona = -1;
	if(seleccionados == 1){
	    Vector parametros = getParametrosRxP();
	    idPersona = Integer.parseInt(parametros.elementAt(0).toString());
	    empleado = parametros.elementAt(1).toString();
	    dependencia = parametros.elementAt(2).toString();
	    responsable = parametros.elementAt(3).toString();
	    
	    BasicReport report = new BasicReport(DependenciaTree.class.getResource("xml/ResourcesPerson.xml"));
	    report.setProperty("persona",empleado);
	    report.setProperty("dependencia",dependencia);
	    report.setProperty("responsable",responsable);
	    String params = "" + idPersona;
	    report.addTableModel("resourcescontrol.xmlgetresourcesperson",params);
	    report.doReport();
	}else{
	    Advisor.messageBox("Debe seleccionar una persona","Error !");
	}*/
    }
    
    private void btnPrintRxD_actionPerformed(ActionEvent e) {
	/*int seleccionada =  vectorDependenciaSeleccionado.getDependencia().getIdDep();
	String dependencia = "";
	String responsable = "";
	int idDependencia = -1;
	if(seleccionada != -1){	
	    Vector parametros = getParametrosRxD();
	    idDependencia = Integer.parseInt(parametros.elementAt(0).toString());
	    dependencia = parametros.elementAt(1).toString();
	    responsable = parametros.elementAt(2).toString();
	    BasicReport report = new BasicReport(DependenciaTree.class.getResource("xml/ResourcesDependency.xml"));
	    report.setProperty("dependencia",dependencia);
	    report.setProperty("responsable",responsable);
	    String params = "" + idDependencia;
	    report.addTableModel("resourcescontrol.xmlgetresourcesdependency",params);
	    report.doReport();
	}else{
	    Advisor.messageBox("Debe seleccionar una dependencia","Error !");
	}*/
    }
    
    private void btnPrintInvGral_actionPerformed(ActionEvent e) {			
	    /*BasicReport report = new BasicReport(DependenciaTree.class.getResource("xml/InformeGral.xml"));
	    report.setProperty("anio",Environment.currentYear);
	    String params = "";
	    report.addTableModel("resourcescontrol.xmlgetinventariogral",params);
	    report.doReport();*/
    }
    
    private void btnPrintInvGralValorado_actionPerformed(ActionEvent e) {
	    /*BasicReport report = new BasicReport(DependenciaTree.class.getResource("xml/InformeGralValorado.xml"));
	    report.setProperty("anio",Environment.currentYear);
	    String params = "";
	    report.addTableModel("resourcescontrol.xmlgetinventariogralvalorado",params);
	    report.doReport();*/
    }
    
    private void btnPrintBajas_actionPerformed(ActionEvent e) {
	    /*InformeBajasPanel infoBajaPanel = new InformeBajasPanel();
	    ExtendedInternalFrame bajasIntFrame = new ExtendedInternalFrame("Informe de Bajas");
	    bajasIntFrame.setCentralPanel(infoBajaPanel);
	    bajasIntFrame.show();            */
    }
    
    private void btnPrintPersonDependency_actionPerformed(ActionEvent e) {
	/*BasicReport report = new BasicReport(DependenciaTree.class.getResource("xml/InformePersonasDependencia.xml"));
	report.setProperty("anio",Environment.currentYear);
	String params = "";
	report.addTableModel("resourcescontrol.xmlgetpersonofdependency",params);
	report.doReport();*/
    }
    
    private void btnPrintNota_actionPerformed(ActionEvent e) {
	NotaInformePanel notaPanel = new NotaInformePanel();
	ExtendedInternalFrame notaIntFrame = new ExtendedInternalFrame("Nota mensual - Intendente");
	notaIntFrame.setCentralPanel(notaPanel);
	notaIntFrame.show();            
    }
    
    /*private Vector getParametrosRxP(){
	Vector resultado = new Vector();
	int idPerson = Integer.parseInt(datosPersonal.getgrillaPersonal().getSelectedsID().elementAt(0).toString());
	Persona persona = new Persona();
	persona.setIdPerson(idPerson);
	persona.retrieveData();
	String empleado = "" + persona.getLastName() + ", "+ persona.getFirstName();
	resultado.add("" + idPerson);
	resultado.add("" + empleado);
	ResultSet result = LibSQL.exFunction("personalfiles.getdependenciaofemployer",""+idPerson);
	try {
	    result.next();
	    String dependencia = "" + result.getString("niveljer") + ", " +result.getString("nombre");
	    String responsable = "" + "YO";
	    resultado.add("" + dependencia);
	    resultado.add("" + responsable);
	} catch (SQLException f) {
	    // TODO
	}
	return(resultado);
    }*/
    
    /*private Vector getParametrosRxD(){
	Vector resultado = new Vector();
	Dependencia dependencia  = vectorDependenciaSeleccionado.getDependencia();
	int idDependencia = dependencia.getIdDep();
        String nombreDependencia  = "" + dependencia.getNombreNivelJerarquico() + ", " +dependencia.getNombre();
        String responsable = "" + "YO";
	resultado.add("" + idDependencia);
	resultado.add("" + nombreDependencia);
	resultado.add("" + responsable);
	return(resultado);
    }*/
    
    public class MouseListenerTree implements MouseListener {

	public void mouseClicked(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {
            if (vectorDependenciaSeleccionado != null) {
                aBMDependenciasPanel.loadData(vectorDependenciaSeleccionado);
                datosPersonal.setDependencia(vectorDependenciaSeleccionado.getDependencia(),vectorDependenciaSeleccionado);            
                misionesYFuncionesPanel.setDependencia(vectorDependenciaSeleccionado.getDependencia());
		recursosPorDependencia.setDependencia(vectorDependenciaSeleccionado.getDependencia());
		recursosPorDependencia.refresh();
		tabbedPaneAsignacion.setSelectedComponent(recursosPorDependencia);
            }
	}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

    }

    public class KeyListenerTree implements KeyListener {

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {

	}

	public void keyReleased(KeyEvent e) {
	    if(e.getKeyCode() == KeyEvent.VK_ENTER){
		if(vectorDependenciaSeleccionado.getDependencia() != null){
			aBMDependenciasPanel.loadData(vectorDependenciaSeleccionado);
			datosPersonal.setDependencia(vectorDependenciaSeleccionado.getDependencia(),vectorDependencia);            
			misionesYFuncionesPanel.setDependencia(vectorDependenciaSeleccionado.getDependencia()); 
			recursosPorDependencia.setDependencia(vectorDependenciaSeleccionado.getDependencia());
			recursosPorDependencia.refresh();
			tabbedPaneAsignacion.setSelectedComponent(recursosPorDependencia);
		}
	    }
	}

    }
     //Para la seleccion
    public class SelectionListenerTree implements TreeSelectionListener{

	public void valueChanged(TreeSelectionEvent e) {
	    if(vectorDependenciaSeleccionado.getDependencia() != null){
	        aBMDependenciasPanel.loadData(vectorDependenciaSeleccionado);
	        datosPersonal.setDependencia(vectorDependenciaSeleccionado.getDependencia(),vectorDependencia);            
	        misionesYFuncionesPanel.setDependencia(vectorDependenciaSeleccionado.getDependencia()); 
	        recursosPorDependencia.setDependencia(vectorDependenciaSeleccionado.getDependencia());
	        recursosPorDependencia.refresh();
	        tabbedPaneAsignacion.setSelectedComponent(recursosPorDependencia);
	    }
	}
    }

    public void setPersona(Persons _persona){
        recursosPorPersona.setPerson(_persona);
    }
    
    public void setPersona(Persona _persona){
	persona = _persona;
	recursosPorPersona.setPersona(persona);
    }
    
    public void setDependencia(Dependencia _dependencia){
	dependencia = _dependencia;
	recursosPorDependencia.setDependencia(dependencia);
    }
    
    public void upDateRecursosPorDependencia(){
	recursosPorDependencia.refresh();
	tabbedPaneAsignacion.setSelectedComponent(recursosPorDependencia);
    }
    
    public void upDateRecursosPorPersona(){
	recursosPorPersona.refresh();
	tabbedPaneAsignacion.setSelectedComponent(recursosPorPersona);
    }
    
}

