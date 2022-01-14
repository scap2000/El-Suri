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
 * PanelNewGroup.java
 *
 * */
package org.digitall.apps.systemmanager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicTextField;

public class PanelNewGroup extends BasicContainerPanel {

    private BasicLabel lblGroupName = new BasicLabel();
    private BasicLabel lblSystem = new BasicLabel();
    private BasicTextField tfName = new BasicTextField();
    public String sistema = "";

    public PanelNewGroup() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	this.setSize(new Dimension(271, 47));
	tfName.setBounds(new Rectangle(180, 13, 85, 20));
	lblGroupName.setText("Group name:");
	lblGroupName.setBounds(new Rectangle(5, 13, 75, 20));
	lblGroupName.setFont(new Font("Dialog", 1, 11));
	lblGroupName.setHorizontalAlignment(SwingConstants.LEFT);
	lblSystem.setBounds(new Rectangle(85, 13, 95, 20));
	lblSystem.setFont(new Font("Dialog", 1, 11));
	lblSystem.setForeground(Color.red);
	lblSystem.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	lblSystem.setHorizontalAlignment(SwingConstants.RIGHT);
	this.add(lblSystem, null);
	this.add(lblGroupName, null);
	this.add(tfName, null);
    }

    public String getGroupName() {
	String cadena = tfName.getText().trim().toLowerCase();
	return cadena;
    }

    public void setSystem(String _sistema) {
	lblSystem.setText(_sistema + "_");
    }

}
