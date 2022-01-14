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

public class PanelAsignedDescuentos extends BasicPrimitivePanel{

    private BasicPanel content = new BasicPanel();
    private JTransLista transListDescuentos = new JTransLista(true,"sueldos.getAllDescuentos","","sueldos.getDescuentosByModeloUser","");
    private Legajo legajo;

    public PanelAsignedDescuentos() {

	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	content.setLayout(null);
	transListDescuentos.setMadre("Descuentos");
	transListDescuentos.setHija("Descuentos Asignados");
	this.setSize(new Dimension(367, 308));
	this.setPreferredSize(new Dimension(333, 321));
	content.setSize(new Dimension(429, 308));
	content.setPreferredSize(new Dimension(400, 300));
	transListDescuentos.setBounds(new Rectangle(0, 0, 405, 290));
	transListDescuentos.setBounds(new Rectangle(5, 0, 360, 305));
	content.add(transListDescuentos, null);
	this.add(content,BorderLayout.CENTER);
    }
    
    public JList getListAsignados(){
	return(transListDescuentos.getListHija());
    }
    
    public JList getListSinAsignar(){
	return(transListDescuentos.getListMadre());
    }
    
    public void cargarListasPorLegajo(){
        transListDescuentos.setStoredHija("sueldos.getAllDescuentosForLegajo");
        transListDescuentos.setParamsHija("" + legajo.getidLegajo());
        transListDescuentos.reload();
    }

    public void setLegajo(Legajo legajo) {
        this.legajo = legajo;
    }
}
