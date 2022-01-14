package org.digitall.lib.geo.mapping.classes;

import java.util.Vector;

public class LayerGroup extends Vector<Layer> {

    private String name = "Grupo de Layers sin nombre";
    private boolean on = false;

    public LayerGroup(String _name) {
	name = _name;
    }

    public void setOn(boolean _on) {
	on = _on;
	for (int i = 0; i < size(); i++) {
	    elementAt(i).setOn(on);
	}
    }

    public boolean isOn() {
	return on;
    }

    public void setOff() {
	on = false;
	for (int i = 0; i < size(); i++) {
	    elementAt(i).setOff();
	}
    }

    public synchronized boolean add(Layer _layer) {
	if (_layer.getGeometrySet().hasAccessPrivileges()) {
	    _layer.setGroup(this);
	    return super.add(_layer);
	} else {
	    return false;
	}
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
