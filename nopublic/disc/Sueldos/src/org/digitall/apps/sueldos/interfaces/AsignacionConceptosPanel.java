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
 * AsignacionConceptosPanel.java
 *
 * */
package org.digitall.apps.sueldos.interfaces;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JPanel;

import org.digitall.apps.sueldos.classes.Conceptos;
import org.digitall.common.personalfiles.classes.Legajo;
import org.digitall.apps.sueldos.classes.LiquidacionSueldos;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ReloadButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;

public class AsignacionConceptosPanel extends BasicPrimitivePanel{
    
    private BasicPanel content = new BasicPanel();
    private SaveDataButton btnSave = new SaveDataButton();    
    private AddButton btnAddConcept = new AddButton();    
    private CloseButton btnClose = new CloseButton();
    private BasicPanel pEmplados = new BasicPanel();
    private int[] sizeColumnList = {143};
    private int[] sizeColumnListEmpleados = {60,258};
    private Vector haberesDataRow = new Vector();
    private Vector descuentosDataRow = new Vector();
    private Vector empleadosDataRow = new Vector();
    private Vector haberHeader = new Vector();
    private Vector descuentoHeader = new Vector();
    private Vector empleadosHeader = new Vector();
    private GridPanel grillaHaberes = new GridPanel(100, sizeColumnList, "Haberes", haberesDataRow);
    private GridPanel grillaDescuentos = new GridPanel(100, sizeColumnList, "Descuentos", descuentosDataRow);
    private CBInput cbCargo = new CBInput(0,"Cargo");
    private TFInput tfNombre = new TFInput(DataTypes.STRING, "Empleado",false);
    private FindButton btnFind = new FindButton();
    private GridPanel grillaEmpleados = new GridPanel(1000, sizeColumnListEmpleados, "", empleadosDataRow);
    private ReloadButton reloadLegajos;
    private LiquidacionSueldos liquidacionSueldos = new LiquidacionSueldos();
    private AsignacionTabsPanel tabbedAsignedPanel = new AsignacionTabsPanel();
    private Legajo legajo;

