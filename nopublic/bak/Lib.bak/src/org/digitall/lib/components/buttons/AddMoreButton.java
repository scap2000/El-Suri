package org.digitall.lib.components.buttons;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

//

public class AddMoreButton extends BasicButton {

    public AddMoreButton() {
	setIcon(IconTypes.addMoreButtonIcon_16x16);
	setMnemonic('C');
	setToolTipText(Environment.lang.getProperty("AddMoreButton"));
	setSize(40, 25);
    }

}
