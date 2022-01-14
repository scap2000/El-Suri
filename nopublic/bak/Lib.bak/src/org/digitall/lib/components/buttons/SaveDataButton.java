package org.digitall.lib.components.buttons;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

//

public class SaveDataButton extends BasicButton {

    public SaveDataButton() {
	setIcon(IconTypes.save_16x16);
	setMnemonic('s');
	setToolTipText(Environment.lang.getProperty("SaveButton"));
	setSize(40, 25);
	setRolloverIcon(IconTypes.save_ro_16x16);
	setDisabledIcon(IconTypes.save_ne_16x16);
	setPressedIcon(IconTypes.save_pr_16x16);
    }

}
