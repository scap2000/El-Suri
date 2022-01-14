package org.digitall.common.filemanager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;

import java.util.Vector;

import org.digitall.common.filemanager.LocalFile;
import org.digitall.common.filemanager.RemoteFile;
import org.digitall.lib.sql.LibSQL;

//

public class FileManServerThread extends Thread {

    private Socket clientSocket = null;
    private ObjectInputStream objectInputStream = null;
    private ObjectOutputStream objectOutputStream = null;
    boolean connected = false;
    private byte[] buffer;
    public static final int BUFFER_SIZE = 1024 * 50;

    public FileManServerThread(Socket _clientSocket, Vector _userList) {
	buffer = new byte[BUFFER_SIZE];
	clientSocket = _clientSocket;
	//in.lista.addElement(this);
	//in.cargarlista();
	//System.out.println("cliente agregado: " + this);
    }

    public void run() {
	try {
	    objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
	    connected = true;
	} catch (IOException x) {
	    x.printStackTrace();
	    connected = false;
	}
	String command;
	int status = 0;
	//(0: inicial; 1: from ok; 2: to ok; 3: msg ok);
	//userName = tryToGetUserName();
	String origUser = "";
	String destUser = "";
	String message = "";
	while (connected) {
	    try {
		System.out.println("Waiting for OBJECT!:");
		Object object = objectInputStream.readObject();
		System.out.println("Object received, class: " + object.getClass().getName());
		if (File.class.isInstance(object)) {
		    //FILE SUBMITTED!;
		    LocalFile file = (LocalFile)object;
		    System.out.println("FILE SUBMITTED: " + file);
		    getFile(file);
		} else if (RemoteFile.class.isInstance(object)) {
		    //FILE RETRIEVED!;
		    RemoteFile file = (RemoteFile)object;
		    System.out.println("FILE RETRIEVED: " + file.getName());
		    putFile(file);
		}
	    } catch (ClassNotFoundException e) {
		e.printStackTrace();
		continue;
	    } catch (IOException e) {
		System.out.println("Connection closed");
		connected = false;
		break;
	    }
	}
	try {
	    clientSocket.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private boolean getFile(LocalFile _file) {
	try {
	    //SQL START TRANSFER!
	    System.out.println("SQL START GET TRANSFER");
	    String params = "" + _file.getIDTransfer();
	    if (LibSQL.getBoolean("admin.starttransfer", params)) {
		//Download from remote host
		BufferedInputStream in = new BufferedInputStream(clientSocket.getInputStream());
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(FileManConfig.REMOTE_PATH + _file.getName()));
		int len = 0;
		int total = 0;
		while ((len = in.read(buffer)) > 0) {
		    out.write(buffer, 0, len);
		    total += len;
		    System.out.println("Total bytes received: " + total);
		}
		in.close();
		out.close();
		System.out.println("Receiving Done!");
		//SQL END COMPLETE TRANSFER!
		params = _file.getIDTransfer() + ", " + _file.length();
		_file.setIdFile(LibSQL.getInt("admin.addlocalfile", params));
		return (_file.getIdFile() == -1 ? false : true);
	    } else {
		System.out.println("Transfer not started");
		return false;
	    }
	} catch (FileNotFoundException e) {
	    //SQL ERROR TRANSFER
	    e.printStackTrace();
	    return false;
	} catch (IOException e) {
	    //SQL ERROR TRANSFER
	    e.printStackTrace();
	    return false;
	}
    }

    private boolean putFile(RemoteFile _remoteFile) {
	try {
	    System.out.println("SQL START PUT TRANSFER");
	    String params = "" + _remoteFile.getIDTransfer();
	    System.out.println("admin.starttransfer(" + params + ")");
	    if (LibSQL.getBoolean("admin.starttransfer", params)) {
		//Upload to remote host
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(FileManConfig.REMOTE_PATH + _remoteFile.getName()));
		BufferedOutputStream out = new BufferedOutputStream(clientSocket.getOutputStream());
		int len = 0;
		int total = 0;
		while ((len = in.read(buffer)) > 0) {
		    out.write(buffer, 0, len);
		    total += len;
		    System.out.println("Total bytes sent: " + total);
		}
		in.close();
		out.close();
		System.out.println("Uploading done!");
		return true;
	    } else {
		System.out.println("Transfer not started");
		return false;
	    }
	} catch (FileNotFoundException e) {
	    //e.printStackTrace();
	    return false;
	} catch (IOException e) {
	    //e.printStackTrace();
	    return false;
	}
    }

}
