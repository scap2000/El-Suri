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
 * AccountsAvailableAmountList.java
 *
 * */
package org.digitall.apps.cashflow.interfaces.accounting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.JMoneyEntry;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class AccountsAvailableAmountList extends BasicPrimitivePanel {

    private BasicPanel findPanel = new BasicPanel();
    private TFInput tfStartDate = new TFInput(DataTypes.DATE, "From", false);
    private TFInput tfEndDate = new TFInput(DataTypes.DATE, "To", false);
    private PrintButton btnPrint = new PrintButton();
    private FindButton btnFind = new FindButton();
    private int[] sizeColumnList = { 263, 98, 104, 98 };
    private Vector dataRow = new Vector();
    private GridPanel listAccounting = new GridPanel(30, sizeColumnList, "Balance de sumas y saldos", dataRow);
    private Vector headerList = new Vector();
    private BasicPanel footerPanel = new BasicPanel();
    private BasicLabel jLabel1 = new BasicLabel();
    private BasicLabel jLabel2 = new BasicLabel();
    private BasicLabel jLabel3 = new BasicLabel();
    private JMoneyEntry tfAvailableAmount = new JMoneyEntry();
    private JMoneyEntry tfCreditAmount = new JMoneyEntry();
    private JMoneyEntry tfDebitAmount = new JMoneyEntry();
    private BasicPanel content = new BasicPanel();
    private BorderLayout borderLayout1 = new BorderLayout();

    public AccountsAvailableAmountList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(706, 440));
	findPanel.setLayout(null);
	findPanel.setPreferredSize(new Dimension(1, 60));
	tfStartDate.setBounds(new Rectangle(225, 15, 95, 35));
	tfEndDate.setBounds(new Rectangle(350, 15, 95, 35));
	btnFind.setBounds(new Rectangle(460, 30, 20, 20));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);
	footerPanel.setPreferredSize(new Dimension(10, 35));
	footerPanel.setLayout(null);
	jLabel1.setText("Total Debe:");
	jLabel1.setBounds(new Rectangle(5, 7, 78, 20));
	jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
	jLabel2.setText("Total Haber:");
	jLabel2.setBounds(new Rectangle(273, 7, 83, 20));
	jLabel2.setToolTipText("null");
	jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
	jLabel3.setText("Saldo:");
	jLabel3.setBounds(new Rectangle(545, 7, 45, 20));
	jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
	tfAvailableAmount.setBounds(new Rectangle(590, 7, 105, 20));
	tfAvailableAmount.setEnabled(false);
	tfCreditAmount.setBounds(new Rectangle(360, 7, 105, 20));
	tfCreditAmount.setEnabled(false);
	tfDebitAmount.setBounds(new Rectangle(85, 7, 105, 20));
	tfDebitAmount.setEnabled(false);
	content.setLayout(borderLayout1);
	btnPrint.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnPrint_actionPerformed(e);
				}

			    }
	);
	findPanel.add(btnFind, null);
	findPanel.add(tfEndDate, null);
	findPanel.add(tfStartDate, null);
	content.add(findPanel, BorderLayout.NORTH);
	content.add(listAccounting, BorderLayout.CENTER);
	footerPanel.add(tfDebitAmount, null);
	footerPanel.add(tfCreditAmount, null);
	footerPanel.add(tfAvailableAmount, null);
	footerPanel.add(jLabel3, null);
	footerPanel.add(jLabel2, null);
	footerPanel.add(jLabel1, null);
	content.add(footerPanel, BorderLayout.SOUTH);
	addButton(btnPrint);
	this.add(content, null);
	setHeaderList();
	tfStartDate.setValue(Proced.setFormatDate(Environment.currentYear + "-01-01", true));
	tfEndDate.setValue(Proced.setFormatDate(Environment.currentDate, true));
	btnPrint.setEnabled(false);
	btnPrint.setToolTipText("Imprimir Balance de Sumas y Saldos");
        findPanel.setBorder(BorderPanel.getBorderPanel("Buscar por rango de fechas"));
        btnFind.setToolTipText("Generar Balance entre las fechas seleccionadas");
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	/*headerList.addElement(Environment.lang.getProperty("AccountingEntryNumber"));
	headerList.addElement(Environment.lang.getProperty("Date"));*/
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Concept"));
	headerList.addElement(Environment.lang.getProperty("DebitAmount"));
	headerList.addElement(Environment.lang.getProperty("CreditAmount"));
	headerList.addElement(Environment.lang.getProperty("DebitAvailableAmount"));
	headerList.addElement(Environment.lang.getProperty("CreditAvailableAmount"));
	listAccounting.getTable().addMouseListener(new MouseAdapter() {

						public void mouseClicked(MouseEvent e) {
						    if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						    } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON3) {
							int index = listAccounting.getTable().rowAtPoint(e.getPoint());
							listAccounting.getTable().getSelectionModel().setSelectionInterval(index, index);
						    }
						}

					    }
	);
	listAccounting.setParams("accounting.getAllBookKeepingEntriesAvailableAmount", "'', ''", headerList);
    }

    public void refresh() {
	if (tfStartDate.getString().length() > 0 && tfEndDate.getString().length() > 0) {
	    String params = "'" + Proced.setFormatDate(tfStartDate.getString(), false) + "','" + Proced.setFormatDate(tfEndDate.getString(), false) + "'";
	    listAccounting.refresh(params);
	    try {
		ResultSet rs = LibSQL.exFunction("accounting.getBookKeepingEntriesAvailableAmount", params);
		if (rs.next()) {
		    tfDebitAmount.setValue(rs.getDouble("debitamount"));
		    tfCreditAmount.setValue(rs.getDouble("creditamount"));
		    tfAvailableAmount.setValue(Math.abs(rs.getDouble("debitamount")-rs.getDouble("creditamount")));
                    tfAvailableAmount.setBackground(tfAvailableAmount.getAmount() < 0.0000001?Color.GREEN.brighter():Color.RED.brighter());
		}
		btnPrint.setEnabled(true);
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	} else {
	    Advisor.messageBox("Debe seleccionar un rango de fechas", "Aviso");
	}
    }

    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void btnPrint_actionPerformed(ActionEvent e) {
	//AccountsAvailableAmountReport report = new AccountsAvailableAmountReport("xml/accountsavailableamount.xml", Proced.setFormatDate(tfStartDate.getString(), false), Proced.setFormatDate(tfEndDate.getString(), false));
	 if (LibSQL.getBoolean("accounting.checkbookkeepingentriesintegrity","")) {
	     Object[] columns = new Object[] { "startdate", "enddate" };
	     Object[][] values = new Object[][] { { tfStartDate.getDate(), tfEndDate.getDate() } };
	     DefaultTableModel header = new DefaultTableModel(values, columns);
	     BasicReport report = new BasicReport(AccountsAvailableAmountList.class.getResource("xml/accountsavailableamount.xml"));
	     report.addTableModel(header);
	     report.addTableModel("accounting.getAllBookKeepingEntriesAvailableAmount", "'" + Proced.setFormatDate(tfStartDate.getDate(), false) + "','" + Proced.setFormatDate(tfEndDate.getDate(), false) + "',0,0");
	     report.addTableModel("accounting.getBookKeepingEntriesAvailableAmount", "'" + Proced.setFormatDate(tfStartDate.getDate(), false) + "','" + Proced.setFormatDate(tfEndDate.getDate(), false) + "'");
	     report.setProperty("startdate","" + tfStartDate.getDate());
	     report.setProperty("enddate","" + tfEndDate.getDate());
	     report.doReport();
	 } else {
	     Advisor.messageBox("Se han encontrado asientos no balanceados, revíselos por favor :(","Problema");
	 }
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Seleccione un rango de fechas para previsualizar e imprimir el Balance de Sumas y Saldos");
    }

}
