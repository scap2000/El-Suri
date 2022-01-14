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
 * JTDate.java
 *
 * */
package org.digitall.lib.components;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.text.MaskFormatter;

import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.calendar.SelectorFecha;
import org.digitall.lib.components.basic.BasicTextInput;
import org.digitall.lib.environment.Environment;

public class JTDate extends BasicTextInput implements KeyListener, FocusListener {

    private int minLimit = 100; //100 años hacia atrás
    private int maxLimit = 100; //100 años hacia adelante
    
    //Esta hará que la fecha ingresada sea válida
    //si está entre la fecha actual - minLimit y
    //fecha actual + maxLimit

    public static String NULLDATE = "  /  /    ";

    public JTDate() throws ParseException {
	this(100, 100);
    }
    
    public JTDate(int _minLimit, int _maxLimit) throws ParseException {
	super(new JTDateFormatter());
	minLimit = _minLimit;
	maxLimit = _maxLimit;
	setToolTipText("<html>F4: 01/01/" + Environment.currentYear + "<br>" +
			"F5: " + Proced.setFormatDate(Environment.currentDate, true) + "<br>" +
			/*"Doble click: Abrir almanaque<br>" +*/
		        "Click derecho: Borrar fecha</html");
	setHorizontalAlignment(CENTER);
	setToday();
	addKeyListener(this);
	addFocusListener(this);
	setInputVerifier(new InputVerifier() {

		      public boolean verify(JComponent input) {
			  boolean returns = false;
			  String _trydate = getText();
		          GregorianCalendar _past = new GregorianCalendar();
		          _past.add(GregorianCalendar.YEAR, -minLimit);
		          GregorianCalendar _future = new GregorianCalendar();
		          _future.add(GregorianCalendar.YEAR, maxLimit);
		          SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			  if (!_trydate.equalsIgnoreCase(NULLDATE)) {
			      try {
				  Date date = formatter.parse(getText());
				  String fecharet = formatter.format(date);
				  formatter.applyPattern("dd/MM/yyyy");
				  fecharet = formatter.format(date);
			          returns = fecharet.equalsIgnoreCase(_trydate) && date.after(_past.getTime()) && date.before(_future.getTime());
			      } catch (ParseException e) {
				  // TODO
			      }
			  } else {
			      returns = true;
			  }
			  if (!returns) {
			      Advisor.messageBox("Fecha no válida, debe estar comprendida entre " + formatter.format(_past.getTime()) + " y " + formatter.format(_future.getTime()), "Error");
			      setValue(null);
			  }
			  return returns;
		      }

		  }
	);

	addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
			if (isEnabled()) {
			    if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
				/*BasicTextInput _textInput = new BasicTextInput();
				_textInput.setValue(getText());
				SelectorFecha _selectorFecha = new SelectorFecha(_textInput);
				ComponentsManager.centerWindow(_selectorFecha);
				_selectorFecha.setModal(true);
				_selectorFecha.setVisible(true);
				setText(_textInput.getText());*/
			    } else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON3) {
				setValue(null);
			    }
			}
		    }

		});
    }

    public void keyTyped(KeyEvent e) {
	if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
	    //setValue(NULLDATE);
	    setValue(null);
	}
    }

    public void keyPressed(KeyEvent e) {

    }

    public void setToday() {
	setValue(new Date());
    }

    public void setFirstDayOfYear() {
	setValue("01/01/" + Environment.currentYear);
    }
    
    public void keyReleased(KeyEvent e) {
	if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
	    setValue(null);
	} else if (e.getKeyCode() == KeyEvent.VK_F5) {
	    setToday();
	} else if (e.getKeyCode() == KeyEvent.VK_F4) {
	    setFirstDayOfYear();
	}
    }

    public void focusGained(FocusEvent e) {

    }

    public void focusLost(FocusEvent e) {

    }

    @Override
    public void setValue(Object _object) {
	if (_object instanceof Date) {
	    super.setValue(_object);
	} else {
	    String _value = "";
	    try {
		_value += _object.toString();
	    } catch (Exception e) {
		//e.printStackTrace();
	    }
	    if (_value.length()<10) {
		super.setValue(null);
	    } else {
		super.setValue(_value);
	    }
	}
    }
}
class JTDateFormatter extends MaskFormatter {

    public JTDateFormatter() throws ParseException {
	super("##/##/####");
    }
    private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    public Object stringToValue(String text) throws ParseException {
	return formato.parseObject(text);
    }

    public String valueToString(Object value) throws ParseException {
	if (value instanceof Date) {
	    return formato.format((Date)value);
	} else  if (value instanceof String) {
            return formato.format(formato.parse((String)value));
        }
	if (value != null) {
	    return formato.format(new Date());
	} else {
	    return JTDate.NULLDATE;
	}
    }

}
