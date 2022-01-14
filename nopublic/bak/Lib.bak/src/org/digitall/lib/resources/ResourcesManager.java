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
