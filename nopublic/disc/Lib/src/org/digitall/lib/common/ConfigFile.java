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
 * ConfigFile.java
 *
 * */
package org.digitall.lib.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import org.digitall.lib.environment.Environment;

public class ConfigFile {

    private String configFile = "myConfig.conf";
    private InputStream input;
    private Properties configuration = new Properties();
    public static final String nullProperty = "N/A";

    public ConfigFile(String _configFile) {
	configFile = _configFile;
	prepareConfigFile();
    }

    public ConfigFile(URL _url) {
	System.out.println("URL_: " + _url);
	configFile = _url.getFile();
	prepareConfigFile();
	loadProperties();
    }

    public ConfigFile(InputStream _inputStream) {
	input = _inputStream;
	loadProperties();
    }

    public ConfigFile() {

    }

    public void setProperty(String _name, Object _value) {
	configuration.put(_name, _value);
	try {
	    saveConfig();
	} catch (IOException ex) {
	    ex.printStackTrace();
	}
    }

    private void saveConfig() throws IOException {
	FileOutputStream output = new FileOutputStream(configFile);
        configuration.store(output, "DIGITALL S.H. Configuration File");
    }

    private void loadProperties() {
	try {
	    configuration.load(input);
	} catch (IOException out) {
	    try {
		saveConfig();
	    } catch (IOException in) {
		in.printStackTrace();
	    }
	} catch (NullPointerException out) {
	    //out.printStackTrace();
	    System.out.println("EXCEPTION!: The system will configure itself..." + configFile);
	}
    }

    public String getProperty(String _name) {
	return (configuration.getProperty(_name) == null ? _name : configuration.getProperty(_name));
    }

    public Vector getPropertyNames() {
	Enumeration propertyNames = configuration.propertyNames();
	Vector propertiesList = new Vector();
	while (propertyNames.hasMoreElements()) {
	    propertiesList.addElement(propertyNames.nextElement());
	}
	return propertiesList;
    }

    private void prepareConfigFile() {
	try {
	    input = new FileInputStream(configFile);
	    //System.out.println(configFile);
	} catch (FileNotFoundException e) {
	    // TODO
	    //e.printStackTrace();
	    try {
		saveConfig();
	    } catch (IOException out) {
		// TODO
		out.printStackTrace();
	    }
	}
	loadProperties();
    }

    public boolean hasProperty(String _name) {
	return configuration.containsKey(_name);
	/*Vector propertiesList = getPropertyNames();
	int i = 0;
	boolean found = false;
	while (i < propertiesList.size() && ! found) {
	    found = propertiesList.elementAt(i).toString().equalsIgnoreCase(_name);
	    i++;
	}
	return found;*/
    }

}
