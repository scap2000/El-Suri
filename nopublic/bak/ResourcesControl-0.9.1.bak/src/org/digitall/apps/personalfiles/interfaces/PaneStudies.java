package org.digitall.apps.personalfiles.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.personalfiles.classes.Persona;
import org.digitall.common.personalfiles.classes.QualificationPerson;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;

public class PaneStudies extends BasicContainerPanel{

    private BorderLayout borderLayout1 = new BorderLayout();
    private BasicPanel jPanel1 = new BasicPanel();
    private BasicPanel jPanel2 = new BasicPanel();
    private TFInput tfName = new TFInput(DataTypes.STRING, "Name",false);
    private CBInput cbStudyLevel = new CBInput(0, "StudyLevel");
    private TFInput tfStudyYear = new TFInput(DataTypes.INTEGER, "StudyYear",true);
    private TFInput jfAverage = new TFInput(DataTypes.DOUBLE, "Average",false);
    private TFInput tfScore = new TFInput(DataTypes.DOUBLE, "Score",false);
    private TFInput tfEstablishment = new TFInput(DataTypes.STRING, "Establishment",false);
    private TFInput tfGraduaciónDate = new TFInput(DataTypes.DATE, "EndDate",false);
    private TFInput tfStartDate = new TFInput(DataTypes.DATE, "StartDate",true);
    private TFInput tfObservation = new TFInput(DataTypes.STRING, "Observations", false);    
    private BasicPanel panelBotonesStudy = new BasicPanel();
    private DeleteButton btnDeleteStudy = new DeleteButton();
    private AssignButton btnAddStudy = new AssignButton(true);
    private BasicLabel jLabel1 = new BasicLabel();    
    private QualificationPerson qualificationPerson;
    private PersonMgmt parent;
    private CBInput cbStatusStudy = new CBInput(0, "Status");    

