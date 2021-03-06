package org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.apps.resourcescontrol_091.classes.Resources;
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

public class RecursosList extends BasicPrimitivePanel {
    
    private AddButton btnNew = new AddButton();
    private EditButton btnModify = new EditButton();
    private DeleteButton btnDelete = new DeleteButton();
    private BasicPanel bpCentro = new BasicPanel();
    private BasicPanel bpSearch = new BasicPanel();    
    private int[] sizeColumnList = { 216, 476};
    private Vector dataRow = new Vector();
    private GridPanel jpList = new GridPanel(30, sizeColumnList, "Tipos de recursos", dataRow);
    private Vector headerList = new Vector();
    private BorderLayout borderLayout1 = new BorderLayout();
    private TFInput tfSearch = new TFInput(DataTypes.STRING, "Resource", false);
    private FindButton btnSearch = new FindButton();
    private RecursosMgmt recursosMgmt = null;
    private Resources resources = null;

    public RecursosList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(760, 405));
	this.setPreferredSize(new Dimension(760, 405));
	bpCentro.setLayout(borderLayout1);
	bpCentro.add(jpList, BorderLayout.CENTER);
	bpSearch.setLayout(null);
	bpSearch.setSize(new Dimension(400, 100));
	bpSearch.setPreferredSize(new Dimension(400, 60));
	btnSearch.setBounds(new Rectangle(210, 33, 40, 25));
	tfSearch.setBounds(new Rectangle(10, 20, 200, 35));
	bpSearch.add(tfSearch, null);
	bpSearch.add(btnSearch, null);
	bpCentro.add(bpSearch, BorderLayout.NORTH);
	this.add(bpCentro, BorderLayout.CENTER);
	this.addButton(btnDelete);
	this.addButton(btnModify);
	this.addButton(btnNew);
	btnModify.setEnabled(false);
	btnDelete.setEnabled(false);	
	setHeaderList();
	
	btnSearch.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnSearch_actionPerformed(e);
				 }

			     }
	);
	
	btnNew.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				 btnNew_actionPerformed(e);
			      }

			  }
	);
	
	btnModify.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnModify_actionPerformed(e);
				 }

			     }
	);
    }    
    
    private void setHeaderList() {      
	
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("Nombre");
	headerList.addElement("Descripci??n"); //Tipo ID
	jpList.getTable().addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
					    loadResource();
					    btnModify.setEnabled(true);
					    if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						loadRecursosMgmt();
					    }
					}

				    }
	);	
        jpList.setParams("personalfiles.getAllResources", "", headerList);	
    }

    public void refresh() {
	String params = "'" + tfSearch.getString() + "'";      
	jpList.refresh(params);
	btnModify.setEnabled(false);
	btnDelete.setEnabled(false);	
    }
    
    private void btnSearch_actionPerformed(ActionEvent e) {     
	refresh();      
    }
    
    private void btnNew_actionPerformed(ActionEvent e) {
	recursosMgmt = new RecursosMgmt(this);	
	//personMgmt.setParentList(this);
	ExtendedInternalFrame recursosMgmtContainer = new ExtendedInternalFrame("Agregar nuevo recurso");
	recursosMgmt.setResource(new Resources());
	recursosMgmtContainer.setCentralPanel(recursosMgmt);
	recursosMgmtContainer.show();
    }
    
    private void btnModify_actionPerformed(ActionEvent e) {
	loadResource();	
	loadRecursosMgmt();	
    }
    
    private void loadResource() {
	if (!dataRow.isEmpty()) {	   
	    resources = new Resources();
	    int idResource = Integer.parseInt(jpList.getValuesAt(0).get(jpList.getTable().getSelectedRow()).toString());
	    resources.setIdResource(idResource);
	    resources.retrieveData();
	    
	    btnModify.setToolTipText("<html><center><u>Modificar Persona</u><br>"+ dataRow.elementAt(1).toString() +"</center></html>");
	    btnDelete.setToolTipText("<html><center><u>Eliminar Persona</u><br>"+ dataRow.elementAt(1).toString() +"</center></html>");
	    btnModify.setEnabled(true);
	    btnDelete.setEnabled(true);
	} else {
	    btnModify.setToolTipText("Modificar la persona seleccionada");
	    btnDelete.setToolTipText("Eliminar la persona seleccionada");
	    btnModify.setEnabled(false);
	    btnDelete.setEnabled(false);
	}
    }
    
    private void loadRecursosMgmt() {
	if (!dataRow.isEmpty()) {
	    recursosMgmt = new RecursosMgmt(this);      
	    //personMgmt.setParentList(this);
	    ExtendedInternalFrame recursosMgmtContainer = new ExtendedInternalFrame("Agregar nuevo recurso");
	    recursosMgmt.setResource(resources);
	    recursosMgmtContainer.setCentralPanel(recursosMgmt);
	    recursosMgmtContainer.show();
	}
    }
}
