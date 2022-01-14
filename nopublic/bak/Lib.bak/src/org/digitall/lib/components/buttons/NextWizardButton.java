package org.digitall.lib.components.buttons;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

//

public class NextWizardButton extends BasicButton {

    public NextWizardButton() {
	setIcon(IconTypes.nextWizardIcon_16x16);
	//setMnemonic('R');
	setToolTipText(Environment.lang.getProperty("NextWizardButton"));
	setSize(40, 25);
    }

}
