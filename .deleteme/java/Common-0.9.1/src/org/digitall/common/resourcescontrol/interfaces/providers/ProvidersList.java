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
 * ProvidersList.java
 *
 * */
package org.digitall.common.resourcescontrol.interfaces.providers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.cashflow.classes.Account;
import org.digitall.common.resourcescontrol.interfaces.providers.ProvidersMain;
import org.digitall.lib.org.Persons;
import org.digitall.common.resourcescontrol.classes.Provider;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

public class ProvidersList extends BasicPrimitivePanel {

    private BasicPanel content = new BasicPanel();
    private BasicPanel findPanel = new BasicPanel("Buscar Proveedor");
    private int[] sizeColumnList = { 172, 48, 52, 108, 250 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(50000, sizeColumnList, "Proveedores", dataRow);
    private Vector headerList = new Vector();
    private TFInput tfFindName = new TFInput(DataTypes.STRING, "Provider", false);
    private FindButton btnSearch = new FindButton();
    private TFInput tfCUIT = new TFInput(DataTypes.INTEGER, "Cuit", false);
    private ProvidersMain parentMain;
    private Provider provider;
    private Account account;
    private ModifyButton btnModify = new ModifyButton();
    private AddButton btnAdd = new AddButton();
    private DeleteButton btnDelete = new DeleteButton();

    public ProvidersList(ProvidersMain _parentMain) {
	try {
	    parentMain = _parentMain;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(620, 390));
	this.setPreferredSize(new Dimension(620, 390));
	findPanel.setLayout(null);
	findPanel.setBounds(new Rectangle(0, 0, 760, 100));
	findPanel.setPreferredSize(new Dimension(760, 70));
	tfFindName.setBounds(new Rectangle(140, 25, 200, 35));
	btnSearch.setBounds(new Rectangle(535, 40, 40, 25));
	btnSearch.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnSearch_actionPerformed(e);
				 }

			     }
	);
	tfCUIT.setBounds(new Rectangle(365, 25, 155, 35));
	tfCUIT.setSize(new Dimension(105, 35));
	btnModify.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnModify_actionPerformed(e);
				 }

			     }
	);
	findPanel.add(tfCUIT, null);
	findPanel.add(tfFindName, null);
	findPanel.add(btnSearch, null);
	content.setLayout(new BorderLayout());
	content.add(findPanel, BorderLayout.NORTH);
	content.add(listPanel, BorderLayout.CENTER);
	this.add(content, null);
	this.addButton(btnDelete);
	this.addButton(btnModify);
	this.addButton(btnAdd);
	btnDelete.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnDelete_actionPerformed(e);
				 }

			     }
	);
	tfFindName.getTextField().addKeyListener(new KeyAdapter() {

					      public void keyReleased(KeyEvent e) {
						  if (e.getKeyCode() == KeyEvent.VK_ENTER)
						      refresh();
					      }

					  }
	);
	tfCUIT.getTextField().addKeyListener(new KeyAdapter() {

					  public void keyReleased(KeyEvent e) {
					      if (e.getKeyCode() == KeyEvent.VK_ENTER)
						  refresh();
					  }

				      }
	);
	setHeaderList();
	btnDelete.setEnabled(false);
	btnModify.setEnabled(false);
	btnDelete.setToolTipText("Borrar el proveedor seleccionado");
	btnModify.setToolTipText("Modificar el proveedor seleccionado");
	btnAdd.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAdd_actionPerformed(e);
			      }

			  }
	);
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Provider"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Suffix"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Type"));
	headerList.addElement(Environment.lang.getProperty("Number"));
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Accounting"));
	headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent e) {
					       loadObjectSelected();
					       if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
					       } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						   loadMgmt();
					       }
					   }

				       }
	);
	listPanel.setParams("resourcescontrol.getallproviders", "-1,''", headerList);
    }

    public void refresh() {
	String params = "";
	String textRealyCuit = "" + tfCUIT.getTextField().getText().trim();// esto se hace por si queda el valor anterior
	if(textRealyCuit.equals("")){
	    tfCUIT.setValue(0);
	}
	if (Integer.parseInt(tfCUIT.getValue().toString()) > 0) {
	    params = tfCUIT.getValue() + ",'" + tfFindName.getString() + "'";
	} else {
	    params = "-1,'" + tfFindName.getString() + "'";
	}
	listPanel.refresh(params);
	parentMain.setTitleAt(1, "Nuevo Proveedor");
	btnDelete.setEnabled(false);
	btnModify.setEnabled(false);
	btnDelete.setToolTipText("Borrar el proveedor seleccionado");
	btnModify.setToolTipText("Modificar el proveedor seleccionado");
    }

    private void loadObjectSelected() {
	if (!dataRow.isEmpty()) {
	    provider = new Provider();
	    provider.setIdProvider(Integer.parseInt("" + dataRow.elementAt(0)));
	    provider.setName("" + dataRow.elementAt(1));
	    provider.setIdSuffix(Integer.parseInt("" + dataRow.elementAt(2)));
	    provider.setIdIdentificationType(Integer.parseInt("" + dataRow.elementAt(4)));
	    //provider.setIdentificationNumber(Long.parseLong("" + dataRow.elementAt(6)));
	    provider.setIdentificationNumber("" + dataRow.elementAt(6));
	    provider.setIdTributaryCondition(Integer.parseInt("" + dataRow.elementAt(7)));
	    provider.setPersonCharge(new Persons(Integer.parseInt("" + dataRow.elementAt(9))));
	    provider.setIdCommunicationType(Integer.parseInt("" + dataRow.elementAt(10)));
	    provider.setStartDate(Proced.setFormatDate("" + dataRow.elementAt(12), false));
	    provider.setIdParent(Integer.parseInt("" + dataRow.elementAt(13)));
	    provider.setDescription("" + dataRow.elementAt(14));
	    provider.setIdCompanyType(Integer.parseInt("" + dataRow.elementAt(15)));
            provider.setMultilateralAggrement(dataRow.elementAt(20).toString().equalsIgnoreCase("SI"));
            provider.setMultilateralAggrementNumber("" + dataRow.elementAt(21));
	    account = new Account(Integer.parseInt("" + dataRow.elementAt(17)));
	    account.setName("" + dataRow.elementAt(18));
	    provider.setAccount(account);
	    btnDelete.setEnabled(true);
	    btnModify.setEnabled(true);
	    btnDelete.setToolTipText("<html><center><u>Borrar Proveedor</u><br>" + provider.getName() + "</center></html>");
	    btnModify.setToolTipText("<html><center><u>Modificar Proveedor</u><br>" + provider.getName() + "</center></html>");
	} else {
	    btnDelete.setEnabled(false);
	    btnModify.setEnabled(false);
	    btnDelete.setToolTipText("Borrar el proveedor seleccionado");
	    btnModify.setToolTipText("Modificar el proveedor seleccionado");
	}
    }

    private void loadMgmt() {
	parentMain.setSelectedTab(1);
	parentMain.getProvidersMgmt().setProvider(provider);
	parentMain.setTitleAt(1, "Modificar Proveedor");
    }

    private void btnSearch_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void btnDelete_actionPerformed(ActionEvent e) {
	if (!dataRow.isEmpty()) {
	    if (Advisor.question("Borrar proveedor", "¿Está seguro de borrar el proveedor seleccionado?")) {
		provider.delData();
		refresh();
	    }
	} else {
	    Advisor.messageBox("Debe seleccionar un proveedor de la Lista", "Mensaje");
	}
    }

    private void btnModify_actionPerformed(ActionEvent e) {
	loadMgmt();
    }

    private void btnAdd_actionPerformed(ActionEvent e) {
	parentMain.setTitleAt(1, "Nuevo Proveedor");
	parentMain.getProvidersMgmt().setProvider(new Provider());
	parentMain.setSelectedTab(1);
    }

}
