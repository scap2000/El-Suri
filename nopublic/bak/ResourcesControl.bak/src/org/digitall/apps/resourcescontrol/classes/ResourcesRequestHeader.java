package org.digitall.apps.resourcescontrol.classes;

public class ResourcesRequestHeader {

    private int idResourcesRequest = -1;

    public ResourcesRequestHeader(int _idResourcesRequest) {
	idResourcesRequest = _idResourcesRequest;
    }
    
    public boolean saveData() {
	boolean result = false;
	//Returns true if OWN data was saved succesful
	//result = LibSQL.exFunction(...);
	return result;
    }

}
