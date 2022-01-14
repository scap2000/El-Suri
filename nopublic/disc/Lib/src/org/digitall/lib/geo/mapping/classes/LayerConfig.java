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
 * LayerConfig.java
 *
 * */
package org.digitall.lib.geo.mapping.classes;

import java.awt.Color;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Vector;

public class LayerConfig implements Serializable {
    
    private static final long serialVersionUID = 10000095L;
    
    private int symbol = -1;
    private boolean drawGeometries = true;
    private boolean paintLabels = true;
    //For points,  -1 = Not Set
    private int pointDiameter = 4; // 4 metros por defecto
    //For polylines and polygons, -1 = Not Set
    private int lineType = -1;
     //For polygons, -1 = Not Set
    private int fillPatern = -1;
    private String name = "";
    private String alias = "";
    private int idGroupName = -1;
    private String groupName = "";
    private String shapeTypeName = "";
    private double minScale = 0.0; //0m por defecto
    private double maxScale = 50000.0; //50000m por defecto
    private double symbolScale = 0.0;
    private boolean on = false;
    private boolean active = false;
    private String labelColumn = "";
    private String filterColumn = "";
    private String queryFilter = "";
    private String queryLabel = "";
    private int labelFontSize = 10;
    private String typeColumn = "";
    private StringBuffer toolTipText;
    private String toolTipLabel;
    private String toolTipValueColumn = "";
    private boolean queryable = false;
    private boolean modifiable = false;
    //tolerancia en metros
    private int tolerance = 4;
    private boolean editable = false;

    private Vector<LayerFilter> filters = new Vector<LayerFilter>();
    private Vector<ImageAttachment> imageAttachments = new Vector<ImageAttachment>();
    private StyleConfig styleConfig;
    private int autoUpdateRateInSeconds = -1;

    private String topValuesQuery = "";    
    private Vector<Object[]> topValues = new Vector<Object[]>();
    private int labelColumnDataType = Types.VARCHAR;

    private int gridSize = 0;
    private double[][][] matrixBounds = null;
    
    private long lastFilterRetrievingTime = 0;

    private boolean extendedQuery = false;
    ///
    private HashMap listadoQueryLabel = new HashMap();
    private HashMap listadoQueryFilter = new HashMap();
    
    private LayerConfigList layerConfigList;
    private int idLayerConfig = -1;
    private String configName = "DEFAULT";
    
    private boolean selectedForReport = false;
    private int idEtiquetaSeleccionada = 0;
    
    public LayerConfig(String _name) {
	name = _name;
	alias = name;
	styleConfig = new StyleConfig(name);
    }

    public void setOutlineColor(Color _color) {
	styleConfig.setOutlineColor(_color);
    }

    public Color getOutlineColor() {
	return styleConfig.getOutlineColor();
    }

    public void setSymbol(int symbol) {
	this.symbol = symbol;
    }

    public int getSymbol() {
	return symbol;
    }

    public void setDrawGeometries(boolean drawGeometries) {
	this.drawGeometries = drawGeometries;
    }

    public boolean drawGeometries() {
	return drawGeometries;
    }

    public void setPointDiameter(int pointDiameter) {
	this.pointDiameter = pointDiameter;
    }

    public int getPointDiameter() {
	return pointDiameter;
    }

    public void setLineType(int lineType) {
	this.lineType = lineType;
    }

    public int getLineType() {
	return lineType;
    }

    public void setFillPatern(int fillPatern) {
	this.fillPatern = fillPatern;
    }

    public int getFillPatern() {
	return fillPatern;
    }

    public void setFillColor(Color _color) {
	styleConfig.setFillColor(_color);
    }

    public Color getFillColor() {
	return styleConfig.getFillColor();
    }

    public void setMouseOverColor(Color _color) {
	styleConfig.setMouseOverColor(_color);
    }

    public Color getMouseOverColor() {
	return styleConfig.getMouseOverColor();
    }

    public void setSelectedOutlineColor(Color _color) {
	styleConfig.setSelectedOutlineColor(_color);
    }

