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
 * ManagementFiles.java
 *
 * */
package org.digitall.apps.systemmanager;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.border.EtchedBorder;

import org.digitall.lib.components.ComponentsManager;
import org.digitall.lib.components.basic.BasicCombo;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.BasicTextField;
import org.digitall.lib.components.buttons.AddComboButton;
import org.digitall.lib.components.buttons.AddMoreButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.GoButton;
import org.digitall.lib.components.buttons.NextWizardButton;
import org.digitall.lib.components.buttons.PreviousWizardButton;
import org.digitall.lib.components.buttons.UnAssignButton;

//

public class ManagementFiles extends BasicDialog {

    private BasicPanel jPanel1 = new BasicPanel();
    private BasicTextField jTextField2 = new BasicTextField();
    private BasicLabel lblPath = new BasicLabel();
    private BasicLabel lblPath2 = new BasicLabel();
    private BasicLabel lblPath3 = new BasicLabel();
    private UnAssignButton bUpFile = new UnAssignButton(true);
    private GoButton bRecargar = new GoButton();
    private PreviousWizardButton bHome = new PreviousWizardButton();
    private AddMoreButton bNewFolder = new AddMoreButton();
    private DeleteButton bEliminar = new DeleteButton();
    private AddComboButton bCopiar = new AddComboButton();
    private NextWizardButton bMover = new NextWizardButton();
    private FindButton bCrearCarp1 = new FindButton();
    private CloseButton bCancel = new CloseButton();
    private BasicCombo jComboBox1 = new BasicCombo();
    private BasicCombo jComboBox2 = new BasicCombo();
    private BasicScrollPane jScrollPane1 = new BasicScrollPane();
    private BasicScrollPane jScrollPane2 = new BasicScrollPane();
    private JList jList1 = new JList();
    private JList jList2 = new JList();
    final String TEXTO = "Files Management";

    public ManagementFiles() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(506, 375));
	this.getContentPane().add(bCancel, null);
	this.getContentPane().setLayout(null);
	this.setTitle("Files Management");
	jPanel1.setBounds(new Rectangle(5, 40, 490, 275));
	jPanel1.setLayout(null);
	jPanel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	jTextField2.setBounds(new Rectangle(320, 62, 115, 20));
	lblPath.setText("Path:");
	lblPath.setBounds(new Rectangle(115, 5, 40, 15));
	lblPath.setFont(new Font("Dialog", 1, 11));
	bUpFile.setBounds(new Rectangle(280, 15, 40, 25));
	bUpFile.setSize(new Dimension(40, 25));
	bUpFile.setMnemonic('F');
	bUpFile.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bUpFile_actionPerformed(e);
		    }

		});
	bRecargar.setBounds(new Rectangle(15, 15, 40, 25));
	bRecargar.setSize(new Dimension(40, 25));
	bRecargar.setMnemonic('F');
	bRecargar.setText("null");
	bRecargar.setToolTipText("null");
	bHome.setBounds(new Rectangle(60, 15, 40, 25));
	bHome.setSize(new Dimension(40, 25));
	bHome.setMnemonic('F');
	bNewFolder.setBounds(new Rectangle(440, 60, 40, 25));
	bNewFolder.setSize(new Dimension(40, 25));
	bNewFolder.setMnemonic('F');
	jComboBox1.setBounds(new Rectangle(115, 20, 160, 20));
	jComboBox2.setBounds(new Rectangle(60, 62, 160, 20));
	lblPath2.setText("Select File:");
	lblPath2.setBounds(new Rectangle(15, 95, 65, 15));
	lblPath2.setFont(new Font("Dialog", 1, 11));
	jScrollPane1.setBounds(new Rectangle(15, 110, 205, 155));
	jScrollPane1.getViewport().setLayout(null);
	jScrollPane1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	jList1.setBounds(new Rectangle(0, 0, 200, 150));
	jScrollPane2.setBounds(new Rectangle(230, 110, 205, 155));
	jScrollPane2.getViewport().setLayout(null);
	jScrollPane2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	jScrollPane2.setSize(new Dimension(205, 155));
	jList2.setBounds(new Rectangle(0, 0, 200, 150));
	lblPath3.setText("Groups:");
	lblPath3.setBounds(new Rectangle(230, 95, 50, 15));
	lblPath3.setFont(new Font("Dialog", 1, 11));
	bCrearCarp1.setBounds(new Rectangle(440, 145, 40, 25));
	bCrearCarp1.setSize(new Dimension(40, 25));
	bCrearCarp1.setMnemonic('F');
	bCancel.setBounds(new Rectangle(5, 320, 40, 25));
	bCancel.setSize(new Dimension(40, 25));
	bCancel.setMnemonic('F');
	bCancel.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bCancel_actionPerformed(e);
		    }

		});
	bEliminar.setBounds(new Rectangle(15, 60, 40, 25));
	bEliminar.setSize(new Dimension(40, 25));
	bEliminar.setMnemonic('F');
	bCopiar.setBounds(new Rectangle(230, 60, 40, 25));
	bCopiar.setSize(new Dimension(40, 25));
	bCopiar.setMnemonic('F');
	bMover.setBounds(new Rectangle(275, 60, 40, 25));
	bMover.setSize(new Dimension(40, 25));
	bMover.setMnemonic('F');
	jPanel1.add(bUpFile, null);
	jPanel1.add(jTextField2, null);
	jPanel1.add(lblPath, null);
	jPanel1.add(bRecargar, null);
	jPanel1.add(bHome, null);
	jPanel1.add(bNewFolder, null);
	jPanel1.add(jComboBox1, null);
	jPanel1.add(jComboBox2, null);
	jPanel1.add(bEliminar, null);
	jPanel1.add(bCopiar, null);
	jPanel1.add(bMover, null);
	jScrollPane1.getViewport().add(jList1, null);
	jPanel1.add(jScrollPane1, null);
	jPanel1.add(lblPath2, null);
	jPanel1.add(lblPath3, null);
	jScrollPane2.getViewport().add(jList2, null);
	jPanel1.add(jScrollPane2, null);
	jPanel1.add(bCrearCarp1, null);
	this.getContentPane().add(jPanel1, null);
	ComponentsManager.centerWindow(this);
    }

    private void bUpFile_actionPerformed(ActionEvent e) {
	FindFiles findfiles = new FindFiles();
	findfiles.setModal(true);
	findfiles.setVisible(true);
    }

    private void bCancel_actionPerformed(ActionEvent e) {
	this.dispose();
    }

}
