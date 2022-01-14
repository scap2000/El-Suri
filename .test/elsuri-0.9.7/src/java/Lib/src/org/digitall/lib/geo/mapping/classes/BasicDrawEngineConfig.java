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
 * BasicDrawEngineConfig.java
 *
 * */
package org.digitall.lib.geo.mapping.classes;

import java.awt.Point;
import java.awt.geom.Point2D;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;

import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.data.Base64Coder;
import org.digitall.lib.geo.esri.ESRIPoint;
import org.digitall.lib.image.LibIMG;
import org.digitall.lib.sql.LibSQL;
import org.digitall.lib.ssl.MD5;

public class BasicDrawEngineConfig implements Serializable {

    private static final long serialVersionUID = 10000095L;

    public static final int OPERATION_ESCAPE = 0;
    public static final int OPERATION_DISTANCE_AREA = 2;
    public static final int OPERATION_QUERY = 8;
    public static final int OPERATION_ZOOM_OUT = 9;
    public static final int OPERATION_ZOOM_IN = 10;
    public static final int OPERATION_ADDRESSES = 11;
    public static final int OPERATION_ZOOM_EXTENTS = 12;
    public static final int OPERATION_STREETS = 13;
    public static final int OPERATION_INFRASTRUCTURES = 14;
    public static final int OPERATION_EDITION = 15;
    public static final int OPERATION_PRINT = 16;
    public static final int OPERATION_MANAGE_IMAGE_ATTACHMENTS = 17;
    public static final int OPERATION_MULTIQUERY = 18;
    public static final int OPERATION_SET_OSNAP_ON_OFF = 19;
    public static final int OPERATION_NOMENCLADOR = 20;
    public static final int OPERATION_SET_LOGO_ON_OFF = 21;
    public static final int OPERATION_SET_ANTIALIAS_ON_OFF = 22;
    public static final int OPERATION_SAVE_MAP_IMAGE = 23;
    public static final int OPERATION_SHOW_HELP = 24;
    public static final int OPERATION_SET_SCALEBAR_ON_OFF = 25;
    public static final int OPERATION_SET_COORDINATES_ON_OFF = 26;
    public static final int OPERATION_SET_COORDINATE_VIEWER_ON_OFF = 27;
    public static final int OPERATION_LIST_LAYERS = 28;
    public static final int OPERATION_FIXED_POLYGON_QUERY = 29;
    public static final int OPERATION_SET_RASTER_MODE_ON_OFF = 30;
    public static final int OPERATION_CONFIGURE_REPORT = 31;
    public static final int OPERATION_SAVE_CURRENT_POSITION = 32;
    public static final int OPERATION_SET_ORTHO_ON_OFF = 33;
    public static final int OPERATION_SET_SNAPTOGRID_ON_OFF = 34;
    
    //Variables del Universo
    private double extents = -1;
    private double drawFactor = 0;
    private double drawFactorOriginal = 0;
    private double xOffset = 0;
    private double yOffset = 0;
    private double xOffsetOriginal = 0;
    private double yOffsetOriginal = 0;
    private double xOffsetPosta = 0;
    private double yOffsetPosta = 0;
    private int fWidth = 0;
    private int fHeight = 0;
    private double drawScale = 1;
    private int projectionType = -1;
    private boolean hasReferencePosition = false;
    private double referencePositionX;
    private double referencePositionY;
    private boolean osnapActive = false;
    private boolean orthoActive = false;
    private boolean snapToGridActive = false;
    private boolean paintLogo = true;
    private boolean antiAlias = false;
    private boolean paintScaleBar = false;
    private boolean paintCoordinates = false;
    private int currentOperation = -1;
    private boolean coordinateViewerVisible = false;
    private String mapName = "Mapa sin nombre";

    private static HashMap<String, BufferedImage> symbolsSet = new HashMap<String, BufferedImage>();
    
    private String propietaryServerURL = "";
    private String propietaryDatabase = "";
    private String propietaryUser = "";
    private String propietaryPassword = "";

    private boolean propietaryDataSet = false;

    public void setExtents(double extents) {
	this.extents = extents;
    }

    public double getExtents() {
	return extents;
    }

    public void setDrawFactor(double drawFactor) {
	this.drawFactor = drawFactor;
    }

    public double getDrawFactor() {
	return drawFactor;
    }

    public void setDrawFactorOriginal(double drawFactorOriginal) {
	this.drawFactorOriginal = drawFactorOriginal;
    }

    public double getDrawFactorOriginal() {
	return drawFactorOriginal;
    }

    public void setXOffset(double xOffset) {
	this.xOffset = xOffset;
    }

    public double getXOffset() {
	return xOffset;
    }

