package org.digitall.lib.components.buttons;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

//

public class BackGridButton extends BasicButton {

    public BackGridButton() {
	setIcon(IconTypes.back_16x16);
	//setMnemonic('C');
	setToolTipText(Environment.lang.getProperty("BackGridButton"));
	setSize(40, 25);
	setIcon(IconTypes.back_16x16);
	setRolloverIcon(IconTypes.back_ro_16x16);
	setDisabledIcon(IconTypes.back_ne_16x16);
	setPressedIcon(IconTypes.back_pr_16x16);
    }

}
