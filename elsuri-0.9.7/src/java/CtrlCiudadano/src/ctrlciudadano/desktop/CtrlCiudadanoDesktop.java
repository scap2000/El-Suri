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
 * SEDesktop.java
 * Modified by Santiago Cassina - SCasSI
 * July, 2013
 * */
package ctrlciudadano.desktop;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import org.digitall.common.systemmanager.interfaces.MonitorSelector;
import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.environment.Environment;

public class CtrlCiudadanoDesktop {

    private MainFrame frame;

    public CtrlCiudadanoDesktop(GraphicsDevice[] devices) {
	Environment.mainClass = CtrlCiudadanoDesktop.class;
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
	Environment.mainClass = CtrlCiudadanoDesktop.class;
	Boot.initGraphics();
	Environment.organization = "SCASSI CtrlCiudadano";
	OrganizationInfo.setOrgName("SCASSI CtrlCiudadano");
	OrganizationInfo.setTitle("SCASSI\nCtrlCiudadano");
	OrganizationInfo.setShortName("SCASSI CtrlCiudadano");
	OrganizationInfo.setDescription("SCASSI CtrlCiudadano");
	OrganizationInfo.setAddress("Juana Moro de López 220 - Salta - Argentina");
	OrganizationInfo.setShortAddress("Juana Moro de López 220");
	OrganizationInfo.setCuit("20-27034668-6");
	OrganizationInfo.setZipCode("4400");
	OrganizationInfo.setLocation("Salta");
	OrganizationInfo.setProvince("Salta");
	OrganizationInfo.setCountry("Argentina");
	OrganizationInfo.setWebAddress("http://www.mapcom.com.ar/ctrlciudadano");
	OrganizationInfo.setPhoneNumber1("+5493874752661");
	OrganizationInfo.setPhoneNumber2("");
	Environment.defaultLocation = "Salta (AR)";
	GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice[] devices = env.getScreenDevices();
	new CtrlCiudadanoDesktop(devices);
    }

}
