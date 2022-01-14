package org.digitall.common.cashflow.interfaces;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JDesktopPane;

import org.digitall.common.cashflow.interfaces.CompanyListPanel;
import org.digitall.common.cashflow.interfaces.CompanyTreePanel;
import org.digitall.common.cashflow.interfaces.PersonListPanel;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicPanel;

public class FrameContainer extends BasicInternalFrame {

    private BasicContainerPanel panel;
    private int panelType;
    public static final int COMPANYLIST = 1;
    public static final int COMPANY = 2;
    public static final int COMPANYTREE = 3;
    public static final int PERSONLIST = 4;
    public static final int PERSON = 5;
    public static final int BUDGETLIST = 6;
    public static final int EXPENDITUREACCOUNT = 7;
    public static final int BUDGETEXPENDITUREACCOUNT = 9;
    public static final int COSTSCENTRE = 10;
    public static final int BUDGETCOSTSCENTRELIST = 12;
    public static final int BUDGETCOSTSCENTREAMOUNT = 13;
    public static final int BUDGETEXPENDITUREACCOUNTMGMT = 14;
    public static final int CCAMOUNTS = 18;
    public static final int CCASSIGNAMOUNTDETAIL = 22;
    private String title = "";
    private JDesktopPane parentDesktop;
    private Component parentList;
    private Object params;

    public FrameContainer(int _panelType, JDesktopPane _parentDesktop, Component _parentList, Object _params) {
	try {
	    params = _params;
	    parentList = _parentList;
	    parentDesktop = _parentDesktop;
	    panelType = _panelType;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.getContentPane().setLayout(null);
	this.setSize(new Dimension(763, 615));
	this.setClosable(true);
	this.setIconifiable(true);
	switch (panelType) {
	    case COMPANYLIST :
		title = "Proveedores";
		panel = new CompanyListPanel(this, parentDesktop);
		break;
	    case COMPANY :
		title = "Administrar Proveedor";
		//panel = new CompanyPanel(this, CompanyPanel.COMPANYTREE);
		break;
	    case COMPANYTREE :
		title = "Proveedores";
		panel = new CompanyTreePanel();
		break;
	    case PERSONLIST :
		title = "Personas";
		panel = new PersonListPanel(parentDesktop, this);
		break;
	    case PERSON :
		title = "Administrar Persona";
		//panel = new PersonPanel(parentDesktop, (PersonListPanel)parentList, this, (Vector)params, PersonPanel.PERSONLIST);
		break;
	    default :
		{

		}
		break;
	}
	this.setTitle(title);
	if (panel != null) {
	    this.setSize(panel.getWidth() + 10, panel.getHeight() + 40);
	    this.getContentPane().add(panel, null);
	}
    }

    public BasicPanel getPanel() {
	return panel;
    }

    public void setPanel(BasicContainerPanel panel) {
	this.panel = panel;
	this.setSize(panel.getWidth() + 10, panel.getHeight() + 40);
	this.getContentPane().add(panel, null);
    }
    
    
    public boolean saveData() {
	if (panel != null) {
	    return panel.saveData();
	} else {
	    return false;
	}
    }

}
