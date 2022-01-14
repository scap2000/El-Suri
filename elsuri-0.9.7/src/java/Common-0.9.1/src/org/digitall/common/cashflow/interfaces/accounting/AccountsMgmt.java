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
 * AccountsMgmt.java
 *
 * */
package org.digitall.common.cashflow.interfaces.accounting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;

import org.digitall.common.cashflow.classes.Account;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TAInputPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;


public class AccountsMgmt extends BasicPrimitivePanel {

    private BasicPanel PanelData = new BasicPanel();
    private TFInput tfCode = new TFInput(DataTypes.INTEGER, "Code", false);
    private TAInputPanel tfDescription = new TAInputPanel(DataTypes.STRING, "Description", false);
    private TFInput tfName = new TFInput(DataTypes.STRING, "Name", false);
    private TFInput tfInitialAmountColor = new TFInput(DataTypes.COLOR, "InitialAmountColor", false);
    private TFInput tfAssignedAmountToACColor = new TFInput(DataTypes.COLOR, "AccountingAmountColor", false);
    private TFInput tfAssignedAmountToCCColor = new TFInput(DataTypes.COLOR, "CostCentreAmountColor", false);
    private BasicPanel colorPanel = new BasicPanel();
    private SaveDataButton btnSave = new SaveDataButton();
    private CloseButton btnCancel = new CloseButton();
    private AccountsMain parentMain;
    private CBInput cbParentAccount = new CBInput(0, "ParentAccountingName", false);
    private CBInput cbAccountType = new CBInput(0, "AccountType");
    private BasicRadioButton rbtnResult = new BasicRadioButton();
    private BasicRadioButton rbtnHeritage = new BasicRadioButton();
    private BasicCheckBox chkImputable = new BasicCheckBox();
    private Account account;
    private Account parentAccount;
    private BasicCheckBox chkValues = new BasicCheckBox();
    private BasicCheckBox chkDeduction = new BasicCheckBox();
    private BasicCheckBox chkCash = new BasicCheckBox();
    private BasicCheckBox chkSubAccount = new BasicCheckBox();
    private boolean modifying = false;

