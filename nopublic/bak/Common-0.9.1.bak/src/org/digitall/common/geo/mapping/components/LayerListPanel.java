package org.digitall.common.geo.mapping.components;

import org.digitall.common.geo.mapping.components.LayerList;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.common.geo.mapping.BasicDrawEngine;
import org.digitall.lib.geo.mapping.components.LayerTableModel;

public class LayerListPanel extends BasicScrollPane {
    
    //LayerList layerList = new LayerList(new LayerTableModel());
    LayerTree layerList = new LayerTree();

    public LayerListPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public void setDrawPanel(BasicDrawEngine _panel) {
	layerList.setDrawPanel(_panel);
    }

    private void jbInit() throws Exception {
	this.getViewport().add(layerList, null);
    }

}
