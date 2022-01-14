package org.digitall.common.cashflow.interfaces.account;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.cashflow.classes.BankAccount;
import org.digitall.common.cashflow.reports.account.CheckListReport;
import org.digitall.common.cashflow.classes.Check;
import org.digitall.common.cashflow.interfaces.account.ChecksMgmt;
import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CancelDataButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.buttons.ReportButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.sql.LibSQL;

public class ChecksList extends BasicPrimitivePanel {

    private int[] sizeColumnList = { 59, 122, 87, 88, 105, 105, 179, 124 };
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(50000, sizeColumnList, "Cheques", dataRow) {
	public void calculate() {
	}
    };
    private AddButton btnAdd = new AddButton();
    private Check selectedCheck;
    private BankAccount account;
    private ModifyButton btnModify = new ModifyButton();
    private CheckMgmt checkMgmt;
    private ChecksMgmt checksMgmt;
    private CloseButton btnClose = new CloseButton();
    private ReportButton btnReport = new ReportButton();
    private String title = "";
    private AssignButton btnDebitSelectedChecks = new AssignButton(true);
    private PrintButton btnChecksReport = new PrintButton();
    private CancelDataButton btnCancelCheck = new CancelDataButton();

    public ChecksList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(900, 455));
	this.setPreferredSize(new Dimension(900, 455));
	listPanel.setBounds(new Rectangle(5, 0, 890, 450));
	btnAdd.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAdd_actionPerformed(e);
			      }

			  }
	);
	btnModify.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnModify_actionPerformed(e);
				 }

			     }
	);
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	btnReport.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnReport_actionPerformed(e);
				 }

			     }
	);
	btnDebitSelectedChecks.addActionListener(new ActionListener() {

					      public void actionPerformed(ActionEvent e) {
						  debitSelectedChecks_actionPerformed(e);
					      }

					  }
	);
	addButton(btnClose);
	addButton(btnModify);
	addButton(btnAdd);
	//addButton(btnReport);
	addButton(btnDebitSelectedChecks);
	addButton(btnCancelCheck);
	addButton(btnChecksReport);
	btnChecksReport.setShowText(true);
	btnChecksReport.addActionListener(new ActionListener() {

				       public void actionPerformed(ActionEvent e) {
					   btnChecksReport_actionPerformed(e);
				       }

				   }
	);
	btnCancelCheck.addActionListener(new ActionListener() {

				      public void actionPerformed(ActionEvent e) {
					  btnCancelCheck_actionPerformed(e);
				      }

				  }
	);
	this.add(listPanel, null);
	setHeaderList();
	btnModify.setEnabled(false);
	btnChecksReport.setToolTipText("<html><center>Listado de cheques emitidos</center></html>");
	btnDebitSelectedChecks.setToolTipText("<html><center>Debitar el/los cheque(s) seleccionado(s) (Deben estar tildados)</center></html>");
	btnAdd.setToolTipText("<html><center>Agregar nueva chequera</center></html>");
	btnModify.setToolTipText("<html><center>Ver/Modificar los datos del cheque seleccionado</center></html>");
	btnCancelCheck.setToolTipText("<html><center>Anular el cheque seleccionado</center></html>");
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Sólo se puede emitir el cheque subsiguiente al último emitido");
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("Letra");
	headerList.addElement("Nº");
	headerList.addElement("*");
	headerList.addElement("Fecha");
	headerList.addElement("Fecha Vto.");
	headerList.addElement("($) Importe");
	headerList.addElement("*");
	headerList.addElement("Tipo");
	headerList.addElement("*");
	headerList.addElement("Destinatario");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("Estado");
	headerList.addElement("*");
	headerList.addElement("*");
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent me) {
					       loadObject();
					       selected(me);
					       //btnModify.setEnabled(selectedCheck.getIdCheckStatus() == Check.NOT_ISSUED);
					       if (me.getButton() == MouseEvent.BUTTON3 && me.getClickCount() == 2) {
						   int index = listPanel.getTable().rowAtPoint(me.getPoint());
						   listPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
					       } else if (me.getButton() == MouseEvent.BUTTON1 && me.getClickCount() == 2) {
						   loadCheckMgmt();
					       }
					   }

				       }
	);
	listPanel.setParams("cashflow.getAllChecksByBankAccount", "-1", headerList);
	btnCancelCheck.setEnabled(false);
    }
    
    private void selected(MouseEvent e){
	int filaSeleccionada = listPanel.getTable().rowAtPoint(e.getPoint());
	listPanel.selectAllItems(false);
	listPanel.getTable().setValueAt(true,filaSeleccionada,0);
	listPanel.getTable().getSelectionModel().setSelectionInterval(filaSeleccionada,filaSeleccionada);
    }

    public void refresh() {
	String params = "" + account.getIDBankAccount();
	listPanel.refresh(params);
	btnModify.setEnabled(false);
	btnCancelCheck.setEnabled(false);
	btnAdd.setToolTipText("<html><center>Agregar nueva chequera</center></html>");
	btnModify.setToolTipText("<html><center>Ver/Modificar los datos del cheque seleccionado</center></html>");
	btnCancelCheck.setToolTipText("<html><center>Anular el cheque seleccionado</center></html>");
    }

    private void loadObject() {
	if (!dataRow.isEmpty()) {
	    selectedCheck = new Check();
	    selectedCheck.setIdCheck(Integer.parseInt("" + dataRow.elementAt(0)));
	    selectedCheck.setLetter("" + dataRow.elementAt(1));
	    selectedCheck.setNumber(Integer.parseInt("" + dataRow.elementAt(2)));
	    selectedCheck.setAccount(new BankAccount(Integer.parseInt("" + dataRow.elementAt(3))));
	    selectedCheck.setDate(Proced.setFormatDate("" + dataRow.elementAt(4), false));
	    selectedCheck.setExpiredDate(Proced.setFormatDate("" + dataRow.elementAt(5), false));
	    selectedCheck.setAmount(Double.parseDouble("" + dataRow.elementAt(6)));
	    selectedCheck.setIdCheckType(Integer.parseInt("" + dataRow.elementAt(7)));
	    selectedCheck.setIdEntity(Integer.parseInt("" + dataRow.elementAt(9)));
	    selectedCheck.setIdEntitytype(Integer.parseInt("" + dataRow.elementAt(11)));
	    selectedCheck.setIdCheckStatus(Integer.parseInt("" + dataRow.elementAt(13)));
	    selectedCheck.setDescription("" + dataRow.elementAt(15));
	    if (selectedCheck.getIdCheckStatus() != 0 && selectedCheck.getIdCheckStatus() != 1 && selectedCheck.getIdCheckStatus() != 2) {
		//idCheckStatus = 0 --> No emitido
		//idCheckStatus = 1 --> Debitado
		//idCheckStatus = 2 --> Anulado
		btnCancelCheck.setEnabled(true);
		btnCancelCheck.setToolTipText("<html><center>Anular el cheque " + selectedCheck.getLetter() + "." + selectedCheck.getNumber() + "</center></html>");
	    } else {
		btnCancelCheck.setEnabled(false);
		btnCancelCheck.setToolTipText("<html><center>No es posible Anular el cheque " + selectedCheck.getLetter() + "." + selectedCheck.getNumber() + "</center></html>");
	    }
	    btnModify.setEnabled(true);
	    btnModify.setToolTipText("<html><center><u>Ver/Modificar cheque</u><br>" + selectedCheck.getLetter() + "." + selectedCheck.getNumber() + "</center></html>");
	} else {
	    btnModify.setToolTipText("<html><center>Ver/Modificar los datos del cheque seleccionado</center></html>");
	}
	btnDebitSelectedChecks.setToolTipText("<html><center>Debitar el/los cheque(s) seleccionado(s) (Deben estar tildados)</center></html>");
    }

    private void loadCheckMgmt() {
	if (validateNextCheck(selectedCheck)) {
	    if (selectedCheck.getIdCheckStatus() != Check.NOT_ISSUED) {
		Advisor.messageBox("El cheque " + selectedCheck.getLetter() + "." + selectedCheck.getNumber() + " no se puede modificar una vez emitido", "");
	    }
	    checkMgmt = new CheckMgmt();
	    checkMgmt.setEntityName("" + dataRow.elementAt(10));
	    checkMgmt.setCheck(selectedCheck);
	    checkMgmt.setParentList(this);
	    ExtendedInternalFrame checksMgmtContainer = new ExtendedInternalFrame("Ver/Modificar los datos del Cheque Nº " + dataRow.elementAt(2).toString());
	    checksMgmtContainer.setCentralPanel(checkMgmt);
	    checksMgmtContainer.show();
	} else {
	    Advisor.messageBox("El cheque " + selectedCheck.getLetter() + "." + selectedCheck.getNumber() + " no se puede dar de alta hasta que no se hayan emitido los anteriores", "");
	}
    }

    private boolean validateNextCheck(Check _check) {
	return LibSQL.getBoolean("cashflow.getCheckValidity", String.valueOf(_check.getIdCheck()));
    }

    public void setAccount(BankAccount account, String _title) {
	this.account = account;
	title = _title;
	refresh();
    }

    private void btnAdd_actionPerformed(ActionEvent e) {
	checksMgmt = new ChecksMgmt();
	checksMgmt.setCheck(new Check(account));
	checksMgmt.setParentList(this);
	ExtendedInternalFrame checksMgmtContainer = new ExtendedInternalFrame("Generar nueva chequera");
	checksMgmtContainer.setCentralPanel(checksMgmt);
	checksMgmtContainer.show();
    }

    private void btnModify_actionPerformed(ActionEvent e) {
	loadCheckMgmt();
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    private void btnReport_actionPerformed(ActionEvent e) {
	int rowsQty = listPanel.getTable().getRowCount();
	if (rowsQty != 0) {
	    new CheckListReport(account, title);
	} else {
	    Advisor.messageBox("El reporte no se generó\nporque no hay datos en la listPanel", "Aviso");
	}
    }

    private void debitSelectedChecks_actionPerformed(ActionEvent e) {
	Vector selectedChecks = listPanel.getSelectedsVector();
	if (selectedChecks.size() > 0) {
	    if (Advisor.question("Debitar cheque(s)", "¿Está seguro?")) {
		for (int i = 0; i < selectedChecks.size(); i++) {
		    Vector _check = (Vector)selectedChecks.elementAt(i);
		    Check check = new Check(Integer.parseInt(_check.elementAt(0).toString()));
		    check.setIdCheckStatus(Integer.parseInt(_check.elementAt(13).toString()));
		    check.setLetter(_check.elementAt(1).toString());
		    check.setNumber(Integer.parseInt(_check.elementAt(2).toString()));
		    if (check.getIdCheckStatus() != Check.DEBITED && check.getIdCheckStatus() != Check.CANCELED) {
			check.updateStatus(Check.DEBITED);
		    } else {
			Advisor.messageBox("El cheque " + check.getLetter() + check.getNumber() + " no se puede debitar", "Aviso");
		    }
		}
		refresh();
	    }
	} else {
	    Advisor.messageBox("Debe tildar el o los cheques que desea debitar", "Aviso");
	}
    }

    private void btnChecksReport_actionPerformed(ActionEvent e) {
	BasicReport report = new BasicReport(ChecksList.class.getResource("xml/checksList.xml"));
	report.addTableModel("cashflow.getAllChecksNotPaid", "" + account.getIDBankAccount());
	report.doReport();
    }
    
    private void btnCancelCheck_actionPerformed(ActionEvent e) {
	Vector selectedChecks = listPanel.getSelectedsVector();
	if (selectedChecks.size() == 1) {
	    if (selectedCheck.getIdEntity() > 0) {
		if (Advisor.question("Anular cheque", "¿Está seguro de anular el cheque " + selectedCheck.getLetter() + selectedCheck.getNumber() + " por un monto de $ " + selectedCheck.getAmount() + "?")) {
		    if (LibSQL.getInt("cashflow.cancelCheck", selectedCheck.getIdCheck()) > 0) {
			Advisor.messageBox("Cheque anulado con éxito", "Aviso");
			refresh();
		    } else {
			Advisor.messageBox("Ocurrió un error al anular el cheque", "Error");
		    }
		}
	    } else {
		Advisor.messageBox("El cheque no está vinculado a ninguna entidad", "Error");
	    }
	} else {
	    Advisor.messageBox("Debe tildar solo un cheque para poder Anularlo", "Error");
	}
    }
    
}
