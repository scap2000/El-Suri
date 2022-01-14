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
