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
 * LayerList.java
 *
 * */
package org.digitall.common.geo.mapping.components;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;

import org.digitall.common.geo.mapping.BasicDrawEngine;
import org.digitall.lib.components.basic.BasicTable;
import org.digitall.lib.geo.gaia.GaiaPrimitiveForm;
import org.digitall.lib.geo.mapping.classes.Layer;
import org.digitall.lib.geo.mapping.components.LayerTableModel;
import org.digitall.lib.geo.shapefile.ShapeTypes;

public class LayerList extends BasicTable {

    private LayerTableModel model = new LayerTableModel();
    private boolean firstLoad = true;
    private Vector layers;
    private BasicDrawEngine panel;
    private JCheckBox btnOn = new JCheckBox("", false);
    private JCheckBox btnActive = new JCheckBox("", false);

    public LayerList(LayerTableModel _model) {
	super(_model);
	model = _model;
	btnOn.addActionListener(new ActionListener() {

			     public void actionPerformed(ActionEvent e) {
				    JCheckBox _check = (JCheckBox)e.getSource();
				    int i = rowAtPoint(new Point(_check.getX(),_check.getY()));
				    ((Layer)layers.elementAt(i)).setOn(_check.isSelected());
				    setValueAt(_check.isSelected(), i, 0); 
			     }

			 }
	);
	btnActive.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     for (int i = 0; i < layers.size(); i++) {
				         Layer _layer = (Layer)layers.elementAt(i);
					 if (i != rowAtPoint(new Point(((JCheckBox)e.getSource()).getX(),((JCheckBox)e.getSource()).getY()))){
					     _layer.setActive(false);
					     setValueAt(false, i, 1);
					     if (_layer.getEditionForm() instanceof GaiaPrimitiveForm) {
					         ((GaiaPrimitiveForm)_layer.getEditionForm()).hideForm();
					     }
					 } else {
					     _layer.setOn();
					     if (_layer.getGeometrySet().getGeometrySetConfig().getShapeType() == ShapeTypes.POINT || _layer.getGeometrySet().getGeometrySetConfig().getShapeType() == ShapeTypes.MULTIPOINT) {
					         setValueAt(true, i, 0);
					         setValueAt(true, i, 1);
						 _layer.setActive(true);
						 if (_layer.getEditionForm() != null) {
						     if (_layer.getEditionForm() instanceof GaiaPrimitiveForm) {
						         ((GaiaPrimitiveForm)_layer.getEditionForm()).showForm();
						     }
						 }
					     } else {
					         setValueAt(false, i, 1);
					         _layer.setOff();
					         if (_layer.getEditionForm() instanceof GaiaPrimitiveForm) {
					             ((GaiaPrimitiveForm)_layer.getEditionForm()).hideForm();
					         }
					     }
					 }
				     }
				 }

			     }
	);
    }

    public void setDrawPanel(BasicDrawEngine _panel) {
	panel = _panel;
	if (firstLoad) {
	    model.addColumn("On");
	    model.addColumn("Act.");
	    model.addColumn("Layer");
	    firstLoad = false;
	}
	layers = panel.getLayers();
	model.setRowCount(0);
	for (int i = 0; i < layers.size(); i++) {
	    Layer _layer = (Layer)layers.elementAt(i);
	    Vector data = new Vector();
	    data.add(Boolean.valueOf(_layer.isOn()));
	    data.add(Boolean.valueOf(_layer.isActive()));
	    data.add(_layer.getAlias());
	    // data.add(CoordinateSystems.getProjectionType(_layer.getProjectionType()));
	    // data.add(ShapeTypes.getShapeType(_layer.getShapeType()));
	    model.addRow(data);
	}
	getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(btnOn));
	getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(btnActive));
	getColumnModel().getColumn(0).setMaxWidth(34);
	getColumnModel().getColumn(1).setMaxWidth(34);
	getColumnModel().getColumn(0).setWidth(34);
	getColumnModel().getColumn(1).setWidth(34);
    }

}
