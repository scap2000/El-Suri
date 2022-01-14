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
 * CarsMgmt_old.java
 *
 * */
package org.digitall.apps.taxes.interfases.carsadmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.text.MaskFormatter;

import org.digitall.apps.taxes.classes.Car;
import org.digitall.apps.taxes.classes.FeesxCategory;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;


public class CarsMgmt_old extends BasicPrimitivePanel {

    private BasicPanel dataPanel = new BasicPanel("Agregar/Modificar un Vehículo");
    
    private CloseButton btnClose = new CloseButton();
    private SaveDataButton btnAccept = new SaveDataButton();
    private FindButton btnAddCuotaxCategoria = new FindButton();
    private AcceptButton btnSeguridad = new AcceptButton();
    private ModifyButton btnModificarFechaBaja = new ModifyButton();

    private TFInput tfDomain = new TFInput(DataTypes.STRING,"Domain",true);
    private TFInput tfHolder = new TFInput(DataTypes.STRING,"OwnerDomain",true);
    private TFInput tfType = new TFInput(DataTypes.STRING,"Type",false);
    private TFInput tfBrand = new TFInput(DataTypes.STRING,"Brand",false);
    private TFInput tfMotor = new TFInput(DataTypes.STRING,"Motor",false);
    private TFInput tfAnnualFee = new TFInput(DataTypes.MONEY,"AnnualFee",false);
    private TFInput tfTwoMonthlyFee = new TFInput(DataTypes.MONEY,"TwoMonthlyFee",false);
    private TFInput tfPaidSince = new TFInput(DataTypes.DATE,"PaidSince",true);
    private TFInput tfFechaExencion = new TFInput(DataTypes.DATE,"ToExemptDate",false);
    private TFInput tfFechaBaja = new TFInput(DataTypes.DATE,"DropOffDate",false);
    private TFInput tfDni = new TFInput(DataTypes.STRING,"DNI",false);
    private TFInput tfObservations = new TFInput(DataTypes.STRING,"Observations",false);
    private TFInput tfAddress = new TFInput(DataTypes.STRING,"Domicilio Legal",false);

    private CBInput cbCategory = new CBInput(CachedCombo.CATEGORYTYPES,"Category",false);
    private CBInput cbModels = new CBInput(0,"Model",false);
    private CBInput cbEximido = new CBInput(0,"Eximido", false);
    private CBInput cbDomainType = new CBInput(0,"DomainType", false);
    private CBInput cbTipoVehiculo = new CBInput(CachedCombo.AUTOMOTORES_TABS,"Tipo Vehiculo",false);

    private Car car;
    private BasicPrimitivePanel parentList;
    private FeesxCategoryMgmt feesxCategoryMgmt;
    private FeesxCategory feesxCategory;
    private ModificarFechaBajaAutomotorMgmt modificarFechaBajaAutomotorMgmt;
    
    int error = 0;
    private static final int ok = 0;
    private static final int dominio = 1;
    private static final int titular = 2;
    private static final int pagaDesde = 3;
    //private static final int Fee = 4;
    private static final int formatoDominio = 5;
    private static final int fechaInvalida = 6;
    private static final int yaExiste = 7;
    private static final int fechaBajaInvalida = 8;

    private JLabel lblDomain = new JLabel();
    private JLabel lblDomainTitle = new JLabel();

    private MaskFormatter patenteNueva;
    private MaskFormatter patenteVieja;

    private BasicCheckBox chkDiscapacitado = new BasicCheckBox();
    
    private Vector components = new Vector();
    


