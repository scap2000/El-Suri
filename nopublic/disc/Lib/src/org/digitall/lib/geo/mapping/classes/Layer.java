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
 * Layer.java
 *
 * */
package org.digitall.lib.geo.mapping.classes;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.data.Format;
import org.digitall.lib.geo.esri.ESRIPoint;
import org.digitall.lib.geo.esri.ESRIPolygon;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.geo.gaia.GaiaPrimitiveForm;
import org.digitall.lib.geo.gaia.components.GaiaConfigPanel;
import org.digitall.lib.geo.shapefile.ShapeTypes;
import org.digitall.lib.sql.LibSQL;

public class Layer {

    //Variables de Dibujo
    private Vector badLines = new Vector();
    //Variables del Universo
    private double drawFactor = 0;
    private double xMin = 0;
    private double xMax = 0;
    private double yMin = 0;
    private double yMax = 0;
    private double xOffset = -1;
    private double yOffset = -1;
    private int fHeight = 500;
    private double extents = -1;
    private OsnapPoints osnapPoints;
    private Component parent;
    private Component configPanel;
    private Component infoPanel;
    //tolerancia en metros
    private BasicPanel editionForm;
    private LayerConfig layerConfig;
    private LayerConfigList layerConfigList;
    private boolean haveConfig = false;

    private HashMap<Integer, Integer> filterMap = new HashMap<Integer, Integer>();
    private HashMap<Integer, String> labelMap = new HashMap<Integer, String>();

    private GeometrySet geometrySet;

    private Vector<BufferedImage> imageAttachments = new Vector<BufferedImage>();
    private int idConfiguracionSeleccionada = 1;

    private boolean loaded = false;

    public Layer(String _name, GeometrySet _geometrySet) {
	this(_name, _geometrySet, "");
    }

    public Layer(String _name, GeometrySet _geometrySet, String _labelColumn) {
	this(_name, _geometrySet, _labelColumn, "");
    }

    public Layer(String _name, GeometrySet _geometrySet, String _labelColumn, String _typeColumn) {
	this(_name, _geometrySet, _labelColumn, _typeColumn, "");
    }

    public Layer(String _name, GeometrySet _geometrySet, String _labelColumn, String _typeColumn, String _toolTipValueColumn) {
	geometrySet = _geometrySet;
	geometrySet.addLayer(this);
	layerConfig = new LayerConfig(_name);
        layerConfigList = new LayerConfigList(_name);
        fetchLayerConfigListFromCache();
	if (!haveConfig) {
            //si no hay ninguna configuración
            if(layerConfigList.size() == 0){
                //se carga en el listado de configuraciones, la configuración base o por defecto
                layerConfig.setIdLayerConfig(0);
                layerConfigList.add(layerConfig);
                layerConfig.setLayerConfigList(layerConfigList);
                //se debe cargar la configuracion que se mostrará
                LayerConfig _layerConfigBase = layerConfig.getCopy();
                _layerConfigBase.setIdLayerConfig(1);
                _layerConfigBase.setNombreConfiguracion("Por defecto");
                layerConfigList.add(_layerConfigBase);
                _layerConfigBase.setLayerConfigList(layerConfigList);
		layerConfigList.setLayerConfig(_layerConfigBase,0);
		layerConfigList.setLayerConfig(layerConfig,1);
                setIdConfiguracionSeleccionada(1);
            }
	    if (_labelColumn != null) {
	        if (_labelColumn.length() != 0) {
	            layerConfig.setLabelColumn(_labelColumn);
	        }
	    }
	    if (_typeColumn != null) {
	        if (_typeColumn.length() != 0) {
	            layerConfig.setTypeColumn(_typeColumn);
	        }
	    }
	    if (_toolTipValueColumn != null) {
	        if (_toolTipValueColumn.length() != 0) {
	            layerConfig.setToolTipValueColumn(_toolTipValueColumn);
	        }
	    }
	    layerConfigList.saveConfigData();
	}
    }

    public ESRIPolygon getFakePolyFromPoly(ESRIPolygon _polygon) {
	double[] xy = new double[_polygon.getVertexCount() * 2];
	for (int i = 0; i < _polygon.getVertexCount() * 2; i += 2) {
	    xy[i] = ((_polygon.getX(i / 2) - xOffset) * drawFactor);
	    xy[i + 1] = (fHeight - ((_polygon.getY(i / 2) - yOffset) * drawFactor));
	}
	return new ESRIPolygon.Double(xy);
    }

    public Rectangle2D.Double getFakeRectangleFromPoint(Point2D xy) {
	double osnapRectSize = 8;
	return new Rectangle2D.Double(xtoPixel(xy.getX()) - osnapRectSize / 2, ytoPixel(xy.getY()) - osnapRectSize / 2, osnapRectSize, osnapRectSize);
    }

    public void setBadLines(Vector _polygons) {
	badLines.removeAllElements();
	for (int j = 0; j < _polygons.size(); j++) {
	    ESRIPolygon _poly = (ESRIPolygon)(_polygons.elementAt(j));
	    for (int i = 0; i < _poly.getVertexCount() - 1; i++) {
		//La primer pendiente es entre el primer punto con el segundo
		//La segunda pendiente es entre el segundo punto con el tercero
		//Hago mod para ejecutar un ciclo en el poligono y
		//evitar un ArrayIndexOutOfBoundsException
		double x0 = _poly.getX((i) % _poly.getVertexCount());
		double x1 = _poly.getX((i + 1) % _poly.getVertexCount());
		double y0 = _poly.getY((i) % _poly.getVertexCount());
		double y1 = _poly.getY((i + 1) % _poly.getVertexCount());
		double rx = x1 - x0;
		double ry = y1 - y0;
		if ((rx != 0) && (ry != 0)) {
		    badLines.add(new Line2D.Double(x0, y0, x1, y1));
		}
	    }
	}
    }

