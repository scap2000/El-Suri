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
 * PanelPersonalData.java
 *
 * */
package org.digitall.apps.systemmanager;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.digitall.common.systemmanager.ChangePassword;
import org.digitall.common.systemmanager.PanelAddress;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.JEntry;
import org.digitall.lib.components.JIntEntry;
import org.digitall.lib.components.JOutry;
import org.digitall.lib.components.JTFecha;
import org.digitall.lib.components.UploadPictureButton;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicTextField;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.combos.JCombo;
import org.digitall.lib.image.ImageFileView;
import org.digitall.lib.image.ImageFilter;
import org.digitall.lib.image.ImagePreview;
import org.digitall.lib.image.LibIMG;
import org.digitall.lib.sql.LibSQL;

//

public class PanelPersonalData extends BasicContainerPanel {

    private JTabbedPane jTabbedPane1 = new JTabbedPane();
    private BasicPanel panelPersonData = new BasicPanel();
    private BasicPanel panelPersonalPhoto = new BasicPanel();
    private BasicPanel jPanel6 = new BasicPanel();
    private BasicPanel jPanel7 = new BasicPanel();
    private PanelAddress panelWorkAddress = new PanelAddress();
    private PanelAddress panelAddress = new PanelAddress();
    private BasicLabel lblPhoto = new BasicLabel();
    private BasicLabel lblIdentificationType = new BasicLabel();
    private BasicLabel jLabel111 = new BasicLabel();
    private BasicLabel jLabel110 = new BasicLabel();
    private BasicLabel jLabel19 = new BasicLabel();
    private BasicLabel jLabel3 = new BasicLabel();
    private BasicLabel labelcredencial = new BasicLabel();
    private BasicLabel credencial = new BasicLabel();
    private BasicLabel lblUsername = new BasicLabel();
    private BasicLabel lblBornCountry = new BasicLabel();
    private JOutry tfUserName = new JOutry();
    private JIntEntry tfCardNumber = new JIntEntry();
    private JTFecha tfBirthdate = new JTFecha();
    private BasicTextField tfStreetNumber3 = new BasicTextField();
    private BasicTextField tfStreetNumber4 = new BasicTextField();
    private BasicTextField tfBlock1 = new BasicTextField();
    private BasicTextField tfFloor1 = new BasicTextField();
    private BasicTextField tfAppt1 = new BasicTextField();
    private BasicTextField tfStreetNumber6 = new BasicTextField();
    private JEntry tfLastName = new JEntry();
    private JEntry tfFirstName = new JEntry();
    private UploadPictureButton bUpload = new UploadPictureButton();
    private CloseButton bClose = new CloseButton();
    private AcceptButton bAccept = new AcceptButton();
    private ModifyButton bactcredencial = new ModifyButton();
    private ModifyButton bcredencial = new ModifyButton();
    private ModifyButton bModify = new ModifyButton();
    private ModifyButton bChangePass = new ModifyButton();
    private JCombo cbIdentificationType = CachedCombo.getCachedCombo(CachedCombo.IDENTIFICATION_TABS);
    private JCombo cbBornCountry = CachedCombo.getCachedCombo(CachedCombo.COUNTRY_TABS);
    private File fotoFile;
    private final int fotoWidthLimit = 640;
    private final int fotoHeightLimit = 480;
    private final long fotoLengthLimit = 65536;
    private String idpersona = "";
    private final int FRAME = 1;
    private final int INTERNALFRAME = 2;
    private final int DIALOG = 3;
    private int parentType = -1;
    private Component parent;
    private int error = 0;
    private BasicPanel jPanel1 = new BasicPanel();
    private BasicPanel jPanel2 = new BasicPanel();
     

