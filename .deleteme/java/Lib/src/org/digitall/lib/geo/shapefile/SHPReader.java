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
 * SHPReader.java
 *
 * */
package org.digitall.lib.geo.shapefile;

import java.awt.geom.Point2D;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.text.DecimalFormat;

import java.util.Vector;

public class SHPReader {

    DecimalFormat df = new DecimalFormat("0.00000000");
    int bytes = 0;
    int shapeType = -1;

    public SHPReader() {
    }

    public Vector read(String file, int _from, int _to) {
	Vector shapes = read(file);
	Vector convShapes = new Vector();
	switch (shapeType) {
	    case ShapeTypes.POINT :
		for (int i = 0; i < shapes.size(); i++) {
		    SHPPoint point = ((SHPPoint)shapes.elementAt(i));
		    point.convertCoords(_from, _to, 19, 0);
		    //SOLO PARA LA 19SUR!!! y FAJA AUTOMATICA
		    convShapes.add(point);
		}
		break;
	    case ShapeTypes.POLYGON :
		for (int i = 0; i < shapes.size(); i++) {
		    SHPPolygon polygon = ((SHPPolygon)shapes.elementAt(i));
		    polygon.convertCoords(_from, _to, 19, 0);
		    //SOLO PARA LA 19SUR!!! y FAJA AUTOMATICA
		    convShapes.add(polygon);
		}
		break;
	}
	return shapes;
    }