    public AccountsMgmt(AccountsMain _parentMain) {
	try {
	    parentMain = _parentMain;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(549, 502));
	this.setPreferredSize(new Dimension(550, 510));
	PanelData.setBounds(new Rectangle(5, 0, 490, 295));
	PanelData.setLayout(null);
	colorPanel.setBorder(BorderPanel.getBorderPanel("Color", Color.BLACK, Color.BLACK));
	tfName.setBounds(new Rectangle(130, 10, 395, 35));
	tfInitialAmountColor.setBounds(new Rectangle(10, 25, 110, 35));
	tfAssignedAmountToACColor.setBounds(new Rectangle(148, 25, 175, 35));
	tfAssignedAmountToCCColor.setBounds(new Rectangle(350, 25, 140, 35));
	colorPanel.setBounds(new Rectangle(20, 390, 505, 70));
	colorPanel.setLayout(null);
	btnCancel.setBounds(new Rectangle(490, 435, 30, 25));
	btnSave.setBounds(new Rectangle(420, 520, 40, 25));
	tfCode.setBounds(new Rectangle(20, 10, 90, 35));
	tfDescription.setBounds(new Rectangle(20, 190, 505, 180));
	btnSave.setBounds(new Rectangle(448, 435, 30, 25));
	btnSave.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnSave_actionPerformed(e);
			       }

			   }
	);
	btnCancel.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnCancel_actionPerformed(e);
				 }

			     }
	);
	cbParentAccount.setBounds(new Rectangle(20, 55, 505, 35));
	cbAccountType.setBounds(new Rectangle(365, 100, 160, 35));
	rbtnHeritage.setText(Environment.lang.getProperty("Heritage"));
	rbtnHeritage.setSelected(true);
	rbtnHeritage.setBounds(new Rectangle(150, 115, 95, 18));
	rbtnHeritage.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					rbtnHeritage_actionPerformed(e);
				    }

				}
	);
	rbtnResult.setText(Environment.lang.getProperty("Result"));
	rbtnResult.setBounds(new Rectangle(260, 115, 95, 18));
	rbtnResult.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      rbtnResult_actionPerformed(e);
				  }

			      }
	);
	chkImputable.setText(Environment.lang.getProperty("Imputable"));
	chkImputable.setBounds(new Rectangle(20, 115, 90, 18));
	chkValues.setText(Environment.lang.getProperty("Values"));
	chkValues.setBounds(new Rectangle(260, 150, 77, 18));
	chkValues.setSize(new Dimension(77, 18));
	chkDeduction.setText(Environment.lang.getProperty("Deduction"));
	chkDeduction.setBounds(new Rectangle(20, 150, 81, 18));
	chkDeduction.setSize(new Dimension(90, 18));
	chkCash.setText(Environment.lang.getProperty("Cash"));
	chkCash.setBounds(new Rectangle(150, 150, 79, 18));
	chkCash.setSize(new Dimension(79, 18));
        chkSubAccount.setText(Environment.lang.getProperty("SubAccount"));
        chkSubAccount.setBounds(new Rectangle(365, 150, 95, 18));
        chkSubAccount.setSize(new Dimension(95, 18));
	cbAccountType.autoSize();
	colorPanel.add(tfInitialAmountColor, null);
	colorPanel.add(tfAssignedAmountToCCColor, null);
	colorPanel.add(tfAssignedAmountToACColor, null);
        PanelData.add(chkCash, null);
        PanelData.add(chkSubAccount, null);
        PanelData.add(chkDeduction, null);
        PanelData.add(chkValues, null);
        PanelData.add(chkImputable, null);
	PanelData.add(rbtnHeritage, null);
	PanelData.add(rbtnResult, null);
	PanelData.add(cbAccountType, null);
	PanelData.add(cbParentAccount, null);
	PanelData.add(colorPanel, null);
	PanelData.add(tfDescription, null);
	PanelData.add(tfCode, null);
	PanelData.add(tfName, null);
        cbAccountType.loadJCombo("SELECT idaccounttype AS id, name, CASE isheritage WHEN true THEN 1 ELSE 0 END AS ref FROM  accounting.accounttypes WHERE estado<>'*' AND idaccounttype > 0");
	cbAccountType.addItemListener(new ItemListener() {

				   public void itemStateChanged(ItemEvent itemEvent) {
				       if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
					    loadParentAccountsCombo();
				       }
				   }

			       }
	);
	cbParentAccount.autoSize();
	this.add(PanelData, null);
	addButton(btnCancel);
	addButton(btnSave);
	ButtonGroup rbtn = new ButtonGroup();
	rbtn.add(rbtnHeritage);
	rbtn.add(rbtnResult);
	btnCancel.setToolTipText("Cancelar");
	btnSave.setToolTipText("Grabar datos");
    }

    private void loadData() {
	if (account != null) {
	    chkImputable.setEnabled(true);
	    tfCode.setValue("" + account.getCode());
	    tfName.setValue(account.getName());
	    chkImputable.setSelected(account.isIsImputable());
	    tfDescription.setText(account.getDescription());
	    tfInitialAmountColor.setValue(account.getInitialAmountcolor());
	    tfInitialAmountColor.setBackgroundColor(Format.hex2Color(account.getInitialAmountcolor()));
	    tfAssignedAmountToACColor.setValue(account.getAssignedAmountToACColor());
	    tfAssignedAmountToACColor.setBackgroundColor(Format.hex2Color(account.getAssignedAmountToACColor()));
	    tfAssignedAmountToCCColor.setValue(account.getAssignedAmountToCCColor());
	    tfAssignedAmountToCCColor.setBackgroundColor(Format.hex2Color(account.getAssignedAmountToCCColor()));
	    chkDeduction.setSelected(account.isIsDeduction());
	    chkCash.setSelected(account.isIsCash());
	    chkValues.setSelected(account.isIsValues());
            chkSubAccount.setSelected(account.isSubAccount());
	    account.retrieveChildrenNumber();
	    if ( account.getChildrenNumber() > 0)  {
	        chkSubAccount.setEnabled(false);
	    } else  {
	        chkSubAccount.setEnabled(true);
	    }
	    loadParentAccountsCombo();
	}
    }

    public void clearData() {
	account = new Account();
	tfCode.setValue("");
	tfName.setValue("");
	loadParentAccountsCombo();
	tfDescription.setText("");
	chkImputable.setSelected(false);
	chkCash.setSelected(false);
	chkDeduction.setSelected(false);
	chkValues.setSelected(false);
    }

    public boolean saveData() {
	if (tfCode.getString().length() > 0 && tfName.getString().length() > 0) {
	    if (!tfCode.getString().equals("-1") && !tfCode.getString().equals("")) {
		if (account == null) {
		    account = new Account();
		}
		account.setIdParent(Integer.parseInt(cbParentAccount.getSelectedValue().toString()));
		account.setCode(Integer.parseInt(tfCode.getString()));
		account.setName(tfName.getString());
		account.setDescription(tfDescription.getText());
		account.setInitialAmountcolor(tfInitialAmountColor.getString().replaceAll("#", ""));
		account.setAssignedAmountToACColor(tfAssignedAmountToACColor.getString().replaceAll("#", ""));
		account.setAssignedAmountToCCColor(tfAssignedAmountToCCColor.getString().replaceAll("#", ""));
		account.setIdAccountType(Integer.parseInt("" + cbAccountType.getSelectedValue()));
		account.setIsImputable(chkImputable.isSelected());
		account.setIsDeduction(chkDeduction.isSelected());
		account.setIsCash(chkCash.isSelected());
		account.setIsValues(chkValues.isSelected());
	        account.setSubAccount(chkSubAccount.isSelected());
		int result = account.saveData();
		if (result >= 0) {
		    result = 0;
		    //if (Advisor.question("Configuracion de Colores","Desea configurar colores de las sub-cuentas automaticamente?")){
		    result = account.saveColor();
		    //}
		}
		if (result >= 0) {
		    setModifying(false);
		    parentMain.setSelectedTab(0);
		    parentMain.getAccountsList().refresh();
		    clearData();
		    return true;
		} else {
		    return false;
		}
	    } else {
		Advisor.messageBox("Debe ingresar un Código de Cuenta válido", "Aviso");
		return false;
	    }
	} else {
	    Advisor.messageBox("Debe completar todos los campos", "Aviso");
	    return false;
	}
    }

    private void btnSave_actionPerformed(ActionEvent e) {
	saveData();
    }

    private void btnCancel_actionPerformed(ActionEvent e) {
	clearData();
	parentMain.setTitleAt(1, "Nueva Cuenta");
	parentMain.setSelectedTab(0);
    }

    public void setAccount(Account _account) {
	this.account = _account;
	loadData();
    }

    private void rbtnHeritage_actionPerformed(ActionEvent e) {
        cbAccountType.setFilter("1");
        loadParentAccountsCombo();
    }

    private void rbtnResult_actionPerformed(ActionEvent e) {
        cbAccountType.setFilter("0");
        loadParentAccountsCombo();
    }

    public void setParentAccount(Account _parentAccount) {
	chkImputable.setEnabled(true);
	this.parentAccount = _parentAccount;
        
	boolean isHeritage = false;
        int idAccountType = -1;
        isHeritage = _parentAccount.isIsHeritage();
        idAccountType = _parentAccount.getIdAccountType();
        tfCode.setValue(_parentAccount.getNewCode() == 0 ? -1 : _parentAccount.getNewCode());
	rbtnHeritage.setSelected(isHeritage);
	rbtnResult.setSelected(!isHeritage);
	if (isHeritage) {
	    cbAccountType.setFilter("1");
	} else {
	    cbAccountType.setFilter("0");
	}
	cbAccountType.setSelectedID(idAccountType);
    }

    private void loadParentAccountsCombo() {
	cbParentAccount.loadJCombo("accounting.getallaccountsbytype", cbAccountType.getSelectedValue());
	if (parentAccount != null) {
	    cbParentAccount.setSelectedID(parentAccount.getIDAccount());
	}
    }
    
    public void setModifying(boolean _modifying) {
	modifying = _modifying;
    }
    
    public boolean isModifying() {
	return modifying;
    }

}
