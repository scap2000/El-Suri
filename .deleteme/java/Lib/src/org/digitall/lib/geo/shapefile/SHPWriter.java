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
 * SHPWriter.java
 *
 * */
package org.digitall.lib.geo.shapefile;

import java.awt.geom.Point2D;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DecimalFormat;

import java.util.Calendar;
import java.util.Vector;

import org.digitall.lib.sql.LibSQL;

public class SHPWriter {

    DecimalFormat df = new DecimalFormat("0.00000000");
    //    ShapeFile shp = new ShapeFile();
    Vector Poligonos = new Vector();
    Vector Records = new Vector();
    Vector Descriptors = new Vector();
    int bytes = 0;
    int polys = 0;

    public SHPWriter() {
	Poligonos = getPolyFromSQL();
	write();
    }

    public Vector getPolyFromSQL() {
	try {
	    String Data = "the_geom from minas";
	    Data = Data.trim();
	    String geometry = Data.substring(0, Data.indexOf(" ")).trim();
	    Data = Data.substring(Data.indexOf(" ")).trim();
	    String Qwhere = "";
	    String Query = "select mapkey, AsText(" + geometry + ") as the_geom,npoints(" + geometry + ") " + Data + Qwhere;
	    ResultSet count = LibSQL.exQuery("select count(*) from (" + Query + ") as foo");
	    count.next();
	    ResultSet Polygons = LibSQL.exQuery(Query);
	    DBFFieldDescriptor fieldDescriptor = new DBFFieldDescriptor();
	    initFieldDescriptor(fieldDescriptor, "MAPKEY", (byte)0x043, (byte)128, (byte)0);
	    fieldDescriptor.setFieldName("MAPKEY");
	    fieldDescriptor.setFieldType((byte)0x043);
	    //"C" o 67!! = Character
	    Descriptors.add(fieldDescriptor);
	    while (Polygons.next()) {
		/**PolÃ­gono*/
		SHPPolygon _poly = new SHPPolygon();
		int fNumPoints = Polygons.getInt("npoints");
		String Poly = Polygons.getString("the_geom");
		Poly = Poly.substring(Poly.indexOf("(((") + 3, Poly.length() - 3);
		//Si es polÃ­gono cerrado, por ejemplo Manzana o Parcela
		Point2D[] _point = new Point2D[fNumPoints];
		for (int i = 0; i < fNumPoints - 1; i++) {
		    //X
		    //Y
		    _point[i] = new Point2D.Double(Double.parseDouble(Poly.substring(0, Poly.indexOf(" ")).trim()), Double.parseDouble(Poly.substring(Poly.indexOf(" "), Poly.indexOf(",")).trim()));
		    Poly = Poly.substring(Poly.indexOf(",") + 1, Poly.length());
		}
		_point[fNumPoints - 1] = new Point2D.Double(Double.parseDouble(Poly.substring(0, Poly.indexOf(" ")).trim()), Double.parseDouble(Poly.substring(Poly.indexOf(" "), Poly.length()).trim()));
		_poly.setNumPoints(fNumPoints);
		_poly.setPoints(_point);
		_poly.setNumParts(1);
		_poly.setShapeType(5);
		Poligonos.add(_poly);
		Records.add(Polygons.getString("mapkey"));
		System.out.println(_poly.getSQLString("", "minas"));
		System.out.println("Record: " + Polygons.getString("mapkey"));
		polys++;
	    }
	} catch (SQLException x) {
	    x.printStackTrace();
	}
	return Poligonos;
    }

