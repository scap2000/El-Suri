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
 * StickyNote.java
 *
 * */
package org.digitall.apps.calendar.interfaces;

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

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicTextArea;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;
import org.digitall.lib.sql.LibSQL;

public class StickyNote extends BasicInternalFrame {

    private int idStickyNote = -1;
    private double pointX = 0;
    private double pointY = 0;
    private String text = "";
    private String date = "";
    private int idUser = -1;
    private BasicTextArea jtArea = new BasicTextArea();
    private Point location = new Point();
    private int width = 160;
    private int height = 160;
    private CoordinatorStickyNote coord;
    private boolean needSaving = false;
    private JPanel jpSouth = new JPanel();
    private BasicLabel lblBackground = new BasicLabel(IconTypes.stickynotes_background);
    private BasicButton btnDelete = new BasicButton(IconTypes.stickynotes_delete);
    private BasicButton btnMinimize = new BasicButton(IconTypes.stickynotes_minimize);
    private BoxLayout boxLayout = new BoxLayout(jpSouth, BoxLayout.LINE_AXIS);
    private GridBagLayout gridBagLayout1 = new GridBagLayout();
    private GridBagLayout gridBagLayout2 = new GridBagLayout();
    private Timer relocateTimer;
    private Point pressed;
    private boolean isPressed = false;

    public StickyNote() {
	this(-1);
    }

    public StickyNote(int _idStickyNote) {
	this(_idStickyNote, "", new Point(0, 0));
    }

    public StickyNote(int _x, int _y) {
	this(-1, "", new Point(_x, _y));
    }

    public StickyNote(String _note) {
	this(-1, _note, new Point(0, 0));
    }

    public StickyNote(String _note, int _x, int _y) {
	this(-1, _note, new Point(_x, _y));
    }

    public StickyNote(int _idStickyNote, String _note, Point _location) {
	super("", IconTypes.stickynote);
	try {
	    idStickyNote = _idStickyNote;
	    jtArea.getTextArea().setText(_note);
	    location = _location;
	    setLocation(_location);
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setBounds(new Rectangle(10, 10, 160, 160));
	this.setLayout(gridBagLayout1);
	((BasicInternalFrameUI)getUI()).setNorthPane(null);
	jtArea.getTextArea().setText("NUEVA NOTA");
	jtArea.setOpaque(false);
	jpSouth.setLayout(boxLayout);
	jpSouth.setOpaque(false);
	jpSouth.setPreferredSize(new Dimension(160, 23));
	jpSouth.setMaximumSize(new Dimension(160, 23));
	jpSouth.setMinimumSize(new Dimension(160, 23));
	btnDelete.setMaximumSize(new Dimension(24, 23));
	btnDelete.setIconTextGap(0);
	btnDelete.setMinimumSize(new Dimension(24, 23));
	btnDelete.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnDelete_actionPerformed(e);
				 }

			     }
	);
	btnMinimize.setMaximumSize(new Dimension(24, 23));
	btnMinimize.setMinimumSize(new Dimension(24, 23));
	btnMinimize.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       btnMinimize_actionPerformed(e);
				   }

			       }
	);
	jtArea.getTextArea().setOpaque(false);
	jtArea.setBorder(null);
	setDesktop(Environment.getDesktop(Environment.MODULE_STICKYNOTES));
	jtArea.getTextArea().setBackground(Color.yellow);
	getContentPane().setLayout(gridBagLayout2);
	jpSouth.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	jpSouth.add(btnDelete);
	jpSouth.add(btnMinimize);
	this.getContentPane().add(jpSouth, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
	getContentPane().add(jtArea, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 108));
	this.getContentPane().add(lblBackground, new GridBagConstraints(0, 0, 1, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
	this.setSize(width, height);
	this.setClosable(true);
	this.setResizable(true);
	this.setIconifiable(true);
	jtArea.getTextArea().getDocument().addDocumentListener(new DocumentListener() {

							    public void insertUpdate(DocumentEvent documentEvent) {
								needSaving = true;
							    }

							    public void removeUpdate(DocumentEvent documentEvent) {
								needSaving = true;
							    }

							    public void changedUpdate(DocumentEvent documentEvent) {
								needSaving = true;
							    }

							}
	);
	//Elimino el icono y achica la barra de tÃ­tulo
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
	jpSouth.addMouseListener(new MouseAdapter() {

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
    }

    public boolean saveData() {
	//GRABAR LA INFO DE: 
	String param = "";
	text = jtArea.getText();
	pointX = getLocation().getX();
	pointY = getLocation().getY();
	param = idStickyNote + "," + pointX + "," + pointY + ",'" + text + "'";
	//y lo mÃ¡s importante: 
	//Environment.sessionUser;
	//sql: (select now()) (fecha/hora actual) - en formato TIMESTAMP WITHOUT TIME ZONE;
	return LibSQL.getBoolean("calendar.addUpdateStickyNote", param);
    }
    /*public void close() {
	if (idStickyNote != -1) {
	    //coord.remove(idStickyNote);
	    //super.close();
	    setIcon(true);
	}
    }*/

    public void close(boolean _askSaveData) {
	if (_askSaveData && needSaving) {
	    int answer = JOptionPane.showInternalConfirmDialog(this, "¿Desea guardar los cambios?", "Cerrar", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, IconTypes.close_ro_16x16);
	    if (answer == JOptionPane.OK_OPTION) {
		Environment.jpStatusBar.setAction("Saving data");
		if (saveData()) {
		    setIcon(true);
		    Environment.jpStatusBar.setAction("Data saved...");
		} else {
		    Environment.jpStatusBar.setAction("Error while saving data");
		}
	    } else if (answer == JOptionPane.NO_OPTION) {
		Environment.jpStatusBar.setAction("Data not saved...");
		setIcon(true);
	    }
	} else {
	    setIcon(true);
	}
    }

    private void retrieveData() {
	//acordarse de hacer un
	setLocation(new Point());
	/*Point(x,y) obtenidos de la base de datos*/
	//y fijarse si funciona bien cuando ejecuto el metodo setLocation, porque
	//a veces en el desktopPane no se pinta adecuadamente este BasicInternalFrame
	//y no investigue la causa todavia...
    }

    public void setIdStickyNote(int _idStickyNote) {
	idStickyNote = _idStickyNote;
    }

    public int getIdStickyNote() {
	return idStickyNote;
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
	jtArea.setText(text);
	needSaving = false;
    }

    public String getText() {
	return text;
    }

    public void setDate(String _date) {
	date = _date;
	setTitle(_date);
    }

    public String getDate() {
	return date;
    }

    public void setIdUser(int _idUser) {
	idUser = _idUser;
    }

    public int getIdUser() {
	return idUser;
    }

    public void setCoord(CoordinatorStickyNote _coord) {
	coord = _coord;
    }

    public void relocate() {
	this.setLocation((int)pointX, (int)pointY);
    }

    private void btnMinimize_actionPerformed(ActionEvent e) {
	setIcon(true);
    }

    private void btnDelete_actionPerformed(ActionEvent e) {
	if (idStickyNote == -1) {
	    if (Advisor.question("Cerrar stickynote", "¿Está seguro de cerrar la nota sin guardarla?")) {
		close();
	    }
	} else {
	    if (Advisor.question("Borrar stickynote", "¿Está seguro de borrar la nota?")) {
		if (LibSQL.getBoolean("deleteSticky", String.valueOf(idStickyNote))) {
		    close();
		}
	    }
	}
    }

}
