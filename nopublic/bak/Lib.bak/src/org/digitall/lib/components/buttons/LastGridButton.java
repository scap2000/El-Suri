package org.digitall.lib.components.buttons;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

//

public class LastGridButton extends BasicButton {

    public LastGridButton() {
	setIcon(IconTypes.last_16x16);
	//setMnemonic('C');
	setToolTipText(Environment.lang.getProperty("LastGridButton"));
	setSize(40, 25);
	setIcon(IconTypes.last_16x16);
	setRolloverIcon(IconTypes.last_ro_16x16);
	setDisabledIcon(IconTypes.last_ne_16x16);
	setPressedIcon(IconTypes.last_pr_16x16);
    }

}
