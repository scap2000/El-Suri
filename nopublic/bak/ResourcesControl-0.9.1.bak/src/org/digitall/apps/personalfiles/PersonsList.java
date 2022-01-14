package org.digitall.apps.personalfiles;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.personalfiles.classes.Persona;
import org.digitall.apps.personalfiles.interfaces.PersonMgmt;
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
import org.digitall.lib.sql.LibSQL;

public class PersonsList extends BasicPrimitivePanel {

    private AddButton btnNew = new AddButton();
    private BasicPanel content = new BasicPanel();
    private BasicPanel jpSearch = new BasicPanel("Buscar Persona");
    private int[] sizeColumnList = { 60, 70, 108, 413, 40};
    private Vector dataRow = new Vector();
    private GridPanel jpList = new GridPanel(50000, sizeColumnList, "Personas", dataRow);
    private Vector headerList = new Vector();
    private TFInput tfSearch = new TFInput(DataTypes.STRING, "Person", false);
    private FindButton btnSearch = new FindButton();
    private EditButton btnModify = new EditButton();
    private DeleteButton btnDelete = new DeleteButton();
    private BasicCheckBox chkEmployee = new BasicCheckBox(); 
    private Persona selectedPerson;
    // private Employees selectedEmployee;
    private PersonMgmt personMgmt = new PersonMgmt();
   
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
	this.add(content, BorderLayout.CENTER);
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
        headerList.addElement("Legajo");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("Tipo ID"); //Tipo ID
	headerList.addElement("Nº ID"); //Nº ID
        headerList.addElement("Apellido(s), Nombre(s)");
	headerList.addElement("Emp.");
        jpList.getTable().addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
					    loadPerson();
					    if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						loadPersonMgmt();
					    }
					}

				    }
	);
	String params = "'" + tfSearch.getString() + "','Falso'"; 
	//jpList.setParams("personalfiles.getAllPersonsList", params, headerList);
        jpList.setParams("personalfiles.getAllPersonsList", params, headerList);
	btnModify.setEnabled(false);
	btnDelete.setEnabled(false);
    }

    public void refresh() {
	String isEmpleado = "Falso";
	if (chkEmployee.isSelected()){
	    isEmpleado ="Verdadero";
	}
	String params = "'" + tfSearch.getString() + "','"+isEmpleado+"'";	
	jpList.refresh(params);
	btnModify.setEnabled(false);
	btnDelete.setEnabled(false);
	btnModify.setToolTipText("Modificar la persona seleccionada");
	btnDelete.setToolTipText("Eliminar la persona seleccionada");
    }

    private void loadPerson() {
	if (!dataRow.isEmpty()) {
	    selectedPerson = new Persona();
	    selectedPerson.setIdPerson(Integer.parseInt("" + dataRow.elementAt(1)));
	    selectedPerson.retrieveData();
	    selectedPerson.retrievePicture();//Cargamos la foto
	    
	    btnModify.setToolTipText("<html><center><u>Modificar Persona</u><br>"+ dataRow.elementAt(5).toString() +"</center></html>");
	    btnDelete.setToolTipText("<html><center><u>Eliminar Persona</u><br>"+ dataRow.elementAt(5).toString() +"</center></html>");
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
	    personMgmt = new PersonMgmt();
	    personMgmt.setParentList(this);
	    personMgmt.setPerson(selectedPerson);
	    personMgmt.loadPestañas();	    
	    ExtendedInternalFrame personMgmtContainer = new ExtendedInternalFrame("Modificar datos de: " + dataRow.elementAt(5).toString());
	    personMgmtContainer.setCentralPanel(personMgmt);
	    personMgmtContainer.show();
	}
    }

    private void btnSearch_actionPerformed(ActionEvent e) {	
	refresh();	
    }

    private void btnNew_actionPerformed(ActionEvent e) {
	personMgmt = new PersonMgmt();
	personMgmt.setParentList(this);
	ExtendedInternalFrame personMgmtContainer = new ExtendedInternalFrame("Agregar nueva Persona");
	personMgmt.setPerson(new Persona());
	personMgmtContainer.setCentralPanel(personMgmt);
	personMgmtContainer.show();
    }

    private void btnClose_actionPerformed(ActionEvent e) {
    }

    private void btnModify_actionPerformed(ActionEvent e) {
	loadPerson();
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
