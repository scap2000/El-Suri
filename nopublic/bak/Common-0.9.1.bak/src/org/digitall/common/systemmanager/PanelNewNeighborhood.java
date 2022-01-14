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

public class PanelNewNeighborhood extends BasicContainerPanel {

    private BasicPanel PanelCountry = new BasicPanel();
    private BasicLabel lblNeighborhood = new BasicLabel();
    private JEntry tfNeighborhood = new JEntry();
    private JOutry tfLocation = new JOutry();
    private AcceptButton bAccept = new AcceptButton();
    private CloseButton bClose = new CloseButton();
    private final int FRAME = 1;
    private final int INTERNALFRAME = 2;
    private final int DIALOG = 3;
    private int parentType = -1;
    private Component parent;
    private String insert, idlocation = "";
    private int error = 0;
    private BasicLabel lblLocation = new BasicLabel();

    public PanelNewNeighborhood(BasicInternalFrame _parent, String _idlocation) {
	try {
	    parent = _parent;
	    parentType = INTERNALFRAME;
	    idlocation = _idlocation;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public PanelNewNeighborhood(BasicDialog _parent, String _idlocation) {
	try {
	    parent = _parent;
	    parentType = DIALOG;
	    idlocation = _idlocation;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public PanelNewNeighborhood(JFrame _parent, String _idlocation) {
	try {
	    parent = _parent;
	    parentType = FRAME;
	    idlocation = _idlocation;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(286, 124));
	tfNeighborhood.setBounds(new Rectangle(5, 50, 265, 20));
	tfNeighborhood.setFont(new Font("Dialog", 1, 11));
	bAccept.setBounds(new Rectangle(240, 90, 40, 25));
	bAccept.setSize(new Dimension(40, 25));
	bAccept.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bAccept_actionPerformed(e);
		    }

		});
	bClose.setBounds(new Rectangle(5, 90, 40, 25));
	bClose.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bClose_actionPerformed(e);
		    }

		});
	lblLocation.setText("Location:");
	lblLocation.setBounds(new Rectangle(5, 5, 265, 10));
	lblLocation.setFont(new Font("Dialog", 1, 11));
	lblLocation.setHorizontalAlignment(SwingConstants.LEFT);
	lblLocation.setText("Location:");
	lblLocation.setBounds(new Rectangle(5, 5, 265, 10));
	lblLocation.setFont(new Font("Dialog", 1, 11));
	lblLocation.setHorizontalAlignment(SwingConstants.LEFT);
	lblNeighborhood.setText("Location:");
	lblNeighborhood.setBounds(new Rectangle(5, 5, 265, 10));
	lblNeighborhood.setFont(new Font("Dialog", 1, 11));
	lblNeighborhood.setHorizontalAlignment(SwingConstants.LEFT);
	tfLocation.setBounds(new Rectangle(5, 15, 265, 20));
	PanelCountry.setBounds(new Rectangle(5, 5, 275, 80));
	PanelCountry.setLayout(null);
	PanelCountry.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	lblNeighborhood.setText("Neighborhood:");
	lblNeighborhood.setBounds(new Rectangle(5, 40, 265, 10));
	lblNeighborhood.setFont(new Font("Dialog", 1, 11));
	lblNeighborhood.setHorizontalAlignment(SwingConstants.LEFT);
	PanelCountry.add(lblLocation, null);
	PanelCountry.add(tfLocation, null);
	PanelCountry.add(lblNeighborhood, null);
	PanelCountry.add(tfNeighborhood, null);
	PanelCountry.add(lblNeighborhood, null);
	this.add(PanelCountry, null);
	this.add(bClose, null);
	this.add(bAccept, null);
	seteaDatos();
    }

    private void seteaDatos() {
	String localidad = org.digitall.lib.sql.LibSQL.getCampo("Select name From tabs.location_tabs Where idlocation = " + idlocation);
	tfLocation.setText(localidad);
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
	if (tfNeighborhood.getText().equals("")) {
	    error = 1;
	    return false;
	} else
	    return true;
    }

    private void bAccept_actionPerformed(ActionEvent e) {
	if (control()) {
	    insert = "INSERT INTO tabs.neighborhood_tabs VALUES ( (select max(idneighborhood) +1 From tabs.neighborhood_tabs)" + " , " + idlocation + " ,'" + tfNeighborhood.getText().toUpperCase().toLowerCase() + "' , '')";
	    //System.out.println("insert: "+ insert);
	    if (LibSQL.exActualizar('a', insert)) {
		org.digitall.lib.components.Advisor.messageBox("Insert Success!", "Message");
	    }
	} else
	    error();
    }

    private void error() {
	switch (error) {
	    case 1:
		org.digitall.lib.components.Advisor.messageBox("Field Neighborhood is empty", "Error");
		break;
	}
    }

}
