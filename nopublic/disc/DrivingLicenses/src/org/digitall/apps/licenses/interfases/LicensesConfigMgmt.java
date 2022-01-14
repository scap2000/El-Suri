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
 * LicensesConfigMgmt.java
 *
 * */
package org.digitall.apps.licenses.interfases;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.CancelDataButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;
import org.digitall.apps.licenses.classes.Category;
import org.digitall.apps.licenses.classes.Configuration;


public class LicensesConfigMgmt extends BasicPrimitivePanel {

    private BasicPanel content = new BasicPanel();
    private BasicPanel findPanel = new BasicPanel("Configuración");
    private BorderLayout borderLayout1 = new BorderLayout();

    private TFInput tfYearsQty = new TFInput(DataTypes.INTEGER, "YearsQty", false);
    private TFInput tfFindDebitAccount = new TFInput(DataTypes.STRING,"SearchBy",false);
    private TFInput tfFindCreditAccount = new TFInput(DataTypes.STRING,"SearchBy",false);
    
    private CBInput cbCostCentre = new CBInput(0, "CostsCentre");
    private CBInput cbAccountsToDebit = new CBInput(0, "DebitCharges");
    private CBInput cbAccountsToCredit = new CBInput(0, "CreditCharges", false);

    private ModifyButton btnModify = new ModifyButton();
    private SaveDataButton btnSave = new SaveDataButton();
    private CancelDataButton btnCancel = new CancelDataButton();
    private CloseButton btnClose = new CloseButton();
    private AddButton btnNewCategory = new AddButton();
    private ModifyButton btnModifyCategory = new ModifyButton();

    private int[] sizeColumnList = { 270, 165};
    
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Categorías de las Licencias", dataRow);
    private Vector headerList = new Vector();
    
    private Configuration licencesConfig;
    private Category categorySelected = new Category();
    private CategoriesMgmt categoriesMgmt;

    private int error = 0;

