package org.digitall.lib.components.buttons;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.icons.IconTypes;

//

public class AddGroupButton extends BasicButton {

    public AddGroupButton() {
	jbInit();
    }

    private void jbInit() {
	setIcon(IconTypes.addGroupIcon);
	setMnemonic('a');
	setToolTipText("Add group");
	setSize(40, 29);
    }

}
