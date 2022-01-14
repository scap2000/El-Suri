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
 * RemoteFileList.java
 *
 * */
package org.digitall.apps.filemanager;

import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.JList;

import org.digitall.common.filemanager.RemoteFile;

public class RemoteFileList extends JList {

    private int currentDirectory = -1;
    private Vector listData = new Vector();

    public RemoteFileList() {
	super();
    }

    public RemoteFile[] getSelectedFiles() {
	int[] selectedIndices = getSelectedIndices();
	RemoteFile[] selectedFiles = new RemoteFile[selectedIndices.length];
	for (int i = 0; i < selectedIndices.length; i++) {
	    selectedFiles[i] = (RemoteFile)listData.elementAt(selectedIndices[i]);
	}
	return selectedFiles;
    }

    public String getToolTipText(MouseEvent evt) {
	// Get item index
	int index = locationToIndex(evt.getPoint());
	// Get item
	//Object item = getModel().getElementAt(index);
	// Return the tool tip text
	try {
	    RemoteFile f = (RemoteFile)listData.elementAt(index);
	    long length = f.length();
	    String deleteable = f.isDeleteable() ? "deleteable, " : "not deleteable, ";
	    String application = f.getApplication() + ", ";
	    String createdBy = "created by: " + f.getCreatedBy();
	    String lastModified = "last modified: " + f.getDateTimeLastModified();
	    return String.valueOf(length) + " bytes - " + deleteable + application + createdBy + lastModified;
	} catch (ArrayIndexOutOfBoundsException e) {
	    return null;
	}
    }

    public void setListData(Vector _list) {
	super.setListData(_list);
    }

    public void setFileList(Vector _list) {
	listData = _list;
    }

    public void setCurrentDirectory(int _currentDirectory) {
	currentDirectory = _currentDirectory;
	LoadRemoteFileList loadRemoteFileList = new LoadRemoteFileList(this);
	loadRemoteFileList.setCurrentDirectory(currentDirectory);
    }

    public int getCurrentDirectory() {
	return currentDirectory;
    }

}
