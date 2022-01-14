/**
 LIMITACIÓN DE RESPONSABILIDAD - COPYRIGHT - [Español]
 ================================================================================
 El Suri - Entorno JAVA de Trabajo y Desarrollo para Gobierno Electrónico
 ================================================================================

 Información del Proyecto:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 por D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO es propiedad de
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) y
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los términos de la Licencia Pública General GNU publicada
 por la Fundación para el Software Libre, ya sea la versión 3
 de la Licencia, o (a su elección) cualquier versión posterior.

 Este programa se distribuye con la esperanza de que sea útil, pero
 SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita
 MERCANTIL o de APTITUD PARA UN PROPÓSITO DETERMINADO.
 Consulte los detalles de la Licencia Pública General GNU para obtener
 una información más detallada.

 Debería haber recibido una copia de la Licencia Pública General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
 El Suri - JAVA Management & Development Environment for Electronic Government
 =====================================================================================

 Project Info:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 by D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO is owned by
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) and
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/
/**
 * FileManTransferFileThread.java
 *
 * */
package org.digitall.apps.filemanager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.Socket;

import java.text.DecimalFormat;

import javax.swing.JProgressBar;

import org.digitall.common.filemanager.LocalFile;
import org.digitall.common.filemanager.RemoteFile;
import org.digitall.lib.sql.LibSQL;

//

public class FileManTransferFileThread extends Thread {

    private Socket fileSocket = null;
    private byte[] buffer;
    public static final int BUFFER_SIZE = 1448;
    //MTU
    //    public static final int BUFFER_SIZE = 1024 * 50;
    private LocalFile localFile;
    private RemoteFile remoteFile;
    private JProgressBar progressBar;
    private BufferedOutputStream out;
    private BufferedInputStream in;
    private int operation = 0;
    public static final int PUT = 1;
    public static final int GET = -1;
    private long size;
    //-1: GET; 1: PUT

    public FileManTransferFileThread(Socket _fileSocket, LocalFile _file, JProgressBar _progressBar) {
	//Upload to remote host
	buffer = new byte[BUFFER_SIZE];
	fileSocket = _fileSocket;
	localFile = _file;
	progressBar = _progressBar;
	operation = PUT;
	//System.out.println(fileSocket);
    }

    public FileManTransferFileThread(Socket _fileSocket, RemoteFile _file, JProgressBar _progressBar) {
	//Download from remote host
	buffer = new byte[BUFFER_SIZE];
	fileSocket = _fileSocket;
	remoteFile = _file;
	progressBar = _progressBar;
	operation = GET;
	//System.out.println(fileSocket);
    }

