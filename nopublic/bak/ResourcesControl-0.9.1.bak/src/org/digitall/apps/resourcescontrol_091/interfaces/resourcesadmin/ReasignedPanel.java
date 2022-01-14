package org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.ButtonGroup;
import org.digitall.apps.personalfiles.classes.Dependencia;
import org.digitall.common.personalfiles.classes.Persona;
import org.digitall.apps.personalfiles.interfaces.DependenciasTreePanel;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;

public class ReasignedPanel extends BasicPrimitivePanel{
    private BasicPanel content = new BasicPanel();
    private Resource recurso;   
    private Persona persona;
    private Dependencia dependencia;
    private Dependencia dependenciaToReasigned;
    private RecursosAsignadosPanel recursosAsignadosPanel;
    private boolean isPersonAsigned;
    private TFInput TFDateAsigned = new TFInput(DataTypes.DATE,"Fecha de Reasignación",true);
    private SaveDataButton btnSave = new SaveDataButton();    
    private CloseButton btnClose = new CloseButton();
    private BasicPanel panelSur = new BasicPanel();
    private BasicRadioButton rbPersona = new BasicRadioButton("a Persona");
    private BasicRadioButton rbDependencia = new BasicRadioButton("a Dependencia");
    private ButtonGroup grupo = new ButtonGroup();
    private CBInput cbPersonas = new CBInput(0,"Persons");
    private TFInput TFDependencia = new TFInput(DataTypes.STRING,"Dependencias",true);
    private String defaultValue = "click aqui para seleccionar una dependencia...";
    private DependenciasTreePanel tree;
    private boolean isPersonReasigned;
    private ExtendedInternalFrame treeDep;
    private Patrimonio patrimonio = new Patrimonio();
    private Persona personToReasigned;
    private BorderLayout borderLayout1 =  new BorderLayout();

