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
 * PanelReporteMecanicosPorOrdenServicio.java
 *
 * */
package org.digitall.projects.kamar.taller.interfaces.informes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Vector;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class PanelReporteMecanicosPorOrdenServicio extends BasicPrimitivePanel {

    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, new int[] { 250, 75, 70, 80, 73, 190 }, "Mecánicos por Orden de Servicio", dataRow);
    private TFInput tfFechaDesde = new TFInput(DataTypes.SIMPLEDATE, "Desde", false);
    private TFInput tfFechaHasta = new TFInput(DataTypes.SIMPLEDATE, "Hasta", false);
    private CBInput cbMecanicos = new CBInput(0, "Mecánico", false);

    private BasicDialog parentContainer;
    private FindButton btnFind = new FindButton();

    private BasicPanel jpCenter = new BasicPanel();

    public PanelReporteMecanicosPorOrdenServicio() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	jpCenter.setLayout(null);
	jpCenter.setBorder(BorderPanel.getBorderPanel("Mecánicos por Orden de Servicio"));
	this.setSize(new Dimension(820, 296));
	listPanel.setBounds(new Rectangle(10, 60, 800, 215));
	tfFechaDesde.setBounds(new Rectangle(10, 20, 80, 35));
	tfFechaHasta.setBounds(new Rectangle(95, 20, 80, 35));
	cbMecanicos.setBounds(new Rectangle(180, 20, 260, 35));
	btnFind.setBounds(new Rectangle(445, 30, 30, 25));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);

	jpCenter.add(tfFechaDesde, null);
	jpCenter.add(tfFechaHasta, null);
	jpCenter.add(cbMecanicos, null);
	jpCenter.add(btnFind, null);
	jpCenter.add(listPanel, null);
	this.add(jpCenter, BorderLayout.CENTER);
	cbMecanicos.setGeneralItem(true);
	cbMecanicos.loadJCombo("taller.getAllMecanicos", "''" + ",0,0");

	tfFechaDesde.setValue("");
	tfFechaHasta.setValue("");

	setHeaderList();
    }

    private void setHeaderList() {
	Vector headerList = new Vector();
	headerList.addElement("* @ O. Serv.");
	headerList.addElement("* ID Mecanico");
	headerList.addElement("Mecánico");
	headerList.addElement("Nº Orden");
	headerList.addElement("Iniciada");
	headerList.addElement("Facturada");
	headerList.addElement("* Nº Factura");
	headerList.addElement("$ Monto");
	headerList.addElement("* Desde");
	headerList.addElement("* Hasta");
	headerList.addElement("Tiempo Total");
	headerList.addElement("* Total (crudo)");
	listPanel.setParams("taller.getReporteMecanicosPorOrdenServicio", "'', '', -1", headerList);
    }

    public void refresh() {
	listPanel.refresh(tfFechaDesde.getDateForSQL() + "," + tfFechaHasta.getDateForSQL()  + "," + cbMecanicos.getSelectedValue());
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
