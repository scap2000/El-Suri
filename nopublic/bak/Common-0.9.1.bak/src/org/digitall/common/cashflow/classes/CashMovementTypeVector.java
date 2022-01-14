package org.digitall.common.cashflow.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import org.digitall.common.cashflow.classes.CashMovementType;
import org.digitall.lib.sql.LibSQL;

public class CashMovementTypeVector {

    public CashMovementTypeVector() {

    }

    public static JTree createTree() throws Exception {
	DefaultMutableTreeNode tree = new DefaultMutableTreeNode("Tipos de Movimientos de Fondos");
	ResultSet treeRS = LibSQL.exFunction("cashflow.getAllCashMovementTypes","0");
	createSubTree(tree, treeRS);
	return new JTree(tree);
    }

    public static void createSubTree(DefaultMutableTreeNode _parentTree, ResultSet _treeRS) throws SQLException {
	while (_treeRS.next()) {
	    CashMovementType cashMovementType = new CashMovementType();
	    cashMovementType.setIdCashMovementType(_treeRS.getInt("idcashmovementtype"));
	    cashMovementType.setName(_treeRS.getString("name"));
	    cashMovementType.setIdParent(_treeRS.getInt("idparent"));
	    cashMovementType.setIdCashMovementTypeRef(_treeRS.getInt("idcashmovementtyperef"));
	    cashMovementType.setSign(_treeRS.getInt("sign"));
	    cashMovementType.setDescription(_treeRS.getString("description"));
	    
	    DefaultMutableTreeNode childrenNodes = new DefaultMutableTreeNode(cashMovementType);
	    _parentTree.add(childrenNodes);
	    ResultSet subTreeRS = LibSQL.exFunction("cashflow.getAllCashMovementTypes",""+ cashMovementType.getIdCashMovementType());
	    createSubTree(childrenNodes, subTreeRS);
	}
    }
}
