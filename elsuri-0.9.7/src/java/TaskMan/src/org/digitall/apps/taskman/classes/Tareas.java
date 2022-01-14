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
 * Tareas.java
 *
 * */
package org.digitall.apps.taskman.classes;

import java.sql.ResultSet;

import org.digitall.apps.taskman.TaskManEnvironment;
import org.digitall.lib.environment.Environment;

public class Tareas {
    
    private int idtarea = -1;
    private String nombreTarea = "";
    private String usuario = "";
    private String fechaCreacion = "";
    private String fechaInicio = "";
    private String fechaFin = "";
    private double tiempoEstimado = 0;
    private double tiempoEjecutado = 0;
    private double porcentaje = 0;
    private String observaciones = "";
    private String estado = "";
    
    public Tareas() {
        super();
    }
    
    public int saveData() {
        int resultado = -1;
	String params = "" + idtarea + ",'" + nombreTarea + "', '" + Environment.sessionUser + "'," + tiempoEstimado + "," + tiempoEjecutado + "," + porcentaje + ",'" + observaciones + "',''";
	resultado = TaskManEnvironment.libSQLMini.getInt("taskman.addtarea",params);
        return resultado;
    }
        
    public void retrieveData() {
        String params = ""+idtarea;
        ResultSet data = TaskManEnvironment.libSQLMini.exFunction("taskman.gettarea",params);
        try {
            if (data.next()) {
                idtarea  = data.getInt("idtarea");
                nombreTarea = data.getString("nombretarea");
                usuario = data.getString("usuario");
                fechaCreacion = data.getString("fechacreacion");
                fechaInicio = data.getString("fechainicio");
                fechaFin = data.getString("fechafin");
                tiempoEstimado = data.getDouble("tiempoestimado");
                tiempoEjecutado = data.getDouble("tiempoejecutado");
                porcentaje = data.getDouble("porcentaje");
                observaciones = data.getString("observaciones");
                estado  = data.getString("estado");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setIdtarea(int idtarea) {
        this.idtarea = idtarea;
    }

    public int getIdtarea() {
        return idtarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setTiempoEstimado(double tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    public double getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setTiempoEjecutado(double tiempoEjecutado) {
        this.tiempoEjecutado = tiempoEjecutado;
    }

    public double getTiempoEjecutado() {
        return tiempoEjecutado;
    }
}
