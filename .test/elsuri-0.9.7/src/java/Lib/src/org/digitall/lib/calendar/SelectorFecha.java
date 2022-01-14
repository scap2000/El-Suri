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
 * SelectorFecha.java
 *
 * */
package org.digitall.lib.calendar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Calendar;

import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicTextInput;
import org.digitall.lib.components.buttons.CloseButton;

public class SelectorFecha extends BasicDialog {

    private SelectorFechaPanel calendar = new SelectorFechaPanel(Calendar.MONDAY, true, 1900, 2099, this);
    private BasicButton bseleccionar = new BasicButton();
    private CloseButton bcerrar = new CloseButton();
    private BasicLabel jLabel2 = new BasicLabel();
    private BasicPanel jPanel1 = new BasicPanel();
    private BasicTextInput jtfechaselec = new BasicTextInput();
    private String fechax = "";
    static String fechaselec, lafecha = "";
    static String band = "";
    private BasicTextInput jtfecha = new BasicTextInput();

    /**
     * FORMULARIO PARA LLEVAR UN CALENDARIO LABORAL, SE DEBERA CARGAR LOS DIAS QUE NO SON LABORALES.
     * YA SEA POR SABADO Y DOMINGO, FERIADOS U OTROS.
     */
    public SelectorFecha(BasicTextInput _jtfecha) {
	jtfecha = _jtfecha;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setIconImage(Toolkit.getDefaultToolkit().getImage(Paso2.class.getResource("iconos/16x16/calendario.gif")));
	this.getContentPane().setLayout(null);
	this.setSize(new Dimension(353, 285));
	this.setTitle("Calendario");
	//this.setBackground(Color.lightGray);
	calendar.setBounds(new Rectangle(10, 10, 320, 210));
	calendar.setBackground(Color.white);
	bseleccionar.setText("Seleccionar");
	bseleccionar.setBounds(new Rectangle(25, 360, 129, 25));
	bseleccionar.setMnemonic('s');
	bseleccionar.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bseleccionar_actionPerformed(e);
		    }

		});
	bcerrar.setBounds(new Rectangle(240, 225, 92, 25));
	bcerrar.setMnemonic('c');
	jPanel1.setBackground(new Color(112, 145, 204));
	jtfechaselec.setEditable(false);
	jtfechaselec.setDisabledTextColor(Color.red);
	jtfechaselec.setEnabled(false);
	jtfechaselec.setBackground(Color.white);
	calendar.initializeCalendar();
	this.getContentPane().add(jLabel2, null);
	jPanel1.add(jtfechaselec, null);
	this.getContentPane().add(jPanel1, null);
	this.getContentPane().add(bcerrar, null);
	this.getContentPane().add(bseleccionar, null);
	this.getContentPane().add(calendar, null);
	jtfechaselec.setText("00/00/0000");
	//aca va 00/00/0000
	//jLabel2.setForeground(Color.lightGray);
	jLabel2.setFont(new Font("Dialog", 1, 13));
	jLabel2.setOpaque(true);
	jPanel1.setLayout(null);
	//jPanel1.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
	jPanel1.setBounds(new Rectangle(20, 310, 325, 45));
	jtfechaselec.setForeground(Color.red);
	jtfechaselec.setFont(new Font("Dialog", 1, 15));
	jLabel2.setBounds(new Rectangle(30, 300, 138, 15));
	jLabel2.setText(" Fecha Seleccionada ");
	jtfechaselec.setBounds(new Rectangle(105, 10, 100, 25));
	//((BasicPanel)this.getContentPane()).setBackground(new Color(112, 145, 204));
	bcerrar.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bcerrar_actionPerformed(e);
		    }

		});
    }

    private void bcerrar_actionPerformed(ActionEvent e) {
	this.dispose();
    }

    /**
     * SELECCIONA UNA FECHA DETERMINADA Y LA DEVUELVE AL FORMULARIO QUE LA SOLICITO
     */
    private void bseleccionar_actionPerformed(ActionEvent e) {
	jtfecha.setText(jtfechaselec.getText());
	this.dispose();
    }

    public void setFechaX(String _fechax) {
	fechax = _fechax;
	try {
	    jtfecha.setValue(Proced.setFormatDate(fechax, true));
	    dispose();
	} catch (Exception x) {
	    x.printStackTrace();
	    org.digitall.lib.components.Advisor.messageBox("Error al asignar la fecha", "Error");
	}
    }

}
