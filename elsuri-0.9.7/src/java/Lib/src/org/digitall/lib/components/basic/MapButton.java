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
 * MapButton.java
 *
 * */
package org.digitall.lib.components.basic;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.digitall.lib.icons.IconTypes;

public class MapButton extends DesktopButton {

    private int id = -1;
    private Point2D.Double centroid;
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel jpLeft = new JPanel();
    private BoxLayout boxLayout = new BoxLayout(jpLeft, BoxLayout.Y_AXIS);
    private BasicButton btnExtra1 = new BasicButton(IconTypes.getIcon("iconos/16x16/extra1.png"));
    private BasicButton btnExtra2 = new BasicButton(IconTypes.getIcon("iconos/16x16/extra2.png"));
    private BasicButton btnExtra3 = new BasicButton(IconTypes.getIcon("iconos/16x16/extra3.png"));
    private BasicButton btnWarning = new BasicButton(IconTypes.getIcon("iconos/16x16/warning.png"));
    private JPanel jpTop = new JPanel();
    private BorderLayout borderLayout2 = new BorderLayout();
    private DesktopButton btnMain;
    public static final int WARNING = 0;
    public static final int EXTRA1 = 1;
    public static final int EXTRA2 = 2;
    public static final int EXTRA3 = 3;

    public MapButton(String _name, ImageIcon _icon) {
	super("");
	btnMain = new DesktopButton(_name, IconTypes.getScaledIcon(_icon, 32, 32));
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void setID(int _id) {
	id = _id;
    }

    public int getID() {
	return id;
    }

    public void setCentroid(Point2D.Double _centroid) {
	centroid = _centroid;
    }

    public Point2D.Double getCentroid() {
	return centroid;
    }

    private void jbInit() throws Exception {
	this.setLayout(borderLayout1);
	jpLeft.setLayout(boxLayout);
	jpLeft.setOpaque(false);
	btnExtra1.setSize(new Dimension(16, 16));
	btnExtra1.setPreferredSize(new Dimension(16, 16));
	btnExtra1.setMinimumSize(new Dimension(16, 16));
	btnExtra1.setMaximumSize(new Dimension(16, 16));
	btnExtra2.setPreferredSize(new Dimension(16, 16));
	btnExtra2.setMaximumSize(new Dimension(16, 16));
	btnExtra2.setMinimumSize(new Dimension(16, 16));
	btnExtra3.setMaximumSize(new Dimension(16, 16));
	btnExtra3.setMinimumSize(new Dimension(16, 16));
	btnExtra3.setPreferredSize(new Dimension(16, 16));
	btnExtra1.setVisible(false);
	btnExtra2.setVisible(false);
	btnExtra3.setVisible(false);
	btnWarning.setVisible(false);
	jpTop.setLayout(borderLayout2);
	jpTop.setOpaque(false);
	btnWarning.setMaximumSize(new Dimension(16, 16));
	btnWarning.setMinimumSize(new Dimension(16, 16));
	btnWarning.setPreferredSize(new Dimension(16, 16));
	jpLeft.add(btnExtra1, null);
	jpLeft.add(btnExtra2, null);
	jpLeft.add(btnExtra3, null);
	jpTop.add(btnWarning, BorderLayout.EAST);
	this.add(jpLeft, BorderLayout.WEST);
	this.add(jpTop, BorderLayout.NORTH);
	this.add(btnMain, BorderLayout.CENTER);
	setCursor(new Cursor(Cursor.HAND_CURSOR));
	jpLeft.setCursor(new Cursor(Cursor.HAND_CURSOR));
	btnExtra1.setCursor(new Cursor(Cursor.HAND_CURSOR));
	btnWarning.setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

    public void setBtnExtra1(BasicButton btnExtra1) {
	this.btnExtra1 = btnExtra1;
    }

    public BasicButton getBtnExtra1() {
	return btnExtra1;
    }

    public void setBtnExtra2(BasicButton btnExtra2) {
	this.btnExtra2 = btnExtra2;
    }

    public BasicButton getBtnExtra2() {
	return btnExtra2;
    }

    public void setBtnExtra3(BasicButton btnExtra3) {
	this.btnExtra3 = btnExtra3;
    }

    public BasicButton getBtnExtra3() {
	return btnExtra3;
    }

    public void setBtnWarning(BasicButton btnWarning) {
	this.btnWarning = btnWarning;
    }

    public BasicButton getBtnWarning() {
	return btnWarning;
    }

    public void setBtnMain(DesktopButton btnMain) {
	this.btnMain = btnMain;
    }

    public DesktopButton getBtnMain() {
	return btnMain;
    }

    public void addActionListener(ActionListener _actionListener) {
	btnMain.addActionListener(_actionListener);
    }

    public void setExtra(int _index, ActionListener _actionListener, String _toolTip) {
	BasicButton _button;
	switch (_index) {
	    case WARNING :
		_button = btnWarning;
		break;
	    case EXTRA1 :
		_button = btnExtra1;
		break;
	    case EXTRA2 :
		_button = btnExtra2;
		break;
	    case EXTRA3 :
		_button = btnExtra3;
		break;
	    default :
		_button = null;
		break;
	}
	if (_button != null) {
	    _button.addActionListener(_actionListener);
	    _button.setVisible(true);
	    _button.setToolTipText(_toolTip);
	}
    }

}
