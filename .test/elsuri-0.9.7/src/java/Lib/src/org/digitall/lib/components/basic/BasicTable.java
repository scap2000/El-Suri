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
 * BasicTable.java
 *
 * */
package org.digitall.lib.components.basic;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

public class BasicTable extends JTable {

    public BasicTable() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicTable(TableModel _model) {
	super(_model);
	setDefaultRenderer(Number.class, new DCellRenderer());
	setDefaultRenderer(Double.class, new DCellRenderer());
	setDefaultRenderer(Object.class, new DCellRenderer());
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /*public Component prepareRenderer(TableCellRenderer r, int row, int col) {
	Component c = super.prepareRenderer(r, row, col);
	if (false) {
	    c.setBackground(BasicConfig.TABLE_SELECTED_BACKGROUND_COLOR);
	    c.setForeground(BasicConfig.TABLE_SELECTED_FOREGROUND_COLOR);
	} else {
	    c.setBackground((row % 2 == 0) ? BasicConfig.TABLE_ALTERNATE_BACKGROUND_COLOR : BasicConfig.TABLE_BASIC_BACKGROUND_COLOR);
	    c.setForeground(BasicConfig.TABLE_NOT_SELECTED_FOREGROUND_COLOR);
	}
	return c;
    }*/

    private void jbInit() throws Exception {
	this.getTableHeader().setBackground(BasicConfig.TABLE_HEADER_BACKGROUND_COLOR);
	this.getTableHeader().setDefaultRenderer(new BasicHeaderRenderer());
    }

    private class DCellRenderer extends DefaultTableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
	    if (value instanceof Long) {
		Long in = (Long) value;
		super.getTableCellRendererComponent(table, in, selected, focused, row, column);
	        setHorizontalAlignment(RIGHT);
	    } else if (value instanceof Integer) {
		Integer in = (Integer) value;
		super.getTableCellRendererComponent(table, in, selected, focused, row, column);
	        setHorizontalAlignment(RIGHT);
	    } else if (value instanceof Double) {
		Double in = (Double) value;
		super.getTableCellRendererComponent(table, in, selected, focused, row, column);
		setHorizontalAlignment(RIGHT);
	    } else if (value instanceof Number) {
		Number in = (Number) value;
		super.getTableCellRendererComponent(table, in, selected, focused, row, column);
		setHorizontalAlignment(RIGHT);
	    } else {
	        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
	        setHorizontalAlignment(LEFT);
	    }
	    // Set the background color
	    if (selected) {
		setBackground(BasicConfig.TABLE_SELECTED_BACKGROUND_COLOR);
		setForeground(BasicConfig.TABLE_SELECTED_FOREGROUND_COLOR);
	    } else {
		setBackground((row % 2 == 0) ? BasicConfig.TABLE_ALTERNATE_BACKGROUND_COLOR : BasicConfig.TABLE_BASIC_BACKGROUND_COLOR);
		setForeground(BasicConfig.TABLE_NOT_SELECTED_FOREGROUND_COLOR);
	    }
	    return this;
	}
	
	

    }

}
