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
 * EmbarazosList.java
 *
 * */
package org.digitall.apps.accionsocial.interfaces;

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

import org.digitall.apps.accionsocial.classes.Embarazo;
import org.digitall.apps.accionsocial.classes.Persona;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.grid.TableTransferHandler;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;


public class EmbarazosList extends BasicPrimitivePanel {

    private BasicPanel contentPanel = new BasicPanel();
    private BasicPanel searchPanel = new BasicPanel();

    private TFInput tfPersonName = new TFInput(DataTypes.STRING, "Name",false);
    private TFInput tfDni = new TFInput(DataTypes.INTEGER, "DNI",false);

    private int[] sizeColumnList = {484, 79, 75};
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel grillaEmbarazos = new GridPanel(50000, sizeColumnList, "Listado de Personas Embarazadas", dataRow) {
	public void finishLoading() {
	    controlBotones();
	}
    };
    
    private FindButton btnFind = new FindButton();
    private AddButton btnAdd = new AddButton();
    private ModifyButton btnEdit = new ModifyButton();
    private PrintButton btnPrint = new PrintButton();
    private DeleteButton btnDel = new DeleteButton();
    
    private Persona persona = new Persona();
    private Embarazo embarazo = new Embarazo();
    private BasicCheckBox chkEmbarazosVigentes = new BasicCheckBox("Vigente");

