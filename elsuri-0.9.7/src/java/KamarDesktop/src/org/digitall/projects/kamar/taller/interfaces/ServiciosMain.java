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
 * ServiciosMain.java
 *
 * */
package org.digitall.projects.kamar.taller.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.SwingConstants;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.JIntEntry;
import org.digitall.lib.components.JMoneyEntry;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicTitleLabel;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CancelDataButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;
import org.digitall.projects.kamar.taller.classes.Servicio;

public class ServiciosMain extends BasicPrimitivePanel {

    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(5000, new int[]{327, 56, 252}, "Servicios", dataRow) {
	@Override
	public void finishLoading() {
	    refreshDetail();
	}
	
    };
    private Vector articulosDataRow = new Vector();
    private Vector tareasDataRow = new Vector();
    private GridPanel tareasListPanel = new GridPanel(5000, new int[]{532, 40, 63}, "Tareas", tareasDataRow) {
	public void calculate() {
	    tfTiempoTotalTareas.setValue(this.getSum(2));
	    tfMontoTotalTareas.setValue(this.getSum(3));
	    tfMontoTotalServicio.setValue(tfMontoTotalTareas.getAmount() + tfMontoTotalArticulos.getAmount());
	}
    };
    private GridPanel articulosListPanel = new GridPanel(5000, new int[]{489, 75, 75}, "Artículos", articulosDataRow) {
	public void calculate() {
	    tfMontoTotalArticulos.setValue(this.getSum(3));
	    tfMontoTotalServicio.setValue(tfMontoTotalTareas.getAmount() + tfMontoTotalArticulos.getAmount());
	}
    };
    private BasicPanel panelData = new BasicPanel();
    private TFInput tfNombre = new TFInput(DataTypes.STRING, "Name", false);
    private TFInput tfPrecio = new TFInput(DataTypes.MONEY, "Precio", false);
    private CBInput cbCategorias = new CBInput(0,"Categoría",false);

    private SaveDataButton btnSave = new SaveDataButton();
    private CancelDataButton btnCancel = new CancelDataButton();
    private BasicDialog parentContainer;
    private Servicio servicio;
    private boolean blank = true;
    private FindButton btnFind = new FindButton();

    private TFInput tfBuscarTarea = new TFInput(DataTypes.STRING, "Buscar", false);
    private CBInput cbTareas = new CBInput(0, "Tarea");
    private AssignButton btnAddTarea = new AssignButton(true);
    private TFInput tfBuscarArticulo = new TFInput(DataTypes.STRING, "Buscar", false);
    private CBInput cbArticulos = new CBInput(0, "Artículo");
    private TFInput tfCantidadArticulos = new TFInput(DataTypes.INTEGER, "Cant.", false);
    private AssignButton btnAddArticulo = new AssignButton(true);
    
    private BasicTitleLabel lblTotalArticulos = new BasicTitleLabel();
    private JMoneyEntry tfMontoTotalArticulos = new JMoneyEntry();
    private BasicTitleLabel lblTotalTareas = new BasicTitleLabel();
    private JIntEntry tfTiempoTotalTareas = new JIntEntry();
    private JMoneyEntry tfMontoTotalTareas = new JMoneyEntry();

    private BasicTitleLabel lblTotalServicio = new BasicTitleLabel();
    private JMoneyEntry tfMontoTotalServicio = new JMoneyEntry();

    private BasicPanel jpCenter = new BasicPanel();
	
