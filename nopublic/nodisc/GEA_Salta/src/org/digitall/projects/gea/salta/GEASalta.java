package org.digitall.projects.gea.salta;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import org.digitall.common.systemmanager.interfaces.MonitorSelector;
import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.environment.Environment;

public class GEASalta {

    private MainFrame frame;

    public GEASalta(GraphicsDevice[] devices) {
	Environment.graphicsDevice = devices[0];
	if (devices.length > 1) {
	    if (!Environment.cfg.hasProperty("Screen")) {
		MonitorSelector monitorSelector = new MonitorSelector(devices);
		monitorSelector.setVisible(true);
	    } else {
		Environment.graphicsDevice = devices[Integer.parseInt(Environment.cfg.getProperty("Screen"))];
	    }
	}
	frame = new MainFrame();
    }

    public static void main(String[] args) {
	// Get all system UI Defaults
	/*
	UIDefaults defaults = UIManager.getDefaults();
	Enumeration enume = defaults.keys();
	for (; enume.hasMoreElements(); ) {
	    // Get property name
	    Object key = enume.nextElement();
	    Object value = defaults.get(key);
	    System.out.println(key + ": " + value);
	}
	*/
	// Get all system properties
	
	/*Properties props = System.getProperties();
	// Enumerate all system properties
	Enumeration enume = props.propertyNames();
	for (; enume.hasMoreElements(); ) {
	    // Get property name
	    String propName = (String)enume.nextElement();
	    // Get property value
	    String propValue = (String)props.get(propName);
            System.out.println(propName + ": " + propValue);
	}*/
	Environment.mainClass = GEASalta.class;
	Boot.initGraphics();
	
	Environment.organization = "Municipalidad de Salta";
	OrganizationInfo.setOrgName("Municipalidad de la Ciudad de Salta");
	OrganizationInfo.setTitle("MUNICIPALIDAD DE SALTA");
	OrganizationInfo.setShortName("Munic. de Salta");
	OrganizationInfo.setDescription("Municipalidad de Salta - Provincia de Salta");
	OrganizationInfo.setAddress("Av. Paraguay Nº 1200 - Salta - C.P. 4400"); 
	OrganizationInfo.setShortAddress("Av. Paraguay Nº 1200");
	OrganizationInfo.setCuit("-");
	OrganizationInfo.setZipCode("4400");
	OrganizationInfo.setLocation("Salta");
	OrganizationInfo.setProvince("Salta");
	OrganizationInfo.setCountry("República Argentina");
	OrganizationInfo.setWebAddress("http://www.municipalidad-salta.gov.ar");
	OrganizationInfo.setPhoneNumber1("(54-387) - 4373475");
	OrganizationInfo.setPhoneNumber2("(54-387) - 4373213");
	Environment.defaultLocation = "CIUDAD DE SALTA (SALTA)";
	
	GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice[] devices = env.getScreenDevices();
	new GEASalta(devices);
    }

}
