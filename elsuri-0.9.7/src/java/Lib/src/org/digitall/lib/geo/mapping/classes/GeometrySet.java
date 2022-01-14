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
 * GeometrySet.java
 *
 * */
package org.digitall.lib.geo.mapping.classes;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Random;
import java.util.Vector;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.data.Base64Coder;
import org.digitall.lib.geo.coordinatesystems.CoordinateSystems;
import org.digitall.lib.geo.esri.ESRIPoint;
import org.digitall.lib.geo.esri.ESRIPolygon;
import org.digitall.lib.geo.gaia.GaiaClient;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.geo.shapefile.ShapeTypes;
import org.digitall.lib.sql.LibSQL;

public class GeometrySet {

    private Vector[][] matrix = null;
    private Component parent;
    private boolean loading = false;
    private boolean loaded = false;
    private boolean needsReload = false;
    private boolean haveConfig = false;
    private boolean hasAccessPrivileges = false;
    private long lastRetrievingTime = 0;
    private GeometrySetConfig geometrySetConfig;
    private Vector<Layer> layers = new Vector<Layer>();
    private int  containedShapeID = -1;
    private GaiaClient _gaiaClient;
    private String name;
    
    public GeometrySet(GeometrySetConfig _geometrySetConfig) {
        geometrySetConfig = _geometrySetConfig;
        setName(geometrySetConfig.getName());
        //this(_geometrySetConfig.getSqlScheme(), _geometrySetConfig.getSqlTable(), _geometrySetConfig.getGeometryField(), _geometrySetConfig.getSqlCondition(), _geometrySetConfig.getIDColumn(), true);
        System.out.println("Searching " + getFileName());
        if ((new File(getFileName()).exists())) {
            Thread threadTask = new Thread(new Runnable() {

                                        public void run() {
                                            fetchGeometriesFromCache();
                                        }
                                    }
                );
            if (!loading) {
                threadTask.start();
            }
        } else {
            System.out.println(getFileName() + " not found! (" + geometrySetConfig.getSqlScheme() + "." + geometrySetConfig.getSqlTable() + ")");
        }
        hasAccessPrivileges = LibSQL.getBoolean("has_table_privilege", "'" + geometrySetConfig.getSqlScheme() + "." + geometrySetConfig.getSqlTable() + "', 'SELECT'"); 
    }
    
    public GeometrySet(String _sqlScheme, String _sqlTable, String _geometryField, String _sqlCondition, String _idColumn) {
	this(_sqlScheme, _sqlTable, _geometryField, _sqlCondition, _idColumn, true);
    }

    public GeometrySet(String _sqlTable, String _geometryField, String _sqlCondition, String _idColumn) {
	this(GaiaEnvironment.getScheme(), _sqlTable, _geometryField, _sqlCondition, _idColumn, true);
    }
    
    public GeometrySet(String _sqlScheme, String _sqlTable, String _geometryField, String _sqlCondition, String _idColumn, boolean _propietary) {
	geometrySetConfig = new GeometrySetConfig();
	geometrySetConfig.setSqlScheme(_sqlScheme);
	geometrySetConfig.setSqlTable(_sqlTable);
        //name = Base64Coder.encode(String.valueOf(new Random().nextDouble()) + geometrySetConfig.getSqlScheme() + "." + geometrySetConfig.getSqlTable());
	geometrySetConfig.setGeometryField(_geometryField);
	geometrySetConfig.setSqlCondition(_sqlCondition);
	geometrySetConfig.setIDColumn(_idColumn);
	geometrySetConfig.setPropietary(_propietary);
	//fetchGeometrySetConfigFromCache();
	if (!haveConfig) {
	    setName(Base64Coder.encode(String.valueOf(new Random().nextDouble()) + geometrySetConfig.getSqlScheme() + "." + geometrySetConfig.getSqlTable()));
            geometrySetConfig.setName(getName());
	    geometrySetConfig.saveData();
        } else {
            //revisar si esta línea es necesaria
            setName(geometrySetConfig.getName());
        }
        
	if ((new File(getFileName()).exists())) {
	    Thread threadTask = new Thread(new Runnable() {

	                                public void run() {
	                                    fetchGeometriesFromCache();
	                                }
	                            }
	        );
	    if (!loading) {
	        threadTask.start();
	    }
	}
	GaiaEnvironment.gaiaEngine.getGeometrySetConfigList().add(geometrySetConfig);
	GaiaEnvironment.gaiaEngine.saveProfile();
	hasAccessPrivileges = LibSQL.getBoolean("has_table_privilege", "'" + geometrySetConfig.getSqlScheme() + "." + geometrySetConfig.getSqlTable() + "', 'SELECT'"); 
    }

    public void addLayer(Layer _layer) {
	boolean _found = false;
	int i = 0;
	while (i < layers.size() && !_found) {
	    _found = (layers.elementAt(i) == _layer);
	    i++;
	}
	if (!_found) {
	    layers.add(_layer);
	}
    }
    
    public boolean removeLayer(Layer _layer) {
	return layers.remove(_layer);
    }

    private synchronized void cacheGeometrySetThread() {
	boolean _enabled = true;
	if (!(GaiaEnvironment.tempDir.exists() && GaiaEnvironment.tempDir.isDirectory())) {
	    if (!GaiaEnvironment.tempDir.mkdir()) {
		Advisor.messageBox("Ha ocurrido un error al crear el directorio temporal", "Error");
		_enabled = false;
	    } else {
		//System.out.println("El directorio temporal se encuentra en " + _tmpDir);
	    }
	} else {
	    //System.out.println("El directorio temporal se encuentra en " + _tmpDir);
	}
	if (_enabled) {
	    Thread threadTask = new Thread(new Runnable() {

					public void run() {
					    System.out.println("Caching geometries " + geometrySetConfig.getSqlScheme() + "." + geometrySetConfig.getSqlTable());
					    try {
						// Write to disk with FileOutputStream
						FileOutputStream outFile = new FileOutputStream(getFileName());
						// Write object with ObjectOutputStream
						ObjectOutputStream outObject = new ObjectOutputStream(outFile);
						// Write object out to disk
						outObject.writeObject(matrix);
						outObject.close();
					    } catch (FileNotFoundException e) {
						e.printStackTrace();
					    } catch (IOException e) {
						e.printStackTrace();
					    }
					    System.out.println("Caching geometries " + geometrySetConfig.getSqlScheme() + "." + geometrySetConfig.getSqlTable() + " done...");
					}

				    }
		);
	    threadTask.start();
	    if (false) {
	        fetchKeys();
	    }
	    geometrySetConfig.saveData();
	}
    }

