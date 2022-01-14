package org.digitall.lib.components.buttons;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

//

public class GoButton extends BasicButton {

    public GoButton() {
	setIcon(IconTypes.goButtonIcon_16x16);
	//setMnemonic('R');
	setToolTipText(Environment.lang.getProperty("GoButton"));
	setSize(45, 25);
	//setMargin(new Insets(1, 1, 1, 1));
    }

}