    public void initShapeFile(ShapeFile _shpFile) {
	_shpFile.setFileCode(9994);
	_shpFile.setUnused4(0);
	_shpFile.setUnused8(0);
	_shpFile.setUnused12(0);
	_shpFile.setUnused16(0);
	_shpFile.setUnused20(0);
	_shpFile.setFileLen(0);
	//Como carajo lo calculo???
	_shpFile.setVersion(1000);
	_shpFile.setShapeType(5);
	double minx = 0.0;
	double miny = 0.0;
	double maxx = 0.0;
	double maxy = 0.0;
	if (Poligonos.size() > 0) {
	    SHPPolygon _poly = (SHPPolygon)Poligonos.elementAt(0);
	    minx = _poly.getPoints()[0].getX();
	    miny = _poly.getPoints()[0].getY();
	    maxx = _poly.getPoints()[0].getX();
	    maxy = _poly.getPoints()[0].getY();
	    for (int i = 0; i < Poligonos.size(); i++) {
		_poly = (SHPPolygon)Poligonos.elementAt(i);
		for (int j = 1; j < _poly.getNumPoints(); j++) {
		    if (_poly.getPoints()[j].getX() < minx) {
			minx = _poly.getPoints()[j].getX();
		    }
		    if (_poly.getPoints()[j].getX() > maxx) {
			maxx = _poly.getPoints()[j].getX();
		    }
		    if (_poly.getPoints()[j].getY() < miny) {
			miny = _poly.getPoints()[j].getY();
		    }
		    if (_poly.getPoints()[j].getY() > maxy) {
			maxy = _poly.getPoints()[j].getY();
		    }
		}
	    }
	}
	_shpFile.setXmin(minx);
	_shpFile.setYmin(miny);
	_shpFile.setXmax(maxx);
	_shpFile.setYmax(maxy);
	_shpFile.setZmin(0.0);
	_shpFile.setZmax(0.0);
	_shpFile.setMmin(0.0);
	_shpFile.setMmax(0.0);
    }

    public void initDBaseFile(DBaseFile _dbFile) {
	Calendar cal = Calendar.getInstance();
	int year = cal.get(1);
	year = year % 2000;
	_dbFile.setFileType((byte)3);
	_dbFile.setYear((byte)year);
	_dbFile.setMonth((byte)(cal.get(2) + 1));
	_dbFile.setDay((byte)cal.get(5));
	_dbFile.setRecNumber(Records.size());
	short cantFields = 1;
	//Mejorar
	_dbFile.setHeaderLength((short)(cantFields * 32 + 32 + 1));
	short recLength = 129;
	//Mejorar
	_dbFile.setRecLength(recLength);
	_dbFile.setUnused12(0);
	_dbFile.setUnused20(0);
	_dbFile.setUnused28(0);
	/*DBF FileType: 3
DBF Year: 95
DBF Month: 7
DBF Day: 26
Rec Number: 65
Header Length: 65
Rec Length: 129
Bytes: 32*/
	// dbase.setHeaderLength();
	// dbase.setRecLength();
    }

    public void initFieldDescriptor(DBFFieldDescriptor _fDescriptor, String _fName, byte _fType, byte _fLength, byte _fDecLength) {
	_fDescriptor.setFieldName(_fName);
	_fDescriptor.setFieldType(_fType);
	_fDescriptor.setFieldLength(_fLength);
	_fDescriptor.setDecimalLength(_fDecLength);
	_fDescriptor.setAddress(0);
	_fDescriptor.setUnused18(0);
	_fDescriptor.setUnused26(0);
	_fDescriptor.setUnused30((short)0);
    }

