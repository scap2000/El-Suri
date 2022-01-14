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
 * InterestedByYearList.java
 *
 * */
package org.digitall.apps.taxes.interfases.cadastraladmin;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.apps.taxes.classes.InterestedByYear;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.grid.TableTransferHandler;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.environment.Environment;

public class InterestedByYearList extends BasicPrimitivePanel {

    private BasicPanel contentPanel = new BasicPanel();
    private BasicPanel searchPanel = new BasicPanel();
    private int[] sizeColumnList = {50, 278, 75};
    private Vector interestedByYearHeader = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel interestedByYearPanel = new GridPanel(30, sizeColumnList, "Impuestos", dataRow);
    
    private FindButton btnFind = new FindButton();
    private AddButton btnAdd = new AddButton();
    private ModifyButton btnEdit = new ModifyButton();
    

    private CBInput cbYears = new CBInput(0,"FileYear",false);
    private CBInput cbImpuesto = new CBInput(0,"TaxesType",false);
    
    private InterestedByYearMgmt interestedByYearMgmt;
    private InterestedByYear interestedByYear;

    public InterestedByYearList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout(null);
	this.setSize(new Dimension(460, 361));
	this.setPreferredSize(new Dimension(710, 515));
	interestedByYearPanel.setBounds(new Rectangle(5, 55, 450, 260));
	contentPanel.setBounds(new Rectangle(5, 5, 700, 500));
	contentPanel.setLayout(null);
	contentPanel.setSize(new Dimension(700, 515));
	searchPanel.setBounds(new Rectangle(5, 10, 495, 45));
	searchPanel.setLayout(null);
	btnFind.setBounds(new Rectangle(380, 15, 20, 20));
	btnFind.setSize(new Dimension(20, 20));
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
	searchPanel.add(cbImpuesto, null);
	searchPanel.add(cbYears, null);
	searchPanel.add(btnFind, null);
	btnEdit.setBounds(new Rectangle(610, 525, 40, 25));
	btnEdit.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnEdit_actionPerformed(e);
			       }

			   }
	);
	cbYears.setBounds(new Rectangle(5, 0, 100, 35));
	cbImpuesto.setBounds(new Rectangle(135, 0, 220, 35));
	contentPanel.add(interestedByYearPanel, null);
	contentPanel.add(searchPanel, null);
	this.add(contentPanel, null);
	this.addButton(btnEdit);
	this.addButton(btnAdd);
	interestedByYearPanel.getTable().setDragEnabled(true);
	interestedByYearPanel.getTable().setTransferHandler(new TableTransferHandler());
	cbYears.autoSize();
	cbImpuesto.autoSize();
	setCadastralHeader();
	loadComboModels();
    }
    private void loadComboModels(){
	int actualYear = Integer.parseInt("0" + Environment.currentYear);
	int cont = 0;
	for (int i = 1974 ; i <= (actualYear + 1) ; i++)  {
	    cont++;
	    cbYears.getCombo().addItem(i,cont);
	}
	cbYears.setSelectedID(cont - 1);
	
	cbImpuesto.getCombo().addItem("Tasa General de Servicios",1);
	cbImpuesto.getCombo().addItem("Impuesto Inmobiliario",2);
	cbImpuesto.getCombo().addItem("Impuesto Automotor",3);
	cbImpuesto.setSelectedID(1);
    }

    
    private void setCadastralHeader() {
	interestedByYearHeader.removeAllElements();
	interestedByYearHeader.addElement(Environment.lang.getProperty("FileYear"));
	interestedByYearHeader.addElement("*");
	interestedByYearHeader.addElement(Environment.lang.getProperty("TaxesType"));
	interestedByYearHeader.addElement("*");
	interestedByYearHeader.addElement("*");
	interestedByYearHeader.addElement(Environment.lang.getProperty("YearTaxes"));
	interestedByYearHeader.addElement("*");
	
	interestedByYearPanel.getTable().addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        loadObjectSelected();
			if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
			    
			} else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
			    loadMgmt(false);
			}
		    }
		});
		
	String params = "0,0" ;
	interestedByYearPanel.setParams("impuestos.getAllInteresesxAnio", params, interestedByYearHeader);
    }
    
    public void refresh() {
	String params = ""+ cbYears.getSelectedItem() +","+ cbImpuesto.getSelectedValue();
	interestedByYearPanel.refresh(params);
    }
    
    private void loadObjectSelected(){
	if (!dataRow.isEmpty()){
	    interestedByYear = new InterestedByYear();
	    interestedByYear.setAnio(Integer.parseInt("" + dataRow.elementAt(0)));
	    interestedByYear.setIdtipoimpuesto(Integer.parseInt("" + dataRow.elementAt(1)));
	    interestedByYear.setTipoImpuesto("" + dataRow.elementAt(2));
	    interestedByYear.setTasaanual(Double.parseDouble("0" + dataRow.elementAt(3)));
	    interestedByYear.setTasadiaria(Double.parseDouble("0" + dataRow.elementAt(4)));
	    interestedByYear.setNuevo(false);
	    
	}
    }
    
    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void loadMgmt(boolean _addAction){
	if (_addAction){
	    interestedByYear = new InterestedByYear();
	}
	interestedByYearMgmt = new InterestedByYearMgmt();
	interestedByYearMgmt.setInterestedByYearMgmt(interestedByYear);
	interestedByYearMgmt.setParentList(this);
	
	ExtendedInternalFrame interestedByYearMgmtContainer = new ExtendedInternalFrame("Agregar/Modificar Tasa Anual");
	interestedByYearMgmtContainer.setCentralPanel(interestedByYearMgmt);
	interestedByYearMgmtContainer.show();
    }
    
    private void btnAdd_actionPerformed(ActionEvent e) {
	loadMgmt(true);
    }

    private void btnEdit_actionPerformed(ActionEvent e) {
	loadMgmt(false);
    }
   
}
