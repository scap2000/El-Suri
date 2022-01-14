package org.digitall.projects.gea.mini;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import org.digitall.common.systemmanager.interfaces.MonitorSelector;
import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.environment.Environment;

public class GEAMini {

    private MainFrame frame;

    public GEAMini(GraphicsDevice[] devices) {
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
	Environment.mainClass = GEAMini.class;
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
	new GEAMini(devices);
    }

}
