package org.digitall.common.geo.mapping.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import org.digitall.common.geo.mapping.panels.NewLayerPanel;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddComboButton;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.geo.mapping.classes.LayerGroup;

public class LayerGroupPanel extends BasicPanel {

    private BasicCheckBox chkOn = new BasicCheckBox();
    private BasicLabel lblLayerGroupName = new BasicLabel();
    private LayerGroup layerGroup;
    private LayerTree layerTree;
    private AddComboButton btnAddLayer = new AddComboButton();

    public LayerGroupPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public LayerGroupPanel(LayerTree _layerTree, LayerGroup _layerGroup) {
	try {
	    jbInit();
	    layerTree = _layerTree;
	    setLayerGroup(_layerGroup);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }


    private void jbInit() throws Exception {
	this.setSize(new Dimension(308, 25));
	this.setLayout(null);
	this.setMinimumSize(new Dimension(292,25));
	//this.setBounds(new Rectangle(10, 10, 378, 25));
	this.setBounds(new Rectangle(10, 10, 292, 25));
	this.setOpaque(false);
	chkOn.setBounds(new Rectangle(0, 2, 20, 20));
	chkOn.setHorizontalAlignment(SwingConstants.CENTER);
	chkOn.addActionListener(new ActionListener() {

			     public void actionPerformed(ActionEvent e) {
				 chkOn_actionPerformed(e);
			     }

			 }
	);
	lblLayerGroupName.setBounds(new Rectangle(60, 7, 220, 15));
	lblLayerGroupName.setText("LayerGroup");
	lblLayerGroupName.setForeground(Color.black);
        btnAddLayer.setBounds(new Rectangle(25, 3, 22, 19));
        btnAddLayer.setToolTipText("Nuevo Layer");
        btnAddLayer.setSize(new Dimension(22, 19));
        btnAddLayer.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnAddLayer_actionPerformed(e);
                }
            });
        this.add(lblLayerGroupName, null);
        this.add(chkOn, null);
	this.add(btnAddLayer, null);
    }

    private void chkOn_actionPerformed(ActionEvent e) {
	if (layerGroup != null) {
	    layerGroup.setOn(chkOn.isSelected());
	}
	layerTree.updateLayers();
    }

    public void setLayerGroup(LayerGroup _layerGroup) {
	layerGroup = _layerGroup;
	chkOn.setSelected(layerGroup.isOn());
	if (layerGroup.getName().equalsIgnoreCase("*")) {
	    //LayerGroup ROOT
	    chkOn.setEnabled(false);
	    lblLayerGroupName.setText("Grupos de Layers");
	} else {
	    lblLayerGroupName.setText(layerGroup.getName());
	}
    }

    public LayerGroup getLayerGroup() {
	return layerGroup;
    }

    private void btnAddLayer_actionPerformed(ActionEvent e) {
        if ((e.getModifiers() & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK) {
            showLayerGroupEditPanel();
        } else {
            showPanel();    
        }
        
    }
    
    private void showPanel() {
        if (!layerGroup.getName().equalsIgnoreCase("*")) {
            ExtendedInternalFrame _newLayerInternalFrame = new ExtendedInternalFrame("Nuevo Layer");
            NewLayerPanel _newLayerPanel = new NewLayerPanel(layerGroup, layerTree);
            _newLayerInternalFrame.setCentralPanel(_newLayerPanel);
            _newLayerInternalFrame.setClosable(true);
            _newLayerInternalFrame.setDesktop(Environment.getActiveDesktop());
            _newLayerInternalFrame.setIconifiable(false);
            _newLayerInternalFrame.show();    
        } else {
            //Object _valorObtenido = Advisor.getValue(DataTypes.STRING, "Ingrese el nombre del Grupo de Layers");
            String _valorObtenido = JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Ingrese el nombre del Grupo de Layers", "Mensaje", JOptionPane.QUESTION_MESSAGE);
            if ((_valorObtenido != null)) {
                if (!_valorObtenido.toString().trim().equals("")) {
                    GaiaEnvironment.gaiaEngine.addLayerGroup(_valorObtenido);
                    GaiaEnvironment.gaiaEngine.saveProfile();
                    layerTree.setLayerGroups(GaiaEnvironment.layerGroups);
                    layerTree.rebuildJTree();    
                } else {
                    Advisor.messageBox("El nombre del nuevo Grupo de Layers no debe ser vacío", "Error");
                }
            }
        }
        
    }

    private void showLayerGroupEditPanel() {
        if (!layerGroup.getName().equalsIgnoreCase("*")) {
            String _layerGroupNewName = JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Ingrese el nuevo nombre del Grupo de Geometrías\n \"" + layerGroup.getName() +"\"", "Mensaje", JOptionPane.QUESTION_MESSAGE);
            if (_layerGroupNewName != null) {
                if (!_layerGroupNewName.trim().equals("")) {
                    GaiaEnvironment.gaiaEngine.renameLayerGroup(layerGroup.getName(), _layerGroupNewName);
                    GaiaEnvironment.gaiaEngine.saveProfile();
                    layerTree.setLayerGroups(GaiaEnvironment.layerGroups);
                    layerTree.rebuildJTree();        
                } else {
                    Advisor.messageBox("El nombre del Grupo de Layers no debe ser vacío", "Error");
                }
            }    
        }
    }
}
