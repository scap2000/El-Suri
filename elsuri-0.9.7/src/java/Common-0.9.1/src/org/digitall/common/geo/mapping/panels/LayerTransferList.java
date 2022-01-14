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
 * LayerTransferList.java
 *
 * */
package org.digitall.common.geo.mapping.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.digitall.common.geo.mapping.components.LayerListModel;
import org.digitall.lib.components.basic.BasicList;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CancelDataButton;
import org.digitall.lib.components.buttons.UnAssignButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.geo.mapping.classes.Layer;
import org.digitall.lib.geo.mapping.classes.LayerGroup;
import org.digitall.lib.geo.mapping.classes.LayerProfile;


public class LayerTransferList extends BasicPrimitivePanel {

    private BasicPanel centralPanel = new BasicPanel();
    
    private TFInput tfGroupName = new TFInput(DataTypes.STRING, "Grupo de Layers", false);
    private CancelDataButton btnCancel = new CancelDataButton();
    private BasicList layersList = new BasicList();
    private BasicScrollPane spLayers = new BasicScrollPane();
    private JButton btnTop = new JButton();
    private UnAssignButton btnUpLayer = new UnAssignButton(true);
    private AssignButton btnDownLayer = new AssignButton(true);
    private JButton btnBottom = new JButton();
    
    private String layerGroupName = "";
    private String layerName = "";
    private Vector layerList;
    private Vector<LayerGroup>  layerGroups;
    private Vector<LayerProfile>  layerProfileList;