    public void setYOffset(double yOffset) {
	this.yOffset = yOffset;
    }

    public double getYOffset() {
	return yOffset;
    }

    public void setXOffsetOriginal(double xOffsetOriginal) {
	this.xOffsetOriginal = xOffsetOriginal;
    }

    public double getXOffsetOriginal() {
	return xOffsetOriginal;
    }

    public void setYOffsetOriginal(double yOffsetOriginal) {
	this.yOffsetOriginal = yOffsetOriginal;
    }

    public double getYOffsetOriginal() {
	return yOffsetOriginal;
    }

    public void setXOffsetPosta(double xOffsetPosta) {
	this.xOffsetPosta = xOffsetPosta;
    }

    public double getXOffsetPosta() {
	return xOffsetPosta;
    }

    public void setYOffsetPosta(double yOffsetPosta) {
	this.yOffsetPosta = yOffsetPosta;
    }

    public double getYOffsetPosta() {
	return yOffsetPosta;
    }

    public void setFWidth(int fWidth) {
	this.fWidth = fWidth;
    }

    public int getFWidth() {
	return fWidth;
    }

    public void setFHeight(int fHeight) {
	this.fHeight = fHeight;
    }

    public int getFHeight() {
	return fHeight;
    }

    public void setDrawScale(double drawScale) {
	this.drawScale = drawScale;
    }

    public double getDrawScale() {
	return drawScale;
    }

    public void setProjectionType(int projectionType) {
	this.projectionType = projectionType;
    }

    public int getProjectionType() {
	return projectionType;
    }

    public int xtoPixel(double x) {
	return (int)(((x - xOffset) * drawFactor));
    }

    public int ytoPixel(double y) {
	return (int)((fHeight - ((y - yOffset) * drawFactor)));
    }

    public int[] xtoPixel(double[] _points) {
	int[] xpoints = new int[_points.length];
	for (int i = 0; i < _points.length; i++) {
	    xpoints[i] = xtoPixel(_points[i]);
	}
	return xpoints;
    }

    public int[] ytoPixel(double[] _points) {
	int[] ypoints = new int[_points.length];
	for (int i = 0; i < _points.length; i++) {
	    ypoints[i] = ytoPixel(_points[i]);
	}
	return ypoints;
    }

    public Point toPixel(double xd, double yd) {
	return new Point(xtoPixel(xd), ytoPixel(yd));
    }

    public Point toPixel(ESRIPoint _point) {
	return new Point(xtoPixel(_point.getX()), ytoPixel(_point.getY()));
    }

    public Point toPixel(Point2D.Double xy) {
	return new Point(xtoPixel(xy.getX()), ytoPixel(xy.getY()));
    }

    public Point2D.Double toSpace(int x, int y) {
	return new Point2D.Double(xtoSpace(x), ytoSpace(y));
    }

    public Point2D.Double toSpace(Point _point) {
	return new Point2D.Double(xtoSpace(_point.x), ytoSpace(_point.y));
    }

    public Point2D.Double toSpace(Point2D _point) {
	return new Point2D.Double(xtoSpace((int)_point.getX()), ytoSpace((int)_point.getY()));
    }

    public double xtoSpace(int x) {
	return (x / drawFactor + xOffset);
    }

    public double ytoSpace(int y) {
	return ((fHeight - y) / drawFactor + yOffset);
    }

    public double xtoSpace(double x) {
	return (x / drawFactor + xOffset);
    }

    public double ytoSpace(double y) {
	return ((fHeight - y) / drawFactor + yOffset);
    }

