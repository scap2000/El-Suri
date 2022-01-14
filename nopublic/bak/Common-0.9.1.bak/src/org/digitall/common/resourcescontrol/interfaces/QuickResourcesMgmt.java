package org.digitall.common.resourcescontrol.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.common.resourcescontrol.classes.Skills;
import org.digitall.common.resourcescontrol.classes.Units;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;

public class QuickResourcesMgmt extends BasicPrimitivePanel {

    private TFInput tfResource = new TFInput(DataTypes.STRING, "Nombre del Recurso", false);
    private TFInput tfFindAccount = new TFInput(DataTypes.STRING, "FindAccounting", false);
    private CBInput cbAccounting = new CBInput(0, "Accounting", false);
    private BasicPanel content = new BasicPanel();
    private SaveDataButton btnSave = new SaveDataButton();
    private CloseButton btnClose = new CloseButton();

    public QuickResourcesMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.add(content, BorderLayout.CENTER);
	this.setSize(new Dimension(582, 133));
	tfResource.setBounds(new Rectangle(5, 5, 570, 35));
	tfFindAccount.setBounds(new Rectangle(5, 50, 110, 35));
	cbAccounting.setBounds(new Rectangle(125, 50, 450, 35));
	content.add(tfResource);
	content.add(tfFindAccount);
	content.add(cbAccounting);
	content.setLayout(null);
	btnSave.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnSave_actionPerformed(e);
		    }

		});
	btnClose.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnClose_actionPerformed(e);
		    }

		});
	cbAccounting.autoSize();
	addButton(btnClose);
	addButton(btnSave);
	tfFindAccount.getTextField().addKeyListener(new KeyAdapter() {

		    public void keyReleased(KeyEvent e) {
			if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			    loadComboAccounting();
			}
		    }

		});
    }

    private void loadComboAccounting() {
	String param = "5000,'" + tfFindAccount.getString() + "'";
	cbAccounting.loadJCombo(LibSQL.exFunction("accounting.getAllAccountsByFilter", param));
    }

    private void btnSave_actionPerformed(ActionEvent e) {
	if (valid()) {
	    try {
		Resource resource = new Resource();
		resource.setName(tfResource.getString());
		resource.setUnit(new Units(0, ""));
		resource.setSkillToProvider(new Skills(0));
		resource.setExpenditureAccount(new ExpenditureAccount(Integer.parseInt(cbAccounting.getSelectedValue().toString())));
		if ((resource.saveData() > 0)) {
		    tfResource.setValue("");
		} else {
		    Advisor.messageBox("Error al intentar grabar el recurso", "Error");
		}
	    } catch (Exception x) {
		Advisor.messageBox("Error al intentar grabar el recurso", "Error");
	    }
	} else {
	    Advisor.messageBox("Debe escribir un nombre y seleccionar una cuenta", "Error");
	}
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    private boolean valid() {
	boolean returns = false;
	try {
	    returns = (cbAccounting.getSelectedIndex() != -1 && tfResource.getString().length() > 0);
	} catch (Exception x) {
	    //ignore
	}
	return returns;
    }

}
