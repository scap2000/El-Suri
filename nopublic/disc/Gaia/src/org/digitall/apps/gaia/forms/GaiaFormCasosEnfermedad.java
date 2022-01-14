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
 * GaiaFormCasosEnfermedad.java
 *
 * */
package org.digitall.apps.gaia.forms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import java.text.DecimalFormat;

import javax.swing.JTextField;

import org.digitall.apps.gaia.classes.GaiaClassCasoEnfermedad;
import org.digitall.apps.gaia.components.GaiaCatastroInputPanel;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.JArea;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.geo.esri.ESRIPoint;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.geo.gaia.GaiaPrimitiveForm;

public class GaiaFormCasosEnfermedad extends GaiaPrimitiveForm {

    private BasicPanel panelData = new BasicPanel();
    private GaiaClassCasoEnfermedad casoEnfermedad;
    private CBInput cbEnfermedades = new CBInput(CachedCombo.ENFERMEDADES_TABS, "Enfermedad", false);
    private CBInput cbTiposCaso = new CBInput(CachedCombo.CASOSENFERMEDADES_TABS, "Tipo de Caso", false);
    private CBInput cbTiposPrevencion = new CBInput(CachedCombo.TIPOSPREVENCIONENFERMEDADES_TABS, "Tipo de Prevención", false);
    private ESRIPoint point;
    private GaiaCatastroInputPanel tfCatastro = new GaiaCatastroInputPanel();
    private TFInput tfNombre = new TFInput(DataTypes.STRING, "Name", false);
    private TFInput tfPunto = new TFInput(DataTypes.STRING, "Coords", false);
    private TFInput tfZona = new TFInput(DataTypes.STRING, "Zone", false);
    private BasicLabel lblObservaciones = new BasicLabel();
    private JArea jtaObservaciones = new JArea();

    public GaiaFormCasosEnfermedad() {
	this(new ESRIPoint(0, 0));
    }

