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
 * ImageFileView.java
 *
 * */
package org.digitall.lib.image;

import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileView;

public class ImageFileView extends FileView {

    ImageIcon jpgIcon = AlbumUtils.createImageIcon("iconos/images/jpgIcon.gif");
    ImageIcon gifIcon = AlbumUtils.createImageIcon("iconos/images/gifIcon.gif");
    ImageIcon tiffIcon = AlbumUtils.createImageIcon("iconos/images/tiffIcon.gif");
    ImageIcon pngIcon = AlbumUtils.createImageIcon("iconos/images/pngIcon.png");

    public String getName(File f) {
	return null;
	//let the L&F FileView figure this out
    }

    public String getDescription(File f) {
	return null;
	//let the L&F FileView figure this out
    }

    public Boolean isTraversable(File f) {
	return null;
	//let the L&F FileView figure this out
    }

    public String getTypeDescription(File f) {
	String extension = AlbumUtils.getExtension(f);
	String type = null;
	if (extension != null) {
	    if (extension.equals(AlbumUtils.jpeg) || extension.equals(AlbumUtils.jpg)) {
		type = "JPEG Image";
	    } else if (extension.equals(AlbumUtils.gif)) {
		type = "GIF Image";
	    } else if (extension.equals(AlbumUtils.tiff) || extension.equals(AlbumUtils.tif)) {
		type = "TIFF Image";
	    } else if (extension.equals(AlbumUtils.png)) {
		type = "PNG Image";
	    }
	}
	return type;
    }

    public Icon getIcon(File f) {
	String extension = AlbumUtils.getExtension(f);
	Icon icon = null;
	if (extension != null) {
	    if (extension.equals(AlbumUtils.jpeg) || extension.equals(AlbumUtils.jpg)) {
		icon = jpgIcon;
	    } else if (extension.equals(AlbumUtils.gif)) {
		icon = gifIcon;
	    } else if (extension.equals(AlbumUtils.tiff) || extension.equals(AlbumUtils.tif)) {
		icon = tiffIcon;
	    } else if (extension.equals(AlbumUtils.png)) {
		icon = pngIcon;
	    }
	}
	return icon;
    }

}
