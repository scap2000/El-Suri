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
