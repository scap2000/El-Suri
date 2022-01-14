package org.digitall.lib.xml.basic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import java.util.Vector;

import javax.swing.JFileChooser;

import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.xml.basic.XMLDocumentProperty;
import org.digitall.lib.xml.basic.XMLSheetStyles;

public abstract class XMLBasicBook {

    public OutputStreamWriter xmlFile = null;
    private BufferedWriter log;
    private String xmlPath = "";
    private String xmlFileName = "";
    public String bookName = "unnamed";
    public Vector<XMLBasicSheet> sheets = new Vector();

    public void doReport() {
	//boolean canWrite = false;
	boolean canWrite = true;
	/*******************************************************************/
	// Abro el Archivo
	xmlPath = Environment.cfg.getProperty(bookName) + File.separator;
	if (xmlFile == null) {
	    canWrite = openFile(xmlPath);
	}
	if (canWrite) {
	    write(startDocumentHeader());
	    write(writeDocumentProperties());
	    write(writeDocumentStyles());
	    writeSheets();
	    write(endDocumentHeader());
	    if (closeFile()) {
		Advisor.messagePopupWindow("<html><p align=center>Informe generado con éxito<br><a href=>Click aquí para verlo</a></p></html>", xmlFileName);
	    }
	}
    }
    

    public OutputStreamWriter xmlFileWriter(String _fileName) {
	// IF File Selected
	if (_fileName != null) {
	    try {
		if (!_fileName.endsWith(".xls")) {
		    _fileName += ".xls";
		}
	        xmlFileName = _fileName;
		xmlFile = new OutputStreamWriter(new FileOutputStream(new File(xmlFileName)), "UTF-8");
		log = new BufferedWriter(xmlFile);
		return xmlFile;
	    } catch (IOException x) {
		Advisor.messageBox("Error de E/S", "Error XMLWorkBook");
		x.printStackTrace();
		return null;
	    }
	} else {
	    return null;
	}
    }

    public boolean openFile(String _path) {
	JFileChooser chooser = new JFileChooser(_path);
	chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	String today = Proced.setFormatDate(Environment.currentDate,true).replaceAll("/","-");
	today = " ("+ today +")";
	chooser.setSelectedFile(new File(bookName + today +".xls"));
	int returnVal = chooser.showSaveDialog(chooser.getParent());
	if (returnVal == JFileChooser.APPROVE_OPTION) {
	    // IF File Selected
	    try {
		String path = chooser.getSelectedFile().getAbsolutePath();
		if (!path.endsWith(".xls")) {
		    path += ".xls";
		}
		Environment.cfg.setProperty(bookName, chooser.getSelectedFile().getParent());
	        xmlFileName = path;
		xmlFile = new OutputStreamWriter(new FileOutputStream(new File(xmlFileName)), "UTF-8");
		log = new BufferedWriter(xmlFile);
		return true;
	    } catch (IOException x) {
		Advisor.messageBox("Error de E/S", "Error XMLWorkBook");
		x.printStackTrace();
		return false;
	    }
	} else {
	    return false;
	}
    }


    public boolean closeFile() {
	try {
	    xmlFile.close();
	    return true;
	} catch (IOException x) {
	    Advisor.messageBox("Error de E/S", "Error " + bookName);
	    x.printStackTrace();
	    return false;
	}
    }

    public void write(String _cadena) {
	try {
	    xmlFile.write(_cadena);
	} catch (IOException x) {
	    Advisor.messageBox("Error de E/S", "Error");
	    x.printStackTrace();
	}
    }

    public String startDocumentHeader() {
	return XMLDocumentProperty.getXlsDocument();
    }

    public String endDocumentHeader() {
	return "</Workbook>\n";
    }

    public String writeDocumentProperties() {
	return XMLDocumentProperty.getXlsDocumentProperty();
    }

    public String writeDocumentStyles() {
	return XMLSheetStyles.getStyles();
    }

    public void writeSheets() {
	sheets.insertElementAt(new XMLCoverSheet(bookName),0);
	for (int i = 0; i < sheets.size(); i++) {
	    write(sheets.elementAt(i).writeSheet());
	}
    }

    public String docProperties() {
	return XMLDocumentProperty.getBasicWorkSheetOptionsFinal();
    }

    public void setBookName(String bookName) {
	this.bookName = bookName;
    }

}
