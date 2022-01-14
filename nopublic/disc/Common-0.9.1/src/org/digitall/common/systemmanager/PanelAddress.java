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
 * PanelAddress.java
 *
 * */
package org.digitall.common.systemmanager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.SwingConstants;

import org.digitall.common.systemmanager.NewLocation;
import org.digitall.common.systemmanager.NewNeighborhood;
import org.digitall.common.systemmanager.NewProvince;
import org.digitall.common.systemmanager.NewStreet;
import org.digitall.common.systemmanager.NewCountry;
import org.digitall.lib.components.JDecEntry;
import org.digitall.lib.components.JIntEntry;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicTextField;
import org.digitall.lib.components.buttons.AddComboButton;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.combos.JCombo;

//

public class PanelAddress extends BasicContainerPanel {

    private BasicLabel lblCountry = new BasicLabel();
    private BasicLabel lblProvince = new BasicLabel();
    private BasicLabel lblNeighborhood = new BasicLabel();
    private BasicLabel lblLocation = new BasicLabel();
    private BasicLabel lblStreet = new BasicLabel();
    private BasicLabel lblPostalCode = new BasicLabel();
    private BasicLabel lblPersoanlAppt = new BasicLabel();
    private BasicLabel lblFloor = new BasicLabel();
    private BasicLabel lblBlock = new BasicLabel();
    private BasicLabel lblEmail = new BasicLabel();
    private BasicLabel lblStreetNumber = new BasicLabel();
    private BasicLabel lblCodeAreaPhone1 = new BasicLabel();
    private BasicLabel lblCodeAreaCellphone = new BasicLabel();
    private JCombo cbCountry = CachedCombo.getCachedCombo(CachedCombo.COUNTRY_TABS);
    private JCombo cbProvince = CachedCombo.getCachedCombo(CachedCombo.PROVINCE_TABS);
    private JCombo cbNeighborhood = CachedCombo.getCachedCombo(CachedCombo.NEIGHBORHOOD_TABS);
    private JCombo cbLocation = CachedCombo.getCachedCombo(CachedCombo.LOCATION_TABS);
    private JCombo cbStreet = CachedCombo.getCachedCombo(CachedCombo.STREET_TABS);
    private BasicTextField tfPostalCode = new BasicTextField();
    private BasicTextField tfAppt = new BasicTextField();
    private BasicTextField tfFloor = new BasicTextField();
    private BasicTextField tfBlock = new BasicTextField();
    private BasicTextField tfEmail = new BasicTextField();
    private JDecEntry tfStreetNumber = new JDecEntry();
    private JIntEntry tfAreaCodePhone = new JIntEntry();
    private JIntEntry tfPhone = new JIntEntry();
    private JIntEntry tfCellphone = new JIntEntry();
    private JIntEntry tfCodeCellphone = new JIntEntry();
    private String idAddress = "";
    private AddComboButton bNewCountry = new AddComboButton();
    private AddComboButton bNewLocation = new AddComboButton();
    private AddComboButton bNewNeighborhood = new AddComboButton();
    private AddComboButton bNewProvince = new AddComboButton();
    private AddComboButton bNewStreet = new AddComboButton();

