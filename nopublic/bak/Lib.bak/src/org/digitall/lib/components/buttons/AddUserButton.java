package org.digitall.lib.components.buttons;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.icons.IconTypes;

//

public class AddUserButton extends BasicButton {

    public AddUserButton() {
	jbInit();
    }

    private void jbInit() {
	setIcon(IconTypes.addUserIcon);
	setMnemonic('a');
	setToolTipText("Add user");
	setSize(40, 29);
    }

}
