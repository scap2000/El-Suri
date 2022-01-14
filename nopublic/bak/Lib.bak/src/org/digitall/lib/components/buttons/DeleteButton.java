package org.digitall.lib.components.buttons;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

//

public class DeleteButton extends BasicButton {

    public DeleteButton() {
	setIcon(IconTypes.delete_16x16);
	//setMnemonic('C');
	setToolTipText(Environment.lang.getProperty("DeleteButton"));
	setSize(40, 25);
	setIcon(IconTypes.delete_16x16);
	setRolloverIcon(IconTypes.delete_ro_16x16);
	setDisabledIcon(IconTypes.delete_ne_16x16);
	setPressedIcon(IconTypes.delete_pr_16x16);
    }

}
