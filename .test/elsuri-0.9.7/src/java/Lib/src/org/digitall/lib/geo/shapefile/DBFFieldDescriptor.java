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
 * DBFFieldDescriptor.java
 *
 * */
package org.digitall.lib.geo.shapefile;

public class DBFFieldDescriptor {

    //Field Descriptor Structure
    private char[] fieldNameArray = new char[11];
    //Field Name in ASCII zero-filled
    private String fieldName = new String();
    //Field Name in ASCII zero-filled
    private byte fieldType;
    //Field Type in ASCII (Character, Numeric, Logical, Date or Memo)
    private int address;
    //Field Data Address
    private byte fieldLength;
    //Field Length in Binary
    private byte decimalLength;
    //Field Decimal Length in Binary
    private double unused18;
    //Unused/RESERVED
    private int unused26;
    //Unused/RESERVED
    private short unused30;
    //Unused/RESERVED;

    public DBFFieldDescriptor() {

    }

    public void setFieldName(String _fieldName) {
	this.fieldName = _fieldName.toUpperCase();
    }

    public String getFieldName() {
	return fieldName;
    }

    public void setFieldType(byte _fieldType) {
	this.fieldType = _fieldType;
    }

    public byte getFieldType() {
	return fieldType;
    }

    public void setAddress(int _address) {
	this.address = _address;
    }

    public int getAddress() {
	return address;
    }

    public void setFieldLength(byte _fieldLength) {
	this.fieldLength = _fieldLength;
    }

    public byte getFieldLength() {
	return fieldLength;
    }

    public void setDecimalLength(byte _decimalLength) {
	this.decimalLength = _decimalLength;
    }

    public byte getDecimalLength() {
	return decimalLength;
    }

    public void setUnused18(double _unused18) {
	this.unused18 = _unused18;
    }

    public double getUnused18() {
	return unused18;
    }

    public void setUnused26(int _unused26) {
	this.unused26 = _unused26;
    }

    public int getUnused26() {
	return unused26;
    }

    public void setUnused30(short _unused30) {
	this.unused30 = _unused30;
    }

    public short getUnused30() {
	return unused30;
    }

    public void setFieldNameArray(char[] _fieldNameArray) {
	this.fieldNameArray = _fieldNameArray;
    }

    public char[] getFieldNameArray() {
	return fieldNameArray;
    }

}
