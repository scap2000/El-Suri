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
 * ComponentsManager.java
 *
 * */
package org.digitall.lib.components;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import javax.swing.JInternalFrame;

import javax.swing.JOptionPane;

import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.environment.Environment;

public abstract class ComponentsManager {

    public static void centerWindow(JDialog ventana) {
	/*patch
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Dimension frameSize = ventana.getSize();
	if (frameSize.height > screenSize.height) {
	    frameSize.height = screenSize.height;
	}
	if (frameSize.width > screenSize.width) {
	    frameSize.width = screenSize.width;
	}
	ventana.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
	endpatch*/
	ventana.setResizable(false);
	if (Environment.mainFrame != null) {
	    ventana.setLocationRelativeTo(Environment.mainFrame);
	} else {
	    ventana.setLocation(((Environment.graphicsDevice.getDefaultConfiguration().getBounds().width - ventana.getWidth()) / 2) + Environment.graphicsDevice.getDefaultConfiguration().getBounds().x, ((Environment.graphicsDevice.getDefaultConfiguration().getBounds().height - ventana.getHeight()) / 2) + Environment.graphicsDevice.getDefaultConfiguration().getBounds().y);
	}
    }

    public static void centerWindow(JFrame ventana) {
	/*patch
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Dimension frameSize = ventana.getSize();
	if (frameSize.height > screenSize.height) {
	    frameSize.height = screenSize.height;
	}
	if (frameSize.width > screenSize.width) {
	    frameSize.width = screenSize.width;
	}
	ventana.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
	endpatch*/
	ventana.setResizable(false);
	if (Environment.mainFrame != null) {
	    ventana.setLocationRelativeTo(Environment.mainFrame);
	} else {
	    ventana.setLocation(((Environment.graphicsDevice.getDefaultConfiguration().getBounds().width - ventana.getWidth()) / 2) + Environment.graphicsDevice.getDefaultConfiguration().getBounds().x, ((Environment.graphicsDevice.getDefaultConfiguration().getBounds().height - ventana.getHeight()) / 2) + Environment.graphicsDevice.getDefaultConfiguration().getBounds().y);
	}
    }

    public static void setConfigurable(final Component _component) {
	String _title = "(" + _component.getClass().getName() + ")";
	if (_component instanceof JInternalFrame) {
	    _title += ((JInternalFrame)_component).getTitle();
	} else if (_component instanceof JDialog) {
	    _title += ((JDialog)_component).getTitle();
	} else if (_component instanceof JFrame) {
	    _title += ((JFrame)_component).getTitle();
	} else {
	    _title += _component.getName();
	}
	
	final String _componentTitle = _title;
	
	restoreConfig(_component, _componentTitle);
	_component.addComponentListener(new ComponentListener() {

		    public void componentResized(ComponentEvent componentEvent) {
			saveState();
		    }

		    public void componentMoved(ComponentEvent componentEvent) {
		        saveState();
		    }

		    public void componentShown(ComponentEvent componentEvent) {
		    }

		    public void componentHidden(ComponentEvent componentEvent) {
		    }
		    
		    private void saveState() {
			if (_component.isVisible()) {
			    //Acomodar x,y con los borde derecho e inferior
			    //para evitar que quede fuera de la pantalla
			    int _x = Environment.getActiveDesktop().getBounds().width-_component.getX()-_component.getWidth();
			    int _y = Environment.getActiveDesktop().getBounds().height-_component.getY()-_component.getHeight();
			    Environment.cfg.setProperty(_componentTitle, _x + "," + _y + "," + _component.getWidth() + "," + _component.getHeight());
			}
		    }

		});
    }

    private static void restoreConfig(Component _component, String _title) {
	boolean _resizable = false;
	if (Environment.cfg.hasProperty(_title)) {
	    try {
		String[] _boundsString = Environment.cfg.getProperty(_title).split(",");
		if (_boundsString.length == 4) {

		    if (_component instanceof ExtendedInternalFrame) {
		        _resizable = false;//((ExtendedInternalFrame)_component).isResizable();
		    } else if (_component instanceof JDialog) {
		        _resizable = ((JDialog)_component).isResizable();
		    } else if (_component instanceof JFrame) {
		        _resizable = ((JFrame)_component).isResizable();
		    }
		    
		    
		    if (_resizable) {
		        _component.setSize(Integer.parseInt(_boundsString[2]), Integer.parseInt(_boundsString[3]));
		    }
		    //Acomodar x,y con los borde derecho e inferior
		    //para evitar que quede fuera de la pantalla
		    int _x = Environment.getActiveDesktop().getBounds().width-Integer.parseInt(_boundsString[2])-Integer.parseInt(_boundsString[0]);
		    int _y = Environment.getActiveDesktop().getBounds().height-Integer.parseInt(_boundsString[3])-Integer.parseInt(_boundsString[1]);
		    _component.setLocation(_x<0?0:_x, _y<0?0:_y);
		}
	    } catch (Exception x) {
		x.printStackTrace();
	    }
	}
    }

}
