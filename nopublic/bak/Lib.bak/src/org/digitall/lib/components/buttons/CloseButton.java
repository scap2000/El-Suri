package org.digitall.lib.components.buttons;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

//

public class CloseButton extends BasicButton {

    public CloseButton() {
	setMnemonic('C');
	setToolTipText(Environment.lang.getProperty("CloseButton"));
	setSize(40, 25);
	setIcon(IconTypes.close_16x16);
	setRolloverIcon(IconTypes.close_ro_16x16);
	setDisabledIcon(IconTypes.close_ne_16x16);
	setPressedIcon(IconTypes.close_pr_16x16);
    }

}
