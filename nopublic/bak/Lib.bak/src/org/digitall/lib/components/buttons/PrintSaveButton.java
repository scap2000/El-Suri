package org.digitall.lib.components.buttons;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

public class PrintSaveButton extends BasicButton {

    public PrintSaveButton() {
	setIcon(IconTypes.print_save_16x16);
	setMnemonic('P');
	setToolTipText(Environment.lang.getProperty("PrintSaveButton"));
	setSize(20, 20);
	setIcon(IconTypes.print_save_16x16);
	setRolloverIcon(IconTypes.print_save_ro_16x16);
	setDisabledIcon(IconTypes.print_save_ne_16x16);
	setPressedIcon(IconTypes.print_save_pr_16x16);
    }

}
