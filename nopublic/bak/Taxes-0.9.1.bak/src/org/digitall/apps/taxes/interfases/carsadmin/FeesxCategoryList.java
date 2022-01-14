package org.digitall.apps.taxes.interfases.carsadmin;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.apps.taxes.classes.FeesxCategory;
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

public class FeesxCategoryList extends BasicPrimitivePanel {

    private BasicPanel contentPanel = new BasicPanel();
    private BasicPanel searchPanel = new BasicPanel();
    private TFInput tfYear = new TFInput(DataTypes.INTEGER, "FileYear",false);
    private TFInput tfCategory = new TFInput(DataTypes.STRING, "Category",false);
    
    private int[] sizeColumnList = {46, 132, 102, 88};
    private Vector FeesxCategoryHeader = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel FeesxCategoryPanel = new GridPanel(50000, sizeColumnList, "Listado de Cuotas por Categorias", dataRow);
    
    private FindButton btnFind = new FindButton();
    private AddButton btnAdd = new AddButton();
    private ModifyButton btnEdit = new ModifyButton();
    
    private FeesxCategory feesxCategory;
    private FeesxCategoryMgmt feesxCategoryMgmt;
    private CarsList parentList;

    public FeesxCategoryList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout(null);
	this.setSize(new Dimension(440, 388));
	this.setPreferredSize(new Dimension(710, 515));
	tfYear.setBounds(new Rectangle(5, 0, 105, 35));
	FeesxCategoryPanel.setBounds(new Rectangle(5, 60, 430, 285));
	contentPanel.setBounds(new Rectangle(5, 5, 700, 500));
	contentPanel.setLayout(null);
	contentPanel.setSize(new Dimension(700, 515));
	searchPanel.setBounds(new Rectangle(5, 10, 430, 50));
	searchPanel.setLayout(null);
	tfCategory.setBounds(new Rectangle(140, 0, 145, 35));
	btnFind.setBounds(new Rectangle(300, 15, 20, 20));
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
	searchPanel.add(tfCategory, null);
	searchPanel.add(tfYear, null);
	searchPanel.add(btnFind, null);
	btnEdit.setBounds(new Rectangle(610, 525, 40, 25));
	btnEdit.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnEdit_actionPerformed(e);
			       }

			   }
	);
	contentPanel.add(FeesxCategoryPanel, null);
	contentPanel.add(searchPanel, null);
	this.add(contentPanel, null);
	this.addButton(btnEdit);
	this.addButton(btnAdd);
	FeesxCategoryPanel.getTable().setDragEnabled(true);
	FeesxCategoryPanel.getTable().setTransferHandler(new TableTransferHandler());
	tfYear.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});
	tfCategory.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});
	setCadastralHeader();
    }

    private void setCadastralHeader() {
	FeesxCategoryHeader.removeAllElements();
	FeesxCategoryHeader.addElement(Environment.lang.getProperty("FileYear"));
	FeesxCategoryHeader.addElement("*");
	FeesxCategoryHeader.addElement(Environment.lang.getProperty("Category"));
	FeesxCategoryHeader.addElement(Environment.lang.getProperty("MonthlyFee"));
	FeesxCategoryHeader.addElement(Environment.lang.getProperty("AnnualFee"));
	FeesxCategoryHeader.addElement("*");
	
	FeesxCategoryPanel.getTable().addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
			    loadObjectSelected();    
			} else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
			    loadMgmt(false);
			}
		    }
		});
		
	String params = "0,''," ;
	FeesxCategoryPanel.setParams("impuestos.getAllCuotasxCategoria", params, FeesxCategoryHeader);
    }
    
    public void refresh() {
	String params = "0"+ tfYear.getString() +",'"+ tfCategory.getString() +"'";
	FeesxCategoryPanel.refresh(params);
    }

    private void loadObjectSelected(){
	if (!dataRow.isEmpty()){
	    feesxCategory = new FeesxCategory();
	    feesxCategory.setAnio(Integer.parseInt("" + dataRow.elementAt(0)));
	    feesxCategory.setIdtipocategoria(Integer.parseInt("" + dataRow.elementAt(1)));
	    feesxCategory.setCategoria("" + dataRow.elementAt(2));
	    feesxCategory.setCuota(Double.parseDouble("0" + dataRow.elementAt(3)));
	    feesxCategory.setAnual(Double.parseDouble("0" + dataRow.elementAt(4)));
	    feesxCategory.setEstado("" + dataRow.elementAt(5));
	    feesxCategory.setNuevo(false);
	}
    }
    
    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void loadMgmt(boolean _addAction){
	if (_addAction){
	    feesxCategory = new FeesxCategory();
	}
	feesxCategoryMgmt = new FeesxCategoryMgmt();
	feesxCategoryMgmt.setFeesxCategory(feesxCategory);
	feesxCategoryMgmt.setParentList(this);
	
	ExtendedInternalFrame feesxCategoryMgmtContainer = new ExtendedInternalFrame("Agregar/Modificar");
	feesxCategoryMgmtContainer.setCentralPanel(feesxCategoryMgmt);
	feesxCategoryMgmtContainer.show();
    }
    
    private void btnAdd_actionPerformed(ActionEvent e) {
	loadMgmt(true);
    }

    private void btnEdit_actionPerformed(ActionEvent e) {
	loadMgmt(false);
    }

    public void setParentList(CarsList _parentList) {
	parentList = _parentList;
    }

}
