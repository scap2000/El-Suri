package org.digitall.lib.components.basic;

public class BasicCheckableListItem {

    private String name;
    private boolean selected;
    private boolean enabled = true;
    private int id;

    public BasicCheckableListItem(int _id, String _name) {
	this(_id, _name, false);
    }

    public BasicCheckableListItem(int _id, String _name, boolean _isSelected) {
	id = _id;
	name = _name;
	selected = _isSelected;
    }

    public void setSelected(boolean _selected) {
	selected = _selected;
    }

    public boolean isSelected() {
	return selected;
    }

    public String toString() {
	return name;
    }

    public String getName() {
	return name;
    }

    public int getId() {
	return id;
    }

    public void setEnabled(boolean _enabled) {
	enabled = _enabled;
    }

    public boolean isEnabled() {
	return enabled;
    }
}