    public Vector read(String file) {
	//String file = "/tmp/files/minas2";
	Vector _polys = new Vector();
	File archivoshp = new File(file + ".shp");
	if (archivoshp.canRead()) {
	    //Trabajar con el archivo
	    try {
		ShapeFile shp = new ShapeFile();
		DataInputStream dis = new DataInputStream(new FileInputStream(archivoshp));
		//Start of File Header (100 bytes)
		shp.setFileCode(readInt(dis));
		//Needs to be equal to 9994
		shp.setUnused4(readInt(dis));
		shp.setUnused8(readInt(dis));
		shp.setUnused12(readInt(dis));
		shp.setUnused16(readInt(dis));
		shp.setUnused20(readInt(dis));
		shp.setFileLen(readInt(dis) * 2);
		shp.setVersion(readIntLittleEndian(dis));
		//Needs to be equal to 1000
		shp.setShapeType(readIntLittleEndian(dis));
		//We only work with Types 1,5
		shapeType = shp.getShapeType();
		shp.setXmin(readDoubleLittleEndian(dis));
		shp.setYmin(readDoubleLittleEndian(dis));
		shp.setXmax(readDoubleLittleEndian(dis));
		shp.setYmax(readDoubleLittleEndian(dis));
		shp.setZmin(readDoubleLittleEndian(dis));
		shp.setZmax(readDoubleLittleEndian(dis));
		shp.setMmin(readDoubleLittleEndian(dis));
		shp.setMmax(readDoubleLittleEndian(dis));
		System.out.println("Xmin: " + shp.getXmin());
		System.out.println("Ymin: " + shp.getYmin());
		System.out.println("Xmax: " + shp.getXmax());
		System.out.println("Ymax: " + shp.getYmax());
		//100 bytes;
		//End of File Header
		if ((shp.getVersion() != 1000) || (shp.getFileCode() != 9994)) {
		    System.err.println("Error, algo en el archivo estÃ¡ mal");
		    //                    System.exit(-255);
		}
		//Start of File Data
		_polys = getShapesFromSHPFile(dis, shp);
	    } catch (FileNotFoundException ex) {
		ex.printStackTrace();
	    } catch (IOException ex) {
		ex.printStackTrace();
	    }
	} else {
	    //Mensaje de error, archivo no legible
	    System.err.println("Error en archivo: " + file);
	}
	File archivoshx = new File(file + ".shx");
	bytes = 0;
	if (archivoshx.canRead()) {
	    //Trabajar con el archivo
	    try {
		DataInputStream dis = new DataInputStream(new FileInputStream(archivoshx));
		ShapeFile shx = new ShapeFile();
		//Start of File Header (100 bytes)
		shx.setFileCode(readInt(dis));
		//Needs to be equal to 9994
		shx.setUnused4(readInt(dis));
		shx.setUnused8(readInt(dis));
		shx.setUnused12(readInt(dis));
		shx.setUnused16(readInt(dis));
		shx.setUnused20(readInt(dis));
		shx.setFileLen(readInt(dis) * 2);
		//Needs to be equal to 1000
		shx.setVersion(readIntLittleEndian(dis));
		shx.setShapeType(readIntLittleEndian(dis));
		shx.setXmin(readDoubleLittleEndian(dis));
		shx.setYmin(readDoubleLittleEndian(dis));
		shx.setXmax(readDoubleLittleEndian(dis));
		shx.setYmax(readDoubleLittleEndian(dis));
		shx.setZmin(readDoubleLittleEndian(dis));
		shx.setZmax(readDoubleLittleEndian(dis));
		shx.setMmin(readDoubleLittleEndian(dis));
		shx.setMmax(readDoubleLittleEndian(dis));
		//100 bytes;
		//End of File Header
		System.out.println("filelenshx: " + shx.getFileLen());
		System.out.println("bytes: " + bytes);
		while (bytes < shx.getFileLen()) {
		    //Index Records
		    shx.setRecNumber(readInt(dis));
		    shx.setRecLength(readInt(dis) * 2);
		    System.out.println("Offset: " + shx.getRecNumber());
		    System.out.println("Content Length: " + shx.getRecLength());
		}
	    } catch (FileNotFoundException ex) {
		ex.printStackTrace();
	    } catch (IOException ex) {
		ex.printStackTrace();
	    }
	} else {
	    //Mensaje de error, archivo no legible
	}
	File archivodbf = new File(file + ".dbf");
	bytes = 0;
	if (archivodbf.canRead()) {
	    //Trabajar con el archivo
	    try {
		DataInputStream dis = new DataInputStream(new FileInputStream(archivodbf));
		DBaseFile dbf = new DBaseFile();
		//Start of DBF Header
		dbf.setFileType(readByte(dis));
		dbf.setYear(readByte(dis));
		dbf.setMonth(readByte(dis));
		dbf.setDay(readByte(dis));
		dbf.setRecNumber(readIntLittleEndian(dis));
		dbf.setHeaderLength(readShortLittleEndian(dis));
		dbf.setRecLength(readShortLittleEndian(dis));
		System.out.println("DBF FileType: " + dbf.getFileType());
		System.out.println("DBF Year: " + dbf.getYear());
		System.out.println("DBF Month: " + dbf.getMonth());
		System.out.println("DBF Day: " + dbf.getDay());
		System.out.println("Rec Number: " + dbf.getRecNumber());
		System.out.println("Header Length: " + dbf.getHeaderLength());
		System.out.println("Rec Length: " + dbf.getRecLength());
		dbf.setUnused12(readDoubleLittleEndian(dis));
		dbf.setUnused20(readDoubleLittleEndian(dis));
		dbf.setUnused28(readInt(dis));
		System.out.println("Bytes: " + bytes);
		//End of DBF Header
		//Start of Field Descriptor
		Vector fields = new Vector();
		byte terminator = readByte(dis);
		int size = 0;
		int t = 1;
		do {
		    DBFFieldDescriptor field = new DBFFieldDescriptor();
		    char fieldName[] = new char[10];
		    fieldName[0] = (char)terminator;
		    for (int i = 1; i < 10; i++) {
			fieldName[i] = (char)readByte(dis);
			System.out.println((byte)fieldName[i]);
			//System.out.println("Char [" + i + "]: " + fieldName[i]);
		    }
		    readByte(dis);
		    field.setFieldNameArray(fieldName);
		    field.setFieldName(new String(fieldName).trim());
		    System.out.println("Field Name: " + field.getFieldName());
		    field.setFieldType(readByte(dis));
		    System.out.println("Field Type: " + field.getFieldType() + " - " + (char)field.getFieldType());
		    field.setAddress(readInt(dis));
		    System.out.println("Field Data Address: " + field.getAddress());
		    field.setFieldLength(readByte(dis));
		    System.out.println("Field Length: " + byte2int(field.getFieldLength()));
		    size += byte2int(field.getFieldLength());
		    field.setDecimalLength(readByte(dis));
		    System.out.println("Decimal Length: " + byte2int(field.getDecimalLength()));
		    readDoubleLittleEndian(dis);
		    readInt(dis);
		    readByte(dis);
		    readByte(dis);
		    terminator = readByte(dis);
		    System.out.println("Terminator: " + terminator);
		    fields.addElement(field);
		    t += 1;
		} while (terminator != 13);
		System.out.println("Bytes: " + bytes);
		//Must be equal to dbf.getHeaderLength();
		if (bytes != dbf.getHeaderLength())
		    System.out.println("Error fatal, algo estÃ¡ mal");
		size = (size * dbf.getRecNumber()) + dbf.getRecNumber() + dbf.getHeaderLength();
		//System.out.println("Total bytes of file: " + size);
		//System.out.println("Total bytes of file in disk: " + archivodbf.length());
		//End of Field Descriptor
		for (int i = 0; i < dbf.getRecNumber(); i++) {
		    //System.out.println("Registro N. " + i);
		    byte status = readByte(dis);
		    if (status == 0x2A) {
			//System.out.println("borrado");
		    } else {
			//System.out.println("no borrado");
		    }
		    for (int j = 0; j < fields.size(); j++) {
			DBFFieldDescriptor field = (DBFFieldDescriptor)fields.elementAt(j);
			//System.out.println("Columna " + field.getFieldName());
			int position = bytes;
			String campo = "";
			while (bytes < position + byte2int(field.getFieldLength())) {
			    campo += (char)readByte(dis);
			}
			campo = campo.trim();
			//System.out.println("Valor: " + campo);
		    }
		}
		//System.out.println("Bytes read: " + bytes);
	    } catch (FileNotFoundException ex) {
		ex.printStackTrace();
	    } catch (IOException ex) {
		ex.printStackTrace();
	    }
	} else {
	    //Mensaje de error, archivo no legible
	}
	return _polys;
    }

