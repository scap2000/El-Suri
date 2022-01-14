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
 * FlatRatesMain.java
 *
 * */
package org.digitall.projects.kamar.taller.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import javax.swing.JOptionPane;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.LastGridButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class FlatRatesMain extends BasicPrimitivePanel {

    private Vector flatRatesDataRow = new Vector();
    private GridPanel flatRatesListPanel = new GridPanel(5000, new int[]{419, 63}, "Tipos de Mecánica", flatRatesDataRow);

    private BasicDialog parentContainer;

    private TFInput tfBuscarCategoria = new TFInput(DataTypes.STRING, "Buscar", false);
    private CBInput cbCategorias = new CBInput(0,"Categoría",false);

    private TFInput tfBuscarTarea = new TFInput(DataTypes.STRING, "Buscar", false);
    private CBInput cbTareas = new CBInput(0, "Tarea");
    private TFInput tfTiempoTarea = new TFInput(DataTypes.INTEGER, "Minutos", false);
    private SaveDataButton btnSaveFlatRateTime = new SaveDataButton();
    private DeleteButton btnDelete = new DeleteButton();
    private LastGridButton btnDuplicate = new LastGridButton();

    private BasicPanel jpCenter = new BasicPanel();

    private TFInput tfFiltrarTarea = new TFInput(DataTypes.STRING, "Filtrar listado", false);
    
    public FlatRatesMain() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	jpCenter.setLayout(null);
	jpCenter.setBorder(BorderPanel.getBorderPanel("Agregar/Modificar Tiempos de Tarea (Flat rate times)"));
	this.setSize(new Dimension(562, 422));
	cbCategorias.setBounds(new Rectangle(135, 25, 280, 35));
	flatRatesListPanel.setBounds(new Rectangle(10, 150, 545, 255));
	btnSaveFlatRateTime.setBounds(new Rectangle(525, 75, 30, 25));
	btnDelete.setBounds(new Rectangle(525, 35, 30, 25));
	btnDelete.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnDelete_actionPerformed(e);
		}
	    });
	btnDuplicate.setBounds(new Rectangle(420, 35, 30, 25));
	btnDuplicate.setToolTipText("Copiar esta configuración a otra categoría");
	btnDuplicate.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnDuplicate_actionPerformed(e);
		}
	    });
	btnSaveFlatRateTime.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnSaveFlatRateTime_actionPerformed(e);
		}
	    });

	jpCenter.add(flatRatesListPanel, null);
	jpCenter.add(tfBuscarCategoria, null);
	jpCenter.add(tfBuscarTarea, null);
	jpCenter.add(cbTareas, null);
	jpCenter.add(tfTiempoTarea, null);
	jpCenter.add(btnSaveFlatRateTime, null);
	jpCenter.add(tfFiltrarTarea, null);
	jpCenter.add(btnDelete, null);
	jpCenter.add(btnDuplicate, null);
	tfBuscarTarea.setBounds(new Rectangle(10, 65, 90, 35));
	tfFiltrarTarea.setBounds(new Rectangle(10, 105, 165, 35));
	tfTiempoTarea.setBounds(new Rectangle(470, 65, 55, 35));

	setHeaderList();

	tfBuscarTarea.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			loadComboTareas(tfBuscarTarea.getStringForSQL());
		    }
		}

	    });

	tfTiempoTarea.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			saveData();
		    }
		}

	    });

	tfFiltrarTarea.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			refresh();
		    }
		}

	    });

	tfBuscarCategoria.setBounds(new Rectangle(10, 25, 120, 35));
	tfBuscarCategoria.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			loadComboCategorias(tfBuscarCategoria.getStringForSQL());
		    }
		}

	    });

	cbTareas.setBounds(new Rectangle(105, 65, 360, 35));

	jpCenter.add(cbCategorias, null);
	this.add(jpCenter, BorderLayout.CENTER);

	loadComboCategorias("''");
	cbCategorias.addItemListener(new ItemListener() {
	    public void itemStateChanged(ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED) {
		    refresh();
		}
	    }
	});
    }

    private void loadComboTareas(String _filtro) {
	cbTareas.loadJCombo("taller.getAllTareas", _filtro + ",0,0");
	//cbTareas.loadJCombo("taller.getAllTareasPorCategoria", _filtro + "," + cbCategorias.getSelectedValue() + ",0,0");
    }

    private void loadComboCategorias(String _filtro) {
	cbCategorias.loadJCombo("taller.getAllCategorias", _filtro + ",0,0");
	refresh();
    }

    private void setHeaderList() {
	Vector flatRatesHeaderList = new Vector();
	flatRatesHeaderList.addElement("* IdTarea");
	flatRatesHeaderList.addElement("Código - Nombre");
	flatRatesHeaderList.addElement("* Código");
	flatRatesHeaderList.addElement("* Nombre");
	flatRatesHeaderList.addElement("Tiempo");
	flatRatesListPanel.getTable().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
		    deleteItem();
		} else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
		    loadData();
		}
	    }
	});
	flatRatesListPanel.setParams("taller.getAllTareasPorCategoria", "'',-1", flatRatesHeaderList);
    }

    private void loadData() {
	if (flatRatesDataRow.size() > 0) {
	    tfTiempoTarea.setValue(flatRatesDataRow.elementAt(4));
	    loadComboTareas("'" + flatRatesDataRow.elementAt(2).toString() + "'");
	    cbTareas.setSelectedID(new Integer(flatRatesDataRow.elementAt(0).toString()));
	}
    }

    public void refresh() {
	if (cbCategorias.getSelectedIndex() != -1) {
	    flatRatesListPanel.refresh(tfFiltrarTarea.getStringForSQL() + "," + cbCategorias.getSelectedValue());
	    flatRatesListPanel.setTitle("Categoría " + cbCategorias.getSelectedItem() + " - Flat Rates");
	} else {
	   // Advisor.messageBox("No ha seleccionado una categoría de vehículo", "Error");
	}
    }
    
    public void setParentContainer(BasicDialog parentContainer) {
	this.parentContainer = parentContainer;
    }

    private void btnSaveFlatRateTime_actionPerformed(ActionEvent e) {
	saveData();
    };
    
    @Override
    public boolean saveData() {
	boolean _returns = false;
	if (cbCategorias.getSelectedIndex() != -1 && cbTareas.getSelectedIndex() != -1) {
	    if (Advisor.question("Grabar Tiempo de Tarea", "¿Está seguro?")) {
		if (LibSQL.getBoolean("taller.addTareaPorCategoria", cbTareas.getSelectedValue() + "," + cbCategorias.getSelectedValue() + "," + tfTiempoTarea.getInteger())) {
		    refresh();
		    _returns = true;
		}
	    }
	} else {
	    Advisor.messageBox("No ha seleccionado una categoría de vehículo o una tarea", "Error");
	}
	return _returns;
    }

    private void btnDuplicate_actionPerformed(ActionEvent e) {
	if (Advisor.question("Copiar configuración", "Tiene la posibilidad de copiar esta configuración a una categoría existente o crear una nueva.\n¿Desea crear una nueva categoría?")) {
	    String _title = JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Ingrese una nueva categoría", "Categorías", JOptionPane.QUESTION_MESSAGE);
	    if (_title != null) {
	        _title = _title.trim().toUpperCase().replaceAll("  ", " ");
	        if (_title.length() > 0) {
		    int _idNuevaCategoria = LibSQL.getInt("taller.setcategoria", -1 + ",'" + _title + "'");
	            if (_idNuevaCategoria != -1) {
			copyConfig(new Integer(cbCategorias.getSelectedValue().toString()), _idNuevaCategoria);
	            } else {
		        Advisor.messageBox("Ha ocurrido un error al intentar crear una categoría", "Error");
		    }
	        } else {
	            Advisor.messageBox("El nombre de la categoría no puede estar vacío", "Error");
	        }
	    }
	} else {
	    Vector _categorias = new Vector();
	    Vector _ids = new Vector();
	    ResultSet _categoriasRS = LibSQL.exFunction("taller.getAllCategorias", "'',0,0");
	    try {
		while (_categoriasRS.next()) {
		    _categorias.add("(" + _categoriasRS.getInt("idcategoria") + ") - " +_categoriasRS.getString("nombre"));
		    _ids.add(_categoriasRS.getInt("idcategoria"));
		}
	    } catch (SQLException x) {
		
	    }
	    String _categoria = (String)JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Seleccione la categoría", "Seleccionar categoría", JOptionPane.QUESTION_MESSAGE, null, _categorias.toArray(), _categorias.toArray()[0]);
	    if (_categoria != null) {
		int idNuevaCategoria = new Integer(_categoria.substring(0, _categoria.indexOf("-")).replaceAll("\\(", "").replaceAll("\\)", "").trim());
		copyConfig(new Integer(cbCategorias.getSelectedValue().toString()), idNuevaCategoria);
	    } else {
	        Advisor.messageBox("Debe seleccionar una categoría", "Error");
	    }

	}
    }

    private void copyConfig(int _categoria, int _idNuevaCategoria) {
	if (LibSQL.getBoolean("taller.copyflatratesconfig", _categoria + "," + _idNuevaCategoria)) {
	    loadComboCategorias("''");
	} else {
	    Advisor.messageBox("Ha ocurrido un error al intentar copiar la configuración", "Error");
	}
    }

    private void btnDelete_actionPerformed(ActionEvent e) {
	deleteItem();
    }
    
    private void deleteItem() {
	if (flatRatesDataRow.size() > 0 && cbCategorias.getSelectedIndex() != -1) {
	    if (Advisor.question("Quitar la tarea de la lista", "¿Está seguro de remover la tarea \"" + flatRatesDataRow.elementAt(1) + "\" de la categoría \"" + cbCategorias.getSelectedItem() + "\"?")) {
		if (LibSQL.getBoolean("taller.removetareaporcategoria", flatRatesDataRow.elementAt(0).toString() + "," + cbCategorias.getSelectedValue())) {
		    refresh();
		}
	    }
	}
    }
}
