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
 * GaiaFormDelito.java
 *
 * */
package org.digitall.apps.gaia.forms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import java.text.DecimalFormat;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.digitall.apps.gaia.classes.GaiaClassDelito;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.geo.esri.ESRIPoint;
import org.digitall.lib.geo.gaia.GaiaPrimitiveForm;
import org.digitall.lib.image.ImageLabel;

public class GaiaFormDelito extends GaiaPrimitiveForm {

    private BasicPanel panelData = new BasicPanel();
    private GaiaClassDelito delito;
    private ESRIPoint point;
    private CBInput cbBarrio = new CBInput(CachedCombo.NEIGHBORHOOD_TABS, "Barrio", false);
    private CBInput cbCalles1 = new CBInput(CachedCombo.STREET_TABS, "Calle 1", false);
    private CBInput cbCalles2 = new CBInput(CachedCombo.STREET_TABS, "Calle 2", false);
    private CBInput cbMovilidadVictima = new CBInput(0, "Movilidad de las Víctimas", false);
    private CBInput cbMovilidadDelincuentes = new CBInput(0, "Movilidad de los Delincuentes", false);
    private CBInput cbTipoDelito = new CBInput(0, "Delito", false);
    private TFInput tfAlturaCalle1 = new TFInput(DataTypes.STRING, "Altura", false);
    private TFInput tfAlturaCalle2 = new TFInput(DataTypes.STRING, "Altura", false);
    private TFInput tfFechaDelito = new TFInput(DataTypes.DATE, "Fecha", false);
    private TFInput tfHoraDelito = new TFInput(DataTypes.STRING, "Hora", false);
    private TFInput tfCantDelincuentes = new TFInput(DataTypes.INTEGER, "Delincuentes", false);
    private TFInput tfCantVictimas = new TFInput(DataTypes.INTEGER, "Víctimas", false);
    private TFInput tfPunto = new TFInput(DataTypes.STRING, "Coords", false);
    private TFInput tfSearchCalle1 = new TFInput(DataTypes.STRING, "Buscar Calle 1", false);
    private TFInput tfSearchCalle2 = new TFInput(DataTypes.STRING, "Buscar Calle 2", false);
    private ImageLabel lblPhoto = new ImageLabel();

    public GaiaFormDelito() {
	this(new ESRIPoint(0, 0));
    }

