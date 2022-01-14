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
