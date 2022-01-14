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
 * CommercesList.java
 *
 * */
package org.digitall.apps.taxes.interfases.commercesadmin;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.apps.taxes.classes.Commerce;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.grid.TableTransferHandler;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;

public class CommercesList extends BasicPrimitivePanel {

    private BasicPanel contentPanel = new BasicPanel();
    private BasicPanel searchPanel = new BasicPanel();

    private TFInput tfTaxpayer = new TFInput(DataTypes.STRING, "TaxPayer",false);
    private TFInput tfPollTax = new TFInput(DataTypes.STRING, "PollTax",false);
    private TFInput tfTradeName = new TFInput(DataTypes.STRING, "TradeName",false);

    private int[] sizeColumnList = {75, 165, 75, 105, 135, 100 , 95};
    private Vector commercesHeader = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(50000, sizeColumnList, "Listado de Vehículos", dataRow){
	public void finishLoading() {
	    controlBotones();
	}
    };
    
    private FindButton btnFind = new FindButton();
    private AddButton btnAdd = new AddButton();
    private ModifyButton btnEdit = new ModifyButton();
    private DeleteButton btnDel = new DeleteButton();

    private Commerce commerce;

    public CommercesList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout(null);
	this.setSize(new Dimension(710, 388));
	this.setPreferredSize(new Dimension(710, 515));
	tfTaxpayer.setBounds(new Rectangle(235, 10, 205, 35));
	listPanel.setBounds(new Rectangle(5, 75, 700, 270));
	contentPanel.setBounds(new Rectangle(5, 5, 700, 500));
	contentPanel.setLayout(null);
	contentPanel.setSize(new Dimension(700, 515));
	searchPanel.setBounds(new Rectangle(5, 10, 690, 50));
	searchPanel.setLayout(null);
	tfPollTax.setBounds(new Rectangle(35, 10, 140, 35));
	btnFind.setBounds(new Rectangle(660, 20, 30, 25));
	btnFind.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      btnFind_actionPerformed(e);
				  }

			      }
	);
	btnAdd.setBounds(new Rectangle(560, 525, 40, 25));
	btnAdd.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAdd_actionPerformed(e);
			      }

			  }
	);
        tfTradeName.setBounds(new Rectangle(495, 10, 140, 35));
        searchPanel.add(tfTradeName, null);
        searchPanel.add(tfPollTax, null);
        searchPanel.add(tfTaxpayer, null);
        searchPanel.add(btnFind, null);
        btnEdit.setBounds(new Rectangle(610, 525, 40, 25));
	btnEdit.setToolTipText("Modificar Comercio");
	btnEdit.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnEdit_actionPerformed(e);
			       }

			   }
	);
	btnDel.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnDel_actionPerformed(e);
			       }

			   }
	);
	contentPanel.add(listPanel, null);
	contentPanel.add(searchPanel, null);
	this.add(contentPanel, null);
	this.addButton(btnDel);
	this.addButton(btnEdit);
	this.addButton(btnAdd);
	listPanel.getTable().setDragEnabled(true);
	listPanel.getTable().setTransferHandler(new TableTransferHandler());
	tfTaxpayer.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});
	tfPollTax.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});
	tfTradeName.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});
        btnEdit.setEnabled(false);
	setCadastralHeader();
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
    }
    
    public void refresh() {
	String params = "'"+ tfPollTax.getString().trim() +"','"+ tfTaxpayer.getString().trim() +"','"+ tfTradeName.getString().trim() +"'";
	listPanel.refresh(params);
        btnEdit.setEnabled(false);
    }

    private void setCadastralHeader() {
	commercesHeader.removeAllElements();
	commercesHeader.addElement("*");    //idcomercio
	commercesHeader.addElement(Environment.lang.getProperty("PollTax"));
	commercesHeader.addElement(Environment.lang.getProperty("TaxPayer"));
        commercesHeader.addElement(Environment.lang.getProperty("DNI"));
	commercesHeader.addElement(Environment.lang.getProperty("StartDate"));
	commercesHeader.addElement(Environment.lang.getProperty("TradingName"));
	commercesHeader.addElement(Environment.lang.getProperty("TradeName"));
	commercesHeader.addElement(Environment.lang.getProperty("AddressReal"));
	commercesHeader.addElement(Environment.lang.getProperty("File"));
	commercesHeader.addElement("*");    //estado
	commercesHeader.addElement("*");    //cuota mensual
	
	listPanel.getTable().addMouseListener(new MouseAdapter() { 
		    public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                            loadObjectSelected();    
                            btnEdit.setEnabled(true);
			} else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
			    loadMgmt(false);
			}
		    }
		});
		
	String params = "'','',''" ;
	
	listPanel.setParams("taxes.getAllCommerces", params, commercesHeader);
    }

    
    private void loadObjectSelected(){
	if (!dataRow.isEmpty()){
            commerce = new Commerce();
            commerce.setIdpadron(Integer.parseInt("" + dataRow.elementAt(0)));
            commerce.retrieveData();
	}
    }
    
    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void loadMgmt(boolean _addAction){
	if (_addAction){
            commerce = new Commerce();
	}
        CommercesMgmt commercesMgmt = new CommercesMgmt();
        commercesMgmt.setCommerce(commerce);
        commercesMgmt.setParentList(this);

	ExtendedInternalFrame commerceMgmtContainer = new ExtendedInternalFrame("Agregar/Modificar");
	commerceMgmtContainer.setCentralPanel(commercesMgmt);
	commerceMgmtContainer.show();
    }
    
    private void btnAdd_actionPerformed(ActionEvent e) {
	loadMgmt(true);
    }

    private void btnEdit_actionPerformed(ActionEvent e) {
	loadMgmt(false);
    }
    
    private void btnDel_actionPerformed(ActionEvent e) {
	if (Advisor.question("Advertencia","¿Está seguro de eliminar el Comercio "+ commerce.getRazonsocial() +"?")) {
	    if (commerce.deleteComercio()) {
		Advisor.messageBox("Comercio Borrado exitosamente","Mensaje");
		refresh();
	    } else {
		Advisor.messageBox("Ocurrió un error al intentar borrar el Comercio seleccionado","Error");
	    }
	}
    }

    
    private void controlBotones(){
	btnEdit.setEnabled(false);
    }
}
