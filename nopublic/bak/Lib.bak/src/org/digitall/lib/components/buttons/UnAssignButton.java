package org.digitall.lib.components.buttons;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.icons.IconTypes;

//

public class UnAssignButton extends BasicButton {

    public UnAssignButton() {
	this(false);
    }

    public UnAssignButton(boolean up) {
	if (up) {
	    setIcon(IconTypes.unAssign_up_16x16);
	    setRolloverIcon(IconTypes.unAssign_up_ro_16x16);
	    setDisabledIcon(IconTypes.unAssign_up_ne_16x16);
	    setPressedIcon(IconTypes.unAssign_up_pr_16x16);
	} else {
	    setIcon(IconTypes.unAssign_left_16x16);
	    setRolloverIcon(IconTypes.unAssign_left_ro_16x16);
	    setDisabledIcon(IconTypes.unAssign_left_ne_16x16);
	    setPressedIcon(IconTypes.unAssign_left_pr_16x16);
	}
	setMnemonic('u');
	setToolTipText("UnAssign");
	setSize(40, 25);
    }

}
