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
 * GaiaFormAdminyServ.java
 *
 * */
package org.digitall.apps.gaia.forms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import java.text.DecimalFormat;

import javax.swing.JTextField;

import org.digitall.apps.gaia.classes.GaiaClassAdminyServ;
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


public class GaiaFormAdminyServ extends GaiaPrimitiveForm {

    private BasicPanel panelData = new BasicPanel();
    private GaiaClassAdminyServ adminyServ;
    private CBInput cbAdminyServTypes = new CBInput(CachedCombo.ADMINISTRACIONYSERVICIOS_TABS, "Type", true);
    private CBInput cbActividades = new CBInput(CachedCombo.ACTIVIDADES_TABS, "Type", true);
    private ESRIPoint point;
    private GaiaCatastroInputPanel tfCatastro = new GaiaCatastroInputPanel();
    private TFInput tfNombre = new TFInput(DataTypes.STRING,"Name", false);
    private TFInput tfResponsable = new TFInput(DataTypes.STRING,"PersonCharge", false);
    private TFInput tfTelefono1 = new TFInput(DataTypes.STRING,"Phone1", false);
    private TFInput tfTelefono2 = new TFInput(DataTypes.STRING,"Phone2", false);
    private TFInput tfFax = new TFInput(DataTypes.STRING,"Fax", false);
    private TFInput tfPunto = new TFInput(DataTypes.STRING,"Coords",false);

    public GaiaFormAdminyServ() {
	this(new ESRIPoint(0, 0));
    }

    public GaiaFormAdminyServ(ESRIPoint _point) {
	super();
	try {
	    point = _point;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    } 

    private void jbInit() throws Exception {
	setTitle("ADMINISTRACIÓN Y SERVICIOS");
	this.setSize(new Dimension(432, 308));
	panelData.setBounds(new Rectangle(10, 10, 290, 326));
	panelData.setLayout(null);
        panelData.setPreferredSize(new Dimension(270, 1));
        panelData.setSize(new Dimension(270, 328));
        cbAdminyServTypes.setBounds(new Rectangle(5, 45, 255, 35));
        cbActividades.setBounds(new Rectangle(5, 165, 255, 35));
        tfCatastro.setBounds(new Rectangle(5, 125, 75, 35));
        tfResponsable.setBounds(new Rectangle(5, 205, 255, 35));
        tfTelefono1.setBounds(new Rectangle(5, 245, 110, 35));
        tfTelefono2.setBounds(new Rectangle(150, 245, 110, 35));
        tfFax.setBounds(new Rectangle(5, 285, 110, 35));
        tfPunto.setBounds(new Rectangle(5, 5, 255, 35));
	panelData.add(tfNombre, null);
	panelData.add(tfPunto, null);
	panelData.add(tfFax, null);
	panelData.add(tfTelefono2, null);
	panelData.add(tfTelefono1, null);
	panelData.add(tfResponsable, null);
	panelData.add(tfCatastro, null);
	panelData.add(cbAdminyServTypes, null);
	panelData.add(cbActividades, null);
	this.setCentralPanel(panelData);
	//panelData.setBorder(BorderPanel.getBorderPanel("Agregar"));
	//panelData.setMinimumSize(new Dimension(1, 100));
	//panelData.setPreferredSize(new Dimension(1, 110));
	cbAdminyServTypes.autoSize();
        cbActividades.autoSize();
        tfPunto.getTextField().setHorizontalAlignment(JTextField.CENTER);
        tfPunto.getTextField().setForeground(Color.red);
        tfPunto.setEditable(false);
        tfNombre.setBounds(new Rectangle(5, 85, 255, 35));
    }

    private void clearData() {
	tfPunto.setValue("");
        tfNombre.setValue("");
        tfCatastro.setValue(0);
        tfResponsable.setValue("");
        tfTelefono1.setValue("");
        tfTelefono2.setValue("");
        tfFax.setValue("");
	cbAdminyServTypes.setSelectedID(0);
        cbActividades.setSelectedID(0);
    }

    public void delete() {
	    if (adminyServ.delete()) {
		getLayer().getGeometrySet().removeGeometry(point);
		clearData();
	    }
    }

    public void saveData() {
	boolean _isnew = adminyServ.getIdAdminyServ() == -1;
	adminyServ.setIdTipoAdminyServ(Integer.parseInt(cbAdminyServTypes.getSelectedValue().toString()));
        adminyServ.setCatastro(Integer.parseInt(tfCatastro.getValue().toString()));
        adminyServ.setNombre(tfNombre.getValue().toString());
        adminyServ.setResponsable(tfResponsable.getValue().toString());
        adminyServ.setIdTipoActividad(Integer.parseInt(cbActividades.getSelectedValue().toString()));
        adminyServ.setTelefono1(tfTelefono1.getValue().toString());
        adminyServ.setTelefono2(tfTelefono2.getValue().toString());
        adminyServ.setFax(tfFax.getValue().toString());
	if (adminyServ.saveData() > 0) {
	    getLayer().addLabelValue(point.getIdPoint(), adminyServ.getNombre());
	    point.setIdPoint(adminyServ.getIdAdminyServ());
	    point.setSymbol(adminyServ.getIdTipoAdminyServ());
	    clearData();
	    if (_isnew) {
		getLayer().getGeometrySet().addGeometry(point);
	    }
	} else {
	    Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
	}
    }


    public void setAdminyServObject(GaiaClassAdminyServ _adminyServ) {
	adminyServ = _adminyServ;
	cbAdminyServTypes.setSelectedID(adminyServ.getIdTipoAdminyServ());
	loadData();
    }

    private void loadData() {
        tfPunto.setValue("("+ (new DecimalFormat("0.0000")).format(adminyServ.getX()) +"; "+ (new DecimalFormat("0.0000")).format(adminyServ.getY()) +")");
        tfNombre.setValue(adminyServ.getNombre());
	cbAdminyServTypes.setSelectedID(adminyServ.getIdTipoAdminyServ());
        tfCatastro.setValue(adminyServ.getCatastro());
        tfResponsable.setValue(adminyServ.getResponsable());
        cbActividades.setSelectedID(adminyServ.getIdTipoActividad());
        tfTelefono1.setValue(adminyServ.getTelefono1());
        tfTelefono2.setValue(adminyServ.getTelefono2());
        tfFax.setValue(adminyServ.getFax());
        if (adminyServ.getIdAdminyServ() != -1)  {
            setEnabledDeleteButton(true);
        } else {
            setEnabledDeleteButton(false);
        }
    }

    @Override
    public void setContentObject(Object _contentObject) {
	if (ESRIPoint.class.isInstance(_contentObject)) {
	    adminyServ = new GaiaClassAdminyServ();
	    point = (ESRIPoint)_contentObject;
	    adminyServ.setIdAdminyServ(point.getIdPoint());
	    adminyServ.retrieveData();
	    setAdminyServObject(adminyServ);
	    adminyServ.setX(point.getX());
	    adminyServ.setY(point.getY());
	    setAdminyServObject(adminyServ);
	    tfCatastro.fetchCatastro(point);
	}
    }

    @Override
    public Object getContentObject() {
	return adminyServ;
    }

    @Override
    public void printReport() {
        BasicReport report = new BasicReport(GaiaFormAdminyServ.class.getResource("xml/AdminyServReport.xml"));
        String param = "0,0";
        report.addTableModel("gis_oran.xmlGetAdminyServ", param);
        report.doReport(); 
    }
}
