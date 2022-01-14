package org.digitall.apps.licenses.interfases;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicTextArea;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.apps.licenses.classes.Driver;
import org.digitall.apps.licenses.classes.Radiogram;


public class RadiogramsMgmt extends BasicPrimitivePanel {

    private BasicPanel centralPanel = new BasicPanel();

    private TFInput tfRadiogramNumber = new TFInput(DataTypes.INTEGER,"Number",false);
    private TFInput tfDateFrom = new TFInput(DataTypes.DATE,"StartDate",true);
    private TFInput tfDateTo = new TFInput(DataTypes.DATE,"EndDate",true);
    
    private BasicTextArea taReason = new BasicTextArea();
    private RadiogramsList parentList;
    private CloseButton btnClose = new CloseButton();
    private SaveDataButton btnSaveData = new SaveDataButton();
    private Radiogram radiogram;
    private Driver driverselected;
    private int error = 0;

    private TFInput tfLastName = new TFInput(DataTypes.STRING,"LastName",true);
    private TFInput tfName = new TFInput(DataTypes.STRING,"Name",true);
    private TFInput tfIdentificationNumber = new TFInput(DataTypes.INTEGER,"IdentificationNumber",true);
    
    private CBInput cbIdentificationType = new CBInput(CachedCombo.IDENTIFICATION_TABS,"Identification",false);

    public RadiogramsMgmt() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        this.setSize(new Dimension(416, 297));
        centralPanel.setLayout(null);
        tfRadiogramNumber.setBounds(new Rectangle(5, 0, 100, 35));
        tfDateFrom.setBounds(new Rectangle(5, 80, 100, 35));
        tfDateTo.setBounds(new Rectangle(180, 80, 100, 35));
        taReason.setBounds(new Rectangle(5, 125, 405, 125));
        tfLastName.setBounds(new Rectangle(5, 40, 160, 35));
        tfName.setBounds(new Rectangle(180, 40, 225, 35));
        tfIdentificationNumber.setBounds(new Rectangle(290, 0, 115, 35));
        cbIdentificationType.setBounds(new Rectangle(180, 0, 100, 35));
        centralPanel.add(tfIdentificationNumber, null);
        centralPanel.add(tfName, null);
        centralPanel.add(tfLastName, null);
        centralPanel.add(taReason, null);
        centralPanel.add(tfDateTo, null);
        centralPanel.add(tfDateFrom, null);
        centralPanel.add(tfRadiogramNumber, null);
        centralPanel.add(cbIdentificationType,null);
        this.add(centralPanel, BorderLayout.CENTER);
        btnClose.addActionListener(new ActionListener() {

                                 public void actionPerformed(ActionEvent e) {
                                     btnClose_actionPerformed(e);
                                 }

                             }
        );
        btnSaveData.addActionListener(new ActionListener() {

                                 public void actionPerformed(ActionEvent e) {
                                     btnSaveData_actionPerformed(e);
                                 }

                             }
        );
        cbIdentificationType.autoSize();
        addButton(btnClose);
        addButton(btnSaveData);
        cbIdentificationType.setSelectedValue("D.N.I.");
        cbIdentificationType.setEnabled(false);
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e){
        super.setParentInternalFrame(_e);
        getParentInternalFrame().setInfo("Agregar/Modificar Radiogramas");
    }
    
    public void setParentList(RadiogramsList _parentList) {
        parentList = _parentList;
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
        getParentInternalFrame().close();
    }
    
    private void btnSaveData_actionPerformed(ActionEvent e) {
        if (saveData()) {
            parentList.refreshRadiograms();
            getParentInternalFrame().close();
        } else {
            switch (error)  {
                case 1: Advisor.messageBox("El campo Fecha de inicio está vacio","Aviso");
                    break;
                case 2: Advisor.messageBox("El campo Fecha de Fin no puede estar vacio","Aviso");
                    break;
                case 3: Advisor.messageBox("El campo Nº no puede estar vacio ni ser cero","Aviso");
                    break;
		case 4: Advisor.messageBox("El campo Apellido no puede estar vacio","Aviso");
		    break;
		case 5: Advisor.messageBox("El campo Nombre no puede estar vacio","Aviso");
		    break;
		case 6: Advisor.messageBox("El campo Nº de Identificación no puede estar vacio","Aviso");
		    break;
		case 7: Advisor.messageBox("La fecha de baja debe ser mayor a la la fecha de inicio","Aviso");
		    break;
                case -1: Advisor.messageBox("El número de radiograma ya está registrado","Error");
                    break;
            }
        }
    }
    public boolean saveData(){
        boolean result = control();
        if (result) {
            loadRadiogramSelected();
            int resul = radiogram.saveData();
            if ( resul > 0) {
                tfRadiogramNumber.setValue(radiogram.getRadiogramnumber());
                result = true;
            } else {
                error = resul;
                result = false;
            }
        } 
        return result;
    }
    
    private boolean control(){
        error = 0;
        boolean result = true;
         if(tfRadiogramNumber.getValue().toString().equals("")||tfRadiogramNumber.getValue().toString().equals("0")) {
            result = false;
            error = 3;
        }else if(tfIdentificationNumber.getValue().toString().equals("0")) {
            result = false;
            error = 6;
        } else if(tfLastName.getValue().toString().equals("")) {
            result = false;
            error = 4;
        } else if(tfName.getValue().toString().equals("")) {
            result = false;
            error = 5;
        } else if (tfDateFrom.getString().equals(""))  {
            result = false;
            error = 1;
        } else if(tfDateTo.getString().equals("")) {
            result = false;
            error = 2;
        }else if (Proced.compareDates(Proced.setFormatDate(tfDateFrom.getString(),false),Proced.setFormatDate(tfDateTo.getString(),false)) == 2) {
	        result = false;
	        error = 7;
	    }     
        return result;
    }
    
    private void loadRadiogramSelected() {
        radiogram.setRadiogramnumber(Integer.parseInt(tfRadiogramNumber.getString()));
        radiogram.setIdidentificationtype(Integer.parseInt(cbIdentificationType.getSelectedValue().toString()));
        radiogram.setIdentificationnumber(Integer.parseInt(tfIdentificationNumber.getString()));
        radiogram.setLastname(tfLastName.getString());
        radiogram.setName(tfName.getString());
        radiogram.setDatefrom(Proced.setFormatDate(tfDateFrom.getString(),false));
        radiogram.setDateto(Proced.setFormatDate(tfDateTo.getString(),false));
        radiogram.setReason(taReason.getText());
    }
    
    public void setRadiogram(Radiogram _radiogram) {
        radiogram = _radiogram;
        if (radiogram.getIdradiogram() != -1) {
            tfRadiogramNumber.setValue(radiogram.getRadiogramnumber());
            tfIdentificationNumber.setValue(radiogram.getIdentificationnumber());
            tfLastName.setValue(radiogram.getLastname());
            tfName.setValue(radiogram.getName());
            tfDateFrom.setValue(Proced.setFormatDate(radiogram.getDatefrom(),true));
            tfDateTo.setValue(Proced.setFormatDate(radiogram.getDateto(),true));
            taReason.setText(radiogram.getReason());
            tfRadiogramNumber.setEnabled(false);
        }
    }

    public Radiogram getRadiogram() {
        return radiogram;
    }

    public void setDriverselected(Driver _driverselected) {
        driverselected = _driverselected;
    }

    public Driver getDriverselected() {
        return driverselected;
    }
}
