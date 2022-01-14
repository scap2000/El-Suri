package org.digitall.lib.common;

import java.awt.Color;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.lang.reflect.Array;
import java.lang.reflect.Modifier;

import java.net.URL;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import org.digitall.lib.data.Format;

public class ClassConfigFile {

    private String classConfigFile = "myConfig.conf";
    private InputStream input;
    private Properties configuration = new Properties();
    public static final String nullProperty = "N/A";
    public static final String userHome = System.getProperty("user.home") + File.separator + ".ddesktop.cache" + File.separator;

    public ClassConfigFile(String _classConfigFile) {
	classConfigFile = userHome + _classConfigFile + " - " + OrganizationInfo.getOrgName() + ".conf";
	prepareConfigFile();
    }

    public void setProperty(String _name, String _value) {
	configuration.put(_name, _value);
	try {
	    saveConfig();
	} catch (IOException ex) {
	    ex.printStackTrace();
	}
    }

    private void saveConfig() throws IOException {
	FileOutputStream output = new FileOutputStream(classConfigFile);
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
	    System.out.println("EXCEPTION!: The system will configure itself..." + classConfigFile);
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
	    input = new FileInputStream(classConfigFile);
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
	return getPropertyNames().contains(_name);
    }
    
    public void saveObjectProperties(Object _object) {
	for (int i = 0; i < _object.getClass().getDeclaredFields().length; i++) {
	    if (_object.getClass().getDeclaredFields()[i].getModifiers() == Modifier.PUBLIC) {
		try {
		    if (_object.getClass().getDeclaredFields()[i].getType().getName().equalsIgnoreCase("int") || _object.getClass().getDeclaredFields()[i].getType().getName().equalsIgnoreCase("double") || _object.getClass().getDeclaredFields()[i].getType().getName().equalsIgnoreCase("boolean") || _object.getClass().getDeclaredFields()[i].getType().getName().equalsIgnoreCase("java.lang.String")) {
			this.setProperty(_object.getClass().getDeclaredFields()[i].getName(), _object.getClass().getDeclaredFields()[i].get(_object).toString());
		    } else if (_object.getClass().getDeclaredFields()[i].getType().getName().equalsIgnoreCase("java.awt.Color")) {
			this.setProperty(_object.getClass().getDeclaredFields()[i].getName(), Format.color2Hex((Color)_object.getClass().getDeclaredFields()[i].get(_object)));
		    } else if (_object.getClass().getDeclaredFields()[i].getType().getName().equalsIgnoreCase("java.lang.StringBuffer")) {
			this.setProperty(_object.getClass().getDeclaredFields()[i].getName(), ((StringBuffer)_object.getClass().getDeclaredFields()[i].get(_object)).toString());
		    }
		} catch (IllegalAccessException e) {
		    e.printStackTrace();
		} catch (NullPointerException e) {
		    //e.printStackTrace();
		    //ignore
		}
	    }
	}
    }

