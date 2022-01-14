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
 * CompanyTreePanel.java
 *
 * */
package org.digitall.common.cashflow.interfaces;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.digitall.common.resourcescontrol.interfaces.providers.CompanyPanel;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.Tree;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.sql.LibSQL;

//

public class CompanyTreePanel extends BasicPrimitivePanel {

    private Tree tree = new Tree();
    private JTree treeCompany;
    private CompanyPanel panelCompanyData = new CompanyPanel(null, CompanyPanel.COMPANYTREE);
    private BasicScrollPane jsptree = new BasicScrollPane();
    private int idCompany = 0;
    private String companyName = "Company";
    private AddButton btnAddEnterprise = new AddButton();
    private AddButton btnAddSuccursal = new AddButton();
    private AddButton btnAddCompany = new AddButton();
    private BasicButton btnExpand = new BasicButton();
    private BasicButton btnCollaps = new BasicButton();
    private DeleteButton btnDelete = new DeleteButton();

    public CompanyTreePanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(970, 350));
	panelCompanyData.setBounds(new Rectangle(395, 5, 560, 340));
	panelCompanyData.setLayout(null);
	jsptree.setBounds(new Rectangle(15, 30, 360, 280));
	btnAddEnterprise.setBounds(new Rectangle(125, 315, 25, 25));
	btnAddEnterprise.setHorizontalAlignment(SwingConstants.LEFT);
	btnAddEnterprise.setToolTipText("Agregar nueva empresa");
	btnAddEnterprise.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
					    btnAddEnterprise_actionPerformed(e);
					}

				    }
	);
	btnAddSuccursal.setBounds(new Rectangle(235, 315, 25, 25));
	btnAddSuccursal.setHorizontalAlignment(SwingConstants.LEFT);
	btnAddSuccursal.setToolTipText("Agregar nueva sucursal");
	btnAddSuccursal.addActionListener(new ActionListener() {

				       public void actionPerformed(ActionEvent e) {
					   btnAddSuccursal_actionPerformed(e);
				       }

				   }
	);
	btnAddCompany.setBounds(new Rectangle(15, 315, 25, 25));
	btnAddCompany.setHorizontalAlignment(SwingConstants.LEFT);
	btnAddCompany.setToolTipText("Agregar nueva compañía");
	btnAddCompany.addActionListener(new ActionListener() {

				     public void actionPerformed(ActionEvent e) {
					 btnAddCompany_actionPerformed(e);
				     }

				 }
	);
	btnExpand.setBounds(new Rectangle(255, 5, 60, 25));
	btnExpand.setText("Expandir");
	btnExpand.setSize(new Dimension(60, 25));
	btnExpand.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnExpand_actionPerformed(e);
				 }

			     }
	);
	btnCollaps.setBounds(new Rectangle(315, 5, 60, 25));
	btnCollaps.setText("Contraer");
	btnCollaps.setSize(new Dimension(60, 25));
	btnCollaps.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      btnCollaps_actionPerformed(e);
				  }

			      }
	);
	btnDelete.setBounds(new Rectangle(335, 315, 40, 25));
	btnDelete.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnDelete_actionPerformed(e);
				 }

			     }
	);
	this.add(btnDelete, null);
	this.add(btnAddCompany, null);
	this.add(btnAddSuccursal, null);
	this.add(btnAddEnterprise, null);
	this.add(panelCompanyData, null);
	jsptree.getViewport().add(treeCompany, null);
	this.add(jsptree, null);
	this.add(btnExpand, null);
	this.add(btnCollaps, null);
	btnAddCompany.setEnabled(false);
	btnAddSuccursal.setEnabled(false);
	btnAddEnterprise.setEnabled(false);
	btnDelete.setEnabled(false);
    }

    public void reload() {
	refresh();
    }

    public void refresh() {
	drawTreeCompany();
	setEnableButton(0);
	panelCompanyData.setIdCompanyType(1);
	panelCompanyData.setIdParent(0);
	panelCompanyData.setPanelCompanyList(this);
    }

    private TreeSelectionListener setTreeSelectionListener() {
	TreeSelectionListener treeSelectionListener = new TreeSelectionListener() {

		public void valueChanged(TreeSelectionEvent evt) {
		    TreePath paths = evt.getPath();
		    String node = paths.getLastPathComponent().toString();
		    idCompany = Integer.parseInt(node.substring(0, node.indexOf("-") - 1).trim());
		    companyName = node.substring(node.indexOf("-") + 1, node.length()).trim();
		    if (idCompany != 0) {
			loadCompanyData();
		        btnDelete.setEnabled(true);
		    } else {
			setEnableButton(idCompany);
		        btnDelete.setEnabled(false);
		    }
		}

	    }
	;
	return treeSelectionListener;
    }

    public void drawTreeCompany() {
	try {
	    treeCompany = tree.createTree("org.companies", "idcompany", "name", "0");
	    treeCompany.setRowHeight(0);
	    jsptree.getViewport().add(treeCompany, null);
	    treeCompany.addTreeSelectionListener(setTreeSelectionListener());
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void setEnableButton(int _companyType) {
	btnAddCompany.setEnabled(false);
	btnAddEnterprise.setEnabled(false);
	btnAddSuccursal.setEnabled(false);
	switch (_companyType) {
	    case 0 :
		btnAddCompany.setEnabled(true);
		break;
	    case 1 :
		btnAddEnterprise.setEnabled(true);
		break;
	    case 2 :
		btnAddSuccursal.setEnabled(true);
		break;
	}
    }

    private void loadCompanyData() {
	try {
	    ResultSet rs = LibSQL.exFunction("org.getCompany", String.valueOf(idCompany));
	    if (rs.next()) {
		panelCompanyData.setData(rs);
		panelCompanyData.setPanelCompanyList(this);
		setEnableButton(rs.getInt("idcompanytype"));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    private void btnAddCompany_actionPerformed(ActionEvent e) {
	panelCompanyData.setIdCompanyType(1);
	panelCompanyData.setIdParent(idCompany);
    }

    private void btnAddEnterprise_actionPerformed(ActionEvent e) {
	panelCompanyData.setIdCompanyType(2);
	panelCompanyData.setIdParent(idCompany);
    }

    private void btnAddSuccursal_actionPerformed(ActionEvent e) {
	panelCompanyData.setIdCompanyType(3);
	panelCompanyData.setIdParent(idCompany);
    }

    private void btnExpand_actionPerformed(ActionEvent e) {
	expandAll(treeCompany, treeCompany.getSelectionPath(), true);
    }

    private void btnCollaps_actionPerformed(ActionEvent e) {
	expandAll(treeCompany, treeCompany.getSelectionPath(), false);
    }

    private void expandAll(JTree _tree, TreePath _parent, boolean _expand) {
	TreeNode node = (TreeNode)_parent.getLastPathComponent();
	if (node.getChildCount() >= 0) {
	    for (Enumeration e = node.children(); e.hasMoreElements(); ) {
		TreeNode n = (TreeNode)e.nextElement();
		TreePath path = _parent.pathByAddingChild(n);
		expandAll(_tree, path, _expand);
	    }
	}
	if (_expand) {
	    _tree.expandPath(_parent);
	} else {
	    _tree.collapsePath(_parent);
	}
    }

    private void btnDelete_actionPerformed(ActionEvent e) {
	if (Advisor.question("Borrar Compañía/Empresa/Sucursal", "¿Está seguro de borrar el ítem seleccionado?")) {
	    try {
		String query = "SELECT COUNT(*) FROM org.companies WHERE idparent=" + idCompany;
		ResultSet rs = LibSQL.exQuery(query);
		if (rs.next()) {
		    if (!rs.getString(1).equals("0")) {
			Advisor.messageBox("Imposible eliminar, contiene referencias", "Imposible eliminar");
		    } else {
			query = "SELECT idaddress FROM org.companyaddresses WHERE idcompany=" + idCompany;
			rs = LibSQL.exQuery(query);
			while (rs.next()) {
			    query = "SELECT org.deladdress(" + rs.getString("idaddress") + ");";
			    LibSQL.updateQuery(query);
			}
			query = "SELECT org.delcompanyaddresses(" + idCompany + ");";
			LibSQL.updateQuery(query);
			query = "SELECT org.delcompany(" + idCompany + ");";
			LibSQL.updateQuery(query);
			drawTreeCompany();
			panelCompanyData.clearData();
		    }
		}
	    } catch (SQLException x) {
		x.printStackTrace();
	    }
	}
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Puede agregar jerárquicamente Compañías, Empresas y Sucursales en el panel izquierdo o modificar sus datos en el panel derecho");
    }

}
