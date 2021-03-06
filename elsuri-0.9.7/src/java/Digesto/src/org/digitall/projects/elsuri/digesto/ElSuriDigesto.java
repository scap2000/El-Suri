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
 * ElSuriDigesto.java
 *
 * */
package org.digitall.projects.elsuri.digesto;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import org.digitall.common.systemmanager.interfaces.MonitorSelector;
import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.environment.Environment;

public class ElSuriDigesto {

    private MainFrame frame;

    public ElSuriDigesto(GraphicsDevice[] devices) {
	Environment.mainClass = ElSuriDigesto.class;
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
	Environment.mainClass = ElSuriDigesto.class;
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
	Environment.lblCalendar.setText("DIGESTO LEGISLATIVO GRAL. MOSCONI");
	GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice[] devices = env.getScreenDevices();
	new ElSuriDigesto(devices);
    }

}
