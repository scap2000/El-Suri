package org.digitall.common.drilling;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;

import org.digitall.common.drilling.PanelDrillData;
import org.digitall.common.drilling.PanelLithologyData;
import org.digitall.common.drilling.PanelSamplingAndAssay;
import org.digitall.common.drilling.PanelHeader;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.icons.IconTypes;

public class ShowData extends BasicDialog {

    private JTabbedPane jTabbedPane1 = new JTabbedPane();
    private PanelDrillData jpDrillData;
    private PanelLithologyData jpLithologyData;
    private PanelSamplingAndAssay jpSamplingAndAssay;
    private CloseButton bCancel = new CloseButton();
    private PanelHeader panelHeader;
    private String drillName = "";
    private String idproject = "", iddrill = "";
    final String TEXTO = "Log for Drill: DDH";

    public ShowData(int _idproject, int _iddrill, String _drillName) {
	try {
	    idproject = String.valueOf(_idproject);
	    iddrill = String.valueOf(_iddrill);
	    drillName = _drillName;
	    jpDrillData = new PanelDrillData(idproject, iddrill);
	    jpLithologyData = new PanelLithologyData(idproject, iddrill);
	    jpSamplingAndAssay = new PanelSamplingAndAssay(idproject, iddrill);
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public ShowData(String _idproject, String _iddrill, String _drillName) {
	try {
	    idproject = _idproject;
	    iddrill = _iddrill;
	    drillName = _drillName;
	    jpDrillData = new PanelDrillData(idproject, iddrill);
	    jpLithologyData = new PanelLithologyData(idproject, iddrill);
	    jpSamplingAndAssay = new PanelSamplingAndAssay(idproject, iddrill);
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(735, 403));
	this.setBounds(new Rectangle(10, 10, 735, 402));
	panelHeader = new PanelHeader(this.getWidth(), TEXTO + drillName, IconTypes.CR_IconHeaderLogo, IconTypes.CRDrilling_IconHeaderLogo);
	this.getContentPane().add(panelHeader, null);
	this.getContentPane().setLayout(null);
	this.setTitle("Drill Logging");
	jTabbedPane1.setBounds(new Rectangle(8, 40, 715, 300));
	bCancel.setBounds(new Rectangle(10, 345, 40, 25));
	bCancel.setMnemonic('C');
	bCancel.setToolTipText("Cancel");
	bCancel.setSize(new Dimension(40, 25));
	bCancel.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bCancel_actionPerformed(e);
		    }

		});
	jpSamplingAndAssay.setToolTipText("null");
	jpSamplingAndAssay.setLayout(null);
	jTabbedPane1.addTab("Logging", jpDrillData);
	jTabbedPane1.addTab("Lithology Data & Structure", jpLithologyData);
	jTabbedPane1.addTab("Sampling & Assay", jpSamplingAndAssay);
	this.getContentPane().add(bCancel, null);
	this.getContentPane().add(jTabbedPane1, null);
	jpSamplingAndAssay.setBounds(5, 5, 715, 265);
	org.digitall.lib.components.ComponentsManager.centerWindow(this);
    }

    private void bCancel_actionPerformed(ActionEvent e) {
	this.dispose();
    }

}
