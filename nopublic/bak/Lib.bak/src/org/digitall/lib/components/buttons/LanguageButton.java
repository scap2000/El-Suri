package org.digitall.lib.components.buttons;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

//

public class LanguageButton extends BasicButton {

    public LanguageButton() {
	setIcon(IconTypes.languageIcon_22x22);
	setMnemonic('L');
	setToolTipText(Environment.lang.getProperty("LanguageButton"));
	setSize(40, 25);
    }

}
