package org.digitall.projects.kratos.mosconi;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import org.digitall.common.systemmanager.interfaces.MonitorSelector;
import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.environment.Environment;

public class KratosMosconi {

    private MainFrame frame;

    public KratosMosconi(GraphicsDevice[] devices) {
	Environment.mainClass = KratosMosconi.class;
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
	Environment.mainClass = KratosMosconi.class;
	Boot.initGraphics();
	Environment.organization = "Municipalidad de Gral. Mosconi";
	OrganizationInfo.setOrgName("Municipalidad de General Enrique Mosconi");
	OrganizationInfo.setTitle("MUNICIPALIDAD DE\nGENERAL ENRIQUE MOSCONI");
	OrganizationInfo.setShortName("Municipalidad de Gral. Mosconi");
	OrganizationInfo.setDescription("Municipalidad de General Enrique Mosconi - Provincia de Salta");
	//OrganizationInfo.setAddress("Leandro N. Alem s/n - Gral. Enrique Mosconi - C.P. 4562"); 
        OrganizationInfo.setAddress("Dr. Gabriel Puló Nº 175 (SUPE) - Gral. E. Mosconi"); 
	//OrganizationInfo.setShortAddress("Leandro N. Alem s/n");
	OrganizationInfo.setShortAddress("Dr. Gabriel Puló Nº 175 (SUPE)");
	OrganizationInfo.setCuit("30-67304646-7");
	OrganizationInfo.setZipCode("4562");
	OrganizationInfo.setLocation("Gral. E. Mosconi");
	OrganizationInfo.setProvince("Salta");
	OrganizationInfo.setCountry("República Argentina");
        OrganizationInfo.setWebAddress("http://www.municipiomosconi.gov.ar");
        OrganizationInfo.setPhoneNumber1("(03875) 481399");
        OrganizationInfo.setPhoneNumber2("(03875) 481701");
	Environment.defaultLocation = "GENERAL MOSCONI (SALTA)";
	GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice[] devices = env.getScreenDevices();
	new KratosMosconi(devices);
    }

}
