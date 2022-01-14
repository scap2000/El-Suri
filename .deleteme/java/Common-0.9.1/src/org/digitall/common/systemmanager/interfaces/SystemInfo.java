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
 * SystemInfo.java
 *
 * */
package org.digitall.common.systemmanager.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Calendar;

import javax.swing.JEditorPane;
import javax.swing.JPanel;

import javax.swing.Timer;

import org.digitall.lib.components.JArea;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.network.NetworkConfig;
import org.digitall.lib.sql.LibSQL;

public class SystemInfo extends BasicPrimitivePanel {

    private TFInput tfHost = new TFInput(DataTypes.STRING, "Server", false);
    private TFInput tfDatabase = new TFInput(DataTypes.STRING, "DBName", false);
    private TFInput tfUser = new TFInput(DataTypes.STRING, "User", false);
    private TFInput tfVersion = new TFInput(DataTypes.STRING, "Versión del Sistema", false);
    private JArea jaAbout = new JArea();
    private JPanel content = new JPanel();
    private GridLayout gridLayout1 = new GridLayout();

    public SystemInfo() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(524, 284));
	content.add(tfHost, null);
	content.add(tfDatabase, null);
	content.add(tfUser, null);
	content.add(tfVersion, null);
	jaAbout.setContentType("text/html");
	//jaAbout.setContentType("text/java");
	jaAbout.setPage(Environment.mainClass.getResource("license.txt"));
	jaAbout.setEditable(false);
	jaAbout.setBounds(new Rectangle(0, 195, 410, 40));
	jaAbout.setPreferredSize(new Dimension(8, 75));
	content.setLayout(gridLayout1);
	content.setSize(new Dimension(410, 160));
	content.setMaximumSize(new Dimension(32767, 160));
	content.setMinimumSize(new Dimension(1, 160));
	content.setPreferredSize(new Dimension(1, 160));
	content.setBounds(new Rectangle(0, 0, 410, 160));
	gridLayout1.setColumns(1);
	gridLayout1.setRows(4);
	this.add(content, BorderLayout.NORTH);
	this.add(jaAbout, BorderLayout.CENTER);
	tfHost.setEditable(false);
	tfDatabase.setEditable(false);
	tfUser.setEditable(false);
	tfVersion.setEditable(false);
	refresh();
	Timer _refreshTimer = new Timer(10000, new ActionListener() {
	
					 public void actionPerformed(ActionEvent e) {
					    refresh();
					 }
					 
				     }
		);
	_refreshTimer.start();
    }

    public void refresh() {
	tfHost.setValue(NetworkConfig.getServerIP());
	tfDatabase.setValue(LibSQL.getDataBase());
	tfUser.setValue(Environment.sessionUser);
	tfVersion.setValue(Environment.SYSTEM_VERSION + ", corriendo en Java " + System.getProperty("java.runtime.version"));
    }

    
}