    public GaiaFormDelito(ESRIPoint _point) {
	super();
	try {
	    point = _point;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setTitle("CASO DELICTIVO");
	this.setSize(new Dimension(432, 308));
	panelData.setBounds(new Rectangle(10, 10, 290, 326));
	panelData.setLayout(null);
	panelData.setPreferredSize(new Dimension(270, 1));
	panelData.setSize(new Dimension(257, 552));
	cbCalles1.setBounds(new Rectangle(5, 150, 195, 35));
	cbMovilidadVictima.setBounds(new Rectangle(5, 270, 245, 35));
	cbMovilidadDelincuentes.setBounds(new Rectangle(5, 350, 245, 35));
	cbTipoDelito.setBounds(new Rectangle(5, 310, 245, 35));
	cbCalles2.setBounds(new Rectangle(5, 230, 195, 35));
	cbBarrio.setBounds(new Rectangle(5, 70, 245, 35));
	tfAlturaCalle1.setBounds(new Rectangle(5, 110, 171, 35));
	tfAlturaCalle2.setBounds(new Rectangle(205, 230, 45, 35));
	tfFechaDelito.setBounds(new Rectangle(5, 310, 110, 35));
	tfHoraDelito.setBounds(new Rectangle(5, 430, 85, 35));
	tfCantDelincuentes.setBounds(new Rectangle(5, 470, 85, 35));
	tfCantVictimas.setBounds(new Rectangle(5, 510, 85, 35));
	tfPunto.setBounds(new Rectangle(5, 30, 245, 35));
	panelData.add(lblPhoto, null);
	panelData.add(tfSearchCalle2, null);
	panelData.add(tfSearchCalle1, null);
	panelData.add(tfFechaDelito, null);
	panelData.add(tfAlturaCalle1, null);
	panelData.add(tfPunto, null);
	panelData.add(tfCantVictimas, null);
	panelData.add(tfCantDelincuentes, null);
	panelData.add(tfHoraDelito, null);
	panelData.add(tfAlturaCalle2, null);
	panelData.add(cbCalles1, null);
	panelData.add(cbCalles2, null);
	panelData.add(cbBarrio, null);
	panelData.add(cbMovilidadVictima, null);
	panelData.add(cbMovilidadDelincuentes, null);
	panelData.add(cbTipoDelito, null);
	this.setCentralPanel(panelData);
	cbBarrio.autoSize();
	cbCalles1.autoSize();
	cbCalles2.autoSize();
	cbMovilidadVictima.autoSize();
	cbMovilidadDelincuentes.autoSize();
	cbTipoDelito.autoSize();
	tfPunto.getTextField().setHorizontalAlignment(JTextField.CENTER);
	tfPunto.getTextField().setForeground(Color.red);
	tfPunto.setEditable(false);
	tfSearchCalle1.setBounds(new Rectangle(5, 110, 195, 35));
	tfSearchCalle2.setBounds(new Rectangle(5, 190, 195, 35));
	lblPhoto.setBounds(new Rectangle(95, 395, 155, 150));
	tfFechaDelito.setBounds(new Rectangle(5, 390, 85, 35));
	tfAlturaCalle1.setBounds(new Rectangle(205, 150, 45, 35));
	cbMovilidadVictima.loadJCombo("SELECT idtipomovilidad, nombre, 0 FROM tabs.tiposmovilidad");
	cbTipoDelito.loadJCombo("SELECT idtipo, nombre, 0 FROM tabs.casosdelitos_tabs");
	cbMovilidadDelincuentes.loadJCombo("SELECT idtipomovilidad, nombre, 0 FROM tabs.tiposmovilidad");
    }

    private void clearData() {
	tfPunto.setValue("");
	tfAlturaCalle1.setValue("");
	tfAlturaCalle2.setValue("");
	tfHoraDelito.setValue("");
	tfCantDelincuentes.setValue("");
	tfCantVictimas.setValue("");
	cbCalles1.setSelectedID(0);
	cbBarrio.setSelectedID(0);
	setEnabledDeleteButton(false);
    }

    public void delete() {
	if (delito.delete()) {
	    getLayer().getGeometrySet().removeGeometry(point);
	    clearData();
	}
    }

    public void saveData() {
	boolean _isnew = delito.getIdDelito() == -1;
	delito.setIdBarrio(Integer.parseInt(cbBarrio.getSelectedValue().toString()));
	delito.setIdCalle1(Integer.parseInt(cbCalles1.getSelectedValue().toString()));
	delito.setAlturaCalle1(Integer.parseInt(tfAlturaCalle1.getValue().toString()));
	delito.setIdCalle2(Integer.parseInt(cbCalles2.getSelectedValue().toString()));
	delito.setAlturaCalle2(Integer.parseInt(tfAlturaCalle2.getValue().toString()));
	delito.setIdMovilidadVictima(Integer.parseInt(cbMovilidadVictima.getSelectedValue().toString()));
	delito.setIdTipoDelito(Integer.parseInt(cbTipoDelito.getSelectedValue().toString()));
	delito.setFechaDelito(Proced.setFormatDate(tfFechaDelito.getDate().toString(), false));
	delito.setHoraDelito(tfHoraDelito.getValue().toString());
	delito.setCantDelincuentes(Integer.parseInt(tfCantDelincuentes.getValue().toString()));
	delito.setCantVictimas(Integer.parseInt(tfCantVictimas.getValue().toString()));
	delito.setIdMovilidadDelincuentes(Integer.parseInt(cbMovilidadDelincuentes.getSelectedValue().toString()));
	if (delito.saveData() > 0) {
	    //point.setLabel(delito.getNombre());
	    point.setIdPoint(delito.getIdDelito());
	    point.setSymbol(delito.getIdTipoDelito());
	    clearData();
	    if (_isnew) {
		getLayer().getGeometrySet().addGeometry(point);
	    }
	} else {
	    Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
	}
    }

    public void setDelitoObject(GaiaClassDelito _delito) {
	delito = _delito;
	//cbCalles1.setSelectedID(delito.getIdDelito());
	loadData();
    }

    private void loadData() {
	tfPunto.setValue("(" + (new DecimalFormat("0.0000")).format(delito.getX()) + "; " + (new DecimalFormat("0.0000")).format(delito.getY()) + ")");
	cbBarrio.setSelectedID(delito.getIdBarrio());
	cbCalles1.setSelectedID(delito.getIdCalle1());
	tfAlturaCalle1.setValue(delito.getAlturaCalle1());
	cbCalles2.setSelectedID(delito.getIdCalle2());
	tfAlturaCalle2.setValue(delito.getAlturaCalle2());
	cbMovilidadVictima.setSelectedID(delito.getIdMovilidadVictima());
	cbTipoDelito.setSelectedID(delito.getIdTipoDelito());
	tfFechaDelito.setValue(Proced.setFormatDate(delito.getFechaDelito(), true));
	tfHoraDelito.setValue(delito.getHoraDelito());
	tfCantDelincuentes.setValue(delito.getCantDelincuentes());
	tfCantVictimas.setValue(delito.getCantVictimas());
	cbMovilidadDelincuentes.setSelectedID(delito.getIdMovilidadDelincuentes());
	if (delito.getIdDelito() != -1) {
	    setEnabledDeleteButton(true);
	} else {
	    setEnabledDeleteButton(false);
	}
    }

    @Override
    public void setContentObject(Object _contentObject) {
	if (ESRIPoint.class.isInstance(_contentObject)) {
	    delito = new GaiaClassDelito();
	    point = (ESRIPoint)_contentObject;
	    delito.setIdDelito(point.getIdPoint());
	    delito.retrieveData();
	    delito.setX(point.getX());
	    delito.setY(point.getY());
	    setDelitoObject(delito);
	}
    }

    @Override
    public Object getContentObject() {
	return delito;
    }

    @Override
    public void printReport() {
	/*BasicReport report = new BasicReport(GaiaFormDelito.class.getResource("xml/DelitoReport.xml"));
        String param = "0,0";
        report.addTableModel("gis_oran.xmlGetSalud", param);
        report.doReport(); */
    }

}
