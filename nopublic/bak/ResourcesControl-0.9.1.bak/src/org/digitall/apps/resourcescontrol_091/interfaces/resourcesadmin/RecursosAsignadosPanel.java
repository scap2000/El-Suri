package org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Vector;
import org.digitall.apps.personalfiles.classes.Dependencia;
import org.digitall.common.personalfiles.classes.Persona;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;

public class RecursosAsignadosPanel extends BasicPrimitivePanel{

    private BasicPanel content = new BasicPanel();
    private int[] sizeColumnList = { 92, 98, 64, 96, 97, 185};
    private Vector dataRow = new Vector();
    private GridPanel grilla = new GridPanel(5000, sizeColumnList, "Recursos Distinguibles", dataRow);
    private Vector headerList = new Vector();
    private TFInput tfRecurso = new TFInput(DataTypes.STRING,"Resource",false);    
    private TFInput tfAsignedEnty = new TFInput(DataTypes.STRING,"Persona/Dependencias",false);
    private TFInput tfPersonaResponsable = new TFInput(DataTypes.STRING,"Responsable de",false);
    private Resource recurso;   
    private Persona persona;
    private Dependencia dependencia;
    private boolean isPersonAsigned;
    private boolean isDependenciaAsigned;
    private String textPerson = "Persona";
    private String textDependencia = "Dependencia";
    private BasicPanel panelTF = new BasicPanel();
    private BorderLayout borderLayout1 = new BorderLayout();
    private boolean libera;
	
    public RecursosAsignadosPanel(Resource _recurso,Persona _persona,Dependencia _dependencia,boolean _isPersonAsigned,boolean _libera) {
	try {
	    recurso = _recurso;
	    persona = _persona;
	    dependencia = _dependencia;
	    isPersonAsigned = _isPersonAsigned;
	    libera = _libera;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	 this.setSize(new Dimension(498, 365));
	tfRecurso.setBounds(new Rectangle(10, 10, 480, 35));
	setEnty();
	setResponsability();
	panelTF.setBorder(BorderPanel.getBorderPanel(" "));
	tfAsignedEnty.setBounds(new Rectangle(10, 50, 480, 35));
	tfPersonaResponsable.setBounds(new Rectangle(10, 90, 480, 35));
	tfPersonaResponsable.setEnabled(false);
	tfRecurso.setEnabled(false);
	tfAsignedEnty.setEnabled(false);
	grilla.setBounds(new Rectangle(5, 105, 480, 220));
	grilla.setSize(480, 220);
	content.setLayout(borderLayout1);
	setTFRecurso();
	setTFAsigined();
	setTFResponsable();
	panelTF.setBounds(new Rectangle(0, 0, 480, 135));
	panelTF.setPreferredSize(new Dimension(480, 135));
	panelTF.setLayout(null);
	panelTF.add(tfAsignedEnty);
	panelTF.add(tfRecurso);
	panelTF.add(tfPersonaResponsable);
	content.add(panelTF,BorderLayout.NORTH);
	content.add(grilla, BorderLayout.CENTER);
	this.add(content, null);
	setHeaderList();
    }
    
    private void setTFAsigined(){
	if(isPersonAsigned){
	    tfAsignedEnty.setValue(persona.getLastName()+", "+persona.getFirstName());    
	}else{
	    tfAsignedEnty.setValue(dependencia.getNombreNivelJerarquico()+" "+dependencia.getNombre());    
	}
    }
    
    private void setTFResponsable(){
	tfPersonaResponsable.setValue(""+ LibSQL.getString("org.getUserName",""));
    }
    private void setResponsability(){
	if(libera){
	    tfPersonaResponsable = new TFInput(DataTypes.STRING,"Responsable de Liberación",false);       
	}else{
	    tfPersonaResponsable = new TFInput(DataTypes.STRING,"Responsable de Reasignación",false);       
	}
    }
    
    private void setEnty(){
	if(isPersonAsigned){
	    tfAsignedEnty = new TFInput(DataTypes.STRING,textPerson,false);    
	}else{
	    tfAsignedEnty = new TFInput(DataTypes.STRING,textDependencia,false);
	}
    }
    
    private void setTFRecurso(){
	if(recurso != null){
	    tfRecurso.setValue(recurso.getName());
	}
    }

    private void setHeaderList() {      
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("Marca");
	headerList.addElement("*");
	headerList.addElement("Tipo desgaste"); 
	headerList.addElement("Desgaste"); 
	headerList.addElement("*"); 
	headerList.addElement("Tipo identif."); 
	headerList.addElement("Nº");    
	headerList.addElement("Asignado a");    
	if(isPersonAsigned){
	    grilla.setParams("personalfiles.getAllPatrimoniosPersonAtResource", "", headerList);        
	}else{
	    grilla.setParams("personalfiles.getAllPatrimoniosDependenciAtResource", "", headerList);    
	}
	
    }

    public void refresh() {
	String params = ""; 
	if(isPersonAsigned){
	    params = ""+persona.getIdPerson()+","+recurso.getIdResource();      
	}else{
	    params = ""+dependencia.getIdDep()+","+recurso.getIdResource();      
	}
	grilla.refresh(params);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Recursos asigado a una persona o dependencia");
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

    public void setIsPersonAsigned(boolean isPersonAsigned) {
	this.isPersonAsigned = isPersonAsigned;
    }

    public boolean isIsPersonAsigned() {
	return isPersonAsigned;
    }

    public void setIsDependenciaAsigned(boolean isDependenciaAsigned) {
	this.isDependenciaAsigned = isDependenciaAsigned;
    }

    public boolean isIsDependenciaAsigned() {
	return isDependenciaAsigned;
    }

    public void setGrilla(GridPanel grilla) {
	this.grilla = grilla;
    }

    public GridPanel getGrilla() {
	return grilla;
    }
    
    public int getCounSelected(){
	int resultado = grilla.getSelectedsID().size();
	return(resultado);
    }
    
    public Vector getSelectedsID(){
	return(grilla.getSelectedsID());
    }
}
