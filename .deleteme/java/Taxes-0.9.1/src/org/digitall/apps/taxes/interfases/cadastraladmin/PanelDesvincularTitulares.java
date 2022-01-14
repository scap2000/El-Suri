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
 * PanelDesvincularTitulares.java
 *
 * */
package org.digitall.apps.taxes.interfases.cadastraladmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.Types;

import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.digitall.apps.taxes.classes.Cadastral;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.sql.LibSQL;

public class PanelDesvincularTitulares extends BasicPrimitivePanel{

    private JLabel lblCatastro = new JLabel();
    private JLabel lblTituloCatastro = new JLabel();
    private BasicPanel content = new BasicPanel();
    private DeleteButton btnDesvincular = new DeleteButton();    
    private CloseButton btnClose = new CloseButton();
    private SaveDataButton btnSave = new SaveDataButton();
    private Cadastral cadastral;
    
    private int[] sizeColumnList = {  230, 123, 84, 85 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(50000, sizeColumnList, "Titulares", dataRow);
    private CadastralList parent;

    public PanelDesvincularTitulares(Cadastral _cadastral, CadastralList _parent) {
	try {
            cadastral = _cadastral;
            parent = _parent;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    private void jbInit() throws Exception {
	content.setLayout(null);
        listPanel.setPreferredSize(new Dimension(400, 250));
        listPanel.setBounds(new Rectangle(5, 50, 520, 205));
	btnDesvincular.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
				   btnDesvincular_actionPerformed(e);
			       }
			   }
	);
        btnSave.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) {
                                   btnSave_actionPerformed(e);
                               }
                           }
        );
        btnDesvincular.setToolTipText("Desvincular Titulares");
        btnSave.setToolTipText("Guardar cambios");
	btnClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }   
	);
	this.setSize(new Dimension(530, 294));
        lblCatastro.setBounds(new Rectangle(150, 25, 185, 20));
        lblCatastro.setFont(new Font("Dialog", 1, 16));
        lblCatastro.setForeground(Color.red);
        lblCatastro.setBackground(new Color(183, 215, 255));
        lblCatastro.setText("" + cadastral.getCatastro());
        lblCatastro.setHorizontalAlignment(SwingConstants.CENTER);
        lblCatastro.setOpaque(true);
        lblCatastro.setBorder(BorderFactory.createLineBorder(Color.yellow, 1));
        lblTituloCatastro.setText("Catastro Registrado");
        lblTituloCatastro.setBounds(new Rectangle(150, 10, 185, 15));
        lblTituloCatastro.setFont(new Font("Dialog", 1, 12));
        lblTituloCatastro.setHorizontalAlignment(SwingConstants.CENTER);
        lblTituloCatastro.setOpaque(true);
        lblTituloCatastro.setBackground(new Color(255, 132, 0));
        lblTituloCatastro.setBorder(BorderFactory.createLineBorder(Color.yellow, 1));
        content.add(lblTituloCatastro);
        content.add(lblCatastro);
        content.add(listPanel);
        addButton(btnClose);
	addButton(btnDesvincular);
        addButton(btnSave);
	this.add(content, BorderLayout.CENTER);
        listPanel.getTable().addMouseListener(new MouseAdapter() {

                                           public void mouseClicked(MouseEvent e) {
                                               if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                                                   
                                               } 
                                           }

                                       }
        );
        listPanel.setCellEditor(Types.INTEGER,2);
        listPanel.setCellEditor(Types.CHAR,3);
        setheaderList();
        listPanel.refresh(cadastral.getIdCatastro());
    }
    
    private void setheaderList() {
        Vector headerList = new Vector();
        headerList.removeAllElements();
        headerList.addElement("*"); //catastro
        headerList.addElement("Apellido y Nombres");
        headerList.addElement("Nº Documento");
        headerList.addElement("Prioridad");
        headerList.addElement("Porcentaje");
        
        listPanel.setParams("taxes.getTitularesByCatastro", "" + cadastral.getIdCatastro(), headerList);
    }
    
    private void btnDesvincular_actionPerformed(ActionEvent e) {       
        desvincularTitulares();
    }
    
    private void desvincularTitulares(){
        if ( listPanel.getSelectedsID().size() > 0) {
            if ( Advisor.question("Desvinculación","¿Está seguro de desvincular el/los titulares seleccionados?")) {
                if (listPanel.getSelectedsID().size() < listPanel.getAllIDs().size()) {
                    if ( LibSQL.getBoolean("taxes.desvincularTitulares", "'" + listPanel.getSelectedsValuesInTableAt(1).toString().replace("[", "{").replace("]", "}") + "'," + cadastral.getIdCatastro() + "," + listPanel.getSelectedsValuesInTableAt(1).size()) ) {
                        Advisor.messageBox("Se desvincularon titulares con éxito", "Aviso");
                        listPanel.refresh("" + cadastral.getIdCatastro());
                    } else {
                        Advisor.messageBox("Se produjo un error al grabar los datos", "Error");
                    }
                } else {
                    Advisor.messageBox("No se pueden desvincular todos los titulares de un catastro", "Aviso");
                }
            }
        } else {
            Advisor.messageBox("Debe seleccionar al menos un titular a desvincular", "Aviso");
        }
    }
    
    private void btnSave_actionPerformed(ActionEvent e) {       
        guardarCambios();
    }
    
    private void guardarCambios(){
        if (control()) {
            if ( LibSQL.getBoolean("taxes.setTitularesByCatastro",  "'" + listPanel.getValuesInTableAt(1).toString().replace("[", "{").replace("]", "}") + "'," 
                                                          + "'" + listPanel.getValuesInTableAt(3).toString().replace("[", "{").replace("]", "}") + "'," 
                                                          + "'" + listPanel.getValuesInTableAt(4).toString().replace("[", "{").replace("]", "}") + "'," 
                                                          + cadastral.getIdCatastro() + "," 
                                                          + listPanel.getAllIDs().size()) ) {
                Advisor.messageBox("Se grabaron con éxito las modificaciones", "Aviso");
                listPanel.refresh("" + cadastral.getIdCatastro());
            } else {
                Advisor.messageBox("Se produjo un error al grabar los datos", "Error");
            }
        }
    }
    
    private boolean control(){
        boolean _returns = false;
        if ( tieneValoresNulos(listPanel.getValuesInTableAt(3)) ){
            Advisor.messageBox("Los valores de la columna Prioridad no pueden ser vacios", "Error");
        } else {
            if ( !sonEnteros(listPanel.getValuesInTableAt(3)) ) {
                Advisor.messageBox("Los valores de la columna Prioridad deben ser enteros", "Error");
            } else {
                if (tieneValoresNulos(listPanel.getValuesInTableAt(4)) ){
                    Advisor.messageBox("Los valores de la columna Porcentaje no pueden ser vacios", "Error");
                } else {
                    _returns = true;
                }    
            }
        }
        return _returns;
    }
    
    private boolean tieneValoresNulos(Vector _vector){
        boolean _hayVacio = false;
        int i = 0;
        while ( !_hayVacio && i < _vector.size()) {
            if ( _vector.elementAt(i).toString().equals("")) {
                _hayVacio = true;
            }
            i++;
        }
        return _hayVacio;
    }
    
    private boolean sonEnteros(Vector _vector){
        boolean _noEnteros = false;
        int i = 0;
        while ( !_noEnteros && i < _vector.size()) {
            try {
                Integer.parseInt(_vector.elementAt(i).toString());
            } catch (Exception e) {
                // TODO: Add catch code
                _noEnteros = true;

            }
            i++;
        }
        return !_noEnteros;
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
        parent.refresh();
	getParentInternalFrame().close();
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {      
        super.setParentInternalFrame(_e);
        getParentInternalFrame().setInfo("Desvinculación y modificación de titulares");
    }
}
