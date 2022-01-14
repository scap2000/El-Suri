package org.digitall.common.geo.mapping.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.common.components.combos.JCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.geo.mapping.components.LayerTree;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddComboButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.geo.mapping.classes.Layer;
import org.digitall.lib.geo.mapping.classes.LayerGroup;
import org.digitall.lib.geo.mapping.classes.LayerProfile;


public class NewLayerPanel extends BasicPrimitivePanel {

    private BasicPanel centralPanel = new BasicPanel();
    private TFInput tfLayerName = new TFInput(DataTypes.STRING, "Nombre ", false);
    private CBInput cbGeometrySets = new CBInput(0, "Grupo de Geometrías", false);
    private SaveDataButton btnSave = new SaveDataButton();
    
    private LayerGroup layerGroup;
    private LayerProfile layerProfile = new LayerProfile();

    private LayerTree layerTree;
    private AddComboButton btnAddGeometrySet = new AddComboButton();
    private CBInput cbLayerGroups = new CBInput(0, "Grupo de Layers", false);

    public NewLayerPanel(LayerGroup _layerGroup, LayerTree _layerTree) {
	layerGroup = _layerGroup;
	layerTree = _layerTree;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(340, 165));
	tfLayerName.setBounds(new Rectangle(10, 5, 295, 35));
        cbGeometrySets.setBounds(new Rectangle(10, 85, 295, 35));
        cbGeometrySets.autoSize();
        cbGeometrySets.setCombo(new JCombo(){
                @Override
                public void update() {
                    loadComboGeometrySets();
                }
            });
        centralPanel.add(cbLayerGroups, null);
        centralPanel.add(btnAddGeometrySet, null);
        centralPanel.add(tfLayerName, null);
        centralPanel.add(cbGeometrySets, null);
        btnSave.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnSave_actionPerformed(e);
				 }

			     }
	);
        btnAddGeometrySet.setBounds(new Rectangle(310, 100, 25, 20));
        btnAddGeometrySet.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnAddGeometrySet_actionPerformed(e);
                }
            });
        btnAddGeometrySet.setToolTipText("Nuevo Grupo de Geometrías");
        cbLayerGroups.setBounds(new Rectangle(10, 45, 295, 35));
        centralPanel.setLayout(null);
        this.add(centralPanel, BorderLayout.CENTER);
	this.addButton(btnSave);
	loadCombos();
        tfLayerName.getTextField().requestFocus();
    }

    private void btnSave_actionPerformed(ActionEvent e) {
        saveData();
    }

    private void btnAddGeometrySet_actionPerformed(ActionEvent e) {
        showNewGeometrySetPanel();
    }
    
    private void loadComboGeometrySets() {
	cbGeometrySets.removeAllItems();
	for (int i = 0; i < GaiaEnvironment.geometrySets.size(); i++) {
	    //cbGeometrySets.getCombo().addItem(GaiaEnvironment.geometrySets.elementAt(i).getGeometrySetConfig().getSqlScheme() + "." + GaiaEnvironment.geometrySets.elementAt(i).getGeometrySetConfig().getSqlTable(), i);
	    cbGeometrySets.getCombo().addItem(GaiaEnvironment.geometrySets.elementAt(i).getGeometrySetConfig().getSqlScheme() + "." + GaiaEnvironment.geometrySets.elementAt(i).getGeometrySetConfig().getSqlTable(), i);
	}
    }

    private void loadComboLayerGroups() {
        cbLayerGroups.removeAllItems();
        for (int i = 0; i < GaiaEnvironment.layerGroups.size(); i++) {
            cbLayerGroups.getCombo().addItem(GaiaEnvironment.layerGroups.elementAt(i).getName(), i);
        }
        cbLayerGroups.setSelectedValue(layerGroup.getName());
    }

    public boolean saveData() {
        if (control()) {
            layerProfile.setLayerName(tfLayerName.getString());
            layerProfile.setLayerGroupName(cbLayerGroups.getSelectedItem().toString());
            layerProfile.setGeometrySetName(GaiaEnvironment.geometrySets.elementAt(Integer.parseInt(cbGeometrySets.getSelectedValue().toString())).getName());
            showLayerConfigPanel(GaiaEnvironment.gaiaEngine.addLayerProfile(layerProfile));
            getParentInternalFrame().close();
        }
	return true;
    }
    
    private boolean control(){
        boolean retorno = true;
        if(tfLayerName.getString().equals("")){
            Advisor.messageBox("El campo \"Nombre\" no debe estar vacío", "Error");
            retorno = false;
        } else if (cbLayerGroups.getSelectedIndex() < 0) {
            Advisor.messageBox("Debe seleccionar un Grupo de Layers", "Error");
            retorno = false;
        } else if (cbGeometrySets.getSelectedIndex() < 0) {
            Advisor.messageBox("Debe seleccionar un Grupo de Geometrías", "Error");
            retorno = false;
        }
        return retorno;
    }

    public void setLayerGroup(LayerGroup layerGroup) {
        this.layerGroup = layerGroup;
    }

    public LayerGroup getLayerGroup() {
        return layerGroup;
    }

    private void showNewGeometrySetPanel() {
        ExtendedInternalFrame _newGeometrySetInternalFrame = new ExtendedInternalFrame("Nuevo Grupo de Geometrías");
        NewGeometrySetPanel _newGeometrySetPanel = new NewGeometrySetPanel(layerTree);
        _newGeometrySetInternalFrame.setCentralPanel(_newGeometrySetPanel);
        _newGeometrySetInternalFrame.setClosable(true);
        _newGeometrySetInternalFrame.setDesktop(Environment.getActiveDesktop());
        _newGeometrySetInternalFrame.setIconifiable(false);
        _newGeometrySetInternalFrame.show();    
    }

    private void showLayerConfigPanel(Layer _layer) {
        layerTree.getDrawPanel().addLayer(_layer);
        GaiaEnvironment.gaiaEngine.saveProfile();
        layerTree.setLayerGroups(GaiaEnvironment.layerGroups);
        layerTree.rebuildJTree();
        ExtendedInternalFrame _layerConfigInternalFrame = new ExtendedInternalFrame("Configuración del layer " + _layer.getAlias());
        LayerConfigPanel _layerConfigPanel = new LayerConfigPanel();
        _layerConfigInternalFrame.setCentralPanel(_layerConfigPanel);
        _layerConfigPanel.setLayer(_layer);
        _layerConfigInternalFrame.setClosable(true);
        _layerConfigInternalFrame.setDesktop(Environment.getActiveDesktop());
        _layerConfigInternalFrame.setIconifiable(false);
        _layerConfigInternalFrame.show();
    }

    private void loadCombos() {
        loadComboGeometrySets();
        loadComboLayerGroups();
    }
}
