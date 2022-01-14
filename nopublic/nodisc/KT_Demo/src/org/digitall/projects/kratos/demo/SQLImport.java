package org.digitall.projects.kratos.demo;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.JArea;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.sql.LibSQL;

public class SQLImport extends BasicPrimitivePanel {

    private JButton btnImport = new JButton();
    private JButton btnOpenFile = new JButton();
    private JTextField tfFileName = new JTextField();
    private File file;
    private JArea taScript = new JArea();
    private static String SQLDriver = "org.postgresql.Driver";
    private StringBuffer script = new StringBuffer();

    public SQLImport() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(405, 302));
	btnImport.setEnabled(false);
	taScript.setEditable(false);
	btnImport.setText("Importar");
	btnImport.setBounds(new Rectangle(305, 275, 90, 20));
	btnImport.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnImport_actionPerformed(e);
				 }

			     }
	);
	btnOpenFile.setText("Abrir");
	btnOpenFile.setBounds(new Rectangle(5, 5, 77, 22));
	btnOpenFile.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       btnOpenFile_actionPerformed(e);
				   }

			       }
	);
	tfFileName.setBounds(new Rectangle(90, 5, 300, 20));
	taScript.setBounds(new Rectangle(5, 35, 390, 235));
	this.add(taScript, null);
	this.add(tfFileName, null);
	this.add(btnOpenFile, null);
	this.add(btnImport, null);
    }

    public File openFile() {
	JFileChooser selector = new JFileChooser();
	selector.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	selector.setFileFilter(new SQLFileFilter());
	int opcion = selector.showOpenDialog(null);
	if (opcion == JFileChooser.APPROVE_OPTION) {
	    tfFileName.setText(selector.getSelectedFile().getPath());
	    return selector.getSelectedFile();
	} else {
	    return null;
	}
    }

    private void btnOpenFile_actionPerformed(ActionEvent e) {
	file = openFile();
	if (file != null) {
	    if (file.canRead()) {
		//File selected and readable, so... do something!
		doPreImporting();
	    } else {
		Advisor.messageBox("El archivo no se puede leer", "Error");
		System.out.println("File not readable");
		file = null;
	    }
	} else {
	    Advisor.messageBox("No ha seleccionado ningún archivo", "Error");
	    System.out.println("File not selected");
	    file = null;
	}
	btnImport.setEnabled(file != null);
    }

    private void doPreImporting() {
	try {
	    if (file != null) {
		BufferedReader _inputBufferedReader = new BufferedReader(new FileReader(file));
		String linea = "";
		script = new StringBuffer();
		int lines = 0;
		while ((linea = _inputBufferedReader.readLine()) != null) {
		    lines++;
		    script.append(linea);
		}
		taScript.setText(script.toString());
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    private void btnImport_actionPerformed(ActionEvent e) {
	doImport();
    }

    private void doImport() {
	Connection pgCon;
	try {
	    pgCon = CreateConnection();
	    try {
	        pgCon.createStatement().execute(script.toString());
	        Advisor.messageBox("Archivo importado con éxito", "Operación correcta");
	    } catch (SQLException e) {
	        e.printStackTrace();
	        Advisor.messageBox("Error al importar el archivo", "Error");
	        Advisor.messageBox(e.getMessage(),"Mensaje del Servidor");
	    }
	} catch (SQLException e) {
	    Advisor.messageBox("Error al conectar a la Base de Datos", "Error");
	    e.printStackTrace();
	}
    }

    public static Connection CreateConnection() throws SQLException {
	try {
	    Class.forName(SQLDriver);
	} catch (ClassNotFoundException x) {
	    x.printStackTrace();
	}
	//System.out.println(LibSQL.getDataBase());
	return DriverManager.getConnection(LibSQL.getDataBase(), "admin", "admin1");
    }

}
