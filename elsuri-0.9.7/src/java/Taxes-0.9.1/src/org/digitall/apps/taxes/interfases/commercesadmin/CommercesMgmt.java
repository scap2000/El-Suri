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
 * CommercesMgmt.java
 *
 * */
package org.digitall.apps.taxes.interfases.commercesadmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import java.io.IOException;

import java.net.URL;

import java.util.Vector;

import javax.imageio.ImageIO;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.digitall.apps.taxes.classes.Commerce;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.JArea;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.esri.ESRIPoint;
import org.digitall.lib.geo.gaia.components.GaiaConfigPanel;
import org.digitall.lib.icons.IconTypes;
import org.digitall.lib.image.ImageLabel;

public class CommercesMgmt extends GaiaConfigPanel {

    private BasicPanel dataPanel = new BasicPanel("Agregar/Modificar un Comercio");

    private CloseButton btnClose = new CloseButton();
    private SaveDataButton btnAccept = new SaveDataButton();

    private TFInput tfPollTax = new TFInput(DataTypes.STRING, "PollTax", true);
    private TFInput tfTaxPayer = new TFInput(DataTypes.STRING, "TaxPayer", true);
    private TFInput tfDni = new TFInput(DataTypes.STRING, "DNI", false);
    private TFInput tfTradingName = new TFInput(DataTypes.STRING, "TradingName", true);
    private TFInput tfTradeName = new TFInput(DataTypes.STRING, "TradeName", true);
    private TFInput tfAddress = new TFInput(DataTypes.STRING, "Address", false);
    private JArea tfDescription = new JArea();//(DataTypes.STRING, "Description", false);
    private BasicLabel lblDescription = new BasicLabel("Descripción:");
    private TFInput tfFile = new TFInput(DataTypes.STRING, "File", false);
    private TFInput tfMonthlyFee = new TFInput(DataTypes.MONEY, "MonthlyFee", false);
    private TFInput tfValidUntil = new TFInput(DataTypes.SIMPLEDATE, "Permiso hasta", false);
    private TFInput tfStartDate = new TFInput(DataTypes.SIMPLEDATE, "StartDate", true);
    private TFInput tfEndDate = new TFInput(DataTypes.SIMPLEDATE, "DropOffDate", true);
    private TFInput tfCuit = new TFInput(DataTypes.STRING, "Cuit", false);
    private TFInput tfCatastro = new TFInput(DataTypes.STRING,"Catastro",true);
    private TFInput tfCoordenadas = new TFInput(DataTypes.STRING,"Ubicación geográfica", false);

    private Commerce commerce;
    private BasicPrimitivePanel parentList;

    private static final int NO_ERROR = 0;
    private static final int ERROR_PADRON = 1;
    private static final int ERROR_CONTRIBUYENTE = 2;
    private static final int ERROR_RUBRO = 3;
    private static final int ERROR_RAZONSOCIAL = 4;
    private static final int ERROR_FECHAINICIO = 5;
    private static final int ERROR_FECHABAJA  = 6;
    private static final int ERROR_CATASTRO  = 7;
    private int error = NO_ERROR;

    private Vector components = new Vector();
    private BasicCheckBox chkAlcohol = new BasicCheckBox("Expendio de alcohol");
    private BasicCheckBox chkCigarrillos = new BasicCheckBox("Venta de cigarrillos");
    private BasicCheckBox chkInflamables = new BasicCheckBox("Inflamables y/o explosivos");
    private BasicCheckBox chkAlimentos = new BasicCheckBox("Alimentos en general");
    private BasicCheckBox  chkCarnesVegetales = new BasicCheckBox("Carnes y vegetales");
    private BasicCheckBox  chkComidas = new BasicCheckBox("Comidas elaboradas");
    private BasicCheckBox  chkVeterinaria = new BasicCheckBox("Veterinaria");
    private BasicCheckBox  chkMedicamentos = new BasicCheckBox("Venta de medicamentos");
    private BasicCheckBox  chkConsultorios = new BasicCheckBox("Consultorios y/o clínicas");
    private BasicPanel jPanel1 = new BasicPanel("Permisos especiales");
    private ImageLabel lblPhoto = new ImageLabel();

