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
 * StatusBar.java
 *
 * */
package org.digitall.lib.components.grid;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JProgressBar;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

//

public class StatusBar extends BasicDialog {

    private JProgressBar progressBar = new JProgressBar();
    private Component parent = null;
    private BasicLabel iconSearch = new BasicLabel(IconTypes.statusBarIcon_16x16);
    private BasicLabel lblTitle = new BasicLabel();
    private CloseButton btnClose = new CloseButton();
    private Thread threadTask;
    protected boolean actionCancel = false;
    private BasicPanel jp = new BasicPanel();

    public StatusBar(Component _parent) {
	try {
	    parent = _parent;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	parent.setEnabled(false);
	iconSearch.setBounds(new Rectangle(15, 15, 40, 35));
	lblTitle.setText(Environment.lang.getProperty("StatusBarText"));
	//"Realizando busqueda, espere por favor...");
	lblTitle.setBounds(new Rectangle(70, 15, 315, 15));
	lblTitle.setFont(new Font("Dialog", 1, 11));
	btnClose.setBounds(new Rectangle(385, 25, 40, 25));
	btnClose.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnClose_actionPerformed(e);
		    }

		});
	jp.setBounds(new Rectangle(70, 30, 290, 20));
	jp.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	jp.setLayout(null);
	jp.setSize(new Dimension(290, 20));
	this.setSize(new Dimension(444, 90));
	this.getContentPane().setLayout(null);
	this.setTitle(Environment.lang.getProperty("StatusBarTitle"));
	//"Buscando...");
	progressBar.setBounds(new Rectangle(10, 5, 270, 10));
	progressBar.setForeground(new Color(41, 13, 151));
	progressBar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	progressBar.setBackground(Color.white);
	progressBar.setToolTipText(Environment.lang.getProperty("StatusBarTitle"));
	jp.add(progressBar, null);
	this.getContentPane().add(jp, null);
	this.getContentPane().add(btnClose, null);
	this.getContentPane().add(lblTitle, null);
	this.getContentPane().add(iconSearch, null);
	progressBar.setIndeterminate(true);
    }

    public void disposeme() {
	parent.setEnabled(true);
	this.setVisible(false);
    }

    public void startTask(Thread _task) {
	threadTask = _task;
	threadTask.start();
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	threadTask.stop();
	actionCancel = true;
	disposeme();
    }

    public boolean getActionCancel() {
	return actionCancel;
    }

}