    private void fetchGeometriesFromDatabase() {
	hasAccessPrivileges = LibSQL.getBoolean("has_table_privilege", "'" + geometrySetConfig.getSqlScheme() + "." + geometrySetConfig.getSqlTable() + "', 'SELECT'"); 
	if (hasAccessPrivileges && !loading) {
	    /*Runtime r = Runtime.getRuntime();
	    long freeMem = r.freeMemory();
	    System.out.println("free memory before creating array: " + freeMem);
	    r.gc();
	    freeMem = r.freeMemory();
	    System.out.println("free memory before creating array and after gc: " + freeMem);*/
	    System.out.println("Retrieving " + geometrySetConfig.getSqlScheme() + "." + geometrySetConfig.getSqlTable() +  " from database");
	    long _start = System.currentTimeMillis();
	    loading = true;
	    loaded = false;
	    try {
	
		resetAllFilterMatches();

		//TODO ESTE CALCULO NO FUNCIONA BIEN CON LATITUD LONGITUD, REVISAR
		//Cuando tengo una población de puntos con valores muy lejanos (en otro país) este algoritmo
		//se caga para calcular bien entonces en MapCanvas->_bounds.intersects(_geometrySet.getGeometrySetConfig().getMatrixBounds()[0][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[1][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[2][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[3][m][n])) 
		//falla y no dibuja nada, ver si sucede lo mismo con polilíneas y polígonos
	        String sqlExtentsQuery = "SELECT " + "ST_xmin(ST_extent(" + geometrySetConfig.getGeometryField() + "))-10 AS xmin, " + "ST_xmax(ST_extent(" + geometrySetConfig.getGeometryField() + "))+10 AS xmax, " 
	                + "ST_ymin(ST_extent(" + geometrySetConfig.getGeometryField() + "))-10 AS ymin, " + "ST_ymax(ST_extent(" + geometrySetConfig.getGeometryField() + "))+10 AS ymax " 
	                + " FROM "+ geometrySetConfig.getSqlScheme() + "." + geometrySetConfig.getSqlTable() + " WHERE " + geometrySetConfig.getSqlCondition() + " AND " + geometrySetConfig.getGeometryField() + " IS NOT NULL;";
		ResultSet _rsExtents = exQuery(sqlExtentsQuery);
		_rsExtents.next();
		geometrySetConfig.setMinX(_rsExtents.getDouble("xmin"));
		geometrySetConfig.setMaxX(_rsExtents.getDouble("xmax"));
		geometrySetConfig.setMinY(_rsExtents.getDouble("ymin"));
		geometrySetConfig.setMaxY(_rsExtents.getDouble("ymax"));
		geometrySetConfig.setExtents(Math.max(geometrySetConfig.getMaxX() - geometrySetConfig.getMinX(), geometrySetConfig.getMaxY() - geometrySetConfig.getMinY()));
		if (geometrySetConfig.getShapeType() == ShapeTypes.POLYGON || geometrySetConfig.getShapeType() == ShapeTypes.MULTIPOLYGON) {
		    ResultSet _rsGrid = exQuery("SELECT ceil(sqrt(count(" + geometrySetConfig.getGeometryField() + "))/sqrt(avg(ST_area(" + geometrySetConfig.getGeometryField() + ")))) AS gridsize FROM " + geometrySetConfig.getSqlScheme() + "." + geometrySetConfig.getSqlTable());
		    if (_rsGrid.next()) {
			geometrySetConfig.setGridSize(_rsGrid.getInt("gridsize"));
		    }
		} else if (geometrySetConfig.getShapeType() == ShapeTypes.POINT || geometrySetConfig.getShapeType() == ShapeTypes.MULTIPOINT) {
		    //geometrySetConfig.setGridSize(1);
		    
		    //Funcionó con Salta, pero con Mosconi da una grilla de 536x536 en Nº de Manzanas
		    //ResultSet _rsGrid = exQuery("SELECT ceil(sqrt(area(extent(" + geometrySetConfig.getGeometryField() + "))/count(" + geometrySetConfig.getGeometryField() + "))) AS gridsize FROM " + geometrySetConfig.getSqlScheme() + "." + geometrySetConfig.getSqlTable());
    
		    ResultSet _rsGrid = exQuery("SELECT ceil(sqrt(ST_area(ST_extent(" + geometrySetConfig.getGeometryField() + ")))/count(" + geometrySetConfig.getGeometryField() + ")) AS gridsize FROM " + geometrySetConfig.getSqlScheme() + "." + geometrySetConfig.getSqlTable());
		    //ResultSet _rsGrid = exQuery("SELECT ceil(area(extent(" + geometrySetConfig.getGeometryField() + "))/count(" + geometrySetConfig.getGeometryField() + ")) AS gridsize FROM " + geometrySetConfig.getSqlScheme() + "." + geometrySetConfig.getSqlTable());
		    if (_rsGrid.next()) {
			geometrySetConfig.setGridSize(_rsGrid.getInt("gridsize"));
		    }
		} else {
		    geometrySetConfig.setGridSize(1);
		}
		matrix = new Vector[geometrySetConfig.getGridSize()][geometrySetConfig.getGridSize()];
		for (int i = 0; i < geometrySetConfig.getGridSize(); i++)  {
		    for (int j = 0; j < geometrySetConfig.getGridSize(); j++)  {
			matrix[i][j] = new Vector();
		    }
		}
		int q = 0;
		//COMIENZO
		switch (geometrySetConfig.getShapeType()) {
		    case ShapeTypes.POLYGON:
		    case ShapeTypes.MULTIPOLYGON:
			try {
			    String sqlQuery = "";
			    if (geometrySetConfig.getShapeType() == ShapeTypes.POLYGON) {
				//Sólo obtengo la geometría [0] o [1]
				 sqlQuery = "SELECT " + geometrySetConfig.getIDColumn() + " AS _id" + (geometrySetConfig.getLabelColumn().length() > 0?", ": "") + geometrySetConfig.getLabelColumn() + (geometrySetConfig.getToolTipValueColumn().length() > 0?", ": "") + geometrySetConfig.getToolTipValueColumn()
					+ (geometrySetConfig.getTypeColumn().length() > 0?", ": "") + geometrySetConfig.getTypeColumn() + ", replace(replace(ST_AsText(" + geometrySetConfig.getGeometryField() + "), 'POLYGON((', ''), '))', '') AS the_geom, "
					+ " ST_x(ST_centroid(" + geometrySetConfig.getGeometryField() + ")) AS centroidX, ST_y(ST_centroid(" + geometrySetConfig.getGeometryField() + ")) AS centroidY, ST_area(" + geometrySetConfig.getGeometryField() + ") AS area"
					+ " FROM "+ geometrySetConfig.getSqlScheme() + "." + geometrySetConfig.getSqlTable() + " WHERE ST_isvalid(" + geometrySetConfig.getGeometryField() + ") AND " + geometrySetConfig.getSqlCondition() + " AND " + geometrySetConfig.getGeometryField() + " IS NOT NULL;";
			    } else {
				sqlQuery = "SELECT " + geometrySetConfig.getIDColumn() + " AS _id" + (geometrySetConfig.getLabelColumn().length() > 0?", ": "") + geometrySetConfig.getLabelColumn() + (geometrySetConfig.getToolTipValueColumn().length() > 0?", ": "") + geometrySetConfig.getToolTipValueColumn()
				       + (geometrySetConfig.getTypeColumn().length() > 0?", ": "") + geometrySetConfig.getTypeColumn() + ", replace(replace(ST_AsText(" + geometrySetConfig.getGeometryField() + "), 'MULTIPOLYGON(((', ''), ')))', '') AS the_geom, "
				       + " ST_x(ST_centroid(" + geometrySetConfig.getGeometryField() + ")) AS centroidX, ST_y(ST_centroid(" + geometrySetConfig.getGeometryField() + ")) AS centroidY, ST_area(" + geometrySetConfig.getGeometryField() + ") AS area"
				       + " FROM "+ geometrySetConfig.getSqlScheme() + "." + geometrySetConfig.getSqlTable() + " WHERE ST_isvalid(" + geometrySetConfig.getGeometryField() + ") AND " + geometrySetConfig.getSqlCondition() + " AND " + geometrySetConfig.getGeometryField() + " IS NOT NULL;";
			    }
			    ResultSet _rsPolygons = exQuery(sqlQuery);
			    //Obtengo el tipo de datos de la columna "label"
			    try {
				_rsPolygons.last();
				int _qty = _rsPolygons.getRow();
				int _percentDone = 0;
				_rsPolygons.beforeFirst();
				while (_rsPolygons.next()) {
				    String _polygonString = _rsPolygons.getString("the_geom").replaceAll("[()]", "");
				    String[] points = _polygonString.split(",");
				    double[] xd = new double[points.length];
				    double[] yd = new double[points.length];
				    for (int i = 0; i < points.length; i++) {
					String[] xy = points[i].split(" ");
					xd[i] = Double.parseDouble(xy[0]);
					yd[i] = Double.parseDouble(xy[1]);
				    }
				    ESRIPolygon _polygon = ESRIPolygon.constructPolygon(xd, yd);
				    //osnapPoints.addOsnapPoints(_polygon);
				    _polygon.setIdPolygon(_rsPolygons.getInt("_id"));
				    ;
				    _polygon.setCentroid(new ESRIPoint(_rsPolygons.getDouble("centroidX"), _rsPolygons.getDouble("centroidY")));
				    _polygon.setArea(_rsPolygons.getDouble("area"));
				    boolean _found = false;
				    int i = 0;
				    while (i < geometrySetConfig.getGridSize() && !_found) {
					int j = 0;
					while (j < geometrySetConfig.getGridSize() && !_found) {
					    if (_polygon.intersects(new Rectangle2D.Double(geometrySetConfig.getMatrixBounds()[0][i][j], geometrySetConfig.getMatrixBounds()[1][i][j], geometrySetConfig.getMatrixBounds()[2][i][j], geometrySetConfig.getMatrixBounds()[3][i][j]))) {
						matrix[i][j].add(_polygon);
						q++;
						if (q*100/_qty > _percentDone) {
						    _percentDone = q*100/_qty;
						    System.out.println(_percentDone + "% done");
						}
						_found = true;
					    }
					    j++;
					}
					i++;
				    }
				}
				System.out.println(q + " polígonos vectorizados en una grilla de " + geometrySetConfig.getGridSize() + "x" + geometrySetConfig.getGridSize());
			    } catch (NullPointerException e) {
				//ignore
			    }
			} catch (SQLException x) {
			    Advisor.messageBox(x.getErrorCode() + ": " + x.getMessage(), "Error en la consulta SQL");
			}
			break;
		    case ShapeTypes.POLYLINE:
		    case ShapeTypes.MULTIPOLYLINE:
			try {
			    String sqlQuery = "";
			    if (geometrySetConfig.getShapeType() == ShapeTypes.POLYLINE) {
				//Sólo obtengo la geometría [0] o [1]
				 sqlQuery = "SELECT " + geometrySetConfig.getIDColumn() + " AS _id" + (geometrySetConfig.getLabelColumn().length() > 0?", ": "") + geometrySetConfig.getLabelColumn() + (geometrySetConfig.getToolTipValueColumn().length() > 0?", ": "") + geometrySetConfig.getToolTipValueColumn()
					+ (geometrySetConfig.getTypeColumn().length() > 0?", ": "") + geometrySetConfig.getTypeColumn() + ", replace(replace(AsText(" + geometrySetConfig.getGeometryField() + "), 'LINESTRING(', ''), ')', '') AS the_geom"
					+ " FROM "+ geometrySetConfig.getSqlScheme() + "." + geometrySetConfig.getSqlTable() + " WHERE " + geometrySetConfig.getSqlCondition() + " AND " + geometrySetConfig.getGeometryField() + " IS NOT NULL;";
			    } else {
				sqlQuery = "SELECT " + geometrySetConfig.getIDColumn() + " AS _id" + (geometrySetConfig.getLabelColumn().length() > 0?", ": "") + geometrySetConfig.getLabelColumn() + (geometrySetConfig.getToolTipValueColumn().length() > 0?", ": "") + geometrySetConfig.getToolTipValueColumn()
				       + (geometrySetConfig.getTypeColumn().length() > 0?", ": "") + geometrySetConfig.getTypeColumn() + ", replace(replace(AsText(" + geometrySetConfig.getGeometryField() + "), 'MULTILINESTRING((', ''), '))', '') AS the_geom"
				       + " FROM "+ geometrySetConfig.getSqlScheme() + "." + geometrySetConfig.getSqlTable() + " WHERE " + geometrySetConfig.getSqlCondition() + " AND " + geometrySetConfig.getGeometryField() + " IS NOT NULL;";
			    }
			    ResultSet _rsPolylines = exQuery(sqlQuery);
			    try {
				_rsPolylines.last();
				int _qty = _rsPolylines.getRow();
				int _percentDone = 0;
				_rsPolylines.beforeFirst();
				while (_rsPolylines.next()) {
				    String _polygonString = _rsPolylines.getString("the_geom");
				    String[] points = _polygonString.split(",");
				    double[] xd = new double[points.length];
				    double[] yd = new double[points.length];
				    for (int i = 0; i < points.length; i++) {
					String[] xy = points[i].split(" ");
					xd[i] = Double.parseDouble(xy[0]);
					yd[i] = Double.parseDouble(xy[1]);
				    }
				    ESRIPolygon _polygon = ESRIPolygon.constructPolygon(xd, yd);
				    _polygon.setIdPolygon(_rsPolylines.getInt("_id"));
				    boolean _found = false;
				    int i = 0;
				    while (i < geometrySetConfig.getGridSize() && !_found) {
					int j = 0;
					while (j < geometrySetConfig.getGridSize() && !_found) {
					    if (_polygon.intersects(new Rectangle2D.Double(geometrySetConfig.getMatrixBounds()[0][i][j], geometrySetConfig.getMatrixBounds()[1][i][j], geometrySetConfig.getMatrixBounds()[2][i][j], geometrySetConfig.getMatrixBounds()[3][i][j]))) {
						matrix[i][j].add(_polygon);
						q++;
						if (q*100/_qty > _percentDone) {
						    _percentDone = q*100/_qty;
						    System.out.println(_percentDone + "% done");
						}
						_found = true;
					    }
					}
				    }
				}
				System.out.println(q + " polilíneas vectorizados en una grilla de " + geometrySetConfig.getGridSize() + "x" + geometrySetConfig.getGridSize());
			    } catch (NullPointerException e) {
				//ignore
			    }
			} catch (SQLException x) {
			    Advisor.messageBox(x.getErrorCode() + ": " + x.getMessage(), "Error en la consulta SQL");
			}
			break;
		    case ShapeTypes.POINT:
		    case ShapeTypes.MULTIPOINT:
			try {
    
			    String sqlQuery = "";
			    if (geometrySetConfig.getShapeType() == ShapeTypes.POINT) {
				//Sólo obtengo la geometría [0] o [1]
				 sqlQuery = "SELECT " + geometrySetConfig.getIDColumn() + " AS _id" + (geometrySetConfig.getLabelColumn().length() > 0?", ": "") + geometrySetConfig.getLabelColumn() + (geometrySetConfig.getToolTipValueColumn().length() > 0?", ": "") + geometrySetConfig.getToolTipValueColumn()
					+ (geometrySetConfig.getTypeColumn().length() > 0?", ": "") + geometrySetConfig.getTypeColumn() + ", ST_x("+ geometrySetConfig.getGeometryField() + ") AS X, ST_y(" + geometrySetConfig.getGeometryField() + ") AS Y "
					+ " FROM "+ geometrySetConfig.getSqlScheme() + "." + geometrySetConfig.getSqlTable() + " WHERE " + geometrySetConfig.getSqlCondition() + " AND " + geometrySetConfig.getGeometryField() + " IS NOT NULL;";
			    } else {
				sqlQuery = "SELECT " + geometrySetConfig.getIDColumn() + " AS _id" + (geometrySetConfig.getLabelColumn().length() > 0?", ": "") + geometrySetConfig.getLabelColumn() + (geometrySetConfig.getToolTipValueColumn().length() > 0?", ": "") + geometrySetConfig.getToolTipValueColumn()
				+ (geometrySetConfig.getTypeColumn().length() > 0?", ": "") + geometrySetConfig.getTypeColumn() + ", ST_x(geometryn("+ geometrySetConfig.getGeometryField() + ",1)) AS X, ST_y(geometryn(" + geometrySetConfig.getGeometryField() + ",1)) AS Y "
					+ " FROM "+ geometrySetConfig.getSqlScheme() + "." + geometrySetConfig.getSqlTable() + " WHERE " + geometrySetConfig.getSqlCondition() + " AND " + geometrySetConfig.getGeometryField() + " IS NOT NULL;";
			    }
			    ResultSet _rsPoints = exQuery(sqlQuery);
    
			    //ARREGLO POR SI HAY UN SOLO PUNTO Y QUE SE PUEDA NAVEGAR Y HACER ZOOM
			    /*if (extents < 10) {
				extents = 400;
			    }*/
			    _rsPoints.last();
			    int _qty = _rsPoints.getRow();
			    int _percentDone = 0;
			    _rsPoints.beforeFirst();
			    while (_rsPoints.next()) {
				ESRIPoint _point = new ESRIPoint(_rsPoints.getDouble("X"), _rsPoints.getDouble("Y"));
				_point.setIdPoint(_rsPoints.getInt("_id"));
				if (geometrySetConfig.getTypeColumn().length() > 0) {
				    _point.setSymbol(_rsPoints.getInt(geometrySetConfig.getTypeColumn()));
				}
				for (int i = 0; i < geometrySetConfig.getGridSize(); i++)  {
				    for (int j = 0; j < geometrySetConfig.getGridSize(); j++)  {
					if ((new Rectangle2D.Double(geometrySetConfig.getMatrixBounds()[0][i][j], geometrySetConfig.getMatrixBounds()[1][i][j], geometrySetConfig.getMatrixBounds()[2][i][j], geometrySetConfig.getMatrixBounds()[3][i][j])).contains(_point)) {
					    matrix[i][j].add(_point);
					    q++;
					    if (q*100/_qty > _percentDone) {
						_percentDone = q*100/_qty;
						System.out.println(_percentDone + "% done");
					    }
					}
				    }
				}
			    }
			    System.out.println(q + " puntos vectorizados en una grilla de " + geometrySetConfig.getGridSize() + "x" + geometrySetConfig.getGridSize());
			} catch (SQLException x) {
			    Advisor.messageBox(x.getErrorCode() + ": " + x.getMessage(), "Error en la consulta SQL");
			}
			break;
		}
		//FIN
		if (parent != null) {
		    parent.repaint();
		}
		fetchKeys();
		cacheGeometrySetThread();
		loaded = true;
		needsReload = false;
	    } catch (Exception x) {
		x.printStackTrace();
		loaded = false;
		needsReload = true;
	    }
	    loading = false;
	    lastRetrievingTime = (System.currentTimeMillis() - _start)/1000;
	    /*freeMem = r.freeMemory();
	    System.out.println("free memory after creating array: " + freeMem);
	    r.gc();
	    freeMem = r.freeMemory();
	    System.out.println("free memory after creating array and after gc: " + freeMem);*/
	    closeConnection();
	}
    }

