package org.digitall.apps.licenses.interfases;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;

import org.digitall.apps.licenses.classes.DriverForm;
import org.digitall.apps.licenses.classes.License;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.PrintSaveButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.misc.BarCode;
import org.digitall.lib.sql.LibSQL;

public class LicensesMgmt extends BasicPrimitivePanel {

    private BasicPanel centralPanel = new BasicPanel();
    
    private TFInput tfDateFrom = new TFInput(DataTypes.DATE,"StartDate",false);
    private TFInput tfDateTo = new TFInput(DataTypes.DATE,"ExpirationDate",false);
    private TFInput tfPrice = new TFInput(DataTypes.DOUBLE,"Price",false);
    private TFInput tfAmount = new TFInput(DataTypes.DOUBLE,"Amount",false);
    private TFInput tfAuthorizedBy = new TFInput(DataTypes.STRING,"AuthorizedBy",false);

    private CBInput cbCategory = new CBInput(0,"Category",false);
    private CBInput cbYears = new CBInput(0,"YearsQty",false);
    
    private CloseButton btnClose = new CloseButton();
    private PrintSaveButton btnPrintSave = new PrintSaveButton();
    
    private DriversFormMgmt parentList;
    private DriverForm driverFormSelected;
    private License license;
    private int yearsQty = 0;
    private int error = 0;
    private BufferedImage photo = null;
    private BufferedImage licensePhoto = null;
    private BasicCheckBox chkHasGlasses = new BasicCheckBox(Environment.lang.getProperty("HasGlasses"), false);
    private BasicCheckBox chkHasHearingAid = new BasicCheckBox(Environment.lang.getProperty("HasHearingAid"), false);
    private BasicCheckBox chkOrganDonor = new BasicCheckBox(Environment.lang.getProperty("OrganDonor"), false);
    private BasicCheckBox chkAdaptedVehicle = new BasicCheckBox(Environment.lang.getProperty("AdaptedVehicle"), false);
    private TFInput tfAllergies = new TFInput(DataTypes.STRING,"Allergies",false);
    private TFInput tfDisqualifications = new TFInput(DataTypes.STRING,"Disqualifications",false);

