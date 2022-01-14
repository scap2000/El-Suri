package org.digitall.apps.gaia.entities.infrastructure;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.components.combos.CachedCombo;
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

public class GaiaInfrastructuresPanel extends GaiaConfigPanel {

    private int[] sizeColumnList = { 56, 95, 227 };
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Infraestructuras", dataRow);
    private BasicPanel panelData = new BasicPanel();
    private TFInput tfName = new TFInput(DataTypes.STRING, "Name", false);
    private TFInput tfCode = new TFInput(DataTypes.STRING, "Description", false);
    private AssignButton btnAssign = new AssignButton(true);
    private DeleteButton btnDelete = new DeleteButton();
    private FindButton btnFind = new FindButton();
    private GaiaInfrastructure infrastructure;
    private CBInput cbInfrastructureTypes = new CBInput(CachedCombo.INFRASTRUCTURETYPE_TABS, "Type", true);
    private AcceptButton btnSelect = new AcceptButton();
    private ESRIPoint point;

    public GaiaInfrastructuresPanel() {
	this(new ESRIPoint(0, 0));
    }

    public GaiaInfrastructuresPanel(ESRIPoint _point) {
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
	tfName.setBounds(new Rectangle(125, 25, 190, 35));
	tfCode.setBounds(new Rectangle(10, 65, 410, 35));
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
	cbInfrastructureTypes.setBounds(new Rectangle(10, 25, 105, 35));
	btnSelect.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnSelect_actionPerformed(e);
				 }

			     }
	);
	panelData.add(btnFind, null);
	panelData.add(btnDelete, null);
	panelData.add(tfName, null);
	panelData.add(tfCode, null);
	panelData.add(cbInfrastructureTypes, null);
	panelData.add(btnAssign, null);
	this.add(panelData, BorderLayout.NORTH);
	this.add(listPanel, BorderLayout.CENTER);
	panelData.setBorder(BorderPanel.getBorderPanel("Agregar infraestructura"));
	panelData.setMinimumSize(new Dimension(1, 100));
	panelData.setPreferredSize(new Dimension(1, 110));
	setHeaderList();
	btnAssign.setToolTipText("Agregar/Modificar infraestructura");
	btnDelete.setToolTipText("Limpiar campos");
	cbInfrastructureTypes.autoSize();
	addButton(btnSelect);
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
						   loadInfrastructure();
						   loadData();
					       }
					   }

				       }
	);
	listPanel.setParams("gis.getAllInfrastructures", "''", headerList);
    }

    public void refresh() {
	String params = "'" + tfName.getString() + "','" + tfCode.getString() + "','" + GaiaEnvironment.getScheme() + "'";
	listPanel.refresh(params);
    }

    private void clearData() {
	tfName.setValue("");
	tfCode.setValue("");
	cbInfrastructureTypes.setSelectedID(0);
    }

    private void btnDelete_actionPerformed(ActionEvent e) {
	if (Advisor.question("Aviso", "¿Está seguro de borrar la infraestructura?\n¡Esta operación no se puede deshacer!")) {
	    if (infrastructure.delete()) {
		getLayer().getGeometrySet().removeGeometry(point);
	    }
	}
    }

    private void btnAssign_actionPerformed(ActionEvent e) {
	boolean _isnew = infrastructure.getIdInfrastructure() == -1;
	infrastructure.setName(tfName.getString());
	infrastructure.setDescription(tfCode.getString());
	infrastructure.setType(Integer.parseInt(cbInfrastructureTypes.getSelectedValue().toString()));
	if (infrastructure.saveData() > 0) {
	    getLayer().addLabelValue(point.getIdPoint(), infrastructure.getName());
	    point.setIdPoint(infrastructure.getIdInfrastructure());
	    point.setSymbol(infrastructure.getType());
	    refresh();
	    clearData();
	    if (_isnew) {
		getLayer().getGeometrySet().addGeometry(point);
	    }
	} else {
	    Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
	}
    }

    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Puede Agregar/Modificar/Buscar");
    }

    public void setInfrastructureObject(GaiaInfrastructure _infrastructure) {
	infrastructure = _infrastructure;
	getParentInternalFrame().setTitle(infrastructure.getIdInfrastructure() == -1 ? "Agregar Infraestructura" : infrastructure.getName());
	cbInfrastructureTypes.setSelectedID(infrastructure.getType());
	tfName.setValue(infrastructure.getName());
	tfCode.setValue(infrastructure.getDescription());
	refresh();
    }

    private void loadInfrastructure() {
	if (!dataRow.isEmpty()) {
	    infrastructure = new GaiaInfrastructure();
	    infrastructure.setIdInfrastructure(Integer.parseInt(dataRow.elementAt(0).toString()));
	    infrastructure.setType(Integer.parseInt(dataRow.elementAt(1).toString()));
	    infrastructure.setName(dataRow.elementAt(2).toString());
	    infrastructure.setDescription(dataRow.elementAt(3).toString());
	    loadData();
	}
    }

    private void loadData() {
	tfName.setValue(infrastructure.getName());
	tfCode.setValue(infrastructure.getDescription());
	cbInfrastructureTypes.setSelectedID(infrastructure.getType());
    }

    private void btnSelect_actionPerformed(ActionEvent e) {
	if (!dataRow.isEmpty() && point.getIdPoint() != -1) {
	    infrastructure.setIdInfrastructure(point.getIdPoint());
	    if (infrastructure.saveData() > -1) {
	        getLayer().addLabelValue(point.getIdPoint(), infrastructure.getName());
	    }
	}
	getParentInternalFrame().setTitle("Infraestructura Urbana");
	getParentInternalFrame().close();
    }

    @Override
    public void setContentObject(Object _contentObject) {
	if (ESRIPoint.class.isInstance(_contentObject)) {
	    infrastructure = new GaiaInfrastructure();
	    point = (ESRIPoint)_contentObject;
	    infrastructure.setIdInfrastructure(point.getIdPoint());
	    infrastructure.retrieveData();
	    setInfrastructureObject(infrastructure);
	    infrastructure.setX(point.getX());
	    infrastructure.setY(point.getY());
	}
    }

    @Override
    public Object getContentObject() {
	return infrastructure;
    }


}
