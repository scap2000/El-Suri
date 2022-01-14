package org.digitall.apps.systemmanager;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.BasicTextField;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;

//

public class FindFiles extends BasicDialog {

    private BasicPanel jPanel1 = new BasicPanel();
    private BasicLabel lblFile = new BasicLabel();
    private BasicLabel lblComment = new BasicLabel();
    private BasicTextField tfFile = new BasicTextField();
    private BasicScrollPane jScrollPane1 = new BasicScrollPane();
    private JTextArea taComment = new JTextArea();
    private FindButton bFind = new FindButton();
    private AcceptButton bAcept = new AcceptButton();
    private CloseButton bCancel = new CloseButton();

    public FindFiles() {
	this(null, "", false);
    }

    public FindFiles(Frame parent, String title, boolean modal) {
	super(parent, title, modal);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(348, 186));
	this.getContentPane().setLayout(null);
	this.setTitle("Find Files");
	jPanel1.setBounds(new Rectangle(5, 10, 330, 110));
	jPanel1.setLayout(null);
	jPanel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	lblFile.setText("File:");
	lblFile.setBounds(new Rectangle(10, 13, 60, 15));
	lblFile.setFont(new Font("Dialog", 1, 11));
	lblFile.setHorizontalAlignment(SwingConstants.RIGHT);
	lblComment.setText("Comment:");
	lblComment.setBounds(new Rectangle(10, 35, 60, 15));
	lblComment.setFont(new Font("Dialog", 1, 11));
	lblComment.setHorizontalAlignment(SwingConstants.RIGHT);
	tfFile.setBounds(new Rectangle(75, 10, 195, 20));
	jScrollPane1.setBounds(new Rectangle(75, 40, 245, 60));
	jScrollPane1.getViewport().setLayout(null);
	taComment.setBounds(new Rectangle(0, 0, 245, 60));
	bFind.setBounds(new Rectangle(275, 8, 45, 25));
	bFind.setSize(new Dimension(40, 25));
	bFind.setMnemonic('F');
	bAcept.setBounds(new Rectangle(295, 125, 40, 25));
	bAcept.setSize(new Dimension(40, 25));
	bAcept.setMnemonic('A');
	bCancel.setBounds(new Rectangle(5, 125, 40, 25));
	bCancel.setSize(new Dimension(40, 25));
	bCancel.setMnemonic('C');
	jPanel1.add(bFind, null);
	jScrollPane1.getViewport().add(taComment, null);
	jPanel1.add(jScrollPane1, null);
	jPanel1.add(tfFile, null);
	jPanel1.add(lblFile, null);
	jPanel1.add(lblComment, null);
	this.getContentPane().add(bCancel, null);
	this.getContentPane().add(bAcept, null);
	this.getContentPane().add(jPanel1, null);
	org.digitall.lib.components.ComponentsManager.centerWindow(this);
    }

}
