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
 * DriversFormMgmt.java
 *
 * */
package org.digitall.apps.licenses.interfases;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.digitall.apps.licenses.classes.Driver;
import org.digitall.apps.licenses.classes.License;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.CancelDataButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.image.ImageCropper;
import org.digitall.lib.image.ImageLabel;
import org.digitall.lib.image.LibIMG;
import org.digitall.lib.misc.BarCode;
import org.digitall.lib.sql.LibSQL;

public class DriversFormMgmt extends BasicPrimitivePanel {

    private BasicPanel dataPanel = new BasicPanel();
    private ImageLabel lblPhoto = new ImageLabel();
    private TFInput tfLastName = new TFInput(DataTypes.STRING, "LastName", false);
    private TFInput tfName = new TFInput(DataTypes.STRING, "Name", false);
    private TFInput tfBirthdate = new TFInput(DataTypes.DATE, "Birthdate", false);
    private TFInput tfIdentificationNumber = new TFInput(DataTypes.INTEGER, "Number", false);
    private TFInput tfAddress = new TFInput(DataTypes.STRING, "Address", false);
    private TFInput tfNationality = new TFInput(DataTypes.STRING, "Nationality", false);
    private TFInput tfClass = new TFInput(DataTypes.INTEGER, "Kind", false);
    private TFInput tfLicenseNumber = new TFInput(DataTypes.INTEGER, "LicenseNumber", false);
    private CBInput cbIdentificationTypes = new CBInput(CachedCombo.IDENTIFICATION_TABS, "Identification", false);
    private CBInput cbBloodGroup = new CBInput(0, "BloodGroup", false);
    private CBInput cbRHFactor = new CBInput(0, "RHFactor", false);
    private CBInput cbSex = new CBInput(0, "Sex", false);
    private SaveDataButton btnSaveDriverForm = new SaveDataButton();
    private ModifyButton btnModifyDriverForm = new ModifyButton();
    private CancelDataButton btnCancelDriverForm = new CancelDataButton();
    private CloseButton btnClose = new CloseButton();
    private AddButton btnAddLicense = new AddButton();
    private ModifyButton btnModifyLicense = new ModifyButton();
    private AcceptButton btnRadiograms = new AcceptButton();
    private PrintButton btnPrintLicense = new PrintButton();
    private PrintButton btnPrintDriverForm = new PrintButton();
    private PrintButton btnPrintBackDriverForm = new PrintButton();
    private Driver driverSelected;
    private Driver driverResguardo;
    private DriversList parentList;
    private LicensesMgmt licensesMgmt;
    private BufferedImage photo = null;
    private BufferedImage licensePhoto = null;
    private int error = 0;
    private int[] sizeColumnList = { 73, 115, 115, 75, 75, 90, 95 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Licencias otorgadas", dataRow);
    private Vector headerList = new Vector();

    public DriversFormMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(692, 461));
	this.setPreferredSize(new Dimension(760, 512));
	dataPanel.setLayout(null);
	dataPanel.setSize(new Dimension(692, 324));
	dataPanel.setPreferredSize(new Dimension(1, 190));
	lblPhoto.setBounds(new Rectangle(20, 5, 175, 175));
	lblPhoto.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	lblPhoto.setHorizontalAlignment(SwingConstants.CENTER);
	lblPhoto.setSize(new Dimension(175, 175));
	tfLastName.setBounds(new Rectangle(220, 5, 145, 35));
	tfName.setBounds(new Rectangle(375, 5, 165, 35));
	tfBirthdate.setBounds(new Rectangle(510, 50, 95, 35));
	cbIdentificationTypes.setBounds(new Rectangle(220, 50, 95, 35));
	cbBloodGroup.setBounds(new Rectangle(375, 145, 85, 35));
	cbRHFactor.setBounds(new Rectangle(465, 145, 70, 35));
	cbSex.setBounds(new Rectangle(445, 50, 45, 35));
	tfIdentificationNumber.setBounds(new Rectangle(320, 50, 105, 35));
	tfAddress.setBounds(new Rectangle(220, 100, 460, 35));
	tfNationality.setBounds(new Rectangle(550, 5, 130, 35));
	tfClass.setBounds(new Rectangle(610, 50, 70, 35));
	tfLicenseNumber.setBounds(new Rectangle(220, 145, 140, 35));
	//dataPanel.add(listPanel, null);
	dataPanel.add(btnCancelDriverForm, null);
	dataPanel.add(btnModifyDriverForm, null);
	dataPanel.add(btnSaveDriverForm, null);
	dataPanel.add(tfLicenseNumber, null);
	dataPanel.add(tfClass, null);
	dataPanel.add(tfNationality, null);
	dataPanel.add(tfAddress, null);
	dataPanel.add(tfIdentificationNumber, null);
	dataPanel.add(tfBirthdate, null);
	dataPanel.add(tfName, null);
	dataPanel.add(tfLastName, null);
	dataPanel.add(lblPhoto, null);
	dataPanel.add(cbIdentificationTypes, null);
	dataPanel.add(cbBloodGroup, null);
	dataPanel.add(cbRHFactor, null);
	dataPanel.add(cbSex, null);
	this.add(dataPanel, BorderLayout.NORTH);
	this.add(listPanel, BorderLayout.CENTER);
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	btnModifyLicense.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
					    btnModifyLicense_actionPerformed(e);
					}

				    }
	);
	listPanel.setBounds(new Rectangle(5, 300, 680, 225));
	btnSaveDriverForm.setBounds(new Rectangle(630, 155, 30, 25));
	btnSaveDriverForm.addActionListener(new ActionListener() {

					 public void actionPerformed(ActionEvent e) {
					     btnSaveDriverForm_actionPerformed(e);
					 }

				     }
	);
	btnModifyDriverForm.setBounds(new Rectangle(605, 155, 30, 25));
	btnModifyDriverForm.addActionListener(new ActionListener() {

					   public void actionPerformed(ActionEvent e) {
					       btnModifyDriverForm_actionPerformed(e);
					   }

				       }
	);
	btnCancelDriverForm.setBounds(new Rectangle(660, 155, 25, 25));
	btnCancelDriverForm.addActionListener(new ActionListener() {

					   public void actionPerformed(ActionEvent e) {
					       btnCancelDriverForm_actionPerformed(e);
					   }

				       }
	);
	btnPrintLicense.addActionListener(new ActionListener() {

				       public void actionPerformed(ActionEvent e) {
					   btnPrintLicense_actionPerformed(e);
				       }

				   }
	);
	btnPrintDriverForm.addActionListener(new ActionListener() {

					  public void actionPerformed(ActionEvent e) {
					      btnPrintDriverForm_actionPerformed(e);
					  }

				      }
	);
	btnPrintBackDriverForm.addActionListener(new ActionListener() {

					      public void actionPerformed(ActionEvent e) {
						  btnPrintBackDriverForm_actionPerformed(e);
					      }

					  }
	);
	btnAddLicense.addActionListener(new ActionListener() {

				     public void actionPerformed(ActionEvent e) {
					 btnAddLicense_actionPerformed(e);
				     }

				 }
	);
	btnRadiograms.addActionListener(new ActionListener() {

				     public void actionPerformed(ActionEvent e) {
					 btnRadiograms_actionPerformed(e);
				     }

				 }
	);
	lblPhoto.addMouseListener(new MouseAdapter() {

			       public void mouseClicked(MouseEvent e) {
				   if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
				   } else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON3 && btnSaveDriverForm.isEnabled()) {
				       loadImage();
				   }
			       }

			   }
	);
	cbIdentificationTypes.autoSize();
	cbBloodGroup.autoSize();
	cbRHFactor.autoSize();
	cbSex.autoSize();
	addButton(btnClose);
	addButton(btnAddLicense);
	addButton(btnModifyLicense);
	addButton(btnPrintLicense);
	addButton(btnPrintDriverForm);
	addButton(btnPrintBackDriverForm);
	cbIdentificationTypes.setSelectedValue("D.N.I.");
	/*cbIdentificationTypes.setEnabled(false);
	tfLicenseNumber.setEnabled(false);*/
	cbIdentificationTypes.setEnabled(true);
	tfLicenseNumber.setEnabled(true);
	loadBloodGroupCombo();
	loadRHFactorCombo();
	loadSexCombo();
	setHeaderList();
	btnRadiograms.setText("Radiogramas\nRecibidos");
	btnRadiograms.setToolTipText("Abre la ventana de Radiogramas recibidos");
	btnPrintDriverForm.setToolTipText("Imprimir Ficha");
	btnPrintLicense.setToolTipText("Imprimir Licencia");
	btnPrintBackDriverForm.setToolTipText("Imprimir Dorso de la Ficha");
	lblPhoto.setToolTipText("Click con el botón derecho para cargar una nueva foto");
    }

    private void loadImage() {
	ImageCropper cropper = new ImageCropper(lblPhoto, true);
	ExtendedInternalFrame k = new ExtendedInternalFrame("Foto del Conductor");
	k.setCentralPanel(cropper);
	k.show();
    }

    private void loadSexCombo() {
	cbSex.getCombo().addItem("M", "1", "0");
	cbSex.getCombo().addItem("F", "2", "0");
    }

    private void loadBloodGroupCombo() {
	cbBloodGroup.getCombo().addItem("A", "1", "0");
	cbBloodGroup.getCombo().addItem("B", "2", "0");
	cbBloodGroup.getCombo().addItem("AB", "3", "0");
	cbBloodGroup.getCombo().addItem("0", "4", "0");
    }

    private void loadRHFactorCombo() {
	cbRHFactor.getCombo().addItem("RH (+)", "1", "0");
	cbRHFactor.getCombo().addItem("RH (-)", "2", "0");
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("FICHA DE LICENCIA DE CONDUCIR");
	getParentInternalFrame().getGeneralButtons().addButton(btnRadiograms);
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	btnModifyLicense.setEnabled(true);
	btnPrintLicense.setEnabled(true);
	btnAddLicense.setEnabled(false);
	parentList.refresh();
	getParentInternalFrame().close();
    }

    public void setDriverSelected(Driver _driverSelected) {
	driverSelected = _driverSelected;
	driverResguardo = driverSelected;
	if (driverSelected.getIdDriver() != -1) {
	    driverSelected.retrieveData();
	    driverResguardo.retrieveData();
	    loadDataDriver(true);
	    refreshLicenses();
	    if (LibSQL.getInt("licenses.getradiogramsqty", "" + driverSelected.getIdIdentificationType() + "," + driverSelected.getIdentificationNumber()) > 0) {
		btnRadiograms.setEnabled(true);
	    } else {
		btnRadiograms.setEnabled(false);
	    }
	    btnModifyDriverForm.setEnabled(true);
	    btnSaveDriverForm.setEnabled(false);
	    btnCancelDriverForm.setEnabled(false);
	    btnAddLicense.setEnabled(true);
	    btnPrintDriverForm.setEnabled(true);
	} else {
	    btnModifyDriverForm.setEnabled(false);
	    btnSaveDriverForm.setEnabled(true);
	    btnCancelDriverForm.setEnabled(false);
	    btnAddLicense.setEnabled(false);
	    btnPrintDriverForm.setEnabled(false);
	}
	btnModifyLicense.setEnabled(false);
	btnPrintLicense.setEnabled(false);
    }

    private void loadDataDriver(boolean _original) {
	if(_original){
	    tfLastName.setValue(driverSelected.getDriverForm().getLastname());
	    tfName.setValue(driverSelected.getDriverForm().getName());
	    tfBirthdate.setValue(Proced.setFormatDate(driverSelected.getDriverForm().getBirthdate(), true));
	    tfIdentificationNumber.setValue("" + driverSelected.getDriverForm().getIdentificationnumber());
	    cbSex.setSelectedValue(driverSelected.getSex());
	    tfAddress.setValue(driverSelected.getDriverForm().getAddress());
	    tfNationality.setValue(driverSelected.getDriverForm().getNationality());
	    tfClass.setValue("" + driverSelected.getDriverForm().getKind());
	    cbBloodGroup.setSelectedValue(driverSelected.getDriverForm().getBloodgroup());
	    cbRHFactor.setSelectedValue(driverSelected.getDriverForm().getRhfactor());
	    tfLicenseNumber.setValue("" + driverSelected.getDriverForm().getIdentificationnumber());
	    driverSelected.getDriverForm().retrievePicture();
	    photo = driverSelected.getDriverForm().getPhotoImage();
	    //IMGLib.escalaImg(driverSelected.getDriverForm().getPhotoImage(), lblPhoto);
	    lblPhoto.setImage(driverSelected.getDriverForm().getPhotoImage());
	    driverSelected.getDriverForm().getLicense().retrieveData();
	    driverSelected.getDriverForm().getLicense().retrievePicture();
	    licensePhoto = driverSelected.getDriverForm().getLicense().getPhotoImage();
	}else{
	    tfLastName.setValue(driverResguardo.getDriverForm().getLastname());
	    tfName.setValue(driverResguardo.getDriverForm().getName());
	    tfBirthdate.setValue(Proced.setFormatDate(driverResguardo.getDriverForm().getBirthdate(), true));
	    tfIdentificationNumber.setValue("" + driverResguardo.getDriverForm().getIdentificationnumber());
	    cbSex.setSelectedValue(driverResguardo.getSex());
	    tfAddress.setValue(driverResguardo.getDriverForm().getAddress());
	    tfNationality.setValue(driverResguardo.getDriverForm().getNationality());
	    tfClass.setValue("" + driverResguardo.getDriverForm().getKind());
	    cbBloodGroup.setSelectedValue(driverResguardo.getDriverForm().getBloodgroup());
	    cbRHFactor.setSelectedValue(driverResguardo.getDriverForm().getRhfactor());
	    tfLicenseNumber.setValue("" + driverResguardo.getDriverForm().getIdentificationnumber());
	    driverResguardo.getDriverForm().retrievePicture();
	    photo = driverResguardo.getDriverForm().getPhotoImage();
	    //IMGLib.escalaImg(driverResguardo.getDriverForm().getPhotoImage(), lblPhoto);
	    lblPhoto.setImage(driverResguardo.getDriverForm().getPhotoImage());
	    driverResguardo.getDriverForm().getLicense().retrieveData();
	    driverResguardo.getDriverForm().getLicense().retrievePicture();
	    licensePhoto = driverResguardo.getDriverForm().getLicense().getPhotoImage();
	}	
	setFormEnabled(false);
    }

    private void setFormEnabled(boolean _valor) {
	if(_valor){
	    tfLastName.setEnabled(_valor);
	    tfName.setEnabled(_valor);
	    tfNationality.setEnabled(_valor);
	    tfIdentificationNumber.setEnabled(!_valor);
	    cbSex.setEnabled(_valor);
	    tfBirthdate.setEnabled(_valor);
	    tfClass.setEnabled(_valor);
	    tfAddress.setEnabled(_valor);
	    cbBloodGroup.setEnabled(_valor);
	    cbRHFactor.setEnabled(_valor);
	    tfLicenseNumber.setEnabled(!_valor);
	    cbIdentificationTypes.setEnabled(!_valor);    
	}else{
	    tfLastName.setEnabled(_valor);
	    tfName.setEnabled(_valor);
	    tfNationality.setEnabled(_valor);
	    tfIdentificationNumber.setEnabled(_valor);
	    cbSex.setEnabled(_valor);
	    tfBirthdate.setEnabled(_valor);
	    tfClass.setEnabled(_valor);
	    tfAddress.setEnabled(_valor);
	    cbBloodGroup.setEnabled(_valor);
	    cbRHFactor.setEnabled(_valor);
	    tfLicenseNumber.setEnabled(_valor);
	    cbIdentificationTypes.setEnabled(_valor);    
	}
	
	
    }

    public void setParentList(DriversList _parentList) {
	parentList = _parentList;
    }

    private void btnModifyLicense_actionPerformed(ActionEvent e) {
	if (!dataRow.isEmpty()) {
	    driverSelected.getDriverForm().getLicense().setIdlicense(Integer.parseInt(dataRow.elementAt(0).toString()));
	    driverSelected.getDriverForm().getLicense().retrieveData();
	    licensesMgmt = new LicensesMgmt();
	    licensesMgmt.setDriverFormSelected(driverSelected.getDriverForm());
	    licensesMgmt.loadLicense(false);
	    licensesMgmt.setParentList(this);
	    ExtendedInternalFrame licensesMgmtContainer = new ExtendedInternalFrame("Licencia de Conducir");
	    licensesMgmtContainer.setCentralPanel(licensesMgmt);
	    licensesMgmtContainer.show();
	}
    }

    private void btnAddLicense_actionPerformed(ActionEvent e) {
	driverSelected.getDriverForm().retrieveDataWithIdDriver();
	licensesMgmt = new LicensesMgmt();
	licensesMgmt.setDriverFormSelected(driverSelected.getDriverForm());
	licensesMgmt.loadLicense(true);
	licensesMgmt.setParentList(this);
	ExtendedInternalFrame licensesMgmtContainer = new ExtendedInternalFrame("Licencia de Conducir");
	licensesMgmtContainer.setCentralPanel(licensesMgmt);
	licensesMgmtContainer.show();
	driverSelected.getDriverForm().getLicense().retrieveData();
	driverSelected.getDriverForm().getLicense().retrievePicture();
	licensePhoto = driverSelected.getDriverForm().getLicense().getPhotoImage();
    }

    private void loadDriverSelected() {
	driverSelected.setIdIdentificationType(Integer.parseInt("" + cbIdentificationTypes.getSelectedValue()));
	driverSelected.setIdentificationNumber(Integer.parseInt("" + tfIdentificationNumber.getString()));
	driverSelected.setLastname(tfLastName.getString());
	driverSelected.setName(tfName.getString());
	driverSelected.setAddress(tfAddress.getString());
	driverSelected.setBirthdate(Proced.setFormatDate(tfBirthdate.getString(), false));
	driverSelected.setSex("" + cbSex.getSelectedValue());
	driverSelected.setNationality(tfNationality.getString());
	driverSelected.setKind(Integer.parseInt("" + tfClass.getString()));
	driverSelected.setBloodgroup("" + cbBloodGroup.getSelectedItem());
	driverSelected.setRhfactor("" + cbRHFactor.getSelectedItem());
	if(lblPhoto.getImage() != null){
	    driverSelected.setPhoto(LibIMG.getBytesFromImage(lblPhoto.getImage()));    
	    driverSelected.setPhotoImage(lblPhoto.getImage());
	}
	//driverResguardo = driverSelected;
	
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	// idlicense
	headerList.addElement("*");
	// iddriverform
	headerList.addElement(Environment.lang.getProperty("Vigente"));
	headerList.addElement(Environment.lang.getProperty("StartDate"));
	headerList.addElement(Environment.lang.getProperty("ExpirationDate"));
	headerList.addElement("*");
	// licensenumber
	headerList.addElement("*");
	// name
	headerList.addElement("*");
	// lastname
	headerList.addElement("*");
	// birthdate
	headerList.addElement("*");
	// identificationtype
	headerList.addElement("*");
	// identificationnumber
	headerList.addElement("*");
	// address
	headerList.addElement(Environment.lang.getProperty("Category"));
	headerList.addElement("*");
	// nationality
	headerList.addElement("*");
	// class
	headerList.addElement("*");
	// boodgroup
	headerList.addElement("*");
	// rhfactor
	headerList.addElement("*");
	// authorizatedby
	headerList.addElement("*");
	// estado
	headerList.addElement(Environment.lang.getProperty("Years"));
	headerList.addElement(Environment.lang.getProperty("Price"));
	headerList.addElement(Environment.lang.getProperty("Amount"));
	headerList.addElement("*");
	// photo
	headerList.addElement("*");
	// idlicensestatus
	headerList.addElement("*");
	// licensestatus
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent e) {
					       if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						   loadObjectSelected();
					       } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON3) {
						   int index = listPanel.getTable().rowAtPoint(e.getPoint());
						   listPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
					       }
					   }

				       }
	);
	String params = "-1";
	listPanel.setParams("licenses.getAllLicenses", params, headerList);
    }

    public void refreshLicenses() {
	String params = "" + driverSelected.getDriverForm().getIddriverform();
	listPanel.refresh(params);
	btnModifyLicense.setEnabled(false);
	btnPrintLicense.setEnabled(false);
    }

    private void loadObjectSelected() {
	if (!dataRow.isEmpty()) {
	    License _license = new License(Integer.parseInt("" + dataRow.elementAt(0)));
	    driverSelected.getDriverForm().setLicense(_license);
	    _license.setIddriverform(driverSelected.getDriverForm().getIddriverform());
	    btnModifyLicense.setEnabled(dataRow.elementAt(2).equals("SI"));
	    btnPrintLicense.setEnabled(true);
	} else {
	    btnModifyLicense.setEnabled(false);
	    btnPrintLicense.setEnabled(false);
	}
    }

    private void btnModifyDriverForm_actionPerformed(ActionEvent e) {
	btnModifyDriverForm.setEnabled(false);
	btnSaveDriverForm.setEnabled(true);
	btnCancelDriverForm.setEnabled(true);
	btnPrintDriverForm.setEnabled(false);
	btnAddLicense.setEnabled(false);
	setFormEnabled(true);
    }

    private void btnSaveDriverForm_actionPerformed(ActionEvent e) {
	if (saveData()) {
	    btnModifyDriverForm.setEnabled(true);
	    btnSaveDriverForm.setEnabled(false);
	    btnCancelDriverForm.setEnabled(false);
	    btnAddLicense.setEnabled(true);
	    btnPrintDriverForm.setEnabled(true);
	    setFormEnabled(false);
	} else {
	    switch (error) {
		case 1 :
		    Advisor.messageBox("El campo Apellido está vacio", "Aviso");
		    break;
		case 2 :
		    Advisor.messageBox("El campo Nombre está vacio", "Aviso");
		    break;
		case 3 :
		    Advisor.messageBox("El campo Nacionalidad está vacio", "Aviso");
		    break;
		case 4 :
		    Advisor.messageBox("El campo Nº de DNI está vacio", "Aviso");
		    break;
		case 5 :
		    Advisor.messageBox("El campo Fecha de Nac. está vacio", "Aviso");
		    break;
		case 6 :
		    Advisor.messageBox("El campo Clase está vacio", "Aviso");
		    break;
		case 7 :
		    Advisor.messageBox("El campo Dirección está vacio", "Aviso");
		    break;
		case -1 :
		    Advisor.messageBox("Ocurrio un error al grabar los datos", "Error");
		    break;
		case -2 :
		    Advisor.messageBox("No es posible generar la Licencia, porque el\nconductor esta afectado por un Radiograma", "Aviso!");
		    break;
	    }
	}
    }

    public boolean saveData() {
	boolean result = control();
	if (result) {
	    loadDriverSelected();
	    int res = driverSelected.saveData();
	    if (res > 0) {
		driverResguardo = driverSelected;
		driverResguardo.setIdDriver(driverSelected.getIdDriver());
		driverResguardo.retrieveData();
		tfLicenseNumber.setValue("" + driverSelected.getIdentificationNumber());
		result = true;
	    } else {
		error = res;
		//error al grabar  los datos
		result = false;
	    }
	}
	return result;
    }

    private boolean control() {
	error = 0;
	boolean result = true;
	if (tfLastName.getString().equals("")) {
	    result = false;
	    error = 1;
	} else if (tfName.getString().equals("")) {
	    result = false;
	    error = 2;
	} else if (tfNationality.getString().equals("")) {
	    result = false;
	    error = 3;
	} else if (tfIdentificationNumber.getString().equals("")) {
	    result = false;
	    error = 4;
	} else if (tfBirthdate.getString().equals("")) {
	    result = false;
	    error = 5;
	} else if (tfClass.getString().equals("")) {
	    result = false;
	    error = 6;
	} else if (tfAddress.getString().equals("")) {
	    result = false;
	    error = 7;
	}
	return result;
    }

    private void btnCancelDriverForm_actionPerformed(ActionEvent e) {
	loadDataDriver(false);
	btnModifyDriverForm.setEnabled(true);
	btnSaveDriverForm.setEnabled(false);
	btnCancelDriverForm.setEnabled(false);
	btnPrintDriverForm.setEnabled(true);
	btnAddLicense.setEnabled(true);
	setFormEnabled(false);
    }

    private void btnRadiograms_actionPerformed(ActionEvent e) {
	RadiogramByDriverList radiogramsByDriverList = new RadiogramByDriverList();
	radiogramsByDriverList.setDriverSelected(driverSelected);
	ExtendedInternalFrame RadiogramsListContainer = new ExtendedInternalFrame("Radiogramas del conductor: " + driverSelected.getLastname() + ", " + driverSelected.getName());
	RadiogramsListContainer.setCentralPanel(radiogramsByDriverList);
	RadiogramsListContainer.show();
    }

    private void btnPrintLicense_actionPerformed(ActionEvent e) {
	BasicReport report = new BasicReport(DriversFormMgmt.class.getResource("xml/license.xml"));
	report.addTableModel("licenses.xmlGetLicense", "" + driverSelected.getDriverForm().getLicense().getIdlicense());
	driverSelected.getDriverForm().getLicense().retrieveData();
	driverSelected.getDriverForm().getLicense().retrievePicture();
	report.setProperty("photo", driverSelected.getDriverForm().getLicense().getPhotoImage());
	BarCode code = new BarCode();
	report.setProperty("barcode", code.getBarCodeEAN13(driverSelected.getDriverForm().getLicense().getBarCode()));
	report.doReport();
    }

    private void btnPrintDriverForm_actionPerformed(ActionEvent e) {
	BasicReport report = new BasicReport(DriversFormMgmt.class.getResource("xml/driverform.xml"));
	report.addTableModel("licenses.xmlGetDriverForm", "" + driverSelected.getDriverForm().getIddriverform());
	report.setProperty("photo", photo);
	report.doReport();
    }

    private void btnPrintBackDriverForm_actionPerformed(ActionEvent e) {
	String n = JOptionPane.showInputDialog("Ingrese la cantidad de Vencimientos\ndel dorso de la Ficha", 1);
	BasicReport report = new BasicReport(DriversFormMgmt.class.getResource("xml/backdriverform.xml"));
	report.addTableModel("licenses.xmlGetDriverForm", "" + driverSelected.getDriverForm().getIddriverform());
	report.setProperty("photo", photo);
	report.doReport();
    }

}
