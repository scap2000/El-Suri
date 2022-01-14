package org.digitall.apps.cashflow.interfaces.accountingentry;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.apps.cashflow.classes.AccountsByModel;
import org.digitall.apps.cashflow.classes.BookKeppingEntriesModels;
import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;


public class BookKeepingEntryModelsList extends BasicPrimitivePanel {

    private int[] sizeColumnList = { 60, 220, 300 };
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Cuentas", dataRow);
    private BasicPanel dataPanel = new BasicPanel();
    private CostsCentre costsCentre;
    
    private AddButton btnNewModel = new AddButton();
    private DeleteButton btnDelete = new DeleteButton();
    
    private ModifyButton btnModifyModel = new ModifyButton();
    private AcceptButton btnAddBookKeepingEntryByModel = new AcceptButton();
    
    private CBInput cbModels = new CBInput(0,"ModelAndType",false);
    private CBInput cbAccounts = new CBInput(0,"Accounts2",false);
    private AssignButton btnAddAccount = new AssignButton(true);
    private AccountsByModel accountsByModel;
    private BookKeppingEntriesModels model;
    private BookKeepingEntryModelsMgmt modelsMgmt;
    private TFInput tfFindAccounts = new TFInput(DataTypes.STRING,"FindAccounting",false);

    public BookKeepingEntryModelsList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout(null);
	this.setSize(new Dimension(578, 410));
	this.setPreferredSize(new Dimension(738, 491));
	dataPanel.setLayout(null);
	dataPanel.setPreferredSize(new Dimension(1, 100));
        btnNewModel.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnNewModel_actionPerformed(e);
			      }

			  }
	);
	
        cbModels.setBounds(new Rectangle(10, 20, 495, 35));
        cbAccounts.setBounds(new Rectangle(115, 60, 390, 35));
        btnAddAccount.setBounds(new Rectangle(515, 75, 20, 20));
	btnModifyModel.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnModifyModel_actionPerformed(e);
				 }

			     }
	);
        dataPanel.add(tfFindAccounts, null);
        dataPanel.add(cbModels, null);
        dataPanel.add(cbAccounts, null);
        dataPanel.add(btnModifyModel, null);
        dataPanel.add(btnNewModel,null);
        dataPanel.add(btnAddAccount, null);
        this.add(dataPanel, BorderLayout.NORTH);
	this.add(listPanel, BorderLayout.CENTER);
	btnDelete.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnDelete_actionPerformed(e);
				 }

			     }
	);
        
        btnAddAccount.addActionListener(new ActionListener() {

                                 public void actionPerformed(ActionEvent e) {
                                     btnAddAccount_actionPerformed(e);
                                 }

                             }
        );
        
	addButton(btnDelete);
	
	setHeaderList();
	btnNewModel.setToolTipText("Agregar un nuevo Modelo de Asiento Contable");
        btnDelete.setToolTipText("<html><center><u>Borrar la Cuenta del Modelo</u>");
        btnNewModel.setBounds(new Rectangle(515, 35, 20, 20));
	btnModifyModel.setToolTipText("Modificar Tipo y nombre del Modelo seleccionado");
        btnAddAccount.setToolTipText("<html><center><u>Agregar una Cuenta al Modelo seleccionado</u>");
        tfFindAccounts.setBounds(new Rectangle(10, 60, 95, 35));
        btnModifyModel.setEnabled(false);
        btnModifyModel.setBounds(new Rectangle(545, 35, 20, 20));
        btnDelete.setEnabled(false);
        cbModels.addItemListener(new ItemListener() {

                                public void itemStateChanged(ItemEvent evt) {
                                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                                        refresh();
                                    }
                                }

                            }
        );
        btnAddBookKeepingEntryByModel.setToolTipText("<html><center><u>Se abrirá la ventana para la carga de un nuevo<br>Asiento a través de Modelos</u>");
        btnAddBookKeepingEntryByModel.setText("Nuevo Asiento\nmediante Modelos");
        btnAddBookKeepingEntryByModel.addActionListener(new ActionListener() {

                                 public void actionPerformed(ActionEvent e) {
                                     btnAddBookKeepingEntryByModel_actionPerformed(e);
                                 }

                             }
        );
        cbModels.autoSize();
        cbAccounts.autoSize();
        loadComboModels();
        loadComboAccounts();
        tfFindAccounts.getTextField().addKeyListener(new KeyAdapter() {

                public void keyTyped(KeyEvent e) {
                    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                        loadComboAccounts();
                    }
                }
            }
        );
        dataPanel.setBorder(BorderPanel.getBorderPanel("Modelos de Asientos Contables"));
    }
    
    public void loadComboModels() {
        cbModels.loadJCombo("accounting.getAllModels","-1");
        if (!cbModels.getSelectedValue().toString().equals("-1")) {
            refresh();
            btnModifyModel.setEnabled(true);
            btnAddAccount.setEnabled(true);
        } else {
            btnModifyModel.setEnabled(false);
            btnAddAccount.setEnabled(false);
        }
    }

    private void loadComboAccounts() {
        String param = "-1,'" + tfFindAccounts.getString() + "'";
        cbAccounts.loadJCombo(LibSQL.exFunction("accounting.getAllAccountsForBookKeepingEntry", param));
        //cbAccounts.loadJCombo("accounting.getAllAccounts","");
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Seleccione un Modelo de Asiento para trabajar");
        getParentInternalFrame().getGeneralButtons().addButton(btnAddBookKeepingEntryByModel);
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Code"));
	headerList.addElement(Environment.lang.getProperty("Name"));
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement(Environment.lang.getProperty("Description"));
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent me) {
					       //loadCostCentre();
					       if (me.getButton() == MouseEvent.BUTTON3 && me.getClickCount() == 2) {
						   /*int index = listPanel.getTable().rowAtPoint(me.getPoint());
						   listPanel.getTable().getSelectionModel().setSelectionInterval(index, index);*/
						   //loadCCToolBar();
					       } else if (me.getButton() == MouseEvent.BUTTON1 && me.getClickCount() == 1) {
						   btnDelete.setEnabled(true);
					       }
					   }

				       }
	);
	listPanel.setParams("accounting.getAllAccountsByModel", "-1", headerList);
    }

    public void refresh() {
	String params = ""+ cbModels.getSelectedValue();
        listPanel.refresh(params);
        btnDelete.setEnabled(false);
        tfFindAccounts.setValue("");
        loadComboAccounts();
    }

    private void loadCostCentre() {
	if (!dataRow.isEmpty()) {
	    costsCentre = new CostsCentre(Integer.parseInt(dataRow.elementAt(0).toString()));
	    costsCentre.setCode(dataRow.elementAt(1).toString());
	    costsCentre.setName(dataRow.elementAt(2).toString());
	    costsCentre.setDescription(dataRow.elementAt(3).toString());
	    costsCentre.setInitialAmount(Double.parseDouble(dataRow.elementAt(4).toString()));
	    costsCentre.setSpentAmount(Double.parseDouble(dataRow.elementAt(5).toString()));
	    costsCentre.setSpentAmountP(Double.parseDouble(dataRow.elementAt(6).toString()));
	    costsCentre.setAvailableAmount(Double.parseDouble(dataRow.elementAt(7).toString()));
	    costsCentre.setModifiedAmount(Double.parseDouble(dataRow.elementAt(8).toString()));
	    costsCentre.setIsProvisionOrder(dataRow.elementAt(11).toString().equalsIgnoreCase("SI"));
	    btnModifyModel.setEnabled(true);
	    btnDelete.setEnabled(true);
	    btnDelete.setToolTipText("<html><center><u>Borrar la Cuenta del Modelo: </u><br>" + cbModels.getSelectedItem() + "</center></html>");
	    btnModifyModel.setToolTipText("<html><center><u>Modificar el Modelo: </u><br>" + cbModels.getSelectedItem() + "</center></html>");
	} else {
	    btnDelete.setToolTipText("Borrar la Cuenta del Modelo seleccionado");
	    btnModifyModel.setToolTipText("Modificar el Modelo seleccionado");
	    btnModifyModel.setEnabled(false);
	    btnDelete.setEnabled(false);
	}
    }

    private void btnDelete_actionPerformed(ActionEvent e) {
        Vector selecteds = listPanel.getSelectedsValuesAt(1);
	if (selecteds.size() > 0) {
	    for (int i = 0; i < selecteds.size(); i++) {
		if (LibSQL.getInt("accounting.delAccountByModel", ""+ cbModels.getSelectedValue() +"," + selecteds.elementAt(i)) != 1 ) {
		    Advisor.messageBox("Error al borrar la Cuenta " + listPanel.getSelectedsValuesInTableAt(2), "Aviso");
		}
	    }
	    refresh(); 
	} else {
	    Advisor.messageBox("Debe tildar una o más Cuentas para borrar", "Aviso");
	}
    }

    private void btnModifyModel_actionPerformed(ActionEvent e) {
        modelsMgmt = new BookKeepingEntryModelsMgmt();
        model = new BookKeppingEntriesModels();
        model.setIdmodel(Integer.parseInt(cbModels.getSelectedValue().toString()));
        model.setName(cbModels.getSelectedItem().toString());
        model.setIdCashMovementType(Integer.parseInt(cbModels.getSelectedValueRef().toString()));
        modelsMgmt.setModel(model);
        modelsMgmt.setParentMain(this);
        
        ExtendedInternalFrame modelsMgmtContainer = new ExtendedInternalFrame("Modificar Modelo de Asiento");
        modelsMgmtContainer.setCentralPanel(modelsMgmt);
        modelsMgmtContainer.show();
    }

    private void btnNewModel_actionPerformed(ActionEvent e) {
        modelsMgmt = new BookKeepingEntryModelsMgmt();
        model = new BookKeppingEntriesModels();
        modelsMgmt.setModel(model);
        modelsMgmt.setParentMain(this);
        
        ExtendedInternalFrame modelsMgmtContainer = new ExtendedInternalFrame("Agregar Modelo de Asiento");
        modelsMgmtContainer.setCentralPanel(modelsMgmt);
        modelsMgmtContainer.show();
    }

    private void btnAddAccount_actionPerformed(ActionEvent e) {
        if (Advisor.question("Pregunta","Esta seguro de agregar la cuenta "+ cbAccounts.getSelectedItem() +"\nal modelo "+ cbModels.getSelectedItem() +"?")){
            accountsByModel = new AccountsByModel();
            accountsByModel.setIdModel(Integer.parseInt(cbModels.getSelectedValue().toString()));
            accountsByModel.setIdaccount(Integer.parseInt(cbAccounts.getSelectedValue().toString()));
            if (accountsByModel.saveData() > 0) {
                refresh();
            }
        }
    }
    
    private void btnAddBookKeepingEntryByModel_actionPerformed(ActionEvent e) {
        if (cbModels.getSelectedIndex() != -1)  {
            BookKeepingEntryByModelMgmt bookKeepingEntryByModelMgmt = new BookKeepingEntryByModelMgmt();
            ExtendedInternalFrame bookKeepingEntryByModelMgmtContainer = new ExtendedInternalFrame("Nuevo Asiento mediante Modelos");
            bookKeepingEntryByModelMgmtContainer.setCentralPanel(bookKeepingEntryByModelMgmt);
            bookKeepingEntryByModelMgmtContainer.setClosable(true);
            bookKeepingEntryByModelMgmtContainer.show();
            getParentInternalFrame().closeWindow();
        }
    }
    
}
