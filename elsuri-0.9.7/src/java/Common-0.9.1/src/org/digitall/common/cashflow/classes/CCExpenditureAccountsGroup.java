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
 * CCExpenditureAccountsGroup.java
 *
 * */
package org.digitall.common.cashflow.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import org.digitall.lib.sql.LibSQL;

public class CCExpenditureAccountsGroup extends Vector {

    private CostsCentre costsCentre;
    private int indexSelected;

    public CCExpenditureAccountsGroup() {

    }

    public CCExpenditureAccountsGroup(CostsCentre _costsCentre) {
	costsCentre = _costsCentre;
    }

    public void sort() {
	Object ccTmp;
	for (int i = 0; i < size() - 1; i++) {
	    for (int j = i + 1; j < size(); j++) {
		int idExpTypei = ((ExpenditureAccount.CCItem)elementAt(i)).getIDExpenditureAccount();
		int idExpTypej = ((ExpenditureAccount.CCItem)elementAt(i)).getIDExpenditureAccount();
		if (idExpTypei > idExpTypej) {
		    ccTmp = elementAt(i);
		    setElementAt(elementAt(j), i);
		    setElementAt(ccTmp, j);
		}
	    }
	}
    }

    public ExpenditureAccount.CCItem getElement(int _idExpenditureAccount) {
	int initIndex = 0;
	int endIndex = size() - 1;
	int midIndex = 0;
	while (initIndex <= endIndex) {
	    midIndex = (initIndex + endIndex) / 2;
	    int idExpAcc = ((ExpenditureAccount.CCItem)elementAt(midIndex)).getIDExpenditureAccount();
	    if (_idExpenditureAccount == idExpAcc) {
		break;
	    } else if (_idExpenditureAccount > idExpAcc) {
		initIndex = midIndex + 1;
	    } else if (_idExpenditureAccount < idExpAcc) {
		endIndex = midIndex - 1;
	    }
	}
	if (initIndex > endIndex) {
	    return null;
	} else {
	    indexSelected = midIndex;
	    return (ExpenditureAccount.CCItem)elementAt(midIndex);
	}
    }

    public int getIndexSelected() {
	return indexSelected;
    }

    public JTree createTree() throws Exception {
	DefaultMutableTreeNode tree = new DefaultMutableTreeNode(costsCentre);
	String params = costsCentre.getIdCostCentre() + ",0";
	ResultSet treeRS = LibSQL.exFunction("cashflow.getAllExpenditureAccountsByCostCentre", params);
	getSubTree(tree, treeRS, 0);
	sort();
	return new JTree(tree);
    }

    private void getSubTree(DefaultMutableTreeNode _parentTree, ResultSet _rs, int _parent) throws SQLException {
	_rs.beforeFirst();
	while (_rs.next()) {
	    if (_rs.getInt("idparent") == _parent) {
		ExpenditureAccount.CCItem objItem = new ExpenditureAccount.CCItem(costsCentre);
		objItem.setIDExpenditureAccount(_rs.getInt("idaccount"));
		objItem.setName(_rs.getString("name"));
		objItem.setIdParent(_rs.getInt("idparent"));
		//objItem.setIdCostCentreDetail(_rs.getInt("idcostcentredetail"));
		objItem.setInitalAmount(_rs.getDouble("initialamount"));
		objItem.setInitalAmountP(_rs.getDouble("initialamountp"));
		objItem.setSpentAmount(_rs.getDouble("spentamount"));
		objItem.setSpentAmountP(_rs.getDouble("spentamountp"));
		objItem.setAvailableCCAmount(_rs.getDouble("availableamount"));
		objItem.setModifiedCCAmount(_rs.getDouble("modifiedamount"));
		this.add(objItem);
		DefaultMutableTreeNode childrenNodes = new DefaultMutableTreeNode(objItem);
		_parentTree.add(childrenNodes);
		//String params = costsCentre.getIdCostCentre() + "," + _rs.getString("idaccount");
		//ResultSet subTreeRS = LibSQL.exFunction("cashflow.getAllExpenditureAccountsByCostCentre", params);
		int previous = _rs.getRow();
		getSubTree(childrenNodes, _rs, _rs.getInt("idaccount"));
		_rs.absolute(previous);
	    }
	}
    }

}
