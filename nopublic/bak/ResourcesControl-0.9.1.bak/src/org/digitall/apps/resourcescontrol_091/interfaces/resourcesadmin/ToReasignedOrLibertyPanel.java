package org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Vector;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.grid.GridPanel;

public class ToReasignedOrLibertyPanel extends BasicPrimitivePanel{

    private BasicPanel content = new BasicPanel();
    private int[] sizeColumnList = { 92, 98, 64, 96, 97};
    private Vector dataRow = new Vector();
    private GridPanel grilla = new GridPanel(30, sizeColumnList, "Recursos a", dataRow);
    private Vector headerList = new Vector();
    private boolean isLiberty;
    private boolean isReasigned;
	
    public ToReasignedOrLibertyPanel(boolean _isLiberty) {
	try {
	    isLiberty = _isLiberty;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(498, 160));
	grilla.setBounds(new Rectangle(10, 5, 480, 150));
	content.setLayout(null);
	setTitelGrid();
	content.add(grilla, null);
	this.add(content, null);
	setHeaderList();
	
    }
    
    private void setTitelGrid(){
	if(isLiberty){
	    grilla.setTitle("Recursos a Liberar");
	}else{
	    grilla.setTitle("Recursos a Reasignar");
	}
    }
    
    private void setHeaderList() {      
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("Recurso");
	headerList.addElement("Marca");
	headerList.addElement("Cant.");
	grilla.setParams("personalfiles.getAllRecursosPorPersona", "-1", headerList);
    }

    public void refresh() {
	String params = "";      
	grilla.refresh(params);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Recursos asigado a una persona o dependencia");
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

    public void setIsLiberty(boolean isLiberty) {
	this.isLiberty = isLiberty;
    }

    public boolean isIsLiberty() {
	return isLiberty;
    }

    public void setIsReasigned(boolean isReasigned) {
	this.isReasigned = isReasigned;
    }

    public boolean isIsReasigned() {
	return isReasigned;
    }
}

