package org.digitall.common.resourcescontrol.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;

import java.util.Vector;

import org.digitall.common.resourcescontrol.classes.Employees;
import org.digitall.common.resourcescontrol.interfaces.PersonsMgmt;
import org.digitall.lib.org.Persons;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.EditButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.image.LibIMG;
import org.digitall.lib.sql.LibSQL;

public class PersonsList extends BasicPrimitivePanel {

    private AddButton btnNew = new AddButton();
    private BasicPanel content = new BasicPanel();
    private BasicPanel jpSearch = new BasicPanel("Buscar Persona");
    private int[] sizeColumnList = { 50, 201, 54, 107, 61, 140, 94 };
    private Vector dataRow = new Vector();
    private GridPanel jpList = new GridPanel(50000, sizeColumnList, "Personas", dataRow);
    private Vector headerList = new Vector();
    private TFInput tfSearch = new TFInput(DataTypes.STRING, "Person", false);
    private FindButton btnSearch = new FindButton();
    private EditButton btnModify = new EditButton();
    private DeleteButton btnDelete = new DeleteButton();
    private BasicCheckBox chkEmployee = new BasicCheckBox();
    private PersonsMgmt personMgmt;
    private Persons selectedPerson;
    private Employees selectedEmployee;

    public PersonsList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(760, 405));
	btnNew.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnNew_actionPerformed(e);
			      }

			  }
	);
	jpSearch.setLayout(null);
	jpSearch.setSize(new Dimension(400, 100));
	jpSearch.setPreferredSize(new Dimension(400, 60));
	tfSearch.setBounds(new Rectangle(10, 20, 200, 35));
	tfSearch.setSize(new Dimension(200, 35));
	btnSearch.setBounds(new Rectangle(315, 30, 40, 25));
	btnSearch.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnSearch_actionPerformed(e);
				 }

			     }
	);
	btnModify.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnModify_actionPerformed(e);
				 }

			     }
	);
	btnDelete.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnDelete_actionPerformed(e);
				 }

			     }
	);
	chkEmployee.setText("Empleados");
	chkEmployee.setBounds(new Rectangle(215, 30, 105, 25));
	jpSearch.add(chkEmployee, null);
	jpSearch.add(tfSearch, null);
	jpSearch.add(btnSearch, null);
	content.setLayout(new BorderLayout());
	content.add(jpSearch, BorderLayout.NORTH);
	content.add(jpList, BorderLayout.CENTER);
	add(content);
	this.addButton(btnDelete);
	this.addButton(btnModify);
	this.addButton(btnNew);
	tfSearch.getTextField().addKeyListener(new KeyAdapter() {

					    public void keyReleased(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER)
						    refresh();
					    }

					}
	);
	setHeaderList();
	btnNew.setToolTipText("Agregar nueva persona");
	btnModify.setToolTipText("Modificar la persona seleccionada");
	btnDelete.setToolTipText("Eliminar la persona seleccionada");
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
	headerList.addElement("Profesión");
	headerList.addElement("*");
        headerList.addElement(Environment.lang.getProperty("Birthdate"));
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	jpList.getTable().addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent e) {
					    loadPerson();
					    if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						loadPersonMgmt();
					    }
					}

				    }
	);
	String params = "'" + tfSearch.getString() + "'," + chkEmployee.isSelected();
	jpList.setParams("org.getAllPersonal", params, headerList);
	//---> Original
	//jpList.setParams("org.getAllPersonal_test", params, headerList);
	 btnModify.setEnabled(false);
	 btnDelete.setEnabled(false);
    }

    public void refresh() {
	String params = "'" + tfSearch.getString() + "'," + chkEmployee.isSelected();
	jpList.refresh(params);
	btnModify.setEnabled(false);
	btnDelete.setEnabled(false);
	btnModify.setToolTipText("Modificar la persona seleccionada");
	btnDelete.setToolTipText("Eliminar la persona seleccionada");
    }

    private void loadPerson() {
	if (!dataRow.isEmpty()) {
	    selectedPerson = new Persons();
	    selectedPerson.setIdPerson(Integer.parseInt("" + dataRow.elementAt(0)));
	    selectedPerson.setName("" + dataRow.elementAt(1));
	    selectedPerson.setLastName("" + dataRow.elementAt(2));
	    selectedPerson.setIdPrefix(Integer.parseInt("" + dataRow.elementAt(3)));
	    selectedPerson.setIdIdentificationType(Integer.parseInt("" + dataRow.elementAt(6)));
	    selectedPerson.setIdentificationNumber(dataRow.elementAt(8).toString());
	    selectedPerson.setIdSuffix(Integer.parseInt("" + dataRow.elementAt(9)));
	    selectedPerson.setIdFormatView(Integer.parseInt("" + dataRow.elementAt(11)));
	    selectedPerson.setIdContactType(Integer.parseInt("" + dataRow.elementAt(13)));
	    selectedPerson.setIdProfession(Integer.parseInt("" + dataRow.elementAt(15)));
	    selectedPerson.setTitle("" + dataRow.elementAt(17));
	    selectedPerson.setBirthDate(Proced.setFormatDate(dataRow.elementAt(18).toString(), false));
	    selectedPerson.setDigitalSign("" + dataRow.elementAt(19));
	    selectedPerson.setIdCivilState(Integer.parseInt("" + dataRow.elementAt(20)));
	    selectedPerson.setIdCommunicationType(Integer.parseInt("" + dataRow.elementAt(22)));
	    selectedPerson.setDescription("" + dataRow.elementAt(24));
	    String params = "" + dataRow.elementAt(0);
	    if (!dataRow.elementAt(25).toString().equals("0")) {
		selectedEmployee = new Employees();
		selectedEmployee.setIdEmployee(Integer.parseInt("" + dataRow.elementAt(25)));
		//selectedEmployee.setCompany(Integer.parseInt(""+ dataRow.elementAt(25)));    // REVISAR!!!
		selectedEmployee.setStartDate(Proced.setFormatDate("" + dataRow.elementAt(27), false));
		selectedEmployee.setEndDate(Proced.setFormatDate("" + dataRow.elementAt(28), false));
		selectedEmployee.setFileNumber(Integer.parseInt("" + dataRow.elementAt(29)));
		selectedEmployee.setFileInternalNumber(Integer.parseInt("" + dataRow.elementAt(30)));
		selectedEmployee.setIdContractType(Integer.parseInt("" + dataRow.elementAt(31)));
	    } else {
		selectedEmployee = new Employees();
	    }
	    selectedEmployee.setPerson(selectedPerson);
	    btnModify.setToolTipText("<html><center><u>Modificar Persona</u><br>"+ dataRow.elementAt(5) +"</center></html>");
	    btnDelete.setToolTipText("<html><center><u>Eliminar Persona</u><br>"+ dataRow.elementAt(5) +"</center></html>");
	    btnModify.setEnabled(true);
	    btnDelete.setEnabled(true);
	} else {
	    btnModify.setToolTipText("Modificar la persona seleccionada");
	    btnDelete.setToolTipText("Eliminar la persona seleccionada");
	    btnModify.setEnabled(false);
	    btnDelete.setEnabled(false);
	}
    }

    private void loadPersonMgmt() {
	if (!dataRow.isEmpty()) {
	personMgmt = new PersonsMgmt();
	personMgmt.setParentList(this);
	personMgmt.setPerson(selectedPerson);
	personMgmt.setEmployee(selectedEmployee);
	personMgmt.setUserName(dataRow.elementAt(32).toString());
	ExtendedInternalFrame personMgmtContainer = new ExtendedInternalFrame("Modificar datos de: " + dataRow.elementAt(5).toString().toUpperCase());
	personMgmtContainer.setCentralPanel(personMgmt);
	personMgmtContainer.show();
	}
    }

    private void btnSearch_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void btnNew_actionPerformed(ActionEvent e) {
	personMgmt = new PersonsMgmt();
	personMgmt.setParentList(this);
	ExtendedInternalFrame personMgmtContainer = new ExtendedInternalFrame("Agregar nueva Persona");
	personMgmtContainer.setCentralPanel(personMgmt);
	personMgmtContainer.show();
    }

    private void btnClose_actionPerformed(ActionEvent e) {
    }

    private void btnModify_actionPerformed(ActionEvent e) {
	loadPersonMgmt();
    }

    private void btnDelete_actionPerformed(ActionEvent e) {
	if (selectedPerson != null) {
	    if (Advisor.question("Borrar persona", "¿Está seguro de borrar la persona seleccionada?")) {
		if (LibSQL.getInt("org.delperson", "" + selectedPerson.getIdPerson()) == 0) {
		    refresh();
		}
	    }
	}
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Listado de Personas y/o Empleados de la Organización");
    }
}
