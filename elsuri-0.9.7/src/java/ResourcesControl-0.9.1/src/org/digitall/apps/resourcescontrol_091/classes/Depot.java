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
 * Depot.java
 *
 * */
package org.digitall.apps.resourcescontrol_091.classes;

import java.util.Vector;

import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.common.resourcescontrol.classes.ResourceBrands;
import org.digitall.lib.org.Persons;

public class Depot {

    private int idDepot = -1;
    private String name = "";
    private String description = "";
    private CostsCentre costCentre;
    private int idAddress = -1;
    private double area = 0;
    private double volume = 0;
    private double capacity = 0;
    private String infrastructure = "";
    private Persons person;
    private String observations = "";
    private Vector resourceBrandsVector = new Vector();

    public Depot() {
    }

    public boolean store(ResourceBrands _resourceBrands) {
	//Almacenar un recurso específico
	 boolean _store = false;
	 int index = getResourceBrandsIndex(_resourceBrands);
	 if (index >= 0) {
	     _store = ((ResourceBrands)resourceBrandsVector.elementAt(index)).produce(_resourceBrands.getStock());
	 } else {
	     _store = resourceBrandsVector.add(_resourceBrands);
	 }
	 return _store;
    }

    public boolean unStore(ResourceBrands _resourceBrands) {
	//Quitar del almacén un recurso específico
	 boolean _unstore = false;
	 int index = getResourceBrandsIndex(_resourceBrands);
	 if (index >= 0) {
	     _unstore = ((ResourceBrands)resourceBrandsVector.elementAt(index)).consume(_resourceBrands.getStock());
	 } else {
	     _unstore = false;
	 }
	 return _unstore;
    }

    private int getResourceBrandsIndex(ResourceBrands _resourceBrands) {
	int index = -1;
	if (haveResourceBrands(_resourceBrands)) {
	    int i = 0;
	    boolean found = false;
	    while (i < resourceBrandsVector.size() && !found) {
		if (_resourceBrands == (ResourceBrands)resourceBrandsVector.elementAt(i)) {
		    index = i;
		    found = true;
		}
		i++;
	    }
	}
	return index;
    }

    private boolean haveResourceBrands(Resource _resourceBrands) {
	return resourceBrandsVector.contains(_resourceBrands);
    }

    public void setIdDepot(int idDepot) {
	this.idDepot = idDepot;
    }

    public int getIdDepot() {
	return idDepot;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getDescription() {
	return description;
    }

    public void setCostCentre(CostsCentre costCentre) {
	this.costCentre = costCentre;
    }

    public CostsCentre getCostCentre() {
	return costCentre;
    }

    public void setIdAddress(int idAddress) {
	this.idAddress = idAddress;
    }

    public int getIdAddress() {
	return idAddress;
    }

    public void setArea(double area) {
	this.area = area;
    }

    public double getArea() {
	return area;
    }

    public void setVolume(double volume) {
	this.volume = volume;
    }

    public double getVolume() {
	return volume;
    }

    public void setCapacity(double capacity) {
	this.capacity = capacity;
    }

    public double getCapacity() {
	return capacity;
    }

    public void setInfrastructure(String infrastructure) {
	this.infrastructure = infrastructure;
    }

    public String getInfrastructure() {
	return infrastructure;
    }

    public void setPerson(Persons person) {
	this.person = person;
    }

    public Persons getPerson() {
	return person;
    }

    public void setObservations(String observations) {
	this.observations = observations;
    }

    public String getObservations() {
	return observations;
    }

    public void setResourceBrandsVector(Vector resourceBrandsVector) {
	this.resourceBrandsVector = resourceBrandsVector;
    }

    public Vector getResourceBrandsVector() {
	return resourceBrandsVector;
    }

    public boolean saveData() {
	//Graba en SQL
	return true;
    }
    
    public void retrieveData() {
	//Recupera la info mediante SQL
    }

}
