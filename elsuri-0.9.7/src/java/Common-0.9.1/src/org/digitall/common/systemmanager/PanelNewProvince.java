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
 * PanelNewProvince.java
 *
 * */
package org.digitall.common.systemmanager;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.digitall.lib.components.JEntry;
import org.digitall.lib.components.JOutry;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.sql.LibSQL;

//

public class PanelNewProvince extends BasicContainerPanel {

    private BasicPanel PanelCountry = new BasicPanel();
    private BasicLabel lblCountry = new BasicLabel();
    private BasicLabel lblCode = new BasicLabel();
    private BasicLabel lblCountry1 = new BasicLabel();
    private JEntry tfProvince = new JEntry();
    private JEntry tfCode = new JEntry();
    private JOutry tfCountry = new JOutry();
    private AcceptButton bAccept = new AcceptButton();
    private CloseButton bClose = new CloseButton();
    private final int FRAME = 1;
    private final int INTERNALFRAME = 2;
    private final int DIALOG = 3;
    private int parentType = -1;
    private Component parent;
    private String insert, idcountry = "";
    private int error = 0;

    public PanelNewProvince(BasicInternalFrame _parent, String _idcountry) {
	try {
	    parent = _parent;
	    parentType = INTERNALFRAME;
	    idcountry = _idcountry;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public PanelNewProvince(BasicDialog _parent, String _idcountry) {
	try {
	    parent = _parent;
	    parentType = DIALOG;
	    idcountry = _idcountry;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public PanelNewProvince(JFrame _parent, String _idcountry) {
	try {
	    parent = _parent;
	    parentType = FRAME;
	    idcountry = _idcountry;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(286, 163));
	tfProvince.setBounds(new Rectangle(5, 50, 265, 20));
	tfProvince.setFont(new Font("Dialog", 1, 11));
	bAccept.setBounds(new Rectangle(240, 130, 40, 25));
	bAccept.setSize(new Dimension(40, 25));
	bAccept.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bAccept_actionPerformed(e);
		    }

		});
	bClose.setBounds(new Rectangle(5, 130, 40, 25));
	bClose.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bClose_actionPerformed(e);
		    }

		});
	lblCountry1.setText("Country:");
	lblCountry1.setBounds(new Rectangle(5, 5, 265, 10));
	lblCountry1.setFont(new Font("Dialog", 1, 11));
	lblCountry1.setHorizontalAlignment(SwingConstants.LEFT);
	tfCountry.setBounds(new Rectangle(5, 15, 265, 20));
	PanelCountry.setBounds(new Rectangle(6, 10, 275, 115));
	PanelCountry.setLayout(null);
	PanelCountry.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	lblCountry.setText("Province/State:");
	lblCountry.setBounds(new Rectangle(5, 40, 265, 10));
	lblCountry.setFont(new Font("Dialog", 1, 11));
	lblCountry.setHorizontalAlignment(SwingConstants.LEFT);
	tfCode.setBounds(new Rectangle(5, 85, 65, 20));
	tfCode.setFont(new Font("Dialog", 1, 11));
	lblCode.setText("Code");
	lblCode.setBounds(new Rectangle(5, 75, 65, 10));
	lblCode.setFont(new Font("Dialog", 1, 11));
	lblCode.setHorizontalAlignment(SwingConstants.LEFT);
	PanelCountry.add(tfCountry, null);
	PanelCountry.add(lblCode, null);
	PanelCountry.add(tfCode, null);
	PanelCountry.add(lblCountry, null);
	PanelCountry.add(tfProvince, null);
	PanelCountry.add(lblCountry1, null);
	this.add(PanelCountry, null);
	this.add(bClose, null);
	this.add(bAccept, null);
	seteaCountry();
    }

    private void seteaCountry() {
	String pais = org.digitall.lib.sql.LibSQL.getCampo("Select name From tabs.country_tabs Where idcountry = " + idcountry);
	tfCountry.setText(pais);
    }

    private void dispose() {
	switch (parentType) {
	    case DIALOG:
		((BasicDialog)parent).dispose();
		break;
	    case INTERNALFRAME:
		((BasicInternalFrame)parent).dispose();
		break;
	    case FRAME:
		((JFrame)parent).dispose();
		break;
	}
    }

    private void bClose_actionPerformed(ActionEvent e) {
	dispose();
    }

    private boolean control() {
	String cantidad = org.digitall.lib.sql.LibSQL.getCampo("Select count(*) From tabs.province_tabs where code = '" + tfCode.getText().trim() + "' ");
	if (tfProvince.getText().equals("")) {
	    error = 1;
	    return false;
	} else if (tfCode.getText().equals("")) {
	    error = 2;
	    return false;
	} else if (!cantidad.equals("0")) {
	    error = 3;
	    return false;
	} else
	    return true;
    }

    private void bAccept_actionPerformed(ActionEvent e) {
	if (control()) {
	    insert = "INSERT INTO tabs.province_tabs VALUES ( (select max(idprovince) +1 From tabs.province_tabs)" + " , " + idcountry + " ,'" + tfCode.getText().toLowerCase().trim() + "', '" + tfProvince.getText().toUpperCase().toLowerCase() + "', '')";
	    System.out.println("insert: " + insert);
	    if (LibSQL.exActualizar('a', insert)) {
		org.digitall.lib.components.Advisor.messageBox("Insert Success!", "Message");
	    }
	} else
	    error();
    }

    private void error() {
	switch (error) {
	    case 1:
		org.digitall.lib.components.Advisor.messageBox("Field Province is empty", "Error");
		break;
	    case 2:
		org.digitall.lib.components.Advisor.messageBox("Field Code is empty", "Error");
		break;
	    case 3:
		org.digitall.lib.components.Advisor.messageBox("Code existing", "Error");
		break;
	}
    }

}
