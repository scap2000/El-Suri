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
 * PersonsMgmt.java
 *
 * */
package org.digitall.common.resourcescontrol.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import javax.imageio.ImageIO;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.components.AddressList;
import org.digitall.common.components.PanelAddress;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.resourcescontrol.classes.Employees;
import org.digitall.common.resourcescontrol.classes.PersonSkills;
import org.digitall.common.resourcescontrol.classes.Skills;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.UploadPictureButton;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.image.ImageFileView;
import org.digitall.lib.image.ImageFilter;
import org.digitall.lib.image.ImagePreview;
import org.digitall.lib.image.LibIMG;
import org.digitall.lib.org.Companies;
import org.digitall.lib.org.Persons;
import org.digitall.lib.sql.LibSQL;

public class PersonsMgmt extends BasicPrimitivePanel {

    private BasicPanel panelData = new BasicPanel();
    private TFInput tfIdentifcationNumber = new TFInput(DataTypes.STRING, "IdentificationNumber", false);
    private TFInput tfName = new TFInput(DataTypes.STRING, "Name", false);
    private TFInput tfBirthDate = new TFInput(DataTypes.DATE, "Birthdate", false);
    private TFInput tfDescription = new TFInput(DataTypes.STRING, "Description", false);
    private TFInput tfTitle = new TFInput(DataTypes.STRING, "Title", false);
    private TFInput tfLastName = new TFInput(DataTypes.STRING, "LastName", false);
    private CBInput cbIdentifiactionType = new CBInput(CachedCombo.IDENTIFICATION_TABS, "Identification");
    private CBInput cbPrefix = new CBInput(CachedCombo.PREFIX_TABS, "Prefix");
    private CBInput cbFormatView = new CBInput(CachedCombo.FORMATVIEW_TABS, "FormatView");
    private CBInput cbSuffix = new CBInput(CachedCombo.SUFFIXPERSON_TABS, "Suffix");
    private CBInput cbCommunicationType = new CBInput(CachedCombo.COMMUNICATIONTYPE_TABS, "CommunicationType");
    private CBInput cbContactType = new CBInput(CachedCombo.CONTACTTYPE_TABS, "ContactType");
    private CBInput cbSkill = new CBInput(CachedCombo.SKILLPERSON_TABS, "Skill", true);
    private CBInput cbCivilState = new CBInput(CachedCombo.CIVILSTATE_TABS, "CivilState", false);
    private CBInput cbProfession = new CBInput(CachedCombo.PROFESSION_TABS, "Profession", false);
    private SaveDataButton btnSaveData = new SaveDataButton();
    private BasicPanel panelPhoto = new BasicPanel();
    private BasicPanel panelLogo = new BasicPanel();
    private BasicPanel jPanel1 = new BasicPanel();
    private BasicPanel jPanel2 = new BasicPanel();
    private JTabbedPane tabbedPane = new JTabbedPane();
    private PanelAddress panelAddress = new PanelAddress();
    private boolean addAction = true;
    private BasicPanel jPanel3 = new BasicPanel();
    private int[] sizeColumnList = { 509 };
    private Vector dataRowList = new Vector();
    private GridPanel skillListPanel = new GridPanel(30, sizeColumnList, "Habilidades", dataRowList);
    private Vector headerSkillList = new Vector();
    private BasicPanel panelSkill = new BasicPanel();
    private AssignButton btnAssignSkill = new AssignButton(true);
    private DeleteButton btnDeleteSkill = new DeleteButton();
    private CloseButton btnClose = new CloseButton();
    private AcceptButton btnAccept = new AcceptButton();
    private BasicPanel jPanel4 = new BasicPanel();
    private BasicPanel jPanel5 = new BasicPanel();
    private BasicCheckBox chkEmployee = new BasicCheckBox();
    private TFInput tfCompany = new TFInput(DataTypes.STRING, "Company", false);
    private TFInput tfStartDate = new TFInput(DataTypes.DATE, "From", false);
    private TFInput tfEndDate = new TFInput(DataTypes.DATE, "To", false);
    private TFInput tfFileNumber = new TFInput(DataTypes.INTEGER, "DocketNumber", false);
    private TFInput tfFileInternalNumber = new TFInput(DataTypes.INTEGER, "DocketInternalNumber", false);
    private CBInput cbContractType = new CBInput(CachedCombo.CONTRACTTYPE, "ContractType");
    private Persons person;
    private Skills objSkill;
    private PersonSkills personSkill = new PersonSkills();
    private PersonsList parentList;
    private Employees employee;
    private BasicCheckBox chkSkill = new BasicCheckBox();
    private CBInput cbCostsCentre = new CBInput(CachedCombo.COSTSCENTRE, "CostsCentre");
    private CBInput cbSex = new CBInput(0, "Sex");
    private String userName;
    private UploadPictureButton btnUpload = new UploadPictureButton();
    private File fotoFile;
    private final int fotoWidthLimit = 640;
    private final int fotoHeightLimit = 480;
    private final long fotoLengthLimit = 65536;
    private BasicLabel lblPhoto = new BasicLabel();
    private BasicPanel jPanel6 = new BasicPanel();
    private BorderLayout borderLayout1 = new BorderLayout();
    private BasicLabel jLabel1 = new BasicLabel();
    private GridLayout gridLayout1 = new GridLayout();
    private BufferedImage photo = null;

