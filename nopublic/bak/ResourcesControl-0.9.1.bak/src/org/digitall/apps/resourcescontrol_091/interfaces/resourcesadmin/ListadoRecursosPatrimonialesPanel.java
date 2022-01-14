package org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.apps.personalfiles.classes.Dependencia;
import org.digitall.apps.personalfiles.interfaces.PersonalPanel;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class ListadoRecursosPatrimonialesPanel extends BasicPrimitivePanel{
    private BasicPanel bpCentro = new BasicPanel();
    private BasicPanel bpNorte = new BasicPanel();    
    private int[] sizeColumnList = { 92, 98, 64, 96, 97};
    private Vector dataRow = new Vector();
    private GridPanel listaRecursosDistSel = new GridPanel(5000, sizeColumnList, "Patrimonios", dataRow);
    private Vector headerList = new Vector();
    private RecursosVariosPanel recursosVariosPanel;
    private PersonalPanel personalPanel;
    private BorderLayout borderLayout1 = new BorderLayout();
    private CloseButton btnClose = new CloseButton();
    private SaveDataButton btnSave = new SaveDataButton();   
    private TFInput tfiRecurso = new TFInput(DataTypes.STRING,"Resource",false);    
    private int idPersona = -1;
    private Dependencia dependencia;
    private boolean isPersonAssing;

    public ListadoRecursosPatrimonialesPanel(boolean _isPersonAssing) {
	try {
	    isPersonAssing = _isPersonAssing;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    private void jbInit() throws Exception {
	this.setSize(new Dimension(500, 400));
	this.setPreferredSize(new Dimension(500, 400)); 
	bpCentro.setLayout(borderLayout1);
	bpNorte.setLayout(null);
	bpNorte.setSize(new Dimension(500, 30));
	bpNorte.setPreferredSize(new Dimension(500, 60));
	tfiRecurso.setEnabled(false);
	bpNorte.add(tfiRecurso, null);
	this.add(bpNorte, BorderLayout.NORTH);        
	//bpCentro.setBorder(BorderPanel.getBorderPanel("Listado de patrimonios disponibles"));	
	this.add(bpCentro, BorderLayout.CENTER);		
	bpCentro.add(listaRecursosDistSel, BorderLayout.CENTER);
	setHeaderList();
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }   
	);
	btnSave.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnSave_actionPerformed(e);
				}

			    }   
	);
	tfiRecurso.setBounds(new Rectangle(10, 15, 480, 35));
	this.addButton(btnClose);
	this.addButton(btnSave);
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
	listaRecursosDistSel.getTable().addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
					    //loadPerson();
					    if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						//loadPersonMgmt();
					    }
					}
				    }
	);	
	    
	listaRecursosDistSel.setParams("personalfiles.getAllPatrimonios", "-1", headerList);	
    }
    
    public void refresh() {
	int posSeleccionado = recursosVariosPanel.getGrilla().getSelectedRow();
	String idRecursoSeleccionado = recursosVariosPanel.getGrillaPanelRecursos().getValuesAt(0).get(posSeleccionado).toString();
	String params = idRecursoSeleccionado;      
	listaRecursosDistSel.refresh(params);	
	
	String nombreRecurso = recursosVariosPanel.getGrillaPanelRecursos().getValuesAt(1).get(posSeleccionado).toString();
	tfiRecurso.setValue(nombreRecurso);
    }
    
    public void setRecursosVariosPanel(RecursosVariosPanel _recursosVariosPanel) {    
	recursosVariosPanel = _recursosVariosPanel;
	}
    
    public GridPanel getGrillaPanelPatrimonio () {
	return listaRecursosDistSel;
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {    
	getParentInternalFrame().close();       
    }
    
    private void btnSave_actionPerformed(ActionEvent e) {      
	//Abrir ventana de asignacion
	int cantRecursosDistinguiblesSeleccionados = listaRecursosDistSel.getSelectedsID().size();
	if(cantRecursosDistinguiblesSeleccionados > 0){
	    ExtendedInternalFrame stockMgmtContainer = new ExtendedInternalFrame("Asignacíon de patrimonio");
	    AsignacionPatrimonioRecursoPanel asignacionPatrimonioRecursoPanel = new AsignacionPatrimonioRecursoPanel(isPersonAssing);
	    asignacionPatrimonioRecursoPanel.setListadoRecursosPatrimonialesPanel(this);
	    asignacionPatrimonioRecursoPanel.setPersonalPanel(personalPanel);
	    asignacionPatrimonioRecursoPanel.setRecursosAsignar(listaRecursosDistSel.getSelectedsID());
	    asignacionPatrimonioRecursoPanel.setIdPerson(idPersona);
	    asignacionPatrimonioRecursoPanel.loadDataRecurso();
	    stockMgmtContainer.setCentralPanel(asignacionPatrimonioRecursoPanel);
	    stockMgmtContainer.show();              
	}else{
	    Advisor.messageBox("Debe selecccionar al menos un recurso distinguible","Error");
	}
    }
    
    public void setIdPerson(int _idPersona){
	idPersona = _idPersona;
    }

    public int getIdPersona() {
	return idPersona;
    }

    public RecursosVariosPanel getRecursosVariosPanel() {
	return recursosVariosPanel;
    }

    public void setPersonalPanel(PersonalPanel personalPanel) {
	this.personalPanel = personalPanel;
    }

    public PersonalPanel getPersonalPanel() {
	return personalPanel;
    }

    public void setDependencia(Dependencia dependencia) {
	this.dependencia = dependencia;
    }

    public Dependencia getDependencia() {
	return dependencia;
    }
}
