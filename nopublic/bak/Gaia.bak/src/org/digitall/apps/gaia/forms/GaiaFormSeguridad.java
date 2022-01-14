package org.digitall.apps.gaia.forms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import java.text.DecimalFormat;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.digitall.apps.gaia.classes.GaiaClassSeguridad;
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

public class GaiaFormSeguridad extends GaiaPrimitiveForm {

    private BasicPanel panelData = new BasicPanel();
    private GaiaClassSeguridad seguridad;
    private CBInput cbSeguridadTypes = new CBInput(CachedCombo.SEGURIDAD_TABS, "Type", false);
    private ESRIPoint point;
    private TFInput tfNombre = new TFInput(DataTypes.STRING, "Name", false);
    private GaiaCatastroInputPanel tfCatastro = new GaiaCatastroInputPanel();
    private TFInput tfResponsable = new TFInput(DataTypes.STRING,"PersonCharge", false);
    private TFInput tfCantEfectivos = new TFInput(DataTypes.INTEGER,"OfficersAmount", false);
    private TFInput tfTelefono1 = new TFInput(DataTypes.STRING,"Phone1", false);
    private TFInput tfTelefono2 = new TFInput(DataTypes.STRING,"Phone2", false);
    private TFInput tfEmail = new TFInput(DataTypes.STRING,"Email", false);
    private TFInput tfFax = new TFInput(DataTypes.STRING,"Fax", false);
    private TFInput tfPunto = new TFInput(DataTypes.STRING, "Coords", false);

    public GaiaFormSeguridad() {
	this(new ESRIPoint(0, 0));
    }