    public LayerTransferList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(390, 315));
	tfGroupName.setBounds(new Rectangle(10, 5, 335, 35));
        spLayers.getViewport().add(layersList, null);
        centralPanel.add(btnBottom, null);
        centralPanel.add(btnUpLayer, null);
        centralPanel.add(btnDownLayer, null);
        centralPanel.add(btnTop, null);
        centralPanel.add(spLayers, null);
        centralPanel.add(tfGroupName, null);
        btnUpLayer.setToolTipText("Mover arriba");
        btnDownLayer.setToolTipText("Mover abajo");
        layersList.addListSelectionListener(new ListSelectionListener() {
                                        public void valueChanged(ListSelectionEvent e) {
                                            if (!e.getValueIsAdjusting()) {
                                                if (layersList.getSelectedIndex() > -1) {
                                                    setEnabled(layersList.getSelectedIndex());
                                                }
                                            }
                                        }
                                    }
        );
        btnCancel.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     
				 }

			     }
	);
        spLayers.setBounds(new Rectangle(10, 45, 335, 225));
        btnTop.setBounds(new Rectangle(355, 75, 25, 30));
        btnTop.setSize(new Dimension(25, 30));
        btnTop.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnTop_actionPerformed(e);
                }
            });
        btnDownLayer.setBounds(new Rectangle(355, 145, 25, 30));
        btnDownLayer.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnDownLayer_actionPerformed(e);
                }
            });
        btnUpLayer.setBounds(new Rectangle(355, 110, 25, 30));
        btnUpLayer.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnUpLayer_actionPerformed(e);
                }
            });
        btnBottom.setBounds(new Rectangle(355, 180, 25, 30));
        btnBottom.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnBottom_actionPerformed(e);
                }
            });
        centralPanel.setLayout(null);
        this.add(centralPanel, BorderLayout.CENTER);
	this.addButton(btnCancel);
        tfGroupName.setEditable(false);
        layerGroups = (Vector<LayerGroup>)GaiaEnvironment.layerGroups.clone();
        layerProfileList = (Vector<LayerProfile>)GaiaEnvironment.gaiaEngine.getLayerProfileList().clone();
        btnCancel.setVisible(false);
    }
    
    public void setLayerGroupName(String _layerGroupNewName) {
        layerGroupName = _layerGroupNewName;
        tfGroupName.setValue(layerGroupName);
        loadComboLayers();
    }

    public String getLayerGroupNewName() {
        return layerGroupName;
    }

    public void setLayerName(String _layerName) {
        layerName = _layerName;
    }

    public String getLayerName() {
        return layerName;
    }
    
    private void btnUpLayer_actionPerformed(ActionEvent e) {
        moveUpLayer();
    }

    private void btnDownLayer_actionPerformed(ActionEvent e) {
        moveDownLayer();
    }
    
    private void btnTop_actionPerformed(ActionEvent e) {
        moveTopLayer();
    }

    private void btnBottom_actionPerformed(ActionEvent e) {
        moveBottomLayer();
    }

    public void setLayerList(Vector layerList) {
        this.layerList = layerList;
    }

    public Vector getLayerList() {
        return layerList;
    }

    private void loadComboLayers() {
        //obtener posición del LayerGroup con nombre layerGroupName
        int _groupIndex = GaiaEnvironment.gaiaEngine.getLayerGroupIndex(layerGroupName);
        //obtener posición del Layer en el LayerGroup original 
        int _layerIndex = GaiaEnvironment.gaiaEngine.getLayerIndex(layerName, GaiaEnvironment.gaiaEngine.getLayerGroupIndex(layerGroupName));
        //cargar datos de los Layers contenidos en el LauerGroup a la lista
        LayerGroup _layerGroup = GaiaEnvironment.layerGroups.elementAt(_groupIndex);
        Vector _layers = new Vector();
        for (int i = 0; i < _layerGroup.size() ;i++) {
            LayerListModel _layerListModel = null;
            if (!_layerGroup.elementAt(i).getAlias().equals(layerName)) {
                 _layerListModel = new LayerListModel(i, _layerGroup.elementAt(i).getAlias(),"");
                
            } else {
                _layerListModel = new LayerListModel(_layerIndex, _layerGroup.elementAt(i).getAlias() + "","");
            }
            _layers.add(_layerListModel);    
        }
        layerList = _layers;
        layersList.setListData(_layers);
        layersList.setSelectedIndex(layerList.size()-1);
        setEnabled(layerList.size()-1);
    }
    
    private void moveUpLayer() {
        int _layerIndex = layersList.getSelectedIndex();
        LayerListModel _layerSelected = ((LayerListModel)layersList.getSelectedValue());
        layerList.remove(_layerIndex);
        layerList.add(_layerIndex-1,_layerSelected);
        layersList.removeAll();
        layersList.setListData(layerList);
        layersList.setSelectedIndex(_layerIndex-1);
        setEnabled(_layerIndex-1);
        moveLayer(_layerIndex, _layerIndex-1);
    }
    
    private void moveTopLayer() {
        int _layerIndex = layersList.getSelectedIndex();
        LayerListModel _layerSelected = ((LayerListModel)layersList.getSelectedValue());
        layerList.remove(_layerIndex);
        layerList.add(0,_layerSelected);
        layersList.removeAll();
        layersList.setListData(layerList);
        layersList.setSelectedIndex(0);
        setEnabled(0);
        moveLayer(_layerIndex, 0);
    }
    
    private void moveDownLayer() {
        int _layerIndex = layersList.getSelectedIndex();
        LayerListModel _layerSelected = ((LayerListModel)layersList.getSelectedValue());
        layerList.remove(_layerIndex);
        layerList.add(_layerIndex+1,_layerSelected);
        layersList.removeAll();
        layersList.setListData(layerList);
        layersList.setSelectedIndex(_layerIndex+1);
        setEnabled(_layerIndex+1);
        moveLayer(_layerIndex, _layerIndex+1);
    }
    
    private void moveBottomLayer() {
        int _layerIndex = layersList.getSelectedIndex();
        LayerListModel _layerSelected = ((LayerListModel)layersList.getSelectedValue());
        layerList.remove(_layerIndex);
        layerList.add(layerList.size(), _layerSelected);
        layersList.removeAll();
        layersList.setListData(layerList);
        layersList.setSelectedIndex(layerList.size()-1);
        setEnabled(layerList.size()-1);
        moveLayer(_layerIndex, layerList.size()-1);
    }
    
    private void moveLayer(int _positionToRemove, int _layerNewPosition) {
        //mover Layer
        int layerGroupIndex = getLayerGroupIndex(layerGroupName);
        Layer _layer = layerGroups.elementAt(layerGroupIndex).elementAt(_positionToRemove);
        layerGroups.elementAt(layerGroupIndex).remove(_positionToRemove);
        layerGroups.elementAt(layerGroupIndex).add(_layerNewPosition,_layer);
        GaiaEnvironment.layerGroups = layerGroups;
        //mover LayerProfile
        if (_positionToRemove > _layerNewPosition) {
            moveLayerProfile(_layer.getName(),_layerNewPosition,true);
        } else {
            moveLayerProfile(_layer.getName(), _layerNewPosition,false);
        }
    }
    
    private void moveLayerProfile(String _layerNameToMove, int _layerProfileNewPosition, boolean _up) {
        int _layerProfilePosition = getLayerProfileIndex(_layerNameToMove);
        LayerProfile _layerProfile = layerProfileList.elementAt(_layerProfilePosition);
        LayerListModel _layerModel = null;
        if (_up) {
            _layerModel = (LayerListModel)(layerList.elementAt(_layerProfileNewPosition+1));
            
        } else {
            _layerModel = (LayerListModel)(layerList.elementAt(_layerProfileNewPosition-1));
        }
        int _layerProfileIndexToMove = getLayerProfileIndex(_layerModel.getText());
        layerProfileList.remove(_layerProfilePosition);
        layerProfileList.add(_layerProfileIndexToMove,_layerProfile);
        GaiaEnvironment.gaiaEngine.setLayerProfileList(layerProfileList);
        GaiaEnvironment.gaiaEngine.saveProfile();    
    }

    private void setEnabled(int _layerIndex) {
        if (_layerIndex == 0) {
            btnUpLayer.setEnabled(false);
            btnTop.setEnabled(false);
        } else {
            btnUpLayer.setEnabled(true);
            btnTop.setEnabled(true);
        }
        if (_layerIndex == (layerList.size()-1)) {
            btnDownLayer.setEnabled(false);
            btnBottom.setEnabled(false);
        } else {
            btnDownLayer.setEnabled(true);
            btnBottom.setEnabled(true);
        }
    }
    
    public int getLayerGroupIndex(String _layerGroupName) {
        int i = 0;
        boolean encontrado = false;
        while (( !encontrado ) && ( i < layerGroups.size() )) {
            if (layerGroups.elementAt(i).getName().equals(_layerGroupName)) {
                encontrado = true;
            }
            i++;
        }
        if ( encontrado ) {
            i--;
        } else {
            i = -1;
        }
        return i;
    }
    
    public int getLayerProfileIndex(String _layerName) {
        int i = 0;
        boolean encontrado = false;
        while (( !encontrado ) && ( i < layerProfileList.size() )) {
            if ((layerProfileList.elementAt(i).getLayerGroupName().equals(layerGroupName))&&(layerProfileList.elementAt(i).getLayerName().equals(_layerName))) {
                encontrado = true;
            }
            i++;
        }
        if ( encontrado ) {
            i--;
        } else {
            i = -1;
        }
        return i;
    }
}
