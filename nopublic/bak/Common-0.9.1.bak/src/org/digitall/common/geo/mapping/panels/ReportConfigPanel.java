package org.digitall.common.geo.mapping.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Vector;

import org.digitall.lib.components.basic.BasicCheckList;
import org.digitall.lib.components.basic.BasicCheckableListItem;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.geo.mapping.classes.Layer;

public class ReportConfigPanel extends BasicPrimitivePanel {

    private Vector<Layer> layers = new Vector<Layer>();
    private BasicCheckList layerList = new BasicCheckList();
    private BasicScrollPane spLayerList = new BasicScrollPane();
    private SaveDataButton btnSaveData = new SaveDataButton();
    private BasicContainerPanel centralPanel = new BasicContainerPanel();

    public ReportConfigPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    private void jbInit() throws Exception {
	centralPanel.setLayout(null);
	this.setSize(new Dimension(337, 328));
	spLayerList.setBounds(new Rectangle(5, 5, 325, 285));
	btnSaveData.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnSaveData_actionPerformed(e);
				 }

			     }
	);
	this.addButton(btnSaveData);
	spLayerList.getViewport().add(layerList);
	centralPanel.add(spLayerList, null);
	this.add(centralPanel, BorderLayout.CENTER);

    }

    

    private void btnSaveData_actionPerformed(ActionEvent e) {
	saveData();
    }

    public boolean saveData() {
	for (int i = 0; i < layerList.getModel().getSize(); i++) {
	    BasicCheckableListItem _layer = (BasicCheckableListItem)layerList.getModel().getElementAt(i);
	    for (int j = 0; j < layers.size(); j++) {
		if (layers.elementAt(j).getName().equals(_layer.getName())) {
		    layers.elementAt(j).getLayerConfig().setSelectedForReport(_layer.isSelected());
		    layers.elementAt(j).getLayerConfigList().saveConfigData();
		}
	    }
	}
	getParentInternalFrame().close();
	return true;
    }

    public void setLayers(Vector<Layer> _layers) {
	layers = _layers;
	Vector _layerList = new Vector();
	for (int i = 0; i < layers.size(); i++) {
	    BasicCheckableListItem item = new BasicCheckableListItem(0, layers.elementAt(i).getName(), layers.elementAt(i).getLayerConfig().isSelectedForReport());
	    item.setEnabled(layers.elementAt(i).getGeometrySet().getGeometriesCount() >0);
	    _layerList.add(item);
	}
	layerList.setListData(_layerList);
	layerList.setSelectedIndex(0);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _parent) {
	_parent.setInfo("Marque los layers a incluir en los reportes");
	super.setParentInternalFrame(_parent);
    }
}
