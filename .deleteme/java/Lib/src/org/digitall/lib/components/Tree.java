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
 * Tree.java
 *
 * */
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