    private int xtoPixel(double x) {
	return (int)(((x - xOffset) * drawFactor));
    }

    private int ytoPixel(double y) {
	return (int)((fHeight - ((y - yOffset) * drawFactor)));
    }

    public void setColor(Color _color) {
	layerConfig.setOutlineColor(_color);
    }

    public Color getColor() {
	return layerConfig.getOutlineColor();
    }

    public void setSymbol(int _symbol) {
	layerConfig.setSymbol(_symbol);
    }

    public int getSymbol() {
	return layerConfig.getSymbol();
    }

    public void setLineType(int _lineType) {
	layerConfig.setLineType(_lineType);
    }

    public int getLineType() {
	return layerConfig.getLineType();
    }

    public void setFillPatern(int _fillPatern) {
	layerConfig.setFillPatern(_fillPatern);
    }

    public int getFillPatern() {
	return layerConfig.getFillPatern();
    }

    public void setFillColor(Color _fillColor) {
	layerConfig.setFillColor(_fillColor);
    }

    public Color getFillColor() {
	return layerConfig.getFillColor();
    }

    public void setOn(boolean _on) {
	if (_on) {
	    setOn();
	} else {
	    setOff();
	}
    }

    public void setOn() {
	layerConfig.setOn(true);
	load();
    }

    public void setOff() {
	layerConfig.setOn(false);
	if (parent != null) {
	    parent.repaint();
	}
    }

    public boolean isOn() {
	return layerConfig.isOn();
    }

    public double getXOffset() {
	return xOffset;
    }

    public double getYOffset() {
	return yOffset;
    }

    public double getExtents() {
	return extents;
    }

    public double getXMin() {
	return xMin;
    }

    public double getYMin() {
	return yMin;
    }

    public String getAlias() {
	return layerConfig.getAlias();
    }

    public String getName() {
	return layerConfig.getName();
    }

    public OsnapPoints getOsnapPoints() {
	return osnapPoints;
    }

    public void setOsnapPointsVector(OsnapPoints _osnapPoints) {
	osnapPoints = _osnapPoints;
    }

    public void setParent(Component _parent) {
	parent = _parent;
    }

    public void setMouseOverColor(Color mouseOverColor) {
	layerConfig.setMouseOverColor(mouseOverColor);
    }

    public Color getMouseOverColor() {
	return layerConfig.getMouseOverColor();
    }

    public int getPointDiameter() {
	return layerConfig.getPointDiameter();
    }

    public void setPointDiameter(int _pointDiameter) {
	layerConfig.setPointDiameter(_pointDiameter);
    }

    public void setQueryable(boolean _queryable) {
	layerConfig.setQueryable(_queryable);
    }

    public boolean isQueryable() {
	return layerConfig.isQueryable();
    }

    public void addFilter(String _name, String _column, String _expression, Color _outlineColor, Color _fillColor, String _toolTipValue) {
	layerConfig.addFilter(new LayerFilter(_name, _column, _expression, _outlineColor, _fillColor, _toolTipValue));
    }

    /**
     * @deprecated
     * @param _column
     * @param _expression
     * @param _outlineColor
     * @param _fillColor
     * @param _toolTipValue
     */
    public void addFilter(String _column, String _expression, Color _outlineColor, Color _fillColor, String _toolTipValue) {
	layerConfig.addFilter(new LayerFilter(getAlias(), _column, _expression, _outlineColor, _fillColor, _toolTipValue));
    }

    public void addFilter(String _name, String _column, String _expression, Color _outlineColor, int _strokeSize, String _toolTipValue) {
	layerConfig.addFilter(new LayerFilter(_name, _column, _expression, _outlineColor, _strokeSize, _toolTipValue));
    }

     public LayerFilter getLayerFilter(int _idFilterMatch) {
	 try {
	     return layerConfig.getFilter(_idFilterMatch);
	 } catch (IndexOutOfBoundsException x) {
	     resetFilterMap();
	     return null;
	 }
     }

    public LayerConfig getLayerConfig() {
	return layerConfig;
    }

    public void setConfigPanel(Component _configPanel) {
	configPanel = _configPanel;
    }

    public Component getConfigPanel() {
	return configPanel;
    }

    public void setModifiable(boolean modifiable) {
	layerConfig.setModifiable(modifiable);
    }

    public boolean isModifiable() {
	return layerConfig.isModifiable();
    }

    public void setDrawGeometries(boolean _drawGeometries) {
	layerConfig.setDrawGeometries(_drawGeometries);
    }

    public boolean drawGeometries() {
	return layerConfig.drawGeometries();
    }

    public double getXMax() {
	return xMax;
    }

    public double getYMax() {
	return yMax;
    }

    public void setInfoPanel(Component _infoPanel) {
	infoPanel = _infoPanel;
    }

    public Component getInfoPanel() {
	return infoPanel;
    }

    public void setTolerance(int _tolerance) {
	layerConfig.setTolerance(_tolerance);
    }

    public int getTolerance() {
	return layerConfig.getTolerance();
    }

    public void setToolTipLabel(String _toolTipLabel) {
	layerConfig.setToolTipLabel(_toolTipLabel);
    }

