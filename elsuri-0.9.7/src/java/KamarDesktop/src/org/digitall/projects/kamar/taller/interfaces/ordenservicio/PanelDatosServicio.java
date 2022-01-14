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
 * PanelDatosServicio.java
 *
 * */
package org.digitall.projects.kamar.taller.interfaces.ordenservicio;

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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.JIntEntry;
import org.digitall.lib.components.JMoneyEntry;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicTitleLabel;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;
import org.digitall.projects.kamar.taller.classes.OrdenServicio;

public class PanelDatosServicio extends BasicPrimitivePanel {

    private Vector serviciosDataRow = new Vector();

    private GridPanel serviciosListPanel = new GridPanel(5000, new int[]{293, 63}, "Servicios", serviciosDataRow) {
	public void calculate() {
	    calculateTotal();
	}
    };
    private TFInput tfBuscarServicio = new TFInput(DataTypes.STRING, "Buscar", false);
    private CBInput cbServicios = new CBInput(0, "Servicio");
    private AssignButton btnAddServicio = new AssignButton();
    
    private Vector articulosDataRow = new Vector();
    private Vector tareasDataRow = new Vector();
    private GridPanel tareasListPanel = new GridPanel(5000, new int[]{365, 63, 140, 65}, "Tareas", tareasDataRow) {
	public void calculate() {
	    calculateTotal();
	}
    };
    private GridPanel articulosListPanel = new GridPanel(5000, new int[]{414, 58, 48, 56, 56}, "Artículos", articulosDataRow) {
	public void calculate() {
	    calculateTotal();
	}
    };

    private BasicDialog parentContainer;

    private TFInput tfBuscarArticulo = new TFInput(DataTypes.STRING, "Buscar", false);
    private CBInput cbArticulos = new CBInput(0, "Artículo");
    private TFInput tfCantidadArticulos = new TFInput(DataTypes.INTEGER, "Cant.", false);
    private AssignButton btnAddArticulo = new AssignButton(true);

    private TFInput tfBuscarTarea = new TFInput(DataTypes.STRING, "Buscar", false);
    private CBInput cbTareas = new CBInput(0, "Tarea");
    private AssignButton btnAddTarea = new AssignButton(true);
    private CBInput cbTiposMecanica = new CBInput(0, "Tipo de Mecánica");
    private TFInput tfTiempoTarea = new TFInput(DataTypes.INTEGER, "Minutos", false);
    
    private JMoneyEntry tfMontoTotalArticulos = new JMoneyEntry();
    private BasicTitleLabel lblTotalArticulos = new BasicTitleLabel();
    private BasicTitleLabel lblTotalTareas = new BasicTitleLabel();
    private JIntEntry tfTiempoTotalTareas = new JIntEntry();
    private JMoneyEntry tfMontoTotalTareas = new JMoneyEntry();

    private JMoneyEntry tfMontoTotalOrdenServicio = new JMoneyEntry();

    private BasicPanel jpCenter = new BasicPanel();
	
    private OrdenServicio ordenServicio;
    private boolean modificable = true;

    private BasicButton btnVerComprobantesVinculados = new BasicButton("Comprobantes Vinculados");

