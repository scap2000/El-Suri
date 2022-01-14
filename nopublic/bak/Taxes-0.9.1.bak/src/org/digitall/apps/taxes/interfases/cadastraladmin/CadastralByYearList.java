package org.digitall.apps.taxes.interfases.cadastraladmin;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.apps.taxes.classes.Cadastral;
import org.digitall.apps.taxes.classes.CadastralByYear;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.grid.TableTransferHandler;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;

public class CadastralByYearList extends BasicPrimitivePanel {

    private BasicPanel contentPanel = new BasicPanel();
    private BasicPanel searchPanel = new BasicPanel();
    private TFInput tfPersonName = new TFInput(DataTypes.STRING, "Name",false);
    private TFInput tfIdentificationNumber = new TFInput(DataTypes.INTEGER, "DNI",false);
    private TFInput tfCadastral = new TFInput(DataTypes.INTEGER, "Cadastral",false);
    
    private int[] sizeColumnList = { 75 , 230 , 105 , 125 , 100 };
    private Vector cadastralByYearHeader = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel cadastralByYearPanel = new GridPanel(30, sizeColumnList, "Listado de Catastros", dataRow);
    
    private FindButton btnFind = new FindButton();
    private CloseButton btnClose = new CloseButton();
    private AddButton btnAdd = new AddButton();
    private ModifyButton btnEdit = new ModifyButton();
    
    private Cadastral cadastral;
    
    private CadastralByYear cadastralByYear;
    private CadastralByYearMgmt cadastralByYearMgmt;
    
    public CadastralByYearList() {
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
	tfPersonName.setBounds(new Rectangle(50, 10, 170, 35));
	cadastralByYearPanel.setBounds(new Rectangle(5, 75, 700, 270));
	contentPanel.setBounds(new Rectangle(5, 5, 700, 500));
	contentPanel.setLayout(null);
	contentPanel.setSize(new Dimension(700, 515));
	searchPanel.setBounds(new Rectangle(5, 10, 690, 50));
	searchPanel.setLayout(null);
	tfIdentificationNumber.setBounds(new Rectangle(270, 10, 145, 35));
	tfCadastral.setBounds(new Rectangle(475, 10, 140, 35));
	btnFind.setBounds(new Rectangle(660, 25, 30, 25));
	btnFind.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      btnFind_actionPerformed(e);
				  }

			      }
	);
	btnClose.setBounds(new Rectangle(660, 525, 40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
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
	
	searchPanel.add(tfCadastral, null);
	searchPanel.add(tfIdentificationNumber, null);
	searchPanel.add(tfPersonName, null);
	searchPanel.add(btnFind, null);
	btnEdit.setBounds(new Rectangle(610, 525, 40, 25));
	btnEdit.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnEdit_actionPerformed(e);
			       }

			   }
	);
	contentPanel.add(cadastralByYearPanel, null);
	contentPanel.add(searchPanel, null);
	this.add(contentPanel, null);
	this.addButton(btnClose);
	this.addButton(btnEdit);
	this.addButton(btnAdd);
	cadastralByYearPanel.getTable().setDragEnabled(true);
	cadastralByYearPanel.getTable().setTransferHandler(new TableTransferHandler());
	tfPersonName.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});
	
	tfIdentificationNumber.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});

	tfCadastral.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});		
	setCadastralHeader();
    }
    
    private void setCadastralHeader() {
	cadastralByYearHeader.removeAllElements();
	cadastralByYearHeader.addElement("*");
	cadastralByYearHeader.addElement("*");
	cadastralByYearHeader.addElement(Environment.lang.getProperty("Cadastral"));
	cadastralByYearHeader.addElement(Environment.lang.getProperty("Titular"));
	cadastralByYearHeader.addElement(Environment.lang.getProperty("Date"));
	cadastralByYearHeader.addElement(Environment.lang.getProperty("TaxableValue"));
	cadastralByYearHeader.addElement(Environment.lang.getProperty("MetrosFrente"));
	cadastralByYearHeader.addElement("*");
	cadastralByYearHeader.addElement("*");
	cadastralByYearHeader.addElement("*");
	cadastralByYearHeader.addElement("*");
	cadastralByYearHeader.addElement("*");
	cadastralByYearHeader.addElement("*");
	
	
	cadastralByYearPanel.getTable().addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        loadObjectSelected();
			if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
			    
			} else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
			    loadMgmt(false);
			}
		    }
		});
		
	String params = "'',0,0" ;
	cadastralByYearPanel.setParams("impuestos.getAllCatastrosxAnio", params, cadastralByYearHeader);
    }
    
    public void refresh() {
	String params = "'"+ tfPersonName.getString() +"',0"+ tfIdentificationNumber.getString() +",0"+ tfCadastral.getString();
	cadastralByYearPanel.refresh(params);
    }
    
    private void loadObjectSelected(){
	if (!dataRow.isEmpty()){
	    cadastralByYear = new CadastralByYear();
	    cadastralByYear.setIdCatastroxanio(Integer.parseInt("" + dataRow.elementAt(0)));
	    cadastralByYear.setIdCatastro(Integer.parseInt("" + dataRow.elementAt(1)));
	    cadastralByYear.setCatastro(Integer.parseInt("" + dataRow.elementAt(2)));
	    cadastralByYear.setTitular("" + dataRow.elementAt(3));
	    cadastralByYear.setFecha("" + Proced.setFormatDate("" + dataRow.elementAt(4),false));
	    cadastralByYear.setVf(Double.parseDouble("0" + dataRow.elementAt(5)));
	    cadastralByYear.setMf(Double.parseDouble("0" + dataRow.elementAt(6)));
	}
    }
    
    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    private void loadMgmt(boolean _addAction){
	if (_addAction){
	    cadastral = new Cadastral();
	    cadastralByYear = new CadastralByYear();
	}
	
	cadastralByYearMgmt = new CadastralByYearMgmt();
	cadastralByYearMgmt.setCadastralByYear(cadastralByYear);
	cadastralByYearMgmt.setParentList(this);
	
	ExtendedInternalFrame cadastralMgmtContainer = new ExtendedInternalFrame("Agregar/Modificar");
	cadastralMgmtContainer.setCentralPanel(cadastralByYearMgmt);
	cadastralMgmtContainer.show();
    }
    
    private void btnAdd_actionPerformed(ActionEvent e) {
	loadMgmt(true);
    }

    private void btnEdit_actionPerformed(ActionEvent e) {
	loadMgmt(false);
    }
   
}
