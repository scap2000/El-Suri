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
 * BasicTabContainer.java
 *
 * */
package org.digitall.lib.components.basic;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BasicTabContainer extends BasicContainerPanel {

    private BasicTitleLabel lblTitle;
    private BasicTabbedPane tabbedPane;
    private ExtendedInternalFrame parentInternalFrame;

    public BasicTabContainer() {
	super();
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	lblTitle = new BasicTitleLabel();
	//lblTitle.setText("TITLE");
	tabbedPane = new BasicTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
	this.setLayout(new BorderLayout());
	this.add(lblTitle, BorderLayout.NORTH);
	lblTitle.setHorizontalAlignment(JLabel.CENTER);
	lblTitle.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
	this.add(tabbedPane, BorderLayout.CENTER);
	tabbedPane.addChangeListener(new ChangeListener() {

				  public void stateChanged(ChangeEvent changeEvent) {
				      changeSelectedTab();
				  }

			      }
	);
    }

    public void addTab(BasicPrimitivePanel _panel) {
	addTab(_panel.getTitle(), _panel);
    }

    public void addTab(String _string, BasicPrimitivePanel _panel) {
	_panel.setTabbedPane(this);
	tabbedPane.addTab(_string, _panel);
    }

    public void addTab(String _string, Icon _icon, BasicPrimitivePanel _panel) {
	_panel.setTabbedPane(this);
	tabbedPane.addTab(_string, _icon, _panel);
    }

    public void addTab(String _string, Icon _icon, BasicPrimitivePanel _panel, String _toolTip) {
	_panel.setTabbedPane(this);
	tabbedPane.addTab(_string, _icon, _panel, _toolTip);
    }

    public void removeTab(int _index) {
	tabbedPane.removeTabAt(_index);
    }

    public int getTabCount() {
	return tabbedPane.getTabCount();
    }

    public int getSelectedTab() {
	return tabbedPane.getSelectedIndex();
    }

    public void setTitle(String _title) {
	lblTitle.setText(_title);
    }

    public void setTitleAt(int _index, String _title) {
	tabbedPane.setTitleAt(_index, _title);
    }

    public void changeSelectedTab() {
	//Solo para compatibilidad
    }

    public void refreshTab(int _index) {
	//Cuidado si se estan aceptando otros objetos distintos al BasicPrimitivePanel
	((BasicPrimitivePanel)tabbedPane.getComponentAt(_index)).refresh();
    }

    public void reloadTab(int _index) {
	//Cuidado si se estan aceptando otros objetos distintos al BasicPrimitivePanel
	((BasicPrimitivePanel)tabbedPane.getComponentAt(_index)).reload();
    }

    public void refresh() {
	//Cuidado si se estan aceptando otros objetos distintos al BasicPrimitivePanel
	((BasicPrimitivePanel)tabbedPane.getComponentAt(tabbedPane.getSelectedIndex())).refresh();
    }

    public void reload() {
	//Cuidado si se estan aceptando otros objetos distintos al BasicPrimitivePanel
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            ((BasicPrimitivePanel)tabbedPane.getComponentAt(i)).reload();
        }
    }

    public void setSelectedTab(int _index) {
	tabbedPane.setSelectedIndex(_index);
    }

    public ExtendedInternalFrame getParentInternalFrame() {
	return parentInternalFrame;
    }

    public void setParentInternalFrame(ExtendedInternalFrame _parent) {
	parentInternalFrame = _parent;
    }

    public BasicDesktop getDesktop() {
	return parentInternalFrame.getDesktop();
    }

    public BasicTabbedPane getTabbedPane() {
	return tabbedPane;
    }

}
