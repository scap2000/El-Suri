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
 * GaiaFormEducacion.java
 *
 * */
package org.digitall.apps.gaia.forms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import java.text.DecimalFormat;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.digitall.apps.gaia.classes.GaiaClassEducacion;
import org.digitall.apps.gaia.components.GaiaCatastroInputPanel;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.esri.ESRIPoint;
import org.digitall.lib.geo.gaia.GaiaPrimitiveForm;

public class GaiaFormEducacion extends GaiaPrimitiveForm {

    private BasicPanel panelData = new BasicPanel();
    private GaiaClassEducacion educacion;
    private CBInput cbNivelesEducativos = new CBInput(CachedCombo.NIVELESEDUCATIVOS_TABS, "Level", false);
    private CBInput cbOrientacionesEducativas = new CBInput(CachedCombo.ORIENTACIONESEDUCATIVAS_TABS, "Orientation", false);
    private ESRIPoint point;
    private TFInput tfPunto = new TFInput(DataTypes.STRING,"Coords", false);
    private GaiaCatastroInputPanel tfCatastro = new GaiaCatastroInputPanel();
    private TFInput tfNombre = new TFInput(DataTypes.STRING,"Name", false);
    private TFInput tfNroEstablecimiento = new TFInput(DataTypes.INTEGER,"SchoolNumber", false);
    private TFInput tfCantEgresados = new TFInput(DataTypes.INTEGER,"GraduatesAmount", false);
    private TFInput tfCantDocentes = new TFInput(DataTypes.INTEGER,"TeachersAmount", false);
    private TFInput tfCantAlumnos = new TFInput(DataTypes.INTEGER,"StudentsAmount", false);

    public GaiaFormEducacion() {
	this(new ESRIPoint(0, 0));
    }

