package org.digitall.common.data;

import org.digitall.common.data.PanelDictionary;
import org.digitall.lib.components.basic.ExtendedInternalFrame;

//

public class Dictionary extends ExtendedInternalFrame {

    private PanelDictionary panelDictionary;

    public Dictionary() {
	super("Thesaurus");
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setCentralPanel(panelDictionary);
    }

}
