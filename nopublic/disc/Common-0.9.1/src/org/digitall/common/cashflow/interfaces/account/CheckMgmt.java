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
 * CheckMgmt.java
 *
 * */
package org.digitall.common.cashflow.interfaces.account;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import org.digitall.common.cashflow.classes.Check;
import org.digitall.common.cashflow.classes.EntityTypes;
import org.digitall.common.cashflow.interfaces.account.PaymentOrderCheckList;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class CheckMgmt extends BasicPrimitivePanel {

    private Check check;
    private TFInput tfLetter = new TFInput(DataTypes.STRING, "FileLetter", false);
    private TFInput tfDate = new TFInput(DataTypes.DATE, "Date", false);
    private TFInput tfExpiredDate = new TFInput(DataTypes.DATE, "ExpirationDate", false);
    private TFInput tfCheckAmount = new TFInput(DataTypes.MONEY, "CheckAmount", false);
    private TFInput tfNumber = new TFInput(DataTypes.INTEGER, "Number", true);
    private TFInput tfFindEntity = new TFInput(DataTypes.STRING, "FindEntity", false);
    private TFInput tfDescription = new TFInput(DataTypes.STRING, "Description", false);
    private CBInput cbEntityType = new CBInput(CachedCombo.ENTITYTYPE, "EntityType");
    private CBInput cbEntity = new CBInput(0, "Entity", false);
    private CBInput cbCheckStatus = new CBInput(CachedCombo.CHECKSTATUS, "CheckStatus");
    private CBInput cbCheckType = new CBInput(CachedCombo.CHECKTYPE, "CheckType");
    private FindButton btnFindEntity = new FindButton();
    private String entityName;
    private SaveDataButton btnAccept = new SaveDataButton();
    private BasicPanel panel = new BasicPanel("Cheque");
    private ChecksList parentList;
    private PaymentOrderCheckList paymentOrderCheckList;
    private CloseButton btnClose = new CloseButton();
    private double availableAmount = 0;

    public CheckMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(570, 235));
	this.setPreferredSize(new Dimension(570, 235));
	tfLetter.setBounds(new Rectangle(20, 30, 65, 35));
	tfNumber.setBounds(new Rectangle(110, 30, 125, 35));
	tfDate.setBounds(new Rectangle(20, 70, 90, 35));
	tfExpiredDate.setBounds(new Rectangle(130, 70, 90, 35));
	tfCheckAmount.setBounds(new Rectangle(245, 30, 125, 35));
	tfFindEntity.setBounds(new Rectangle(145, 110, 110, 35));
	tfDescription.setBounds(new Rectangle(20, 150, 520, 35));
	cbEntityType.setBounds(new Rectangle(20, 110, 110, 35));
	cbEntity.setBounds(new Rectangle(265, 110, 225, 35));
	cbCheckStatus.setBounds(new Rectangle(245, 70, 135, 35));
	cbCheckType.setBounds(new Rectangle(400, 70, 140, 35));
	btnFindEntity.setBounds(new Rectangle(500, 120, 40, 25));
	btnAccept.setBounds(new Rectangle(470, 240, 40, 25));
	btnAccept.setSize(new Dimension(40, 25));
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	panel.setBounds(new Rectangle(5, 0, 560, 225));
	panel.setLayout(null);
	btnClose.setBounds(new Rectangle(510, 240, 40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	panel.add(tfLetter, null);
	panel.add(btnFindEntity, null);
	panel.add(cbCheckType, null);
	panel.add(cbCheckStatus, null);
	panel.add(cbEntity, null);
	panel.add(cbEntityType, null);
	panel.add(tfDescription, null);
	panel.add(tfFindEntity, null);
	panel.add(tfCheckAmount, null);
	panel.add(tfExpiredDate, null);
	panel.add(tfDate, null);
	panel.add(tfNumber, null);
	this.add(panel, null);
	this.addButton(btnClose);
	this.addButton(btnAccept);
	cbEntityType.autoSize();
	cbEntity.autoSize();
	cbCheckStatus.autoSize();
	cbCheckType.autoSize();
	setEnabledEntity(false);
	tfDate.setValue(Proced.setFormatDate(Environment.currentDate, true));
	tfFindEntity.getTextField().addKeyListener(new KeyAdapter() {

						public void keyTyped(KeyEvent e) {
						    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
							loadComboEntity();
						    }
						}

					    }
	);
	cbEntityType.getCombo().addItemListener(new ItemListener() {

					     public void itemStateChanged(ItemEvent evt) {
						 if (evt.getStateChange() == ItemEvent.SELECTED) {
						     if (cbEntityType.getSelectedValue().equals("-1")) {
							 setEnabledEntity(false);
							 cbEntity.getCombo().removeAllItems();
						     } else {
							 setEnabledEntity(true);
							 cbEntity.getCombo().removeAllItems();
							 if (check != null) {
							     if (cbEntityType.getSelectedValue().equals("" + check.getIdEntitytype())) {
								 cbEntity.getCombo().addItem(entityName, "" + check.getIdEntity());
							     }
							 }
						     }
						 }
					     }

					 }
	);
	btnFindEntity.setEnabled(false);
	tfLetter.setEnabled(false);
	tfNumber.setEnabled(false);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Modifique los campos que desee y presione el botón \"Grabar\"");
    }

    private void setEnabledEntity(boolean _enabled) {
	tfFindEntity.setEnabled(_enabled);
	cbEntity.setEnabled(_enabled);
	btnFindEntity.setEnabled(_enabled);
    }

    private void loadComboEntity() {
	String param = "'" + tfFindEntity.getString() + "'";
	switch (Integer.parseInt("" + cbEntityType.getSelectedValue())) {
	    case EntityTypes.PERSON :
		cbEntity.loadJCombo(LibSQL.exFunction("org.getAllPersonsByFilter", param));
		break;
	    case EntityTypes.PROVIDER :
		cbEntity.loadJCombo(LibSQL.exFunction("org.getAllProvidersByFilter", param));
		break;
	    default :
		break;
	}
    }

    private void loadData() {
	if (check.getIdCheck() != -1) {
	    tfCheckAmount.setValue(check.getAmount());
	    tfDate.setValue(Proced.setFormatDate(check.getDate(), true));
	    tfDescription.setValue(check.getDescription());
	    tfExpiredDate.setValue(Proced.setFormatDate(check.getExpiredDate(), true));
	    tfLetter.setValue(check.getLetter());
	    tfNumber.setValue("" + check.getNumber());
	    cbCheckStatus.setSelectedID("" + check.getIdCheckStatus());
	    cbCheckType.setSelectedID("" + check.getIdCheckType());
	    cbEntityType.setSelectedID("" + check.getIdEntitytype());
	    cbEntity.getCombo().addItem(entityName, "" + check.getIdEntity());
	}
	tfCheckAmount.setEnabled(check.getIdCheckStatus() == Check.NOT_ISSUED || check.getAmount()==0);
	tfDate.setEnabled(check.getIdCheckStatus() == Check.NOT_ISSUED || check.getAmount()==0);
	tfExpiredDate.setEnabled(check.getIdCheckStatus() == Check.NOT_ISSUED || check.getAmount()==0);
	btnAccept.setEnabled(check.getIdCheckStatus() == Check.NOT_ISSUED || check.getAmount()==0);
	btnAccept.setVisible(check.getIdCheckStatus() == Check.NOT_ISSUED || check.getAmount()==0);
	btnFindEntity.setEnabled(check.getIdCheckStatus() == Check.NOT_ISSUED || check.getAmount()==0);
	btnFindEntity.setVisible(check.getIdCheckStatus() == Check.NOT_ISSUED || check.getAmount()==0);
	tfFindEntity.setVisible(check.getIdCheckStatus() == Check.NOT_ISSUED || check.getAmount()==0);
	cbEntityType.setEnabled(check.getIdCheckStatus() == Check.NOT_ISSUED || check.getAmount()==0);
	cbEntity.setEnabled(check.getIdCheckStatus() == Check.NOT_ISSUED || check.getAmount()==0);
	cbCheckStatus.setEnabled(check.getIdCheckStatus() == Check.NOT_ISSUED || check.getAmount()==0);
	cbCheckType.setEnabled(check.getIdCheckStatus() == Check.NOT_ISSUED || check.getAmount()==0);
	tfDescription.setEnabled(check.getIdCheckStatus() == Check.NOT_ISSUED || check.getAmount()==0);
    }

    public void setCheck(Check check) {
	this.check = check;
	loadData();
    }

    public void setEntityName(String entityName) {
	this.entityName = entityName;
    }

    public boolean saveData() {
	if ((tfCheckAmount.getAmount() <= availableAmount) || (availableAmount == 0)) {
	    int checksQty = 1;
	    int result = -1;
	    for (int i = 1; i <= checksQty; i++) {
		check.setAmount(tfCheckAmount.getAmount());
		check.setDate(Proced.setFormatDate(tfDate.getString(), false));
		check.setDescription(tfDescription.getString());
		check.setExpiredDate(Proced.setFormatDate(tfExpiredDate.getString(), false));
		check.setIdCheckStatus(Integer.parseInt("0" + cbCheckStatus.getSelectedValue()));
		check.setIdCheckType(Integer.parseInt("0" + cbCheckType.getSelectedValue()));
		check.setIdEntity(Integer.parseInt("0" + cbEntity.getSelectedValue()));
		check.setIdEntitytype(Integer.parseInt("0" + cbEntityType.getSelectedValue()));
		check.setLetter(tfLetter.getString());
		check.setNumber(Integer.parseInt("0" + tfNumber.getString()) + i - 1);
		if (check.getIdCheckStatus() != Check.NOT_ISSUED) {
		    if (Advisor.question("Emisión de cheque", "Una vez emitido el cheque, éste no se podrá modificar\n¿Está seguro?")) {
			if (check.getIdCheck() == -1) {
			    result = check.addData();
			} else {
			    result = check.setData();
			}
		    }
		} else {
		    Advisor.messageBox("No se puede guardar un cheque \""+cbCheckStatus.getSelectedItem()+"\"", "");
		}
		if (result < 0) {
		    break;
		} else {
		    if (checksQty != 1) {
			check = new Check(check.getAccount());
		    }
		}
	    }
	    if (result >= 0) {
		if (parentList != null) {
		    parentList.refresh();
		} else {
		    paymentOrderCheckList.updateAmountChecks(check.getAmount());
		    paymentOrderCheckList.refresh();
		}
		getParentInternalFrame().close();
		return true;
	    } else {
		return false;
	    }
	} else {
	    Advisor.messageBox("El importe del cheque debe ser menor o igual a " + Format.money(availableAmount), "Importe no válido");
	    return false;
	}
    }

    private void btnAccept_actionPerformed(ActionEvent e) {
	saveData();
    }

    public void setParentList(ChecksList parentList) {
	this.parentList = parentList;
    }

    public void setPaymentOrderCheckList(PaymentOrderCheckList _paymentOrderCheckList) {
	this.paymentOrderCheckList = _paymentOrderCheckList;
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    public void setAvailableAmount(double availableAmount) {
	this.availableAmount = availableAmount;
    }

}
