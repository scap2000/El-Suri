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
 * TFPasswordInput.java
 *
 * */
package org.digitall.lib.components.inputpanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;

import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPasswordField;
import org.digitall.lib.environment.Environment;

//

public class TFPasswordInput extends BasicPanel {

    private BasicLabel lbl = new BasicLabel();
    private BasicLabel lblValidator = new BasicLabel();
    private BasicPasswordField pwdField = new BasicPasswordField();
    private String componentName;
    private boolean required = true;
    private boolean editable = true;
    private ActionListener actionListener;
    private MouseAdapter mouseAdapter;
    private KeyAdapter keyAdapter;
    private KeyListener keyListener;

    /**
     *
     * @param _componentName
     * @param _required
     */
    public TFPasswordInput(String _componentName, boolean _required) {
	try {
	    componentName = _componentName;
	    required = _required;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public TFPasswordInput() {
	try {
	    componentName = "Password";
	    required = true;
	    editable = true;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(171, 77));
	lblValidator.setText("(*)");
	lblValidator.setForeground(new Color(231, 0, 0));
	lblValidator.setFont(new Font("Default", 1, 11));
	lblValidator.setBorder(null);
	lbl.setText("Label of Dictionary");
	lbl.setBounds(new Rectangle(10, 10, 40, 20));
	//pwdField.setBounds(new Rectangle(10, 30, 120, 20));
	//pwdField.setBorder(BorderFactory.createLineBorder(Color.red, 1));
	//pwdField.setForeground(Color.red);
	//char k = 149;
	//pwdField.setEchoChar(k);
	//Font font = new Font("Courier",12,Font.BOLD);
	//pwdField.setFont(font);
	/*this.add(txtField, null);
        this.add(lbl, null);*/
	pwdField.setFont(new Font("SansSerif", 1, 18));
	load();
    }

    private void load() {
	String sLabelText = Environment.lang.getProperty(componentName) + ":";
	lbl.setText(sLabelText);
	lbl.setBounds(new Rectangle(0, 0, this.getWidth(), 14));
	if (required) {
	    lblValidator.setBounds(new Rectangle(this.getWidth() - 12, 14, 20, 20));
	    this.add(lblValidator, null);
	    lblValidator.setVisible(true);
	}
	pwdField.setName(componentName);
	pwdField.setBounds(new Rectangle(0, 15, 50, 20));
	pwdField.setEditable(editable);
	pwdField.setBackground(Color.white);
	this.add(lbl, null);
	this.add(pwdField, null);
    }

    public void autoSize() {
	int b = 0;
	if (required) {
	    lblValidator.setBounds(new Rectangle(this.getWidth() - 18, 14, 20, 20));
	    lbl.setBounds(new Rectangle(0 + b, 0 + b, (this.getWidth() - 20) - b, 14 - b));
	    pwdField.setBounds(new Rectangle(0 + b, 14 + b, (this.getWidth() - 20) - b, (20 - b)));
	} else {
	    lbl.setBounds(new Rectangle(0+b, 0+b, this.getWidth()-b, 14-b));
	    pwdField.setBounds(new Rectangle(0+b, 14, this.getWidth(), 20-b));
	}
    }

    public void setText(String _text) {
	pwdField.setText(_text);
    }

    public void setBackgroundColor(Color _color) {
	pwdField.setBackground(_color);
    }

    public void setDisabledTextColor(Color _color) {
	pwdField.setDisabledTextColor(_color);
    }

    public String getPassword() {
	return new String(pwdField.getPassword());
    }

    public void setActionListener(ActionListener _actionListener) {
	actionListener = _actionListener;
	pwdField.addActionListener(actionListener);
    }

    public void setKeyListener(KeyAdapter _keyAdapter) {
	keyAdapter = _keyAdapter;
	pwdField.addKeyListener(keyAdapter);
    }

    public void setKeyListener(KeyListener _keyListener) {
	keyListener = _keyListener;
	pwdField.addKeyListener(keyListener);
    }

    public void setMouseListener(MouseAdapter _mouseAdapter) {
	mouseAdapter = _mouseAdapter;
	pwdField.addMouseListener(mouseAdapter);
    }

    public void setEditable(boolean _valor) {
	pwdField.setEditable(_valor);
    }

    public void setEnabled(boolean _valor) {
	pwdField.setEnabled(_valor);
    }
    
    public BasicPasswordField getPasswordField() {
	return pwdField;
    }

}