    private byte readByte(DataInputStream is) throws IOException {
	bytes += 1;
	return is.readByte();
    }

    private double readDoubleLittleEndian(DataInputStream is) throws IOException {
	long accum = 0;
	bytes += 8;
	for (int shiftBy = 0; shiftBy < 64; shiftBy += 8) {
	    accum |= ((long)is.readByte() & 0xff) << shiftBy;
	}
	return Double.longBitsToDouble(accum);
    }

    private void writeDoubleLittleEndian(DataOutputStream os, double _num) throws IOException {
	long accum = Double.doubleToLongBits(_num);
	os.writeByte((byte)(((long)accum & (0xffffffff)) >> 0));
	os.writeByte((byte)(((long)accum & (0xffffffff)) >> 8));
	os.writeByte((byte)(((long)accum & (0xffffff00)) >> 16));
	os.writeByte((byte)(((long)accum & (0xffffff00)) >> 24));
	os.writeByte((byte)(((long)accum & (0xffff0000)) >> 32));
	os.writeByte((byte)(((long)accum & (0xffff0000)) >> 40));
	os.writeByte((byte)(((long)accum & (0xff000000)) >> 48));
	os.writeByte((byte)(((long)accum & (0xff000000)) >> 56));
    }

    private void writeIntLittleEndian(DataOutputStream _os, int _num) throws IOException {
	int accum = _num;
	_os.writeByte((byte)(((long)accum & (0xffffffff)) >> 0));
	_os.writeByte((byte)(((long)accum & (0xffffffff)) >> 8));
	_os.writeByte((byte)(((long)accum & (0xffffff00)) >> 16));
	_os.writeByte((byte)(((long)accum & (0xffffff00)) >> 24));
    }

    private int readIntLittleEndian(DataInputStream is) throws IOException {
	int accum = 0;
	bytes += 4;
	for (int shiftBy = 0; shiftBy < 32; shiftBy += 8) {
	    accum |= (is.readByte() & 0xff) << shiftBy;
	}
	return accum;
    }

