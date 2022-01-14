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
 * BasicTabbedPane.java
 *
 * */
package org.digitall.lib.components.basic;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class BasicTabbedPane extends JTabbedPane {

    public BasicTabbedPane() {
	super();
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicTabbedPane(int _tabPlacement, int _tabLayoutPolicy) {
	super(_tabPlacement, _tabLayoutPolicy);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setUI(new BasicTabbedPaneUI() {

	   protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
	       g.setColor(!isSelected ? getBackgroundAt(tabIndex).darker() : getBackgroundAt(tabIndex));
	       switch (tabPlacement) {
		   case LEFT :
		       g.fillRect(x + 1, y + 1, w - 1, h - 3);
		       //g.fillRect(x, y, w, h);
		       break;
		   case RIGHT :
		       g.fillRect(x, y + 1, w - 2, h - 3);
		       break;
		   case BOTTOM :
		       g.fillRect(x + 1, y, w - 3, h - 1);
		       break;
		   case TOP :
		   default :
		       g.fillRect(x + 1, y + 1, w - 3, h - 1);
	       }
	   }

	   protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
	       //g.setColor(lightHighlight);  
	       g.setColor(isSelected ? getBackgroundAt(tabIndex).brighter() : getBackgroundAt(tabIndex).darker());
	       switch (tabPlacement) {
		   case LEFT :
		       // bottom-left highlight
		       g.drawLine(x + 1, y + h - 2, x + 1, y + h - 2);
		       // left highlight
		       g.drawLine(x, y + 2, x, y + h - 3);
		       // top-left highlight
		       g.drawLine(x + 1, y + 1, x + 1, y + 1);
		       // top highlight
		       g.drawLine(x + 2, y, x + w - 1, y);
		       // bottom shadow
		       g.setColor(g.getColor().darker());
		       g.drawLine(x + 2, y + h - 2, x + w - 1, y + h - 2);
		       // bottom dark shadow
		       g.setColor(g.getColor().darker());
		       g.drawLine(x + 2, y + h - 1, x + w - 1, y + h - 1);
		       break;
		   case RIGHT :
		       g.drawLine(x, y, x + w - 3, y);
		       // top highlight
		       g.setColor(shadow);
		       g.drawLine(x, y + h - 2, x + w - 3, y + h - 2);
		       // bottom shadow
		       g.drawLine(x + w - 2, y + 2, x + w - 2, y + h - 3);
		       // right shadow
		       g.setColor(darkShadow);
		       g.drawLine(x + w - 2, y + 1, x + w - 2, y + 1);
		       // top-right dark shadow
		       g.drawLine(x + w - 2, y + h - 2, x + w - 2, y + h - 2);
		       // bottom-right dark shadow
		       g.drawLine(x + w - 1, y + 2, x + w - 1, y + h - 3);
		       // right dark shadow
		       g.drawLine(x, y + h - 1, x + w - 3, y + h - 1);
		       // bottom dark shadow
		       break;
		   case BOTTOM :
		       g.drawLine(x, y, x, y + h - 3);
		       // left highlight
		       g.drawLine(x + 1, y + h - 2, x + 1, y + h - 2);
		       // bottom-left highlight
		       g.setColor(shadow);
		       g.drawLine(x + 2, y + h - 2, x + w - 3, y + h - 2);
		       // bottom shadow
		       g.drawLine(x + w - 2, y, x + w - 2, y + h - 3);
		       // right shadow
		       g.setColor(darkShadow);
		       g.drawLine(x + 2, y + h - 1, x + w - 3, y + h - 1);
		       // bottom dark shadow
		       g.drawLine(x + w - 2, y + h - 2, x + w - 2, y + h - 2);
		       // bottom-right dark shadow
		       g.drawLine(x + w - 1, y, x + w - 1, y + h - 3);
		       // right dark shadow
		       break;
		   case TOP :
		   default :
		       g.drawLine(x, y + 2, x, y + h - 1);
		       // left highlight
		       g.drawLine(x + 1, y + 1, x + 1, y + 1);
		       // top-left highlight
		       g.drawLine(x + 2, y, x + w - 3, y);
		       // top highlight              
		       g.setColor(shadow);
		       g.drawLine(x + w - 2, y + 2, x + w - 2, y + h - 1);
		       // right shadow
		       g.setColor(darkShadow);
		       g.drawLine(x + w - 1, y + 2, x + w - 1, y + h - 1);
		       // right dark-shadow
		       g.drawLine(x + w - 2, y + 1, x + w - 2, y + 1);
		       // top-right shadow
	       }
	   }

       }
	);
	setAlignmentY(LEFT_ALIGNMENT);
	setFocusable(false);
	setOpaque(false);
	setFont(new Font("Lucida Sans", Font.PLAIN, 10));
    }

}
