package org.digitall.lib.components.buttons;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

//

public class FindButton extends BasicButton {

    public FindButton() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setIcon(IconTypes.find_16x16);
	String label = Environment.lang.getProperty("SearchButton");
	setToolTipText(label);
	setSize(40, 25);
	setIcon(IconTypes.find_16x16);
	setRolloverIcon(IconTypes.find_ro_16x16);
	setDisabledIcon(IconTypes.find_ne_16x16);
	setPressedIcon(IconTypes.find_pr_16x16);
    }

}
