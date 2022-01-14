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
 * JTFecha.java
 *
 * */
package org.digitall.lib.components;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import javax.swing.text.MaskFormatter;

import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.calendar.SelectorFecha;
import org.digitall.lib.components.basic.BasicTextInput;

//

public class JTFecha extends BasicTextInput {

    public JTFecha() {
	super(new SimpleDateFormat("dd/MM/yyyy"));
	setValue("");
	setEditable(false);
	//setBackground(new Color(255, 255, 255));
	//setBorder(BorderFactory.createLineBorder(new Color(196, 50, 50), 2));
	setHorizontalAlignment(CENTER);
	setToolTipText("Botón izquierdo: seleccionar fecha; Botón derecho: borrar fecha");
	addMouseListener(new MouseAdapter() {

		    public void mouseClicked(MouseEvent e) {
			if (isEnabled()) {
			    if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
				BasicTextInput kk = new BasicTextInput();
				kk.setValue(((BasicTextInput)e.getSource()).getText());
				SelectorFecha c = new SelectorFecha(kk);
				org.digitall.lib.components.ComponentsManager.centerWindow(c);
				c.setModal(true);
				c.show();
				setText(kk.getText());
			    } else {
				setText("");
			    }
			}
		    }

		});
    }

   /* public JTFecha() {
	super(new DateFormat());
	setValue(new Date());
	// Definición de la máscara de DNI
	MaskFormatter maskDNI = null;
	try {
	  maskDNI = new MaskFormatter("##/##/####");
	} catch (ParseException e) {
	    // De momento, no hacemos nada
	  }
	// El caràcter a mostrar en las posiciones escribibles es el
	// subrayado.
	maskDNI.setPlaceholderCharacter('_');
	maskDNI.install(this);
	setFormatter(maskDNI);
	maskDNI.setOverwriteMode(true);
	setEditable(true);
	//setBackground(new Color(255, 255, 255));
	//setBorder(BorderFactory.createLineBorder(new Color(196, 50, 50), 2));
	setHorizontalAlignment(CENTER);
	setToolTipText("Botón izquierdo: seleccionar fecha; Botón derecho: borrar fecha");
	addMouseListener(new MouseAdapter() {

		    public void mouseClicked(MouseEvent e) {
			if (isEnabled()) {
			    if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
				BasicTextInput kk = new BasicTextInput(((BasicTextInput)e.getSource()).getText());
				SelectorFecha c = new SelectorFecha(kk);
				org.digitall.lib.components.ComponentsManager.centerWindow(c);
				c.setModal(true);
				c.show();
				setText(kk.getText());
			    } else {
				setText("");
			    }
			}
		    }

		});
    }*/


    public String getSQLFecha(String _campo) {
	String fecha = super.getText();
	if (fecha.length() > 0) {
	    return _campo + " = '" + Proced.setFormatDate(fecha, false) + "'";
	} else {
	    return _campo + " = null";
	}
    }
    
    public void setValue(Object _object) {
	//super.setValue(_object);
	setText(_object.toString());
	//System.out.println("FECHA: "  + _object);
	if (_object != null) {
	    if (_object.toString().length() != 0) {
		//super.setValue(_object);
	    }
	}
    }

    public Date getDate() {
	Calendar cal = Calendar.getInstance();
	String fecha = super.getText();
	if (fecha.length() > 0) {
	    String[] dateParts = fecha.split("/");
    	    cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateParts[0]));
	    cal.set(Calendar.MONTH, Integer.parseInt(dateParts[1]));
    	    cal.set(Calendar.YEAR, Integer.parseInt(dateParts[2]));
	    return cal.getTime();
    	} else {
	    return null;
	}
    }

}
