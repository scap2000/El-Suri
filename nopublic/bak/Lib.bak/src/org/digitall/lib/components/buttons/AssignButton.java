package org.digitall.lib.components.buttons;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.icons.IconTypes;

//

public class AssignButton extends BasicButton {

    public AssignButton() {
	this(false);
    }

    public AssignButton(boolean down) {
	if (down) {
	    setIcon(IconTypes.assignDown_16x16);
	    setRolloverIcon(IconTypes.assignDown_ro_16x16);
	    setDisabledIcon(IconTypes.assignDown_ne_16x16);
	    setPressedIcon(IconTypes.assignDown_pr_16x16);
	} else {
	    setIcon(IconTypes.assignRight_16x16);
	    setRolloverIcon(IconTypes.assignRight_ro_16x16);
	    setDisabledIcon(IconTypes.assignRight_ne_16x16);
	    setPressedIcon(IconTypes.assignRight_pr_16x16);
	}
	setMnemonic('g');
	setToolTipText("Assign");
	setSize(40, 25);
    }

}
