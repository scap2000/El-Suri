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
 * FrameContainer.java
 *
 * */
package org.digitall.apps.projecttask.interfaces;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JDesktopPane;

import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicPanel;

public class FrameContainer extends BasicInternalFrame {

    private BasicPanel panel;
    private int panelType;
    public static final int TASKTREE = 1;
    public static final int TASKMGMT = 2;
    public static final int USERLIST = 3;
    public static final int TASKBYEMPLOYEEMGMT = 4;
    public static final int PERSON = 5;
    public static final int BUDGETLIST = 6;
    public static final int EXPENDITUREACCOUNT = 7;
    public static final int BUDGETEXPENDITUREACCOUNTLIST = 8;
    public static final int BUDGETEXPENDITUREACCOUNT = 9;
    public static final int COSTSCENTRE = 10;
    public static final int BUDGETCOSTCENTRE = 11;
    public static final int BUDGETCOSTSCENTRELIST = 12;
    public static final int BUDGETCOSTSCENTREAMOUNT = 13;
    public static final int BUDGETEXPENDITUREACCOUNTMGMT = 14;
    public static final int ACCOUNTMGMT = 15;
    public static final int ACCOUNTSLIST = 16;
    public static final int ACCOUNTDETAILS = 17;
    private String title = "";
    private JDesktopPane parentDesktop;
    private Component parentList;
    private Object params;

    public FrameContainer(int _panelType, JDesktopPane _parentDesktop, Component _parentList, Object _params) {
	try {
	    params = _params;
	    parentList = _parentList;
	    parentDesktop = _parentDesktop;
	    panelType = _panelType;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.getContentPane().setLayout(null);
	this.setSize(new Dimension(416, 360));
	this.setClosable(true);
	this.setIconifiable(true);
	this.setTitle(title);
	this.setSize(panel.getWidth() + 10, panel.getHeight() + 40);
	this.getContentPane().add(panel, null);
    }

}
