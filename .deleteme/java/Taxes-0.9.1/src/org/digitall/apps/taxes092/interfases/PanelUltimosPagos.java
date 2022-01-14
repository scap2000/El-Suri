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
 * PanelUltimosPagos.java
 *
 * */
package org.digitall.apps.taxes092.interfases;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.grid.GridPanel;

public class PanelUltimosPagos extends BasicPrimitivePanel {
    private BasicPanel panelCentro = new BasicPanel();
    private BasicPanel panelContenedor = new BasicPanel();
    private BorderLayout borderLayout1 = new BorderLayout();
    private CloseButton btnClose= new CloseButton();
    private PrintButton btnImprimirPagos = new PrintButton();
    
    private int[] sizeColumnList = {  147, 89, 121, 124, 114, 298, 298, 298 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(50000, sizeColumnList, "Últimos Pagos", dataRow);
    private Vector headerList = new Vector();
    
    public PanelUltimosPagos() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        this.setSize(new Dimension(713, 441));
        this.setPreferredSize(new Dimension(660, 512));
        listPanel.setPreferredSize(new Dimension(400, 250));
        listPanel.setBounds(new Rectangle(5, 15, 700, 380));
        panelCentro.setLayout(null);
        panelCentro.setBounds(new Rectangle(0, 1, 660, 300));
        panelCentro.setBorder(BorderPanel.getBorderPanel(""));
        panelContenedor.setLayout(borderLayout1);
        panelCentro.add(listPanel, BorderLayout.CENTER);
        panelContenedor.add(panelCentro, BorderLayout.CENTER);
        this.add(panelContenedor, BorderLayout.CENTER);
        addButton(btnClose);
        addButton(btnImprimirPagos);
        btnImprimirPagos.setToolTipText("Imprimir listado de pagos realizados");
        btnClose.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    btnClose_actionPerformed(e);
                                }
                            }
        );
        btnImprimirPagos.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    
                                }
                            }
        );
        
        listPanel.getTable().addMouseListener(new MouseAdapter() {

                                           public void mouseClicked(MouseEvent e) {
                                               if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                                                   
                                               } 
                                           }

                                       }
        );
        setheaderList();
        listPanel.refresh("");
    }
    
    private void setheaderList() {
        headerList.removeAllElements();
        headerList.addElement("*"); //idBien
        headerList.addElement("Catastro/ Dominio"); 
        headerList.addElement("Tipo");
        headerList.addElement("T.G.S");
        headerList.addElement("Inmobiliario");
        headerList.addElement("Automotor");
        headerList.addElement("Plan de Pago (T.G.S)");  
        headerList.addElement("Plan de Pago (Inmob)");
        headerList.addElement("Plan de Pago (Automotor)");
        
        listPanel.setParams("taxes.getUltimosPagos", "", headerList);
    }

    private void btnImprimirPagos_actionPerformed(ActionEvent e) {
        
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
        this.getParentInternalFrame().close();
    }
    
}
