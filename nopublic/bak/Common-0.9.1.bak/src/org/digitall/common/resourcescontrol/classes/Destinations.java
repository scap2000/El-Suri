package org.digitall.common.resourcescontrol.classes;

public class Destinations {

    private int idDestination = -1;
    private String name = "";
    
    public Destinations(int _idDestination) {
	idDestination = _idDestination;
    }

    public Destinations() {

    }
    
    public void setIddestination(int _iddestination) {
	idDestination = _iddestination;
    }

    public int getIddestination() {
	return idDestination;
    }

    public void setName(String _name) {
	name = _name;
    }

    public String getName() {
	return name;
    }

}
