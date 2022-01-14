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
 * FilterQueryBuilderPanel.java
 *
 * */
package org.digitall.common.geo.mapping.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.EventObject;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.event.CellEditorListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.digitall.lib.components.ComponentsManager;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.geo.mapping.classes.Layer;
import org.digitall.lib.geo.mapping.classes.LayerConfig;
import org.digitall.lib.sql.LibSQL;

public class FilterQueryBuilderPanel extends BasicDialog {

    private JTree entitiesTree;
    private BasicScrollPane spEntitiesTree = new BasicScrollPane();
    private DefaultTreeModel treeModel;
    private EntitiesTreeCellRenderer entitiesTreeCellRenderer = new EntitiesTreeCellRenderer();
    private EntitiesTreeCellEditor entitiesTreeCellEditor = new EntitiesTreeCellEditor();
    private Layer layer;
    private Vector<TableReference> tableReferences = new Vector<TableReference>();
    private TableReference selectedTableReference;
    private String labelScheme;
    private String labelTable;
    private String tableColumn;
    private String labelColumn;
    private StringBuffer labelQuery;
    private BasicPrimitivePanel jpContent = new BasicPrimitivePanel();
    
    public FilterQueryBuilderPanel(StringBuffer _query) {
	super(new JFrame(), "Constructor de filtros", true);
	labelQuery = _query;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setSize(500,300);
	ComponentsManager.setConfigurable(this);
    }

    private void loadData() {
	setLayout(new BorderLayout());
	jpContent.add(spEntitiesTree, BorderLayout.CENTER);
	String _scheme = layer.getGeometrySetConfig().getSqlScheme();
	String _table = layer.getGeometrySetConfig().getSqlTable();
	DefaultMutableTreeNode root = new DefaultMutableTreeNode("Relaciones de " + _scheme + "." + _table);
	entitiesTree = new JTree(root);
	entitiesTree.setRowHeight(25);
	buildJTree(root, _scheme, _table);
	entitiesTree.setEditable(true);
	treeModel = (DefaultTreeModel)entitiesTree.getModel();
	entitiesTree.setCellRenderer(entitiesTreeCellRenderer);
	entitiesTree.setCellEditor(entitiesTreeCellEditor);
	entitiesTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
	spEntitiesTree.getViewport().add(entitiesTree, BorderLayout.CENTER);
	this.add(jpContent, BorderLayout.CENTER);
    }

    public void setLayer(Layer _layer) {
	layer = _layer;
	loadData();
    }

