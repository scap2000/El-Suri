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
 * DesktopButton.java
 *
 * */
package org.digitall.lib.components.basic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import org.digitall.lib.data.Format;
import org.digitall.lib.icons.IconTypes;

public class DesktopButton extends BasicButton {

    private String title;
    private ImageIcon animationIcon;
    private Icon originalIcon;
    private Timer animation;
    private int period = 25;
    private boolean shrinking = true;

    public DesktopButton(String _title) {
	this(_title, null);
    }

    public DesktopButton(String _title, ImageIcon _icon) {
	super(Format.toHtmlCentered(_title), _icon);
	title = _title;
	animation = new Timer(period, new ActionListener() {

			   public void actionPerformed(ActionEvent e) {
			       animationIcon = (ImageIcon)getIcon();
			       if (shrinking) {
				   setIcon(IconTypes.getScaledIcon(animationIcon, (int)(animationIcon.getIconWidth() * 1.1), (int)(animationIcon.getIconHeight() * 0.9)));
				   shrinking = animationIcon.getIconHeight() * 1.5 > originalIcon.getIconHeight();
			       } else {
				   setIcon(IconTypes.getScaledIcon(animationIcon, (int)(animationIcon.getIconWidth() * 0.9), (int)(animationIcon.getIconHeight() * 1.1)));
				   shrinking = animationIcon.getIconHeight() > originalIcon.getIconHeight();
			       }
			   }

		       }
	    );
	addMouseListener(new MouseAdapter() {

		      public void mousePressed(MouseEvent e) {
			  setText(Format.toHtmlCenteredUnderline(title));
		      }

		      public void mouseReleased(MouseEvent e) {
			  setText(Format.toHtmlCentered(title));
		      }

		      public void mouseEntered(MouseEvent e) {
			  setText(Format.toHtmlCenteredUnderline(title));
		      }

		      public void mouseExited(MouseEvent e) {
			  setText(Format.toHtmlCentered(title));
		      }

		  }
	);
	
	
	addKeyListener(new KeyAdapter() {

		@Override
		public void keyReleased(KeyEvent keyEvent) {
		    if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
			doClick();
		    }
		}

	    }
	);

	
    }
    
    public void setTitle(String _title) {
	title = _title;
	setText(Format.toHtmlCentered(title));
    }

}
