package org.digitall.apps.gaia.entities.streets;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.geo.esri.ESRIPoint;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.geo.gaia.components.GaiaConfigPanel;

public class GaiaAddressReenumerationPanel extends GaiaConfigPanel {

    private int[] sizeColumnList = { 56, 95, 227 };
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Direcciones", dataRow);
    private BasicPanel panelData = new BasicPanel();
    private TFInput tfNumber = new TFInput(DataTypes.STRING, "Number", false);
    private TFInput tfStreet = new TFInput(DataTypes.STRING, "Street", false);
    private AssignButton btnAssign = new AssignButton(true);
    private DeleteButton btnDelete = new DeleteButton();
    private FindButton btnFind = new FindButton();
    private GaiaAddress address;
    private CBInput cbAddressTypes = new CBInput(0, "Type", false);
    private AcceptButton btnSelect = new AcceptButton();
    private ESRIPoint point;

    public GaiaAddressReenumerationPanel() {
	this(new ESRIPoint(0, 0));
    }

    public GaiaAddressReenumerationPanel(ESRIPoint _point) {
	try {
	    point = _point;
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
	tfNumber.setBounds(new Rectangle(125, 65, 295, 35));
	tfStreet.setBounds(new Rectangle(10, 20, 305, 35));
	btnAssign.setBounds(new Rectangle(315, 35, 40, 25));
	btnAssign.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAssign_actionPerformed(e);
				 }

			     }
	);
	btnDelete.setBounds(new Rectangle(350, 35, 40, 25));
	btnDelete.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnDelete_actionPerformed(e);
				 }

			     }
	);
	btnFind.setBounds(new Rectangle(380, 35, 40, 25));
	btnFind.setBounds(new Rectangle(385, 35, 40, 25));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);
	cbAddressTypes.setBounds(new Rectangle(10, 65, 105, 35));
	btnSelect.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnSelect_actionPerformed(e);
				 }

			     }
	);
	panelData.add(btnFind, null);
	panelData.add(btnDelete, null);
	panelData.add(tfNumber, null);
	panelData.add(tfStreet, null);
	panelData.add(cbAddressTypes, null);
	panelData.add(btnAssign, null);
	this.add(panelData, BorderLayout.NORTH);
	this.add(listPanel, BorderLayout.CENTER);
	panelData.setBorder(BorderPanel.getBorderPanel("Agregar Dirección"));
	panelData.setMinimumSize(new Dimension(1, 100));
	panelData.setPreferredSize(new Dimension(1, 110));
	setHeaderList();
	btnAssign.setToolTipText("Agregar/Modificar dirección");
	btnDelete.setToolTipText("Limpiar campos");
	cbAddressTypes.autoSize();
	addButton(btnSelect);
	tfStreet.setEditable(false);
	tfNumber.getTextField().addKeyListener(new KeyAdapter() {

						public void keyReleased(KeyEvent e) {
						    if (e.getKeyCode() == KeyEvent.VK_ENTER)
							assignAddress();
						}

					    }
	);	
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("ID");
	headerList.addElement("Tipo");
	headerList.addElement("Nombre");
	headerList.addElement("*");
	headerList.addElement("*");
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent e) {
					       if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						   loadAddress();
						   loadData();
					       }
					   }

				       }
	);
	listPanel.setParams("gis.getAllAddresses", "''", headerList);
	loadCombo();
    }

    public void refresh() {
	String params = "'" + tfNumber.getString() + "','" + tfStreet.getString() + "','" + GaiaEnvironment.getScheme() + "'";
	listPanel.refresh(params);
	tfNumber.focus();
    }

    private void clearData() {
	tfNumber.setValue("");
	tfStreet.setValue("");
	cbAddressTypes.setSelectedID(0);
	tfNumber.focus();
    }

    private void btnDelete_actionPerformed(ActionEvent e) {
	if (Advisor.question("Aviso", "¿Está seguro de borrar la dirección?\n¡Esta operación no se puede deshacer!")) {
	    if (address.delete()) {
		getLayer().getGeometrySet().removeGeometry(point);
	    }
	}
    }

    private void btnAssign_actionPerformed(ActionEvent e) {
	assignAddress();
    }
    
    private void assignAddress() {
	boolean _isnew = address.getIdAddress() == -1;
	address.setIdStreet(GaiaEnvironment.getSelectedStreetID());
	address.setNumber(tfNumber.getString());
	address.setType(Integer.parseInt(cbAddressTypes.getSelectedValue().toString()));
	if (address.saveData() > 0) {
	    //point.setLabel(address.getNumber());
	    point.setIdPoint(address.getIdAddress());
	    refresh();
	    clearData();
	    if (_isnew) {
		getLayer().getGeometrySet().addGeometry(point);
	    }
	    getLayer().addLabelValue(point.getIdPoint(), String.valueOf(address.getNumber()));
	} else {
	    Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
	}
	tfNumber.focus();
    }

    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Puede Agregar/Modificar/Buscar");
    }

    public void setAddressObject(GaiaAddress _address) {
	address = _address;
	getParentInternalFrame().setTitle(address.getIdAddress() == -1 ? "Agregar Dirección" : address.getNumber());
	cbAddressTypes.setSelectedID(address.getType());
	tfNumber.setValue(address.getIdStreet());
	tfStreet.setValue(address.getNumber());
	refresh();
	tfNumber.focus();
    }

    private void loadAddress() {
	if (!dataRow.isEmpty()) {
	    address = new GaiaAddress();
	    address.setIdAddress(Integer.parseInt(dataRow.elementAt(0).toString()));
	    address.retrieveData();
	}
	tfNumber.focus();
    }

    private void loadData() {
	tfNumber.setValue(address.getNumber());
	tfStreet.setValue(address.getStreetName());
	cbAddressTypes.setSelectedID(address.getType());
	System.out.println(tfNumber.getTextField().isRequestFocusEnabled());
	tfNumber.focus();
    }

    private void loadCombo() {
	String params = "";
	cbAddressTypes.loadJCombo("tabs.getAllAddressTypes", params);
	tfNumber.focus();
    }

    private void btnSelect_actionPerformed(ActionEvent e) {
	if (!dataRow.isEmpty() && point.getIdPoint() != -1) {
	    address.setIdAddress(point.getIdPoint());
	    if (address.saveData() > -1) {
	        getLayer().addLabelValue(point.getIdPoint(), String.valueOf(address.getNumber()));
	    }
	}
	getParentInternalFrame().setTitle("Dirección");
	getParentInternalFrame().close();
    }

    @Override
    public void setContentObject(Object _contentObject) {
	if (ESRIPoint.class.isInstance(_contentObject)) {
	    address = new GaiaAddress();
	    point = (ESRIPoint)_contentObject;
	    address.setIdAddress(point.getIdPoint());
	    address.retrieveData();
	    setAddressObject(address);
	    address.setX(point.getX());
	    address.setY(point.getY());
	    if (address.getIdStreet() == -1) {
	        address.setIdStreet(GaiaEnvironment.getSelectedStreetID());
	        address.setStreetName(GaiaEnvironment.getSelectedStreetName());
	    }
	    loadData();
	}
    }

    @Override
    public Object getContentObject() {
	return address;
    }

}
