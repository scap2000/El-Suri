package org.digitall.lib.components.buttons;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

//

public class CancelDataButton extends BasicButton {

    public CancelDataButton() {
	setIcon(IconTypes.dontsave_16x16);
	setMnemonic('a');
	setToolTipText(Environment.lang.getProperty("CancelButton"));
	setSize(40, 25);
	setRolloverIcon(IconTypes.dontsave_ro_16x16);
	setDisabledIcon(IconTypes.dontsave_ne_16x16);
	setPressedIcon(IconTypes.dontsave_pr_16x16);
    }

}
