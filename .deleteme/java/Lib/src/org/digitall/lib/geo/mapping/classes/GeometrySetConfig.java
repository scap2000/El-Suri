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
 * GeometrySetConfig.java
 *
 * */
package org.digitall.lib.geo.mapping.classes;

import java.awt.geom.Rectangle2D;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.sql.Types;

import java.util.Vector;

import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.data.Base64Coder;
import org.digitall.lib.data.Base64Coder;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.coordinatesystems.CoordinateSystems;
import org.digitall.lib.geo.gaia.GaiaClient;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.geo.shapefile.ShapeTypes;
import org.digitall.lib.sql.LibSQL;
import org.digitall.lib.ssl.MD5;

public class GeometrySetConfig implements Serializable {

    private static final long serialVersionUID = 10000096L;
    
    private int shapeType = -1;
    private int projectionType = CoordinateSystems.GK;
    private double minX = 0;
    private double maxX = 0;
    private double minY = 0;
    private double maxY = 0;
    private double extents = -1;
    private String sqlScheme = "";
    private String sqlTable = "";
    private String geometryField = "";
    private String sqlCondition = "";
    private String idColumn = "";
    private String labelColumn = "";
    private int labelColumnDataType = Types.VARCHAR;
    private String typeColumn = "";
    private String toolTipValueColumn = "";
    private int gridSize = 0;
    private int tolerance = 4; // 4 metros por defecto
    private int pointDiameter = 4; // 4 metros por defecto
    private double[][][] matrixBounds = null;
    private String name;

    private String serverURL = "";
    private String database = "";
    private String user = "";
    private String password = "";

    private boolean propietary = true;

    public void setMinX(double _minX) {
	this.minX = _minX;
    }

    public double getMinX() {
	return minX;
    }

    public void setMaxX(double _maxX) {
	this.maxX = _maxX;
    }

    public double getMaxX() {
	return maxX;
    }

    public void setMinY(double _minY) {
	this.minY = _minY;
    }

    public double getMinY() {
	return minY;
    }

    public void setMaxY(double _maxY) {
	this.maxY = _maxY;
    }

    public double getMaxY() {
	return maxY;
    }

    public void setExtents(double extents) {
	this.extents = extents;
    }

    public double getExtents() {
	return extents;
    }

    public void setSqlScheme(String sqlScheme) {
	this.sqlScheme = sqlScheme;
    }

    public String getSqlScheme() {
	return sqlScheme;
    }

    public void setSqlTable(String sqlTable) {
	this.sqlTable = sqlTable;
    }

    public String getSqlTable() {
	return sqlTable;
    }

    public void setGeometryField(String geometryField) {
	this.geometryField = geometryField;
    }

    public String getGeometryField() {
	return geometryField;
    }

    public void setSqlCondition(String sqlCondition) {
	this.sqlCondition = sqlCondition;
    }

    public String getSqlCondition() {
	return sqlCondition;
    }

    public int getShapeType() {
	return shapeType;
    }