    public void saveData() {
	String _userHome = System.getProperty("user.home");
	final File _tmpDir = new File(_userHome + File.separator + ".ddesktop.cache");
	boolean _enabled = true;
	if (!(_tmpDir.exists() && _tmpDir.isDirectory())) {
	    if (!_tmpDir.mkdir()) {
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
		//System.out.println("Caching engine configuration");
		// Write to disk with FileOutputStream
		FileOutputStream outFile = new FileOutputStream(_tmpDir + File.separator + MD5.getMD5(mapName + " GIS Engine - " + OrganizationInfo.getOrgName()) + ".dec");
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
	}
    }

    public void setReferencePosition(Point2D.Double _referencePosition) {
	if (_referencePosition != null) {
	    referencePositionX = _referencePosition.getX();
	    referencePositionY = _referencePosition.getY();
	    hasReferencePosition = true;
	} else {
	    hasReferencePosition = false;
	}
    }

    public Point2D.Double getReferencePosition() {
	if (hasReferencePosition) {
	    return new Point2D.Double(referencePositionX, referencePositionY);
	} else {
	    return null;
	}
    }

    public boolean hasReferencePosition() {
	return hasReferencePosition;
    }

    public void setOsnapActive(boolean osnapActive) {
	this.osnapActive = osnapActive;
    }

    public boolean isOsnapActive() {
	return osnapActive;
    }

    public boolean paintLogo() {
	return paintLogo;
    }
    
    public void setPaintLogo(boolean _paintLogo) {
	paintLogo = _paintLogo;
    }

    public boolean isAntiAlias() {
	return antiAlias;
    }
    
    public void setAntiAlias(boolean _antiAlias) {
	antiAlias = _antiAlias;
    }

    public void setPaintScaleBar(boolean _scaleBar) {
	paintScaleBar = _scaleBar;
    }
    
    public boolean paintScaleBar() {
	return paintScaleBar;
    }

    public void setPaintCoordinates(boolean _paintCoordinates) {
	paintCoordinates = _paintCoordinates;
    }
    
    public boolean paintCoordinates() {
	return paintCoordinates;
    }

    public static void loadSymbolsVector() {
	try {
	    ResultSet _symbols = LibSQL.exQuery("SELECT idtype, symbol FROM tabs.infrastructuretype_tabs ORDER BY idtype");
	    symbolsSet.clear();
	    while (_symbols.next()) {
		symbolsSet.put(_symbols.getString("idtype"), LibIMG.getImageFromBytes(_symbols.getBytes("symbol")));
	    }
	} catch (SQLException e) {
	    // TODO
	    e.printStackTrace();
	}
    }

    public static BufferedImage getSymbol(int _symbol) {
	if (symbolsSet.size() == 0) {
	    loadSymbolsVector();
	}
	return symbolsSet.get(String.valueOf(_symbol));
    }

    public BasicDrawEngineConfig getCopy() {
	BasicDrawEngineConfig _engineConfig = new BasicDrawEngineConfig();
	_engineConfig.setAntiAlias(isAntiAlias());
	_engineConfig.setOsnapActive(isOsnapActive());
	_engineConfig.setDrawFactor(getDrawFactor());
	_engineConfig.setDrawFactorOriginal(getDrawFactorOriginal());
	_engineConfig.setDrawScale(getDrawScale());
	_engineConfig.setExtents(getExtents());
	_engineConfig.setFHeight(getFHeight());
	_engineConfig.setFWidth(getFWidth());
	_engineConfig.setPaintCoordinates(paintCoordinates());
	_engineConfig.setPaintScaleBar(paintScaleBar());
	_engineConfig.setPaintLogo(paintLogo());
	_engineConfig.setProjectionType(getProjectionType());
	_engineConfig.setReferencePosition(getReferencePosition());
	_engineConfig.setXOffset(getXOffset());
	_engineConfig.setXOffsetOriginal(getXOffsetOriginal());
	_engineConfig.setXOffsetPosta(getXOffsetPosta());
	_engineConfig.setYOffset(getYOffset());
	_engineConfig.setYOffsetOriginal(getYOffsetOriginal());
	_engineConfig.setYOffsetPosta(getYOffsetPosta());
	return _engineConfig;
    }

    public void setCurrentOperation(int currentOperation) {
	this.currentOperation = currentOperation;
    }

    public int getCurrentOperation() {
	return currentOperation;
    }

    public void setCoordinateViewerVisible(boolean coordinateViewerVisible) {
	this.coordinateViewerVisible = coordinateViewerVisible;
    }

    public boolean isCoordinateViewerVisible() {
	return coordinateViewerVisible;
    }

    public void setMapName(String mapName) {
	this.mapName = mapName;
    }

    public String getMapName() {
	return mapName;
    }

    public void setPropietaryConnectionParams(String _serverURL, String _database, String _user, String _password) {
	propietaryServerURL = Base64Coder.encode(_serverURL);
	propietaryDatabase = Base64Coder.encode(_database);
	propietaryUser = Base64Coder.encode(_user);
	propietaryPassword = Base64Coder.encode(_password);
	propietaryDataSet = true;
	saveData();
    }

    public String getPropietaryServerURL() {
	return propietaryServerURL;
    }

    public String getPropietaryDatabase() {
	return propietaryDatabase;
    }

    public String getPropietaryUser() {
	return propietaryUser;
    }

    public String getPropietaryPassword() {
	return propietaryPassword;
    }

    public void setOrthoActive(boolean _orthoActive) {
	orthoActive = _orthoActive;
    }

    public boolean isOrthoActive() {
	return orthoActive;
    }

    public boolean isPropietaryDataSet() {
	return propietaryDataSet;
    }

    public void setSnapToGridActive(boolean _snapToGridActive) {
	snapToGridActive = _snapToGridActive;
    }

    public boolean isSnapToGridActive() {
	return snapToGridActive;
    }
}
