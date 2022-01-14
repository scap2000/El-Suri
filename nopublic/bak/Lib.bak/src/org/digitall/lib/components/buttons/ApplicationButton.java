package org.digitall.lib.components.buttons;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

//

public class ApplicationButton extends BasicButton {

    public ApplicationButton() {
	setIcon(IconTypes.applicationIcon_16x16);
	//setMnemonic('C');
	setToolTipText(Environment.lang.getProperty("ApplicationButton"));
	setSize(40, 25);
    }

}