    public Color getSelectedOutlineColor() {
	return styleConfig.getSelectedOutlineColor();
    }

    public void setOn() {
	setOn(true);
    }

    public void setOn(boolean on) {
	this.on = on;
	saveData();
    }

    public boolean isOn() {
	return on;
    }

    public void setActive(boolean active) {
	this.active = active;
    }

    public boolean isActive() {
	return active;
    }

    public void setLabelColumn(String labelColumn) {
	this.labelColumn = labelColumn;
	//setTopValuesQuery("");
    }

    public String getLabelColumn() {
	return labelColumn;
    }

    public void setTypeColumn(String typeColumn) {
	this.typeColumn = typeColumn;
    }

    public String getTypeColumn() {
	return typeColumn;
    }

    public void setToolTipText(StringBuffer toolTipText) {
	this.toolTipText = toolTipText;
    }

    public StringBuffer getToolTipText() {
	return toolTipText;
    }

    public void setToolTipLabel(String toolTipLabel) {
	this.toolTipLabel = toolTipLabel;
    }

    public String getToolTipLabel() {
	return toolTipLabel;
    }

    public void setToolTipValueColumn(String toolTipValueColumn) {
	this.toolTipValueColumn = toolTipValueColumn;
    }

    public String getToolTipValueColumn() {
	return toolTipValueColumn;
    }

    public void setQueryable(boolean queryable) {
	this.queryable = queryable;
    }

    public boolean isQueryable() {
	return queryable;
    }

    public void setModifiable(boolean modifiable) {
	this.modifiable = modifiable;
    }

    public boolean isModifiable() {
	return modifiable;
    }

    public void setFilters(Vector<LayerFilter> filters) {
	this.filters = filters;
    }

    public Vector<LayerFilter> getFilters() {
	return filters;
    }

    public LayerFilter getFilter(int _id) {
        int i = 0;
        boolean found = false;
        LayerFilter returns = null;
        while (i < filters.size() && !found) {
            if (filters.elementAt(i).getID() == _id) {
                found = true;
                returns = filters.elementAt(i);
            }
            i++;
        }
        return returns;
    }

    public void setTolerance(int tolerance) {
	this.tolerance = tolerance;
    }

    public int getTolerance() {
	return tolerance;
    }

    public void setEditable(boolean editable) {
	this.editable = editable;
    }

    public boolean isEditable() {
	return editable;
    }

    public void setLabelFontSize(int labelFontSize) {
	this.labelFontSize = labelFontSize;
    }

    public int getLabelFontSize() {
	return labelFontSize;
    }

    public void setAlias(String _alias) {
	alias = _alias;
    }

    public String getName() {
	return name;
    }

    public String getAlias() {
	return alias;
    }

    public void setIdGroupName(int idGroupName) {
	this.idGroupName = idGroupName;
    }

    public int getIdGroupName() {
	return idGroupName;
    }

    public void setGroupName(String groupName) {
	this.groupName = groupName;
    }

    public String getGroupName() {
	return groupName;
    }

    public void setShapeTypeName(String _shapeTypeName) {
	this.shapeTypeName = _shapeTypeName;
    }

    public String getType() {
	return shapeTypeName;
    }

    public void setMinScale(double minScale) {
	this.minScale = minScale;
    }

    public double getMinScale() {
	return minScale;
    }

    public void setMaxScale(double maxScale) {
	this.maxScale = maxScale;
    }

    public double getMaxScale() {
	return maxScale;
    }

    public void setSymbolScale(double symbolScale) {
	this.symbolScale = symbolScale;
    }

    public double getSymbolScale() {
	return symbolScale;
    }

    public void setStyleConfig(StyleConfig styleConfig) {
	this.styleConfig = styleConfig;
    }

    public StyleConfig getStyleConfig() {
	return styleConfig;
    }

