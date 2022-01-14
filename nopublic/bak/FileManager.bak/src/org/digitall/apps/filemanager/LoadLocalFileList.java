package org.digitall.apps.filemanager;

import java.awt.Cursor;

import java.io.File;

import java.util.Arrays;
import java.util.Vector;

import org.digitall.common.filemanager.LocalFile;

public class LoadLocalFileList extends Thread {

    private LocalFileList list;
    private String currentDirectory;
    private Vector listData;

    public LoadLocalFileList(LocalFileList _list) {
	list = _list;
	listData = new Vector();
	list.setListData(listData);
    }

    public void run() {
	//list.setEnabled(false);
	Cursor prevCursor = list.getCursor();
	list.setCursor(new Cursor(Cursor.WAIT_CURSOR));
	File[] fileList = (new File(currentDirectory)).listFiles();
	Vector vFileList = new Vector();
	if (fileList != null) {
	    Arrays.sort(fileList);
	    for (int i = 0; i < fileList.length; i++) {
		if (!fileList[i].isDirectory()) {
		    listData.add(fileList[i].getName());
		    vFileList.add(new LocalFile(fileList[i].getName(), currentDirectory));
		}
	    }
	}
	list.setFileList(vFileList);
	list.setListData(listData);
	list.setEnabled(true);
	list.setCursor(prevCursor);
    }

    public void setCurrentDirectory(String _currentDirectory) {
	currentDirectory = _currentDirectory;
	start();
    }

}
