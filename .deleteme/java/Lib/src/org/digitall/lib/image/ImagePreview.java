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
 * ImagePreview.java
 *
 * */
package org.digitall.lib.image;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

public class ImagePreview extends JComponent implements PropertyChangeListener {

    ImageIcon thumbnail = null;
    File file = null;

    public ImagePreview(JFileChooser fc) {
	setPreferredSize(new Dimension(100, 50));
	fc.addPropertyChangeListener(this);
    }

    public void loadImage() {
	if (file == null) {
	    thumbnail = null;
	    return;
	}
	//Don't use createImageIcon (which is a wrapper for getResource)
	//because the image we're trying to load is probably not one
	//of this program's own resources.
	ImageIcon tmpIcon = new ImageIcon(file.getPath());
	if (tmpIcon != null) {
	    if (tmpIcon.getIconWidth() > 90) {
		thumbnail = new ImageIcon(tmpIcon.getImage().getScaledInstance(90, -1, Image.SCALE_DEFAULT));
	    } else {
		//no need to miniaturize
		thumbnail = tmpIcon;
	    }
	}
    }

    public void propertyChange(PropertyChangeEvent e) {
	boolean update = false;
	String prop = e.getPropertyName();
	//If the directory changed, don't show an image.
	if (JFileChooser.DIRECTORY_CHANGED_PROPERTY.equals(prop)) {
	    file = null;
	    update = true;
	    //If a file became selected, find out which one.
	} else if (JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(prop)) {
	    file = (File)e.getNewValue();
	    update = true;
	}
	//Update the preview accordingly.
	if (update) {
	    thumbnail = null;
	    if (isShowing()) {
		loadImage();
		repaint();
	    }
	}
    }

    protected void paintComponent(Graphics g) {
	if (thumbnail == null) {
	    loadImage();
	}
	if (thumbnail != null) {
	    int x = getWidth() / 2 - thumbnail.getIconWidth() / 2;
	    int y = getHeight() / 2 - thumbnail.getIconHeight() / 2;
	    if (y < 0) {
		y = 0;
	    }
	    if (x < 5) {
		x = 5;
	    }
	    thumbnail.paintIcon(this, g, x, y);
	}
    }

}
