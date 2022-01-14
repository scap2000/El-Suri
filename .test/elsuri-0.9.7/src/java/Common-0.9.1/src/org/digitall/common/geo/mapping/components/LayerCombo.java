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
 * LayerCombo.java
 *
 * */
package org.digitall.common.geo.mapping.components;

import java.awt.BorderLayout;
import java.awt.Component;

import java.awt.GridLayout;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.Map;

import javax.swing.ComboBoxEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.border.BevelBorder;

public class LayerCombo extends JComboBox {

    public LayerCombo() {
	setRenderer(new myRen());
	addItemListener(new ItemListener() {

		     public void itemStateChanged(ItemEvent e) {
			 System.out.println(e);
			 System.out.println(getItemAt(0).getClass());
		     }

		 }
	);
    }

    class myRen extends JPanel implements ListCellRenderer {

	JPanel testPanel = null;
	JCheckBox text1 = null;
	JLabel text2 = null;

	myRen() {
	    text1 = new JCheckBox("", true);
	    addMouseListener(new MouseListener() {

			  public void mouseClicked(MouseEvent e) {
			      System.out.println(e);
			  }

			  public void mousePressed(MouseEvent e) {
			      System.out.println(e);
			  }

			  public void mouseReleased(MouseEvent e) {
			      System.out.println(e);
			  }

			  public void mouseEntered(MouseEvent e) {
			      System.out.println(e);
			  }

			  public void mouseExited(MouseEvent e) {
			      System.out.println(e);
			  }

		      }
	    );
	    text2 = new JLabel("", 10);
	    testPanel = new JPanel();
	    testPanel.setLayout(new GridLayout(1, 2));
	    testPanel.add(text1);
	    testPanel.add(text2);
	}

	public Component getListCellRendererComponent(JList list, Object value, int idx, boolean isSel, boolean hasFocus) {
	    if (value != null) {
		text1.setText((String)value);
		text2.setText(((String)value).substring(1, 2));
	    }
	    testPanel.setBackground((isSel)?list.getSelectionBackground(): list.getBackground());
	    testPanel.setForeground((isSel)?list.getSelectionForeground(): list.getForeground());
	    return testPanel;
	}

    }

}
