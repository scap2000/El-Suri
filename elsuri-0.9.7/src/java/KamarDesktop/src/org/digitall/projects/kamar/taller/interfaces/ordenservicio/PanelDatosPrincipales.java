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
 * PanelDatosPrincipales.java
 *
 * */
package org.digitall.projects.kamar.taller.interfaces.ordenservicio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;

import javax.swing.SwingUtilities;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.inputpanels.TAInputPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;
import org.digitall.projects.kamar.taller.classes.Articulo;
import org.digitall.projects.kamar.taller.classes.OrdenServicio;
import org.digitall.projects.kamar.taller.classes.Vehiculo;

public class PanelDatosPrincipales extends BasicPrimitivePanel {

    private TFInput tfNumero = new TFInput(DataTypes.STRING,"Número",true);
    private TFInput tfNombre = new TFInput(DataTypes.STRING,"Nombre y Apellido",true);
    private TFInput tfDocumento = new TFInput(DataTypes.STRING,"Documento",true);
    private TFInput tfDomicilio = new TFInput(DataTypes.STRING,"Domicilio",true);
    private TFInput tfTelefono = new TFInput(DataTypes.STRING,"Teléfono",true);
    private CBInput cbMarca = new CBInput(0, "Marca");
    private TFInput tfAnio = new TFInput(DataTypes.STRING,"Año",false);
    private CBInput cbTipoVehiculo = new CBInput(0,"Modelo",false);
    private TFInput tfNroCuadro = new TFInput(DataTypes.STRING,"Nº Cuadro",false);
    private TAInputPanel tfObservaciones = new TAInputPanel(DataTypes.STRING, "Observations", false);
    private TFInput tfBuscarCliente = new TFInput(DataTypes.STRING, "Buscar", false);
    private CBInput cbClientes = new CBInput(0, "Cliente");
    private BasicPanel jpCenter = new BasicPanel();

    private BasicPanel jpBusqueda = new BasicPanel();
    private TFInput tfBuscarVehiculo = new TFInput(DataTypes.STRING, "Buscar por VIN", false);
    private CBInput cbVehiculos = new CBInput(0, "Vehículos encontrados");

    private TFInput tfBuscarTipoVehiculo = new TFInput(DataTypes.STRING, "Buscar", false);

    private AcceptButton btnAceptarCliente= new AcceptButton();
    private AcceptButton btnAceptarVehiculo = new AcceptButton();
    private BasicRadioButton rbInterna = new BasicRadioButton("Interna", false);
    private BasicRadioButton rbExterna = new BasicRadioButton("Externa", false);
    private ButtonGroup grpInterna = new ButtonGroup();

    private OrdenServicio ordenServicio;
    
