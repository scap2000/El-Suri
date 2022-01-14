package org.digitall.apps.sueldos.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;

import java.util.Vector;

import javax.swing.JList;

import org.digitall.common.personalfiles.classes.Legajo;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.sql.LibSQL;

public class PanelAsignedHaberes extends BasicPrimitivePanel{

    private BasicPanel content = new BasicPanel();
    private JTransLista transListHaberes = new JTransLista(true,"sueldos.getAllHaberes","","sueldos.getHaberesByModeloUser","");
    private Legajo legajo;

    public PanelAsignedHaberes() {

	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	content.setLayout(null);
	transListHaberes.setMadre("Haberes");
	transListHaberes.setHija("Haberes Asignados");
	this.setSize(new Dimension(367, 308));
	this.setPreferredSize(new Dimension(333, 321));
	content.setSize(new Dimension(429, 308));
	content.setPreferredSize(new Dimension(400, 300));
	transListHaberes.setBounds(new Rectangle(0, 0, 405, 290));
	transListHaberes.setBounds(new Rectangle(5, 0, 360, 305));
	content.add(transListHaberes, null);
	this.add(content,BorderLayout.CENTER);
    }
    
    public JList getListAsignados(){
	return(transListHaberes.getListHija());
    }
    
    public JList getListSinAsignar(){
	return(transListHaberes.getListMadre());
    }
    
    public void cargarListasPorLegajo(){
        transListHaberes.setStoredHija("sueldos.getAllHaberesForLegajo");
        transListHaberes.setParamsHija("" + legajo.getidLegajo());
        transListHaberes.reload();
    }

    public void setLegajo(Legajo legajo) {
        this.legajo = legajo;
    }
}
