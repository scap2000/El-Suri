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
 * PlanSocial.java
 *
 * */
package org.digitall.apps.accionsocial.classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.digitall.lib.sql.LibSQL;

public class PlanSocial {
    
    private int idPlanSocial = -1;
    private String nombre = "";
    private String fechaAlta = "";
    private String fechaBaja = "";
    private String motivoBaja = "";
    private String tipoPlanSocial = "";
    private String numeroInstrumento = "";
    private double costoEstimado = 0.0;
    private String observaciones = "";
    private String estado = "";
        
    public PlanSocial() {
        super();
    }
    
    public int saveData() {
        int result = -1;
        String params = "";
        if (idPlanSocial == -1)  {
            params = "'"  + nombre + "','" + fechaAlta + "','" + fechaBaja  + "','" + motivoBaja + "','"
                          + tipoPlanSocial + "','" + numeroInstrumento + "'," + costoEstimado + ",'"
                          + observaciones + "', ''";
            result = LibSQL.getInt("accionsocial.addPlanSocial", params);
            idPlanSocial = result; 
        } else {
            params = "'"  + idPlanSocial + "," + nombre + "','" + fechaAlta + "','" + fechaBaja  + "','" + motivoBaja + "','"
                          + tipoPlanSocial + "','" + numeroInstrumento + "'," + costoEstimado + ",'"
                          + observaciones + "','" + estado + "'";
            result = LibSQL.getInt("accionsocial.setPlanSocial", params);
        }
         return result;
    }

    public boolean deleteCar() {
        return LibSQL.getBoolean("accionsocial.delPlanSocial", ""+ idPlanSocial);
    }


    public void retrieveData() {
        
        try {
            ResultSet rs = LibSQL.exFunction("accionsocial.getPlanSocial", ""+ idPlanSocial);
            if (rs.next()) {
                idPlanSocial = rs.getInt("idplansocial");
                nombre = rs.getString("nombre");
                fechaAlta = rs.getString("fechaalta");
                fechaBaja = rs.getString("fechabaja");
                motivoBaja = rs.getString("motivobaja");
                tipoPlanSocial = rs.getString("tipoplansocial");
                numeroInstrumento = rs.getString("numeroinstrumento");
                costoEstimado = rs.getDouble("costoestimado");
                observaciones = rs.getString("observaciones");
                estado = rs.getString("estado");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setIdPlanSocial(int idPlanSocial) {
        this.idPlanSocial = idPlanSocial;
    }

    public int getIdPlanSocial() {
        return idPlanSocial;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
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

    public void setIdTipoPlanSocial(String tipoPlanSocial) {
        this.tipoPlanSocial = tipoPlanSocial;
    }

    public String getIdTipoPlanSocial() {
        return tipoPlanSocial;
    }

    public void setNumeroInstrumento(String numeroInstrumento) {
        this.numeroInstrumento = numeroInstrumento;
    }

    public String getNumeroInstrumento() {
        return numeroInstrumento;
    }

    public void setCostoEstimado(double costoEstimado) {
        this.costoEstimado = costoEstimado;
    }

    public double getCostoEstimado() {
        return costoEstimado;
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
}
