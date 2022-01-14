package org.digitall.lib.components;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.icons.IconTypes;

//

public class ShowPictureButton extends BasicButton {

    public ShowPictureButton() {
	jbInit();
    }

    private void jbInit() {
	setIcon(IconTypes.showPictureIcon);
	setToolTipText("Show image");
	setSize(40, 30);
    }

}
