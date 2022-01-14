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
 * GaiaFormTurismo.java
 *
 * */
package org.digitall.apps.gaia.forms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import java.text.DecimalFormat;

import javax.swing.JTextField;

import org.digitall.apps.gaia.classes.GaiaClassTurismo;
import org.digitall.apps.gaia.components.GaiaCatastroInputPanel;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.esri.ESRIPoint;
import org.digitall.lib.geo.gaia.GaiaPrimitiveForm;

public class GaiaFormTurismo extends GaiaPrimitiveForm {

    private BasicPanel panelData = new BasicPanel();
    private GaiaClassTurismo turismo;
    private CBInput cbTipoInstTur = new CBInput(CachedCombo.INSTALACIONESTURISTICAS_TABS, "Type", false);
    private CBInput cbCategoriaTur = new CBInput(CachedCombo.CATEGORIASTURISTICAS_TABS, "Category", false);
    private ESRIPoint point;
    private GaiaCatastroInputPanel tfCatastro = new GaiaCatastroInputPanel();
    private TFInput tfNombre = new TFInput(DataTypes.STRING,"Name", false);
    private TFInput tfEspecialidad = new TFInput(DataTypes.STRING,"Speciality", false);
    private TFInput tfHabitaciones = new TFInput(DataTypes.INTEGER,"RoomsAmount", false);
    private TFInput tfTelefono1 = new TFInput(DataTypes.STRING,"Phone1", false);
    private TFInput tfTelefono2 = new TFInput(DataTypes.STRING,"Phone2", false);
    private TFInput tfFax = new TFInput(DataTypes.STRING,"Fax", false);
    private TFInput tfPunto = new TFInput(DataTypes.STRING,"Coords", false);

    public GaiaFormTurismo() {
	this(new ESRIPoint(0, 0));
    }

