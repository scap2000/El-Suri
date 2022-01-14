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
 * FeeOptions.java
 *
 * */
package org.digitall.apps.taxes.interfases;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.digitall.lib.components.basic.BasicDialog;

public class FeeOptions extends BasicDialog {

    private JPanel OptionPanel = new JPanel();
    private JRadioButton rbOpcion1 = new JRadioButton();
    private JRadioButton rbOpcion2 = new JRadioButton();
    //private JRadioButton rbOpcion3 = new JRadioButton();
    private ButtonGroup grupoBtn = new ButtonGroup();
    private int idTipoImpuesto = -1;
    
    private JLabel lblTitle = new JLabel();
    private JButton btnAccept = new JButton();
    private JTextField tfOption;
    private JLabel lblSubTitle = new JLabel();

    public FeeOptions() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    private void jbInit() throws Exception {
	this.setLayout( null );
	this.setSize(new Dimension(414, 217));
	this.setTitle("Aviso!");
	this.setPreferredSize(new Dimension(565, 300));
	
	rbOpcion1.setText("<html>Registrar el último pago. Luego de registrarlo deberá<br>recalcular la deuda del catastro/dominio</html>");
	rbOpcion1.setBounds(new Rectangle(15, 10, 360, 35));
	//rbOpcion2.setText("Calcular la deuda a partir de la Fecha de Registro");
	rbOpcion2.setText("Cancelar");
	rbOpcion2.setBounds(new Rectangle(15, 55, 360, 20));
	rbOpcion2.setFont(new Font("Dialog", 1, 12));
	/*rbOpcion3.setText("Continuar");
	rbOpcion3.setBounds(new Rectangle(15, 70, 495, 15));
	rbOpcion3.setLayout(null);
	rbOpcion3.setFont(new Font("Dialog", 1, 12));*/
	lblTitle.setText("El Catastro/Dominio no registra último pago");
	lblTitle.setBounds(new Rectangle(17, 5, 375, 20));
	lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
	lblTitle.setFont(new Font("Dialog", 1, 14));
	btnAccept.setText("Aceptar");
	btnAccept.setBounds(new Rectangle(157, 160, 95, 25));
	btnAccept.setMnemonic('A');
	btnAccept.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnAccept_actionPerformed(e);
				}

			    }
	);
	lblSubTitle.setText("¿Qué desea hacer?");
	lblSubTitle.setBounds(new Rectangle(17, 35, 375, 20));
	lblSubTitle.setToolTipText("null");
	lblSubTitle.setHorizontalAlignment(SwingConstants.CENTER);
	lblSubTitle.setFont(new Font("Dialog", 1, 14));
	OptionPanel.setBounds(new Rectangle(9, 60, 390, 90));
	OptionPanel.setLayout(null);
	OptionPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	grupoBtn.add(rbOpcion1);
	grupoBtn.add(rbOpcion2);
	//grupoBtn.add(rbOpcion3);
	rbOpcion1.setSelected(true);
	rbOpcion1.setFont(new Font("Dialog", 1, 12));
	//OptionPanel.add(rbOpcion3, null);
	OptionPanel.add(rbOpcion2, null);
	OptionPanel.add(rbOpcion1, null);
	this.getContentPane().add(lblSubTitle, null);
	this.getContentPane().add(OptionPanel, null);
	this.getContentPane().add(btnAccept, null);
	this.getContentPane().add(lblTitle, null);
    }
 
    private void btnAccept_actionPerformed(ActionEvent e) {
	if (rbOpcion1.isSelected())  {
	    tfOption.setText("1");
	} else if (rbOpcion2.isSelected()) {
	    tfOption.setText("2");
	} /*else if (rbOpcion3.isSelected()) {
	    tfOption.setText("3");
	}*/
	this.dispose();
    }


    public void setTfOption(JTextField _tfOption) {
	tfOption = _tfOption;
    }

    public void setIdTipoImpuesto(int idTipoImpuesto) {
	this.idTipoImpuesto = idTipoImpuesto;
	loadTitle();
	
    }

    private void loadTitle() {
	switch (idTipoImpuesto){
	    case 1: {
		    lblTitle.setText("el Catastro no registra último Pago de");
		    lblSubTitle.setText(" la Tasa General de Servicios, ¿Qué desea hacer?");
	    }
		break;
	    case 2: {
		    lblTitle.setText("el Catastro no registra último Pago del");
		    lblSubTitle.setText("Impuesto Inmobiliario, ¿Qué desea hacer?");
		}
		break;
	    case 3: {
		    lblTitle.setText("el Dominio no registra último Pago del");
		    lblSubTitle.setText("Impuesto Automotor, ¿Qué desea hacer?");
		}
		break;  
	}
    }

}
