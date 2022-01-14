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
 * CadastralMgmt.java
 *
 * */
package org.digitall.apps.taxes.interfases.cadastraladmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.SwingConstants;

import org.digitall.apps.taxes.classes.Cadastral;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicTitleLabel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;

public class CadastralMgmt extends BasicPrimitivePanel {

    private BasicPanel dataPanel = new BasicPanel("");
    
    private CloseButton btnClose = new CloseButton();
    private SaveDataButton btnAccept = new SaveDataButton();
    
    private TFInput tfCadastral = new TFInput(DataTypes.INTEGER,"Cadastral",true);
    private TFInput tfOwner = new TFInput(DataTypes.STRING,"Titular del catastro",true);
    private TFInput tfDni = new TFInput(DataTypes.INTEGER,"DNI",true);
    private TFInput tfPorcentaje = new TFInput(DataTypes.STRING,"%",true);
    private TFInput tfFechaAsig = new TFInput(DataTypes.DATE,"AssignedDate",true);
    private TFInput tfFechabaja = new TFInput(DataTypes.DATE,"DropOffDate",false);
    private TFInput tfBarrio = new TFInput(DataTypes.STRING,"Neighborhood",false);
    private TFInput tfStreet = new TFInput(DataTypes.STRING,"Street",false);
    private TFInput tfStreetNumber = new TFInput(DataTypes.STRING,"Number",false);
    private TFInput tfSeccion = new TFInput(DataTypes.STRING,"Section",false);
    private TFInput tfManzana = new TFInput(DataTypes.STRING,"Apple",false);
    private TFInput tfLmanzana = new TFInput(DataTypes.STRING,"FileLetter",false);
    private TFInput tfParcela = new TFInput(DataTypes.STRING,"Parcel",false);
    private TFInput tfLparcela = new TFInput(DataTypes.STRING,"FileLetter",false);
    private TFInput tfCatOrig1 = new TFInput(DataTypes.INTEGER,"OriginalCategory1",false);
    private TFInput tfCatOrig2 = new TFInput(DataTypes.INTEGER,"OriginalCategory2",false);
    private TFInput tfSupUrbana = new TFInput(DataTypes.DOUBLE,"UrbanArea",false);
    private TFInput tfValorTerreno = new TFInput(DataTypes.MONEY,"LandValue",false);
    private TFInput tfValorMejoras = new TFInput(DataTypes.MONEY,"ImprovementsValue",false);
    private TFInput tfValorComun = new TFInput(DataTypes.MONEY,"CommonValue",false);
    private TFInput tfValorFiscal = new TFInput(DataTypes.MONEY,"TaxableValue",false);
    private CBInput cbTerreno = new CBInput(0,"Terrain",false);
    private CBInput cbDepartamento = new CBInput(0,"Department",false); 
    private CBInput cbLocation = new CBInput(0,"Location",false);
    private TFInput tfName = new TFInput(DataTypes.STRING,"Name",false);
    private TFInput tfLastName = new TFInput(DataTypes.STRING,"LastName",false);
    private CBInput cbIdentificationType = new CBInput(CachedCombo.IDENTIFICATION_TABS, "Identification");
    private CBInput cbCategoria = new CBInput(0,"Categoría",false);
    private TFInput tfIdentificationNumber = new TFInput(DataTypes.INTEGER, "IdentificationNumber", false);
    private TFInput tfMetrosDeFrente = new TFInput(DataTypes.DOUBLE, "Mts. de Frente", true);
    
    private Cadastral cadastral;
    //private CadastralList parentList;
    private BasicPrimitivePanel parentList;
    
    private int action = 0;
    int error = 0;
    
    private static final int ok = 0;
    private static final int catastro = 1;
    private static final int titular = 2;
    private static final int dni = 3;
    private static final int porcentaje = 4;
    private static final int fechaAsig = 5;
    private static final int mtsFrente = 6;
    private static final int duplicado = 7;
    private static final int sobrepasaPorcentaje = 8;
    private static final int catastrocero = 9;
    
    private final int addCadastral = 1;
    private final int addOwner = 2;
    private final int editCadastral = 3;
    private BasicTitleLabel lblApoderado = new BasicTitleLabel("Apoderado");

    private BasicCheckBox chkEsquina = new BasicCheckBox();
    private BasicCheckBox chkRural = new BasicCheckBox();

