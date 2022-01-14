package org.digitall.apps.taxes.interfases.cadastraladmin;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.apps.taxes.classes.InterestedByYear;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.grid.TableTransferHandler;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.environment.Environment;

public class InterestedByYearList extends BasicPrimitivePanel {

    private BasicPanel contentPanel = new BasicPanel();
    private BasicPanel searchPanel = new BasicPanel();
    private int[] sizeColumnList = {50, 278, 75};
    private Vector interestedByYearHeader = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel interestedByYearPanel = new GridPanel(30, sizeColumnList, "Impuestos", dataRow);
    
    private FindButton btnFind = new FindButton();
    private AddButton btnAdd = new AddButton();
    private ModifyButton btnEdit = new ModifyButton();
    

    private CBInput cbYears = new CBInput(0,"FileYear",false);
    private CBInput cbImpuesto = new CBInput(0,"TaxesType",false);
    
    private InterestedByYearMgmt interestedByYearMgmt;
    private InterestedByYear interestedByYear;

    public InterestedByYearList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout(null);
	this.setSize(new Dimension(460, 361));
	this.setPreferredSize(new Dimension(710, 515));
	interestedByYearPanel.setBounds(new Rectangle(5, 55, 450, 260));
	contentPanel.setBounds(new Rectangle(5, 5, 700, 500));
	contentPanel.setLayout(null);
	contentPanel.setSize(new Dimension(700, 515));
	searchPanel.setBounds(new Rectangle(5, 10, 495, 45));
	searchPanel.setLayout(null);
	btnFind.setBounds(new Rectangle(380, 15, 20, 20));
	btnFind.setSize(new Dimension(20, 20));
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
	searchPanel.add(cbImpuesto, null);
	searchPanel.add(cbYears, null);
	searchPanel.add(btnFind, null);
	btnEdit.setBounds(new Rectangle(610, 525, 40, 25));
	btnEdit.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnEdit_actionPerformed(e);
			       }

			   }
	);
	cbYears.setBounds(new Rectangle(5, 0, 100, 35));
	cbImpuesto.setBounds(new Rectangle(135, 0, 220, 35));
	contentPanel.add(interestedByYearPanel, null);
	contentPanel.add(searchPanel, null);
	this.add(contentPanel, null);
	this.addButton(btnEdit);
	this.addButton(btnAdd);
	interestedByYearPanel.getTable().setDragEnabled(true);
	interestedByYearPanel.getTable().setTransferHandler(new TableTransferHandler());
	cbYears.autoSize();
	cbImpuesto.autoSize();
	setCadastralHeader();
	loadComboModels();
    }
    private void loadComboModels(){
	int actualYear = Integer.parseInt("0" + Environment.currentYear);
	int cont = 0;
	for (int i = 1974 ; i <= (actualYear + 1) ; i++)  {
	    cont++;
	    cbYears.getCombo().addItem(i,cont);
	}
	cbYears.setSelectedID(cont - 1);
	
	cbImpuesto.getCombo().addItem("Tasa General de Servicios",1);
	cbImpuesto.getCombo().addItem("Impuesto Inmobiliario",2);
	cbImpuesto.getCombo().addItem("Impuesto Automotor",3);
	cbImpuesto.setSelectedID(1);
    }

    
    private void setCadastralHeader() {
	interestedByYearHeader.removeAllElements();
	interestedByYearHeader.addElement(Environment.lang.getProperty("FileYear"));
	interestedByYearHeader.addElement("*");
	interestedByYearHeader.addElement(Environment.lang.getProperty("TaxesType"));
	interestedByYearHeader.addElement("*");
	interestedByYearHeader.addElement("*");
	interestedByYearHeader.addElement(Environment.lang.getProperty("YearTaxes"));
	interestedByYearHeader.addElement("*");
	
	interestedByYearPanel.getTable().addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        loadObjectSelected();
			if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
			    
			} else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
			    loadMgmt(false);
			}
		    }
		});
		
	String params = "0,0" ;
	interestedByYearPanel.setParams("impuestos.getAllInteresesxAnio", params, interestedByYearHeader);
    }
    
    public void refresh() {
	String params = ""+ cbYears.getSelectedItem() +","+ cbImpuesto.getSelectedValue();
	interestedByYearPanel.refresh(params);
    }
    
    private void loadObjectSelected(){
	if (!dataRow.isEmpty()){
	    interestedByYear = new InterestedByYear();
	    interestedByYear.setAnio(Integer.parseInt("" + dataRow.elementAt(0)));
	    interestedByYear.setIdtipoimpuesto(Integer.parseInt("" + dataRow.elementAt(1)));
	    interestedByYear.setTipoImpuesto("" + dataRow.elementAt(2));
	    interestedByYear.setTasaanual(Double.parseDouble("0" + dataRow.elementAt(3)));
	    interestedByYear.setTasadiaria(Double.parseDouble("0" + dataRow.elementAt(4)));
	    interestedByYear.setNuevo(false);
	    
	}
    }
    
    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void loadMgmt(boolean _addAction){
	if (_addAction){
	    interestedByYear = new InterestedByYear();
	}
	interestedByYearMgmt = new InterestedByYearMgmt();
	interestedByYearMgmt.setInterestedByYearMgmt(interestedByYear);
	interestedByYearMgmt.setParentList(this);
	
	ExtendedInternalFrame interestedByYearMgmtContainer = new ExtendedInternalFrame("Agregar/Modificar Tasa Anual");
	interestedByYearMgmtContainer.setCentralPanel(interestedByYearMgmt);
	interestedByYearMgmtContainer.show();
    }
    
    private void btnAdd_actionPerformed(ActionEvent e) {
	loadMgmt(true);
    }

    private void btnEdit_actionPerformed(ActionEvent e) {
	loadMgmt(false);
    }
   
}