    public void reload() {
	if (!loading) {
	    tryToConnect();
	    if (geometrySetConfig.getProjectionType() != CoordinateSystems.GK) {
		if (Advisor.question("Error", "Las geometrías " + geometrySetConfig.getSqlScheme() + "." + geometrySetConfig.getSqlTable() + " están en el Sistema de Coordenadas " 
		    + CoordinateSystems.getProjectionType(geometrySetConfig.getProjectionType()) + "\n el cual aún no está soportado, "
		    + "¿desea continuar cargando las geometrías?"/*, "Error en ProjectionType, sólo " + CoordinateSystems.getProjectionType(CoordinateSystems.GK) + " está soportada (temporalmente)"*/)) {
		    fetchGeometriesFromDatabaseThread();
		}
	    } else {
		fetchGeometriesFromDatabaseThread();
	    }
	}
    }
    
    private synchronized void fetchGeometriesFromDatabaseThread() {
	    Thread threadTask = new Thread(new Runnable() {

					public void run() {
					    fetchGeometriesFromDatabase();
					} //end run;
				    }
		);

	if (!loading) {
	    if (geometrySetConfig.getGeometryTypeFromSQL(_gaiaClient) != -1) {
		threadTask.setPriority(Thread.MIN_PRIORITY);
		if (lastRetrievingTime > 120) {
		    if (Advisor.question("Recargar layer", "La última vez que recargó este layer le tomó " + lastRetrievingTime + 
                                                                                   (lastRetrievingTime==1?" segundo":" segundos") + ", ¿Está seguro?")) {
			threadTask.start();
		    }
		} else {
		    threadTask.start();
		}
	    }
	} else {
	    /*if (Advisor.question(null, "Bajando layer", "<html><p align=center>El layer " + getName() + " se está bajando de la base de datos<br>¿Desea cancelar la operación?</p></html>")) {
		try {
		    loaded = false;
		    loading = false;
		    needsReload = true;
		    threadTask.interrupt();
		} catch (Exception x) {
		    //ignore
		}
	    }*/
	    //Advisor.messageBox("<html><p align=center>El layer " + getName() + " se está bajando de la base de datos<br>Puede demorar unos minutos dependiendo de su conexión a internet<br>Por favor sea paciente</p></html>", "Aviso");
	}
	//SwingUtilities.invokeLater(threadTask); // Para simular maquina rapida :)
    }

