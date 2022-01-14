package org.digitall.lib.components.buttons;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.icons.IconTypes;

public class AcceptButton extends BasicButton {

    public AcceptButton() {
	setIcon(IconTypes.accept_16x16);
	setMnemonic('a');
	setToolTipText("Aceptar");
	setSize(40, 25);
	setIcon(IconTypes.accept_16x16);
	setRolloverIcon(IconTypes.accept_ro_16x16);
	setDisabledIcon(IconTypes.accept_ne_16x16);
	setPressedIcon(IconTypes.accept_pr_16x16);
    }

}