    public void loadObjectProperties(Object _object) {
	for (int i = 0; i < _object.getClass().getDeclaredFields().length; i++) {
	    if (_object.getClass().getDeclaredFields()[i].getModifiers() == Modifier.PUBLIC) {
		if (!this.hasProperty(_object.getClass().getDeclaredFields()[i].getName())) {
		    try {
			//System.out.println(_object.getClass().getDeclaredFields()[i].getName());
			if (_object.getClass().getDeclaredFields()[i].getType().getName().equalsIgnoreCase("int") || _object.getClass().getDeclaredFields()[i].getType().getName().equalsIgnoreCase("double") || _object.getClass().getDeclaredFields()[i].getType().getName().equalsIgnoreCase("boolean") || _object.getClass().getDeclaredFields()[i].getType().getName().equalsIgnoreCase("java.lang.String")) {
			    this.setProperty(_object.getClass().getDeclaredFields()[i].getName(), _object.getClass().getDeclaredFields()[i].get(_object).toString());
			} else if (_object.getClass().getDeclaredFields()[i].getType().getName().equalsIgnoreCase("java.awt.Color")) {
			    this.setProperty(_object.getClass().getDeclaredFields()[i].getName(), Format.color2Hex((Color)_object.getClass().getDeclaredFields()[i].get(_object)));
			} else if (_object.getClass().getDeclaredFields()[i].getType().getName().equalsIgnoreCase("java.lang.StringBuffer")) {
			    this.setProperty(_object.getClass().getDeclaredFields()[i].getName(), ((StringBuffer)_object.getClass().getDeclaredFields()[i].get(_object)).toString());
			} else if (_object.getClass().getDeclaredFields()[i].getType().getName().equalsIgnoreCase("java.util.Vector")) {
			    Vector _vector = (Vector)_object.getClass().getDeclaredFields()[i].get(_object);
			    String data = ""; 
			    if (_vector.size() > 0) {
			        for (int j = 0; j < _vector.size()-1; j++) {
			            data += _vector.elementAt(j).toString() + ";";
			        }
			        data += _vector.elementAt(_vector.size()-1);
			        this.setProperty(_object.getClass().getDeclaredFields()[i].getName(), data);
			    }
			} else {
			    System.out.println(_object.getClass().getDeclaredFields()[i].getType().getName());
			}
		    } catch (IllegalAccessException e) {
			e.printStackTrace();
		    } catch (NullPointerException e) {
			//e.printStackTrace();
			//ignore
		    }
		} else {
		    try {
			if (_object.getClass().getDeclaredFields()[i].getType().getName().equalsIgnoreCase("int")) {
			    _object.getClass().getDeclaredFields()[i].set(_object, Integer.parseInt(this.getProperty(_object.getClass().getDeclaredFields()[i].getName())));
			} else if (_object.getClass().getDeclaredFields()[i].getType().getName().equalsIgnoreCase("double")) {
			    _object.getClass().getDeclaredFields()[i].set(_object, Double.parseDouble(this.getProperty(_object.getClass().getDeclaredFields()[i].getName())));
			} else if (_object.getClass().getDeclaredFields()[i].getType().getName().equalsIgnoreCase("boolean")) {
			    _object.getClass().getDeclaredFields()[i].set(_object, Boolean.parseBoolean(this.getProperty(_object.getClass().getDeclaredFields()[i].getName())));
			} else if (_object.getClass().getDeclaredFields()[i].getType().getName().equalsIgnoreCase("java.lang.String")) {
			    _object.getClass().getDeclaredFields()[i].set(_object, this.getProperty(_object.getClass().getDeclaredFields()[i].getName()));
			} else if (_object.getClass().getDeclaredFields()[i].getType().getName().equalsIgnoreCase("java.awt.Color")) {
			    _object.getClass().getDeclaredFields()[i].set(_object, Format.hex2Color(this.getProperty(_object.getClass().getDeclaredFields()[i].getName())));
			} else if (_object.getClass().getDeclaredFields()[i].getType().getName().equalsIgnoreCase("java.lang.StringBuffer")) {
			    _object.getClass().getDeclaredFields()[i].set(_object, new StringBuffer(this.getProperty(_object.getClass().getDeclaredFields()[i].getName())));
			} else if (_object.getClass().getDeclaredFields()[i].getType().getName().equalsIgnoreCase("java.util.Vector")) {
			    String[] _vector = this.getProperty(_object.getClass().getDeclaredFields()[i].getName()).split(";");
			    Vector data = new Vector();
			    if (_vector.length > 0) {
			        for (int j = 0; j < _vector.length; j++) {
			            data.add(_vector[j]);
			        }
			        _object.getClass().getDeclaredFields()[i].set(_object, data);
			    }
			} else {
			    //System.out.println(_object.getClass().getDeclaredFields()[i].getType().getName());
			}
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		}
	    }
	}
    }

}
