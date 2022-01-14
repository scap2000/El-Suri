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
 * AddressList.java
 *
 * */
package org.digitall.apps.personalfiles;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import javax.swing.SwingConstants;

import org.digitall.apps.personalfiles.AddressPanel;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class AddressList extends BasicPrimitivePanel {

    private int[] sizeColumnList = { 224, 39, 42, 35, 42, 100, 71, 51 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Domicilios", dataRow);
    private Vector headerList = new Vector();
    private AddressPanel panelAddress = new AddressPanel();
    private BasicPanel panel = new BasicPanel();
    private CloseButton btnClose = new CloseButton();
    private AcceptButton btnAccept = new AcceptButton();
    private int type;
    private int idObjectParent;
    public static final int COMPANYTYPE = 1;
    public static final int PERSONTYPE = 2;
    private boolean addAction = true;
    private AddressPanel parentPanelAddress;
    private AssignButton btnAdd = new AssignButton(true);
    private DeleteButton btnDelete = new DeleteButton();
    private BasicPanel jPanel1 = new BasicPanel();
    private BorderLayout borderLayout1 = new BorderLayout();
    private GridBagLayout gridBagLayout1 = new GridBagLayout();
    private BasicPanel jPanel2 = new BasicPanel();
    private BasicLabel jLabel1 = new BasicLabel();
    private BorderLayout borderLayout2 = new BorderLayout();

    public AddressList(AddressPanel _parentPanelAddress, int _type, int _idObjectParent) {
	try {
	    parentPanelAddress = _parentPanelAddress;
	    type = _type;
	    idObjectParent = _idObjectParent;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(515, 517));
	listPanel.setBounds(new Rectangle(5, 285, 625, 185));
	listPanel.setPreferredSize(new Dimension(400, 180));
	listPanel.setMinimumSize(new Dimension(173, 180));
	listPanel.setSize(new Dimension(515, 180));
	panel.setBounds(new Rectangle(0, 0, 515, 300));
	panel.setLayout(borderLayout1);
	panel.setBorder(BorderPanel.getBorderPanel("Agregar Domicilio"));
	//new Rectangle(0, 480, 640, 5));
	panel.setMinimumSize(new Dimension(1, 100));
	panel.setPreferredSize(new Dimension(1, 310));
	btnClose.setBounds(new Rectangle(7, 490, 40, 25));
	btnClose.setSize(new Dimension(40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	btnAccept.setBounds(new Rectangle(592, 490, 40, 25));
	btnAccept.setSize(new Dimension(40, 25));
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	btnAdd.setSize(new Dimension(40, 25));
	btnAdd.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAdd_actionPerformed(e);
			      }

			  }
	);
	btnDelete.setSize(new Dimension(40, 25));
	btnDelete.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnDelete_actionPerformed(e);
				 }

			     }
	);
	jPanel1.setSize(new Dimension(635, 40));
	jPanel1.setPreferredSize(new Dimension(71, 35));
	jPanel1.setLayout(gridBagLayout1);
	jPanel2.setLayout(borderLayout2);
	jLabel1.setText("Puede agregar o borrar direcciones con los botones a su derecha -->");
	jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
	panelAddress.setSize(new Dimension(515, 225));
	//addButton(btnAccept);
	addButton(btnClose);
	jPanel1.add(btnAdd, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
	jPanel1.add(btnDelete, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
	jPanel2.add(jLabel1, BorderLayout.CENTER);
	jPanel1.add(jPanel2, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
	panel.add(panelAddress, BorderLayout.CENTER);
	panel.add(jPanel1, BorderLayout.SOUTH);
	add(panel, BorderLayout.NORTH);
	add(listPanel, BorderLayout.CENTER);
	setHeaderList();
	panelAddress.clearData();
	panelAddress.setObjectParent(idObjectParent, type);
	panelAddress.addComboItemListener();
	btnAdd.setToolTipText("Agregar dirección");
	btnDelete.setToolTipText("Borrar la dirección seleccionada");
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("Calle");
	headerList.addElement("Nro.");
	headerList.addElement("Block");
	headerList.addElement("Piso");
	headerList.addElement("Dpto.");
	headerList.addElement("Tel.");
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
	headerList.addElement("*");
	headerList.addElement("Tipo");
	headerList.addElement("*");
	headerList.addElement("Defecto");
	headerList.addElement("*");
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent e) {
					       if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						   panelAddress.setData(Integer.parseInt(dataRow.elementAt(0).toString()), false);
						   addAction = false;
					       } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						   setDefault();
					       }
					   }

				       }
	);
	switch (type) {
	    case COMPANYTYPE :
		{
		    String params = String.valueOf(idObjectParent);
		    listPanel.setParams("org.getallCompanyAddresses", params, headerList);
		}
		break;
	    case PERSONTYPE :
		{
		    String params = String.valueOf(idObjectParent);
		    listPanel.setParams("org.getallPersonAddresses", params, headerList);
		}
		break;
	    default :
		{

		}
		break;
	}
	refresh();
    }

    public void refresh() {
	listPanel.refresh(String.valueOf(idObjectParent));
    }

    private void setDefault() {
	if (!dataRow.isEmpty()) {
	    String query = "";
	    String idaddress = dataRow.elementAt(0).toString();
	    switch (type) {
		case COMPANYTYPE :
		    {
			String idcompany = dataRow.elementAt(1).toString();
			query = "SELECT org.setCompanyAddressDefault(" + idcompany + "," + idaddress + ",'TRUE');";
		    }
		    break;
		case PERSONTYPE :
		    {
			String idperson = dataRow.elementAt(1).toString();
			query = "SELECT org.setPersonAddressDefault(" + idperson + "," + idaddress + ",'TRUE');";
		    }
		    break;
		default :
		    {

		    }
		    break;
	    }
	    if (query.length() > 0) {
		LibSQL.updateQuery(query);
		refresh();
	    }
	}
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    private void btnAdd_actionPerformed(ActionEvent e) {
	if (addAction) {
	    panelAddress.setIdAddress(0);
	}
	panelAddress.saveData("false");
	panelAddress.setTabbIndex(0);
	refresh();
	addAction = true;
    }

    private void btnAccept_actionPerformed(ActionEvent e) {
	try {
	    switch (type) {
		case COMPANYTYPE :
		    {
			ResultSet rsAddresses = LibSQL.exFunction("org.getCompanyAddressByDefault", String.valueOf(idObjectParent));
			if (rsAddresses.next()) {
			    parentPanelAddress.setData(rsAddresses.getInt("idaddress"), true);
			}
		    }
		    break;
		case PERSONTYPE :
		    {
			ResultSet rsAddresses = LibSQL.exFunction("org.getPersonAddressByDefault", String.valueOf(idObjectParent));
			if (rsAddresses.next()) {
			    parentPanelAddress.setData(rsAddresses.getInt("idaddress"), true);
			}
		    }
		    break;
		default :
		    {

		    }
		    break;
	    }
	    getParentInternalFrame().close();
	} catch (SQLException x) {
	    x.printStackTrace();
	}
    }

    private void btnDelete_actionPerformed(ActionEvent e) {
	if (!dataRow.isEmpty()) {
	    if (!dataRow.elementAt(27).toString().equals("SI")) {
		String idaddress = dataRow.elementAt(0).toString();
		int returnDelete = -1;
		if (Advisor.question("Borrar dirección", "¿Está seguro de borrar la dirección seleccionada?")) {
		    switch (type) {
			case COMPANYTYPE :
			    {
				String idcompany = dataRow.elementAt(1).toString();
				String params = idcompany + "," + idaddress;
				returnDelete = LibSQL.getInt("org.delCompanyAddress", params);
			    }
			    break;
			case PERSONTYPE :
			    {
				String idperson = dataRow.elementAt(1).toString();
				String params = idperson + "," + idaddress;
				returnDelete = LibSQL.getInt("org.delPersonAddress", params);
			    }
			    break;
			default :
			    {

			    }
			    break;
		    }
		    if (returnDelete != -1) {
			if (LibSQL.getInt("org.deladdress", idaddress) != -1) {
			    refresh();
			} else {
			    Advisor.messageBox(Environment.lang.getProperty("ErrorDelete"), "Error");
			}
		    } else {
			Advisor.messageBox(Environment.lang.getProperty("ErrorDelete"), "Error");
		    }
		}
	    } else {
		Advisor.messageBox(Environment.lang.getProperty("ErrorDeleteDefault"), "Error");
	    }
	} else {
	    Advisor.messageBox("Debe seleccionar una dirección para borrar", "Aviso");
	}
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Recuerde que puede agregar tantas direcciones como desee");
    }

}
