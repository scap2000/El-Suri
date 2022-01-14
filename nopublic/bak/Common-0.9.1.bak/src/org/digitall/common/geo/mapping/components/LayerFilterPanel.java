package org.digitall.common.geo.mapping.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;

import org.digitall.common.geo.mapping.panels.StyleConfigPanel;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.mapping.classes.Layer;
import org.digitall.lib.geo.mapping.classes.LayerFilter;
import org.digitall.lib.geo.shapefile.ShapeTypes;
import org.digitall.lib.icons.IconTypes;

public class LayerFilterPanel extends BasicPanel {

    private BasicCheckBox chkOn = new BasicCheckBox();
    private BasicLabel lblLayerFilterName = new BasicLabel();
    private PrintButton btnConfigure = new PrintButton();
    private DeleteButton btnRemove = new DeleteButton();
    private LayerFilter layerFilter;
    private LayerTree layerTree;
    private Layer layer;
    private DefaultMutableTreeNode parentObject;
    private BasicLabel lblColor = new BasicLabel();
    private GridBagLayout gridBagLayout1 = new GridBagLayout();

    public LayerFilterPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public LayerFilterPanel(LayerTree _layerTree, LayerFilter _layerFilter, Object _parentObject) {
	try {
	    jbInit();
	    layerTree = _layerTree;
	    setParentObject(_parentObject);
	    setLayerFilter(_layerFilter);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }


    private void jbInit() throws Exception {
	this.setSize(new Dimension(292, 25));
	this.setLayout(gridBagLayout1);
	this.setMinimumSize(new Dimension(292,25));
	lblColor.setOpaque(true);
	//this.setBounds(new Rectangle(10, 10, 378, 25));
	this.setBounds(new Rectangle(10, 10, 292, 25));
	this.setOpaque(false);
	chkOn.setHorizontalAlignment(SwingConstants.CENTER);
	chkOn.addActionListener(new ActionListener() {

			     public void actionPerformed(ActionEvent e) {
				 chkOn_actionPerformed(e);
			     }

			 }
	);
	lblLayerFilterName.setText("LayerFilter");
	lblLayerFilterName.setForeground(Color.black);
	btnConfigure.setSize(new Dimension(20, 20));
	btnConfigure.setToolTipText("Configuar estilo del filtro");
	btnConfigure.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					btnConfigure_actionPerformed(e);
				    }

				}
	);
	btnRemove.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnRemove_actionPerformed(e);
				 }

			     }
	);
	this.add(lblLayerFilterName, new GridBagConstraints(4, 0, 1, 2, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(-3, 0, 0, 27), 116, 15));
	this.add(chkOn, new GridBagConstraints(0, 0, 1, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(-3, 0, 0, 0), -3, -6));
	this.add(btnRemove, new GridBagConstraints(3, 0, 1, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(-3, 0, 0, 0), 2, -3));
	this.add(lblColor, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 10, 0, 0), 10, 10));
	this.add(btnConfigure, new GridBagConstraints(1, 0, 1, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(-3, 0, 0, 0), -8, -13));
    }

    private void chkOn_actionPerformed(ActionEvent e) {
	if (layerFilter != null) {
	    layerFilter.setActive(chkOn.isSelected());
	    layer.getLayerConfig().saveData();
	}
	layerTree.updateLayers();
    }

    public void setLayerFilter(LayerFilter _layerFilter) {
	layerFilter = _layerFilter;
	chkOn.setSelected(layerFilter.isActive());
	if (layerFilter.getName().equalsIgnoreCase("*")) {
	    chkOn.setEnabled(false);
	    lblLayerFilterName.setText("--");
	} else {
	    lblLayerFilterName.setText(layerFilter.getName() + " (" + layerFilter.getMatches() + ")");
	    //lblLayerFilterName.setText((layerFilter.getName().length()>20?layerFilter.getName().substring(0, 20)+"...":layerFilter.getName()) + " (" + layerFilter.getMatches() + ")");
	}
	lblColor.setOpaque(true);
	if (layerFilter.getStyleConfig().getSymbol() != -1) {
	    //System.out.println(layerFilter.getStyleConfig().getSymbol());
	    lblColor.setOpaque(false);
	    lblColor.setIcon(IconTypes.getScaledIcon(new ImageIcon(layerTree.getDrawPanel().getEngineConfig().getSymbol(layerFilter.getStyleConfig().getSymbol())), 16, 18));
	} else {
	    switch (layer.getGeometrySetConfig().getShapeType())      {
		case ShapeTypes.POINT:
		case ShapeTypes.MULTIPOINT:
		    lblColor.setBackground(layerFilter.getStyleConfig().getFillColor());
		    lblColor.setBorder(BorderFactory.createLineBorder(layerFilter.getStyleConfig().getOutlineColor(), layerFilter.getStyleConfig().getLineWidth()));
		    break;      
		case ShapeTypes.POLYLINE:
		case ShapeTypes.MULTIPOLYLINE:
		    lblColor.setBackground(layerFilter.getStyleConfig().getOutlineColor());
		    lblColor.setBorder(BorderFactory.createLineBorder(layerFilter.getStyleConfig().getOutlineColor(), layerFilter.getStyleConfig().getLineWidth()));
		    break;      
		case ShapeTypes.POLYGON:
		case ShapeTypes.MULTIPOLYGON:
		    lblColor.setBackground(layerFilter.getStyleConfig().getFillColor());
		    lblColor.setBorder(BorderFactory.createLineBorder(layerFilter.getStyleConfig().getOutlineColor(), layerFilter.getStyleConfig().getLineWidth()));
		    break;
	    }
	}
	lblColor.setToolTipText("R: " + lblColor.getBackground().getRed() + " G: " + lblColor.getBackground().getGreen() + " B: " + lblColor.getBackground().getBlue());
    }

    private void setParentObject(Object _parentObject) {
	Object _object = ((DefaultMutableTreeNode)_parentObject).getUserObject();
	if (_object instanceof Layer) {
	    layer  = (Layer)_object;
	    parentObject = (DefaultMutableTreeNode)_parentObject;
	} else {
	    layer = null;
	    parentObject = null;
	}
    }

    public LayerFilter getLayerFilter() {
	return layerFilter;
    }

    private void btnConfigure_actionPerformed(ActionEvent e) {
	if (layerFilter != null) {
	    ExtendedInternalFrame _styleConfigInternalFrame = new ExtendedInternalFrame("Filtro " + layerFilter.getName());
	    StyleConfigPanel _styleConfigPanel = new StyleConfigPanel();
	    _styleConfigInternalFrame.setCentralPanel(_styleConfigPanel);
	    _styleConfigPanel.setStyleConfig(layerFilter.getStyleConfig());
	    _styleConfigPanel.setLayerConfig(layer.getLayerConfig());
	    _styleConfigInternalFrame.setClosable(true);
	    _styleConfigInternalFrame.setDesktop(Environment.getActiveDesktop());
	    _styleConfigInternalFrame.setIconifiable(false);
	    _styleConfigInternalFrame.show();
	}
    }

    private void btnRemove_actionPerformed(ActionEvent e) {
	if (layer != null && layerFilter != null) {
	    if (JOptionPane.showInternalConfirmDialog(Environment.getActiveDesktop(), "¿Está seguro de borrar el filtro " + layerFilter.getToolTipValue() + "?", "Borrar filtro", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
	        layer.removeFilter(layerFilter);
		if (parentObject != null) {
		    layerTree.rebuild(parentObject);
		}
	        layerTree.updateLayers();
	    }
	}
    }

}
