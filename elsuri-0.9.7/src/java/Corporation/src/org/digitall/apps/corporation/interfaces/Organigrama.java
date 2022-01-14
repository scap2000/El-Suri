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
 * Organigrama.java
 *
 * */
package org.digitall.apps.corporation.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetContext;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Enumeration;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.digitall.apps.corporation.report.DependenciesReport;
import org.digitall.common.resourcescontrol.interfaces.PersonsList;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.JEntry;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.BasicTabbedPane;
import org.digitall.lib.components.basic.BasicTextField;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.misc.TableTransferHandler;
import org.digitall.lib.sql.LibSQL;

//public class Organigrama extends BasicDialog implements ActionListener, ItemListener, ListSelectionListener, DropTargetListener {

public class Organigrama extends BasicPrimitivePanel implements ActionListener, ItemListener, ListSelectionListener, DropTargetListener {

    private BasicTabbedPane jTabbedPane1 = new BasicTabbedPane();
    private BasicPanel jPanel1 = new BasicPanel();
    private BasicPanel jPanel2 = new BasicPanel();
    private BasicPanel jPanel3 = new BasicPanel();
    private BasicPanel jPanelIndu = new BasicPanel();
    private BasicScrollPane jScrollPane1 = new BasicScrollPane();
    private BasicTextField tfDependencyName = new BasicTextField();
    private BasicTextField tfSubdependencyName = new BasicTextField();
    private BasicTextField tfSearch = new BasicTextField();
    private BasicTextField tfLevel = new BasicTextField();
    private BasicTextField iddep = new BasicTextField();
    private BasicLabel lblMainLabel = new BasicLabel();
    private BasicLabel label2 = new BasicLabel();
    private BasicLabel label3 = new BasicLabel();
    private BasicLabel lblSubdependencyName = new BasicLabel();
    private BasicLabel lblDependencyName = new BasicLabel();
    private BasicLabel lblIdentityCard = new BasicLabel();
    private BasicLabel lblFirstName = new BasicLabel();
    private BasicLabel lblLastName = new BasicLabel();
    private BasicLabel lblFile = new BasicLabel();
    private BasicButton btnAddDependency = new BasicButton();
    private BasicButton btnModDependency = new BasicButton();
    private BasicButton btnDelDependency = new BasicButton();
    private BasicButton btnAddSubdependency = new BasicButton();
    private BasicButton btnModSubdependency = new BasicButton();
    private BasicButton btnDelSubdependency = new BasicButton();
    private BasicButton btnAccept = new BasicButton();
    private BasicButton btnCancel = new BasicButton();
    private BasicButton btnSelDependency = new BasicButton();
    private BasicButton btnPrint = new BasicButton();
    private BasicButton btnPeople = new BasicButton();
    private BasicButton btnExpandAll = new BasicButton();
    private BasicButton btnCollapseAll = new BasicButton();
    private BasicButton btnSearch = new BasicButton();
    private BasicButton btnSearchPeople = new BasicButton("iconos/16x16/buscar.gif");
    private JTree jtOrganigrama = new JTree();
    private CBInput cbHierarchicalLevelDependency = new CBInput(0, "HierarchicalLevel", false);
    private CBInput cbHierarchicalLevelSubdependency = new CBInput(0, "HierarchicalLevel", false);   
    private int[] sizeColumnList = { 60, 90, 100, 130, 75, 95, 115, 110, 125, 75, 125, 75, 75 };
    private Vector dataRow = new Vector();
    private GridPanel peoplePanel = new GridPanel(30, sizeColumnList, "Personas", dataRow);
    private Vector peopleHeaderList = new Vector();
    private String lastName = "", firstName = "", identificationNumber = "", employeeFileNumber = "";
    private int[] columnSize = { 90, 505 };
    private Vector clothingDataRow = new Vector();
    private GridPanel clothingGridPanel = new GridPanel(30, columnSize, "Indumentaria", clothingDataRow);
    private Vector clothingHeader = new Vector();
    private String nivelj = "0";
    int iddependencia = 0;
    private BasicRadioButton jradelante = new BasicRadioButton();
    private BasicRadioButton jratras = new BasicRadioButton();
    private JEntry jtbnro = new JEntry();
    private JEntry jtbnombre = new JEntry();
    private JEntry jtbapellido = new JEntry();
    private JEntry jtbnrolegajo = new JEntry();
    private int dependenciaOrigen = -1;
    private Vector personalDep = new Vector();
    private Vector personalSubDeps = new Vector();
    private String personalTotal = "";

