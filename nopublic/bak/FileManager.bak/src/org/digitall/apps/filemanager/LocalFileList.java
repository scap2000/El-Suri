package org.digitall.apps.filemanager;

import java.awt.event.MouseEvent;

import java.util.Date;
import java.util.Vector;

import javax.swing.JList;

import org.digitall.common.filemanager.LocalFile;

public class LocalFileList extends JList {

    private String currentDirectory = "";
    private Vector listData = new Vector();

    public LocalFileList() {
	super();
    }

    public LocalFile[] getSelectedFiles() {
	int[] selectedIndices = getSelectedIndices();
	LocalFile[] selectedFiles = new LocalFile[selectedIndices.length];
	for (int i = 0; i < selectedIndices.length; i++) {
	    selectedFiles[i] = (LocalFile)listData.elementAt(selectedIndices[i]);
	}
	return selectedFiles;
    }

    public String getToolTipText(MouseEvent evt) {
	// Get item index
	int index = locationToIndex(evt.getPoint());
	// Get item
	try {
	    //Object item = list.getModel().getElementAt(index);
	    // Return the tool tip text
	    LocalFile f = (LocalFile)listData.elementAt(index);
	    String readable = f.canRead() ? "readable, " : "not readable, ";
	    String writable = f.canWrite() ? "writable, " : "not writable, ";
	    String hidden = f.isHidden() ? "hidden, " : "not hidden, ";
	    String lastModified = "last modified: " + new Date(f.lastModified());
	    return String.valueOf(f.length()) + " bytes - " + readable + writable + hidden + lastModified;
	} catch (ArrayIndexOutOfBoundsException e) {
	    return null;
	}
    }

    public void setCurrentDirectory(String _currentDirectory) {
	currentDirectory = _currentDirectory;
	LoadLocalFileList loadLocalFileList = new LoadLocalFileList(this);
	loadLocalFileList.setCurrentDirectory(currentDirectory);
    }

    public String getCurrentDirectory() {
	return currentDirectory;
    }

    public void setListData(Vector _list) {
	super.setListData(_list);
    }

    public void setFileList(Vector _list) {
	listData = _list;
    }

}
