package org.digitall.apps.personalfiles.interfaces;

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
import org.digitall.common.personalfiles.classes.Relatives;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class SelectRelatives extends BasicContainerPanel{

    private BorderLayout borderLayout1 = new BorderLayout();
    private BasicPanel jPanel1 = new BasicPanel();
    private BorderLayout borderLayout2 = new BorderLayout();
    private BasicPanel jPanel2 = new BasicPanel();
    private TFInput tfSearch = new TFInput(DataTypes.STRING, "Person", false);
    private BasicCheckBox chkEmployee = new BasicCheckBox();
    private FindButton btnSearch = new FindButton();
    private BasicPanel jPanel3 = new BasicPanel();
    private BasicLabel jLabel1 = new BasicLabel();
    private AssignButton btnAddRelatives = new AssignButton(true);
    private int[] sizeColumnList = {70, 108, 286,55};
    private Vector dataRow = new Vector();
    private GridPanel jpList = new GridPanel(30, sizeColumnList, "Personas", dataRow);
    private Vector headerList = new Vector();
    private PersonMgmt parent = null;
    private Persona person = null;
    private Relatives relative = null;
    private AsignarParentesco asignarParentesco = null;
    
    public SelectRelatives() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public SelectRelatives(PersonMgmt _parent) {
	try {
	    this.parent = _parent;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(borderLayout1);
	this.setSize(new Dimension(580, 300));
	this.setPreferredSize(new Dimension(580, 300));
	jPanel1.setLayout(borderLayout2);
	jPanel1.setBorder(BorderPanel.getBorderPanel("Agregar Familiar"));
	jPanel2.setPreferredSize(new Dimension(10, 50));
	jPanel2.setLayout(null);
	tfSearch.setBounds(new Rectangle(5, 10, 215, 35));
	tfSearch.getTextField().addKeyListener(new KeyAdapter() {

					    public void keyReleased(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER)
						    refresh();
					    }

					}
	);      
	chkEmployee.setText("Empleados");
	chkEmployee.setBounds(new Rectangle(225, 20, 95, 20));
	btnSearch.setBounds(new Rectangle(300, 20, 55, 25));
	btnSearch.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnSearch_actionPerformed(e);
				 }

			     }
	);
	jPanel3.setLayout(null);
	jPanel3.setPreferredSize(new Dimension(1, 30));
	jLabel1.setText("Puede asignar un familiar con el boton a su derecha -->");
	jLabel1.setBounds(new Rectangle(105, 5, 335, 15));
	btnAddRelatives.setBounds(new Rectangle(535, 5, 30, 20));
	btnAddRelatives.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnAddRelatives_actionPerformed(e);
				}

			    }
	);
	this.setSize(580, 275);
	jPanel2.add(btnSearch, null);
	jPanel2.add(chkEmployee, null);
	jPanel2.add(tfSearch, null);
	jPanel1.add(jPanel2, BorderLayout.NORTH);
	jPanel1.add(jPanel3, BorderLayout.SOUTH);
	jPanel3.add(btnAddRelatives, null);
	jPanel3.add(jLabel1, null);
	jPanel1.add(jpList, BorderLayout.CENTER);
	this.add(jPanel1, BorderLayout.CENTER);
	setHeaderList();
	btnAddRelatives.setEnabled(false);
	
    }
    
    private void setHeaderList() {      
	headerList.removeAllElements();
      	
        headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("Tipo ID");
	headerList.addElement("Nº ID");
        headerList.addElement("Apellido(s), Nombre(s)");
	headerList.addElement("Emp.");
        jpList.getTable().addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
					    loadRelatives();
					    btnAddRelatives.setEnabled(true);
					    if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						//loadPersonMgmt();						 
					    }
					}

				    }
	);
	String params = "'',-1,'Falso'"; 
	jpList.setParams("org.getAllNotRelatives", params, headerList);	
    }
    public void refresh() {
	String isEmpleado = "Falso";
	if (chkEmployee.isSelected()){
	    isEmpleado ="Verdadero";
	}
         String params = "'" + tfSearch.getString() + "',"+ person.getIdPerson()+",'"+isEmpleado+"'"; 
	 jpList.refresh(params);	 
    }
    
    public void refreshDatos() {	
	refresh();        
	parent.refreshGridRelatives();
	btnAddRelatives.setEnabled(false);
    }
    
    private void btnSearch_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void btnAddRelatives_actionPerformed(ActionEvent e) {
	asignarParentesco = new AsignarParentesco();
	ExtendedInternalFrame relativesMgmtContainer = new ExtendedInternalFrame("Agregar un familiar");
	relativesMgmtContainer.setCentralPanel(asignarParentesco);
	asignarParentesco.setRelatives(relative);
	asignarParentesco.setParent(this);
	relativesMgmtContainer.show();  
    }
    
    public void setPerson(Persona _person){
	this.person = _person;
    }

    private void loadRelatives(){       
	if (!dataRow.isEmpty()) {
	    relative = new Relatives();   
	    relative.setIdPerson(Integer.parseInt("" + dataRow.elementAt(0)));	    	    	   
	    relative.setIdPersonEmployee(Integer.parseInt("" + person.getIdPerson()));                                      
	    
	}
    }
    public void refreshComponent(){
	parent.refreshGridRelatives();
	parent.setEnabledBotonesRelatives();
    }
    public void setRelativeNull(){
        relative = null;
    }
    
}
