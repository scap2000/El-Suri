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
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.projects.kamar.taller.classes.OrdenServicio;
import org.digitall.projects.kamar.taller.interfaces.ordenservicio.PanelOrdenServicio;

public class PanelReporteOrdenesServicio extends BasicPrimitivePanel {

    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, new int[]{ 64, 125, 83, 75, 75, 75, 77, 85, 75 }, "Órdenes de Servicio", dataRow);
    private CBInput cbEstado = new CBInput(0,"Estado",false);
    private TFInput tfFechaDesde = new TFInput(DataTypes.SIMPLEDATE, "Desde", false);
    private TFInput tfFechaHasta = new TFInput(DataTypes.SIMPLEDATE, "Hasta", false);
    private TFInput tfFechaFacturadoDesde = new TFInput(DataTypes.SIMPLEDATE, "Facturadas desde", false);
    private TFInput tfFechaFacturadoHasta = new TFInput(DataTypes.SIMPLEDATE, "Facturadas hasta", false);
    private CBInput cbModelo = new CBInput(0,"Modelo",false);

    private BasicDialog parentContainer;
    private FindButton btnFind = new FindButton();

    private BasicPanel jpCenter = new BasicPanel();

    public PanelReporteOrdenesServicio() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	jpCenter.setLayout(null);
	jpCenter.setBorder(BorderPanel.getBorderPanel("Órdenes de Servicio"));
	this.setSize(new Dimension(820, 296));
	listPanel.setBounds(new Rectangle(10, 60, 800, 215));
	cbEstado.setBounds(new Rectangle(400, 20, 190, 35));
	tfFechaDesde.setBounds(new Rectangle(10, 20, 80, 35));
	tfFechaHasta.setBounds(new Rectangle(95, 20, 80, 35));
	tfFechaFacturadoDesde.setBounds(new Rectangle(180, 20, 105, 35));
	tfFechaFacturadoHasta.setBounds(new Rectangle(290, 20, 105, 35));
	cbModelo.setBounds(new Rectangle(490, 15, 195, 35));
	btnFind.setBounds(new Rectangle(595, 30, 30, 25));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);

	jpCenter.add(cbEstado, null);
	jpCenter.add(tfFechaDesde, null);
	jpCenter.add(tfFechaHasta, null);
	jpCenter.add(tfFechaFacturadoDesde, null);
	jpCenter.add(tfFechaFacturadoHasta, null);
	jpCenter.add(btnFind, null);
	jpCenter.add(listPanel, null);
	this.add(jpCenter, BorderLayout.CENTER);
	cbEstado.setGeneralItem(true);
	cbEstado.loadJCombo("taller.getAllEstadosOrdenesServicio", "''" + ",0,0");
	cbModelo.setGeneralItem(true);
	cbModelo.loadJCombo("taller.getAllTiposVehiculo", "''" + ",0,0");

	tfFechaDesde.setValue("");
	tfFechaHasta.setValue("");
	tfFechaFacturadoDesde.setValue("");
	tfFechaFacturadoHasta.setValue("");

	setHeaderList();
    }

    private void setHeaderList() {
	Vector headerList = new Vector();
	headerList.addElement("* ID Orden Servicio");
	headerList.addElement("Número");
	headerList.addElement("* ID Estado");
	headerList.addElement("Estado");
	headerList.addElement("* ID Cliente");
	headerList.addElement("* Cliente");
	headerList.addElement("* Teléfono");
	headerList.addElement("Fecha Alta");
	headerList.addElement("Ingreso al taller");
	headerList.addElement("Egreso del taller");
	headerList.addElement("Facturada");
	headerList.addElement("* Tiempo Estimado");
	headerList.addElement("Nº Factura");
	headerList.addElement("$ Facturado");
	headerList.addElement("$ Estimado");
	headerList.addElement("$ Servicios");
	headerList.addElement("$ Tareas");
	headerList.addElement("$ Articulos");
	headerList.addElement("* ID Vehiculo");
	headerList.addElement("* ID Tipo Vehiculo");
	headerList.addElement("* Modelo");
	headerList.addElement("* # Cuadro");
	headerList.addElement("* Marca");
	headerList.addElement("* Anio");
	listPanel.getTable().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
	        if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
	            if (dataRow.size() > 0) {
	                try {
	                    OrdenServicio _ordenServicio = new OrdenServicio(new Integer(dataRow.elementAt(0).toString()));
	                    _ordenServicio.retrieveData();
	                    PanelOrdenServicio _panelOrdenServicio = new PanelOrdenServicio();
	                    ExtendedInternalFrame newOrdenServicio = new ExtendedInternalFrame("Nueva Orden de Servicio", _panelOrdenServicio);
	                    _panelOrdenServicio.setOrdenServicio(_ordenServicio);
	                    newOrdenServicio.setClosable(true);
	                    newOrdenServicio.show();
	                } catch (NumberFormatException x) {
	                    Advisor.messageBox("No ha seleccionado una Orden de servicio válida", "Órdenes de servicio vinculadas");
	                }
	            }
	        }
	    }
	});
	listPanel.setParams("taller.getReporteOrdenesServicio", "'', '', '', '', -1", headerList);
    }

    public void refresh() {
	listPanel.refresh(tfFechaDesde.getDateForSQL() + "," + tfFechaHasta.getDateForSQL()  + "," + tfFechaFacturadoDesde.getDateForSQL() + "," + tfFechaFacturadoHasta.getDateForSQL()  + "," + cbEstado.getSelectedValue());
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

}
