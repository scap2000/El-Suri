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
 * BookKeepingEntryList.java
 *
 * */
package org.digitall.apps.cashflow.interfaces.accountingentry;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.JOptionPane;

import org.digitall.apps.cashflow.classes.BookKeppingEntriesModels;
import org.digitall.common.cashflow.classes.BookKeepingEntry;
import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.OkButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.buttons.RefreshGridButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;


public class BookKeepingEntryList extends BasicPrimitivePanel{

    private BasicPanel jPanel1 = new BasicPanel();
    private BasicPanel contentPanel = new BasicPanel();
    //private BasicTabbedPane tabbedPane = new BasicTabbedPane();
    private TFInput tfStartDate = new TFInput(DataTypes.SIMPLEDATE,"From",false);
    private TFInput tfEndDate = new TFInput(DataTypes.SIMPLEDATE,"To",false);
    private TFInput tfNumber = new TFInput(DataTypes.STRING,"AccountingEntryNumber",false);
    private FindButton btnFind = new FindButton();
    private TFInput tfConcept = new TFInput(DataTypes.STRING,"Concept",false);
    private int[] sizeColumnList = {80, 88, 205, 100, 115};
    private Vector dataRow = new Vector();
    private GridPanel listBookKeepingEntryPanel = new GridPanel(30, sizeColumnList, "Asientos", dataRow);
    private Vector headerList = new Vector();
    private int[] sizeColumnDetail = {311, 75, 75, 334};
    private Vector dataRowDetail = new Vector();
    private GridPanel listBookKeepingEntryDetailPanel = new GridPanel(30, sizeColumnDetail, "Detalle del Asiento seleccionado", dataRowDetail);
    private Vector headerDetail = new Vector();
    private BookKeepingEntry bookKeepingEntry;
    private BasicPanel jPanel2 = new BasicPanel();
    private BasicPanel jPanel3 = new BasicPanel();
    private PrintButton btnReport = new PrintButton();
    private PrintButton btnReportBookkeepingEntry = new PrintButton();
    private BorderLayout borderLayout1 = new BorderLayout();
    private BorderLayout borderLayout2 = new BorderLayout();
    private RefreshGridButton btnChkIntegrity = new RefreshGridButton();
    private OkButton btnPrint = new OkButton();
    private DeleteButton btnDelete = new DeleteButton();
    private GridLayout gridLayout1 = new GridLayout();
    private AcceptButton btnAddBookKeepingEntryModel = new AcceptButton();
    private BookKeppingEntriesModels model;
    private BookKeepingEntryModelsList bookKeepingEntryModelsList;
    

