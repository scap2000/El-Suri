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
 * RecursosPorDependencia.java
 *
 * */
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

