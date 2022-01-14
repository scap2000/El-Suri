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
