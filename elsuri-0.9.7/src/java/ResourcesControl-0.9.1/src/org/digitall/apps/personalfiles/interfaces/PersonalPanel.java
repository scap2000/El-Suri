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
 * PersonalPanel.java
 *
 * */
package org.digitall.apps.personalfiles.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;

import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;

import javax.swing.SwingConstants;

import org.digitall.apps.personalfiles.classes.Dependencia;
import org.digitall.apps.personalfiles.classes.VectorDependencia;
import org.digitall.common.personalfiles.classes.Persona;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.org.Persons;
import org.digitall.lib.sql.LibSQL;

public class PersonalPanel extends BasicPrimitivePanel {

    private BorderLayout borderLayout1 = new BorderLayout();
    private BasicPanel jPanel1 = new BasicPanel();
    private BorderLayout borderLayout2 = new BorderLayout();
    private BasicPanel jPanel2 = new BasicPanel();
    private TFInput tfSearch = new TFInput(DataTypes.STRING, "Person", false);
    private FindButton btnSearch = new FindButton();
    private int[] sizeColumnList = {54, 85, 181 , 276 , 135};
    private Vector dataRow = new Vector();
    private GridPanel grillaPersonal = new GridPanel(1000, sizeColumnList, "Personal", dataRow);
    private Vector headerList = new Vector();	
    private Dependencia dependencia = null;
    private BasicCheckBox bcbTodoPersonal = new BasicCheckBox();    
    private Persons person;
    private DependenciaTree parentMain;
    private VectorDependencia vectorDependencia = null;
    private String paramsIn = "";
    private JLabel lbTotalSub = new JLabel();
    private JLabel lbInDependency = new JLabel();
    private String defaultTextTotalSub = "Cantidad de personas total en Subdependencias: ";
    private String defaultTextDep = "Cantidad de personas en la Dependencia: ";
    private String defaultTextTotal = "Cantidad de personas en Total: ";
    private JLabel lbTotal = new JLabel();
    private JLabel lbCantidadTotalSub = new JLabel();
    private JLabel lbCantidadEnDep = new JLabel();
    private JLabel lbCantidadTotal = new JLabel();