    public PanelAddress(String _idAddress) {
	try {
	    idAddress = _idAddress;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public PanelAddress() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	this.setBounds(new Rectangle(0, 0, 533, 201));
	this.add(bNewStreet, null);
	this.add(bNewProvince, null);
	this.add(bNewNeighborhood, null);
	this.add(bNewLocation, null);
	this.add(bNewCountry, null);
	this.add(lblCodeAreaCellphone, null);
	this.add(lblCodeAreaPhone1, null);
	this.add(tfCodeCellphone, null);
	this.add(tfCellphone, null);
	this.add(tfPhone, null);
	this.add(tfAreaCodePhone, null);
	this.add(cbProvince, null);
	this.add(lblProvince, null);
	this.add(cbCountry, null);
	this.add(lblCountry, null);
	this.add(cbNeighborhood, null);
	this.add(lblNeighborhood, null);
	this.add(cbLocation, null);
	this.add(lblLocation, null);
	this.add(cbStreet, null);
	this.add(lblStreet, null);
	this.add(tfPostalCode, null);
	this.add(lblPostalCode, null);
	this.add(tfBlock, null);
	this.add(lblBlock, null);
	this.add(tfStreetNumber, null);
	this.add(lblStreetNumber, null);
	this.add(tfAppt, null);
	this.add(lblPersoanlAppt, null);
	this.add(tfFloor, null);
	this.add(lblFloor, null);
	this.add(tfEmail, null);
	this.add(lblEmail, null);
	lblCountry.setBounds(new Rectangle(10, 15, 230, 10));
	lblCountry.setText("Country:");
	lblCountry.setFont(new Font("Dialog", 1, 11));
	lblCountry.setHorizontalAlignment(SwingConstants.LEFT);
	cbCountry.setBounds(new Rectangle(10, 25, 220, 20));
	cbProvince.setBounds(new Rectangle(270, 25, 225, 20));
	cbProvince.setFont(new Font("Dialog", 1, 11));
	lblProvince.setText("Province/State:");
	lblProvince.setBounds(new Rectangle(270, 15, 225, 10));
	lblProvince.setFont(new Font("Dialog", 1, 11));
	lblProvince.setHorizontalAlignment(SwingConstants.LEFT);
	cbNeighborhood.setBounds(new Rectangle(270, 65, 225, 20));
	cbNeighborhood.setFont(new Font("Dialog", 1, 11));
	lblNeighborhood.setText("Neighborhood:");
	lblNeighborhood.setBounds(new Rectangle(270, 54, 225, 10));
	lblNeighborhood.setFont(new Font("Dialog", 1, 11));
	lblNeighborhood.setHorizontalAlignment(SwingConstants.LEFT);
	cbLocation.setBounds(new Rectangle(10, 65, 220, 20));
	cbLocation.setFont(new Font("Dialog", 1, 11));
	lblLocation.setText("Location:");
	lblLocation.setBounds(new Rectangle(10, 54, 230, 10));
	lblLocation.setFont(new Font("Dialog", 1, 11));
	lblLocation.setHorizontalAlignment(SwingConstants.LEFT);
	cbStreet.setBounds(new Rectangle(100, 100, 215, 20));
	cbStreet.setFont(new Font("Dialog", 1, 11));
	lblStreet.setText("Street:");
	lblStreet.setBounds(new Rectangle(100, 89, 215, 10));
	lblStreet.setFont(new Font("Dialog", 1, 11));
	lblStreet.setHorizontalAlignment(SwingConstants.LEFT);
	tfPostalCode.setBounds(new Rectangle(10, 103, 85, 15));
	tfPostalCode.setFont(new Font("Dialog", 1, 11));
	lblPostalCode.setText("CP:");
	lblPostalCode.setBounds(new Rectangle(10, 92, 85, 10));
	lblPostalCode.setFont(new Font("Dialog", 1, 11));
	lblPostalCode.setHorizontalAlignment(SwingConstants.LEFT);
	tfAppt.setBounds(new Rectangle(485, 103, 35, 15));
	tfAppt.setFont(new Font("Dialog", 1, 11));
	lblPersoanlAppt.setText("Appt.:");
	lblPersoanlAppt.setBounds(new Rectangle(485, 92, 35, 10));
	lblPersoanlAppt.setFont(new Font("Dialog", 1, 11));
	lblPersoanlAppt.setHorizontalAlignment(SwingConstants.LEFT);
	tfFloor.setBounds(new Rectangle(270, 220, 135, 20));
	tfFloor.setFont(new Font("Dialog", 1, 11));
	tfFloor.setBounds(new Rectangle(445, 103, 35, 15));
	tfFloor.setFont(new Font("Dialog", 1, 11));
	lblFloor.setText("Floor:");
	lblFloor.setBounds(new Rectangle(445, 92, 35, 10));
	lblFloor.setFont(new Font("Dialog", 1, 11));
	lblFloor.setHorizontalAlignment(SwingConstants.LEFT);
	tfBlock.setBounds(new Rectangle(75, 215, 155, 20));
	tfBlock.setFont(new Font("Dialog", 1, 11));
	tfBlock.setBounds(new Rectangle(405, 103, 35, 15));
	tfBlock.setFont(new Font("Dialog", 1, 11));
	lblBlock.setText("Block:");
	lblBlock.setBounds(new Rectangle(405, 92, 35, 10));
	lblBlock.setFont(new Font("Dialog", 1, 11));
	lblBlock.setHorizontalAlignment(SwingConstants.LEFT);
	tfStreetNumber.setBounds(new Rectangle(350, 103, 50, 15));
	tfStreetNumber.setFont(new Font("Dialog", 1, 11));
	lblStreetNumber.setText("Nï¿½:");
	lblStreetNumber.setBounds(new Rectangle(350, 92, 50, 10));
	lblStreetNumber.setFont(new Font("Dialog", 1, 11));
	lblStreetNumber.setHorizontalAlignment(SwingConstants.LEFT);
	tfEmail.setBounds(new Rectangle(10, 175, 335, 15));
	tfEmail.setFont(new Font("Dialog", 1, 11));
	tfAreaCodePhone.setBounds(new Rectangle(10, 140, 45, 15));
	tfAreaCodePhone.setFont(new Font("Dialog", 1, 11));
	tfPhone.setBounds(new Rectangle(60, 140, 110, 15));
	tfPhone.setFont(new Font("Dialog", 1, 11));
	tfCellphone.setBounds(new Rectangle(235, 140, 110, 15));
	tfCellphone.setFont(new Font("Dialog", 1, 11));
	tfCodeCellphone.setBounds(new Rectangle(185, 140, 45, 15));
	tfCodeCellphone.setFont(new Font("Dialog", 1, 11));
	lblCodeAreaPhone1.setText("Phone:");
	lblCodeAreaPhone1.setBounds(new Rectangle(10, 130, 160, 10));
	lblCodeAreaPhone1.setFont(new Font("Dialog", 1, 11));
	lblCodeAreaPhone1.setHorizontalAlignment(SwingConstants.LEFT);
	lblCodeAreaCellphone.setText("Cellphone:");
	lblCodeAreaCellphone.setBounds(new Rectangle(185, 130, 160, 10));
	lblCodeAreaCellphone.setFont(new Font("Dialog", 1, 11));
	lblCodeAreaCellphone.setHorizontalAlignment(SwingConstants.LEFT);
	bNewCountry.setBounds(new Rectangle(235, 25, 20, 20));
	bNewCountry.setSize(new Dimension(20, 20));
	bNewCountry.setToolTipText("New country");
	bNewCountry.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bNewCountry_actionPerformed(e);
		    }

		});
	bNewLocation.setBounds(new Rectangle(235, 65, 20, 20));
	bNewLocation.setSize(new Dimension(20, 20));
	bNewLocation.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bNewLocation_actionPerformed(e);
		    }

		});
	bNewNeighborhood.setBounds(new Rectangle(500, 65, 20, 20));
	bNewNeighborhood.setSize(new Dimension(20, 20));
	bNewNeighborhood.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bNewNeighborhood_actionPerformed(e);
		    }

		});
	bNewProvince.setBounds(new Rectangle(500, 25, 20, 20));
	bNewProvince.setSize(new Dimension(20, 20));
	bNewProvince.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bNewProvince_actionPerformed(e);
		    }

		});
	bNewStreet.setBounds(new Rectangle(320, 100, 20, 20));
	bNewStreet.setSize(new Dimension(20, 20));
	bNewStreet.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bNewStreet_actionPerformed(e);
		    }

		});
	lblEmail.setText("Email:");
	lblEmail.setBounds(new Rectangle(10, 165, 330, 10));
	lblEmail.setFont(new Font("Dialog", 1, 11));
	lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
	tfPostalCode.setBackground(Color.white);
	tfStreetNumber.setBackground(Color.white);
	tfBlock.setBackground(Color.white);
	tfFloor.setBackground(Color.white);
	tfAppt.setBackground(Color.white);
	tfAreaCodePhone.setBackground(Color.white);
	tfAreaCodePhone.setSize(new Dimension(45, 15));
	tfPhone.setBackground(Color.white);
	tfCodeCellphone.setBackground(Color.white);
	tfCellphone.setBackground(Color.white);
	tfEmail.setBackground(Color.white);
	setearDatos();
	cbCountry.addItemListener(new ItemListener() {

		    public void itemStateChanged(ItemEvent evt) {
			if (evt.getStateChange() == ItemEvent.SELECTED) {
			    //String idcountry = org.digitall.lib.sql.LibSQL.getCampo("SELECT idcountry FROM tabs.country_tabs WHERE name = '" + cbCountry.getSelectedItem().toString() + "' ");
			    //System.out.println("idlocation: "+idlocation);
			    //cargaNeighborhood(idlocation);
			    //cargaStreet(idlocation);
			    loadProvince(cbCountry.getSelectedValue());
			}
		    }

		});
	cbProvince.addItemListener(new ItemListener() {

		    public void itemStateChanged(ItemEvent evt) {
			if (evt.getStateChange() == ItemEvent.SELECTED && cbProvince.getSelectedIndex() > -1) {
			    //String idprovince = org.digitall.lib.sql.LibSQL.getCampo("SELECT idprovince FROM tabs.province_tabs WHERE name = '" + cbProvince.getSelectedItem().toString() + "' ");
			    //System.out.println("idprovince: "+idprovince);
			    loadLocation(cbProvince.getSelectedValue());
			}
		    }

		});
	cbLocation.addItemListener(new ItemListener() {

		    public void itemStateChanged(ItemEvent evt) {
			if (evt.getStateChange() == ItemEvent.SELECTED && cbLocation.getSelectedIndex() > -1) {
			    //String idlocation = org.digitall.lib.sql.LibSQL.getCampo("SELECT idlocation FROM tabs.location_tabs WHERE name = '" + cbLocation.getSelectedItem().toString() + "' ");
			    //System.out.println("idlocation: "+idlocation);
			    loadNeighborhood(cbLocation.getSelectedValue());
			    loadStreet(cbLocation.getSelectedValue());
			}
		    }

		});
    }

    private void setearDatos() {
	//System.out.println("address: "+ idAddress);
	
	/** 
	 * Cesar:	Tener en cuenta que la consulta debe hacerse sobre la nueva estructura de 
	 * 		la tabla addresses.
	 * */
	
	if (!idAddress.equals("")) {
	    String _query = "SELECT *, tabs.country_tabs.name as country, " 
			+ "tabs.province_tabs.name as province," 
			+ "tabs.location_tabs.name as location," 
			+ "tabs.neighborhood_tabs.name as neighborhood," 
			+ "tabs.street_tabs.name as street FROM org.addresses, " + "tabs.country_tabs,\n" + "tabs.province_tabs,\n" + "tabs.location_tabs,\n" + "tabs.neighborhood_tabs,\n" + "tabs.street_tabs \n" + "Where idaddress = 0" + idAddress + "and tabs.country_tabs.idcountry = addresses.idcountry \n" + "and tabs.province_tabs.idprovince = addresses.idprovince \n" + "and tabs.location_tabs.idlocation = addresses.idlocation\n" + "and tabs.neighborhood_tabs.idneighborhood = addresses.idneighborhood\n" + "and tabs.street_tabs.idstreet = addresses.idstreet";
	    ResultSet address = org.digitall.lib.sql.LibSQL.exQuery(_query);
	    try {
		if (address.next()) {
		    //Proced.CargaCombo(cbCountry, "Select name From tabs.country_tabs", address.getString("country"));
		    cbCountry.setSelectedValue(address.getString("country"));
		    //Proced.CargaCombo(cbProvince, "Select name From tabs.province_tabs", address.getString("province"));
		    cbProvince.setSelectedValue(address.getString("province"));
		    //Proced.CargaCombo(cbLocation, "Select name From tabs.location_tabs", address.getString("location"));
		    cbLocation.setSelectedValue(address.getString("location"));
		    //Proced.CargaCombo(cbNeighborhood, "Select name From tabs.neighborhood_tabs", address.getString("neighborhood"));
		    cbNeighborhood.setSelectedValue(address.getString("neighborhood"));
		    //Proced.CargaCombo(cbStreet, "Select name From tabs.street_tabs Order by name", address.getString("street"));
		    cbStreet.setSelectedValue(address.getString("street"));
		    tfStreetNumber.setText(address.getString("num_street"));
		    tfBlock.setText(address.getString("block"));
		    tfFloor.setText(address.getString("floor"));
		    tfAppt.setText(address.getString("apartment"));
		    tfPostalCode.setText(address.getString("postal_code"));
		    tfAreaCodePhone.setText(address.getString("phonearea"));
		    tfPhone.setText(address.getString("phone"));
		    tfCodeCellphone.setText(address.getString("cellphonearea"));
		    tfCellphone.setText(address.getString("cellphone"));
		    tfEmail.setText(address.getString("email"));
		    habilitarPanel(true);
		}
	    } catch (SQLException ex) {
		ex.printStackTrace();
	    }
	} else {
	    //Proced.CargaCombo(cbCountry, "Select name From tabs.country_tabs", "Argentina");
	}
    }

    private void habilitarPanel(boolean _valor) {
	cbCountry.setEnabled(_valor);
	cbProvince.setEnabled(_valor);
	cbLocation.setEnabled(_valor);
	cbNeighborhood.setEnabled(_valor);
	cbStreet.setEnabled(_valor);
	tfPostalCode.setEditable(_valor);
	tfStreetNumber.setEditable(_valor);
	tfBlock.setEditable(_valor);
	tfFloor.setEditable(_valor);
	tfAppt.setEditable(_valor);
	tfAreaCodePhone.setEditable(_valor);
	tfPhone.setEditable(_valor);
	tfCodeCellphone.setEditable(_valor);
	tfCellphone.setEditable(_valor);
	tfEmail.setEditable(_valor);
	bNewCountry.setEnabled(_valor);
	bNewProvince.setEnabled(_valor);
	bNewLocation.setEnabled(_valor);
	bNewNeighborhood.setEnabled(_valor);
	bNewStreet.setEnabled(_valor);
    }

    public void habilitarPanelAddress(boolean _valor) {
	habilitarPanel(_valor);
    }
    //public boolean saveData(){

    public String getConsulta() {
    
	/** 
	 * Cesar:	Hay que preparar las consultas para que se inserten en la
	 * 		nueva estructura de addreeses
	 * 
	 * */
	
	String idpais = org.digitall.lib.sql.LibSQL.getCampo("Select idcountry From tabs.country_tabs Where name = '" + cbCountry.getSelectedItem().toString() + "' ");
	String idprovincia = org.digitall.lib.sql.LibSQL.getCampo("Select idprovince From tabs.province_tabs Where name = '" + cbProvince.getSelectedItem().toString() + "' ");
	String idlocalidad = org.digitall.lib.sql.LibSQL.getCampo("Select idlocation From tabs.location_tabs Where name = '" + cbLocation.getSelectedItem().toString() + "' ");
	String idbarrio = org.digitall.lib.sql.LibSQL.getCampo("Select idneighborhood From tabs.neighborhood_tabs Where name = '" + cbNeighborhood.getSelectedItem().toString() + "' ");
	String idcalle = org.digitall.lib.sql.LibSQL.getCampo("Select idstreet From tabs.street_tabs Where name = '" + cbStreet.getSelectedItem().toString() + "' ");
	//String ididentification = org.digitall.lib.sql.LibSQL.getCampo("Select ididentification From tabs.identification_tabs Where name = '"+ cbIdentificationType.getSelectedItem().toString() +"' ");
	String consulta =
	    //" idresource = ,"
	    "UPDATE org.addresses SET " + " idcountry = 0" + idpais + "," + " idprovince = 0" + idprovincia + "," + " idlocation = 0" + idlocalidad + "," + " idneighborhood = 0" + idbarrio + "," + " idstreet = 0" + idcalle + "," + " num_street = 0" + tfStreetNumber.getText() + "," + " postal_code = '" + tfPostalCode.getText().trim() + "'," + " block = '" + tfBlock.getText().trim() + "'," + " floor = 0" + tfFloor.getText().trim() + "," + " apartment = '" + tfAppt.getText().trim() + "'," + " phonearea = 0" + tfAreaCodePhone.getText().trim() + "," + " phone = 0" + tfPhone.getText() + "," + " cellphonearea = 0" + tfCodeCellphone.getText().trim() + "," + " cellphone = 0" + tfCellphone.getText().trim() + "," + " email = '" + tfEmail.getText().toLowerCase().trim() + "' " + " WHERE idaddress = 0" + idAddress + ";";
	//System.out.println("consulta P. Addresss --> "+ consulta);
	//return LibSQL.exActualizar('m',consulta);
	return consulta;
    }

    private void loadProvince(Object _idCountry) {
	/*        String filtro = "";
        if (cbCountry.getSelectedItem().toString().equals("Argentina")) {
            filtro = "SALTA";
        }
        if (_idcountry.equals("0")) {
            Proced.CargaCombo(cbProvince, "Select name From tabs.province_tabs where idcountry = " + _idcountry, filtro);
        }
        if (org.digitall.lib.sql.LibSQL.getCampo("select count(*) from tabs.province_tabs where idcountry = " + _idcountry).equals("0")) {
            Proced.CargaCombo(cbProvince, "Select name From tabs.province_tabs where idcountry = " + 0, filtro);
        } else {
            Proced.CargaCombo(cbProvince, "Select name From tabs.province_tabs where idcountry = " + _idcountry, filtro);
        }
        String idprovince = org.digitall.lib.sql.LibSQL.getCampo("SELECT idprovince FROM tabs.province_tabs WHERE name = '" + cbProvince.getSelectedItem().toString() + "' ");*/
	cbProvince.setFilter(_idCountry);
	if (cbCountry.getSelectedItem().toString().equals("Argentina")) {
	    cbProvince.setSelectedValue("SALTA");
	} else {
	    cbProvince.setSelectedValue(null);
	}
	loadLocation(cbProvince.getSelectedValue());
    }

    private void loadLocation(Object _idProvince) {
	/*        String filtro = "";
        if (cbProvince.getSelectedItem().toString().equals("SALTA")) {
            filtro = "SALTA";
        }
        if (org.digitall.lib.sql.LibSQL.getCampo("select count(*) From tabs.location_tabs where idprovince = " + _idprovince).equals("0")) {
            Proced.CargaCombo(cbLocation, "Select name From tabs.location_tabs where idprovince = " + 0, filtro);
        } else {
            Proced.CargaCombo(cbLocation, "Select name From tabs.location_tabs where idprovince = " + _idprovince, filtro);
        }
        String idlocation = org.digitall.lib.sql.LibSQL.getCampo("SELECT idlocation FROM tabs.location_tabs WHERE name = '" + cbLocation.getSelectedItem().toString() + "' ");*/
	cbLocation.setFilter(_idProvince);
	if (cbProvince.getSelectedItem().toString().equals("SALTA")) {
	    cbLocation.setSelectedValue("SALTA");
	} else {
	    cbLocation.setSelectedValue(null);
	}
	loadNeighborhood(cbLocation.getSelectedValue());
	loadStreet(cbLocation.getSelectedValue());
    }

    private void loadNeighborhood(Object _idLocation) {
	/*        String filtro = "";
        if (cbLocation.getSelectedItem().toString().equals("SALTA")) {
            filtro = "Microcentro";
        }
        if (org.digitall.lib.sql.LibSQL.getCampo("select count(*) From tabs.neighborhood_tabs where idlocation = " + _idlocation).equals("0")) {
            Proced.CargaCombo(cbNeighborhood, "Select name From tabs.neighborhood_tabs where idlocation = " + 0, filtro);
        } else {
            Proced.CargaCombo(cbNeighborhood, "Select name From tabs.neighborhood_tabs where idlocation = " + _idlocation, filtro);
        }
        //String idneighborhood = org.digitall.lib.sql.LibSQL.getCampo("SELECT idneighborhood FROM tabs.neighborhood_tabs WHERE name = '"+ cbNeighborhood.getSelectedItem().toString() +"' ");
        //Proced.CargaCombo(cbNeighborhood,"Select name From tabs.Location_tabs where idlocation = " + _idlocation,"");*/
	cbNeighborhood.setFilter(_idLocation);
	if (cbLocation.getSelectedItem().toString().equals("SALTA")) {
	    cbNeighborhood.setSelectedValue("Microcentro");
	} else {
	    cbNeighborhood.setSelectedValue(null);
	}
    }

    private void loadStreet(Object _idLocation) {
	/*String filtro = "";
        if (cbLocation.getSelectedItem().toString().equals("SALTA")) {
            filtro = "Abï¿½n, Victor";
        }
        if (org.digitall.lib.sql.LibSQL.getCampo("Select count(*) From tabs.street_tabs where idlocation = " + _idlocation).equals("0")) {
            Proced.CargaCombo(cbStreet, "Select name From tabs.street_tabs Where idlocation = " + 0, filtro);
        } else {
            Proced.CargaCombo(cbStreet, "Select name From tabs.street_tabs Where idlocation = " + _idlocation, filtro);
            //Proced.CargaCombo(cbStreet,"Select name From tabs.streetsview Where idlocation = "+ _idlocation,filtro);
        }
        Proced.CargaCombo(cbStreet, "Select name From tabs.street_tabs where idlocation = " + _idlocation, filtro);*/
	cbStreet.setFilter(_idLocation);
	if (cbLocation.getSelectedItem().toString().equals("SALTA")) {
	    cbStreet.setSelectedValue("Abï¿½n, Victor");
	} else {
	    cbStreet.setSelectedValue(null);
	}
    }

    private void bNewCountry_actionPerformed(ActionEvent e) {
	NewCountry nuevoPais = new NewCountry();
	nuevoPais.setModal(true);
	nuevoPais.setVisible(true);
	cbCountry.update();
    }

    private void bNewProvince_actionPerformed(ActionEvent e) {
	String idcountry = org.digitall.lib.sql.LibSQL.getCampo("SELECT idcountry FROM tabs.country_tabs WHERE name = '" + cbCountry.getSelectedItem().toString() + "' ");
	if (idcountry.equals("0")) {
	    org.digitall.lib.components.Advisor.messageBox("Please, select a country", "Message");
	} else {
	    NewProvince nuevoProvincia = new NewProvince(idcountry);
	    nuevoProvincia.setModal(true);
	    nuevoProvincia.setVisible(true);
	}
    }

    private void bNewLocation_actionPerformed(ActionEvent e) {
	String consulta = "SELECT idprovince FROM tabs.province_tabs WHERE name = '" + cbProvince.getSelectedItem().toString() + "' ";
	//System.out.println("consulta: "+ consulta);
	String idprovince = org.digitall.lib.sql.LibSQL.getCampo(consulta);
	if (idprovince.equals("0")) {
	    org.digitall.lib.components.Advisor.messageBox("Please, select a Province/State", "Message");
	} else {
	    NewLocation nuevaLocalidad = new NewLocation(idprovince);
	    nuevaLocalidad.setModal(true);
	    nuevaLocalidad.setVisible(true);
	}
    }

    private void bNewNeighborhood_actionPerformed(ActionEvent e) {
	String consulta = "SELECT idlocation FROM tabs.location_tabs WHERE name = '" + cbLocation.getSelectedItem().toString() + "' ";
	//System.out.println("consulta: "+ consulta);
	String idlocation = org.digitall.lib.sql.LibSQL.getCampo(consulta);
	if (idlocation.equals("0")) {
	    org.digitall.lib.components.Advisor.messageBox("Please, select a Location", "Message");
	} else {
	    NewNeighborhood nuevoBarrio = new NewNeighborhood(idlocation);
	    nuevoBarrio.setModal(true);
	    nuevoBarrio.setVisible(true);
	}
    }

    private void bNewStreet_actionPerformed(ActionEvent e) {
	String consulta = "SELECT idlocation FROM tabs.location_tabs WHERE name = '" + cbLocation.getSelectedItem().toString() + "' ";
	//System.out.println("consulta: "+ consulta);
	String idlocation = org.digitall.lib.sql.LibSQL.getCampo(consulta);
	if (idlocation.equals("0")) {
	    org.digitall.lib.components.Advisor.messageBox("Please, select a Location", "Message");
	} else {
	    NewStreet nuevaCalle = new NewStreet(idlocation);
	    nuevaCalle.setModal(true);
	    nuevaCalle.setVisible(true);
	}
    }

}
