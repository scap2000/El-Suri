package org.digitall.apps.personalfiles.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.personalfiles.classes.Persona;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.image.ImageCropper;
import org.digitall.lib.image.ImageLabel;

public class PaneDatosPersonalesEmpleados extends BasicContainerPanel{

    private BorderLayout borderLayout1 = new BorderLayout(); 
    private BasicPanel jPanel1 = new BasicPanel();
    private CBInput cbIdentifiactionType = new CBInput(CachedCombo.IDENTIFICATION_TABS,"Identification");
    private TFInput tfIdentificationNumber = new TFInput(DataTypes.STRING,"IdentificationNumber",true);
    private TFInput tfFirstName = new TFInput(DataTypes.STRING,"FirstName",true);
    private TFInput tfLastName = new TFInput(DataTypes.STRING,"LastName",true);
    private TFInput tfMarriedLastName = new TFInput(DataTypes.STRING, "MarriedLastName",false);
    private CBInput cbBloodGroup = new CBInput(0,"BloodGroup",false);
    private TFInput tfBirthDate = new TFInput(DataTypes.DATE,"Birthdate",false);
    private CBInput cbBornCountry = new CBInput(CachedCombo.COUNTRY_TABS, "Nationality");
    private CBInput cbPrefix = new CBInput(CachedCombo.PREFIX_TABS, "Prefix",false);
    private CBInput cbSuffix = new CBInput(CachedCombo.SUFFIXPERSON_TABS, "Suffix",false);  
    private CBInput cbProfession = new CBInput(CachedCombo.PROFESSION_TABS, "Profession");
    private TFInput tfTitle = new TFInput(DataTypes.STRING,"Title",true);
    private CBInput cbCivilState = new CBInput(CachedCombo.CIVILSTATE_TABS,"CivilState",false);
    private CBInput cbCommunicationType = new CBInput(CachedCombo.COMMUNICATIONTYPE_TABS, "CommunicationType");
    private TFInput tfObservation = new TFInput(DataTypes.STRING, "Observations", false);
    private CBInput cbSex = new CBInput(0, "Sex",false);
    private CBInput cbBloodFactor = new CBInput(0,"RHFactor",false);
    private BasicCheckBox chkEmployee = new BasicCheckBox();
    private ImageLabel lblPhoto = new ImageLabel();    
    private Persona person = null;

    public PaneDatosPersonalesEmpleados() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(borderLayout1);
	this.setSize(new Dimension(576, 541));
	jPanel1.setLayout(null);
	jPanel1.setBorder(BorderPanel.getBorderPanel("Agregar Personal"));
	cbIdentifiactionType.setBounds(new Rectangle(235, 175, 150, 40));
	tfIdentificationNumber.setBounds(new Rectangle(395, 175, 120, 35));
	tfFirstName.setBounds(new Rectangle(235, 135, 240, 35));
	tfLastName.setBounds(new Rectangle(305, 55, 185, 35));
	tfMarriedLastName.setBounds(new Rectangle(235, 95, 185, 35));
	tfMarriedLastName.setSize(new Dimension(185, 35));
	cbBloodGroup.setBounds(new Rectangle(30, 270, 85, 35));
	tfBirthDate.setBounds(new Rectangle(135, 230, 90, 35));
	cbBornCountry.setBounds(new Rectangle(235, 230, 185, 35));
	cbPrefix.setBounds(new Rectangle(235, 55, 60, 35));
	
	cbSuffix.setBounds(new Rectangle(485, 135, 60, 35));
	cbProfession.setBounds(new Rectangle(30, 390, 305, 35));
	tfTitle.setBounds(new Rectangle(30, 350, 465, 35));
	cbCivilState.setBounds(new Rectangle(255, 270, 165, 35));
	cbCommunicationType.setBounds(new Rectangle(30, 310, 170, 35));
	tfObservation.setBounds(new Rectangle(30, 435, 515, 35));
	cbSex.setBounds(new Rectangle(30, 230, 65, 35));

