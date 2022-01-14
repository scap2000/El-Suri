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
 * RendicionCuentasMain.java
 *
 * */
package org.digitall.apps.sueldos.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.buttons.SaveDataButton;

public class RendicionCuentasMain extends BasicPrimitivePanel{
    
    private BasicPanel container = new BasicPanel();
    private CloseButton btnClose = new CloseButton();
    private PrintButton btnPrint = new PrintButton();
    private AddButton btnAdd = new AddButton();
    private SaveDataButton btnSave = new SaveDataButton();
    private CBInput cbInformes = new CBInput(0,"Informe");
    private CBInput cbAños = new CBInput(0,"Año");
    private CBInput cbMeses = new CBInput(0,"Mes");
    
    public RendicionCuentasMain() {
        super();
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        container.setLayout(null);
        container.add(cbInformes, null);
        container.add(cbAños, null);
        container.add(cbMeses, null);
        this.add(container, BorderLayout.CENTER);
        btnAdd.setToolTipText("Crear Informe Rendición de Cuentas");
        btnClose.setToolTipText("Cerrar Ventana");
        btnPrint.setToolTipText("Imprimir Informe Rendición de Cuentas");
        btnSave.setToolTipText("Generar Informe Rendición de Cuentas");
        cbInformes.setBounds(new Rectangle(10, 130, 300, 35));
        cbInformes.setPreferredSize(new Dimension(250, 50));
        cbInformes.setBounds(new Rectangle(10, 5, 320, 35));
        cbInformes.autoSize();
        cbAños.setBounds(new Rectangle(10, 130, 300, 35));
        cbAños.setPreferredSize(new Dimension(250, 50));
        cbAños.setBounds(new Rectangle(348, 5, 85, 35));
        cbAños.autoSize();
        cbMeses.setBounds(new Rectangle(10, 130, 300, 35));
        cbMeses.setPreferredSize(new Dimension(250, 50));
        cbMeses.setBounds(new Rectangle(450, 5, 140, 35));
        cbMeses.autoSize();
        addButton(btnClose);
        addButton(btnPrint);
        addButton(btnAdd);
        addButton(btnSave);
        btnClose.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    btnClose_actionPerformed(e);
                                }

                            }   
        );
        loadComboInformes();
        this.setSize(new Dimension(601, 89));
    }
    
    private void loadComboInformes(){
        //cbInformes.loadJCombo("sueldos.getAllInformesRendiciones","");
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {      
        getParentInternalFrame().close();
    }
}
