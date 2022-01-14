package org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;

public class IngresarStockRecursoAAsignar extends BasicPrimitivePanel{

    private BasicPanel bpCentro = new BasicPanel();
    private TFInput tfiStockAsignado = new TFInput(DataTypes.STRING,"",true);
    private SaveDataButton btnSave = new SaveDataButton();    
    private CloseButton btnClose = new CloseButton();
    private int idPerson = -1;
    private int idDependencia = -1;
    private int idResource;
    private RecursosVariosPanel recursosVariosPanel;

    public IngresarStockRecursoAAsignar() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setPreferredSize(new Dimension(370, 113));
	this.setSize(new Dimension(370, 113));
	bpCentro.setLayout(null);
	bpCentro.setBorder(BorderPanel.getBorderPanel("Ingresar cantidad stock"));
	tfiStockAsignado.setBounds(new Rectangle(10, 25, 100, 35));
	bpCentro.add(tfiStockAsignado, null);
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
	this.add(bpCentro, BorderLayout.CENTER);
	this.addButton(btnClose);
	this.addButton(btnSave);	
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	recursosVariosPanel.getGrillaPanelRecursos().selectAllItems(false);
	getParentInternalFrame().close();       
    }
    
    private void btnSave_actionPerformed(ActionEvent e) {
	//Pregunto para saber que tabla estamos por modificar
	if (idPerson != -1)  {
	    if (saveDataResourcesPerson()) {           
	        recursosVariosPanel.refresh();         
	        getParentInternalFrame().close();
	    }	
	} else {
	    if (saveDataResourcesDependency()) {           
	        recursosVariosPanel.refresh();         
	        getParentInternalFrame().close();
	    }   
	}
		
	
    }
    
    public boolean saveDataResourcesPerson() {
	boolean resul = false;
	String params =  idResource +","+ idPerson+","+tfiStockAsignado.getValue();
	int result = LibSQL.getInt("personalfiles.addResourcesPerson", params);
	if (result > 0){
	    resul =  true;
	} else {	   	    
	    Advisor.messageBox("¡Ocurrio un error al grabar los datos!", "Error");
	}       
	return resul;
    }
    
    public boolean saveDataResourcesDependency() {
	boolean resul = false;
	String params =  idResource +","+ idDependencia +","+tfiStockAsignado.getValue();
	int result = LibSQL.getInt("personalfiles.addResourcesDependency", params);
	if (result > 0){
	    resul =  true;
	} else {                    
	    Advisor.messageBox("¡Ocurrio un error al grabar los datos!", "Error");
	}       
	return resul;
    }
    
    public void setIdPerson(int _idPerson) {
	idPerson = _idPerson;
    }
    
    public void setIdResurce(int _idResource) {
	idResource = _idResource;
    }
    
    public void setIdDependencia(int _idDependencia) {
	idDependencia = _idDependencia;
    }    
    
    public void setRecursosVariosPanel(RecursosVariosPanel _recursosVariosPanel) {
	recursosVariosPanel = _recursosVariosPanel;
    }
}