    public String getToolTipLabel() {
	return layerConfig.getToolTipLabel();
    }

    public void setToolTipValueColumn(String _toolTipValueColumn) {
	layerConfig.setToolTipValueColumn(_toolTipValueColumn);
    }

    public String getToolTipValueColumn() {
	return layerConfig.getToolTipValueColumn();
    }

    public void fireClick(MouseEvent _me, Rectangle2D.Double _extents) {
	fireClick(_me, _extents, true);
    }

    public void fireClick(MouseEvent _me, Rectangle2D.Double _extents, boolean _info) {
	Advisor.messageBox("Funcion no implementada", "Funcion no implementada");
	Component _panel = null;
	_panel = _info?infoPanel: configPanel;
	/*if (_me.getButton() == _me.BUTTON1) {
	    _panel = pointInfoPanel;
	} else if (_me.getButton() == _me.BUTTON3) {
	    _panel = pointConfigPanel;
	}
	if (_panel == null) {
	    if (pointInfoPanel != null) {
		_panel = pointInfoPanel;
	    } else if (pointConfigPanel != null) {
		_panel = pointConfigPanel;
	    }
	}*/
	if (_panel != null) {
	    if (ExtendedInternalFrame.class.isInstance(_panel)) {
		ExtendedInternalFrame _frame = (ExtendedInternalFrame)_panel;
		_frame.setClosable(true);
		_frame.setIconifiable(false);
		GaiaConfigPanel.class.isInstance(_frame.getCentralPanel());
		{
		    GaiaConfigPanel _contentPanel = (GaiaConfigPanel)_frame.getCentralPanel();
		    _contentPanel.setContentExtents(_extents);
		}
		if (!_frame.isVisible()) {
		    _frame.setVisible(true);
		}
		_frame.show();
	    } else if (BasicDialog.class.isInstance(_panel)) {
		BasicDialog _frame = (BasicDialog)_panel;
		_frame.setModal(true);
		_frame.setVisible(true);
	    }
	}
    }

    public void load() {
	if (geometrySet.needsReload()) {
	    geometrySet.reload();
	} else if (!geometrySet.isLoaded()) {
	    geometrySet.reload();
	} else {
	    if (parent != null) {
		parent.repaint();
	    }
	}
	if (!loaded) {
	    fetchFilterValuesFromCache();
	    fetchLabelValuesFromCache();
	    loaded = true;
	}
	retrieveImageAttachments();
    }

    public void reload() {
	geometrySet.setNeedsReload(true);
	load();
    }

    public void setActive(boolean active) {
	layerConfig.setActive(active);
    }

    public boolean isActive() {
	return layerConfig.isActive();
    }

    public void setEditable(boolean _editable) {
	layerConfig.setEditable(_editable);
    }

    public boolean isEditable() {
	return layerConfig.isEditable();
    }

    public void setEditionForm(BasicPanel _editionForm) {
	editionForm = _editionForm;
    }

    public BasicPanel getEditionForm() {
	return editionForm;
    }

    @Deprecated
    public synchronized void fetchLayerConfigFromCache() {
	File _cacheFile = new File(GaiaEnvironment.getCacheFileName(getName()) + ".lconfig");
	if (_cacheFile.exists()) {
	    try {
		System.out.println("Fetching layer " + getAlias() + " configuration");
		// Read from disk using FileInputStream
		FileInputStream inFile = new FileInputStream(_cacheFile);

		// Read object using ObjectInputStream
		ObjectInputStream inObject = new ObjectInputStream(inFile);

		// Read an object
		Object cachedObject = inObject.readObject();

		if (cachedObject instanceof LayerConfig) {
		    // Cast object to a Vector
		    layerConfig = (LayerConfig)cachedObject;
		    if (parent != null) {
			parent.repaint();
		    }
		}
		haveConfig = true;
	    } catch (ClassNotFoundException e) {
		System.out.println("Error: " + e.getMessage());
	    } catch (InvalidClassException e) {
		System.out.println("Error: " + e.getMessage());
	    } catch (FileNotFoundException e) {
		System.out.println("Error: " + e.getMessage());
	    } catch (IOException e) {
		System.out.println("Error: " + e.getMessage());
	    }
	    System.out.println("Fetching layer " + getAlias() + " configuration done...");
	}
    }
    
