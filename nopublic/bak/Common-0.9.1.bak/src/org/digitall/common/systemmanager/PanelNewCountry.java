package org.digitall.common.systemmanager;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.digitall.lib.components.JEntry;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;

//

public class PanelNewCountry extends BasicContainerPanel {

    private BasicPanel PanelCountry = new BasicPanel();
    private BasicLabel lblCountry = new BasicLabel();
    private BasicLabel lblCode = new BasicLabel();
    private JEntry tfName = new JEntry();
    private JEntry tfCode = new JEntry();
    private AcceptButton bAccept = new AcceptButton();
    private CloseButton bClose = new CloseButton();
    private final int FRAME = 1;
    private final int INTERNALFRAME = 2;
    private final int DIALOG = 3;
    private int parentType = -1;
    private Component parent;
    private String insert = "";
    private int error = 0;

    public PanelNewCountry(BasicInternalFrame _parent) {
	try {
	    parent = _parent;
	    parentType = INTERNALFRAME;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public PanelNewCountry(BasicDialog _parent) {
	try {
	    parent = _parent;
	    parentType = DIALOG;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public PanelNewCountry(JFrame _parent) {
	try {
	    parent = _parent;
	    parentType = FRAME;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(286, 118));
	tfName.setBounds(new Rectangle(5, 15, 265, 20));
	tfName.setFont(new Font("Dialog", 1, 11));
	bAccept.setBounds(new Rectangle(240, 85, 40, 25));
	bAccept.setSize(new Dimension(40, 25));
	bAccept.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bAccept_actionPerformed(e);
		    }

		});
	bClose.setBounds(new Rectangle(5, 85, 40, 25));
	bClose.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bClose_actionPerformed(e);
		    }

		});
	PanelCountry.setBounds(new Rectangle(5, 5, 275, 75));
	PanelCountry.setLayout(null);
	PanelCountry.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	lblCountry.setText("Country");
	lblCountry.setBounds(new Rectangle(5, 5, 265, 10));
	lblCountry.setFont(new Font("Dialog", 1, 11));
	lblCountry.setHorizontalAlignment(SwingConstants.LEFT);
	tfCode.setBounds(new Rectangle(5, 50, 65, 20));
	tfCode.setFont(new Font("Dialog", 1, 11));
	lblCode.setText("Code");
	lblCode.setBounds(new Rectangle(5, 40, 265, 10));
	lblCode.setFont(new Font("Dialog", 1, 11));
	lblCode.setHorizontalAlignment(SwingConstants.LEFT);
	PanelCountry.add(lblCode, null);
	PanelCountry.add(tfCode, null);
	PanelCountry.add(lblCountry, null);
	PanelCountry.add(tfName, null);
	this.add(PanelCountry, null);
	this.add(bClose, null);
	this.add(bAccept, null);
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
	String cantidad = org.digitall.lib.sql.LibSQL.getCampo("Select count(*) From tabs.country_tabs where code = '" + tfCode.getText().toLowerCase().trim() + "' ");
	if (tfName.getText().equals("")) {
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
	    insert = "INSERT INTO tabs.country_tabs VALUES ( (select max(idcountry) +1 From tabs.country_tabs)" + " , '" + tfCode.getText().toLowerCase().trim() + "', '" + tfName.getText().toUpperCase().toLowerCase(Locale.FRENCH) + "', '')";
	    System.out.println("insert: " + insert);
	    /*if (LibSQL.exActualizar('a',insert)){
                org.digitall.lib.components.Advisor.messageBox("Insert Success!","Message");
            }*/
	} else
	    error();
    }

    private void error() {
	switch (error) {
	    case 1:
		org.digitall.lib.components.Advisor.messageBox("El campo Country estï¿½ vacio", "Error");
		break;
	    case 2:
		org.digitall.lib.components.Advisor.messageBox("El campo Code estï¿½ vacio", "Error");
		break;
	    case 3:
		org.digitall.lib.components.Advisor.messageBox("El codigo que ingresï¿½ ya existe", "Error");
		break;
	}
    }

}
