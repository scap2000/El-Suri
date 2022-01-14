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
 * XMLBasicBook.java
 *
 * */
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
