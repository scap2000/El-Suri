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
 * BasicInternalFrame.java
 *
 * */
package org.digitall.lib.components.basic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import org.digitall.lib.components.ComponentsManager;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

public class BasicInternalFrame extends JInternalFrame {

    private boolean needSaving = false;
    private JPopupMenu popup;
    private DesktopButton desktopButton;
    private MapButton mapButton;
    private BasicDesktop desktop;
    private int layer = 0;
    private BasicMenuItem[] desktopMenuItems;
    private String name;
    private ImageIcon icon;
    private boolean mapped;
    private boolean loaded = false;

    public BasicInternalFrame() {
	this("Ventana Sin Nombre");
    }

    public BasicInternalFrame(String _title) {
	this(_title, new ImageIcon());
    }

    public BasicInternalFrame(String _title, ImageIcon _icon) {
	this(_title, _icon, null);
    }

    public BasicInternalFrame(String _title, ImageIcon _icon, BasicDesktop _desktop) {
	super(_title.replaceAll("\\n", " "));
	name = _title;
	icon = _icon;
	desktop = _desktop;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setDefaultCloseOperation(BasicInternalFrame.DO_NOTHING_ON_CLOSE);
	this.setBorder(BorderFactory.createLineBorder(BasicConfig.INTERNALFRAME_BORDER_COLOR, 2));
	popup = new JPopupMenu();
	BasicMenuItem menuIconify = new BasicMenuItem("Iconificar");
	JMenu menuTransfer = new JMenu("Transfer to another desktop");
	BasicDesktop[] desktops = Environment.getDesktops();
	desktopMenuItems = new BasicMenuItem[desktops.length];
	for (int i = 0; i < desktops.length; i++) {
	    desktopMenuItems[i] = new BasicMenuItem(i, desktops[i].getName());
	    menuTransfer.add(desktopMenuItems[i]);
	    desktopMenuItems[i].addActionListener(new ActionListener() {

					       public void actionPerformed(ActionEvent e) {
						    transferToDesktop(Environment.getDesktops()[((BasicMenuItem)e.getSource()).getIdMenuItem()]);
					       }

					   }
	    );
	}
	popup.add(menuIconify);
	popup.add(menuTransfer);
	((BasicInternalFrameUI)getUI()).getNorthPane().addMouseListener(new MouseAdapter() {

								     public void mouseClicked(MouseEvent me) {
									 if (me.getButton() == MouseEvent.BUTTON3 && me.getClickCount() == 1) {
									     popup.show(me.getComponent(), me.getX(), me.getY());
									 }
								     }

								 }
	);
	this.addInternalFrameListener(new InternalFrameAdapter() {

				   public void internalFrameClosing(InternalFrameEvent e) {
				       close(true);
				   }

			       }
	);
	this.setContentPane(new BasicContainer());
	desktopButton = new DesktopButton(name, icon);
	mapButton = new MapButton(name, icon);
	desktopButton.addActionListener(new ActionListener() {

				     public void actionPerformed(ActionEvent e) {
					 desktopButton.setVisible(false);
					 show();
				     }

				 }
	);
	mapButton.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     show();
				 }

			     }
	);
    }

    public void transferToDesktop(BasicDesktop _desktop) {
	close();
	setDesktop(_desktop);
	show();
    }

    public void close() {
	if (desktop != null) {
	    desktop.remove(this);
	    desktop.repaint();
	}
	setVisible(false);
    }

    public void close(boolean _askSaveData) {
	if (_askSaveData && needSaving) {
	    int answer = JOptionPane.showInternalConfirmDialog(this, "¿Desea guardar los cambios?", "Cerrar", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, IconTypes.close_ro_16x16);
	    if (answer == JOptionPane.OK_OPTION) {
		Environment.jpStatusBar.setAction("Saving data");
		if (saveData()) {
		    close();
		    Environment.jpStatusBar.setAction("Data saved...");
		} else {
		    Environment.jpStatusBar.setAction("Error while saving data");
		}
	    } else if (answer == JOptionPane.NO_OPTION) {
		Environment.jpStatusBar.setAction("Data not saved...");
                if (cancelSaveData()) {
                    close();
                }
	    }
	} else {
	    close();
	}
    }

    public boolean saveData() {
	//GRABAR LA INFO
	return true;
    }
    
    public boolean cancelSaveData() {
        //NO GRABAR LA INFO
        return true;
    }
    
    public void setIcon(boolean _icon) {
	if (_icon) {
	    DesktopButton _button = isMapped() ? mapButton : desktopButton;
	    //DesktopButton _button = desktopButton;
	    close();
	    int i = 0;
	    boolean found = false;
	    while (i < desktop.getComponents().length && !found) {
		found = desktop.getComponents()[i].equals(_button);
		i++;
	    }
	    if (!found) {
		desktop.addButton(_button);
	    }
	    _button.setVisible(true);
	}
    }

    public void setNeedSaving(boolean _needSaving) {
	needSaving = _needSaving;
    }

    public void setDesktop(BasicDesktop _desktop) {
	desktop = _desktop;
    }

    public void setDesktop(BasicDesktop _desktop, int _layer) {
	layer = _layer;
	desktop = _desktop;
    }

    public void setDesktop(String _desktop) {
	setDesktop(Environment.getDesktop(_desktop));
    }

    public BasicDesktop getDesktop() {
	return desktop;
    }

    public void show() {
	if (desktop != null) {
	    int i = 0;
	    boolean found = false;
	    while (i < desktop.getComponents().length && !found) {
		found = desktop.getComponents()[i].equals(this);
		i++;
	    }
	    if (!found) {
		desktop.add(this, layer);
	    }
	    if (!loaded) {
	        initialize();
	        loaded = true;
	    }
	    super.show();
	} else {
	    //Debug.println("Asignado ActiveDesktop");
	    setDesktop(Environment.getActiveDesktop());
	    show();
	}
    }

    public void initialize() {
	ComponentsManager.setConfigurable(this);
    }

    public void createKeyBindings() {
	Action anAction = new IFrameAction();
	//getInputMap().put(KeyStroke.getKeyStroke('W', InputEvent.CTRL_DOWN_MASK), "close");
	getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke('W', InputEvent.CTRL_DOWN_MASK, true), "close");
	getActionMap().put("close", anAction);
    }

    public void destroyKeyBindings() {
	getActionMap().remove("close");
    }

    public void setClosable(boolean _closable) {
	super.setClosable(_closable);
	if (isClosable()) {
	    createKeyBindings();
	} else {
	    destroyKeyBindings();
	}
    }

    public void setTitle(String _name) {
	name = _name;
	desktopButton.setTitle(_name);
    }

    public void undecorate() {
	setBorder(null);
	((BasicInternalFrameUI)getUI()).setNorthPane(null);
    }

    public void setMapped(boolean _value) {
	mapButton.setVisible(_value);
	mapped = _value;
    }

    public MapButton getMapButton() {
	return mapButton;
    }

    public boolean isMapped() {
	return mapped;
    }
    
    public void setMapButton(MapButton _button) {
	mapButton = _button;
	mapButton.setVisible(true);
	mapButton.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     show();
				 }

			     }
	);
	
    }

    public void setToolTipText(String _toolTipText) {
	desktopButton.setToolTipText(_toolTipText);	
    }

    private class IFrameAction implements Action {

	public void addPropertyChangeListener(PropertyChangeListener arg0) {
	    //System.out.println("addPropertyChangeListener: \"U\" has been typed!");
	}

	public Object getValue(String arg0) {
	    //System.out.println("getValue: \"U\" has been typed!");
	    return null;
	}

	public boolean isEnabled() {
	    //System.out.println("isEnabled: \"U\" has been typed!");
	    close(true);
	    return false;
	}

	public void putValue(String arg0, Object arg1) {
	    //System.out.println("putValue: \"U\" has been typed!");
	}

	public void removePropertyChangeListener(PropertyChangeListener arg0) {
	    //System.out.println("removePropertyChangeListener: \"U\" has been typed!");
	}

	public void setEnabled(boolean arg0) {
	    //System.out.println("set enabled: \"U\" has been typed!");
	}

	public void actionPerformed(ActionEvent arg0) {
	    //System.out.println("action performed: \"U\" has been typed!");
	}

    }

}
