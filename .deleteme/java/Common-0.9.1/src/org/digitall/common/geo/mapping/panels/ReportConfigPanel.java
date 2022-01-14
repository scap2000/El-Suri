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
 * ReportConfigPanel.java
 *
 * */
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
