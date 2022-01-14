package org.digitall.lib.components.buttons;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

public class PrintButton extends BasicButton {

    public PrintButton() {
	setIcon(IconTypes.print_16x16);
	setMnemonic('P');
	setToolTipText(Environment.lang.getProperty("PrintButton"));
	setSize(20, 20);
	setIcon(IconTypes.print_16x16);
	setRolloverIcon(IconTypes.print_ro_16x16);
	setDisabledIcon(IconTypes.print_ne_16x16);
	setPressedIcon(IconTypes.print_pr_16x16);
    }

}
