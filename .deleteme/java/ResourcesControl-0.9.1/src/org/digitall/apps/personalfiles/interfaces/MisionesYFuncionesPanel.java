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
 * MisionesYFuncionesPanel.java
 *
 * */
package org.digitall.apps.personalfiles.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import org.digitall.apps.personalfiles.classes.Dependencia;
import org.digitall.apps.personalfiles.classes.VectorDependencia;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.EditButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class MisionesYFuncionesPanel extends BasicPanel{
    
    private EditButton bEdit = new EditButton();
    private DeleteButton bDelete = new DeleteButton();
    private Dependencia dependencia = null;
    private AcceptButton bAccept = new AcceptButton();
    private DependenciaTree parent = null;
    private VectorDependencia vectorDependencia = null;
    private Vector vecTiposDependenciaNombre = new Vector();
    private Vector vecTiposDependenciaId = new Vector();    
    private BorderLayout borderLayout1 = new BorderLayout();
    private BasicLabel blNroPersonasDepDat = new BasicLabel();
    private BasicLabel blNroPersonasSubdepDat = new BasicLabel();
    private String paramsIn = "";
    private JTextPane tpMisiones = new JTextPane();
    private JTextPane tpFunciones = new JTextPane();
    private JScrollPane spMisiones = new JScrollPane(tpMisiones);
    private JScrollPane spFunciones = new JScrollPane(tpFunciones);    
    private TFInput tfiDependencia = new TFInput(DataTypes.STRING, "Dependencia",false);   

    public MisionesYFuncionesPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setPreferredSize(new Dimension(790, 180));
	this.setSize(new Dimension(800, 188));
	this.setLayout(null);
	//this.setBorder(BorderPanel.getBorderPanel("Agregar Subdependencia")); 		
	blNroPersonasSubdepDat.setBounds(new Rectangle(175, 60, 70, 15));	
	//private ScrollPane spFunciones = new ScrollPane();	
	spMisiones.setBorder(BorderFactory.createTitledBorder("Mision"));	
	spMisiones.setBounds(new Rectangle(10, 10, 390, 135));	
	spFunciones.setBorder(BorderFactory.createTitledBorder("Funciones"));	
	spFunciones.setBounds(new Rectangle(405, 10, 385, 135));	
	tfiDependencia.setBounds(new Rectangle(10, 145, 390, 35));
	tfiDependencia.setPreferredSize(new Dimension(390, 35));
	tfiDependencia.setSize(new Dimension(390, 35));
	tfiDependencia.setEnabled(false);
	bEdit.setBounds(new Rectangle(700, 40, 30, 20));
	bEdit.setPreferredSize(new Dimension(30, 20));
	bEdit.setSize(new Dimension(30, 20));
/*	bEdit.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    bEdit_actionPerformed(e);
				}

			    }
	);
*/
	bDelete.setBounds(new Rectangle(730, 40, 30, 20));
	bDelete.setPreferredSize(new Dimension(30, 20));
/*	bDelete.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    bDelete_actionPerformed(e);
				}

			    }
	);
*/
	bAccept.setBounds(new Rectangle(765, 160, 30, 20));
	bAccept.setSize(new Dimension(30, 20));
	bAccept.setPreferredSize(new Dimension(30, 20));
/*	bAccept.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    bAccept_actionPerformed(e);
				}

			    }
	);
*/
	bAccept.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   bAccept_actionPerformed(e);
			       }

			   }
	);
	this.add(tfiDependencia, null);
	this.add(spFunciones, null);
	this.add(spMisiones, null);
	this.add(blNroPersonasSubdepDat, null);
	//loadCombo();
	this.add(bAccept, null);
	this.add(tfiDependencia, null);		
    }
    
    private void bAccept_actionPerformed(ActionEvent e) {
	dependencia.setMisiones(tpMisiones.getText());
	dependencia.setFunciones(tpFunciones.getText());
	if (!(dependencia.setMisionesYFunciones() > 0)) {		                           
	    Advisor.messageBox("Ocurrio un error al grabar los datos!", "Error");
	}
    }

    public void setDependencia(Dependencia _dependencia) {
	dependencia = _dependencia;	
	tfiDependencia.setValue(dependencia.getNombre());			
	tpMisiones.setText(dependencia.getMisiones());	
	tpMisiones.setCaretPosition(0);
	tpFunciones.setText(dependencia.getFunciones());
	tpFunciones.setCaretPosition(0);
    }
}
