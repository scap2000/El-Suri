package org.digitall.lib.components;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.icons.IconTypes;

//

public class UploadPictureButton extends BasicButton {

    public UploadPictureButton() {
	jbInit();
    }

    private void jbInit() {
	setIcon(IconTypes.uploadPictureIcon);
	setToolTipText("Upload image");
	setSize(40, 30);
    }

}
