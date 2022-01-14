/**
 LIMITACIÓN DE RESPONSABILIDAD - COPYRIGHT - [Español]
 ================================================================================
 El Suri - Entorno JAVA de Trabajo y Desarrollo para Gobierno Electrónico
 ================================================================================

 Información del Proyecto:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 por D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO es propiedad de
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) y
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los términos de la Licencia Pública General GNU publicada
 por la Fundación para el Software Libre, ya sea la versión 3
 de la Licencia, o (a su elección) cualquier versión posterior.

 Este programa se distribuye con la esperanza de que sea útil, pero
 SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita
 MERCANTIL o de APTITUD PARA UN PROPÓSITO DETERMINADO.
 Consulte los detalles de la Licencia Pública General GNU para obtener
 una información más detallada.

 Debería haber recibido una copia de la Licencia Pública General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
 El Suri - JAVA Management & Development Environment for Electronic Government
 =====================================================================================

 Project Info:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 by D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO is owned by
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) and
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/
/**
 * LayerTree.java
 *
 * */
package org.digitall.common.geo.mapping.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.Enumeration;
import java.util.EventObject;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.ToolTipManager;
import javax.swing.event.CellEditorListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.digitall.common.geo.mapping.BasicDrawEngine;
import org.digitall.lib.geo.mapping.classes.BasicDrawEngineConfig;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.geo.gaia.GaiaPrimitiveForm;
import org.digitall.lib.geo.mapping.classes.Layer;
import org.digitall.lib.geo.mapping.classes.LayerFilter;
import org.digitall.lib.geo.mapping.classes.LayerGroup;

public class LayerTree extends BasicPanel {

    private JTree layerTree;
    private Vector<LayerGroup> layerGroups = new Vector<LayerGroup>();
    private DefaultTreeModel treeModel;
    private LayerTreeCellRenderer layerTreeCellRenderer = new LayerTreeCellRenderer();
    private LayerTreeCellEditor layerTreeCellEditor = new LayerTreeCellEditor();
    private BasicDrawEngine panel;
    private BorderLayout borderLayout1 = new BorderLayout();
    private Layer selectedLayer;
    private LayerGroup selectedLayerGroup;
    private LayerFilter selectedLayerFilter;
    private LayerTree _this;

