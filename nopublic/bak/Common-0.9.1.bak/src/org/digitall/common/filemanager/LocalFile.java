package org.digitall.common.filemanager;

import java.io.File;

import java.util.Date;

public class LocalFile extends File {

    private int idTransfer = -1;
    private int idFile = -1;
    private int destinationDir = -1;
    private String name = "";
    private String directory = "";
    private String createdBy = "";
    private Date dateTimeCreated = null;
    private boolean deleteable = false;
    private long size = -1;
    private String mimeType = "";
    private Date dateTimeLastModified = null;
    private String comment = "";
    private String application = "";

    public LocalFile(String _name, String _directory) {
	super(_directory + _name);
	System.out.println("LEN: " + super.length());
	name = _name;
	directory = _directory;
    }

    public void setIdFile(int _idFile) {
	idFile = _idFile;
    }

    public int getIdFile() {
	return idFile;
    }

    public void setDestinationDir(int _idDirectory) {
	destinationDir = _idDirectory;
    }

    public int getDestinationDir() {
	return destinationDir;
    }

    public void setName(String _name) {
	name = _name;
    }

    public String getName() {
	return name;
    }

    public void setDirectory(String _directory) {
	directory = _directory;
    }

    public String getDirectory() {
	return directory;
    }

    public void setCreatedBy(String _createdBy) {
	createdBy = _createdBy;
    }

    public String getCreatedBy() {
	return createdBy;
    }

    public void setDateTimeCreated(Date _dateTimeCreated) {
	dateTimeCreated = _dateTimeCreated;
    }

    public Date getDateTimeCreated() {
	return dateTimeCreated;
    }

    public void setDeleteable(boolean _deleteable) {
	deleteable = _deleteable;
    }

    public boolean isDeleteable() {
	return deleteable;
    }

    public void setSize(long _size) {
	size = _size;
    }

    public long getSize() {
	return size;
    }

    public void setMimeType(String _mimeType) {
	mimeType = _mimeType;
    }

    public String getMimeType() {
	return mimeType;
    }

    public void setDateTimeLastModified(Date _dateTimeLastModified) {
	dateTimeLastModified = _dateTimeLastModified;
    }

    public Date getDateTimeLastModified() {
	return dateTimeLastModified;
    }

    public void setComment(String _comment) {
	comment = _comment;
    }

    public String getComment() {
	return comment;
    }

    public void setApplication(String _application) {
	application = _application;
    }

    public String getApplication() {
	return application;
    }

    public void setIDTransfer(int _idTransfer) {
	idTransfer = _idTransfer;
    }

    public int getIDTransfer() {
	return idTransfer;
    }

}