    public CarsMgmt_old() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(565, 317));
	this.setPreferredSize(new Dimension(565, 300));
	btnClose.setBounds(new Rectangle(540, 535, 40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	btnAccept.setBounds(new Rectangle(490, 535, 40, 25));
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	tfDomain.setBounds(new Rectangle(385, 25, 160, 35));
        
	cbCategory.setBounds(new Rectangle(10, 190, 65, 35));
        
	dataPanel.setBounds(new Rectangle(5, 0, 555, 220));
	dataPanel.setLayout(null);
	tfHolder.setBounds(new Rectangle(10, 65, 190, 35));
        
	cbEximido.setBounds(new Rectangle(190, 245, 60, 35));
        
        cbDomainType.setBounds(new Rectangle(210, 25, 145, 35));
	
        lblDomain.setBounds(new Rectangle(15, 40, 185, 20));
        lblDomain.setFont(new Font("Dialog", 1, 15));
        lblDomain.setForeground(Color.red);
        lblDomain.setBackground(Color.white);
        lblDomainTitle.setText("Dominio Registrado");
        lblDomainTitle.setBounds(new Rectangle(15, 25, 185, 15));
        lblDomainTitle.setForeground(Color.white);
        lblDomainTitle.setFont(new Font("Dialog", 0, 12));
        tfFechaExencion.setBounds(new Rectangle(265, 235, 100, 35));
	
	tfFechaBaja.setBounds(new Rectangle(400, 235, 115, 35));
	
	cbModels.setBounds(new Rectangle(90, 190, 75, 35));
        
	tfAddress.setBounds(new Rectangle(10, 110, 280, 35));
        
	tfObservations.setBounds(new Rectangle(300, 110, 245, 35));
        
	tfType.setBounds(new Rectangle(10, 150, 145, 35));
        
	tfBrand.setBounds(new Rectangle(190, 150, 175, 35));
        
	tfMotor.setBounds(new Rectangle(400, 150, 145, 35));
        
        dataPanel.add(btnModificarFechaBaja, null);
        dataPanel.add(btnSeguridad, null);
	dataPanel.add(chkDiscapacitado, null);
	dataPanel.add(cbTipoVehiculo, null);
	dataPanel.add(btnAddCuotaxCategoria, null);
	dataPanel.add(lblDomainTitle, null);
        dataPanel.add(lblDomain, null);
	dataPanel.add(tfDni, null);
        dataPanel.add(tfFechaBaja, null);
        dataPanel.add(tfFechaExencion, null);
        dataPanel.add(cbModels, null);
	dataPanel.add(tfHolder, null);
        dataPanel.add(tfDomain, null);
	dataPanel.add(cbCategory, null);
	dataPanel.add(tfMotor, null);
	dataPanel.add(tfBrand, null);
	dataPanel.add(tfType, null);
	dataPanel.add(tfObservations, null);
        dataPanel.add(tfAddress, null);
	dataPanel.add(cbEximido, null);
	dataPanel.add(cbDomainType, null);
	dataPanel.add(tfPaidSince, null);
	dataPanel.add(tfAnnualFee, null);
        dataPanel.add(tfTwoMonthlyFee, null);
        this.add(dataPanel, BorderLayout.CENTER);
	this.addButton(btnClose);
	this.addButton(btnAccept);
	cbEximido.autoSize();
	cbCategory.autoSize();
	cbModels.autoSize();
	cbDomainType.autoSize();
	tfAnnualFee.setBounds(new Rectangle(250, 190, 115, 35));
        
        tfTwoMonthlyFee.setBounds(new Rectangle(400, 190, 115, 35));
        
	tfPaidSince.setBounds(new Rectangle(10, 235, 115, 35));
	
	cbEximido.getCombo().addItem("NO","1");
	cbEximido.getCombo().addItem("SI","2");
	cbCategory.addItemListener(new ItemListener() {
	      public void itemStateChanged(ItemEvent evt) {
		  if (evt.getStateChange() == ItemEvent.SELECTED) {
		      getFee();
		  }
	      }
	});
	cbModels.addItemListener(new ItemListener() {
	      public void itemStateChanged(ItemEvent evt) {
		  if (evt.getStateChange() == ItemEvent.SELECTED) {
		      getFee();;
		  }
	      }
	});
	cbEximido.addItemListener(new ItemListener() {
	    public void itemStateChanged(ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED) {
		    setFechaEximido();
		    
		}
	    }
	    });
        patenteNueva = new MaskFormatter("UUU-###");
        patenteVieja = new MaskFormatter("U-#######");
        patenteNueva.setPlaceholderCharacter('_');
        patenteVieja.setPlaceholderCharacter('_');
	btnAddCuotaxCategoria.setBounds(new Rectangle(370, 215, 25, 20));
	btnAddCuotaxCategoria.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnAddCuotaxCategoria_actionPerformed(e);
		}
	    }
	);
        btnModificarFechaBaja.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnModificarFechaBaja_actionPerformed(e);
                }
            }
        );
	tfDni.setBounds(new Rectangle(210, 65, 145, 35));
	
	tfAnnualFee.setEnabled(false);
	tfTwoMonthlyFee.setEnabled(false);
	loadCombos();
        cbDomainType.addItemListener(new ItemListener() {
              public void itemStateChanged(ItemEvent evt) {
                  if (evt.getStateChange() == ItemEvent.SELECTED) {
                      formatDomain();
                  }
              }
        });
	setFechaEximido();
        btnAddCuotaxCategoria.setEnabled(false);
	cbEximido.setVisible(false);
	btnAddCuotaxCategoria.setVisible(false);
	cbTipoVehiculo.setBounds(new Rectangle(385, 70, 90, 35));
	cbTipoVehiculo.autoSize();
	cbTipoVehiculo.setVisible(false);
	chkDiscapacitado.setText("Discapacitado");
	chkDiscapacitado.setBounds(new Rectangle(385, 75, 110, 25));
        btnModificarFechaBaja.setToolTipText("Modificar la Fecha de baja");
        btnModificarFechaBaja.setBounds(new Rectangle(525, 245, 30, 25));
        btnModificarFechaBaja.setSize(new Dimension(30, 25));
        btnSeguridad.setToolTipText("Desbloquear combos");
	btnSeguridad.setBounds(new Rectangle(190, 200, 40, 25));
	btnSeguridad.setSize(new Dimension(30, 25));
	btnSeguridad.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnSeguridad_actionPerformed(e);
		}
	    }
	);
        
        components.add(cbDomainType);
        components.add(tfDomain);
        components.add(tfHolder);
        components.add(tfDni);
        components.add(chkDiscapacitado);
        components.add(tfAddress);
        components.add(tfObservations);
        components.add(tfType);
        components.add(tfBrand);
        components.add(tfMotor);

        components.add(cbCategory);
        components.add(cbModels);
        components.add(cbEximido);
        components.add(tfFechaExencion);
        components.add(tfFechaBaja);
        components.add(tfAnnualFee);
        components.add(tfTwoMonthlyFee);
        components.add(tfPaidSince);
        
        /*
        components.add(tfDomain);
        components.add(cbCategory);
        components.add(tfHolder);
        components.add(cbEximido);
        components.add(cbDomainType);
        components.add(tfFechaExencion);
        components.add(tfFechaBaja);
        components.add(cbModels);
        components.add(tfAddress);
        components.add(tfObservations);
        components.add(tfType);
        components.add(tfBrand);
        components.add(tfMotor);
        components.add(tfAnnualFee);
        components.add(tfTwoMonthlyFee);
        components.add(tfPaidSince);
        components.add(tfDni);
	components.add(chkDiscapacitado);
        */
	tfFechaExencion.setEnabled(false);
	btnSeguridad.setVisible(false);
        btnModificarFechaBaja.setVisible(true);
    }
    
     public void setParentInternalFrame(ExtendedInternalFrame _e) {
	 super.setParentInternalFrame(_e);
	 //getParentInternalFrame().getGeneralButtons().addButton(btnFeesxCategoryList);
     }
    
    private void loadDomainTypeCombo(boolean _valor){
        if (_valor) {
            cbDomainType.getCombo().addItem("Sin Asignar","0");
        }
        cbDomainType.getCombo().addItem("Patente Nueva","1");
        cbDomainType.getCombo().addItem("Patente Vieja","2");
        cbDomainType.getCombo().addItem("DNI / CUIT","3");
    }
    
    private void setFechaEximido() {
	/*if (cbEximido.getSelectedItem().equals("SI"))  {
	    tfFechaExencion.setEnabled(true);
	} else {
	    tfFechaExencion.setValue("");
	    tfFechaExencion.setEnabled(false);
	}
	*/
    }
    
    private void formatDomain() {
        patenteNueva.uninstall();
        patenteVieja.uninstall();
        tfDomain.setValue((car!=null)?car.getDominio().replaceAll(" ", "-"):"");
        switch (new Integer(cbDomainType.getSelectedValue().toString())) {
            case 1 :
                //tfDomain.setValue("ZZZ-999");
                patenteNueva.install(tfDomain.getTextField());
                break;
            case 2 :
                //tfDomain.setValue("Z-9999999");
                patenteVieja.install(tfDomain.getTextField());
                break;
        }
    }

    private void loadCombos(){
	// carga combo models
        int actualYear = Integer.parseInt("0" + Environment.currentYear);
	int cont = 0;
	for (int i = 1960 ; i <= (actualYear + 1) ; i++)  {
	    cont++;
	    cbModels.getCombo().addItem(i,cont);
	}
	cbModels.setSelectedID(cont - 1);
	getFee();
    }
    
    private void getFee(){
	double montoAnual = LibSQL.getDouble("taxes.getCuotaAnual",""+ cbModels.getSelectedItem() + "," + cbCategory.getSelectedItem());
	tfAnnualFee.setValue(montoAnual);
	tfTwoMonthlyFee.setValue((montoAnual / 6.0));
    }
    
    public void load(){
	loadData();
    }

    private void loadData(){
	formatDomain();
	 if (car.getIddominio() != -1) {
	    loadDomainTypeCombo(true);
	    tfPaidSince.setEnabled(false);
            lblDomain.setText(car.getDominio());
	    tfDomain.getTextField().setText(car.getDominio());
            cbDomainType.setSelectedID(car.getTipoDominio());
	    tfHolder.setValue(car.getTitular());
	    tfType.setValue(car.getTipo());
            tfDni.setValue(car.getDni());
	    tfBrand.setValue(car.getMarca());
	    tfMotor.setValue(car.getNromotor());
	    tfAddress.setValue(car.getDomicilio());
	    tfObservations.setValue(car.getObservaciones());
	    cbCategory.setSelectedValue(car.getIdtipocategoria());
	    cbModels.setSelectedValue(car.getModelo());
	    
            if (!car.getPagadesde().equals("null"))  {
	        tfPaidSince.setValue("" + Proced.setFormatDate(car.getPagadesde(),true));
	    }
	    if (car.getFechaExencion() != null)  {
		tfFechaExencion.setValue("" + Proced.setFormatDate(car.getFechaExencion(),true));
	        cbEximido.setSelectedValue("SI");
	    } else {
	        cbEximido.setSelectedValue("NO");
	    }
	    if (car.getFechaBaja() != null)  {
                tfFechaBaja.setValue("" + Proced.setFormatDate(car.getFechaBaja(),true));
		setEnabledAll(false);
            } else{
                enabledComponents();
            }
	    chkDiscapacitado.setSelected(car.isDiscapacitado());
	    
	    cbCategory.setEnabled(false);
	    cbModels.setEnabled(false);
	    getFee();
	    
	    if (LibSQL.getBoolean("taxes.isUserToModifyCarData","3,"+ car.getIdAutomotor() )) {
		tfFechaBaja.setEnabled(true);
	    } else {
		tfFechaBaja.setEnabled(false);
	    } 
	 } else {
	    loadDomainTypeCombo(false);
            lblDomain.setVisible(false);
            lblDomainTitle.setVisible(false);
	    tfFechaBaja.setEnabled(false);
	 }
    }
    
    private void setEnabledAll(boolean _test){
	for(int i = 0; i < components.size(); i++){
	    ((JComponent)components.elementAt(i)).setEnabled(_test);
	}
    }
    
    private void enabledComponents(){
        for(int i = 0; i < 10; i++){
            ((JComponent)components.elementAt(i)).setEnabled(true);
        }
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    private void setData(){
        car.setDominio(tfDomain.getString());
        car.setTitular(tfHolder.getString());
        car.setDni(tfDni.getValue().toString());
        car.setTipo(tfType.getString());
        car.setMarca(tfBrand.getString());
        car.setNromotor(tfMotor.getString());
        car.setIdtipocategoria(cbCategory.getSelectedItem().toString());
        car.setModelo(Integer.parseInt(cbModels.getSelectedItem().toString()));
        if (cbEximido.getSelectedItem().equals("SI"))  {
            car.setEximido(true);
        } else  {
            car.setEximido(false);
        }
	car.setDomicilio(tfAddress.getString());
	car.setObservaciones(tfObservations.getString());
        car.setCuota(tfTwoMonthlyFee.getAmount());
        car.setValoranual(tfAnnualFee.getAmount());
        if (!tfPaidSince.getString().equals(""))  {
            car.setPagadesde("" + Proced.setFormatDate(tfPaidSince.getString(),false));    
        }
        if (!tfFechaExencion.getString().equals(""))  {
            car.setFechaExencion("" + Proced.setFormatDate(tfFechaExencion.getString(),false));    
        }
	
        if (!tfFechaBaja.getString().equals(""))  {
            car.setFechaBaja("" + Proced.setFormatDate(tfFechaBaja.getString(),false));    
        } else {
	    car.setFechaBaja("");
	}
        car.setTipoDominio(Integer.parseInt(cbDomainType.getSelectedValue().toString()));
	if (chkDiscapacitado.isSelected())  {
	    car.setDiscapacitado(true);
	} else  {
	    car.setDiscapacitado(false);
	}
	
    }

    public boolean saveData(){
	if (control() == ok)  {
	    if (car == null){
	        car = new Car();
	    }
            setData();
	    if (car.saveData() >= 0) {
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

    private void callFeesxCategory(){
	feesxCategory = new FeesxCategory();
	feesxCategory.setAnio(Integer.parseInt("0"+ cbModels.getSelectedItem()));
	feesxCategory.setIdtipocategoria(Integer.parseInt("0" + cbCategory.getSelectedValue()));
	feesxCategory.setCategoria("" + cbCategory.getSelectedItem());
	feesxCategory.setNuevo(true);
	
	feesxCategoryMgmt = new FeesxCategoryMgmt();
	feesxCategoryMgmt.setFeesxCategory(feesxCategory);
	feesxCategoryMgmt.setParentMgmt(this);
	 
	ExtendedInternalFrame feesxCategoryMgmtContainer = new ExtendedInternalFrame("Agregar/Modificar");
	feesxCategoryMgmtContainer.setCentralPanel(feesxCategoryMgmt);
	feesxCategoryMgmtContainer.show();
    }

    public void recalcCuota(){
	if (car.getIddominio() != -1) {
	    car.getFeesxCategory().retrieveData();
	} else {
	    feesxCategory = new FeesxCategory();
	    feesxCategory.setAnio(Integer.parseInt(cbModels.getSelectedItem().toString()));
	    feesxCategory.setIdtipocategoria(Integer.parseInt(cbCategory.getSelectedItem().toString()));
	    feesxCategory.retrieveData();
	    car.setFeesxCategory(feesxCategory);
	}
	if (car.getFeesxCategory().getAnual() > 0)  {
	    tfAnnualFee.setValue(car.getFeesxCategory().getAnual());
	    tfTwoMonthlyFee.setValue(car.getFeesxCategory().getCuota());
	    btnAddCuotaxCategoria.setEnabled(false);
	} else  {
	    btnAddCuotaxCategoria.setEnabled(true);
	}
    }

    public void setCar(Car _car) {
	car = _car;
    }

    private int control() {
	error = ok;
	if (car.getIdAutomotor() == -1)  {
	    if (LibSQL.getBoolean("taxes.existDomain","'"+tfDomain.getTextField().getText().trim()+"'")) {
		error = yaExiste;
	    }
	}
	if(error != yaExiste){
	    if (tfDomain.getTextField().getText().trim().equals(""))  {
	        error = dominio;
	    } else if (tfHolder.getString().equals("")) {
	        error = titular;
	    } else if(tfPaidSince.getString().equals("")) {
	        error = pagaDesde;
	    } else if ((tfDomain.getTextField().getValue().toString() == "") || (tfDomain.getTextField().getValue().toString().contains("_"))) {
	        error = formatoDominio;
	    } else if (getYear(Proced.setFormatDate(tfPaidSince.getString(),false)) < Integer.parseInt(cbModels.getSelectedItem().toString())) {
	        error = fechaInvalida;
	    }else if (Proced.compareDates(Proced.setFormatDate(tfPaidSince.getString(),false),Proced.setFormatDate(tfFechaBaja.getString(),false)) == 2) {
	        error = fechaBajaInvalida;
	    }     
	}
	return error;
    }
    
    private int getYear(String _date){
	return (Integer.parseInt(_date.substring(0,_date.indexOf("-"))));
    }

    private void showMessage() {
	switch (error)  {
	    case 1: 
		    Advisor.messageBox("Debe ingresar el número de Dominio", "Mensaje");
		break;
	    case 2: 
		    Advisor.messageBox("Debe ingresar el nombre del Titular", "Mensaje");
		break;
	    case 3: 
		    Advisor.messageBox("El campo Paga desde no puede estar vacio", "Mensaje");           
		break;
	    case 5: 
	            /*Advisor.messageBox("No existe el valor de la cuota, debe agregarlo para poder grabar los datos", "Mensaje");
		    btnAddCuotaxCategoria.setEnabled(true);*/
	            Advisor.messageBox("El formato del dominio es incorrecto", "Mensaje");           
	        break;
	    case fechaInvalida: 
	            Advisor.messageBox("EL campo Paga desde debe tener una fecha mayor o igual al año del modelo", "Mensaje");           
	        break;
	    case yaExiste: 
	            Advisor.messageBox("Ya existe el Dominio: "+tfDomain.getValue().toString(), "Mensaje");           
	        break;
	    case fechaBajaInvalida: 
	            Advisor.messageBox("La fecha de baja debe ser mayor o igual a la fecha de inicio.", "Mensaje");           
	        break;
	}
    }
    
    public void setBtn(boolean _valor){
	//btnAddFeesxCategory.setEnabled(_valor);
    }

    private void btnAddCuotaxCategoria_actionPerformed(ActionEvent e) {
	callFeesxCategory();
    }

    private void btnSeguridad_actionPerformed(ActionEvent e) {
	cbCategory.setEnabled(true);
	cbModels.setEnabled(true);
	car.setModifyModelCategory(true);
    }	
    
    /**2010-03-09 (moraless)*/
    public void recargarFormulario(){
        car.retrieveData();
        loadData();
    }
    
    /**2010-03-09 (moraless)*/
    private void btnModificarFechaBaja_actionPerformed(ActionEvent e) {
        //modificarFechaBajaAutomotorMgmt = new ModificarFechaBajaAutomotorMgmt(car.getIddominio());
        //modificarFechaBajaAutomotorMgmt.setParentList(this);
        
        ExtendedInternalFrame modificarFechaBajaAutomotorMgmtContainer = new ExtendedInternalFrame("Modificar");
        modificarFechaBajaAutomotorMgmtContainer.setCentralPanel(modificarFechaBajaAutomotorMgmt);
        modificarFechaBajaAutomotorMgmtContainer.show();
        
        /*
        feesxCategory.setAnio(Integer.parseInt("0"+ cbModels.getSelectedItem()));
        feesxCategory.setIdtipocategoria(Integer.parseInt("0" + cbCategory.getSelectedValue()));
        feesxCategory.setCategoria("" + cbCategory.getSelectedItem());
        feesxCategory.setNuevo(true);
        
        feesxCategoryMgmt = new FeesxCategoryMgmt();
        feesxCategoryMgmt.setFeesxCategory(feesxCategory);
        feesxCategoryMgmt.setParentMgmt(this);
         
        ExtendedInternalFrame feesxCategoryMgmtContainer = new ExtendedInternalFrame("Agregar/Modificar");
        feesxCategoryMgmtContainer.setCentralPanel(feesxCategoryMgmt);
        feesxCategoryMgmtContainer.show();
    */
    }
}