    private void buildJTree(DefaultMutableTreeNode _node, String _scheme, String _table) {
	/*Claves foráneas descendentes*/
	String _fwQuery = "SELECT pg_constraint.oid, pg_constraint.conname, " + 
	"(SELECT " + 
	"pg_attribute.attname FROM pg_attribute " + 
	"WHERE pg_attribute.attrelid = conrelid AND " + 
	"pg_attribute.attnum = any(conkey)) AS idcolumn, " + 
	"(SELECT " + 
	"pg_namespace.nspname FROM " + 
	"pg_class, pg_namespace WHERE " + 
	"pg_class.oid = confrelid AND " + 
	"pg_class.relnamespace = pg_namespace.oid " + 
	") AS scheme, " + 
	"(SELECT " + 
	"pg_class.relname FROM " + 
	"pg_class, pg_attribute WHERE " + 
	"pg_class.oid = confrelid AND " + 
	"pg_attribute.attrelid = confrelid AND " + 
	"pg_attribute.attnum = any(confkey)) AS table, " + 
	"(SELECT " + 
	"pg_attribute.attname FROM " + 
	"pg_class, pg_attribute WHERE " + 
	"pg_class.oid = confrelid AND " + 
	"pg_attribute.attrelid = confrelid AND " + 
	"pg_attribute.attnum = any(confkey)) AS column " + 
	"FROM pg_constraint, pg_class, pg_namespace nsp WHERE " + 
	"pg_class.relname = '" + _table + "' AND " + 
	"nsp.nspname = '" + _scheme + "' AND " + 
	"pg_class.relnamespace = nsp.oid AND " + 
	"pg_constraint.conrelid = pg_class.oid AND " + 
	"pg_constraint.contype = 'f';";

	String _bwQuery = "SELECT pg_constraint.oid, pg_constraint.conname, " + 
	"(SELECT " + 
	"pg_attribute.attname FROM pg_attribute " + 
	"WHERE pg_attribute.attrelid = confrelid AND " + 
	"pg_attribute.attnum = any(confkey)) AS idcolumn, " + 
	"(SELECT " + 
	"pg_namespace.nspname FROM " + 
	"pg_class, pg_namespace WHERE " + 
	"pg_class.oid = conrelid AND " + 
	"pg_class.relnamespace = pg_namespace.oid " + 
	") AS scheme, " + 
	"(SELECT " + 
	"pg_class.relname FROM " + 
	"pg_class, pg_attribute WHERE " + 
	"pg_class.oid = conrelid AND " + 
	"pg_attribute.attrelid = conrelid AND " + 
	"pg_attribute.attnum = any(conkey)) AS table, " + 
	"(SELECT " + 
	"pg_attribute.attname FROM " + 
	"pg_class, pg_attribute WHERE " + 
	"pg_class.oid = conrelid AND " + 
	"pg_attribute.attrelid = conrelid AND " + 
	"pg_attribute.attnum = any(conkey)) AS column " + 
	"FROM pg_constraint, pg_class, pg_namespace nsp WHERE " + 
	"pg_class.relname = '" + _table + "' AND " + 
	"nsp.nspname = '" + _scheme + "' AND " + 
	"pg_class.relnamespace = nsp.oid AND " + 
	"pg_constraint.confrelid = pg_class.oid AND " + 
	"pg_constraint.contype = 'f';";
	
	try {
	    ResultSet _forwardReferences = LibSQL.exQuery(_fwQuery);
	    while (_forwardReferences.next()) {
		TableReference _ref = new TableReference(_forwardReferences.getString("oid"));
		if (addTableReference(_ref)) {
		    _ref.setSourceScheme(_scheme);
		    _ref.setSourceTable(_table);
		    _ref.setSourceColumn(_forwardReferences.getString("idcolumn"));
		    _ref.setDestScheme(_forwardReferences.getString("scheme"));
		    _ref.setDestTable(_forwardReferences.getString("table"));
		    _ref.setDestColumn(_forwardReferences.getString("column"));
		    _ref.setForward();
		    _ref.loadColumns();
		    DefaultMutableTreeNode _relation = new DefaultMutableTreeNode(_ref);
		    _node.add(_relation);
		}
	    }
	    ResultSet _backwardReferences = LibSQL.exQuery(_bwQuery);
	    while (_backwardReferences.next()) {
	        TableReference _ref = new TableReference(_backwardReferences.getString("oid"));
	        if (addTableReference(_ref)) {
		    _ref.setSourceScheme(_scheme);
		    _ref.setSourceTable(_table);
		    _ref.setSourceColumn(_backwardReferences.getString("idcolumn"));
		    _ref.setDestScheme(_backwardReferences.getString("scheme"));
		    _ref.setDestTable(_backwardReferences.getString("table"));
		    _ref.setDestColumn(_backwardReferences.getString("column"));
		    _ref.setBackward();
		    _ref.loadColumns();
		    DefaultMutableTreeNode _relation = new DefaultMutableTreeNode(_ref);
		    _node.add(_relation);
		}
	    }
	} catch (SQLException e) {
	    // TODO
	}
	//entitiesTree.expandPath(entitiesTree.getClosestPathForLocation(0,0));
	
	entitiesTree.scrollPathToVisible(new TreePath(_node.getLastLeaf().getPath()));

	entitiesTree.updateUI();
    }

    private boolean addTableReference(TableReference _ref) {
	int i = 0;
	boolean found = false;
	while (i < tableReferences.size() && !found) {
	    found = tableReferences.elementAt(i).getId().equals(_ref.getId());
	    i++;
	}
	if (found) {
	    return false;
	} else {
	    return tableReferences.add(_ref);
	}
    }
    
    private void buildQuery() {
	String _labelQuery = "";
	TreePath _treePath = entitiesTree.getEditingPath();
	Object[] _path = _treePath.getPath();
	String _from = "";
	String _condition = "";
	if (_path.length > 0) {
	    TableReference _ref = (TableReference)((DefaultMutableTreeNode)_path[_path.length-1]).getUserObject();
	    labelScheme = _ref.getDestScheme();
	    labelTable = _ref.getDestTable();
	    labelColumn = labelTable + "_" + tableColumn;
	    for (int i = _path.length-1; i > 1; i--) {
	        _ref = (TableReference)((DefaultMutableTreeNode)_path[i]).getUserObject();
	        _from += _ref.getDestScheme() + "." + _ref.getDestTable() + ", ";
	        _condition += _ref.getReferenceCondition() + " AND \n";
	    }

	    _ref = (TableReference)((DefaultMutableTreeNode)_path[1]).getUserObject();
	    _from += _ref.getDestScheme() + "." + _ref.getDestTable();
	    _condition += _ref.getReferenceCondition();
	}
	_labelQuery += "(SELECT " + labelTable + "." + tableColumn + " FROM " + _from + " WHERE " + _condition  + " LIMIT 1) AS " + labelColumn;
	labelQuery.append(_labelQuery);
	dispose();
    }
    
