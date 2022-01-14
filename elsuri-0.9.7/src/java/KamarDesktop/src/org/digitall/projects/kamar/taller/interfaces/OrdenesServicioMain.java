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
 * OrdenesServicioMain.java
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;
import org.digitall.projects.kamar.taller.classes.OrdenServicio;
import org.digitall.projects.kamar.taller.interfaces.ordenservicio.PanelOrdenServicio;

public class OrdenesServicioMain extends BasicPrimitivePanel {

    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, new int[]{ 68, 78, 218, 68, 93, 75, 82, 276, 271 }, "Órdenes de Servicio", dataRow) {

	@Override
	public void finishLoading() {
	    btnIngresar.setEnabled(false);
	    btnEgresar.setEnabled(false);
	    btnVerMecanicos.setEnabled(false);
	    btnFacturar.setEnabled(false);
	    tfNroFactura.setValue("");
	    tfMontoFacturado.setValue(0);
	    tfNroFactura.setEditable(false);
	    tfMontoFacturado.setEditable(false);
	}
    };
    private TFInput tfNumero = new TFInput(DataTypes.STRING, "Nro. Orden", false);
    private TFInput tfCliente = new TFInput(DataTypes.STRING, "Cliente", false);
    private CBInput cbEstado = new CBInput(0,"Estado",false);
    private TFInput tfFechaDesde = new TFInput(DataTypes.SIMPLEDATE, "Desde", false);
    private TFInput tfFechaHasta = new TFInput(DataTypes.SIMPLEDATE, "Hasta", false);
    private CBInput cbModelo = new CBInput(0,"Modelo",false);

    private BasicDialog parentContainer;
    private OrdenServicio ordenServicio;
    private FindButton btnFind = new FindButton();
    private BasicButton btnNueva = new BasicButton("(F6) Nueva Orden");
    private TFInput tfNroFactura = new TFInput(DataTypes.STRING, "Nro. Factura", false);
    private TFInput tfMontoFacturado = new TFInput(DataTypes.MONEY, "Monto", false);
    private BasicButton btnFacturar = new BasicButton("Facturar Orden");
    private BasicButton btnIngresar = new BasicButton("(F7) Ingresar al taller");
    private BasicButton btnEgresar = new BasicButton("(F8) Egresar del taller");
    private BasicButton btnVerMecanicos = new BasicButton("(F9) Mecánicos Asignados");

    private BasicPanel jpCenter = new BasicPanel();
    private BasicPanel jpSouth = new BasicPanel();
    
    public OrdenesServicioMain() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	jpCenter.setLayout(null);
	jpSouth.setLayout(null);
	jpSouth.setPreferredSize(new Dimension(1,90));
	jpCenter.setBorder(BorderPanel.getBorderPanel("Órdenes de Servicio"));
	this.setSize(new Dimension(774, 374));
	listPanel.setBounds(new Rectangle(10, 55, 755, 215));
	tfNumero.setBounds(new Rectangle(10, 15, 95, 35));
	tfCliente.setBounds(new Rectangle(110, 15, 230, 35));
	cbEstado.setBounds(new Rectangle(345, 15, 190, 35));
	tfFechaDesde.setBounds(new Rectangle(540, 15, 80, 35));
	tfFechaHasta.setBounds(new Rectangle(625, 15, 80, 35));
	cbModelo.setBounds(new Rectangle(490, 15, 195, 35));
	btnFind.setBounds(new Rectangle(730, 25, 30, 25));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);
	jpCenter.add(tfNumero, null);
	jpCenter.add(tfCliente, null);
	jpCenter.add(cbEstado, null);
	jpCenter.add(tfFechaDesde, null);
	jpCenter.add(tfFechaHasta, null);
	jpCenter.add(btnFind, null);
	btnNueva.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnNueva_actionPerformed(e);
		}
	    });
	btnNueva.setOpaque(true);
	btnNueva.setFont(new Font("Dialog", 1, 10));
	btnNueva.setForeground(Color.black);
	btnNueva.setBackground(new Color(0, 107, 165));
	btnNueva.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	btnNueva.setBounds(new Rectangle(5, 15, 115, 25));
	btnFacturar.setOpaque(true);
	btnFacturar.setFont(new Font("Dialog", 1, 10));
	btnFacturar.setForeground(Color.black);
	btnFacturar.setBackground(new Color(198, 49, 0));
	btnFacturar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	btnFacturar.setBounds(new Rectangle(610, 45, 155, 25));
	tfNroFactura.setBounds(new Rectangle(610, 5, 80, 35));
	tfMontoFacturado.setBounds(new Rectangle(695, 5, 70, 35));
	btnFacturar.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnFacturar_actionPerformed(e);
		}
	    });
	btnVerMecanicos.setOpaque(true);
	btnVerMecanicos.setFont(new Font("Dialog", 1, 10));
	btnVerMecanicos.setForeground(Color.black);
	btnVerMecanicos.setBackground(new Color(255, 132, 0));
	btnVerMecanicos.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	btnVerMecanicos.setBounds(new Rectangle(425, 15, 160, 25));
	btnVerMecanicos.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnVerMecanicos_actionPerformed(e);
		}
	    });
	btnIngresar.setOpaque(true);
	btnIngresar.setFont(new Font("Dialog", 1, 10));
	btnIngresar.setForeground(Color.black);
	btnIngresar.setBackground(new Color(90, 148, 0));
	btnIngresar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	btnEgresar.setOpaque(true);
	btnEgresar.setFont(new Font("Dialog", 1, 10));
	btnEgresar.setForeground(Color.black);
	btnEgresar.setBackground(new Color(165, 165, 0));
	btnEgresar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	btnIngresar.setBounds(new Rectangle(130, 15, 135, 25));
	btnIngresar.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnIngresar_actionPerformed(e);
		}
	    });
	btnEgresar.setBounds(new Rectangle(275, 15, 140, 25));
	btnEgresar.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnEgresar_actionPerformed(e);
		}
	    });
	jpCenter.add(listPanel, null);
	this.add(jpCenter, BorderLayout.CENTER);
	jpSouth.add(btnNueva, null);
	jpSouth.add(tfNroFactura, null);
	jpSouth.add(tfMontoFacturado, null);
	jpSouth.add(btnFacturar, null);
	jpSouth.add(btnVerMecanicos, null);
	jpSouth.add(btnIngresar, null);
	jpSouth.add(btnEgresar, null);
	this.add(jpSouth, BorderLayout.SOUTH);
	cbEstado.setGeneralItem(true);
	cbEstado.loadJCombo("taller.getAllEstadosOrdenesServicio", "''" + ",0,0");
	cbModelo.setGeneralItem(true);
	cbModelo.loadJCombo("taller.getAllTiposVehiculo", "''" + ",0,0");
	ordenServicio = new OrdenServicio();
	tfFechaDesde.setValue("");
	tfFechaHasta.setValue("");
	tfNumero.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			refresh();
		    }
		}

	    }
	);

	tfCliente.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			refresh();
		    }
		}

	    }
	);

	tfMontoFacturado.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			facturarOrdenServicio();
		    }
		}

	    }
	);

	registerKeyboardAction(
	    new ActionListener()  {
		public void actionPerformed(ActionEvent actionEvent) {
		    nuevaOrden();
		}
	    },
	    "NuevaOrden",
	    KeyStroke.getKeyStroke( KeyEvent.VK_F6, 0),
	    JComponent.WHEN_IN_FOCUSED_WINDOW 
	);
	registerKeyboardAction(
	    new ActionListener()  {
		public void actionPerformed(ActionEvent actionEvent) {
		    ingresarTaller();
		}
	    },
	    "IngresarTaller",
	    KeyStroke.getKeyStroke( KeyEvent.VK_F7, 0),
	    JComponent.WHEN_IN_FOCUSED_WINDOW 
	);
	registerKeyboardAction(
	    new ActionListener()  {
		public void actionPerformed(ActionEvent actionEvent) {
		    egresarTaller();
		}
	    },
	    "EgresarTaller",
	    KeyStroke.getKeyStroke( KeyEvent.VK_F8, 0),
	    JComponent.WHEN_IN_FOCUSED_WINDOW 
	);
	registerKeyboardAction(
	    new ActionListener()  {
		public void actionPerformed(ActionEvent actionEvent) {
		    showMecanicosAsignados();
		}
	    },
	    "VerMecanicosAsignados",
	    KeyStroke.getKeyStroke( KeyEvent.VK_F9, 0),
	    JComponent.WHEN_IN_FOCUSED_WINDOW 
	);
	setHeaderList();
    }

    private void setHeaderList() {
	Vector headerList = new Vector();
	headerList.addElement("* ID Orden Servicio");
	headerList.addElement("Número");
	headerList.addElement("* idestado");
	headerList.addElement("Estado");
	headerList.addElement("* idcliente");
	headerList.addElement("Cliente");
	headerList.addElement("Teléfono");
	headerList.addElement("* ingresovehiculo");
	headerList.addElement("* egresovehiculo");
	headerList.addElement("Iniciada");
	headerList.addElement("* fechafin");
	headerList.addElement("* tiempoestimado");
	headerList.addElement("$ Estimado");
	headerList.addElement("* numerofactura");
	headerList.addElement("$ Facturado");
	headerList.addElement("* idvehiculo");
	headerList.addElement("* idtipovehiculo");
	headerList.addElement("Modelo");
	headerList.addElement("# Cuadro");
	headerList.addElement("* Marca");
	headerList.addElement("* Anio");
	listPanel.getTable().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
		    loadData();
		} else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
		    loadData();
		    PanelOrdenServicio _panelOrdenServicio = new PanelOrdenServicio();
		    ExtendedInternalFrame newOrdenServicio = new ExtendedInternalFrame("Nueva Orden de Servicio", _panelOrdenServicio);
		    _panelOrdenServicio.setOrdenServicio(ordenServicio);
		    newOrdenServicio.setClosable(true);
		    newOrdenServicio.show();
		}
	    }
	});
	listPanel.setParams("taller.getAllOrdenesServicio", "'', '', -1, '', ''", headerList);
    }

    public void refresh() {
	clearData();
	listPanel.refresh(tfNumero.getStringForSQL() + "," + tfCliente.getStringForSQL() + "," + cbEstado.getSelectedValue() + "," + tfFechaDesde.getDateForSQL() + "," + tfFechaHasta.getDateForSQL());
    }
    
    private void loadData(){
	btnIngresar.setEnabled(false);
	btnEgresar.setEnabled(false);
	btnFacturar.setEnabled(false);
	tfNroFactura.setEditable(false);
	tfMontoFacturado.setEditable(false);
	tfNroFactura.setValue("");
	tfMontoFacturado.setValue(0.0);
	if (dataRow.size() > 0) {
	    btnVerMecanicos.setEnabled(true);
	    ordenServicio = new OrdenServicio(new Integer(dataRow.elementAt(0).toString()));
	    ordenServicio.setNumero((dataRow.elementAt(1).toString()));
	    ordenServicio.setIdEstado(new Integer(dataRow.elementAt(2).toString()));
	    tfMontoFacturado.setValue(dataRow.elementAt(12));
	    switch (ordenServicio.getIdEstado()) {
	        case 0: //Alta
		    btnIngresar.setEnabled(true);
	            break;
	        case 1: //Iniciada/En proceso
		    btnEgresar.setEnabled(true);
	            break;
	        case 2: //Finalizada/A facturar
		    btnFacturar.setEnabled(true);
		    tfNroFactura.setEditable(true);
		    tfMontoFacturado.setEditable(true);
	            break;
	        case 3: //Facturada/Cerrada
	            break;
	        case 4: //Cancelada/Anulada
	            break;
	    }
	}
    }

    private void clearData() {
	ordenServicio = new OrdenServicio();
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

    private void btnNueva_actionPerformed(ActionEvent e) {
	nuevaOrden();
    }
    
    private void nuevaOrden() {
	PanelOrdenServicio _panelOrdenServicio = new PanelOrdenServicio();
	ExtendedInternalFrame newOrdenServicio = new ExtendedInternalFrame("Nueva Orden de Servicio", _panelOrdenServicio);
	_panelOrdenServicio.setOrdenServicio(new OrdenServicio());
	newOrdenServicio.setClosable(true);
	newOrdenServicio.show();
    }

    private void btnIngresar_actionPerformed(ActionEvent e) {
	ingresarTaller();
    }
    
    private void ingresarTaller() {
	if (ordenServicio.getIdOrdenServicio() != -1) {
	    if (ordenServicio.getIdEstado() == 0) {
		if (Advisor.question("Ingresar vehículo", "¿Desea ingresar el vehículo al taller?")) {
		    if (LibSQL.getBoolean("taller.ingresarvehiculoporordenservicio", ordenServicio.getIdOrdenServicio())) {
			refresh();
		    } else {
			Advisor.messageBox("Error al intentar ingresar el vehículo", "Ingreso de vehículo");
		    }
		}
	    } else {
	        Advisor.messageBox("Este vehículo ya fue ingresado", "Ingreso de vehículo");
	    }
	} else {
	    Advisor.messageBox("Debe seleccionar una orden de servicio", "Ingreso de vehículo");
	}
    }

    private void btnEgresar_actionPerformed(ActionEvent e) {
	egresarTaller();
    }
    
    private void egresarTaller() {
	if (ordenServicio.getIdOrdenServicio() != -1) {
	    System.out.println(ordenServicio.getIdEstado());
	    if (ordenServicio.getIdEstado() == 1) {
		if (Advisor.question("Egresar vehículo", "¿Desea egresar el vehículo del taller?")) {
		    if (LibSQL.getBoolean("taller.egresarvehiculoporordenservicio", ordenServicio.getIdOrdenServicio())) {
			refresh();
		    } else {
			Advisor.messageBox("Error al intentar egresar el vehículo", "Egreso de vehículo");
		    }
		}
	    } else {
	        Advisor.messageBox("Este vehículo ya fue egresado", "Egreso de vehículo");
	    }
	} else {
	    Advisor.messageBox("Debe seleccionar una orden de servicio", "Egreso de vehículo");
	}
    }

    private void btnVerMecanicos_actionPerformed(ActionEvent e) {
	showMecanicosAsignados();
    }
    
    private void showMecanicosAsignados() {
	if (ordenServicio.getIdOrdenServicio() != -1) {
	    GridPanel mecanicosListPanel = new GridPanel(5000, new int[] { 235, 100, 100 }, "Mecánicos", new Vector()) {
		public void finishLoading() {
		    requestFocusInWindow();
		}
	    };
	    mecanicosListPanel.setPreferredSize(new Dimension(500,150));
	    Vector mecanicosHeaderList = new Vector();
	    mecanicosHeaderList.addElement("* ID Orden de Servicio");
	    mecanicosHeaderList.addElement("* ID Mecánico");
	    mecanicosHeaderList.addElement("Nombre");
	    mecanicosHeaderList.addElement("Inicio");
	    mecanicosHeaderList.addElement("Fin");
	    mecanicosListPanel.setParams("taller.getAllMecanicosPorOrdenServicio", "-1", mecanicosHeaderList);
	    mecanicosListPanel.removeControls();
	    mecanicosListPanel.refresh(ordenServicio.getIdOrdenServicio());
	    JOptionPane.showInternalMessageDialog(this, mecanicosListPanel, "Mecánicos asignados", JOptionPane.OK_OPTION, new ImageIcon(""));
	} else {
	    Advisor.messageBox("Debe seleccionar una orden de servicio", "Mecánicos asignados");
	}
    }

    private void btnFacturar_actionPerformed(ActionEvent e) {
	facturarOrdenServicio();
    }
    
    private void facturarOrdenServicio () {
	if (ordenServicio.getIdOrdenServicio() != -1) {
	    if (tfNroFactura.getString().length() > 0) {
		if (Advisor.question("Facturar Orden de Servicio", "¿Desea facturar la Orden de Servicio Nº " + ordenServicio.getNumero() + "?\n* Factura Nº " + tfNroFactura.getString() + "\n* Monto: " + tfMontoFacturado.getTextField().getText())) {
		    if (LibSQL.getBoolean("taller.facturarordenservicio", ordenServicio.getIdOrdenServicio() + "," + tfNroFactura.getStringForSQL() + "," + tfMontoFacturado.getAmount())) {
			refresh();
		    } else {
			Advisor.messageBox("Error al intentar facturar la Orden de Servicio", "Facturación de Orden");
		    }
		}
	    } else {
		Advisor.messageBox("Debe escribir un número de factura", "Facturación de Orden");
	    }
	} else {
	    Advisor.messageBox("Debe seleccionar una orden de servicio", "Facturación de Orden");
	}
    }
}
