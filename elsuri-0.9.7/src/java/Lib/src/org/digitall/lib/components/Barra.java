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
 * Barra.java
 *
 * */
package org.digitall.lib.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
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
import org.digitall.lib.icons.IconTypes;

//

public class Barra extends BasicDialog {

    private JProgressBar jbarra = new JProgressBar();
    private Component x = null;
    private BasicLabel imagen2 = new BasicLabel(IconTypes.statusBarIcon_16x16);
    private BasicLabel jLabel1 = new BasicLabel();
    private CloseButton bcancelar = new CloseButton();
    private Thread tarea;
    protected boolean cancelar = false;
    private BasicPanel jPanel1 = new BasicPanel();

    public Barra(Component jd) {
	try {
	    x = jd;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	x.setEnabled(false);
	imagen2.setBounds(new Rectangle(13, 5, 40, 35));
	jLabel1.setText("Realizando busqueda, espere por favor...");
	jLabel1.setBounds(new Rectangle(66, 10, 252, 15));
	bcancelar.setText("Cancelar");
	bcancelar.setBounds(new Rectangle(229, 75, 96, 25));
	bcancelar.setMargin(new Insets(2, 3, 2, 14));
	bcancelar.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bcancelar_actionPerformed(e);
		    }

		});
	jPanel1.setBounds(new Rectangle(10, 40, 315, 20));
	jPanel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	jPanel1.setLayout(null);
	//x.setVisible(false);
	this.setSize(new Dimension(349, 134));
	this.getContentPane().setLayout(null);
	this.setTitle("Buscando...");
	jbarra.setBounds(new Rectangle(5, 5, 305, 10));
	jbarra.setForeground(new Color(41, 13, 151));
	jbarra.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	jbarra.setBackground(Color.white);
	jbarra.setToolTipText("Buscando....");
	jPanel1.add(jbarra, null);
	this.getContentPane().add(jPanel1, null);
	this.getContentPane().add(bcancelar, null);
	this.getContentPane().add(jLabel1, null);
	this.getContentPane().add(imagen2, null);
	jbarra.setIndeterminate(true);
	// tarea.start();
    }

    public void disposeme() {
	x.setEnabled(true);
	//x.setVisible(true);
	this.dispose();
    }

    public void Inicia(Thread Tarea) {
	tarea = Tarea;
	tarea.start();
    }

    private void bcancelar_actionPerformed(ActionEvent e) {
	tarea.stop();
	cancelar = true;
	disposeme();
    }

    public boolean getCancelar() {
	return cancelar;
    }

}
