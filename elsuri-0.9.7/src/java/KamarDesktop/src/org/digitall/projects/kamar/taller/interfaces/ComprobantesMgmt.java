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
 * ComprobantesMgmt.java
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

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;
import org.digitall.projects.kamar.taller.classes.Comprobante;

public class ComprobantesMgmt extends BasicPrimitivePanel {

    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, new int[]{278, 52, 52, 63, 52, 52}, "Artículos", dataRow) {
	@Override
	public void calculate() {
	    tfTotal.setValue(this.getSum(4));
	}
    };
    private TFInput tfBuscarProveedor = new TFInput(DataTypes.STRING, "Buscar", false);
    private CBInput cbProveedores = new CBInput(0, "Proveedor");
    private TFInput tfNroComprobante = new TFInput(DataTypes.STRING, "Nro. Comprobante", false);
    private TFInput tfFecha = new TFInput(DataTypes.SIMPLEDATE, "Fecha", false);
    private TFInput tfTotal = new TFInput(DataTypes.MONEY, "$ Total", false);
    private TFInput tfBuscarArticulo = new TFInput(DataTypes.STRING, "Buscar", false);
    private CBInput cbArticulos = new CBInput(0, "Artículo");
    private TFInput tfCantidad = new TFInput(DataTypes.DOUBLE, "Cantidad", false);
    private TFInput tfPrecioUnitario = new TFInput(DataTypes.MONEY, "$ Unitario", false);
    private TFInput tfBuscarOrden = new TFInput(DataTypes.STRING, "Buscar", false);
    private CBInput cbOrdenServicio = new CBInput(0, "Orden de Servicio");
    private TFInput tfCantidadVincular = new TFInput(DataTypes.INTEGER, "Cantidad", false);
    private AcceptButton btnVincular = new AcceptButton();

    private SaveDataButton btnSave = new SaveDataButton();
    private AssignButton btnAssign = new AssignButton(true);
    private BasicDialog parentContainer;
    private Comprobante comprobante;
    private int idDetalleComprobante = -1;

    private boolean modificable = true;

    private BasicPanel jpCenter = new BasicPanel();
    
    public ComprobantesMgmt() {
	this(new Comprobante());
    }

    public ComprobantesMgmt(Comprobante _comprobante) {
	try {
	    comprobante = _comprobante;
	    if (comprobante.getIdComprobante() != -1) {
		comprobante.loadData(comprobante.getIdComprobante());
		modificable = false;
	    }
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	jpCenter.setLayout(null);
	tfTotal.setEditable(false);
	jpCenter.setBorder(BorderPanel.getBorderPanel("Agregar/Modificar comprobantes"));
	this.setSize(new Dimension(649, 359));
	listPanel.setBounds(new Rectangle(10, 155, 630, 190));
	tfBuscarProveedor.setBounds(new Rectangle(10, 20, 85, 35));
	tfBuscarOrden.setBounds(new Rectangle(10, 110, 85, 35));
	cbProveedores.setBounds(new Rectangle(100, 20, 215, 35));
	tfBuscarProveedor.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			loadComboProveedores(tfBuscarProveedor.getStringForSQL());
		    }
		}

	    });
	tfBuscarOrden.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			loadComboOrdenes(tfBuscarOrden.getStringForSQL());
		    }
		}

	    });
	tfNroComprobante.setBounds(new Rectangle(320, 20, 110, 35));
	tfFecha.setBounds(new Rectangle(435, 20, 85, 35));
	tfTotal.setBounds(new Rectangle(525, 20, 75, 35));
	tfPrecioUnitario.setBounds(new Rectangle(525, 25, 75, 35));
	tfBuscarArticulo.setBounds(new Rectangle(10, 65, 85, 35));
	tfBuscarArticulo.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			loadComboArticulos(tfBuscarArticulo.getStringForSQL());
		    }
		}

	    }
	);
	cbArticulos.setBounds(new Rectangle(100, 65, 350, 35));
	tfCantidad.setBounds(new Rectangle(455, 65, 65, 35));
	tfPrecioUnitario.setBounds(new Rectangle(525, 65, 75, 35));
	tfPrecioUnitario.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			saveDetail();
		    }
		}

	    }
	);
	cbOrdenServicio.setBounds(new Rectangle(100, 110, 420, 35));
	btnSave.setBounds(new Rectangle(610, 30, 30, 25));
	tfCantidadVincular.setBounds(new Rectangle(525, 110, 65, 35));
	tfCantidadVincular.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			vincularArticulo();
		    }
		}

	    }
	);
	btnVincular.setBounds(new Rectangle(605, 120, 30, 25));
	btnVincular.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnVincular_actionPerformed(e);
		}
	    });
	btnSave.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnSaveHeader_actionPerformed(e);
			      }

			  }
	);
	btnAssign.setBounds(new Rectangle(610, 75, 30, 25));
	btnAssign.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAssign_actionPerformed(e);
				 }

			     }
	);

	jpCenter.add(tfBuscarProveedor, null);
	jpCenter.add(tfBuscarOrden, null);
	jpCenter.add(cbProveedores, null);
	jpCenter.add(tfNroComprobante, null);
	jpCenter.add(tfFecha, null);
	jpCenter.add(tfTotal, null);
	jpCenter.add(tfBuscarArticulo, null);
	jpCenter.add(cbArticulos, null);
	jpCenter.add(tfCantidad, null);
	jpCenter.add(cbOrdenServicio, null);
	jpCenter.add(tfPrecioUnitario, null);
	jpCenter.add(btnSave, null);
	jpCenter.add(tfCantidadVincular, null);
	jpCenter.add(btnAssign, null);
	jpCenter.add(btnVincular, null);
	jpCenter.add(listPanel, null);
	this.add(jpCenter, BorderLayout.CENTER);
	setHeaderList();
	refresh();
	updateControls();
    }

    private void loadComboOrdenes(String _filtro) {
	cbOrdenServicio.loadJCombo("taller.getAllOrdenesParaVinculacion", _filtro);
	modificable = false;
	loadData();
   }

    private void loadComboProveedores(String _filtro) {
	cbProveedores.loadJCombo("taller.getAllProveedores", _filtro + ",0,0");
    }

    private void loadComboArticulos(String _filtro) {
	cbArticulos.loadJCombo("taller.getAllArticulosByFilter", _filtro + ",0,0");
    }

    private void setHeaderList() {
	Vector headerList = new Vector();
	headerList.addElement("* ID Detalle Comprobante");
	headerList.addElement("* ID Comprobante");
	headerList.addElement("* ID Artículo");
	headerList.addElement("Artículo");
	headerList.addElement("$ U.");
	headerList.addElement("Cant.");
	headerList.addElement("$ Total");
	headerList.addElement("Vinc.");
	headerList.addElement("Rest.");
	listPanel.getTable().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
		    loadData();
		} else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
		    if (dataRow.size() > 0 && modificable) {
		        if (LibSQL.getBoolean("taller.canremovedetallecomprobante", dataRow.elementAt(0))) {
			    if (Advisor.question("Quitar artículo del comprobante", "¿Está seguro de remover el artículo?")) {
				if (LibSQL.getBoolean("taller.removedetallecomprobante", dataRow.elementAt(0))) {
				    refresh();
				} else {
				    Advisor.messageBox("Ha ocurrido un error al intentar remover el artículo", "Quitar artículo del comprobante");
				}
			    }
			} else {
			    Advisor.messageBox("El artículo ya se ha vinculado a una Orden de Servicio\nNo puede eliminarse del comprobante<", "Quitar artículo del comprobante");
			}
		    }
		}
	    }
	});
	listPanel.setParams("taller.getDetalleComprobante", "-1", headerList);
    }

    public void refresh() {
	listPanel.refresh(comprobante.getIdComprobante());
	clearData();
    }
    
    private void loadData(){
	if (dataRow.size() > 0) {
	    //idDetalleComprobante = new Integer(dataRow.elementAt(0).toString());
	    //blank = false;
	    cbOrdenServicio.setEnabled(true);
	    tfBuscarOrden.setEnabled(true);
	    tfCantidadVincular.setEnabled(true);
	    btnVincular.setEnabled(true);
	}
    }

    private void clearData() {
	idDetalleComprobante = -1;
	tfCantidad.setValue(0.0);
	tfPrecioUnitario.setValue(0.0);
	cbOrdenServicio.setEnabled(false);
	tfBuscarOrden.setEnabled(false);
	tfCantidadVincular.setEnabled(false);
	btnVincular.setEnabled(false);
    }
    
    private void btnAssign_actionPerformed(ActionEvent e) {
	saveDetail();
    }

    private void btnSaveHeader_actionPerformed(ActionEvent e) {
	saveData();
    };
    
    @Override
    public boolean saveData() {
	boolean _returns = false;
	if (cbProveedores.getSelectedIndex() != -1) {
	    if (Advisor.question("Agregar Comprobante", "¿Está seguro?")) {
		comprobante = new Comprobante();
		comprobante.setIdProveedor(new Integer(cbProveedores.getSelectedValue().toString()));
		comprobante.setNumero(tfNroComprobante.getString());
		comprobante.setFecha(tfFecha.getDate());
		if (comprobante.saveData() != -1) {
		    _returns = true;
		    updateControls();
		}
	    }
	} else {
	    Advisor.messageBox("Debe seleccionar un proveedor", "Agregar comprobante");
	}
	return _returns;
    }

    private void updateControls() {
	tfBuscarOrden.setEnabled(false);
	cbOrdenServicio.setEnabled(false);
	tfCantidadVincular.setEnabled(false);
	btnVincular.setEnabled(false);
	if (comprobante.getIdComprobante() != -1) {
	    tfBuscarProveedor.setEnabled(false);
	    cbProveedores.setEnabled(false);
	    tfNroComprobante.setEnabled(false);
	    tfFecha.setEnabled(false);
	    btnSave.setEnabled(false);
	    tfBuscarArticulo.setEnabled(modificable);
	    cbArticulos.setEnabled(modificable);
	    tfCantidad.setEnabled(modificable);
	    tfPrecioUnitario.setEnabled(modificable);
	    btnAssign.setEnabled(modificable);
	    tfNroComprobante.setValue(comprobante.getNumero());
	    tfFecha.setText(comprobante.getFecha());
	    loadComboProveedores("'" + comprobante.getNombreProveedor() + "'");
	} else {
	    tfBuscarArticulo.setEnabled(false);
	    cbArticulos.setEnabled(false);
	    tfCantidad.setEnabled(false);
	    tfPrecioUnitario.setEnabled(false);
	    btnAssign.setEnabled(false);
	}
    }

    private boolean saveDetail() {
	boolean _returns = false;
	if (cbArticulos.getSelectedIndex() == -1) {
	    Advisor.messageBox("Debe seleccionar un artículo", "Agregar detalle al comprobante");
	} else if (tfCantidad.getAmount() <= 0) {
	    Advisor.messageBox("La cantidad debe ser mayor que cero", "Agregar detalle al comprobante");
	} else if (tfPrecioUnitario.getAmount() <= 0) {
	    Advisor.messageBox("El precio debe ser mayor que cero", "Agregar detalle al comprobante");
	} else {
	    if (LibSQL.getInt("taller.setDetalleComprobante", idDetalleComprobante + "," + comprobante.getIdComprobante() + "," + cbArticulos.getSelectedValue() + "," + tfCantidad.getAmount() + "," + tfPrecioUnitario.getAmount() + "," + cbOrdenServicio.getSelectedValue() ) != -1) {
		clearData();
		refresh();
	        _returns = true;
	    }
	}
	return _returns;
    }

    public void setParentContainer(BasicDialog parentContainer) {
	this.parentContainer = parentContainer;
    }

    private void btnVincular_actionPerformed(ActionEvent e) {
	vincularArticulo();
    }
    
    private void vincularArticulo() {
	if (cbOrdenServicio.getSelectedIndex() == -1) {
	    Advisor.messageBox("Debe seleccionar una Orden de Servicio", "Vincular artículo");
	} else if (/*tfCantidadVincular.getAmount() <= 0 || */tfCantidadVincular.getAmount() > new Double(dataRow.elementAt(8).toString())) {
	    Advisor.messageBox("No se pueden vincular más de  " + dataRow.elementAt(8) + " artículos (Rest.)", "Vincular artículo");
	} else {
	    if (Advisor.question("Vincular artículo", "¿Está seguro de vincular el artículo " + dataRow.elementAt(2) + " a la Orden " + cbOrdenServicio.getSelectedItem() + "?")) {
	        if (LibSQL.getBoolean("taller.vinculararticuloconorden", dataRow.elementAt(0) + "," + cbOrdenServicio.getSelectedValue() + "," + tfCantidadVincular.getAmount())) {
	            refresh();
	        } else {
	            Advisor.messageBox("Ha ocurrido un error al intentar vincular el artículo", "Vincular artículo");
	        }
	    }
	}
    }
}
