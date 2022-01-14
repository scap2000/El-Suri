package org.digitall.apps.taxes.interfases.rentsadmin;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.apps.taxes.classes.Rent;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.grid.TableTransferHandler;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;

public class RentsList extends BasicPrimitivePanel {

    private BasicPanel contentPanel = new BasicPanel();
    private BasicPanel searchPanel = new BasicPanel();

    private TFInput tfEmpresa = new TFInput(DataTypes.STRING, "Enterprise",false);
    private TFInput tfResponsable = new TFInput(DataTypes.STRING, "PersonCharge",false);
    private TFInput tfPredio = new TFInput(DataTypes.STRING, "Predio",false);

    private int[] sizeColumnList = {180, 165, 258, 90, 92, 70 , 345};
    private Vector rentsHeader = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel rentsPanel = new GridPanel(50000, sizeColumnList, "Listado de Alquileres", dataRow){
	public void finishLoading() {
	    controlBotones();
	}
    }
    ;
    
    private FindButton btnFind = new FindButton();
    private AddButton btnAdd = new AddButton();
    private ModifyButton btnEdit = new ModifyButton();
    
    private Rent rent;
    private RentsMgmt rentsMgmt;

    public RentsList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout(null);
	this.setSize(new Dimension(710, 388));
	this.setPreferredSize(new Dimension(710, 515));
	tfEmpresa.setBounds(new Rectangle(25, 10, 205, 35));
	rentsPanel.setBounds(new Rectangle(5, 75, 700, 270));
	contentPanel.setBounds(new Rectangle(5, 5, 700, 500));
	contentPanel.setLayout(null);
	contentPanel.setSize(new Dimension(700, 515));
	searchPanel.setBounds(new Rectangle(5, 10, 690, 50));
	searchPanel.setLayout(null);
	tfResponsable.setBounds(new Rectangle(295, 10, 140, 35));
	btnFind.setBounds(new Rectangle(660, 20, 30, 25));
	btnFind.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      btnFind_actionPerformed(e);
				  }

			      }
	);
	btnAdd.setBounds(new Rectangle(560, 525, 40, 25));
	btnAdd.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAdd_actionPerformed(e);
			      }

			  }
	);
        tfPredio.setBounds(new Rectangle(495, 10, 140, 35));
        searchPanel.add(tfPredio, null);
        searchPanel.add(tfResponsable, null);
        searchPanel.add(tfEmpresa, null);
        searchPanel.add(btnFind, null);
        btnEdit.setBounds(new Rectangle(610, 525, 40, 25));
	btnEdit.setToolTipText("Modificar Alquiler");
	btnEdit.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnEdit_actionPerformed(e);
			       }

			   }
	);
	contentPanel.add(rentsPanel, null);
	contentPanel.add(searchPanel, null);
	this.add(contentPanel, null);
	this.addButton(btnEdit);
	this.addButton(btnAdd);
	rentsPanel.getTable().setDragEnabled(true);
	rentsPanel.getTable().setTransferHandler(new TableTransferHandler());
	tfEmpresa.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});
	tfResponsable.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});
	tfPredio.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});
        btnEdit.setEnabled(false);
	setCadastralHeader();
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
    }
    
    public void refresh() {
	String params = "'"+ tfEmpresa.getString().trim() +"','"+ tfResponsable.getString().trim() +"','"+ tfPredio.getString().trim() +"'";
	rentsPanel.refresh(params);
        btnEdit.setEnabled(false);
    }

    private void setCadastralHeader() {
	rentsHeader.removeAllElements();
        rentsHeader.addElement("*"); //idempresa
        rentsHeader.addElement(Environment.lang.getProperty("Enterprise"));
        rentsHeader.addElement(Environment.lang.getProperty("PersonCharge"));
        rentsHeader.addElement(Environment.lang.getProperty("Predio"));
        rentsHeader.addElement("C. Comodato");
        rentsHeader.addElement("Vigencia");
        rentsHeader.addElement("Duracion");
        rentsHeader.addElement("*");
        rentsHeader.addElement(Environment.lang.getProperty("Observations"));
        rentsHeader.addElement("*");
        rentsHeader.addElement("*");
        rentsHeader.addElement("*");
	 
        rentsPanel.getTable().addMouseListener(new MouseAdapter() {
        
                                              public void mouseClicked(MouseEvent e) {
                                                  if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                                                      loadObjectSelected();    
                                                      btnEdit.setEnabled(true);
                                                  } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
                                                      loadMgmt(false);
                                                  }
                                              }
        
                                          }
        );
        String params = "'"+ tfEmpresa.getString() +"','" + tfResponsable.getString() + "','" + tfPredio.getString() +"'";
        rentsPanel.setParams("taxes.getAllRents", params, rentsHeader);
    }

    
    private void loadObjectSelected(){
	if (!dataRow.isEmpty()){
            rent = new Rent();
            rent.setIdempresa(Integer.parseInt("" + dataRow.elementAt(0)));
            rent.retrieveData();
	}
    }
    
    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void loadMgmt(boolean _addAction){
	if (_addAction){
            rent = new Rent();
	}
        rentsMgmt = new RentsMgmt();
        rentsMgmt.setRent(rent);
        rentsMgmt.setParentList(this);

	ExtendedInternalFrame rentsMgmtContainer = new ExtendedInternalFrame("Agregar/Modificar");
	rentsMgmtContainer.setCentralPanel(rentsMgmt);
	rentsMgmtContainer.show();
    }
    
    private void btnAdd_actionPerformed(ActionEvent e) {
	loadMgmt(true);
    }

    private void btnEdit_actionPerformed(ActionEvent e) {
	loadMgmt(false);
    }
    
    private void controlBotones(){
	btnEdit.setEnabled(false);
    }
}
