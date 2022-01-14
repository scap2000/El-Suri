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
 * Prestacion.java
 *
 * */
package org.digitall.apps.accionsocial.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class Prestacion {
    
    private int idPrestacion = -1;
    private int idPlanSocial = 0;
    private String fechaAlta = "";
    private String fechaBaja = "";
    private String motivoBaja = "";
    private String recurso = "";
    private double cantMinima = 0.0;
    private double cantMaxima = 0.0;
    private String observaciones = "";
    private String estado = "";
    private double cantDisponible = 0.0;
        
    public Prestacion() {
        super();
    }
    
    public int saveData() {
        int result = -1;
        String params = "";
        if (idPrestacion == -1)  {
            params =   ""  + idPlanSocial +",'"+ fechaAlta + "','" + fechaBaja + "','" + motivoBaja + "','"
                           + recurso + "'," + cantMinima + "," + cantMaxima + ",'" + observaciones + "',"
                           + cantDisponible + ",''";
            
            result = LibSQL.getInt("accionsocial.addPrestacion", params);
            idPrestacion = result; 
        } else {
            params = "" + idPrestacion + "," + idPlanSocial + ",'" + fechaAlta + "','" + fechaBaja + "','" + motivoBaja + "','"
                        + recurso + "'," + cantMinima + "," + cantMaxima + ",'" + observaciones + "',"
                        + cantDisponible + ",'" + estado + "'";
            result = LibSQL.getInt("accionsocial.setPrestacion", params);
        }
         return result;
    }

    public boolean deletePrestacion() {
        return LibSQL.getBoolean("accionsocial.delPrestacion", ""+ idPrestacion);
    }

    public void retrieveData() {
        try {
            ResultSet rs = LibSQL.exFunction("accionsocial.getPrestacion", ""+ idPrestacion);
            if (rs.next()) {
                idPrestacion = rs.getInt("idprestacion");
                idPlanSocial = rs.getInt("idprestacion");
                fechaAlta = rs.getString("fechaalta");
                fechaBaja = rs.getString("fechabaja");
                motivoBaja = rs.getString("motivobaja");
                recurso = rs.getString("recurso");
                cantMinima = rs.getDouble("cantminima");
                cantMaxima = rs.getDouble("cantmaxima");
                observaciones = rs.getString("observaciones");
                estado = rs.getString("estado");
                cantDisponible = rs.getDouble("cantdisponible");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean actualizarStock(double _cantidad){
        boolean result = false;
        String params = "" + idPrestacion + "," + _cantidad;
        result = LibSQL.getBoolean("accionsocial.setStockPrestacion", params);
        return result;
    }

    public void setIdPrestacion(int idPrestacion) {
        this.idPrestacion = idPrestacion;
    }

    public int getIdPrestacion() {
        return idPrestacion;
    }

    public void setIdPlanSocial(int idPlanSocial) {
        this.idPlanSocial = idPlanSocial;
    }

    public int getIdPlanSocial() {
        return idPlanSocial;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaBaja(String fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getFechaBaja() {
        return fechaBaja;
    }

    public void setMotivoBaja(String motivoBaja) {
        this.motivoBaja = motivoBaja;
    }

    public String getMotivoBaja() {
        return motivoBaja;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setCantMinima(double cantMinima) {
        this.cantMinima = cantMinima;
    }

    public double getCantMinima() {
        return cantMinima;
    }

    public void setCantMaxima(double cantMaxima) {
        this.cantMaxima = cantMaxima;
    }

    public double getCantMaxima() {
        return cantMaxima;
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

    public void setCantDisponible(double cantDisponible) {
        this.cantDisponible = cantDisponible;
    }

    public double getCantDisponible() {
        return cantDisponible;
    }
}
