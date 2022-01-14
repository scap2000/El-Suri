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
 * CCTotalAmountsTree.java
 *
 * */
package org.digitall.apps.cashflow.interfaces.costscentre;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.cashflow.interfaces.expenditureaccounts.ExpenditureAccountTreeLabel;
import org.digitall.common.cashflow.interfaces.expenditureaccounts.ExpenditureAccountTreeRender;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.BasicTextField;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.environment.Environment;

public class CCTotalAmountsTree extends BasicContainerPanel {
    private JTree treeExpenditureAccounts = new JTree();
    private BasicScrollPane jsptree = new BasicScrollPane();
    private BasicPanel panelTree = new BasicPanel();
    private CostsCentre costsCentre;
     
    private CloseButton btnClose = new CloseButton();
    private BasicTextField tfAmount = new BasicTextField();
    private BasicLabel jLabel1 = new BasicLabel();
    
    public CCTotalAmountsTree() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout( null );
	this.setSize(new Dimension(690, 500));
	panelTree.setBounds(new Rectangle(5, 0, 680, 450));
	panelTree.setLayout(null);
	panelTree.setBorder(BorderPanel.getBorderPanel("Tipos de Gastos", Color.blue, Color.black));
	btnClose.setBounds(new Rectangle(645, 465, 40, 25));
	btnClose.setSize(new Dimension(40, 25));
	tfAmount.setBounds(new Rectangle(50, 465, 155, 25));
	tfAmount.setForeground(Color.blue);
	tfAmount.setFont(new Font("Default", 1, 16));
	jLabel1.setText("Total:");
	jLabel1.setBounds(new Rectangle(5, 470, 45, 14));
	jLabel1.setFont(new Font("Default", 1, 13));
	jLabel1.setSize(new Dimension(45, 14));
	jsptree.setBounds(new Rectangle(10, 25, 660, 415));
	jsptree.getViewport().add(treeExpenditureAccounts, null);
	panelTree.add(jsptree, null);
	this.add(jLabel1, null);
	this.add(tfAmount, null);
	this.add(btnClose, null);
	 
	this.add(panelTree, null);
	
	drawTreeExpenditureAccount();
    }

    public void drawTreeExpenditureAccount() {
	try {
	    treeExpenditureAccounts = costsCentre.getExpenditureAccountsTree();
	    if (treeExpenditureAccounts != null) {
		treeExpenditureAccounts.setRowHeight(0);
		treeExpenditureAccounts.setRowHeight(16);
		jsptree.getViewport().add(treeExpenditureAccounts, null);
//		treeExpenditureAccounts.addTreeSelectionListener(setTreeSelectionListener());
		treeExpenditureAccounts.setCellRenderer(new ExpenditureAccountTreeRender(ExpenditureAccountTreeLabel.CC_ASSIGNED));
		
		treeExpenditureAccounts.addMouseListener(new MouseAdapter() {
			    public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1 && e.getButton() == e.BUTTON3) { 
				    JTree tree = (JTree)e.getComponent();
				    TreePath parentpath = tree.getClosestPathForLocation(e.getX(), e.getY());
				    DefaultMutableTreeNode node = (DefaultMutableTreeNode)parentpath.getLastPathComponent();
				    if (ExpenditureAccount.class.isInstance(node.getUserObject())) {
					tree.setSelectionPath(parentpath);
					Vector params = new Vector();
					params.add(costsCentre);
					params.add((ExpenditureAccount)node.getUserObject());
					
					
					/*FrameContainer ccAssignedAmountDetail = new FrameContainer(FrameContainer.CCASSIGNAMOUNTDETAIL, parentDesktop, null, params);
					parentDesktop.add(ccAssignedAmountDetail);
					ccAssignedAmountDetail.setVisible(true);    */
				    }
				}
			    }
			});
	    } else
		Advisor.messageBox(Environment.lang.getProperty("LoadError"), "Error");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