    public ReasignedPanel(Resource _recurso,Persona _persona,Dependencia _dependencia) {
	try {
	    recurso = _recurso;
	    persona = _persona;
	    dependencia = _dependencia;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    private void jbInit() throws Exception {
	this.setSize(new Dimension(500, 518));
	content.setLayout(borderLayout1);
	TFDateAsigned.setBounds(new Rectangle(10, 75, 145, 35));
	setPersonAsigned();
	recursosAsignadosPanel = new RecursosAsignadosPanel(recurso,persona,dependencia,isPersonAsigned,false);
	recursosAsignadosPanel.refresh();
	content.add(recursosAsignadosPanel,BorderLayout.CENTER);
	btnSave.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
				   btnSave_actionPerformed(e);
			       }
			   }
	);
	btnClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }   
	);
	panelSur.setBounds(new Rectangle(0, 0, 480, 115));
	panelSur.setPreferredSize(new Dimension(480, 115));
	panelSur.setLayout(null);
	rbPersona.setBounds(new Rectangle(5, 12, 140, 20));
	rbDependencia.setBounds(new Rectangle(5, 47, 140, 20));
	rbDependencia.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    rbDependencia_actionPerformed(e);
		}
	    }
	);
	cbPersonas.setBounds(new Rectangle(155, 5, 335, 35));
	TFDependencia.setBounds(new Rectangle(155, 40, 335, 35));
	TFDependencia.setEditable(false);
	TFDependencia.setValue(defaultValue);
	TFDependencia.getTextField().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		click();
	    }
	});
	TFDateAsigned.setValue(Proced.setFormatDate(Environment.currentDate,true));
	cbPersonas.autoSize();
	loadComboPersonas();
	grupo.add(rbPersona);
	grupo.add(rbDependencia);
	rbPersona.setSelected(true);
	isPersonReasigned = true;
	rbPersona.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    rbPersona_actionPerformed(e);
		}
	    }
	);
	content.add(panelSur,BorderLayout.SOUTH);
	panelSur.add(TFDependencia, null);
	panelSur.add(cbPersonas, null);
	panelSur.add(rbPersona, null);
	panelSur.add(rbDependencia, null);
	panelSur.add(TFDateAsigned,null);
	this.addButton(btnClose);
	this.addButton(btnSave);
	this.add(content, null);
	panelSur.setBorder(BorderPanel.getBorderPanel(""));
    }
    
    private void click(){
	if(!isPersonReasigned){
	    TFDependencia.setValue("");
	    tree = new DependenciasTreePanel(TFDependencia.getTextField());
	    treeDep = new ExtendedInternalFrame("Selección de dependencia");
	    treeDep.setCentralPanel(tree);
	    treeDep.show();      
	}
    }
    
    private void loadComboPersonas(){
	cbPersonas.loadJCombo("personalfiles.getAllPersonal","true");
    }
    
    private void btnSave_actionPerformed(ActionEvent e) {       
	ReasignarRecursos();
    }
	
    private void ReasignarRecursos(){
	if(recursosAsignadosPanel.getCounSelected() > 0){
	    if(Advisor.question("Confirmación","¿Desea realizar la operación?")){
		patrimonio.setDateAsigned(Proced.setFormatDate(TFDateAsigned.getDate(), false));
		Vector recursosDistSelectedToLiberty = new Vector();
		recursosDistSelectedToLiberty = recursosAsignadosPanel.getSelectedsID();
		patrimonio.setRecursosDistSelectedToLiberty(recursosDistSelectedToLiberty);
		if(isPersonAsigned){
		    patrimonio.setPersona(persona);
		    if(isPersonReasigned){
			personToReasigned = new Persona();
			int idPerson = Integer.parseInt(cbPersonas.getSelectedValue().toString());
			personToReasigned.setIdPerson(idPerson);
			personToReasigned.retrieveData();
			patrimonio.setPersonToReasigned(personToReasigned);
			if(patrimonio.reasignarRecursosPersonToPerson()){
			    Advisor.messageBox("Se grabó la reasignación con exito.","Aviso");
			    cerrar();
			}else{
			    Advisor.messageBox("Se produjo un error al grabar los datos.","Error");                    
			}
		    }else{
			if(!(TFDependencia.getValue().toString().equals("")||TFDependencia.getValue().toString().equals(defaultValue))){
			    dependenciaToReasigned = tree.getDependenciaSeleccionada();
			    patrimonio.setDependenciaToReasigned(dependenciaToReasigned);
			    if(patrimonio.reasignarRecursosPersonToDep()){
				Advisor.messageBox("Se grabó la reasignación con exito.","Aviso");                
			        cerrar();
			    }else{
				Advisor.messageBox("Se produjo un error al grabar los datos.","Error");                    
			    }
			}else{
			    Advisor.messageBox("Debe seleccionar una dependencia.","Acción inválida");
			}  
		    }
		}else{
		    patrimonio.setDependencia(dependencia);
		    if(isPersonReasigned){
			    personToReasigned = new Persona();
			    int idPerson = Integer.parseInt(cbPersonas.getSelectedValue().toString());
			    personToReasigned.setIdPerson(idPerson);
			    personToReasigned.retrieveData();
			    patrimonio.setPersonToReasigned(personToReasigned);
			    if(patrimonio.reasignarRecursosDepToPerson()){
				Advisor.messageBox("Se grabó la reasignación con exito.","Aviso");                
			        cerrar();
			    }else{
				Advisor.messageBox("Se produjo un error al grabar los datos.","Error");                    
			    }
		    }else{
			if(!(TFDependencia.getValue().toString().equals("")||TFDependencia.getValue().toString().equals(defaultValue))){
			    dependenciaToReasigned = tree.getDependenciaSeleccionada();
			    patrimonio.setDependenciaToReasigned(dependenciaToReasigned);
			    if(patrimonio.reasignarRecursosDepToDep()){
				Advisor.messageBox("Se grabó la reasignación con exito.","Aviso");                
			        cerrar();
			    }else{
				Advisor.messageBox("Se produjo un error al grabar los datos.","Error");                    
			    }
			}else{
			    Advisor.messageBox("Debe seleccionar una dependencia.","Acción inválida");
			} 
		    }
		}
	    }else{
		//nada
	    }
	}else{
	    Advisor.messageBox("Debe seleccionar al menos un recurso.","Acción inválida");
	}
   }
	
    private void btnClose_actionPerformed(ActionEvent e) {      
	cerrar();
    }
    
    private void cerrar(){
	getParentInternalFrame().close();
    }
    
    private void setPersonAsigned(){
	if(persona != null){
	    isPersonAsigned = true;
	}else{
	    isPersonAsigned = false;
	}
    }

    public void setRecurso(Resource recurso) {
	this.recurso = recurso;
    }

    public Resource getRecurso() {
	return recurso;
    }

    public void setPersona(Persona persona) {
	this.persona = persona;
    }

    public Persona getPersona() {
	return persona;
    }

    public void setDependencia(Dependencia dependencia) {
	this.dependencia = dependencia;
    }

    public Dependencia getDependencia() {
	return dependencia;
    }

    private void rbPersona_actionPerformed(ActionEvent e) {
	isPersonReasigned = true;
	cbPersonas.getCombo().setEnabled(isPersonReasigned);
	TFDependencia.getTextField().setEnabled(!isPersonAsigned);
	TFDependencia.setValue("");
    }

    private void rbDependencia_actionPerformed(ActionEvent e) {
	isPersonReasigned = false;
	cbPersonas.getCombo().setEnabled(isPersonReasigned);
	TFDependencia.getTextField().setEnabled(!isPersonReasigned);
	TFDependencia.setValue(defaultValue);
    }
}

