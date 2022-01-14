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
 * ConnectionPanel.java
 *
 * */
package org.digitall.common.geo.mapping.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPasswordField;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CancelDataButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.geo.mapping.classes.GeometrySetConfig;

public class ConnectionPanel extends BasicPrimitivePanel {

    private BasicContainerPanel centralPanel = new BasicContainerPanel();
    private TFInput tfServer = new TFInput(DataTypes.STRING, "Servidor", false);
    private TFInput tfDatabase = new TFInput(DataTypes.STRING, "Base de Datos", false);
    private TFInput tfUser = new TFInput(DataTypes.STRING, "Usuario", false);
    
    private BasicPasswordField tfPass = new BasicPasswordField();
    private SaveDataButton btnAccept = new SaveDataButton();
    private CancelDataButton btnCancel = new CancelDataButton();

    private GeometrySetConfig geometrySetConfig;
    private BasicLabel lbPass = new BasicLabel();

    public ConnectionPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(260, 207));
	tfServer.setBounds(new Rectangle(10, 5, 235, 35));
	tfDatabase.setBounds(new Rectangle(10, 125, 80, 35));
	tfDatabase.setBounds(new Rectangle(10, 45, 235, 35));
	tfUser.setBounds(new Rectangle(10, 85, 235, 35));
        tfPass.setBounds(new Rectangle(10, 140, 235, 20));
        centralPanel.add(lbPass, null);
        centralPanel.add(tfServer, null);
        centralPanel.add(tfDatabase, null);
        centralPanel.add(tfUser, null);
        centralPanel.add(tfPass, null);
        btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
        btnCancel.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnCancel_actionPerformed(e);
				 }

			     }
	);
        lbPass.setText("Contraseña");
        lbPass.setBounds(new Rectangle(10, 125, 235, 14));
        centralPanel.setLayout(null);
        this.add(centralPanel, BorderLayout.CENTER);
	this.addButton(btnAccept);
	this.addButton(btnCancel);
    }

    private void btnAccept_actionPerformed(ActionEvent e) {
	saveData();
    }
    
    private void btnCancel_actionPerformed(ActionEvent e) {
        getParentInternalFrame().close();
    }

    public boolean saveData() {
	geometrySetConfig.setConnectionParams(tfServer.getString(), tfDatabase.getString(), tfUser.getString(), new String(tfPass.getPassword()));
	getParentInternalFrame().close();
	return true;
    }

    public void setGeometrySetConfig(GeometrySetConfig _geometrySetConfig) {
	geometrySetConfig = _geometrySetConfig;
	loadData();
    }

    private void loadData() {
	tfServer.setValue(geometrySetConfig.getServerURL());
        tfDatabase.setValue(geometrySetConfig.getDatabase());
        tfUser.setValue(geometrySetConfig.getUser());
        tfPass.setText(geometrySetConfig.getPassword());
	//getParentInternalFrame().setInfo(layerConfig.getAlias());
    }
}
