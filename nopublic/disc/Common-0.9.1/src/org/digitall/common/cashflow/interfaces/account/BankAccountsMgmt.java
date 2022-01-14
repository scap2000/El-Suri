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
 * BankAccountsMgmt.java
 *
 * */
package org.digitall.common.cashflow.interfaces.account;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;

import org.digitall.common.cashflow.classes.BankAccount;
import org.digitall.common.cashflow.classes.Account;
import org.digitall.common.cashflow.classes.AccountTypes;
import org.digitall.common.cashflow.interfaces.accounting.AccountsMain;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.combos.JCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class BankAccountsMgmt extends BasicPrimitivePanel {

    private TFInput tfNumber = new TFInput(DataTypes.STRING,"Number",true);
    private TFInput tfAmount = new TFInput(DataTypes.MONEY,"AvailableAmount",false);
    private TFInput tfCBU = new TFInput(DataTypes.STRING,"CBU",true);
    private TFInput tfOwner = new TFInput(DataTypes.STRING,"OwnerAccount",true);
    private TFInput tfStartDate = new TFInput(DataTypes.DATE,"Date",true);
    private CBInput cbBank = new CBInput(CachedCombo.BANK,"Bank");
    private CBInput cbOwner = new CBInput(0,"OwnerAccount");
    private CBInput cbAccountType = new CBInput(CachedCombo.ACCOUNTTYPE, "AccountType");
    private CBInput cbMoneyType = new CBInput(CachedCombo.MONEYTYPE,"MoneyType");
    private CBInput cbAccount = new CBInput(0,"Accounting",false);
    private BasicRadioButton rbtnPerson = new BasicRadioButton("Persona");
    private BasicRadioButton rbtnCompany = new BasicRadioButton("Compañía");
    private SaveDataButton btnAccept = new SaveDataButton();
    private CloseButton btnClose = new CloseButton();
    private BasicPanel content = new BasicPanel("Agregar/Modificar Cuenta Bancaria");
    private BankAccount bankAccount;
    private BankAccountsList parentList;
    private TFInput tfFindAccount = new TFInput(DataTypes.STRING,"FindAccounting",false);
    private Account account;
    private int error = 0;
    
    public BankAccountsMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	} 
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(674, 196));
	this.setPreferredSize(new Dimension(605, 215));
	tfFindAccount.setBounds(new Rectangle(220, 110, 100, 35));
	cbAccount.setBounds(new Rectangle(330, 110, 300, 35));
	tfNumber.setBounds(new Rectangle(220, 30, 130, 35));
	tfAmount.setBounds(new Rectangle(445, 30, 95, 35));
	tfCBU.setBounds(new Rectangle(15, 110, 190, 35));
	cbBank.setBounds(new Rectangle(15, 70, 190, 35));
	cbOwner.setBounds(new Rectangle(15, 285, 105, 35));
	cbAccountType.setBounds(new Rectangle(15, 30, 150, 35));
	cbMoneyType.setBounds(new Rectangle(375, 30, 60, 35));
	rbtnPerson.setBounds(new Rectangle(465, 85, 83, 20));
	rbtnPerson.setHorizontalAlignment(SwingConstants.CENTER);
	rbtnPerson.setHorizontalTextPosition(SwingConstants.RIGHT);
	rbtnPerson.setSize(new Dimension(83, 20));
	rbtnPerson.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      rbtnPerson_actionPerformed(e);
				  }

			      }
	);
	rbtnCompany.setBounds(new Rectangle(560, 85, 95, 20));
	rbtnCompany.setSelected(true);
	rbtnCompany.setHorizontalAlignment(SwingConstants.CENTER);
	rbtnCompany.setHorizontalTextPosition(SwingConstants.RIGHT);
	rbtnCompany.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       rbtnCompany_actionPerformed(e);
				   }

			       }
	);
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	btnClose.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnClose_actionPerformed(e);
				 }

			     }
	);
	tfOwner.setBounds(new Rectangle(220, 70, 230, 35));
	content.setBounds(new Rectangle(5, 0, 595, 175));
	content.setLayout(null);
	tfStartDate.setBounds(new Rectangle(560, 30, 105, 35));
	content.add(tfStartDate, null);
	content.add(cbMoneyType, null);
	content.add(cbAccountType, null);
	content.add(tfCBU, null);
	content.add(tfAmount, null);
	content.add(rbtnPerson, null);
	content.add(rbtnCompany, null);
	content.add(tfOwner, null);
	content.add(cbBank, null);
	content.add(tfNumber, null);
	content.add(tfFindAccount, null);
	content.add(cbAccount, null);
	this.add(content, null);
	this.addButton(btnClose);
	this.addButton(btnAccept);
	
	cbAccountType.autoSize();
	cbBank.autoSize();
	cbMoneyType.autoSize();
	cbOwner.autoSize();
	cbAccount.autoSize();
	cbOwner.setVisible(false);
	
	ButtonGroup btng = new ButtonGroup();
	btng.add(rbtnCompany);
	btng.add(rbtnPerson);
	
	cbMoneyType.setSelectedID("1");
	tfStartDate.setValue(Proced.setFormatDate(Environment.currentDate, true));
	tfFindAccount.getTextField().addKeyListener(new KeyAdapter() {
	      public void keyTyped(KeyEvent e) {
			  if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			      loadComboAccount();
			  }
		      }
	
		  }
	);
	//cbBank.setSelectedID("-1");
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("La cuenta contable debe ser imputable del tipo ACTIVO o RECURSOS (no efectivo)");
    }

    private void loadComboAccount(){
	String params = "-1,'" + tfFindAccount.getString() + "'";
	cbAccount.loadJCombo(LibSQL.exFunction("accounting.getAllAccountsForBankAccounts", params)); 
    }
    
    private void loadData(){
	tfAmount.setValue(bankAccount.getAmount());
	tfCBU.setValue(bankAccount.getCbu());
	tfNumber.setValue(bankAccount.getNumber());
	tfOwner.setValue(bankAccount.getOwnerName());
	cbAccountType.setSelectedID(String.valueOf(bankAccount.getIdAccountType()));
	cbBank.setSelectedID(String.valueOf(bankAccount.getIdBank()));
	cbMoneyType.setSelectedID(String.valueOf(bankAccount.getIdMoneyType()));
	cbAccount.getCombo().addItem(bankAccount.getAccount().getFullName(), ""+ bankAccount.getAccount().getIDAccount());
	if(bankAccount.getIDBankAccount() == 0){
	    tfStartDate.setValue(Proced.setFormatDate(Environment.currentDate,true)); 
	} else {
	    tfStartDate.setValue(Proced.setFormatDate(bankAccount.getStartDate(),true)); 
	}
	if (bankAccount.isIsCompany()){
	    rbtnCompany.setSelected(true);
	} else {
	    rbtnPerson.setSelected(true);
	}
	tfAmount.setEditable(bankAccount.isAmountModifiable());
    }
    
    private void loadSolicitorPeopleCombo() {
	JCombo combo = new JCombo();
	//combo.loadJCombo(LibSQL.exFunction("file.getAllEntitiesPersons", ""));
	cbOwner.setCombo(combo);
	cbOwner.updateUI();
	//loadComboListener();
    }

    private void loadSolicitorCompanyCombo() {
	JCombo combo = new JCombo();
	//combo.loadJCombo(LibSQL.exFunction("file.getAllEntitiesCompanies", ""));
	cbOwner.setCombo(combo);
	cbOwner.updateUI();
	//loadComboListener();
    }
    
    public boolean saveData(){
	if (tfCBU.getString().length() == 18 || tfCBU.getString().equals("0") || tfCBU.getString().length() == 0){
	    if (bankAccount == null){
	        bankAccount = new BankAccount();
	    }
	    bankAccount.setAmount(tfAmount.getAmount());
	    if (bankAccount.getIDBankAccount() == 0){
		bankAccount.setAvailableAmount(tfAmount.getAmount());    
	    }
	    
	    bankAccount.setCbu(Long.parseLong("0"+ tfCBU.getString()));
	    bankAccount.setEndDate("");
	    bankAccount.setIdAccountType(Integer.parseInt(cbAccountType.getSelectedValue().toString()));
	    bankAccount.setIdBank(Integer.parseInt(cbBank.getSelectedValue().toString()));
	    bankAccount.setIdMoneyType(Integer.parseInt(cbMoneyType.getSelectedValue().toString()));
	    bankAccount.setIdOwner(0);
	    bankAccount.setIsCompany(rbtnCompany.isSelected());
	    bankAccount.setNumber(tfNumber.getString());
	    bankAccount.setOwnerName(tfOwner.getString());
	    bankAccount.setStartDate(Proced.setFormatDate(tfStartDate.getString(),false));
	    bankAccount.setAccount(new Account(Integer.parseInt(""+ cbAccount.getSelectedValue())));
	    
	    int result = bankAccount.saveData();
	    if (result >= 0){
		bankAccount.updateBudgetsAmount();
		parentList.refresh();
		getParentInternalFrame().close();
		return true;
	    } else if (result == -1){
		//Advisor.messageBox("Este Nº de Cuenta ya existe","Nº de Cuenta no válido");
	        Advisor.messageBox("Error de actualizacion de datos","Error");
	        return false;
	    } else {
		Advisor.messageBox("Error de actualizacion de datos","Error");
	        return false;
	    }
	} else{
	    Advisor.messageBox("La cantidad de digitos del CBU debe ser 18","CBU Incorrecto");
	    return false;
	}       
    }
    
    private void rbtnPerson_actionPerformed(ActionEvent e) {
	loadSolicitorPeopleCombo();
    }

    private void rbtnCompany_actionPerformed(ActionEvent e) {
	loadSolicitorPeopleCombo();
    }

    private void btnAccept_actionPerformed(ActionEvent e) {
        if (control()) {
            if (cbAccountType.getSelectedIndex() != -1 && !tfNumber.getString().equals("0") && tfStartDate.getString().length()>0 && cbBank.getSelectedIndex() != -1 && tfOwner.getString().length()>0 && tfCBU.getString().length()>0 && cbAccount.getSelectedIndex() != -1) {
                saveData();
            } else {
                Advisor.messageBox("Hay campos incompletos", "Error");
                //CAPO!!!: Aplicale el control clasico que haces vos...
            }
        } else {
           errorMessage(); 
        }
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }
    
    public void setBankAccount(BankAccount _bankAccount) {
	this.bankAccount = _bankAccount;
	loadData();
    }

    public void setParentList(BankAccountsList parentList) {
	this.parentList = parentList;
    }

    private void loadAccount(){
	AccountsMain accountMain = new AccountsMain();
	account = new Account();
	accountMain.getAccountsList().setFilter(AccountTypes.ACTIVE);
	accountMain.setAccount(account);    
	accountMain.setParentPanel(this);

	ExtendedInternalFrame accountContainer = new ExtendedInternalFrame("Cuentas");
	accountContainer.setCentralPanel(accountMain);    
	accountContainer.show();
    }
    
    private void assignAccount(){
	tfFindAccount.setValue("");
	cbAccount.removeAllItems();
	cbAccount.getCombo().addItem(account.getName(), ""+ account.getIDAccount());
    }
    
    public void reload() {
    	assignAccount();
	account = null;
    }
    
    private void btnFindAccount_actionPerformed(ActionEvent e) {
	loadAccount();
    }

    private boolean control() { 
    System.out.println("cantidad de digitos: "+tfCBU.getString().length());
        boolean result = true;
        if (tfNumber.getValue().equals(""))  {
            error = 1;
            result = false;
        } else if (tfOwner.getValue().equals(""))  {
            error = 2;
            result = false;
        } else if (cbAccount.getSelectedIndex() == -1){
            error = 3;
            result = false;
        } else if (tfStartDate.getString().equals("")){ 
            error = 4;
            result = false;
        } else if (tfCBU.getString().equals("")){ 
            error = 5;
            result = false;
        }else if (tfCBU.getString().length() != 18){ 
            error = 6;
            result = false;
        }
        return result;
    }

    private void errorMessage() {
        switch (error)  {
            case 1: {
                    Advisor.messageBox("Debe completar el campo Nº " + tfStartDate.getDate(),"Aviso");
                } break;
            case 2: {
                    Advisor.messageBox("Debe completar el campo Titular de la Cuenta","Aviso");
                } break;
            case 3: {
                    Advisor.messageBox("Debe seleccionar una Cuenta","Aviso");
                } break;
            case 4: {
                    Advisor.messageBox("Debe completar el campo Fecha","Aviso");
                } break;
	    case 5: {
		    Advisor.messageBox("Debe completar el campo CBU","Aviso");
		} break;
	    case 6: {
		    Advisor.messageBox("El campo CBU debe tener 18 digitos","Aviso");
		} break;
        }
        
    }
}
