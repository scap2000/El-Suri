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
 * GaiaPuntoPruebaPanel.java
 *
 * */
package org.digitall.apps.gaia.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.RefreshGridButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.geo.esri.ESRIPoint;
import org.digitall.lib.geo.gaia.components.GaiaConfigPanel;
import org.digitall.lib.sql.LibSQL;

public class GaiaPuntoPruebaPanel extends GaiaConfigPanel {

    private BasicPanel panelData = new BasicPanel();
    private TFInput tfDescripcion = new TFInput(DataTypes.STRING, "Description", false);
    private ESRIPoint point;
    private BasicRadioButton rbOpcionA = new BasicRadioButton("Opción A");
    private BasicRadioButton rbOpcionB = new BasicRadioButton("Opción B");
    private BasicRadioButton rbOpcionC = new BasicRadioButton("Opción C");
    private CBInput cbSeleccion = new CBInput(0, "Selección", false);
    private RefreshGridButton rgRefresh = new RefreshGridButton();
    private ButtonGroup bgOpciones = new ButtonGroup();
    private SaveDataButton btnSave = new SaveDataButton();

    public GaiaPuntoPruebaPanel() {
	this(new ESRIPoint(0, 0));
    }

    public GaiaPuntoPruebaPanel(ESRIPoint _point) {
	try {
	    point = _point;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(432, 205));
	panelData.setBounds(new Rectangle(5, 0, 305, 70));
	panelData.setLayout(null);
	tfDescripcion.setBounds(new Rectangle(10, 35, 410, 35));
	rbOpcionA.setBounds(new Rectangle(55, 90, 93, 18));
	rbOpcionB.setBounds(new Rectangle(165, 90, 93, 18));
	rbOpcionC.setBounds(new Rectangle(275, 90, 93, 18));

	bgOpciones.add(rbOpcionA);
	bgOpciones.add(rbOpcionB);
	bgOpciones.add(rbOpcionC);

	panelData.add(rbOpcionC, null);
	panelData.add(rbOpcionB, null);
	panelData.add(rbOpcionA, null);
	rbOpcionA.setSelected(true);
	panelData.add(tfDescripcion, null);

	panelData.add(cbSeleccion, null);
	cbSeleccion.setBounds(new Rectangle(136, 120, 160, 35));
	cbSeleccion.autoSize();

	for (int i = 1; i < 9; i++) {
	    cbSeleccion.addItem("Selección " + i);
	}

	rgRefresh.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
		    setContentObject(point);
		}
	});

	this.add(panelData, BorderLayout.CENTER);
	panelData.setBorder(BorderPanel.getBorderPanel("Datos del Punto Prueba"));
	panelData.setMinimumSize(new Dimension(1, 100));
	panelData.setPreferredSize(new Dimension(1, 110));
	addButton(rgRefresh);
	btnSave.setEnabled(false);
	addButton(btnSave);
    }

    private void clearData() {
	tfDescripcion.setValue("");
	cbSeleccion.setSelectedValue("Selección 1");
	rbOpcionA.setSelected(false);
	rbOpcionB.setSelected(false);
	rbOpcionC.setSelected(false);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Punto prueba");
    }

    @Override
    public void setContentObject(Object _contentObject) {
	if (ESRIPoint.class.isInstance(_contentObject)) {
	    point = (ESRIPoint)_contentObject;
	    clearData();
	    ResultSet _rsPuntoPrueba = LibSQL.exFunction("gea_salta.getPuntoPrueba", point.getIdPoint());
	    try {
		if (_rsPuntoPrueba.next()) {
		    tfDescripcion.setText(_rsPuntoPrueba.getString("descripcion"));
		    String _opcion = _rsPuntoPrueba.getString("opcion");
		    if (_opcion.equalsIgnoreCase("A")) {
			rbOpcionA.setSelected(true);	
		    } else if (_opcion.equalsIgnoreCase("B")) {
			rbOpcionB.setSelected(true);	
		    } else if (_opcion.equalsIgnoreCase("C")) {
			rbOpcionC.setSelected(true);	
		    }
		    cbSeleccion.setSelectedValue("Selección " + _rsPuntoPrueba.getInt("seleccion"));
		}
	    } catch (SQLException e) {
		// TODO: Add catch code
		e.printStackTrace();
	    }
	}
    }

    @Override
    public Object getContentObject() {
	return point;
    }

}
