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
 * PanelAsignedDescuentos.java
 *
 * */
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
