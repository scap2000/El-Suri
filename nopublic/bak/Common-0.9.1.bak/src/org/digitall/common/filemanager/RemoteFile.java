package org.digitall.common.filemanager;

import java.io.Serializable;

import java.util.Date;

public class RemoteFile implements Serializable {

    private int idTransfer = -1;
    private int idFile = -1;
    private int idDirectory = -1;
    private String name = "";
    private String destinationDir = "";
    private String createdBy = "";
    private Date dateTimeCreated = null;
    private boolean deleteable = false;
    private long length = -1;
    private String mimeType = "";
    private Date dateTimeLastModified = null;
    private String comment = "";
    private String application = "";

    public RemoteFile() {
    }

    public RemoteFile(String _name) {
	name = _name;
    }

    public void setIdFile(int _idFile) {
	idFile = _idFile;
    }

    public int getIdFile() {
	return idFile;
    }

    public void setIdDirectory(int _idDirectory) {
	idDirectory = _idDirectory;
    }

    public int getIdDirectory() {
	return idDirectory;
    }

    public void setName(String _name) {
	name = _name;
    }

    public String getName() {
	return name;
    }

    public void setDestinationDir(String _destinationDir) {
	destinationDir = _destinationDir;
    }

    public String getDestinationDir() {
	return destinationDir;
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

    public void setLength(long _length) {
	length = _length;
    }

    public long length() {
	return length;
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
