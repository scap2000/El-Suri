package org.digitall.lib.components.buttons;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

//

public class FirstGridButton extends BasicButton {

    public FirstGridButton() {
	setIcon(IconTypes.first_16x16);
	//setMnemonic('C');
	setToolTipText(Environment.lang.getProperty("FirstGridButton"));
	setSize(40, 25);
	setIcon(IconTypes.first_16x16);
	setRolloverIcon(IconTypes.first_ro_16x16);
	setDisabledIcon(IconTypes.first_ne_16x16);
	setPressedIcon(IconTypes.first_pr_16x16);
    }

}
