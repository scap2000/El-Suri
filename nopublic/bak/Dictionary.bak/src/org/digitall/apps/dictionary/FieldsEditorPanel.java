package org.digitall.apps.dictionary;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.common.ConfigFile;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.sql.LibSQL;

//

public class FieldsEditorPanel extends BasicContainerPanel {

    private SaveDataButton btnSave = new SaveDataButton();

    public FieldsEditorPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	btnSave.setBounds(new Rectangle(345, 265, 40, 25));
	btnSave.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnSave_actionPerformed(e);
		    }

		});
	this.add(btnSave, null);
    }

    private void btnSave_actionPerformed(ActionEvent e) {
	ConfigFile lang_en_us = new ConfigFile("/digitall/produccion/jdevhome/mywork/DDesktop/Resources/src/org/digitall/lib/dictionary/en_us/lang.conf");
	ConfigFile lang_es_ar = new ConfigFile("/digitall/produccion/jdevhome/mywork/DDesktop/Resources/src/org/digitall/lib/dictionary/es_ar/lang.conf");
	ResultSet lang = LibSQL.exQuery("SELECT * FROM tabs.language_tabs /*order by algo*/");
	try {
	    while (lang.next()) {
		lang_en_us.setProperty(lang.getString("name"), lang.getString("en_us"));
		lang_es_ar.setProperty(lang.getString("name"), lang.getString("es_ar"));
	    }
	} catch (SQLException x) {
	    // TODO
	    x.printStackTrace();
	}
    }

}
