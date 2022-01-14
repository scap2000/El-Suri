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
