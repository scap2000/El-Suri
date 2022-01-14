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
 * AddressPanel.java
 *
 * */
package org.digitall.apps.personalfiles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import org.digitall.common.personalfiles.classes.DireccionPersona;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.apps.personalfiles.AddressList;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddMoreButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

//

public class AddressPanel extends BasicContainerPanel {

    private TFInput tfPostalCode = new TFInput(DataTypes.STRING, "PostalCode", false);
    private TFInput tfAppt = new TFInput(DataTypes.STRING, "Appt", false);
    private TFInput tfFloor = new TFInput(DataTypes.INTEGER, "Floor", false);
    private TFInput tfBlock = new TFInput(DataTypes.STRING, "Block", false);
    private TFInput tfEmail = new TFInput(DataTypes.STRING, "Email", false);
    private TFInput tfNumber = new TFInput(DataTypes.INTEGER, "Number", false);
    private TFInput tfPhone2 = new TFInput(DataTypes.STRING, "Phone2", false);
    private TFInput tfPhone = new TFInput(DataTypes.STRING, "Phone", false);
    private TFInput tfMobile2 = new TFInput(DataTypes.STRING, "Mobile2", false);
    private TFInput tfMobile = new TFInput(DataTypes.STRING, "Mobile", false);
    private TFInput tfFax = new TFInput(DataTypes.STRING, "Fax", false);
    private TFInput tfEmail2 = new TFInput(DataTypes.STRING, "Email2", false);
    private TFInput tfUrl = new TFInput(DataTypes.STRING, "Url", false);
    private TFInput tfContactSchedule = new TFInput(DataTypes.STRING, "Schedule", false);
    private TFInput tfNick = new TFInput(DataTypes.STRING, "Nick", false);
    private CBInput cbContact = new CBInput(0, "Contact");
    private CBInput cbAddressType = new CBInput(CachedCombo.ADDRESSTYPE_TABS, "AddressType");
    private CBInput cbCountry = new CBInput(CachedCombo.COUNTRY_TABS, "Country");
    private CBInput cbProvince = new CBInput(CachedCombo.PROVINCE_TABS, "ProvinceState");
    private CBInput cbLocation = new CBInput(CachedCombo.LOCATION_TABS, "Location");
    private CBInput cbNeighborhood = new CBInput(CachedCombo.NEIGHBORHOOD_TABS, "Neighborhood");
    private CBInput cbStreet = new CBInput(CachedCombo.STREET_TABS, "Street");
    private ItemListener countryItemListener;
    private ItemListener provinceItemListener;
    private ItemListener locationItemListener;
    private ItemListener neighborhoodItemListener;
    private boolean hasItemListener = false;
    private int idAddress = 0;
    private int idAddressAux = 0;
    private int objectParentType;
    private int idObjectParent;
    private JDesktopPane parentDesktop;
    private boolean withReference = true;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private BasicPanel jPanel1 = new BasicPanel();
    private BasicPanel jPanel2 = new BasicPanel();
    private BasicPanel jPanel3 = new BasicPanel();
    private BasicPanel jPanel4 = new BasicPanel();
    private BasicPanel jPanel5 = new BasicPanel();
    private BasicPanel jPanel6 = new BasicPanel();
    private AddMoreButton btnAddMore = new AddMoreButton();
    private BasicCheckBox chkAllProvince = new BasicCheckBox();
    private BasicCheckBox chkAllLocation = new BasicCheckBox();
    private BasicCheckBox chkAllNeighborhood = new BasicCheckBox();
    private BasicCheckBox chkAllStreet = new BasicCheckBox();
    private BorderLayout borderLayout1 = new BorderLayout();
    private BorderLayout borderLayout2 = new BorderLayout();

    
    
    private DireccionPersona direccionPersona = null;
    
    private int idPerson = -1;
    private BasicCheckBox chkIsDefault = new BasicCheckBox();

