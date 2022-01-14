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
 * ClearTaxesTGS.java
 *
 * */
package org.digitall.apps.taxes.interfases.clearfees;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.SwingConstants;

import org.digitall.apps.taxes.classes.Cadastral;
import org.digitall.apps.taxes.interfases.cadastraladmin.CadastralMgmt;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicTitleLabel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.buttons.RefreshGridButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class ClearTaxesTGS extends BasicPrimitivePanel {

    private BasicPanel content = new BasicPanel();
    private BasicPanel findPanel = new BasicPanel();
    private BasicPanel impuestosPanel = new BasicPanel();
    private BasicPanel tgsPanel = new BasicPanel();
    private BasicPanel inmobPanel = new BasicPanel();
    
    private GridLayout gridLayout1 = new GridLayout();
    
    private BasicTitleLabel lblTgsTitle = new BasicTitleLabel();
    private BasicTitleLabel lblInmobTitle = new BasicTitleLabel();
    private BorderLayout borderLayout4 = new BorderLayout();
    
    private TFInput tfCadastral = new TFInput(DataTypes.INTEGER, "Cadastral", false);
    private TFInput tfOwner = new TFInput(DataTypes.STRING, "Name", false);
    private TFInput tfIdentificationNumber = new TFInput(DataTypes.INTEGER, "DNI", false);
    
    private int[] propertiesColumnSize = { 66, 240, 86, 125, 130,60};
    private Vector dataRow = new Vector();
    private GridPanel propertiesPanel = new GridPanel(50000, propertiesColumnSize, "Propiedades", dataRow);
    private Vector propertiesHeader = new Vector();

    private FindButton btnSearch = new FindButton();
    private DeleteButton btnDeleteTgsFees = new DeleteButton();
    private RefreshGridButton btnRecalcTgsFees = new RefreshGridButton();
    private DeleteButton btnDeleteInmobFees = new DeleteButton();
    private RefreshGridButton btnRecalcInmobFees = new RefreshGridButton();
    private PrintButton btnPrintTgsFees = new PrintButton();
    private PrintButton btnPrintInmobFees = new PrintButton();
    private AddButton btnAddCadastral = new AddButton();
    private ModifyButton btnModifyCadastral = new ModifyButton();

    private CBInput cbTgsMonth = new CBInput(0,"Advance",false);
    private CBInput cbTgsYear = new CBInput(0,"FileYear",false);
    
    private CBInput cbInmobMonth = new CBInput(0,"Advance",false);
    private CBInput cbInmobYear = new CBInput(0,"FileYear",false);
    
    private int idcatastro = -1;
    
    private CadastralMgmt cadastralMgmt;
    private Cadastral cadastral;
    private final int addCadastral = 1;
    private final int addOwner = 2;
    private final int editCadastral = 3;
    

    public ClearTaxesTGS() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(700, 479));
	tfCadastral.setBounds(new Rectangle(360, 15, 110, 35));
	tfOwner.setBounds(new Rectangle(70, 15, 125, 35));
        
	tfIdentificationNumber.setBounds(new Rectangle(220, 15, 105, 35));
	btnSearch.setBounds(new Rectangle(645, 20, 30, 30));
	btnSearch.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnSearch_actionPerformed(e);
				 }

			     }
	);
        impuestosPanel.setLayout(gridLayout1);
        cbTgsMonth.setBounds(new Rectangle(10, 45, 85, 35));
        cbTgsMonth.setOpaque(false);
        cbInmobMonth.setBounds(new Rectangle(10, 45, 85, 35));
        cbInmobMonth.setOpaque(false);
        cbTgsYear.setBounds(new Rectangle(105, 45, 100, 35));
        cbTgsYear.setOpaque(false);
        cbInmobYear.setBounds(new Rectangle(110, 45, 100, 35));
        cbInmobYear.setOpaque(false);
        tgsPanel.setLayout(null);
        tgsPanel.setBackground(new Color(87, 149, 29));
        inmobPanel.setLayout(null);
        inmobPanel.setBackground(new Color(149, 130, 11));
        lblTgsTitle.setText("IMPUESTO TGS");
        lblInmobTitle.setText("IMPUESTO INMOBILIARIO");
        lblTgsTitle.setBounds(new Rectangle(0, 5, 350, 25));
        lblTgsTitle.setFont(new Font("Lucida Sans", 1, 15));
        lblTgsTitle.setHorizontalAlignment(SwingConstants.CENTER);

        lblInmobTitle.setBounds(new Rectangle(0, 5, 350, 25));
        lblInmobTitle.setFont(new Font("Lucida Sans", 1, 15));
        lblInmobTitle.setHorizontalAlignment(SwingConstants.CENTER);

        lblInmobTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTgsTitle.setHorizontalTextPosition(SwingConstants.CENTER);
        lblInmobTitle.setHorizontalTextPosition(SwingConstants.CENTER);
        impuestosPanel.setBounds(new Rectangle(5, 300, 405, 200));
        impuestosPanel.setPreferredSize(new Dimension(405, 120));
        tgsPanel.add(lblTgsTitle, null);
        tgsPanel.add(cbTgsYear, null);
        tgsPanel.add(cbTgsMonth, null);
        tgsPanel.add(btnDeleteTgsFees, null);
        tgsPanel.add(btnRecalcTgsFees,null);
        tgsPanel.add(btnPrintTgsFees,null);
        inmobPanel.add(btnDeleteInmobFees, null);
        inmobPanel.add(lblInmobTitle, null);
        inmobPanel.add(cbInmobYear, null);
        inmobPanel.add(cbInmobMonth, null);
        inmobPanel.add(btnPrintInmobFees, null);
        inmobPanel.add(btnRecalcInmobFees,null);
        this.addButton(btnModifyCadastral);
        this.addButton(btnAddCadastral);
        cbInmobMonth.autoSize();
        cbTgsMonth.autoSize();
        cbTgsYear.autoSize();
        cbInmobYear.autoSize();
        findPanel.setBounds(new Rectangle(5, 5, 685, 45));
        findPanel.setLayout(null);
        findPanel.setPreferredSize(new Dimension(1, 65));
        propertiesPanel.setBounds(new Rectangle(5, 55, 685, 155));
        propertiesPanel.setPreferredSize(new Dimension(400, 400));
        btnRecalcTgsFees.setBounds(new Rectangle(230, 60, 115, 25));
        //btnPrintTgsFees.setBounds(new Rectangle(270, 50, 20, 20));
        btnRecalcTgsFees.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnRecalcTgsFees_actionPerformed(e);
                }
            }
        );
        btnPrintTgsFees.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnPrintTgsFees_actionPerformed(e);
                }
            }
        );
        btnPrintInmobFees.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnPrintInmobFees_actionPerformed(e);
                }
            }
        );
        
        btnAddCadastral.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnAddCadastral_actionPerformed(e);
                }
            }
        );
        btnModifyCadastral.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnModifyCadastral_actionPerformed(e);
                }
            }
        );
        
        btnRecalcInmobFees.setBounds(new Rectangle(230, 60, 110, 25));
        btnRecalcInmobFees.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnRecalcInmobFees_actionPerformed(e);
                }
            }
        );
        btnDeleteTgsFees.setBounds(new Rectangle(230, 35, 115, 20));
        btnDeleteTgsFees.setHorizontalAlignment(SwingConstants.LEFT);
        btnDeleteTgsFees.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnDeleteTgsFees.setText("Borrar");
        btnDeleteTgsFees.setToolTipText("<html><center>Borrar anticipos de TGS<br>para el Catastro seleccionado</html>");
        btnDeleteTgsFees.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnDeleteTgsFees_actionPerformed(e);
                }
            }
        );
        btnDeleteInmobFees.setBounds(new Rectangle(230, 35, 110, 20));
        btnDeleteInmobFees.setHorizontalAlignment(SwingConstants.LEFT);
        btnDeleteInmobFees.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnDeleteInmobFees.setText("Borrar");
        btnDeleteInmobFees.setToolTipText("<html><center>Borrar anticipos del Imp. Inmobiliario<br>para el Catastro seleccionado</center></html>");
        btnDeleteInmobFees.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnDeleteInmobFees_actionPerformed(e);
                }
            }
        );
        content.setLayout(borderLayout4);
        findPanel.add(tfIdentificationNumber, null);
        findPanel.add(btnSearch, null);
        findPanel.add(tfOwner, null);
        findPanel.add(tfCadastral, null);
        this.add(content, null);
        content.add(findPanel, BorderLayout.NORTH);
	content.add(propertiesPanel, BorderLayout.CENTER);
        impuestosPanel.add(tgsPanel, null);
        impuestosPanel.add(inmobPanel, null);
        content.add(impuestosPanel, BorderLayout.SOUTH);
	tfOwner.getTextField().addKeyListener(new KeyAdapter() {

					   public void keyTyped(KeyEvent e) {
					       char caracter = e.getKeyChar();
					       if ((caracter == KeyEvent.VK_ENTER)) {
						   refresh();
					       }
					   }

				       }
	);
	tfIdentificationNumber.getTextField().addKeyListener(new KeyAdapter() {

							  public void keyTyped(KeyEvent e) {
							      char caracter = e.getKeyChar();
							      if ((caracter == KeyEvent.VK_ENTER)) {
								  refresh();
							      }
							  }

						      }
	);
	tfCadastral.getTextField().addKeyListener(new KeyAdapter() {

					       public void keyTyped(KeyEvent e) {
						   char caracter = e.getKeyChar();
						   if ((caracter == KeyEvent.VK_ENTER)) {
						       refresh();
						   }
					       }

					   }
	);
        btnRecalcTgsFees.setToolTipText("<html><center>Restaurar los anticipos de TGS <br>para el catastro seleccionado</center></html>");
        btnRecalcTgsFees.setText("Restaurar ant.");
        btnRecalcTgsFees.setHorizontalAlignment(SwingConstants.LEFT);
        btnRecalcTgsFees.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnRecalcInmobFees.setToolTipText("<html><center>Restaurar los anticipos de Impuesto Inmobiliario<br>para el catastro seleccionado</center></html>");
        btnRecalcInmobFees.setText("Restaurar ant.");
        btnRecalcInmobFees.setHorizontalAlignment(SwingConstants.LEFT);
        btnRecalcInmobFees.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnPrintTgsFees.setToolTipText("<html><center>Imprimir los anticipos de TGS<br>para el catastro seleccionado</center></html>");
        btnPrintTgsFees.setText("Imprimir");
        btnPrintTgsFees.setBounds(new Rectangle(230, 90, 115, 20));
        btnPrintTgsFees.setHorizontalAlignment(SwingConstants.LEFT);
        btnPrintTgsFees.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnPrintInmobFees.setToolTipText("<html><center>Imprimir los anticipos del Impuesto Inmobiliario<br>para el catastro seleccionado</center></html>");
        btnPrintInmobFees.setText("Imprimir");
        btnPrintInmobFees.setBounds(new Rectangle(230, 90, 110, 20));
        btnPrintInmobFees.setHorizontalAlignment(SwingConstants.LEFT);
        btnPrintInmobFees.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnAddCadastral.setToolTipText("<html><center>Agregar nuevo Catastro</center></html>");
        btnModifyCadastral.setToolTipText("<html><center>Modificar el Catastro seleccionado</center></html>");
        setPropertiesHeader();
        loadCombos();
        setEnabledCombosAndButtons(false);
        findPanel.setBorder(BorderPanel.getBorderPanel("Buscar"));
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
    }

    private void loadCombos(){
        int actualYear = Integer.parseInt("0" + Environment.currentYear);
        int cont = 0;
        for (int i = 2000 ; i <= (actualYear) ; i++)  {
            cont++;
            cbTgsYear.getCombo().addItem(i,cont);
            cbInmobYear.getCombo().addItem(i,cont);
        }
        cbTgsYear.setSelectedID(cont);
        cbInmobYear.setSelectedID(cont);
        cont = 0;
        for (int i = 1 ; i <= (12) ; i++)  {
            cont++;
            cbTgsMonth.getCombo().addItem(i,cont);
            cbInmobMonth.getCombo().addItem(i,cont);
        }
    }

    private void setEnabledCombosAndButtons(boolean _valor){
        cbTgsMonth.setEnabled(_valor);
        cbTgsYear.setEnabled(_valor);
        btnDeleteTgsFees.setEnabled(_valor);
        btnDeleteInmobFees.setEnabled(_valor);
        btnRecalcTgsFees.setEnabled(_valor);
        btnPrintTgsFees.setEnabled(_valor);
        btnPrintInmobFees.setEnabled(_valor);
        btnRecalcInmobFees.setEnabled(_valor);
        cbInmobMonth.setEnabled(_valor);
        cbInmobYear.setEnabled(_valor);
        btnModifyCadastral.setEnabled(_valor);
    }

    private void setPropertiesHeader() {
	propertiesHeader.removeAllElements();
	propertiesHeader.addElement("*"); //idcatastro
	propertiesHeader.addElement("Catastro");
	propertiesHeader.addElement(Environment.lang.getProperty("Name"));
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        propertiesHeader.addElement("DNI");propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");
        propertiesHeader.addElement("Ult. Anticipo TGS");
        propertiesHeader.addElement("Ult. Anticipo Inmob.");
	propertiesHeader.addElement("*");
	propertiesHeader.addElement("*");
	propertiesHeader.addElement("Es Rural");
        
	propertiesPanel.getTable().addMouseListener(new MouseAdapter() {

						 public void mouseClicked(MouseEvent e) {
						     loadObjectSelected();
						     if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
                                                     
						     } else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                                                        setEnabledCombosAndButtons(true);
						     }
						 }

					     }
	);
	String params = "'" + tfOwner.getString() + "','" + tfIdentificationNumber.getString() + "',0" + tfCadastral.getString();
	propertiesPanel.setParams("taxes.getAllCatastros", params, propertiesHeader);
    }

    public void refresh() {
	String params = "'" + tfOwner.getString() + "','" + tfIdentificationNumber.getString() + "',0" + tfCadastral.getString();
	propertiesPanel.refresh(params);
        setEnabledCombosAndButtons(false);
    }

    private void loadObjectSelected() {
	if (!dataRow.isEmpty()) {
	    idcatastro = Integer.parseInt("" + dataRow.elementAt(0));
	    cadastral = new Cadastral();
	    cadastral.setIdCatastro(Integer.parseInt(dataRow.elementAt(0).toString()));
	    cadastral.retrieveData();
	}
    }

    private void btnSearch_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void btnDeleteTgsFees_actionPerformed(ActionEvent e) {
        if (Advisor.question("Aviso!","¿Está seguro de borrar los anticipos?")) {
            String params = idcatastro + ","+ cbTgsMonth.getSelectedItem() +","+ cbTgsYear.getSelectedItem();
	    //if (LibSQL.getInt("taxes.deleteTgsFees",params) > 0) {  <== Caso: Valor Fiscal
            if (LibSQL.getInt("taxes.deleteTgsMFFees",params) > 0) {
                refresh();
            }
        }
    }

    private void btnRecalcTgsFees_actionPerformed(ActionEvent e) {
            if (Advisor.question("Aviso!","¿Está seguro de regenerar los anticipos de TGS.\npara el catastro seleccionado?")) {
                if (LibSQL.getInt("taxes.generarCuotaTgsMf",idcatastro) == 0) {
                    refresh();
                }
            }
    }

    private void btnPrintTgsFees_actionPerformed(ActionEvent e) {
	if (!dataRow.isEmpty()) {
	    idcatastro = Integer.parseInt(dataRow.elementAt(0).toString());
	    if (LibSQL.getInt("taxes.setTgsFeesMF",idcatastro) != -1) {
	        String catastro = dataRow.elementAt(1).toString();
	        BasicReport report = new BasicReport(ClearTaxesTGS.class.getResource("xml/TgsFeesReport.xml"));
	        String param = ""+ idcatastro;
	        report.addTableModel("taxes.xmlgetCatastro",catastro);
	        report.addTableModel("taxes.xmlGetTgsFeesMF", param);
	        report.doReport();
	    } else {
	        Advisor.messageBox("No se pudo actualizar la deuda del catastro seleccionado", "Error");
	    }
	} else {
	    Advisor.messageBox("Debe seleccionar un catastro", "Error");
	}
    }

    private void btnDeleteInmobFees_actionPerformed(ActionEvent e) {
        if (Advisor.question("Aviso!","¿Está seguro de borrar los anticipos?")) {
            String params = idcatastro + ","+ cbInmobMonth.getSelectedItem() +","+ cbInmobYear.getSelectedItem();
            if (LibSQL.getInt("taxes.deleteInmobFees",params) > 0) {
                refresh();
            }
        }
    }

    private void btnRecalcInmobFees_actionPerformed(ActionEvent e) {
            if (Advisor.question("Aviso!","¿Está seguro de regenerar los anticipos del Imp. Inmob.\npara el catastro seleccionado?")) {
                if (LibSQL.getInt("taxes.generarCuotaInmob",idcatastro) == 0) {
                    refresh();
                }
            }
    }

    private void btnPrintInmobFees_actionPerformed(ActionEvent e) {
	if (!dataRow.isEmpty()) {
	    idcatastro = Integer.parseInt(dataRow.elementAt(0).toString());

	    if (LibSQL.getInt("taxes.setInmobFees",idcatastro) != -1) {
		String catastro = dataRow.elementAt(1).toString();
		BasicReport report = new BasicReport(ClearTaxesTGS.class.getResource("xml/InmobFeesReport.xml"));
		String param = ""+ idcatastro;
		report.addTableModel("taxes.xmlgetCatastro",catastro);
		report.addTableModel("taxes.xmlGetInmobFees", param);
		report.doReport();
	    } else {
	        Advisor.messageBox("No se pudo actualizar la deuda del catastro seleccionado", "Error");
	    }
	} else {
	    Advisor.messageBox("Debe seleccionar un catastro", "Error");
	}
    }

    private void loadMgmt(int _Action){
        if (_Action == addCadastral){
            cadastral = new Cadastral();
        } 
        
        cadastralMgmt = new CadastralMgmt();
        cadastralMgmt.setAction(_Action);
        cadastralMgmt.setCadastral(cadastral);
        cadastralMgmt.setParentList(this);
        
        ExtendedInternalFrame cadastralMgmtContainer = new ExtendedInternalFrame("Agregar/Modificar");
        cadastralMgmtContainer.setCentralPanel(cadastralMgmt);
        cadastralMgmtContainer.show();
    }

    private void btnAddCadastral_actionPerformed(ActionEvent e) {
        loadMgmt(addCadastral);
        //Advisor.messageBox("Modulo en Construcción","Aviso");
    }


    private void btnModifyCadastral_actionPerformed(ActionEvent e) {
        loadMgmt(editCadastral);
    }


}
