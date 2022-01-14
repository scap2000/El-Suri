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
