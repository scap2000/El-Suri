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
 * CatastrosConProblemasMgmt.java
 *
 * */
package org.digitall.apps.taxes.interfases.cadastraladmin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.ButtonGroup;

import org.digitall.apps.taxes.classes.Cadastral;
import org.digitall.apps.taxes.classes.CatastroxProblema;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.basic.BasicTextArea;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.grid.TableTransferHandler;
import org.digitall.lib.sql.LibSQL;


public class CatastrosConProblemasMgmt extends BasicPrimitivePanel {

    private BasicPanel contentPanel = new BasicPanel();
    private BasicPanel searchPanel = new BasicPanel();

    private int[] sizeColumnList = {88, 65, 500};
    private Vector cadastralHeader = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel cadastralPanel = new GridPanel(50000, sizeColumnList, "Problemas", dataRow) {
	public void finishLoading() {
	    controlBotones();
	}
    };
    
    private AssignButton btnAssign = new AssignButton(true);
    private CloseButton btnClose = new CloseButton();
    private DeleteButton btnBorrar = new DeleteButton();
    
    private BasicTextArea taProblema = new BasicTextArea();
    private Cadastral cadastral;
    private BasicPrimitivePanel parentList;
    private BasicRadioButton rbtnProblema = new BasicRadioButton();
    private BasicRadioButton rbtnSolucion = new BasicRadioButton();
    private ButtonGroup grupo = new ButtonGroup();
    private boolean addAction = true;
    private CatastroxProblema catastroxproblema;

