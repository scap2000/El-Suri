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
