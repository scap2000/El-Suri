package org.digitall.lib.geo.mapping.classes;

import java.io.Serializable;

public class LayerProfile implements Serializable{

    private static final long serialVersionUID = 10000095L;

    private String geometrySetName;
    private String layerGroupName;
    private String layerName;
    
    public LayerProfile() {
        super();
    }

    public LayerProfile(String _geometrySetName, String _layerGroupName, String _layerName) {
	super();
	geometrySetName = _geometrySetName;
	layerGroupName = _layerGroupName;
	layerName = _layerName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setGeometrySetName(String geometrySetName) {
        this.geometrySetName = geometrySetName;
    }

    public String getGeometrySetName() {
        return geometrySetName;
    }

    public void setLayerGroupName(String layerGroupName) {
        this.layerGroupName = layerGroupName;
    }

    public String getLayerGroupName() {
        return layerGroupName;
    }

    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }

    public String getLayerName() {
        return layerName;
    }
}
