package org.digitall.projects.gea.oran;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import org.digitall.common.systemmanager.interfaces.MonitorSelector;
import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.environment.Environment;

public class GEAOran {

    private MainFrame frame;

    public GEAOran(GraphicsDevice[] devices) {
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

	Environment.mainClass = GEAOran.class;
	Boot.initGraphics();
	Environment.organization = "Municipalidad de San Ramón de la Nueva Orán";
	OrganizationInfo.setOrgName("Municipalidad de San Ramón de la Nueva Orán");
	OrganizationInfo.setTitle("MUNICIPALIDAD DE\nSAN RAMÓN DE LA NUEVA ORÁN");
	OrganizationInfo.setShortName("Municipalidad de Orán");
	OrganizationInfo.setDescription("San Ramón de la Nueva Orán - Provincia de Salta");
	OrganizationInfo.setAddress("Belgrano 655 - Orán - C.P. 4530"); 
	OrganizationInfo.setShortAddress("Belgrano 655");
	OrganizationInfo.setCuit("30-59347057-8");
	OrganizationInfo.setZipCode("4530");
	OrganizationInfo.setLocation("San Ramón de la Nueva Orán");
	OrganizationInfo.setProvince("Salta");
	OrganizationInfo.setCountry("República Argentina");
	OrganizationInfo.setWebAddress("http://www.oran.gov.ar/");
	OrganizationInfo.setPhoneNumber1("(03878) 421374");
	OrganizationInfo.setPhoneNumber2("(03878) 421975");
	GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice[] devices = env.getScreenDevices();
	new GEAOran(devices);
    }

}
