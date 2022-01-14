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
 * TiemposPorTarea.java
 *
 * */
package org.digitall.apps.taskman.classes;

import java.sql.ResultSet;

import org.digitall.apps.taskman.TaskManEnvironment;
import org.digitall.lib.environment.Environment;

public class TiemposPorTarea {
    
    private int idTiempoPorTarea = -1;
    private int idTarea = -1;
    private String fechaHoraInicio = "";
    private String fechaHoraFin = "";
    private String usuario = "";
    private double porcentaje = 0;
    private String estado = "";
    private Tareas tarea = new Tareas();
    
    public TiemposPorTarea() {
        super();
    }
    
    public int saveData() {
        int resultado = -1;
	String params = "" + idTiempoPorTarea + "," + idTarea + ",'" + Environment.sessionUser + "'," + porcentaje + ",''";
	resultado = TaskManEnvironment.libSQLMini.getInt("taskman.addtiempoportarea",params);
        idTiempoPorTarea = resultado;
        return resultado;
    }
        
    public void retrieveData() {
        String params = ""+idTiempoPorTarea;
        ResultSet data = TaskManEnvironment.libSQLMini.exFunction("taskman.gettiempoportarea",params);
        try {
            if (data.next()) {
                idTarea  = data.getInt("idtarea");
                fechaHoraInicio = data.getString("fechahorainicio");
                fechaHoraFin = data.getString("fechahorafin");
                usuario = data.getString("usuario");
                porcentaje = data.getDouble("porcentaje");
                estado  = data.getString("estado");
                //tarea.setIdtarea(idTarea);
                //tarea.retrieveData();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void setidTiempoPorTarea(int idTiempoPorTarea) {
        this.idTiempoPorTarea = idTiempoPorTarea;
    }

    public int getidTiempoPorTarea() {
        return idTiempoPorTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public int getIdTarea() {
        return idTarea;
    }

    public void setFechaHoraInicio(String fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public String getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraFin(String fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public String getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setTarea(Tareas tarea) {
        this.tarea = tarea;
    }

    public Tareas getTarea() {
        return tarea;
    }
}
