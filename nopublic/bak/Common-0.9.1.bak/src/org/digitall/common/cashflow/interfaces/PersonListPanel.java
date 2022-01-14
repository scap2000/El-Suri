package org.digitall.common.cashflow.interfaces;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.JDesktopPane;

import org.digitall.common.cashflow.interfaces.FrameContainer;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.EditButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.PanelGrid;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

//

public class PersonListPanel extends BasicContainerPanel {

    private CloseButton btnClose = new CloseButton();
    private AddButton btnNew = new AddButton();
     
    private BasicPanel panelSearch = new BasicPanel();
    private int[] sizeColumnList = { 42, 192, 51, 103, 46, 227, 86 };
    private PanelGrid listPanel = new PanelGrid(30, sizeColumnList, "Personas", false);
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private TFInput tfSearch = new TFInput(DataTypes.STRING, "Person", false);
    private FindButton btnSearch = new FindButton();
    private Component parent;
    private JDesktopPane parentDesktop;
    private EditButton btnEdit = new EditButton();
    private DeleteButton btnDelete = new DeleteButton();
    private BasicCheckBox chkEmployee = new BasicCheckBox();

    public PersonListPanel(JDesktopPane _parentDesktop, Component _parent) {
	try {
	    parentDesktop = _parentDesktop;
	    parent = _parent;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(780, 470));
	btnClose.setBounds(new Rectangle(735, 435, 40, 25));
	btnClose.setSize(new Dimension(40, 25));
	btnClose.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnClose_actionPerformed(e);
		    }

		});
	btnNew.setBounds(new Rectangle(5, 435, 40, 25));
	btnNew.setSize(new Dimension(40, 25));
	btnNew.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnNew_actionPerformed(e);
		    }

		});
	//new Rectangle(0, 425, 770, 1));
	//setSize(new Dimension(780, 1));
	panelSearch.setBounds(new Rectangle(5, 0, 770, 70));
	panelSearch.setBorder(BorderPanel.getBorderPanel("Buscar...", Color.blue, Color.black));
	panelSearch.setLayout(null);
	panelSearch.setSize(new Dimension(770, 70));
	listPanel.setBounds(new Rectangle(5, 85, 770, 330));
	tfSearch.setBounds(new Rectangle(55, 25, 200, 35));
	tfSearch.setSize(new Dimension(200, 35));
	btnSearch.setBounds(new Rectangle(265, 35, 40, 25));
	btnSearch.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnSearch_actionPerformed(e);
		    }

		});
	btnEdit.setBounds(new Rectangle(53, 435, 40, 25));
	btnEdit.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnEdit_actionPerformed(e);
		    }

		});
	btnDelete.setBounds(new Rectangle(100, 435, 40, 25));
	btnDelete.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnDelete_actionPerformed(e);
		    }

		});
	chkEmployee.setText("Empleados");
	chkEmployee.setBounds(new Rectangle(355, 35, 85, 25));
	chkEmployee.setFont(new Font("Default", 0, 11));
	panelSearch.add(chkEmployee, null);
	panelSearch.add(tfSearch, null);
	panelSearch.add(btnSearch, null);
	this.add(btnDelete, null);
	this.add(btnEdit, null);
	this.add(listPanel, null);
	this.add(panelSearch, null);
	 
	this.add(btnClose, null);
	this.add(btnNew, null);
	listPanel.autoSize();
	tfSearch.setKeyListener(new KeyAdapter() {

		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    loadList();
		    }

		});
	setHeaderList();
	loadList();
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("Prefijo");
	headerList.addElement("Apellido, Nombre");
	headerList.addElement("*");
	headerList.addElement("Tipo");
	headerList.addElement("Numero");
	headerList.addElement("*");
	headerList.addElement("Sufijo");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("Profesiï¿½n");
	headerList.addElement("*");
	headerList.addElement("Fecha Nac.");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	listPanel.getTable().addMouseListener(new MouseAdapter() {

		    public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
			    dataRow = listPanel.getDataRow();
			} else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
			    loadPerson();
			}
		    }

		});
    }

    public void loadList() {
	String params = "'" + tfSearch.getString() + "'";
	listPanel.setTable(parent, "org.getallPersons", params, headerList);
    }

    private void btnSearch_actionPerformed(ActionEvent e) {
	loadList();
    }

    private void loadPerson() {
	dataRow = listPanel.getDataRow();
	FrameContainer person = new FrameContainer(FrameContainer.PERSON, parentDesktop, this, dataRow);
	parentDesktop.add(person);
	person.setVisible(true);
    }

    private void btnNew_actionPerformed(ActionEvent e) {
	FrameContainer person = new FrameContainer(FrameContainer.PERSON, parentDesktop, this, null);
	parentDesktop.add(person);
	person.setVisible(true);
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	parent.setVisible(false);
    }

    private void btnEdit_actionPerformed(ActionEvent e) {
	loadPerson();
    }

    private void btnDelete_actionPerformed(ActionEvent e) {
	dataRow = listPanel.getDataRow();
	if (LibSQL.deleteQuery("org.delPerson", dataRow.elementAt(0).toString()) != -1)
	    loadList();
	else
	    Advisor.messageBox(Environment.lang.getProperty("DeleteError"), "Error");
    }

}
