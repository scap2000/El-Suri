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
 * PersonMgmt.java
 *
 * */
package org.digitall.apps.personalfiles.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Vector;

import org.digitall.apps.personalfiles.AddressPanel;
import org.digitall.apps.personalfiles.PersonsList;
import org.digitall.common.personalfiles.classes.DireccionPersona;
import org.digitall.common.personalfiles.classes.Persona;
import org.digitall.common.personalfiles.classes.QualificationPerson;
import org.digitall.common.personalfiles.classes.Relatives;
import org.digitall.common.personalfiles.classes.TrabajoPersona;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicTabbedPane;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.EditButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class PersonMgmt extends BasicPrimitivePanel{

    private BasicPanel jPanelData = new BasicPanel();    
    private BasicPanel panelDatosPersonal = new BasicPanel();
    private BasicPanel panelDatosFamily = new BasicPanel();
    private BasicPanel panelDatosAddress = new BasicPanel();
    private BasicPanel panelDatosJobs = new BasicPanel();
    private BasicPanel panelDatosStudies = new BasicPanel();
    private BasicTabbedPane jTabbedPane1 = new BasicTabbedPane();
    private AddButton btnAdd = new AddButton();
    private SaveDataButton btnSave = new SaveDataButton();
    private DeleteButton btnDelete = new DeleteButton();
    private CloseButton btnClose = new CloseButton();    
    //Pestaña Datos personales
    private PaneDatosPersonalesEmpleados paneDatosPersonalesEmpleados = new PaneDatosPersonalesEmpleados();
    private BorderLayout borderLayout9 = new BorderLayout();
    //Pestaña Direcciones
    private int[] sizeColumnListAddress = { 149, 39, 42, 35, 42, 100, 71, 51 };
    private Vector dataRowAddress = new Vector();
    private GridPanel listPanelAddress = new GridPanel(30, sizeColumnListAddress, "Direcciones", dataRowAddress);
    private Vector headerListAddresses = new Vector();
    private AddressPanel addressPanel = new AddressPanel();
    private BasicPanel panelNorteAdress = new BasicPanel();
    private BorderLayout borderLayout1 = new BorderLayout();
    private BorderLayout borderLayout2 = new BorderLayout();
    private BorderLayout borderLayout3 = new BorderLayout();
    private BorderLayout borderLayout6 = new BorderLayout();
    private BasicPanel panelBotonesAddress = new BasicPanel();
    private DeleteButton btnDeleteAddress = new DeleteButton();
    private AssignButton btnAddAddress = new AssignButton(true);
    private BasicLabel jLabel2 = new BasicLabel();    
    //Pestaña Familiares
    private SelectRelatives selectRelatives = new SelectRelatives(this);
    private int[] sizeColumnListRelatives = { 115,70, 108, 236};
    private Vector dataRowRelatives = new Vector();
    private GridPanel listPanelRelatives = new GridPanel(30, sizeColumnListRelatives, "Familiares", dataRowRelatives);    
    private Vector headerListRelatives = new Vector();    
    private BasicPanel panelBotonesRelatives = new BasicPanel();
    private EditButton btnModifyReltives = new EditButton();
    private DeleteButton btnDeleteRelatives = new DeleteButton();        
    
    private AcceptButton  btnOcult = new AcceptButton();        
    
    private BasicLabel jLabel1 = new BasicLabel();  
    //Pestana Cargos
    private PaneJobs panelJobs = new PaneJobs(this);
    private BorderLayout borderLayout4 = new BorderLayout();    
    private int[] sizeColumnListJobs = { 231, 231, 66, 80,80,80};
    private Vector dataRowJobs = new Vector();
    private Vector headerListJobs = new Vector();
    private GridPanel listPanelJobs = new GridPanel(30, sizeColumnListJobs, "Cargos", dataRowJobs);
    //Pestaña Estudios
    private BorderLayout borderLayout5 = new BorderLayout();
    private PaneStudies paneStudies = new PaneStudies(this);
    private int[] sizeColumnListStudies = { 210, 210, 109};
    private Vector dataRowStudies = new Vector();
    private Vector headerListStudies = new Vector(); 
    private GridPanel listPanelStudies = new GridPanel(30, sizeColumnListStudies, "Estudios", dataRowStudies);     
    private Persona person = null;
    private PersonsList parentList = null;
    private QualificationPerson qualificationPerson = null;
    private TrabajoPersona trabajoPersona = null;
    private Relatives relative = null;
    private DireccionPersona direccionPersona = null;
    private Persona personRelative = null;
    private BorderLayout borderLayout7 = new BorderLayout();

    public PersonMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {    
	this.setSize(new Dimension(580, 600));
	this.setPreferredSize(new Dimension(565, 430));		
	jPanelData.setLayout(borderLayout7);
	jPanelData.setSize(new Dimension(580, 600));
	panelDatosFamily.setLayout(borderLayout2);
	panelDatosAddress.setLayout(borderLayout3);
	panelDatosJobs.setLayout(borderLayout4);
	panelDatosStudies.setLayout(borderLayout5);
	jTabbedPane1.setSize(new Dimension(540, 480));
	jTabbedPane1.setPreferredSize(new Dimension(540, 480));
	btnAdd.setBounds(new Rectangle(515, 10, 25, 30));
	btnDelete.setBounds(new Rectangle(535, 10, 30, 30));		
	panelDatosPersonal.add(paneDatosPersonalesEmpleados, BorderLayout.CENTER);
	jTabbedPane1.addTab("Datos personales", panelDatosPersonal);
	jTabbedPane1.addTab("Direcciones", panelDatosAddress);
	jTabbedPane1.addTab("Familiares", panelDatosFamily);
	jTabbedPane1.addTab("Cargos", panelDatosJobs);
	jTabbedPane1.addTab("Capacitación", panelDatosStudies);	
	//Pestaña Datos Personales
	panelDatosPersonal.setLayout(borderLayout9);	
	paneDatosPersonalesEmpleados.setPreferredSize(new Dimension(576, 541));
	//Pestaña Domincilios
	panelNorteAdress.setLayout(borderLayout1);
	panelNorteAdress.setBorder(BorderPanel.getBorderPanel("Agregar Domicilio"));
	panelNorteAdress.setPreferredSize(new Dimension(600, 320));
	addressPanel.setBounds(new Rectangle(5, 20, 565, 300));
	addressPanel.setSize(new Dimension(565, 240));
	addressPanel.setPreferredSize(new Dimension(565, 240));
	panelBotonesAddress.setPreferredSize(new Dimension(580, 40));
	panelBotonesAddress.setLayout(null);
	jLabel2.setText("Puede agregar o borrar direcciones con los botones a su derecha -->");
	jLabel2.setBounds(new Rectangle(50, 15, 415, 15));
	panelBotonesAddress.add(jLabel2, null);
	panelBotonesAddress.add(btnAddAddress, null);
	btnDeleteAddress.setEnabled(false);
	panelBotonesAddress.add(btnDeleteAddress, null);
	panelNorteAdress.add(addressPanel, BorderLayout.CENTER);
	panelNorteAdress.add(panelBotonesAddress, BorderLayout.SOUTH);
	listPanelAddress.setPreferredSize(new Dimension(575, 267));
	listPanelAddress.setMinimumSize(new Dimension(173, 250));
	listPanelAddress.setSize(new Dimension(575, 200));
	panelDatosAddress.add(panelNorteAdress, BorderLayout.NORTH);
	panelDatosAddress.add(listPanelAddress, BorderLayout.CENTER);	
	//Pestaña Familiares	
	panelDatosFamily.setLayout(new BorderLayout());	
	selectRelatives.setPreferredSize(new Dimension(580, 300));	
	listPanelRelatives.setPreferredSize(new Dimension(600, 300));
	listPanelRelatives.setMinimumSize(new Dimension(173, 250));
	listPanelRelatives.setSize(new Dimension(575, 200));
	panelBotonesRelatives.setLayout(null);
	panelBotonesRelatives.setPreferredSize(new Dimension(1, 30));
	panelDatosFamily.add(listPanelRelatives, BorderLayout.CENTER);
	panelDatosFamily.add(selectRelatives, BorderLayout.NORTH);
	panelBotonesRelatives.add(jLabel1, null);
	panelBotonesRelatives.add(btnModifyReltives, null);
	btnModifyReltives.setEnabled(false);
	panelBotonesRelatives.add(btnDeleteRelatives, null);
	btnDeleteRelatives.setEnabled(false);
	jLabel1.setText("Puede modificar o borrar familiares con los botones a su derecha -->");
	panelDatosFamily.add(panelBotonesRelatives, BorderLayout.SOUTH);
	
	//Pestaña Cargos			
	panelJobs.setPreferredSize(new Dimension(200,190));
	listPanelJobs.setPreferredSize(new Dimension(560, 400));
	listPanelJobs.setMinimumSize(new Dimension(173, 250));
	listPanelJobs.setSize(new Dimension(575, 200));	
	panelDatosJobs.add(panelJobs, BorderLayout.NORTH);
	panelDatosJobs.add(listPanelJobs, BorderLayout.CENTER);	
	//Pestaña Estudios	
	paneStudies.setPreferredSize(new Dimension(580,270));
	btnDeleteAddress.setBounds(new Rectangle(535, 15, 30, 20));
	btnDeleteAddress.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
					    btnDeleteAddress_actionPerformed(e);
					}

				    }
	);
	btnAddAddress.setBounds(new Rectangle(505, 15, 30, 20));
	btnAddAddress.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					btnAddAddres_actionPerformed(e);
				    }

				}
	);
	panelDatosStudies.add(paneStudies, BorderLayout.NORTH);
	panelDatosStudies.add(listPanelStudies, BorderLayout.CENTER);
	jPanelData.add(jTabbedPane1, BorderLayout.CENTER);
	this.add(jPanelData, BorderLayout.CENTER);
	//jPanelData.add(jTabbedPane1, new PaneConstraints("jTabbedPane1", "jTabbedPane1", PaneConstraints.ROOT, 0.5f));
	listPanelStudies.setPreferredSize(new Dimension(400, 280));
	listPanelStudies.setMinimumSize(new Dimension(173, 250));
	listPanelStudies.setSize(new Dimension(575, 280));
	//Panel Contenedor		 			
	btnDeleteRelatives.setBounds(new Rectangle(540, 5, 35, 20));
	btnDeleteRelatives.addActionListener(new ActionListener() {

					  public void actionPerformed(ActionEvent e) {
					      btnDeleteRelatives_actionPerformed(e);
					  }

				      }
	);
	btnModifyReltives.setBounds(new Rectangle(510, 5, 35, 20));
	btnModifyReltives.addActionListener(new ActionListener() {

					 public void actionPerformed(ActionEvent e) {
					     btnModifyReltives_actionPerformed(e);
					 }

				     }
	);
	jLabel1.setBounds(new Rectangle(60, 5, 420, 15));
	
	if((Environment.sessionUser.equals("cesar")) || (Environment.sessionUser.equals("admin")) ) {
	    btnOcult.setVisible(true);
	}else{
	    btnOcult.setVisible(false);
	}

	addButton(btnClose);   
	addButton(btnSave);   
	addButton(btnOcult);   
	
	btnSave.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       btnSaveData_actionPerformed(e);
				        //loadForm_actionPerformed(e);
				   }

			       }
	);
	btnOcult.addActionListener(new ActionListener() {
    
				    public void actionPerformed(ActionEvent e) {
					btnOcult_actionPerformed(e);
					 //loadForm_actionPerformed(e);
				    }
    
				}
	 );      
	btnClose.addActionListener(new ActionListener() {
                                   public void actionPerformed(ActionEvent e) {
			               btnClose_actionPerformed(e);
				  }

			       }
			       
	);
	setHeaderListRelatives();
	setHeaderListJobs();
	setHeaderListStudies();	
	setHeaderListAddress();
	jTabbedPane1.setEnabledAt(1,false);
	jTabbedPane1.setEnabledAt(2,false);
	jTabbedPane1.setEnabledAt(3,false);
	jTabbedPane1.setEnabledAt(4,false);     
	
	setHeaderListJobs();
	
    }
    
    public void loadPestanias(){
	refreshListPane();	
	jTabbedPane1.setEnabledAt(1,true);
	jTabbedPane1.setEnabledAt(2,true);
	jTabbedPane1.setEnabledAt(3,true);
	jTabbedPane1.setEnabledAt(4,true);    	
    }
    
    private void setHeaderListJobs() {      
	headerListJobs.removeAllElements();
	headerListJobs.addElement("*");
	headerListJobs.addElement("Dependencia");
	headerListJobs.addElement("*");
	headerListJobs.addElement("Cargo");
	headerListJobs.addElement("Salario $");
	headerListJobs.addElement("Fecha dsig.");
	headerListJobs.addElement("Fecha ini.");
	headerListJobs.addElement("Fecha fin");
	headerListJobs.addElement("*");
	headerListJobs.addElement("*");	
	listPanelJobs.getTable().addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						loadCargosPersona();
					    if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						panelJobs.setJobsPerson(trabajoPersona);
					    }
					}

				    }
	);      
	String params = "";
	listPanelJobs.setParams("personalfiles.getalljobspersonal", params, headerListJobs);   
    }
    
    private void setHeaderListStudies() {      
	headerListStudies.removeAllElements();
	headerListStudies.addElement("*");
	headerListStudies.addElement("*");
	headerListStudies.addElement("Nivel de estudio");
	headerListStudies.addElement("*");
	headerListStudies.addElement("Nombre estudio");
	headerListStudies.addElement("*");
	headerListStudies.addElement("*");
	headerListStudies.addElement("*");
	headerListStudies.addElement("*");
	headerListStudies.addElement("*");
	headerListStudies.addElement("*"); 
	headerListStudies.addElement("*"); 
	headerListStudies.addElement("*"); 
	headerListStudies.addElement("Estado"); 	
	headerListStudies.addElement("*"); 
	listPanelStudies.getTable().addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						loadEstudiosPersona();
					    if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						paneStudies.setQualificationPerson(qualificationPerson);
					    }
					}
				    }
	);      
	
	String params = "-1";
	listPanelStudies.setParams("personalfiles.getAllQualificationPerson", params, headerListStudies);   
    }
    
    private void setHeaderListRelatives() {      
	headerListRelatives.removeAllElements();
        
        headerListRelatives.addElement("*");
	headerListRelatives.addElement("*");
	headerListRelatives.addElement("*");
	headerListRelatives.addElement("Parentesco");
	headerListRelatives.addElement("*");
	headerListRelatives.addElement("Tipo ID");
	headerListRelatives.addElement("Nº ID");
	headerListRelatives.addElement("Apellido(s), Nombre(s)");       

	listPanelRelatives.getTable().addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						loadRelatives();
						btnModifyReltives.setEnabled(true);
						btnDeleteRelatives.setEnabled(true);
					    if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						 loadPersonMgmt();
					    }
					}

				    }
	);       
	
	String params = "-1";
	listPanelRelatives.setParams("personalfiles.getAllRelatives", params, headerListRelatives);   
    }

    private void setHeaderListAddress() {
	headerListAddresses.removeAllElements();	
	
	headerListAddresses.addElement("*");
	headerListAddresses.addElement("*");
	headerListAddresses.addElement("*");
	headerListAddresses.addElement("*");
	headerListAddresses.addElement("*");
	headerListAddresses.addElement("*");
	headerListAddresses.addElement("*");
	headerListAddresses.addElement("*");
	headerListAddresses.addElement("Calle");
	headerListAddresses.addElement("Nro.");
	headerListAddresses.addElement("Block");
	headerListAddresses.addElement("Piso");
	headerListAddresses.addElement("Dpto.");
	headerListAddresses.addElement("Tel.");	
	headerListAddresses.addElement("*");
	headerListAddresses.addElement("*");
	headerListAddresses.addElement("*");
	headerListAddresses.addElement("*");
	headerListAddresses.addElement("*");
	headerListAddresses.addElement("*");
	headerListAddresses.addElement("*");
	headerListAddresses.addElement("*");
	headerListAddresses.addElement("*");
	headerListAddresses.addElement("*");
	headerListAddresses.addElement("*");
	headerListAddresses.addElement("Tipo");
	headerListAddresses.addElement("*");
	headerListAddresses.addElement("Defecto");
	headerListAddresses.addElement("*");
	headerListAddresses.addElement("*");
	
	listPanelAddress.getTable().addMouseListener(new MouseAdapter() {
					   public void mouseClicked(MouseEvent e) {
					       if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						    btnAddAddress.setEnabled(true);						    
						    loadAddressesPerson();					    
					       } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
					           btnDeleteAddress.setEnabled(true);
						   addressPanel.setDireccionPersona(direccionPersona);
					       }
					   }

				       }
	);
	String params = "-1";
	listPanelAddress.setParams("personalfiles.getAllPersonAddresses", params, headerListAddresses);
    }
	
    public void refreshListPane() {	
	String params = "" + person.getIdPerson();
	listPanelJobs.refresh(params);
	listPanelStudies.refresh(params);
	listPanelRelatives.refresh(params);
	listPanelAddress.refresh(params);
    }
    
   public void setParentList(PersonsList parentList) {
	this.parentList = parentList;
    }
    
    public boolean saveData() {
	boolean resul = false;
        if (person == null) {
            person = new Persona();
        }	    

        paneDatosPersonalesEmpleados.loadData(person);
            
        int result = person.saveData();
        if (result > 0) {
            resul = true;
        }
        else if (result == -1){
            Advisor.messageBox("Ocurrio un error al grabar los datos personales!", "Error");
        }else if (result == -2){
            Advisor.messageBox("Ocurrio un error al grabar la foto!", "Error");
        }
	
        return resul;
    }
    
    public boolean saveDataOcult() {
	boolean resul = false;
	if (person == null) {
	    person = new Persona();
	}           

	paneDatosPersonalesEmpleados.loadData(person);
	    
	int result = person.saveDataOcult();
	if (result > 0) {
	    resul = true;
	}
	else if (result == -1){
	    Advisor.messageBox("Ocurrio un error al grabar los datos personales!", "Error");
	}else if (result == -2){
	    Advisor.messageBox("Ocurrio un error al grabar la foto!", "Error");
	}
	
	return resul;
    }
    
    
     
    private void btnSaveData_actionPerformed(ActionEvent e) {
	if (saveData()) {
	    parentList.refresh();	
	    if (!jTabbedPane1.isEnabledAt(1)){
	        jTabbedPane1.setEnabledAt(1,true);
	        jTabbedPane1.setEnabledAt(2,true);
	        jTabbedPane1.setEnabledAt(3,true);
	        jTabbedPane1.setEnabledAt(4,true);  
		this.getParentInternalFrame().setTitle(person.getLastName().toUpperCase()+", "+person.getFirstName().toUpperCase());
	    }	    
	}
	/*if(listPanelAddress.getAllValues().size() == 0){
	    addAdress();
	}*/
    }
    
    
     private void btnOcult_actionPerformed(ActionEvent e) {
	 if (saveDataOcult()) {
	     parentList.refresh();       
	     if (!jTabbedPane1.isEnabledAt(1)){
		 jTabbedPane1.setEnabledAt(1,true);
		 jTabbedPane1.setEnabledAt(2,true);
		 jTabbedPane1.setEnabledAt(3,true);
		 jTabbedPane1.setEnabledAt(4,true);  
		 this.getParentInternalFrame().setTitle(person.getLastName().toUpperCase()+", "+person.getFirstName().toUpperCase());
	     }       
	 }
	 /*if(listPanelAddress.getAllValues().size() == 0){
	     addAdress();
	 }*/
     }
	 
    public void setPerson(Persona _person){
	person = _person;	
        paneDatosPersonalesEmpleados.setData(person);
	//A qui le pasamos a la grilla la persona que le estamos por asignar el familiar
	 selectRelatives.setPerson(person);
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }
    
    public void refreshGridJobs(){
	String params = "" + person.getIdPerson();
	listPanelJobs.refresh(params);
    }
    
    public void refreshGridStudy(){
	String params = "" + person.getIdPerson();
	listPanelStudies.refresh(params);
    }
    
    public void refreshGridRelatives(){
	String params = "" + person.getIdPerson();	
	listPanelRelatives.refresh(params);
    }
    
    public void refreshGridAddresses(){
	String params = "" + person.getIdPerson();      
	listPanelAddress.refresh(params);
    }
    
    public void setEnabledBotonesRelatives(){
	btnModifyReltives.setEnabled(false);
	btnDeleteRelatives.setEnabled(false);
    }
	
    public Persona getPerson(){
	return person;    
    }
    private void loadCargosPersona(){
	if (!dataRowJobs.isEmpty()) {
	    trabajoPersona = new TrabajoPersona();	  
	    trabajoPersona.setIdjobperson(Integer.parseInt("" + dataRowJobs.elementAt(9)));
	    trabajoPersona.setIdJobs(Integer.parseInt("" + dataRowJobs.elementAt(2)));
	    trabajoPersona.setIdPerson(Integer.parseInt("" + dataRowJobs.elementAt(8)));
	    trabajoPersona.setSalary(Float.parseFloat("" + dataRowJobs.elementAt(4)));
	    trabajoPersona.setDateDesignation("" + dataRowJobs.elementAt(5));
	    trabajoPersona.setDateStart("" + dataRowJobs.elementAt(6));
	    trabajoPersona.setDateEnd("" + dataRowJobs.elementAt(7));	   
	    trabajoPersona.setIddependency(Integer.parseInt("" + dataRowJobs.elementAt(0)));
	}	
    }
    
    private void loadEstudiosPersona(){
	if (!dataRowStudies.isEmpty()) {
	    qualificationPerson = new QualificationPerson();   
	    
	    qualificationPerson.setIdQualificationPerson(Integer.parseInt("" + dataRowStudies.elementAt(0)));	    
	    qualificationPerson.setIdTypeQualification(Integer.parseInt("" + dataRowStudies.elementAt(1)));
	    qualificationPerson.setName("" + dataRowStudies.elementAt(4));
	    qualificationPerson.setIdPerson(Integer.parseInt("" + dataRowStudies.elementAt(3)));
	    qualificationPerson.setEstablishment("" + dataRowStudies.elementAt(5));
	    qualificationPerson.setDateGraduation("" + dataRowStudies.elementAt(6));	    
	    qualificationPerson.setYear(Integer.parseInt("" + dataRowStudies.elementAt(7)));
	    qualificationPerson.setPromedio(Float.parseFloat("" + dataRowStudies.elementAt(8)));
	    qualificationPerson.setPuntaje(Float.parseFloat("" + dataRowStudies.elementAt(9)));
	    qualificationPerson.setObservations("" + dataRowStudies.elementAt(10));	   
	    qualificationPerson.setIdQualificationStatus(Integer.parseInt("" + dataRowStudies.elementAt(12)));         
	    qualificationPerson.setDateStart("" + dataRowStudies.elementAt(14));         
	}
    }
    private void loadRelatives(){	
	if (!dataRowRelatives.isEmpty()) {
	    //Cargamos estos datos por si desp el usuario elimina al pariente
	    //por lo que necesitamos el idrelative..
	    relative = new Relatives();   
	    relative.setIdPerson(Integer.parseInt("" + dataRowRelatives.elementAt(0)));
	    relative.setIdRelative(Integer.parseInt("" + dataRowRelatives.elementAt(1)));
	    relative.setIdKinships(Integer.parseInt("" + dataRowRelatives.elementAt(2)));
	    relative.setIdPersonEmployee(Integer.parseInt("" + person.getIdPerson()));                                          
	    
	    personRelative = new Persona();
	    
	    personRelative.setIdPerson(Integer.parseInt("" + dataRowRelatives.elementAt(0)));
	    personRelative.retrieveData();
	    personRelative.retrievePicture();
        
	   // personRelative.setIdcontacttype(0);
           //personRelative.setIdformatview(0);
           //personRelative.setIdTypeQualification(0);		    
	}
    }
    
    private void loadAddressesPerson(){       	 
   	direccionPersona = new DireccionPersona();   
	direccionPersona.setIdAddress(Integer.parseInt("" + dataRowAddress.elementAt(0)));
	direccionPersona.setIdCountry(Integer.parseInt("" + dataRowAddress.elementAt(2)));
	direccionPersona.setIdProvince(Integer.parseInt("" + dataRowAddress.elementAt(3)));
	direccionPersona.setIdLocation(Integer.parseInt("" + dataRowAddress.elementAt(4)));
	direccionPersona.setIdNeighborHood(Integer.parseInt("" + dataRowAddress.elementAt(5)));
	direccionPersona.setIdStreet(Integer.parseInt("" + dataRowAddress.elementAt(7)));
	direccionPersona.setNumber(Integer.parseInt("" + dataRowAddress.elementAt(9)));
	direccionPersona.setPostalCode("" + dataRowAddress.elementAt(14));
	direccionPersona.setBlock("" + dataRowAddress.elementAt(10));
	direccionPersona.setFloor(Integer.parseInt("" + dataRowAddress.elementAt(11)));
	direccionPersona.setApartment("" + dataRowAddress.elementAt(12));
	direccionPersona.setPhone("" + dataRowAddress.elementAt(13));
	direccionPersona.setPhone2("" + dataRowAddress.elementAt(15));
	direccionPersona.setMobile("" + dataRowAddress.elementAt(16));
	direccionPersona.setMobile2("" + dataRowAddress.elementAt(17));
	direccionPersona.setEmail("" + dataRowAddress.elementAt(18));
	direccionPersona.setEmail2("" + dataRowAddress.elementAt(19));
	direccionPersona.setFax("" + dataRowAddress.elementAt(20));
	direccionPersona.setUrl("" + dataRowAddress.elementAt(21));
	direccionPersona.setIdContact(Integer.parseInt("" + dataRowAddress.elementAt(22)));
	direccionPersona.setContactsChedule("" + dataRowAddress.elementAt(23));
	direccionPersona.setIdAddressType(Integer.parseInt("" + dataRowAddress.elementAt(24)));
	direccionPersona.setNick("" + dataRowAddress.elementAt(26));
	direccionPersona.setIdAddressPerson(Integer.parseInt("" + dataRowAddress.elementAt(29)));	
	if (dataRowAddress.elementAt(27).toString().equals("SI")){
	    direccionPersona.setDefault(true);
	}else{
	    direccionPersona.setDefault(false);
	}	
    }

    private void btnDeleteRelatives_actionPerformed(ActionEvent e) {
	if (relative != null) {
	    if (Advisor.question("Borrar familiar", "¿Está seguro de borrar el familiar seleccionado?")) {        
		if (LibSQL.getInt("personalfiles.delRelatives", "" + relative.getIdRelative()) == 0) {
		    refreshGridRelatives();		  			    
		    btnDeleteRelatives.setEnabled(false);
		    btnModifyReltives.setEnabled(false);
		    selectRelatives.refreshDatos();
		    selectRelatives.setRelativeNull();
		}
	    }
	}
    }
    
    private void loadPersonMgmt() {
	if (!dataRowRelatives.isEmpty()) {
	    PersonMgmt personMgmt = new PersonMgmt();
	    personMgmt.setParentList(parentList);
	    personMgmt.setPerson(personRelative);
	    personMgmt.loadPestanias();      
	    ExtendedInternalFrame personMgmtContainer = new ExtendedInternalFrame("Modificar datos de: " + dataRowRelatives.elementAt(8).toString().toUpperCase()+", "+dataRowRelatives.elementAt(7).toString().toUpperCase());
	    personMgmtContainer.setCentralPanel(personMgmt);
	    personMgmtContainer.show();
	}
   
    }private void btnModifyReltives_actionPerformed(ActionEvent e) {
	loadPersonMgmt();
    }

    private void btnAddAddres_actionPerformed(ActionEvent e) {
	addAdress();
    }
    
    private void addAdress(){
	addressPanel.setIdPerson(person.getIdPerson());
	addressPanel.saveData();
	addressPanel.setTabbIndex(0);   
	refreshGridAddresses();
	btnDeleteAddress.setEnabled(false);
    }

    private void btnDeleteAddress_actionPerformed(ActionEvent e) {
	if (direccionPersona != null) {
	    if (Advisor.question("Borrar Dirección", "¿Está seguro de borrar la direccion seleccionada?")) {        
		if (LibSQL.getInt("personalfiles.delAddressPerson", "" + direccionPersona.getIdAddressPerson()) == 0) {
		    refreshGridAddresses();       
		    addressPanel.clearData();
		    addressPanel.setDireccionPersonaNull();
		    btnDeleteAddress.setEnabled(false);		    		    
		}
	    }
	}
    }

    public boolean isFechasSuperpuestas(Date dateStart, Date dateEnd){
        boolean band = false;
        int cantfilas = listPanelJobs.getTable().getRowCount();
        int i = 0;
        Date dateIni, dateFin;
        listPanelJobs.selectAllItems(true);
        Vector vectorDatos = listPanelJobs.getSelectedsVector();
        try {
            
            while ((i < cantfilas)&&(band == false)){              
                Vector vectorAux = (Vector)vectorDatos.get(i);
                i++;                
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                dateIni = sdf.parse(vectorAux.get(6).toString());
                dateFin = sdf.parse(vectorAux.get(7).toString());
                
                //1º caso                   
                if ((dateStart.after(dateIni) && dateStart.before(dateFin)) && (dateEnd.after(dateIni) && dateEnd.before(dateFin))){
                    band = true;
                } 
                //2º caso
                if ((dateStart.after(dateIni) && dateStart.before(dateFin)) && (dateEnd.after(dateIni) && dateEnd.after(dateFin))){
                    band = true;
                }
                //3º caso
                if ((dateStart.before(dateIni) && dateStart.before(dateFin)) && (dateEnd.after(dateIni) && dateEnd.before(dateFin))){
                    band = true;
                }
                //4º caso
                if ((dateStart.before(dateIni) && dateStart.before(dateFin)) && (dateEnd.after(dateIni) && dateEnd.after(dateFin))){
                    band = true;
                } 
                //5º caso                 
                 if (dateStart.equals(dateIni) && dateEnd.equals(dateFin)){
                     band = true;
                 } 
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        
        return band;
    }
    
    public GridPanel getListPanelJobs() {
	return listPanelJobs;
    }
}