    public String parseFilter() {
	String _filter = " -1 AS _filter_ ";
	if (filters.size() > 0) {
	    /* EJEMPLO
	     CASE
	         WHEN SECCION = 'A' THEN 1
	         WHEN SECCION = 'B' THEN 2
	         ELSE -1
	     END AS _filter_

	     */
	    _filter = "CASE ";
	    for (int i = 0; i < filters.size(); i++) {
	        _filter += "WHEN " + filters.elementAt(i).getExpression() + " THEN " + 
                        filters.elementAt(i).getID()
                           + "\n";
	    }
	    _filter += "ELSE -1 END AS _filter_";
	}
	return _filter;
    }

    public void saveData() {
	/*final Object _recordable = this;
	Thread _thread = new Thread(new Runnable() {
	    public void run() {
	        System.out.println("Caching layer " + getAlias() + " configuration");
		boolean _enabled = true;
		if (!(GaiaEnvironment.tempDir.exists() && GaiaEnvironment.tempDir.isDirectory())) {
		    if (!GaiaEnvironment.tempDir.mkdir()) {
			System.err.println("Error al crear el directorio temporal");
			_enabled = false;
		    } else {
			//System.out.println("El directorio temporal se encuentra en " + _tmpDir);
		    }
		} else {
		    //System.out.println("El directorio temporal se encuentra en " + _tmpDir);
		}
		if (_enabled) {
		    try {
			// Write to disk with FileOutputStream
			FileOutputStream outFile = new FileOutputStream(GaiaEnvironment.getCacheFileName(getName())+ ".lconfig");
			// Write object with ObjectOutputStream
			ObjectOutputStream outObject = new ObjectOutputStream(outFile);
			// Write object out to disk
			outObject.writeObject(_recordable);
			outObject.close();
		    } catch (FileNotFoundException e) {
			e.printStackTrace();
		    } catch (IOException e) {
			e.printStackTrace();
		    }
		}
	        System.out.println("Caching layer " + getAlias() + " configuration done...");
	    }
	});
	_thread.setPriority(Thread.MIN_PRIORITY);
	_thread.start();
    }
    
    public void saveDataConfig() {*/
        //layerConfigList.add(this);
        layerConfigList.saveConfigData();
        
    }
    
    ///**2010-04-19(moraless)*/
    /*public void saveDataConfig(int _id) {
        layerConfigList.setLayerConfig(this, _id);
        layerConfigList.saveDataConfig();
    }*/
    

    public void setPaintLabels(boolean paintLabels) {
	this.paintLabels = paintLabels;
    }

    public boolean paintLabels() {
	return paintLabels;
    }

    public void addFilter(LayerFilter _layerFilter) {
	int i = 0;
        int _maxID = -1;
	boolean found = false;
	while (i < filters.size() && !found) {
            if (filters.elementAt(i).getID()>_maxID) {
                _maxID = filters.elementAt(i).getID();
            }
	    found = filters.elementAt(i).getExpression().equalsIgnoreCase(_layerFilter.getExpression());
	    i++;
	}
	if (!found) {
	    _layerFilter.setID(_maxID+1);
	    filters.add(_layerFilter);
	} else {
	    System.out.println("El filtro ya existe, será ignorado");
	}
    }

    public Vector<ImageAttachment> getImageAttachments() {
	return imageAttachments;
    }

    /**
     * Sólo se tiene en cuenta al momento de agregar el layer en el BasicDrawEngine
     * */
    public void setAutoUpdateRateInSeconds(int _autoUpdateRateInSeconds) {
	autoUpdateRateInSeconds = _autoUpdateRateInSeconds;
    }

    /**
     * Sólo se tiene en cuenta al momento de agregar el layer en el BasicDrawEngine
     * */
    public int getAutoUpdateRateInSeconds() {
	return autoUpdateRateInSeconds;
    }

    public Vector<Object[]> getTopValues() {
	return topValues;
    }

    public void setGridSize(int gridSize) {
	this.gridSize = gridSize;
    }

    public int getGridSize() {
	return gridSize;
    }

    public void setMatrixBounds(double[][][] matrixBounds) {
	this.matrixBounds = matrixBounds;
    }

    public double[][][] getMatrixBounds() {
	return matrixBounds;
    }

    public void setLabelColumnDataType(int _labelColumnDataType) {
	labelColumnDataType = _labelColumnDataType;
    }