    public LicensesConfigMgmt() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(490, 384));
        this.setPreferredSize(new Dimension(730, 392));
        findPanel.setLayout(null);
        findPanel.setPreferredSize(new Dimension(1, 140));

        findPanel.setSize(new Dimension(659, 100));
        btnCancel.setBounds(new Rectangle(450, 25, 30, 25));
        tfYearsQty.setBounds(new Rectangle(10, 20, 105, 35));
        cbAccountsToDebit.setBounds(new Rectangle(135, 60, 340, 35));
        cbCostCentre.setBounds(new Rectangle(135, 20, 250, 35));
        btnModify.setBounds(new Rectangle(390, 25, 30, 25));
        btnModify.addActionListener(new ActionListener() {

                                        public void actionPerformed(ActionEvent e) {
                                            btnModify_actionPerformed(e);
                                        }

                                    }
        );
        content.setLayout(borderLayout1);
        
        btnClose.addActionListener(new ActionListener() {

                                       public void actionPerformed(ActionEvent e) {
                                           btnClose_actionPerformed(e);
                                       }

                                   }
        );
        btnNewCategory.addActionListener(new ActionListener() {

                                       public void actionPerformed(ActionEvent e) {
                                           btnNewCategory_actionPerformed(e);
                                       }

                                   }
        );
        btnModifyCategory.addActionListener(new ActionListener() {

                                       public void actionPerformed(ActionEvent e) {
                                           btnModifyCategory_actionPerformed(e);
                                       }

                                   }
        );
        
        btnSave.setBounds(new Rectangle(420, 25, 30, 25));
        content.add(findPanel, BorderLayout.NORTH);
        content.add(listPanel, BorderLayout.CENTER);
        this.add(content, null);
        this.addButton(btnClose);
        this.addButton(btnNewCategory);
        this.addButton(btnModifyCategory);
        findPanel.add(tfFindCreditAccount, null);
        findPanel.add(tfFindDebitAccount, null);
        findPanel.add(btnCancel, null);
        findPanel.add(btnSave, null);
        findPanel.add(cbAccountsToCredit, null);
        findPanel.add(btnModify, null);
        findPanel.add(cbAccountsToDebit, null);
        findPanel.add(cbCostCentre, null);
        findPanel.add(tfYearsQty, null);
        cbAccountsToDebit.autoSize();
        cbAccountsToCredit.autoSize();
        cbCostCentre.autoSize();
        cbAccountsToCredit.setBounds(new Rectangle(135, 100, 340, 35));
        cbAccountsToCredit.autoSize();

        
        btnSave.addActionListener(new ActionListener() {
                                      public void actionPerformed(ActionEvent e) {
                                          btnSave_actionPerformed(e);
                                      }
                                  }
        );
        btnCancel.setToolTipText("Cancelar las Modificaciones");
        btnCancel.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            btnCancel_actionPerformed(e);
                                        }
                                    }
        );
        tfFindDebitAccount.getTextField().addKeyListener(new KeyAdapter() {
                 public void keyTyped(KeyEvent e) {
                     if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                         loadComboDebit();
                     }
                 }

             }
        );
        tfFindCreditAccount.getTextField().addKeyListener(new KeyAdapter() {
               public void keyTyped(KeyEvent e) {
                   if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                       loadComboCredit();
                   }
               }
        
           }
        );
        btnModify.setEnabled(true);
        btnSave.setEnabled(false);
        btnCancel.setEnabled(false);
        btnModifyCategory.setEnabled(false);
        btnModify.setToolTipText("Modificar las Cuentas y la max. cantidad de años");
        btnSave.setToolTipText("Grabar las Modificaciones");
        btnModifyCategory.setToolTipText("Modificar Categoría");
        tfFindDebitAccount.setBounds(new Rectangle(10, 60, 105, 35));
        tfFindCreditAccount.setBounds(new Rectangle(10, 100, 105, 35));
        btnNewCategory.setToolTipText("Agregar Categoría");
        setHeaderList();
        loadCombos();
        setEnabledHeaderPanel(false);
    }

    private void loadComboCredit() {
        String params = tfFindCreditAccount.getString();
        cbAccountsToCredit.loadJCombo(LibSQL.exFunction("accounting.getAllAccountsForBookKeepingEntry","-1,'"+ params +"'"));
    }

    private void loadComboDebit() {
        String params = tfFindDebitAccount.getString();
        cbAccountsToDebit.loadJCombo(LibSQL.exFunction("accounting.getAllAccountsForBookKeepingEntry","-1,'"+ params +"'"));
    }

    private void loadComboCostCentres() {
        cbCostCentre.loadJCombo(LibSQL.exFunction("cashflow.getAllCostsCentresByBudgets",""));
    }


    private void setEnabledHeaderPanel(boolean _valor) {
        tfYearsQty.setEnabled(_valor);
        cbAccountsToDebit.setEnabled(_valor);
        cbAccountsToCredit.setEnabled(_valor);
        tfFindDebitAccount.setEnabled(_valor);
        tfFindCreditAccount.setEnabled(_valor);
        cbCostCentre.setEnabled(_valor);
    }

    private void loadCombos() {
        cbAccountsToDebit.loadJCombo(LibSQL.exFunction("accounting.getAllAccountsForBookKeepingEntry","-1,''"));
        cbAccountsToCredit.loadJCombo(LibSQL.exFunction("accounting.getAllAccountsForBookKeepingEntry","-1,''"));
        cbCostCentre.loadJCombo(LibSQL.exFunction("cashflow.getAllCostsCentresByBudgets",""));
        licencesConfig = new Configuration(1);
        licencesConfig.retrieveData();
        tfYearsQty.setValue("" + licencesConfig.getYearsqty());
        cbAccountsToDebit.setSelectedID(licencesConfig.getIdaccounttodebit());
        cbAccountsToCredit.setSelectedID(licencesConfig.getIdaccounttocredit());
        cbCostCentre.setSelectedID(licencesConfig.getIdcostcentre());
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
        super.setParentInternalFrame(_e);
        getParentInternalFrame().setInfo("Puede configurar la cant. de años, cuentas y categorias");
    }

    private void setHeaderList() {
        headerList.removeAllElements();
        headerList.addElement("*");
        headerList.addElement(Environment.lang.getProperty("Category"));
        headerList.addElement(Environment.lang.getProperty("Price"));
        headerList.addElement("*");

        listPanel.getTable().addMouseListener(new MouseAdapter() {

              public void mouseClicked(MouseEvent me) {
                  loadObject();
                  if (me.getButton() == me.BUTTON1 && me.getClickCount() == 1) {

                  } else if (me.getButton() == me.BUTTON1 && me.getClickCount() == 2) {
                      loadCategory();
                  }
              }

          }
        );
        listPanel.setParams("licenses.getAllCategories", "-1", headerList);
    }

    public void refresh() {
        String params = "-1";
        listPanel.refresh(params);
        btnModifyCategory.setEnabled(false);
    }

    private void loadObject() {
        if (!dataRow.isEmpty()) {
            categorySelected.setIdcategory(Integer.parseInt("" + dataRow.elementAt(0)));
            categorySelected.setName("" + dataRow.elementAt(1));
            categorySelected.setPrice(Double.parseDouble("" + dataRow.elementAt(2)));
            categorySelected.setEstado("" + dataRow.elementAt(3));
            btnModifyCategory.setEnabled(true);
        } 
    }

    private void btnModify_actionPerformed(ActionEvent e) {
        btnModify.setEnabled(false);
        btnSave.setEnabled(true);
        btnCancel.setEnabled(true);
        setEnabledHeaderPanel(true);
    }

    private void btnClose_actionPerformed(ActionEvent e) {
        loadComboCostCentres();
        getParentInternalFrame().setIcon(true);
    }

    private void loadHeaderPanel(){
        licencesConfig.retrieveData();
        tfYearsQty.setValue("" + licencesConfig.getYearsqty());
        cbAccountsToDebit.setSelectedID(licencesConfig.getIdaccounttodebit());
        cbAccountsToCredit.setSelectedID(licencesConfig.getIdaccounttocredit());
    }
    
    private void btnSave_actionPerformed(ActionEvent e) {
        if (saveHeaderData())  {
            btnModify.setEnabled(true);
            btnSave.setEnabled(false);
            btnCancel.setEnabled(false);
            loadHeaderPanel();
            setEnabledHeaderPanel(false);
            tfFindDebitAccount.setValue("");
            tfFindCreditAccount.setValue("");
        } else {
            switch (error)  {
                case 1: Advisor.messageBox("Error 1","Error");
                    break;
                case 2: Advisor.messageBox("Error 2","Error");
                    break;
                case 3: Advisor.messageBox("Error 3","Error");
                    break;
                case 4: Advisor.messageBox("Error 4","Error");
                    break;
            }
        }
    }

    private boolean saveHeaderData() {
        boolean resul = false;
        if (headerControl()) {
            licencesConfig.setYearsqty(Integer.parseInt("" + tfYearsQty.getValue()));
            licencesConfig.setIdaccounttodebit(Integer.parseInt("" + cbAccountsToDebit.getSelectedValue()));
            licencesConfig.setIdaccounttocredit(Integer.parseInt("" + cbAccountsToCredit.getSelectedValue()));
            if (licencesConfig.saveData() > 0)  {
                resul = true;
            } else  {
                error = 1;
                resul = false;
            }
        } 
        return resul;
    }

    private boolean headerControl() {
        boolean resul = false;
        if (tfYearsQty.getString().equals(""))  {
            error = 2;
        } else if(Integer.parseInt(tfYearsQty.getString()) <= 0) {
            error = 3;
        } else if(cbAccountsToDebit.getSelectedValue() == cbAccountsToCredit.getSelectedValue()) {
            error = 4;
        } else {
            resul = true;
        }
        return resul;
    }

    private void btnCancel_actionPerformed(ActionEvent e) {
        btnModify.setEnabled(true);
        btnSave.setEnabled(false);
        btnCancel.setEnabled(false);
        setEnabledHeaderPanel(false);
        loadCombos();
        tfFindDebitAccount.setValue("");
        tfFindCreditAccount.setValue("");
    }
    
    private void btnNewCategory_actionPerformed(ActionEvent e) {
        categoriesMgmt = new CategoriesMgmt();
        categoriesMgmt.setCategory(new Category());
        categoriesMgmt.setParentList(this);
        ExtendedInternalFrame categoriesMgmtContainer = new ExtendedInternalFrame("Agregar categoria");
        categoriesMgmtContainer.setCentralPanel(categoriesMgmt);
        categoriesMgmtContainer.show();
    }
    
    private void btnModifyCategory_actionPerformed(ActionEvent e) {
        loadCategory();
    }
    
    private void loadCategory(){
        categoriesMgmt = new CategoriesMgmt();
        categoriesMgmt.setCategory(categorySelected);
        categoriesMgmt.setParentList(this);
        ExtendedInternalFrame categoriesMgmtContainer = new ExtendedInternalFrame("Modificar categoria: " + categorySelected.getName());
        categoriesMgmtContainer.setCentralPanel(categoriesMgmt);
        categoriesMgmtContainer.show();
    }
}