    public GaiaFormSeguridad(ESRIPoint _point) {
	super();
	try {
	    point = _point;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setTitle("SEGURIDAD");
	this.setSize(new Dimension(432, 308));
	panelData.setBounds(new Rectangle(10, 10, 290, 326));
	panelData.setLayout(null);
        panelData.setPreferredSize(new Dimension(270, 1));
        panelData.setSize(new Dimension(270, 395));
        tfNombre.setBounds(new Rectangle(5, 110, 255, 35));
	cbSeguridadTypes.setBounds(new Rectangle(5, 70, 260, 35));
        tfCatastro.setBounds(new Rectangle(5, 150, 75, 35));
        tfResponsable.setBounds(new Rectangle(5, 190, 255, 35));
        tfCantEfectivos.setBounds(new Rectangle(5, 230, 110, 35));
        tfTelefono1.setBounds(new Rectangle(5, 270, 110, 35));
        tfTelefono2.setBounds(new Rectangle(150, 270, 110, 35));
        tfEmail.setBounds(new Rectangle(5, 310, 255, 35));
        tfFax.setBounds(new Rectangle(5, 350, 120, 35));
        tfPunto.setBounds(new Rectangle(5, 30, 255, 35));
        panelData.add(tfPunto, null);
        panelData.add(tfFax, null);
        panelData.add(tfEmail, null);
        panelData.add(tfTelefono2, null);
        panelData.add(tfTelefono1, null);
        panelData.add(tfCantEfectivos, null);
        panelData.add(tfResponsable, null);
        panelData.add(tfCatastro, null);
        panelData.add(tfNombre, null);
        panelData.add(cbSeguridadTypes, null);
        this.setCentralPanel(panelData);
	//panelData.setBorder(BorderPanel.getBorderPanel("Agregar"));
	//panelData.setMinimumSize(new Dimension(1, 100));
	//panelData.setPreferredSize(new Dimension(1, 110));
	cbSeguridadTypes.autoSize();
        tfPunto.getTextField().setHorizontalAlignment(JTextField.CENTER);
        tfPunto.getTextField().setForeground(Color.red);
        tfPunto.setEditable(false);
    }

    private void clearData() {
        tfPunto.setValue("");
        tfNombre.setValue("");
        tfCatastro.setValue(0);
        tfResponsable.setValue("");
        tfCantEfectivos.setValue("");
        tfTelefono1.setValue("");
        tfTelefono2.setValue("");
        tfFax.setValue("");
        tfEmail.setValue("");
	cbSeguridadTypes.setSelectedID(0);
        setEnabledDeleteButton(false);
    }

    public void delete() {
	if (seguridad.delete()) {
	    getLayer().getGeometrySet().removeGeometry(point);
	    clearData();
	}
    }

    @Override
    public void saveData() {
	boolean _isnew = seguridad.getIdSeguridad() == -1;
	seguridad.setNombre(tfNombre.getString());
	seguridad.setIdtiposeguridad(Integer.parseInt(cbSeguridadTypes.getSelectedValue().toString()));
        seguridad.setCatastro(Integer.parseInt(tfCatastro.getValue().toString()));
        seguridad.setTipoSeguridad(cbSeguridadTypes.getSelectedItem().toString());
        seguridad.setResponsable(tfResponsable.getValue().toString());
        seguridad.setCantefectivos(Integer.parseInt(tfCantEfectivos.getValue().toString()));
        seguridad.setTelefono1(tfTelefono1.getValue().toString());
        seguridad.setTelefono2(tfTelefono2.getValue().toString());
        seguridad.setEmail(tfEmail.getValue().toString());
        seguridad.setFax(tfFax.getValue().toString());
	if (seguridad.saveData() > 0) {
	    getLayer().addLabelValue(point.getIdPoint(), seguridad.getNombre());
	    point.setIdPoint(seguridad.getIdSeguridad());
	    point.setSymbol(seguridad.getIdtiposeguridad());
	    clearData();
	    if (_isnew) {
		getLayer().getGeometrySet().addGeometry(point);
	    }
	} else {
	    Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
	}
    }


    public void setSeguridadObject(GaiaClassSeguridad _seguridad) {
	seguridad = _seguridad;
	cbSeguridadTypes.setSelectedID(seguridad.getIdtiposeguridad());
	loadData();
    }

    private void loadData() {
	tfNombre.setValue(seguridad.getNombre());
	cbSeguridadTypes.setSelectedID(seguridad.getIdtiposeguridad());
        tfCatastro.setValue(seguridad.getCatastro());
        tfPunto.setValue("("+ (new DecimalFormat("0.0000")).format(seguridad.getX()) +"; "+ (new DecimalFormat("0.0000")).format(seguridad.getY()) +")");
        tfResponsable.setValue(seguridad.getResponsable());
        tfCantEfectivos.setValue(seguridad.getCantefectivos());
        tfTelefono1.setValue(seguridad.getTelefono1());
        tfTelefono2.setValue(seguridad.getTelefono2());
        tfEmail.setValue(seguridad.getEmail());
        tfFax.setValue(seguridad.getFax());
        if (seguridad.getIdSeguridad() != -1)  {
            setEnabledDeleteButton(true);
        } else  {
            setEnabledDeleteButton(false);
        }
    }

    @Override
    public void setContentObject(Object _contentObject) {
	if (ESRIPoint.class.isInstance(_contentObject)) {
	    seguridad = new GaiaClassSeguridad();
	    point = (ESRIPoint)_contentObject;
	    seguridad.setIdSeguridad(point.getIdPoint());
	    seguridad.setX(point.getX());
	    seguridad.setY(point.getY());
	    seguridad.retrieveData();
	    setSeguridadObject(seguridad);
	    tfCatastro.fetchCatastro(point);
	}
    }

    @Override
    public Object getContentObject() {
	return seguridad;
    }
    
    @Override
    public void printReport() {
        BasicReport report = new BasicReport(GaiaFormSeguridad.class.getResource("xml/SeguridadReport.xml"));
        String param = "0,0";
        report.addTableModel("gis_oran.xmlGetSeguridad", param);
        report.doReport(); 
    }

}
