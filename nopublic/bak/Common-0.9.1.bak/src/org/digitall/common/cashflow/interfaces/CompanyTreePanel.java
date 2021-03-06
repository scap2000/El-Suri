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
	btnAddCompany.setToolTipText("Agregar nueva compa????a");
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
	if (Advisor.question("Borrar Compa????a/Empresa/Sucursal", "??Est?? seguro de borrar el ??tem seleccionado?")) {
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
	getParentInternalFrame().setInfo("Puede agregar jer??rquicamente Compa????as, Empresas y Sucursales en el panel izquierdo o modificar sus datos en el panel derecho");
    }

}
