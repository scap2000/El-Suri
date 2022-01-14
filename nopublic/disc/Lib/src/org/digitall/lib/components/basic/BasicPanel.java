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
 * BasicPanel.java
 *
 * */
package org.digitall.lib.components.basic;

import java.awt.LayoutManager;

import javax.swing.JPanel;

import org.digitall.lib.components.BorderPanel;

public class BasicPanel extends JPanel {

    private String title = "";

    public BasicPanel() {
	this("");
    }

    public BasicPanel(String _title) {
	super();
	title = _title;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicPanel(LayoutManager _layout) {
	super(_layout);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setBackground(BasicConfig.PANEL_BACKGROUND_COLOR);
	if (title.trim().length() > 0) {
	    setTitle(title);
	}
    }


    public void setTitle(String _title) {
	setBorder(BorderPanel.getBorderPanel(_title));
	//setBorder(BorderPanel.getBorderPanel(_title, BasicConfig.LABEL_FOREGROUND_COLOR, this.getBackground()));
    }

}


/*public class BasicPanel extends JXLayer {

    private Painter translucentPainter = new TranslucentPainter();
    private JPanel panel = new JPanel();
    private String title = "";
    private LayoutManager layout = null;

    public BasicPanel() {
	this("");
    }

    public BasicPanel(String _title) {
	super();
	title = _title;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setPainter(translucentPainter);
	panel.setLayout(null);
	setView(panel);
	this.setBackground(BasicConfig.PANEL_BACKGROUND_COLOR);
	if (title.trim().length() > 0) {
	    setTitle(title);
	}
    }

    public Component add(Component component) {
	panel.add(component);
	setView(panel);
	return component;
    }

    public void add(Component component, Object object) {
	panel.add(component, object);
	setView(panel);
    }

    public void setLayout(LayoutManager layoutManager) {
	if (layoutManager != null) {
	    super.setLayout(layoutManager);
	}
    }

    static class TranslucentPainter<V extends JComponent> extends AbstractPainter<V> {

	public void paint(Graphics2D g2, JXLayer<V> l) {
	    l.paint(g2);
	    if (l.isLocked()) {
		// the view is hidden, so we need to paint it manually,
		// it is important to call print() instead of paint() here
		// because print() doesn't affect the frame's double buffer
		l.getView().print(g2);
		g2.setColor(new Color(0, 128, 128, 128));
		g2.fillRect(0, 0, l.getWidth(), l.getHeight());
	    }
	}

    }

    public void setTitle(String _title) {
	panel.setBorder(BorderPanel.getBorderPanel(_title, BasicConfig.LABEL_FOREGROUND_COLOR, this.getBackground()));
    }

}
*/