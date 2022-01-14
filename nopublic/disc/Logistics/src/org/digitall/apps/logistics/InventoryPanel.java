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
 * InventoryPanel.java
 *
 * */
package org.digitall.apps.logistics;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.JDesktopPane;

import org.digitall.lib.components.Grilla;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.environment.Environment;

//

public class InventoryPanel extends BasicContainerPanel {

    private AddButton bAdd = new AddButton();
    private CloseButton bClose = new CloseButton();
    private Component parent;
    private boolean firstLoad = true;
    private int[] tcol = { };
    private int[] tamCol = { 100, 125, 209, 75 };
    private Grilla grilla = new Grilla(30, tcol, tamCol, false, false);
    private Vector cabecera = new Vector();
    private Vector datos = new Vector();
    private JDesktopPane desktop;
    private int mode = Environment.UNSETMODE;

    public InventoryPanel(BasicDialog _parent) {
	try {
	    parent = _parent;
	    mode = Environment.STANDALONEMODE;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public InventoryPanel(BasicInternalFrame _parent, JDesktopPane _desktop) {
	try {
	    parent = _parent;
	    desktop = _desktop;
	    mode = Environment.DESKTOPMODE;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(543, 266));
	this.setBounds(new Rectangle(10, 10, 545, 266));
	bAdd.setBounds(new Rectangle(505, 240, 40, 25));
	bClose.setBounds(new Rectangle(455, 240, 40, 25));
	bClose.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bClose_actionPerformed(e);
		    }

		});
	bAdd.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bAdd_actionPerformed(e);
		    }

		});
	grilla.setSize(new Dimension(615, 374));
	grilla.setBounds(new Rectangle(-1, 0, 545, 240));
	this.add(grilla, BorderLayout.CENTER);
	this.add(bAdd, null);
	this.add(bClose, null);
	grilla.Redimensiona();
	grilla.getTable().addMouseListener(new MouseAdapter() {

		    public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
			    datos = grilla.VDatos();
			    if (mode == Environment.STANDALONEMODE) {
				BasicDialog _temp = new BasicDialog();
				_temp.setModal(true);
				_temp.getContentPane().add(new InventoryDataPanel(_temp, datos.elementAt(0).toString()));
				_temp.setSize(580, 500);
				org.digitall.lib.components.ComponentsManager.centerWindow(_temp);
				_temp.show();
				refreshPanel();
			    } else if (mode == Environment.DESKTOPMODE) {
				BasicInternalFrame _temp = new BasicInternalFrame();
				_temp.getContentPane().add(new InventoryDataPanel(_temp, datos.elementAt(0).toString()));
				Rectangle rect = parent.getBounds();
				int x = (int)(rect.getX() + rect.getWidth());
				int y = (int)(rect.getY() + rect.getHeight());
				_temp.setBounds(x, y, 580, 500);
				_temp.setClosable(false);
				_temp.setResizable(true);
				_temp.setIconifiable(true);
				desktop.add(_temp);
				_temp.show();
			    }
			}
		    }

		});
	refreshPanel();
    }

    private void bOk_actionPerformed(ActionEvent e) {
	dispose();
    }

    private void refreshPanel() {
	String consulta = "SELECT idresource, name, code, description, amount FROM inventory.resources WHERE estado <> '*' ORDER BY idresource";
	String consultaCount = "SELECT COUNT(*) FROM (" + consulta + ") AS foo";
	cabecera.removeAllElements();
	cabecera.add("*");
	cabecera.add("Nombre");
	cabecera.add("Código");
	cabecera.add("Descripción");
	cabecera.add("Cantidad");
	grilla.ActualizaTabla(parent, consulta, consultaCount, cabecera);
	firstLoad = false;
    }

    private void bAdd_actionPerformed(ActionEvent e) {
	if (mode == Environment.STANDALONEMODE) {
	    BasicDialog _temp = new BasicDialog();
	    _temp.setModal(true);
	    _temp.getContentPane().add(new InventoryDataPanel(_temp));
	    _temp.setSize(580, 500);
	    org.digitall.lib.components.ComponentsManager.centerWindow(_temp);
	    _temp.show();
	    refreshPanel();
	} else if (mode == Environment.DESKTOPMODE) {
	    BasicInternalFrame _temp = new BasicInternalFrame();
	    _temp.getContentPane().add(new InventoryDataPanel(_temp));
	    Rectangle rect = parent.getBounds();
	    int x = (int)(rect.getX() + rect.getWidth());
	    int y = (int)(rect.getY() + rect.getHeight());
	    _temp.setBounds(x, y, 580, 500);
	    _temp.setClosable(false);
	    _temp.setResizable(true);
	    _temp.setIconifiable(true);
	    desktop.add(_temp);
	    _temp.show();
	}
    }

    private void dispose() {
	if (mode == Environment.STANDALONEMODE) {
	    ((BasicDialog)parent).dispose();
	} else if (mode == Environment.DESKTOPMODE) {
	    ((BasicInternalFrame)parent).dispose();
	}
    }

    private void bClose_actionPerformed(ActionEvent e) {
	dispose();
    }

}
