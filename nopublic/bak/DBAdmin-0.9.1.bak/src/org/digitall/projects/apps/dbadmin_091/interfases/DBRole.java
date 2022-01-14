package org.digitall.projects.apps.dbadmin_091.interfases;

import java.awt.Color;

import org.digitall.lib.components.basic.BasicCheckableListItem;
import org.digitall.projects.apps.dbadmin_091.SystemConfiguration;

public class DBRole extends BasicCheckableListItem {

    private int privilege = -1;
    private Color background = Color.WHITE;
    private Color foreGround = Color.GRAY;
    private String text;

    public DBRole(String _text) {
	super(-1, _text);
	setText(_text);
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

}
