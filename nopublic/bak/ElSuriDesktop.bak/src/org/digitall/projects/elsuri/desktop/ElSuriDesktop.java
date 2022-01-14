package org.digitall.projects.elsuri.desktop;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import org.digitall.common.systemmanager.interfaces.MonitorSelector;
import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.environment.Environment;

public class ElSuriDesktop {

    private MainFrame frame;

    public ElSuriDesktop(GraphicsDevice[] devices) {
	Environment.mainClass = ElSuriDesktop.class;
	Environment.graphicsDevice = devices[0];
	if (devices.length > 1) {
	    if (!Environment.cfg.hasProperty("Screen")) {
		MonitorSelector monitorSelector = new MonitorSelector(devices);
		monitorSelector.show();
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
	/*
	Properties props = System.getProperties();
	// Enumerate all system properties
	Enumeration enume = props.propertyNames();
	for (; enume.hasMoreElements(); ) {
	    // Get property name
	    String propName = (String)enume.nextElement();
	    // Get property value
	    String propValue = (String)props.get(propName);
            System.out.println(propName + ": " + propValue);
	}
	*/
	Environment.mainClass = ElSuriDesktop.class;
	Boot.initGraphics();
	Environment.organization = "Municipalidad de DIGITALL";
	OrganizationInfo.setOrgName("Municipalidad de DIGITALL");
	OrganizationInfo.setTitle("MUNICIPALIDAD DE\nDIGITALL");
	OrganizationInfo.setShortName("Municipalidad de DIGITALL");
	OrganizationInfo.setDescription("Municipalidad de DIGITALL - Provincia DIGITALL");
        OrganizationInfo.setAddress("Dr. Univac I Nº 1010 - DIGITALL"); 
	OrganizationInfo.setShortAddress("openSUSE Nº 1010");
	OrganizationInfo.setCuit("99-99999999-9");
	OrganizationInfo.setZipCode("0000");
	OrganizationInfo.setLocation("DIGITALL");
	OrganizationInfo.setProvince("DIGITALL");
	OrganizationInfo.setCountry("República Informática");
        OrganizationInfo.setWebAddress("http://www.digitallsh.com.ar");
        OrganizationInfo.setPhoneNumber1("(0387) 4313379");
        OrganizationInfo.setPhoneNumber2("(0387) 4313379");
	Environment.defaultLocation = "DIGITALL (DIGITALL)";
	GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice[] devices = env.getScreenDevices();
	new ElSuriDesktop(devices);
    }

}