    public void setGridSize(int gridSize) {
        this.gridSize = (gridSize>50?50:(gridSize>0?gridSize:1));
        setMatrixBounds(new double[4][getGridSize()][getGridSize()]);
        for (int i = 0; i < getGridSize(); i++)  {
            for (int j = 0; j < getGridSize(); j++)  {
                getMatrixBounds()[0][i][j] = getMinX() + (i * (getMaxX() - getMinX())/getGridSize());
                getMatrixBounds()[1][i][j] = getMinY() + (j * (getMaxY() - getMinY())/getGridSize());
                getMatrixBounds()[2][i][j] = (getMaxX() - getMinX())/getGridSize();
                getMatrixBounds()[3][i][j] = (getMaxY() - getMinY())/getGridSize();
            }
        }
        
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

    public void setIDColumn(String idColumn) {
	this.idColumn = idColumn;
    }

    public String getIDColumn() {
	return idColumn;
    }

    public void setLabelColumn(String labelColumn) {
	this.labelColumn = labelColumn;
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

    public void setLabelColumnDataType(int labelColumnDataType) {
	this.labelColumnDataType = labelColumnDataType;
    }

    public int getLabelColumnDataType() {
	return labelColumnDataType;
    }

    public void setToolTipValueColumn(String toolTipValueColumn) {
	this.toolTipValueColumn = toolTipValueColumn;
    }

    public String getToolTipValueColumn() {
	return toolTipValueColumn;
    }

    public int getGeometryTypeFromSQL(GaiaClient _gaiaClient) {
	int _shapeType = -1;
	try {
	    String geometryType;
	    if (_gaiaClient != null) {
	        geometryType =  _gaiaClient.getString("gis.getgeometrytype", "'" + sqlScheme + "','" + sqlTable + "','" + geometryField + "'");
	    } else {
	        geometryType = LibSQL.getString("gis.getgeometrytype", "'" + sqlScheme + "','" + sqlTable + "','" + geometryField + "'");
	    }
	    if (geometryType.equals("POINT")) {
		_shapeType = ShapeTypes.POINT;
	    } else if (geometryType.equals("MULTIPOINT")) {
		_shapeType = ShapeTypes.MULTIPOINT;
	    } else if (geometryType.equals("LINESTRING")) {
		_shapeType = ShapeTypes.POLYLINE;
	    } else if (geometryType.equals("MULTILINESTRING")) {
		_shapeType = ShapeTypes.MULTIPOLYLINE;
	    } else if (geometryType.equals("POLYGON")) {
		_shapeType = ShapeTypes.POLYGON;
	    } else if (geometryType.equals("MULTIPOLYGON")) {
		_shapeType = ShapeTypes.MULTIPOLYGON;
	    } else if (geometryType.equals("UNKNOWN")) {
		_shapeType = ShapeTypes.UNKNOWN;
	    } else {
		_shapeType = -1;
	    }
	    shapeType = _shapeType;
	    return _shapeType;
	} catch (Exception x) {
	    x.printStackTrace();
	    return _shapeType;
	}
    }

    public void setPointDiameter(int pointDiameter) {
	this.pointDiameter = pointDiameter;
    }

    public int getPointDiameter() {
	return pointDiameter;
    }

    public void setTolerance(int tolerance) {
	this.tolerance = tolerance;
    }

    public int getTolerance() {
	return tolerance;
    }

    public void saveData() {
        /*
	String _userHome = System.getProperty("user.home");
	File tempDir = new File(_userHome + File.separator + ".ddesktop.cache");
	boolean _enabled = true;
	if (!(tempDir.exists() && tempDir.isDirectory())) {
	    if (!tempDir.mkdir()) {
		System.err.println("Error al crear el directorio temporal");
		_enabled = false;
	    } else {
		//System.out.println("El directorio temporal se encuentra en " + _tmpDir);
	    }
	} else {
	    //System.out.println("El directorio temporal se encuentra en " + _tmpDir);
	}
	if (_enabled) {
	    System.out.println("Caching geometries " + sqlScheme + "." + sqlTable + " configuration");
	    try {
		// Write to disk with FileOutputStream
		String cacheFileName = tempDir + File.separator + MD5.getMD5(sqlScheme + "." + sqlTable + " - " + OrganizationInfo.getOrgName() + " - " + Environment.sessionUser);
		FileOutputStream outFile = new FileOutputStream(cacheFileName + ".gsetconfig");
		// Write object with ObjectOutputStream
		ObjectOutputStream outObject = new ObjectOutputStream(outFile);
		// Write object out to disk
		outObject.writeObject(this);
		outObject.close();
	    } catch (FileNotFoundException e) {
		e.printStackTrace();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    System.out.println("Fetching geometries " + sqlScheme + "." + sqlTable + " configuration done...");
	}*/
        GaiaEnvironment.gaiaEngine.saveProfile();
    }

    public Rectangle2D getBounds() {
	return new Rectangle2D.Double(minX, minY, maxX-minX, maxY-minY);
    }

    public void setProjectionType(int projectionType) {
	this.projectionType = projectionType;
    }

    public int getProjectionType() {
	return projectionType;
    }

    public String getServerURL() {
	return Base64Coder.decode(serverURL);
    }

    public String getDatabase() {
	return Base64Coder.decode(database);
    }

    public String getUser() {
	return Base64Coder.decode(user);
    }

    public String getPassword() {
	return Base64Coder.decode(password);
    }

    public void setConnectionParams(String _serverURL, String _database, String _user, String _password) {
	serverURL = Base64Coder.encode(_serverURL);
	database = Base64Coder.encode(_database);
	user = Base64Coder.encode(_user);
	password = Base64Coder.encode(_password);
	saveData();
    }

    protected void setPropietary(boolean _propietary) {
	propietary = _propietary;
    }

    public boolean isPropietary() {
	return propietary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