    public GaiaFormEducacion(ESRIPoint _point) {
	super();
	try {
	    point = _point;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    } 

    private void jbInit() throws Exception {
	setTitle("EDUCACION");
	this.setSize(new Dimension(432, 308));
	panelData.setBounds(new Rectangle(10, 10, 290, 326));
	panelData.setLayout(null);
        panelData.setPreferredSize(new Dimension(270, 1));
        panelData.setSize(new Dimension(270, 310));
        cbNivelesEducativos.setBounds(new Rectangle(5, 150, 260, 35));
        cbNivelesEducativos.setBounds(new Rectangle(5, 150, 260, 35));
        cbOrientacionesEducativas.setBounds(new Rectangle(5, 190, 260, 35));
        tfCatastro.setBounds(new Rectangle(185, 110, 75, 35));
        tfNombre.setBounds(new Rectangle(5, 110, 171, 35));
        tfNroEstablecimiento.setBounds(new Rectangle(5, 310, 110, 35));
        tfCantEgresados.setBounds(new Rectangle(5, 270, 100, 35));
        tfPunto.setBounds(new Rectangle(5, 30, 255, 35));
        panelData.add(tfCantAlumnos, null);
        panelData.add(tfCantDocentes, null);
        panelData.add(tfNroEstablecimiento, null);
        panelData.add(tfNombre, null);
        panelData.add(tfPunto, null);
        panelData.add(tfCantEgresados, null);
        panelData.add(tfCatastro, null);
        panelData.add(cbNivelesEducativos, null);
        panelData.add(cbOrientacionesEducativas, null);
        this.setCentralPanel(panelData);
	//panelData.setBorder(BorderPanel.getBorderPanel("Agregar"));
	//panelData.setMinimumSize(new Dimension(1, 100));
	//panelData.setPreferredSize(new Dimension(1, 110));
	cbNivelesEducativos.autoSize();
        cbOrientacionesEducativas.autoSize();
        tfPunto.getTextField().setHorizontalAlignment(JTextField.CENTER);
        tfPunto.getTextField().setForeground(Color.red);
        tfPunto.setEditable(false);
        tfCantDocentes.setBounds(new Rectangle(5, 230, 100, 35));
        tfCantAlumnos.setBounds(new Rectangle(165, 230, 100, 35));
        tfNroEstablecimiento.setBounds(new Rectangle(5, 110, 75, 35));
        tfNombre.setBounds(new Rectangle(5, 70, 255, 35));
    }

    private void clearData() {
        tfPunto.setValue("");
        tfNombre.setValue("");
        tfCatastro.setValue(0);
        tfNroEstablecimiento.setValue("");
        tfCantDocentes.setValue(0);
        tfCantAlumnos.setValue(0);
        tfCantEgresados.setValue(0);
	cbNivelesEducativos.setSelectedID(0);
        cbOrientacionesEducativas.setSelectedID(0);
        setEnabledDeleteButton(false);
    }

    public void delete() {
	    if (educacion.delete()) {
		getLayer().getGeometrySet().removeGeometry(point);
	        clearData();
	    }
    }

    public void saveData() {
	boolean _isnew = educacion.getIdEducacion() == -1;
        educacion.setNroestablecimiento(tfNroEstablecimiento.getValue().toString());
        educacion.setCatastro(Integer.parseInt(tfCatastro.getValue().toString()));
        educacion.setNombre(tfNombre.getValue().toString());
        educacion.setDocentes(Integer.parseInt(tfCantDocentes.getValue().toString()));
        educacion.setAlumnos(Integer.parseInt(tfCantAlumnos.getValue().toString()));
	educacion.setIdniveleducativo(Integer.parseInt(cbNivelesEducativos.getSelectedValue().toString()));
        educacion.setIdOrientacionEducativa(Integer.parseInt(cbOrientacionesEducativas.getSelectedValue().toString()));
        educacion.setCantegresados(tfCantEgresados.getValue().toString());
	if (educacion.saveData() > 0) {
	    getLayer().addLabelValue(point.getIdPoint(), educacion.getNombre());
	    point.setIdPoint(educacion.getIdEducacion());
	    point.setSymbol(educacion.getIdniveleducativo());
	    clearData();
	    if (_isnew) {
		getLayer().getGeometrySet().addGeometry(point);
	    }
	} else {
	    Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
	}
    }


    public void setEducacionObject(GaiaClassEducacion _educacion) {
	educacion = _educacion;
	cbNivelesEducativos.setSelectedID(educacion.getIdniveleducativo());
	loadData();
    }

    private void loadData() {
        tfPunto.setValue("("+ (new DecimalFormat("0.0000")).format(educacion.getX()) +"; "+ (new DecimalFormat("0.0000")).format(educacion.getY()) +")");
        tfNroEstablecimiento.setValue(educacion.getNroestablecimiento());
        tfCatastro.setValue(educacion.getCatastro());
        tfNombre.setValue(educacion.getNombre());
        tfCantDocentes.setValue(educacion.getDocentes());
        tfCantAlumnos.setValue(educacion.getAlumnos());
	cbNivelesEducativos.setSelectedID(educacion.getIdniveleducativo());
        cbOrientacionesEducativas.setSelectedID(educacion.getIdOrientacionEducativa());
        tfCantEgresados.setValue(educacion.getCantegresados());
        if (educacion.getIdEducacion() != -1)  {
            setEnabledDeleteButton(true);
        } else  {
            setEnabledDeleteButton(false);
        }
    }

    @Override
    public void setContentObject(Object _contentObject) {
	if (ESRIPoint.class.isInstance(_contentObject)) {
	    educacion = new GaiaClassEducacion();
	    point = (ESRIPoint)_contentObject;
	    educacion.setIdEducacion(point.getIdPoint());
	    educacion.retrieveData();
	    educacion.setX(point.getX());
	    educacion.setY(point.getY());
	    setEducacionObject(educacion);
	    tfCatastro.fetchCatastro(point);
	}
    }

    @Override
    public Object getContentObject() {
	return educacion;
    }

    @Override
    public void printReport() {
        BasicReport report = new BasicReport(GaiaFormEducacion.class.getResource("xml/EducacionReport.xml"));
        String param = "0,0";
        report.addTableModel("gis_oran.xmlGetEducacion", param);
        report.doReport(); 
    }
}


