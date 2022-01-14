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
 * FindFiles.java
 *
 * */
package org.digitall.apps.systemmanager;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.BasicTextField;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;

//

public class FindFiles extends BasicDialog {

    private BasicPanel jPanel1 = new BasicPanel();
    private BasicLabel lblFile = new BasicLabel();
    private BasicLabel lblComment = new BasicLabel();
    private BasicTextField tfFile = new BasicTextField();
    private BasicScrollPane jScrollPane1 = new BasicScrollPane();
    private JTextArea taComment = new JTextArea();
    private FindButton bFind = new FindButton();
    private AcceptButton bAcept = new AcceptButton();
    private CloseButton bCancel = new CloseButton();

    public FindFiles() {
	this(null, "", false);
    }

    public FindFiles(Frame parent, String title, boolean modal) {
	super(parent, title, modal);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(348, 186));
	this.getContentPane().setLayout(null);
	this.setTitle("Find Files");
	jPanel1.setBounds(new Rectangle(5, 10, 330, 110));
	jPanel1.setLayout(null);
	jPanel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	lblFile.setText("File:");
	lblFile.setBounds(new Rectangle(10, 13, 60, 15));
	lblFile.setFont(new Font("Dialog", 1, 11));
	lblFile.setHorizontalAlignment(SwingConstants.RIGHT);
	lblComment.setText("Comment:");
	lblComment.setBounds(new Rectangle(10, 35, 60, 15));
	lblComment.setFont(new Font("Dialog", 1, 11));
	lblComment.setHorizontalAlignment(SwingConstants.RIGHT);
	tfFile.setBounds(new Rectangle(75, 10, 195, 20));
	jScrollPane1.setBounds(new Rectangle(75, 40, 245, 60));
	jScrollPane1.getViewport().setLayout(null);
	taComment.setBounds(new Rectangle(0, 0, 245, 60));
	bFind.setBounds(new Rectangle(275, 8, 45, 25));
	bFind.setSize(new Dimension(40, 25));
	bFind.setMnemonic('F');
	bAcept.setBounds(new Rectangle(295, 125, 40, 25));
	bAcept.setSize(new Dimension(40, 25));
	bAcept.setMnemonic('A');
	bCancel.setBounds(new Rectangle(5, 125, 40, 25));
	bCancel.setSize(new Dimension(40, 25));
	bCancel.setMnemonic('C');
	jPanel1.add(bFind, null);
	jScrollPane1.getViewport().add(taComment, null);
	jPanel1.add(jScrollPane1, null);
	jPanel1.add(tfFile, null);
	jPanel1.add(lblFile, null);
	jPanel1.add(lblComment, null);
	this.getContentPane().add(bCancel, null);
	this.getContentPane().add(bAcept, null);
	this.getContentPane().add(jPanel1, null);
	org.digitall.lib.components.ComponentsManager.centerWindow(this);
    }

}
