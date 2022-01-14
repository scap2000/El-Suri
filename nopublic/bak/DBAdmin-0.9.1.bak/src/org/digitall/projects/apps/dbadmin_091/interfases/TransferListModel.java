package org.digitall.projects.apps.dbadmin_091.interfases;

import java.awt.Color;

import org.digitall.lib.components.basic.BasicCheckableListItem;
import org.digitall.projects.apps.dbadmin_091.SystemConfiguration;

public class TransferListModel extends BasicCheckableListItem {

    private int privilege = -1;
    private Color background = Color.WHITE;
    private Color foreGround = Color.GRAY;
    private String text;
    private String reference;
    private int id = -1;
    private String nombreBD = "";

    public TransferListModel(int _id, String _text, String _reference) {
	super(_id, _text);
	setText(_text);
	id = _id;
	reference = _reference;
	//setNombreBD();
    }
    
    private void setNombreBD(){
	nombreBD = text;
	//text = text.substring(text.indexOf("."),text.length());
	text = "11111111";
    }
    
    public String getSaveName(){
	return nombreBD;
    }

    public Color getBackground() {
	return background;
    }

    public void setBackground(Color _color) {
	background = _color;
    }

    public Color getForeground() {
	return foreGround;
    }

    public void setForeground(Color _color) {
	foreGround = _color;
    }

    public int getPrivilege() {
	return privilege;
    }

    public void setPrivilege(int _privilege) {
	privilege = _privilege;
	switch (privilege) {
	    case SystemConfiguration.ADMIN_PRIV :
		setBackground(Color.RED);
		setForeground(Color.BLACK);
		break;
	    case SystemConfiguration.USER_PRIV :
		setBackground(Color.YELLOW);
		setForeground(Color.BLACK);
		break;
	    case SystemConfiguration.QUERY_PRIV :
	        setBackground(Color.GREEN);
	        setForeground(Color.BLACK);
	        break;
	    case SystemConfiguration.EXEC_PRIV :
	        setBackground(Color.GREEN);
	        setForeground(Color.BLACK);
	        break;
	    default :
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);
		break;
	}
    }

    public void setText(String _text) {
	text = _text;
    }

    public String getText() {
	return text;
    }

    public void incPrivilege() {
	if (privilege >= 2) {
	    setPrivilege(-1);
	} else {
	    setPrivilege(privilege + 1);
	}
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