    public GaiaFormTurismo(ESRIPoint _point) {
	super();
	try {
	    point = _point;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    } 

    private void jbInit() throws Exception {
	setTitle("TURISMO");
	this.setSize(new Dimension(432, 308));
	panelData.setBounds(new Rectangle(10, 10, 290, 326));
	panelData.setLayout(null);
        panelData.setPreferredSize(new Dimension(270, 1));
        panelData.setSize(new Dimension(270, 392));
        cbTipoInstTur.setBounds(new Rectangle(5, 70, 260, 35));
        cbCategoriaTur.setBounds(new Rectangle(5, 110, 260, 35));
        tfCatastro.setBounds(new Rectangle(5, 190, 75, 35));
        tfEspecialidad.setBounds(new Rectangle(5, 230, 255, 35));
        tfTelefono1.setBounds(new Rectangle(5, 310, 110, 35));
        tfTelefono2.setBounds(new Rectangle(150, 310, 110, 35));
        tfFax.setBounds(new Rectangle(5, 350, 110, 35));
        tfPunto.setBounds(new Rectangle(5, 30, 255, 35));
        panelData.add(tfHabitaciones, null);
        panelData.add(tfNombre, null);
        panelData.add(tfPunto, null);
        panelData.add(tfFax, null);
        panelData.add(tfTelefono2, null);
        panelData.add(tfTelefono1, null);
        panelData.add(tfEspecialidad, null);
        panelData.add(tfCatastro, null);
        panelData.add(cbTipoInstTur, null);
        panelData.add(cbCategoriaTur, null);
        this.setCentralPanel(panelData);
	//panelData.setBorder(BorderPanel.getBorderPanel("Agregar"));
	//panelData.setMinimumSize(new Dimension(1, 100));
	//panelData.setPreferredSize(new Dimension(1, 110));
	cbTipoInstTur.autoSize();
        cbCategoriaTur.autoSize();
        tfPunto.getTextField().setHorizontalAlignment(JTextField.CENTER);
        tfPunto.getTextField().setForeground(Color.red);
        tfPunto.setEditable(false);
        tfHabitaciones.setBounds(new Rectangle(5, 270, 110, 35));
        tfNombre.setBounds(new Rectangle(5, 150, 255, 35));
    }

    private void clearData() {
        tfPunto.setValue("");
        tfNombre.setValue("");
        tfCatastro.setValue(0);
        tfHabitaciones.setValue(0);
        tfEspecialidad.setValue("");
        tfTelefono1.setValue("");
        tfTelefono2.setValue("");
        tfFax.setValue("");
	cbTipoInstTur.setSelectedID(0);
        cbCategoriaTur.setSelectedID(0);
        setEnabledDeleteButton(false);
    }

    public void delete() {
	    if (turismo.delete()) {
		getLayer().getGeometrySet().removeGeometry(point);
	        clearData();
	    }
    }

    public void saveData() {
	boolean _isnew = turismo.getIdTurismo() == -1;
	turismo.setIdTipoInstTur(Integer.parseInt(cbTipoInstTur.getSelectedValue().toString()));
        turismo.setIdCatTur(Integer.parseInt(cbCategoriaTur.getSelectedValue().toString()));
        turismo.setCatastro(Integer.parseInt(tfCatastro.getValue().toString()));
        turismo.setNombre(tfNombre.getValue().toString());
        turismo.setHabitaciones(Integer.parseInt(tfHabitaciones.getValue().toString()));
        turismo.setEspecialidad(tfEspecialidad.getValue().toString());
        turismo.setTelefono1(tfTelefono1.getValue().toString());
        turismo.setTelefono2(tfTelefono2.getValue().toString());
        turismo.setFax(tfFax.getValue().toString());
	if (turismo.saveData() > 0) {
	    getLayer().addLabelValue(point.getIdPoint(), turismo.getNombre());
	    point.setIdPoint(turismo.getIdTurismo());
	    point.setSymbol(turismo.getIdTipoInstTur());
	    clearData();
	    if (_isnew) {
		getLayer().getGeometrySet().addGeometry(point);
	    }
	} else {
	    Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
	}
    }


    public void setTurismoObject(GaiaClassTurismo _turismo) {
	turismo = _turismo;
	cbTipoInstTur.setSelectedID(turismo.getIdTurismo());
        cbCategoriaTur.setSelectedID(turismo.getIdTipoInstTur());
	loadData();
    }

    private void loadData() {
        tfPunto.setValue("("+ (new DecimalFormat("0.0000")).format(turismo.getX()) +"; "+ (new DecimalFormat("0.0000")).format(turismo.getY()) +")");
	cbTipoInstTur.setSelectedID(turismo.getIdTipoInstTur());
        cbCategoriaTur.setSelectedID(turismo.getIdCatTur());
        tfNombre.setValue(turismo.getNombre());
        tfCatastro.setValue(turismo.getCatastro());
        tfHabitaciones.setValue(turismo.getHabitaciones());
        tfEspecialidad.setValue(turismo.getEspecialidad());
        tfTelefono1.setValue(turismo.getTelefono1());
        tfTelefono2.setValue(turismo.getTelefono2());
        tfFax.setValue(turismo.getFax());
        if (turismo.getIdTurismo() != -1)  {
            setEnabledDeleteButton(true);
        } else  {
            setEnabledDeleteButton(false);
        }
    }

    @Override
    public void setContentObject(Object _contentObject) {
	if (ESRIPoint.class.isInstance(_contentObject)) {
	    turismo = new GaiaClassTurismo();
	    point = (ESRIPoint)_contentObject;
	    turismo.setIdTurismo(point.getIdPoint());
	    turismo.retrieveData();
	    turismo.setX(point.getX());
	    turismo.setY(point.getY());
	    setTurismoObject(turismo);
	    tfCatastro.fetchCatastro(point);
	}
    }

    @Override
    public Object getContentObject() {
	return turismo;
    }

    @Override
    public void printReport() {
        BasicReport report = new BasicReport(GaiaFormTurismo.class.getResource("xml/TurismoReport.xml"));
        String param = "0,0";
        report.addTableModel("gis_oran.xmlGetTurismo", param);
        report.doReport(); 
    }
}

