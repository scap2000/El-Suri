/**
 LIMITACIÓN DE RESPONSABILIDAD - COPYRIGHT - [Español]
 ================================================================================
 El Suri - Entorno JAVA de Trabajo y Desarrollo para Gobierno Electrónico
 ================================================================================

 Información del Proyecto:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 por D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO es propiedad de
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) y
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los términos de la Licencia Pública General GNU publicada
 por la Fundación para el Software Libre, ya sea la versión 3
 de la Licencia, o (a su elección) cualquier versión posterior.

 Este programa se distribuye con la esperanza de que sea útil, pero
 SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita
 MERCANTIL o de APTITUD PARA UN PROPÓSITO DETERMINADO.
 Consulte los detalles de la Licencia Pública General GNU para obtener
 una información más detallada.

 Debería haber recibido una copia de la Licencia Pública General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
 El Suri - JAVA Management & Development Environment for Electronic Government
 =====================================================================================

 Project Info:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 by D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO is owned by
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) and
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/
/**
 * CashMovementsList.java
 *
 * */
package org.digitall.common.cashflow.interfaces.cashmovement;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;


import org.digitall.common.cashflow.classes.BookKeepingEntry;
import org.digitall.common.cashflow.classes.CashMovement;
import org.digitall.common.cashflow.interfaces.account.BankAccountsList;
import org.digitall.common.cashflow.interfaces.cashmovement.CashMovementTypesTree;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.EditButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class CashMovementsList  extends BasicPrimitivePanel {

    private BasicPanel findPanel = new BasicPanel("Buscar");
    private int[] sizeColumnList = { 64, 100, 184, 90, 100, 100 , 123};
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Notas", dataRow);
    
    private AddButton btnAdd = new AddButton();
    private FindButton btnFind = new FindButton();
    private AcceptButton btnCashMovementType = new AcceptButton();
    private PrintButton btnTotalReport = new PrintButton();
    private PrintButton btnMovementReport = new PrintButton();
    private DeleteButton btnDeleteNotes = new DeleteButton();
     
    private TFInput tfStartDate = new TFInput(DataTypes.DATE, "From", false);
    private TFInput tfEndDate = new TFInput(DataTypes.DATE, "To", false);
    private TFInput tfBeneficiary = new TFInput(DataTypes.STRING,"BeneficiarySubject",false);
    
    private CBInput cbCashMovementType = new CBInput(CachedCombo.CASHMOVEMENTTYPE, "NoteTypes");
    private CBInput cbCostsCentre = new CBInput(CachedCombo.COSTSCENTRE, "CostsCentre");
    
    private CashMovementsMgmt cashMovementsMgmt;
    private CashMovement selectedCashMovement;
    private BookKeepingEntry bookKeepingEntry;
    private String voucherNumber;
    //private AcceptButton btnExpType = new AcceptButton();
    private BasicPanel content = new BasicPanel();
    private String params = "";
    private String startDate = "";
    private String endDate = "";

    public CashMovementsList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public CashMovementsList(int _type) {
	try {
	    jbInit();
	    cbCashMovementType.setSelectedID(""+ _type);
	    refresh();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }


    private void jbInit() throws Exception {
	this.setSize(new Dimension(730, 425));
	this.setPreferredSize(new Dimension(730, 425));
	btnCashMovementType.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnCashMovementType_actionPerformed(e);
				 }

			     }
	);
	btnCashMovementType.setText("Tipos de\nMovimientos\nde Fondos");
	content.setLayout(null);
	tfBeneficiary.getTextField().addKeyListener(new KeyAdapter() {

	                                          public void keyTyped(KeyEvent e) {
	                                              if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
	                                                  refresh();
	                                              }
	                                          }

	                                      }
	);
        tfBeneficiary.setBounds(new Rectangle(15, 60, 190, 35));
        findPanel.setBounds(new Rectangle(5, 0, 720, 100));
	findPanel.setLayout(null);
	listPanel.setBounds(new Rectangle(5, 105, 720, 275));
	btnAdd.setBounds(new Rectangle(590, 455, 40, 25));
	btnAdd.setSize(new Dimension(40, 25));
	btnAdd.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAdd_actionPerformed(e);
			      }

			  }
	);
	tfStartDate.setBounds(new Rectangle(15, 20, 90, 35));
	cbCashMovementType.setBounds(new Rectangle(230, 20, 205, 35));
	tfEndDate.setBounds(new Rectangle(115, 20, 90, 35));
	btnFind.setBounds(new Rectangle(670, 30, 40, 25));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);
	cbCostsCentre.setBounds(new Rectangle(460, 20, 190, 35));
	cbCostsCentre.setGeneralItem(true);
	cbCashMovementType.setGeneralItem(true);
	btnTotalReport.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			    btnTotalReport_actionPerformed(e);
			}
		}
	);
        btnMovementReport.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            btnMovementReport_actionPerformed(e);
                        }
                }
        );
        btnDeleteNotes.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            btnDeleteNotes_actionPerformed(e);
                        }
                }
        );
        content.add(findPanel, null);
	content.add(listPanel, null);
	add(content, null);
        addButton(btnDeleteNotes);
	addButton(btnTotalReport);
        addButton(btnMovementReport);
        findPanel.add(tfBeneficiary, null);
        findPanel.add(cbCostsCentre, null);
        findPanel.add(btnFind, null);
        findPanel.add(tfEndDate, null);
        findPanel.add(cbCashMovementType, null);
        findPanel.add(tfStartDate, null);
        cbCashMovementType.autoSize();
	cbCostsCentre.autoSize();
	
	cbCostsCentre.getCombo().addItem("Todos", "0");
        cbCashMovementType.getCombo().addItem("Todos","0","-1");
	cbCostsCentre.setSelectedID("0");
        cbCashMovementType.setSelectedID("0");
	setHeaderList();
	btnTotalReport.setToolTipText("Listado de Notas");
        btnMovementReport.setToolTipText("Imprimir la Nota seleccionada");
	btnCashMovementType.setToolTipText("Abre la ventana de Tipos de Movimientos de Fondos");
        btnDeleteNotes.setToolTipText("Borrar la Nota seleccionada");
        btnMovementReport.setEnabled(false);
        btnDeleteNotes.setEnabled(false);
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	 getParentInternalFrame().setInfo("Puede listar las Notas por rango de fechas, tipo y/o Centros de Costos");
    }
    
    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
        headerList.addElement("Nº Nota");
        headerList.addElement("Tipo Nota");
	headerList.addElement(Environment.lang.getProperty("Concept"));
	headerList.addElement(Environment.lang.getProperty("Date"));
	headerList.addElement("Ingreso");
	headerList.addElement("Egreso");
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
        headerList.addElement("Beneficiario");
        headerList.addElement("*");
        
	listPanel.getTable().addMouseListener(new MouseAdapter(){
	    public void mouseClicked(MouseEvent me) {
		selected(me);
	        loadObject();
		/*if (me.getButton() == MouseEvent.BUTTON1 && me.getClickCount() == 2) {
		    
		} else if (me.getButton() == MouseEvent.BUTTON3 && me.getClickCount() == 2) {
		    /*int index = listPanel.getTable().rowAtPoint(me.getPoint());
		    listPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
		    loadDetail();
		}*/
	    }
	});
	
	listPanel.setParams("cashflow.getAllCashMovements", "'', '', 0, 0,-1,-1,''", headerList);
    }
    
    private void selected(MouseEvent e){
	int filaSeleccionada = listPanel.getTable().rowAtPoint(e.getPoint());
	listPanel.selectAllItems(false);
	listPanel.getTable().setValueAt(true,filaSeleccionada,0);
	listPanel.getTable().getSelectionModel().setSelectionInterval(filaSeleccionada,filaSeleccionada);
    }

    public void refresh() {
        loadParams();
	listPanel.refresh(params);
        btnMovementReport.setEnabled(false);
        btnDeleteNotes.setEnabled(false);
    }
    
    public void loadParams() {
        String[] values = cbCashMovementType.getSelectedValueRef().toString().split("#");
        
        if (tfStartDate.getString().equals(""))  {
            startDate = Environment.currentYear + "-01-01";
        } else {
            startDate = Proced.setFormatDate(tfStartDate.getString(), false);
        }
        if (tfEndDate.getString().equals(""))  {
            endDate = Environment.currentYear + "-12-31";
        } else {
            endDate = Proced.setFormatDate(tfEndDate.getString(), false);
        }
        
        if (values[0].toString().equals("-1"))  {
            params = "'"+ startDate +"','"+ endDate
                        +"',"+ cbCashMovementType.getSelectedValue() +","+ cbCostsCentre.getSelectedValue() +",-1,-1,'"+ tfBeneficiary.getString().trim() +"'" ;    
        } else {
            params = "'"+ startDate +"','"+ endDate
                        +"',"+ cbCashMovementType.getSelectedValue() +","+ cbCostsCentre.getSelectedValue() +","+ values[0] +","+ values[1] +",'"+ tfBeneficiary.getString().trim() +"'";
        }
    }

    private void loadObject(){
	if (!dataRow.isEmpty())	{
	    selectedCashMovement =  new CashMovement();
	    selectedCashMovement.setIdCashMovement(Integer.parseInt(""+ dataRow.elementAt(0)));
	    btnMovementReport.setEnabled(true);
            btnDeleteNotes.setEnabled(true);
	}
    }
    
    private void btnAdd_actionPerformed(ActionEvent e) {
	cashMovementsMgmt = new CashMovementsMgmt();
	cashMovementsMgmt.setParentList(this);
	
	ExtendedInternalFrame cashMovementsMgmtContainer = new ExtendedInternalFrame("Movimiento de Fondo");
	cashMovementsMgmtContainer.setCentralPanel(cashMovementsMgmt);
	cashMovementsMgmtContainer.show();  
    }
    
    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    private void btnCashMovementType_actionPerformed(ActionEvent e) {
	CashMovementTypesTree cashMovementTypesTree = new CashMovementTypesTree();
	
	ExtendedInternalFrame cashMovementTypesTreeContainer = new ExtendedInternalFrame("Tipos de Movimientos de Fondos");
	cashMovementTypesTreeContainer.setCentralPanel(cashMovementTypesTree);
	cashMovementTypesTreeContainer.show();
    }    
    
    private void btnAccount_actionPerformed(ActionEvent e) {
	BankAccountsList accountsList = new BankAccountsList();
	
	ExtendedInternalFrame accountsListContainer = new ExtendedInternalFrame("Cuentas Bancarias");
	accountsListContainer.setCentralPanel(accountsList);
	accountsListContainer.show();
    }

    private void btnTotalReport_actionPerformed(ActionEvent e) {
        loadParams();
	if (listPanel.getResultTotalQty() > 0) {
	    BasicReport report = new BasicReport(CashMovementsList.class.getResource("xml/cashmovementreport.xml"));
	    report.addTableModel("cashflow.getAllCashMovements", "" + params + ",0,0");
	    report.setProperty("from", "" + Proced.setFormatDate(startDate, true));
	    report.setProperty("to", "" + Proced.setFormatDate(endDate, true));
	    report.setProperty("cashmovementtype", "" + cbCashMovementType.getSelectedItem());
	    report.doReport();
	} else {
	    Advisor.messageBox("No hay resultados para imprimir", "Error");
	}
    }

    private void btnMovementReport_actionPerformed(ActionEvent e) {
        selectedCashMovement.retrieveData();
        BasicReport report = new BasicReport(CashMovementsList.class.getResource("xml/selectedCashMovementReport.xml"));
        report.addTableModel("accounting.xmlGetBookKeepingEntry", "" + selectedCashMovement.getIdbookkeepingentry());
        report.addTableModel("accounting.xmlgetBookKeepingEntryDetail", "" + selectedCashMovement.getIdbookkeepingentry());
        String movementType = "";
        if (selectedCashMovement.getCashMovementType().getIdCashMovementType() == 1)  {
            movementType = "NOTA DE INGRESO";    
        } else  {
            movementType = "NOTA DE EGRESO";
        }
        report.setProperty("movementtype", movementType);    
        report.setProperty("totalAmount", selectedCashMovement.getAmount());
        report.setProperty("textamount",Format.NumberToText.numberToText(selectedCashMovement.getAmount()) + ".-");
        report.doReport();
    }
    
    private void btnDeleteNotes_actionPerformed(ActionEvent e) {
        if (Advisor.question("Aviso","Está seguro de eliminar la Nota seleccionada?")){
            if (LibSQL.getInt("accounting.delSelectedNote",""+ selectedCashMovement.getIdCashMovement()) > 0)  {
                Advisor.messageBox("La Nota fué borrada exisotamente","Mensaje");
                refresh();
            } else  {
                Advisor.messageBox("Ocurrió un error al borrar la nota","Error");
            }
        }
    }
    
}