    /**2010-04-19(moraless)*/
    public synchronized void fetchLayerConfigListFromCache() {
        File _cacheFile = new File(GaiaEnvironment.getCacheFileName(getName()) + ".lcl");
        if (_cacheFile.exists()) {
            try {
                System.out.println("Fetching layer " + getAlias() + " configuration");
                // Read from disk using FileInputStream
                FileInputStream inFile = new FileInputStream(_cacheFile);

                // Read object using ObjectInputStream
                ObjectInputStream inObject = new ObjectInputStream(inFile);

                // Read an object
                Object cachedObject = inObject.readObject();

                if (cachedObject instanceof LayerConfigList) {
                    // Cast object to a Vector
                    layerConfigList = (LayerConfigList)cachedObject;
                    if (layerConfigList.size() > 1) {
                        //layerConfig = layerConfigList.elementAt(0);
                        //inicia en la configuración 1 porque la configuracion 0 no se muestra
                        layerConfig = layerConfigList.elementAt(1);
                        
                        layerConfig.setLayerConfigList(layerConfigList);
                    }
                    if (parent != null) {
                        parent.repaint();
                    }
                }
                haveConfig = true;
            } catch (ClassNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (InvalidClassException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (FileNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println("Fetching layer " + getAlias() + " configuration done...");
        }
    }

    private synchronized void fetchFilterValuesFromCache() {
	File _cacheFile = new File(GaiaEnvironment.getCacheFileName(getName()) + ".lfv");
	if (_cacheFile.exists()) {
	    try {
	        System.out.println("Fetching layer " + getAlias() + " filter values from cache");
		// Read from disk using FileInputStream
		FileInputStream inFile = new FileInputStream(_cacheFile);

		// Read object using ObjectInputStream
		ObjectInputStream inObject = new ObjectInputStream(inFile);

		// Read an object
		Object cachedObject = inObject.readObject();

		if (cachedObject instanceof HashMap) {
		    // Cast object to a Vector
		    filterMap = (HashMap<Integer, Integer>)cachedObject;
		    if (parent != null) {
			parent.repaint();
		    }
		}
	    } catch (ClassNotFoundException e) {
		System.out.println("Error: " + e.getMessage());
	    } catch (InvalidClassException e) {
		System.out.println("Error: " + e.getMessage());
	    } catch (FileNotFoundException e) {
		System.out.println("Error: " + e.getMessage());
	    } catch (IOException e) {
		System.out.println("Error: " + e.getMessage());
	    }
	    System.out.println("Fetching layer " + getAlias() + " filter values from cache done...");
	}
    }

    private synchronized void fetchLabelValuesFromCache() {
	File _cacheFile = new File(GaiaEnvironment.getCacheFileName(getName()) + ".llv");
	boolean _success = false;
	if (_cacheFile.exists()) {
	    try {
		System.out.println("Fetching layer " + getAlias() + " label values from cache");
		// Read from disk using FileInputStream
		FileInputStream inFile = new FileInputStream(_cacheFile);

		// Read object using ObjectInputStream
		ObjectInputStream inObject = new ObjectInputStream(inFile);

		// Read an object
		Object cachedObject = inObject.readObject();

		if (cachedObject instanceof HashMap) {
		    // Cast object to a Vector
		    labelMap = (HashMap<Integer, String>)cachedObject;
		    if (parent != null) {
			parent.repaint();
		    }
		}
		_success = true;
	    } catch (ClassNotFoundException e) {
		System.out.println("Error: " + e.getMessage());
	    } catch (InvalidClassException e) {
		System.out.println("Error: " + e.getMessage());
	    } catch (FileNotFoundException e) {
		System.out.println("Error: " + e.getMessage());
	    } catch (IOException e) {
		System.out.println("Error: " + e.getMessage());
	    }
	    System.out.println("Fetching layer " + getAlias() + " label values from cache done...");
	}
	if (!_success) {
	    //Esta línea está comentada porque solicita mucha info al arrancar
	    //por primera vez el sistema
	    //fetchLabelValues();
	}
    }

    private void cacheFilterValuesThread() {
	Thread _thread = new Thread(new Runnable() {
	    public void run() {
		System.out.println("Caching layer " + getAlias() + " filter values");
		String _fileName = GaiaEnvironment.getCacheFileName(getName()) + ".lfv";
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
			if (filterMap.size() > 0) {
			    // Write to disk with FileOutputStream
			    FileOutputStream outFile = new FileOutputStream(_fileName);
			    // Write object with ObjectOutputStream
			    ObjectOutputStream outObject = new ObjectOutputStream(outFile);
			    // Write object out to disk
			    outObject.writeObject(filterMap);
			    outObject.close();
			} else {
			    File _file = new File(_fileName);
			    if (_file.exists()) {
				_file.delete();
			    }
			}
		    } catch (FileNotFoundException e) {
			e.printStackTrace();
		    } catch (IOException e) {
			e.printStackTrace();
		    }
		}
		System.out.println("Caching layer " + getAlias() + " filter values done...");
	        layerConfig.saveData();
	    }
	});
	_thread.setPriority(Thread.MIN_PRIORITY);
	_thread.start();
    }

    private void cacheLabelValuesThread() {
	Thread _thread = new Thread(new Runnable() {
	    public void run() {
		System.out.println("Caching layer " + getAlias() + " label values");
	        String _fileName = GaiaEnvironment.getCacheFileName(getName()) + ".llv";
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
			if (labelMap.size() > 0) {
			    // Write to disk with FileOutputStream
			    FileOutputStream outFile = new FileOutputStream(_fileName);
			    // Write object with ObjectOutputStream
			    ObjectOutputStream outObject = new ObjectOutputStream(outFile);
			    // Write object out to disk
			    outObject.writeObject(labelMap);
			    outObject.close();
			} else {
			    File _file = new File(_fileName);
			    if (_file.exists()) {
				_file.delete();
			    }
			}
		    } catch (FileNotFoundException e) {
			e.printStackTrace();
		    } catch (IOException e) {
			e.printStackTrace();
		    }
		}
		System.out.println("Caching layer " + getAlias() + " label values done...");
	        layerConfig.saveData();
	    }
	});
	_thread.setPriority(Thread.MIN_PRIORITY);
	_thread.start();
    }

    public void fireClick(Object _geometry) {
	fireClick(_geometry, true);
    }
    
    public void fireClick(Object _geometry, boolean _info) {
	Component _panel = null;
	_panel = _info?infoPanel:configPanel;
	if (_panel != null) {
	    if (ExtendedInternalFrame.class.isInstance(_panel)) {
		ExtendedInternalFrame _frame = (ExtendedInternalFrame)_panel;
		_frame.setClosable(true);
		_frame.setIconifiable(false);
		GaiaConfigPanel.class.isInstance(_frame.getCentralPanel());
		{
		    GaiaConfigPanel _contentPanel = (GaiaConfigPanel)_frame.getCentralPanel();
		    _contentPanel.setContentObject(_geometry);
		}
		if (!_frame.isVisible()) {
		    _frame.setVisible(true);
		}
		_frame.show();
	    } else if (BasicDialog.class.isInstance(_panel)) {
		BasicDialog _frame = (BasicDialog)_panel;
		_frame.setModal(true);
		_frame.setVisible(true);
	    }
	}
    }
    
    public void edit(Object _geometry) {
	if (editionForm != null) {
	    if (GaiaPrimitiveForm.class.isInstance(editionForm)) {
		GaiaPrimitiveForm _form = (GaiaPrimitiveForm)editionForm;
		_form.setContentObject(_geometry);
	    }
	}
	
    }

    public void setLabelFontSize(int labelFontSize) {
	layerConfig.setLabelFontSize(labelFontSize);
    }

    public int getLabelFontSize() {
	return layerConfig.getLabelFontSize();
    }

    public void setGroup(LayerGroup _layerGroup) {
	layerConfig.setGroupName(_layerGroup.getName());
    }

    public void retrieveImageAttachments() {
	imageAttachments.removeAllElements();
	for (int i = 0; i < layerConfig.getImageAttachments().size(); i++) {
	    imageAttachments.add(layerConfig.getImageAttachments().elementAt(i).getImage());
	}
    }

    public Vector<BufferedImage> getImageAttachments() {
	return imageAttachments;
    }
    
    public double[][][] getMatrixBounds() {
	return layerConfig.getMatrixBounds();
    }

    public void setGeometrySet(GeometrySet geometrySet) {
	this.geometrySet = geometrySet;
    }

    public GeometrySet getGeometrySet() {
	return geometrySet;
    }

    public GeometrySetConfig getGeometrySetConfig() {
	return geometrySet.getGeometrySetConfig();
    }
    
    /**2010-04-16(moraless)*/
    private void reloadFiltersQuery(){
        for (int i = 0; i < layerConfig.getFilters().size(); i++) {
            String nombreColumnaFiltro = layerConfig.getFilters().elementAt(0).getColumn();
            if (layerConfig.getQueryFilter(nombreColumnaFiltro) == null){
                if (layerConfig.getFilters().elementAt(i).getExpression().toUpperCase().indexOf("SELECT") != -1){
                    layerConfig.setQueryFilter(layerConfig.getFilters().elementAt(i).getExpression(), layerConfig.getFilters().elementAt(i).getColumn());
                } else {
                    layerConfig.setQueryFilter(layerConfig.getFilters().elementAt(i).getColumn(), layerConfig.getFilters().elementAt(i).getColumn());
                }    
            } else {
                
            }
        }
    }

    /**2010-03-31(moraless)*/
    private String fetchFilterValues() {
        String queryFilter = "SELECT _id";
        resetFilterMap();
        Vector<String> _columnsVector = new Vector<String>();
        String _columns = "";        
        boolean _hasFilter = layerConfig.getFilters().size()>0;
        if (_hasFilter) {
            reloadFiltersQuery();
            queryFilter += ", " + layerConfig.parseFilter();
            for (int i = 0; i < layerConfig.getFilters().size(); i++) {
                if (
                    !queryFilter.contains(layerConfig.getFilters().elementAt(i).getColumn()) && 
                    !_columnsVector.contains(layerConfig.getFilters().elementAt(i).getColumn()) && 
                    !layerConfig.getLabelColumn().equals(layerConfig.getFilters().elementAt(i).getColumn()) && 
                    layerConfig.getFilters().elementAt(i).getColumn().length()>0) {
                        _columnsVector.add(layerConfig.getFilters().elementAt(i).getColumn());
                }
            }
            for (int i = 0; i < _columnsVector.size(); i++)  {
                _columns += _columnsVector.elementAt(i) + ", ";
            }
            
            String consultaSelect = "";
            for (int i = 0; i < layerConfig.getFilters().size(); i++) {
		try {
		    String query = layerConfig.getQueryFilter(layerConfig.getFilters().elementAt(i).getColumn());
	    if (consultaSelect.indexOf(query) == -1 && layerConfig.getFilters().elementAt(i).getColumn().length() > 0) {
			consultaSelect += query.length()>0?query + ", ":"";    
		    }
                } catch (NullPointerException e) {
		    e.printStackTrace();
		}
            }
            
            queryFilter += " FROM " 
            + "(SELECT " + (consultaSelect) + _columns
            + geometrySet.getGeometrySetConfig().getIDColumn() + " AS _id FROM "
            + geometrySet.getGeometrySetConfig().getSqlScheme() + "." + geometrySet.getGeometrySetConfig().getSqlTable() 
            + " WHERE " + geometrySet.getGeometrySetConfig().getSqlCondition() + ") AS foo;";
            
            ResultSet _rsValues = LibSQL.exQuery(queryFilter);

            try {
                while (_rsValues.next()) {
                    if (_hasFilter) {
                        addFilterKey(_rsValues.getInt("_id"), _rsValues.getInt("_filter_"));
                    }
                }
            } catch (Exception x) {
                x.printStackTrace();
                Advisor.messageBox("Ha ocurrido un error, intente alguna o varias de las siguientes opciones:\nBorre todos los filtros\nSeleccione nuevamente la columna de Etiqueta (sobre todo si es de otra tabla)", "Error");
            }
        }
        return queryFilter;
    }
    
    /**2010-03-31(moraless)*/
    public String fetchLabelValues(){
	String queryLabel = "";
	if (getGeometrySet().hasAccessPrivileges()) {
	    resetLabelMap();
	    queryLabel = "SELECT _id";
	    boolean _hasLabel = layerConfig.getLabelColumn().length()>0;
	    String _columns = "";
	    String query = layerConfig.getQueryLabel(layerConfig.getLabelColumn());
	    if (_hasLabel) {
		queryLabel += ", " + layerConfig.getLabelColumn();
		 if (layerConfig.isExtendedQuery()) {
		     
		 } else {
		     if ((!layerConfig.getLabelColumn().equals(query))) {
			 _columns += layerConfig.getLabelColumn() + ", ";
		     }
		 }
	    }
    
	    if(query == null){
		queryLabel += " FROM " 
		+ "(SELECT " + _columns
		+ geometrySet.getGeometrySetConfig().getIDColumn() + " AS _id FROM "
		+ geometrySet.getGeometrySetConfig().getSqlScheme() + "." + geometrySet.getGeometrySetConfig().getSqlTable() 
		+ " WHERE " + geometrySet.getGeometrySetConfig().getSqlCondition() + ") AS foo;";    
	    } else {
		queryLabel += " FROM " 
		+ "(SELECT " + (query.length()>0?query + ", ":"") + _columns
		+ geometrySet.getGeometrySetConfig().getIDColumn() + " AS _id FROM "
		+ geometrySet.getGeometrySetConfig().getSqlScheme() + "." + geometrySet.getGeometrySetConfig().getSqlTable() 
		+ " WHERE " + geometrySet.getGeometrySetConfig().getSqlCondition() + ") AS foo;";
	    }
	    
	    if (_hasLabel) {        
		ResultSet _rsValues = LibSQL.exQuery(queryLabel);
	
		try {
		    ResultSetMetaData _rsMetaData = _rsValues.getMetaData();
		    int i = 0;
		    boolean _found = false;
		    layerConfig.setLabelColumnDataType(Types.VARCHAR);
		    while (i < _rsMetaData.getColumnCount() && !_found) {
			i++;
			_found = _rsMetaData.getColumnName(i).equalsIgnoreCase(layerConfig.getLabelColumn());
			if (_found) {
			    layerConfig.setLabelColumnDataType(_rsMetaData.getColumnType(i));
			}
		    }
		    while (_rsValues.next()) {
			if (_hasLabel) {
			    addLabelValue(_rsValues.getInt("_id"), _rsValues.getString(layerConfig.getLabelColumn()));
			}
		    }
		} catch (Exception x) {
		    x.printStackTrace();
		    Advisor.messageBox("Ha ocurrido un error, intente alguna o varias de las siguientes opciones:\nBorre todos los filtros\nSeleccione nuevamente la columna de Etiqueta (sobre todo si es de otra tabla)", "Error");
		}
	    }
	    cacheLabelValuesThread();
	}
	return queryLabel;
    }

    /**2010-03-31(moraless)*/
    public void fetchValues() {
	Thread _thread = new Thread(new Runnable() {
	    public void run() {
		long _start = System.currentTimeMillis();
                fetchFilterValues();
	        cacheFilterValuesThread();
                fetchLabelValues();
	        cacheLabelValuesThread();
		layerConfig.setLastFilterRetrievingTime((System.currentTimeMillis() - _start)/1000);
	    }
	});
	if (layerConfig.getLastFilterRetrievingTime() > 5) {
	    if (Advisor.question("Refrescar filtros", "La última vez que refrescó los filtros le tomó " + layerConfig.getLastFilterRetrievingTime() + " segundos, ¿Está seguro?")) {
		_thread.start();
	    }
	} else {
	    _thread.start();
	}
    }

    public void fetchTopValues(String _scheme, String _table, String _column) {
	layerConfig.setTopValuesQuery("SELECT PUBLIC.ISNULL(" + _column + "::text,'NULOS') AS columna, count(" + _column + ") AS cantidad FROM " + _scheme + "." + _table + " GROUP BY " + _column + " ORDER BY cantidad DESC, columna ASC");
	fetchTopValues();
    }

    public void fetchTopValues() {
	if (getGeometrySet().hasAccessPrivileges()) {
	    if (layerConfig.getTopValuesQuery().length() > 0) {
		ResultSet _topValues = LibSQL.exQuery(layerConfig.getTopValuesQuery());
		try {
		    layerConfig.getTopValues().removeAllElements();
		    while (_topValues.next()) {
			layerConfig.getTopValues().add(new Object[] { _topValues.getString("columna"), _topValues.getString("cantidad") });
		    }
		} catch (SQLException e) {
		    // TODO
		    Advisor.printException(e);
		}
	    } else if (layerConfig.getLabelColumn().length() > 0) {
		switch (getLayerConfig().getLabelColumnDataType()) {
		    case Types.BIGINT:
		    case Types.DECIMAL:
		    case Types.DOUBLE:
		    case Types.FLOAT:
		    case Types.INTEGER:
		    case Types.NUMERIC:
		    case Types.REAL:
		    case Types.SMALLINT:
		    case Types.TINYINT:
			layerConfig.setTopValuesQuery("SELECT PUBLIC.ISNULL(" + layerConfig.getLabelColumn() + "::character varying,'0'::character varying) AS columna, count(" + layerConfig.getLabelColumn() + ") AS cantidad FROM " + geometrySet.getGeometrySetConfig().getSqlScheme() + "." + geometrySet.getGeometrySetConfig().getSqlTable() + " GROUP BY " + layerConfig.getLabelColumn() + " ORDER BY cantidad DESC, columna ASC");
			break;
		    default:
			layerConfig.setTopValuesQuery("SELECT PUBLIC.ISNULL(" + layerConfig.getLabelColumn() + "::text,'NULOS') AS columna, count(" + layerConfig.getLabelColumn() + ") AS cantidad FROM " + geometrySet.getGeometrySetConfig().getSqlScheme() + "." + geometrySet.getGeometrySetConfig().getSqlTable() + " GROUP BY " + layerConfig.getLabelColumn() + " ORDER BY cantidad DESC, columna ASC");
			break;
		}
		fetchTopValues();
	    }
	}
    }

    public void addFilterKey(int _idPolygon, int _idFilterMatch) {
	filterMap.put(_idPolygon, _idFilterMatch);
	if (_idFilterMatch != -1) {
	    layerConfig.getFilter(_idFilterMatch).incrementMatch();
	}
    }

    public void resetFilterMap() {
	for (int i = 0; i < layerConfig.getFilters().size(); i++)  {
	    layerConfig.getFilters().elementAt(i).restartMatches();
	}
	filterMap = new HashMap();
    }
    
    public void addLabelValue(int _idPolygon, String _label) {
	labelMap.put(_idPolygon, _label);
    }

    public void resetLabelMap() {
	labelMap = new HashMap();
    }
    
    public int getFilterValue(int _idPolygon) {
	Integer _returns = filterMap.get(_idPolygon);
	if (_returns != null) {
	    return _returns;
	} else {
	    //System.out.println("ID Nulo: " + _idPolygon);
	    return -1;
	}
    }

    public String getLabelValue(int _idPolygon) {
	String _returns = labelMap.get(_idPolygon);
	if (_returns != null) {
	    return _returns;
	} else {
	    return "";
	}
    }

    public void setToolTipText(String _toolTipText) {
	 layerConfig.setToolTipText(new StringBuffer(_toolTipText));
	 String Style = "style='background-color:#FFFFFF;COLOR:#000000'";
	 //layerConfig.getToolTipText().append("<html>");
	 layerConfig.getToolTipText().append("<table frame='hsides' cellpadding='0' cellspacing='0' width='100%'>");
	 layerConfig.getToolTipText().append("<tr style='background-color:#303030;COLOR:#FFFFFF'>");
	 layerConfig.getToolTipText().append("<td width='10'>&nbsp;</td>");
	 layerConfig.getToolTipText().append("<td width='110' colspan='3' align='left'><b>@ Geometría:</b></td>");
	 layerConfig.getToolTipText().append("<td width='25'>&nbsp;</td>");
	 layerConfig.getToolTipText().append("<tdop align='left'><b>Datos</b></td>");
	 layerConfig.getToolTipText().append("<td width='10'>&nbsp;</td>");
	 layerConfig.getToolTipText().append("</tr>");
	 layerConfig.getToolTipText().append("<tr " + Style + " height='22'>");
	 layerConfig.getToolTipText().append("<td width='10'>&nbsp;</td>");
	 int _idGeometry = -1;
	 String _toolTipValue = "";
	 String _labelValue = "";
	 int _idFilterMatch = -1;
	 switch (geometrySet.getGeometrySetConfig().getShapeType())      {
	     case ShapeTypes.POINT:
	     case ShapeTypes.MULTIPOINT:
		 //ESRIPoint _point = (ESRIPoint)geometries.elementAt(getContainedShapeIndex());
		 ESRIPoint _point = geometrySet.getPoint(geometrySet.getContainedShapeID());
		 _idGeometry = _point.getIdPoint();
		 _idFilterMatch = getFilterValue(_point.getIdPoint());
		 _labelValue = getLabelValue(_point.getIdPoint());
		 if (_labelValue.length() == 0 && _idFilterMatch != -1) {
		     _labelValue = layerConfig.getFilter(_idFilterMatch).getName();
		 }
		 break;
	     case ShapeTypes.POLYGON:
	     case ShapeTypes.MULTIPOLYGON:
	     case ShapeTypes.POLYLINE:
	     case ShapeTypes.MULTIPOLYLINE:
		 ESRIPolygon _polygon = geometrySet.getPolygon(geometrySet.getContainedShapeID());
		 _idGeometry = _polygon.getIdPolygon();
		 _idFilterMatch = getFilterValue(_polygon.getIdPolygon());
		 _labelValue = getLabelValue(_polygon.getIdPolygon());
		 if (_labelValue.length() == 0 && _idFilterMatch != -1) {
		     _labelValue = layerConfig.getFilter(_idFilterMatch).getName();
		 }
		 break;      
	 }
	 layerConfig.getToolTipText().append("<td>" + _idGeometry + "</td>");
	 layerConfig.getToolTipText().append("<td width='15' bgcolor='" + Format.color2Hex(layerConfig.getMouseOverColor()) + "'>&nbsp;</td>");
	 layerConfig.getToolTipText().append("<td width='5'></td>");
	 layerConfig.getToolTipText().append("<td>&nbsp;</td>");
	 layerConfig.getToolTipText().append("<td width='2' bgcolor='" + (_idFilterMatch!=-1?Format.color2Hex(getLayerFilter(_idFilterMatch).getStyleConfig().getFillColor()):"#DDDDDD") + "'>&nbsp;</td>");
	 layerConfig.getToolTipText().append("<td>");
	 layerConfig.getToolTipText().append(layerConfig.getToolTipLabel() != null?layerConfig.getToolTipLabel() + ": ": "");
	 layerConfig.getToolTipText().append(_toolTipValue != null?_toolTipValue: "-");
	 layerConfig.getToolTipText().append("</td>");
	 layerConfig.getToolTipText().append("<td bgcolor='#DDDDDD'>");
	 layerConfig.getToolTipText().append(layerConfig.getLabelColumn() != null?layerConfig.getLabelColumn() + ": ": "");
	 layerConfig.getToolTipText().append(_labelValue != null?_labelValue: "-");
	 layerConfig.getToolTipText().append("</td>");
	 layerConfig.getToolTipText().append("<td width='10'>&nbsp;</td>");
	 layerConfig.getToolTipText().append("</tr>");
	 layerConfig.getToolTipText().append("</table>");
	 //layerConfig.getToolTipText().append("</html>");
     }

    public StringBuffer getToolTipText() {
	return getToolTipText(geometrySet.getContainedShapeID());
    }
    
    public StringBuffer getToolTipText(int _idGeometry) {
	layerConfig.setToolTipText(new StringBuffer(""));
	String Style = "style='background-color:#FFFFFF;COLOR:#000000'";
	layerConfig.getToolTipText().append("<table frame='hsides' cellpadding='0' cellspacing='0' width='100%'>");
	layerConfig.getToolTipText().append("<tr style='background-color:#303030;COLOR:#FFFFFF'>");
	layerConfig.getToolTipText().append("<td width='10'>&nbsp;</td>");
	layerConfig.getToolTipText().append("<td width='220' colspan='3' align='left'><b>@ Geometría: <font size=2>[" + layerConfig.getName() + "]</font></b></td>");
	layerConfig.getToolTipText().append("<td width='25'>&nbsp;</td>");
	layerConfig.getToolTipText().append("<tdop align='left'><b>Datos</b></td>");
	layerConfig.getToolTipText().append("<td width='10'>&nbsp;</td>");
	layerConfig.getToolTipText().append("</tr>");
	layerConfig.getToolTipText().append("<tr " + Style + " height='22'>");
	layerConfig.getToolTipText().append("<td width='10'>&nbsp;</td>");
	String _toolTipValue = "";
	int _idFilterMatch = getFilterValue(_idGeometry);
	String _labelValue = getLabelValue(_idGeometry);
	if (_labelValue.length() == 0 && _idFilterMatch != -1) {
	    _labelValue = layerConfig.getFilter(_idFilterMatch).getName();
	}
	layerConfig.getToolTipText().append("<td>" + _idGeometry + "</td>");
	layerConfig.getToolTipText().append("<td width='15' bgcolor='" + Format.color2Hex(layerConfig.getMouseOverColor()) + "'>&nbsp;</td>");
	layerConfig.getToolTipText().append("<td width='5'></td>");
	layerConfig.getToolTipText().append("<td>&nbsp;</td>");
	layerConfig.getToolTipText().append("<td width='2' bgcolor='" + (_idFilterMatch!=-1?Format.color2Hex(getLayerFilter(_idFilterMatch).getStyleConfig().getFillColor()):"#DDDDDD") + "'>&nbsp;</td>");
	layerConfig.getToolTipText().append("<td>");
	layerConfig.getToolTipText().append(layerConfig.getToolTipLabel() != null?layerConfig.getToolTipLabel() + ": ": "");
	layerConfig.getToolTipText().append(_toolTipValue != null?_toolTipValue: "-");
	layerConfig.getToolTipText().append("</td>");
	layerConfig.getToolTipText().append("<td bgcolor='#DDDDDD'>");
	layerConfig.getToolTipText().append(layerConfig.getLabelColumn() != null?layerConfig.getLabelColumn() + ": ": "");
	layerConfig.getToolTipText().append(_labelValue != null?_labelValue: "-");
	layerConfig.getToolTipText().append("</td>");
	layerConfig.getToolTipText().append("<td width='10'>&nbsp;</td>");
	layerConfig.getToolTipText().append("</tr>");
	layerConfig.getToolTipText().append("</table>");
        return layerConfig.getToolTipText();
    }

    public void removeFilter(LayerFilter _layerFilter) {
	//int _idFilter = layerConfig.getFilters().indexOf(_layerFilter);
	int _idFilter = _layerFilter.getID();
	Iterator _iterator = filterMap.entrySet().iterator();
	while (_iterator.hasNext()) {
	    Map.Entry<Integer, Integer> _entry = (Map.Entry)_iterator.next();
	    if (_entry.getValue() == _idFilter) {
		_entry.setValue(-1);
	    }
	}
	layerConfig.getFilters().remove(_layerFilter);
	saveData();
    }

    public void saveData() {
	cacheFilterValuesThread();
	cacheLabelValuesThread();
        layerConfigList.saveConfigData();
    }

    public void setLayerConfigList(LayerConfigList layerConfigList) {
        this.layerConfigList = layerConfigList;
    }

    public LayerConfigList getLayerConfigList() {
        return layerConfigList;
    }

    public void setLayerConfig(LayerConfig _layerConfig){
        layerConfig = _layerConfig;
    }

    public void setIdConfiguracionSeleccionada(int idConfiguracionSeleccionada) {
        this.idConfiguracionSeleccionada = idConfiguracionSeleccionada;
    }

    public int getIdConfiguracionSeleccionada() {
        return idConfiguracionSeleccionada;
    }

    public boolean haveConfig() {
	return haveConfig;
    }
}
