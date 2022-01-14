package org.digitall.apps.gaia.entities.streets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.lib.geo.gaia.components.GaiaConfigPanel;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.geo.esri.ESRIPolygon;
import org.digitall.lib.geo.gaia.GaiaEnvironment;

public class GaiaStreetsPanel extends GaiaConfigPanel {

    private int[] sizeColumnList = { 200, 95, 75 };
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Calles", dataRow);
    private BasicPanel panelData = new BasicPanel();
    private TFInput tfName = new TFInput(DataTypes.STRING, "Name", false);
    private TFInput tfCode = new TFInput(DataTypes.STRING, "Code", false);
    private AssignButton btnAssign = new AssignButton(true);
    private AddButton btnNew = new AddButton();
    private boolean addAction = true;
    private FindButton btnFind = new FindButton();
    private GaiaStreet street;
    private CBInput cbStreetTypes = new CBInput(0, "Type", false);
    private AcceptButton btnSelect = new AcceptButton();
    private ESRIPolygon polyLine;

    public GaiaStreetsPanel() {
	this(new ESRIPolygon.Double());
    }

    public GaiaStreetsPanel(ESRIPolygon _polyLine) {
	try {
	    polyLine = _polyLine;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(432, 308));
	listPanel.setBounds(new Rectangle(5, 80, 305, 310));
	panelData.setBounds(new Rectangle(5, 0, 305, 70));
	panelData.setLayout(null);
	tfName.setBounds(new Rectangle(10, 25, 200, 35));
	tfCode.setBounds(new Rectangle(215, 25, 90, 35));
	btnAssign.setBounds(new Rectangle(310, 35, 40, 25));
	btnAssign.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnAssign_actionPerformed(e);
		}

	    });
	btnNew.setBounds(new Rectangle(345, 35, 40, 25));
	btnNew.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnNew_actionPerformed(e);
		}

	    });
	btnFind.setBounds(new Rectangle(380, 35, 40, 25));
	btnFind.setBounds(new Rectangle(380, 35, 40, 25));
	btnFind.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnFind_actionPerformed(e);
		}

	    });
	cbStreetTypes.setBounds(new Rectangle(10, 65, 265, 35));
	btnSelect.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnSelect_actionPerformed(e);
		}

	    });
	panelData.add(btnFind, null);
	panelData.add(btnNew, null);
	panelData.add(tfName, null);
	panelData.add(tfCode, null);
	panelData.add(cbStreetTypes, null);
	panelData.add(btnAssign, null);
	this.add(panelData, BorderLayout.NORTH);
	this.add(listPanel, BorderLayout.CENTER);
	panelData.setBorder(BorderPanel.getBorderPanel("Agregar nueva Calle"));
	panelData.setMinimumSize(new Dimension(1, 100));
	panelData.setPreferredSize(new Dimension(1, 110));
	setHeaderList();
	btnAssign.setToolTipText("Agregar/Modificar un Calle");
	btnNew.setToolTipText("Limpiar campos");
	cbStreetTypes.autoSize();
	addButton(btnSelect);
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("Calle");
	headerList.addElement("Código");
	headerList.addElement("Tipo");
	headerList.addElement("*");
	headerList.addElement("*");
	listPanel.getTable().addMouseListener(new MouseAdapter() {

		public void mouseClicked(MouseEvent e) {
		    if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
			loadStreet();
			loadData();
		    }
		}

	    });
	listPanel.setParams("gis.getAllStreets", "''", headerList);
	loadCombo();
    }

    public void refresh() {
	String params = "'" + tfCode.getString() + "','" + tfName.getString() + "','" + GaiaEnvironment.getScheme() + "'";
	listPanel.refresh(params);
    }

    private void clearData() {
	tfName.setValue("");
	tfCode.setValue("");
	cbStreetTypes.setSelectedID(0);
	addAction = true;
    }

    private void btnNew_actionPerformed(ActionEvent e) {
	clearData();
    }

    private void btnAssign_actionPerformed(ActionEvent e) {
	if (addAction) {
	    street.setIdstreet(-1);
	}
	street.setName(tfName.getString());
	street.setCode(tfCode.getString());
	street.setType(Integer.parseInt(cbStreetTypes.getSelectedValue().toString()));
	if (street.saveStreetData() > 0) {
	    getLayer().getGeometrySet().addGeometry(polyLine);
	    getLayer().addLabelValue(polyLine.getIdPolygon(), String.valueOf(street.getName()));
	    refresh();
	    clearData();
	} else {
	    Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
	}
    }

    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Puede Agregar/Modificar/Buscar Calles");
    }

    public void setStreetObject(GaiaStreet _street) {
	street = _street;
	getParentInternalFrame().setTitle(street.getName());
	cbStreetTypes.setSelectedID(street.getType());
	tfName.setValue(street.getName());
	tfCode.setValue(street.getCode());
	refresh();
    }

    private void loadStreet() {
	if (!dataRow.isEmpty()) {
	    street = new GaiaStreet();
	    street.setIdstreet(Integer.parseInt(dataRow.elementAt(0).toString()));
	    street.setIdLocation(Integer.parseInt(dataRow.elementAt(1).toString()));
	    street.setName(dataRow.elementAt(2).toString());
	    street.setCode(dataRow.elementAt(3).toString());
	    street.setType(Integer.parseInt(dataRow.elementAt(5).toString()));
	    addAction = false;
	}
    }

    private void loadData() {
	tfName.setValue(street.getName());
	tfCode.setValue(street.getCode());
	cbStreetTypes.setSelectedID(street.getType());
    }

    private void loadCombo() {
	String params = "";
	cbStreetTypes.loadJCombo("tabs.getAllStreetTypes", params);
    }

    private void btnSelect_actionPerformed(ActionEvent e) {
	if (!dataRow.isEmpty() && polyLine.getIdPolygon() != -1) {
	    street.setIdPolyline(polyLine.getIdPolygon());
	    if (street.saveData() > -1) {
	        getLayer().addLabelValue(polyLine.getIdPolygon(), String.valueOf(street.getName()));
	    }
	}
	getParentInternalFrame().setTitle("Listado de Calles");
	getParentInternalFrame().close();
    }

    @Override
    public void setContentObject(Object _contentObject) {
	if (ESRIPolygon.class.isInstance(_contentObject)) {
	    street = new GaiaStreet();
	    polyLine = (ESRIPolygon) _contentObject;
	    street.setIdPolyline(polyLine.getIdPolygon());
	    street.retrieveData();
	    setStreetObject(street);
	    addAction = false;
	}
    }

    @Override
    public Object getContentObject() {
	return street;
    }


}
