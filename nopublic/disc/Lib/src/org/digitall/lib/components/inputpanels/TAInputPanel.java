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
 * TAInputPanel.java
 *
 * */
package org.digitall.lib.components.inputpanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;

import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicTextArea;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;

//

public class TAInputPanel extends BasicPanel {

    private BasicLabel lbl = new BasicLabel();
    private BasicLabel lblValidator = new BasicLabel();
    private BasicTextArea textArea = new BasicTextArea();
    private String componentName;
    private int dataType;
    private boolean required = true;
    private BorderLayout borderLayout1 = new BorderLayout();

    public TAInputPanel() {
	this(DataTypes.STRING, "na", false);
    }

    /**
     *
     * @param _dataType
     * @param _componentName
     * @param _required
     */
    public TAInputPanel(int _dataType, String _componentName, boolean _required) {
	try {
	    componentName = _componentName;
	    dataType = _dataType;
	    required = _required;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }


    private void jbInit() throws Exception {
	this.setSize(new Dimension(171, 80));
	this.setLayout(borderLayout1);
	lblValidator.setText("(*)");
	lblValidator.setForeground(new Color(231, 0, 0));
	lblValidator.setFont(new Font("Default", 1, 11));
	lblValidator.setBorder(null);
	String sLabelText = Environment.lang.getProperty(componentName) + ":";
	lbl.setText(sLabelText);
	if (required) {
	    this.add(lblValidator, BorderLayout.EAST);
	    lblValidator.setVisible(true);
	}
	textArea.setName(componentName);
	textArea.setBackground(Color.white);
	textArea.setFont(textArea.getFont().deriveFont(Font.BOLD));

	this.add(lbl, BorderLayout.NORTH);
	this.add(textArea, BorderLayout.CENTER);
    }

    public void setText(String _text) {
	textArea.setText(_text);
    }

    public void setBackgroundColor(Color _color) {
	textArea.setBackground(_color);	
    }
    
    public void setDisabledTextColor(Color _color){
	textArea.setDisabledTextColor(_color);
    }
    
    public String getText() {
	return textArea.getText();
    }

    public void setEditable(boolean _editable) {
	textArea.setEditable(_editable);
    }

    public void setEnabled(boolean _valor) {
	textArea.setEnabled(_valor);
	if (!_valor) {
	    textArea.setOpaque(false);
	    textArea.setBorder(BorderFactory.createLineBorder(new Color(150,150,150), 1));
	    textArea.setDisabledTextColor(new Color(255,255,255));
	} else {
	    textArea.setOpaque(true);
	}
    }

    public BasicTextArea getTextArea() {
	return textArea;
    }

    public int getDataType() {
	return dataType;
    }
}