    public LayerTree() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(borderLayout1);
	_this = this;
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0, true), "refresh_tree");
        getActionMap().put("refresh_tree", new AbstractAction() {
            public void actionPerformed(ActionEvent evt) {
                setLayerGroups(GaiaEnvironment.layerGroups);
                rebuildJTree();
            }
        }
        );
    }

    public void setLayerGroups(Vector<LayerGroup> _layerGroups) {
	layerGroups = _layerGroups;
	layerTree = buildJTree(layerGroups);
	//layerTree.setRowHeight(25);
	layerTree.setEditable(true);
	treeModel = (DefaultTreeModel)layerTree.getModel();
	layerTree.setCellRenderer(layerTreeCellRenderer);
	layerTree.setCellEditor(layerTreeCellEditor);
	layerTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
	removeAll();
	BasicScrollPane spTree = new BasicScrollPane(layerTree);
	add(spTree, BorderLayout.CENTER);
	ToolTipManager.sharedInstance().registerComponent(layerTree);
	layerTree.addMouseListener(new MouseListener() {

				public void mouseClicked(MouseEvent e) {
				}

				public void mousePressed(MouseEvent e) {

				}

				public void mouseReleased(MouseEvent e) {

				}

				public void mouseEntered(MouseEvent e) {

				}

				public void mouseExited(MouseEvent e) {

				}

			    }
	);
	layerTree.addKeyListener(new KeyListener() {

			      public void keyReleased(KeyEvent e) {
			      }

			      public void keyTyped(KeyEvent e) {

			      }

			      public void keyPressed(KeyEvent e) {

			      }

			  }
	);
    }

    public void updateLayers() {
	if (selectedLayer != null) {
	    if (selectedLayer.isActive()) {
	        if (selectedLayer.getEditionForm() instanceof GaiaPrimitiveForm) {
	            ((GaiaPrimitiveForm)selectedLayer.getEditionForm()).showForm();
	        }
	    } else {
	        if (selectedLayer.getEditionForm() instanceof GaiaPrimitiveForm) {
	            ((GaiaPrimitiveForm)selectedLayer.getEditionForm()).hideForm();
	        }
	    }
	    if (!selectedLayer.isOn()) {
		selectedLayer.setActive(false);
	    }
	    for (int i = 0; i < layerGroups.size(); i++) {
		for (int j = 0; j < layerGroups.elementAt(i).size(); j++) {
		    Layer _layer = (Layer)layerGroups.elementAt(i).elementAt(j);
		    if (_layer != selectedLayer) {
			_layer.setActive(false);
		    }
		}
	    }
	}
    }

    public void setDrawPanel(BasicDrawEngine _panel) {
	panel = _panel;
	setLayerGroups(panel.getLayerGroups());
    }
    
    public BasicDrawEngine getDrawPanel() {
	return panel;
    }

    public void rebuildJTree() {
	rebuild((DefaultMutableTreeNode)treeModel.getRoot());
    }

    protected void rebuild(DefaultMutableTreeNode _node) {
	layerTree.setSelectionPath(new TreePath(_node));
	Object _userObject = _node.getUserObject();
	if (_userObject instanceof LayerGroup) {
	    //TODO
	} else if (_userObject instanceof Layer) {
	    _node.removeAllChildren();
	    Layer _layer = (Layer)_userObject;

	    Vector<LayerFilter> _filters = _layer.getLayerConfig().getFilters();
	    for (int j = 0; j < _filters.size(); j++)  {
	        _node.add(new DefaultMutableTreeNode(_filters.elementAt(j)));
	    }
	    ((DefaultTreeModel)layerTree.getModel()).reload(_node);
	} else if (_userObject instanceof LayerFilter) {
	    //TODO
	} else {
	    //TODO
	}
	for (Enumeration e=_node.children(); e.hasMoreElements(); ) {
	    DefaultMutableTreeNode _newNode = (DefaultMutableTreeNode)e.nextElement();
	    rebuild(_newNode);
	}
    }

    private JTree buildJTree(Vector<LayerGroup> _groups) {
	DefaultMutableTreeNode _root = new DefaultMutableTreeNode("Layers");
	for (int i = 0; i < _groups.size(); i++) {
	    DefaultMutableTreeNode _group = new DefaultMutableTreeNode((LayerGroup)_groups.elementAt(i));
	    buildSubTree(_group, _groups.elementAt(i));
	    _root.add(_group);
	}
	return new JTree(_root);
    }

    private void buildSubTree(DefaultMutableTreeNode _parent, LayerGroup _leafs) {
	for (int i = 0; i < _leafs.size(); i++) {
	    Layer _layer = (Layer)_leafs.elementAt(i);
	    DefaultMutableTreeNode _leaf = new DefaultMutableTreeNode(_layer);
	    Vector<LayerFilter> _filters = _layer.getLayerConfig().getFilters();
	    for (int j = 0; j < _filters.size(); j++)  {
		_leaf.add(new DefaultMutableTreeNode(_filters.elementAt(j)));
	    }
	    _parent.add(_leaf);
	}
    }

    public void setSelectedLayer(Layer _selectedLayer) {
	selectedLayer = _selectedLayer;
    }

    public Layer getSelectedLayer() {
	return selectedLayer;
    }

    public class LayerTreeCellRenderer extends DefaultTreeCellRenderer {

	Component renderer;

	public LayerTreeCellRenderer() {

	}

	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
	    Component returnValue = new BasicPanel();
	    if ((value != null) && (value instanceof DefaultMutableTreeNode)) {
		Object userObject = ((DefaultMutableTreeNode)value).getUserObject();
		if (userObject instanceof Layer) {
		    selectedLayer = (Layer)userObject;
		    if (selected) {
			renderer = new LayerPanel(_this, selectedLayer);
		    } else {
			renderer = new LayerPanel(_this, selectedLayer);
		    }
		} else if (userObject instanceof LayerGroup) {
		    selectedLayerGroup = (LayerGroup)userObject;
		    LayerGroupPanel _lg = new LayerGroupPanel(_this, selectedLayerGroup);
		    if (selected) {
			renderer = new LayerGroupPanel(_this, selectedLayerGroup);
		    } else {
			renderer = new LayerGroupPanel(_this, selectedLayerGroup);
		    }
		    renderer = _lg;
		} else if (userObject instanceof LayerFilter) {
		    selectedLayerFilter = (LayerFilter)userObject;
		    LayerFilterPanel _lf = new LayerFilterPanel(_this, selectedLayerFilter, ((DefaultMutableTreeNode)value).getParent());
		    renderer = _lf;
		} else {
		    renderer = new LayerGroupPanel(_this, new LayerGroup("*"));
		    renderer.setEnabled(false);
		    renderer.setForeground(Color.BLACK);
		}
		
	        renderer.setSize(new Dimension(getWidth()-60<480?480:getWidth()-60,renderer.getHeight()));
	        renderer.setPreferredSize(renderer.getSize());
		returnValue = renderer;
		//returnValue.setEnabled(true);
	    }
	    repaint();
	    return returnValue;
	}

    }

    private class LayerTreeCellEditor implements TreeCellEditor {

	Component editor;

	public LayerTreeCellEditor() {

	}

	public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {
	    Component returnValue = new BasicPanel();
	    if ((value != null) && (value instanceof DefaultMutableTreeNode)) {
		Object userObject = ((DefaultMutableTreeNode)value).getUserObject();
		if (userObject instanceof Layer) {
		    selectedLayer = (Layer)userObject;
		    LayerPanel _l = new LayerPanel(_this, selectedLayer);
		    editor = new LayerPanel(_this, selectedLayer);
		    _l.setOpaque(true);
		    _l.setBackground(new Color(195, 195, 195));
		    _l.setBorder(BorderFactory.createLineBorder(new Color(165, 165, 165), 1));
		    editor = _l;
		} else if (userObject instanceof LayerGroup) {
		    selectedLayerGroup = (LayerGroup)userObject;
		    LayerGroupPanel _lg = new LayerGroupPanel(_this, selectedLayerGroup);
		    _lg.setOpaque(true);
		    _lg.setBackground(new Color(195, 195, 195));
		    _lg.setBorder(BorderFactory.createLineBorder(new Color(165, 165, 165), 1));
		    editor = _lg;
		} else if (userObject instanceof LayerFilter) {
		    selectedLayerFilter = (LayerFilter)userObject;
		    LayerFilterPanel _lf = new LayerFilterPanel(_this, selectedLayerFilter, ((DefaultMutableTreeNode)value).getParent());
		    _lf.setOpaque(true);
		    _lf.setBackground(new Color(195, 195, 195));
		    _lf.setBorder(BorderFactory.createLineBorder(new Color(165, 165, 165), 1));
		    editor = _lf;
		} else {
		    editor = new LayerGroupPanel(_this, new LayerGroup("*"));
		    editor.setEnabled(false);
		    editor.setForeground(Color.BLACK);
		}
	        editor.setSize(new Dimension(getWidth()-60<480?480:getWidth()-60,editor.getHeight()));
	        editor.setPreferredSize(editor.getSize());
	        returnValue = editor;
	    }
	    return returnValue;
	}

	public Object getCellEditorValue() {
	    return null;
	}

	public boolean isCellEditable(EventObject anEvent) {
	    return true;
	}

	public boolean shouldSelectCell(EventObject anEvent) {
	    return false;
	}

	public boolean stopCellEditing() {
	    return false;
	}

	public void cancelCellEditing() {

	}

	public void addCellEditorListener(CellEditorListener l) {

	}

	public void removeCellEditorListener(CellEditorListener l) {

	}

    }

}

