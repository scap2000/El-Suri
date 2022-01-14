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
 * BasicCard.java
 *
 * */
package org.digitall.common.components.basic;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.net.URL;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import org.digitall.common.components.combos.JCombo;
import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.JTFecha;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicTitleLabel;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;
import org.digitall.lib.sql.LibSQL;

public class BasicCard extends BasicInternalFrame {

    private int idCard = -1;
    private double pointX = 0;
    private double pointY = 0;
    private String text = "";
    private BasicPanel jpContent = new BasicPanel();
    private Point location = new Point();
    private int width = 600;
    private int height = 100;
    private BasicPanel jpNorth = new BasicPanel();
    private BasicLabel lblBackground = new BasicLabel();
    private BasicButton btnIconify = new BasicButton(IconTypes.stickynotes_delete);
    private PrintButton btnPrintReport = new PrintButton();
    private GridBagLayout gridBagLayout2 = new GridBagLayout();
    private Timer relocateTimer;
    private Point pressed;
    private boolean isPressed = false;
    private JTFecha tfStartDate = new JTFecha();
    private JTFecha tfEndDate = new JTFecha();
    private BasicLabel lblEndDate = new BasicLabel();
    private BasicLabel lblStartDate = new BasicLabel();
    private URL reportURL = null;
    private Properties properties = new Properties();
    private String cardDefinition = "";
    private BasicTitleLabel lblTitle = new BasicTitleLabel();
    private GridBagLayout gridBagLayout1 = new GridBagLayout();
    private JCombo cbFilter = new JCombo();
    private GridBagLayout gridBagLayout3 = new GridBagLayout();
    private String comboString = "";

