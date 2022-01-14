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
 * DNDSupport.java
 *
 * */
package org.digitall.apps.logistics.interfaces;

import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;

import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

import org.digitall.lib.components.basic.BasicLabel;

public class DNDSupport extends TransferHandler implements Transferable {

    private static final DataFlavor flavors[] = { DataFlavor.imageFlavor };
    private Image image;

    public int getSourceActions(JComponent c) {
	return TransferHandler.COPY;
    }

    public boolean canImport(JComponent comp, DataFlavor[] flavor) {
	if (!(comp instanceof BasicLabel) && !(comp instanceof AbstractButton)) {
	    return false;
	}
	for (int i = 0, n = flavor.length; i < n; i++) {
	    for (int j = 0, m = flavors.length; j < m; j++) {
		if (flavor[i].equals(flavors[j])) {
		    return true;
		}
	    }
	}
	return false;
    }

    public Transferable createTransferable(JComponent comp) {
	// Clear
	image = null;
	if (comp instanceof BasicLabel) {
	    BasicLabel label = (BasicLabel)comp;
	    Icon icon = label.getIcon();
	    if (icon instanceof ImageIcon) {
		image = ((ImageIcon)icon).getImage();
		return this;
	    }
	} else if (comp instanceof AbstractButton) {
	    AbstractButton button = (AbstractButton)comp;
	    Icon icon = button.getIcon();
	    if (icon instanceof ImageIcon) {
		image = ((ImageIcon)icon).getImage();
		return this;
	    }
	}
	return null;
    }

    public boolean importData(JComponent comp, Transferable t) {
	if (comp instanceof BasicLabel) {
	    BasicLabel label = (BasicLabel)comp;
	    if (t.isDataFlavorSupported(flavors[0])) {
		try {
		    image = (Image)t.getTransferData(flavors[0]);
		    ImageIcon icon = new ImageIcon(image);
		    label.setIcon(icon);
		    return true;
		} catch (UnsupportedFlavorException ignored) {

		} catch (IOException ignored) {

		}
	    }
	} else if (comp instanceof AbstractButton) {
	    AbstractButton button = (AbstractButton)comp;
	    if (t.isDataFlavorSupported(flavors[0])) {
		try {
		    image = (Image)t.getTransferData(flavors[0]);
		    ImageIcon icon = new ImageIcon(image);
		    button.setIcon(icon);
		    return true;
		} catch (UnsupportedFlavorException ignored) {

		} catch (IOException ignored) {

		}
	    }
	}
	return false;
    }
    // Transferable

    public Object getTransferData(DataFlavor flavor) {
	if (isDataFlavorSupported(flavor)) {
	    return image;
	}
	return null;
    }

    public DataFlavor[] getTransferDataFlavors() {
	return flavors;
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
	return flavors[0].equals(flavor);
    }

}
