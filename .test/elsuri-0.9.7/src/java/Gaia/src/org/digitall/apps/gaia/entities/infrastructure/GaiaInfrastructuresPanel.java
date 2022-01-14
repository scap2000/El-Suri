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
 * GaiaInfrastructuresPanel.java
 *
 * */
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