    public int getLabelColumnDataType() {
	return labelColumnDataType;
    }
    
    public void setLastFilterRetrievingTime(long lastFilterRetrievingTime) {
	this.lastFilterRetrievingTime = lastFilterRetrievingTime;
    }

    public long getLastFilterRetrievingTime() {
	return lastFilterRetrievingTime;
    }

    public void setTopValuesQuery(String _topValuesQuery) {
	this.topValuesQuery = _topValuesQuery;
    }

    public String getTopValuesQuery() {
	return topValuesQuery;
    }

    public void setExtendedQuery(boolean _extendedQuery) {
	extendedQuery = _extendedQuery;
    }

    public boolean isExtendedQuery() {
	return extendedQuery;
    }
    
    public void setQueryFilter(String queryFilter) {
        this.queryFilter = queryFilter;
    }

    //moraless
    public void setQueryFilter(String _queryFilter,String _nombreColumna) {
        listadoQueryFilter.put(_nombreColumna, _queryFilter);
    }
    
    public String getQueryFilter() {
        return queryFilter;
    }

    public void setQueryLabel(String queryLabel) {
        this.queryLabel = queryLabel;
    }

    public void setQueryLabel(String _queryFilter,String _nombreColumna) {
        listadoQueryLabel.put(_nombreColumna, _queryFilter);
    }
    public String getQueryLabel() {
        return queryLabel;
    }
    
    public String getQueryLabel(String _nombreColumna){
        return (String)listadoQueryLabel.get(_nombreColumna);
    }
    
    public String getQueryFilter(String _nombreColumna){
        String expresion = (String)listadoQueryFilter.get(_nombreColumna);
        return expresion;
    }

    public void setFilterColumn(String filterColumn) {
        this.filterColumn = filterColumn;
    }

    public String getFilterColumn() {
        return filterColumn;
    }
    
    public void removeAllQueryFilter(){
        listadoQueryFilter = new HashMap();
    }
    
    public void removeAllQueryLabel(){
        listadoQueryLabel = new HashMap();
    }

    public void setLayerConfigList(LayerConfigList layerConfigList) {
        this.layerConfigList = layerConfigList;
    }

    public void setIdLayerConfig(int idLayerConfig) {
        this.idLayerConfig = idLayerConfig;
    }

    public int getIdLayerConfig() {
        return idLayerConfig;
    }
    
    public void removeAllFilters(){
        filters = new Vector<LayerFilter>();
    }

    public void setSelectedForReport(boolean selectedForReport) {
        this.selectedForReport = selectedForReport;
    }

    public boolean isSelectedForReport() {
        return selectedForReport;
    }

    public void setNombreConfiguracion(String _configName) {
        this.configName = _configName;
    }

    public String getNombreConfiguracion() {
        return configName;
    }

    public void setListadoQueryLabel(HashMap listadoQueryLabel) {
        this.listadoQueryLabel = listadoQueryLabel;
    }

    public HashMap getListadoQueryLabel() {
        return listadoQueryLabel;
    }

    public void setListadoQueryFilter(HashMap listadoQueryFilter) {
        this.listadoQueryFilter = listadoQueryFilter;
    }

    public HashMap getListadoQueryFilter() {
        return listadoQueryFilter;
    }
    
    public LayerConfig getCopy() {
        LayerConfig _layerConfig = new LayerConfig(getName());
        _layerConfig.setStyleConfig(getStyleConfig());
        _layerConfig.setDrawGeometries(drawGeometries);
        _layerConfig.setActive(isActive());
        _layerConfig.setQueryable(isQueryable());
        _layerConfig.setModifiable(isModifiable());
        //_layerConfig.setOn(isOn());
        return _layerConfig;
    }

    public void setIdEtiquetaSeleccionada(int idEtiquetaSeleccionada) {
        this.idEtiquetaSeleccionada = idEtiquetaSeleccionada;
    }

    public int getIdEtiquetaSeleccionada() {
        return idEtiquetaSeleccionada;
    }
}
