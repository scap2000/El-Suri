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
