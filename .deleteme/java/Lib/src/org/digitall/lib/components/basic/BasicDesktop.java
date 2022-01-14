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
 * BasicDesktop.java
 *
 * */
package org.digitall.lib.components.basic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import java.beans.PropertyVetoException;

import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.Timer;

import org.digitall.lib.icons.IconTypes;

public class BasicDesktop extends JDesktopPane {

    private static final String DEFAULT_NAME = "basicDesktop";
    private BasicLabel lblTopLeft;
    private BasicLabel lblBottomLeft;
    private BasicLabel lblTopRight;
    private int xPadding = 22;
    private int yPadding = 22;
    private int idDesktop = -1;
    private int buttons = 0;
    private Color startColor = BasicConfig.DESKTOP_GRADIENT_START_COLOR;
    private Color endColor = BasicConfig.DESKTOP_GRADIENT_END_COLOR;
    private boolean active = false;
    private Timer timerShow;
    private int period = 10;
    private boolean opaco = true;
    private Component bottomRightComponent = null;
    private int dockBorder = 5;

    public BasicDesktop() {
	super();
	setName(DEFAULT_NAME);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicDesktop(int _idDesktop, String _desktopName) {
	super();
	idDesktop = _idDesktop;
	setName(_desktopName);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setOpaque(true);
	setOpaco(true);
	setAutoscrolls(true);
	setBackground(BasicConfig.DESKTOP_BACKGROUND_COLOR);
	ImageIcon topLeftIcon = IconTypes.desktop_top_left;
	lblTopLeft = new BasicLabel(topLeftIcon);
	lblTopLeft.setSize(topLeftIcon.getIconWidth(), topLeftIcon.getIconHeight());
	ImageIcon bottomLeftIcon = IconTypes.desktop_bottom_left;
	lblBottomLeft = new BasicLabel(bottomLeftIcon);
	lblBottomLeft.setSize(bottomLeftIcon.getIconWidth(), bottomLeftIcon.getIconHeight());
	ImageIcon topRightIcon = IconTypes.desktop_top_right;
	lblTopRight = new BasicLabel(topRightIcon);
	lblTopRight.setSize(topRightIcon.getIconWidth(), topRightIcon.getIconHeight());
	add(lblTopLeft, new Integer(Integer.MIN_VALUE));
	add(lblBottomLeft, new Integer(Integer.MIN_VALUE));
	add(lblTopRight, new Integer(Integer.MIN_VALUE));
	this.addComponentListener(new ComponentListener() {

			       public void componentHidden(ComponentEvent componentEvent) {
				   relocateComponents();
			       }

			       public void componentMoved(ComponentEvent componentEvent) {
				   relocateComponents();
			       }

			       public void componentResized(ComponentEvent componentEvent) {
				   relocateComponents();
			       }

			       public void componentShown(ComponentEvent componentEvent) {
				   relocateComponents();
			       }

			   }
	);
	relocateComponents();
    }

    public void relocateComponents() {

	lblTopLeft.setLocation(xPadding, yPadding);
	lblBottomLeft.setLocation(xPadding, (int)getBounds().getMaxY() - lblBottomLeft.getHeight() - yPadding);
	lblTopRight.setLocation((int)(getBounds().getMaxX() - getLocation().getX()) - lblTopRight.getWidth() - xPadding, (int)getBounds().getMinY() + yPadding);
	//cascade();
	relocateButtons();
	relocateDesktops();
	if (bottomRightComponent != null) {
	    int x = (int)((getBounds().getMaxX() - getLocation().getX()) - (bottomRightComponent.getWidth() + dockBorder));
	    int y = (int)((getBounds().getMaxY() - getLocation().getY()) - (bottomRightComponent.getHeight() + dockBorder));
	    bottomRightComponent.setLocation(x, y);
	}
	Component[] components = getComponents();
	int i = 0;
	boolean found = false;
	while (i < components.length && !found) {
	    if (components[i] instanceof BasicPrimitivePanel) {
		components[i].setBounds(getBounds());
		found = true;
	    }
	    i++;
	}
    }

    private void cascade() {
	int separation = 24;
	Component[] frames = getComponents();
	Vector extendedInternalFrames = new Vector();
	for (int i = 0; i < frames.length; i++) {
	    if (frames[i] instanceof ExtendedInternalFrame) {
		extendedInternalFrames.add(frames[i]);
	    }
	}
	Rectangle dBounds = getBounds();
	int margin = frames.length * separation + separation;
	int width = dBounds.width - margin;
	int height = dBounds.height - margin;
	if (height < 40)
	    height = 40;
	int x = 0;
	int y = 0;
	for (int i = 0; i < extendedInternalFrames.size(); i++) {
	    //frames[i].setBounds(x, y, width, height);
	    ((ExtendedInternalFrame)extendedInternalFrames.elementAt(i)).setLocation(x, y);
	    x += separation;
	    y += separation;
	}
    }
    /*public void paintComponent(Graphics g) {
	Graphics2D g2 = (Graphics2D)g;
	g2.setPaint(new GradientPaint(100, 100, Color.blue, 200, 200, Color.white));
	Rectangle rec = new Rectangle(getWidth(), getHeight());
	g2.fill(rec);
    }*/

    public void paintComponent(Graphics g) {
	if (isOpaco()) {
	    Graphics2D g2 = (Graphics2D)g;
	    int w = getWidth();
	    int h = getHeight();
	    GradientPaint gradient = new GradientPaint(0, 0, startColor, w, 0, endColor, false);
	    g2.setPaint(gradient);
	    g2.fillRect(0, 0, w, h);
	} else {
	    setBackground(startColor);
	    super.paintComponent(g);
	}
    }

    public void remove(Component _component) {
	super.remove(_component);
	int i = 0;
	boolean found = false;
	while (i < getComponents().length && !found) {
	    if (getComponents()[i] instanceof BasicInternalFrame) {
		try {
		    ((BasicInternalFrame)getComponents()[i]).setSelected(true);
		    found = true;
		} catch (PropertyVetoException x) {
		    continue;
		}
	    }
	    i++;
	}
    }

    public void addButton(DesktopButton _button) {
	buttons++;
	_button.setSize(_button.getPreferredSize());
	add(_button);
	relocateButtons();
    }

    public void removeButton(DesktopButton _button) {
	buttons--;
	remove(_button);
	relocateButtons();
    }

    public void relocateButtons() {
	int i = 0;
	double maxY = 0;
	int horizontalGap = 50;
	int x = horizontalGap;
	int verticalGap = 50;
	int y = verticalGap;
	while (i < getComponents().length) {
	    if (getComponents()[i] instanceof DesktopButton && !(getComponents()[i] instanceof MapButton)) {
		DesktopButton _dkButton = (DesktopButton)getComponents()[i];
		if (_dkButton.getPreferredSize().getHeight() > maxY) {
		    maxY = _dkButton.getPreferredSize().getHeight();
		}
		if (getWidth() > x + _dkButton.getPreferredSize().getWidth()) {
		    _dkButton.setLocation(x, y);
		    x += _dkButton.getPreferredSize().getWidth() + horizontalGap;
		} else {
		    y += maxY + verticalGap;
		    maxY = 0;
		    x = horizontalGap;
		    _dkButton.setLocation(x, y);
		    x += _dkButton.getPreferredSize().getWidth() + horizontalGap;
		}
	    }
	    i++;
	}
    }

    public Vector<BasicDesktop> getDesktops() {
	Vector<BasicDesktop> _desktops = new Vector();
	int i = 0;
	while (i < getComponents().length) {
	    if (getComponents()[i] instanceof BasicDesktop) {
		_desktops.add((BasicDesktop)getComponents()[i]);
	    }
	    i++;
	}
	return _desktops;
    }

    public void relocateDesktops() {
	Vector<BasicDesktop> _desktops = getDesktops();
	for (int i = 0; i < _desktops.size(); i++) {
	    _desktops.elementAt(i).setSize(getSize());
	    if (_desktops.elementAt(i).isActive()) {
		_desktops.elementAt(i).setLocation(0, 0);
		_desktops.elementAt(i).setVisible(true);
	    } else {
		_desktops.elementAt(i).setLocation(getWidth(), 0);
	    }
	}
    }

    public int getIdDesktop() {
	return idDesktop;
    }

    public void setStartColor(Color _startColor) {
	startColor = _startColor;
    }

    public Color getStartColor() {
	return startColor;
    }

    public void setEndColor(Color _endColor) {
	endColor = _endColor;
    }

    public Color getEndColor() {
	return endColor;
    }

    public void setActive(boolean _active) {
	active = _active;
    }

    public boolean isActive() {
	return active;
    }

    public void switchDesktops(final BasicDesktop _old, final BasicDesktop _new) {
	if (_old != _new) {
	    _new.setSize(getSize());
	    _old.setSize(getSize());
	    _new.setLocation(getWidth(), 0);
	    _new.setActive(true);
	    _old.setActive(false);
	    _new.setVisible(true);
	    //final boolean oldOpaco = _old.isOpaco();
	    //final boolean newOpaco = _new.isOpaco();
	    //_new.setOpaco(false);
	    //_old.setOpaco(false);
	    final int step = 50;
	    setLayer(_old, -1);
	    setLayer(_new, 0);
	    if (timerShow != null) {
		timerShow.stop();
	    }
	    timerShow = new Timer(period, new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   if (_new.getLocation().x - step > 0) {
				       //System.out.println("running");
				       //_old.setLocation(_old.getLocation().x-step, _old.getLocation().y);
				       _new.setLocation(_new.getLocation().x - step, _new.getLocation().y);
				   } else {
				       //System.out.println("running stopped");
				       _old.setVisible(false);
				       relocateDesktops();
				       timerShow.stop();
				       //_new.setOpaco(newOpaco);
				       //_old.setOpaco(oldOpaco);
				   }
			       }

			   }
		);
	    timerShow.start();
	}
    }

    public void setOpaco(boolean _opaco) {
	opaco = _opaco;
    }

    public boolean isOpaco() {
	return opaco;
    }

    public void setBottomRightComponent(Component _bottomRightComponent) {
	bottomRightComponent = _bottomRightComponent;
    }

}