    public BookKeepingEntryList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(656, 547));
	jPanel1.setPreferredSize(new Dimension(10, 70));
	jPanel1.setLayout(null);
	
	tfStartDate.setBounds(new Rectangle(10, 20, 90, 35));
	tfNumber.setBounds(new Rectangle(210, 20, 105, 35));
	btnFind.setBounds(new Rectangle(615, 35, 20, 20));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);
	tfConcept.setBounds(new Rectangle(325, 20, 275, 35));
	jPanel2.setLayout(borderLayout1);
	jPanel3.setLayout(borderLayout2);
	btnReport.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnReport_actionPerformed(e);
				 }

			     }
	);
        btnReportBookkeepingEntry.addActionListener(new ActionListener() {

                                 public void actionPerformed(ActionEvent e) {
                                     btnReportBookeepingEntry_actionPerformed(e);
                                 }

                             }
        );
	btnPrint.setBounds(new Rectangle(275, 30, 22, 27));
	btnPrint.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnPrint_actionPerformed(e);
				}

			    }
	);
	btnChkIntegrity.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnChkIntegrity_actionPerformed(e);
				}

			    }
	);
	addButton(btnDelete);
	addButton(btnPrint);
	addButton(btnChkIntegrity);
	addButton(btnReport);
        addButton(btnReportBookkeepingEntry);
        jPanel1.add(tfEndDate, null);
        jPanel1.add(tfConcept, null);
        jPanel1.add(btnFind, null);
        jPanel1.add(tfStartDate, null);
        jPanel1.add(tfNumber, null);
        contentPanel.setLayout(gridLayout1);
	this.add(jPanel1, BorderLayout.NORTH);
	jPanel2.add(listBookKeepingEntryPanel, BorderLayout.CENTER);
	jPanel3.add(listBookKeepingEntryDetailPanel, BorderLayout.CENTER);
	contentPanel.add(jPanel2, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 213, -185));
	contentPanel.add(jPanel3, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 213, 0));
	this.add(contentPanel, BorderLayout.CENTER);
	
	setHeaderList();
	setHeaderDetail();
	btnPrint.setVisible(false);
	btnDelete.setBounds(new Rectangle(265, 10, 28, 33));
	btnDelete.setSize(new Dimension(20, 20));
	btnDelete.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnDelete_actionPerformed(e);
				 }

			     }
	);
	gridLayout1.setRows(2);
	tfConcept.getTextField().addKeyListener(new KeyAdapter() {
		 public void keyTyped(KeyEvent e) {
		     if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			 refresh();
		     }
		 }

	     }
	);
	
	tfStartDate.getTextField().addKeyListener(new KeyAdapter() {
		 public void keyTyped(KeyEvent e) {
		     if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			 refresh();
		     }
		 }

	     }
	);
        
        tfEndDate.getTextField().addKeyListener(new KeyAdapter() {
                         public void keyTyped(KeyEvent e) {
                             if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                                 refresh();
                             }
                         }

                     }
                );
	
	tfNumber.getTextField().addKeyListener(new KeyAdapter() {
		 public void keyTyped(KeyEvent e) {
		     if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			 refresh();
		     }
		 }

	     }
	);
	btnDelete.setEnabled(false);
	btnReport.setToolTipText("Imprimir Libro Diario");
        btnReportBookkeepingEntry.setToolTipText("Imprimir Libro Diario del período");
	btnChkIntegrity.setToolTipText("Verificar la integridad de los asientos");
	btnDelete.setToolTipText("Borrar el asiento seleccionado");
        btnAddBookKeepingEntryModel.setText("Crear Modelo\nde Asiento");
        //btnAddBookKeepingEntry.setToolTipText("<html><p align=center>Abre la ventana de administración de cheques</p></html>");
         btnAddBookKeepingEntryModel.addActionListener(new ActionListener() {

                                         public void actionPerformed(ActionEvent e) {
                                             btnAddBookKeepingEntryModel_actionPerformed(e);
                                         }

                                     }
         );
         btnAddBookKeepingEntryModel.setEnabled(false);
        tfEndDate.setBounds(new Rectangle(110, 20, 90, 35));
        jPanel1.setBorder(BorderPanel.getBorderPanel(Environment.lang.getProperty("FindAccountingEntry")));
        tfStartDate.setValue(Proced.setFormatDate(Environment.currentDate, true));
        tfEndDate.setValue(Proced.setFormatDate(Environment.currentDate, true));
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("AccountingEntryNumber"));
	headerList.addElement(Environment.lang.getProperty("Date"));
	headerList.addElement(Environment.lang.getProperty("Concept"));
	headerList.addElement(Environment.lang.getProperty("DebitAmount"));
	headerList.addElement(Environment.lang.getProperty("CreditAmount"));
	headerList.addElement("*");
				
	listBookKeepingEntryPanel.getTable().addMouseListener(new MouseAdapter() {
	     public void mouseClicked(MouseEvent e) {
		 loadObjectSelected();
		 if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
		     refreshDetail(); 
		 } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
		     //refreshDetail(); 
		 } else if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON3) {
		    int index = listBookKeepingEntryPanel.getTable().rowAtPoint(e.getPoint());
		    listBookKeepingEntryPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
		} 
	     }
	 }
	);
	
	listBookKeepingEntryPanel.getTable().addKeyListener(new KeyAdapter() {
		public void keyPresed(KeyEvent e){
		    listBookKeepingEntryPanel_keyTyped(e);
		}
		
		public void keyReleased(KeyEvent e){
		    listBookKeepingEntryPanel_keyTyped(e);
		}
		
		public void keyTyped(KeyEvent e){
		    listBookKeepingEntryPanel_keyTyped(e);
		}
	    
	    }
	);
	
	listBookKeepingEntryPanel.setParams("accounting.getAllBookKeepingEntries", "'','', -1, ''",headerList);    
    }
    
    public void refresh() {
        String params = "'" + Proced.setFormatDate(tfStartDate.getDate(), false) + "','" + Proced.setFormatDate(tfEndDate.getDate(), false) + "'," + (tfNumber.getString().equals("")? "-1": tfNumber.getString()) +" ,'"+ tfConcept.getString() +"'";
	//String params = "'"+ Proced.setFormatDate(tfStartDate.getString(), false) +"', "+ (tfNumber.getString().equals("")? "-1": tfNumber.getString()) +" ,'"+ tfConcept.getString() +"'";
	listBookKeepingEntryPanel.refresh(params);
	btnDelete.setEnabled(false);
        btnAddBookKeepingEntryModel.setEnabled(false);
        if (bookKeepingEntry != null) {
            bookKeepingEntry.setIDBookKeepingEntry(-1); 
            refreshDetail();
        }
        
    }
    
    private void loadObjectSelected() {
	if (!dataRow.isEmpty()) {
	    bookKeepingEntry = new BookKeepingEntry();
	    bookKeepingEntry.setIDBookKeepingEntry(Integer.parseInt(""+ dataRow.elementAt(0)));
	    bookKeepingEntry.setNumber(Integer.parseInt(""+ dataRow.elementAt(1)));
	    bookKeepingEntry.setDate(Proced.setFormatDate(""+ dataRow.elementAt(2), false));
	    bookKeepingEntry.setConcept(""+ dataRow.elementAt(3));
	    bookKeepingEntry.setDebitAmount(Double.parseDouble(""+ dataRow.elementAt(4)));
	    bookKeepingEntry.setCreditAmount(Double.parseDouble(""+ dataRow.elementAt(5)));
	    btnDelete.setEnabled(true);
	    btnDelete.setToolTipText("<html><center><u>Borrar asiento</u><br>Nº " + dataRow.elementAt(1) + ": " + dataRow.elementAt(3));
            btnAddBookKeepingEntryModel.setEnabled(true);
	} else {
	    btnDelete.setToolTipText("Borrar el asiento seleccionado");
	}
    }
    
    private void setHeaderDetail() {
	headerDetail.removeAllElements();
	headerDetail.addElement("*");
	headerDetail.addElement("*");
	headerDetail.addElement("*");
	headerDetail.addElement("*");
	headerDetail.addElement(Environment.lang.getProperty("Accounting"));
	headerDetail.addElement(Environment.lang.getProperty("Debit"));
	headerDetail.addElement(Environment.lang.getProperty("Credit"));
	headerDetail.addElement(Environment.lang.getProperty("Observations"));
	headerDetail.addElement("*");
	
	listBookKeepingEntryDetailPanel.getTable().addMouseListener(new MouseAdapter() {
	     public void mouseClicked(MouseEvent e) {
		 loadObjectDetailSelected();
		 if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
		     
		 } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
		    
		 } else if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON3) {
		    int index = listBookKeepingEntryDetailPanel.getTable().rowAtPoint(e.getPoint());
		    listBookKeepingEntryDetailPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
		} 
	     }
	 }
	);
        //String params = "-1,'" + tfStartDate.getDate() + "','" + tfEndDate.getDate() + "'";
	listBookKeepingEntryDetailPanel.setParams("accounting.getAllBookKeepingEntryDetail", "-1", headerDetail);
    }
    
    public void refreshDetail() {
	if (bookKeepingEntry != null) {
	    listBookKeepingEntryDetailPanel.refresh(bookKeepingEntry.getIDBookKeepingEntry());
	}
    }
    
    private void loadObjectDetailSelected() {
	
    }
    
    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void btnPrint_actionPerformed(ActionEvent e) {
	
    }

    private void btnDelete_actionPerformed(ActionEvent e) {
	if (!dataRow.isEmpty() && Advisor.question("Borrar asiento", "¿Está seguro de borrar el asiento " + dataRow.elementAt(1) + "?")){
	    if (bookKeepingEntry.delete()>=0){
		refresh();
	    }
	}
    }
    
    private void listBookKeepingEntryPanel_keyTyped(KeyEvent e) {
	refreshDetail();
    }

    private void btnChkIntegrity_actionPerformed(ActionEvent e) {
	if (LibSQL.getBoolean("accounting.checkbookkeepingentriesintegrity","")) {
	    Advisor.messageBox("Todos los asientos están balanceados :)","Aviso");
	} else {
	    Advisor.messageBox("Se han encontrado asientos no balanceados, revíselos por favor :(","Problema");
	}
    }

    private void btnReport_actionPerformed(ActionEvent e) {
	generarReporteLibroDiarioEntreFechas(Environment.currentDate,Environment.currentDate, false);
    }
    
    private void btnReportBookeepingEntry_actionPerformed(ActionEvent e) {
        generarReporteLibroDiarioEntreFechas(tfStartDate.getDate(),tfEndDate.getDate(), true);
    }
    
    private void generarReporteLibroDiarioEntreFechas(String _fechaInicio, String _fechaFin, boolean _periodo) {
        BasicReport report = new BasicReport(BookKeepingEntryList.class.getResource("xml/bookKeepingEntries.xml"));
        String params = "-1,'" + _fechaInicio + "','" + _fechaFin + "'";
        report.addTableModel("accounting.xmlgetAllBookKeepingEntryDetail",params);
        //report.setProperty("startdate", Proced.setFormatDate(Environment.currentYear + "-01-01", true));
        if (_periodo) {
            if (_fechaInicio.equals("")) {
                report.setProperty("startdate", "Desde: -");
            } else {
                report.setProperty("startdate", "Desde: " + _fechaInicio);
            }
            
            if (_fechaFin.equals("")) {
                report.setProperty("enddate", "Hasta: -");
            } else {
                report.setProperty("enddate", "Hasta: " + _fechaFin);
            }    
        } else {
            report.setProperty("startdate", "");
            report.setProperty("enddate", "");
        }
        
        report.doReport();
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Listado de los Asientos Contables y su correspondiente detalle");
        getParentInternalFrame().getGeneralButtons().addButton(btnAddBookKeepingEntryModel);
    }
    
    private void btnAddBookKeepingEntryModel_actionPerformed(ActionEvent e) {
        String name = "";
        name = JOptionPane.showInputDialog("Ingrese el nombre del Modelo de Asiento a crear", bookKeepingEntry.getConcept());
        if (name != null && !name.equals(""))  {
            model = new BookKeppingEntriesModels();
            model.setName(name);
            if (model.loadNewModel(name,bookKeepingEntry.getIDBookKeepingEntry()) > 0) {
                ExtendedInternalFrame bookKeepingEntryModelsListContainer = new ExtendedInternalFrame("Movimientos de Fondos");
                bookKeepingEntryModelsList = new BookKeepingEntryModelsList();
                bookKeepingEntryModelsList.loadComboModels();
                bookKeepingEntryModelsListContainer.setCentralPanel(bookKeepingEntryModelsList);
                bookKeepingEntryModelsListContainer.show();
                bookKeepingEntryModelsListContainer.setClosable(true);
                getParentInternalFrame().setIcon(true);
            } else {
                Advisor.messageBox("Ocurrió un error al Grabar el Modelo","Error");   
            }
        }
    }

}
