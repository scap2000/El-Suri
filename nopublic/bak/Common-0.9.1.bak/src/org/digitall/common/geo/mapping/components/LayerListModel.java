package org.digitall.common.geo.mapping.components;

import org.digitall.lib.components.basic.BasicCheckableListItem;

public class LayerListModel extends BasicCheckableListItem {

    private String text;
    private String reference;
    private int id = -1;

    public LayerListModel(int _id, String _text, String _reference) {
	super(_id, _text);
	setText(_text);
	id = _id;
	reference = _reference;
    }
    
    public void setText(String _text) {
	text = _text;
    }

    public String getText() {
	return text;
    }

    public void setId(int id) {
	this.id = id;
    }
    
    public int getId(){
	return id;
    }

    public void setReference(String reference) {
	this.reference = reference;
    }

    public String getReference() {
	return reference;
    }
}
