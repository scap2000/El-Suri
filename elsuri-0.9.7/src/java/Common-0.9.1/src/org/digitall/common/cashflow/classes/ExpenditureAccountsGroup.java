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
 * ExpenditureAccountsGroup.java
 *
 * */
package org.digitall.common.cashflow.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import org.digitall.lib.data.Format;
import org.digitall.common.cashflow.classes.Budget;
import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.sql.LibSQL;

public class ExpenditureAccountsGroup extends Vector {

    private int indexSelected;
    private Budget budget;
    private CostsCentre costsCentre;

    public ExpenditureAccountsGroup(Budget _budget) {
	budget = _budget;
    }

    public ExpenditureAccountsGroup(CostsCentre _costsCentre) {
	costsCentre = _costsCentre;
    }

    public ExpenditureAccountsGroup() {
    }

    public void sort() {
	Object ccTmp;
	for (int i = 0; i < size() - 1; i++) {
	    for (int j = i + 1; j < size(); j++) {
		int idExpTypei = ((ExpenditureAccount)elementAt(i)).getIDExpenditureAccount();
		int idExpTypej = ((ExpenditureAccount)elementAt(j)).getIDExpenditureAccount();
		if (idExpTypei > idExpTypej) {
		    ccTmp = elementAt(i);
		    setElementAt(elementAt(j), i);
		    setElementAt(ccTmp, j);
		}
	    }
	}
    }

    public ExpenditureAccount getElement(int _idExpenditureAccount) {
	int initIndex = 0;
	int endIndex = size() - 1;
	int midIndex = 0;
	while (initIndex <= endIndex) {
	    midIndex = (initIndex + endIndex) / 2;
	    int idExpType = ((ExpenditureAccount)elementAt(midIndex)).getIDExpenditureAccount();
	    if (_idExpenditureAccount == idExpType) {
		break;
	    } else if (_idExpenditureAccount > idExpType) {
		initIndex = midIndex + 1;
	    } else if (_idExpenditureAccount < idExpType) {
		endIndex = midIndex - 1;
	    }
	}
	if (initIndex > endIndex) {
	    return null;
	} else {
	    indexSelected = midIndex;
	    return (ExpenditureAccount)elementAt(midIndex);
	}
    }

    public int getIndexSelected() {
	return indexSelected;
    }

    public JTree createTree(boolean _fixed) throws Exception {
	DefaultMutableTreeNode tree;
	ResultSet treeRS;
	if (budget != null || costsCentre != null) {
	    if (budget != null) {
		tree = new DefaultMutableTreeNode(budget);
		String params = budget.getIdBudget() + ",0,'" + _fixed + "'";
		treeRS = LibSQL.exFunction("cashflow.getallBudgetsByExpenditureAccount", params);
	    } else {
		tree = new DefaultMutableTreeNode(costsCentre);
		String params = costsCentre.getIdCostCentre() + ",0";
		treeRS = LibSQL.exFunction("cashflow.getallExpenditureAccountsByCostsCentre", params);
	    }
	} else {
	    tree = new DefaultMutableTreeNode("Tipos de Gastos");
	    treeRS = LibSQL.exFunction("accounting.getAllExpenditureAccounts", "0");
	}
	createSubTree(tree, treeRS, _fixed);
	sort();
	return new JTree(tree);
    }

    public void createSubTree(DefaultMutableTreeNode _parentTree, ResultSet _rs, boolean _fixed) throws SQLException {
	while (_rs.next()) {
	    ExpenditureAccount objExpenditureAccount = new ExpenditureAccount();
	    String params;
	    ResultSet subTreeRS;
	    if (budget != null || costsCentre != null) {
		if (budget != null) {
		    objExpenditureAccount.setIdBudgetExpenditureAccount(_rs.getInt("idBudgetExpenditureAccount"));
		    objExpenditureAccount.setIdBudget(_rs.getInt("idBudget"));
		    objExpenditureAccount.setAssignedAmountToET(_rs.getDouble("assignedamounttoet"));
		    objExpenditureAccount.setAssignedAmountToETp(_rs.getDouble("assignedamounttoetp"));
		    objExpenditureAccount.setAssignedAmountToCC(_rs.getDouble("assignedamounttocc"));
		    objExpenditureAccount.setAssignedAmountToCCp(_rs.getDouble("assignedamounttoccp"));
		    objExpenditureAccount.setAssignedAmountToETColor(Format.hex2Color(_rs.getString("assignedamounttoetcolor")));
		    objExpenditureAccount.setAssignedAmountToCCColor(Format.hex2Color(_rs.getString("assignedamounttocccolor")));
		    objExpenditureAccount.setValueBlock(_rs.getInt("valueblock"));
		    params = budget.getIdBudget() + "," + _rs.getString("idaccount") + ",'" + _fixed + "'";
		    subTreeRS = LibSQL.exFunction("cashflow.getallBudgetsByExpenditureAccount", params);
		} else {
		    params = costsCentre.getIdCostCentre() + "," + _rs.getString("idaccount");
		    subTreeRS = LibSQL.exFunction("cashflow.getallExpenditureAccountsByCostsCentre", params);
		}
		objExpenditureAccount.setInitialAmount(_rs.getDouble("initialamount"));
		objExpenditureAccount.setInitialAmountP(_rs.getDouble("initialamountp"));
		objExpenditureAccount.setModifiedAmount(_rs.getDouble("modifiedamount"));
	    } else {
		objExpenditureAccount.setCode(_rs.getString("code"));
		objExpenditureAccount.setDescription(_rs.getString("description"));
		params = _rs.getString("idaccount");
		subTreeRS = LibSQL.exFunction("accounting.getAllExpenditureAccounts", params);
	    }
	    objExpenditureAccount.setIdParent(_rs.getInt("idparent"));
	    objExpenditureAccount.setIDExpenditureAccount(_rs.getInt("idaccount"));
	    objExpenditureAccount.setName(_rs.getString("name"));
	    objExpenditureAccount.setInitialAmountColor(Format.hex2Color(_rs.getString("initialamountcolor")));
	    this.add(objExpenditureAccount);
	    DefaultMutableTreeNode childrenNodes = new DefaultMutableTreeNode(objExpenditureAccount);
	    _parentTree.add(childrenNodes);
	    createSubTree(childrenNodes, subTreeRS, _fixed);
	}
    }