    private synchronized void fetchGeometriesFromCache() {
	System.out.println("Fetching geometries " + geometrySetConfig.getSqlScheme() + "." + geometrySetConfig.getSqlTable() + " from cache...");
	loading = true;
	loaded = false;
	try {
	    if (parent != null) {
		parent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	    }

	    // Read from disk using FileInputStream
	    FileInputStream inFile = new FileInputStream(new File(getFileName()));

	    // Read object using ObjectInputStream
	    ObjectInputStream inObject = new ObjectInputStream(inFile);

	    // Read an object
	    Object cachedObject = inObject.readObject();

	    if (cachedObject instanceof Vector[][]) {
		// Cast object to a Vector
		matrix = (Vector[][])cachedObject;
		if (parent != null) {
		    parent.repaint();
		}
	    } else {
		System.out.println(cachedObject.getClass());
	    }
	    loaded = true;
	} catch (InvalidClassException e) {
	    e.printStackTrace();
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	if (!loaded) {
	    fetchGeometriesFromDatabase();
	}
	loading = false;
	if (parent != null) {
	    parent.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	System.out.println("Fetching geometries " + geometrySetConfig.getSqlScheme() + "." + geometrySetConfig.getSqlTable() + " done...");
    }

    public void setParent(Component parent) {
	this.parent = parent;
    }

    public void addGeometry(Object _geometry) {
	boolean _added = false;
	if (_geometry instanceof ESRIPolygon && ( (geometrySetConfig.getShapeType() == ShapeTypes.POLYGON) || (geometrySetConfig.getShapeType() == ShapeTypes.MULTIPOLYGON) || (geometrySetConfig.getShapeType() == ShapeTypes.POLYLINE) || (geometrySetConfig.getShapeType() == ShapeTypes.MULTIPOLYLINE) ) ) {
	    ESRIPolygon _polygon = (ESRIPolygon)_geometry;
	    for (int i = 0; i < geometrySetConfig.getGridSize(); i++)  {
		for (int j = 0; j < geometrySetConfig.getGridSize(); j++)  {
		    //if ((new Rectangle2D.Double(geometrySetConfig.getMatrixBounds()[0][i][j], geometrySetConfig.getMatrixBounds()[1][i][j], geometrySetConfig.getMatrixBounds()[2][i][j], geometrySetConfig.getMatrixBounds()[3][i][j])).intersects(_polygon.getBounds2D())) {
		    if (_polygon.intersects(new Rectangle2D.Double(geometrySetConfig.getMatrixBounds()[0][i][j], geometrySetConfig.getMatrixBounds()[1][i][j], geometrySetConfig.getMatrixBounds()[2][i][j], geometrySetConfig.getMatrixBounds()[3][i][j]))) {
			matrix[i][j].add(_polygon);
			_added = true;
		    }
		}
	    }
	} else if (_geometry instanceof ESRIPoint && (  (geometrySetConfig.getShapeType() == ShapeTypes.POINT)  || (geometrySetConfig.getShapeType() == ShapeTypes.MULTIPOINT) )) {
	    ESRIPoint _point = (ESRIPoint)_geometry;
	    for (int i = 0; i < geometrySetConfig.getGridSize(); i++)  {
		for (int j = 0; j < geometrySetConfig.getGridSize(); j++)  {
		    //if ((new Rectangle2D.Double(geometrySetConfig.getMatrixBounds()[0][i][j], geometrySetConfig.getMatrixBounds()[1][i][j], geometrySetConfig.getMatrixBounds()[2][i][j], geometrySetConfig.getMatrixBounds()[3][i][j])).intersects(_polygon.getBounds2D())) {
		    if ( (new Rectangle2D.Double(geometrySetConfig.getMatrixBounds()[0][i][j], geometrySetConfig.getMatrixBounds()[1][i][j], geometrySetConfig.getMatrixBounds()[2][i][j], geometrySetConfig.getMatrixBounds()[3][i][j])).contains(_point)) {
			matrix[i][j].add(_point);
		        _added = true;
		    }
		}
	    }
	}
	if (!_added) {
	    matrix[0][0].add(_geometry);
	}
    }
    
    public void removeGeometry(Object _geometry) {
	for (int i = 0; i < geometrySetConfig.getGridSize(); i++)  {
	    for (int j = 0; j < geometrySetConfig.getGridSize(); j++)  {
		matrix[i][j].remove(_geometry);
	    }
	}
    }

    @Deprecated
    public Vector getContainedGeometries(Rectangle2D _bounds) {
	Vector _geometries = new Vector();
	if ( ((geometrySetConfig.getShapeType() == ShapeTypes.POLYGON) || (geometrySetConfig.getShapeType() == ShapeTypes.MULTIPOLYGON)) || ((geometrySetConfig.getShapeType() == ShapeTypes.POLYLINE) || (geometrySetConfig.getShapeType() == ShapeTypes.MULTIPOLYLINE)) ) {
	    for (int i = 0; i < geometrySetConfig.getGridSize(); i++) {
		for (int j = 0; j < geometrySetConfig.getGridSize(); j++) {
		    if (((ESRIPolygon)matrix[i][j].elementAt(i)).intersects(new Rectangle2D.Double(geometrySetConfig.getMatrixBounds()[0][i][j], geometrySetConfig.getMatrixBounds()[1][i][j], geometrySetConfig.getMatrixBounds()[2][i][j], geometrySetConfig.getMatrixBounds()[3][i][j]))) {
			_geometries.add(matrix[i][j].elementAt(i));
		    }
		}
	    }
	}
	return _geometries;
    }


    public ESRIPolygon getPolygon(int _idPolygon) {
	ESRIPolygon returns = null;
	if ( ((geometrySetConfig.getShapeType() == ShapeTypes.POLYGON) || (geometrySetConfig.getShapeType() == ShapeTypes.MULTIPOLYGON)) || ((geometrySetConfig.getShapeType() == ShapeTypes.POLYLINE) || (geometrySetConfig.getShapeType() == ShapeTypes.MULTIPOLYLINE)) ) {
	    boolean found = false;
	    int i = 0;
	    while (!found && i < geometrySetConfig.getGridSize()) {
		int j = 0;
		while (!found && j < geometrySetConfig.getGridSize()) {       
		    for (int k = 0; k < matrix[i][j].size(); k++)  {
			if (((ESRIPolygon)matrix[i][j].elementAt(k)).getIdPolygon() == _idPolygon) {
			    returns = (ESRIPolygon)matrix[i][j].elementAt(k);
			    found = true;
			}
		    }
		    j++;
		}
		i++;
	    }
	} else {
	    System.out.println("Se ha pedido un polígono/polilínea a un layer de un tipo diferente");
	}
	return returns;
    }

    public ESRIPoint getPoint(int _idPoint) {
	ESRIPoint returns = null;
	if ( (geometrySetConfig.getShapeType() == ShapeTypes.POINT) || (geometrySetConfig.getShapeType() == ShapeTypes.MULTIPOINT) ) {
	    boolean found = false;
	    int i = 0;
	    while (!found && i < geometrySetConfig.getGridSize()) {
		int j = 0;
		while (!found && j < geometrySetConfig.getGridSize()) {
		    for (int k = 0; k < matrix[i][j].size(); k++)  {
			if (((ESRIPoint)matrix[i][j].elementAt(k)).getIdPoint() == _idPoint) {
			    returns = (ESRIPoint)matrix[i][j].elementAt(k);
			    found = true;
			}
		    }
		    j++;
		}
		i++;
	    }
	} else {
	    System.out.println("Se ha pedido un punto a un layer de un tipo diferente");
	}
	return returns;
    }

    public int getContainedShapeID(double _x, double _y) {
	boolean found = (matrix == null);
	containedShapeID = -1;
	if (geometrySetConfig.getShapeType() == ShapeTypes.POLYGON || geometrySetConfig.getShapeType() == ShapeTypes.MULTIPOLYGON) {
	   int i = 0;
	   while (!found && i < geometrySetConfig.getGridSize()) {
	       int j = 0;
	       while (!found && j < geometrySetConfig.getGridSize()) {
		   int k = 0;
		   while (!found && k < matrix[i][j].size()) {
		       ESRIPolygon _polygon = (ESRIPolygon)matrix[i][j].elementAt(k);
		       if (_polygon.contains(_x, _y)) {
			   containedShapeID = _polygon.getIdPolygon();
			   found = true;
		       }
		       k++;
		   }
		   j++;
	       }
	       i++;
	   }
	} else if (geometrySetConfig.getShapeType() == ShapeTypes.POLYLINE || geometrySetConfig.getShapeType() == ShapeTypes.MULTIPOLYLINE) {
	    int i = 0;
	    while (!found && i < geometrySetConfig.getGridSize()) {
		int j = 0;
		while (!found && j < geometrySetConfig.getGridSize()) {
		    int k = 0;
		    while (!found && k < matrix[i][j].size()) {
			ESRIPolygon _polyline = (ESRIPolygon)matrix[i][j].elementAt(k);
			int l = 0;
			while (l < _polyline.getVertexCount() - 1 & !found) {
			    if (new Line2D.Double(_polyline.getX(l), _polyline.getY(l), _polyline.getX(l + 1), _polyline.getY(l + 1)).intersects(_x - geometrySetConfig.getTolerance() / 2, _y - geometrySetConfig.getTolerance() / 2, geometrySetConfig.getTolerance(), geometrySetConfig.getTolerance())) {
				containedShapeID = _polyline.getIdPolygon();
				found = true;
			    }
			    l++;
			}
			k++;
		    }
		    j++;
		}
		i++;
	    }
	} else if (geometrySetConfig.getShapeType() == ShapeTypes.POINT || geometrySetConfig.getShapeType() == ShapeTypes.MULTIPOINT) {
	    int i = 0;
	    while (!found && i < geometrySetConfig.getGridSize()) {
		int j = 0;
		while (!found && j < geometrySetConfig.getGridSize()) {
		    int k = 0;
		    while (!found && k < matrix[i][j].size()) {
		       ESRIPoint _point = (ESRIPoint)matrix[i][j].elementAt(k);
		       double _tolerance = Math.max((double)geometrySetConfig.getTolerance() , geometrySetConfig.getPointDiameter());
		       Shape point = new Ellipse2D.Double(_point.getX() -  _tolerance / 2.0, _point.getY() - _tolerance  / 2.0, _tolerance , _tolerance);
		       if (point.contains(new Point2D.Double(_x, _y))) {
			   containedShapeID = _point.getIdPoint();
			   found = true;
		       }
		       k++;
		    }
		    j++;
		}
		i++;
	    }
	}
	return containedShapeID;
    }

    public int[] getContainedShapeIDS(double _x, double _y) {
	boolean found = (matrix == null);
	Vector<Integer> _containedShapeIDS = new Vector<Integer>();
	if (geometrySetConfig.getShapeType() == ShapeTypes.POLYGON || geometrySetConfig.getShapeType() == ShapeTypes.MULTIPOLYGON) {
	   int i = 0;
	   while (!found && i < geometrySetConfig.getGridSize()) {
	       int j = 0;
	       while (!found && j < geometrySetConfig.getGridSize()) {
		   int k = 0;
		   while (!found && k < matrix[i][j].size()) {
		       ESRIPolygon _polygon = (ESRIPolygon)matrix[i][j].elementAt(k);
		       if (_polygon.contains(_x, _y)) {
			   _containedShapeIDS.add(_polygon.getIdPolygon());
		       }
		       k++;
		   }
		   j++;
	       }
	       i++;
	   }
	} else if (geometrySetConfig.getShapeType() == ShapeTypes.POLYLINE || geometrySetConfig.getShapeType() == ShapeTypes.MULTIPOLYLINE) {
	    int i = 0;
	    while (!found && i < geometrySetConfig.getGridSize()) {
		int j = 0;
		while (!found && j < geometrySetConfig.getGridSize()) {
		    int k = 0;
		    while (!found && k < matrix[i][j].size()) {
			ESRIPolygon _polyline = (ESRIPolygon)matrix[i][j].elementAt(k);
			int l = 0;
			while (l < _polyline.getVertexCount() - 1 & !found) {
			    if (new Line2D.Double(_polyline.getX(l), _polyline.getY(l), _polyline.getX(l + 1), _polyline.getY(l + 1)).intersects(_x - geometrySetConfig.getTolerance() / 2, _y - geometrySetConfig.getTolerance() / 2, geometrySetConfig.getTolerance(), geometrySetConfig.getTolerance())) {
			        _containedShapeIDS.add(_polyline.getIdPolygon());
			    }
			    l++;
			}
			k++;
		    }
		    j++;
		}
		i++;
	    }
	} else if (geometrySetConfig.getShapeType() == ShapeTypes.POINT || geometrySetConfig.getShapeType() == ShapeTypes.MULTIPOINT) {
	    int i = 0;
	    while (!found && i < geometrySetConfig.getGridSize()) {
		int j = 0;
		while (!found && j < geometrySetConfig.getGridSize()) {
		    int k = 0;
		    while (!found && k < matrix[i][j].size()) {
		       ESRIPoint _point = (ESRIPoint)matrix[i][j].elementAt(k);
		       double _tolerance = Math.max((double)geometrySetConfig.getTolerance() , geometrySetConfig.getPointDiameter());
		       Shape point = new Ellipse2D.Double(_point.getX() -  _tolerance / 2.0, _point.getY() - _tolerance  / 2.0, _tolerance , _tolerance);
		       if (point.contains(new Point2D.Double(_x, _y))) {
		           _containedShapeIDS.add(_point.getIdPoint());
		       }
		       k++;
		    }
		    j++;
		}
		i++;
	    }
	}
	int[] _results = new int[_containedShapeIDS.size()];
	for (int i = 0; i < _results.length; i++)  {
	    _results[i] = _containedShapeIDS.elementAt(i);
	}
	return _results;
    }

    public Object getContainedShape(double _x, double _y) {
	boolean found = false;
	ESRIPolygon containedShape = null;
	if (geometrySetConfig.getShapeType() == ShapeTypes.POLYGON || geometrySetConfig.getShapeType() == ShapeTypes.MULTIPOLYGON) {
	    int i = 0;
	    while (!found && i < geometrySetConfig.getGridSize()) {
		int j = 0;
		while (!found && j < geometrySetConfig.getGridSize()) {
		    int k = 0;
		    while (!found && k < matrix[i][j].size()) {
			ESRIPolygon _polygon = (ESRIPolygon)matrix[i][j].elementAt(k);
			if (_polygon.contains(_x, _y)) {
			    containedShape = _polygon;
			    found = true;
			}
			k++;
		    }
		    j++;
		}
		i++;
	    }
	} else {
	    //TODO
	}
	return containedShape;
    }

    private void resetAllFilterMatches() {
	for (int i = 0; i < layers.size(); i++)  {
	    layers.elementAt(i).resetFilterMap();
	}
    }

    private void fetchKeys() {
	for (int i = 0; i < layers.size(); i++)  {
	    if (layers.elementAt(i).isOn()) {
	        layers.elementAt(i).fetchLabelValues();
	    }
	}
    }

    public Vector[][] getMatrix() {
	return matrix;
    }

    public Vector getGeometriesFromMatrix(int i, int j) {
	if (!loaded) {
	    return new Vector();
	} else {
	    return matrix[i][j];
	}
    }
    
    public int getGeometriesCount() {
	int _total = 0;
	for (int i = 0; i < geometrySetConfig.getGridSize(); i++)  {
	    for (int j = 0; j < geometrySetConfig.getGridSize(); j++)  {
		try {
		    _total += matrix[i][j].size();
		} catch (NullPointerException x) {
		    continue;
		}
	    }
	}
	return _total;
    }

    private synchronized void fetchGeometrySetConfigFromCache() {
	final File _cacheFile = new File(GaiaEnvironment.getCacheFileName(geometrySetConfig.getSqlScheme() + "." + geometrySetConfig.getSqlTable()) + ".gsetconfig");
        System.out.println("Searching " + _cacheFile.getName());
	if (!(_cacheFile.exists())) {
	    System.out.println(_cacheFile.getName() + " not found!");
	    //layerConfig.saveData();
	} else {
	    System.out.println("Fetching geometries " + geometrySetConfig.getSqlScheme() + "." + geometrySetConfig.getSqlTable() + " configuration");
	    try {
		// Read from disk using FileInputStream
		FileInputStream inFile = new FileInputStream(_cacheFile);

		// Read object using ObjectInputStream
		ObjectInputStream inObject = new ObjectInputStream(inFile);

		// Read an object
		Object cachedObject = inObject.readObject();

		if (cachedObject instanceof GeometrySetConfig) {
		    // Cast object to a Vector
		    geometrySetConfig = (GeometrySetConfig)cachedObject;
		    if (parent != null) {
			parent.repaint();
		    }
		}
		haveConfig = true;
		needsReload = false;
	    } catch (ClassNotFoundException e) {
		System.out.println("Error: " + e.getMessage());
		geometrySetConfig.saveData();
	    } catch (InvalidClassException e) {
		System.out.println("Error: " + e.getMessage());
		geometrySetConfig.saveData();
	    } catch (FileNotFoundException e) {
		System.out.println("Error: " + e.getMessage());
		geometrySetConfig.saveData();
	    } catch (IOException e) {
		System.out.println("Error: " + e.getMessage());
		geometrySetConfig.saveData();
	    }
	    System.out.println("Fetching geometries " + geometrySetConfig.getSqlScheme() + "." + geometrySetConfig.getSqlTable() + " configuration done...");
	}
    }

    public GeometrySetConfig getGeometrySetConfig() {
	return geometrySetConfig;
    }

    public void setContainedShapeID(int _containedShapeID) {
	containedShapeID = _containedShapeID;
    }

    public int getContainedShapeID() {
	return containedShapeID;
    }

    public boolean isLoaded() {
	return loaded;
    }

    public void setNeedsReload(boolean _needsReload) {
	this.needsReload = _needsReload;
    }

    public boolean needsReload() {
	return needsReload;
    }

    public boolean contains(Layer _layer) {
	return layers.contains(_layer);
    }
    
    public Vector<Layer> getLayers() {
	return layers;
    }
    
    public boolean hasAccessPrivileges() {
	return hasAccessPrivileges;
    }
    
    public boolean compressFile() {
	boolean returns = false;
	try {
	    int BUFFER = 2048;
	    BufferedInputStream origin = null;
	    FileOutputStream dest = new FileOutputStream(getFileName() + ".zipped");
	    CheckedOutputStream checksum = new CheckedOutputStream(dest, new Adler32());
	    ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(checksum));
	    //out.setMethod(ZipOutputStream.DEFLATED);
	    byte data[] = new byte[BUFFER];

	    File _file = new File(getFileName());
	    FileInputStream _inFile = new FileInputStream(_file);
	    origin = new BufferedInputStream(_inFile, BUFFER);
	    ZipEntry entry = new ZipEntry(getFileName());
	    out.putNextEntry(entry);
	    int count;
	    while ((count = origin.read(data, 0, BUFFER)) != -1) {
		out.write(data, 0, count);
	    }
	    origin.close();
	    out.close();
	    System.out.println("checksum: " + checksum.getChecksum().getValue());
	    returns = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return returns;
    }

    public boolean decompressFile() {
	boolean returns = false;
	try {
	    int BUFFER = 2048;
	    BufferedOutputStream dest = null;
	    FileInputStream fis = new FileInputStream(getFileName() + ".zipped");
	    CheckedInputStream checksum = new CheckedInputStream(fis, new Adler32());
	    ZipInputStream zis = new ZipInputStream(new BufferedInputStream(checksum));
	    ZipEntry entry;
	    while ((entry = zis.getNextEntry()) != null) {
		System.out.println("Extracting: " + entry);
		int count;
		byte data[] = new byte[BUFFER];
		// write the files to the disk
		FileOutputStream fos = new FileOutputStream(entry.getName() + ".unzipped");
		dest = new BufferedOutputStream(fos, BUFFER);
		while ((count = zis.read(data, 0, BUFFER)) != -1) {
		    dest.write(data, 0, count);
		}
		dest.flush();
		dest.close();
	    }
	    zis.close();
	    System.out.println("checksum: " + checksum.getChecksum().getValue());
	    returns = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return returns;
    }

    public boolean compressObject() {
	boolean returns = false;
	try {
	    FileOutputStream fos = new FileOutputStream(getFileName() + ".zippedObject");
	    GZIPOutputStream gz = new GZIPOutputStream(fos);
	    ObjectOutputStream oos = new ObjectOutputStream(gz);
	    oos.writeObject(matrix);
	    oos.flush();
	    oos.close();
	    fos.close();
	    returns = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return returns;
    }

    public boolean decompressObject() {
	boolean returns = false;
	try {
	    FileInputStream fis = new FileInputStream(getFileName() + ".zippedObject");
	    GZIPInputStream gs = new GZIPInputStream(fis);
	    ObjectInputStream ois = new ObjectInputStream(gs);

	    Object _compressedObject = ois.readObject();

	    if (_compressedObject instanceof Vector[][]) {
	    
	        matrix = (Vector[][])_compressedObject;
	        if (parent != null) {
	            parent.repaint();
	        }
	        FileOutputStream outFile = new FileOutputStream(getFileName() + ".unzippedObject");
	        // Write object with ObjectOutputStream
	        ObjectOutputStream outObject = new ObjectOutputStream(outFile);
	        // Write object out to disk
	        outObject.writeObject(_compressedObject);
	        outObject.close();
	    
	    } else {
	        System.out.println(_compressedObject.getClass());
	    }
	    ois.close();
	    fis.close();
	    returns = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return returns;
    }

    private String getFileName() {
	return GaiaEnvironment.getCacheFileName(geometrySetConfig.getSqlScheme() + "." + geometrySetConfig.getSqlTable()) + ".gsc";
    }

    private ResultSet exQuery(String _sqlStat) {
	if (_gaiaClient != null) {
	    return _gaiaClient.exQuery(_sqlStat);
	} else {
	    return LibSQL.exQuery(_sqlStat);
	}
    }

    private boolean tryToConnect() {
	if (geometrySetConfig.getServerURL().length() > 0) {
	    _gaiaClient = new GaiaClient(geometrySetConfig.getServerURL(), geometrySetConfig.getDatabase(), geometrySetConfig.getUser(), geometrySetConfig.getPassword());
	    if (!_gaiaClient.startClient()) {
		_gaiaClient = null;
	        Advisor.messageBox("<html>Error al intentar conectarse al servidor de geometrías.\nPor favor revise la <u>configuración</u> y el <u>acceso a internet</u></html>.", "Error");
	    }
	} else {
	    _gaiaClient = null;
	}
	return (_gaiaClient != null);
    }

    private void closeConnection() {
	if (_gaiaClient != null) {
	    _gaiaClient.closeConnection();
	}
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
