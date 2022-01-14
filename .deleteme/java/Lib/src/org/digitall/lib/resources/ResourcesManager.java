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
 * ResourcesManager.java
 *
 * */
package org.digitall.lib.resources;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.digitall.lib.common.ConfigFile;
import org.digitall.lib.environment.Environment;

public abstract class ResourcesManager {

    public static String systemPath = "";
    public static String iconsPath = "";
    public static String graphicsPath = "";
    public static String reportsPath = "";

    public static void setSystemPath(String _path) {
	systemPath = _path;
    }

    public static String getSystemPath() {
	return systemPath;
    }

    public static void setIconsPath(String _path) {
	iconsPath = _path;
    }

    public static void setGraphicsPath(String _path) {
	graphicsPath = _path;
    }

    public static void setReportsPath(String _path) {
	reportsPath = _path;
    }

    public static String getIconsPath() {
	return iconsPath;
    }

    public static String getGraphicsPath() {
	return graphicsPath;
    }

    public static String getReportsPath() {
	return reportsPath;
    }

    public static boolean extractFromJAR(String fileName, String destinationPath) {
	boolean result = false;
	FileInputStream inFile = null;
	FileOutputStream outFile = null;
	try {
	    File file = new File(destinationPath + fileName);
	    try {
		file.createNewFile();
	    } catch (Exception sad) {
		sad.printStackTrace();
	    }
	    InputStream source = Environment.mainClass.getResourceAsStream("default.conf");
	    if (source != null) {
	        BufferedInputStream in = new BufferedInputStream(source);
	        FileOutputStream out = new FileOutputStream(file);
	        int ch;
	        while ((ch = in.read()) != -1) {
	            out.write(ch);
	        }
	        in.close();
	        out.close();
	        result = true;
	    } else {
		System.out.println("No hay un archivo base de configuración en el paquete");
	    }
	} catch (IOException ex) {
	    ex.printStackTrace();
	    System.out.println(ex);
	} finally {
	    if (inFile != null) {
		try {
		    inFile.close();
		} catch (IOException ex) {
		    ex.printStackTrace();
		}
	    }
	    if (outFile != null) {
		try {
		    outFile.close();
		} catch (IOException ex) {
		    ex.printStackTrace();
		}
	    }
	}
	return result;
    }

    public static void initConfig() {
	String _defaultCfg = "default.conf";
	if (!(new File(_defaultCfg)).exists()) {
	    tryExtractCfg(_defaultCfg);
	    Environment.defaultCfg = new ConfigFile(_defaultCfg);
	} else {
	    Environment.defaultCfg = new ConfigFile(_defaultCfg);
	    double version = 0.0;
	    ConfigFile jarCfg = new ConfigFile(Environment.mainClass.getResourceAsStream("default.conf"));
	    try {
		version = Double.parseDouble(Environment.defaultCfg.getProperty("CFGVERSION"));
	    } catch (NumberFormatException x) {
	        tryExtractCfg(_defaultCfg);
	        Environment.defaultCfg = new ConfigFile(_defaultCfg);
	    }
	    double versionJAR = 0.0;
	    try {
		versionJAR = Double.parseDouble(jarCfg.getProperty("CFGVERSION"));
	    } catch (NumberFormatException x) {
		System.out.println("No se ha encontrado el archivo de configuración en el paquete original\nse utilizará el archivo existente");
	    }
	    if (version < versionJAR) {
	        tryExtractCfg(_defaultCfg);
	        Environment.defaultCfg = new ConfigFile(_defaultCfg);
	    }
	}
    }

    private static void tryExtractCfg(String _fileName) {
	System.out.println("Extrayendo la configuración inicial...");
	if (!extractFromJAR(_fileName, "")) {
	    System.err.println("No se pudo iniciar la configuración...\nQuizá no tenga permisos de escritura");
	    System.err.println("Archivo: " + _fileName);
	    System.exit(-1);
	}
	;
    }

}