    public AddressPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(borderLayout1);
	this.setBounds(new Rectangle(0, 0, 515, 225));
	this.setSize(new Dimension(580, 257));
	jPanel6.setLayout(borderLayout2);
	this.add(jPanel6, BorderLayout.CENTER);
	cbCountry.setBounds(new Rectangle(15, 5, 140, 35));
	cbProvince.setBounds(new Rectangle(165, 5, 300, 35));
	cbNeighborhood.setBounds(new Rectangle(15, 90, 450, 35));
	cbLocation.setBounds(new Rectangle(15, 50, 450, 35));
	cbStreet.setBounds(new Rectangle(15, 135, 355, 35));
	tfPostalCode.setBounds(new Rectangle(180, 180, 110, 35));
	tfAppt.setBounds(new Rectangle(125, 180, 40, 35));
	tfAppt.setSize(new Dimension(40, 35));
	tfFloor.setBounds(new Rectangle(270, 220, 135, 20));
	tfFloor.setBounds(new Rectangle(75, 180, 35, 35));
	tfBlock.setBounds(new Rectangle(75, 215, 155, 20));
	tfBlock.setBounds(new Rectangle(15, 180, 45, 35));
	tfNumber.setBounds(new Rectangle(415, 135, 70, 35));
	tfEmail.setBounds(new Rectangle(20, 15, 415, 35));
	tfPhone2.setBounds(new Rectangle(225, 15, 160, 35));
	tfPhone.setBounds(new Rectangle(20, 15, 160, 35));
	tfMobile2.setBounds(new Rectangle(225, 115, 160, 35));
	tfMobile.setBounds(new Rectangle(20, 115, 160, 35));
	tfFax.setBounds(new Rectangle(20, 65, 160, 35));
	tfEmail2.setBounds(new Rectangle(20, 60, 415, 35));
	tfUrl.setBounds(new Rectangle(20, 150, 415, 35));
	tfContactSchedule.setBounds(new Rectangle(20, 70, 70, 35));
	tfNick.setBounds(new Rectangle(20, 105, 210, 35));
	cbContact.setBounds(new Rectangle(20, 15, 435, 35));
	btnAddMore.setVisible(false);
	cbAddressType.setBounds(new Rectangle(310, 180, 115, 35));
	jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	jPanel1.setLayout(null);
	jPanel2.setLayout(null);
	jPanel2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	jPanel3.setLayout(null);
	jPanel3.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	jPanel4.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	jPanel4.setLayout(null);
	jPanel4.setEnabled(false);
	jPanel5.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	jPanel5.setLayout(null);
	jPanel5.setEnabled(false);
	btnAddMore.setBounds(new Rectangle(445, 190, 40, 25));
	btnAddMore.setSize(new Dimension(40, 25));
	btnAddMore.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      btnAddMore_actionPerformed(e);
				  }

			      }
	);
	chkAllProvince.setBounds(new Rectangle(475, 20, 15, 15));
	chkAllProvince.setSize(new Dimension(15, 15));
	chkAllProvince.setHorizontalAlignment(SwingConstants.CENTER);
	chkAllProvince.addActionListener(new ActionListener() {

				      public void actionPerformed(ActionEvent e) {
					  chkAllProvince_actionPerformed(e);
				      }

				  }
	);
	chkAllLocation.setBounds(new Rectangle(475, 65, 15, 15));
	chkAllLocation.setHorizontalAlignment(SwingConstants.CENTER);
	chkAllLocation.addActionListener(new ActionListener() {

				      public void actionPerformed(ActionEvent e) {
					  chkAllLocation_actionPerformed(e);
				      }

				  }
	);
	chkAllNeighborhood.setBounds(new Rectangle(475, 105, 15, 15));
	chkAllNeighborhood.setHorizontalAlignment(SwingConstants.CENTER);
	chkAllNeighborhood.addActionListener(new ActionListener() {

					  public void actionPerformed(ActionEvent e) {
					      chkAllNeighborhood_actionPerformed(e);
					  }

				      }
	);
	chkAllStreet.setBounds(new Rectangle(380, 150, 15, 15));
	chkAllStreet.setHorizontalAlignment(SwingConstants.CENTER);
	chkAllStreet.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					chkAllStreet_actionPerformed(e);
				    }

				}
	);
	//jPanel1.add(chkAllStreet, null);
	//jPanel1.add(chkAllNeighborhood, null);
	jPanel1.add(chkIsDefault, null);
	jPanel1.add(chkAllLocation, null);
	jPanel1.add(chkAllProvince, null);
	jPanel1.add(cbAddressType, null);
	jPanel1.add(btnAddMore, null);
	jPanel1.add(cbCountry, null);
	jPanel1.add(cbProvince, null);
	jPanel1.add(cbNeighborhood, null);
	jPanel1.add(cbLocation, null);
	jPanel1.add(cbStreet, null);
	jPanel1.add(tfPostalCode, null);
	jPanel1.add(tfBlock, null);
	jPanel1.add(tfNumber, null);
	jPanel1.add(tfAppt, null);
	jPanel1.add(tfFloor, null);
	jPanel2.add(tfPhone, null);
	jPanel2.add(tfMobile2, null);
	jPanel2.add(tfMobile, null);
	jPanel2.add(tfFax, null);
	jPanel2.add(tfPhone2, null);
	jPanel3.add(tfEmail, null);
	jPanel3.add(tfNick, null);
	jPanel3.add(tfUrl, null);
	jPanel3.add(tfEmail2, null);
	jPanel4.add(cbContact, null);
	jPanel4.add(tfContactSchedule, null);
	tabbedPane.addTab("Dirección", jPanel1);
	tabbedPane.addTab("Teléfono/Celular", jPanel2);
	//tabbedPane.addTab("Contacto", jPanel4);
	//tabbedPane.addTab("Coordenadas", jPanel5);
	tabbedPane.addTab("E-Mail/Url", jPanel3);
	jPanel6.add(tabbedPane, BorderLayout.CENTER);
	cbCountry.autoSize();
	cbLocation.autoSize();
	cbNeighborhood.autoSize();
	cbProvince.autoSize();
	cbStreet.autoSize();
	cbAddressType.autoSize();
	cbContact.autoSize();
	tfPostalCode.setEditable(true);
	comboItemListeners();
	chkAllProvince.setToolTipText("Mostrar sólo Provincias/Estados del País seleccionado");
	chkAllLocation.setToolTipText("Mostrar sólo Localidades de la Provincia/Estado seleccionada/o");
	chkAllNeighborhood.setToolTipText("Mostrar sólo Barrios de la Localidad seleccionada");
	chkAllStreet.setToolTipText("Mostrar sólo Calles del Barrio seleccionado");
	chkIsDefault.setText("Por defecto");
	chkIsDefault.setBounds(new Rectangle(445, 195, 100, 20));
	cbCountry.setSelectedValue(Environment.defaultCountry);
	cbProvince.setSelectedValue(Environment.defaultProvince);
	cbLocation.setSelectedValue(Environment.defaultLocation);
    }

    private void comboItemListeners() {
	countryItemListener = new ItemListener() {

		public void itemStateChanged(ItemEvent evt) {
		    if (evt.getStateChange() == ItemEvent.SELECTED) {
			loadProvince(cbCountry.getSelectedValue());
		    }
		}

	    }
	;
	provinceItemListener = new ItemListener() {

		public void itemStateChanged(ItemEvent evt) {
		    if (evt.getStateChange() == ItemEvent.SELECTED) {
			loadLocation(cbProvince.getSelectedValue());
		    }
		}

	    }
	;
	locationItemListener = new ItemListener() {

		public void itemStateChanged(ItemEvent evt) {
		    if (evt.getStateChange() == ItemEvent.SELECTED) {
			loadNeighborhood(cbLocation.getSelectedValue());
			loadLocationStreet(cbLocation.getSelectedValue());
		    }
		}

	    }
	;
	neighborhoodItemListener = new ItemListener() {

		public void itemStateChanged(ItemEvent evt) {
		    if (evt.getStateChange() == ItemEvent.SELECTED) {
			loadStreet(cbNeighborhood.getSelectedValue());
		    }
		}

	    }
	;
    }

    public void addComboItemListener() {
	hasItemListener = true;
	cbCountry.addItemListener(countryItemListener);
	cbProvince.addItemListener(provinceItemListener);
	cbLocation.addItemListener(locationItemListener);
	cbNeighborhood.addItemListener(neighborhoodItemListener);
    }

    public void removeComboItemListener() {
	if (hasItemListener) {
	    hasItemListener = false;
	    cbCountry.removeItemListener(countryItemListener);
	    cbProvince.removeItemListener(provinceItemListener);
	    cbLocation.removeItemListener(locationItemListener);
	    cbNeighborhood.removeItemListener(neighborhoodItemListener);
	}
    }

    private void loadProvince(Object _idCountry) {
	chkAllProvince.setSelected(true);
	cbProvince.setFilter(_idCountry);
    }

    private void loadLocation(Object _idProvince) {
	chkAllLocation.setSelected(true);
	cbLocation.setFilter(_idProvince);
    }

    private void loadNeighborhood(Object _idLocation) {
	chkAllNeighborhood.setSelected(false);
	cbNeighborhood.removeFilter();
    }

    private void loadLocationStreet(Object _idLocation) {
	chkAllStreet.setSelected(false);
	cbStreet.removeFilter();
    }

    private void loadStreet(Object _idNeighborhood) {
	chkAllStreet.setSelected(false);
	cbStreet.removeFilter();
    }

    public void clearData() {
	btnAddMore.setVisible(false);
	idAddress = 0;
	removeComboItemListener();
	cbCountry.setSelectedID("1");
	cbProvince.setSelectedID("0");
	cbLocation.setSelectedID("0");
	cbNeighborhood.setSelectedID("0");
	cbStreet.setSelectedID("0");
	cbAddressType.setSelectedID("0");
	tfNumber.setValue("0");
	tfBlock.setValue("");
	tfFloor.setValue("0");
	tfAppt.setValue("");
	tfPostalCode.setValue("");
	tfPhone2.setValue("");
	tfPhone.setValue("");
	tfMobile.setValue("");
	tfMobile2.setValue("");
	tfEmail.setValue("");
	tfEmail2.setValue("");
	tfFax.setValue("");
	tfUrl.setValue("");
	cbContact.setSelectedID("0");
	tfContactSchedule.setValue("");
	tfNick.setValue("");
	chkIsDefault.setSelected(false);
	cbCountry.setSelectedValue(Environment.defaultCountry);
	cbProvince.setSelectedValue(Environment.defaultProvince);
	cbLocation.setSelectedValue(Environment.defaultLocation);
    }

    public void setData(int _idAddress, boolean _addMore) {
	idAddress = _idAddress;
	removeComboItemListener();
	if (_idAddress != 0) {
	    ResultSet rsAddress = LibSQL.exFunction("org.getAddress", String.valueOf(_idAddress));
	    try {
		if (rsAddress.next()) {
		    cbCountry.setSelectedID(rsAddress.getString("idcountry"));
		    cbProvince.setSelectedID(rsAddress.getString("idprovince"));
		    cbLocation.setSelectedID(rsAddress.getString("idlocation"));
		    cbNeighborhood.setSelectedID(rsAddress.getString("idneighborhood"));
		    cbStreet.setSelectedID(rsAddress.getString("idstreet"));
		    cbAddressType.setSelectedID(rsAddress.getString("idaddresstype"));
		    tfNumber.setValue(rsAddress.getString("number"));
		    tfBlock.setValue(rsAddress.getString("block"));
		    tfFloor.setValue(rsAddress.getString("floor"));
		    tfAppt.setValue(rsAddress.getString("apartment"));
		    tfPostalCode.setValue(rsAddress.getString("postalcode"));
		    tfPhone2.setValue(rsAddress.getString("phone2"));
		    tfPhone.setValue(rsAddress.getString("phone"));
		    tfMobile.setValue(rsAddress.getString("mobile"));
		    tfMobile2.setValue(rsAddress.getString("mobile2"));
		    tfEmail.setValue(rsAddress.getString("email"));
		    tfEmail2.setValue(rsAddress.getString("email2"));
		    tfFax.setValue(rsAddress.getString("fax"));
		    tfUrl.setValue(rsAddress.getString("url"));
		    cbContact.setSelectedID(rsAddress.getString("idcontact"));
		    tfContactSchedule.setValue(rsAddress.getString("contactschedule"));
		    tfNick.setValue(rsAddress.getString("nick"));
		    btnAddMore.setVisible(_addMore);
		    addComboItemListener();
		}
	    } catch (SQLException ex) {
		ex.printStackTrace();
	    }
	} else {
	    cbCountry.setSelectedID("1");
	    cbProvince.setSelectedID("0");
	    cbLocation.setSelectedID("0");
	    cbNeighborhood.setSelectedID("0");
	    cbStreet.setSelectedID("0");
	}
    }

    public void saveData(String _isDefault, boolean _withReference) {
	withReference = _withReference;
	//saveData(_isDefault);
    }
    
    public boolean saveData(String var) {
	 return true;
    }

    public boolean saveData() {
	boolean resul = false;
	if (direccionPersona == null){
	    direccionPersona = new DireccionPersona();
	}
	direccionPersona.setIdCountry(Integer.parseInt(cbCountry.getSelectedValue().toString()));	
	direccionPersona.setIdProvince(Integer.parseInt(cbProvince.getSelectedValue().toString()));
	direccionPersona.setIdLocation(Integer.parseInt(cbLocation.getSelectedValue().toString()));
	direccionPersona.setIdNeighborHood(Integer.parseInt(cbNeighborhood.getSelectedValue().toString()));
	direccionPersona.setIdStreet(Integer.parseInt(cbStreet.getSelectedValue().toString()));
	direccionPersona.setIdAddressType(Integer.parseInt(cbAddressType.getSelectedValue().toString()));
	direccionPersona.setNumber(Integer.parseInt(tfNumber.getValue().toString()));
	direccionPersona.setBlock(tfBlock.getValue().toString());
	direccionPersona.setFloor(Integer.parseInt(tfFloor.getValue().toString()));
	direccionPersona.setApartment(tfAppt.getValue().toString());
	direccionPersona.setPostalCode(tfPostalCode.getValue().toString());
	direccionPersona.setPhone(tfPhone.getValue().toString());
	direccionPersona.setPhone2(tfPhone2.getValue().toString());
	direccionPersona.setMobile(tfMobile.getValue().toString());
	direccionPersona.setMobile2(tfMobile2.getValue().toString());
	direccionPersona.setEmail(tfEmail.getValue().toString());
	direccionPersona.setEmail2(tfEmail2.getValue().toString());
	direccionPersona.setFax(tfFax.getValue().toString());
	direccionPersona.setUrl(tfUrl.getValue().toString());
	direccionPersona.setIdContact(Integer.parseInt(cbContact.getSelectedValue().toString()));
	direccionPersona.setContactsChedule((tfContactSchedule.getString().equals("") ? "0:00" : tfContactSchedule.getString()));
	direccionPersona.setNick(tfNick.getValue().toString());
	direccionPersona.setIdPerson(idPerson);
	if (chkIsDefault.isSelected()) {
	    direccionPersona.setDefault(true);
	}
	
	if (direccionPersona.saveData() > 0) {
	    resul = true;	    
	    direccionPersona = null;//Para q se limpien de datos basurasss
	}
	else {
	    Advisor.messageBox("Ocurrio un error al grabar los datos!", "Error");	    
	}
	clearData();
	return resul;	
    }

    private void addReference(String _isDefault) {
	//switch (objectParentType) {
	  //  case AddressList.COMPANYTYPE :
	//	{
	//	    String params = idObjectParent + "," + idAddress + ",'" + _isDefault + "'";
	//	    LibSQL.getInt("org.addCompanyAddress", params);
	//	}
	//	break;
	  //  case AddressList.PERSONTYPE :
	//	{
		    String params = idPerson + "," + idAddress + ",'" + _isDefault + "'";
		    LibSQL.getInt("personalfiles.addAddressPerson", params);
	//	}
	//	break;
	//}
    }

    private void btnAddMore_actionPerformed(ActionEvent e) {
	AddressList addressList = new AddressList(this, objectParentType, idObjectParent);
	ExtendedInternalFrame addresses = new ExtendedInternalFrame("Administración de direcciones", addressList);
	addresses.show();
    }

    public void setParentDesktop(JDesktopPane _parentDesktop) {
	parentDesktop = _parentDesktop;
    }
    
    public void setIdPerson(int _idPerson){
	this.idPerson = _idPerson;    
    }
    
    public void setIdAddresses(int _idAddress){
	this.idAddress = _idAddress;	
    }

    public void setObjectParent(int _idObjectParent, int _objectParentType) {
	idObjectParent = _idObjectParent;
	objectParentType = _objectParentType;
    }

    public int getIdAddress() {
	return idAddress;
    }

    public void setIdAddress(int _idAddress) {
	idAddress = _idAddress;
    }

    private void chkAllProvince_actionPerformed(ActionEvent e) {
	if (chkAllProvince.isSelected()) {
	    cbProvince.setFilter(cbCountry.getSelectedValue());
	} else {
	    cbProvince.removeFilter();
	}
	cbProvince.updateUI();
    }

    private void chkAllStreet_actionPerformed(ActionEvent e) {
	if (chkAllStreet.isSelected()) {
	    //cbStreet.setFilter(cbLocation.getSelectedValue());
	} else {
	    cbStreet.removeFilter();
	}
	cbStreet.updateUI();
    }

    private void chkAllNeighborhood_actionPerformed(ActionEvent e) {
	if (chkAllNeighborhood.isSelected()) {
	    //cbNeighborhood.setFilter(cbLocation.getSelectedValue());
	} else {
	    cbNeighborhood.removeFilter();
	}
	cbNeighborhood.updateUI();
    }

    private void chkAllLocation_actionPerformed(ActionEvent e) {
	if (chkAllLocation.isSelected()) {
	    cbLocation.setFilter(cbProvince.getSelectedValue());
	} else {
	    cbLocation.removeFilter();
	}
	cbLocation.updateUI();
    }

    public void setTabbIndex(int _index) {
	tabbedPane.setSelectedIndex(_index);
    }

    public int getIdAddressAux() {
	return idAddressAux;
    }

    public void setDireccionPersona(DireccionPersona _direccionPersona){
	this.direccionPersona = _direccionPersona;
	loadData();
    }
    
    public void loadData(){
	cbCountry.setSelectedID(direccionPersona.getIdCountry());
	cbLocation.setSelectedID(direccionPersona.getIdLocation());
	cbProvince.setSelectedID(direccionPersona.getIdProvince());
	cbNeighborhood.setSelectedID(direccionPersona.getIdNeighborHood());
	cbStreet.setSelectedID(direccionPersona.getIdStreet());
	cbAddressType.setSelectedID(direccionPersona.getIdAddressType());
	tfNumber.setValue(direccionPersona.getNumber());
	tfBlock.setValue(direccionPersona.getBlock());
	tfFloor.setValue(direccionPersona.getFloor());
	tfAppt.setValue(direccionPersona.getApartment());
	tfPostalCode.setValue(direccionPersona.getPostalCode());
	tfPhone2.setValue(direccionPersona.getPhone2());
	tfPhone.setValue(direccionPersona.getPhone());
	tfMobile.setValue(direccionPersona.getMobile());
	tfMobile2.setValue(direccionPersona.getMobile2());
	tfEmail.setValue(direccionPersona.getEmail());
	tfEmail2.setValue(direccionPersona.getEmail2());
	tfFax.setValue(direccionPersona.getFax());
	tfUrl.setValue(direccionPersona.getUrl());
	cbContact.setSelectedID(direccionPersona.getIdContact());
	tfContactSchedule.setValue(direccionPersona.getContactsChedule());
	tfNick.setValue(direccionPersona.getNick());	
	chkIsDefault.setSelected(Boolean.valueOf(direccionPersona.isDefault()));
    }
    
    public void setDireccionPersonaNull(){
	direccionPersona = null;    
    }
}