    public PersonalPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(borderLayout1);
	this.setSize(new Dimension(790, 174));
	this.setPreferredSize(new Dimension(790, 174));
	jPanel1.setLayout(borderLayout2);	
	jPanel2.setPreferredSize(new Dimension(780, 40));
	jPanel2.setLayout(null);
	jPanel2.setSize(new Dimension(780, 30));
	jPanel2.setBounds(new Rectangle(0, 0, 790, 10));
	tfSearch.setBounds(new Rectangle(5, 0, 215, 35));
	tfSearch.getTextField().addKeyListener(new KeyAdapter() {

					    public void keyReleased(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER)
						    refresh();
					    }

					}
	);      
	btnSearch.setBounds(new Rectangle(365, 15, 30, 20));
	btnSearch.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnSearch_actionPerformed(e);
				 }

			     }
	);
	grillaPersonal.setPreferredSize(new Dimension(780, 69));
	grillaPersonal.setSize(new Dimension(780, 100));
	bcbTodoPersonal.setText("Todo el Personal");
	bcbTodoPersonal.setBounds(new Rectangle(230, 15, 135, 20));
	lbTotalSub.setText("lbTotal");
	lbTotalSub.setBounds(new Rectangle(415, 0, 310, 15));
	lbInDependency.setText("lbInDependency");
	lbInDependency.setBounds(new Rectangle(415, 12, 310, 15));
	jPanel2.add(lbCantidadTotal, null);
	jPanel2.add(lbCantidadEnDep, null);
	jPanel2.add(lbCantidadTotalSub, null);
	jPanel2.add(lbTotal, null);
	jPanel2.add(lbInDependency, null);
	jPanel2.add(lbTotalSub, null);
	jPanel2.add(bcbTodoPersonal, null);
	jPanel2.add(btnSearch, null);
	jPanel2.add(tfSearch, null);
	jPanel1.add(jPanel2, BorderLayout.NORTH);
	jPanel1.add(grillaPersonal, BorderLayout.CENTER);
	this.add(jPanel1, BorderLayout.CENTER);
	setHeaderList();
	lbTotalSub.setText(defaultTextTotalSub);
	lbTotalSub.setForeground(Color.white);
	lbTotalSub.setFont(new Font("Dialog", 1, 12));
	lbInDependency.setText(defaultTextDep);
	lbInDependency.setForeground(Color.white);
	lbInDependency.setFont(new Font("Dialog", 1, 12));
	lbTotal.setText(defaultTextTotal);
	lbTotal.setBounds(new Rectangle(415, 25, 310, 15));
	lbTotal.setForeground(Color.white);
	lbTotal.setFont(new Font("Dialog", 1, 12));
	lbCantidadTotalSub.setText("0");
	lbCantidadTotalSub.setBounds(new Rectangle(745, 0, 35, 15));
	lbCantidadTotalSub.setFont(new Font("Dialog", 1, 14));
	lbCantidadTotalSub.setForeground(new Color(247, 247, 0));
	lbCantidadTotalSub.setHorizontalAlignment(SwingConstants.RIGHT);
	lbCantidadEnDep.setText("0");
	lbCantidadEnDep.setBounds(new Rectangle(745, 12, 35, 15));
	lbCantidadEnDep.setFont(new Font("Dialog", 1, 14));
	lbCantidadEnDep.setForeground(new Color(247, 247, 0));
	lbCantidadEnDep.setHorizontalAlignment(SwingConstants.RIGHT);
	lbCantidadTotal.setText("0");
	lbCantidadTotal.setBounds(new Rectangle(745, 25, 35, 15));
	lbCantidadTotal.setFont(new Font("Dialog", 1, 14));
	lbCantidadTotal.setForeground(new Color(247, 247, 0));
	lbCantidadTotal.setHorizontalAlignment(SwingConstants.RIGHT);
	grillaPersonal.getTable().addKeyListener(new KeyAdapter() {
	     public void keyReleased(KeyEvent key) {
	         if (key.getKeyCode() == key.VK_DOWN || key.getKeyCode() == key.VK_UP || key.getKeyCode() == key.VK_ENTER || key.getKeyCode() == key.VK_PAGE_DOWN || key.getKeyCode() == key.VK_PAGE_UP) {
	             //LOAD DATA
			    upDate();
	         }
	     }
	 });
	 
	 grillaPersonal.getTable().addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent me) {
	            if (me.getClickCount() == 1 && me.getButton() == MouseEvent.BUTTON1) {
	                //LOAD DATA
			    upDate();
			    //selected(me);
	            } else if (me.getClickCount() == 2 && me.getButton() == MouseEvent.BUTTON1) {
	                //EDIT DATA
	            }
	        }
	    }
	 );
    }
    
    private void selected(MouseEvent e){
	int filaSeleccionada = grillaPersonal.getTable().rowAtPoint(e.getPoint());
	grillaPersonal.selectAllItems(false);
	grillaPersonal.getTable().setValueAt(true,filaSeleccionada,0);
	grillaPersonal.getTable().getSelectionModel().setSelectionInterval(filaSeleccionada,filaSeleccionada);
    }
    
    private void upDate(){
	Persona persona = new Persona();
	persona.setIdPerson(Integer.parseInt(dataRow.elementAt(0).toString()));
	persona.retrieveData();
	parentMain.setPersona(persona);
	parentMain.upDateRecursosPorPersona();
    }
    private void setHeaderList() {      
	headerList.removeAllElements();
	
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("Tipo ID");
	headerList.addElement("Nº ID");
	headerList.addElement("Apellido(s), Nombre(s)");	
	headerList.addElement("*");
	headerList.addElement("Dependencia");
	headerList.addElement("*");
	headerList.addElement("Cargo");
	grillaPersonal.getTable().addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
					    loadPerson();
					    if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						setPerson();
					    }
					}

				    }
	);
	String params = "'',-1,''"; 
	//grillaPersonal.setParams("personalfiles.getAllPersonasDependenciafilter", params, headerList);       
	grillaPersonal.setParams("personalfiles.getAllPersonasDependencia", params, headerList);       
    }

    public void refresh() {
	if (vectorDependencia != null) {
	    String params = "";
	    paramsIn = "";
	    getNroPersonalEnSubdependencia(vectorDependencia);
	    paramsIn = paramsIn.substring(0, paramsIn.length() - 1);
	    if (!bcbTodoPersonal.isSelected()) {
		if (dependencia != null) {
		    params = "'" + tfSearch.getValue() + "'," + dependencia.getIdDep();
		} else {
		    params = "'" + tfSearch.getValue() + "', -2";
		}
	    } else {
		params = "'" + tfSearch.getValue() + "', -2";
	    }
	    grillaPersonal.refresh(params);
	    cargarLabel();
	}
    }
    
    private void cargarLabel(){
	int result = LibSQL.getInt("personalfiles.getNroPersonsInDependency",dependencia.getIdDep());   
	paramsIn = "";
	getNroPersonalEnSubdependencia(vectorDependencia);      
	String params = paramsIn.substring(0, paramsIn.length()-1);             
	
	ResultSet data = LibSQL.exFunction("personalfiles.getAllNroPersonsIndependency", "'"+params+"'");
		
	
	try {
	     if (data.next()) {                  
		 int cantPersonalSubDependencia = data.getInt("nroPersonal") - result;                   
		 lbCantidadTotalSub.setText(""+ cantPersonalSubDependencia+"");
		 lbCantidadEnDep.setText("" + result+"");
		 lbCantidadTotal.setText("" + (cantPersonalSubDependencia + result));
	    }
	 } catch (Exception ex) {
	     ex.printStackTrace();
	}       
    }
    
    public void getNroPersonalEnSubdependencia(VectorDependencia _vectorDependencia) {
	int tamVec = _vectorDependencia.size() - 1;
	Dependencia dependencia = _vectorDependencia.getDependencia();    
	paramsIn = dependencia.getIdDep()+","+ paramsIn;
	for (int i = 0; i <= tamVec; i++) {
	    VectorDependencia vectorSubDependencia = (VectorDependencia)_vectorDependencia.get(i);          
	    getNroPersonalEnSubdependencia(vectorSubDependencia);
	}
    } 
    
    private void btnSearch_actionPerformed(ActionEvent e) {
	refresh();
    }

    public void setDependencia(Dependencia _dependencia,VectorDependencia _vectorDependencias){
	this.vectorDependencia = _vectorDependencias;
	this.dependencia = _dependencia;
	/*String params = "''," + dependencia.getIdDep();
	grillaPersonal.refresh(params);         */
	refresh();
    }
    
    public JTable getGrilla() {			
        return grillaPersonal.getTable();
    }
    
    public Vector getPersona(int _nroFila) {
	Vector result = null;
	try{ 
	    result = grillaPersonal.getSelectedsID();
	} catch(Exception e) {
	    e.printStackTrace();
	}
	return result;
    } 
    
    public int getIdPersona() {
	int result = 0;
	try{ 
	    result = Integer.valueOf(dataRow.get(0)+"");
	} catch(Exception e) {
	    e.printStackTrace();
	}
	return result;
    }
    
    public GridPanel getGridPanelPersonal() {
	return grillaPersonal;
    }
    
    private void loadPerson(){
        person = new Persons();
        person.setIdPerson(Integer.valueOf(dataRow.get(0).toString()));
    }
    
    private void setPerson(){
        parentMain.setPersona(person);
    }

    public void setParentMain(DependenciaTree _parentMain) {
        parentMain = _parentMain;
    }
    
    /*public int getIDSelectedPerson(){
	int resultado = -1;
	int i = 0;
	while(i < grillaPersonal.getse){
	    
	}
    }*/

    public void setgrillaPersonal(GridPanel grillaPersonal) {
	this.grillaPersonal = grillaPersonal;
    }

    public GridPanel getgrillaPersonal() {
	return grillaPersonal;
    }
}
