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
 * RemoteFile.java
 *
 * */
package org.digitall.common.filemanager;

import java.io.Serializable;

import java.util.Date;

public class RemoteFile implements Serializable {

    private int idTransfer = -1;
    private int idFile = -1;
    private int idDirectory = -1;
    private String name = "";
    private String destinationDir = "";
    private String createdBy = "";
    private Date dateTimeCreated = null;
    private boolean deleteable = false;
    private long length = -1;
    private String mimeType = "";
    private Date dateTimeLastModified = null;
    private String comment = "";
    private String application = "";

    public RemoteFile() {
    }

    public RemoteFile(String _name) {
	name = _name;
    }

    public void setIdFile(int _idFile) {
	idFile = _idFile;
    }

    public int getIdFile() {
	return idFile;
    }

    public void setIdDirectory(int _idDirectory) {
	idDirectory = _idDirectory;
    }

    public int getIdDirectory() {
	return idDirectory;
    }

    public void setName(String _name) {
	name = _name;
    }

    public String getName() {
	return name;
    }

    public void setDestinationDir(String _destinationDir) {
	destinationDir = _destinationDir;
    }

    public String getDestinationDir() {
	return destinationDir;
    }

    public void setCreatedBy(String _createdBy) {
	createdBy = _createdBy;
    }

    public String getCreatedBy() {
	return createdBy;
    }

    public void setDateTimeCreated(Date _dateTimeCreated) {
	dateTimeCreated = _dateTimeCreated;
    }

    public Date getDateTimeCreated() {
	return dateTimeCreated;
    }

    public void setDeleteable(boolean _deleteable) {
	deleteable = _deleteable;
    }

    public boolean isDeleteable() {
	return deleteable;
    }

    public void setLength(long _length) {
	length = _length;
    }

    public long length() {
	return length;
    }

    public void setMimeType(String _mimeType) {
	mimeType = _mimeType;
    }

    public String getMimeType() {
	return mimeType;
    }

    public void setDateTimeLastModified(Date _dateTimeLastModified) {
	dateTimeLastModified = _dateTimeLastModified;
    }

    public Date getDateTimeLastModified() {
	return dateTimeLastModified;
    }

    public void setComment(String _comment) {
	comment = _comment;
    }

    public String getComment() {
	return comment;
    }

    public void setApplication(String _application) {
	application = _application;
    }

    public String getApplication() {
	return application;
    }

    public void setIDTransfer(int _idTransfer) {
	idTransfer = _idTransfer;
    }

    public int getIDTransfer() {
	return idTransfer;
    }

}
