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
 * DCalendar.java
 *
 * */
package org.digitall.apps.calendar.interfaces;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicTextArea;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

public class DCalendar extends BasicInternalFrame {

    private CalendarDrawPanel calendarDrawPanel = new CalendarDrawPanel();
    private BasicPanel dayPanel = new BasicPanel();
    private BasicLabel dayLabel = new BasicLabel(IconTypes.calendar_bg);
    private BasicTextArea jtaDayDetails = new BasicTextArea();
    private int location = 0;
    private int period = 10;
    private Timer timerShow;
    private Timer timerHide;
    private boolean showing = false;
    private boolean hiding = false;
    private BasicButton btnShow = new BasicButton(IconTypes.calendar_show);
    private BasicButton btnHide = new BasicButton(IconTypes.calendar_hide);
    private int border = 5;
    private int maxHeight = 460;
    private int minHeight = 100;
    private BasicLabel lblMonthName = new BasicLabel();
    private BasicLabel lblDayName = new BasicLabel();
    private BasicLabel lblDayNumber = new BasicLabel();
    private GridBagLayout gridBagLayout1 = new GridBagLayout();
    private GridBagLayout gridBagLayout2 = new GridBagLayout();

    public DCalendar() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	Environment.lblDayName = lblDayName;
	Environment.lblDayNumber = lblDayNumber;
	Environment.lblMonthName = lblMonthName;
	this.setContentPane(new Container());
	this.setOpaque(true);
	this.setBackground(Color.white);
	this.setLayout(null);
	this.setSize(new Dimension(436, 474));
	this.setBounds(new Rectangle(10, 10, 435, 100));
	dayPanel.setBounds(new Rectangle(0, 0, 435, 100));
	dayPanel.setLayout(gridBagLayout1);
	dayPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	dayLabel.setLayout(gridBagLayout2);
	dayLabel.setPreferredSize(new Dimension(74, 70));
	dayLabel.setSize(new Dimension(74, 70));
	dayLabel.setMaximumSize(new Dimension(74, 70));
	dayLabel.setMinimumSize(new Dimension(74, 70));
	dayLabel.setBounds(new Rectangle(2, 0, 74, 70));
	btnShow.setMinimumSize(new Dimension(24, 12));
	btnShow.setPreferredSize(new Dimension(24, 12));
	btnShow.setSize(new Dimension(24, 12));
	btnShow.setMaximumSize(new Dimension(24, 12));
	btnShow.setBounds(new Rectangle(5, 82, 24, 12));
	btnShow.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnShow_actionPerformed(e);
			       }

			   }
	);
	btnHide.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnHide_actionPerformed(e);
			       }

			   }
	);
	lblMonthName.setHorizontalAlignment(SwingConstants.CENTER);
	lblMonthName.setHorizontalTextPosition(SwingConstants.CENTER);
	lblMonthName.setFont(new Font("Lucida Sans", 1, 9));
	lblMonthName.setForeground(Color.white);
	lblMonthName.setPreferredSize(new Dimension(60, 17));
	lblMonthName.setText("DICIEMBRE");
	lblDayName.setHorizontalAlignment(SwingConstants.CENTER);
	lblDayName.setHorizontalTextPosition(SwingConstants.CENTER);
	lblDayName.setFont(new Font("Lucida Sans", 1, 9));
	lblDayName.setPreferredSize(new Dimension(57, 17));
	lblDayName.setForeground(Color.black);
	lblDayName.setText("WEDNESDAY");
	lblDayNumber.setHorizontalAlignment(SwingConstants.CENTER);
	lblDayNumber.setHorizontalTextPosition(SwingConstants.CENTER);
	lblDayNumber.setFont(new Font("Lucida Sans", 1, 28));
	lblDayNumber.setForeground(new Color(108, 108, 108));
	lblDayNumber.setText("88");
	calendarDrawPanel.setBounds(new Rectangle(0, 97, 435, 360));
	dayPanel.add(jtaDayDetails, new GridBagConstraints(2, 0, 1, 2, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 0, 5, 5), 0, 0));
	dayLabel.add(lblMonthName, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 0, 0, 0), 0, 0));
	dayLabel.add(lblDayName, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 4, 0), 0, 0));
	dayLabel.add(lblDayNumber, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
	dayPanel.add(dayLabel, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
	dayPanel.add(btnHide, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 3, 8), 0, 0));
	dayPanel.add(btnShow, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 13, 3, 0), 0, 0));
	setBounds(0, 0, getWidth(), dayPanel.getHeight());
	this.add(dayPanel, null);
	jtaDayDetails.setText("Las tareas del día de hoy son: ... ... ...\n... ... ... ... ... ...\n... ... ... ... ... ...");
	jtaDayDetails.setBorder(null);
    }

    public void start() {
	setDesktop("MAIN");
	((BasicInternalFrameUI)getUI()).setNorthPane(null);
	setBorder(null);
	calendarDrawPanel.initCalendar();
	relocate();
	this.setVisible(true);
	this.add(calendarDrawPanel, null);
	calendarDrawPanel.setVisible(false);
    }

    public void relocate() {
	setLocation((int)(getDesktop().getBounds().getMaxX() - getDesktop().getLocation().getX()) - (getWidth() + border), (int)((getDesktop().getBounds().getMaxY() - getDesktop().getLocation().getY()) - getHeight()) - border);
	location = (int)getLocation().getY();
    }

    public void showWindow() {
	calendarDrawPanel.setVisible(false);
	timerShow = new Timer(period, new ActionListener() {

			   public void actionPerformed(ActionEvent e) {
			       if (!hiding) {
				   if (maxHeight > getHeight()) {
				       setBounds((int)(getDesktop().getBounds().getMaxX() - getDesktop().getLocation().getX()) - (getWidth() + border), getLocation().y - Environment.animationStep, getWidth(), (int)getHeight() + Environment.animationStep);
				       location -= Environment.animationStep;
				       showing = true;
				       //System.out.println("show running");
				   } else {
				       showing = false;
				       calendarDrawPanel.setVisible(true);
				       timerShow.stop();
				       //System.out.println("show terminated");
				   }
			       }
			   }

		       }
	    );
	timerShow.start();
    }

    public void hideWindow() {
	if (showing) {
	    showing = false;
	    timerShow.stop();
	}
	calendarDrawPanel.setVisible(false);
	timerHide = new Timer(period, new ActionListener() {

			   public void actionPerformed(ActionEvent e) {
			       if (!showing) {
				   if (minHeight < getHeight()) {
				       setBounds((int)(getDesktop().getBounds().getMaxX() - getDesktop().getLocation().getX()) - (getWidth() + border), getLocation().y + Environment.animationStep, getWidth(), (int)getHeight() - Environment.animationStep);
				       location += Environment.animationStep;
				       hiding = true;
				       //System.out.println("hide running");
				   } else {
				       hiding = false;
				       timerHide.stop();
				       //System.out.println("hide terminated");
				   }
			       }
			   }

		       }
	    );
	timerHide.start();
    }

    private void btnShow_actionPerformed(ActionEvent e) {
	showWindow();
    }

    private void btnHide_actionPerformed(ActionEvent e) {
	hideWindow();
    }

   

}
