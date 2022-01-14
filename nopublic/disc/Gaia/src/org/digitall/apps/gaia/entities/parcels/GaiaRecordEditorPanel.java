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
 * GaiaRecordEditorPanel.java
 *
 * */
package org.digitall.apps.gaia.entities.parcels;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.geo.gaia.components.GaiaConfigPanel;
import org.digitall.lib.sql.LibSQL;

public class GaiaRecordEditorPanel extends GaiaConfigPanel {

    private JPanel jpCenter = new JPanel();
    private BorderLayout borderLayout1 = new BorderLayout();
    private JScrollPane jsScroll = new JScrollPane();
    private JPanel jPanel1 = new JPanel();
    private JLabel[] labels;
    private JTextField[] fields;
    private AssignButton test = new AssignButton();

    public GaiaRecordEditorPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(445, 274));
	this.setPreferredSize(new Dimension(760, 512));
	jpCenter.setLayout(borderLayout1);
	jPanel1.setLayout(null);
	jsScroll.getViewport().add(jPanel1, null);
	jpCenter.add(jsScroll, BorderLayout.CENTER);
	jsScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	jsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	this.add(jpCenter, BorderLayout.CENTER);
	addButton(test);
	test.addActionListener(new ActionListener() {

			    public void actionPerformed(ActionEvent e) {
				test_actionPerformed(e);
			    }

			}
	);
    }

    private void test_actionPerformed(ActionEvent e) {
	jPanel1.removeAll();
	ResultSet _test = LibSQL.exQuery("SELECT * FROM gis_salta.parcelas_sa WHERE idparcela = 5");
	try {
	    if (_test.next()) {
		ResultSetMetaData _metadata = _test.getMetaData();
		int _columns = _metadata.getColumnCount();
	        fields = new JTextField[_columns];
	        labels = new JLabel[_columns];
		for (int i = 0; i < _columns; i++) {

		    labels[i] = new JLabel(_metadata.getColumnName(i+1));
		    jPanel1.add(labels[i]);
		    labels[i].setBounds(10, (int)(jPanel1.getBounds().getMinX() + 30 * i), 100, 20);

		    fields[i] = new JTextField(_test.getString(i+1));
		    jPanel1.add(fields[i]);
		    fields[i].setBounds(120, (int)(jPanel1.getBounds().getMinX() + 30 * i), 120, 20);
		}
		jPanel1.setPreferredSize(new Dimension(getWidth(), _columns * 30));
	    }
	} catch (SQLException f) {
	    System.out.println(f.getMessage());
	}
    }

}
