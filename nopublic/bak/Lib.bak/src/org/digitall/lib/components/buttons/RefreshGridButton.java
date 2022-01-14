package org.digitall.lib.components.buttons;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

public class RefreshGridButton extends BasicButton {

    public RefreshGridButton() {
	setIcon(IconTypes.refresh_16x16);
	//setMnemonic('C');
	setToolTipText(Environment.lang.getProperty("RefreshGridButton"));
	setSize(40, 25);
	setIcon(IconTypes.refresh_16x16);
	setRolloverIcon(IconTypes.refresh_ro_16x16);
	setDisabledIcon(IconTypes.refresh_ne_16x16);
	setPressedIcon(IconTypes.refresh_pr_16x16);
    }

}
