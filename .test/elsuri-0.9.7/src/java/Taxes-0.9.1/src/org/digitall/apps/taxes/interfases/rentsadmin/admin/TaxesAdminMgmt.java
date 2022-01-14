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
 * TaxesAdminMgmt.java
 *
 * */
package org.digitall.apps.taxes.interfases.rentsadmin.admin;

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

import org.digitall.apps.taxes.classes.Car;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.grid.TableTransferHandler;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;

public class TaxesAdminMgmt extends BasicPrimitivePanel {

    private BasicPanel contentPanel = new BasicPanel();
    private BasicPanel northPanel = new BasicPanel();

    private TFInput tfNombreImpuesto = new TFInput(DataTypes.STRING, "Name",false);
    private TFInput tfBuscarCuentaDebe = new TFInput(DataTypes.STRING, "Domain",false);

    private int[] sizeColumnList = {75, 190, 75, 85, 135, 55 , 80};
    private Vector gridHeader = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel gridPanel = new GridPanel(50000, sizeColumnList, "Listado de Impuestos", dataRow) {
	public void finishLoading() {
	    controlBotones();
	}
    }
    ;
    
    private FindButton btnFind = new FindButton();
    private AddButton btnAdd = new AddButton();
    private ModifyButton btnEdit = new ModifyButton();
    private AcceptButton btnAdminCuotas = new AcceptButton();
    private PrintButton btnPrint = new PrintButton();
    private DeleteButton btnDel = new DeleteButton();
    
    private Car car;
    
    private BorderLayout borderLayout1 = new BorderLayout();

    public TaxesAdminMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout(null);
	contentPanel.setLayout(borderLayout1);
	this.setSize(new Dimension(710, 388));
	this.setPreferredSize(new Dimension(710, 515));
	tfNombreImpuesto.setBounds(new Rectangle(50, 10, 170, 35));
	gridPanel.setBounds(new Rectangle(5, 75, 400, 270));
	gridPanel.setSize(new Dimension(400, 300));
	contentPanel.setBounds(new Rectangle(5, 5, 400, 500));
	contentPanel.setSize(new Dimension(700, 515));
	northPanel.setBounds(new Rectangle(5, 10, 690, 50));
	northPanel.setLayout(null);
	tfBuscarCuentaDebe.setBounds(new Rectangle(280, 10, 140, 35));
	btnFind.setBounds(new Rectangle(660, 25, 30, 25));
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
	btnAdminCuotas.setText("Administrar Cuotas\npor Categoría");
	btnAdminCuotas.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       btnAdminCuotas_actionPerformed(e);
				   }

			       }
	);
	btnPrint.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnPrint_actionPerformed(e);
				}

			    }
	);
	northPanel.add(tfBuscarCuentaDebe, null);
	northPanel.add(tfNombreImpuesto, null);
	northPanel.add(btnFind, null);
	btnEdit.setBounds(new Rectangle(610, 525, 40, 25));
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
	contentPanel.add(northPanel, borderLayout1.NORTH);
	contentPanel.add(gridPanel, borderLayout1.CENTER);
	this.add(contentPanel, BorderLayout.CENTER);
	this.addButton(btnEdit);
	this.addButton(btnAdd);
	
	if (Environment.sessionUser.equals("cesar"))  {
	    this.addButton(btnDel);
	}
	gridPanel.getTable().setDragEnabled(true);
	gridPanel.getTable().setTransferHandler(new TableTransferHandler());
	tfNombreImpuesto.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});
	tfBuscarCuentaDebe.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});
        btnEdit.setEnabled(false);
	btnDel.setEnabled(false);
	setCadastralHeader();
	addButton(btnPrint);
	btnPrint.setVisible(false);
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	//getParentInternalFrame().getGeneralButtons().addButton(btnAdminCuotas);
    }
    
    private void btnAdminCuotas_actionPerformed(ActionEvent e) {
	 
    }


    public void refresh() {
	String params = "'"+ tfNombreImpuesto.getString().trim() +"','"+ tfBuscarCuentaDebe.getString().trim() +"'";
	gridPanel.refresh(params);
	btnEdit.setEnabled(false);
	btnDel.setEnabled(false);
    }

    private void setCadastralHeader() {
	gridHeader.removeAllElements();
	gridHeader.addElement("*");    //iddominio
	gridHeader.addElement(Environment.lang.getProperty("Domain"));
	gridHeader.addElement(Environment.lang.getProperty("Name"));
        gridHeader.addElement(Environment.lang.getProperty("DNI"));
	gridHeader.addElement(Environment.lang.getProperty("Type"));
	gridHeader.addElement(Environment.lang.getProperty("Brand"));
	gridHeader.addElement("*");    //motor
	gridHeader.addElement("*");    //categoria
	gridHeader.addElement(Environment.lang.getProperty("Model"));
        gridHeader.addElement("*");    //cuota
        gridHeader.addElement("*");    //valor anual
        gridHeader.addElement("*");    //pagadesde
	gridHeader.addElement(Environment.lang.getProperty("Eximido"));
	gridHeader.addElement("*");    //fechabaja
	gridHeader.addElement("*");    //estado
	gridHeader.addElement("*");    //idautomotor
	gridHeader.addElement("*");    //anticipoautomotor
	gridHeader.addElement("*");    //domicilio
	gridHeader.addElement("*");    //observaciones
	gridHeader.addElement("*");    //tipo dominio
	gridPanel.getTable().addMouseListener(new MouseAdapter() { 
		    public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                            loadObjectSelected();    
			} else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
			    loadMgmt(false);
			}
		    }
		});
		
	String params = "'','',''" ;
		gridPanel.setParams("taxes.getAllCars", params, gridHeader);
    }

    
    private void loadObjectSelected(){
	if (!dataRow.isEmpty()){
	    car = new Car();
	    car.setIddominio(Integer.parseInt("" + dataRow.elementAt(0)));
	    car.setDominio("" + dataRow.elementAt(1));
	    car.retrieveData();
	    btnEdit.setEnabled(true);
	    btnDel.setEnabled(true);
	}
    }
    
    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void loadMgmt(boolean _addAction){
	
    }
    
    private void btnAdd_actionPerformed(ActionEvent e) {
	//Falta una funcion que haga controles. Por ej. la fecha del modelo no debe ser superior a la fecha de inicio de pago
	loadMgmt(true);
    }

    private void btnEdit_actionPerformed(ActionEvent e) {
	//Falta una funcion que haga controles. Por ej. la fecha del modelo no debe ser superior a la fecha de inicio de pago
	loadMgmt(false);
    }

    private void btnPrint_actionPerformed(ActionEvent e) {
    
    }
    
    private void controlBotones(){
	btnEdit.setEnabled(false);
	btnDel.setEnabled(false);
    }
    
    private void btnDel_actionPerformed(ActionEvent e) {
	// Funcion que podra borrar automotores, solo en modo admin
	if (Advisor.question("Advertencia","¿Está seguro de eliminar el Dominio "+ car.getDominio() +"?")) {
	    if (car.deleteCar()) {
		Advisor.messageBox("Dominio Borrado exitosamente","Mensaje");
	    } else {
	        Advisor.messageBox("Ocurrió un error al borrar el Dominio","Error");
	    }
	}
    }
    
}