    public PaneStudies() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    public PaneStudies(PersonMgmt _personMgmt) {
	try {
	    parent = _personMgmt;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {	
	this.setLayout(borderLayout1);
	this.setSize(580,270);
	this.setLayout(borderLayout1);
	jPanel1.setLayout(null);
	jPanel1.setBorder(BorderPanel.getBorderPanel("Agregar Estudio"));
	jPanel1.setSize(new Dimension(580, 230));
	jPanel1.setToolTipText("null");
	jPanel1.add(tfStartDate, null);
	jPanel1.add(cbStatusStudy, null);
	jPanel1.add(tfObservation, null);
	jPanel1.add(tfGraduaciónDate, null);
	jPanel1.add(tfEstablishment, null);
        jPanel1.add(tfScore, null);
        jPanel1.add(jfAverage, null);
        jPanel1.add(tfStudyYear, null);
	jPanel1.add(cbStudyLevel, null);
	jPanel1.add(tfName, null);
	tfName.setBounds(new Rectangle(10, 20, 270, 35));
	cbStudyLevel.setBounds(new Rectangle(290, 20, 260, 35));
	tfStudyYear.setBounds(new Rectangle(10, 60, 100, 35));
	jfAverage.setBounds(new Rectangle(160, 60, 100, 35));
	tfScore.setBounds(new Rectangle(320, 60, 100, 35));
	tfEstablishment.setBounds(new Rectangle(10, 100, 410, 35));
	tfGraduaciónDate.setBounds(new Rectangle(320, 140, 90, 35));
	tfGraduaciónDate.setEnabled(false);
	tfGraduaciónDate.setPreferredSize(new Dimension(89, 31));
	tfGraduaciónDate.setSize(new Dimension(100, 35));
	tfStartDate.setEnabled(false);
	tfObservation.setBounds(new Rectangle(10, 180, 540, 35));	
	cbStudyLevel.autoSize();	
	loadcombos();
	panelBotonesStudy.setPreferredSize(new Dimension(580, 40));
	panelBotonesStudy.setLayout(null);      
	btnDeleteStudy.setBounds(new Rectangle(535, 10, 30, 30));       
	btnAddStudy.setBounds(new Rectangle(505, 10, 35, 30));
	btnAddStudy.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       btnAddStudy_actionPerformed(e);
				   }

			       }
	);
	jLabel1.setText("Puede agregar o borrar estudios con los botones a su derecha -->");
	jLabel1.setBounds(new Rectangle(65, 15, 405, 15));
	cbStatusStudy.setBounds(new Rectangle(10, 140, 155, 35));
	cbStatusStudy.addItemListener(new ItemListener() {

				   public void itemStateChanged(ItemEvent e) {
				       cbStatusStudy_itemStateChanged(e);
				   }

			       }
	);
	tfStartDate.setBounds(new Rectangle(200, 140, 110, 35));
	panelBotonesStudy.add(jLabel1, null);
	panelBotonesStudy.add(btnAddStudy, null);
	panelBotonesStudy.add(btnDeleteStudy, null);
	this.add(jPanel1, BorderLayout.CENTER);
	this.add(panelBotonesStudy, BorderLayout.SOUTH);
	btnDeleteStudy.setEnabled(false);
	btnDeleteStudy.addActionListener(new ActionListener() {

				      public void actionPerformed(ActionEvent e) {
					  btnDeleteStudy_actionPerformed(e);
				      }

				  }
	);
	cbStatusStudy.autoSize(); 
        
    }
    
    public void setQualificationPerson(QualificationPerson _qualificationPerson){
	this.qualificationPerson = _qualificationPerson;
	loadData();
	btnDeleteStudy.setEnabled(true);
    }
    
    private void loadData(){   
	tfName.setValue(qualificationPerson.getName());
	cbStudyLevel.setSelectedID(qualificationPerson.getIdTypeQualification());
	tfStudyYear.setValue(qualificationPerson.getYear());
	jfAverage.setValue(qualificationPerson.getPromedio());
	tfScore.setValue(qualificationPerson.getPuntaje());
	tfEstablishment.setValue(qualificationPerson.getEstablishment());	
	tfGraduaciónDate.setValue(qualificationPerson.getDateGraduation());
	tfObservation.setValue(qualificationPerson.getObservations());	
	cbStatusStudy.setSelectedID(qualificationPerson.getIdQualificationStatus());	
	tfStartDate.setValue(qualificationPerson.getDateStart());
        loadcombos();
    }

    private void loadcombos() {
	cbStudyLevel.loadJCombo(LibSQL.exFunction("personalfiles.getAllTypeQualification", ""));
	cbStatusStudy.loadJCombo(LibSQL.exFunction("personalfiles.getAllQualificationStatus", ""));
	cbStudyLevel.setSelectedID("1");
    }
    
    public boolean saveData() {
	boolean resul = false;
	if (qualificationPerson == null) {
	    qualificationPerson = new QualificationPerson();
	}       	
	qualificationPerson.setName(tfName.getValue().toString());
	qualificationPerson.setIdTypeQualification(Integer.parseInt(cbStudyLevel.getSelectedValue().toString()));
	qualificationPerson.setYear(Integer.parseInt(tfStudyYear.getValue().toString()));
	qualificationPerson.setPromedio(Float.parseFloat(jfAverage.getValue().toString()));
	qualificationPerson.setPuntaje(Float.parseFloat(tfScore.getValue().toString()));
	qualificationPerson.setEstablishment(tfEstablishment.getValue().toString());
	qualificationPerson.setIdQualificationStatus(Integer.parseInt(cbStatusStudy.getSelectedValue().toString()));	
	qualificationPerson.setDateStart(Proced.setFormatDate(tfStartDate.getString(), false));	
	qualificationPerson.setDateGraduation(Proced.setFormatDate(tfGraduaciónDate.getString(), false));
	qualificationPerson.setObservations(tfObservation.getValue().toString());
	Persona person = parent.getPerson();
	qualificationPerson.setIdPerson(person.getIdPerson());
	if (qualificationPerson.saveData() > 0) {
		resul = true;			        
	    }
	    else {		
		Advisor.messageBox("Ocurrio un error al grabar los datos!", "Error");
	    }
	return resul;
    }
    
    private void clearData(){
	tfName.setValue("");
	cbStudyLevel.setSelectedID("1");
	tfStudyYear.setValue("");
	jfAverage.setValue(0);
	tfScore.setValue(0);
	tfEstablishment.setValue("");
	tfGraduaciónDate.setValue("");
	tfObservation.setValue("");	
	qualificationPerson = null;
    }

    private void btnAddStudy_actionPerformed(ActionEvent e) {
	if (saveData()) {
	    parent.refreshGridStudy();
	    clearData();
	    btnDeleteStudy.setEnabled(false);
	}       
    }

    private void btnDeleteStudy_actionPerformed(ActionEvent e) {
	if (qualificationPerson != null) {
	    if (Advisor.question("Borrar estudio", "¿Está seguro de borrar el estudio seleccionado?")) {		
		if (LibSQL.getInt("personalfiles.delQualificationPerson", "" + qualificationPerson.getIdQualificationPerson()) == 0) {
		    parent.refreshGridStudy();		    
		    clearData();
		    btnDeleteStudy.setEnabled(false);
		}
	    }
	}
    }

    private void cbStatusStudy_itemStateChanged(ItemEvent e) {
	if (e.getItem().equals("Cursando")) {	    
	    tfGraduaciónDate.setEnabled(false);	    	    
	    tfStartDate.setEnabled(true);
	}else if (e.getItem().equals("Incompleto")) {	    
	    tfGraduaciónDate.setEnabled(false);         
	    tfStartDate.setEnabled(true);
	}else if (e.getItem().equals("N/A")) {	    
	    tfGraduaciónDate.setEnabled(false);     
	    tfStartDate.setEnabled(false);
	}else if (e.getItem().equals("Completo")) {	    
	    tfGraduaciónDate.setEnabled(true);     
	    tfStartDate.setEnabled(true);
	}
    }    

}
