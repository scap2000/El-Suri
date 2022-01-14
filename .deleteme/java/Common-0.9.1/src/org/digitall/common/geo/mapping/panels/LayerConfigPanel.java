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
 * LayerConfigPanel.java
 *
 * */
package org.digitall.common.geo.mapping.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.io.File;

import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.common.ConfigFile;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.GoButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.buttons.RefreshGridButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.buttons.UnAssignButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.geo.mapping.classes.Layer;
import org.digitall.lib.geo.mapping.classes.LayerConfig;
import org.digitall.lib.geo.mapping.classes.LayerFilter;
import org.digitall.lib.geo.shapefile.ShapeTypes;


public class LayerConfigPanel extends BasicPrimitivePanel {

    private BasicPanel centralPanel = new BasicPanel();
    private TFInput tfAlias = new TFInput(DataTypes.STRING, "Name", false);
    private CBInput cbConfiguraciones = new CBInput(0, "Configuración", false);
    private CBInput cbLabelItem = new CBInput(0, "Etiqueta (req. recargar)", false);
    private TFInput tfMinScale = new TFInput(DataTypes.DOUBLE, "Esc. mínima", false);
    private TFInput tfMaxScale = new TFInput(DataTypes.DOUBLE, "Esc. máxima", false);
    private TFInput tfSymbolScale = new TFInput(DataTypes.DOUBLE, "Esc. símbolos", false);
    private TFInput tfTolerance = new TFInput(DataTypes.INTEGER, "Tolerancia", false);
    private TFInput tfPointDiameter = new TFInput(DataTypes.INTEGER, "Diám. del Pto.", false);
    private CBInput cbGroupName = new CBInput(0, "Grupo", false);
    private CBInput cbType = new CBInput(0, "Type", false);
    private BasicCheckBox chkOn = new BasicCheckBox();
    private BasicCheckBox chkActive = new BasicCheckBox();
    private BasicCheckBox chkDrawGeometries = new BasicCheckBox();
    private BasicCheckBox chkPaintLabels = new BasicCheckBox();
    private SaveDataButton btnSave = new SaveDataButton();
    private PrintButton btnStyle = new PrintButton();
    private PrintButton btnModifyFilterStyle = new PrintButton();
    private ModifyButton btnModifyFilter = new ModifyButton();
    private AddButton btnAddFilter = new AddButton();
    private GoButton btnConnection = new GoButton();
    private AddButton btnAddConfig = new AddButton();
    private DeleteButton btnDeleteConfig = new DeleteButton();
    private RefreshGridButton btnRefreshConfig = new RefreshGridButton();
    private CBInput cbFilters = new CBInput(0, "Filtros (req. recargar)", false);
    private DeleteButton btnRemoveFilter = new DeleteButton();
    private FindButton btnFindLabel = new FindButton();
    private RefreshGridButton btnAutoFilters = new RefreshGridButton();
    
    private Layer layer;
    private LayerConfig layerConfig;
    
    private boolean  nuevaConfiguracion = true;
    private AssignButton btnExportarConfig = new AssignButton();
    private UnAssignButton btnImportarConfig = new UnAssignButton();
    private UnAssignButton btnOrderLayer = new UnAssignButton(true);
    