    public LicensesMgmt() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        this.setSize(new Dimension(502, 276));
        this.setPreferredSize(new Dimension(760, 512));
        centralPanel.setLayout(null);
        tfDateFrom.setBounds(new Rectangle(210, 10, 105, 35));
        tfDateTo.setBounds(new Rectangle(335, 10, 105, 35));
        tfPrice.setBounds(new Rectangle(265, 195, 105, 35));
        tfAmount.setBounds(new Rectangle(375, 195, 105, 35));
	tfAuthorizedBy.setBounds(new Rectangle(10, 195, 250, 35));
	tfAllergies.setBounds(new Rectangle(10, 110, 470, 35));
	tfDisqualifications.setBounds(new Rectangle(10, 150, 470, 35));
        cbCategory.setBounds(new Rectangle(10, 10, 80, 35));
        cbYears.setBounds(new Rectangle(110, 10, 80, 35));
	centralPanel.add(chkHasGlasses, null);
	centralPanel.add(chkHasHearingAid, null);
	centralPanel.add(chkOrganDonor, null);
	centralPanel.add(chkAdaptedVehicle, null);
	centralPanel.add(tfAuthorizedBy, null);
	centralPanel.add(tfAllergies, null);
	centralPanel.add(tfDisqualifications, null);
	centralPanel.add(tfAmount, null);
	centralPanel.add(tfPrice, null);
	centralPanel.add(tfDateTo, null);
	centralPanel.add(tfDateFrom, null);
	centralPanel.add(cbCategory, null);
	centralPanel.add(cbYears, null);
	this.add(centralPanel, BorderLayout.CENTER);
        btnClose.addActionListener(new ActionListener() {

                                 public void actionPerformed(ActionEvent e) {
                                     btnClose_actionPerformed(e);
                                 }

                             }
        );
        btnPrintSave.addActionListener(new ActionListener() {

                                 public void actionPerformed(ActionEvent e) {
                                     btnPrintSave_actionPerformed(e);
                                 }

                             }
        );
	chkHasGlasses.setBounds(new Rectangle(10, 55, 205, 20));
	chkHasHearingAid.setBounds(new Rectangle(235, 55, 205, 20));
	chkOrganDonor.setBounds(new Rectangle(10, 80, 205, 20));
	chkAdaptedVehicle.setBounds(new Rectangle(235, 80, 205, 20));
	cbCategory.getCombo().addItemListener(new ItemListener() {

                                        public void itemStateChanged(ItemEvent evt) {
                                            if (evt.getStateChange() == ItemEvent.SELECTED) {
                                                loadPrice();
                                            }
                                        }

                                    }
        );
        cbYears.getCombo().addItemListener(new ItemListener() {

                                        public void itemStateChanged(ItemEvent evt) {
                                            if (evt.getStateChange() == ItemEvent.SELECTED) {
                                                loadPrice();
                                            }
                                        }

                                    }
        );
        cbCategory.autoSize();
        cbYears.autoSize();
        addButton(btnClose);
        addButton(btnPrintSave);
        tfPrice.setEnabled(false);
        tfAmount.setEnabled(false);
    }
    
    public void setParentList(DriversFormMgmt _parentList) {
        parentList = _parentList;
    }
    
    private void loadCategoriesCombo(){
        cbCategory.loadJCombo(LibSQL.exFunction("licenses.getAllCategories", ""));
    }
    
    private void loadYearsCombo(){
         yearsQty = LibSQL.getInt("licenses.getConfigurationByYears","");
         if (yearsQty > 0) {
            for (int i = 1 ; i <= yearsQty ; i++ )  {
                cbYears.getCombo().addItem(i,i,"0");
            }
         }
    }
    
    
    private void btnClose_actionPerformed(ActionEvent e) {
        //parentList.refresh();
        getParentInternalFrame().close();
    }

    public void setDriverFormSelected(DriverForm _driverFormSelected) {
        driverFormSelected = _driverFormSelected;
    }

    public DriverForm getDriverFormSelected() {
        return driverFormSelected;
    }
    
    public void loadLicense(boolean _new) {
        loadCategoriesCombo();
        loadYearsCombo();
        license = new License();
        if (_new)  {
            tfDateFrom.setValue(Proced.setFormatDate(Environment.currentDate,true));
            loadPrice();
            tfDateFrom.setEnabled(false);
            tfDateTo.setEnabled(false);
        } else  {
            cbCategory.setSelectedValue(driverFormSelected.getLicense().getCategory());
            cbYears.setSelectedValue(driverFormSelected.getLicense().getYears());
            tfDateFrom.setValue(Proced.setFormatDate(driverFormSelected.getLicense().getDatefrom(),true));
            tfDateTo.setValue(Proced.setFormatDate(driverFormSelected.getLicense().getDateto(),true));
            tfAuthorizedBy.setValue(driverFormSelected.getLicense().getAuthorizedby());
            tfPrice.setValue(driverFormSelected.getLicense().getPrice());
            tfAmount.setValue(driverFormSelected.getLicense().getAmount());
            setEnabledPanel(false);
            tfAuthorizedBy.setEnabled(true);
            license.setIdlicense(driverFormSelected.getLicense().getIdlicense());
	    tfAllergies.setValue(driverFormSelected.getLicense().getAllergies());
	    tfDisqualifications.setValue(driverFormSelected.getLicense().getDisqualifications());
	    chkHasGlasses.setSelected(driverFormSelected.getLicense().hasGlasses());
	    chkHasHearingAid.setSelected(driverFormSelected.getLicense().hasHearingAid());
	    chkOrganDonor.setSelected(driverFormSelected.getLicense().isOrganDonor());
	    chkAdaptedVehicle.setSelected(driverFormSelected.getLicense().isAdaptedVehicle());
        }
    }
    
    public void setEnabledPanel(boolean _valor){
        cbCategory.setEnabled(_valor);
        cbYears.setEnabled(_valor);
        tfDateFrom.setEnabled(_valor);
        tfDateTo.setEnabled(_valor);
        tfAuthorizedBy.setEnabled(_valor);
        tfPrice.setEnabled(_valor);
        tfAmount.setEnabled(_valor);
    }
    
    private void loadPrice(){
        tfPrice.setValue(Double.parseDouble(cbCategory.getSelectedValueRef().toString()));
        tfAmount.setValue(Double.parseDouble(cbCategory.getSelectedValueRef().toString()) * Double.parseDouble(cbYears.getSelectedValue().toString()));
        int actualYear = Integer.parseInt(Environment.currentYear);
        int yearSelected = Integer.parseInt(cbYears.getSelectedValue().toString());
        String dateTo= (actualYear + yearSelected) +"-"+ Environment.currentMonth + "-"+Environment.currentDay;
        tfDateTo.setValue(Proced.setFormatDate(dateTo,true));
    }
    
    private void btnPrintSave_actionPerformed(ActionEvent e) {
        if (saveData()) {
            printLicence();
            parentList.refreshLicenses();
            getParentInternalFrame().close();
        } 
    }
    
    
    private void printLicence() {
        BasicReport report = new BasicReport(LicensesMgmt.class.getResource("xml/license.xml"));
        report.addTableModel("licenses.xmlGetLicense", "" + license.getIdlicense());
	license.retrieveData();
        report.setProperty("photo", licensePhoto);
	BarCode code = new BarCode();
	report.setProperty("barcode", code.getBarCodeEAN13(license.getBarCode()));
        report.doReport();
    }
    
    private void loadObject(){
        license.setIddriverform(driverFormSelected.getIddriverform());
        license.setDatefrom(Proced.setFormatDate(tfDateFrom.getString(),false));
        license.setDateto(Proced.setFormatDate(tfDateTo.getString(),false));
        license.setLicensenumber(driverFormSelected.getIdentificationnumber());
        license.setName(driverFormSelected.getName());
        license.setLastname(driverFormSelected.getLastname());
        license.setBirthday(driverFormSelected.getBirthdate());
        license.setIdentificationtype(driverFormSelected.getIdentificationtype());
        license.setIdentificationnumber(driverFormSelected.getIdentificationnumber());
        license.setAddress(driverFormSelected.getAddress());
        license.setIdcategory(Integer.parseInt(cbCategory.getSelectedValue().toString()));
        license.setCategory(cbCategory.getSelectedItem().toString());
        license.setNationality(driverFormSelected.getNationality());
        license.setKind(driverFormSelected.getKind());
        license.setBloodgruop(driverFormSelected.getBloodgroup());
        license.setRhfactor(driverFormSelected.getRhfactor());
        license.setYears(Integer.parseInt(cbYears.getSelectedItem().toString()));
        license.setPrice(tfPrice.getAmount());
        license.setAmount(tfAmount.getAmount());
        driverFormSelected.retrievePicture();
        photo = driverFormSelected.getPhotoImage();
        license.setPhotoImage(photo);
        licensePhoto = photo;
	license.setAuthorizedby(tfAuthorizedBy.getString());
	license.setHasGlasses(chkHasGlasses.isSelected());
	license.setHasHearingAid(chkHasHearingAid.isSelected());
	license.setOrganDonor(chkOrganDonor.isSelected());
	license.setAdaptedVehicle(chkAdaptedVehicle.isSelected());
	license.setAllergies(tfAllergies.getString());
        license.setDisqualifications(tfDisqualifications.getString());
    }
    
    public boolean saveData() {
        boolean result = false;
        if (control())  {
            loadObject();
            int res = license.saveData();
            if ( res > 0)  {
                result = true;
            } else  {
                error = res;
                result = false;
            }
        } else  {
            switch (error)  {
                case 1: Advisor.messageBox("El campo \"Autorizado por\" est?? vacio","Aviso");
                    break;
                case 2: Advisor.messageBox("Ocurri?? un error al grabar los datos","Error");
                    break;
                case -2: Advisor.messageBox("No es posible generar la Licencia, porque el\nconductor est?? afectado por un Radiograma","Aviso!");
                    break;
            }
            result = false;
        }
        return result;
    }

    private boolean control() {
        boolean resul = true;
        String params = driverFormSelected.getIdidentificationtype() +","+ driverFormSelected.getIdentificationnumber();
        if (tfAuthorizedBy.getString().equals(""))  {
            resul = false;
            error = 1;
            
        } else if (LibSQL.getInt("licenses.existRadiogramsByDriver",params) != 0) {
            resul = false;
            error = -2;
        }
        return resul;
        
    }

}