        cbBloodFactor.setBounds(new Rectangle(125, 270, 85, 35));
	chkEmployee.setText("Empleado");
	chkEmployee.setBounds(new Rectangle(230, 30, 110, 20));
	lblPhoto.setBounds(new Rectangle(30, 35, 175, 175));
	lblPhoto.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	lblPhoto.setHorizontalAlignment(SwingConstants.CENTER);
	lblPhoto.setSize(new Dimension(175, 175));
	jPanel1.add(lblPhoto, null);
	jPanel1.add(chkEmployee, null);
	jPanel1.add(cbBloodFactor, null);
        jPanel1.add(cbSex, null);
	jPanel1.add(tfObservation, null);
	jPanel1.add(cbCommunicationType, null);
	jPanel1.add(cbCivilState, null);
	jPanel1.add(tfTitle, null);
	jPanel1.add(cbProfession, null);
	jPanel1.add(cbSuffix, null);
	jPanel1.add(cbPrefix, null);
	jPanel1.add(cbBornCountry, null);
	jPanel1.add(tfBirthDate, null);
	jPanel1.add(cbBloodGroup, null);
	jPanel1.add(tfMarriedLastName, null);
	jPanel1.add(tfLastName, null);
	jPanel1.add(tfFirstName, null);
	jPanel1.add(tfIdentificationNumber, null);
	jPanel1.add(cbIdentifiactionType, null);
	this.add(jPanel1, BorderLayout.CENTER);
	cbIdentifiactionType.autoSize();
	cbBloodGroup.autoSize();
	cbBornCountry.autoSize();
	cbPrefix.autoSize();
	cbSuffix.autoSize();	
	cbProfession.autoSize();
	cbCivilState.autoSize();
	cbCommunicationType.autoSize();
	cbSex.autoSize();
        cbBloodFactor.autoSize();
	loadCombos();
	lblPhoto.addMouseListener(new MouseAdapter() {

			       public void mouseClicked(MouseEvent e) {
				   if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
				       loadImage();
				   } 
			       }

			   }
	);
	cbBornCountry.setSelectedValue(Environment.defaultCountry);
    }
    
    private void loadCombos(){
	cbBloodGroup.getCombo().addItem("A","1");
	cbBloodGroup.getCombo().addItem("B","2");
	cbBloodGroup.getCombo().addItem("AB","3");
	cbBloodGroup.getCombo().addItem("0","4");
	cbBloodGroup.setSelectedID("1");
	
	cbBloodFactor.getCombo().addItem("RH(+)","1");
	cbBloodFactor.getCombo().addItem("RH(-)","2");
	
	cbSex.getCombo().addItem("M", "1");
	cbSex.getCombo().addItem("F", "2");
		
	//cbParent
    }
     
     private void loadImage() {	
	 ImageCropper cropper = new ImageCropper(lblPhoto, true);
	 ExtendedInternalFrame k = new ExtendedInternalFrame("Foto de la Persona");
	 k.setCentralPanel(cropper);
	 k.show();
     }
   
    public void loadData(Persona _person){
        if (tfLastName.getString().length() > 0 && tfFirstName.getString().length() > 0) {
            person = _person;        
            person.setFirstName(tfFirstName.getString());
            person.setLastName(tfLastName.getString());
            person.setMarriedLastName(tfMarriedLastName.getString());
            person.setBloodGroup(cbBloodGroup.getSelectedItem().toString().trim());
            person.setBloodFactor(cbBloodFactor.getSelectedItem().toString().trim());
            person.setIdBornCountry(Integer.parseInt(cbBornCountry.getSelectedValue().toString()));      
            person.setIdSuffix(Integer.parseInt(cbSuffix.getSelectedValue().toString()));
            person.setIdPrefix(Integer.parseInt(cbPrefix.getSelectedValue().toString()));                
            person.setIdProfession(Integer.parseInt(cbProfession.getSelectedValue().toString()));
            person.setTitle(tfTitle.getString());
            person.setBirthDate(Proced.setFormatDate(tfBirthDate.getString(), false));          
            person.setIdCivilState(Integer.parseInt(cbCivilState.getSelectedValue().toString()));
            person.setIdIdentification(Integer.parseInt(cbIdentifiactionType.getSelectedValue().toString()));
            person.setIdentificationNumber(tfIdentificationNumber.getString());
            person.setIdCommunicationType(Integer.parseInt(cbCommunicationType.getSelectedValue().toString()));
            person.setDescription(tfObservation.getString());           
            person.setSex(cbSex.getSelectedItem().toString().trim());
            person.setIdcontacttype(0);
            person.setIdformatview(0);
            person.setIsPersonal(chkEmployee.isSelected());
	    person.setPhotoImage(lblPhoto.getImage());	
            person.setLive(true);
        }else{           
            Advisor.messageBox("Debe al menos completar los campos Apellido y Nombre", "Aviso");            
        }
    }
    
    public void setData(Persona _person){
        person = _person;
        tfFirstName.setValue(person.getFirstName());   
        tfLastName.setValue(person.getLastName());
        tfMarriedLastName.setValue(person.getMarriedLastName());
        cbBloodGroup.setSelectedValue(person.getBloodGroup().trim());        
        cbBloodFactor.setSelectedValue(person.getBloodFactor());    
        cbBornCountry.setSelectedID(person.getIdBornCountry());
        cbSuffix.setSelectedID(person.getIdSuffix());
        cbPrefix.setSelectedID(person.getIdPrefix());
        cbProfession.setSelectedID(person.getIdProfession());
        tfTitle.setValue(person.getTitle());
        tfBirthDate.setValue(Proced.setFormatDate(person.getBirthDate(), true));
        cbCivilState.setSelectedID(person.getIdCivilState());
        cbIdentifiactionType.setSelectedID(person.getIdIdentification());
        tfIdentificationNumber.setValue(person.getIdentificationNumber());
        cbCommunicationType.setSelectedID(person.getIdCommunicationType());
        tfObservation.setValue(person.getDescription());
        cbSex.setSelectedValue(person.getSex());
        chkEmployee.setSelected(person.getIsPersonal());
        
        if (person.getPhotoImage() != null){
            lblPhoto.setImage(person.getPhotoImage());            
        }else{
	    lblPhoto.setImage(null);            
	    lblPhoto.setText("No posee foto.");
	}
    }

}
