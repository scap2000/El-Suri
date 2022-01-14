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
 * PopupPrinter.java
 *
 * */
package org.digitall.apps.personalfiles.interfaces;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.border.EtchedBorder;

import org.digitall.apps.personalfiles.classes.Dependencia;
import org.digitall.apps.personalfiles.classes.VectorDependencia;
import org.digitall.apps.personalfiles.interfaces.DependenciaTree;
import org.digitall.common.personalfiles.classes.Persona;
import org.digitall.common.reports.BasicReport;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class PopupPrinter extends JPopupMenu{

    // se ocupan items para cada menu o columna

    private JMenuItem miRxP  = new JMenuItem("Imprimir Recursos de la Persona seleccionada");
    private JMenuItem miRxD  = new JMenuItem("Imprimir Recursos de la Dependencia seleccionada");
    private JMenuItem miIG   = new JMenuItem("Imprimir Inventario General");
    private JMenuItem miIGV  = new JMenuItem("Imprimir Inventario Gral. Valorado");
    private JMenuItem miPxD  = new JMenuItem("Imprimir personas por Dependencias");
    private JMenuItem miBR   = new JMenuItem("Imprimir bajas de Recursos");
    private JMenuItem miRORG = new JMenuItem("Imprimir Resumen Organigrama");
    private JMenuItem miExit = new JMenuItem("Salir");
    
    
    private Resource recurso;
    private Persona persona;
    private Dependencia dependencia;
    
    private Vector IDsPersonSelected =  new Vector();
    private VectorDependencia vectorDependenciaSeleccionado = new VectorDependencia();

    public PopupPrinter() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setBackground(new Color(0, 132, 198));
	this.add(miRxP);
	this.add(miRxD);
	this.add(miIG);
	this.add(miIGV);
	this.add(miPxD);
	this.add(miBR);
	this.add(miRORG);
	this.add(miExit);
	miRxP.setForeground(Color.white);
	miRxD.setForeground(Color.white);
	miIG.setForeground(Color.white);
	miIGV.setForeground(Color.white);
	miPxD.setForeground(Color.white);
	miBR.setForeground(Color.white);
	miRORG.setForeground(Color.white);
	miExit.setForeground(Color.white);
	this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	miRxP.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
		    clickRxP();
		}
	    });
	miRxD.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
		    clickRxD();
		}
	    });
	miIG.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
		    clickIG();
		}
	    });
	miIGV.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
		    clickIGV();
		}
	    });
	miPxD.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
		    clickPxD();
		}
	    });
	miBR.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
		    clickBR();
		}
	    });
	miExit.addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1 && e.getButton() == e.BUTTON3) {
		    clickExit(e);              
		}
	    }
	});
	miRORG.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
		    clickRORG();
		}
	    });
    }
    
    private void clickRxD(){
	int seleccionada =  vectorDependenciaSeleccionado.getDependencia().getIdDep();
	String dependencia = "";
	String responsable = "";
	int idDependencia = -1;
	if(seleccionada != -1){ 
	    Vector parametros = getParametrosRxD();
	    idDependencia = Integer.parseInt(parametros.elementAt(0).toString());
	    dependencia = parametros.elementAt(1).toString();
	    responsable = parametros.elementAt(2).toString();
	    BasicReport report = new BasicReport(DependenciaTree.class.getResource("xml/ResourcesDependency.xml"));
	    report.setProperty("dependencia",dependencia);
	    report.setProperty("responsable",responsable);
	    String params = "" + idDependencia;
	    report.addTableModel("resourcescontrol.xmlgetresourcesdependency",params);
	    report.doReport();
	}else{
	    Advisor.messageBox("Debe seleccionar una dependencia","Error !");
	}
    }
    
    private void clickRxP(){
	 int seleccionados = IDsPersonSelected.size();
		String empleado = "";
		String dependencia = "";
		String responsable = "";
		int idPersona = -1;
		if(seleccionados == 1){
		    Vector parametros = getParametrosRxP();
		    idPersona = Integer.parseInt(parametros.elementAt(0).toString());
		    empleado = parametros.elementAt(1).toString();
		    dependencia = parametros.elementAt(2).toString();
		    responsable = parametros.elementAt(3).toString();
		    BasicReport report = new BasicReport(DependenciaTree.class.getResource("xml/ResourcesPerson.xml"));
		    report.setProperty("persona",empleado);
		    report.setProperty("dependencia",dependencia);
		    report.setProperty("responsable",responsable);
		    String params = "" + idPersona;
		    report.addTableModel("resourcescontrol.xmlgetresourcesperson",params);
		    report.doReport();
		}else{
		    Advisor.messageBox("Debe seleccionar una persona","Error !");
		}	
    }
    private void clickIG(){
	BasicReport report = new BasicReport(DependenciaTree.class.getResource("xml/InformeGral.xml"));
	report.setProperty("anio",Environment.currentYear);
	String params = "";
	report.addTableModel("resourcescontrol.xmlgetinventariogral",params);
	report.doReport();
    }
    private void clickIGV(){
	BasicReport report = new BasicReport(DependenciaTree.class.getResource("xml/InformeGralValorado.xml"));
	report.setProperty("anio",Environment.currentYear);
	String params = "";
	report.addTableModel("resourcescontrol.xmlgetinventariogralvalorado",params);
	report.doReport();
    }
    private void clickPxD(){
	BasicReport report = new BasicReport(DependenciaTree.class.getResource("xml/InformePersonasDependencia.xml"));
	report.setProperty("anio",Environment.currentYear);
	String params = "";
	report.addTableModel("resourcescontrol.xmlgetpersonofdependency",params);
	report.doReport();
    }
    
    private void clickRORG(){
	BasicReport report = new BasicReport(DependenciaTree.class.getResource("xml/InformePersonasOrganigrama.xml"));
	report.setProperty("anio",Environment.currentYear);
	String params = "";
	report.addTableModel("resourcescontrol.xmlgetResumeOrganigrama",params);
	report.doReport();
    }
    
    private void clickBR(){
	InformeBajasPanel infoBajaPanel = new InformeBajasPanel();
	ExtendedInternalFrame bajasIntFrame = new ExtendedInternalFrame("Informe de Bajas");
	bajasIntFrame.setCentralPanel(infoBajaPanel);
	bajasIntFrame.show();            
    }
    
    private void clickExit(MouseEvent e){
	
    }
    
    private Vector getParametrosRxP(){
	Vector resultado = new Vector();
	int idPerson = Integer.parseInt(IDsPersonSelected.elementAt(0).toString());
	Persona persona = new Persona();
	persona.setIdPerson(idPerson);
	persona.retrieveData();
	String empleado = "" + persona.getLastName() + ", "+ persona.getFirstName();
	resultado.add("" + idPerson);
	resultado.add("" + empleado);
	ResultSet result = LibSQL.exFunction("personalfiles.getdependenciaofemployer",""+idPerson);
	try {
	    result.next();
	    String dependencia = "" + result.getString("niveljer") + " " +result.getString("nombre");
	    String responsable = "" + result.getString("responsable");
	    resultado.add("" + dependencia); 
	    resultado.add("" + responsable);
	} catch (SQLException f) {
	    // TODO
	}
	return(resultado);
    }
    
    private Vector getParametrosRxD(){
	Vector resultado = new Vector();
	Dependencia dependencia  = vectorDependenciaSeleccionado.getDependencia();
	int idDependencia = dependencia.getIdDep();
	String nombreDependencia  = "" + dependencia.getNombreNivelJerarquico() + " " +dependencia.getNombre();
	String responsable = "" + LibSQL.getString("org.getUserName","");
	resultado.add("" + idDependencia);
	resultado.add("" + nombreDependencia);
	resultado.add("" + responsable);
	return(resultado);
    }

    public void setRecurso(Resource recurso) {
	this.recurso = recurso;
    }

    public Resource getRecurso() {
	return recurso;
    }

    public void setPersona(Persona persona) {
	this.persona = persona;
    }

    public Persona getPersona() {
	return persona;
    }

    public void setDependencia(Dependencia dependencia) {
	this.dependencia = dependencia;
    }

    public Dependencia getDependencia() {
	return dependencia;
    }
    
    public int getAlto(){
	return(this.getComponentCount() * 21);
    }

    public void setIDsPersonSelected(Vector iDsPersonSelected) {
	this.IDsPersonSelected = iDsPersonSelected;
    }

    public void setVectorDependenciaSeleccionado(VectorDependencia vectorDependenciaSeleccionado) {
	this.vectorDependenciaSeleccionado = vectorDependenciaSeleccionado;
    }
}

