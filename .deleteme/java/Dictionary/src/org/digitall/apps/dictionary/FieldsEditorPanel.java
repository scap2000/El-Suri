/**
 LIMITACIÓN DE RESPONSABILIDAD - COPYRIGHT - [Español]
 ================================================================================
 El Suri - Entorno JAVA de Trabajo y Desarrollo para Gobierno Electrónico
 ================================================================================

 Información del Proyecto:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 por D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO es propiedad de
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) y
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los términos de la Licencia Pública General GNU publicada
 por la Fundación para el Software Libre, ya sea la versión 3
 de la Licencia, o (a su elección) cualquier versión posterior.

 Este programa se distribuye con la esperanza de que sea útil, pero
 SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita
 MERCANTIL o de APTITUD PARA UN PROPÓSITO DETERMINADO.
 Consulte los detalles de la Licencia Pública General GNU para obtener
 una información más detallada.

 Debería haber recibido una copia de la Licencia Pública General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
 El Suri - JAVA Management & Development Environment for Electronic Government
 =====================================================================================

 Project Info:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 by D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO is owned by
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) and
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/
/**
 * FieldsEditorPanel.java
 *
 * */
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
