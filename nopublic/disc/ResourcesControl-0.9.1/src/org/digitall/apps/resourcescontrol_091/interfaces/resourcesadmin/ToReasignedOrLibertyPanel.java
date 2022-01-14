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
 * ToReasignedOrLibertyPanel.java
 *
 * */
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