    public AsignacionConceptosPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public AsignacionConceptosPanel(Legajo _legajo) {
        try {
            legajo = _legajo;
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
	content.setLayout(null);
	tfNombre.setPreferredSize(new Dimension(155, 35));
	tfNombre.setBounds(new Rectangle(10, 20, 155, 35));
	tfNombre.setSize(155, 35);
	tfNombre.getTextField().addKeyListener(new KeyListener(){

		public void keyTyped(KeyEvent e) {
		}

		public void keyPressed(KeyEvent e) {
	
		}

		public void keyReleased(KeyEvent e) {
		    if (e.getKeyCode() == KeyEvent.VK_ENTER){
		        buscar();       
		    }
		}
	    });
	cbCargo.setBounds(new Rectangle(175, 20, 175, 35));
	cbCargo.setPreferredSize(new Dimension(250, 50));
	cbCargo.autoSize();
	btnFind.setBounds(new Rectangle(355, 30, 30, 25));
	btnFind.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      btnFind_actionPerformed(e);
				  }

			      }
	);
	grillaEmpleados.setBounds(new Rectangle(5, 65, 380, 305));
	tabbedAsignedPanel.setBounds(new Rectangle(400, 5, 390, 375));
	tabbedAsignedPanel.setBorder(BorderPanel.getBorderPanel("Conceptos"));
	pEmplados.setLayout(null);
	pEmplados.add(grillaEmpleados, null);
	pEmplados.add(tfNombre,null);
	pEmplados.add(cbCargo,null);
	pEmplados.add(btnFind,null);
	pEmplados.setBorder(BorderPanel.getBorderPanel("Empleados"));
	btnSave.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
				   btnSave_actionPerformed(e);
			       }
			   }
	);
        btnAddConcept.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) {
                                   btnAddConcept_actionPerformed(e);
                               }
                           }
        );
	btnClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }   
	);
        btnAddConcept.setToolTipText("Agregar concepto");
	pEmplados.setBounds(new Rectangle(5, 5, 390, 375));
	grillaHaberes.setBounds(new Rectangle(405, 5, 205, 375));
	grillaHaberes.getTable().setDragEnabled(true);
	setHaberesHeader();
	grillaDescuentos.setBounds(new Rectangle(615, 5, 205, 375));
	grillaDescuentos.getTable().setDragEnabled(true);
	grillaEmpleados.getTable().setDragEnabled(true);
	setHaberesHeader();	
	setDescuentosHeader();
	setEmpleadosHeader();
	addButton(btnClose);
	addButton(btnSave);
        addButton(btnAddConcept);
	//grillaHaberes.refresh("");
	//grillaDescuentos.refresh("");
	grillaEmpleados.refresh(legajo.getidPerson() + ",'',-1,-2");
	initSearchParams();
	this.setSize(new Dimension(795, 422));
	/*content.add(grillaDescuentos, null);
	content.add(grillaHaberes, null);*/
	content.add(tabbedAsignedPanel, null);
	content.add(pEmplados, null);
	cbCargo.setSelectedValue("N/A");
	this.add(content, null);
	//this.getParentInternalFrame().pack();
    }
    
    private void initSearchParams(){
	tfNombre.setValue("");
	cbCargo.loadJCombo("personalfiles.getAllCargos","");
    }
    
    private void btnFind_actionPerformed(ActionEvent e) {
	buscar();
    }
    
    private void buscar(){
	    String params =  "-1,'"+tfNombre.getValue().toString() + "'," +cbCargo.getSelectedValue()+ ",-2";
	    grillaEmpleados.refresh(params);
    }
    
    private void setHaberesHeader() {
	haberHeader.removeAllElements();
	haberHeader.addElement("*"); //idconcepto
	haberHeader.addElement("*"); // idtipoconcepto
	haberHeader.addElement("*"); // idtablareferenciada
	haberHeader.addElement("*"); // debehaber
	haberHeader.addElement("Concepto"); 
	 
	grillaHaberes.getTable().addMouseListener(new MouseAdapter() {
					      public void mouseClicked(MouseEvent e) {
						  if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						  } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {						      
						  }
					      }
					  }
	);
	String params = "";
	grillaHaberes.setParams("sueldos.getAllHaberes", params, haberHeader);
    }
    
    private void setDescuentosHeader() {
	descuentoHeader.removeAllElements();
	descuentoHeader.addElement("*"); //idconcepto
	descuentoHeader.addElement("*"); // idtipoconcepto
	descuentoHeader.addElement("*"); // idtablareferenciada
	descuentoHeader.addElement("*"); // debehaber
	descuentoHeader.addElement("Concepto"); 
	 
	grillaDescuentos.getTable().addMouseListener(new MouseAdapter() {
					      public void mouseClicked(MouseEvent e) {
						  if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						  } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						  }
					      }
					  }
	);
	String params = "";
	grillaDescuentos.setParams("sueldos.getAllDescuentos", params, descuentoHeader);
    }
    
    private void setEmpleadosHeader() {
        empleadosHeader.addElement("*");        
	empleadosHeader.addElement("Legajo");
	empleadosHeader.addElement("*");
	empleadosHeader.addElement("*");
	empleadosHeader.addElement("*");
	empleadosHeader.addElement("Apellido(s), Nombre(s)");        
	empleadosHeader.addElement("*");
	empleadosHeader.addElement("*");
	empleadosHeader.addElement("*");
	empleadosHeader.addElement("*");
	grillaEmpleados.getTable().addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
					    //loadPerson();
					    if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						//setPerson();
						int filaSeleccionada = grillaEmpleados.getTable().rowAtPoint(e.getPoint());
						grillaEmpleados.selectAllItems(false);
						grillaEmpleados.getTable().setValueAt(true,filaSeleccionada,0);
						grillaEmpleados.getTable().getSelectionModel().setSelectionInterval(filaSeleccionada,filaSeleccionada);
						int numLegajo = Integer.parseInt(grillaEmpleados.getSelectedsID().elementAt(0).toString());
						legajo.retriveDataForNumber(numLegajo);
						cargarUltimosConceptos();
					    }
					}

				    }
	);
	String params = "-1,'',-1,-2"; 
	grillaEmpleados.setParams("personalfiles.getAllPersonasDependenciaWithLegajo", params, empleadosHeader); 
    }
    
    private void btnSave_actionPerformed(ActionEvent e) {
	guardarCambiosModelForUser();
	grabarConceptosToPerson();
    }
    
    private void btnAddConcept_actionPerformed(ActionEvent e) {
        abrirMgmtConceptos();
    }
    
    private void abrirMgmtConceptos(){
        ConceptosMgmt conceptosMgmt = new ConceptosMgmt(new Conceptos());
        ExtendedInternalFrame container = new ExtendedInternalFrame("Agregar/Modificar Conceptos");
        container.setCentralPanel(conceptosMgmt);
        container.show();
    }
    
    private void guardarCambiosModelForUser(){
	boolean hayCambioEnModelo = tabbedAsignedPanel.getPReciboPreview().hayCambios();
	if(hayCambioEnModelo){
	    if(Advisor.question("Guardar cambios","¿Desea guardar los cambios del Modelo?")){
		if(tabbedAsignedPanel.getPReciboPreview().grabarModelo()){
		    Advisor.messageBox("Se grabó con éxito el modelo.","Modelo grabado");
		}else{
		    Advisor.messageBox("Se produjo un error al grabar los datos.","Error");
		}
	    }
	}
    }
    
    private void grabarConceptosToPerson(){
	if(grillaEmpleados.getSelectedsID().size() > 0){
	    Vector haberes = tabbedAsignedPanel.getPReciboPreview().getActualHaberes();
	    Vector descuentos = tabbedAsignedPanel.getPReciboPreview().getActualDescuentos();
	    if((haberes.size() > 0 )||(descuentos.size() > 0 )){
		if(liquidacionSueldos.asignarConceptosAPersonas(grillaEmpleados.getSelectedsID(),haberes,descuentos) != -1){
		    //upDateValuesFromBaseSueldo(haberes, descuentos);//Esto lo sacamos por ahora pero debe estar descomentado mas adelante
		    Advisor.messageBox("Se asignaron los conceptos exitosamente","Conceptos Asignados");
		}else{
		    Advisor.messageBox("Se produjo un error al grabar los datos.","Error");
		}
	    }else{
	        Advisor.messageBox("Debe seleccionar al menos un concepto a asignar","Error");
	    }
	}else{
	    Advisor.messageBox("Debe seleccionar al menos una persona.","Error");
	}
    }
    
    private void upDateValuesFromBaseSueldo(Vector _haberes, Vector _descuentos){
	boolean resultado = false;
	String params = "";
	int idConcepto = -1;
	Vector idsHaberes = LibSQL.getVector("sueldos.getLisIdHaberes","'"+vectorToParams(_haberes)+"'");
	Vector idsDescuentos = LibSQL.getVector("sueldos.getLisIdDescuentos","'"+vectorToParams(_descuentos)+"'");
	Vector conceptos = sumarVectores(idsHaberes,idsDescuentos);
	for(int i = 0; i < conceptos.size(); i++){
	    idConcepto = Integer.parseInt(conceptos.elementAt(i).toString());
	    Conceptos concepto = new Conceptos();
	    concepto.setIdconcepto(idConcepto);
	    concepto.retrieveData();
	    params = "" + idConcepto;	
	    resultado =  LibSQL.getBoolean("sueldos.upDateValuesConcept",params);
	}
    }
    
    private String vectorToParams(Vector _vector){
	String resultado = "";
	int tam = _vector.size();
	for(int i = 0 ; i < tam; i++){
	    resultado += _vector.elementAt(i).toString() + ",";  
	}
	return(resultado);
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {      
	guardarCambiosModelForUser();
	getParentInternalFrame().close();
    }
    
    private void reloadLegajos_actionPerformed(ActionEvent e) {
    
    }
    
    private Vector sumarVectores(Vector _vec1, Vector _vec2){
	Vector resultado = new Vector();
	for(int i = 0; i < _vec1.size(); i++){
	    resultado.add(_vec1.elementAt(i).toString());
	}
	for(int i = 0; i < _vec2.size(); i++){
	    resultado.add(_vec2.elementAt(i).toString());
	}
	return(resultado);
    }
    
    private void cargarUltimosConceptos(){
                
        tabbedAsignedPanel.getPAsignedDescuentos().setLegajo(legajo);
        tabbedAsignedPanel.getPAsignedDescuentos().cargarListasPorLegajo();
        
        tabbedAsignedPanel.getPAsignedHaberes().setLegajo(legajo);
        tabbedAsignedPanel.getPAsignedHaberes().cargarListasPorLegajo();
        
        tabbedAsignedPanel.getPReciboPreview().setLegajo(legajo);
        tabbedAsignedPanel.getPReciboPreview().cargarListasPorLegajo();        
    }
}