    public PanelPersonalData(BasicInternalFrame _parent, String _idpersona) {
	try {
	    parent = _parent;
	    idpersona = _idpersona;
	    parentType = INTERNALFRAME;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public PanelPersonalData(BasicDialog _parent, String _idpersona) {
	try {
	    parent = _parent;
	    idpersona = _idpersona;
	    parentType = DIALOG;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public PanelPersonalData(JFrame _parent, String _idpersona) {
	try {
	    parent = _parent;
	    idpersona = _idpersona;
	    parentType = FRAME;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(535, 420));
	//panelHeader = new PanelHeader(this.getWidth(),TEXTO,IconTypes.CR_IconHeaderLogo,IconTypes.CRDrilling_IconHeaderLogo);
	//this.add(panelHeader,null);
	jTabbedPane1.setBounds(new Rectangle(5, 35, 525, 320));
	 
	this.add(bModify, null);
	this.add(bcredencial, null);
	this.add(bactcredencial, null);
	this.add(bAccept, null);
	this.add(bClose, null);
	this.add(jTabbedPane1, null);
	if (!idpersona.equals("")) {
	    /** 
	     * Cesar:	Con el id de la persona, hay que capturar el id de la direccion particular
	     * 		y el id de la direccion del tabajo, para luego enciarselos a los paneles
	     *          correspondientes para que ellos seteen los datos
	     * */
	     
	     /**
	      * Nota:
	      * 	a partir del dia 32-01-2008, se va a reemplazr la tabla employees por la tabla employees2.
	      * 	Es decir la tabla employees2 pasará a ser la tabla employees.
	      * */
	    String consulta = "";
	    //String consulta = "Select idpersonal_address From org.employees Where idpeople = " + idpersona;
	    String idAddress = org.digitall.lib.sql.LibSQL.getCampo(consulta);
	    //panelAddress.setData(Integer.parseInt(idAddress), false);
	     consulta = "";
	    //consulta = "Select idwork_address From org.employees Where idpeople = " + idpersona;
	    String idWorkAddress = org.digitall.lib.sql.LibSQL.getCampo(consulta);
	    //panelWorkAddress.setData(Integer.parseInt(idWorkAddress), false);
	}
	jPanel6.setLayout(null);
	jPanel6.setBounds(new Rectangle(2, 34, 750, 313));
	panelPersonalPhoto.setBounds(new Rectangle(310, 5, 205, 285));
	panelPersonalPhoto.setBorder(org.digitall.lib.components.BorderPanel.getBorderPanel("Personal Photo", Color.blue, Color.black));
	panelPersonalPhoto.setLayout(null);
	panelPersonalPhoto.setOpaque(false);
	lblPhoto.setHorizontalAlignment(SwingConstants.CENTER);
	lblPhoto.setHorizontalTextPosition(SwingConstants.CENTER);
	lblPhoto.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	lblPhoto.setBounds(new Rectangle(7, 25, 190, 200));
	bUpload.setMargin(new Insets(2, 5, 2, 5));
	bUpload.setMnemonic('U');
	bUpload.setBounds(new Rectangle(157, 240, 40, 35));
	bUpload.setToolTipText("Upload");
	bUpload.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bimagen_actionPerformed(e);
		    }

		});
	panelPersonData.setBounds(new Rectangle(5, 5, 280, 285));
	panelPersonData.setLayout(null);
	panelPersonData.setBorder(org.digitall.lib.components.BorderPanel.getBorderPanel("Person Data", Color.blue, Color.black));
	bClose.setBounds(new Rectangle(490, 385, 40, 25));
	bClose.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bcerrar_actionPerformed(e);
		    }

		});
	bAccept.setBounds(new Rectangle(435, 385, 40, 25));
	bAccept.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bAccept_actionPerformed(e);
		    }

		});
	bactcredencial.setEnabled(false);
	bactcredencial.setBounds(new Rectangle(395, 345, 35, 25));
	bactcredencial.setMargin(new Insets(2, 5, 2, 2));
	bactcredencial.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bactcredencial_actionPerformed(e);
		    }

		});
	bactcredencial.setVisible(false);
	bcredencial.setMargin(new Insets(2, 2, 2, 14));
	bcredencial.setText("Generar");
	bcredencial.setBounds(new Rectangle(290, 345, 95, 25));
	bcredencial.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bcredencial_actionPerformed(e);
		    }

		});
	bcredencial.setVisible(false);
	tfUserName.setBounds(new Rectangle(15, 35, 200, 20));
	lblUsername.setBounds(new Rectangle(15, 25, 125, 10));
	lblUsername.setText("User Name:");
	lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
	lblUsername.setFont(new Font("Dialog", 1, 11));
	panelWorkAddress.setOpaque(false);
	panelWorkAddress.setLayout(null);
	panelWorkAddress.setBounds(new Rectangle(3, 34, 515, 225));
	panelAddress.setBounds(new Rectangle(3, 34, 515, 225));
	tfStreetNumber3.setBounds(new Rectangle(10, 105, 85, 20));
	tfStreetNumber3.setFont(new Font("Dialog", 1, 11));
	tfStreetNumber3.setForeground(Color.red);
	tfStreetNumber3.setText("12345678910");
	tfStreetNumber4.setBounds(new Rectangle(565, 105, 35, 20));
	tfStreetNumber4.setFont(new Font("Dialog", 1, 11));
	tfStreetNumber4.setForeground(Color.red);
	tfBlock1.setBounds(new Rectangle(75, 215, 155, 20));
	tfBlock1.setForeground(Color.red);
	tfFloor1.setBounds(new Rectangle(270, 220, 135, 20));
	tfFloor1.setFont(new Font("Dialog", 1, 11));
	tfFloor1.setForeground(Color.red);
	tfAppt1.setBounds(new Rectangle(445, 105, 35, 20));
	tfAppt1.setFont(new Font("Dialog", 1, 11));
	tfAppt1.setForeground(Color.red);
	lblBornCountry.setBounds(new Rectangle(15, 245, 165, 10));
	lblBornCountry.setText("Born Country:");
	lblBornCountry.setHorizontalAlignment(SwingConstants.LEFT);
	lblBornCountry.setFont(new Font("Dialog", 1, 11));
	cbBornCountry.setBounds(new Rectangle(15, 255, 250, 20));
	cbBornCountry.setFont(new Font("Dialog", 1, 11));
	jPanel1.setLayout(null);
	jPanel2.setLayout(null);
	//new Rectangle(0, 370, 535, 2));
	//setSize(new Dimension(535, 2));
	bModify.setBounds(new Rectangle(665, 345, 40, 25));
	bModify.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bModify_actionPerformed(e);
		    }

		});
	bModify.setVisible(false);
	bChangePass.setEnabled(false);
	bChangePass.setBounds(new Rectangle(225, 27, 40, 28));
	bChangePass.setMargin(new Insets(2, 5, 2, 2));
	bChangePass.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bChangePass_actionPerformed(e);
		    }

		});
	tfStreetNumber6.setBounds(new Rectangle(10, 145, 330, 20));
	tfStreetNumber6.setFont(new Font("Dialog", 1, 11));
	tfStreetNumber6.setForeground(Color.red);
	tfStreetNumber6.setText("12345678910");
	tfCardNumber.setBounds(new Rectangle(120, 165, 145, 20));
	tfCardNumber.setFont(new Font("Dialog", 1, 11));
	tfLastName.setBounds(new Rectangle(15, 79, 250, 20));
	tfLastName.setFont(new Font("Dialog", 1, 11));
	tfFirstName.setBounds(new Rectangle(15, 125, 250, 20));
	tfFirstName.setFont(new Font("Dialog", 1, 11));
	cbIdentificationType.setBounds(new Rectangle(15, 167, 85, 20));
	cbIdentificationType.setSize(new Dimension(85, 20));
	lblIdentificationType.setText("I. Card:");
	lblIdentificationType.setBounds(new Rectangle(15, 157, 125, 10));
	lblIdentificationType.setFont(new Font("Dialog", 1, 11));
	lblIdentificationType.setHorizontalAlignment(SwingConstants.LEFT);
	jLabel111.setBounds(new Rectangle(15, 113, 145, 10));
	jLabel111.setText("Name:");
	jLabel111.setHorizontalAlignment(SwingConstants.LEFT);
	jLabel111.setFont(new Font("Dialog", 1, 11));
	jLabel110.setBounds(new Rectangle(15, 69, 115, 10));
	jLabel110.setText("Last Name:");
	jLabel110.setFont(new Font("Dialog", 1, 11));
	jLabel19.setBounds(new Rectangle(120, 155, 85, 10));
	jLabel19.setText("Number:");
	jLabel19.setFont(new Font("Dialog", 1, 11));
	jLabel19.setHorizontalAlignment(SwingConstants.LEFT);
	tfBirthdate.setBounds(new Rectangle(15, 211, 85, 20));
	tfBirthdate.setFont(new Font("Dialog", 1, 11));
	jLabel3.setBounds(new Rectangle(15, 201, 85, 10));
	jLabel3.setText("Birthdate:");
	jLabel3.setHorizontalAlignment(SwingConstants.LEFT);
	jLabel3.setFont(new Font("Dialog", 1, 11));
	jPanel7.setLayout(null);
	labelcredencial.setText("Credential of: ");
	labelcredencial.setBounds(new Rectangle(10, 15, 505, 15));
	labelcredencial.setHorizontalAlignment(SwingConstants.CENTER);
	labelcredencial.setHorizontalTextPosition(SwingConstants.CENTER);
	labelcredencial.setFont(new Font("Dialog", 1, 17));
	credencial.setBounds(new Rectangle(10, 40, 505, 200));
	credencial.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	jPanel6.add(panelPersonData, null);
	jPanel6.add(panelPersonalPhoto, null);
	panelPersonalPhoto.add(lblPhoto, null);
	panelPersonalPhoto.add(bUpload, null);
	panelPersonData.add(cbBornCountry, null);
	panelPersonData.add(lblUsername, null);
	panelPersonData.add(tfUserName, null);
	panelPersonData.add(tfLastName, null);
	panelPersonData.add(tfFirstName, null);
	panelPersonData.add(jLabel111, null);
	panelPersonData.add(jLabel110, null);
	panelPersonData.add(tfCardNumber, null);
	panelPersonData.add(cbIdentificationType, null);
	panelPersonData.add(lblIdentificationType, null);
	panelPersonData.add(jLabel19, null);
	panelPersonData.add(tfBirthdate, null);
	panelPersonData.add(jLabel3, null);
	panelPersonData.add(bChangePass, null);
	panelPersonData.add(lblBornCountry, null);
	panelPersonData.add(tfLastName, null);
	panelPersonData.add(tfFirstName, null);
	panelPersonData.add(tfCardNumber, null);
	panelPersonData.add(tfLastName, null);
	jPanel7.add(labelcredencial, null);
	jPanel7.add(credencial, null);
	jTabbedPane1.addTab("Personal Data", jPanel6);
	jTabbedPane1.addTab("Address", jPanel1);
	jPanel2.add(panelWorkAddress, null);
	jTabbedPane1.addTab("Work Address", jPanel2);
	jTabbedPane1.addTab("Credential", jPanel7);
	jPanel1.add(panelAddress, null);
	setearDatos();
	tfLastName.setBackground(Color.white);
	tfFirstName.setBackground(Color.white);
	tfCardNumber.setBackground(Color.white);
	tfBirthdate.setBackground(Color.white);
    }

    private void habilitaPanelPersonalData(boolean _valor) {
	tfLastName.setEditable(_valor);
	tfFirstName.setEditable(_valor);
	tfCardNumber.setEditable(_valor);
	tfBirthdate.setEditable(_valor);
	cbIdentificationType.setEnabled(_valor);
	//cbBornCountry.setEditable(_valor);
	cbBornCountry.setEnabled(_valor);
	//tfUserName.setEditable(_valor);
	bChangePass.setEnabled(_valor);
    }

    private void setearDatos() {
	/** 
	 * Cesar: 	Tener en cuenta que para hacer esta consulta hay que utilizar la nueva 
	 * 		estructura de employees, esto quiere decir que datos como el Nombre
	 *              o el apellido habrá que buscarlo de la tabla Personas
	 * */
	 String consulta = "";
	//String consulta = "select * From org.employees Where idpeople = " + idpersona;
	//System.out.println("consulta: " + consulta);
	ResultSet empleado = org.digitall.lib.sql.LibSQL.exQuery(consulta);
	try {
	    if (empleado.next()) {
		tfLastName.setText(empleado.getString("last_name"));
		tfFirstName.setText(empleado.getString("first_name"));
		tfCardNumber.setText(empleado.getString("identification_number"));
		tfBirthdate.setValue(Proced.setFormatDate(empleado.getString("date_born"), true));
		tfUserName.setText(empleado.getString("user_name"));
		cbIdentificationType.setSelectedID(empleado.getString("ididentification"));
		String bornCountry = org.digitall.lib.sql.LibSQL.getCampo("Select name from tabs.country_tabs Where idcountry = " + empleado.getString("born_country"));
		//Proced.CargaCombo(cbBornCountry, "Select name From tabs.country_tabs", bornCountry);
		cbBornCountry.setSelectedValue(bornCountry);
		habilitaPanelPersonalData(true);
		LibIMG.escalaImg(LibIMG.getImageFromBytes(empleado.getBytes("photo"), idpersona), lblPhoto);
	    }
	} catch (SQLException e) {
	    // TODO
	}
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

    private void bimagen_actionPerformed(ActionEvent e) {
	try {
	    String filename = File.separator + "jpg";
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
		    BufferedImage img = ImageIO.read(fotoFile);
		    //System.out.println("Width, height: " + img.getWidth() + "," + img.getHeight());
		    if ((img.getWidth() < fotoWidthLimit) && (img.getHeight() < fotoHeightLimit)) {
			LibIMG.escalaImg(img, lblPhoto);
			//System.out.println("Length: " + fotoFile.length());
			/** 
			 * Cesar:	Tener en cuenta que la condicion ya no será idpeople, sino idemployee (o idperson)
			 * */
			if (LibSQL.saveImageSQL(fotoFile, "org.employees", "photo", "WHERE idpeople = " + idpersona)) {
			    //org.digitall.lib.components.Advisor.messageBox("Update Success!!","Message");
			    //dispose();
			} else
			    org.digitall.lib.components.Advisor.messageBox("Can't save image", "Error");
		    } else
			org.digitall.lib.components.Advisor.messageBox("File length not allowed. Cant' be greater than " + (fotoLengthLimit / 1024.0) + " Kbytes", "Messsage");
		} else
		    org.digitall.lib.components.Advisor.messageBox("Photo width/height not allowed. Can't be greater than " + fotoWidthLimit + "x" + fotoHeightLimit + " pixels", "Messsage");
	    }
	} catch (IOException x) {
	    x.printStackTrace();
	}
    }

    private void bChangePass_actionPerformed(ActionEvent e) {
	ChangePassword pass = new ChangePassword(tfUserName.getText());
	pass.setModal(true);
	pass.setVisible(true);
    }

    private void bcerrar_actionPerformed(ActionEvent e) {
	dispose();
    }

    private boolean control() {
	if (tfLastName.getText().trim().equals("")) {
	    error = 1;
	    return false;
	} else if (tfFirstName.getText().trim().equals("")) {
	    error = 2;
	    return false;
	} else
	    return true;
    }

    private void bAccept_actionPerformed(ActionEvent e) {
	if (control()) {
	    String update = getConsulta();
	    //panelAddress.saveData("true", false);
	    //update += " UPDATE org.employees SET idpersonal_address = " + panelAddress.getIdAddress() + " WHERE idpeople = 0" + idpersona + ";";
	    //panelWorkAddress.saveData("true", false);
	    //update += " UPDATE org.employees SET idwork_address = " + panelWorkAddress.getIdAddress() + " WHERE idpeople = 0" + idpersona + ";";
	    /** Ricardo
             * update += panelAddress.getConsulta();
               update += panelWorkAddress.getConsulta();
            */
	    System.out.println("Up: " + update);
	    if (LibSQL.exActualizar('m', update)) {
		//System.out.println("Up--> " + update);
		/*if (LibIMG.saveImageSQL(fotoFile, "org.employees", "photo", "WHERE idpeople = " + idpersona )){
                    org.digitall.lib.components.Advisor.messageBox("Update Success!!","Message");
                    dispose();
                } else org.digitall.lib.components.Advisor.messageBox("Can't save image","Error");*/
		org.digitall.lib.components.Advisor.messageBox("Update Success!!", "Message");
		dispose();
	    } else
		org.digitall.lib.components.Advisor.messageBox("Can't save data", "Error");
	} else
	    error();
    }
    // private boolean saveDataPanelPersonal() {

    private String getConsulta() {
	/**
	 * a partir del dia 32-01-2008, se va a reemplazr la tabla employees por la tabla employees2.
	 * Es decir la tabla employees2 pasará a ser la tabla employees.
	 * */
	String idpais = org.digitall.lib.sql.LibSQL.getCampo("Select idcountry From tabs.country_tabs Where name = '" + cbBornCountry.getSelectedItem().toString() + "' ");
	String ididentification = org.digitall.lib.sql.LibSQL.getCampo("Select ididentification From tabs.identification_tabs Where name = '" + cbIdentificationType.getSelectedItem().toString() + "' ");
	String consulta = "";
	//String consulta = "UPDATE org.employees SET " + " last_name = '" + tfLastName.getText() + "'," + " first_name = '" + tfFirstName.getText() + "'," + " user_name = '" + tfUserName.getText() + "'," + " born_country = 0" + idpais + "," + " ididentification = 0" + ididentification + "," + " identification_number = 0" + tfCardNumber.getText() + "," + tfBirthdate.getSQLFecha("date_born") + " WHERE idpeople = 0" + idpersona + "; ";
	
	//System.out.println("consulta P. Personal --> "+ consulta);
	return consulta;
    }

    private void error() {
	switch (error) {
	    case 1:
		org.digitall.lib.components.Advisor.messageBox("Field Last Name is empty", "Message");
		break;
	    case 2:
		org.digitall.lib.components.Advisor.messageBox("Field First Name is empty", "Message");
		break;
	    case 3:
		org.digitall.lib.components.Advisor.messageBox("Field First Name is empty", "Message");
		break;
	}
    }

    private void bactcredencial_actionPerformed(ActionEvent e) {
    }

    private void bcredencial_actionPerformed(ActionEvent e) {
    }

    private void bModify_actionPerformed(ActionEvent e) {
	habilitaPanelPersonalData(true);
	/** Ricardo
        panelAddress.habilitarPanelAddress(true);
        panelWorkAddress.habilitarPanelAddress(true);
        */
    }

    private void jButton1_actionPerformed(ActionEvent e) {
    }

}