    public PanelDatosPrincipales() {
	super();
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(546, 458));
	//btnAceptarVehiculo.setEnabled(false);
	grpInterna.add(rbInterna);
	grpInterna.add(rbExterna);
	rbInterna.setSelected(true);
	jpCenter.setLayout(null);
	jpCenter.setBorder(BorderPanel.getBorderPanel("Datos principales"));
	tfNumero.setBounds(new Rectangle(20, 25, 125, 35));
	tfTelefono.setBounds(new Rectangle(315, 150, 160, 35));
	tfNombre.setEditable(false);
	tfNombre.setBounds(new Rectangle(20, 110, 350, 35));
	tfDocumento.setEditable(false);
	tfDocumento.setBounds(new Rectangle(375, 110, 160, 35));
	tfDomicilio.setEditable(false);
	tfDomicilio.setBounds(new Rectangle(20, 150, 290, 35));
	cbMarca.setBounds(new Rectangle(290, 260, 185, 35));
	tfAnio.setBounds(new Rectangle(395, 300, 80, 35));
	cbTipoVehiculo.setBounds(new Rectangle(95, 300, 295, 35));
	tfNroCuadro.setBounds(new Rectangle(20, 260, 265, 35));
	tfObservaciones.setBounds(new Rectangle(20, 340, 455, 75));
	tfBuscarCliente.setBounds(new Rectangle(20, 65, 90, 35));
	jpBusqueda.setBounds(new Rectangle(20, 190, 515, 65));
	tfBuscarVehiculo.setBounds(new Rectangle(10, 20, 120, 35));
	tfBuscarVehiculo.setOpaque(false);
	cbClientes.setBounds(new Rectangle(115, 65, 420, 35));
	tfBuscarTipoVehiculo.setBounds(new Rectangle(20, 300, 70, 35));
	tfBuscarTipoVehiculo.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			loadComboTiposVehiculo(tfBuscarTipoVehiculo.getStringForSQL());
		    }
		}

	    });
	cbVehiculos.setBounds(new Rectangle(135, 20, 370, 35));
	cbVehiculos.setOpaque(false);
	jpCenter.add(tfNumero, null);
	jpCenter.add(tfBuscarCliente, null);
	jpCenter.add(cbClientes, null);
	jpCenter.add(jpBusqueda, null);
	jpCenter.add(tfObservaciones, null);
	jpCenter.add(tfDomicilio, null);
	jpCenter.add(tfNombre, null);
	jpCenter.add(tfDocumento, null);
	jpCenter.add(tfTelefono, null);
	jpCenter.add(cbMarca, null);
	jpCenter.add(tfAnio, null);
	jpCenter.add(tfBuscarTipoVehiculo, null);
	jpCenter.add(cbTipoVehiculo, null);
	jpCenter.add(tfNroCuadro, null);
	//jpCenter.add(btnAceptarCliente, null);
	jpCenter.add(btnAceptarVehiculo, null);
	jpCenter.add(rbInterna, null);
	jpCenter.add(rbExterna, null);
	jpBusqueda.setLayout(null);
	jpBusqueda.setTitle("Vehículos existentes");
	jpBusqueda.setBackground(new Color(16, 61, 82));
	jpBusqueda.add(tfBuscarVehiculo, null);
	jpBusqueda.add(cbVehiculos, null);

	tfBuscarCliente.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			loadComboClientes(tfBuscarCliente.getStringForSQL());
		    }
		}
	    }
	);
	
	tfBuscarVehiculo.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			loadComboVehiculos(tfBuscarVehiculo.getStringForSQL());
		    }
		}

	    }
	);

	loadComboMarcas("''");
	
	cbClientes.addItemListener(new ItemListener() {
	    public void itemStateChanged(ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED) {
		    updateDatosCliente();
		}
	    }
	});
	
	cbVehiculos.addItemListener(new ItemListener() {
	    public void itemStateChanged(ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED) {
		    updateDatosVehiculo();
		}
	    }
	});

	btnAceptarCliente.setBounds(new Rectangle(490, 160, 40, 25));
	btnAceptarCliente.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnAceptarCliente_actionPerformed(e);
		}
	    });
	btnAceptarVehiculo.setBounds(new Rectangle(435, 420, 40, 25));
	rbInterna.setBounds(new Rectangle(220, 420, 95, 25));
	rbExterna.setBounds(new Rectangle(325, 420, 95, 25));
	btnAceptarVehiculo.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnAceptarVehiculo_actionPerformed(e);
		}
	    });
	this.add(jpCenter, BorderLayout.CENTER);
    }
    
    

    private void loadComboTiposVehiculo(String _filtro) {
	cbTipoVehiculo.loadJCombo("taller.getAllTiposVehiculo", _filtro + ",0,0");
    }
    
    private void loadComboMarcas(String _filtro) {
	cbMarca.loadJCombo("taller.getAllMarcas", _filtro + ",0,0");
    }
    
    private void updateDatosCliente() {
	clearDatosCliente();
	long _idCliente = -1;
	if (ordenServicio.getIdOrdenServicio() != -1) {
	    _idCliente = ordenServicio.getIdClienteExterno();
	} else {
	    if (cbClientes.getSelectedIndex() != -1) {
	        _idCliente = new Long(cbClientes.getSelectedValue().toString());
	    }
	}
	if (_idCliente != -1) {
	    ResultSet _datosCliente = LibSQL.exFunction("taller.getDatosCliente", _idCliente);
	    try {
		if (_datosCliente.next()) {
		    tfNombre.setValue(_datosCliente.getString("nombre"));
		    tfDocumento.setValue(_datosCliente.getString("documento"));
		    tfDomicilio.setValue(_datosCliente.getString("domicilio"));
		    tfTelefono.setValue(_datosCliente.getString("telefono"));
		    cbVehiculos.loadJCombo("taller.getAllVehiculosPorCliente", _idCliente);
		    getTabContainer().getParentInternalFrame().setTitle("Orden de Servicio Nº " + ordenServicio.getNumero() + " de " + tfNombre.getString());
		    updateDatosVehiculo();
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
    }

    private void updateDatosVehiculo() {
	clearDatosVehiculo();
	int _idVehiculo = -1;
	if (ordenServicio.getVehiculo().getIdVehiculo() != -1) {
	    _idVehiculo = ordenServicio.getVehiculo().getIdVehiculo();
	} else {
	    if (cbVehiculos.getSelectedIndex() != -1) {
		_idVehiculo = new Integer(cbVehiculos.getSelectedValue().toString());
	    }
	}
	if (_idVehiculo != -1) {
	    ResultSet _datosVehiculo = LibSQL.exFunction("taller.getDatosVehiculo", _idVehiculo);
	    try {
		if (_datosVehiculo.next()) {
		    tfNroCuadro.setValue(_datosVehiculo.getString("numerocuadro"));
		    cbMarca.setSelectedValue(_datosVehiculo.getString("marca"));
		    tfAnio.setValue(_datosVehiculo.getString("anio"));
		    cbTipoVehiculo.loadJCombo("taller.getAllTiposVehiculo", "''" + ",0,0");
		    cbTipoVehiculo.setSelectedID(_datosVehiculo.getString("idtipovehiculo"));
		    rbInterna.setSelected(_datosVehiculo.getBoolean("interna"));
		    rbExterna.setSelected(!_datosVehiculo.getBoolean("interna"));
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
    }

    private void clearDatosCliente() {
	tfNombre.setValue("");
	tfDocumento.setValue("");
	tfDomicilio.setValue("");
	tfTelefono.setValue("");
    }

    private void clearDatosVehiculo() {
	tfNroCuadro.setValue("");
	//tfMarca.setValue("");
	tfAnio.setValue("");
	cbTipoVehiculo.setSelectedValue("");
	tfNroCuadro.setEditable(cbVehiculos.getSelectedIndex() == -1);
	cbMarca.setEnabled(cbVehiculos.getSelectedIndex() == -1);
	tfAnio.setEditable(cbVehiculos.getSelectedIndex() == -1);
	tfBuscarTipoVehiculo.setEnabled(cbVehiculos.getSelectedIndex() == -1);
	cbTipoVehiculo.setEnabled(cbVehiculos.getSelectedIndex() == -1);
	rbInterna.setEnabled(cbVehiculos.getSelectedIndex() == -1);
	rbExterna.setEnabled(cbVehiculos.getSelectedIndex() == -1);
    }

    private void loadComboClientes(String _filtro) {
	if (_filtro.length() >= 5) { //3 caracteres de filtro y 2 apóstrofos
	    cbClientes.loadJCombo("taller.getAllExternalClientesByFilter", _filtro);
	    updateDatosCliente();
	} else {
	    Advisor.messageBox("Debe introducir al menos 3 letras del Nombre (cualquier parte) o al menos los 3 (primeros) números del DNI", "Error");
	}
    }

    private void loadComboVehiculos(String _filtro) {
	if (_filtro.length() >= 0) { //3 caracteres de filtro y 2 apóstrofos
	    cbVehiculos.loadJCombo("taller.getAllVehiculosByFilter", _filtro);
	    updateDatosVehiculo();
	} else {
	    Advisor.messageBox("Debe introducir al menos los 3 primeros caracteres del Nº de Cuadro", "Error");
	}
    }

    public void setOrdenServicio(OrdenServicio _ordenServicio) {
	ordenServicio = _ordenServicio;
	updateDatosCliente();
	updateDatosVehiculo();
	if (ordenServicio.getIdOrdenServicio() != -1) {
	    tfNumero.setEditable(false);
	    btnAceptarVehiculo.setEnabled(true);
	    btnAceptarCliente.setEnabled(false);
	    tfBuscarCliente.setEditable(false);
	    cbClientes.setEnabled(false);
	    tfBuscarCliente.setEnabled(false);
	    cbClientes.setEnabled(false);
	    tfTelefono.setEditable(false);
	    btnAceptarVehiculo.setEnabled(false);
	    jpBusqueda.setEnabled(false);
	    tfBuscarVehiculo.setEditable(false);
	    cbVehiculos.setEnabled(false);
	    tfBuscarVehiculo.setEnabled(false);
	    cbVehiculos.setEnabled(false);
	    ordenServicio.retrieveVehiculo();
	    tfNroCuadro.setEditable(false);
	    cbMarca.setEnabled(false);
	    tfAnio.setEditable(false);
	    tfBuscarTipoVehiculo.setEnabled(false);
	    tfBuscarTipoVehiculo.setEnabled(false);
	    tfObservaciones.setEditable(false);
	    cbTipoVehiculo.setEnabled(false);
	    rbInterna.setEnabled(false);
	    rbExterna.setEnabled(false);
	    tfNumero.setValue(ordenServicio.getNumero());
	    tfObservaciones.setText(ordenServicio.getObservaciones());
	}
    }

    private void btnAceptarCliente_actionPerformed(ActionEvent e) {
	//aceptarCliente();
    }
    
    private boolean aceptarCliente() {
	boolean _returns = false;
	if (cbClientes.getSelectedIndex() != -1) {
	    ordenServicio.setIdClienteExterno(new Long(cbClientes.getSelectedValue().toString()));
	    ordenServicio.setNumero(tfNumero.getString());
	    ordenServicio.setTelefono(tfTelefono.getString());
	    ordenServicio.setObservaciones(tfObservaciones.getText());
	    if (ordenServicio.saveData() != -1) {
	        tfNumero.setEditable(false);
		btnAceptarVehiculo.setEnabled(true);
	        btnAceptarCliente.setEnabled(false);
	        tfBuscarCliente.setEditable(false);
	        tfTelefono.setEditable(false);
	        cbClientes.setEnabled(false);
	        getTabContainer().getParentInternalFrame().setTitle("Orden de Servicio Nº " + ordenServicio.getNumero() + " de " + tfNombre.getString());
	        getTabContainer().getParentInternalFrame().setNeedSaving(true);
	        Environment.addUnfinishedTask(getTabContainer().getParentInternalFrame());
		_returns = true;
	    } else {
		Advisor.messageBox("Ha ocurrido un error al intentar grabar los datos", "Generar orden de servicio");
	    }
	} else {
	    Advisor.messageBox("Debe seleccionar un cliente", "Error");
	}
	return _returns;
    }

    
    private void btnAceptarVehiculo_actionPerformed(ActionEvent e) {
	saveData();
    }
    
    @Override
    public boolean saveData() {
	boolean _returns = false;
	int idVehiculo = -1;
	if (cbVehiculos.getSelectedIndex() != -1) {
	    idVehiculo = new Integer(cbVehiculos.getSelectedValue().toString());
	} else {
	    if (cbTipoVehiculo.getSelectedIndex() != -1) {
		Vehiculo _vehiculo = new Vehiculo(-1, tfNroCuadro.getString(), tfAnio.getString(), cbMarca.getSelectedItem().toString(), new Integer(cbTipoVehiculo.getSelectedValue().toString()), rbInterna.isSelected());
		idVehiculo = _vehiculo.saveData();
	    } else {
		Advisor.messageBox("No ha seleccionado un modelo de vehículo", "Error");
	    }
	}
	if (idVehiculo != -1) {
	    if (aceptarCliente()) {
		if (LibSQL.getBoolean("taller.addVehiculoPorOrdenServicio", ordenServicio.getIdOrdenServicio() + "," + idVehiculo)) {
		    _returns = true;
		    getTabContainer().getParentInternalFrame().setNeedSaving(false);
		    Environment.removeUnfinishedTask(getTabContainer().getParentInternalFrame());
		    btnAceptarVehiculo.setEnabled(false);
		    tfBuscarVehiculo.setEditable(false);
		    cbVehiculos.setEnabled(false);
		    ordenServicio.retrieveVehiculo();
		    tfNroCuadro.setEditable(false);
		    cbMarca.setEnabled(false);
		    tfAnio.setEditable(false);
		    tfBuscarTipoVehiculo.setEnabled(false);
		    tfObservaciones.setEditable(false);
		    cbTipoVehiculo.setEnabled(false);
		    rbInterna.setEnabled(false);
		    rbExterna.setEnabled(false);
		} else {
		    Advisor.messageBox("Error al intentar asignar el vehículo a la orden de servicio", "Error");
		}
	    }
	} else {
	    //Advisor.messageBox("No se ha seleccionado un vehículo", "Error");
	}
	return _returns;
    }
}
