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
 * BasicModalInternalFrame.java
 *
 * */
package org.digitall.lib.components.basic;

import java.awt.AWTEvent;
import java.awt.ActiveEvent;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.MenuComponent;
import java.awt.event.MouseEvent;

import java.lang.reflect.InvocationTargetException;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;

public class BasicModalInternalFrame extends JInternalFrame {

    // indica si aquest es modal o no.
    boolean modal = true;

    public BasicModalInternalFrame(String _title, ImageIcon _icon) {
	//super(_title, _icon);
	super();
    }

    @Override
    public void show() {
	super.show();
	if (this.modal) {
	    startModal();
	}
    }

    @Override
    public void setVisible(boolean value) {
	super.setVisible(value);
	if (modal) {
	    if (value) {
		startModal();
	    } else {
		stopModal();
	    }
	}
    }

    private synchronized void startModal() {
	final BasicModalInternalFrame me = this;
	Thread threadTask = new Thread(new Runnable() {

				    public void run() {
					try {
					    if (SwingUtilities.isEventDispatchThread()) {
						EventQueue theQueue = getToolkit().getSystemEventQueue();
						while (isVisible()) {
						    AWTEvent event = theQueue.getNextEvent();
						    Object source = event.getSource();
						    boolean dispatch = true;
						    if (event instanceof MouseEvent) {
							MouseEvent e = (MouseEvent)event;
							MouseEvent m = SwingUtilities.convertMouseEvent((Component)e.getSource(), e, me);
							if (!contains(m.getPoint()) && e.getID() != MouseEvent.MOUSE_DRAGGED) {
							    dispatch = false;
							}
						    }
						    if (dispatch) {
							if (event instanceof ActiveEvent) {
							    ((ActiveEvent)event).dispatch();
							} else if (source instanceof Component) {
							    ((Component)source).dispatchEvent(event);
							} else if (source instanceof MenuComponent) {
							    ((MenuComponent)source).dispatchEvent(event);
							} else {
							    System.err.println("Unable to dispatch: " + event);
							}
						    }
						}
					    } else {
						while (isVisible()) {
						    wait();
						}
					    }
					} catch (InterruptedException ignored) {

					}
				    }

				}
	    );
	//threadTask.start();
	SwingUtilities.invokeLater(threadTask);
	/* try {
	     if (SwingUtilities.isEventDispatchThread()) {
	         EventQueue theQueue = getToolkit().getSystemEventQueue();
	         while (isVisible()) {
	             AWTEvent event = theQueue.getNextEvent();
	             Object source = event.getSource();
	             boolean dispatch = true;
	             if (event instanceof MouseEvent) {
	                 MouseEvent e = (MouseEvent)event;
	                 MouseEvent m = SwingUtilities.convertMouseEvent((Component)e.getSource(), e, this);
	                 if (!this.contains(m.getPoint()) && e.getID() != MouseEvent.MOUSE_DRAGGED) {
	                     dispatch = false;
	                 }
	             }
	             if (dispatch) {
	                 if (event instanceof ActiveEvent) {
	                     ((ActiveEvent)event).dispatch();
	                 } else if (source instanceof Component) {
	                     ((Component)source).dispatchEvent(event);
	                 } else if (source instanceof MenuComponent) {
	                     ((MenuComponent)source).dispatchEvent(event);
	                 } else {
	                     System.err.println("Unable to dispatch: " + event);
	                 }
	             }
	         }
	     } else {
	         while (isVisible()) {
	             wait();
	         }
	     }
	 } catch (InterruptedException ignored) {

	 }*/
    }

    private synchronized void stopModal() {
	notifyAll();
    }

    public void setModal(boolean modal) {
	this.modal = modal;
    }

    public boolean isModal() {
	return this.modal;
    }

}
