package org.digitall.lib.components.buttons;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

public class OkButton extends BasicButton{

    public OkButton() {
	setIcon(IconTypes.ok_16x16);
	setMnemonic('a');
	setToolTipText(Environment.lang.getProperty("EndProcess"));
	setSize(40, 25);
	setIcon(IconTypes.ok_16x16);
	setRolloverIcon(IconTypes.ok_ro_16x16);
	setDisabledIcon(IconTypes.ok_ne_16x16);
	setPressedIcon(IconTypes.ok_pr_16x16);
    }

}
