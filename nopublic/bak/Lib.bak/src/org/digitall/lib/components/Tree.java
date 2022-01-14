package org.digitall.lib.components;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import org.digitall.lib.sql.LibSQL;

//

public class Tree extends JTree {

    public Tree() {
    }

    public JTree createTree(String _table, String _idField, String _nameField, String _idParent) throws Exception {
	ResultSet treeRS = LibSQL.exQuery("Select * from " + _table + " where " + _idField + "=" + _idParent + " and estado<>'*' order by " + _idField);
	if (treeRS.next()){
	    DefaultMutableTreeNode arbol = new DefaultMutableTreeNode(treeRS.getString(_idField) + " - " + treeRS.getString(_nameField));
	    treeRS = LibSQL.exQuery("Select * from " + _table + " where idparent = " + _idParent + " and estado<>'*' order by " + _idField);
	    createSubTree(_table, _idField, _nameField, arbol, treeRS);
	    return new JTree(arbol);
	} else {
	    Advisor.messageBox("No existen items cargados","Items inexistentes <"+ _table +">");
	    return null;
	}
    }

    public void createSubTree(String _table, String _idField, String _nameField, DefaultMutableTreeNode _parentField, ResultSet rs) throws SQLException {
	while (rs.next()) {
	    DefaultMutableTreeNode hijos = new DefaultMutableTreeNode(rs.getString(_idField) + " - " + rs.getString(_nameField));
	    _parentField.add(hijos);
	    ResultSet subTreeRS = LibSQL.exQuery("Select * from " + _table + " where estado<>'*' and idparent = " + rs.getString(_idField) + " order by " + _idField);
	    createSubTree(_table, _idField, _nameField, hijos, subTreeRS);
	}
    }

    public JTree createTree(String _table, String _idField, String _nameField, String _idParent, String _codeField) throws SQLException {
	ResultSet treeRS = LibSQL.exQuery("SELECT * FROM " + _table + " WHERE " + _idField + "=" + _idParent + " AND estado<>'*' ORDER BY " + _idField);
	if (treeRS.next()){
	    String nodeName = treeRS.getString(_idField) + " - " + treeRS.getString(_nameField);
	    if (_codeField.length() > 0)
		nodeName = treeRS.getString(_codeField) + " - " + treeRS.getString(_nameField);
	    DefaultMutableTreeNode tree = new DefaultMutableTreeNode(nodeName);
	    treeRS = LibSQL.exQuery("SELECT * FROM " + _table + " WHERE idparent = " + _idParent + " AND estado<>'*' ORDER BY " + _codeField);
	    createSubTree(_table, _idField, _nameField, tree, treeRS, _codeField);
	    return new JTree(tree);
	} else {
	    Advisor.messageBox("No existen items cargados","Items inexistentes");
	    return null;
	}
    }

    public void createSubTree(String _table, String _idField, String _nameField, DefaultMutableTreeNode _parentField, ResultSet rs, String _codeField) throws SQLException {
	while (rs.next()) {
	    String nodeName = rs.getString(_idField) + " - " + rs.getString(_nameField);
	    if (_codeField.length() > 0)
		nodeName = rs.getString(_codeField) + " - " + rs.getString(_nameField);
	    DefaultMutableTreeNode childrenNodes = new DefaultMutableTreeNode(nodeName);
	    _parentField.add(childrenNodes);
	    ResultSet subTreeRS = LibSQL.exQuery("SELECT * FROM " + _table + " WHERE estado<>'*' AND idparent = " + rs.getString(_idField) + " ORDER BY " + _idField);
	    createSubTree(_table, _idField, _nameField, childrenNodes, subTreeRS, _codeField);
	}
    }

}
