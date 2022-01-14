package org.digitall.apps.systemmanager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import org.digitall.lib.components.JEntry;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.BasicTextField;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.sql.LibSQL;

//

public class NewSystem extends BasicDialog {

    private BasicPanel jPanel1 = new BasicPanel();
    private BasicLabel lblSystem = new BasicLabel();
    private BasicLabel lblDescription = new BasicLabel();
    private AcceptButton bAccept = new AcceptButton();
    private CloseButton bCancel = new CloseButton();
    private BasicTextField tfCr = new BasicTextField();
    private JEntry tfSystem = new JEntry();
    private BasicScrollPane spDescription = new BasicScrollPane();
    private JTextArea taDescription = new JTextArea();

    public NewSystem() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(347, 175));
	this.getContentPane().setLayout(null);
	this.setTitle("New System");
	this.setFont(new Font("Dialog", 1, 11));
	bAccept.setBounds(new Rectangle(290, 115, 45, 25));
	bAccept.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bAccept_actionPerformed(e);
		    }

		});
	bCancel.setBounds(new Rectangle(10, 115, 45, 25));
	bCancel.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bCancel_actionPerformed(e);
		    }

		});
	jPanel1.setBounds(new Rectangle(10, 10, 325, 100));
	jPanel1.setLayout(null);
	jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	lblSystem.setText("System:");
	lblSystem.setBounds(new Rectangle(30, 15, 50, 15));
	lblSystem.setFont(new Font("Dialog", 1, 11));
	lblSystem.setHorizontalAlignment(SwingConstants.RIGHT);
	lblDescription.setText("Description:");
	lblDescription.setBounds(new Rectangle(10, 35, 70, 15));
	lblDescription.setFont(new Font("Dialog", 1, 11));
	lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
	tfCr.setBounds(new Rectangle(85, 10, 30, 20));
	tfCr.setText("CR");
	tfCr.setFont(new Font("Dialog", 1, 11));
	tfCr.setEditable(false);
	tfCr.setBackground(Color.white);
	tfCr.setForeground(Color.red);
	tfCr.setHorizontalAlignment(BasicTextField.CENTER);
	tfSystem.setBounds(new Rectangle(115, 10, 195, 20));
	tfSystem.setFont(new Font("Dialog", 1, 11));
	tfSystem.setForeground(Color.red);
	spDescription.setBounds(new Rectangle(85, 35, 225, 50));
	spDescription.getViewport().setLayout(null);
	taDescription.setBounds(new Rectangle(0, 0, 225, 50));
	taDescription.setWrapStyleWord(true);
	taDescription.setLineWrap(true);
	taDescription.setFont(new Font("Dialog", 1, 11));
	taDescription.setForeground(Color.red);
	spDescription.getViewport().add(taDescription, null);
	jPanel1.add(spDescription, null);
	jPanel1.add(tfSystem, null);
	jPanel1.add(tfCr, null);
	jPanel1.add(lblDescription, null);
	jPanel1.add(lblSystem, null);
	this.getContentPane().add(jPanel1, null);
	this.getContentPane().add(bCancel, null);
	this.getContentPane().add(bAccept, null);
	org.digitall.lib.components.ComponentsManager.centerWindow(this);
    }

    private void bCancel_actionPerformed(ActionEvent e) {
	this.dispose();
    }

    private void bAccept_actionPerformed(ActionEvent e) {
	createSystem();
    }

    private void createSystem() {
	if (!tfSystem.getText().equals("")) {
	    String cr = tfCr.getText().toLowerCase().trim();
	    String sistema = tfSystem.getText().toLowerCase().trim();
	    String consultaInsert = "INSERT INTO admin.systems VALUES ((Select max(idsist)+1 From admin.systems),'" + cr + sistema + "','" + taDescription.getText().trim() + "','');";
	    //System.out.println("consultaInsert--> "+consultaInsert);
	    //            LibSQL.exActualizar('a', consultaInsert);
	    String createGroups = " CREATE GROUP " + cr + sistema + "_admin; CREATE GROUP " + cr + sistema + "_user; CREATE GROUP " + cr + sistema + "_query;";
	    if (LibSQL.exActualizar('a', consultaInsert + createGroups)) {
		org.digitall.lib.components.Advisor.messageBox("Create System Success!", "Message");
		this.dispose();
	    } else {
		org.digitall.lib.components.Advisor.messageBox("Error al intentar crear el nuevo sistema", "Error");
	    }
	} else {
	    org.digitall.lib.components.Advisor.messageBox("System field is Empty", "Error");
	}
    }

}
