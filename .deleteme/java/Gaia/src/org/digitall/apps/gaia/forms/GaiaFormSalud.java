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
 * GaiaFormSalud.java
 *
 * */
package org.digitall.apps.gaia.forms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import java.text.DecimalFormat;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.digitall.apps.gaia.classes.GaiaClassSalud;
import org.digitall.apps.gaia.components.GaiaCatastroInputPanel;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.JArea;
import org.digitall.lib.components.JTArea;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.esri.ESRIPoint;
import org.digitall.lib.geo.gaia.GaiaPrimitiveForm;

public class GaiaFormSalud extends GaiaPrimitiveForm {

    private BasicPanel panelData = new BasicPanel();
    private GaiaClassSalud salud;
    private CBInput cbTiposEstablecimientos = new CBInput(CachedCombo.ESTABLECIMIENTOS_TABS, "Type", false);
    private ESRIPoint point;
    private GaiaCatastroInputPanel tfCatastro = new GaiaCatastroInputPanel();
    private TFInput tfNombre = new TFInput(DataTypes.STRING,"Name", false);
    private TFInput tfResponsable = new TFInput(DataTypes.STRING,"PersonCharge", false);
    private TFInput tfCamas = new TFInput(DataTypes.INTEGER,"BedsAmount", false);
    private TFInput tfTelefono1 = new TFInput(DataTypes.STRING,"Phone1", false);
    private TFInput tfTelefono2 = new TFInput(DataTypes.STRING,"Phone2", false);
    private TFInput tfFax = new TFInput(DataTypes.STRING,"Fax", false);
    private TFInput tfPunto = new TFInput(DataTypes.STRING,"Coords", false);
    private TFInput tfZonaHospital = new TFInput(DataTypes.STRING,"Zone", false);
    private TFInput tfComplejidad = new TFInput(DataTypes.STRING,"Complexity", false);
    private BasicLabel lblEnfermedades = new BasicLabel();
    private BasicLabel lblProfesionales = new BasicLabel();
    private JArea jtaEnfermedades = new JArea();
    private JArea jtaProfesionales = new JArea();

    public GaiaFormSalud() {
	this(new ESRIPoint(0, 0));
    }

