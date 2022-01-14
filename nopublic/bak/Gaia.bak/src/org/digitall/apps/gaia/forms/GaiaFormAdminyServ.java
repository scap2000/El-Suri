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