    public CatastrosConProblemasMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout(null);
	this.setSize(new Dimension(710, 448));
	this.setPreferredSize(new Dimension(710, 515));
        cadastralPanel.setBounds(new Rectangle(5, 135, 700, 270));
	contentPanel.setBounds(new Rectangle(5, 5, 700, 450));
	contentPanel.setLayout(null);
	contentPanel.setSize(new Dimension(700, 515));
	searchPanel.setBounds(new Rectangle(5, 5, 700, 120));
	searchPanel.setLayout(null);
        btnAssign.setBounds(new Rectangle(625, 55, 30, 25));
	btnAssign.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      btnAssign_actionPerformed(e);
				  }

			      }
	);
        
        btnClose.addActionListener(new ActionListener() {

                                  public void actionPerformed(ActionEvent e) {
                                      btnClose_actionPerformed(e);
                                  }

                              }
        );
        
        btnBorrar.addActionListener(new ActionListener() {

                                  public void actionPerformed(ActionEvent e) {
                                      btnBorrar_actionPerformed(e);
                                  }

                              }
        );

        searchPanel.add(btnBorrar, null);
        searchPanel.add(rbtnSolucion, null);
        searchPanel.add(rbtnProblema, null);
        searchPanel.add(taProblema, null);
        searchPanel.add(btnAssign, null);
        contentPanel.add(cadastralPanel, null);
	contentPanel.add(searchPanel, null);
	this.add(contentPanel, BorderLayout.CENTER);
        
	cadastralPanel.getTable().setDragEnabled(true);
	cadastralPanel.getTable().setTransferHandler(new TableTransferHandler());

	setCadastralHeader();
        searchPanel.setBorder(BorderPanel.getBorderPanel("Búsqueda"));
        searchPanel.setSize(new Dimension(700, 120));
        taProblema.setBounds(new Rectangle(135, 25, 480, 75));
        rbtnProblema.setText("Es Problema");
        rbtnProblema.setBounds(new Rectangle(15, 35, 115, 25));
        rbtnSolucion.setText("Es Solución");
        rbtnSolucion.setBounds(new Rectangle(15, 70, 115, 25));
        btnBorrar.setBounds(new Rectangle(665, 55, 30, 25));
        addButton(btnClose);
        grupo.add(rbtnProblema);
        grupo.add(rbtnSolucion);
        rbtnProblema.setSelected(true);
        btnBorrar.setEnabled(false);
        btnAssign.setToolTipText("Agregar/Modificar");
    }
    
    public void setParentList(BasicPrimitivePanel _parentList) {
        parentList = _parentList;
    }
    
    private void setCadastralHeader() {
	
        cadastralHeader.removeAllElements();
        cadastralHeader.addElement("*"); //idcatastro
        cadastralHeader.addElement("*");
        cadastralHeader.addElement("Fecha");
        cadastralHeader.addElement("Tipo");
        cadastralHeader.addElement("Problema");
        cadastralHeader.addElement("*");
        cadastralHeader.addElement("*");
	 
	 cadastralPanel.getTable().addMouseListener(new MouseAdapter() {

	                                          public void mouseClicked(MouseEvent e) {
	                                              loadObjectSelected();
	                                              loadData();
	                                              if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
							      
	                                              } else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                                                          //loadUnit();
                                                          
                                                          //loadObjectSelected();
                                                          
	                                              }
	                                          }

	                                      }
	 );
	 String params = "-1";
	 cadastralPanel.setParams("taxes.getCatastroxProblemas", params, cadastralHeader);
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {	
	super.setParentInternalFrame(_e);
    }

    public void setCadastral(Cadastral _cadastral) {
        cadastral = _cadastral;
        cadastralPanel.setTitle("Problemas del Catastro Nº "+ cadastral.getCatastro() +"  ("+ cadastral.getDomape() +")");
        refresh();
    }

    public void refresh() {
	String params = ""+ cadastral.getIdCatastro();
	cadastralPanel.refresh(params);
        btnBorrar.setEnabled(false);
    }
    
    private void loadObjectSelected(){
	if (!dataRow.isEmpty()){
	    if (catastroxproblema == null) {
	        catastroxproblema = new CatastroxProblema();
	        
	    }
	    /*else {
	        catastroxproblema.setIdcatastro(Integer.parseInt("" + dataRow.elementAt(0)));
	        catastroxproblema.retrieveData();
	    }*/
	    catastroxproblema.setIdcatastrosxproblemas(Integer.parseInt("" + dataRow.elementAt(0)));
	    catastroxproblema.retrieveData();
	    btnBorrar.setEnabled(true);
	}
    }
    
    private void loadData() {
        taProblema.setText(catastroxproblema.getProblema());
        if ( catastroxproblema.esproblema() ){
            rbtnProblema.setSelected(true);
        } else {
            rbtnSolucion.setSelected(true);
        }
        addAction = false;
    }
    
    private void clearData() {
        taProblema.setText("");
        addAction = true;
        rbtnProblema.setSelected(true);
    }
    
    private void btnAssign_actionPerformed(ActionEvent e) {
        if (addAction) {
            catastroxproblema = new CatastroxProblema();
        }
        if(!taProblema.getText().equals("")){
            catastroxproblema.setIdcatastro(cadastral.getIdCatastro());
            catastroxproblema.setEsproblema(rbtnProblema.isSelected());
            catastroxproblema.setProblema(taProblema.getText());
            if (catastroxproblema.saveData() != -1) {
                //refresh();
                clearData();
                refresh();    
            } else {
                Advisor.messageBox("Ocurrió un error al guardar los datos", "Error");
            }
        }
        else{
            Advisor.messageBox("Debe ingresar descripción del Problema/Solución", "Mensaje");
        }   
    }

    private void btnClose_actionPerformed(ActionEvent e){
        parentList.refresh();
        getParentInternalFrame().close();
    }
    
    private void btnBorrar_actionPerformed(ActionEvent e) {
        if (Advisor.question("Mensaje", "¿Confirma la eliminación del item seleccionado?")) {
            String params = ""+ dataRow.elementAt(0) + ","+dataRow.elementAt(1);
            if (LibSQL.getInt("taxes.delProblemaxCatastro", params) > -1) {
                //refresh();
                clearData();
                refresh();    
            } else {
                Advisor.messageBox("Ocurrió un error al guardar los datos", "Error");
            }    
        }
    }
    
    private void controlBotones() {
        btnBorrar.setEnabled(false);
        clearData();
    }
    
    private void btnVerProblemas_actionPerformed(ActionEvent e) {
        
    }
    
}
