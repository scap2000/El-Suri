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
