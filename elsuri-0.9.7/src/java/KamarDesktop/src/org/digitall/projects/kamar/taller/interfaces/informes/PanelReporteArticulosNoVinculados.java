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
package org.digitall.projects.kamar.taller.interfaces.informes;

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
import org.digitall.projects.kamar.taller.classes.Comprobante;
import org.digitall.projects.kamar.taller.classes.OrdenServicio;
import org.digitall.projects.kamar.taller.interfaces.ComprobantesMgmt;
import org.digitall.projects.kamar.taller.interfaces.ordenservicio.PanelOrdenServicio;

public class PanelReporteArticulosNoVinculados extends BasicPrimitivePanel {

    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, new int[]{ 315, 68, 90, 78, 89, 86 }, "Artículos no vinculados", dataRow);
    private TFInput tfBuscarProveedor = new TFInput(DataTypes.STRING, "Buscar", false);
    private CBInput cbProveedores = new CBInput(0, "Proveedor");
    private TFInput tfFechaDesde = new TFInput(DataTypes.SIMPLEDATE, "Desde", false);
    private TFInput tfFechaHasta = new TFInput(DataTypes.SIMPLEDATE, "Hasta", false);

    private BasicDialog parentContainer;
    private FindButton btnFind = new FindButton();

    private BasicPanel jpCenter = new BasicPanel();

    private BasicButton btnVerComprobante = new BasicButton("(F4) Ver el comprobante");

    public PanelReporteArticulosNoVinculados() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	jpCenter.setLayout(null);
	jpCenter.setBorder(BorderPanel.getBorderPanel("Artículos no vinculados"));
	this.setSize(new Dimension(820, 296));
	listPanel.setBounds(new Rectangle(10, 60, 800, 215));
	tfBuscarProveedor.setBounds(new Rectangle(10, 20, 115, 35));
	cbProveedores.setBounds(new Rectangle(130, 20, 255, 35));
	tfBuscarProveedor.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			loadComboProveedores(tfBuscarProveedor.getStringForSQL());
		    }
		}

	    });
	tfFechaDesde.setBounds(new Rectangle(390, 20, 80, 35));
	tfFechaHasta.setBounds(new Rectangle(475, 20, 80, 35));
	btnFind.setBounds(new Rectangle(560, 30, 30, 25));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);

	//jpCenter.add(tfBuscarProveedor, null);
	//jpCenter.add(cbProveedores, null);
	//jpCenter.add(tfFechaDesde, null);
	//jpCenter.add(tfFechaHasta, null);
	jpCenter.add(btnFind, null);
	jpCenter.add(listPanel, null);
	jpCenter.add(btnVerComprobante, null);
	this.add(jpCenter, BorderLayout.CENTER);
	cbProveedores.setGeneralItem(true);
	loadComboProveedores("''");
	tfFechaDesde.setValue("");
	tfFechaHasta.setValue("");
	setHeaderList();
	btnVerComprobante.setOpaque(true);
	btnVerComprobante.setFont(new Font("Dialog", 1, 10));
	btnVerComprobante.setForeground(Color.black);
	btnVerComprobante.setBackground(new Color(255, 132, 0));
	btnVerComprobante.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	btnVerComprobante.setBounds(new Rectangle(645, 30, 165, 25));
	btnVerComprobante.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		btnVerComprobantes_actionPerformed(e);
	    }
	});

	registerKeyboardAction(
	    new ActionListener()  {
		public void actionPerformed(ActionEvent actionEvent) {
		    showComprobantes();
		}
	    },
	    "VerComprobantes",
	    KeyStroke.getKeyStroke( KeyEvent.VK_F4, 0),
	    JComponent.WHEN_IN_FOCUSED_WINDOW 
	);
    }

    private void btnVerComprobantes_actionPerformed(ActionEvent e) {
	showComprobantes();
    }

    private void loadComboProveedores(String _filtro) {
	cbProveedores.loadJCombo("taller.getAllProveedores", _filtro + ",0,0");
    }

    private void setHeaderList() {
	Vector headerList = new Vector();
	headerList.addElement("* ID Detalle Comprobante");
	headerList.addElement("* ID Comprobante");
	headerList.addElement("* ID Proveedor");
	headerList.addElement("* Proveedor");
	headerList.addElement("* ID Artículo");
	headerList.addElement("Artículo");
	headerList.addElement("$ U.");
	headerList.addElement("Sin vincular");
	headerList.addElement("$ Total");
	headerList.addElement("Comprados");
	headerList.addElement("Vinculados");
	listPanel.setParams("taller.getReporteArticulosNoVinculados", "", headerList);
    }

    public void refresh() {
	listPanel.refresh("");
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

    private void showComprobantes() {
	if (dataRow.size() > 0) {
	    int idComprobante = new Integer(dataRow.elementAt(1).toString());
	    ComprobantesMgmt _panelComprobante = new ComprobantesMgmt(new Comprobante(idComprobante));
	    ExtendedInternalFrame _comprobante = new ExtendedInternalFrame("Comprobante", _panelComprobante);
	    _comprobante.setClosable(true);
	    _comprobante.show();
	} else {
	    Advisor.messageBox("Debe seleccionar un artículo", "Comprobante");
	}
    }

}