    public JTree getTree(boolean _fixed) throws Exception {
	DefaultMutableTreeNode tree;
	ResultSet treeRS;
	if (budget != null || costsCentre != null) {
	    if (budget != null) {
		tree = new DefaultMutableTreeNode(budget);
		String params = budget.getIdBudget() + ",-1,'" + _fixed + "'";
		treeRS = LibSQL.exFunction("cashflow.getallBudgetsByExpenditureAccount", params);
		getSubTree(tree, treeRS, _fixed, 0);
	    } else {
		tree = new DefaultMutableTreeNode(costsCentre);
		String params = costsCentre.getIdCostCentre() + ",0";
		treeRS = LibSQL.exFunction("cashflow.getallExpenditureAccountsByCostsCentre", params);
	    }
	} else {
	    tree = new DefaultMutableTreeNode("Tipos de Gastos");
	    treeRS = LibSQL.exFunction("accounting.getAllExpenditureAccounts", "-1");
	    getSubTree(tree, treeRS, _fixed, 0);
	}
	sort();
	return new JTree(tree);
    }

    public void getSubTree(DefaultMutableTreeNode _parentTree, ResultSet _rs, boolean _fixed, int _parent) throws SQLException {
	_rs.beforeFirst();
	while (_rs.next()) {
	    if (_rs.getInt("idparent") == _parent) {
		ExpenditureAccount objExpenditureAccount = new ExpenditureAccount();
		//String params;
		//ResultSet subTreeRS;
		if (budget != null || costsCentre != null) {
		    if (budget != null) {
			objExpenditureAccount.setIdBudgetExpenditureAccount(_rs.getInt("idBudgetExpenditureAccount"));
			objExpenditureAccount.setIdBudget(_rs.getInt("idBudget"));
			objExpenditureAccount.setAssignedAmountToET(_rs.getDouble("assignedamounttoet"));
			objExpenditureAccount.setAssignedAmountToETp(_rs.getDouble("assignedamounttoetp"));
			objExpenditureAccount.setAssignedAmountToCC(_rs.getDouble("assignedamounttocc"));
			objExpenditureAccount.setAssignedAmountToCCp(_rs.getDouble("assignedamounttoccp"));
			objExpenditureAccount.setAssignedAmountToETColor(Format.hex2Color(_rs.getString("assignedamounttoetcolor")));
			objExpenditureAccount.setAssignedAmountToCCColor(Format.hex2Color(_rs.getString("assignedamounttocccolor")));
			objExpenditureAccount.setValueBlock(_rs.getInt("valueblock"));
			//params = budget.getIdBudget() + "," + _rs.getString("idaccount") + ",'" + _fixed + "'";
			//subTreeRS = LibSQL.exFunction("cashflow.getallBudgetsByExpenditureAccount", params);
		    } else {
			//params = costsCentre.getIdCostCentre() + "," + _rs.getString("idaccount");
			//subTreeRS = LibSQL.exFunction("cashflow.getallExpenditureAccountsByCostsCentre", params);
			Advisor.messageBox("Arbol no disponible","Aviso");
		    }
		    objExpenditureAccount.setInitialAmount(_rs.getDouble("initialamount"));
		    objExpenditureAccount.setInitialAmountP(_rs.getDouble("initialamountp"));
		    objExpenditureAccount.setModifiedAmount(_rs.getDouble("modifiedamount"));
		} else {
		    objExpenditureAccount.setCode(_rs.getString("code"));
		    objExpenditureAccount.setDescription(_rs.getString("description"));
		    //params = _rs.getString("idaccount");
		    //subTreeRS = LibSQL.exFunction("accounting.getAllExpenditureAccounts", params);
		}
		objExpenditureAccount.setIdParent(_rs.getInt("idparent"));
		objExpenditureAccount.setIDExpenditureAccount(_rs.getInt("idaccount"));
		objExpenditureAccount.setName(_rs.getString("name"));
		objExpenditureAccount.setInitialAmountColor(Format.hex2Color(_rs.getString("initialamountcolor")));
		this.add(objExpenditureAccount);
		DefaultMutableTreeNode childrenNodes = new DefaultMutableTreeNode(objExpenditureAccount);
		_parentTree.add(childrenNodes);
		int previous = _rs.getRow();
		getSubTree(childrenNodes, _rs, _fixed, _rs.getInt("idaccount"));
		_rs.absolute(previous);
	    }
	}
    }

}
