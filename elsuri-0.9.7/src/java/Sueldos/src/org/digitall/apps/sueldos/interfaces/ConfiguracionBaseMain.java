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
 * ConfiguracionBaseMain.java
 *
 * */
package org.digitall.apps.sueldos.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;

public class ConfiguracionBaseMain extends BasicPrimitivePanel{
    
    private ConfiguracionBase configuracionBase = new ConfiguracionBase();
    private SaveDataButton btnSave = new SaveDataButton();
    private CloseButton btnClose = new CloseButton();
    private BasicPanel pContainer = new BasicPanel();

    public ConfiguracionBaseMain() {

	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(692, 457));
        pContainer.setPreferredSize(new Dimension(692, 457));
        pContainer.add(configuracionBase, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(692, 447));
        configuracionBase.setBounds(new Rectangle(0, 0, 690, 425));
        this.add(pContainer, BorderLayout.CENTER);
	addButton(btnClose);
	addButton(btnSave);
	btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    btnSave_actionPerformed(e);
				}
			    }
	);
	btnClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}
			    }
	);
	
	btnClose.setEnabled(true);
        pContainer.setLayout(null);
        btnSave.setEnabled(true);
	/*configuracionBase.getPanelEmpleados().refresh();
	configuracionBase.getPanelSindicatos().refresh();
	configuracionBase.getPanelCategorias().refresh();
	configuracionBase.getPanelArticulos().refresh();
	configuracionBase.getPanelNovedades().refresh();
	configuracionBase.getPanelObrasSociales().refresh();
	configuracionBase.getPanelFamiliares().refresh();
	configuracionBase.getPanelAportes().refresh();
	configuracionBase.getPanelSociales().refresh();*/
	//configuracionBase.getPanelEmpleados().started();
    }
    
    /*private void btnAccept_actionPerformed(ActionEvent e) {
	configuracionBase.getPanelEmpleados().started();
	configuracionBase.getPanelSindicatos().started();
	configuracionBase.getPanelCategorias().started();
	configuracionBase.getPanelArticulos().started();
	configuracionBase.getPanelNovedades().started();
	configuracionBase.getPanelObrasSociales().started();
	configuracionBase.getPanelFamiliares().started();
	configuracionBase.getPanelAportes().started();
	configuracionBase.getPanelSociales().started();
	btnClose.setEnabled(true);
	btnSave.setEnabled(true);
	btnAccept.setEnabled(false);
    }*/
    
    private void btnSave_actionPerformed(ActionEvent e) {
	saveAll(true);
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	saveAll(true);
	this.getParentInternalFrame().setIcon(true);
    }
    
    private void saveAll(boolean _exit){
	configuracionBase.getPanelEmpleados().saveEmpleados(_exit);
	configuracionBase.getPanelSindicatos().saveSindicatos(_exit);
	configuracionBase.getPanelCategorias().saveCategorias(_exit);
	configuracionBase.getPanelArticulos().saveArticulos(_exit);
	configuracionBase.getPanelNovedades().saveNovedades(_exit);
	configuracionBase.getPanelObrasSociales().saveObrasSociales(_exit);
	configuracionBase.getPanelFamiliares().saveFamiliar(_exit);
	configuracionBase.getPanelAportes().saveAportes(_exit);
	configuracionBase.getPanelSociales().saveSociales(_exit);
    }
}
