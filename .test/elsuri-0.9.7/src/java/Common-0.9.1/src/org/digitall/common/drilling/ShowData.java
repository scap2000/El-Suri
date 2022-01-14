/**
 LIMITACIÓN DE RESPONSABILIDAD - COPYRIGHT - [Español]
 ================================================================================
 El Suri - Entorno JAVA de Trabajo y Desarrollo para Gobierno Electrónico
 ================================================================================

 Información del Proyecto:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 por D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO es propiedad de
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) y
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los términos de la Licencia Pública General GNU publicada
 por la Fundación para el Software Libre, ya sea la versión 3
 de la Licencia, o (a su elección) cualquier versión posterior.

 Este programa se distribuye con la esperanza de que sea útil, pero
 SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita
 MERCANTIL o de APTITUD PARA UN PROPÓSITO DETERMINADO.
 Consulte los detalles de la Licencia Pública General GNU para obtener
 una información más detallada.

 Debería haber recibido una copia de la Licencia Pública General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
 El Suri - JAVA Management & Development Environment for Electronic Government
 =====================================================================================

 Project Info:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 by D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO is owned by
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) and
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

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
 * ShowData.java
 *
 * */
package org.digitall.common.drilling;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;

import org.digitall.common.drilling.PanelDrillData;
import org.digitall.common.drilling.PanelLithologyData;
import org.digitall.common.drilling.PanelSamplingAndAssay;
import org.digitall.common.drilling.PanelHeader;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.icons.IconTypes;

public class ShowData extends BasicDialog {

    private JTabbedPane jTabbedPane1 = new JTabbedPane();
    private PanelDrillData jpDrillData;
    private PanelLithologyData jpLithologyData;
    private PanelSamplingAndAssay jpSamplingAndAssay;
    private CloseButton bCancel = new CloseButton();
    private PanelHeader panelHeader;
    private String drillName = "";
    private String idproject = "", iddrill = "";
    final String TEXTO = "Log for Drill: DDH";

    public ShowData(int _idproject, int _iddrill, String _drillName) {
	try {
	    idproject = String.valueOf(_idproject);
	    iddrill = String.valueOf(_iddrill);
	    drillName = _drillName;
	    jpDrillData = new PanelDrillData(idproject, iddrill);
	    jpLithologyData = new PanelLithologyData(idproject, iddrill);
	    jpSamplingAndAssay = new PanelSamplingAndAssay(idproject, iddrill);
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public ShowData(String _idproject, String _iddrill, String _drillName) {
	try {
	    idproject = _idproject;
	    iddrill = _iddrill;
	    drillName = _drillName;
	    jpDrillData = new PanelDrillData(idproject, iddrill);
	    jpLithologyData = new PanelLithologyData(idproject, iddrill);
	    jpSamplingAndAssay = new PanelSamplingAndAssay(idproject, iddrill);
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(735, 403));
	this.setBounds(new Rectangle(10, 10, 735, 402));
	panelHeader = new PanelHeader(this.getWidth(), TEXTO + drillName, IconTypes.CR_IconHeaderLogo, IconTypes.CRDrilling_IconHeaderLogo);
	this.getContentPane().add(panelHeader, null);
	this.getContentPane().setLayout(null);
	this.setTitle("Drill Logging");
	jTabbedPane1.setBounds(new Rectangle(8, 40, 715, 300));
	bCancel.setBounds(new Rectangle(10, 345, 40, 25));
	bCancel.setMnemonic('C');
	bCancel.setToolTipText("Cancel");
	bCancel.setSize(new Dimension(40, 25));
	bCancel.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bCancel_actionPerformed(e);
		    }

		});
	jpSamplingAndAssay.setToolTipText("null");
	jpSamplingAndAssay.setLayout(null);
	jTabbedPane1.addTab("Logging", jpDrillData);
	jTabbedPane1.addTab("Lithology Data & Structure", jpLithologyData);
	jTabbedPane1.addTab("Sampling & Assay", jpSamplingAndAssay);
	this.getContentPane().add(bCancel, null);
	this.getContentPane().add(jTabbedPane1, null);
	jpSamplingAndAssay.setBounds(5, 5, 715, 265);
	org.digitall.lib.components.ComponentsManager.centerWindow(this);
    }

    private void bCancel_actionPerformed(ActionEvent e) {
	this.dispose();
    }

}
