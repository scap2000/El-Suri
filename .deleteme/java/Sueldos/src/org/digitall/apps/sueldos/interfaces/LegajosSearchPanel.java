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
 * LegajosSearchPanel.java
 *
 * */
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


