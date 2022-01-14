package org.digitall.apps.filemanager;

import java.awt.Cursor;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import org.digitall.common.filemanager.RemoteFile;

//

public class LoadRemoteFileList extends Thread {

    private RemoteFileList list;
    private int currentDirectory;
    private Vector listData;

    public LoadRemoteFileList(RemoteFileList _list) {
	list = _list;
	listData = new Vector();
	list.setListData(listData);
    }

    public void run() {
	//list.setEnabled(false);
	Cursor prevCursor = list.getCursor();
	list.setCursor(new Cursor(Cursor.WAIT_CURSOR));
	ResultSet fileSet = org.digitall.lib.sql.LibSQL.exQuery("SELECT * FROM admin.virtual_filesystem_files where iddirectory = 0" + currentDirectory + " AND estado != '*' ORDER BY name");
	Vector fileList = new Vector();
	try {
	    while (fileSet.next()) {
		RemoteFile _file = new RemoteFile();
		_file.setName(fileSet.getString("name"));
		_file.setLength(fileSet.getLong("size"));
		_file.setIdDirectory(currentDirectory);
		fileList.add(_file);
		listData.add(_file.getName());
	    }
	    list.setFileList(fileList);
	    list.setListData(listData);
	} catch (SQLException x) {
	    x.printStackTrace();
	}
	list.setEnabled(true);
	list.setCursor(prevCursor);
    }

    public void setCurrentDirectory(int _currentDirectory) {
	currentDirectory = _currentDirectory;
	start();
    }

}
