package org.digitall.apps.filemanager;

import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.JList;

import org.digitall.common.filemanager.RemoteFile;

public class RemoteFileList extends JList {

    private int currentDirectory = -1;
    private Vector listData = new Vector();

    public RemoteFileList() {
	super();
    }

    public RemoteFile[] getSelectedFiles() {
	int[] selectedIndices = getSelectedIndices();
	RemoteFile[] selectedFiles = new RemoteFile[selectedIndices.length];
	for (int i = 0; i < selectedIndices.length; i++) {
	    selectedFiles[i] = (RemoteFile)listData.elementAt(selectedIndices[i]);
	}
	return selectedFiles;
    }

    public String getToolTipText(MouseEvent evt) {
	// Get item index
	int index = locationToIndex(evt.getPoint());
	// Get item
	//Object item = getModel().getElementAt(index);
	// Return the tool tip text
	try {
	    RemoteFile f = (RemoteFile)listData.elementAt(index);
	    long length = f.length();
	    String deleteable = f.isDeleteable() ? "deleteable, " : "not deleteable, ";
	    String application = f.getApplication() + ", ";
	    String createdBy = "created by: " + f.getCreatedBy();
	    String lastModified = "last modified: " + f.getDateTimeLastModified();
	    return String.valueOf(length) + " bytes - " + deleteable + application + createdBy + lastModified;
	} catch (ArrayIndexOutOfBoundsException e) {
	    return null;
	}
    }

    public void setListData(Vector _list) {
	super.setListData(_list);
    }

    public void setFileList(Vector _list) {
	listData = _list;
    }

    public void setCurrentDirectory(int _currentDirectory) {
	currentDirectory = _currentDirectory;
	LoadRemoteFileList loadRemoteFileList = new LoadRemoteFileList(this);
	loadRemoteFileList.setCurrentDirectory(currentDirectory);
    }

    public int getCurrentDirectory() {
	return currentDirectory;
    }

}