    public PersonsMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout(null);
	this.setSize(new Dimension(559, 362));
	this.setPreferredSize(new Dimension(565, 330));
	panelData.setLayout(null);
	btnSaveData.setBounds(new Rectangle(465, 330, 40, 25));
	btnSaveData.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       btnSaveData_actionPerformed(e);
				   }

			       }
	);
	tfIdentifcationNumber.setBounds(new Rectangle(125, 60, 150, 35));
	tfName.setBounds(new Rectangle(220, 15, 140, 35));
	tfBirthDate.setBounds(new Rectangle(315, 60, 85, 35));
	tfDescription.setBounds(new Rectangle(15, 240, 520, 35));
	jPanel3.setLayout(null);
	jPanel3.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	skillListPanel.setBounds(new Rectangle(7, 85, 530, 190));
	panelSkill.setBounds(new Rectangle(7, 10, 530, 65));
	panelSkill.setLayout(null);
	cbSkill.setBounds(new Rectangle(30, 20, 325, 35));
	btnAssignSkill.setBounds(new Rectangle(430, 30, 40, 25));
	btnAssignSkill.addActionListener(new ActionListener() {

				      public void actionPerformed(ActionEvent e) {
					  btnAssignSkill_actionPerformed(e);
				      }

				  }
	);
	btnDeleteSkill.setBounds(new Rectangle(480, 30, 40, 25));
	btnDeleteSkill.addActionListener(new ActionListener() {

				      public void actionPerformed(ActionEvent e) {
					  btnDeleteSkill_actionPerformed(e);
				      }

				  }
	);
	tfLastName.setBounds(new Rectangle(95, 15, 115, 35));
	btnClose.setBounds(new Rectangle(515, 330, 40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	jPanel4.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	jPanel4.setLayout(null);
	jPanel5.setBounds(new Rectangle(315, 50, 215, 215));
	jPanel5.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	chkEmployee.setText("Habilitar para carga");
	chkEmployee.setBounds(new Rectangle(315, 20, 215, 18));
	chkEmployee.setFont(new Font("Default", 0, 11));
	chkEmployee.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       chkEmployee_actionPerformed(e);
				   }

			       }
	);
	tfCompany.setBounds(new Rectangle(20, 20, 265, 35));
	tfStartDate.setBounds(new Rectangle(20, 125, 95, 35));
	tfEndDate.setBounds(new Rectangle(195, 125, 90, 35));
	tfFileNumber.setBounds(new Rectangle(20, 178, 110, 35));
	tfFileInternalNumber.setBounds(new Rectangle(165, 178, 120, 35));
	cbContractType.setBounds(new Rectangle(20, 230, 265, 35));
	chkSkill.setText("Habilitar para carga");
	chkSkill.setBounds(new Rectangle(155, 15, 150, 15));
	chkSkill.setFont(new Font("Default", 0, 11));
	chkSkill.setSelected(true);
	chkSkill.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    chkSkill_actionPerformed(e);
				}

			    }
	);
	//cbCostsCentre.setBounds(new Rectangle(20, 73, 265, 35));
	cbSex.setBounds(new Rectangle(370, 15, 75, 35));
	btnUpload.setBounds(new Rectangle(215, 260, 40, 35));
	cbProfession.setBounds(new Rectangle(245, 150, 290, 35));
	tfTitle.setBounds(new Rectangle(15, 195, 520, 35));
	cbCivilState.setBounds(new Rectangle(430, 60, 105, 35));
	cbContactType.setBounds(new Rectangle(15, 150, 105, 35));
	panelPhoto.setBounds(new Rectangle(10, 10, 250, 245));
	panelPhoto.setLayout(null);
	panelPhoto.setBorder(BorderPanel.getBorderPanel("Foto", Color.black, Color.black));
	panelLogo.setBounds(new Rectangle(285, 10, 250, 240));
	panelLogo.setLayout(null);
	panelLogo.setBorder(BorderPanel.getBorderPanel("Logo", Color.black, Color.black));
	tabbedPane.setBounds(new Rectangle(7, 8, 550, 315));
	jPanel1.setLayout(null);
	jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	jPanel2.setLayout(borderLayout1);
	jPanel2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	cbIdentifiactionType.setBounds(new Rectangle(15, 60, 95, 35));
	cbPrefix.setBounds(new Rectangle(15, 15, 70, 35));
	cbFormatView.setBounds(new Rectangle(15, 105, 370, 35));
	cbSuffix.setBounds(new Rectangle(455, 15, 80, 35));
	cbCommunicationType.setBounds(new Rectangle(405, 105, 130, 35));
	panelData.add(cbSex, null);
	panelData.add(cbCivilState, null);
	panelData.add(tfTitle, null);
	panelData.add(cbProfession, null);
	panelData.add(tfLastName, null);
	panelData.add(cbCommunicationType, null);
	panelData.add(cbSuffix, null);
	panelData.add(cbFormatView, null);
	panelData.add(cbPrefix, null);
	panelData.add(tfBirthDate, null);
	panelData.add(tfName, null);
	panelData.add(cbIdentifiactionType, null);
	panelData.add(tfIdentifcationNumber, null);
	panelData.add(cbContactType, null);
	panelData.add(tfDescription, null);
	jPanel1.add(btnUpload, null);
	panelPhoto.add(lblPhoto, null);
	jPanel1.add(panelPhoto, null);
	jPanel1.add(panelLogo, null);
	tabbedPane.addTab("Datos Personales", panelData);
	jPanel6.add(jLabel1, null);
	jPanel2.add(jPanel6, BorderLayout.NORTH);
	jPanel2.add(panelAddress, BorderLayout.CENTER);
	tabbedPane.addTab("Domicilio", jPanel2);
	tabbedPane.addTab("Foto & Logo", jPanel1);
	tabbedPane.addTab("Habilidades", jPanel3);
	tabbedPane.addTab("Datos Empleado", jPanel4);
	//panelSkill.add(chkSkill, null);
	panelSkill.add(btnDeleteSkill, null);
	panelSkill.add(btnAssignSkill, null);
	panelSkill.add(cbSkill, null);
	panelSkill.setBorder(BorderPanel.getBorderPanel("Agregar Habilidad", Color.blue, Color.black));
	jPanel3.add(panelSkill, null);
	jPanel3.add(skillListPanel, null);
	//jPanel4.add(cbCostsCentre, null);
	jPanel4.add(cbContractType, null);
	jPanel4.add(tfFileInternalNumber, null);
	jPanel4.add(tfFileNumber, null);
	jPanel4.add(tfEndDate, null);
	jPanel4.add(tfStartDate, null);
	jPanel4.add(tfCompany, null);
	jPanel4.add(chkEmployee, null);
	jPanel4.add(jPanel5, null);
	this.add(tabbedPane, null);
	this.addButton(btnClose);
	this.addButton(btnSaveData);
	this.addButton(btnAccept);
	cbCommunicationType.autoSize();
	cbIdentifiactionType.autoSize();
	cbFormatView.autoSize();
	cbSuffix.autoSize();
	cbPrefix.autoSize();
	cbContactType.autoSize();
	cbSkill.autoSize();
	cbCivilState.autoSize();
	cbContactType.autoSize();
	cbProfession.autoSize();
	cbContractType.autoSize();
	cbCostsCentre.autoSize();
	cbSex.autoSize();
	btnUpload.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     bimagen_actionPerformed(e);
				 }

			     }
	);
	/**if (param != null) {
	    tabbedPane.setEnabledAt(3, true);
	    setData(param);
	} else {
	    tabbedPane.setEnabledAt(3, false);
	    panelAddress.addComboItemListener();
	}*/
	lblPhoto.setBounds(new Rectangle(15, 20, 220, 205));
	lblPhoto.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	jPanel6.setPreferredSize(new Dimension(10, 40));
	jPanel6.setLayout(gridLayout1);
	jLabel1.setText("Puede ingresar más de una dirección presionando el botón \"Agregar más\"");
	jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
	setHeaderSkillList();
	disableEmployeeData();
	disableSkillData();
	/*tfCompany.setValue(Environment.currentCompany.getName());*/
	tfCompany.setValue(OrganizationInfo.getOrgName());
	tfCompany.setVisible(false);
	cbSex.getCombo().addItem("M", 0);
	cbSex.getCombo().addItem("F", 1);
	cbSex.getCombo().setSelectedIndex(0);
	btnAssignSkill.setToolTipText("Asignar habilidad");
	btnDeleteSkill.setToolTipText("Quitar habilidad");
	btnUpload.setToolTipText("Seleccionar una foto para cargar");
	btnClose.setToolTipText("Cancelar");
        cbIdentifiactionType.setSelectedID(1);
        cbSuffix.setSelectedID(1);
        cbCivilState.setSelectedID(0);
        cbProfession.setSelectedID(0);
        cbCommunicationType.setSelectedID(0);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Complete los datos y presione el botón \"Grabar\"");
    }

    private void bimagen_actionPerformed(ActionEvent e) {
	try {
	    //String filename = File.separator + "jpg";
	    JFileChooser fc = new JFileChooser();
	    //Proced.getLastPath());
	    /** REVISAR LA OBTENCION DE LASTPATH */
	    fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    fc.addChoosableFileFilter(new ImageFilter());
	    fc.setAcceptAllFileFilterUsed(false);
	    //Add custom icons for file types.
	    fc.setFileView(new ImageFileView());
	    //Add the preview pane.
	    fc.setAccessory(new ImagePreview(fc));
	    fc.setMultiSelectionEnabled(false);
	    // Show open dialog; this method does not return until the dialog is closed
	    fc.showOpenDialog(this);
	    fotoFile = fc.getSelectedFile();
	    if (fotoFile != null) {
		//LIMITES: WIDTH: 256, HEIGHT: 256, LENGTH: 64Kb (65535 bytes);
		if (fotoFile.length() < fotoLengthLimit) {
		    photo = ImageIO.read(fotoFile);
		    if ((photo.getWidth() <= fotoWidthLimit) && (photo.getHeight() <= fotoHeightLimit)) {
			LibIMG.escalaImg(photo, lblPhoto);
			if (person != null) { 
			    person.setPhotoImage(photo);
			}
			/*if (LibSQL.saveImageSQL(fotoFile, "org.persons", "photo", "WHERE idperson = " + person.getIdPerson())) {
			    //Advisor.messageBox("Update Success!!","Message");
			    //dispose();
			} else {
			    //Advisor.messageBox("Can't save image", "Error");
			    Advisor.messageBox("No se pudo grabar la imagen", "Error");
			}*/
		    } else
			//Advisor.messageBox("Photo width/height not allowed. Can't be greater than " + fotoWidthLimit + "x" + fotoHeightLimit + " pixels", "Messsage");
			Advisor.messageBox("Tamaño de imagen no permitido. Las dimensiones máximas son " + fotoWidthLimit + "x" + fotoHeightLimit + " píxeles", "Aviso");
		} else
		    //Advisor.messageBox("File length not allowed. Cant' be greater than " + (fotoLengthLimit / 1024.0) + " Kbytes", "Messsage");
		    Advisor.messageBox("Tamaño de archivo no permitido. El tamaño máximo es " + (fotoLengthLimit / 1024.0) + " Kbytes", "Aviso");
	    }
	} catch (IOException x) {
	    Advisor.messageBox(x.getMessage(), "Error");
	    x.printStackTrace();
	}
    }

    private void setHeaderSkillList() {
	headerSkillList.removeAllElements();
	headerSkillList.addElement("*");
	headerSkillList.addElement("*");
	headerSkillList.addElement("Habilidad");
	headerSkillList.addElement("*");
	skillListPanel.getTable().addMouseListener(new MouseAdapter() {

						public void mouseClicked(MouseEvent e) {
						    loadObject();
						    if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {

						    } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						    }
						}

					    }
	);
	skillListPanel.setParams("org.getallPersonSkills", "-1", headerSkillList);
    }

    private void loadObject() {
	objSkill = new Skills(Integer.parseInt("" + dataRowList.elementAt(1)));
	personSkill.setSkill(objSkill);
    }

    public void loadSkillList() {
	String params = "" + person.getIdPerson();
	skillListPanel.refresh(params);
    }

    private void loadSkillCombo() {
	cbSkill.loadJCombo(LibSQL.exFunction("org.getallPersonSkillsAvailable", "" + person.getIdPerson()));
    }

    private void loadEmployeeData() {
	tfStartDate.setValue(Proced.setFormatDate(employee.getStartDate(), true));
	tfEndDate.setValue(Proced.setFormatDate(employee.getEndDate(), true));
	tfFileNumber.setValue("" + employee.getFileNumber());
	tfFileInternalNumber.setValue("" + employee.getFileInternalNumber());
	cbContractType.setSelectedID("" + employee.getIdContractType());
    }

    private void clearEmployeeData() {
	tfStartDate.setValue("");
	tfEndDate.setValue("");
	tfFileNumber.setValue("");
	tfFileInternalNumber.setValue("");
	cbContractType.setSelectedID("");
	chkEmployee.setSelected(false);
	cbCostsCentre.setEnabled(false);
	disableEmployeeData();
    }

    private void loadData() {
	try {
	    addAction = false;
	    tfName.setValue(person.getName());
	    tfLastName.setValue(person.getLastName());
	    cbSuffix.setSelectedID("" + person.getIdSuffix());
	    cbIdentifiactionType.setSelectedID("" + person.getIdIdentificationType());
	    tfIdentifcationNumber.setValue("" + person.getIdentificationNumber());
	    cbPrefix.setSelectedID("" + person.getIdPrefix());
	    cbFormatView.setSelectedID("" + person.getIdFormatView());
	    cbContactType.setSelectedID("" + person.getIdContactType());
	    cbProfession.setSelectedID("" + person.getIdProfession());
	    tfTitle.setValue(person.getTitle());
	    tfBirthDate.setValue(Proced.setFormatDate(person.getBirthDate(), true));
	    cbCivilState.setSelectedID("" + person.getIdCivilState());
	    cbCommunicationType.setSelectedID("" + person.getIdCommunicationType());
	    tfDescription.setValue(person.getDescription());
	    ResultSet rsAddresses = LibSQL.exFunction("org.getPersonAddressByDefault", "" + person.getIdPerson());
	    if (rsAddresses.next()) {
		panelAddress.setData(rsAddresses.getInt("idaddress"), true);
		panelAddress.setParentDesktop(Environment.mainDesktop);
		panelAddress.setObjectParent(person.getIdPerson(), AddressList.PERSONTYPE);
	    }
	    cbSex.setSelectedValue(person.getSex());
	    person.retrievePicture();
	    photo = person.getPhotoImage();
	    LibIMG.escalaImg(person.getPhotoImage(),lblPhoto);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    private int saveEmployeeData() {
	int result = -1;
	int result2 = -1;
	if (employee == null) {
	    employee = new Employees();
	    employee.setPerson(person);
	}
	/*employee.setCompany(Environment.currentCompany);*/
	Companies company = new Companies(-1);
	company.setIdCompanyType(67);
	employee.setCompany(company);
	employee.setEndDate(Proced.setFormatDate(tfEndDate.getString(), false));
	employee.setStartDate(Proced.setFormatDate(tfStartDate.getString(), false));
	employee.setFileInternalNumber(Integer.parseInt("0" + tfFileInternalNumber.getString()));
	employee.setFileNumber(Integer.parseInt("0" + tfFileNumber.getString()));
	employee.setIdContractType(Integer.parseInt("0" + cbContractType.getSelectedValue()));
	if (employee.getIdEmployee() == -1) {
	    result = employee.addData();
	    result2 = LibSQL.getInt("organigrama.addPersonasxDependencia", "1," + employee.getIdEmployee() + ",'Sin cargo jerárquico'");
	} else {
	    result = employee.setData();
	}
	if (result != -1) {
	    //CostsCentre costsCentre = new CostsCentre(Integer.parseInt("" + cbCostsCentre.getSelectedValue()));
	    //costsCentre.setUser(person.getIdPerson());
	}
	return result;
    }

    public boolean saveData() {
	if (tfLastName.getString().length() > 0 && tfName.getString().length() > 0) {
	    if (person == null) {
		person = new Persons();
	    }
	    person.setName(tfName.getString());
	    person.setLastName(tfLastName.getString());
	    person.setIdSuffix(Integer.parseInt(cbSuffix.getSelectedValue().toString()));
	    person.setIdPrefix(Integer.parseInt(cbPrefix.getSelectedValue().toString()));
	    person.setIdFormatView(Integer.parseInt(cbFormatView.getSelectedValue().toString()));
	    person.setIdContactType(Integer.parseInt(cbContactType.getSelectedValue().toString()));
	    person.setIdProfession(Integer.parseInt(cbProfession.getSelectedValue().toString()));
	    person.setTitle(tfTitle.getString());
	    person.setBirthDate(Proced.setFormatDate(tfBirthDate.getString(), false));
	    person.setDigitalSign("");
	    person.setIdCivilState(Integer.parseInt(cbCivilState.getSelectedValue().toString()));
	    person.setIdIdentificationType(Integer.parseInt(cbIdentifiactionType.getSelectedValue().toString()));
	    person.setIdentificationNumber(tfIdentifcationNumber.getString());
	    person.setIdCommunicationType(Integer.parseInt(cbCommunicationType.getSelectedValue().toString()));
	    person.setDescription(tfDescription.getString());
	    person.setSex(cbSex.getSelectedItem().toString());
	    person.setPhotoImage(photo);
	    int result = -1;
	    if (person.getIdPerson() == -1) {
		result = person.addData();
	    } else {
		result = person.setData();
		if (chkEmployee.isSelected()) {
		    result = saveEmployeeData();
		}
	    }
	    if (result != -1) {
		panelAddress.setObjectParent(person.getIdPerson(), AddressList.PERSONTYPE);
		if (panelAddress.getIdAddressAux() == 0) {
		    return panelAddress.saveData("true");
		} else {
		    return true;
		}
	    } else {
		return false;
	    }
	} else {
	    Advisor.messageBox("Debe al menos completar los campos Apellido y Nombre", "Aviso");
	    return false;
	}
    }

    private void disableEmployeeData() {
	if (chkEmployee.isSelected()) {
	    tfStartDate.setEnabled(true);
	    tfEndDate.setEnabled(true);
	    tfFileInternalNumber.setEnabled(true);
	    tfFileNumber.setEnabled(true);
	    cbContractType.setEnabled(true);
	    tfCompany.setEnabled(true);
	    cbCostsCentre.setEnabled(true);
	} else {
	    tfStartDate.setEnabled(false);
	    tfEndDate.setEnabled(false);
	    tfFileInternalNumber.setEnabled(false);
	    tfFileNumber.setEnabled(false);
	    cbContractType.setEnabled(false);
	    tfCompany.setEnabled(false);
	    cbCostsCentre.setEnabled(false);
	}
    }

    private void disableSkillData() {
	if (chkSkill.isSelected()) {
	    cbSkill.setEnabled(true);
	    btnAssignSkill.setEnabled(true);
	    btnDeleteSkill.setEnabled(true);
	    skillListPanel.setEnabled(true);
	} else {
	    cbSkill.setEnabled(false);
	    btnAssignSkill.setEnabled(false);
	    btnDeleteSkill.setEnabled(false);
	    skillListPanel.setEnabled(false);
	}
    }

    private void btnSaveData_actionPerformed(ActionEvent e) {
	if (saveData()) {
	    parentList.refresh();
	    getParentInternalFrame().close();
	}
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    private void btnAssignSkill_actionPerformed(ActionEvent e) {
	if (cbSkill.getSelectedIndex() != -1) {
	    objSkill = new Skills(Integer.parseInt("" + cbSkill.getSelectedValue()));
	    personSkill.setSkill(objSkill);
	    if (personSkill.assignSkill() >= 0) {
		loadSkillCombo();
		loadSkillList();
	    }
	}
    }

    private void btnDeleteSkill_actionPerformed(ActionEvent e) {
	if (!dataRowList.isEmpty()) {
	    if (personSkill.unAssignSkill() != -1) {
		loadSkillCombo();
		loadSkillList();
	    } else {
		Advisor.messageBox(Environment.lang.getProperty("DeleteError"), "Error");
	    }
	}
    }

    public void setPerson(Persons person) {
	this.person = person;
	personSkill.setPerson(person);
	loadSkillList();
	loadSkillCombo();
	loadData();
    }
    //System.out.println("idperson: " + person.getIdPerson());

    private void chkEmployee_actionPerformed(ActionEvent e) {
	if (!tfLastName.getString().equals("") || !tfName.getString().equals("")) {
	    if (person == null) {
		saveData();
	    }
	    disableEmployeeData();
	} else {
	    Advisor.messageBox("Debe completar los datos de la persona", "Persona no registrada");
	    chkEmployee.setSelected(false);
	}
    }

    public void setParentList(PersonsList parentList) {
	this.parentList = parentList;
    }

    public void setEmployee(Employees _employee) {
	employee = _employee;
	if (employee.getIdEmployee() == -1) {
	    clearEmployeeData();
	} else {
	    loadEmployeeData();
	}
    }

    private void chkSkill_actionPerformed(ActionEvent e) {
	if (!tfLastName.getString().equals("") || !tfName.getString().equals("")) {
	    if (person == null) {
		saveData();
		loadSkillCombo();
		loadSkillList();
		personSkill.setPerson(person);
	    }
	    disableSkillData();
	} else {
	    Advisor.messageBox("Debe completar los datos de la persona", "Persona no registrada");
	    chkSkill.setSelected(false);
	}
    }

    public void setUserName(String userName) {
	this.userName = userName;
	//System.out.println(userName);
	/*CostsCentre costsCentre = new CostsCentre(0);
	costsCentre.getDataByUserName(userName);
	cbCostsCentre.setSelectedID("" + costsCentre.getIdCostCentre());*/
    }

}