    public ServiciosMain() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(723, 490));
	jpCenter.setLayout(null);
	jpCenter.setBorder(BorderPanel.getBorderPanel("Agregar/Modificar servicios"));
	listPanel.setBounds(new Rectangle(10, 90, 700, 95));
	tareasListPanel.setBounds(new Rectangle(10, 235, 700, 95));
	articulosListPanel.setBounds(new Rectangle(10, 380, 700, 95));
	panelData.setBounds(new Rectangle(5, 20, 710, 65));
	panelData.setLayout(null);
	tfNombre.setBounds(new Rectangle(10, 20, 200, 35));
	tfNombre.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			refresh();
		    }
		}

	    }
	);
	tfPrecio.setBounds(new Rectangle(215, 20, 70, 35));
	tfPrecio.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			saveData();
		    }
		}

	    }
	);
	cbCategorias.setBounds(new Rectangle(290, 20, 225, 35));
	lblTotalArticulos.setBounds(new Rectangle(600, 335, 110, 20));
	lblTotalArticulos.setText("Monto estimado:");
	lblTotalArticulos.setHorizontalAlignment(SwingConstants.RIGHT);
	lblTotalArticulos.setFont(new Font("Arial", 1, 13));
	tfMontoTotalArticulos.setBounds(new Rectangle(615, 355, 95, 20));
	tfMontoTotalArticulos.setEditable(false);
	tfMontoTotalArticulos.setBackground(Color.yellow);
	tfMontoTotalArticulos.setFont(new Font("Dialog", 1, 13));

	lblTotalTareas.setBounds(new Rectangle(545, 190, 165, 20));
	lblTotalTareas.setText("Tiempo/Monto estimado:");
	lblTotalTareas.setHorizontalAlignment(SwingConstants.RIGHT);
	lblTotalTareas.setFont(new Font("Arial", 1, 13));
	tfMontoTotalTareas.setBounds(new Rectangle(615, 325, 95, 20));
	tfMontoTotalTareas.setBounds(new Rectangle(615, 210, 95, 20));
	tfMontoTotalTareas.setEditable(false);
	tfMontoTotalTareas.setBackground(Color.yellow);
	tfMontoTotalTareas.setFont(new Font("Dialog", 1, 13));
	tfTiempoTotalTareas.setBounds(new Rectangle(555, 210, 55, 20));
	tfTiempoTotalTareas.setEditable(false);
	tfTiempoTotalTareas.setFont(new Font("Dialog", 1, 13));
	tfTiempoTotalTareas.setBackgroundColor(new Color(255, 156, 49));
	tfTiempoTotalTareas.setBackground(Color.yellow);

	lblTotalServicio.setBounds(new Rectangle(585, 10, 110, 20));
	lblTotalServicio.setText("Monto estimado:");
	lblTotalServicio.setHorizontalAlignment(SwingConstants.RIGHT);
	lblTotalServicio.setFont(new Font("Arial", 1, 13));
	tfMontoTotalServicio.setBounds(new Rectangle(615, 325, 95, 20));
	tfMontoTotalServicio.setBounds(new Rectangle(620, 35, 75, 20));
	tfMontoTotalServicio.setEditable(false);
	tfMontoTotalServicio.setBackground(Color.yellow);
	tfMontoTotalServicio.setFont(new Font("Dialog", 1, 13));

	btnSave.setBounds(new Rectangle(520, 30, 30, 25));
	btnSave.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnSave_actionPerformed(e);
			      }

			  }
	);
	btnCancel.setBounds(new Rectangle(550, 30, 30, 25));
	btnCancel.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnCancel_actionPerformed(e);
				 }

			     }
	);
	btnFind.setBounds(new Rectangle(580, 30, 30, 25));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);

	panelData.add(btnCancel, null);
	panelData.add(tfNombre, null);
	panelData.add(tfPrecio, null);
	panelData.add(btnSave, null);
	panelData.add(cbCategorias, null);
	panelData.add(lblTotalServicio, null);
	panelData.add(tfMontoTotalServicio, null);
	panelData.add(btnFind, null);
	tfBuscarArticulo.setBounds(new Rectangle(10, 340, 85, 35));
	tfCantidadArticulos.setBounds(new Rectangle(525, 340, 45, 35));
	tfCantidadArticulos.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			agregarArticulo();
		    }
		}

	    });
	tfBuscarArticulo.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			loadComboArticulos(tfBuscarArticulo.getStringForSQL());
		    }
		}

	    });
	cbArticulos.setBounds(new Rectangle(100, 340, 420, 35));
	btnAddArticulo.setBounds(new Rectangle(570, 350, 30, 25));
	btnAddArticulo.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnAddArticulo_actionPerformed(e);
		}
	    });
	tfBuscarTarea.setBounds(new Rectangle(10, 195, 85, 35));
	tfBuscarTarea.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			loadComboTareas(tfBuscarTarea.getStringForSQL());
		    }
		}

	    });
	cbTareas.setBounds(new Rectangle(100, 195, 415, 35));
	btnAddTarea.setBounds(new Rectangle(520, 205, 30, 25));
	btnAddTarea.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnAddTarea_actionPerformed(e);
		}
	    });

	jpCenter.add(panelData, null);
	jpCenter.add(listPanel, null);
	jpCenter.add(tareasListPanel, null);
	jpCenter.add(articulosListPanel, null);
	jpCenter.add(tfBuscarTarea, null);
	jpCenter.add(cbTareas, null);
	jpCenter.add(btnAddTarea, null);
	jpCenter.add(tfBuscarArticulo, null);
	jpCenter.add(cbArticulos, null);
	jpCenter.add(btnAddArticulo, null);
	jpCenter.add(lblTotalArticulos, null);
	jpCenter.add(tfMontoTotalArticulos, null);
	jpCenter.add(tfCantidadArticulos, null);
	jpCenter.add(tfMontoTotalTareas, null);
	jpCenter.add(tfTiempoTotalTareas, null);
	jpCenter.add(lblTotalTareas, null);
	panelData.setBorder(BorderPanel.getBorderPanel("Datos del servicio", Color.blue, Color.black));

	listPanel.removeControls();
	articulosListPanel.removeControls();
	tareasListPanel.removeControls();

	this.add(jpCenter, BorderLayout.CENTER);

	servicio = new Servicio();
	tfCantidadArticulos.setValue(1);
	setHeaderList();
	refresh();

	loadComboCategorias("''");
	cbCategorias.addItemListener(new ItemListener() {
	    public void itemStateChanged(ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED) {
		    //refresh();
		}
	    }
	});
    }

    private void loadComboCategorias(String _filtro) {
	cbCategorias.loadJCombo("taller.getAllCategorias", _filtro + ",0,0");
    }

    private void loadComboArticulos(String _filtro) {
	if (_filtro.length() >= 5) { //3 caracteres de filtro y 2 apóstrofos
	    cbArticulos.loadJCombo("taller.getAllExternalArticulosByFilter", _filtro);
	} else {
	    Advisor.messageBox("Debe introducir al menos 3 letras del Nombre (cualquier parte) o al menos los 3 (primeros) números del Código", "Error");
	}
    }

    private void loadComboTareas(String _filtro) {
	cbTareas.loadJCombo("taller.getAllTareas", _filtro + ",0,0");
    }
    
    private void setHeaderList() {
	Vector headerList = new Vector();
	headerList.addElement("* ID Servicio");
	headerList.addElement("Nombre");
	headerList.addElement("Precio");
	headerList.addElement("* ID Categoría");
	headerList.addElement("Categoría");
	listPanel.getTable().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
		    refreshDetail();
		} else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
		    loadData();
		}
	    }
	});
	listPanel.setParams("taller.getAllServicios", "''", headerList);

	Vector tareasHeaderList = new Vector();
	tareasHeaderList.addElement("* IdTarea");
	tareasHeaderList.addElement("Descripción");
	tareasHeaderList.addElement("* Codigo");
	tareasHeaderList.addElement("* Nombre");
	tareasHeaderList.addElement("Tiempo");
	tareasHeaderList.addElement("Precio");
	tareasListPanel.getTable().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
		    if (tareasDataRow.size() > 0 && dataRow.size() > 0) {
			if (Advisor.question("Quitar tarea de la lista", "¿Está seguro de remover la tarea?")) {
			    if (LibSQL.getBoolean("taller.removetareafromservicio", dataRow.elementAt(0) + "," + tareasDataRow.elementAt(0))) {
				tareasListPanel.refresh(servicio.getIdServicio());
			    }
			}
		    }
		}
	    }
	});
	tareasListPanel.setParams("taller.getAllTareasPorServicio", "''", tareasHeaderList);

	Vector articulosHeaderList = new Vector();
	articulosHeaderList.addElement("*");
	articulosHeaderList.addElement("Nombre");
	articulosHeaderList.addElement("Cant.");
	articulosHeaderList.addElement("Precio");
	articulosListPanel.getTable().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
		    if (articulosDataRow.size() > 0 && dataRow.size() > 0) {
			if (Advisor.question("Quitar artículo de la lista", "¿Está seguro de remover el artículo?")) {
			    if (LibSQL.getBoolean("taller.removearticulofromservicio", dataRow.elementAt(0) + "," + articulosDataRow.elementAt(0))) {
				articulosListPanel.refresh(servicio.getIdServicio());
			    }
			}
		    }
		}
	    }
	});
	articulosListPanel.setParams("taller.getAllArticulosPorServicio", "''", articulosHeaderList);
    }

    public void refresh() {
	listPanel.refresh(tfNombre.getStringForSQL() + "," + cbCategorias.getSelectedValue());
    }
    
    private void loadData(){
	if (dataRow.size() > 0) {
	    servicio = new Servicio(new Integer(dataRow.elementAt(0).toString()), dataRow.elementAt(1).toString(), new Double(dataRow.elementAt(2).toString()));
	    tfNombre.setValue(servicio.getName());
	    tfPrecio.setValue(servicio.getPrice());
	    cbCategorias.setSelectedID(dataRow.elementAt(3));
	    blank = false;
	    refreshDetail();
	}
    }

    private void refreshDetail() {
	int _idServicio = -1;
	if (dataRow.size() > 0) {
	    _idServicio = new Integer(dataRow.elementAt(0).toString());
	}
	tareasListPanel.refresh(_idServicio);
	articulosListPanel.refresh(_idServicio);
    }

    private void clearData() {
	servicio = new Servicio();
	tfNombre.setValue("");
	tfPrecio.setValue(0);
	blank = true;
    }
    
    private void btnCancel_actionPerformed(ActionEvent e) {
	clearData();
    }

    private void btnSave_actionPerformed(ActionEvent e) {
	saveData();
    }
    
    @Override
    public boolean saveData() {
	boolean _returns = false;
	boolean _continue = true;
	if (blank){
	    servicio = new Servicio();
	} else {
	    _continue = Advisor.question("Agregar/Modificar servicio", "¿Está seguro de modificar el servicio \"" + servicio.getName() + "\"?");
	}
	if (_continue) {
	    if (tfNombre.getString().length() == 0) {
	        Advisor.messageBox("Debe escribir un nombre para el servicio", "Agregar/Modificar servicio");
	    } else if (cbCategorias.getSelectedIndex() == -1) {
	        Advisor.messageBox("Debe seleccionar una categoría para el servicio", "Agregar/Modificar servicio");
	    } else {
		servicio.setName(tfNombre.getString());
	        servicio.setPrice(tfPrecio.getAmount());
	        servicio.setIdCategoria(new Integer(cbCategorias.getSelectedValue().toString()));
		if (servicio.saveData() != -1) {
		    clearData();
		    refresh();
		    _returns = true;
		} else {
		    Advisor.messageBox("Ha ocurrido un error al intentar grabar los datos", "Agregar/Modificar servicio");
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

    private void btnAddTarea_actionPerformed(ActionEvent e) {
	agregarTarea();
    }
    
    private void agregarTarea() {
	if (dataRow.size() > 0  && cbTareas.getSelectedIndex() != -1) {
	    if (LibSQL.getBoolean("taller.addTareaPorServicio", dataRow.elementAt(0) + "," + cbTareas.getSelectedValue())) {
		refreshDetail();
	    }
	}
    }

    private void btnAddArticulo_actionPerformed(ActionEvent e) {
	agregarArticulo();
    }
    
    private void agregarArticulo() {
	if (dataRow.size() > 0 && cbArticulos.getSelectedIndex() != -1) {
	    boolean _internal = cbArticulos.getSelectedValueRef().toString().equals("t");
	    if (LibSQL.getBoolean("taller.addArticuloPorServicio", dataRow.elementAt(0) + "," + cbArticulos.getSelectedValue() + "," + _internal + "," + tfCantidadArticulos.getAmount())) {
		refreshDetail();
	    }
	}
    }

}
