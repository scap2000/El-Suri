/**
 LIMITACIÃN DE RESPONSABILIDAD - COPYRIGHT - [EspaÃ±ol]
 ================================================================================
 KamarDesktop - Entorno JAVA de Trabajo y Desarrollo para Taller de Servicios Kamar
 ================================================================================

 Copyright (C) 2011 Lic. Santiago Cassina (scap2000@yahoo.com)
 http://www.scassi.com.ar

 La copia y distribuciÃ³n de este archivo, con o sin modificaciones,
 estÃ¡n permitidas por cualquier medio sin cargo mientras se preserven
 el Aviso de Copyright y este mismo aviso.

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los tÃ©rminos de la Licencia PÃºblica General GNU publicada
 por la FundaciÃ³n para el Software Libre, ya sea la versiÃ³n 3
 de la Licencia, o (a su elecciÃ³n) cualquier versiÃ³n posterior.

 Este programa se distribuye con la esperanza de que sea Ãºtil, pero
 SIN GARANTÃA ALGUNA; ni siquiera la garantÃ­a implÃ­cita
 MERCANTIL o de APTITUD PARA UN PROPÃSITO DETERMINADO.
 Consulte los detalles de la Licencia PÃºblica General GNU para obtener
 una informaciÃ³n mÃ¡s detallada.

 DeberÃ­a haber recibido una copia de la Licencia PÃºblica General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
KamarDesktop - JAVA Management & Development Environment for Kamar Mechanical Services
 =====================================================================================

 Copyright (C) 2011 by Lic. Santiago Cassina (scap2000@yahoo.com)
 http://www.scassi.com.ar

 Copying and distribution of this file, with or without modification,
 are permitted in any medium without royalty provided the copyright
 notice and this notice are preserved.

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
 * TiposVehiculoMain.java
 *
 * */
package org.digitall.projects.kamar.taller.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.JOptionPane;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CancelDataButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;
import org.digitall.projects.kamar.taller.classes.TipoVehiculo;

public class TiposVehiculoMain extends BasicPrimitivePanel {

    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(100, new int[] {316, 140}, "Tipos de Vehículo", dataRow);
    private BasicPanel panelData = new BasicPanel();
    private TFInput tfDescripcion = new TFInput(DataTypes.STRING, "Descripción", false);
    private TFInput tfBuscarCategoria = new TFInput(DataTypes.STRING, "Buscar", false);
    private CBInput cbCategorias = new CBInput(0, "Categoría", true) {
	@Override
	public void performAddButtonAction() {
	    String _title = JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Ingrese una nueva categoría", "Categorías", JOptionPane.QUESTION_MESSAGE);
	    if (_title != null) {
	        _title = _title.trim().toUpperCase().replaceAll("  ", " ");
	        if (_title.length() > 0) {
		    int _idCategoria = LibSQL.getInt("taller.setcategoria", -1 + ",'" + _title + "'");
		    if ( _idCategoria != -1) {
			getCombo().addItem(_title, _idCategoria, -1);
			setSelectedValue(_title);
		    }
	        } else {
	            Advisor.messageBox("El nombre de la categoría no puede estar vacío", "Error");
	        }
	    }
	}
    };
       
    private CloseButton btnClose = new CloseButton();
    private SaveDataButton btnSave = new SaveDataButton();
    private DeleteButton btnDelete = new DeleteButton();
    private CancelDataButton btnCancel = new CancelDataButton();
    private BasicDialog parentContainer;
    private TipoVehiculo tipoVehiculo;
    private boolean blank = true;
    private FindButton btnFind = new FindButton();
    
