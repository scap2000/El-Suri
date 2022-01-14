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
