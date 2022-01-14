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
 * AccountsGroup.java
 *
 * */
package org.digitall.common.cashflow.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import org.digitall.lib.sql.LibSQL;

public class AccountsGroup extends Vector {

    private int indexSelected;
    
    public AccountsGroup() {
    }

    public void sort() {
	Object ccTmp;
	for (int i = 0; i < size() - 1; i++) {
	    for (int j = i + 1; j < size(); j++) {
		int idAccount_i = ((Account)elementAt(i)).getIDAccount();
		int idAccount_j = ((Account)elementAt(j)).getIDAccount();
		if (idAccount_i > idAccount_j) {
		    ccTmp = elementAt(i);
		    setElementAt(elementAt(j), i);
		    setElementAt(ccTmp, j);
		}
	    }
	}
    }

    public Account getElement(int _idAccount) {
	int initIndex = 0;
	int endIndex = size() - 1;
	int midIndex = 0;
	while (initIndex <= endIndex) {
	    midIndex = (initIndex + endIndex) / 2;
	    int idAccount = ((Account)elementAt(midIndex)).getIDAccount();
	    if (_idAccount == idAccount) {
		break;
	    } else if (_idAccount > idAccount) {
		initIndex = midIndex + 1;
	    } else if (_idAccount < idAccount) {
		endIndex = midIndex - 1;
	    }
	}
	if (initIndex > endIndex) {
	    return null;
	} else {
	    indexSelected = midIndex;
	    return (Account)elementAt(midIndex);
	}
    }

    public int getIndexSelected() {
	return indexSelected;
    }

    public JTree getTree(Account _parentAccount) throws Exception {
	DefaultMutableTreeNode tree;
	ResultSet treeRS;
	tree = new DefaultMutableTreeNode(_parentAccount.getName());
	String params = "-1,"+ _parentAccount.getIdAccountType() +", -1, '', 0, 0";
	treeRS = LibSQL.exFunction("accounting.getAllAccounts", params);           
	
	getSubTree(tree, treeRS, _parentAccount.getIDAccount());
	sort();
	return new JTree(tree);
    }

    public void getSubTree(DefaultMutableTreeNode _parentTree, ResultSet _rs, int _parent) throws SQLException {
	_rs.beforeFirst();
	while (_rs.next()) {
	    if (_rs.getInt("idparent") == _parent) {
		Account account = new Account();
		account.setIDAccount(_rs.getInt("idaccount"));
		account.setCode(_rs.getInt("code"));
		account.setName(_rs.getString("name"));
		account.setIdParent(_rs.getInt("idparent"));
		account.setIdAccountType(_rs.getInt("idaccounttype"));
		account.setInitialAmountcolor(_rs.getString("initialamountcolor"));
		account.setAssignedAmountToACColor(_rs.getString("assignedamounttoetcolor"));
		account.setAssignedAmountToCCColor(_rs.getString("assignedamounttocccolor"));
		account.setDescription(_rs.getString("description"));
		account.setIsImputable(_rs.getBoolean("isimputable"));
		account.setIsHeritage(_rs.getBoolean("isheritage"));
		account.setChildrenNumber(_rs.getInt("childrennumber"));
		this.add(account);
		DefaultMutableTreeNode childrenNodes = new DefaultMutableTreeNode(account);
		_parentTree.add(childrenNodes);
		/*String params;
		ResultSet subTreeRS;
		params = _rs.getInt("idaccount") +",-1, -1, '', 0, 0";
		subTreeRS = LibSQL.exFunction("accounting.getAllAccounts", params);
		*/
		int previous = _rs.getRow();
		getSubTree(childrenNodes, _rs, _rs.getInt("idaccount"));
		_rs.absolute(previous);
	    }
	}
    }

    public JTree createTree(Account _parentAccount) throws Exception {
	DefaultMutableTreeNode tree;
	ResultSet treeRS;
	tree = new DefaultMutableTreeNode(_parentAccount.getName());
	String params = _parentAccount.getIDAccount() +","+ _parentAccount.getIdAccountType() +", -1, '', 0, 0";
	treeRS = LibSQL.exFunction("accounting.getAllAccounts", params);           
	
	createSubTree(tree, treeRS, _parentAccount);
	sort();
	return new JTree(tree);
    }

    public void createSubTree(DefaultMutableTreeNode _parentTree, ResultSet _rs, Account _parentAccount) throws SQLException {
	while (_rs.next()) {
	    Account account = new Account();
	    String params;
	    ResultSet subTreeRS;
	    
	    account.setIDAccount(_rs.getInt("idaccount"));    
	    account.setCode(_rs.getInt("code"));    
	    account.setName(_rs.getString("name"));    
	    account.setIdParent(_rs.getInt("idparent"));        
	    account.setIdAccountType(_rs.getInt("idaccounttype"));        
	    account.setInitialAmountcolor(_rs.getString("initialamountcolor"));      
	    account.setAssignedAmountToACColor(_rs.getString("assignedamounttoetcolor"));         
	    account.setAssignedAmountToCCColor(_rs.getString("assignedamounttocccolor"));         
	    account.setDescription(_rs.getString("description"));     
	    account.setIsImputable(_rs.getBoolean("isimputable"));     
	    account.setIsHeritage(_rs.getBoolean("isheritage"));         
	    account.setChildrenNumber(_rs.getInt("childrennumber"));
	    this.add(account);
	    
	    DefaultMutableTreeNode childrenNodes = new DefaultMutableTreeNode(account);
	    _parentTree.add(childrenNodes);
	    
	    params = _rs.getInt("idaccount") +","+ _parentAccount.getIdAccountType() +", -1, '', 0, 0";
	    subTreeRS = LibSQL.exFunction("accounting.getAllAccounts", params);
	    createSubTree(childrenNodes, subTreeRS, _parentAccount);
	}
    }    
}
