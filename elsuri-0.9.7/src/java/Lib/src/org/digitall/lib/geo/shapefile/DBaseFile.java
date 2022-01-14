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
 * DBaseFile.java
 *
 * */
package org.digitall.lib.geo.shapefile;

public class DBaseFile {

    //DBF Header Structure
    private byte fileType;
    //Fle Type
    private byte year;
    //Year of Last Update
    private byte month;
    //month of Last Update
    private byte day;
    //day of Last Update
    private int recNumber;
    //Number of Records
    private short headerLength;
    //Length of Header Structure
    private short recLength;
    //Length of Record
    private double unused12;
    //Unused/RESERVED
    private double unused20;
    //Unused/RESERVED
    private int unused28;
    //Unused/RESERVED

    public DBaseFile() {

    }

    public void setFileType(byte _fileType) {
	this.fileType = _fileType;
    }

    public byte getFileType() {
	return fileType;
    }

    public void setYear(byte _year) {
	this.year = _year;
    }

    public byte getYear() {
	return year;
    }

    public void setMonth(byte _month) {
	this.month = _month;
    }

    public byte getMonth() {
	return month;
    }

    public void setDay(byte _day) {
	this.day = _day;
    }

    public byte getDay() {
	return day;
    }

    public void setRecNumber(int _recNumber) {
	this.recNumber = _recNumber;
    }

    public int getRecNumber() {
	return recNumber;
    }

    public void setHeaderLength(short _headerLength) {
	this.headerLength = _headerLength;
    }

    public short getHeaderLength() {
	return headerLength;
    }

    public void setRecLength(short _recLength) {
	this.recLength = _recLength;
    }

    public short getRecLength() {
	return recLength;
    }

    public void setUnused12(double _unused12) {
	this.unused12 = _unused12;
    }

    public double getUnused12() {
	return unused12;
    }

    public void setUnused20(double _unused20) {
	this.unused20 = _unused20;
    }

    public double getUnused20() {
	return unused20;
    }

    public void setUnused28(int _unused28) {
	this.unused28 = _unused28;
    }

    public int getUnused28() {
	return unused28;
    }

}