    public CadastralMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout( null );
	this.setSize(new Dimension(584, 471));
	this.setPreferredSize(new Dimension(565, 510));
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
	tfDni.setBounds(new Rectangle(310, 25, 125, 35));
        tfPorcentaje.setBounds(new Rectangle(450, 25, 65, 35));
	dataPanel.setLayout(null);
        tfCatOrig1.setBounds(new Rectangle(15, 275, 95, 35));
	tfCatOrig2.setBounds(new Rectangle(135, 275, 95, 35));
	tfSupUrbana.setBounds(new Rectangle(255, 275, 95, 35));
	cbTerreno.setBounds(new Rectangle(380, 280, 135, 35));
        cbDepartamento.setBounds(new Rectangle(15, 105, 280, 35));
        cbLocation.setBounds(new Rectangle(335, 105, 230, 35));
        tfFechaAsig.setBounds(new Rectangle(90, 65, 125, 35));
	tfFechabaja.setBounds(new Rectangle(310, 65, 125, 35));
	tfBarrio.setBounds(new Rectangle(15, 145, 185, 35));
	tfStreet.setBounds(new Rectangle(210, 145, 275, 35));
	tfStreetNumber.setBounds(new Rectangle(500, 145, 65, 35));
	tfSeccion.setBounds(new Rectangle(15, 185, 95, 35));
	tfManzana.setBounds(new Rectangle(165, 185, 80, 35));
	tfLmanzana.setBounds(new Rectangle(285, 185, 65, 35));
	tfParcela.setBounds(new Rectangle(405, 185, 80, 35));
	tfLparcela.setBounds(new Rectangle(500, 185, 65, 35));
	tfValorTerreno.setBounds(new Rectangle(15, 320, 90, 35));
	tfValorMejoras.setBounds(new Rectangle(130, 320, 95, 35));
	tfValorComun.setBounds(new Rectangle(255, 320, 95, 35));
	tfValorFiscal.setBounds(new Rectangle(375, 320, 95, 35));
	cbTerreno.setBounds(new Rectangle(365, 280, 155, 35));
	cbTerreno.setBounds(new Rectangle(375, 275, 190, 35));
	cbDepartamento.setBounds(new Rectangle(15, 105, 295, 35));
	tfCadastral.setBounds(new Rectangle(15, 25, 60, 35));
	tfOwner.setBounds(new Rectangle(90, 25, 200, 35));

	tfName.setBounds(new Rectangle(165, 380, 165, 35));
	tfLastName.setBounds(new Rectangle(15, 380, 145, 35));
	cbCategoria.setBounds(new Rectangle(15, 235, 110, 35));
	cbIdentificationType.setBounds(new Rectangle(340, 380, 95, 35));
	tfIdentificationNumber.setBounds(new Rectangle(445, 380, 120, 35));
	lblApoderado.setBounds(new Rectangle(10, 365, 555, 15));
	lblApoderado.setFont(new Font("Lucida Sans", 1, 15));
	lblApoderado.setForeground(new Color(199, 0, 0));
	lblApoderado.setHorizontalTextPosition(SwingConstants.CENTER);
	lblApoderado.setHorizontalAlignment(SwingConstants.CENTER);
	tfMetrosDeFrente.setBounds(new Rectangle(165, 230, 105, 35));
	cbCategoria.setBounds(new Rectangle(15, 230, 110, 35));
	chkEsquina.setText("Es Esquina");
	chkRural.setText("Es Rural");
	chkEsquina.setBounds(new Rectangle(285, 245, 100, 20));
	chkRural.setBounds(new Rectangle(485, 335, 80, 20));
        dataPanel.add(chkEsquina, null);
        dataPanel.add(chkRural, null);
        dataPanel.add(cbCategoria, null);
        dataPanel.add(tfMetrosDeFrente, null);
        dataPanel.add(lblApoderado, null);
        dataPanel.add(cbDepartamento,null);
        dataPanel.add(cbLocation,null);
        dataPanel.add(cbTerreno, null);
        dataPanel.add(tfSupUrbana, null);
        dataPanel.add(tfCatOrig2, null);
        dataPanel.add(tfCatOrig1, null);
        dataPanel.add(tfValorFiscal, null);
        dataPanel.add(tfValorComun, null);
        dataPanel.add(tfValorMejoras, null);
        dataPanel.add(tfValorTerreno, null);
        dataPanel.add(tfLparcela, null);
        dataPanel.add(tfParcela, null);
        dataPanel.add(tfLmanzana, null);
        dataPanel.add(tfManzana, null);
        dataPanel.add(tfSeccion, null);
        dataPanel.add(tfStreetNumber, null);
        dataPanel.add(tfStreet, null);
        dataPanel.add(tfBarrio, null);
	dataPanel.add(tfFechabaja, null);
	dataPanel.add(tfFechaAsig, null);
	dataPanel.add(tfOwner, null);
	dataPanel.add(tfDni, null);
	dataPanel.add(tfPorcentaje, null);
        dataPanel.add(tfCadastral, null);
        dataPanel.add(tfName, null);
        dataPanel.add(tfLastName, null);
        dataPanel.add(tfIdentificationNumber, null);
        dataPanel.add(cbIdentificationType, null);
        this.add(dataPanel, BorderLayout.CENTER);
	this.addButton(btnClose);
	this.addButton(btnAccept);
        //--cbTerreno.autoSize();
        //--cbDepartamento.autoSize();
        //--cbLocation.autoSize();
	//--cbCategoria.autoSize();
	//--cbIdentificationType.autoSize();
	