    public void write() {
	String file = "/tmp/files/_minas";
	File archivoshp = new File(file + ".shp");
	File archivoshx = new File(file + ".shx");
	File archivodbf = new File(file + ".dbf");
	try {
	    //if (archivoshp.createNewFile() && archivoshx.createNewFile()) 
	    // {
	    //Trabajar con los archivos
	    DataOutputStream dos = new DataOutputStream(new FileOutputStream(archivoshp));
	    DataOutputStream dosidx = new DataOutputStream(new FileOutputStream(archivoshx));
	    ShapeFile shp = new ShapeFile();
	    initShapeFile(shp);
	    //Inicializo los valores por defecto
	    //FALTA CALCULAR fileLen()!!!
	    //Start of File Header (100 bytes)
	    writeInt(dos, dosidx, shp.getFileCode());
	    //Needs to be equal to 9994
	    writeInt(dos, dosidx, shp.getUnused4());
	    writeInt(dos, dosidx, shp.getUnused8());
	    writeInt(dos, dosidx, shp.getUnused12());
	    writeInt(dos, dosidx, shp.getUnused16());
	    writeInt(dos, dosidx, shp.getUnused20());
	    int tamanio = calcTamanio(Poligonos, shp.getShapeType());
	    System.out.println("Tamanio: " + tamanio);
	    int tamanioidx = (Poligonos.size() * 8) / 2 + 50;
	    System.out.println("Tamanio IDX: " + tamanioidx);
	    writeInt(dos, dosidx, tamanio, tamanioidx);
	    //Como lo calculo???
	    writeIntLittleEndian(dos, dosidx, shp.getVersion());
	    //Needs to be equal to 1000
	    writeIntLittleEndian(dos, dosidx, shp.getShapeType());
	    //We only work with Type 5
	    writeDoubleLittleEndian(dos, dosidx, shp.getXmin());
	    writeDoubleLittleEndian(dos, dosidx, shp.getYmin());
	    writeDoubleLittleEndian(dos, dosidx, shp.getXmax());
	    writeDoubleLittleEndian(dos, dosidx, shp.getYmax());
	    writeDoubleLittleEndian(dos, dosidx, shp.getZmin());
	    writeDoubleLittleEndian(dos, dosidx, shp.getZmax());
	    writeDoubleLittleEndian(dos, dosidx, shp.getMmin());
	    writeDoubleLittleEndian(dos, dosidx, shp.getMmax());
	    System.out.println("Xmin: " + shp.getXmin());
	    System.out.println("Ymin: " + shp.getYmin());
	    System.out.println("Xmax: " + shp.getXmax());
	    System.out.println("Ymax: " + shp.getYmax());
	    //100 bytes;
	    System.out.println("Bytes: " + bytes);
	    //End of File Header
	    //Start of File Data
	    int recNumber = 0;
	    int recLengthidx = 50;
	    while (recNumber < Poligonos.size()) {
		SHPPolygon poly = (SHPPolygon)Poligonos.elementAt(recNumber);
		//Start of Record Header
		recNumber++;
		//Siempre empieza en 1
		writeInt(dos, recNumber);
		System.out.println("recnumber: " + recNumber);
		int recLength = (44 + poly.getNumParts() * 4 + poly.getNumPoints() * 16) / 2;
		writeInt(dos, recLength);
		writeInt(dosidx, recLengthidx);
		System.out.println("reclen: " + recLength);
		System.out.println("reclenidx: " + recLengthidx);
		System.out.println("OffsetIdx: " + (44 + poly.getNumParts() * 4 + poly.getNumPoints() * 16) / 2);
		recLengthidx += (52 + poly.getNumParts() * 4 + poly.getNumPoints() * 16) / 2;
		writeInt(dosidx, (44 + poly.getNumParts() * 4 + poly.getNumPoints() * 16) / 2);
		//                    while (bytes < bytesact + recLength) {
		//Start of Record Data
		writeIntLittleEndian(dos, poly.getShapeType());
		double[] box = poly.getBox();
		writeDoubleLittleEndian(dos, box[0]);
		//puede ser writeLong
		writeDoubleLittleEndian(dos, box[1]);
		//puede ser writeLong
		writeDoubleLittleEndian(dos, box[2]);
		//puede ser writeLong
		writeDoubleLittleEndian(dos, box[3]);
		//puede ser writeLong
		writeIntLittleEndian(dos, poly.getNumParts());
		writeIntLittleEndian(dos, poly.getNumPoints());
		//System.out.println("NumParts: " + poly.getNumParts());
		for (int i = 0; i < poly.getNumParts(); i++) {
		    writeIntLittleEndian(dos, poly.getPartPoints(i));
		}
		Point2D[] puntos = new Point2D.Double[poly.getNumPoints()];
		for (int j = 0; j < poly.getNumPoints(); j++) {
		    writeDoubleLittleEndian(dos, poly.getPoints()[j].getX());
		    //puede ser writeLong
		    writeDoubleLittleEndian(dos, poly.getPoints()[j].getY());
		    //puede ser writeLong
		    //System.out.println("Punto[" + j + "]: " + poly.getPoints()[j]);
		}
		//End of Record Data
		//                    }
	    }
	    //} else {
	    //Mensaje de error, archivos no escribibles
	    //     Proced.Mensaje("Archivos no escribibles", "Error");
	    //}
	    DataOutputStream dosdbf = new DataOutputStream(new FileOutputStream(archivodbf));
	    DBaseFile dbf = new DBaseFile();
	    initDBaseFile(dbf);
	    //Inicializo los valores por defecto
	    bytes = 0;
	    //Start of DBF Header
	    writeByte(dosdbf, dbf.getFileType());
	    writeByte(dosdbf, dbf.getYear());
	    writeByte(dosdbf, dbf.getMonth());
	    writeByte(dosdbf, dbf.getDay());
	    writeIntLittleEndian(dosdbf, dbf.getRecNumber());
	    writeShortLittleEndian(dosdbf, dbf.getHeaderLength());
	    //REVISAR!!!
	    writeShortLittleEndian(dosdbf, dbf.getRecLength());
	    System.out.println("DBF FileType: " + dbf.getFileType());
	    System.out.println("DBF Year: " + dbf.getYear());
	    System.out.println("DBF Month: " + dbf.getMonth());
	    System.out.println("DBF Day: " + dbf.getDay());
	    System.out.println("Rec Number: " + dbf.getRecNumber());
	    System.out.println("Header Length: " + dbf.getHeaderLength());
	    System.out.println("Rec Length: " + dbf.getRecLength());
	    writeDoubleLittleEndian(dosdbf, dbf.getUnused12());
	    writeDoubleLittleEndian(dosdbf, dbf.getUnused20());
	    writeIntLittleEndian(dosdbf, dbf.getUnused28());
	    System.out.println("Bytes: " + bytes);
	    //End of DBF Header
	    //Start of Field Descriptor
	    for (int i = 0; i < Descriptors.size(); i++) {
		DBFFieldDescriptor field = (DBFFieldDescriptor)Descriptors.elementAt(i);
		String fieldName = field.getFieldName();
		char[] fName = fieldName.toCharArray();
		for (int j = 0; j < fName.length; j++) {
		    System.out.println("Char [" + j + "]: " + fName[j]);
		    writeByte(dosdbf, (byte)fName[j]);
		}
		for (int j = fieldName.length(); j < 11; j++) {
		    System.out.println("Char [" + j + "]: " + 0x00);
		    writeByte(dosdbf, (byte)0x00);
		}
		writeByte(dosdbf, field.getFieldType());
		System.out.println("Field Type: " + (char)field.getFieldType());
		writeInt(dosdbf, field.getAddress());
		writeByte(dosdbf, field.getFieldLength());
		writeByte(dosdbf, field.getDecimalLength());
		writeDouble(dosdbf, field.getUnused18());
		writeInt(dosdbf, field.getUnused26());
		writeShort(dosdbf, field.getUnused30());
		/** CAPAZ QUE TERMINA DE ESTA FORMA EL SECTOR DE LOS ENCABEZADOS
                if (i == Descriptors.size()-1) {
                    writeByte(dosdbf, (byte)0x0D); //terminator = 13 = 0Dh;
                } else {
                    writeByte(dosdbf, (byte)0x00); //terminator = 13 = 0Dh;
                }
                */
	    }
	    /** CAPAZ QUE TERMINA DE ESTA OTRA!!! FORMA EL SECTOR DE LOS ENCABEZADOS*/
	    writeByte(dosdbf, (byte)0x0D);
	    //terminator = 13 = 0Dh;
	    System.out.println("Bytes: " + bytes);
	    //Must be equal to dbf.getHeaderLength();
	    if (bytes != dbf.getHeaderLength())
		System.out.println("Error fatal, algo estÃ¡ mal");
	    /*size = (size * dbf.getRecNumber()) + dbf.getRecNumber() + dbf.getHeaderLength();
            System.out.println("Total bytes of file: " + size);
            System.out.println("Total bytes of file in disk: " + archivodbf.length());*/
	    //End of Field Descriptor
	    //Start of Data
	    for (int i = 0; i < dbf.getRecNumber(); i++) {
		System.out.println("Registro N. " + i);
		writeByte(dosdbf, (byte)0x20);
		//0x2A si el registro estÃ¡ borrado
		for (int j = 0; j < Descriptors.size(); j++) {
		    DBFFieldDescriptor field = (DBFFieldDescriptor)Descriptors.elementAt(j);
		    System.out.println("Columna " + field.getFieldName());
		    char[] campo = Records.elementAt(i).toString().toCharArray();
		    //!!Porque tengo un solo campo por ahora!!!
		    int position = bytes;
		    while (bytes < position + byte2int(field.getFieldLength())) {
			if ((bytes - position) < campo.length) {
			    System.out.println("campo[" + (bytes - position) + "]: " + campo[bytes - position]);
			    writeByte(dosdbf, (byte)campo[bytes - position]);
			} else {
			    writeByte(dosdbf, (byte)0x20);
			}
		    }
		    System.out.println("Valor: " + campo);
		}
	    }
	    System.out.println("Bytes written: " + bytes);
	} catch (FileNotFoundException ex) {
	    ex.printStackTrace();
	} catch (IOException ex) {
	    ex.printStackTrace();
	}
    }

