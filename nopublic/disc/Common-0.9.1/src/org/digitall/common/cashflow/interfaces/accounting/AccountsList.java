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
 * AccountsList.java
 *
 * */
package org.digitall.common.cashflow.interfaces.accounting;

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

import javax.swing.ButtonGroup;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.digitall.common.cashflow.classes.Account;
import org.digitall.common.cashflow.classes.AccountTypes;
import org.digitall.common.cashflow.classes.AccountsGroup;
import org.digitall.common.cashflow.reports.accounting.AccountsListReport;
import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.BasicTabbedPane;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class AccountsList extends BasicPrimitivePanel {

    private BasicPanel content = new BasicPanel();
    private BasicPanel findPanel = new BasicPanel();
    private int[] sizeColumnList = { 55, 324, 216, 71 };
    private Vector dataRow = new Vector();
    private BasicTabbedPane tabbedPane = new BasicTabbedPane();
    private GridPanel listPanel = new GridPanel(50000, sizeColumnList, "", dataRow) {
	public void calculate() {
	    controlBotones();
	}
    }
    ;
    private Vector headerList = new Vector();
    private AccountsMain parentMain;
    private BasicRadioButton rbtnActive = new BasicRadioButton();
    private BasicRadioButton rbtnPassive = new BasicRadioButton();
    private BasicRadioButton rbtnNetHeritage = new BasicRadioButton();
    private BasicRadioButton rbtnResource = new BasicRadioButton();
    private BasicRadioButton rbtnEgress = new BasicRadioButton();
    private BasicRadioButton rbtnAll = new BasicRadioButton();
    private TFInput tfName = new TFInput(DataTypes.STRING, "Name", false);
    private TFInput tfCode = new TFInput(DataTypes.STRING, "Code", false);
    private FindButton btnFind = new FindButton();
    private BasicScrollPane jspTree = new BasicScrollPane();
    private JTree accountTree;
    private AccountsGroup accountsGroup = new AccountsGroup();
    private Account account;
    private Account parentAccount;
    private BasicPanel panelTree = new BasicPanel();
    private BasicButton btnExpand = new BasicButton();
    private BasicButton btnCollapse = new BasicButton();
    private BasicPanel jPanel1 = new BasicPanel();
    private AddButton btnNew = new AddButton();
    private AcceptButton btnSelect = new AcceptButton();
    private CloseButton btnClose = new CloseButton();
    private BasicPrimitivePanel parentPanel;
    private BasicButton btnViewAll = new BasicButton();
    private ModifyButton btnModify = new ModifyButton();
    private PrintButton btnPrint = new PrintButton();
    private DeleteButton btnDelete = new DeleteButton();

    public AccountsList(AccountsMain _parentMain) {
	try {
	    parentMain = _parentMain;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(550, 510));
	this.setPreferredSize(new Dimension(550, 510));
	panelTree.setBounds(new Rectangle(5, 290, 705, 355));
	jspTree.setBounds(new Rectangle(55, 85, 705, 355));
	panelTree.setLayout(new BorderLayout());
	jPanel1.add(btnViewAll, null);
	jPanel1.add(btnCollapse, null);
	jPanel1.add(btnExpand, null);
	panelTree.add(jPanel1, BorderLayout.NORTH);
	panelTree.add(jspTree, BorderLayout.CENTER);
	btnExpand.setText(Environment.lang.getProperty("Expand"));
	btnExpand.setBounds(new Rectangle(405, 5, 66, 20));
	btnExpand.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnExpand_actionPerformed(e);
				 }

			     }
	);
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	btnCollapse.setText(Environment.lang.getProperty("Collapse"));
	btnCollapse.setBounds(new Rectangle(475, 5, 64, 20));
	btnCollapse.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       btnCollapse_actionPerformed(e);
				   }

			       }
	);
	jPanel1.setBounds(new Rectangle(70, 20, 245, 25));
	jPanel1.setLayout(null);
	jPanel1.setPreferredSize(new Dimension(705, 30));
	btnNew.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnNew_actionPerformed(e);
			      }

			  }
	);
	btnSelect.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnSelect_actionPerformed(e);
				 }

			     }
	);
	findPanel.setLayout(null);
	findPanel.setBounds(new Rectangle(0, 0, 760, 100));
	findPanel.setPreferredSize(new Dimension(760, 75));
	rbtnActive.setText(Environment.lang.getProperty("Active"));
	rbtnActive.setBounds(new Rectangle(5, 5, 71, 18));
	rbtnActive.setSize(new Dimension(71, 18));
	rbtnActive.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      rbtnActive_actionPerformed(e);
				  }

			      }
	);
	rbtnPassive.setText(Environment.lang.getProperty("Passive"));
	rbtnPassive.setBounds(new Rectangle(80, 5, 73, 18));
	rbtnPassive.setSize(new Dimension(73, 18));
	rbtnPassive.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       rbtnPassive_actionPerformed(e);
				   }

			       }
	);
	rbtnNetHeritage.setText(Environment.lang.getProperty("NetHeritage"));
	rbtnNetHeritage.setBounds(new Rectangle(155, 5, 129, 18));
	rbtnNetHeritage.setSize(new Dimension(129, 18));
	rbtnNetHeritage.addActionListener(new ActionListener() {

				       public void actionPerformed(ActionEvent e) {
					   rbtnNetHeritage_actionPerformed(e);
				       }

				   }
	);
	rbtnResource.setText(Environment.lang.getProperty("Resources"));
	rbtnResource.setBounds(new Rectangle(285, 5, 95, 20));
	rbtnResource.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					rbtnResource_actionPerformed(e);
				    }

				}
	);
	rbtnEgress.setText(Environment.lang.getProperty("Outgoings"));
	rbtnEgress.setBounds(new Rectangle(380, 5, 85, 20));
	rbtnEgress.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      rbtnEgress_actionPerformed(e);
				  }

			      }
	);
	rbtnAll.setText(Environment.lang.getProperty("All"));
	rbtnAll.setBounds(new Rectangle(470, 5, 67, 18));
	rbtnAll.setSize(new Dimension(67, 18));
	rbtnAll.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   rbtnAll_actionPerformed(e);
			       }

			   }
	);
	tfName.setBounds(new Rectangle(140, 30, 340, 35));
	tfCode.setBounds(new Rectangle(5, 30, 105, 35));
	btnFind.setBounds(new Rectangle(505, 45, 20, 20));
	btnFind.setSize(new Dimension(20, 20));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);
	content.setLayout(new BorderLayout());
	btnClose.setVisible(false);
	btnViewAll.setText(Environment.lang.getProperty("ViewAll"));
	btnViewAll.setBounds(new Rectangle(5, 5, 93, 22));
	btnViewAll.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      btnViewAll_actionPerformed(e);
				  }

			      }
	);
	btnModify.setBounds(new Rectangle(120, 50, 20, 20));
	btnModify.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnModify_actionPerformed(e);
				 }

			     }
	);
	btnSelect.setVisible(false);
	addButton(btnDelete);
	//addButton(btnClose);
	addButton(btnModify);
	addButton(btnNew);
	addButton(btnSelect);
	addButton(btnPrint);
	findPanel.add(btnFind, null);
	findPanel.add(tfCode, null);
	findPanel.add(tfName, null);
	findPanel.add(rbtnAll, null);
	findPanel.add(rbtnEgress, null);
	findPanel.add(rbtnResource, null);
	findPanel.add(rbtnNetHeritage, null);
	findPanel.add(rbtnPassive, null);
	findPanel.add(rbtnActive, null);
	tabbedPane.addTab(Environment.lang.getProperty("List"), listPanel);
	tabbedPane.addTab(Environment.lang.getProperty("Tree"), panelTree);
	content.add(findPanel, BorderLayout.NORTH);
	content.add(tabbedPane, BorderLayout.CENTER);
	add(content, null);
	ButtonGroup rbtn = new ButtonGroup();
	rbtn.add(rbtnAll);
	rbtn.add(rbtnActive);
	rbtn.add(rbtnEgress);
	rbtn.add(rbtnNetHeritage);
	rbtn.add(rbtnPassive);
	rbtn.add(rbtnResource);
	rbtnEgress.setSelected(true);
	btnPrint.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnPrint_actionPerformed(e);
				}

			    }
	);
	btnDelete.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnDelete_actionPerformed(e);
				 }

			     }
	);
	tabbedPane.addChangeListener(new ChangeListener() {

				  public void stateChanged(ChangeEvent evt) {
				      JTabbedPane pane = (JTabbedPane)evt.getSource();
				      int sel = pane.getSelectedIndex();
				      if (sel == 1) {
					  if (account != null) {
					      loadObjectSelected();
					      drawTreeAccount(account);
					  } else {
					      drawTreeAccount(parentAccount);
					  }
				      }
				  }

			      }
	);
	tfName.getTextField().addKeyListener(new KeyAdapter() {

					  public void keyReleased(KeyEvent e) {
					      if (e.getKeyCode() == KeyEvent.VK_ENTER)
						  refresh();
					  }

				      }
	);
	tfCode.getTextField().addKeyListener(new KeyAdapter() {

					  public void keyReleased(KeyEvent e) {
					      if (e.getKeyCode() == KeyEvent.VK_ENTER)
						  refresh();
					  }

				      }
	);
	setHeaderList();
	setFilter(AccountTypes.EXPENDITURE);
	btnModify.setToolTipText("Modificar la Cuenta seleccionada");
	btnNew.setToolTipText("Agregar una nueva Cuenta");
	btnPrint.setToolTipText("Imprimir el listado de Cuentas Contables del Tipo seleccionado");
	btnDelete.setToolTipText("Tilde una o varias Cuentas para borrar");
	btnDelete.setEnabled(false);
	btnModify.setEnabled(false);
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Code"));
	headerList.addElement(Environment.lang.getProperty("Name"));
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Description"));
	headerList.addElement(Environment.lang.getProperty("Imputable"));
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
        headerList.addElement("*");
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent e) {
					       loadObjectSelected();
                                               if (account.isSubAccount())  {
                                                   btnNew.setEnabled(false);
                                               } else  {
                                                   btnNew.setEnabled(true);
                                               }
                                               
					       if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						    if(listPanel.getSelectedsID().size() > 0){
							btnDelete.setEnabled(true);
						    }else{
						        btnDelete.setEnabled(false);
						    }
					       } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						   if (parentPanel != null) {
						       selectAccount();
						   } else {
						       loadMgmt();
						   }
					       } else if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON3) {
						   int index = listPanel.getTable().rowAtPoint(e.getPoint());
						   listPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
						   newAccount();
					       }
					   }

				       }
	);
	listPanel.setParams("accounting.getAllAccounts", "-1, -1, -1, ''", headerList);
    }

    public void refresh() {
	int idAccountType = parentAccount.getIdAccountType();
	String params = "-1," + idAccountType + ",";
	if (tfCode.getString().length() > 0) {
	    params += tfCode.getString() + ",";
	} else {
	    params += "-1,";
	}
	if (tfName.getString().length() > 0) {
	    params += "'" + tfName.getString() + "'";
	} else {
	    params += "''";
	}
	listPanel.refresh(params);
	tabbedPane.setSelectedIndex(0);
	parentMain.setTitleAt(1, "Nueva Cuenta");
	switch (idAccountType) {
	    case 1000 :
		//ACTIVO
		btnPrint.setToolTipText("Imprimir el listado de Cuentas Contables del Tipo ACTIVO");
		break;
	    case 2000 :
		//PASIVO
		btnPrint.setToolTipText("Imprimir el listado de Cuentas Contables del Tipo PASIVO");
		break;
	    case 3000 :
		//PATRIMONIO NETO
		btnPrint.setToolTipText("Imprimir el listado de Cuentas Contables del Tipo PATRIMONIO NETO");
		break;
	    case 4000 :
		//RECURSOS
		btnPrint.setToolTipText("Imprimir el listado de Cuentas Contables del Tipo RECURSOS");
		break;
	    case 5000 :
		//EGRESOS
		btnPrint.setToolTipText("Imprimir el listado de Cuentas Contables del Tipo EGRESOS");
		break;
	    default :
		//TODOS
		btnPrint.setToolTipText("Imprimir el listado de Cuentas Contables de TODOS los Tipos");
		break;
	}
	btnModify.setToolTipText("Modificar la Cuenta seleccionada");
	btnNew.setToolTipText("Agregar una nueva Cuenta");
	btnDelete.setToolTipText("Tilde una o varias Cuentas para borrar");
        btnNew.setEnabled(true);
    }

    private void loadObjectSelected() {
	if (!dataRow.isEmpty()) {
	    if (account == null) {
		account = new Account();
	    }
	    account.setIDAccount(Integer.parseInt("" + dataRow.elementAt(0)));
	    account.setCode(Integer.parseInt("" + dataRow.elementAt(1)));
	    account.setName("" + dataRow.elementAt(2));
	    account.setIdParent(Integer.parseInt("" + dataRow.elementAt(3)));
	    account.setIdAccountType(Integer.parseInt("" + dataRow.elementAt(5)));
	    account.setInitialAmountcolor("" + dataRow.elementAt(7));
	    account.setAssignedAmountToACColor("" + dataRow.elementAt(8));
	    account.setAssignedAmountToCCColor("" + dataRow.elementAt(9));
	    account.setDescription("" + dataRow.elementAt(10));
	    account.setIsImputable(dataRow.elementAt(11).toString().equals("SI") ? true : false);
	    account.setIsHeritage(dataRow.elementAt(12).toString().equals("SI") ? true : false);
	    account.setChildrenNumber(Integer.parseInt("" + dataRow.elementAt(13)));
	    account.setIsDeduction(dataRow.elementAt(14).toString().equals("SI") ? true : false);
	    account.setIsCash(dataRow.elementAt(15).toString().equals("SI") ? true : false);
	    account.setIsValues(dataRow.elementAt(16).toString().equals("SI") ? true : false);
	    account.setSubAccount(dataRow.elementAt(18).toString().equals("SI"));
	    btnModify.setToolTipText("<html><center><u>Modificar Cuenta</u><br>" + account.getName() + "</center></html>");
	    btnNew.setToolTipText("<html><center><u>Agregar nueva Cuenta</u><br>" + account.getName() + "</center></html>");
	    btnModify.setEnabled(account.getCode() % 1000 != 0);
	} else {
	    btnModify.setToolTipText("Modificar la Cuenta seleccionada");
	    btnNew.setToolTipText("Agregar una nueva Cuenta");
	}
    }

    private TreeSelectionListener setTreeSelectionListener() {
	TreeSelectionListener treeSelectionListener = new TreeSelectionListener() {

		public void valueChanged(TreeSelectionEvent evt) {
		    TreePath paths = evt.getPath();
		    DefaultMutableTreeNode node = (DefaultMutableTreeNode)paths.getLastPathComponent();
		    if ((Account.class.isInstance(node.getUserObject()))){
			account = (Account)node.getUserObject();    
			DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode)node.getParent();
			if (Account.class.isInstance(parentNode.getUserObject())) {
			    parentAccount = (Account)parentNode.getUserObject();
			    btnModify.setToolTipText("<html><center><u>Modificar Cuenta</u><br>" + account.getName() + "</center></html>");
			    btnNew.setToolTipText("<html><center><u>Agregar nueva Cuenta</u><br>" + account.getName() + "</center></html>");
			} else {
			    //System.out.println(parentNode.getUserObject());
			    //parentAccount = (Account)parentNode.getUserObject();
			    btnModify.setToolTipText("Modificar la Cuenta seleccionada");
			    btnNew.setToolTipText("Agregar una nueva Cuenta");
			}
		    } else {
			if (parentPanel == null) {
			    account = null;
			}
		    }
		}

	    };
	return treeSelectionListener;
    }

    public void drawTreeAccount(Account _parentAccount) {
	try {
	    accountTree = accountsGroup.getTree(_parentAccount);
	    if (accountTree != null) {
		accountTree.setRowHeight(0);
		accountTree.setRowHeight(16);
		jspTree.getViewport().add(accountTree, null);
		accountTree.addTreeSelectionListener(setTreeSelectionListener());
		accountTree.setCellRenderer(new AccountTreeRenderer("(" + _parentAccount.getCode() + ") - " + _parentAccount.getName()));
		accountTree.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
			    if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
				//if(account == null){
				loadObjectSelected();
				//}
				if (account != null) {
				    loadMgmt();
				    accountTree.expandPath(accountTree.getSelectionPath());
				}
			    } else {
				if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
				    TreePath paths = accountTree.getSelectionPath();
				    if (paths != null) {
					DefaultMutableTreeNode node = (DefaultMutableTreeNode)paths.getLastPathComponent();
					if ((Account.class.isInstance(node.getUserObject()))) {
					    account = (Account)node.getUserObject();
					    selectedGrid();
					    System.out.println("code: " + account.getCode());
					}
				    }
				}
			    }
			}

		    });
	    } else
		Advisor.messageBox(Environment.lang.getProperty("LoadError"), "Error");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    private void selectedGrid(){
	boolean encontrado = false;
	listPanel.selectAllItems(true);
	Vector selecteds = listPanel.getSelectedsVector();
	Vector aux = new Vector();
	int i = 0;
	while(!encontrado && (i < selecteds.size())){
	    aux = (Vector)selecteds.elementAt(i);
	    if(aux.elementAt(1).toString().toUpperCase().equals((""+account.getCode()).toUpperCase())){
		encontrado = true;
	    }else{
		i++;
	    }
	}
	listPanel.selectAllItems(false);
	if(encontrado){
	    listPanel.getTable().getSelectionModel().setSelectionInterval(i, i);
	    listPanel.getTable().scrollRectToVisible(listPanel.getTable().getCellRect(i, 0, true));
	    loadObjectSelected();
	}
    }

    private void newAccount() {
	//parentMain.getAccountMgmt().clearData();
	parentMain.getAccountMgmt().setParentAccount(getParentAccount());
	//parentMain.newAccount();
	parentMain.getAccountMgmt().setModifying(false);
	parentMain.setSelectedTab(1);
    }

    private void loadMgmt() {
	if (account.getCode() % 1000 != 0) {
	    parentAccount = new Account(account.getIdParent());
	    parentAccount.retrieveData();
	    parentMain.setSelectedTab(1);
	    parentMain.setTitleAt(1, "Modificar Cuenta");
	    parentMain.getAccountMgmt().setParentAccount(parentAccount);
	    parentMain.getAccountMgmt().setAccount(account);
	    parentMain.getAccountMgmt().setModifying(true);
	} else {
	    Advisor.messageBox("No es posible modificar datos de la cuenta seleccionada", "Aviso");
	}
    }

    private void setParent(int _idAccount, boolean _isHeritage, String _name) {
	if (parentPanel == null) {
	    account = null;
	}
	parentAccount = new Account();
	parentAccount.setIDAccount(_idAccount);
	parentAccount.setName(_name);
	parentAccount.setIdAccountType(_idAccount);
	parentAccount.setIsHeritage(_isHeritage);
    }

    public void setFilter(int _accountType) {
	account = null;
	switch (_accountType) {
	    case AccountTypes.RESOURCE :
		setParent(AccountTypes.RESOURCE, false, rbtnResource.getText());
		break;
	    case AccountTypes.EXPENDITURE :
		setParent(AccountTypes.EXPENDITURE, false, rbtnEgress.getText());
		break;
	    case AccountTypes.ACTIVE :
		setParent(AccountTypes.ACTIVE, true, rbtnActive.getText());
		break;
	    case AccountTypes.PASSIVE :
		setParent(AccountTypes.PASSIVE, true, rbtnPassive.getText());
		break;
	    case AccountTypes.NETHERITAGE :
		setParent(AccountTypes.NETHERITAGE, true, rbtnNetHeritage.getText());
		break;
	    default :
		setParent(0, false, "Cuentas");
		break;
	}
    }

    private void rbtnActive_actionPerformed(ActionEvent e) {
	setFilter(AccountTypes.ACTIVE);
	refresh();
    }

    private void rbtnPassive_actionPerformed(ActionEvent e) {
	setFilter(AccountTypes.PASSIVE);
	refresh();
    }

    private void rbtnNetHeritage_actionPerformed(ActionEvent e) {
	setFilter(AccountTypes.NETHERITAGE);
	refresh();
    }

    private void rbtnResource_actionPerformed(ActionEvent e) {
	setFilter(AccountTypes.RESOURCE);
	refresh();
    }

    private void rbtnEgress_actionPerformed(ActionEvent e) {
	setFilter(AccountTypes.EXPENDITURE);
	refresh();
    }

    private void rbtnAll_actionPerformed(ActionEvent e) {
	setFilter(0);
	refresh();
    }

    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void btnExpand_actionPerformed(ActionEvent e) {
	Proced.expandAll(accountTree, accountTree.getSelectionPath(), true);
    }

    private void btnCollapse_actionPerformed(ActionEvent e) {
	Proced.expandAll(accountTree, accountTree.getSelectionPath(), false);
    }

    public Account getAccount() {
	return account;
    }

    public Account getParentAccount() {
	return parentAccount;
    }

    private void btnNew_actionPerformed(ActionEvent e) {
	newAccount();
    }

    private void selectAccount() {
	if (account.getIDAccount() != -1) {
	    if (parentPanel != null) {
		parentPanel.reload();
	    }
	    parentMain.getParentInternalFrame().close();
	} else {
	    Advisor.messageBox("Debe seleccionar una Cuenta", "Cuenta requerida");
	}
    }

    private void btnSelect_actionPerformed(ActionEvent e) {
	selectAccount();
    }

    public void setAccount(Account _account) {
	this.account = _account;
	btnClose.setVisible(true);
	btnSelect.setVisible(true);
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	parentMain.getParentInternalFrame().close();
    }

    public void setParentPanel(BasicPrimitivePanel parentPanel) {
	this.parentPanel = parentPanel;
    }

    private void btnViewAll_actionPerformed(ActionEvent e) {
	drawTreeAccount(parentAccount);
    }

    private void btnModify_actionPerformed(ActionEvent e) {
	loadMgmt();
    }

    private void btnPrint_actionPerformed(ActionEvent e) {
	//AccountsListReport report = new AccountsListReport("xml/accountsList.xml", parentAccount.getIDAccount());
	BasicReport report = new BasicReport(AccountsListReport.class.getResource("xml/accountsList.xml"));
	report.addTableModel("accounting.getallaccounts", parentAccount.getIDAccount() );
	report.doReport();
    }

    private void btnDelete_actionPerformed(ActionEvent e) {
	Vector deleteList = listPanel.getSelectedsID();
	if (deleteList.size() > 0) {
	    if (Advisor.question("Atención", "Está por borrar " + deleteList.size() + " cuenta(s), ¿Está seguro?")) {
		String _values = "'";
		for (int i = 0; i < deleteList.size() - 1; i++) {
		    _values += deleteList.elementAt(i) + ",";
		}
		_values += deleteList.elementAt(deleteList.size() - 1) + "'";
		if (!LibSQL.getBoolean("accounting.delaccounts", _values)) {
		    if (deleteList.size() == 1) {
			Advisor.messageBox("Error al borrar la cuenta seleccionada", "Error");
		    } else {
			Advisor.messageBox("Error al borrar la(s) cuenta(s), intente seleccionando menos cuentas", "Error");
		    }
		} else {
		    refresh();
		}
	    }
	} else {
	    Advisor.messageBox("No ha seleccionado nada para borrar", "Error");
	}
    }
    
    private void controlBotones(){
	btnDelete.setEnabled(false);
	btnModify.setEnabled(false);
    }
}