    public LayerConfigPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(416, 315));
	tfAlias.setBounds(new Rectangle(10, 5, 200, 35));
	tfMinScale.setBounds(new Rectangle(10, 125, 80, 35));
	tfMinScale.setBounds(new Rectangle(240, 45, 80, 35));
	tfMaxScale.setBounds(new Rectangle(325, 45, 80, 35));
	tfSymbolScale.setBounds(new Rectangle(200, 150, 90, 35));
	tfTolerance.setBounds(new Rectangle(105, 150, 90, 35));
	tfPointDiameter.setBounds(new Rectangle(10, 150, 90, 35));
	chkOn.setBounds(new Rectangle(10, 125, 90, 20));
	chkOn.setText("Encendido");
	chkActive.setText("Activo");
	chkActive.setBounds(new Rectangle(325, 125, 90, 20));
	cbType.setBounds(new Rectangle(215, 5, 135, 35));
	cbGroupName.setBounds(new Rectangle(10, 45, 200, 35));
        cbGroupName.addItemListener(new ItemListener() {
            
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        cbGroupName_itemStateChanged(e);
                    }
                }
            
            });
        cbType.autoSize();
	cbGroupName.autoSize();
	cbLabelItem.setBounds(new Rectangle(10, 190, 260, 35));
	cbFilters.setBounds(new Rectangle(10, 230, 260, 35));
	btnRemoveFilter.setBounds(new Rectangle(380, 240, 30, 30));
	btnRemoveFilter.setToolTipText("Borrar el filtro");
	btnRemoveFilter.addActionListener(new ActionListener() {

				       public void actionPerformed(ActionEvent e) {
					   btnRemoveFilter_actionPerformed(e);
				       }

				   }
	);
	btnModifyFilterStyle.setBounds(new Rectangle(275, 240, 30, 30));
	btnModifyFilterStyle.setToolTipText("Configurar estilo del filtro");
	btnModifyFilter.setBounds(new Rectangle(310, 240, 30, 30));
	btnModifyFilter.setToolTipText("Modificar el filtro");
	btnModifyFilter.addActionListener(new ActionListener() {

				       public void actionPerformed(ActionEvent e) {
					   btnModifyFilter_actionPerformed(e);
				       }

				   }
	);
	btnAddFilter.setBounds(new Rectangle(345, 240, 30, 30));
	btnAddFilter.setToolTipText("Agregar nuevo filtro");
	btnStyle.setToolTipText("Configurar estilo del layer");
	btnStyle.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnStyle_actionPerformed(e);
				}

			    }
	);
	btnModifyFilterStyle.addActionListener(new ActionListener() {

					    public void actionPerformed(ActionEvent e) {
						btnModifyFilterStyle_actionPerformed(e);
					    }

					}
	);
	btnAddFilter.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					btnAddFilter_actionPerformed(e);
				    }

				}
	);
	btnFindLabel.setBounds(new Rectangle(275, 200, 30, 30));
	btnFindLabel.setToolTipText("Buscar relaciones entre las tablas");
	btnFindLabel.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnFindLabel_actionPerformed(e);
		    }

		});
	btnAutoFilters.setBounds(new Rectangle(305, 200, 30, 30));
	btnAutoFilters.setToolTipText("Generar filtros automáticos");
	btnAutoFilters.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnAutoFilters_actionPerformed(e);
		    }

		});
        btnExportarConfig.setBounds(new Rectangle(350, 95, 30, 30));
        btnImportarConfig.setBounds(new Rectangle(320, 95, 30, 30));
        btnRefreshConfig.setBounds(new Rectangle(230, 95, 30, 30));
        btnRefreshConfig.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnRefreshConfig_actionPerformed(e);
                }
            });
        btnConnection.setBounds(new Rectangle(380, 5, 30, 35));
	btnConnection.setToolTipText("Configurar conexión");
        btnConnection.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnConnection_actionPerformed(e);
                }
            });
        cbConfiguraciones.setBounds(new Rectangle(10, 85, 215, 35));
        cbConfiguraciones.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if(e.getStateChange() == ItemEvent.SELECTED){
                        
                    }
                    
                }
            });
        btnAddConfig.setBounds(new Rectangle(260, 95, 30, 30));
        btnAddConfig.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnAddConfig_actionPerformed(e);
                }
            });
        btnDeleteConfig.setBounds(new Rectangle(290, 95, 30, 30));
        btnDeleteConfig.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnDeleteConfig_actionPerformed(e);
                }
            });
        centralPanel.add(btnOrderLayer, null);
        centralPanel.add(btnImportarConfig, null);
        centralPanel.add(btnExportarConfig, null);
        centralPanel.add(btnRefreshConfig, null);
        centralPanel.add(btnConnection, null);
        centralPanel.add(btnDeleteConfig, null);
        centralPanel.add(btnAddConfig, null);
        centralPanel.add(cbConfiguraciones, null);
        centralPanel.add(chkDrawGeometries, null);
        centralPanel.add(chkPaintLabels, null);
        centralPanel.add(tfAlias, null);
        centralPanel.add(tfMinScale, null);
        centralPanel.add(tfMaxScale, null);
        centralPanel.add(tfSymbolScale, null);
        centralPanel.add(tfTolerance, null);
        centralPanel.add(tfPointDiameter, null);
        centralPanel.add(chkOn, null);
        centralPanel.add(chkActive, null);
        centralPanel.add(cbType, null);
        centralPanel.add(cbGroupName, null);
        centralPanel.add(cbLabelItem, null);
        centralPanel.add(cbFilters, null);
        centralPanel.add(btnRemoveFilter, null);
        centralPanel.add(btnFindLabel, null);
        centralPanel.add(btnAutoFilters, null);
        centralPanel.add(btnModifyFilterStyle, null);
        centralPanel.add(btnModifyFilter, null);
        centralPanel.add(btnAddFilter, null);
	btnSave.setToolTipText("Graba la configuración y recarga el layer (Shift para no recargar)");
        btnSave.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnSave_actionPerformed(e);
				 }

			     }
	);
	chkDrawGeometries.setText("Geometrías");
	chkDrawGeometries.setBounds(new Rectangle(110, 125, 100, 20));
	chkPaintLabels.setText("Etiquetas");
	chkPaintLabels.setBounds(new Rectangle(210, 125, 100, 20));
	centralPanel.setLayout(null);
	cbLabelItem.autoSize();
	cbFilters.autoSize();
	this.add(centralPanel, BorderLayout.CENTER);
	this.addButton(btnSave);
	this.addButton(btnStyle);
	loadCombos();
        btnRefreshConfig.setToolTipText("Cargar configuración");
        btnAddConfig.setToolTipText("Nueva configuración");
        btnDeleteConfig.setToolTipText("Borrar configuración");
        btnExportarConfig.setToolTipText("Exportar configuración");
        btnExportarConfig.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnExportarConfig_actionPerformed(e);
                }
            });
        btnImportarConfig.setToolTipText("Importar configuración");
        btnImportarConfig.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnImportarConfig_actionPerformed(e);
                }
            });
        btnOrderLayer.setBounds(new Rectangle(210, 55, 30, 30));
        btnOrderLayer.setToolTipText("Ordenar Layer");
        btnOrderLayer.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnOrderLayer_actionPerformed(e);
                }
            });
    }

    private void btnSave_actionPerformed(ActionEvent e) {
	saveData();
	if ((e.getModifiers() & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK) {
	    //no recarga
	} else {
	    layer.fetchValues();
	}
    }

    private void loadCombos() {
	cbType.removeAllItems();
	cbType.getCombo().addItem(ShapeTypes.getShapeType(ShapeTypes.POINT), ShapeTypes.POINT);
	cbType.getCombo().addItem(ShapeTypes.getShapeType(ShapeTypes.MULTIPOINT), ShapeTypes.MULTIPOINT);
	cbType.getCombo().addItem(ShapeTypes.getShapeType(ShapeTypes.POLYLINE), ShapeTypes.POLYLINE);
	cbType.getCombo().addItem(ShapeTypes.getShapeType(ShapeTypes.MULTIPOLYLINE), ShapeTypes.MULTIPOLYLINE);
	cbType.getCombo().addItem(ShapeTypes.getShapeType(ShapeTypes.POLYGON), ShapeTypes.POLYGON);
	cbType.getCombo().addItem(ShapeTypes.getShapeType(ShapeTypes.MULTIPOLYGON), ShapeTypes.MULTIPOLYGON);
	cbGroupName.removeAllItems();
	for (int i = 0; i < GaiaEnvironment.layerGroups.size(); i++) {
	    //cbGroupName.getCombo().addItem(GaiaEnvironment.layerGroups.elementAt(i).getName());
	    cbGroupName.getCombo().addItem(GaiaEnvironment.layerGroups.elementAt(i).getName(),i);
	}
    }

    public boolean saveData() {
	layerConfig.setAlias(tfAlias.getString());
	//layerConfig.setIdGroupName(Integer.parseInt(cbGroupName.getSelectedValue().toString()));
	layerConfig.setGroupName(cbGroupName.getSelectedItem().toString());
	//layerConfig.setShapeType(Integer.parseInt(cbType.getSelectedValue().toString()));
	layerConfig.setShapeTypeName(cbType.getSelectedItem().toString());
	layerConfig.setMinScale(tfMinScale.getAmount());
	layerConfig.setMaxScale(tfMaxScale.getAmount());
	layerConfig.setOn(chkOn.isSelected());
	layerConfig.setActive(chkActive.isSelected());
        layer.setOn(chkOn.isSelected());
	layerConfig.setDrawGeometries(chkDrawGeometries.isSelected());
	layerConfig.setPaintLabels(chkPaintLabels.isSelected());
	layer.getGeometrySetConfig().setPointDiameter(tfPointDiameter.getInteger());
	layer.getGeometrySetConfig().setTolerance(tfTolerance.getInteger());
	layerConfig.setPointDiameter(tfPointDiameter.getInteger());
	layerConfig.setSymbolScale(tfSymbolScale.getDouble());
	layerConfig.setTolerance(tfTolerance.getInteger());
	layerConfig.setLabelColumn(cbLabelItem.getSelectedValueRef().toString().trim());
        layerConfig.setFilterColumn(cbFilters.getSelectedValueRef().toString().trim());
	//Cuidado!!! Santiago - Revisar esto!
	//System.out.println("Beta line");
	if (cbLabelItem.getSelectedValueRef().toString().equals("0")) {
	    layerConfig.setQueryLabel("");
	}
        //actualizo el layerConfig modificado en el listado 
        layer.getLayerConfigList().setLayerConfig(layerConfig, layer.getIdConfiguracionSeleccionada());//es + 1 porque la config 0 es la base
        layer.saveData();
        layer.setIdConfiguracionSeleccionada(cbConfiguraciones.getSelectedIndex()+1);
        layer.getLayerConfig().setIdEtiquetaSeleccionada(cbLabelItem.getSelectedIndex());
	getParentInternalFrame().close();
	return true;
    }
    
    public void setLayer(Layer _layer) {
	layer = _layer;
	layerConfig = _layer.getLayerConfig();
	loadData();
    }

    private void loadData() {
	tfAlias.setValue(layerConfig.getAlias());
	//tfAlias.setEditable(false);
	getParentInternalFrame().setInfo(layerConfig.getAlias());
	cbGroupName.setSelectedValue(layerConfig.getGroupName());
	//cbGroupName.setEnabled(false);
	//layerConfig.setGroupName(cbGroupName.getSelectedItem().toString());
	cbType.setSelectedID(layer.getGeometrySetConfig().getShapeType());
	cbType.setEnabled(false);
	//layerConfig.setType(cbGroupName.getSelectedItem().toString());
	tfMinScale.setValue(layerConfig.getMinScale());
	tfMaxScale.setValue(layerConfig.getMaxScale());
	chkOn.setSelected(layerConfig.isOn());
	chkActive.setSelected(layerConfig.isActive());
	chkDrawGeometries.setSelected(layerConfig.drawGeometries());
	chkPaintLabels.setSelected(layerConfig.paintLabels());
	tfPointDiameter.setValue(layerConfig.getPointDiameter());
	tfSymbolScale.setValue(layerConfig.getSymbolScale());
	tfTolerance.setValue(layerConfig.getTolerance());
	reloadLabelItems();
	reloadFilters();
        if(nuevaConfiguracion){
            reloadConfigList();
        }
    }

    private void btnModifyFilterStyle_actionPerformed(ActionEvent e) {
	if (layerConfig.getFilters().size() > 0 && cbFilters.getSelectedIndex() != -1) {
	    LayerFilter _layerFilter = (LayerFilter)cbFilters.getSelectedValue();
	    ExtendedInternalFrame _styleConfigInternalFrame = new ExtendedInternalFrame("Filtro " + layerConfig.getAlias());
	    StyleConfigPanel _styleConfigPanel = new StyleConfigPanel();
	    _styleConfigInternalFrame.setCentralPanel(_styleConfigPanel);
	    _styleConfigPanel.setStyleConfig(_layerFilter.getStyleConfig());
	    _styleConfigPanel.setLayerConfig(layer.getLayerConfig());
	    _styleConfigInternalFrame.setClosable(true);
	    _styleConfigInternalFrame.setDesktop(Environment.getActiveDesktop());
	    _styleConfigInternalFrame.setIconifiable(false);
	    _styleConfigInternalFrame.show();
	}
    }

    
    private void btnAddFilter_actionPerformed(ActionEvent e) {
	if ( ((e.getModifiers() & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK) ) {
	    if (Advisor.question("Filtro automático", "¿Desea crear el filtro para el resto?")) {
		boolean _found = false;
		int i = 0;
		while (i < layerConfig.getFilters().size() && !_found) {
		    _found = layerConfig.getFilters().get(i).getName().equals("[RESTANTES]");
		    i++;
		}
		if (_found) {
		    layer.removeFilter(layerConfig.getFilters().get(i-1));
		}
	        LayerFilter _layerFilter = new LayerFilter("[RESTANTES]", "''",  "true", null, null, 1, "[RESTANTES]");
		layerConfig.addFilter(_layerFilter);
	        reloadFilters();
	    }
	} else {
	    Object[] _topValues = new Object[layerConfig.getTopValues().size()];
	    //Convierto las columnas de cbLabelItem para revisar que no haya casos vacíos,
	    //si no hago esto, aparece un JList de tamaño pequeño en el JOptionPane
	    Vector<String> _columns = new Vector<String>();
	    for (int i = 0; i < cbLabelItem.getCombo().getItemsVector().size(); i++) {
		if (cbLabelItem.getCombo().getItemsVector().elementAt(i).toString().length() > 0) {
		    _columns.add(cbLabelItem.getCombo().getItemsVector().elementAt(i).toString() + ": [" + cbLabelItem.getCombo().getValuesVector().elementAt(i).toString() + "]");
		}
	    }
	    String _column = ((String)JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Seleccione la columna", "Columna del Filtro", JOptionPane.QUESTION_MESSAGE, null, _columns.toArray(), cbLabelItem.getSelectedItem()));
	    //String _column = (String)JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Seleccione la columna", "Columna del Filtro", JOptionPane.QUESTION_MESSAGE, null, cbLabelItem.getCombo().getItemsVector().toArray(), cbLabelItem.getSelectedItem());
	    
	    /*
            StringBuffer _query = new StringBuffer();
            FilterQueryBuilderPanel _filterBuilder = new FilterQueryBuilderPanel(_query);
	    System.out.println();
	    _filterBuilder.setLayer(layer);
	    _filterBuilder.setVisible(true);
            */
            
            cbLabelItem.setSelectedValue(_column);
            _column = cbLabelItem.getSelectedValueRef().toString();

	    //layerConfig.setQueryFilter(_query.toString(),_filterBuilder.getLabelColumn());////
            //String _column = _filterBuilder.getLabelColumn();
	    String _selectedFilter = null;
	    if (_column != null) {
	        _column = _column.split(":")[0];
		if (_column.equals(cbLabelItem.getCombo().getItemAt(cbLabelItem.getCombo().getItemCount()-1))) {
		    for (int i = 0; i < layerConfig.getTopValues().size(); i++)  {
			_topValues[i] = layerConfig.getTopValues().elementAt(i)[0].toString() + " --> " + layerConfig.getTopValues().elementAt(i)[1].toString() + " resultado/s";
		    }
                    if(_topValues.length > 0){
                        _selectedFilter = (String)JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Seleccione el resultado esperado", "Resultado esperado", JOptionPane.QUESTION_MESSAGE, null, _topValues, _topValues[0]);   
                    }
		}
		String _toolTipValue = (String)JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Ingrese el Nombre del Filtro", "Nombre del Filtro", JOptionPane.QUESTION_MESSAGE, null, null, new String(_selectedFilter!=null?_selectedFilter.split(" --> ")[0]:""));
		String _expresion = (String)JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Ingrese la expresión", "Expresión del Filtro", JOptionPane.QUESTION_MESSAGE, null, null, new String(_selectedFilter!=null?("upper(" + _column + ") = '" + _selectedFilter.split(" --> ")[0] + "'"):new String(_column!=null?_column:"")));
		if (_toolTipValue != null && _expresion != null && _column != null) {
		    LayerFilter _layerFilter = new LayerFilter(_toolTipValue, _column,  _expresion, null, null, 1, _toolTipValue);
		    layerConfig.addFilter(_layerFilter);
		    ExtendedInternalFrame _styleConfigInternalFrame = new ExtendedInternalFrame("Filtro " + _layerFilter.getName());
		    StyleConfigPanel _styleConfigPanel = new StyleConfigPanel();
		    _styleConfigInternalFrame.setCentralPanel(_styleConfigPanel);
		    _styleConfigPanel.setStyleConfig(_layerFilter.getStyleConfig());
		    _styleConfigPanel.setLayerConfig(layer.getLayerConfig());
		    _styleConfigInternalFrame.setClosable(true);
		    _styleConfigInternalFrame.setDesktop(Environment.getActiveDesktop());
		    _styleConfigInternalFrame.setIconifiable(false);
		    _styleConfigInternalFrame.show();
		    reloadFilters();
		} else {
		    JOptionPane.showInternalMessageDialog(Environment.getActiveDesktop(), "Debe completar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
		}
	    }
	}
    }

    private void btnStyle_actionPerformed(ActionEvent e) {
	if (layerConfig.getStyleConfig() != null) {
	    ExtendedInternalFrame _styleConfigInternalFrame = new ExtendedInternalFrame("Layer " + layerConfig.getAlias());
	    StyleConfigPanel _styleConfigPanel = new StyleConfigPanel();
	    _styleConfigInternalFrame.setCentralPanel(_styleConfigPanel);
	    _styleConfigPanel.setStyleConfig(layerConfig.getStyleConfig());
	    _styleConfigPanel.setLayerConfig(layer.getLayerConfig());
	    _styleConfigInternalFrame.setClosable(true);
	    _styleConfigInternalFrame.setDesktop(Environment.getActiveDesktop());
	    _styleConfigInternalFrame.setIconifiable(false);
	    _styleConfigInternalFrame.show();
	}
    }

    private void btnModifyFilter_actionPerformed(ActionEvent e) {
	if (layerConfig.getFilters().size() > 0 && cbFilters.getSelectedIndex() != -1) {
	    LayerFilter _layerFilter = (LayerFilter)(cbFilters.getSelectedValue());
	    String _toolTipValue = (String)JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Ingrese el Texto", "Texto del Filtro", JOptionPane.QUESTION_MESSAGE, null, null, _layerFilter.getToolTipValue());
	    Object[] _topValues = new Object[layerConfig.getTopValues().size()];
	    //Convierto las columnas de cbLabelItem para revisar que no haya casos vacíos,
	    //si no hago esto, aparece un JList de tamaño pequeño en el JOptionPane
	    Vector<String> _columns = new Vector<String>();
	    for (int i = 0; i < cbLabelItem.getCombo().getItemsVector().size(); i++) {
	        if (cbLabelItem.getCombo().getItemsVector().elementAt(i).toString().length() > 0) {
	            _columns.add(cbLabelItem.getCombo().getItemsVector().elementAt(i).toString() + ": [" + cbLabelItem.getCombo().getValuesVector().elementAt(i).toString() + "]");
	        }
	    }
	    String _column = (String)JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Ingrese la columna", "Columna del Filtro", JOptionPane.QUESTION_MESSAGE, null, _columns.toArray(), _layerFilter.getColumn());
	    String _expresion = (String)JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Ingrese la expresión", "Expresión del Filtro", JOptionPane.QUESTION_MESSAGE, null, null, _layerFilter.getExpression());
	    if (_toolTipValue != null && _expresion != null && _column != null) {
	        _layerFilter.setName(_toolTipValue);
	        _layerFilter.setToolTipValue(_toolTipValue);
                
                //resguardo el item seleccionado antes de la modificación del filtro
                int itemSeleccionado = cbLabelItem.getSelectedIndex();
                cbLabelItem.setSelectedValue(_column);
	        _layerFilter.setColumn(cbLabelItem.getSelectedValueRef().toString());
	        cbLabelItem.getCombo().setSelectedIndex(itemSeleccionado);
		//_layerFilter.setColumn(_column);
                
	        _layerFilter.setExpression(_expresion);
		reloadFilters();
	    } else {
		JOptionPane.showInternalMessageDialog(Environment.getActiveDesktop(), "Debe completar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
    }

    private void btnRemoveFilter_actionPerformed(ActionEvent e) {
	if ( ((e.getModifiers() & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK) ) {
	    if (JOptionPane.showInternalConfirmDialog(Environment.getActiveDesktop(), "¿Está seguro de borrar todos los filtros?", "Borrar filtros", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
	        layerConfig.getFilters().removeAllElements();
		layerConfig.setExtendedQuery(false);
	        reloadFilters();
	    }
	} else {
	    if (layerConfig.getFilters().size() > 0 && cbFilters.getSelectedIndex() != -1) {
		LayerFilter _layerFilter = (LayerFilter)(cbFilters.getSelectedValue());
		if (JOptionPane.showInternalConfirmDialog(Environment.getActiveDesktop(), "¿Está seguro de borrar el filtro " + _layerFilter.getToolTipValue() + "?", "Borrar filtro", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
		    layer.removeFilter(_layerFilter);
		    reloadFilters();
		}
	    }
	}
    }

    private void btnFindLabel_actionPerformed(ActionEvent e) {
	//_filterBuilderInternalFrame.setSize();
	StringBuffer _query = new StringBuffer("");
	FilterQueryBuilderPanel _filterBuilder = new FilterQueryBuilderPanel(_query);
	_filterBuilder.setLayer(layer);
	_filterBuilder.setVisible(true);
        
	if (_query.length() > 0) {
            cbLabelItem.loadJCombo("SELECT '' AS datatype, '' AS refvalue, ' ' AS column UNION SELECT typname AS datatype, " +
            "CASE WHEN " + 
            "    col_description(ta.attrelid,ta.attnum) IS NULL THEN attname " + 
            "    WHEN col_description(ta.attrelid,ta.attnum) = '' THEN attname " + 
            "    ELSE col_description(ta.attrelid,ta.attnum) " + 
            "END " + 
            "AS refvalue, attname AS column FROM pg_class bc, pg_attribute ta, " + "pg_namespace ns, " + " pg_type ty WHERE ta.attrelid = bc.oid and ta.attnum > 0  and not ta.attisdropped and bc.relnamespace = ns.oid " + " and ns.nspname = '" + layer.getGeometrySetConfig().getSqlScheme() + "' and relam = 0 AND bc.relname = '" + layer.getGeometrySetConfig().getSqlTable() + "' " + " and nspname not like 'pg_%' and ta.atttypid = ty.oid order by datatype");
            for(int i = 0; i < cbLabelItem.getCombo().getItemCount(); i++){
                cbLabelItem.getCombo().setSelectedIndex(i);
                String nombreColumna = cbLabelItem.getSelectedValueRef().toString();
                layerConfig.setQueryLabel(nombreColumna,nombreColumna);
                layerConfig.setQueryFilter(nombreColumna,nombreColumna);
            }
            //String labelQuery = "(SELECT " + layer.getGeometrySetConfig().getSqlTable() + "." + tableColumn + " FROM " + _from + " WHERE " + _condition  + " LIMIT 1) AS " + labelColumn;
            cbLabelItem.getCombo().addItem(_filterBuilder.getLabelColumn(), _query, _filterBuilder.getLabelColumn());
	    cbLabelItem.setSelectedValue(_filterBuilder.getLabelColumn());
	    //layerConfig.setQueryLabel(_query.toString());
	    layerConfig.setQueryLabel(_query.toString(),_filterBuilder.getLabelColumn());////
	    layerConfig.setQueryFilter(_query.toString(),_filterBuilder.getLabelColumn());////
	    layerConfig.setLabelColumn("");
	    layerConfig.setExtendedQuery(true);
	    layer.fetchTopValues(_filterBuilder.getLabelScheme(), _filterBuilder.getLabelTable(), _filterBuilder.getTableColumn());
	    if (Advisor.question("Filtros automáticos", "¿Desea crear filtros automáticamente?\n(se borrarán todos los filtros previos)")) {
		doAutoFilters();
	    }
	} else if (Advisor.question("Filtro", "¿Desea borrar el filtro previamente creado?")) {
	    layerConfig.setQueryFilter("");
	    layerConfig.setExtendedQuery(false);
	    reloadLabelItems();
	}
	/*
	layerConfig.getImageAttachments().removeAllElements();
	ImageAttachment _attachment = new ImageAttachment();
	_attachment.selectImageFile();
	layerConfig.getImageAttachments().add(_attachment);
	*/
    }
    
    private void reloadLabelItems() {
	cbLabelItem.loadJCombo("SELECT '' AS datatype, '' AS refvalue, ' ' AS column UNION SELECT typname AS datatype, " +
	"CASE WHEN " + 
	"    col_description(ta.attrelid,ta.attnum) IS NULL THEN attname " + 
	"    WHEN col_description(ta.attrelid,ta.attnum) = '' THEN attname " + 
	"    ELSE col_description(ta.attrelid,ta.attnum) " + 
	"END " + 
	"AS refvalue, attname AS column FROM pg_class bc, pg_attribute ta, " + "pg_namespace ns, " + " pg_type ty WHERE ta.attrelid = bc.oid and ta.attnum > 0  and not ta.attisdropped and bc.relnamespace = ns.oid " + " and ns.nspname = '" + layer.getGeometrySetConfig().getSqlScheme() + "' and relam = 0 AND bc.relname = '" + layer.getGeometrySetConfig().getSqlTable() + "' " + " and nspname not like 'pg_%' and ta.atttypid = ty.oid order by refvalue");
	////Vector columnsVector = cbLabelItem.getCombo().getItemsVector();
	Vector columnsVector = new Vector();
	for(int i = 0; i < cbLabelItem.getCombo().getItemCount();i++){
            cbLabelItem.getCombo().setSelectedIndex(i);
            columnsVector.add(cbLabelItem.getSelectedValueRef());
        }
	if (!columnsVector.contains(layerConfig.getLabelColumn())) {
	    columnsVector.add(layerConfig.getLabelColumn());
	    cbLabelItem.getCombo().addItem(layerConfig.getLabelColumn(), layerConfig.getQueryLabel(), layerConfig.getLabelColumn());
	}
        
        for(int i = 0; i < cbLabelItem.getCombo().getItemCount(); i++){
            cbLabelItem.getCombo().setSelectedIndex(i);
            String nombreColumna = cbLabelItem.getSelectedValueRef().toString();
            if((layerConfig.getQueryLabel(nombreColumna) == null)){
                layerConfig.setQueryLabel(nombreColumna,nombreColumna);
                layerConfig.setQueryFilter(nombreColumna,nombreColumna);
            }
        }
	//cbLabelItem.setSelectedValue(layerConfig.getLabelColumn());
	cbLabelItem.getCombo().setSelectedIndex(layer.getLayerConfig().getIdEtiquetaSeleccionada());
    }

    private void reloadFilters() {
	cbFilters.removeAllItems();
	for (int i = 0; i < layerConfig.getFilters().size(); i++) {
	    cbFilters.getCombo().addItem(layerConfig.getFilters().elementAt(i).getToolTipValue(), layerConfig.getFilters().elementAt(i), layerConfig.getFilters().elementAt(i).getColumn());
	}
    }

    private void doAutoFilters() {
	if (Advisor.question("Atención", "Se borrarán todos los filtros previos, ¿Está seguro?")) {
	    layerConfig.setLabelColumn(cbLabelItem.getSelectedValueRef().toString());
	    if (layerConfig.isExtendedQuery()) {
		layerConfig.setQueryFilter(cbLabelItem.getSelectedValue().toString());
	    } else {
	        layerConfig.setTopValuesQuery("");
	        layerConfig.setQueryFilter("");
	    }
	    layer.fetchTopValues();
	    Object[] _topValues = new Object[layerConfig.getTopValues().size()];
	    for (int i = 0; i < layerConfig.getTopValues().size(); i++)  {
		_topValues[i] = layerConfig.getTopValues().elementAt(i)[0].toString() + " --> " + layerConfig.getTopValues().elementAt(i)[1].toString() + " resultado/s";
	    }
	    String _limitValue = (String)JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Seleccione un dato como límite para la creación de filtros", "Dato límite", JOptionPane.QUESTION_MESSAGE, null, _topValues, _topValues[0]);
	    if (_limitValue != null) {
	        layerConfig.getFilters().removeAllElements();
		layer.resetFilterMap();
		boolean _found = false;
		int i = 0;
		while (!_found && i < _topValues.length) {
		    String _toolTipValue = _topValues[i].toString().split(" --> ")[0].trim();
		    String _column = cbLabelItem.getSelectedValueRef().toString();
		    String _expresion = "trim(upper(" + _column + "::text)) = upper('" + _toolTipValue + "')";
		    LayerFilter _layerFilter = new LayerFilter((_toolTipValue.length()>0?_toolTipValue:"[FILTRO VACÍO]"), _column,  _expresion, null, null, 1, _toolTipValue);
		    //layerConfig.getFilters().add(_layerFilter);
                    layerConfig.addFilter(_layerFilter);
		    Random rand = new Random();
		    //Copio algunos atributos del StyleConfig del layer
		    _layerFilter.getStyleConfig().setLabelFont(layerConfig.getStyleConfig().getLabelFont());
		    _layerFilter.getStyleConfig().setLabelColor(layerConfig.getStyleConfig().getLabelColor());
		    _layerFilter.getStyleConfig().setTransparency(layerConfig.getStyleConfig().getTransparency());
		    _layerFilter.getStyleConfig().setLineWidth(layerConfig.getStyleConfig().getLineWidth());
		    _layerFilter.getStyleConfig().setStrokeStyle(layerConfig.getStyleConfig().getStrokeStyle());
		    //Le seteo un color de relleno/contorno aleatorio
		     Color _color = new Color(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256));
		     switch (layer.getGeometrySetConfig().getShapeType())      {
		         case ShapeTypes.POLYLINE:
		         case ShapeTypes.MULTIPOLYLINE:
			    _layerFilter.getStyleConfig().setOutlineColor(_color);
		            break;      
		         case ShapeTypes.POINT:
		         case ShapeTypes.MULTIPOINT:
		         case ShapeTypes.POLYGON:
		         case ShapeTypes.MULTIPOLYGON:
			    _layerFilter.getStyleConfig().setOutlineColor(_color);
			    _layerFilter.getStyleConfig().setFillColor(_color);
			    break;
		     }
		    _found = _limitValue.equals(_topValues[i].toString());
		    i++;
		}
	        reloadFilters();
	    } else {
	        Advisor.messageBox("<html><p align=center>Debe seleccionar un dato límite para el filtro<br>Se canceló la operacion</p></html>", "Error");
	    }
	}
    }

    private void btnAutoFilters_actionPerformed(ActionEvent e) {
	doAutoFilters();
    }

    private void btnConnection_actionPerformed(ActionEvent e) {
        if (layerConfig != null) {
            ExtendedInternalFrame _connectionPanelInternalFrame = new ExtendedInternalFrame("Conexión");
            ConnectionPanel _layerConfigPanel = new ConnectionPanel();
            _connectionPanelInternalFrame.setCentralPanel(_layerConfigPanel);
            _layerConfigPanel.setGeometrySetConfig(layer.getGeometrySetConfig());
            _connectionPanelInternalFrame.setClosable(true);
            _connectionPanelInternalFrame.setDesktop(Environment.getActiveDesktop());
            _connectionPanelInternalFrame.setIconifiable(false);
            _connectionPanelInternalFrame.show();
        }
    }

    private void reloadConfigList() {
        nuevaConfiguracion = false;
        cbConfiguraciones.removeAllItems();
        for(int i=1; i< layer.getLayerConfigList().size(); i++){
            cbConfiguraciones.getCombo().addItem(layer.getLayerConfigList().elementAt(i).getNombreConfiguracion(),""+i,"0");
        }
        cbConfiguraciones.getCombo().setSelectedIndex(layer.getIdConfiguracionSeleccionada()-1);//la configuracion por defecto no se muestra
    }

    private void btnAddConfig_actionPerformed(ActionEvent e) {
        String _nombreConfig = JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Ingrese nombre de la nueva configuración", "Configuración del Layer", JOptionPane.QUESTION_MESSAGE);
        if (_nombreConfig != null) {
            _nombreConfig = _nombreConfig.toUpperCase();
            LayerConfig layerConfigBase = layer.getLayerConfigList().elementAt(0);
            LayerConfig nuevoLayerConfig = layerConfigBase.getCopy();
            int idLayerConfig = layer.getLayerConfigList().size();
            //agrego nuevo layerConfig a la lista del layer
            layer.getLayerConfigList().add(nuevoLayerConfig);
            //agrego la lista al nuevo layerConfig
            nuevoLayerConfig.setLayerConfigList(layer.getLayerConfigList());
            //asigno el ID al layerConfig
            nuevoLayerConfig.setIdLayerConfig(idLayerConfig);
            //asigno nombre de la nueva configuracion
            nuevoLayerConfig.setNombreConfiguracion(_nombreConfig);
            nuevoLayerConfig.setListadoQueryFilter((HashMap)layerConfig.getListadoQueryFilter().clone());
            nuevoLayerConfig.setListadoQueryLabel((HashMap)layerConfig.getListadoQueryLabel().clone());
            //guardo los cambios
            layer.getLayerConfigList().saveConfigData();
            
            layer.fetchLayerConfigListFromCache();
            //asigno al layer el nuevo layerConfig
            layer.setLayerConfig(nuevoLayerConfig);
            //recargo los datos
            nuevaConfiguracion = true;
            layer.setIdConfiguracionSeleccionada(idLayerConfig);
            setLayer(layer);
        }
    }

    private void btnDeleteConfig_actionPerformed(ActionEvent e) {
        if (Advisor.question("Borrar configuración","¿Está seguro de borrar la configuración \"" + cbConfiguraciones.getSelectedItem() + "\"?")) {
	    if (layer.getLayerConfigList().remove(layer.getLayerConfigList().elementAt(Integer.parseInt(cbConfiguraciones.getSelectedValue().toString())))) {
	        layer.setLayerConfig(layer.getLayerConfigList().elementAt(1));
	        layer.setIdConfiguracionSeleccionada(1);
	        nuevaConfiguracion = true;
                setLayer(layer);
	        layer.saveData();
                layer.fetchValues();
	    }
        }
    }

    private void btnRefreshConfig_actionPerformed(ActionEvent e) {
	cargarConfiguracion();
    }
    
    private void cargarConfiguracion() {
        nuevaConfiguracion = false;
        layer.setIdConfiguracionSeleccionada(Integer.parseInt(cbConfiguraciones.getSelectedValue().toString()));
        //obtengo el layerConfig seleccionado
        LayerConfig _layerConfig = layer.getLayerConfigList().elementAt(layer.getIdConfiguracionSeleccionada());
        layer.setLayerConfig(_layerConfig);
        setLayer(layer);
        layer.fetchValues();
    }

    private void btnExportarConfig_actionPerformed(ActionEvent e) {
        if (JOptionPane.showInternalConfirmDialog(Environment.getActiveDesktop(), "¿Desea exportar el listado de Configuraciones?", "Exportar configuración", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            JFileChooser fc=new JFileChooser();
            if (!Environment.cfg.getProperty("configList").equalsIgnoreCase(ConfigFile.nullProperty))  {
                fc.setCurrentDirectory(new File(Environment.cfg.getProperty("configList")));
            }
            fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int opcion = fc.showSaveDialog(this);
            if (opcion != fc.CANCEL_OPTION) {
                File file = fc.getSelectedFile();
                Environment.cfg.setProperty("configList", file.getPath().replaceAll(file.getName(),""));
                layer.getLayerConfigList().setFileName(file.getAbsolutePath());
                layer.getLayerConfigList().exportLayerConfig();
            }
            
        }
    }

    private void btnImportarConfig_actionPerformed(ActionEvent e) {
        JFileChooser fc=new JFileChooser();
        if (!Environment.cfg.getProperty("configList").equalsIgnoreCase(ConfigFile.nullProperty))  {
            fc.setCurrentDirectory(new File(Environment.cfg.getProperty("configList")));
        }
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int opcion = fc.showOpenDialog(this);
        if (opcion != fc.CANCEL_OPTION) {
            File file = fc.getSelectedFile();
            Environment.cfg.setProperty("configList", file.getPath().replaceAll(file.getName(),""));
            layer.getLayerConfigList().importLayerConfigList(file.getAbsolutePath());
            nuevaConfiguracion = true;
            setLayer(layer);
            cargarConfiguracion();
            reloadConfigList();
        }
    }

    private void btnOrderLayer_actionPerformed(ActionEvent e) {
        showLayerTransferList();
    }
    
    private void showLayerTransferList() {
        ExtendedInternalFrame _layerTransferListInternalFrame = new ExtendedInternalFrame("Modificar posición del layer " + layer.getAlias());
        LayerTransferList _layerTransferList = new LayerTransferList();
        _layerTransferListInternalFrame.setCentralPanel(_layerTransferList);
        _layerTransferList.setLayerName(layer.getAlias());
        _layerTransferList.setLayerGroupName(cbGroupName.getSelectedItem().toString());
        _layerTransferListInternalFrame.setClosable(true);
        _layerTransferListInternalFrame.setDesktop(Environment.getActiveDesktop());
        _layerTransferListInternalFrame.setIconifiable(false);
        _layerTransferListInternalFrame.show();
    }

    private void cbGroupName_itemStateChanged(ItemEvent e) {
        if (cbGroupName.getSelectedIndex() > -1) {
            GaiaEnvironment.gaiaEngine.moveLayerToGroup(layer.getLayerConfig().getGroupName(), layer.getName(), cbGroupName.getSelectedItem().toString());
        }
    }
}
