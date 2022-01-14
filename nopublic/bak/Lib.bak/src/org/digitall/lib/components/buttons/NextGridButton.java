package org.digitall.lib.components.buttons;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

//

public class NextGridButton extends BasicButton {

    public NextGridButton() {
	setIcon(IconTypes.next_16x16);
	//setMnemonic('C');
	setToolTipText(Environment.lang.getProperty("NextGridButton"));
	setSize(40, 25);
	setIcon(IconTypes.next_16x16);
	setRolloverIcon(IconTypes.next_ro_16x16);
	setDisabledIcon(IconTypes.next_ne_16x16);
	setPressedIcon(IconTypes.next_pr_16x16);
    }

}
