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
 * RadiogramsMgmt.java
 *
 * */
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