    public Organigrama(BasicTextField _iddep) {
	try {
	    iddep = _iddep;
	    btnSelDependency.setEnabled(true);
	    jtOrganigrama.setRowHeight(0);
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public Organigrama(int _dependenciaOrigen) {
	try {
	    dependenciaOrigen = _dependenciaOrigen;
	    iddep = new BasicTextField(dependenciaOrigen);
	    btnSelDependency.setEnabled(false);
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void reload() {
	try {
	    //jScrollPane1.getViewport().removeAll();
	    //jtOrganigrama.setRowHeight(0);
	    jtOrganigrama = crearArbolDependencias("dependencias","iddep","nombre",""+dependenciaOrigen);
	    //jtOrganigrama = createTree("iddep", "name", dependenciaOrigen);
	    jtOrganigrama.setCellRenderer(new MiRendererDeArbol());
	    jtOrganigrama.setSelectionPath(jtOrganigrama.getPathForLocation(0, 0));
	    jScrollPane1.getViewport().add(jtOrganigrama, null);
	    // ARBOL
	    jtOrganigrama.addTreeSelectionListener(new TreeSelectionListener() {

						/**
	                                 * AQUI DEBERIA IR EL CODIGO PARA ACTUALIZAR LA DATA DE LOS PANELES INFERIORES
	                                 * */
						public void valueChanged(TreeSelectionEvent evt) {
						    TreePath paths = evt.getPath();
						    String node = paths.getLastPathComponent().toString();
						    tfLevel.setText(node.substring(0, node.indexOf("-") - 1).trim());
						    tfDependencyName.setText(node.substring(node.indexOf("-") + 1, node.length()).trim());
						    iddependencia = Integer.parseInt(tfLevel.getText());
						    if (iddependencia == 0) {
							iddependencia = Integer.parseInt(node.substring(node.indexOf("(") + 1, node.indexOf(")")));
						    }
						    label2.setText("Dependencia: " + String.valueOf(getCantidadPersonasDependencia(iddependencia)) + " persona(s)");
						    label3.setText("Subdependencia: " + String.valueOf(getCantidadPersonasSubDependencias(iddependencia)) + " persona(s)");
						    if (iddependencia > 1) {
							btnModDependency.setEnabled(true);
							btnDelDependency.setEnabled(true);
							tfDependencyName.setEditable(true);
							btnAddDependency.setEnabled(true);
							btnAddSubdependency.setEnabled(true);
						    } else {
							btnModDependency.setEnabled(false);
							btnDelDependency.setEnabled(false);
							tfDependencyName.setEditable(false);
							btnAddDependency.setEnabled(false);
							btnAddSubdependency.setEnabled(false);
						    }
						    ActualizaTabla();
						}

					    }
	    );
	    // FIN ARBOL
	    DropTarget target = new DropTarget(jtOrganigrama, this);
	} catch (Exception e) {
	    e.printStackTrace();
	    Advisor.messageBox("No se pudo generar el organigrama", "Error");
	}
    }

    private void jbInit() throws Exception {
	//this.getContentPane().setLayout(null);
	this.setSize(new Dimension(758, 605));
	this.setTitle("Listado de dependencias");
	jScrollPane1.setBounds(new Rectangle(20, 35, 675, 260));
	btnSelDependency.setBounds(new Rectangle(20, 540, 105, 25));
	tfDependencyName.setBounds(new Rectangle(205, 60, 285, 20));
	tfSubdependencyName.setBounds(new Rectangle(205, 145, 360, 20));
	btnAddDependency.setText("+");
	btnAddDependency.setBounds(new Rectangle(370, 225, 44, 20));
	btnModDependency.setText("Modificar");
	btnModDependency.setBounds(new Rectangle(495, 58, 95, 25));
	btnModDependency.setMnemonic('M');
	btnDelDependency.setText("Borrar");
	btnDelDependency.setBounds(new Rectangle(595, 58, 75, 25));
	btnDelDependency.setMnemonic('B');
	btnAddSubdependency.setText("Agregar");
	btnAddSubdependency.setBounds(new Rectangle(575, 143, 90, 25));
	btnAddSubdependency.setMnemonic('g');
	btnModSubdependency.setText("M");
	btnModSubdependency.setMnemonic('M');
	btnModSubdependency.setBounds(new Rectangle(425, 280, 45, 20));
	btnDelSubdependency.setText("-");
	btnDelSubdependency.setBounds(new Rectangle(480, 280, 45, 20));
	btnAccept.setText("Aceptar");
	btnAccept.setMnemonic('A');
	btnAccept.setBounds(new Rectangle(335, 325, 90, 25));
	btnCancel.setText("Cerrar");
	btnCancel.setMnemonic('C');
	btnCancel.setBounds(new Rectangle(620, 540, 75, 25));
	tfLevel.setBounds(new Rectangle(165, 60, 35, 20));
	tfLevel.setEnabled(false);
	lblMainLabel.setText("Personal por Dependencias:");
	lblMainLabel.setBounds(new Rectangle(227, 5, 295, 25));
	lblMainLabel.setFont(new Font("Dialog", 1, 20));
	btnSelDependency.setText("Seleccionar");
	btnSelDependency.setMnemonic('S');
	jTabbedPane1.setBounds(new Rectangle(20, 300, 675, 235));
	jTabbedPane1.addChangeListener(new ChangeListener() {

				    public void stateChanged(ChangeEvent e) {
					jTabbedPane1_stateChanged(e);
				    }

				}
	);
	jPanel1.setLayout(null);
	jPanel2.setLayout(null);
	jtbapellido.requestFocusInWindow();
	jPanel1.add(cbHierarchicalLevelDependency, null);
	jPanel1.add(label3, null);
	jPanel1.add(cbHierarchicalLevelSubdependency, null);
	jPanel1.add(tfLevel, null);
	jPanel1.add(btnAddSubdependency, null);
	jPanel1.add(btnModDependency, null);
	jPanel1.add(btnDelDependency, null);
	jPanel1.add(tfSubdependencyName, null);
	jPanel1.add(tfDependencyName, null);
	jPanel1.add(label2, null);
	jPanel1.add(lblSubdependencyName, null);
	jPanel1.add(lblDependencyName, null);
	jTabbedPane1.addTab("Datos Principales", jPanel1);
	jPanel2.add(peoplePanel, null);
	jPanel2.add(jtbnro, null);
	jPanel2.add(btnSearchPeople, null);
	jPanel2.add(jtbnrolegajo, null);
	jPanel2.add(lblFile, null);
	jPanel2.add(lblIdentityCard, null);
	jPanel2.add(jtbnombre, null);
	jPanel2.add(lblFirstName, null);
	jPanel2.add(jtbapellido, null);
	jPanel2.add(lblLastName, null);
	jTabbedPane1.addTab("Personal", jPanel2);
	jTabbedPane1.addTab("Arbol", jPanel3);
	jPanelIndu.add(clothingGridPanel, null);
	clothingGridPanel.setBounds(new Rectangle(5, 5, 660, 200));
	/**
	 * PARA PODER VISUALIZAR LA PESTAÑA DE INDUMENTARIA,
	 * HAY QUE DESCOMENTAR DOS LINEAS: 
	 * 1) LA LINEA QUE SIGUE --> //jTabbedPane1.addTab("Indumentaria", jPanelIndu);
	 * 2) AL FINAL LA LINEA --> //setClothingHeader();
	 */
	//jTabbedPane1.addTab("Indumentaria", jPanelIndu);
	jPanel3.add(jratras, null);
	jPanel3.add(jradelante, null);
	jPanel3.add(tfSearch, null);
	jPanel3.add(btnSearch, null);
	jPanel3.add(btnExpandAll, null);
	jPanel3.add(btnCollapseAll, null);
	this.add(btnPeople, null);
	this.add(btnPrint, null);
	this.add(jTabbedPane1, null);
	this.add(lblMainLabel, null);
	this.add(jScrollPane1, null);
	this.add(btnCancel, null);
	this.add(btnSelDependency, BorderLayout.CENTER);
	btnAddDependency.addActionListener(this);
	btnModDependency.addActionListener(this);
	btnDelDependency.addActionListener(this);
	btnAddSubdependency.addActionListener(this);
	btnModSubdependency.addActionListener(this);
	btnDelSubdependency.addActionListener(this);
	btnAccept.addActionListener(this);
	btnCancel.addActionListener(this);
	btnSelDependency.addActionListener(this);
	tfDependencyName.setName("50");
	tfSubdependencyName.setName("50");
	btnModDependency.setEnabled(false);
	btnDelDependency.setEnabled(false);
	tfDependencyName.setEditable(false);
	btnAddDependency.setEnabled(false);
	btnAddSubdependency.setEnabled(false);
	btnPeople.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnPeople_actionPerformed(e);
				 }

			     }
	);
	btnSearch.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     bbuscar_actionPerformed(e);
				 }

			     }
	);
	btnExpandAll.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					bexpandall_actionPerformed(e);
				    }

				}
	);
	btnCollapseAll.addActionListener(new ActionListener() {

				      public void actionPerformed(ActionEvent e) {
					  bcollapseall_actionPerformed(e);
				      }

				  }
	);
	peoplePanel.getTable().setDragEnabled(true);
	peoplePanel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	peoplePanel.setTransferHandler(new TableTransferHandler());
	jradelante.setSelected(true);
	jratras.setBounds(new Rectangle(305, 65, 85, 25));
	jratras.setText("Atrás");
	jradelante.setBounds(new Rectangle(305, 40, 85, 25));
	jradelante.setText("Adelante");
	tfSearch.setBounds(new Rectangle(10, 40, 195, 25));
	btnSearch.setBounds(new Rectangle(210, 40, 88, 25));
	btnSearch.setText("Buscar");
	btnExpandAll.setBounds(new Rectangle(10, 10, 95, 25));
	jPanel3.setLayout(null);
	btnCollapseAll.setToolTipText("Cierra todos los subnodos");
	btnExpandAll.setToolTipText("Expande todos los subnodos");
	btnCollapseAll.setText("Colapsar");
	btnCollapseAll.setBounds(new Rectangle(110, 10, 95, 25));
	btnCollapseAll.addActionListener(new ActionListener() {

				      public void actionPerformed(ActionEvent e) {
					  bexpandall_actionPerformed(e);
				      }

				  }
	);
	btnExpandAll.setText("Expandir");
	label3.setHorizontalAlignment(SwingConstants.CENTER);
	btnPeople.setText("Personas");
	btnPeople.setMnemonic('P');
	btnPeople.setBounds(new Rectangle(230, 540, 95, 25));
	label2.setHorizontalAlignment(SwingConstants.CENTER);
	lblDependencyName.setSize(new Dimension(101, 15));
	lblDependencyName.setBounds(new Rectangle(205, 40, 52, 15));
	lblDependencyName.setText("Nombre:");
	lblSubdependencyName.setSize(new Dimension(52, 15));
	lblSubdependencyName.setBounds(new Rectangle(205, 125, 52, 15));
	lblSubdependencyName.setText("Nombre:");
	label3.setFont(new Font("Dialog", 1, 20));
	label3.setBounds(new Rectangle(10, 90, 660, 25));
	label3.setText("Subdependencia:");
	label2.setFont(new Font("Dialog", 1, 20));
	label2.setBounds(new Rectangle(10, 5, 660, 25));
	label2.setText("Dependencia:");
	btnPrint.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    bimprimir_actionPerformed(e);
				}

			    }
	);
	btnPrint.setBounds(new Rectangle(130, 540, 95, 25));
	btnPrint.setMnemonic('I');
	btnPrint.setText("Imprimir");
	loadComboHierarchicalLevelDependency();
	loadComboHierarchicalLevelSubdependency();
	ButtonGroup group = new ButtonGroup();
	peoplePanel.setBounds(new Rectangle(0, 35, 670, 175));
	jPanelIndu.setLayout(null);
	jtbapellido.addKeyListener(new KeyAdapter() {

				public void keyReleased(KeyEvent e) {
				    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					find();
				    }
				}

			    }
	);
	jtbnombre.addKeyListener(new KeyAdapter() {

			      public void keyReleased(KeyEvent e) {
				  if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				      find();
				  }
			      }

			  }
	);
	jtbnro.addKeyListener(new KeyAdapter() {

			   public void keyReleased(KeyEvent e) {
			       if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				   find();
			       }
			   }

		       }
	);
	jtbnrolegajo.addKeyListener(new KeyAdapter() {

				 public void keyReleased(KeyEvent e) {
				     if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					 find();
				     }
				 }

			     }
	);
	lblFirstName.setSize(new Dimension(52, 15));
	lblFile.setBounds(new Rectangle(445, 5, 44, 20));
	lblFile.setText("Legajo:");
	jtbnrolegajo.setBounds(new Rectangle(495, 5, 90, 20));
	btnSearchPeople.addActionListener(new ActionListener() {

				       public void actionPerformed(ActionEvent e) {
					   bbuscarPersona_actionPerformed(e);
				       }

				   }
	);
	btnSearchPeople.setText("Buscar");
	btnSearchPeople.setBounds(new Rectangle(590, 5, 75, 20));
	btnSearchPeople.setMnemonic('b');
	btnSearchPeople.setMargin(new Insets(2, 5, 2, 14));
	lblLastName.setBounds(new Rectangle(5, 5, 55, 20));
	lblLastName.setText("Apellido:");
	jtbapellido.setBounds(new Rectangle(65, 5, 90, 20));
	lblFirstName.setText("Nombre:");
	lblFirstName.setBounds(new Rectangle(160, 5, 52, 20));
	jtbnombre.setBounds(new Rectangle(215, 5, 90, 20));
	lblIdentityCard.setText("DNI:");
	lblIdentityCard.setBounds(new Rectangle(310, 5, 26, 20));
	jtbnro.setBounds(new Rectangle(345, 5, 90, 20));
	this.setBounds(new Rectangle(10, 10, 758, 600));
	group.add(jradelante);
	group.add(jratras);
	cbHierarchicalLevelDependency.setBounds(new Rectangle(10, 45, 150, 35));
	cbHierarchicalLevelSubdependency.setBounds(new Rectangle(10, 130, 190, 35));
	cbHierarchicalLevelDependency.autoSize();
	cbHierarchicalLevelSubdependency.autoSize();
	setPeopleHeaderList();
	btnSelDependency.setVisible(false);
	//setClothingHeader();
    }

    private void setPeopleHeaderList() {
	peopleHeaderList.removeAllElements();
	peopleHeaderList.addElement("*");
	peopleHeaderList.addElement("*");
	peopleHeaderList.addElement("*");
	peopleHeaderList.addElement(Environment.lang.getProperty("DocumentType"));
	peopleHeaderList.addElement(Environment.lang.getProperty("DocumentNumber"));
	peopleHeaderList.addElement(Environment.lang.getProperty("LastName"));
	peopleHeaderList.addElement(Environment.lang.getProperty("FirstName"));
	peopleHeaderList.addElement(Environment.lang.getProperty("EmployeeFileNumber"));
	peopleHeaderList.addElement(Environment.lang.getProperty("InternalEmployeeFileNumber"));
	peopleHeaderList.addElement("*");
	peopleHeaderList.addElement(Environment.lang.getProperty("CivilState"));
	peopleHeaderList.addElement("*");
	peopleHeaderList.addElement(Environment.lang.getProperty("Email"));
	peopleHeaderList.addElement("*");
	peopleHeaderList.addElement(Environment.lang.getProperty("Street"));
	peopleHeaderList.addElement(Environment.lang.getProperty("Number"));
	peopleHeaderList.addElement(Environment.lang.getProperty("Phone"));
	peopleHeaderList.addElement(Environment.lang.getProperty("Mobile"));
	peopleHeaderList.addElement(Environment.lang.getProperty("Nick"));
	peopleHeaderList.addElement("*");
	peoplePanel.getTable().addMouseListener(new MouseAdapter() {

					     public void mouseClicked(MouseEvent e) {
						 if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {

						 } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {

						 }
					     }

					 }
	);
	//peoplePanel.setParams("organigrama.getPeople", "'','','','',''",peopleHeaderList);
	peoplePanel.setParams("organigrama.getPersons", "'','','','',0", peopleHeaderList);
    }

    public void refreshPeoplePanel() {
	String params = "'" + lastName + "','" + firstName + "','" + identificationNumber + "','" + employeeFileNumber + "',0" + iddependencia;
	//System.out.println("SELECT organigrama.getPersons(" + params + ")");
	peoplePanel.refresh(params);
    }

    private void setClothingHeader() {
	clothingHeader.removeAllElements();
	clothingHeader.addElement(Environment.lang.getProperty("Quantity"));
	clothingHeader.addElement(Environment.lang.getProperty("Clothing"));
	clothingGridPanel.getTable().addMouseListener(new MouseAdapter() {

						   public void mouseClicked(MouseEvent e) {
						       if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {

						       } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {

						       }
						   }

					       }
	);
	clothingGridPanel.setParams("inventario.getClothing", "''", clothingHeader);
    }

    public void refreshClothingPanel() {
	String params = "" + personalTotal;
	clothingGridPanel.refresh(params);
    }

    public void loadComboHierarchicalLevelDependency() {
	String param = "";
	cbHierarchicalLevelDependency.loadJCombo(LibSQL.exFunction("organigrama.getAllDependencies", param));
    }

    public void loadComboHierarchicalLevelSubdependency() {
	String param = "";
	cbHierarchicalLevelSubdependency.loadJCombo(LibSQL.exFunction("organigrama.getAllDependencies", param));
    }

    private TreeNode getNodeForEvent(DropTargetDragEvent dtde) {
	Point p = dtde.getLocation();
	DropTargetContext dtc = dtde.getDropTargetContext();
	JTree tree = (JTree)dtc.getComponent();
	TreePath path = tree.getClosestPathForLocation(p.x, p.y);
	return (TreeNode)path.getLastPathComponent();
    }

    public void dragExit(DropTargetEvent x) {

    }

    public void drop(DropTargetDropEvent dtde) {
	Point pt = dtde.getLocation();
	DropTargetContext dtc = dtde.getDropTargetContext();
	JTree tree = (JTree)dtc.getComponent();
	TreePath parentpath = tree.getClosestPathForLocation(pt.x, pt.y);
	DefaultMutableTreeNode parent = (DefaultMutableTreeNode)parentpath.getLastPathComponent();
	String node = parentpath.getLastPathComponent().toString();
	String iddependencia = node.substring(0, node.indexOf("-") - 1).trim();
	try {
	    Transferable tr = dtde.getTransferable();
	    DataFlavor[] flavors = tr.getTransferDataFlavors();
	    for (int i = 0; i < flavors.length; i++) {
		if (tr.isDataFlavorSupported(flavors[i])) {
		    dtde.acceptDrop(dtde.getDropAction());
		    String v = "" + peoplePanel.getMultiSelectionSelectedsID();
		    v = v.replace("[", "{");
		    v = v.replace("]", "}");
		    if (LibSQL.getInt("organigrama.setPersonasxDependencia", "'" + v + "', " + peoplePanel.getMultiSelectionSelectedsID().size() + "," + iddependencia) > 0) {
			ActualizaTabla();
		    }
		    dtde.dropComplete(true);
		    return;
		}
	    }
	    dtde.rejectDrop();
	} catch (Exception e) {
	    e.printStackTrace();
	    dtde.rejectDrop();
	}
    }

    public void dropActionChanged(DropTargetDragEvent x) {

    }

    public void dragOver(DropTargetDragEvent dtde) {
	TreeNode node = getNodeForEvent(dtde);
	dtde.acceptDrag(dtde.getDropAction());
    }

    public void dragEnter(DropTargetDragEvent dtde) {
	TreeNode node = getNodeForEvent(dtde);
	dtde.acceptDrag(dtde.getDropAction());
    }

    private void ActualizaTabla() {
	if (jTabbedPane1.getSelectedIndex() == 1)//Si esta seleccionada la pestaña "Personal"
	{
	    refreshPeoplePanel();
	} else if (jTabbedPane1.getSelectedIndex() == 3)//Si esta seleccionada la pestaña "Indumentaria"
	{
	    if (armaPersonalTotal()) {
		refreshClothingPanel();
	    }
	}
    }

    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == btnModDependency) {
	    try {
		if (!tfDependencyName.getText().equals("")) {
		    if (LibSQL.getInt("organigrama.setDependency", "'" + cbHierarchicalLevelDependency.getSelectedItem().toString() + "','" + tfDependencyName.getText().trim() + "','" + tfLevel.getText() + "'") != -1) {
			DefaultTreeModel model = (DefaultTreeModel)jtOrganigrama.getModel();
			model.valueForPathChanged(jtOrganigrama.getSelectionPath(), tfLevel.getText() + " - " + tfDependencyName.getText());
			tfLevel.setText("");
			tfDependencyName.setText("");
			ActualizarArbol();
		    }
		} else {
		    JOptionPane.showMessageDialog((Component)null, "Nombre vacio", "Error", JOptionPane.YES_NO_OPTION);
		}
	    } catch (Exception x) {
		System.out.println(x.getMessage());
	    }
	} else if (e.getSource() == btnDelDependency) {
	    try {
		if (LibSQL.getInt("organigrama.delDependency", "'" + tfLevel.getText().trim() + "'") > 0) {
		    MutableTreeNode node = (MutableTreeNode)jtOrganigrama.getSelectionPath().getLastPathComponent();
		    DefaultTreeModel model = (DefaultTreeModel)jtOrganigrama.getModel();
		    model.removeNodeFromParent(node);
		    tfLevel.setText("");
		    tfDependencyName.setText("");
		    ActualizarArbol();
		}
	    } catch (Exception x) {
		x.printStackTrace();
	    }
	} else if (e.getSource() == btnAddSubdependency) {
	    try {
		if (!tfSubdependencyName.getText().equals("")) {
		    Advisor.messageBox("Está por agregar un/a " + cbHierarchicalLevelSubdependency.getSelectedItem().toString() + "\ndependiente del/la (" + cbHierarchicalLevelDependency.getSelectedItem().toString() + "):\n" + tfDependencyName.getText() + "\nSi no está seguro, presione el botón \"Aceptar\"\n" + "y en la pantalla siguiente elija la opción \"No\"", "¡Advertencia!");
		    if (LibSQL.getInt("organigrama.addDependency", "'" + cbHierarchicalLevelSubdependency.getSelectedItem().toString() + "','" + tfSubdependencyName.getText() + "',0" + tfLevel.getText()) > 0) {
			ActualizarArbol();
		    }
		} else {
		    JOptionPane.showMessageDialog((Component)null, "Nombre vacio", "Error", JOptionPane.YES_NO_OPTION);
		}
	    } catch (Exception x) {
		System.out.print(x.getMessage());
	    }
	} else if (e.getSource() == btnSelDependency) {
	    iddep.setText(tfLevel.getText());
	    //this.dispose();
	} else if (e.getSource() == btnCancel) {
	    //this.dispose();
	    try {
		jtOrganigrama = new JTree();
		jtOrganigrama = crearArbolDependencias("", "iddep", "nombre", String.valueOf(dependenciaOrigen));
	    } catch (Exception f) {
		f.printStackTrace();
	    }
	    jtOrganigrama.setCellRenderer(new MiRendererDeArbol());
	    jtOrganigrama.setSelectionPath(jtOrganigrama.getPathForLocation(0, 0));
	    jScrollPane1.getViewport().add(jtOrganigrama, null);
	    // ARBOL
	}
    }

    public void itemStateChanged(ItemEvent evt) {
    }

    public void valueChanged(ListSelectionEvent e) {
	ListSelectionModel modelo = (ListSelectionModel)e.getSource();
	if (!modelo.isSelectionEmpty()) {
	}
    }

    public void valueChanged(TreeSelectionEvent e) {

    }

    private void jTabbedPane1_stateChanged(ChangeEvent e) {
	ActualizaTabla();
    }

    public class MiRendererDeArbol extends BasicLabel implements TreeCellRenderer {

	private ImageIcon[] imgNivel;
	private Color[] colorNivel;
	private boolean seleccionado;

	public MiRendererDeArbol() {
	    // Cargamos las imgenes de las cartas
	    colorNivel = new Color[12];
	    colorNivel[0] = new Color(60, 60, 60);
	    colorNivel[1] = new Color(60, 60, 120);
	    colorNivel[2] = new Color(60, 60, 180);
	    colorNivel[3] = new Color(120, 60, 60);
	    colorNivel[4] = new Color(120, 60, 120);
	    colorNivel[5] = new Color(120, 60, 180);
	    colorNivel[6] = new Color(180, 60, 240);
	    colorNivel[7] = new Color(180, 60, 120);
	    colorNivel[8] = new Color(180, 60, 180);
	    colorNivel[9] = new Color(240, 60, 240);
	    colorNivel[10] = new Color(240, 60, 240);
	    colorNivel[11] = new Color(240, 60, 240);
	    imgNivel = new ImageIcon[12];
	    imgNivel[0] = new ImageIcon();
	    imgNivel[1] = new ImageIcon();
	    imgNivel[2] = new ImageIcon();
	    imgNivel[3] = new ImageIcon();
	    imgNivel[4] = new ImageIcon();
	    imgNivel[5] = new ImageIcon();
	    imgNivel[6] = new ImageIcon();
	    imgNivel[7] = new ImageIcon();
	    imgNivel[8] = new ImageIcon();
	    imgNivel[9] = new ImageIcon();
	    imgNivel[10] = new ImageIcon();
	    imgNivel[11] = new ImageIcon();
	}

	public Component getTreeCellRendererComponent(JTree arbol, Object valor, boolean seleccionado, boolean expandido, boolean rama, int fila, boolean conFoco) {
	    // Hay que encontrar el nodo en que estamos y coger el
	    // texto que contiene
	    DefaultMutableTreeNode nodo = (DefaultMutableTreeNode)valor;
	    String texto = (String)nodo.getUserObject();
	    this.seleccionado = seleccionado;
	    // Se fija el color de fondo en función de que está o no
	    // seleccionada la celda del árbol
	    if (!seleccionado)
		setForeground(Color.black);
	    else
		setForeground(Color.white);
	    // Fijamos el icono que corresponde al texto de la celda, para
	    // presentar la imagen de la dependencia que corresponde a esa celda
	    /*ResultSet result = LibSQL.exFunction("organigrama.getDependency", "" + texto.substring(0, texto.indexOf("-") - 1).trim() + ",0,0");
	    try {
		if (result.next()) {
		    nivelj = result.getString("niveljerarquico");
		}
	    } catch (Exception ex) {
		ex.printStackTrace();
	    }*/
	    int nivel = Integer.parseInt(nivelj);
	    setIcon(imgNivel[nivel]);
	    setForeground(colorNivel[nivel]);
	    // A continuación del icono, ponemos el texto
	    setText(texto);
	    return (this);
	}
	// Sobreescribimos el método paint() para fijar el color de
	// fondo. Normalmente, un BasicLabel puede pintar su propio fondo,
	// pero, seguramente debido aparentemente a un bug, o a una
	// limitación en el TreeCellRenderer, es necesario recurrir
	// al método paint() para hacer esto

	public void paint(Graphics g) {
	    Color color;
	    Icon currentI = getIcon();
	    // Fijamos el colos de fondo
	    color = seleccionado ? Color.cyan : Color.white;
	    g.setColor(color);
	    // Rellenamos el rectángulo que ocupa el texto sobre la
	    // celda del árbol
	    g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
	    super.paint(g);
	}

    }

    void bimprimir_actionPerformed(ActionEvent e) {
	new DependenciesReport(Integer.parseInt(tfLevel.getText()), personalTotal);
    }

    public void ActualizarArbol() {
	Advisor.messageBox("A continuación se redibujará el Organigrama", "¡Advertencia!");
	if (btnSelDependency.isEnabled()) {
	    Organigrama org = new Organigrama(iddep);
	    //this.dispose();
	    //org.setModal(true);
	    org.show();
	} else {
	    Organigrama org = new Organigrama(dependenciaOrigen);
	    //this.dispose();
	    //org.setModal(true);
	    org.show();
	}
    }

    void btnPeople_actionPerformed(ActionEvent e) {
	//Abre el panel de personas
	/*Personas personas = new Personas();
	personas.setModal(true);
	Proced.CentraVentana(personas);
	personas.show();
	ActualizaTabla();
*/
	PersonsList personsList = new PersonsList();
	ExtendedInternalFrame personListContainer = new ExtendedInternalFrame("Buscar Personas");
	personListContainer.setCentralPanel(personsList);
	personListContainer.show();
    }

    void bexpandall_actionPerformed(ActionEvent e) {
	expandAll(jtOrganigrama, jtOrganigrama.getSelectionPath(), true);
    }

    void bcollapseall_actionPerformed(ActionEvent e) {
	expandAll(jtOrganigrama, jtOrganigrama.getSelectionPath(), false);
    }

    private void expandAll(JTree tree, TreePath parent, boolean expand) {
	// Traverse children
	TreeNode node = (TreeNode)parent.getLastPathComponent();
	if (node.getChildCount() >= 0) {
	    for (Enumeration e = node.children(); e.hasMoreElements(); ) {
		TreeNode n = (TreeNode)e.nextElement();
		TreePath path = parent.pathByAddingChild(n);
		expandAll(tree, path, expand);
	    }
	}
	// Expansion or collapse must be done bottom-up
	if (expand) {
	    tree.expandPath(parent);
	} else {
	    tree.collapsePath(parent);
	}
    }

    void bbuscar_actionPerformed(ActionEvent e) {
	int startRow = 0;
	String node = jtOrganigrama.getPathForLocation(0, 0).getLastPathComponent().toString();
	jtOrganigrama.setSelectionPath(jtOrganigrama.getNextMatch(tfSearch.getText(), 0, Position.Bias.Forward));
    }

    void bbuscarPersona_actionPerformed(ActionEvent e) {
	find();
    }

    private void find() {
	if (jtbnrolegajo.getText().length() > 0) {
	    employeeFileNumber = "" + jtbnrolegajo.getText().trim();
	} else {
	    employeeFileNumber = "";
	}
	if (jtbapellido.getText().length() > 0) {
	    lastName = "" + jtbapellido.getText().trim();
	} else {
	    lastName = "";
	}
	if (jtbnombre.getText().length() > 0) {
	    firstName = "" + jtbnombre.getText().trim();
	} else {
	    firstName = "";
	}
	if (jtbnro.getText().length() > 0) {
	    identificationNumber = "" + jtbnro.getText().trim();
	} else {
	    identificationNumber = "";
	}
	ActualizaTabla();
    }

    private int getCantidadPersonasDependencia(int _iddep) {
	personalTotal = "";
	personalDep.removeAllElements();
	addPersonal(personalDep, "dependencia", _iddep);
	return personalDep.size();
    }

    private void addPersonal(Vector _personas, String _opcion, int _iddep) {
	try {
	    String function = "";
	    if (_opcion.equals("dependencia")) {
		function = "organigrama.getAllPersonasByIdDependencia";
	    } else if (_opcion.equals("subdependencia")) {
		function = "organigrama.getAllPersonasxDependencia";
	    } else if (_opcion.equals("total")) {
		function = "organigrama.getAllPersonasxdependenciaByTotal";
	    }
	    ResultSet res = LibSQL.exFunction(function, "" + _iddep);
	    // .exFunction(_Query, "");    ;
	    while (res.next()) {
		_personas.add(res.getString("idpersona"));
	    }
	} catch (SQLException x) {
	    x.printStackTrace();
	}
    }

    private int getCantidadPersonasSubDependencias(int _iddep) {
	personalSubDeps.removeAllElements();
	int SubDep = _iddep;
	addPersonal(personalSubDeps, "subdependencia", SubDep);
	for (int i = 0; i < 9; i++) {
	    addPersonal(personalSubDeps, "total", SubDep);
	}
	getCantidadIndumentaria();
	return personalSubDeps.size();
    }

    private boolean armaPersonalTotal() {
	personalTotal = "";
	for (int i = 0; i < personalDep.size(); i++) {
	    personalTotal += personalDep.elementAt(i).toString() + ",";
	}
	for (int i = 0; i < personalSubDeps.size(); i++) {
	    personalTotal += personalSubDeps.elementAt(i).toString() + ",";
	}
	if (personalTotal.length() > 0) {
	    personalTotal = "'" + personalTotal.substring(0, personalTotal.length() - 1) + "'";
	} else {
	    personalTotal = "'0'";
	}
	return true;
    }

    private int getCantidadIndumentaria() {
	if (armaPersonalTotal()) {
	    try {
		ResultSet res = LibSQL.exFunction("inventario.getIndumentariaxpersona", "" + personalTotal + "");
		//
		//System.out.println("------------- INDUMENTARIA DE " + tfDependencyName.getText() + " -------------");
		while (res.next()) {
		    //System.out.println(res.getInt("cantidad") + " " + res.getString("tipo"));
		}
	    } catch (SQLException x) {
		Advisor.messageBox(x.getMessage(), "Error");
	    }
	}
	return 0;
    }

    public JTree createTree(String _idField, String _dataField, int _initialID) throws Exception {
	JTree tree = null;
	boolean found = false;
	ResultSet rs = LibSQL.exFunction("organigrama.getAllDependencies", "");

	Dependency root = new Dependency(_initialID, -1, -1, "");
	DependenciesTree organigrama = new DependenciesTree(root);
	while (rs.next()) {
	    organigrama.addDependency(new Dependency(rs.getInt(_idField), rs.getInt("parent"), rs.getInt("level"), rs.getString(_dataField)));
	}

	/*while (rs.next() && !found) {
	    if (rs.getInt(_idField) == _initialID) {
		int _level = rs.getInt("level");
		DefaultMutableTreeNode arbol = new DefaultMutableTreeNode(rs.getString(_idField) + " - " + rs.getString(_dataField));
		createSubTree(_idField, _dataField, arbol, rs, _level + 2, rs.getInt(_idField));
		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
		tree = new JTree(arbol);
		tree.setCellRenderer(renderer);
		found = true;
	    }
	}*/
	System.out.println(organigrama);
	tree = organigrama.getTree();
	tree.setCellRenderer(new DefaultTreeCellRenderer());
	return tree;
    }
    
    public void createSubTree(String _idField, String _dataField, DefaultMutableTreeNode padre, ResultSet rs, int _subLevel, int _parent) throws SQLException {
	if (rs.getInt("parent") == _parent) {
	    int _level = rs.getInt("level");
	    int id = rs.getInt(_idField);
	    if (id == 6) {
		System.out.println("");
	    }
	    DefaultMutableTreeNode directa = null;
	    while (_subLevel + 1 < _level) {
		DefaultMutableTreeNode _directa = new DefaultMutableTreeNode("0 - Depende de (" + rs.getInt("parent") + ")");
		if (directa == null) {
		    directa = _directa;
		} else {
		    directa.getLastLeaf().add(_directa);
		}
		//directa = _directa;
		_subLevel++;
	    }
	    DefaultMutableTreeNode hijos = new DefaultMutableTreeNode(rs.getString(_idField) + " - " + rs.getString(_dataField));
	    if (directa != null) {
		directa.getLastLeaf().add(hijos);
		padre.add(directa);
	    } else {
		padre.add(hijos);
	    }
	    int previous = rs.getRow();
	    if (_level >= _subLevel && directa != null) {
		createSubTree(_idField, _dataField, directa, rs, _level, rs.getInt(_idField));
	    } else {
		createSubTree(_idField, _dataField, hijos, rs, _level, rs.getInt(_idField));
	    }
	    rs.absolute(previous);
	}
    }

    public static JTree crearArbolDependencias(String tabla, String campoid, String campodescrip, String padre) throws Exception {
	ResultSet rs = LibSQL.exFunction("organigrama.getAllDependencias", padre + ",0,0");
	rs.next();
	int nivel = rs.getInt("niveljerarquico");
	DefaultMutableTreeNode arbol = new DefaultMutableTreeNode(rs.getString(campoid) + " - " + rs.getString(campodescrip));
	rs = LibSQL.exFunction("organigrama.getAllDependenciasByPadre", padre + ",0,0");
	crearSubDependencias(tabla, campoid, campodescrip, arbol, rs, nivel + 2);
	DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
	/*renderer.setOpenIcon(new ImageIcon("/tmp/1.gif"));
	renderer.setClosedIcon(new ImageIcon("/tmp/1.gif"));
	renderer.setLeafIcon(new ImageIcon("/tmp/2.gif"));*/
	JTree tree = new JTree(arbol);
	tree.setCellRenderer(renderer);
	return tree;
    }

    public static void crearSubDependencias(String tabla, String campoid, String campodescrip, DefaultMutableTreeNode padre, ResultSet rs, int _nivel) throws SQLException {
	while (rs.next()) {
	    int nivel = rs.getInt("niveljerarquico");
	    while (_nivel + 1 < nivel) {
		DefaultMutableTreeNode directa = new DefaultMutableTreeNode("0 - Depende de (" + rs.getInt("padre") + ")");
		padre.add(directa);
		padre = directa;
		_nivel++;
	    }
	    DefaultMutableTreeNode hijos = new DefaultMutableTreeNode(rs.getString(campoid) + " - " + rs.getString(campodescrip));
	    padre.add(hijos);
	    ResultSet Resul = LibSQL.exFunction("organigrama.getAllSubDependenciasByPadre", rs.getString(campoid) + ",'" + campoid + "',0,0");
	    crearSubDependencias(tabla, campoid, campodescrip, hijos, Resul, nivel);
	}
    }

    private class DependenciesTree extends Vector<Dependency>{
	private Dependency root;

	public DependenciesTree(Dependency _root) {
	    root = _root;
	}
	
	public void addDependency(Dependency _dependency) {
	    int _idParent = _dependency.getIDParent();
	    int i = 0;
	    boolean found = false;
	    while (i < size() && !found)  {
		if (elementAt(i).getIDDep() == _idParent) {
		    found = true;
		    System.out.println(_dependency.getLevel() + "-" + elementAt(i).getLevel());
		    if (_dependency.getLevel() > elementAt(i).getLevel()+1) {
		        _dependency.setParent(getDirectDependency(elementAt(i), _dependency.getLevel()));
		    } else {
		        _dependency.setParent(elementAt(i));
		    }
		}
		i++;
	    }
	    if (!found) {
		_dependency.setParent(root);
	    }
	    add(_dependency);
	}
	
	public Dependency getDirectDependency(Dependency _parent, int _level) {
	    int i = 0;
	    boolean found = false;
	    Dependency _dependency = null;
	    while (i < size() && !found)  {
	        _dependency = elementAt(i);
		found = (_dependency.isDirect() && _dependency.getIDParent() == _parent.getIDDep() && _dependency.getLevel() == _level);
		i++;
	    }
	    //Armo las subdependencias directas
	    if (!found) {
		Dependency _max = null;
		i = 0;
	        while (i < size() && !found)  {
	            Dependency _test = elementAt(i);
	            if (_test.isDirect() && _test.getIDParent() == _parent.getIDDep()) {
			if (_max == null) {
			    _max = _test;
			} else {
			    if (_max.getLevel() < _test.getLevel()) {
				_max = _test;
			    }
			}
		    }
		    i++;
		}
	        if (_max == null) {
	            _dependency = new Dependency(0, _parent.getIDDep(), _parent.getLevel()+1, "0 - Depende de ("+_parent.getIDDep()+")");
		    _dependency.setDirect(true);
		    _dependency.setParent(_parent);
	        } else {
		    _dependency = _max;
		}
	    } else {
	    }
	    add(_dependency);
	    return _dependency;
	}
	
	public JTree getTree() {
	    DefaultMutableTreeNode tree = new DefaultMutableTreeNode(root.getIDDep() + " - " + root.getName());
	    populate(root, tree);
	    return new JTree(tree);
	}
	
	public void populate(Dependency _parent, DefaultMutableTreeNode _tree) {
	    for (int i = 0; i < size(); i++)  {
		if (elementAt(i).getParent() == _parent) {
		    DefaultMutableTreeNode _subTree = new DefaultMutableTreeNode(elementAt(i).getIDDep() + " - " + elementAt(i).getName());
		    populate(elementAt(i), _subTree);
		    _tree.add(_subTree);
		}
	    }
	    
	}
    }

    private class Dependency {

	private int iddep = -1;
	private Dependency parent;
	private int idParent;
	private int level = -1;
	private String name = "";
	private boolean direct = false;

	public Dependency(int _iddep, int _idParent, int _level, String _name) {
	    iddep = _iddep;
	    idParent = _idParent;
	    level = _level;
	    name = _name;
	}

	public int getIDDep() {
	    return iddep;
	}
	public Dependency getParent() {
	    return parent;
	}
	public int getIDParent() {
	    return idParent;
	}
	public void setParent(Dependency _parent) {
	    parent = _parent;
	}
	public int getLevel() {
	    return level;
	}
	public String getName() {
	    return name;
	}
	
	public boolean isDirect() {
	    return direct;
	}
	
	public void setDirect(boolean _direct) {
	    direct = _direct;
	}
    }

}