        loadComboTerreno();
        loadCombos();
	
	dataPanel.setBorder(BorderPanel.getBorderPanel("Agregar/Modificar un Catastro"));
	//tfSeccion.getTextField().set
    }
    
    private void setMaximosCarcteres(TFInput _tfInput, int _length){
	
    }
    private void loadComboTerreno(){
	cbTerreno.getCombo().addItem("Edificado","1");
	cbTerreno.getCombo().addItem("Baldio","2");
    }
    
    private void loadCombos(){
        cbDepartamento.loadJCombo(LibSQL.exFunction("taxes.getAllDepartments",""));
        cbLocation.loadJCombo(LibSQL.exFunction("taxes.getAllLocations",""));
	cbCategoria.loadJCombo(LibSQL.exFunction("taxes.getAllCategoriasCatastrales",""));
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
    }    

    
    private void loadData(){
	if (cadastral.getIdCatastro() != -1) {
	    cadastral.retrieveApoderadoData();
            if(action == editCadastral){
                tfCadastral.setEnabled(false);
                tfFechaAsig.setEnabled(false);
                tfFechabaja.setEnabled(false);
                tfOwner.setValue(cadastral.getDomape());
                tfDni.setValue(cadastral.getDomnudoc());
                tfPorcentaje.setValue(cadastral.getDompor());
                cadastral.setCatastroAux(cadastral.getCatastro());
                cadastral.setDomapeAux(cadastral.getDomape());
                cadastral.setDomnudocAux(cadastral.getDomnudoc());
		cbLocation.setSelectedID(cadastral.getTecloc());
            } else {
                tfCadastral.setEnabled(false);
                tfFechaAsig.setEnabled(false);
                tfFechabaja.setEnabled(false);
                cbDepartamento.setEnabled(false);
                cbLocation.setEnabled(false);
                tfBarrio.setEnabled(false);
                tfStreet.setEnabled(false);
                tfStreetNumber.setEnabled(false);
                tfSeccion.setEnabled(false);
                tfManzana.setEnabled(false);
                tfLmanzana.setEnabled(false);
                tfParcela.setEnabled(false);
                tfLparcela.setEnabled(false);
                tfCatOrig1.setEnabled(false);
                tfCatOrig2.setEnabled(false);
                tfSupUrbana.setEnabled(false);
                cbTerreno.setEnabled(false);
                tfValorTerreno.setEnabled(false);
                tfValorMejoras.setEnabled(false);
                tfValorComun.setEnabled(false);
                tfValorFiscal.setEnabled(false);
		cbLocation.setSelectedID(cadastral.getTecloc());
		tfIdentificationNumber.setEnabled(false);
		tfLastName.setEnabled(false);
		tfName.setEnabled(false);
		cbIdentificationType.setEnabled(false);
		cbCategoria.setEnabled(false);
		tfMetrosDeFrente.setEnabled(false);
		chkEsquina.setEnabled(false);
		chkRural.setEnabled(false);
            }
	    tfCadastral.setValue(cadastral.getCatastro());
            cbDepartamento.setSelectedValue(cadastral.getDepartamento());
	    cbLocation.setSelectedValue(cadastral.getLocalidad());
	    if (!cadastral.getTecfeasg().equals("null"))  {
	        tfFechaAsig.setValue(Proced.setFormatDate(cadastral.getTecfeasg(), true));
	    }
	    tfBarrio.setValue(cadastral.getDdesbarrio());
	    tfStreet.setValue(cadastral.getDcalle());
	    tfStreetNumber.setValue(cadastral.getDnumro());
	    tfSeccion.setValue(cadastral.getTecsec());
	    tfManzana.setValue(cadastral.getTecman());
	    tfLmanzana.setValue(cadastral.getTecmanl());
	    tfParcela.setValue(cadastral.getTecpar());
	    tfLparcela.setValue(cadastral.getTecparl());
	    tfCatOrig1.setValue(cadastral.getTecorg1());
	    tfCatOrig2.setValue(cadastral.getTecorg2());
	    tfSupUrbana.setValue(cadastral.getTecsuurb());
	    cbTerreno.setSelectedValue(cadastral.getTerreno());
	    System.out.println("terval: "+cadastral.getTerval());
	    tfValorTerreno.setValue(Double.parseDouble(cadastral.getTerval()));
	    tfValorMejoras.setValue(Double.parseDouble(cadastral.getTervmej()));
	    tfValorComun.setValue(Double.parseDouble(cadastral.getTervcom()));
	    tfValorFiscal.setValue(Double.parseDouble(cadastral.getValfis()));
	    tfName.setValue(cadastral.getApoderadoName());
	    tfLastName.setValue(cadastral.getApoderadoLastName());
	    cbIdentificationType.setSelectedID(cadastral.getApoderadoTipoDoc());
	    tfIdentificationNumber.setValue(cadastral.getApoderadoDoc()); 
	    if (!cadastral.getFechaBaja().equals("null"))  {
	        tfFechabaja.setValue(Proced.setFormatDate(cadastral.getFechaBaja(), true));
	    }
	    cbCategoria.setSelectedID(cadastral.getIdCategoria());
	    tfMetrosDeFrente.setValue(cadastral.getMetrosDeFrente());
	    chkEsquina.setSelected(cadastral.isEsEsquina());
	    chkRural.setSelected(!cadastral.isUrbano());
	} else {
	    tfCadastral.setEnabled(true);
	    tfFechaAsig.setEnabled(true);
	    tfFechabaja.setEnabled(false);
	}
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    private boolean ownerControl(){
        boolean ok = true;
        if (tfOwner.getValue().equals("")) {
            ok = false;
            error = titular;
        } else if(tfDni.getValue().toString().equals("0")|| !controlNumber(tfDni.getValue().toString())){
            ok = false;
            error = dni;
        } else if (tfPorcentaje.getValue().equals("")){
            ok = false;
            error = porcentaje;
        }else if (Double.parseDouble(tfMetrosDeFrente.getValue().toString()) <= 0 ){
            ok = false;
            error = mtsFrente;
        }
        return ok;
    }

    private boolean editCadastralControl(){
        boolean ok = true;
        if (tfOwner.getValue().equals("")) {
            ok = false;
            error = titular;
        } else if(tfDni.getValue().toString().equals("0")|| !controlNumber(tfDni.getValue().toString())){
            ok = false;
            error = dni;
        } else if (tfPorcentaje.getValue().equals("")){
            ok = false;
            error = porcentaje;
        } else if (Double.parseDouble(tfMetrosDeFrente.getValue().toString()) <= 0 ){
            ok = false;
            error = mtsFrente;
        }
        return ok;
    }
    
    private boolean controlNumber(String _value){
	boolean ok = true;
	if(isNumber(_value)){
	    if(Double.parseDouble(_value) == 0.0){
		ok = false;
	    }
	}
	return(ok);
    }
    
    private boolean isNumber(String _value) {
	String numbers = "0123456789";
	String letra = "";
	int i = 0;
	boolean hayLetra = false;
	while(( i < _value.toString().length())&&(!hayLetra)){
	    letra = "" + _value.charAt(i);
	    if(numbers.indexOf(letra) == -1){
		hayLetra = true;
	    }
	    i++;
	}
	return(!hayLetra);
    }

    private void loadCadastralForm(){
        cadastral.setDomape(tfOwner.getValue().toString());
        cadastral.setDomnudoc(tfDni.getValue().toString());
        cadastral.setDompor(tfPorcentaje.getValue().toString());
        cadastral.setDepartamento(cbDepartamento.getSelectedItem().toString());
        cadastral.setTecloc(Integer.parseInt(cbLocation.getSelectedValue().toString()));
        cadastral.setLocalidad(cbLocation.getSelectedValueRef().toString());
        cadastral.setDdesbarrio(tfBarrio.getValue().toString());
        cadastral.setDcalle(tfStreet.getValue().toString());
        cadastral.setDnumro(tfStreetNumber.getValue().toString());
        cadastral.setTecsec(tfSeccion.getValue().toString());
        cadastral.setTecman(tfManzana.getValue().toString());
        cadastral.setTecmanl(tfLmanzana.getValue().toString());
        cadastral.setTecpar(tfParcela.getValue().toString());
        cadastral.setTecparl(tfLparcela.getValue().toString());
        cadastral.setTecorg1(Integer.parseInt(tfCatOrig1.getValue().toString()));
        cadastral.setTecorg2(Integer.parseInt(tfCatOrig2.getValue().toString()));
        cadastral.setTecsuurb(Double.parseDouble(tfSupUrbana.getValue().toString()));
        cadastral.setTerreno(cbTerreno.getSelectedItem().toString());
        cadastral.setTerval(tfValorTerreno.getValue().toString());
        cadastral.setTervmej(tfValorMejoras.getValue().toString());
        cadastral.setTervcom(tfValorComun.getValue().toString());
        cadastral.setValfis(tfValorFiscal.getValue().toString());
	cadastral.setApoderadoName(tfName.getString());
	cadastral.setApoderadoLastName(tfLastName.getString());
	cadastral.setApoderadoTipoDoc(cbIdentificationType.getSelectedValue().toString());
	cadastral.setApoderadoDoc(tfIdentificationNumber.getString());
        if (!tfFechabaja.getString().equals(""))  {
            cadastral.setFechaBaja("" + Proced.setFormatDate(tfFechabaja.getString(),false));    
        }
	cadastral.setIdCategoria(Integer.parseInt(cbCategoria.getSelectedValue().toString()));
	cadastral.setMetrosDeFrente(tfMetrosDeFrente.getDouble());
	cadastral.setEsEsquina(chkEsquina.isSelected());
	cadastral.setUrbano(!chkRural.isSelected());
    }

    private boolean addCadastralControl(){
        boolean ok = true;
	if (tfCadastral.getValue().equals("")) {
	    ok = false;
	    error = catastro;
	} else if ( Integer.parseInt(tfCadastral.getValue().toString()) == 0 ) {
	    ok = false;
	    error = catastrocero;
	} else if (LibSQL.getBoolean("taxes.existCadastral",""+tfCadastral.getValue())) {
	    ok = false;
	    error = duplicado;
	} else if (tfOwner.getValue().equals("")) {
            ok = false;
            error = titular;
        } else if(tfDni.getValue().toString().equals("0")|| !controlNumber(tfDni.getValue().toString())){
            ok = false;
            error = dni;
        } else if (tfPorcentaje.getValue().equals("") || tfPorcentaje.getValue().equals("0")){
            ok = false;
            error = porcentaje;
        }else if (tfFechaAsig.getString().trim().equals("")){
            ok = false;
            error = fechaAsig;
        } else if (Double.parseDouble(tfMetrosDeFrente.getValue().toString()) <= 0 ){
            ok = false;
            error = mtsFrente;
        }
        return ok;
    }
    private void loadNewCadastral(){
        cadastral.setCatastro(Integer.parseInt(tfCadastral.getValue().toString()));
        cadastral.setDomape(tfOwner.getValue().toString());
        cadastral.setDomnudoc(tfDni.getValue().toString());
        cadastral.setDompor(tfPorcentaje.getValue().toString());
        cadastral.setTecfeasg("" + Proced.setFormatDate("" + tfFechaAsig.getString().trim(),false));        
        cadastral.setDpto(Integer.parseInt(cbDepartamento.getSelectedValue().toString()));
        cadastral.setDepartamento(cbDepartamento.getSelectedItem().toString());
        cadastral.setTecloc(Integer.parseInt(cbLocation.getSelectedValue().toString()));
        cadastral.setLocalidad(cbLocation.getSelectedValueRef().toString());
        cadastral.setDdesbarrio(tfBarrio.getValue().toString());
        cadastral.setDcalle(tfStreet.getValue().toString());
        cadastral.setDnumro(tfStreetNumber.getValue().toString());
        cadastral.setTecsec(tfSeccion.getValue().toString());
        cadastral.setTecman(tfManzana.getValue().toString());
        cadastral.setTecmanl(tfLmanzana.getValue().toString());
        cadastral.setTecpar(tfParcela.getValue().toString());
        cadastral.setTecparl(tfLparcela.getValue().toString());
        cadastral.setTecorg1(Integer.parseInt(tfCatOrig1.getValue().toString()));
        cadastral.setTecorg2(Integer.parseInt(tfCatOrig2.getValue().toString()));
        cadastral.setTecsuurb(Double.parseDouble(tfSupUrbana.getValue().toString()));
        cadastral.setTerreno(cbTerreno.getSelectedItem().toString());
        cadastral.setTerval(tfValorTerreno.getValue().toString());
        cadastral.setTervmej(tfValorMejoras.getValue().toString());
        cadastral.setTervcom(tfValorComun.getValue().toString());
        cadastral.setValfis(tfValorFiscal.getValue().toString());
	cadastral.setApoderadoName(tfName.getString());
	cadastral.setApoderadoLastName(tfLastName.getString());
	cadastral.setApoderadoTipoDoc(cbIdentificationType.getSelectedValue().toString());
	cadastral.setApoderadoDoc(tfIdentificationNumber.getString());
        cadastral.setIdCategoria(Integer.parseInt(cbCategoria.getSelectedValue().toString()));
	cadastral.setMetrosDeFrente(tfMetrosDeFrente.getDouble());
	cadastral.setEsEsquina(chkEsquina.isSelected());
	cadastral.setUrbano(!chkRural.isSelected());
    }

    public boolean saveData(){
        boolean ret = false;
        if (action == addCadastral) {
            if (addCadastralControl()){
                loadNewCadastral();
                if (cadastral.saveData(action) >= 0){
                    ret = true;
                } else {
                    ret = false;
                }
            } else {
                showMessage();
                ret = false;  
            }
        } else if (action == addOwner) {
            if (ownerControl()){
                cadastral.setDomape(tfOwner.getValue().toString());
                cadastral.setDomnudoc(tfDni.getValue().toString());
                cadastral.setDompor(tfPorcentaje.getValue().toString());
                if (cadastral.saveData(action) >= 0){
                    ret = true;
                } else {
                    ret = false;
                }
            } else {
                showMessage();
                ret = false;  
            }
        } else if (action == editCadastral){
            if (editCadastralControl()){
                loadCadastralForm();
                if (cadastral.saveData(action) >= 0){
                    ret = true;
                } else {
                    ret = false;
                }
            } else {
                showMessage();
                ret = false;  
            }
        }
        
        return ret;
    }
    
    private void btnAccept_actionPerformed(ActionEvent e) {
	if (saveData()) {
            Advisor.messageBox("Se actualizó correctamente la Categoría TGS en el GIS", "Aviso");
	    parentList.refresh();
	    getParentInternalFrame().close();
	} 
    }

     public void setParentList(BasicPrimitivePanel _parentList) {
         parentList = _parentList;
     }

    public void setCadastral(Cadastral _cadastral) {
        cadastral = _cadastral;
        loadData();
    }

    private int control() {
	error = ok;
	if (tfCadastral.getString().equals(""))  {
	    error = catastro;
	} else if (tfOwner.getString().equals("")) {
	    error = titular;
	} else if(tfFechaAsig.getString().equals("")) {
	    error = fechaAsig;
	}
	return error;
    }

    private void showMessage() {
	switch (error)  {
	    case catastro: 
		    Advisor.messageBox("Debe ingresar el número de Catastro", "Mensaje");
		break;
	    case titular: 
	            Advisor.messageBox("Debe ingresar el nombre del Titular", "Mensaje");
	        break;
	    case dni: 
	            Advisor.messageBox("Debe ingresar el D.N.I. del Propietario", "Mensaje");           
	        break;
	    case porcentaje: 
	            Advisor.messageBox("El porcentaje ingresado no es valido", "Mensaje");
	        break;
	    case fechaAsig: 
	            Advisor.messageBox("El campo Fecha de asignación no puede estar vacio", "Mensaje");           
	        break;
	    case mtsFrente: 
	            Advisor.messageBox("El campo Mts. de Frente debe ser mayor que cero", "Mensaje");
	        break;
	    case duplicado: 
	            Advisor.messageBox("El catastro Nº "+ tfCadastral.getValue() +" ya existe", "Mensaje");
	        break;
	    case sobrepasaPorcentaje: 
	            Advisor.messageBox("La suma de porcentajes de titularidad sobrepasa el 100%", "Mensaje");
	        break;
	    case catastrocero: 
	            Advisor.messageBox("El número de Catastro no puede ser cero", "Mensaje");
	        break;
	}
    }

  
    public void setAction(int _action) {
        action = _action;
        //setForm();
    }
    
}