    public EmbarazosList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout(null);
	this.setSize(new Dimension(710, 388));
	this.setPreferredSize(new Dimension(710, 515));
	tfPersonName.setBounds(new Rectangle(15, 20, 170, 35));
	grillaEmbarazos.setBounds(new Rectangle(5, 75, 700, 270));
	contentPanel.setBounds(new Rectangle(5, 5, 700, 500));
	contentPanel.setLayout(null);
	contentPanel.setSize(new Dimension(700, 515));
	searchPanel.setBounds(new Rectangle(5, 5, 700, 65));
	searchPanel.setLayout(null);
        searchPanel.setBorder(BorderPanel.getBorderPanel("Buscar Personas"));
        btnFind.setBounds(new Rectangle(460, 30, 30, 25));
	btnFind.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      btnFind_actionPerformed(e);
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
	btnPrint.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnPrint_actionPerformed(e);
				}

			    }
	);
	
	btnPrint.addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1 && e.getButton() == e.BUTTON3) {
		    clickPrint(e);
		}
	    }
	});
	
	tfDni.setBounds(new Rectangle(230, 20, 105, 35));
        searchPanel.add(chkEmbarazosVigentes, null);
        searchPanel.add(tfDni, null);
        searchPanel.add(tfPersonName, null);
        searchPanel.add(btnFind, null);
        btnEdit.setBounds(new Rectangle(610, 525, 40, 25));
	btnEdit.setToolTipText("Modificar Automotor");
	btnEdit.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnEdit_actionPerformed(e);
			       }

			   }
	);
	btnDel.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnDel_actionPerformed(e);
				}

			    }
	);
	contentPanel.add(grillaEmbarazos, null);
	contentPanel.add(searchPanel, null);
	this.add(contentPanel, BorderLayout.CENTER);
	addButton(btnEdit);
	addButton(btnAdd); 
	
	if (Environment.sessionUser.equals("admin"))  {
	    this.addButton(btnDel);
	}
	grillaEmbarazos.getTable().setDragEnabled(true);
	grillaEmbarazos.getTable().setTransferHandler(new TableTransferHandler());
	tfPersonName.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});
        tfDni.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});
        btnEdit.setEnabled(false);
	btnDel.setEnabled(false);
        chkEmbarazosVigentes.setBounds(new Rectangle(375, 30, 80, 25));
        chkEmbarazosVigentes.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    chkEmbarazosVigentes_itemStateChanged(e);
                }
            });
        setCadastralHeader();
	addButton(btnPrint);
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	//getParentInternalFrame().getGeneralButtons().addButton(btnAdminCuotas);
    }
    
    public void refresh() {
        String params = "'"+ tfPersonName.getString().trim() +"','"+ tfDni.getString().trim() +"'," + (chkEmbarazosVigentes.isSelected()?true:false);
	grillaEmbarazos.refresh(params);
	btnEdit.setEnabled(false);
	btnDel.setEnabled(false);
	btnPrint.setVisible(true);
    }

    private void setCadastralHeader() {
	headerList.removeAllElements();
	headerList.addElement("*");    //idembarazo
	headerList.addElement("*");    //idpersona
	headerList.addElement("Apellido y Nombres");
	headerList.addElement("D.N.I.");
	headerList.addElement("Vigente");
	grillaEmbarazos.getTable().addMouseListener(new MouseAdapter() { 
		    public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                            loadObjectSelected();    
			} else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
			    loadMgmt(false);
			}
		    }
		});
		
	String params = "'','',false" ;
		grillaEmbarazos.setParams("accionsocial.getallembarazos", params, headerList);
    }

    
    private void loadObjectSelected() {
	if (!dataRow.isEmpty()) {
            persona = new Persona();
            persona.setIdPersona(Integer.parseInt("" + dataRow.elementAt(1)));
            persona.retrieveData();
	    embarazo = new Embarazo();
	    embarazo.setIdEmbarazo(Integer.parseInt("" + dataRow.elementAt(0)));
	    embarazo.retrieveData();
	    btnEdit.setEnabled(true);
	    btnDel.setEnabled(true);
	}
    }
    
    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void loadMgmt(boolean _addAction){
	/*if (_addAction) {
	    car = new Car();
	}
	carsMgmt = new CarsMgmt();
	carsMgmt.setCar(car);
	carsMgmt.setParentList(this);
	
	ExtendedInternalFrame carsMgmtContainer = new ExtendedInternalFrame("Agregar/Modificar");
	carsMgmtContainer.setCentralPanel(carsMgmt);
	carsMgmtContainer.show();
	carsMgmt.load();*/
    }
    
    private void btnAdd_actionPerformed(ActionEvent e) {
	//Falta una funcion que haga controles. Por ej. la fecha del modelo no debe ser superior a la fecha de inicio de pago
	loadMgmt(true);
    }

    private void btnEdit_actionPerformed(ActionEvent e) {
	//Falta una funcion que haga controles. Por ej. la fecha del modelo no debe ser superior a la fecha de inicio de pago
	loadMgmt(false);
    }

    private void btnPrint_actionPerformed(ActionEvent e) {
	/*boolean vigente = true;
	BasicReport report = new BasicReport(CarsList.class.getResource("xml/PadronAutomotor.xml"));
	report.addTableModel("taxes.xmlGetPadronAutomotor", ""+ vigente );
	if (vigente) {
	    report.setProperty("title", "Padrón Automotor Vigente");
	    report.setProperty("impuesto", "TASA GENERAL DE SERVICIOS");
	} else {
	    report.setProperty("title", "Padrón Automotor con baja");
	}
	report.doReport();*/
    }
    
    private void controlBotones(){
	btnEdit.setEnabled(false);
	btnDel.setEnabled(false);
    }
    
    private void btnDel_actionPerformed(ActionEvent e) {
	// Funcion que podra borrar automotores, solo en modo admin
	/*if (Advisor.question("Advertencia","¿Está seguro de eliminar el Dominio "+ car.getDominio() +"?")) {
	    if (car.deleteCar()) {
		Advisor.messageBox("Dominio Borrado exitosamente","Mensaje");
		refresh();
	    } else {
	        Advisor.messageBox("Ocurrió un error al borrar el Dominio","Error");
	    }
	}*/
    }
    
    private void clickPrint(MouseEvent e){
	/*PopupPrinterCars popupPrint = new  PopupPrinterCars();
	int x = (int)e.getPoint().getX();
	int y = (int)e.getPoint().getY() - popupPrint.getAlto();
	//popupPrint.setIDsPersonSelected(datosPersonal.getgrillaPersonal().getSelectedsID());
	//popupPrint.setVectorDependenciaSeleccionado(vectorDependenciaSeleccionado);
	popupPrint.show(btnPrint,x,y);*/
    }

    private void chkEmbarazosVigentes_itemStateChanged(ItemEvent e) {
        refresh();
    }
}