    public PanelDatosServicio() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(723, 513));
	jpCenter.setLayout(null);
	jpCenter.setBorder(BorderPanel.getBorderPanel("Agregar/Modificar servicios"));
	serviciosListPanel.setBounds(new Rectangle(280, 15, 430, 85));
	tareasListPanel.setBounds(new Rectangle(10, 185, 700, 120));
	articulosListPanel.setBounds(new Rectangle(10, 350, 700, 120));
	lblTotalArticulos.setBounds(new Rectangle(600, 305, 110, 20));
	lblTotalArticulos.setText("Monto estimado:");
	lblTotalArticulos.setHorizontalAlignment(SwingConstants.RIGHT);
	lblTotalArticulos.setFont(new Font("Arial", 1, 13));
	lblTotalTareas.setBounds(new Rectangle(545, 140, 165, 20));
	lblTotalTareas.setText("Tiempo/Monto estimado:");
	lblTotalTareas.setHorizontalAlignment(SwingConstants.RIGHT);
	lblTotalTareas.setFont(new Font("Arial", 1, 13));
	tfMontoTotalArticulos.setBounds(new Rectangle(615, 325, 95, 20));
	tfMontoTotalArticulos.setEditable(false);
	tfMontoTotalArticulos.setBackground(Color.yellow);
	tfMontoTotalArticulos.setFont(new Font("Dialog", 1, 13));

	tfMontoTotalTareas.setBounds(new Rectangle(615, 325, 95, 20));
	tfMontoTotalTareas.setBounds(new Rectangle(615, 160, 95, 20));
	tfMontoTotalTareas.setEditable(false);
	tfMontoTotalTareas.setBackground(Color.yellow);
	tfMontoTotalTareas.setFont(new Font("Dialog", 1, 13));

	tfTiempoTotalTareas.setBounds(new Rectangle(545, 160, 65, 20));
	tfTiempoTotalTareas.setEditable(false);
	tfTiempoTotalTareas.setFont(new Font("Dialog", 1, 13));
	tfTiempoTotalTareas.setBackgroundColor(new Color(255, 156, 49));
	tfTiempoTotalTareas.setBackground(Color.yellow);

	tfMontoTotalOrdenServicio.setBounds(new Rectangle(615, 325, 95, 20));
	tfMontoTotalOrdenServicio.setBounds(new Rectangle(140, 20, 130, 35));
	tfMontoTotalOrdenServicio.setEditable(false);
	tfMontoTotalOrdenServicio.setBackground(Color.RED);
	tfMontoTotalOrdenServicio.setForeground(Color.WHITE);
	tfMontoTotalOrdenServicio.setFont(new Font("Dialog", Font.BOLD, 18));

	tfBuscarServicio.setBounds(new Rectangle(10, 20, 85, 35));
	tfBuscarServicio.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			loadComboServicios(tfBuscarServicio.getStringForSQL());
		    }
		}

	    }
	);

	tfTiempoTarea.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			agregarTarea();
		    }
		}

	    }
	);

	tfCantidadArticulos.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			agregarArticulo();
		    }
		}

	    }
	);
	cbServicios.setBounds(new Rectangle(10, 60, 225, 35));
	btnAddServicio.setBounds(new Rectangle(240, 70, 30, 25));

	btnAddServicio.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnAddServicio_actionPerformed(e);
		}
	    });
	tfBuscarArticulo.setBounds(new Rectangle(10, 310, 85, 35));
	tfCantidadArticulos.setBounds(new Rectangle(525, 310, 45, 35));
	tfBuscarArticulo.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			loadComboArticulos(tfBuscarArticulo.getStringForSQL());
		    }
		}

	    }
	);
	cbArticulos.setBounds(new Rectangle(100, 310, 420, 35));
	btnAddArticulo.setBounds(new Rectangle(570, 320, 30, 25));
	btnAddArticulo.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnAddArticulo_actionPerformed(e);
		}
	    });
	tfBuscarTarea.setBounds(new Rectangle(10, 105, 85, 35));
	tfBuscarTarea.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			loadComboTareas(tfBuscarTarea.getStringForSQL());
		    }
		}

	    }
	);
	cbTareas.setBounds(new Rectangle(100, 105, 610, 35));
	cbTiposMecanica.setBounds(new Rectangle(100, 145, 270, 35));
	tfTiempoTarea.setBounds(new Rectangle(375, 145, 65, 35));
	btnAddTarea.setBounds(new Rectangle(450, 155, 30, 25));
	btnAddTarea.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnAddTarea_actionPerformed(e);
		}
	    });

	jpCenter.add(serviciosListPanel, null);
	jpCenter.add(tareasListPanel, null);
	jpCenter.add(articulosListPanel, null);
	jpCenter.add(tfBuscarServicio, null);
	jpCenter.add(cbServicios, null);
	jpCenter.add(btnAddServicio, null);
	jpCenter.add(tfBuscarTarea, null);
	jpCenter.add(cbTareas, null);
	jpCenter.add(cbTiposMecanica, null);
	jpCenter.add(tfTiempoTarea, null);
	jpCenter.add(btnAddTarea, null);
	jpCenter.add(tfBuscarArticulo, null);
	jpCenter.add(cbArticulos, null);
	jpCenter.add(btnAddArticulo, null);
	jpCenter.add(lblTotalArticulos, null);
	jpCenter.add(tfMontoTotalOrdenServicio, null);
	jpCenter.add(tfMontoTotalTareas, null);
	jpCenter.add(tfMontoTotalArticulos, null);
	jpCenter.add(lblTotalTareas, null);
	jpCenter.add(tfTiempoTotalTareas, null);
	jpCenter.add(tfCantidadArticulos, null);
	jpCenter.add(btnVerComprobantesVinculados, null);
	serviciosListPanel.removeControls();
	articulosListPanel.removeControls();
	tareasListPanel.removeControls();

	this.add(jpCenter, BorderLayout.CENTER);

	ordenServicio = new OrdenServicio();
	loadComboServicios("''");
	loadComboTiposMecanica("''");
	tfCantidadArticulos.setValue(1);
	cbTareas.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent evt) {
		    if (evt.getStateChange() == ItemEvent.SELECTED) {
			loadFlatRate();
		    }
		}
	    });

	setHeaderList();

	btnVerComprobantesVinculados.setOpaque(true);
	btnVerComprobantesVinculados.setFont(new Font("Dialog", 1, 10));
	btnVerComprobantesVinculados.setForeground(Color.black);
	btnVerComprobantesVinculados.setBackground(new Color(255, 132, 0));
	btnVerComprobantesVinculados.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	btnVerComprobantesVinculados.setBounds(new Rectangle(545, 475, 165, 25));
	btnVerComprobantesVinculados.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnVerComprobantes_actionPerformed(e);
		}
	    });

    }

    private void btnVerComprobantes_actionPerformed(ActionEvent e) {
	showComprobantesVinculados();
    }
    
    private void loadFlatRate() {
	if (cbTareas.getSelectedIndex() != -1) {
	    String tareasData[] = cbTareas.getSelectedValueRef().toString().split("#");
	    cbTiposMecanica.setSelectedID(tareasData[0]);
	    tfTiempoTarea.setValue(tareasData[1]);
	}
    }

    private void loadComboServicios(String _filtro) {
	cbServicios.loadJCombo("taller.getAllServiciosPorTipoVehiculo", _filtro + "," + ordenServicio.getVehiculo().getIdTipoVehiculo() + ",0,0");
    }

    private void loadComboTiposMecanica(String _filtro) {
	cbTiposMecanica.loadJCombo("taller.getAllTiposMecanica", _filtro + ",0,0");
    }

    private void loadComboArticulos(String _filtro) {
	if (_filtro.length() >= 5) { //3 caracteres de filtro y 2 apóstrofos
	    cbArticulos.loadJCombo("taller.getAllExternalArticulosByFilter", _filtro);
	} else {
	    Advisor.messageBox("Debe introducir al menos 3 letras del Nombre (cualquier parte) o al menos los 3 (primeros) números del Código", "Error");
	}
    }

    private void loadComboTareas(String _filtro) {
	cbTareas.loadJCombo("taller.getAllTareasPorTipoVehiculo", _filtro + "," + ordenServicio.getVehiculo().getIdTipoVehiculo() +",0,0");
	loadFlatRate();
	//cbTareas.loadJCombo("taller.getAllTareas", _filtro + "," + ordenServicio.getVehiculo().getIdTipoVehiculo() +",0,0");
    }
    
    private void setHeaderList() {
	Vector serviciosHeaderList = new Vector();
	serviciosHeaderList.addElement("*");
	serviciosHeaderList.addElement("Nombre");
	serviciosHeaderList.addElement("Precio");
	serviciosListPanel.getTable().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
		    if (serviciosDataRow.size() > 0 && ordenServicio.getIdOrdenServicio() != -1 && modificable) {
			if (Advisor.question("Remover servicio (y relacionados) de la lista", "¿Está seguro de remover el servicio?")) {
			    if (LibSQL.getBoolean("taller.removeserviciofromordenservicio", ordenServicio.getIdOrdenServicio() + "," + serviciosDataRow.elementAt(0))) {
			        serviciosListPanel.refresh(ordenServicio.getIdOrdenServicio());
			        articulosListPanel.refresh(ordenServicio.getIdOrdenServicio());
			        tareasListPanel.refresh(ordenServicio.getIdOrdenServicio());
			    } else {
				Advisor.messageBox("Ha ocurrido un error al intentar remover el servicio de la Orden", "Remover servicio");
			    }
			}
		    }
		}
	    }
	});
	serviciosListPanel.setParams("taller.getAllServiciosPorOrdenServicio", "''", serviciosHeaderList);

	Vector tareasHeaderList = new Vector();
	tareasHeaderList.addElement("*");
	tareasHeaderList.addElement("Tarea");
	tareasHeaderList.addElement("* Código");
	tareasHeaderList.addElement("* Nombre");
	tareasHeaderList.addElement("Tiempo");
	tareasHeaderList.addElement("* ID Tipo Mec.");
	tareasHeaderList.addElement("Tipo Mec.");
	tareasHeaderList.addElement("Total");
	tareasListPanel.getTable().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
		    if (tareasDataRow.size() > 0 && ordenServicio.getIdOrdenServicio() != -1 && modificable) {
			if (Advisor.question("Remover tarea de la lista", "¿Está seguro de remover la tarea?")) {
			    if (LibSQL.getBoolean("taller.removetareafromordenservicio", ordenServicio.getIdOrdenServicio() + "," + tareasDataRow.elementAt(0))) {
				tareasListPanel.refresh(ordenServicio.getIdOrdenServicio());
			    } else {
			        Advisor.messageBox("Ha ocurrido un error al intentar remover la tarea de la Orden", "Remover tarea");
			    }
			}
		    }
		} else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
		    loadDataTareas();
		}
	    }
	});
	tareasListPanel.setParams("taller.getAllTareasPorOrdenServicio", "''", tareasHeaderList);

	Vector articulosHeaderList = new Vector();
	articulosHeaderList.addElement("*");
	articulosHeaderList.addElement("*");
	articulosHeaderList.addElement("Nombre");
	articulosHeaderList.addElement("P/U");
	articulosHeaderList.addElement("Cant.");
	articulosHeaderList.addElement("Vinc.");
	articulosHeaderList.addElement("Total");
	articulosListPanel.getTable().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
		    if (articulosDataRow.size() > 0 && ordenServicio.getIdOrdenServicio() != -1 && modificable) {
			if (Advisor.question("Remover artículo de la lista", "¿Está seguro de remover el artículo?")) {
			    if (LibSQL.getBoolean("taller.removearticulofromordenservicio", articulosDataRow.elementAt(0))) {
				articulosListPanel.refresh(ordenServicio.getIdOrdenServicio());
			    } else {
			        Advisor.messageBox("Ha ocurrido un error al intentar remover el artículo de la Orden", "Remover artículo");
			    }
			}
		    }
		}
	    }
	});
	articulosListPanel.setParams("taller.getAllArticulosPorOrdenServicio", "''", articulosHeaderList);
    }

    private void loadDataTareas() {
	if (tareasDataRow.size() > 0) {
	    loadComboTareas("'" + tareasDataRow.elementAt(3) + "'");
	    cbTareas.setSelectedID(tareasDataRow.elementAt(0));
	    tfTiempoTarea.setValue(tareasDataRow.elementAt(4));
	    cbTiposMecanica.setSelectedID(tareasDataRow.elementAt(5));
	}
    }

    private void refreshDetail(int _idOrdenServicio) {
	tareasListPanel.refresh(_idOrdenServicio);
	articulosListPanel.refresh(_idOrdenServicio);
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

    private void btnAddServicio_actionPerformed(ActionEvent e) {
	agregarServicio();
    }
    
    private void agregarServicio() {
	if (ordenServicio.getIdOrdenServicio() != -1 && cbServicios.getSelectedIndex() != -1) {
	    if (LibSQL.getBoolean("taller.addServicioPorOrdenServicio", ordenServicio.getIdOrdenServicio() + "," + cbServicios.getSelectedValue())) {
		serviciosListPanel.refresh(ordenServicio.getIdOrdenServicio());
	        articulosListPanel.refresh(ordenServicio.getIdOrdenServicio());
	        tareasListPanel.refresh(ordenServicio.getIdOrdenServicio());
	    }
	}
    }

    private void btnAddArticulo_actionPerformed(ActionEvent e) {
	agregarArticulo();
    }
    
    private void agregarArticulo() {
	if (ordenServicio.getIdOrdenServicio() != -1 && cbArticulos.getSelectedIndex() != -1) {
	    boolean _internal = cbArticulos.getSelectedValueRef().toString().equals("t");
	    //System.out.println(cbArticulos.getSelectedValue() + "," + internal);
	    if (LibSQL.getBoolean("taller.addArticuloPorOrdenServicio", ordenServicio.getIdOrdenServicio() + "," + cbArticulos.getSelectedValue() + "," + _internal + "," + tfCantidadArticulos.getAmount())) {
		articulosListPanel.refresh(ordenServicio.getIdOrdenServicio());
	    }
	}
    }

    private void btnAddTarea_actionPerformed(ActionEvent e) {
	agregarTarea();
    };
    
    private void agregarTarea() {
	if (ordenServicio.getIdOrdenServicio() != -1 && cbTareas.getSelectedIndex() != -1 && cbTiposMecanica.getSelectedIndex() != -1) {
	    if (LibSQL.getBoolean("taller.addTareaPorOrdenServicio", ordenServicio.getIdOrdenServicio() + "," + cbTareas.getSelectedValue() + "," + tfTiempoTarea.getInteger() + "," + cbTiposMecanica.getSelectedValue())) {
		tareasListPanel.refresh(ordenServicio.getIdOrdenServicio());
	    }
	}
    }

    public void setOrdenServicio(OrdenServicio _ordenServicio) {
	ordenServicio = _ordenServicio;
	if (ordenServicio.getIdOrdenServicio() != -1) {
	    serviciosListPanel.refresh(ordenServicio.getIdOrdenServicio());
	    articulosListPanel.refresh(ordenServicio.getIdOrdenServicio());
	    tareasListPanel.refresh(ordenServicio.getIdOrdenServicio());
	}
	switch (ordenServicio.getIdEstado()) {
	    case 2:
	    case 3:
	    case 4:
		modificable = false;
		break;
	}
	updateControls();
    }
    
    private void updateControls() {
	if (!modificable) {
		articulosListPanel.setEnabled(false);
		tfBuscarServicio.setEnabled(false);
		cbServicios.setEnabled(false);
		btnAddServicio.setEnabled(false);
		tfBuscarTarea.setEnabled(false);
		cbTareas.setEnabled(false);
		cbTiposMecanica.setEnabled(false);
		tfTiempoTarea.setEnabled(false);
		btnAddTarea.setEnabled(false);
		tfBuscarArticulo.setEnabled(false);
		cbArticulos.setEnabled(false);
		btnAddArticulo.setEnabled(false);
		tfCantidadArticulos.setEnabled(false);
	}
    }
    
    private void calculateTotal() {
	try {
	    double _total = LibSQL.getDouble("taller.getmontoestimadoporordenservicio", ordenServicio.getIdOrdenServicio());
	    
	    tfTiempoTotalTareas.setValue(tareasListPanel.getSum(2));
	    tfMontoTotalTareas.setValue(tareasListPanel.getSum(4));
	    tfMontoTotalArticulos.setValue(articulosListPanel.getSum(5));
	    //tfMontoTotalOrdenServicio.setValue(tfMontoTotalTareas.getAmount() + tfMontoTotalArticulos.getAmount() + serviciosListPanel.getSum(2));*/
	    tfMontoTotalOrdenServicio.setValue(_total);
	    getTabContainer().getParentInternalFrame().setInfo("Orden de Servicio Nº " + ordenServicio.getNumero() + ", Monto total estimado: " + tfMontoTotalOrdenServicio.getText());
	} catch (Exception e) {
	    // TODO: Add catch code
	    //e.printStackTrace();
	}
    }
    
    private void showComprobantesVinculados() {
	if (ordenServicio.getIdOrdenServicio() != -1) {
	    GridPanel comprobantesListPanel = new GridPanel(5000, new int[] { 195, 83, 100, 55 }, "Comprobantes vinculados", new Vector()) {
		public void finishLoading() {
		    requestFocusInWindow();
		}
	    };
	    comprobantesListPanel.setPreferredSize(new Dimension(500,150));
	    Vector comprobantesVinculadosHeaderList = new Vector();
	    comprobantesVinculadosHeaderList.addElement("* ID Comprobante");
	    comprobantesVinculadosHeaderList.addElement("* ID Proveedor");
	    comprobantesVinculadosHeaderList.addElement("Proveedor");
	    comprobantesVinculadosHeaderList.addElement("Número");
	    comprobantesVinculadosHeaderList.addElement("* Fecha");
	    comprobantesVinculadosHeaderList.addElement("* $ Total");
	    comprobantesVinculadosHeaderList.addElement("* ID Artículo");
	    comprobantesVinculadosHeaderList.addElement("Artículo");
	    comprobantesVinculadosHeaderList.addElement("$ U.");
	    comprobantesListPanel.setParams("taller.getAllComprobantesPorOrdenServicio", "-1", comprobantesVinculadosHeaderList);
	    comprobantesListPanel.removeControls();
	    comprobantesListPanel.refresh(ordenServicio.getIdOrdenServicio());
	    JOptionPane.showInternalMessageDialog(this, comprobantesListPanel, "Comprobantes Vinculados", JOptionPane.OK_OPTION, new ImageIcon(""));
	}
    }
}