    public StringBuffer getLabelQuery() {
	return labelQuery;
    }

    public String getLabelScheme() {
	return labelScheme;
    }

    public String getLabelTable() {
	return labelTable;
    }

    public String getLabelColumn() {
	return labelColumn;
    }

    public String getTableColumn() {
	return tableColumn;
    }

    public class EntitiesTreeCellRenderer extends DefaultTreeCellRenderer {

	Component renderer;

	public EntitiesTreeCellRenderer() {

	}

	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
	    Component returnValue = new BasicPanel();
	    if ((value != null) && (value instanceof DefaultMutableTreeNode)) {
		Object userObject = ((DefaultMutableTreeNode)value).getUserObject();
		if (userObject instanceof TableReference) {
		    //TreePath _treePath = entitiesTree.getSelectionPath();
		    //Object[] _path = _treePath.getPath();
		    if (/*_path.length*/1 > 0) {
			//selectedTableReference = (TableReference)((DefaultMutableTreeNode)_path[_path.length-1]).getUserObject();
			selectedTableReference = (TableReference)userObject;
		        TableReferencePanel _renderer = new TableReferencePanel(selectedTableReference);
		        _renderer.showColumns();
		        renderer = _renderer;
		        if (isSelected) {
		            _renderer.setBorder(BorderFactory.createLineBorder(Color.red, 1));
		        } else {
		            _renderer.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		        }
		    }
		} else {
		    renderer = new TableReferencePanel(new TableReference("-1"));
		    renderer.setEnabled(false);
		    renderer.setBackground(Color.white);
		}
		//renderer.setPreferredSize(renderer.getSize());
		returnValue = renderer;
		//returnValue.setEnabled(true);*/
	    }
	    return returnValue;
	}

    }

    private class EntitiesTreeCellEditor implements TreeCellEditor {

	Component editor;

	public EntitiesTreeCellEditor() {

	}

	public Component getTreeCellEditorComponent(JTree tree, final Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {
	    Component returnValue = new BasicPanel();
	    if ((value != null) && (value instanceof DefaultMutableTreeNode)) {
		Object userObject = ((DefaultMutableTreeNode)value).getUserObject();
		if (userObject instanceof TableReference) {
		    TreePath _treePath = entitiesTree.getEditingPath();
		    //Object[] _path = _treePath.getPath();
		    if (/*_path.length*/1 > 0) {
			    //selectedTableReference = (TableReference)((DefaultMutableTreeNode)_path[_path.length-1]).getUserObject();
			    selectedTableReference = (TableReference)userObject;
			    TableReferencePanel _editor = new TableReferencePanel(selectedTableReference) {
			    public void load() {
			        selectedTableReference = (TableReference)((DefaultMutableTreeNode)value).getUserObject();
				buildJTree((DefaultMutableTreeNode)value, selectedTableReference.getDestScheme(), selectedTableReference.getDestTable());
			    }
			    
			    public void acceptColumn() {
			        tableColumn = this.getSelectedColumn();
			        labelColumn = this.getSelectedColumnDescription();
				buildQuery();
			    }
			};
		        if (isSelected) {
		            _editor.setBorder(BorderFactory.createLineBorder(Color.red, 1));
		        } else {
		            _editor.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		        }
		        _editor.showColumns();
		        editor = _editor;
		    }
		} else {
		    editor = new TableReferencePanel(new TableReference("-1"));
		    editor.setEnabled(false);
		    editor.setBackground(Color.white);
		}
		//editor.setPreferredSize(editor.getSize());
		returnValue = editor;
	    }
	    return returnValue;
	}

	public Object getCellEditorValue() {
	    return null;
	}

	public boolean isCellEditable(EventObject anEvent) {
	    return true;
	}

	public boolean shouldSelectCell(EventObject anEvent) {
	    return false;
	}

	public boolean stopCellEditing() {
	    return false;
	}

	public void cancelCellEditing() {

	}

	public void addCellEditorListener(CellEditorListener l) {

	}

	public void removeCellEditorListener(CellEditorListener l) {

	}

    }

}