    public TiposVehiculoMain() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(524, 326));
	panelData.setPreferredSize(new Dimension(340, 120));
	panelData.setLayout(null);
	tfDescripcion.setBounds(new Rectangle(15, 25, 500, 35));
	tfBuscarCategoria.setBounds(new Rectangle(15, 70, 85, 35));
	tfBuscarCategoria.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			loadComboCategorias(tfBuscarCategoria.getStringForSQL());
		    }
		}

	    }
	);
	cbCategorias.setBounds(new Rectangle(105, 70, 270, 35));
	btnClose.setBounds(new Rectangle(305, 410, 40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	btnSave.setBounds(new Rectangle(420, 80, 30, 25));
	btnSave.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnSave_actionPerformed(e);
			      }

			  }
	);
	btnCancel.setBounds(new Rectangle(450, 80, 30, 25));
	btnCancel.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnCancel_actionPerformed(e);
				 }

			     }
	);
	btnFind.setBounds(new Rectangle(385, 80, 30, 25));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);
	btnDelete.setBounds(new Rectangle(485, 80, 30, 25));
	btnDelete.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnDelete_actionPerformed(e);
		}
	    });
	panelData.add(btnFind, null);
	panelData.add(btnCancel, null);
	panelData.add(tfDescripcion, null);
	panelData.add(tfBuscarCategoria, null);
	panelData.add(cbCategorias, null);
	panelData.add(btnSave, null);
	panelData.add(btnDelete, null);
	this.add(panelData, BorderLayout.NORTH);
	this.add(listPanel, BorderLayout.CENTER);
	panelData.setBorder(BorderPanel.getBorderPanel("Agregar/Modificar tipo de Vehículo", Color.blue, Color.black));
	setHeaderList();
	cbCategorias.setGeneralItem(true);
	loadComboCategorias("''");
	refresh();
    }

    private void loadComboCategorias(String _filtro) {
	cbCategorias.loadJCombo("taller.getAllCategorias", _filtro + ",0,0");
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("* IdTipoVehiculo");
	headerList.addElement("* Artículo");
	headerList.addElement("Descripción");
	headerList.addElement("* IdCategoria");
	headerList.addElement("Categoría");
	listPanel.getTable().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
		    clearData();		    
		} else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
		    loadData();
		}
	    }
	});
	listPanel.setParams("taller.getAllTiposVehiculo", "''", headerList);
    }

    public void refresh() {
	listPanel.refresh(tfDescripcion.getStringForSQL() + "," + cbCategorias.getSelectedValue());
    }
    
    private void loadData(){
	if (dataRow.size() > 0) {
	    tipoVehiculo = new TipoVehiculo(new Integer(dataRow.elementAt(0).toString()), dataRow.elementAt(1).toString(), dataRow.elementAt(2).toString(), new Integer(dataRow.elementAt(3).toString()));
	    //tfNombre.setValue(tipoVehiculo.getNombre());
	    tfDescripcion.setValue(tipoVehiculo.getDescripcion());
	    loadComboCategorias("''");
	    cbCategorias.setSelectedID(tipoVehiculo.getIdCategoria());
	}
	blank = false;
    }

    private void clearData() {
	//tfNombre.setValue("");
	tfDescripcion.setValue("");
	//cbCategorias.setSelectedID(0);
	blank = true;
    }
    
    private void btnCancel_actionPerformed(ActionEvent e) {
	clearData();
    }

    private void btnSave_actionPerformed(ActionEvent e) {
	saveData();
    };
    
    @Override
    public boolean saveData() {
	boolean _returns = false;
	Vector selecteds = listPanel.getSelectedsID();
	if (selecteds.size() > 0 && cbCategorias.getSelectedIndex() != -1) {
	    if (Advisor.question("Asignar Categoría", "¿Está seguro de asignar la categoría\n" + cbCategorias.getSelectedItem() + "\na todos los tipos de vehículo seleccionados?")) {
		if (LibSQL.getBoolean("taller.setcategoriabyids", cbCategorias.getSelectedValue() + ",'" + listPanel.getSelectedsIDSeparatedByComma() + "'")) {
	            clearData();
	            refresh();
		    _returns = true;
	        } else {
	            Advisor.messageBox("Ha ocurrido un error al intentar grabar los datos", "Asignación múltiple de tipos de vehículo");
	        }
	    }
	} else {
	    boolean _continue = true;
	    if (blank){
		tipoVehiculo = new TipoVehiculo();
	    } else {
		_continue = Advisor.question("Agregar/Modificar Tipo de Vehículo", "¿Está seguro de modificar el tipo de vehículo \"" + tipoVehiculo.getDescripcion() + "\"?");
	    }
	    if (_continue) {
		if (cbCategorias.getSelectedIndex() == -1) {
		    Advisor.messageBox("No ha seleccionado una categoría", "Agregar/Modificar Tipo de Vehículo");
		} else if (tfDescripcion.getString().length() == 0) {
		    Advisor.messageBox("Debe ingresar una descripción", "Datos vacíos");
		} else {
		    //tipoVehiculo.setNombre(tfNombre.getString());
		    tipoVehiculo.setDescripcion(tfDescripcion.getString());
		    tipoVehiculo.setIdCategoria(new Integer(cbCategorias.getSelectedValue().toString()));
		    if (tipoVehiculo.saveData() != -1) {
			clearData();
			refresh();
			_returns = true;
		    } else {
			Advisor.messageBox("Ha ocurrido un error al intentar grabar los datos", "Agregar/Modificar Tipo de Vehículo");
		    }
		}
	    }
	}
	return _returns;
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	parentContainer.setVisible(false);
    }

    public void setParentContainer(BasicDialog parentContainer) {
	this.parentContainer = parentContainer;
    }

    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void btnDelete_actionPerformed(ActionEvent e) {
	deleteItem();
    }
    
    private void deleteItem() {
	Vector selecteds = listPanel.getSelectedsID();
	if (selecteds.size() > 0) {
	    if (Advisor.question("Borrar tipos de vehículo", "¿Está seguro de borrar todos los tipos de vehículo seleccionados?")) {
		if (LibSQL.getBoolean("taller.deletetiposvehiculobyids", "'" + listPanel.getSelectedsIDSeparatedByComma() + "'")) {
		    clearData();
		    refresh();
		} else {
		    Advisor.messageBox("Ha ocurrido un error al intentar borrar los tipos de vehículo", "Borrado múltiple de tipos de vehículo");
		}
	    }
	} else {
	    if (!blank){
		if (Advisor.question("Borrar Tipo de Vehículo", "¿Está seguro de borrar el tipo de vehículo \"" + tipoVehiculo.getDescripcion() + "\"?")) {
		    if (LibSQL.getBoolean("taller.deletetiposvehiculobyids", "'" + tipoVehiculo.getIdTipoVehiculo() + "'")) {
		        clearData();
		        refresh();
		    } else {
		        Advisor.messageBox("Ha ocurrido un error al intentar borrar el tipo de vehículo", "Borrado de vehículo");
		    }
		}
	    } else {
		Advisor.messageBox("No ha seleccionado un tipo de vehículo", "Agregar/Modificar Tipo de Vehículo");
	    }
	}
    }

}
