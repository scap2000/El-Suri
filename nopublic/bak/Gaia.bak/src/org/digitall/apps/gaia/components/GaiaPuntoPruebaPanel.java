package org.digitall.apps.gaia.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.RefreshGridButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.geo.esri.ESRIPoint;
import org.digitall.lib.geo.gaia.components.GaiaConfigPanel;
import org.digitall.lib.sql.LibSQL;

public class GaiaPuntoPruebaPanel extends GaiaConfigPanel {

    private BasicPanel panelData = new BasicPanel();
    private TFInput tfDescripcion = new TFInput(DataTypes.STRING, "Description", false);
    private ESRIPoint point;
    private BasicRadioButton rbOpcionA = new BasicRadioButton("Opción A");
    private BasicRadioButton rbOpcionB = new BasicRadioButton("Opción B");
    private BasicRadioButton rbOpcionC = new BasicRadioButton("Opción C");
    private CBInput cbSeleccion = new CBInput(0, "Selección", false);
    private RefreshGridButton rgRefresh = new RefreshGridButton();
    private ButtonGroup bgOpciones = new ButtonGroup();
    private SaveDataButton btnSave = new SaveDataButton();

    public GaiaPuntoPruebaPanel() {
	this(new ESRIPoint(0, 0));
    }

    public GaiaPuntoPruebaPanel(ESRIPoint _point) {
	try {
	    point = _point;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(432, 205));
	panelData.setBounds(new Rectangle(5, 0, 305, 70));
	panelData.setLayout(null);
	tfDescripcion.setBounds(new Rectangle(10, 35, 410, 35));
	rbOpcionA.setBounds(new Rectangle(55, 90, 93, 18));
	rbOpcionB.setBounds(new Rectangle(165, 90, 93, 18));
	rbOpcionC.setBounds(new Rectangle(275, 90, 93, 18));

	bgOpciones.add(rbOpcionA);
	bgOpciones.add(rbOpcionB);
	bgOpciones.add(rbOpcionC);

	panelData.add(rbOpcionC, null);
	panelData.add(rbOpcionB, null);
	panelData.add(rbOpcionA, null);
	rbOpcionA.setSelected(true);
	panelData.add(tfDescripcion, null);

	panelData.add(cbSeleccion, null);
	cbSeleccion.setBounds(new Rectangle(136, 120, 160, 35));
	cbSeleccion.autoSize();

	for (int i = 1; i < 9; i++) {
	    cbSeleccion.addItem("Selección " + i);
	}

	rgRefresh.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
		    setContentObject(point);
		}
	});

	this.add(panelData, BorderLayout.CENTER);
	panelData.setBorder(BorderPanel.getBorderPanel("Datos del Punto Prueba"));
	panelData.setMinimumSize(new Dimension(1, 100));
	panelData.setPreferredSize(new Dimension(1, 110));
	addButton(rgRefresh);
	btnSave.setEnabled(false);
	addButton(btnSave);
    }

    private void clearData() {
	tfDescripcion.setValue("");
	cbSeleccion.setSelectedValue("Selección 1");
	rbOpcionA.setSelected(false);
	rbOpcionB.setSelected(false);
	rbOpcionC.setSelected(false);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Punto prueba");
    }

    @Override
    public void setContentObject(Object _contentObject) {
	if (ESRIPoint.class.isInstance(_contentObject)) {
	    point = (ESRIPoint)_contentObject;
	    clearData();
	    ResultSet _rsPuntoPrueba = LibSQL.exFunction("gea_salta.getPuntoPrueba", point.getIdPoint());
	    try {
		if (_rsPuntoPrueba.next()) {
		    tfDescripcion.setText(_rsPuntoPrueba.getString("descripcion"));
		    String _opcion = _rsPuntoPrueba.getString("opcion");
		    if (_opcion.equalsIgnoreCase("A")) {
			rbOpcionA.setSelected(true);	
		    } else if (_opcion.equalsIgnoreCase("B")) {
			rbOpcionB.setSelected(true);	
		    } else if (_opcion.equalsIgnoreCase("C")) {
			rbOpcionC.setSelected(true);	
		    }
		    cbSeleccion.setSelectedValue("Selección " + _rsPuntoPrueba.getInt("seleccion"));
		}
	    } catch (SQLException e) {
		// TODO: Add catch code
		e.printStackTrace();
	    }
	}
    }

    @Override
    public Object getContentObject() {
	return point;
    }

}
