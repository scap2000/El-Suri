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
 * ControlMain.java
 *
 * */
package org.digitall.apps.systemmanager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.NextWizardButton;
import org.digitall.lib.icons.IconTypes;

//

public class ControlMain extends JFrame {

    private BasicPanel panelCenter = new BasicPanel();
    private BasicPanel jPanel1 = new BasicPanel();
    private BorderLayout layoutMain = new BorderLayout();
    private BasicLabel jLabel1 = new BasicLabel();
    private BasicLabel lbl = new BasicLabel();
    private NextWizardButton bNext = new NextWizardButton();
    private CloseButton bExit = new CloseButton();
    private JRadioButton rbSystems = new JRadioButton();
    private JRadioButton rbUsers = new JRadioButton();
    private JRadioButton rbGroups = new JRadioButton();
    private JRadioButton rbFiles = new JRadioButton();
    private ButtonGroup grupo = new ButtonGroup();
    final String TEXTO = "Systems Administrator";
    private BasicLabel jLabel2 = new BasicLabel(IconTypes.SYSTEM_ICON_22x22);
    private BasicLabel jLabel4 = new BasicLabel(IconTypes.USER_ICON_22x22);
    private BasicLabel jLabel5 = new BasicLabel(IconTypes.FILE_ICON_22x22);

    public ControlMain() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(363, 216));
	this.setTitle("Systems Administrator");
	this.getContentPane().setLayout(layoutMain);
	panelCenter.setLayout(null);
	bNext.setBounds(new Rectangle(305, 155, 40, 25));
	bNext.setMnemonic('N');
	bNext.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bNext_actionPerformed(e);
		    }

		});
	jPanel1.setBounds(new Rectangle(5, 65, 345, 55));
	jPanel1.setLayout(null);
	jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	rbSystems.setText("Systems");
	rbSystems.setBounds(new Rectangle(35, 20,70, 15));
	rbSystems.setFont(new Font("Dialog", 1, 11));
	rbUsers.setText("Users");
	rbUsers.setBounds(new Rectangle(150, 20, 70, 15));
	rbUsers.setFont(new Font("Dialog", 1, 11));
	rbUsers.setMnemonic('U');
	jLabel1.setText(" Options");
	jLabel1.setBounds(new Rectangle(10, 55, 55, 15));
	jLabel1.setFont(new Font("Dialog", 1, 11));
	jLabel1.setOpaque(true);
	rbGroups.setText("Groups");
	rbGroups.setBounds(new Rectangle(35, 30, 70, 15));
	rbGroups.setFont(new Font("Dialog", 1, 11));
	rbGroups.setMnemonic('G');
	rbFiles.setText("Files");
	rbFiles.setBounds(new Rectangle(255, 20, 70, 15));
	rbFiles.setFont(new Font("Dialog", 1, 11));
	rbFiles.setMnemonic('F');
	jLabel2.setBounds(new Rectangle(10, 15, 20, 25));
	jLabel2.setSize(new Dimension(22, 22));
	jLabel2.setBackground(Color.white);
	jLabel2.setOpaque(true);
	jLabel4.setBounds(new Rectangle(125, 15, 22, 22));
	jLabel4.setSize(new Dimension(24, 24));
	jLabel4.setBackground(Color.white);
	jLabel4.setOpaque(true);
	jLabel5.setBounds(new Rectangle(230, 15, 22, 22));
	jLabel5.setSize(new Dimension(24, 24));
	jLabel5.setBackground(Color.white);
	jLabel5.setOpaque(true);
	lbl.setBounds(new Rectangle(0, 0, 55, 35));
	bExit.setBounds(new Rectangle(10, 155, 40, 25));
	bExit.setMnemonic('E');
	bExit.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bExit_actionPerformed(e);
		    }

		});
	grupo.add(rbGroups);
	grupo.add(rbUsers);
	grupo.add(rbSystems);
	grupo.add(rbFiles);
	jPanel1.add(rbGroups, null);
	jPanel1.add(rbUsers, null);
	jPanel1.add(rbSystems, null);
	jPanel1.add(rbFiles, null);
	jPanel1.add(jLabel2, null);
	jPanel1.add(jLabel4, null);
	jPanel1.add(jLabel5, null);
	panelCenter.add(jLabel1, null);
	panelCenter.add(bExit, null);
	panelCenter.add(jPanel1, null);
	panelCenter.add(bNext, null);
	panelCenter.add(lbl, null);
	this.getContentPane().add(panelCenter, BorderLayout.CENTER);
	rbSystems.setSelected(true);
	rbSystems.setMnemonic('S');
	rbGroups.setVisible(false);
    }

    void fileExit_ActionPerformed(ActionEvent e) {
	this.dispose();
    }

    private void bExit_actionPerformed(ActionEvent e) {
	this.dispose();
    }

    private void bNext_actionPerformed(ActionEvent e) {
	if (rbSystems.isSelected()) {
	    Systems sistemas = new Systems();
	    sistemas.setVisible(true);
	    sistemas.setModal(true);
	} else if (rbFiles.isSelected()) {
	    ManagementFiles files = new ManagementFiles();
	    files.setModal(true);
	    files.setVisible(true);
	} else if (rbUsers.isSelected()) {
	    FindUser buscarUsuario = new FindUser();
	    buscarUsuario.setModal(true);
	    buscarUsuario.setVisible(true);
	} else if (rbGroups.isSelected()) {
	    Advisor.messageBox("Sorry, button under construction!", "Message");
	}
    }

}
