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
 * ShapeFile.java
 *
 * */
package org.digitall.lib.geo.shapefile;

public class ShapeFile {

    //Header data
    private int fileCode;
    //Always 9994
    private int unused4;
    //Byte4
    private int unused8;
    //Byte8
    private int unused12;
    //Byte12
    private int unused16;
    //Byte16
    private int unused20;
    //Byte20
    private int fileLen;
    //File Length
    private int version;
    //Always 1000
    private int shapeType;
    //Shape Type
    private double Xmin;
    //Bounding Box;
    private double Ymin;
    //Bounding Box;
    private double Xmax;
    //Bounding Box;
    private double Ymax;
    //Bounding Box;
    private double Zmin;
    //Bounding Box;
    private double Zmax;
    //Bounding Box;
    private double Mmin;
    //Bounding Box;
    private double Mmax;
    //Bounding Box;
    private int recNumber;
    //Record Number;
    private int recLength;
    //Forma parte tambiÃ©n del archivo.shx
    //Content Length;

    public ShapeFile() {

    }

    public void setFileCode(int _fileCode) {
	this.fileCode = _fileCode;
    }

    public int getFileCode() {
	return fileCode;
    }

    public void setUnused4(int unused4) {
	this.unused4 = unused4;
    }

    public int getUnused4() {
	return unused4;
    }

    public void setUnused8(int unused8) {
	this.unused8 = unused8;
    }

    public int getUnused8() {
	return unused8;
    }

    public void setUnused12(int unused12) {
	this.unused12 = unused12;
    }

    public int getUnused12() {
	return unused12;
    }

    public void setUnused16(int unused16) {
	this.unused16 = unused16;
    }

    public int getUnused16() {
	return unused16;
    }

    public void setUnused20(int unused20) {
	this.unused20 = unused20;
    }

    public int getUnused20() {
	return unused20;
    }

    public void setFileLen(int _fileLen) {
	this.fileLen = _fileLen;
    }

    public int getFileLen() {
	return fileLen;
    }

    public void setVersion(int _version) {
	this.version = _version;
    }

    public int getVersion() {
	return version;
    }

    public void setShapeType(int _shapeType) {
	this.shapeType = _shapeType;
    }

    public int getShapeType() {
	return shapeType;
    }

    public void setXmin(double _xmin) {
	this.Xmin = _xmin;
    }

    public double getXmin() {
	return Xmin;
    }

    public void setYmin(double _yMin) {
	this.Ymin = _yMin;
    }

    public double getYmin() {
	return Ymin;
    }

    public void setXmax(double _xMax) {
	this.Xmax = _xMax;
    }

    public double getXmax() {
	return Xmax;
    }

    public void setYmax(double _yMax) {
	this.Ymax = _yMax;
    }

    public double getYmax() {
	return Ymax;
    }

    public void setZmin(double _zMin) {
	this.Zmin = _zMin;
    }

    public double getZmin() {
	return Zmin;
    }

    public void setZmax(double _zMax) {
	this.Zmax = _zMax;
    }

    public double getZmax() {
	return Zmax;
    }

    public void setMmin(double _mMin) {
	this.Mmin = _mMin;
    }

    public double getMmin() {
	return Mmin;
    }

    public void setMmax(double _mMax) {
	this.Mmax = _mMax;
    }

    public double getMmax() {
	return Mmax;
    }

    public void setRecNumber(int _recNumber) {
	this.recNumber = _recNumber;
    }

    public int getRecNumber() {
	return recNumber;
    }

    public void setRecLength(int _recLength) {
	this.recLength = _recLength;
    }

    public int getRecLength() {
	return recLength;
    }

}