    public CommercesMgmt() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        //this.setLayout( null );
        this.setPreferredSize(new Dimension(565, 500));
        this.setSize(new Dimension(565, 500));
        //btnClose.setBounds(new Rectangle(540, 535, 40, 25));
        btnClose.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                btnClose_actionPerformed(e);
            }

        });
        //btnAccept.setBounds(new Rectangle(490, 535, 40, 25));
        btnAccept.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                btnAccept_actionPerformed(e);
            }

        });
        tfDescription.setBounds(new Rectangle(10, 170,390, 90));
        tfValidUntil.setBounds(new Rectangle(10, 375, 130, 35));
        tfStartDate.setBounds(new Rectangle(315, 375, 115, 35));
        tfPollTax.setBounds(new Rectangle(10, 25, 145, 35));
        components.add(tfPollTax);
        dataPanel.setBounds(new Rectangle(5, 0, 555, 220));
        dataPanel.setLayout(null);
        tfTaxPayer.setBounds(new Rectangle(160, 25, 235, 35));
        components.add(tfTaxPayer);
        tfTradingName.setBounds(new Rectangle(10, 65, 145, 35));
        components.add(tfTradingName);
        tfTradeName.setBounds(new Rectangle(160, 65, 235, 35));
        components.add(tfTradeName);
        tfAddress.setBounds(new Rectangle(10, 105, 535, 35));
        lblDescription.setBounds(new Rectangle(10, 150, 540, 20));
        components.add(tfAddress);
        components.add(tfDescription);
        jPanel1.setLayout(null);
        jPanel1.add(chkAlcohol);
        jPanel1.add(chkCigarrillos, null);
        jPanel1.add(chkInflamables, null);
        jPanel1.add(chkAlimentos, null);
        jPanel1.add(chkCarnesVegetales, null);
        jPanel1.add(chkComidas, null);
        jPanel1.add(chkVeterinaria, null);
        jPanel1.add(chkMedicamentos, null);
        jPanel1.add(chkConsultorios, null);
        dataPanel.add(tfEndDate, null);
        dataPanel.add(tfCuit, null);
        dataPanel.add(tfDni, null);
        dataPanel.add(tfTaxPayer, null);
        dataPanel.add(tfPollTax, null);
        dataPanel.add(tfAddress, null);
        dataPanel.add(tfDescription, null);
        dataPanel.add(lblDescription, null);
        dataPanel.add(tfTradeName, null);
        dataPanel.add(tfTradingName, null);
        dataPanel.add(tfValidUntil, null);
        dataPanel.add(tfStartDate, null);
        dataPanel.add(tfFile, null);
        dataPanel.add(tfMonthlyFee, null);
        dataPanel.add(tfCoordenadas, null);
        dataPanel.add(tfCatastro, null);
        dataPanel.add(jPanel1, null);
        dataPanel.add(lblPhoto, BorderLayout.CENTER);
        this.add(dataPanel, BorderLayout.CENTER);
        this.addButton(btnClose);
        this.addButton(btnAccept);
        lblPhoto.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        lblPhoto.setHorizontalAlignment(SwingConstants.CENTER);
        lblPhoto.setBounds(new Rectangle(405, 145, 145, 115));
        //lblPhoto.setSize(new Dimension(175, 175));
        tfFile.setBounds(new Rectangle(165, 415, 175, 35));
        components.add(tfFile);
        tfFile.getTextField().addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if ((caracter == KeyEvent.VK_ENTER)) {
                    calcPayment();
                }
            }

        });
        tfMonthlyFee.setBounds(new Rectangle(10, 415, 145, 35));
        components.add(tfMonthlyFee);
        components.add(tfValidUntil);
        components.add(tfStartDate);
        components.add(tfCuit);
        components.add(tfEndDate);
        tfDni.setBounds(new Rectangle(400, 25, 145, 35));
        components.add(tfDni);
        tfEndDate.setEnabled(false);
        tfEndDate.setBounds(new Rectangle(440, 375, 115, 35));
        tfCatastro.setBounds(new Rectangle(200, 375, 105, 35));
        tfCuit.setBounds(new Rectangle(400, 65, 145, 35));
        jPanel1.setBounds(new Rectangle(10, 265, 545, 100));
        chkAlcohol.setBounds(new Rectangle(15, 20, 180, 19));
        chkAlcohol.setOpaque(true);
        chkAlcohol.setBackground(new Color(198, 49, 0));
        chkCigarrillos.setBounds(new Rectangle(15, 45, 180, 19));
        chkCigarrillos.setOpaque(true);
        chkCigarrillos.setBackground(new Color(198, 49, 0));
        chkInflamables.setBounds(new Rectangle(15, 70, 180, 19));
        chkInflamables.setOpaque(true);
        chkInflamables.setBackground(new Color(198, 49, 0));
        chkAlimentos.setBounds(new Rectangle(200, 20, 170, 20));
        chkAlimentos.setBackground(new Color(0, 132, 0));
        chkAlimentos.setOpaque(true);
        chkCarnesVegetales.setBounds(new Rectangle(200, 45, 170, 19));
        chkCarnesVegetales.setBackground(new Color(0, 132, 0));
        chkCarnesVegetales.setOpaque(true);
        chkComidas.setBounds(new Rectangle(200, 70, 170, 20));
        chkComidas.setBackground(new Color(0, 132, 0));
        chkComidas.setOpaque(true);
        chkVeterinaria.setBounds(new Rectangle(375, 20, 160, 19));
        chkVeterinaria.setOpaque(true);
        chkVeterinaria.setBackground(new Color(0, 99, 148));
        chkMedicamentos.setBounds(new Rectangle(375, 45, 160, 20));
        chkMedicamentos.setOpaque(true);
        chkMedicamentos.setBackground(new Color(0, 99, 148));
        chkConsultorios.setBounds(new Rectangle(375, 70, 160, 20));
        chkConsultorios.setOpaque(true);
        chkConsultorios.setBackground(new Color(0, 99, 148));
        tfCatastro.getTextField().setHorizontalAlignment(JTextField.CENTER);
        //tfCatastro.getTextField().setForeground(Color.red);
        components.add(tfCatastro);
        tfCoordenadas.setBounds(new Rectangle(350, 415, 205, 35));
        tfCoordenadas.getTextField().setHorizontalAlignment(JTextField.CENTER);
        tfCoordenadas.getTextField().setForeground(Color.red);
        tfCoordenadas.setEditable(false);
        tfCoordenadas.setValue("0 ; 0");
        components.add(tfCoordenadas);
    }


    private void loadData() {
            tfPollTax.setValue(commerce.getPadron());
            tfTaxPayer.setValue(commerce.getContribuyente());
            tfDni.setValue(commerce.getDni());
            tfTradingName.setValue(commerce.getRubro());
            tfTradeName.setValue(commerce.getRazonsocial());
            tfCuit.setValue(commerce.getCuit());
            tfAddress.setValue(commerce.getDomicilio());
            tfDescription.setText(commerce.getDescription());

            chkAlcohol.setSelected(commerce.isAlcohol());
            chkCigarrillos.setSelected(commerce.isCigarrillos());
            chkInflamables.setSelected(commerce.isInflamables());
            chkAlimentos.setSelected(commerce.isAlimentos());
            chkCarnesVegetales.setSelected(commerce.isCarnesvegetales());
            chkComidas.setSelected(commerce.isComidas());
            chkVeterinaria.setSelected(commerce.isVeterinaria());
            chkMedicamentos.setSelected(commerce.isMedicamentos());
            chkConsultorios.setSelected(commerce.isConsultorios());

            tfValidUntil.setValue("" + Proced.setFormatDate(commerce.getPermisoHasta(), true));
            tfCatastro.setValue(commerce.getCatastro());
            tfStartDate.setValue("" + Proced.setFormatDate(commerce.getFechainicio(), true));
            tfEndDate.setValue("" + Proced.setFormatDate(commerce.getFechaBaja(), true));
            tfMonthlyFee.setValue(commerce.getCuotamensual());
            tfFile.setValue(commerce.getExpte());
            if (commerce.getMapLocation() != null) {
                tfCoordenadas.setValue(commerce.getMapLocation().getLatitude() + ";" + commerce.getMapLocation().getLongitude());
            }
            if (commerce.getIdpadron() != -1) {
                if (commerce.getFechaBaja() != null) {
                    setEnabledAll(false);
                } else {
                    tfEndDate.setEnabled(true);
                }
            } else {
                tfEndDate.setEnabled(false);
            }
            Thread _thread = new Thread(new Runnable() {
            public void run()  {
                lblPhoto.setIcon(IconTypes.busy);
                if (commerce.getPictureFile() != null) {
                    BufferedImage img;
                    try {
                        img = ImageIO.read(new URL(Environment.mediaServer + commerce.getPictureFile()));
                        lblPhoto.setImage(img);
                    } catch (IOException e) {   
                        e.printStackTrace();
                        lblPhoto.setImage(null);
                    }
                }
            }
        });
        _thread.start();
        }
    
    private void btnClose_actionPerformed(ActionEvent e) {
        getParentInternalFrame().close();
    }

    private void setData() {
        commerce.setPadron(tfPollTax.getString());
        commerce.setContribuyente(tfTaxPayer.getString());
        commerce.setDni(tfDni.getString());
        commerce.setRubro(tfTradingName.getString());
        commerce.setRazonsocial(tfTradeName.getString());
        commerce.setDomicilio(tfAddress.getString());
        commerce.setCuotamensual(tfMonthlyFee.getAmount());
        commerce.setFechainicio(tfStartDate.getDateForSQL());
        commerce.setFechaBaja(tfEndDate.getDateForSQL());
        commerce.setExpte(tfFile.getString());
        commerce.setCuit(tfCuit.getValue().toString());

        commerce.setCatastro(tfCatastro.getString());
        commerce.setDescription(tfDescription.getText());
        commerce.setAlcohol(chkAlcohol.isSelected());
        commerce.setCigarrillos(chkCigarrillos.isSelected());
        commerce.setInflamables(chkInflamables.isSelected());
        commerce.setAlimentos(chkAlimentos.isSelected());
        commerce.setCarnesvegetales(chkCarnesVegetales.isSelected());
        commerce.setComidas(chkComidas.isSelected());
        commerce.setVeterinaria(chkVeterinaria.isSelected());
        commerce.setMedicamentos(chkMedicamentos.isSelected());
        commerce.setConsultorios(chkConsultorios.isSelected());
        commerce.setPermisoHasta(tfValidUntil.getDateForSQL());
    }

    public boolean saveData() {
        if (control() == NO_ERROR) {
            if (commerce == null) {
                commerce = new Commerce();
            }
            setData();
            if (commerce.saveData() >= 0) {
                return true;
            } else {
                return false;
            }
        } else {
            showMessage();
            return false;
        }
    }

    private void btnAccept_actionPerformed(ActionEvent e) {
        if (saveData()) {
            parentList.refresh();
            getParentInternalFrame().close();
        }
    }

    public void setParentList(BasicPrimitivePanel _parentList) {
        parentList = _parentList;
    }

    private void calcPayment() {
        tfMonthlyFee.setValue(tfFile.getAmount() / 6);
    }

    public void mgmtRefresh() {
        calcPayment();
    }

    public void setCommerce(Commerce _commerce) {
        commerce = _commerce;
        loadData();
    }

    private int control() {
        error = NO_ERROR;
        if (tfPollTax.getString().equals("")) {
            error = ERROR_PADRON;
        } else if (tfTaxPayer.getString().equals("")) {
            error = ERROR_CONTRIBUYENTE;
        } else if (tfTradingName.getString().equals("")) {
            error = ERROR_RUBRO;
        } else if (tfTradeName.getString().equals("")) {
            error = ERROR_RAZONSOCIAL;
        } else if (tfStartDate.getDateForSQL().equals("")) {
            error = ERROR_FECHAINICIO;
        } else if (Proced.compareDates(Proced.setFormatDate(tfStartDate.getDate(), false), Proced.setFormatDate(tfEndDate.getDate(), false)) == 2) {
            error = ERROR_FECHABAJA;
        } else if (tfCatastro.getString().equals("")) {
            error = ERROR_CATASTRO;
        }
        return error;
    }

    private void showMessage() {
        switch (error) {
        case ERROR_PADRON:
            Advisor.messageBox("Debe ingresar el número de Padron", "Mensaje");
            break;
        case ERROR_CONTRIBUYENTE:
            Advisor.messageBox("Debe ingresar el nombre del Contribuyente", "Mensaje");
            break;
        case ERROR_RUBRO:
            Advisor.messageBox("Debe ingresar el Rubro del Comercio", "Mensaje");
            break;
        case ERROR_RAZONSOCIAL:
            Advisor.messageBox("Debe ingresar la Razón Social del Comercio", "Mensaje");
            break;
        case ERROR_FECHAINICIO:
            Advisor.messageBox("El campo fecha de inicio no puede estar vacio", "Mensaje");
            break;
        case ERROR_FECHABAJA:
            Advisor.messageBox("La fecha de de baja no puede ser anterior a la fecha de inicio", "Mensaje");
            break;
        case ERROR_CATASTRO:
            Advisor.messageBox("Debe ingresar el número de catastro asociado", "Mensaje");
            break;
        }
    }

    private void setEnabledAll(boolean _test) {
        for (int i = 0; i < components.size(); i++) {
            ((JComponent)components.elementAt(i)).setEnabled(_test);
        }
    }

    public void setContentObject(Object _contentObject) {
        super.setContentObject(_contentObject);
        if (_contentObject instanceof ESRIPoint) {
            commerce = new Commerce();
            commerce.setIdpadron(((ESRIPoint)_contentObject).getIdPoint());
            commerce.retrieveData();
            loadData();
        }
    }

}
