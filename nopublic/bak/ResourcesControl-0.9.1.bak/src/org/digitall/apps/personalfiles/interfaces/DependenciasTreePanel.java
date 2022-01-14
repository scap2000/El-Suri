package org.digitall.apps.personalfiles.interfaces;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JTree;
import javax.swing.text.JTextComponent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;
import org.digitall.apps.personalfiles.classes.Dependencia;
import org.digitall.apps.personalfiles.classes.VectorDependencia;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.sql.LibSQL;

public class DependenciasTreePanel extends BasicPrimitivePanel{

    private JTree dependenciasTree;
    private BasicScrollPane bspTree = new BasicScrollPane();
    private DependenciaCellRenderer depencenciaCellRenderer = new DependenciaCellRenderer();
    private VectorDependencia vectorDependencia;
    private VectorDependencia vectorDependenciaSeleccionado;
    private VectorDependencia dependenciasVector = new VectorDependencia();    
    private Vector <Dependencia> dependenciasVector_;
    private int tamVector;
    private BasicPanel content = new BasicPanel();
    private BasicPanel panelSur = new BasicPanel();
    private DefaultTreeModel model;
    private AcceptButton btnAcept = new AcceptButton();
    private Dependencia dependenciaSeleccionada;
    private JTextComponent textComponent;
    
    public DependenciasTreePanel(JTextComponent _textComponent) {
	try {
	    textComponent = _textComponent;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public DependenciasTreePanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    private void jbInit() throws Exception {
	content.setLayout(new BorderLayout());
	this.setSize(new Dimension(400, 471));
	cargarTree();
	dependenciasTree.expandPath(dependenciasTree.getPathForRow(0));
	dependenciasTree.setSelectionPath(dependenciasTree.getPathForRow(0));
	dependenciasTree.updateUI();
	panelSur.setLayout(new BorderLayout());
	panelSur.add(btnAcept, BorderLayout.EAST);
	btnAcept.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
				   btnAcept_actionPerformed(e);
			       }
			   }
	);
	this.add(content, BorderLayout.CENTER);
	this.add(panelSur, BorderLayout.SOUTH);
    }
    
    private void btnAcept_actionPerformed(ActionEvent e) {       
	acept();
    }
    
    private void acept(){
	dependenciaSeleccionada = vectorDependenciaSeleccionado.getDependencia();
	if(textComponent != null){
	    textComponent.setText(""+dependenciaSeleccionada.getNombreNivelJerarquico()+" "+dependenciaSeleccionada.getNombre());   
	}
	this.getParentInternalFrame().close();
    }

    private void cargarTree(){
	getAllDependencias();
	tamVector = dependenciasVector_.size();
	builtVectorHijosDeDependencias(dependenciasVector_.get(1));
	modelarTree(dependenciasVector, dependenciasVector_.get(1));
	dependenciasTree = new JTree(dependenciasVector);
	model = (DefaultTreeModel)dependenciasTree.getModel();
	
	dependenciasTree.setCellRenderer(depencenciaCellRenderer);       		
	dependenciasTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
	bspTree.getViewport().add(dependenciasTree);
	content.add(bspTree,BorderLayout.CENTER);
    }
    
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

    public Dependencia getDependenciaSeleccionada() {
	return dependenciaSeleccionada;
    }

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
	    //recursosPorDependencia.setDependencia(dependencia);
	    //Si la hoja que toma el evento esta seleccionado
	    if (selected) {
	      vectorDependenciaSeleccionado = (VectorDependencia)userObject;
	      //aBMDependenciasPanel.loadData(vectorDependencia);//*********//*********
	      //datosPersonal.setDependencia(dependencia);            
	      renderer = new DatosDependenciaPanel(dependencia); 
	      renderer.paintSelectedPanel();
	      //idDependenciaSelect = dependencia.getIdDep();          
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

}
