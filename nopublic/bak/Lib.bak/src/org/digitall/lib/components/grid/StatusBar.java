package org.digitall.lib.components.grid;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JProgressBar;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

//

public class StatusBar extends BasicDialog {

    private JProgressBar progressBar = new JProgressBar();
    private Component parent = null;
    private BasicLabel iconSearch = new BasicLabel(IconTypes.statusBarIcon_16x16);
    private BasicLabel lblTitle = new BasicLabel();
    private CloseButton btnClose = new CloseButton();
    private Thread threadTask;
    protected boolean actionCancel = false;
    private BasicPanel jp = new BasicPanel();

    public StatusBar(Component _parent) {
	try {
	    parent = _parent;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	parent.setEnabled(false);
	iconSearch.setBounds(new Rectangle(15, 15, 40, 35));
	lblTitle.setText(Environment.lang.getProperty("StatusBarText"));
	//"Realizando busqueda, espere por favor...");
	lblTitle.setBounds(new Rectangle(70, 15, 315, 15));
	lblTitle.setFont(new Font("Dialog", 1, 11));
	btnClose.setBounds(new Rectangle(385, 25, 40, 25));
	btnClose.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnClose_actionPerformed(e);
		    }

		});
	jp.setBounds(new Rectangle(70, 30, 290, 20));
	jp.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	jp.setLayout(null);
	jp.setSize(new Dimension(290, 20));
	this.setSize(new Dimension(444, 90));
	this.getContentPane().setLayout(null);
	this.setTitle(Environment.lang.getProperty("StatusBarTitle"));
	//"Buscando...");
	progressBar.setBounds(new Rectangle(10, 5, 270, 10));
	progressBar.setForeground(new Color(41, 13, 151));
	progressBar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	progressBar.setBackground(Color.white);
	progressBar.setToolTipText(Environment.lang.getProperty("StatusBarTitle"));
	jp.add(progressBar, null);
	this.getContentPane().add(jp, null);
	this.getContentPane().add(btnClose, null);
	this.getContentPane().add(lblTitle, null);
	this.getContentPane().add(iconSearch, null);
	progressBar.setIndeterminate(true);
    }

    public void disposeme() {
	parent.setEnabled(true);
	this.setVisible(false);
    }

    public void startTask(Thread _task) {
	threadTask = _task;
	threadTask.start();
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	threadTask.stop();
	actionCancel = true;
	disposeme();
    }

    public boolean getActionCancel() {
	return actionCancel;
    }

}