    private short readShortLittleEndian(DataInputStream is) throws IOException {
	short accum = 0;
	bytes += 2;
	for (int shiftBy = 0; shiftBy < 16; shiftBy += 8) {
	    accum |= (is.readByte() & 0xff) << shiftBy;
	}
	return accum;
    }

    private int byte2int(byte _byte) {
	return ((_byte + 256) % 256);
    }

    private byte int2byte(int _int) {
	return ((byte)_int);
    }

    private int readInt(DataInputStream is) throws IOException {
	bytes += 4;
	return is.readInt();
    }

    public Vector getShapesFromSHPFile(DataInputStream _dis, ShapeFile _shp) throws IOException {
	Vector shapes = new Vector();
	switch (_shp.getShapeType()) {
	    case ShapeTypes.POLYGON :
		//SHPPolygon _polygon = new SHPPolygon();
		shapes = getPolygonsFromSHPFile(_dis, _shp);
		System.out.println("Archivo de Tipo POLYGON");
		break;
	    case ShapeTypes.POINT :
		//SHPPoint _point = new SHPPoint();
		shapes = getPointsFromSHPFile(_dis, _shp);
		System.out.println("Archivo de Tipo POINT");
		break;
	    default :
		System.err.println("Tipo de archivo no permitido");
		break;
	}
	return shapes;
    }

    public Vector getPolygonsFromSHPFile(DataInputStream _dis, ShapeFile _shp) throws IOException {
	Vector _polygons = new Vector();
	while (bytes < _shp.getFileLen()) {
	    //Start of Record Header
	    _shp.setRecNumber(readInt(_dis));
	    _shp.setRecLength(readInt(_dis) * 2);
	    System.out.println("recnumber: " + _shp.getRecNumber());
	    System.out.println("reclen: " + _shp.getRecLength());
	    SHPPolygon poly = new SHPPolygon();
	    int bytesact = bytes;
	    while (bytes < bytesact + _shp.getRecLength()) {
		//Start of Record Data
		poly.setShapeType(readIntLittleEndian(_dis));
		double[] box = new double[4];
		box[0] = readDoubleLittleEndian(_dis);
		box[1] = readDoubleLittleEndian(_dis);
		box[2] = readDoubleLittleEndian(_dis);
		box[3] = readDoubleLittleEndian(_dis);
		poly.setBox(box);
		poly.setNumParts(readIntLittleEndian(_dis));
		poly.setNumPoints(readIntLittleEndian(_dis));
		for (int i = 0; i < poly.getNumParts(); i++) {
		    poly.setPartPoints(i, readIntLittleEndian(_dis));
		}
		Point2D[] puntos = new Point2D.Double[poly.getNumPoints()];
		for (int j = 0; j < poly.getNumPoints(); j++) {
		    double x = readDoubleLittleEndian(_dis);
		    double y = readDoubleLittleEndian(_dis);
		    puntos[j] = new Point2D.Double(x, y);
		}
		poly.setPoints(puntos);
		//End of Record Data
		_polygons.add(poly);
	    }
	}
	return _polygons;
    }

    public Vector getPointsFromSHPFile(DataInputStream _dis, ShapeFile _shp) throws IOException {
	Vector _points = new Vector();
	while (bytes < _shp.getFileLen()) {
	    //Start of Record Header
	    _shp.setRecNumber(readInt(_dis));
	    _shp.setRecLength(readInt(_dis) * 2);
	    System.out.println("recnumber: " + _shp.getRecNumber());
	    System.out.println("reclen: " + _shp.getRecLength());
	    SHPPoint point = new SHPPoint();
	    int bytesact = bytes;
	    while (bytes < bytesact + _shp.getRecLength()) {
		//Start of Record Data
		point.setShapeType(readIntLittleEndian(_dis));
		double[] box = new double[4];
		point.setX(readDoubleLittleEndian(_dis));
		point.setY(readDoubleLittleEndian(_dis));
		//End of Record Data
		_points.add(point);
	    }
	}
	return _points;
    }

    public int getShapeType() {
	return shapeType;
    }

}
