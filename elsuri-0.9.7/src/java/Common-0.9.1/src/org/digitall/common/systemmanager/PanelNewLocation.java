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
 * PanelNewLocation.java
 *
 * */
package org.digitall.common.systemmanager;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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
import org.digitall.common.components.combos.JCombo;
import org.digitall.lib.sql.LibSQL;

//

public class PanelNewLocation extends BasicContainerPanel {

    private BasicPanel PanelCountry = new BasicPanel();
    private BasicLabel lblLocation = new BasicLabel();
    private BasicLabel lblProvince = new BasicLabel();
    private BasicLabel lblAccident = new BasicLabel();
    private BasicLabel lblAccident1 = new BasicLabel();
    private BasicLabel lblLogitude = new BasicLabel();
    private JCombo cbAccident = new JCombo();
    private JEntry tfLocation = new JEntry();
    private JOutry tfLatitude = new JOutry();
    private JOutry tfLongitude = new JOutry();
    private JOutry tfProvince = new JOutry();
    private AcceptButton bAccept = new AcceptButton();
    private CloseButton bClose = new CloseButton();
    private final int FRAME = 1;
    private final int INTERNALFRAME = 2;
    private final int DIALOG = 3;
    private int parentType = -1;
    private Component parent;
    private String insert, idprovince = "";
    private int error = 0;

    public PanelNewLocation(BasicInternalFrame _parent, String _idprovince) {
	try {
	    parent = _parent;
	    parentType = INTERNALFRAME;
	    idprovince = _idprovince;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public PanelNewLocation(BasicDialog _parent, String _idprovince) {
	try {
	    parent = _parent;
	    parentType = DIALOG;
	    idprovince = _idprovince;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public PanelNewLocation(JFrame _parent, String _idprovince) {
	try {
	    parent = _parent;
	    parentType = FRAME;
	    idprovince = _idprovince;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(286, 192));
	tfLocation.setBounds(new Rectangle(5, 50, 265, 20));
	tfLocation.setFont(new Font("Dialog", 1, 11));
	bAccept.setBounds(new Rectangle(240, 160, 40, 25));
	bAccept.setSize(new Dimension(40, 25));
	bAccept.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bAccept_actionPerformed(e);
		    }

		});
	bClose.setBounds(new Rectangle(5, 160, 40, 25));
	bClose.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bClose_actionPerformed(e);
		    }

		});
	tfLatitude.setBounds(new Rectangle(5, 120, 65, 20));
	cbAccident.setBounds(new Rectangle(5, 85, 145, 20));
	cbAccident.setFont(new Font("Dialog", 1, 11));
	cbAccident.addItemListener(new ItemListener() {

		    public void itemStateChanged(ItemEvent evt) {
			if (evt.getStateChange() == ItemEvent.SELECTED) {
			    String accident = org.digitall.lib.sql.LibSQL.getCampo("SELECT accident FROM tabs.location_tabs WHERE accident = '" + cbAccident.getSelectedItem().toString() + "' ");
			    //System.out.println("idlocation: "+idlocation);
			    //cargaNeighborhood(idlocation);
			    //cargaStreet(idlocation);
			}
		    }

		});
	lblAccident.setText("Accident:");
	lblAccident.setBounds(new Rectangle(5, 75, 145, 10));
	lblAccident.setFont(new Font("Dialog", 1, 11));
	lblAccident.setHorizontalAlignment(SwingConstants.LEFT);
	lblAccident1.setText("Latitude:");
	lblAccident1.setBounds(new Rectangle(5, 110, 100, 10));
	lblAccident1.setFont(new Font("Dialog", 1, 11));
	lblAccident1.setHorizontalAlignment(SwingConstants.LEFT);
	tfLongitude.setBounds(new Rectangle(85, 120, 65, 20));
	lblLogitude.setText("Logitude:");
	lblLogitude.setBounds(new Rectangle(85, 110, 65, 10));
	lblLogitude.setFont(new Font("Dialog", 1, 11));
	lblLogitude.setHorizontalAlignment(SwingConstants.LEFT);
	lblProvince.setText("Province/State:");
	lblProvince.setBounds(new Rectangle(5, 5, 265, 10));
	lblProvince.setFont(new Font("Dialog", 1, 11));
	lblProvince.setHorizontalAlignment(SwingConstants.LEFT);
	tfProvince.setBounds(new Rectangle(5, 15, 265, 20));
	PanelCountry.setBounds(new Rectangle(5, 5, 275, 150));
	PanelCountry.setLayout(null);
	PanelCountry.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	lblLocation.setText("Location:");
	lblLocation.setBounds(new Rectangle(5, 40, 265, 10));
	lblLocation.setFont(new Font("Dialog", 1, 11));
	lblLocation.setHorizontalAlignment(SwingConstants.LEFT);
	PanelCountry.add(lblLogitude, null);
	PanelCountry.add(tfLongitude, null);
	PanelCountry.add(lblAccident1, null);
	PanelCountry.add(lblAccident, null);
	PanelCountry.add(cbAccident, null);
	PanelCountry.add(tfLatitude, null);
	PanelCountry.add(tfProvince, null);
	PanelCountry.add(lblLocation, null);
	PanelCountry.add(tfLocation, null);
	PanelCountry.add(lblProvince, null);
	this.add(PanelCountry, null);
	this.add(bClose, null);
	this.add(bAccept, null);
	seteaDatos();
    }

    private void seteaDatos() {
	String provincia = org.digitall.lib.sql.LibSQL.getCampo("Select name From tabs.province_tabs Where idprovince = " + idprovince);
	tfProvince.setText(provincia);
	cbAccident.loadJCombo("Select distinct(accident) from tabs.location_tabs Where estado <> '*'");
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
	if (tfLocation.getText().equals("")) {
	    error = 1;
	    return false;
	} else
	    return true;
    }

    private void bAccept_actionPerformed(ActionEvent e) {
	if (control()) {
	    insert = "INSERT INTO tabs.location_tabs VALUES ( (select max(idlocation) +1 From tabs.location_tabs)" + " , " + idprovince + " ,'" + tfLocation.getText().toUpperCase().toLowerCase() + "' " + " , '" + cbAccident.getSelectedItem().toString() + "' , '', '', '')";
	    //System.out.println("insert: "+ insert);
	    if (LibSQL.exActualizar('a', insert)) {
		org.digitall.lib.components.Advisor.messageBox("Insert Success!", "Message");
	    }
	} else
	    error();
    }

    private void error() {
	switch (error) {
	    case 1:
		org.digitall.lib.components.Advisor.messageBox("Field Location is empty", "Error");
		break;
	}
    }

}
