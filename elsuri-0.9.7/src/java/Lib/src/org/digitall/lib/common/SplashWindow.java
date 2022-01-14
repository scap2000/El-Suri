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
 * SplashWindow.java
 *
 * */
package org.digitall.lib.common;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;

import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicTitleLabel;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

public class SplashWindow extends BasicDialog {

    private BasicTitleLabel lblLoading = new BasicTitleLabel();
    private BasicTitleLabel lblImage = new BasicTitleLabel(IconTypes.splash_background);
    private BasicTitleLabel lblLine1 = new BasicTitleLabel();
    private BasicTitleLabel lblLine2 = new BasicTitleLabel();
    private BasicTitleLabel lblLine3 = new BasicTitleLabel();
    private BasicTitleLabel lblLine4 = new BasicTitleLabel();
    private BasicPanel southPanel = new BasicPanel();
    private BasicPanel leftSouthPanel = new BasicPanel();
    private BasicPanel rightSouthPanel = new BasicPanel();

    public SplashWindow() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() {
	setUndecorated(true);
	setLayout(new BorderLayout());
	lblImage.setLayout(new BorderLayout());
	lblImage.add(southPanel, BorderLayout.SOUTH);
	southPanel.setLayout(new BorderLayout());
	southPanel.setOpaque(false);
	leftSouthPanel.setOpaque(false);
	rightSouthPanel.setOpaque(false);
	southPanel.add(leftSouthPanel, BorderLayout.WEST);
	southPanel.add(rightSouthPanel, BorderLayout.EAST);
	leftSouthPanel.setLayout(new GridLayout(3,1));
	rightSouthPanel.setLayout(new GridLayout(2,1));
	leftSouthPanel.add(lblLine1);
	leftSouthPanel.add(lblLine2);
	leftSouthPanel.add(lblLine3);
	rightSouthPanel.add(lblLine4);
	rightSouthPanel.add(lblLoading);
	this.getContentPane().add(lblImage, BorderLayout.CENTER);
	lblImage.setOpaque(false);
	addMouseListener(new MouseAdapter() {

		    public void mousePressed(MouseEvent e) {
			setVisible(false);
			dispose();
		    }

		});
	//		final int pause = waitTime;
	/*final int pause = 15000;
	final Runnable closerRunner = new Runnable() {

		public void run() {
		    setVisible(false);
		    //dispose();
		}

	    };
	Runnable waitRunner = new Runnable() {

		public void run() {
		    try {
			Thread.sleep(pause);
			SwingUtilities.invokeAndWait(closerRunner);
		    } catch (Exception e) {
			e.printStackTrace();
			// can catch InvocationTargetException
			// can catch InterruptedException
		    }
		}

	    };
	Thread splashThread = new Thread(waitRunner, "SplashThread");*/
	this.setForeground(Color.white);
	setBackground(Color.white);
	getRootPane().setBackground(Color.white);
	//lblImage.setOpaque(true);
	//lblImage.setBackground(Color.white);
	lblImage.setBounds(new Rectangle(0, 0, 460, 310));
	
	int width = IconTypes.splash_background.getImage().getWidth(this);
        int height = IconTypes.splash_background.getImage().getHeight(this);
	
	lblImage.setBounds(0,0,width, height);
	this.getContentPane().setLayout(null);
	//pack();
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	//Dimension labelSize = lblImage.getPreferredSize();
	lblImage.addMouseListener(new MouseAdapter() {

		    public void mouseClicked(MouseEvent e) {
			lblImage_mouseClicked(e);
		    }

		});
	lblLine1.setText("Basado en El Suri Desktop");
	lblLine2.setText("Todos los derechos reservados");
	lblLine3.setText("Licencia GNU/GPL 3.0 y posteriores");

	/*
	lblLine1.setBounds(new Rectangle(10, 260, 265, 15));
	lblLine2.setBounds(new Rectangle(10, 275, 265, 15));
	lblLine3.setBounds(new Rectangle(10, 290, 375, 15));
	lblLine4.setBounds(new Rectangle(255, 255, 190, 15));
	*/
	lblLoading.setFont(new Font("Lucida Sans", Font.PLAIN, 10));
	lblLoading.setHorizontalAlignment(SwingConstants.RIGHT);
	lblLoading.setText("Iniciando el sistema, espere por favor...");
	lblLine4.setText("Version: " + Environment.SYSTEM_VERSION);

	lblLine1.setBounds(new Rectangle(10, height-45, 265, 15));
	lblLine2.setBounds(new Rectangle(10, height-30, 265, 15));
	lblLine3.setBounds(new Rectangle(10, height-15, 375, 15));
	lblLine4.setBounds(new Rectangle(255, height-30, 190, 15));
	lblLoading.setBounds(new Rectangle(255, height-15, 200, 15));

	lblLine1.setFont(new Font("Lucida Sans", 0, 9));
	lblLine2.setFont(new Font("Lucida Sans", 0, 9));
	lblLine3.setFont(new Font("Lucida Sans", 0, 9));
	lblLine4.setFont(new Font("Lucida Sans", 0, 9));
	lblLine4.setHorizontalAlignment(SwingConstants.RIGHT);
	this.addFocusListener(new FocusAdapter() {

		    public void focusLost(FocusEvent e) {
			this_focusLost(e);
		    }

		});
	//setVisible(true);
	//this.setBounds(new Rectangle(10, 10, 460, 310));
	this.setSize(new Dimension(460, 310));
	this.setSize(new Dimension(width, height));
	//splashThread.start();
    }

    private void this_focusLost(FocusEvent e) {
	//System.out.println("focuslost");
    }

    private void lblImage_mouseClicked(MouseEvent e) {
	setVisible(false);
    }

}
