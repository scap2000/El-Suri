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
 * TableReferencePanel.java
 *
 * */
package org.digitall.common.geo.mapping.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.common.components.combos.JCombo;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.RefreshGridButton;

public class TableReferencePanel extends BasicPanel {

    private BasicLabel lblTableRefName = new BasicLabel();
    private BasicPanel jpCenter = new BasicPanel();
    private JCombo cbColumns = new JCombo();
    private RefreshGridButton btnLoad = new RefreshGridButton();
    private TableReference tableReference;
    private BorderLayout borderLayout1 = new BorderLayout();
    private AcceptButton btnAccept = new AcceptButton();

    public TableReferencePanel(TableReference _ref) {
	tableReference = _ref;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(378, 20));
	this.setLayout(borderLayout1);
	this.setMinimumSize(new Dimension(378, 20));
	this.setOpaque(true);
	if (tableReference.isForward()) {
	    setBackground(Color.green);
	} else {
	    setBackground(Color.cyan);
	}
	lblTableRefName.setText(tableReference.getReferenceCondition());
	setToolTipText(tableReference.getReferenceCondition());
	lblTableRefName.setForeground(Color.black);
	cbColumns.setPreferredSize(new Dimension(150, 20));
	btnLoad.setSize(new Dimension(20, 20));
	btnLoad.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			load();
		    }

		});
	btnLoad.setToolTipText("Obtener subrelaciones");
	btnAccept.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			acceptColumn();
		    }

		});
	this.add(jpCenter, BorderLayout.CENTER);
	jpCenter.setOpaque(false);
	jpCenter.setLayout(new BorderLayout());
	jpCenter.add(lblTableRefName, BorderLayout.CENTER);
	jpCenter.add(btnAccept, BorderLayout.EAST);
	this.add(btnLoad, BorderLayout.WEST);
    }

    public void load() {
	
    }

    public void acceptColumn() {

    }
    
    public void showColumns() {
	    for (int i = 0; i < tableReference.getColumns().size(); i++) {
	        cbColumns.addItem(tableReference.getColumns().elementAt(i).toString().split("&")[2]);
	    }
	    this.add(cbColumns, BorderLayout.EAST);
    }
    
    public String getSelectedColumn() {
	return tableReference.getColumns().elementAt(cbColumns.getSelectedIndex()).toString().split("&")[0];
    }
    
    public String getSelectedColumnDescription() {
	return tableReference.getColumns().elementAt(cbColumns.getSelectedIndex()).toString().split("&")[2];
    }
    
    public void hideColumns() {
	cbColumns.removeAllItems();
	this.remove(cbColumns);
    }

}