    public void run() {
	if (operation == PUT) {
	    try {
		System.out.println("SQL INITIATE PUT TRANSFER");
		//Tell to the DataBase that a transfer is initiated
		//SQL INITIATE TRANSFER
		if (localFile.getIDTransfer() != -1) {
		    progressBar.setEnabled(true);
		    progressBar.setStringPainted(true);
		    progressBar.setMaximum(100);
		    //Porcentual no?
		    progressBar.setValue(0);
		    int len = 0;
		    int total = 0;
		    size = localFile.length();
		    long start = System.currentTimeMillis();
		    long prev;
		    long middle = start;
		    while ((len = in.read(buffer)) > 0) {
			prev = System.currentTimeMillis();
			out.write(buffer, 0, len);
			middle = System.currentTimeMillis();
			double interval = (middle - start) / 1000;
			double speed = total / interval;
			String rate = new DecimalFormat("#0.00").format(speed / 1024) + " KB/s";
			total += len;
			double pbValue = 100.0 * ((double)total / (double)size);
			progressBar.setValue((int)pbValue);
			progressBar.setString((new DecimalFormat("#0.00").format(pbValue)) + "% at " + rate);
			progressBar.setToolTipText("File transferring: " + new DecimalFormat("#0.00").format(pbValue) + "% at " + rate);
		    }
		    in.close();
		    out.close();
		    //org.digitall.lib.components.Advisor.messageBox("File transferred", "OK");
		    long end = System.currentTimeMillis();
		    //double trate = total/((end-start)/1000);
		    //progressBar.setString("Uploading done at " + new DecimalFormat("#0.00").format(trate/1024) + " KB/s");
		    progressBar.setToolTipText("");
		    progressBar.setValue(0);
		    progressBar.setEnabled(false);
		} else {
		    org.digitall.lib.components.Advisor.messageBox("Couldn't negotiate transfer", "Error");
		}
	    } catch (FileNotFoundException e) {
		e.printStackTrace();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	} else if (operation == GET) {
	    try {
		//Tell to the DataBase that a transfer is initiated
		System.out.println("SQL INITIATE GET TRANSFER");
		//SQL INITIATE TRANSFER
		if (remoteFile.getIDTransfer() != -1) {
		    progressBar.setEnabled(true);
		    progressBar.setStringPainted(true);
		    progressBar.setMaximum(100);
		    //Porcentual no?
		    progressBar.setValue(0);
		    int len = 0;
		    int total = 0;
		    size = remoteFile.length();
		    long start = System.currentTimeMillis();
		    long prev;
		    long middle = start;
		    while ((len = in.read(buffer)) > 0) {
			prev = System.currentTimeMillis();
			out.write(buffer, 0, len);
			middle = System.currentTimeMillis();
			double interval = (middle - start) / 1000;
			double speed = total / interval;
			String rate = new DecimalFormat("#0.00").format(speed / 1024) + " KB/s";
			total += len;
			System.out.println("Total rec: " + total);
			double pbValue = 100.0 * ((double)total / (double)size);
			progressBar.setValue((int)pbValue);
			progressBar.setString((new DecimalFormat("#0.00").format(pbValue)) + "% at " + rate);
			progressBar.setToolTipText("File transferring: " + new DecimalFormat("#0.00").format(pbValue) + "% at " + rate);
		    }
		    in.close();
		    out.close();
		    long end = System.currentTimeMillis();
		    //double trate = total/((end-start)/1000);
		    //progressBar.setString("Uploading done at " + new DecimalFormat("#0.00").format(trate/1024) + " KB/s");
		    progressBar.setToolTipText("");
		    progressBar.setValue(0);
		    progressBar.setEnabled(false);
		    if (!LibSQL.getBoolean("admin.endtransfer", "" + remoteFile.getIDTransfer())) {
			org.digitall.lib.components.Advisor.messageBox("File NOT fully transferred", "Error");
		    }
		} else {
		    org.digitall.lib.components.Advisor.messageBox("Couldn't negotiate transfer", "Error");
		}
	    } catch (FileNotFoundException e) {
		e.printStackTrace();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	} else {
	    org.digitall.lib.components.Advisor.messageBox("Error, operation not set", "Error");
	}
    }

    public boolean prepareTransfer() {
	boolean prepared = true;
	try {
	    if (operation == GET) {
		//Primero el FILE por las dudas de que sea un error local
		//Si dejo el fileSocket primero y luego da error, el servidor trabajará al vicio
		File outputFile = new File(remoteFile.getDestinationDir() + remoteFile.getName());
		System.out.println("Tratando de grabar el archivo " + remoteFile.getDestinationDir() + ":" + remoteFile.getName());
		out = new BufferedOutputStream(new FileOutputStream(outputFile));
		in = new BufferedInputStream(fileSocket.getInputStream());
		if (!outputFile.canWrite()) {
		    //org.digitall.lib.components.Advisor.messageBox("Error, can't write file", "Error");
		    prepared = false;
		}
	    } else if (operation == PUT) {
		//Primero el FILE por las dudas de que sea un error local
		//Si dejo el fileSocket primero y luego da error, el servidor trabajará al vicio
		File inputFile = new File(localFile.getDirectory() + localFile.getName());
		in = new BufferedInputStream(new FileInputStream(inputFile));
		out = new BufferedOutputStream(fileSocket.getOutputStream());
		if (!inputFile.canRead()) {
		    //org.digitall.lib.components.Advisor.messageBox("Error, can't read file", "Error");
		    prepared = false;
		}
	    } else {
		return false;
	    }
	} catch (IOException e) {
	    prepared = false;
	    operation = 0;
	    try {
		fileSocket.close();
	    } catch (IOException f) {
		f.printStackTrace();
	    }
	    e.printStackTrace();
	}
	return prepared;
    }

}
