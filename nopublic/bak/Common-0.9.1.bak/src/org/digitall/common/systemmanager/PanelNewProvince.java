package org.digitall.common.systemmanager;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.digitall.lib.components.JEntry;
import org.digitall.lib.components.JOutry;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.sql.LibSQL;

//

public class PanelNewProvince extends BasicContainerPanel {

    private BasicPanel PanelCountry = new BasicPanel();
    private BasicLabel lblCountry = new BasicLabel();
    private BasicLabel lblCode = new BasicLabel();
    private BasicLabel lblCountry1 = new BasicLabel();
    private JEntry tfProvince = new JEntry();
    private JEntry tfCode = new JEntry();
    private JOutry tfCountry = new JOutry();
    private AcceptButton bAccept = new AcceptButton();
    private CloseButton bClose = new CloseButton();
    private final int FRAME = 1;
    private final int INTERNALFRAME = 2;
    private final int DIALOG = 3;
    private int parentType = -1;
    private Component parent;
    private String insert, idcountry = "";
    private int error = 0;

    public PanelNewProvince(BasicInternalFrame _parent, String _idcountry) {
	try {
	    parent = _parent;
	    parentType = INTERNALFRAME;
	    idcountry = _idcountry;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public PanelNewProvince(BasicDialog _parent, String _idcountry) {
	try {
	    parent = _parent;
	    parentType = DIALOG;
	    idcountry = _idcountry;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public PanelNewProvince(JFrame _parent, String _idcountry) {
	try {
	    parent = _parent;
	    parentType = FRAME;
	    idcountry = _idcountry;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(286, 163));
	tfProvince.setBounds(new Rectangle(5, 50, 265, 20));
	tfProvince.setFont(new Font("Dialog", 1, 11));
	bAccept.setBounds(new Rectangle(240, 130, 40, 25));
	bAccept.setSize(new Dimension(40, 25));
	bAccept.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bAccept_actionPerformed(e);
		    }

		});
	bClose.setBounds(new Rectangle(5, 130, 40, 25));
	bClose.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bClose_actionPerformed(e);
		    }

		});
	lblCountry1.setText("Country:");
	lblCountry1.setBounds(new Rectangle(5, 5, 265, 10));
	lblCountry1.setFont(new Font("Dialog", 1, 11));
	lblCountry1.setHorizontalAlignment(SwingConstants.LEFT);
	tfCountry.setBounds(new Rectangle(5, 15, 265, 20));
	PanelCountry.setBounds(new Rectangle(6, 10, 275, 115));
	PanelCountry.setLayout(null);
	PanelCountry.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	lblCountry.setText("Province/State:");
	lblCountry.setBounds(new Rectangle(5, 40, 265, 10));
	lblCountry.setFont(new Font("Dialog", 1, 11));
	lblCountry.setHorizontalAlignment(SwingConstants.LEFT);
	tfCode.setBounds(new Rectangle(5, 85, 65, 20));
	tfCode.setFont(new Font("Dialog", 1, 11));
	lblCode.setText("Code");
	lblCode.setBounds(new Rectangle(5, 75, 65, 10));
	lblCode.setFont(new Font("Dialog", 1, 11));
	lblCode.setHorizontalAlignment(SwingConstants.LEFT);
	PanelCountry.add(tfCountry, null);
	PanelCountry.add(lblCode, null);
	PanelCountry.add(tfCode, null);
	PanelCountry.add(lblCountry, null);
	PanelCountry.add(tfProvince, null);
	PanelCountry.add(lblCountry1, null);
	this.add(PanelCountry, null);
	this.add(bClose, null);
	this.add(bAccept, null);
	seteaCountry();
    }

    private void seteaCountry() {
	String pais = org.digitall.lib.sql.LibSQL.getCampo("Select name From tabs.country_tabs Where idcountry = " + idcountry);
	tfCountry.setText(pais);
    }

    private void dispose() {
	switch (parentType) {
	    case DIALOG:
		((BasicDialog)parent).dispose();
		break;
	    case INTERNALFRAME:
		((BasicInternalFrame)parent).dispose();
		break;
	    case FRAME:
		((JFrame)parent).dispose();
		break;
	}
    }

    private void bClose_actionPerformed(ActionEvent e) {
	dispose();
    }

    private boolean control() {
	String cantidad = org.digitall.lib.sql.LibSQL.getCampo("Select count(*) From tabs.province_tabs where code = '" + tfCode.getText().trim() + "' ");
	if (tfProvince.getText().equals("")) {
	    error = 1;
	    return false;
	} else if (tfCode.getText().equals("")) {
	    error = 2;
	    return false;
	} else if (!cantidad.equals("0")) {
	    error = 3;
	    return false;
	} else
	    return true;
    }

    private void bAccept_actionPerformed(ActionEvent e) {
	if (control()) {
	    insert = "INSERT INTO tabs.province_tabs VALUES ( (select max(idprovince) +1 From tabs.province_tabs)" + " , " + idcountry + " ,'" + tfCode.getText().toLowerCase().trim() + "', '" + tfProvince.getText().toUpperCase().toLowerCase() + "', '')";
	    System.out.println("insert: " + insert);
	    if (LibSQL.exActualizar('a', insert)) {
		org.digitall.lib.components.Advisor.messageBox("Insert Success!", "Message");
	    }
	} else
	    error();
    }

    private void error() {
	switch (error) {
	    case 1:
		org.digitall.lib.components.Advisor.messageBox("Field Province is empty", "Error");
		break;
	    case 2:
		org.digitall.lib.components.Advisor.messageBox("Field Code is empty", "Error");
		break;
	    case 3:
		org.digitall.lib.components.Advisor.messageBox("Code existing", "Error");
		break;
	}
    }

}
