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
 * GaiaFormComercios.java
 *
 * */
package org.digitall.apps.gaia.forms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.text.DecimalFormat;

import javax.swing.JTextField;

import org.digitall.apps.gaia.classes.GaiaClassComercios;
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


public class GaiaFormComercios extends GaiaPrimitiveForm {

    private BasicPanel panelData = new BasicPanel();
    private GaiaClassComercios comercio;
    private CBInput cbCatComerciales = new CBInput(CachedCombo.CATEGORIASCOMERCIALES_TABS, "Category", false);
    private CBInput cbRubrosComerciales = new CBInput(CachedCombo.RUBROSCOMERCIALES_TABS, "TradingName", false);
    private ESRIPoint point;
    private GaiaCatastroInputPanel tfCatastro = new GaiaCatastroInputPanel();
    private TFInput tfNombre = new TFInput(DataTypes.STRING,"Name", false);
    private TFInput tfActividad = new TFInput(DataTypes.STRING,"GainfulEmployment", false);
    private TFInput tfTelefono1 = new TFInput(DataTypes.STRING,"Phone1", false);
    private TFInput tfTelefono2 = new TFInput(DataTypes.STRING,"Phone2", false);
    private TFInput tfFax = new TFInput(DataTypes.STRING,"Fax", false);
    private TFInput tfPunto = new TFInput(DataTypes.STRING,"Coords", false);

    public GaiaFormComercios() {
	this(new ESRIPoint(0, 0));
    }

    public GaiaFormComercios(ESRIPoint _point) {
	super();
	try {
	    point = _point;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    } 

    private void jbInit() throws Exception {
	setTitle("COMERCIOS");
	this.setSize(new Dimension(432, 308));
	panelData.setBounds(new Rectangle(10, 10, 290, 326));
	panelData.setLayout(null);
        panelData.setPreferredSize(new Dimension(270, 1));
        panelData.setSize(new Dimension(270, 353));
        cbCatComerciales.setBounds(new Rectangle(5, 70, 255, 35));
        cbRubrosComerciales.setBounds(new Rectangle(5, 110, 255, 35));
        tfCatastro.setBounds(new Rectangle(5, 190, 75, 35));
        tfActividad.setBounds(new Rectangle(5, 230, 255, 35));
        tfTelefono1.setBounds(new Rectangle(5, 270, 110, 35));
        tfTelefono2.setBounds(new Rectangle(150, 270, 110, 35));
        tfFax.setBounds(new Rectangle(5, 310, 110, 35));
        tfPunto.setBounds(new Rectangle(5, 30, 255, 35));
        panelData.add(tfNombre, null);
        panelData.add(tfPunto, null);
        panelData.add(tfFax, null);
        panelData.add(tfTelefono2, null);
        panelData.add(tfTelefono1, null);
        panelData.add(tfActividad, null);
        panelData.add(tfCatastro, null);
        panelData.add(cbCatComerciales, null);
        panelData.add(cbRubrosComerciales, null);
        this.setCentralPanel(panelData);
	//panelData.setBorder(BorderPanel.getBorderPanel("Agregar"));
	//panelData.setMinimumSize(new Dimension(1, 100));
	//panelData.setPreferredSize(new Dimension(1, 110));
	cbCatComerciales.autoSize();
        cbRubrosComerciales.autoSize();
	cbRubrosComerciales.setFilter(cbCatComerciales.getSelectedValue());
	cbCatComerciales.getCombo().addItemListener(new ItemListener() {
	    public void itemStateChanged (ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED) {
		   cbRubrosComerciales.setFilter(cbCatComerciales.getSelectedValue());
		}
	    }
	});
        tfPunto.getTextField().setHorizontalAlignment(JTextField.CENTER);
        tfPunto.getTextField().setForeground(Color.red);
        tfPunto.setEditable(false);
        tfNombre.setBounds(new Rectangle(5, 150, 255, 35));
    }

    private void clearData() {
        tfPunto.setValue("");
	tfNombre.setValue("");
        tfCatastro.setValue(0);
        tfActividad.setValue("");
        tfTelefono1.setValue("");
        tfTelefono2.setValue("");
        tfFax.setValue("");
	cbCatComerciales.setSelectedID(0);
        cbRubrosComerciales.setSelectedID(0);
        setEnabledDeleteButton(false);
    }

    public void delete() {
	    if (comercio.delete()) {
		getLayer().getGeometrySet().removeGeometry(point);
		clearData();
	    }
    }

    public void saveData() {
	boolean _isnew = comercio.getIdComercio() == -1;
	comercio.setIdCategoriaComercial(Integer.parseInt(cbCatComerciales.getSelectedValue().toString()));
        comercio.setCatastro(Integer.parseInt(tfCatastro.getValue().toString()));
        comercio.setNombre(tfNombre.getValue().toString());
        comercio.setActividad(tfActividad.getValue().toString());
        comercio.setIdRubroComercial(Integer.parseInt(cbRubrosComerciales.getSelectedValue().toString()));
        comercio.setTelefono1(tfTelefono1.getValue().toString());
        comercio.setTelefono2(tfTelefono2.getValue().toString());
        comercio.setFax(tfFax.getValue().toString());
	if (comercio.saveData() > 0) {
	    getLayer().addLabelValue(point.getIdPoint(), comercio.getNombre());
	    point.setIdPoint(comercio.getIdComercio());
	    point.setSymbol(comercio.getIdCategoriaComercial());
	    clearData();
	    if (_isnew) {
		getLayer().getGeometrySet().addGeometry(point);
	    }
	} else {
	    Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
	}
    }


    public void setComercioObject(GaiaClassComercios _comercio) {
	comercio = _comercio;
	cbCatComerciales.setSelectedID(comercio.getIdCategoriaComercial());
        cbRubrosComerciales.setSelectedID(comercio.getIdRubroComercial());
	loadData();
    }

    private void loadData() {
        tfPunto.setValue("("+ (new DecimalFormat("0.0000")).format(comercio.getX()) +"; "+ (new DecimalFormat("0.0000")).format(comercio.getY()) +")");
	cbCatComerciales.setSelectedID(comercio.getIdCategoriaComercial());
        cbRubrosComerciales.setSelectedID(comercio.getIdRubroComercial());
        tfNombre.setValue(comercio.getNombre());
        tfCatastro.setValue(comercio.getCatastro());
        tfActividad.setValue(comercio.getActividad());
        tfTelefono1.setValue(comercio.getTelefono1());
        tfTelefono2.setValue(comercio.getTelefono2());
        tfFax.setValue(comercio.getFax());
        if (comercio.getIdComercio() != -1)  {
            setEnabledDeleteButton(true);
        } else {
            setEnabledDeleteButton(false);
        }
    }

    @Override
    public void setContentObject(Object _contentObject) {
	if (ESRIPoint.class.isInstance(_contentObject)) {
	    comercio = new GaiaClassComercios();
	    point = (ESRIPoint)_contentObject;
	    comercio.setIdComercio(point.getIdPoint());
	    comercio.retrieveData();
	    comercio.setX(point.getX());
	    comercio.setY(point.getY());
	    setComercioObject(comercio);
	    tfCatastro.fetchCatastro(point);
	}
    }

    @Override
    public Object getContentObject() {
	return comercio;
    }

    @Override
    public void printReport() {
        BasicReport report = new BasicReport(GaiaFormComercios.class.getResource("xml/ComerciosReport.xml"));
        String param = "0,0";
        report.addTableModel("gis_oran.xmlGetComercios", param);
        report.doReport(); 
    }
}
