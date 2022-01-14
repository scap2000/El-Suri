package org.digitall.apps.sueldos.interfaces;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.common.personalfiles.classes.Legajo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.BackGridButton;
import org.digitall.lib.components.buttons.FirstGridButton;
import org.digitall.lib.components.buttons.LastGridButton;
import org.digitall.lib.components.buttons.NextGridButton;
import org.digitall.lib.components.buttons.RefreshGridButton;
import org.digitall.lib.sql.LibSQL;

public class LegajosSearchPanel extends BasicPanel{
    private NextGridButton btnNext = new NextGridButton();
    private BackGridButton btnBack = new BackGridButton();
    private FirstGridButton btnFirst = new FirstGridButton();
    private LastGridButton btnLast = new LastGridButton();
    private RefreshGridButton btnRefresh = new RefreshGridButton();
    private Legajo legajo = new Legajo();
    private int startLegajos = -1;
    private int endLegajos = -1;
    private int actualLegajo = -1;
    private ReciboSueldoPanel recibosPanel = null;
    private LegajosPanelMain legajosPanel = null;
    

    public LegajosSearchPanel(ReciboSueldoPanel _recibosPanel) {
	recibosPanel = _recibosPanel;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public LegajosSearchPanel(LegajosPanelMain _legajosPanel) {
	legajosPanel = _legajosPanel;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public LegajosSearchPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	inicializar();
	btnFirst.setEnabled(false);
	btnBack.setEnabled(false);
	btnNext.setEnabled(true);
	btnLast.setEnabled(true);
	btnRefresh.setEnabled(true);
	this.setSize(new Dimension(183, 30));
	this.setLayout(null);
	btnFirst.setBounds(new Rectangle(5, 5, 35, 20));
	btnBack.setBounds(new Rectangle(40, 5, 35, 20));
	btnNext.setBounds(new Rectangle(75, 5, 35, 20));
	btnLast.setBounds(new Rectangle(110, 5, 35, 20));
	btnRefresh.setBounds(new Rectangle(145, 5, 35, 20));
	btnRefresh.setToolTipText("Actualizar buscador");
	btnFirst.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					    btnFirst_actionPerformed(e);
					}
				    }
	);
	btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					    btnBack_actionPerformed(e);
					}
				    }
	);
	btnNext.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					    btnNext_actionPerformed(e);
					}
				    }
	);
	btnLast.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					    btnLast_actionPerformed(e);
					}
				    }
	);
	btnRefresh.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					    btnRefresh_actionPerformed(e);
					}
				    }
	);
	this.setBorder(BorderPanel.getBorderPanel(""));
	this.add(btnFirst, null);
	this.add(btnBack, null);
	this.add(btnNext, null);
	this.add(btnLast, null);
	this.add(btnRefresh, null);
    }
    
    public void inicializar() {
        cargarIndex(true);
        cargarLegajoActual();
    }
    
    public void upDateLegajosSearchIndex(boolean _atStart){
	cargarIndex(_atStart);
    }
    
    public void cargarIndex(boolean _atStart){
	ResultSet result = LibSQL.exFunction("sueldos.getIndexLegajos","");
	try {
	    result.next();
	    startLegajos = result.getInt("start");
	    endLegajos = result.getInt("end");
	    if(_atStart){
	        actualLegajo = startLegajos;
	    }
	} catch (NullPointerException e) {
	    
	} catch (SQLException e) {
	    // TODO
	     startLegajos = -1;
	     endLegajos = -1;
	     enabledAll(false);
	}
    }
    
    private void enabledAll(boolean _enabled){
	btnFirst.setEnabled(_enabled);
	btnBack.setEnabled(_enabled);
	btnNext.setEnabled(_enabled);
	btnLast.setEnabled(_enabled);
    }
    
    private void cargarLegajoActual(){
	if(actualLegajo != -1){
	    legajo.retriveDataForNumber(actualLegajo);
	    updateFlechas();
	    if(recibosPanel != null){
		recibosPanel.setLegajo(legajo);
	    }else{
	        if(legajosPanel != null){
	            legajosPanel.setLegajo(legajo);
	        }
	    }
	}
    }
    private void updateFlechas(){
	boolean test = (actualLegajo == startLegajos);
	if(test){
	    btnFirst.setEnabled(!test);
	    btnBack.setEnabled(!test);
	    btnNext.setEnabled(test);
	    btnLast.setEnabled(test);
	    if(actualLegajo == endLegajos){
	        btnFirst.setEnabled(!test);
	        btnBack.setEnabled(!test);
	        btnNext.setEnabled(!test);
	        btnLast.setEnabled(!test);
	    }
	}else{
	    test = (actualLegajo == endLegajos);
	    if(test){
	        btnFirst.setEnabled(test);
	        btnBack.setEnabled(test);
	        btnNext.setEnabled(!test);
	        btnLast.setEnabled(!test);
	    }else{
	        btnFirst.setEnabled(!test);
	        btnBack.setEnabled(!test);
	        btnNext.setEnabled(!test);
	        btnLast.setEnabled(!test);
	    }
	}
    }
    
    private void btnFirst_actionPerformed(ActionEvent e) {
	pressFirst();
    }
    
    private void btnBack_actionPerformed(ActionEvent e) {
	pressBack();
    }
    
    private void btnNext_actionPerformed(ActionEvent e) {
	pressNext();
    }
    
    private void btnLast_actionPerformed(ActionEvent e) {
	pressLast();
    }
    
    private void btnRefresh_actionPerformed(ActionEvent e) {
	pressRefresh();
    }
    
    public void pressFirst(){
	actualLegajo = startLegajos;
	//updateFlechas();
	cargarLegajoActual();
    }
    
    public void pressBack(){
	actualLegajo = legajo.getNumPreview(actualLegajo);
	//updateFlechas();
	cargarLegajoActual();
    }
    
    public void pressNext(){
	actualLegajo = legajo.getNumNext(actualLegajo);
	//updateFlechas();
	cargarLegajoActual();
    }
    
    public void pressLast(){
	actualLegajo = endLegajos;
	//updateFlechas();
	cargarLegajoActual();
    }
    
    public void pressRefresh(){
	cargarIndex(true);
	cargarLegajoActual();
    }
    
    public void setLegajo(Legajo legajo) {
	this.legajo = legajo;
    }

    public Legajo getLegajo() {
	return legajo;
    }

    public int setActualLegajo(int _actualLegajo) {
	if(legajo.existNumber(_actualLegajo)){
	    this.actualLegajo = _actualLegajo;
	    //updateFlechas();
	    cargarLegajoActual();
	}else{
	    Advisor.messageBox("No existe el legajo con el número: "+_actualLegajo,"Error");
	}
	return(actualLegajo);
    }
    
    public void upDate(){
	cargarIndex(false);
    }

    public int getActualLegajo() {
	return actualLegajo;
    }

    public void setRecibosPanel(ReciboSueldoPanel recibosPanel) {
	this.recibosPanel = recibosPanel;
	cargarLegajoActual();
    }

    public ReciboSueldoPanel getRecibosPanel() {
	return recibosPanel;
    }

    public void setLegajosPanel(LegajosPanelMain legajosPanel) {
	this.legajosPanel = legajosPanel;
	cargarLegajoActual();
    }

    public LegajosPanelMain getLegajosPanel() {
	return legajosPanel;
	
    }

    public void setStartLegajos(int startLegajos) {
        this.startLegajos = startLegajos;
    }

    public int getStartLegajos() {
        return startLegajos;
    }

    public void setEndLegajos(int endLegajos) {
        this.endLegajos = endLegajos;
    }

    public int getEndLegajos() {
        return endLegajos;
    }
}


