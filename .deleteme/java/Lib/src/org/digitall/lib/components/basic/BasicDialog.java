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
 * BasicDialog.java
 *
 * */
package org.digitall.lib.components.basic;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.digitall.lib.components.basic.BasicInternalFrame;

public class BasicDialog extends JDialog {

    private boolean needSaving = false;

    public BasicDialog() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicDialog(BasicDialog _parent) {
	super(_parent);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicDialog(Frame _parent, String _title) {
	super(_parent, _title);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicDialog(JFrame _parent) {
	super(_parent);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicDialog(Frame _parent, String _title, boolean _modal) {
	super(_parent, _title, _modal);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicDialog(Dialog _parent, String _title, boolean _modal) {
	super(_parent, _title, _modal);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//getContentPane().setBackground(BasicConfig.INTERNALFRAME_BACKGROUND_COLOR);
	setContentPane(new BasicContainer());
	setDefaultCloseOperation(BasicInternalFrame.DO_NOTHING_ON_CLOSE);
	//setBorder(BorderFactory.createLineBorder(Color.RED, 2));
	//setUndecorated(true);
	getLayeredPane().setBorder(BorderFactory.createLineBorder(Color.RED, 2));
	addWindowListener(new WindowAdapter() {

			    public void windowClosing(WindowEvent e) {
				closeBasicDialog(true);
			    }

			}
	);
    }

    public void closeBasicDialog() {
	dispose();///???
    }

    public void closeBasicDialog(boolean _askSaveData) {
	if (_askSaveData && needSaving) {
	    int answer = JOptionPane.showConfirmDialog(this, "Â¿Desea guardar los cambios?", "Cerrar", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	    if (answer == JOptionPane.OK_OPTION) {
		if (saveData()) {
		    closeBasicDialog();
		}
	    } else if (answer == JOptionPane.NO_OPTION) {
		closeBasicDialog();
	    }
	} else {
	    closeBasicDialog();
	}
    }

    public boolean saveData() {
	//GRABAR LA INFO
	return true;
    }

    public void setNeedSaving(boolean _needSaving) {
	needSaving = _needSaving;
    }

    /* protected void paintComponent(Graphics g) {
	 Graphics2D g2 = (Graphics2D)g;
	 g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	 int w = getWidth();
	 int h = getHeight();
	 GradientPaint gradient = new GradientPaint(20, 0, BasicConfig.PANEL_GRADIENT_START_COLOR, 20, h, BasicConfig.PANEL_GRADIENT_END_COLOR, false);
	 g2.setPaint(gradient);
	 g2.fillRect(0, 0, w, h);
	 super.paintComponent(g);
     }*/

}
