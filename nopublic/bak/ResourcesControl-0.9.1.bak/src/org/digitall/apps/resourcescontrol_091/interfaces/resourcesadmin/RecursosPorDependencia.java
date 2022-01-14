package org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.apps.personalfiles.classes.Dependencia;
import org.digitall.apps.personalfiles.interfaces.PersonalPanel;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.grid.GridPanel;

public class RecursosPorDependencia extends BasicPanel{
    private int[] sizeColumnList = {165, 55, 34};
    private Vector dataRow = new Vector();
    private GridPanel grilla = new GridPanel(5000, sizeColumnList, "Recursos por Dependencia", dataRow);
    private Vector headerList = new Vector();
    private BorderLayout borderLayout1 = new BorderLayout();    
    private ListadoRecursosPatrimonialesPanel listadoRecursosPatrimonialesPanel;
    private Dependencia dependencia;
    private PersonalPanel personalPanel;
    private PopupMenuResourcesAsigned popup;
    
    public RecursosPorDependencia() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setPreferredSize(new Dimension(290, 300));
	this.setSize(new Dimension(290, 300));
	this.setLayout(borderLayout1);
	grilla.setPreferredSize(new Dimension(290, 300));
	grilla.setSize(new Dimension(290, 300));
	this.add(grilla, BorderLayout.CENTER);
	setHeaderList();
    }
    
    private void setHeaderList() {      
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("Recurso");
	headerList.addElement("Marca");
	headerList.addElement("Cant.");
	grilla.getTable().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1 && e.getButton() == e.BUTTON3) {
		    clickDerecho(e);                
		}
	    }
	});
	grilla.setParams("personalfiles.getAllRecursosPorDependencia","-1", headerList); 
    }
    
    private void clickDerecho(MouseEvent e){
	popup = new PopupMenuResourcesAsigned();
	int x = (int)e.getPoint().getX();
	int y = (int)e.getPoint().getY();
	int filaSeleccionada = grilla.getTable().rowAtPoint(e.getPoint());
	grilla.selectAllItems(false);
	grilla.getTable().setValueAt(true,filaSeleccionada,0);
	grilla.getTable().getSelectionModel().setSelectionInterval(filaSeleccionada,filaSeleccionada);
	int idRecurso = Integer.parseInt(grilla.getSelectedsID().elementAt(0).toString());
	Resource recurso = new Resource();
	recurso.setIdResource(idRecurso);
	recurso.retrieveData();
	popup.setRecurso(recurso);
	System.out.println("Dependencia: "+dependencia.getNombre());
	popup.setDependencia(dependencia);
	popup.show(e.getComponent(),x,y);
    }
    
    public void refresh() {
	grilla.refresh(""+ dependencia.getIdDep());
    }
   
    public ListadoRecursosPatrimonialesPanel getListadoRecursosPatrimonialesPanel() {
	return listadoRecursosPatrimonialesPanel;
    }

    public Dependencia getDependencia() {
	return dependencia;
    }

    public void setPersonalPanel(PersonalPanel personalPanel) {
	this.personalPanel = personalPanel;
    }

    public PersonalPanel getPersonalPanel() {
	return personalPanel;
    }

    public GridPanel getGrilla() {
	return grilla;
    }

    public void setDependencia(Dependencia _dependencia){
	dependencia = _dependencia;	
	if(popup != null){
	    popup.setDependencia(dependencia);
	}
    }
}

