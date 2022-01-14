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
 * MainPanel.java
 * Modified by Santiago Cassina - SCasSI
 * July, 2013
 * */
package org.scassi.projects.cerronegro.desktop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

import org.digitall.common.systemmanager.ChangePassword;
import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.html.BrowserLauncher;
import org.digitall.lib.icons.IconTypes;

public class MainPanel extends BasicPanel {

    private JFrame parent;
    private ImageIcon topLeftImage = IconTypes.getIcon("iconos/ui/desktoppanel/leftup.png");
    private ImageIcon btnExitImage = IconTypes.getIcon("iconos/ui/desktoppanel/btnexit.png");
    private ImageIcon btnMinimizeImage = IconTypes.getIcon("iconos/ui/desktoppanel/btnminimize.png");
    private BasicPanel jpLeft = new BasicPanel();
    private BasicPanel jpRight = new BasicPanel();
    private BasicPanel jpCenter = new BasicPanel();
    private BorderLayout blMain = new BorderLayout();
    private BorderLayout blCenter = new BorderLayout();
    private BorderLayout blCentralPanel = new BorderLayout();
    private BorderLayout blRight = new BorderLayout();
    private BorderLayout blLeft = new BorderLayout();
    private GridBagLayout gblBtnContainer = new GridBagLayout();
    private BasicButton btnStickyNotes = new BasicButton(IconTypes.stickynotes_32x32);
    private BasicPanel jpCentralPanel = new BasicPanel();
    private BasicPanel jpTopRight = new BasicPanel();
    private BasicPanel jpButtonsContainer = new BasicPanel();
    private BasicButton btnExit = new BasicButton(btnExitImage);
    private BasicButton btnMinimize = new BasicButton(btnMinimizeImage);
    private BasicLabel lblLeftFiller = new BasicLabel();
    private BasicLabel lblRightFiller = new BasicLabel();
    private BasicButton lblTopLeft = new BasicButton(topLeftImage);
    private BasicButton btnMain = new BasicButton(IconTypes.main_32x32);
    private BasicButton btnReports = new BasicButton(IconTypes.reports_32x32);
    private BasicButton btnAvl = new BasicButton(IconTypes.main_32x32);
    private BasicButton btnGaia = new BasicButton(IconTypes.gaia_32x32);
    private Separator lblSeparator0 = new Separator();
    private Separator lblSeparator1 = new Separator();
    private Separator lblSeparator2 = new Separator();
    private Separator lblSeparator3 = new Separator();
    private BorderLayout borderLayout1 = new BorderLayout();

