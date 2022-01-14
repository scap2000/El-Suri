package org.digitall.common.geo.mapping.components;

import java.awt.Dimension;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSeparator;

import javax.swing.SwingConstants;

import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.geo.mapping.classes.Layer;

public class LayerComboPanel extends JList {

    private BasicCheckBox chkOn = new BasicCheckBox();
    private BasicLabel lblLayerName = new BasicLabel();
    private JSeparator separator = new JSeparator();
    private GridBagLayout gbLayout = new GridBagLayout();
    private Layer layer;

    public LayerComboPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public LayerComboPanel(Layer _layer) {
	layer = _layer;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(gbLayout);
	this.setSize(new Dimension(260, 20));
	chkOn.setText("On");
	separator.setOrientation(SwingConstants.VERTICAL);
	this.add(lblLayerName, new GridBagConstraints(2, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
	this.add(chkOn, new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));
	this.add(separator, new GridBagConstraints(1, 0, 1, 1, 0.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, new Insets(0, 2, 0, 2), 0, 0));
	setLayer(layer);
    }

    public void setLayer(Layer _layer) {
	layer = _layer;
	if (layer != null) {
	    lblLayerName.setText(layer.getAlias());
	    chkOn.setSelected(layer.isOn());
	}
    }

    public Layer getLayer() {
	return layer;
    }

}
