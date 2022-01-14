/**
 LIMITACIÃN DE RESPONSABILIDAD - COPYRIGHT - [EspaÃ±ol]
 ================================================================================
 KamarDesktop - Entorno JAVA de Trabajo y Desarrollo para Taller de Servicios Kamar
 ================================================================================

 Copyright (C) 2011 Lic. Santiago Cassina (scap2000@yahoo.com)
 http://www.scassi.com.ar

 La copia y distribuciÃ³n de este archivo, con o sin modificaciones,
 estÃ¡n permitidas por cualquier medio sin cargo mientras se preserven
 el Aviso de Copyright y este mismo aviso.

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los tÃ©rminos de la Licencia PÃºblica General GNU publicada
 por la FundaciÃ³n para el Software Libre, ya sea la versiÃ³n 3
 de la Licencia, o (a su elecciÃ³n) cualquier versiÃ³n posterior.

 Este programa se distribuye con la esperanza de que sea Ãºtil, pero
 SIN GARANTÃA ALGUNA; ni siquiera la garantÃ­a implÃ­cita
 MERCANTIL o de APTITUD PARA UN PROPÃSITO DETERMINADO.
 Consulte los detalles de la Licencia PÃºblica General GNU para obtener
 una informaciÃ³n mÃ¡s detallada.

 DeberÃ­a haber recibido una copia de la Licencia PÃºblica General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
KamarDesktop - JAVA Management & Development Environment for Kamar Mechanical Services
 =====================================================================================

 Copyright (C) 2011 by Lic. Santiago Cassina (scap2000@yahoo.com)
 http://www.scassi.com.ar

 Copying and distribution of this file, with or without modification,
 are permitted in any medium without royalty provided the copyright
 notice and this notice are preserved.

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
 * Tarea.java
 *
 * */
package org.digitall.projects.kamar.taller.classes;

import java.sql.ResultSet;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.sql.LibSQL;

public class Tarea {

    private int idTarea = -1;
    private String nombre;
    private String codigo = "";
    private int idTipoMecanica = -1;

    public Tarea() {
    }

    public Tarea(int _idTarea, String _nombre, String _codigo, int _idTipoMecanica) {
	idTarea = _idTarea;
	nombre = _nombre;
	codigo = _codigo;
	idTipoMecanica = _idTipoMecanica;
    }
    
    public Tarea(int _idTarea) {
	idTarea = _idTarea;
    }

    public void setIdTarea(int idTarea) {
	this.idTarea = idTarea;
    }

    public int getIdTarea() {
	return idTarea;
    }

    public void setNombre(String _nombre) {
	nombre = _nombre;
    }

    public String getNombre() {
	return nombre;
    }

    public void loadData(int _idTarea) {
	idTarea = _idTarea;
	ResultSet data = LibSQL.exFunction("taller.getTarea", idTarea);
	try {
	    if (data.next()) {
		nombre = data.getString("nombre");
		codigo = data.getString("codigo");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public int saveData() {
	if (!nombre.trim().equals("") && idTipoMecanica != -1) {
	    int result = LibSQL.getInt("taller.setTarea", idTarea + ",'" + nombre + "','" + codigo + "'," + idTipoMecanica);
	    if (idTarea == -1) {
		idTarea = result;
	    }
	    return result;
	} else if (nombre.trim().equals("")) {
	    Advisor.messageBox("Debe ingresar un nombre para la Tarea", "Nombre no válido");
	    return -1;
	} else if (idTipoMecanica == -1) {
	    Advisor.messageBox("Debe seleccionar un Tipo de Mecánica para la Tarea", "Tipo de mecánica no seleccionada");
	    return -1;
	} else {
	    Advisor.messageBox("Error no especificado", "Agregar/Modificar tarea");
	    return -1;
	}
    }

    public void setCodigo(String _codigo) {
	codigo = _codigo;
    }

    public String getCodigo() {
	return codigo;
    }

    public void setIdTipoMecanica(int idTipoMecanica) {
	this.idTipoMecanica = idTipoMecanica;
    }

    public int getIdTipoMecanica() {
	return idTipoMecanica;
    }
}