    private void writeInt(DataOutputStream os, int _int) throws IOException {
	os.writeInt(_int);
	bytes += 4;
    }

    private void writeInt(DataOutputStream os, DataOutputStream osx, int _int) throws IOException {
	os.writeInt(_int);
	osx.writeInt(_int);
	bytes += 4;
    }

    private void writeInt(DataOutputStream os, DataOutputStream osx, int _int, int _intx) throws IOException {
	os.writeInt(_int);
	osx.writeInt(_intx);
	bytes += 4;
    }

    private void writeByte(DataOutputStream os, byte _num) throws IOException {
	os.writeByte(_num);
	bytes++;
    }

    private void writeIntLittleEndian(DataOutputStream os, int _num) throws IOException {
	os.writeByte((byte)(((long)_num & (0xffffffff)) >> 0));
	bytes++;
	os.writeByte((byte)(((long)_num & (0xffffffff)) >> 8));
	bytes++;
	os.writeByte((byte)(((long)_num & (0xffffff00)) >> 16));
	bytes++;
	os.writeByte((byte)(((long)_num & (0xffffff00)) >> 24));
	bytes++;
    }

    private void writeShortLittleEndian(DataOutputStream os, short _num) throws IOException {
	os.writeByte((byte)(((long)_num & (0xffffffff)) >> 0));
	bytes++;
	os.writeByte((byte)(((long)_num & (0xffffffff)) >> 8));
	bytes++;
    }

