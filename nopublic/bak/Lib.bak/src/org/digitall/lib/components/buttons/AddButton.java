package org.digitall.lib.components.buttons;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

public class AddButton extends BasicButton {

    public AddButton() {
	setIcon(IconTypes.new_16x16);
	setMnemonic('n');
	setToolTipText(Environment.lang.getProperty("AddButton"));
	setSize(20, 20);
	setIcon(IconTypes.new_16x16);
	setRolloverIcon(IconTypes.new_ro_16x16);
	setDisabledIcon(IconTypes.new_ne_16x16);
	setPressedIcon(IconTypes.new_pr_16x16);
    }

}
