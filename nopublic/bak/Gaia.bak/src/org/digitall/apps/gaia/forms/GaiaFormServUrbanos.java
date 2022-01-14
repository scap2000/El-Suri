package org.digitall.apps.gaia.forms;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import java.text.DecimalFormat;

import javax.swing.JTextField;

import javax.swing.SwingConstants;

import org.digitall.apps.gaia.classes.GaiaClassServUrbanos;
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

public class GaiaFormServUrbanos extends GaiaPrimitiveForm {

    private BasicPanel panelData = new BasicPanel();
    private GaiaClassServUrbanos servUrbano;
    private CBInput cbServUrbanosTypes = new CBInput(CachedCombo.SERVICIOSURBANOS_TABS, "Type", false);
    private ESRIPoint point;
    private GaiaCatastroInputPanel tfCatastro = new GaiaCatastroInputPanel();
    private TFInput tfResponsable = new TFInput(DataTypes.STRING,"PersonCharge", false);
    private TFInput tfTelefono1 = new TFInput(DataTypes.STRING,"Phone1", false);
    private TFInput tfTelefono2 = new TFInput(DataTypes.STRING,"Phone2", false);
    private TFInput tfFax = new TFInput(DataTypes.STRING,"Fax", false);
    private TFInput tfPunto = new TFInput(DataTypes.STRING,"Coords", false);
    private TFInput tfNombre = new TFInput(DataTypes.STRING,"Name", false);

    public GaiaFormServUrbanos() {
	this(new ESRIPoint(0, 0));
    }

    public GaiaFormServUrbanos(ESRIPoint _point) {
	super();
	try {
	    point = _point;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setTitle("SERVICIOS URBANOS");
	this.setSize(new Dimension(432, 308));
	panelData.setBounds(new Rectangle(10, 10, 290, 326));
	panelData.setLayout(null);
        panelData.setPreferredSize(new Dimension(270, 1));
        panelData.setSize(new Dimension(270, 319));
        cbServUrbanosTypes.setBounds(new Rectangle(5, 70, 260, 35));
        tfCatastro.setBounds(new Rectangle(5, 150, 75, 35));
        tfResponsable.setBounds(new Rectangle(5, 190, 255, 35));
        tfTelefono1.setBounds(new Rectangle(5, 230, 110, 35));
        tfTelefono2.setBounds(new Rectangle(150, 230, 110, 35));
        tfFax.setBounds(new Rectangle(5, 270, 110, 35));
        tfPunto.setBounds(new Rectangle(5, 30, 255, 35));
        panelData.add(tfNombre, null);
        panelData.add(tfPunto, null);
        panelData.add(tfFax, null);
        panelData.add(tfTelefono2, null);
        panelData.add(tfTelefono1, null);
        panelData.add(tfResponsable, null);
        panelData.add(tfCatastro, null);
        panelData.add(cbServUrbanosTypes, null);
        this.setCentralPanel(panelData);
	//panelData.setBorder(BorderPanel.getBorderPanel("Agregar"));
	//panelData.setMinimumSize(new Dimension(1, 100));
	//panelData.setPreferredSize(new Dimension(1, 110));
	cbServUrbanosTypes.autoSize();
        tfPunto.getTextField().setHorizontalAlignment(JTextField.CENTER);
        tfPunto.getTextField().setForeground(Color.red);
        tfPunto.setEditable(false);
        tfNombre.setBounds(new Rectangle(5, 110, 255, 35));
    }

    private void clearData() {
	 tfPunto.setValue("");
	 tfNombre.setValue("");
	 tfCatastro.setValue(0);
	 tfResponsable.setValue("");
	 tfTelefono1.setValue("");
	 tfTelefono2.setValue("");
	 tfFax.setValue("");
	cbServUrbanosTypes.setSelectedID(0);
        setEnabledDeleteButton(false);
    }

    public void delete() {
	    if (servUrbano.delete()) {
		getLayer().getGeometrySet().removeGeometry(point);
	        clearData();
	    }
    }

    public void saveData() {
	boolean _isnew = servUrbano.getIdServUrbano() == -1;
	servUrbano.setIdTipoServUrbano(Integer.parseInt(cbServUrbanosTypes.getSelectedValue().toString()));
        servUrbano.setNombre(tfNombre.getValue().toString());
        servUrbano.setCatastro(Integer.parseInt(tfCatastro.getValue().toString()));
        servUrbano.setResponsable(tfResponsable.getValue().toString());
        servUrbano.setTelefono1(tfTelefono1.getValue().toString());
        servUrbano.setTelefono2(tfTelefono2.getValue().toString());
        servUrbano.setFax(tfFax.getValue().toString());
	if (servUrbano.saveData() > 0) {
	    getLayer().addLabelValue(point.getIdPoint(), servUrbano.getNombre());
	    point.setIdPoint(servUrbano.getIdServUrbano());
	    point.setSymbol(servUrbano.getIdTipoServUrbano());
	    clearData();
	    if (_isnew) {
		getLayer().getGeometrySet().addGeometry(point);
	    }
	} else {
	    Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
	}
    }


    public void setServUrbanoObject(GaiaClassServUrbanos _servUrbano) {
	servUrbano = _servUrbano;
	cbServUrbanosTypes.setSelectedID(servUrbano.getIdTipoServUrbano());
	loadData();
    }

    private void loadData() {
        tfPunto.setValue("("+ (new DecimalFormat("0.0000")).format(servUrbano.getX()) +"; "+ (new DecimalFormat("0.0000")).format(servUrbano.getY()) +")");
	tfNombre.setValue(servUrbano.getNombre());
	cbServUrbanosTypes.setSelectedID(servUrbano.getIdTipoServUrbano());
        tfCatastro.setValue(servUrbano.getCatastro());
        tfResponsable.setValue(servUrbano.getResponsable());
        tfTelefono1.setValue(servUrbano.getTelefono1());
        tfTelefono2.setValue(servUrbano.getTelefono2());
        tfFax.setValue(servUrbano.getFax());
        if (servUrbano.getIdServUrbano() != -1)  {
            setEnabledDeleteButton(true);
        } else  {
            setEnabledDeleteButton(false);
        }
    }

    @Override
    public void setContentObject(Object _contentObject) {
	if (ESRIPoint.class.isInstance(_contentObject)) {
	    servUrbano = new GaiaClassServUrbanos();
	    point = (ESRIPoint)_contentObject;
	    servUrbano.setIdServUrbano(point.getIdPoint());
	    servUrbano.retrieveData();
	    servUrbano.setX(point.getX());
	    servUrbano.setY(point.getY());
	    setServUrbanoObject(servUrbano);
	    tfCatastro.fetchCatastro(point);
	}
    }

    @Override
    public Object getContentObject() {
	return servUrbano;
    }

    @Override
    public void printReport() {
        BasicReport report = new BasicReport(GaiaFormServUrbanos.class.getResource("xml/ServUrbanosReport.xml"));
        String param = "0,0";
        report.addTableModel("gis_oran.xmlGetServiciosUrbanos", param);
        report.doReport(); 
    }
}