    public GaiaFormSalud(ESRIPoint _point) {
	super();
	try {
	    point = _point;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    } 

    private void jbInit() throws Exception {
	setTitle("SALUD");
	this.setSize(new Dimension(432, 308));
	panelData.setBounds(new Rectangle(10, 10, 290, 326));
	panelData.setLayout(null);
        panelData.setPreferredSize(new Dimension(270, 1));
        panelData.setSize(new Dimension(270, 634));
        cbTiposEstablecimientos.setBounds(new Rectangle(5, 70, 260, 35));
        tfCatastro.setBounds(new Rectangle(5, 150, 75, 35));
        tfNombre.setBounds(new Rectangle(5, 110, 171, 35));
        tfResponsable.setBounds(new Rectangle(5, 270, 255, 35));
        tfCamas.setBounds(new Rectangle(5, 310, 110, 35));
        tfTelefono1.setBounds(new Rectangle(5, 350, 110, 35));
        tfTelefono2.setBounds(new Rectangle(150, 350, 110, 35));
        tfFax.setBounds(new Rectangle(5, 390, 110, 35));
        tfPunto.setBounds(new Rectangle(5, 30, 255, 35));
        panelData.add(lblProfesionales, null);
        panelData.add(jtaProfesionales, null);
        panelData.add(lblEnfermedades, null);
        panelData.add(jtaEnfermedades, null);
        panelData.add(tfComplejidad, null);
        panelData.add(tfZonaHospital, null);
        panelData.add(tfCamas, null);
        panelData.add(tfNombre, null);
        panelData.add(tfPunto, null);
        panelData.add(tfFax, null);
        panelData.add(tfTelefono2, null);
        panelData.add(tfTelefono1, null);
        panelData.add(tfResponsable, null);
        panelData.add(tfCatastro, null);
        panelData.add(cbTiposEstablecimientos, null);
        this.setCentralPanel(panelData);
	//panelData.setBorder(BorderPanel.getBorderPanel("Agregar"));
	//panelData.setMinimumSize(new Dimension(1, 100));
	//panelData.setPreferredSize(new Dimension(1, 110));
	cbTiposEstablecimientos.autoSize();
        tfPunto.getTextField().setHorizontalAlignment(JTextField.CENTER);
        tfPunto.getTextField().setForeground(Color.red);
        tfPunto.setEditable(false);
        tfZonaHospital.setBounds(new Rectangle(5, 190, 235, 35));
        tfComplejidad.setBounds(new Rectangle(5, 230, 255, 35));
        jtaEnfermedades.setBounds(new Rectangle(5, 450, 255, 70));
        lblEnfermedades.setText("Enfermedades");
        lblEnfermedades.setBounds(new Rectangle(5, 430, 110, 20));
        jtaProfesionales.setBounds(new Rectangle(5, 545, 255, 80));
        lblProfesionales.setText("Profesionales");
        lblProfesionales.setBounds(new Rectangle(5, 525, 110, 20));
        tfCamas.setBounds(new Rectangle(5, 310, 110, 35));
        tfNombre.setBounds(new Rectangle(5, 110, 255, 35));
    }

    private void clearData() {
	 tfPunto.setValue("");
	 tfNombre.setValue("");
	 tfCatastro.setValue(0);
	 tfZonaHospital.setValue("");
         tfComplejidad.setValue("");
	 tfResponsable.setValue("");
         tfTelefono1.setValue("");
	 tfTelefono2.setValue("");
	 tfFax.setValue("");
	cbTiposEstablecimientos.setSelectedID(0);
        setEnabledDeleteButton(false);
    }

    public void delete() {
	    if (salud.delete()) {
		getLayer().getGeometrySet().removeGeometry(point);
		clearData();
	    }
    }

    public void saveData() {
	boolean _isnew = salud.getIdSalud() == -1;
	salud.setIdTipoEstablecimiento(Integer.parseInt(cbTiposEstablecimientos.getSelectedValue().toString()));
        salud.setCatastro(Integer.parseInt(tfCatastro.getValue().toString()));
        salud.setZonaHospital(tfZonaHospital.getValue().toString());
        salud.setNombre(tfNombre.getValue().toString());
        salud.setComplejidad(tfComplejidad.getValue().toString());
        salud.setResponsable(tfResponsable.getValue().toString());
        salud.setCamas(Integer.parseInt(tfCamas.getValue().toString()));
        salud.setTelefono1(tfTelefono1.getValue().toString());
        salud.setTelefono2(tfTelefono2.getValue().toString());
        salud.setFax(tfFax.getValue().toString());
        salud.setEnfermedades(jtaEnfermedades.getText());
        salud.setProfesionales(jtaProfesionales.getText());
	if (salud.saveData() > 0) {
	    getLayer().addLabelValue(point.getIdPoint(), salud.getNombre());
	    point.setIdPoint(salud.getIdSalud());
	    point.setSymbol(salud.getIdTipoEstablecimiento());
	    clearData();
	    if (_isnew) {
		getLayer().getGeometrySet().addGeometry(point);
	    }
	} else {
	    Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
	}
    }


    public void setSaludObject(GaiaClassSalud _salud) {
	salud = _salud;
	cbTiposEstablecimientos.setSelectedID(salud.getIdSalud());
	loadData();
    }

    private void loadData() {
        tfPunto.setValue("("+ (new DecimalFormat("0.0000")).format(salud.getX()) +"; "+ (new DecimalFormat("0.0000")).format(salud.getY()) +")");
	cbTiposEstablecimientos.setSelectedID(salud.getIdTipoEstablecimiento());
        tfNombre.setValue(salud.getNombre());
        tfCatastro.setValue(salud.getCatastro());
        tfZonaHospital.setValue(salud.getZonaHospital());
        tfNombre.setValue(salud.getNombre());
        tfComplejidad.setValue(salud.getComplejidad());
        tfCamas.setValue(salud.getCamas());
        tfResponsable.setValue(salud.getResponsable());
        tfTelefono1.setValue(salud.getTelefono1());
        tfTelefono2.setValue(salud.getTelefono2());
        tfFax.setValue(salud.getFax());
        jtaEnfermedades.setText(salud.getEnfermedades());
        jtaProfesionales.setText(salud.getProfesionales());
        if (salud.getIdSalud() != -1)  {
            setEnabledDeleteButton(true);
        } else  {
            setEnabledDeleteButton(false);
        }
    }

    @Override
    public void setContentObject(Object _contentObject) {
	if (ESRIPoint.class.isInstance(_contentObject)) {
	    salud = new GaiaClassSalud();
	    point = (ESRIPoint)_contentObject;
	    salud.setIdSalud(point.getIdPoint());
	    salud.retrieveData();
	    salud.setX(point.getX());
	    salud.setY(point.getY());
	    setSaludObject(salud);
	    tfCatastro.fetchCatastro(point);
	}
    }

    @Override
    public Object getContentObject() {
	return salud;
    }

    @Override
    public void printReport() {
        BasicReport report = new BasicReport(GaiaFormSalud.class.getResource("xml/SaludReport.xml"));
        String param = "0,0";
        report.addTableModel("gis_oran.xmlGetSalud", param);
        report.doReport(); 
    }
}


