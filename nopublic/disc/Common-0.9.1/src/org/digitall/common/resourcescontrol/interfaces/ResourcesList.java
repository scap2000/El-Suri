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
 * ResourcesList.java
 *
 * */
package org.digitall.common.resourcescontrol.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetContext;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.cashflow.classes.ExpenditureAccountsGroup;
import org.digitall.common.cashflow.interfaces.expenditureaccounts.ExpenditureAccountTreeLabel;
import org.digitall.common.cashflow.interfaces.expenditureaccounts.ExpenditureAccountTreeRender;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.filemanager.ExpandTreePathThread;
import org.digitall.common.reports.BasicReport;
import org.digitall.common.resourcescontrol.classes.ExportResources;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.common.resourcescontrol.classes.Skills;
import org.digitall.common.resourcescontrol.interfaces.PersonsList;
import org.digitall.common.resourcescontrol.interfaces.ResourcesComposerList;
import org.digitall.common.resourcescontrol.interfaces.ResourcesMgmt;
import org.digitall.common.resourcescontrol.interfaces.SkillList;
import org.digitall.common.resourcesrequests.interfaces.resourcesrequests.NewResourcesList;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.grid.TableTransferHandler;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;

public class ResourcesList extends BasicPrimitivePanel implements DropTargetListener {

    private CBInput cbSkillToProvider = new CBInput(CachedCombo.SKILLPROVIDER_TABS, "ResourceTypes");
    private TFInput tfFind = new TFInput(DataTypes.STRING, "FindResource", false);
    private int[] sizeColumnList = { 209, 54, 81, 162, 193 };
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(1000, sizeColumnList, "Listado de Recursos", dataRow);
    
    private FindButton btnFind = new FindButton();
    private CloseButton btnClose = new CloseButton();
    private AddButton btnAdd = new AddButton();
    private AcceptButton btnCompose = new AcceptButton();
    private DeleteButton btnDelete = new DeleteButton();
    private ModifyButton btnModify = new ModifyButton();
    private AcceptButton btnHumanResources = new AcceptButton();
    private AcceptButton btnSkills = new AcceptButton();
    private PrintButton btnPrintResourcesByAccount = new PrintButton();
    private PrintButton btnPrintResourceList = new PrintButton();
    private AcceptButton btnImport = new AcceptButton();
    
    private JTree treeExpenditureAccount;
    private BasicPanel panelTree = new BasicPanel();
    private BasicScrollPane jsptree = new BasicScrollPane();
    private ExpenditureAccountsGroup expenditureAccountsVector = new ExpenditureAccountsGroup();
    private ExpenditureAccount selectedExpenditureAccount;
    private ResourcesMgmt resourcesMgmt;
    private ResourcesComposerList resourcesComposerList;
    private boolean loadList = true;
    private TreePath dropPath = null;
    private TreePath prevDropPath = null;
    private ExpandTreePathThread expandThread;
    private NewResourcesList newResourcesList;
    private BasicCheckBox chkWOExpenditureAccount = new BasicCheckBox();
    
    private Resource selectedResource;