    public MainPanel(JFrame _parent) {
	try {
	    parent = _parent;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(blMain);
	this.setBounds(new Rectangle(10, 10, 800, 100));
	this.setSize(new Dimension(924, 100));
	this.setBackground(new Color(199, 215, 218));
	this.setOpaque(true);
	jpLeft.setPreferredSize(new Dimension(150, 10));
	jpLeft.setLayout(blLeft);
	jpLeft.setOpaque(false);
	jpRight.setPreferredSize(new Dimension(70, 10));
	jpRight.setLayout(blRight);
	jpRight.setOpaque(false);
	jpCenter.setLayout(blCenter);
	jpCenter.setOpaque(false);
	btnStickyNotes.addActionListener(new ActionListener() {

				      public void actionPerformed(ActionEvent e) {
					  btnStickyNotes_actionPerformed(e);
				      }

				  }
	);
	jpCentralPanel.setLayout(blCentralPanel);
	jpCentralPanel.setOpaque(false);

	btnExit.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnExit_actionPerformed(e);
			       }

			   }
	);
	btnMinimize.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       btnMinimize_actionPerformed(e);
				   }

			       }
	);
	jpButtonsContainer.setBounds(new Rectangle(29, 44, 394, 60));
	jpButtonsContainer.setPreferredSize(new Dimension(10, 51));
	jpButtonsContainer.setOpaque(false);
	jpButtonsContainer.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	jpButtonsContainer.setLayout(new BoxLayout(jpButtonsContainer, BoxLayout.X_AXIS));
	lblLeftFiller.setMinimumSize(new Dimension(80, 22));
	lblLeftFiller.setPreferredSize(new Dimension(8000, 22));
	lblRightFiller.setMinimumSize(new Dimension(80, 22));
	lblRightFiller.setPreferredSize(new Dimension(80, 22));
	lblTopLeft.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      lblTopLeft_actionPerformed(e);
				  }

			      }
	);
	btnAvl.setRolloverEnabled(true);
	btnAvl.setRolloverIcon(IconTypes.main_ro_32x32);
	btnAvl.setHorizontalAlignment(SwingConstants.CENTER);
	btnAvl.addActionListener(new ActionListener() {

					   public void actionPerformed(ActionEvent e) {
					       btnAvl_actionPerformed(e);
					   }

				       }
	);
	
	btnMain.setRolloverEnabled(true);
	btnMain.setRolloverIcon(IconTypes.main_ro_32x32);
	btnMain.setHorizontalAlignment(SwingConstants.CENTER);
	btnMain.setPreferredSize(new Dimension(40, 40));
	btnMain.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnMain_actionPerformed(e);
				}

			    }
	);
	
	btnReports.setRolloverEnabled(true);
	btnReports.setRolloverIcon(IconTypes.reports_ro_32x32);
	btnReports.setHorizontalAlignment(SwingConstants.CENTER);
	btnReports.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnReports_actionPerformed(e);
				}

			    }
	);
	btnGaia.setRolloverEnabled(true);
	btnGaia.setRolloverIcon(IconTypes.gaia_ro_32x32);
	btnGaia.setHorizontalAlignment(SwingConstants.CENTER);
	btnGaia.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					btnGaia_actionPerformed(e);
				    }

				}
	);
	btnStickyNotes.setRolloverEnabled(true);
	btnStickyNotes.setRolloverIcon(IconTypes.stickynotes_ro_32x32);

	jpTopRight.setLayout(borderLayout1);
	jpTopRight.setOpaque(false);
	jpTopRight.setMinimumSize(new Dimension(150, 68));
	jpTopRight.setPreferredSize(new Dimension(150, 35));
	jpTopRight.setSize(new Dimension(150, 68));
	jpTopRight.setMaximumSize(new Dimension(150, 68));
	jpTopRight.setBounds(new Rectangle(0, 0, 150, 68));
	jpLeft.add(lblTopLeft, BorderLayout.CENTER);
	this.add(jpLeft, BorderLayout.WEST);
	jpTopRight.add(btnExit, BorderLayout.WEST);
	jpTopRight.add(btnMinimize, BorderLayout.EAST);
	jpRight.add(jpTopRight, BorderLayout.NORTH);
	this.add(jpRight, BorderLayout.EAST);
	jpCentralPanel.add(jpButtonsContainer, BorderLayout.CENTER);
	jpButtonsContainer.add(lblLeftFiller, null);
	jpButtonsContainer.add(btnMain, null);
	jpButtonsContainer.add(lblSeparator0, null);
	jpButtonsContainer.add(btnReports, null);
	jpButtonsContainer.add(lblSeparator1, null);
	jpButtonsContainer.add(btnAvl, null);
	jpButtonsContainer.add(lblSeparator2, null);
	jpButtonsContainer.add(btnGaia, null);
	jpButtonsContainer.add(lblSeparator3, null);
	jpButtonsContainer.add(btnStickyNotes, null);
	jpButtonsContainer.add(lblRightFiller, null);

	jpCenter.add(jpCentralPanel, BorderLayout.CENTER);
	this.add(jpCenter, BorderLayout.CENTER);
	btnAvl.setToolTipText(Environment.MODULE_AVL);
	btnReports.setToolTipText(Environment.MODULE_REPORTS);
	btnGaia.setToolTipText(Environment.MODULE_GAIA);

	btnGaia.setPreferredSize(new Dimension(90, 90));
	btnGaia.setMaximumSize(btnGaia.getPreferredSize());
	btnStickyNotes.setPreferredSize(new Dimension(90, 90));
	btnStickyNotes.setMaximumSize(btnStickyNotes.getPreferredSize());
	btnAvl.setPreferredSize(new Dimension(90, 90));
	btnAvl.setMaximumSize(btnAvl.getPreferredSize());
	btnReports.setPreferredSize(new Dimension(90, 90));
	btnReports.setMaximumSize(btnReports.getPreferredSize());
	btnMain.setPreferredSize(new Dimension(90, 90));
	btnMain.setMaximumSize(btnMain.getPreferredSize());

	btnStickyNotes.setToolTipText(Environment.MODULE_STICKYNOTES);
	lblTopLeft.setToolTipText(OrganizationInfo.getWebAddress());
	btnExit.setToolTipText("Salir");
	btnExit.setPreferredSize(new Dimension(35, 35));
	btnMinimize.setToolTipText("Minimizar - (F11 Pantalla completa)");
	btnMinimize.setPreferredSize(new Dimension(35, 35));
    }
    //JPANEL with Gradient Background

    /*public void paintComponent(Graphics g) {
	Graphics2D g2 = (Graphics2D)g;
	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	int w = getWidth();
	int h = getHeight();
	GradientPaint gradient = new GradientPaint(0, 0, BasicConfig.PANEL_GRADIENT_START_COLOR, 0, h, BasicConfig.PANEL_GRADIENT_END_COLOR, false);
	g2.setPaint(gradient);
	g2.fillRect(0, 0, w, h);
    }*/

    private void btnExit_actionPerformed(ActionEvent e) {
	if ((e.getModifiers() & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK) {
	    ChangePassword pass = new ChangePassword(Environment.sessionUser);
	    pass.setModal(true);
	    pass.setVisible(true);
	} else {
	    closeApplication();
	}
    }

    public void closeApplication() {
	Advisor.closeApplication();
    }

    private void btnAvl_actionPerformed(ActionEvent e) {
	Environment.mainTabbedPane.setSelectedIndex(Environment.getDesktopIndex(Environment.MODULE_AVL));
    }

    private void btnGaia_actionPerformed(ActionEvent e) {
	Environment.mainTabbedPane.setSelectedIndex(Environment.getDesktopIndex(Environment.MODULE_GAIA));
    }

    private void btnMain_actionPerformed(ActionEvent e) {
	Environment.mainTabbedPane.setSelectedIndex(Environment.getDesktopIndex(Environment.MODULE_MAIN));
    }

    private void btnReports_actionPerformed(ActionEvent e) {
	Environment.mainTabbedPane.setSelectedIndex(Environment.getDesktopIndex(Environment.MODULE_REPORTS));
    }

    private void btnStickyNotes_actionPerformed(ActionEvent e) {
	Environment.mainTabbedPane.setSelectedIndex(Environment.getDesktopIndex(Environment.MODULE_STICKYNOTES));
    }

    private void lblTopLeft_actionPerformed(ActionEvent e) {
	BrowserLauncher.openURL(OrganizationInfo.getWebAddress());
    }

    private void btnMinimize_actionPerformed(ActionEvent e) {
	parent.setState(JFrame.ICONIFIED);
    }

    private class Separator extends BasicLabel {

	private int width = 15;

	public Separator() {
	    super(IconTypes.getIcon("iconos/ui/desktoppanel/btnseparator.png"));
	    setPreferredSize(new Dimension(width, 24));
	    setSize(new Dimension(width, 24));
	    setMinimumSize(new Dimension(width, 24));
	    setMaximumSize(new Dimension(width, 24));
	}

    }

}
