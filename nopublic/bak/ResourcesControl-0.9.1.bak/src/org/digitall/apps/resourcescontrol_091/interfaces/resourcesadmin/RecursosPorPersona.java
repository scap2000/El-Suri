package org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.Vector;

import org.digitall.common.personalfiles.classes.Persona;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.org.Persons;

public class RecursosPorPersona extends BasicPanel{

    private int[] sizeColumnList = {165 , 55 , 34};
    private Vector dataRow = new Vector();
    private GridPanel grilla = new GridPanel(5000, sizeColumnList, "Recursos por Persona", dataRow);
    private Vector headerList = new Vector();
    private BorderLayout borderLayout1 = new BorderLayout();    
    private Persons person = new Persons();
    private Persona persona;
    private ListadoRecursosPatrimonialesDesAsignPanel lrpDesAssig;
    private PopupMenuResourcesAsigned popup;
    
    public RecursosPorPersona() {
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
	MouseListenerTabla mouseListenerTabla = new MouseListenerTabla();
	grilla.getTable().addMouseListener(mouseListenerTabla);	
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
	grilla.setParams("personalfiles.getAllRecursosPorPersona", "-1", headerList); 
    }
    
    private void clickDerecho(MouseEvent e){
	popup = new PopupMenuResourcesAsigned();
	//popup
	System.out.println("sera dependencia??"+this.getParent());
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
	popup.setPersona(persona);
	popup.show(e.getComponent(),x,y);
    }
    
    public void refresh() {
	grilla.refresh(""+ persona.getIdPerson());
    }

    public void setGrilla(GridPanel grilla) {
	this.grilla = grilla;
    }

    public GridPanel getGrilla() {
	return grilla;
    }

    public class MouseListenerTabla implements MouseListener{

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {	    
	    if (e.getClickCount() == 2) {				
		
	    }
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

    }
    
    public void setPerson(Persons _person){
        person = _person;
        refresh();
    }
    
    public void setPersona(Persona _persona){
	persona = _persona;
    }

}
