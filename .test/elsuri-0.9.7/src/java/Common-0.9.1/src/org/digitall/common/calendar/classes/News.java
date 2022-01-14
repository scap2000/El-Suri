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
 * News.java
 *
 * */
package org.digitall.common.calendar.classes;

import org.digitall.lib.sql.LibSQL;

public class News {

    private int idnew = -1;
    private int idsenderuser = -1;
    private int idrecipientuser = -1;
    private int idpriority = -1;
    private int idstatus = -1;
    private String text = "";
    private boolean statusread = false;
    private boolean statusanswer = false;
    private String date = "";
    private String subject = "";
    private String estado = "";


    public News() {

    }

    public void setIdnew(int _idnew) {
	idnew = _idnew;
    }

    public int getIdnew() {
	return idnew;
    }

    public void setIdsenderuser(int _idsenderuser) {
	idsenderuser = _idsenderuser;
    }

    public int getIdsenderuser() {
	return idsenderuser;
    }

    public void setIdrecipientuser(int _idrecipientuser) {
	idrecipientuser = _idrecipientuser;
    }

    public int getIdrecipientuser() {
	return idrecipientuser;
    }

    public void setIdpriority(int _idpriority) {
	idpriority = _idpriority;
    }

    public int getIdpriority() {
	return idpriority;
    }

    public void setIdstatus(int _idstatus) {
	idstatus = _idstatus;
    }

    public int getIdstatus() {
	return idstatus;
    }

    public void setText(String _text) {
	text = _text;
    }

    public String getText() {
	return text;
    }

    public void setStatusread(boolean _statusread) {
	statusread = _statusread;
    }

    public boolean isStatusread() {
	return statusread;
    }

    public void setStatusanswer(boolean _statusanswer) {
	statusanswer = _statusanswer;
    }

    public boolean isStatusanswer() {
	return statusanswer;
    }

    public void setDate(String _date) {
	date = _date;
    }

    public String getDate() {
	return date;
    }

    public void setSubject(String _subject) {
	subject = _subject;
    }

    public String getSubject() {
	return subject;
    }

    public void setEstado(String _estado) {
	estado = _estado;
    }

    public String getEstado() {
	return estado;
    }
    
    public int saveData(){
	int result = -1;
	String params = ""+ idrecipientuser +","+ idpriority +","+ 
			idstatus +",'"+ text + "','"+ subject + "'";
	if (idnew == -1)  {
	    result = LibSQL.getInt("calendar.addNews", params);
	    idnew = result;
	} else {
	    params = idnew + params;
	    result = LibSQL.getInt("calendar.setNews", params); //falta crearla
	}
	return result;
    }

}
