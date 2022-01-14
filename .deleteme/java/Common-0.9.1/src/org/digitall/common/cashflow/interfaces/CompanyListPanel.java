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
 * CompanyListPanel.java
 *
 * */
package org.digitall.common.cashflow.interfaces;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.JDesktopPane;

import org.digitall.common.cashflow.interfaces.FrameContainer;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

//

public class CompanyListPanel extends BasicPrimitivePanel {

    private int[] sizeColumnList = { };
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Proveedores", dataRow);
    private CloseButton btnClose = new CloseButton();
    private AddButton btnAdd = new AddButton();
     
    private Component parent;
    private JDesktopPane parentDesktop;
    private BasicPanel panelSearch = new BasicPanel();
    private TFInput tfSearch = new TFInput(DataTypes.STRING, "Company", false);
    private FindButton btnSearch = new FindButton();

    public CompanyListPanel(Component _parent, JDesktopPane _parentDesktop) {
	try {
	    parentDesktop = _parentDesktop;
	    parent = _parent;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(900, 480));
	listPanel.setBounds(new Rectangle(5, 70, 890, 355));
	panelSearch.setBounds(new Rectangle(5, 0, 890, 70));
	panelSearch.setLayout(null);
	tfSearch.setBounds(new Rectangle(95, 25, 130, 20));
	tfSearch.setSize(new Dimension(130, 35));
	btnSearch.setBounds(new Rectangle(245, 35, 40, 25));
	btnSearch.setSize(new Dimension(40, 25));
	btnSearch.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnSearch_actionPerformed(e);
		    }

		});
	btnClose.setBounds(new Rectangle(855, 450, 40, 25));
	btnClose.setPreferredSize(new Dimension(40, 25));
	btnClose.setSize(new Dimension(40, 25));
	btnClose.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnClose_actionPerformed(e);
		    }

		});
	btnAdd.setBounds(new Rectangle(5, 450, 40, 25));
	btnAdd.setPreferredSize(new Dimension(40, 25));
	btnAdd.setSize(new Dimension(40, 25));
	btnAdd.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnAdd_actionPerformed(e);
		    }

		});
	panelSearch.add(btnSearch, null);
	panelSearch.add(tfSearch, null);
	panelSearch.setBorder(org.digitall.lib.components.BorderPanel.getBorderPanel("Buscar...", Color.red, Color.black));
	this.add(panelSearch, null);
	this.add(btnClose, null);
	 
	this.add(listPanel, null);
	this.add(btnAdd, null);
	tfSearch.addKeyListener(new KeyAdapter() {

		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }

		});
	setHeaderList();
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("Proveedor");
	headerList.addElement("*");
	headerList.addElement("Prefijo");
	headerList.addElement("*");
	headerList.addElement("Tipo");
	headerList.addElement("Numero");
	headerList.addElement("*");
	headerList.addElement("Condicion Tributaria");
	headerList.addElement("*");
	headerList.addElement("Tipo Comunicacion");
	headerList.addElement("Inicio");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("Clasif.");
	headerList.addElement("*");
	listPanel.getTable().addMouseListener(new MouseAdapter() {

		    public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
			    loadCompanyData();
			}
		    }

		});
	listPanel.setParams("org.getallCompanies", "''", headerList);
    }

    public void refresh() {
	String params = "'" + tfSearch.getString() + "'";
	listPanel.refresh(params);
    }

    private void loadCompanyData() {
	if (!dataRow.isEmpty()) {
	    FrameContainer company = new FrameContainer(FrameContainer.COMPANY, parentDesktop, this, dataRow);
	    parentDesktop.add(company);
	    company.setVisible(true);
	}
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	parent.setVisible(false);
    }

    private void btnAdd_actionPerformed(ActionEvent e) {
	FrameContainer company = new FrameContainer(FrameContainer.COMPANY, parentDesktop, this, null);
	parentDesktop.add(company);
	company.setVisible(true);
    }

    private void btnSearch_actionPerformed(ActionEvent e) {
    }

}