    public GaiaFormCasosEnfermedad(ESRIPoint _point) {
	super();
	try {
	    point = _point;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setTitle("Control y Prev. de Enfermedades Endémicas");
	this.setSize(new Dimension(432, 308));
	panelData.setBounds(new Rectangle(10, 10, 290, 326));
	panelData.setLayout(null);
	panelData.setPreferredSize(new Dimension(270, 1));
	panelData.setSize(new Dimension(270, 407));
	cbEnfermedades.setBounds(new Rectangle(5, 70, 260, 35));
	cbTiposCaso.setBounds(new Rectangle(5, 110, 260, 35));
	cbTiposPrevencion.setBounds(new Rectangle(5, 150, 260, 35));
	tfCatastro.setBounds(new Rectangle(5, 230, 75, 35));
	tfNombre.setBounds(new Rectangle(5, 110, 171, 35));
	tfPunto.setBounds(new Rectangle(5, 30, 255, 35));
	panelData.add(lblObservaciones, null);
	panelData.add(jtaObservaciones, null);
	panelData.add(tfZona, null);
	panelData.add(tfNombre, null);
	panelData.add(tfPunto, null);
	panelData.add(tfCatastro, null);
	panelData.add(cbEnfermedades, null);
	panelData.add(cbTiposCaso, null);
	panelData.add(cbTiposPrevencion, null);
	this.setCentralPanel(panelData);
	//panelData.setBorder(BorderPanel.getBorderPanel("Agregar"));
	//panelData.setMinimumSize(new Dimension(1, 100));
	//panelData.setPreferredSize(new Dimension(1, 110));
	cbEnfermedades.autoSize();
	cbTiposCaso.autoSize();
	cbTiposPrevencion.autoSize();
	tfPunto.getTextField().setHorizontalAlignment(JTextField.CENTER);
	tfPunto.getTextField().setForeground(Color.red);
	tfPunto.setEditable(false);
	tfZona.setBounds(new Rectangle(5, 270, 235, 35));
	jtaObservaciones.setBounds(new Rectangle(5, 330, 255, 70));
	lblObservaciones.setText("Observaciones");
	lblObservaciones.setBounds(new Rectangle(5, 310, 110, 20));
	tfNombre.setBounds(new Rectangle(5, 190, 255, 35));
	setEnabledPrintButton(false);
    }

    private void clearData() {
	tfPunto.setValue("");
	tfNombre.setValue("");
	tfCatastro.setValue(0);
	tfZona.setValue("");
	jtaObservaciones.setText("");
	cbEnfermedades.setSelectedID(0);
	cbTiposCaso.setSelectedID(0);
	cbTiposPrevencion.setSelectedID(0);
	setEnabledDeleteButton(false);
    }

    public void delete() {
	if (casoEnfermedad.delete()) {
	    getLayer().getGeometrySet().removeGeometry(point);
	    clearData();
	}
    }

    public void saveData() {
	boolean _isnew = casoEnfermedad.getIdCaso() == -1;
	casoEnfermedad.setIdEnfermedad(Integer.parseInt(cbEnfermedades.getSelectedValue().toString()));
	casoEnfermedad.setIdTipoCaso(Integer.parseInt(cbTiposCaso.getSelectedValue().toString()));
	casoEnfermedad.setIdTipoPrevencion(Integer.parseInt(cbTiposPrevencion.getSelectedValue().toString()));
	casoEnfermedad.setCatastro(Integer.parseInt(tfCatastro.getValue().toString()));
	casoEnfermedad.setZona(tfZona.getValue().toString());
	casoEnfermedad.setNombre(tfNombre.getValue().toString());
	casoEnfermedad.setObservaciones(jtaObservaciones.getText());
	if (casoEnfermedad.saveData() > 0) {
	    getLayer().addLabelValue(point.getIdPoint(), casoEnfermedad.getNombre());
	    point.setIdPoint(casoEnfermedad.getIdCaso());
	    point.setSymbol(casoEnfermedad.getIdEnfermedad());
	    clearData();
	    if (_isnew) {
		getLayer().getGeometrySet().addGeometry(point);
	    }
	} else {
	    Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
	}
    }

    public void setCasoEnfermedadObject(GaiaClassCasoEnfermedad _casoEnfermedad) {
	casoEnfermedad = _casoEnfermedad;
	cbEnfermedades.setSelectedID(casoEnfermedad.getIdEnfermedad());
	cbTiposCaso.setSelectedID(casoEnfermedad.getIdTipoCaso());
	cbTiposPrevencion.setSelectedID(casoEnfermedad.getIdTipoPrevencion());
	loadData();
    }

    private void loadData() {
	tfPunto.setValue("(" + (new DecimalFormat("0.0000")).format(casoEnfermedad.getX()) + "; " + (new DecimalFormat("0.0000")).format(casoEnfermedad.getY()) + ")");
	cbEnfermedades.setSelectedID(casoEnfermedad.getIdEnfermedad());
	cbTiposCaso.setSelectedID(casoEnfermedad.getIdTipoCaso());
	cbTiposPrevencion.setSelectedID(casoEnfermedad.getIdTipoPrevencion());
	tfNombre.setValue(casoEnfermedad.getNombre());
	tfCatastro.setValue(casoEnfermedad.getCatastro());
	tfZona.setValue(casoEnfermedad.getZona());
	tfNombre.setValue(casoEnfermedad.getNombre());
	jtaObservaciones.setText(casoEnfermedad.getObservaciones());
	if (casoEnfermedad.getIdCaso() != -1) {
	    setEnabledDeleteButton(true);
	} else {
	    setEnabledDeleteButton(false);
	}
    }

    @Override
    public void setContentObject(Object _contentObject) {
	if (ESRIPoint.class.isInstance(_contentObject)) {
	    casoEnfermedad = new GaiaClassCasoEnfermedad();
	    point = (ESRIPoint)_contentObject;
	    casoEnfermedad.setIdCaso(point.getIdPoint());
	    casoEnfermedad.retrieveData();
	    casoEnfermedad.setX(point.getX());
	    casoEnfermedad.setY(point.getY());
	    setCasoEnfermedadObject(casoEnfermedad);
	    tfCatastro.fetchCatastro(point);
	}
    }

    @Override
    public Object getContentObject() {
	return casoEnfermedad;
    }

    @Override
    public void printReport() {
	BasicReport report = new BasicReport(GaiaFormCasosEnfermedad.class.getResource("xml/CasosEnfermedadReport.xml"));
	report.addTableModel(GaiaEnvironment.getScheme() + ".xmlGetCasosEnfermedad", "0,0");
	report.doReport();
    }

}