    private void writeIntLittleEndian(DataOutputStream os, DataOutputStream osx, int _num) throws IOException {
	os.writeByte((byte)(((long)_num & (0xffffffff)) >> 0));
	osx.writeByte((byte)(((long)_num & (0xffffffff)) >> 0));
	bytes++;
	os.writeByte((byte)(((long)_num & (0xffffffff)) >> 8));
	osx.writeByte((byte)(((long)_num & (0xffffffff)) >> 8));
	bytes++;
	os.writeByte((byte)(((long)_num & (0xffffff00)) >> 16));
	osx.writeByte((byte)(((long)_num & (0xffffff00)) >> 16));
	bytes++;
	os.writeByte((byte)(((long)_num & (0xffffff00)) >> 24));
	osx.writeByte((byte)(((long)_num & (0xffffff00)) >> 24));
	bytes++;
    }

    private void writeShort(DataOutputStream os, short _num) throws IOException {
	os.writeShort(_num);
	bytes += 2;
    }

    private void writeDouble(DataOutputStream os, double _num) throws IOException {
	os.writeDouble(_num);
	bytes += 8;
    }

    private void writeDoubleLittleEndian(DataOutputStream os, double _num) throws IOException {
	long accum = Double.doubleToLongBits(_num);
	os.writeByte((byte)(((long)accum & (0xffffffff)) >> 0));
	bytes++;
	os.writeByte((byte)(((long)accum & (0xffffffff)) >> 8));
	bytes++;
	os.writeByte((byte)(((long)accum & (0xffffff00)) >> 16));
	bytes++;
	os.writeByte((byte)(((long)accum & (0xffffff00)) >> 24));
	bytes++;
	os.writeByte((byte)(((long)accum & (0xffff0000)) >> 32));
	bytes++;
	os.writeByte((byte)(((long)accum & (0xffff0000)) >> 40));
	bytes++;
	os.writeByte((byte)(((long)accum & (0xff000000)) >> 48));
	bytes++;
	os.writeByte((byte)(((long)accum & (0xff000000)) >> 56));
	bytes++;
    }

