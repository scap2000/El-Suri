package org.digitall.lib.components.buttons;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.icons.IconTypes;

//

public class AddComboButton extends BasicButton {

    public AddComboButton() {
	setIcon(IconTypes.add_item_16x16);
	setToolTipText("Add new");
	setSize(20, 20);
	setIcon(IconTypes.add_item_16x16);
	setRolloverIcon(IconTypes.add_item_ro_16x16);
	setDisabledIcon(IconTypes.add_item_ne_16x16);
	setPressedIcon(IconTypes.add_item_pr_16x16);
    }

}
