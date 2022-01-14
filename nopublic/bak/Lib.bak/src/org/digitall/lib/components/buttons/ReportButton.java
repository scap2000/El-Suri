package org.digitall.lib.components.buttons;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

//

public class ReportButton extends BasicButton {

    public ReportButton() {
	setIcon(IconTypes.report_16x16);
	setMnemonic('R');
	setToolTipText(Environment.lang.getProperty("ReportButton"));
	setSize(40, 25);
	setIcon(IconTypes.report_16x16);
	setRolloverIcon(IconTypes.report_ro_16x16);
	setDisabledIcon(IconTypes.report_ne_16x16);
	setPressedIcon(IconTypes.report_pr_16x16);
    }

}
