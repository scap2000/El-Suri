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
 * ComprobantesMain.java
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
import javax.swing.border.BevelBorder;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;
import org.digitall.projects.kamar.taller.classes.Comprobante;
import org.digitall.projects.kamar.taller.interfaces.ordenservicio.PanelOrdenServicio;

public class ComprobantesMain extends BasicPrimitivePanel {

    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, new int[]{285, 109, 88, 75}, "Comprobantes", dataRow) {

	@Override
	public void finishLoading() {
	    refreshDetail();
	}
    };
    private GridPanel detailPanel = new GridPanel(30, new int[]{ 278, 50, 62, 62, 52, 52}, "Artículos", new Vector());
    private TFInput tfBuscarProveedor = new TFInput(DataTypes.STRING, "Buscar", false);
    private CBInput cbProveedores = new CBInput(0, "Proveedor");
    private TFInput tfNroComprobante = new TFInput(DataTypes.STRING, "Nro. Comprobante", false);
    private TFInput tfFecha = new TFInput(DataTypes.SIMPLEDATE, "Fecha", false);
    private BasicCheckBox chkPendientes = new BasicCheckBox("Pendientes", false);

    private FindButton btnBuscar = new FindButton();
    private BasicDialog parentContainer;

    private BasicButton btnNuevo = new BasicButton("Nuevo Comprobante");
    private BasicButton btnVincular = new BasicButton("Vincular con Órdenes");

    private BasicPanel jpCenter = new BasicPanel();
    private Comprobante comprobante;
    
    public ComprobantesMain() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	jpCenter.setLayout(null);
	jpCenter.setBorder(BorderPanel.getBorderPanel("Agregar/Modificar comprobantes"));
	this.setSize(new Dimension(646, 449));
	listPanel.setBounds(new Rectangle(10, 60, 625, 205));
	detailPanel.setBounds(new Rectangle(10, 270, 625, 140));
	tfBuscarProveedor.setBounds(new Rectangle(10, 20, 60, 35));
	cbProveedores.setBounds(new Rectangle(75, 20, 240, 35));
	tfBuscarProveedor.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
		        loadComboProveedores(tfBuscarProveedor.getStringForSQL());
		    }
		}

	    });
	tfNroComprobante.setBounds(new Rectangle(320, 20, 65, 35));
	tfFecha.setBounds(new Rectangle(390, 20, 85, 35));
	chkPendientes.setBounds(new Rectangle(500, 30, 105, 20));
	btnBuscar.setBounds(new Rectangle(605, 30, 30, 25));
	btnBuscar.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  refresh();
			      }

			  }
	);

	btnNuevo.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnNuevo_actionPerformed(e);
		}
	    });
	btnNuevo.setOpaque(true);
	btnNuevo.setFont(new Font("Dialog", 1, 10));
	btnNuevo.setForeground(Color.black);
	btnNuevo.setBackground(new Color(0, 107, 165));
	btnNuevo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	btnNuevo.setBounds(new Rectangle(10, 415, 135, 25));

	btnVincular.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnVincular_actionPerformed(e);
		}
	    });
	btnVincular.setOpaque(true);
	btnVincular.setFont(new Font("Dialog", 1, 10));
	btnVincular.setForeground(Color.black);
	btnVincular.setBackground(new Color(198, 49, 0));
	btnVincular.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	btnVincular.setBounds(new Rectangle(480, 415, 155, 25));

	jpCenter.add(tfBuscarProveedor, null);
	jpCenter.add(cbProveedores, null);
	jpCenter.add(tfNroComprobante, null);
	jpCenter.add(tfFecha, null);
	jpCenter.add(chkPendientes, null);
	jpCenter.add(btnBuscar, null);
	jpCenter.add(listPanel, null);
	jpCenter.add(detailPanel, null);
	jpCenter.add(btnNuevo, null);
	jpCenter.add(btnVincular, null);
	tfFecha.setValue("");
	this.add(jpCenter, BorderLayout.CENTER);
	comprobante = new Comprobante();
	cbProveedores.setGeneralItem(true);
	loadComboProveedores("''");
	detailPanel.removeControls();
	setHeaderList();
    }

    private void btnNuevo_actionPerformed(ActionEvent e) {
	ComprobantesMgmt _comprobanteMgmt = new ComprobantesMgmt(new Comprobante());
	ExtendedInternalFrame newComprobante = new ExtendedInternalFrame("Nuevo comprobante", _comprobanteMgmt);
	newComprobante.setClosable(true);
	newComprobante.show();
    }

    private void btnVincular_actionPerformed(ActionEvent e) {
	if (comprobante.getIdComprobante() != -1) {
	    ComprobantesMgmt _comprobanteMgmt = new ComprobantesMgmt(comprobante);
	    ExtendedInternalFrame newComprobante = new ExtendedInternalFrame("Nuevo comprobante", _comprobanteMgmt);
	    newComprobante.setClosable(true);
	    newComprobante.show();
	} else {
	    Advisor.messageBox("Debe seleccionar un comprobante", "Vincular comprobante con Orden de Servicio");
	}
    }
    
    private void loadComboProveedores(String _filtro) {
	cbProveedores.loadJCombo("taller.getAllProveedores", _filtro + ",0,0");
    }

    private void setHeaderList() {
	Vector headerList = new Vector();
	headerList.addElement("* ID Comprobante");
	headerList.addElement("* ID Proveedor");
	headerList.addElement("Proveedor");
	headerList.addElement("Número");
	headerList.addElement("Fecha");
	headerList.addElement("$ Total");
	listPanel.getTable().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
		    loadData();
		}
	    }
	});
	listPanel.setParams("taller.getAllComprobantes", "-1,'','', false", headerList);

	Vector detailList = new Vector();
	detailList.addElement("* ID Detalle Comprobante");
	detailList.addElement("* ID Comprobante");
	detailList.addElement("* ID Artículo");
	detailList.addElement("Artículo");
	detailList.addElement("$ U.");
	detailList.addElement("Cant.");
	detailList.addElement("$ Total");
	detailList.addElement("Vinc.");
	detailList.addElement("Rest.");
	detailPanel.setParams("taller.getDetalleComprobante", "-1", detailList);
    }

    public void refresh() {
	comprobante = new Comprobante();
	listPanel.refresh(cbProveedores.getSelectedValue() + "," + tfNroComprobante.getStringForSQL() + "," + tfFecha.getDateForSQL() + "," + chkPendientes.isSelected());
    }
    
    private void refreshDetail() {
	int _idComprobante = 0;
	if (dataRow.size() > 0) {
	    _idComprobante = new Integer(dataRow.elementAt(0).toString());
	}
	detailPanel.refresh(_idComprobante);
    }
    
    private void loadData(){
	if (dataRow.size() > 0) {
	    comprobante = new Comprobante(new Integer(dataRow.elementAt(0).toString()));
	    refreshDetail();
	}
    }

    public void setParentContainer(BasicDialog parentContainer) {
	this.parentContainer = parentContainer;
    }

}