    private void writeDoubleLittleEndian(DataOutputStream os, DataOutputStream osx, double _num) throws IOException {
	long accum = Double.doubleToLongBits(_num);
	os.writeByte((byte)(((long)accum & (0xffffffff)) >> 0));
	osx.writeByte((byte)(((long)accum & (0xffffffff)) >> 0));
	bytes++;
	os.writeByte((byte)(((long)accum & (0xffffffff)) >> 8));
	osx.writeByte((byte)(((long)accum & (0xffffffff)) >> 8));
	bytes++;
	os.writeByte((byte)(((long)accum & (0xffffff00)) >> 16));
	osx.writeByte((byte)(((long)accum & (0xffffff00)) >> 16));
	bytes++;
	os.writeByte((byte)(((long)accum & (0xffffff00)) >> 24));
	osx.writeByte((byte)(((long)accum & (0xffffff00)) >> 24));
	bytes++;
	os.writeByte((byte)(((long)accum & (0xffff0000)) >> 32));
	osx.writeByte((byte)(((long)accum & (0xffff0000)) >> 32));
	bytes++;
	os.writeByte((byte)(((long)accum & (0xffff0000)) >> 40));
	osx.writeByte((byte)(((long)accum & (0xffff0000)) >> 40));
	bytes++;
	os.writeByte((byte)(((long)accum & (0xff000000)) >> 48));
	osx.writeByte((byte)(((long)accum & (0xff000000)) >> 48));
	bytes++;
	os.writeByte((byte)(((long)accum & (0xff000000)) >> 56));
	osx.writeByte((byte)(((long)accum & (0xff000000)) >> 56));
	bytes++;
    }

    private int calcTamanio(Vector _polys, int _shapetype) {
	int j = 0;
	switch (_shapetype) {
	    case 5 :
		for (int k = 0; k < _polys.size(); k++) {
		    SHPPolygon _poly = (SHPPolygon)_polys.elementAt(k);
		    j += (52 + _poly.getNumPoints() * 16 + _poly.getNumParts() * 4) / 2;
		}
		j += 50;
		break;
	    default :
		System.err.println("Error, tipo de archivo no soportado");
		System.exit(-255);
		break;
	}
	return j;
    }

    private int byte2int(byte _byte) {
	return ((_byte + 256) % 256);
    }

}
