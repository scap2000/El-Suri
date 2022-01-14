package org.digitall.lib.components.buttons;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

//

public class PreviousWizardButton extends BasicButton {

    public PreviousWizardButton() {
	setIcon(IconTypes.previousWizardIcon_16x16);
	//setMnemonic('R');
	setToolTipText(Environment.lang.getProperty("PreviousWizardButton"));
	setSize(40, 25);
    }

}
