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
 * GaiaCadastralFinderPanel.java
 *
 * */
package org.digitall.apps.gaia.entities.parcels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import org.digitall.apps.taxes.classes.Cadastral;
import org.digitall.common.geo.mapping.BasicDrawEngine;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.icons.IconTypes;
import org.digitall.lib.sql.LibSQL;

public class GaiaCadastralFinderPanel extends BasicInternalFrame {

    private BasicPanel listPanel = new BasicPanel();
    private BasicPanel searchPanel = new BasicPanel();
    private BasicDrawEngine drawEngine = null;

    private int[] sizeColumnList = {60, 230, 70, 50, 155, 50, 170};
    private Vector cadastralHeader = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel cadastralPanel = new GridPanel(30, sizeColumnList, "Listado de Catastros", dataRow);
    private TFInput tfPersonName = new TFInput(DataTypes.STRING, "Name",false);
    private TFInput tfIdentificationNumber = new TFInput(DataTypes.INTEGER, "DNI",false);
    private TFInput tfCadastral = new TFInput(DataTypes.INTEGER, "Cadastral",false);
    private Cadastral cadastral;

    private int location = 0;
    private int period = 10;
    private Timer timerShow;
    private Timer timerHide;
    private boolean showing = false;
    private boolean hiding = false;
    private BasicButton btnShow = new BasicButton(IconTypes.calendar_show);
    private BasicButton btnHide = new BasicButton(IconTypes.calendar_hide);
    private int border = 5;
    private int maxHeight = 278;
    private int minHeight = 86;
    private BorderLayout borderLayout1 = new BorderLayout();

    public GaiaCadastralFinderPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setContentPane(new Container());
	this.setOpaque(true);
	this.setBackground(Color.white);
	this.setLayout(null);
	this.setSize(new Dimension(290, 278));
	this.setBounds(new Rectangle(10, 10, 290, 278));