    public BasicCard(int _idCard) {
	//, URL _reportURL) {
	idCard = _idCard;
	//reportURL = _reportURL;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setBounds(new Rectangle(10, 10, 600, 100));
	this.setLayout(gridBagLayout1);
	this.setSize(new Dimension(568, 105));
	((BasicInternalFrameUI)getUI()).setNorthPane(null);
	jpNorth.setLayout(gridBagLayout3);
	jpNorth.setPreferredSize(new Dimension(160, 23));
	jpNorth.setMaximumSize(new Dimension(160, 23));
	jpNorth.setMinimumSize(new Dimension(160, 23));
	jpNorth.setBackground(new Color(255, 132, 0));
	btnIconify.setMaximumSize(new Dimension(24, 23));
	btnIconify.setIconTextGap(0);
	btnIconify.setMinimumSize(new Dimension(24, 23));
	btnIconify.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      btnIconify_actionPerformed(e);
				  }

			      }
	);
	btnPrintReport.setMaximumSize(new Dimension(24, 23));
	btnPrintReport.setMinimumSize(new Dimension(24, 23));
	btnPrintReport.addActionListener(new ActionListener() {

				      public void actionPerformed(ActionEvent e) {
					  btnPrintReport_actionPerformed(e);
				      }

				  }
	);
	tfStartDate.setSize(new Dimension(80, 20));
	tfStartDate.setMinimumSize(new Dimension(80, 20));
	tfStartDate.setPreferredSize(new Dimension(80, 20));
	tfEndDate.setMinimumSize(new Dimension(80, 20));
	tfEndDate.setPreferredSize(new Dimension(80, 20));
	lblEndDate.setText("Hasta:");
	lblStartDate.setText("Desde:");
	lblTitle.setBackground(new Color(247, 181, 0));
	lblTitle.setMinimumSize(new Dimension(2, 15));
	lblTitle.setMaximumSize(new Dimension(32767, 32767));
	lblTitle.setText("Reporte: ");
	lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
	jpContent.setOpaque(false);
	jpContent.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	jpContent.setOpaque(false);
	setDesktop(Environment.getDesktop(Environment.MODULE_STICKYNOTES));
	jpContent.setBackground(Color.yellow);
	getContentPane().setLayout(gridBagLayout2);
	jpNorth.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	jpNorth.add(btnIconify, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), -10, -10));
	jpNorth.add(btnPrintReport, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 10, 0, 10), 0, 0));
	jpNorth.add(tfStartDate, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
	jpNorth.add(tfEndDate, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
	jpNorth.add(lblEndDate, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));
	jpNorth.add(lblStartDate, new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));
	jpNorth.add(lblTitle, new GridBagConstraints(1, 0, 6, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
	getContentPane().add(jpContent, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 1), 386, 181));
	jpNorth.add(cbFilter, new GridBagConstraints(6, 1, 1, 1, 2.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 5), 0, 0));
	getContentPane().add(jpNorth, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 1), 240, 22));
	this.getContentPane().add(lblBackground, new GridBagConstraints(0, 0, 1, 2, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 1), 0, 0));
	this.setSize(width, height);
	this.setClosable(true);
	this.setResizable(false);
	this.setIconifiable(true);
	//Elimino el icono y achica la barra de título
	//No se muestra el title si la condicion isPalette = true
	//putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
	show();
	relocate();
	relocateTimer = new Timer(10, new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   if (isPressed) {
				       try {
					   if (MouseInfo.getPointerInfo().getLocation() != null) {
					       //setLocation(MouseInfo.getPointerInfo().getLocation());
					       location = getLocation();
					       int x = location.x - pressed.x + MouseInfo.getPointerInfo().getLocation().x;
					       int y = location.y - pressed.y + MouseInfo.getPointerInfo().getLocation().y;
					       if (!((x >= 0 || Math.abs(x) <= getWidth() - 20) && x <= (getDesktop().getBounds().getMaxX() - getDesktop().getBounds().getMinX()) - 20)) {
						   x = getLocation().x;
					       }
					       if (!(y >= 0 && y <= (getDesktop().getBounds().getMaxY() - getDesktop().getBounds().getMinY()) - 20)) {
						   y = getLocation().y;
					       }
					       if (x < 0) {
						   x = 0;
					       }
					       if (x + getWidth() > getDesktop().getBounds().getMaxX()) {
						   x = (int)(getDesktop().getBounds().getMaxX() - getWidth());
					       }
					       if (y < 0) {
						   y = 0;
					       }
					       if (y + getHeight() > getDesktop().getBounds().getMaxY()) {
						   y = (int)(getDesktop().getBounds().getMaxY() - getHeight());
					       }
					       setLocation(x, y);
					       pressed.setLocation(MouseInfo.getPointerInfo().getLocation());
					   }
				       } catch (NullPointerException x) {
					   setLocation(location);
				       }
				   }
			       }

			   }
	    );
	jpNorth.addMouseListener(new MouseAdapter() {

			      public void mousePressed(MouseEvent me) {
				  pressed = MouseInfo.getPointerInfo().getLocation();
				  isPressed = true;
				  toFront();
				  relocateTimer.start();
			      }

			      public void mouseReleased(MouseEvent me) {
				  pressed = null;
				  isPressed = false;
				  relocateTimer.stop();
			      }

			  }
	);
	lblBackground.setBackground(new Color(214, 231, 255));
	lblBackground.setOpaque(true);
	cbFilter.setGeneralItem(true);
	retrieveData();
	cbFilter.addMouseListener(new MouseAdapter() {

			       public void mouseClicked(MouseEvent e) {
				   if (e.getButton() == MouseEvent.BUTTON3 && e.getClickCount() == 2) {
				       updateCombo();
				   }
			       }

			   }
	);
    }

    public boolean saveData() {
	//GRABAR LA INFO DE: 
	String param = "";
	pointX = getLocation().getX();
	pointY = getLocation().getY();
	param = idCard + "," + pointX + "," + pointY;
	return LibSQL.getBoolean("org.updateCardPosition", param);
    }

    public void close(boolean _askSaveData) {
	Environment.jpStatusBar.setAction("Saving data");
	if (saveData()) {
	    setIcon(true);
	    Environment.jpStatusBar.setAction("Data saved...");
	} else {
	    Environment.jpStatusBar.setAction("Error while saving data");
	}
    }

    private void retrieveData() {
	ResultSet card = LibSQL.exFunction("org.getCard", "" + idCard);
	try {
	    if (card.next()) {
		setTitle(card.getString("name"));
		pointX = card.getDouble("pointx");
		pointY = card.getDouble("pointy");
		cardDefinition = card.getString("definition");
		if (card.getString("startdate").length() > 0) {
		    tfStartDate.setValue(Proced.setFormatDate(LibSQL.getCampo(card.getString("startdate")), true));
		}
		if (card.getString("enddate").length() > 0) {
		    tfEndDate.setValue(Proced.setFormatDate(LibSQL.getCampo(card.getString("enddate")), true));
		}
		if (card.getString("filter").length() > 0) {
		    comboString = card.getString("filter");
		    updateCombo();
		}
		properties = new Properties();
		if (card.getString("properties").length() > 0) {
		    String[] props = card.getString("properties").split(";");
		    for (int i = 0; i < props.length; i++) {
			properties.setProperty(props[i].split(",")[0], props[i].split(",")[1]);
		    }
		}
		String[] xml = card.getString("xml").split(",");
		try {
		    reportURL = Environment.mainClass.getClassLoader().loadClass(xml[0]).getResource(xml[1]);
		} catch (ClassNotFoundException e) {
		    Advisor.messageBox("No se ha encontrado " + xml[0] + "/" + xml[1], "Error");
		    // TODO
		}
	    }
	} catch (SQLException e) {
	    Advisor.messageBox(e.getMessage(), "Error");
	}
	//acordarse de hacer un
	setLocation(new Point((int)(pointX), (int)pointY));
	setSize(width, height);
	/*Point(x,y) obtenidos de la base de datos*/
	//y fijarse si funciona bien cuando ejecuto el metodo setLocation, porque
	//a veces en el desktopPane no se pinta adecuadamente este BasicInternalFrame
	//y no investigué la causa todavia...
    }

    public void setIdStickyNote(int _idStickyNote) {
	idCard = _idStickyNote;
    }

    public int getIdStickyNote() {
	return idCard;
    }

    public void setPointX(double _pointX) {
	pointX = _pointX;
    }

    public double getPointX() {
	return pointX;
    }

    public void setPointY(double _pointY) {
	pointY = _pointY;
    }

    public double getPointY() {
	return pointY;
    }

    public void setText(String _text) {
	text = _text;
    }

    public String getText() {
	return text;
    }

    public void relocate() {
	this.setLocation((int)pointX, (int)pointY);
    }

    private void btnPrintReport_actionPerformed(ActionEvent e) {
	if (getStartDate() != null && getEndDate() != null) {
	    BasicReport report = new BasicReport(reportURL);
	    parseCard(report);
	    report.setProperty("startdate", tfStartDate.getText());
	    report.setProperty("enddate", tfEndDate.getText());
	    report.doReport();
	    if (Environment.debugMode) {
		saveData();
	        retrieveData();
	    }
	}
    }

    private void btnIconify_actionPerformed(ActionEvent e) {
	setIcon(true);
    }

    public void setStartDate(Date _startDate) {
	tfStartDate.setValue(_startDate);
    }

    public void setEndDate(Date _endDate) {
	tfEndDate.setValue(_endDate);
    }

    public Date getStartDate() {
	return tfStartDate.getDate();
    }

    public Date getEndDate() {
	return tfEndDate.getDate();
    }

    public void setField(String _name, String _value) {
	properties.setProperty(_name, _value);
    }

    public void parseCard(BasicReport _report) {
	String[] models = cardDefinition.split(";");
	for (int i = 0; i < models.length; i++) {
	    String[] args = models[i].split(",");
	    String params = "";
	    for (int j = 1; j < args.length; j++) {
		boolean isString = args[j].split(":")[0].equalsIgnoreCase("varchar");
		try {
		    if (args[j].split(":")[1].equalsIgnoreCase("startdate")) {
			if (isString) {
			    params += "'" + Proced.setFormatDate(tfStartDate.getText(), false) + "'";
			} else {
			    params += Proced.setFormatDate(tfStartDate.getText(), false);
			}
		    } else if (args[j].split(":")[1].equalsIgnoreCase("enddate")) {
			if (isString) {
			    params += "'" + Proced.setFormatDate(tfEndDate.getText(), false) + "'";
			} else {
			    params += Proced.setFormatDate(tfEndDate.getText(), false);
			}
		    } else if (args[j].split(":")[1].equalsIgnoreCase("filter")) {
			if (isString) {
			    params += "'" + cbFilter.getSelectedItem() + "'";
			} else {
			    params += cbFilter.getSelectedValue();
			}
		    } else {
			if (isString) {
			    params += "'" + properties.getProperty(args[j].split(":")[1]) + "'";
			} else {
			    params += properties.getProperty(args[j].split(":")[1]);
			}
		    }
		    if (j < args.length - 1) {
			params += ", ";
		    }
		} catch (NullPointerException e) {
		    Advisor.messageBox("Error al intentar definir el reporte desde su tarjeta", "Error");
		}
	    }
	    _report.addTableModel(args[0], params);
	}
    }

    public void setContent(BasicPanel _content) {
	jpContent = _content;
    }

    public void setTitle(String _title) {
	super.setTitle(_title);
	lblTitle.setText(_title);
    }

    private void updateCombo() {
	String[] query = comboString.split(":");
	if (query[0].equals("function")) {
	    cbFilter.loadJCombo(LibSQL.exFunction(query[1], "''"));
	} else {
	    cbFilter.loadJCombo(LibSQL.exQuery(query[1]));
	}
    }

}
