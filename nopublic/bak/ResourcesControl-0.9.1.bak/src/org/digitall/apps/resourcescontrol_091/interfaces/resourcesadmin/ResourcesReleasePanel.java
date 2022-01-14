package org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import org.digitall.apps.personalfiles.classes.Dependencia;
import org.digitall.common.personalfiles.classes.Persona;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;

public class ResourcesReleasePanel extends BasicPrimitivePanel{
    private BasicPanel content = new BasicPanel();
    private Resource recurso;   
    private Persona persona;
    private Dependencia dependencia;
    private RecursosAsignadosPanel recursosAsignadosPanel;
    private boolean isPersonAsigned;
    private TFInput TFDateAsigned = new TFInput(DataTypes.DATE,"Fecha de Liberación",true);
    private SaveDataButton btnSave = new SaveDataButton();    
    private CloseButton btnClose = new CloseButton();
    private Patrimonio patrimonio = new Patrimonio();
    private BasicPanel panelSur = new BasicPanel();

    public ResourcesReleasePanel(Resource _recurso,Persona _persona,Dependencia _dependencia) {
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
	this.setSize(new Dimension(500, 429));
	content.setLayout(new BorderLayout());
	TFDateAsigned.setBounds(new Rectangle(5, 5, 125, 35));
	panelSur.setBounds(new Rectangle(0, 0, 480, 55));
	panelSur.setPreferredSize(new Dimension(480, 55));
	panelSur.setLayout(null);
	panelSur.add(TFDateAsigned,null);
	panelSur.setBorder(BorderPanel.getBorderPanel(""));
	TFDateAsigned.setValue(Proced.setFormatDate(Environment.currentDate,true));
	setPersonAsigned();
	recursosAsignadosPanel = new RecursosAsignadosPanel(recurso,persona,dependencia,isPersonAsigned,true);
	content.add(panelSur, BorderLayout.SOUTH);
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
	this.addButton(btnClose);
	this.addButton(btnSave);
	this.add(content, null);
	recursosAsignadosPanel.refresh();
    }
    
    private void btnSave_actionPerformed(ActionEvent e) {       
	liberarRecursos();
    }
    
    private void liberarRecursos(){
	if(recursosAsignadosPanel.getCounSelected() > 0){
	    if(Advisor.question("Confirmación","¿Desea realizar la operación?")){
		Vector recursosDistSelectedToLiberty = new Vector();
		recursosDistSelectedToLiberty = recursosAsignadosPanel.getSelectedsID();
		patrimonio.setRecursosDistSelectedToLiberty(recursosDistSelectedToLiberty);
		patrimonio.setDateAsigned(Proced.setFormatDate(TFDateAsigned.getDate(), false));
		System.out.println(TFDateAsigned.getDate());
		if(isPersonAsigned){
		    patrimonio.setPersona(persona);
		    if(patrimonio.liberarRecursosPerson()){
			Advisor.messageBox("Se grabó la liberación con exito.","Aviso");		    
			cerrar();
		    }else{
			Advisor.messageBox("Se produjo un error al grabar los datos.","Error");                    
		    }
		}else{
		    patrimonio.setDependencia(dependencia);
		    if(patrimonio.liberarRecursosDependency()){
			Advisor.messageBox("Se grabó la liberación con exito.","Aviso");                
			cerrar();
		    }else{
			Advisor.messageBox("Se produjo un error al grabar los datos.","Error");                    
		    }
		}
	    }
	}else{
	    Advisor.messageBox("Debe seleccionar al menos un recurso.","Acción invalida");
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
}