	tfPersonName.setBounds(new Rectangle(5, 5, 155, 35));
	tfIdentificationNumber.setBounds(new Rectangle(165, 5, 120, 35));
	tfCadastral.setBounds(new Rectangle(165, 45, 120, 35));
	searchPanel.add(tfPersonName, null);
	searchPanel.add(tfIdentificationNumber, null);
	searchPanel.add(tfCadastral, null);
	searchPanel.add(btnHide, null);
	searchPanel.add(btnShow, null);
	tfPersonName.getTextField().addKeyListener(new KeyAdapter() {

						public void keyReleased(KeyEvent e) {
						    if (e.getKeyCode() == KeyEvent.VK_ENTER)
							refresh();
						}

					    }
	);
	tfIdentificationNumber.getTextField().addKeyListener(new KeyAdapter() {

							  public void keyReleased(KeyEvent e) {
							      if (e.getKeyCode() == KeyEvent.VK_ENTER)
								  refresh();
							  }

						      }
	);
	tfCadastral.getTextField().addKeyListener(new KeyAdapter() {

							  public void keyReleased(KeyEvent e) {
							      if (e.getKeyCode() == KeyEvent.VK_ENTER)
								  refresh();
							  }

						      }
	);
	searchPanel.setBounds(new Rectangle(0, 0, 435, 100));
	searchPanel.setLayout(null);
	searchPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	searchPanel.setSize(new Dimension(290, 86));
	btnShow.setMinimumSize(new Dimension(24, 12));
	btnShow.setPreferredSize(new Dimension(24, 12));
	btnShow.setSize(new Dimension(24, 12));
	btnShow.setMaximumSize(new Dimension(24, 12));
	btnShow.setBounds(new Rectangle(40, 55, 30, 25));
	btnShow.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnShow_actionPerformed(e);
			       }

			   }
	);
	btnHide.setBounds(new Rectangle(85, 55, 30, 25));
	btnHide.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnHide_actionPerformed(e);
			       }

			   }
	);
	listPanel.setBounds(new Rectangle(0, 90, 290, 200));
	listPanel.setLayout(borderLayout1);
	listPanel.setSize(new Dimension(290, 192));
	setBounds(0, 0, getWidth(), searchPanel.getHeight());
	this.add(searchPanel, null);

	listPanel.add(cadastralPanel, BorderLayout.CENTER);
	setCadastralHeader();

    }

    private void setCadastralHeader() {
	
	cadastralHeader.removeAllElements();
	cadastralHeader.addElement("*"); //idcatastro
	cadastralHeader.addElement("Catastro");
	cadastralHeader.addElement(Environment.lang.getProperty("Name"));
	cadastralHeader.addElement("*");
	cadastralHeader.addElement("*");
	cadastralHeader.addElement("*");
	cadastralHeader.addElement("*");
	cadastralHeader.addElement("*");
	cadastralHeader.addElement("*");
	cadastralHeader.addElement(Environment.lang.getProperty("DNI"));
	cadastralHeader.addElement("*");
	cadastralHeader.addElement(Environment.lang.getProperty("%"));
	cadastralHeader.addElement("*");
	
	cadastralHeader.addElement("*");cadastralHeader.addElement("*");
	cadastralHeader.addElement("*");cadastralHeader.addElement("*");
	cadastralHeader.addElement("*");cadastralHeader.addElement("*");
	cadastralHeader.addElement("*");cadastralHeader.addElement("*");
	cadastralHeader.addElement("*");cadastralHeader.addElement("*");
	
	cadastralHeader.addElement("*");cadastralHeader.addElement("*");
	cadastralHeader.addElement("*");cadastralHeader.addElement("*");
	cadastralHeader.addElement("*");cadastralHeader.addElement("*");
	cadastralHeader.addElement("*");cadastralHeader.addElement("*");
	cadastralHeader.addElement("*");cadastralHeader.addElement("*");
	
	cadastralHeader.addElement("*");cadastralHeader.addElement("*");
	cadastralHeader.addElement("*");cadastralHeader.addElement("*");
	cadastralHeader.addElement(Environment.lang.getProperty("Street"));
	cadastralHeader.addElement(Environment.lang.getProperty("Number"));
	cadastralHeader.addElement(Environment.lang.getProperty("Neighborhood"));
	cadastralHeader.addElement("*");cadastralHeader.addElement("*");
	cadastralHeader.addElement("*");cadastralHeader.addElement("*");
	cadastralHeader.addElement("*");cadastralHeader.addElement("*");
	 
	 cadastralPanel.getTable().addMouseListener(new MouseAdapter() {

						  public void mouseClicked(MouseEvent e) {
						      if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						          loadSelectedObject();
						      }
						  }

					      }
	 );
	 String params = "'" + tfPersonName.getString() + "','" + tfIdentificationNumber.getString() + "',0" + tfCadastral.getString();
	 cadastralPanel.setParams(GaiaEnvironment.getScheme() + ".getAllCatastros", params, cadastralHeader);
    }

    public void refresh() {
	showWindow();
	String params = "'"+ tfPersonName.getString() +"','"+ tfIdentificationNumber.getString() +"',0"+ tfCadastral.getString();
	cadastralPanel.refresh(params);
    }

    private void loadSelectedObject(){
	if (!dataRow.isEmpty()){
	    cadastral = new Cadastral();
	    cadastral.setIdCatastro(Integer.parseInt(dataRow.elementAt(0).toString()));
	    cadastral.setCatastro(Integer.parseInt(dataRow.elementAt(1).toString()));
	    cadastral.setDomape(dataRow.elementAt(2).toString());
	    cadastral.setDpto(Integer.parseInt(dataRow.elementAt(3).toString()));
	    cadastral.setDepartamento(dataRow.elementAt(4).toString());
	    cadastral.setLocalidad(dataRow.elementAt(5).toString());
	    cadastral.setTecloc(Integer.parseInt(dataRow.elementAt(6).toString()));
	    cadastral.setDomcon(dataRow.elementAt(7).toString());
	    cadastral.setDomtidoc(dataRow.elementAt(8).toString());
	    cadastral.setDomnudoc(dataRow.elementAt(9).toString());
	    cadastral.setDomfis(dataRow.elementAt(10).toString());
	    cadastral.setDompor(dataRow.elementAt(11).toString());
	    cadastral.setTecbavig(Integer.parseInt(dataRow.elementAt(12).toString()));
	    cadastral.setVigencia(dataRow.elementAt(13).toString());
	    cadastral.setPlano(Integer.parseInt(dataRow.elementAt(14).toString()));
	    if (!dataRow.elementAt(15).equals("null"))  {
		cadastral.setTecfeasg("" + Proced.setFormatDate("" + dataRow.elementAt(15),false));        
	    } 
	    cadastral.setTecsec(dataRow.elementAt(16).toString());
	    cadastral.setTecman(dataRow.elementAt(17).toString());
	    cadastral.setTecmanl(dataRow.elementAt(18).toString());
	    cadastral.setTecpar(dataRow.elementAt(19).toString());
	    cadastral.setTecparl(dataRow.elementAt(20).toString());
	    cadastral.setTecunif(dataRow.elementAt(21).toString());
	    cadastral.setTecurru(Integer.parseInt(dataRow.elementAt(22).toString()));
	    cadastral.setTecorg1(Integer.parseInt(dataRow.elementAt(23).toString()));
	    cadastral.setTecorg2(Integer.parseInt(dataRow.elementAt(24).toString()));
	    cadastral.setTecderd(Integer.parseInt(dataRow.elementAt(25).toString()));
	    cadastral.setTecderh(Integer.parseInt(dataRow.elementAt(26).toString()));
	    cadastral.setTecobs(dataRow.elementAt(27).toString());
	    cadastral.setTecsuurb(Double.parseDouble(dataRow.elementAt(28).toString()));
	    cadastral.setTerbe(dataRow.elementAt(29).toString());
	    cadastral.setTerreno(dataRow.elementAt(30).toString());
	    cadastral.setTerval(dataRow.elementAt(31).toString());
	    cadastral.setTervmej(dataRow.elementAt(32).toString());
	    cadastral.setTervcom(dataRow.elementAt(33).toString());
	    cadastral.setValfis(dataRow.elementAt(34).toString());
	    cadastral.setResol(dataRow.elementAt(35).toString());
	    cadastral.setResolfe(dataRow.elementAt(36).toString());
	    cadastral.setDcalle(dataRow.elementAt(37).toString());
	    cadastral.setDnumro(dataRow.elementAt(38).toString());
	    cadastral.setDdesbarrio(dataRow.elementAt(39).toString());
	    if (!dataRow.elementAt(44).equals("null"))  {
		cadastral.setFechaBaja("" + Proced.setFormatDate("" + dataRow.elementAt(44),false));
	    } else {
		cadastral.setFechaBaja("");
	    }
	}
	if (drawEngine != null) {
	    drawEngine.setPolygonEnvironment(GaiaEnvironment.getCadastralLayer(), LibSQL.getInt(GaiaEnvironment.getScheme() + ".getidparcelabycatastro", cadastral.getCatastro()), Color.orange, true);
	}
    }

    public void start() {
	((BasicInternalFrameUI)getUI()).setNorthPane(null);
	setBorder(null);
	relocate();
	this.setVisible(true);
	this.add(listPanel, null);
	listPanel.setVisible(false);
    }

    public void relocate() {
	setLocation((int)(getDesktop().getBounds().getMaxX() - getDesktop().getLocation().getX()) - (getWidth() + border), (int)((getDesktop().getBounds().getMaxY() - getDesktop().getLocation().getY()) - getHeight()) - border);
	location = (int)getLocation().getY();
    }

    public void showWindow() {
	listPanel.setVisible(false);
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
				       listPanel.setVisible(true);
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
	listPanel.setVisible(false);
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

    public void setDrawEngine(BasicDrawEngine drawEngine) {
	this.drawEngine = drawEngine;
    }

}