    public ResourcesList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout(null);
	this.setSize(new Dimension(710, 515));
	this.setPreferredSize(new Dimension(710, 515));
	panelTree.setBounds(new Rectangle(5, 0, 700, 200));
	panelTree.setLayout(null);
	panelTree.setPreferredSize(new Dimension(1, 200));
	jsptree.setBounds(new Rectangle(10, 25, 410, 165));
	cbSkillToProvider.setBounds(new Rectangle(435, 75, 265, 35));
	tfFind.setBounds(new Rectangle(435, 25, 265, 35));
	listPanel.setBounds(new Rectangle(5, 200, 700, 270));
	btnHumanResources.addActionListener(new ActionListener() {

					 public void actionPerformed(ActionEvent e) {
					     btnHumanResources_actionPerformed(e);
					 }

				     }
	);
	btnHumanResources.setText("Recursos\nHumanos");
	btnSkills.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnSkills_actionPerformed(e);
				 }

			     }
	);
	btnImport.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnImport_actionPerformed(e);
				 }

			     }
	);
	btnSkills.setText("Calificaciones\nde Habilidades");
	btnFind.setBounds(new Rectangle(670, 125, 30, 25));
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
	btnPrintResourcesByAccount.addActionListener(new ActionListener() {

						  public void actionPerformed(ActionEvent e) {
						      btnPrint_actionPerformed(e);
						  }

					      }
	);
	btnPrintResourceList.addActionListener(new ActionListener() {

					    public void actionPerformed(ActionEvent e) {
						btnPrintResourceList_actionPerformed(e);
					    }

					}
	);
	btnCompose.setText("Componer\nRecurso");
	btnCompose.setBounds(new Rectangle(460, 525, 90, 25));
	btnCompose.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      btnCompose_actionPerformed(e);
				  }

			      }
	);
	jsptree.getViewport().add(treeExpenditureAccount, null);
	panelTree.add(chkWOExpenditureAccount, null);
	panelTree.add(jsptree, null);
	panelTree.add(tfFind, null);
	panelTree.add(cbSkillToProvider, null);
	panelTree.add(btnFind, null);
	btnModify.setBounds(new Rectangle(610, 525, 40, 25));
	btnModify.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnModify_actionPerformed(e);
				 }

			     }
	);
	btnDelete.setBounds(new Rectangle(610, 525, 40, 25));
	chkWOExpenditureAccount.setText("Sin discriminar por Tipos de Gasto");
	chkWOExpenditureAccount.setBounds(new Rectangle(435, 125, 230, 25));
	add(panelTree, BorderLayout.NORTH);
	add(listPanel, BorderLayout.CENTER);
	if (newResourcesList != null) {
	    addButton(btnClose);
	}
	addButton(btnDelete);
	addButton(btnModify);
	addButton(btnAdd);
	addButton(btnPrintResourcesByAccount);
	addButton(btnPrintResourceList);
	cbSkillToProvider.autoSize();
	listPanel.getTable().setDragEnabled(true);
	listPanel.getTable().setTransferHandler(new TableTransferHandler());
	cbSkillToProvider.getCombo().addItem("Todos", "-1");
	cbSkillToProvider.setSelectedID("-1");
	tfFind.getTextField().addKeyListener(new KeyAdapter() {

					  public void keyReleased(KeyEvent e) {
					      if (e.getKeyCode() == KeyEvent.VK_ENTER)
						  refresh();
					  }

				      }
	);
	setHeaderList();
	btnAdd.setToolTipText("Agregar nuevo recurso");
	btnPrintResourcesByAccount.setToolTipText("Imprimir lista de Recursos por Cuentas");
	btnPrintResourceList.setToolTipText("Imprimir Padron de Recursos");
	btnModify.setToolTipText("Modificar el recurso seleccionado");
	btnModify.setEnabled(false);
	btnDelete.setToolTipText("Borrar el recurso seleccionado");
	btnDelete.setEnabled(false);
	btnDelete.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnDelete_actionPerformed(e);
				 }

			     }
	);
	btnCompose.setToolTipText("Abre la ventana de Composición de Recursos");
	btnHumanResources.setToolTipText("Abre la ventana de Administración de Recursos Humanos");
	btnSkills.setToolTipText("Abre la ventana de Administración de Habilidades");
	btnImport.setToolTipText("Abre la ventana para la importacion de materiales");
	btnImport.setText("Accion");
        panelTree.setBorder(BorderPanel.getBorderPanel("Tipos de Gastos"));
	drawTreeExpenditureAccount();
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().getGeneralButtons().addButton(btnCompose);
	getParentInternalFrame().getGeneralButtons().addButton(btnHumanResources);
	getParentInternalFrame().getGeneralButtons().addButton(btnSkills);
	getParentInternalFrame().getGeneralButtons().addButton(btnImport);
	getParentInternalFrame().setInfo("Busque recursos por Nombre, Tipos de Gasto y/o Clasificación para Ver, Modificar o Agregar uno nuevo");
    }

    public void refresh() {
	btnModify.setEnabled(false);
	btnDelete.setEnabled(false);
	btnModify.setToolTipText("Modificar el recurso seleccionado");
	btnDelete.setToolTipText("Borrar el recurso seleccionado");
	if (loadList) {
	    int resourceType = -1;
	    int expenditureAccount = -1;
	    resourceType = Integer.parseInt(cbSkillToProvider.getSelectedValue().toString());
	    if (chkWOExpenditureAccount.isSelected()) {
		listPanel.setTitle("Listado de Recursos sin TIPO DE GASTOS");
	    } else {
		listPanel.setTitle("Listado de Recursos");
	    }
	    if (selectedExpenditureAccount != null && !chkWOExpenditureAccount.isSelected()) {
		expenditureAccount = selectedExpenditureAccount.getIDExpenditureAccount();
		listPanel.setTitle("Listado de Recursos por " + selectedExpenditureAccount.getName().toUpperCase());
	    }
	    String params = "'" + tfFind.getString() + "'," + resourceType + "," + expenditureAccount;
	    listPanel.refresh(params);
	    if (treeExpenditureAccount != null) {
		treeExpenditureAccount.setDragEnabled(true);
	    }
	}
    }

    private TreeSelectionListener setTreeSelectionListener() {
	TreeSelectionListener treeSelectionListener = new TreeSelectionListener() {

		public void valueChanged(TreeSelectionEvent evt) {
		    TreePath paths = evt.getPath();
		    DefaultMutableTreeNode node = (DefaultMutableTreeNode)paths.getLastPathComponent();
		    if (ExpenditureAccount.class.isInstance(node.getUserObject())) {
			if (loadList) {
			    selectedExpenditureAccount = (ExpenditureAccount)node.getUserObject();
			    chkWOExpenditureAccount.setSelected(false);
			}
		    } else {
			selectedExpenditureAccount = null;
			chkWOExpenditureAccount.setSelected(true);
		    }
		    refresh();
		}

	    }
	;
	return treeSelectionListener;
    }

    public void drawTreeExpenditureAccount() {
	try {
	    treeExpenditureAccount = expenditureAccountsVector.getTree(false);
	    DropTarget target = new DropTarget(treeExpenditureAccount, this);
	    if (treeExpenditureAccount != null) {
		treeExpenditureAccount.setRowHeight(0);
		treeExpenditureAccount.setRowHeight(16);
		jsptree.getViewport().add(treeExpenditureAccount, null);
		treeExpenditureAccount.addTreeSelectionListener(setTreeSelectionListener());
		treeExpenditureAccount.setCellRenderer(new ExpenditureAccountTreeRender(ExpenditureAccountTreeLabel.EXPENDITUREACCOUNT));
		treeExpenditureAccount.setDragEnabled(true);
	    } else
		Advisor.messageBox(Environment.lang.getProperty("LoadError"), "Error");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Name"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Unit"));
	headerList.addElement(Environment.lang.getProperty("TotalStock"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Type"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("ExpenditureAccount"));
	headerList.addElement("*");
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
					       if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
					       } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						   loadMgmt(false);
					       }
					   }

				       }
	);
	String params = "'" + tfFind.getString() + "',-1, -1";
	listPanel.setParams("resourcescontrol.getAllResources", params, headerList);
    }

    private void loadObjectSelected() {
	if (!dataRow.isEmpty()) {
	    selectedResource = new Resource();
	    selectedResource.setIdResource(Integer.parseInt("" + dataRow.elementAt(0)));
	    selectedResource.setName("" + dataRow.elementAt(1));
	    selectedResource.setIdUnit(Integer.parseInt("" + dataRow.elementAt(2)));
	    selectedResource.setTotalStock(Double.parseDouble("" + dataRow.elementAt(4)));
	    selectedResource.setSkillToProvider(new Skills(Integer.parseInt("" + dataRow.elementAt(5))));
	    ExpenditureAccount eType = new ExpenditureAccount();
	    eType.setIDExpenditureAccount(Integer.parseInt("" + dataRow.elementAt(7)));
	    eType.setName("" + dataRow.elementAt(8));
	    selectedResource.setExpenditureAccount(eType);
	    selectedResource.setDescription("" + dataRow.elementAt(10));
	    selectedResource.setLifetime(Integer.parseInt("" + dataRow.elementAt(11)));
	    selectedResource.setIdLifeTimeType(Integer.parseInt("" + dataRow.elementAt(12)));
            String distinguible = dataRow.elementAt(15).toString();
            if (distinguible.equals("SI"))  {
                selectedResource.setDistinguishable(true);
            } else  {
                selectedResource.setDistinguishable(false);
            }
	    selectedResource.setDestined(dataRow.elementAt(16).toString());
	    btnModify.setToolTipText("<html><center><u>Modificar Recurso</u><br>" + selectedResource.getName() + "</center></html>");
	    btnModify.setEnabled(true);
	    btnDelete.setToolTipText("<html><center><u>Borrar Recurso</u><br>" + selectedResource.getName() + "</center></html>");
	    btnDelete.setEnabled(true);
	} else {
	    btnModify.setToolTipText("Modificar el recurso seleccionado");
	    btnModify.setEnabled(false);
	    btnDelete.setToolTipText("Borrar el recurso seleccionado");
	    btnDelete.setEnabled(false);
	}
    }

    public void setFindResource(Resource _resource) {
	tfFind.setValue(_resource.getName());
	selectedExpenditureAccount = null;
	chkWOExpenditureAccount.setSelected(true);
	refresh();
    }

    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void btnCompose_actionPerformed(ActionEvent e) {
	resourcesComposerList = new ResourcesComposerList();
	resourcesComposerList.setResource(selectedResource);
	ExtendedInternalFrame resourcesComposerListContainer = new ExtendedInternalFrame("Componentes del recurso: " + selectedResource.getName().toUpperCase());
	resourcesComposerListContainer.setCentralPanel(resourcesComposerList);
	resourcesComposerListContainer.show();
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	if (newResourcesList != null) {
	    newResourcesList.refresh();
	    getParentInternalFrame().close();
	}
    }

    private void loadMgmt(boolean _addAction) {
	if (selectedExpenditureAccount != null || !_addAction) {
	    if (_addAction) {
		selectedResource = new Resource();
		selectedResource.setExpenditureAccount(selectedExpenditureAccount);
	    }
	    resourcesMgmt = new ResourcesMgmt();
	    resourcesMgmt.setResource(selectedResource);
	    resourcesMgmt.setParentList(this);
	    ExtendedInternalFrame resourcesMgmtContainer = new ExtendedInternalFrame("Agregar/Modificar");
	    resourcesMgmtContainer.setCentralPanel(resourcesMgmt);
	    resourcesMgmtContainer.show();
	} else {
	    Advisor.messageBox("Debe seleccionar un Tipo de Gasto", "Seleccionar Tipo de Gasto");
	}
    }

    private void btnAdd_actionPerformed(ActionEvent e) {
	loadMgmt(true);
    }

    private void btnModify_actionPerformed(ActionEvent e) {
	loadMgmt(false);
    }

    public void dragExit(DropTargetEvent x) {
	loadList = true;
    }

    public void drop(DropTargetDropEvent dropTargetDragEvent) {
	Point pt = dropTargetDragEvent.getLocation();
	DropTargetContext dtc = dropTargetDragEvent.getDropTargetContext();
	JTree tree = (JTree)dtc.getComponent();
	TreePath parentpath = tree.getClosestPathForLocation(pt.x, pt.y);
	DefaultMutableTreeNode parent = (DefaultMutableTreeNode)parentpath.getLastPathComponent();
	try {
	    if (ExpenditureAccount.class.isInstance(parent.getUserObject())) {
		selectedResource.setExpenditureAccount((ExpenditureAccount)parent.getUserObject());
		if (selectedResource.updateExpenditureAccount() > -1) {
		    loadList = true;
		    refresh();
		}
		dropTargetDragEvent.dropComplete(true);
	    } else {
		dropTargetDragEvent.rejectDrop();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    dropTargetDragEvent.rejectDrop();
	}
    }

    public void dropActionChanged(DropTargetDragEvent dropTargetDragEvent) {

    }

    public void dragOver(DropTargetDragEvent dropTargetDragEvent) {
	dropPath = getNodeForEvent(dropTargetDragEvent);
	DefaultMutableTreeNode nodeTarget = (DefaultMutableTreeNode)dropPath.getLastPathComponent();
	if (ExpenditureAccount.class.isInstance(nodeTarget.getUserObject())) {
	    loadList = false;
	    if (dropPath != prevDropPath) {
		expandThread = new ExpandTreePathThread(treeExpenditureAccount);
		expandThread.setPath(dropPath);
		expandThread.start();
		prevDropPath = dropPath;
	    }
	    treeExpenditureAccount.setSelectionPath(prevDropPath);
	    dropTargetDragEvent.acceptDrag(dropTargetDragEvent.getDropAction());
	} else {
	    dropTargetDragEvent.rejectDrag();
	    loadList = true;
	}
    }

    public void dragEnter(DropTargetDragEvent dtde) {
    }

    private TreePath getNodeForEvent(DropTargetDragEvent dtde) {
	Point p = dtde.getLocation();
	DropTargetContext dtc = dtde.getDropTargetContext();
	JTree tree = (JTree)dtc.getComponent();
	return tree.getClosestPathForLocation(p.x, p.y);
    }

    public void setNewResourcesList(NewResourcesList newResourcesList) {
	this.newResourcesList = newResourcesList;
	btnClose.setEnabled(true);
    }

    private void btnHumanResources_actionPerformed(ActionEvent e) {
	PersonsList personsList = new PersonsList();
	ExtendedInternalFrame personsListContainer = new ExtendedInternalFrame("Recursos Humanos");
	personsListContainer.setCentralPanel(personsList);
	personsListContainer.show();
    }

    private void btnSkills_actionPerformed(ActionEvent e) {
	SkillList skillsList = new SkillList();
	ExtendedInternalFrame skillsListContainer = new ExtendedInternalFrame("Habilidades");
	skillsListContainer.setCentralPanel(skillsList);
	skillsListContainer.show();
    }
    private void btnImport_actionPerformed(ActionEvent e) {
	ExportResources er = new ExportResources();
	er.setVisible(true);
    }
    

    private void btnPrint_actionPerformed(ActionEvent e) {
	BasicReport report = new BasicReport(ResourcesList.class.getResource("xml/ResourcesList.xml"));
	report.addTableModel("resourcescontrol.xmlGetResourcesList", "");
	report.doReport();
    }

    private void btnPrintResourceList_actionPerformed(ActionEvent e) {
	BasicReport report = new BasicReport(ResourcesList.class.getResource("xml/Resources.xml"));
	report.addTableModel("resourcescontrol.xmlGetResources", "");
	report.doReport();
    }

    private void btnDelete_actionPerformed(ActionEvent e) {
	if (Advisor.question("Atención", "¿Está seguro de borrar el recurso " + selectedResource.getName() + "?")) {
	    if (selectedResource.delete()) {
		refresh();
	    } else {
		Advisor.messageBox("Error al intentar borrar el recurso", "Error");
	    }
	}
    }

    public GridPanel getListPanel() {
	return listPanel;
    }
}
