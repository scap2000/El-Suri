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
 * RecursosVariosPanel.java
 *
 * */
package org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.sql.Types;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JTable;

import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class RecursosVariosPanel extends BasicPanel{
    private int[] sizeColumnList = {193,71, 69, 75};
    private Vector dataRow = new Vector();
    private GridPanel grilla = new GridPanel(5000, sizeColumnList, "Recursos en General", dataRow){
	public void calculate(){
	    selectRowGrid(0);
	}
    };
    private Vector headerList = new Vector();
    private BorderLayout borderLayout1 = new BorderLayout();    
    private ListadoRecursosPatrimonialesPanel listadoRecursosPatrimonialesPanel = new ListadoRecursosPatrimonialesPanel(true);
    private BasicPanel panelNorte = new BasicPanel();
    private FindButton btnSearch = new FindButton();
    private TFInput tfSearch = new TFInput(DataTypes.STRING, "Recurso", false);

    public RecursosVariosPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	panelNorte.setLayout(null);
	panelNorte.setPreferredSize(new Dimension(290, 45));
	panelNorte.setSize(new Dimension(290, 45));
	panelNorte.setBorder(BorderPanel.getBorderPanel(""));
	tfSearch.setBounds(new Rectangle(10, 5, 245, 35));
	this.setPreferredSize(new Dimension(290, 300));
	this.setSize(new Dimension(290, 300));
	this.setLayout(borderLayout1);
	grilla.setPreferredSize(new Dimension(290, 300));
	grilla.setSize(new Dimension(290, 300));
	btnSearch.setBounds(new Rectangle(250, 15, 40, 25));
	btnSearch.addActionListener(new ActionListener() {
				 public void actionPerformed(ActionEvent e) {
				     btnSearch_actionPerformed(e);
				 }
			     }
	);
	tfSearch.getTextField().addKeyListener(new KeyAdapter() {
					    public void keyReleased(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER)
						    buscar(tfSearch.getValue().toString());
					    }
					}
	);      
	this.add(grilla, BorderLayout.CENTER);
	panelNorte.add(tfSearch, null);
	panelNorte.add(btnSearch, null);
	this.add(panelNorte,BorderLayout.NORTH);
	setHeaderList();
	MouseListenerTabla mouseListenerTabla = new MouseListenerTabla();
	grilla.getTable().addMouseListener(mouseListenerTabla);	
	grilla.setCellEditor(Types.INTEGER,2);		
	grilla.setCellEditor(Types.INTEGER,3);
	//grilla.setCellEditor(Types.INTEGER,4);	
    }
    
    private void setHeaderList() {      
	headerList.removeAllElements();
	
	headerList.addElement("*");             //0: id recurso 
	headerList.addElement("Nombre");        //1: nombre
	headerList.addElement("Existencia");    //2: stock
	headerList.addElement("Asignado");      //3: Asignado
	headerList.addElement("Disponible");    //4: Disponible
	headerList.addElement("*");             //5: el recurso es para..	
	grilla.getTable().addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {                        
					    if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						//loadPersonMgmt();                                              
						selected(e);
					    }
					}
					
				        public void mousePressed(MouseEvent e) {                        
				            if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
				                //loadPersonMgmt();                                              
				                selected(e);
				            }
				        }

				    }
	);	
	grilla.setParams("personalfiles.getAllRecursosGral", "''", headerList); 
	grilla.refresh("");
	listadoRecursosPatrimonialesPanel.setRecursosVariosPanel(this);
    }
    
    private void selected(MouseEvent e){
	int filaSeleccionada = grilla.getTable().rowAtPoint(e.getPoint());
	grilla.selectAllItems(false);
	grilla.getTable().setValueAt(true,filaSeleccionada,0);
	grilla.getTable().getSelectionModel().setSelectionInterval(filaSeleccionada,filaSeleccionada);
    }
    
    private void selectRowGrid(int _row){
	grilla.getTable().setValueAt(true,_row,0);
	grilla.getTable().getSelectionModel().setSelectionInterval(_row,_row);
    }
    
    public void refresh() {
	buscar(tfSearch.getValue().toString());
    }
    
    private void btnSearch_actionPerformed(ActionEvent e) {     
	buscar(tfSearch.getValue().toString());      
    }
    
    private void buscar(String _pista){
	grilla.refresh("'"+_pista+"'");
    }
    
    public JTable getGrilla() {                 
	return grilla.getTable();
    }
    
    public GridPanel getGrillaPanelRecursos() {                 
	return grilla;
    }
    
    public GridPanel getGrillaPanelPatrmonio() {         
	return listadoRecursosPatrimonialesPanel.getGrillaPanelPatrimonio();
    }
    
    public RecursosVariosPanel getRecursosVariosPanel() {
	return this;
    }
    
    public ListadoRecursosPatrimonialesPanel getListadoRecursosPatrimonialesPanel() {
	return listadoRecursosPatrimonialesPanel;
    }
    
    public class MouseListenerTabla implements MouseListener{

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {	    
	    if (e.getClickCount() == 2) {				
		/*listadoRecursosPatrimonialesPanel.refresh();
		
		ExtendedInternalFrame stockMgmtContainer = new ExtendedInternalFrame("Seleccionar el patrimonio por asignar");
		stockMgmtContainer.setCentralPanel(listadoRecursosPatrimonialesPanel);   
		stockMgmtContainer.show();  */
	    }
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

    }

}
