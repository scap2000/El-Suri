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
 * ImageLabel.java
 *
 * */
package org.digitall.lib.image;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import org.digitall.lib.components.basic.BasicLabel;

public class ImageLabel extends BasicLabel {

    private BufferedImage image;
    private double width = 320.0;
    private double height = 320.0;
    private int previousWidth = 0;
    private int previousHeight = 0;
    
    public ImageLabel() {
	this(320.0, 320.0);
    }
    
    public ImageLabel(double _width, double _height) {
	width = _width;
	height = _height;
	this.addComponentListener(new ComponentAdapter() {
		public void componentResized(ComponentEvent e) {
		    if (e.getID() == ComponentEvent.COMPONENT_RESIZED &&  (previousWidth != getWidth() && previousHeight != getHeight())) {
			previousWidth = getWidth();
			previousHeight = getHeight();
		        setImage(image);
		    }
		}
	});
    }

    public void setImage(BufferedImage _image) {
	//La imagen SIEMPRE se escalará a lo ancho
	//entre el parámetro seteado y su propia anchura
	//y se guarda en memoria con ese tamaño
	if (_image != null) {
	    image = LibIMG.scale((double)width/(double)_image.getWidth(), _image);
	    updateImage();
	} else {
	    image = null;
	    setIcon(null);
	}
    }

    public BufferedImage getImage() {
	return image;
    }
    
    private void updateImage() {
	//La imagen SIEMPRE se escalará al menor de los tamaños entre width y heigth
	//para asegurarse de mostrar la imagen completa, incluso dentro de un scrollpane
	//además de auto-ajustar la imagen en caso de un componente que cambia de tamaño
	if (getWidth() > 0) {
	    setIcon(new ImageIcon(LibIMG.scale(Math.min((double)getWidth() / (double)image.getWidth(),(double)getHeight() / (double)image.getHeight()), image)));
	} else {
	    setIcon(new ImageIcon(LibIMG.scale(1, image)));
	}
    }

}
