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
 * Entrega.java
 *
 * */
package org.digitall.apps.accionsocial.classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.digitall.lib.sql.LibSQL;

public class Entrega {
    
    private int idEntrega = -1;
    private int idBeneficiario = 0;
    private int idTutor = 0;
    private int idPrestacion = 0;
    private String fechaHoraEntrega = "";
    private String despachante = "";
    private String fechaCarga = "";
    private String usuario = "";
    private double cantidad = 0.0;
    private double montoEstimado = 0.0;
    private String observaciones = "";
    private boolean adomicilio = false;
    private double x = 0;
    private double y = 0;
    private String estado = "";
        
    public Entrega() {
        super();
    }
    
    public int saveData() {
        int result = -1;
        String params = "";
        if (idEntrega == -1)  {
            params = "" + idBeneficiario + "," + idTutor + "," + idPrestacion + ",'" + fechaHoraEntrega + "','" + despachante + "','"
                        + fechaCarga + "','" + usuario + "'," + cantidad + "," + montoEstimado + ",'" + observaciones + "',"
                        + adomicilio + "," + x + "," + y; 
            result = LibSQL.getInt("accionsocial.addEntrega", params);
            idEntrega = result; 
        } else {
            params = "" + idEntrega + "," + idBeneficiario + "," + idTutor + "," + idPrestacion + ",'" + fechaHoraEntrega + "','" 
                        + despachante + "','" + fechaCarga + "','" + usuario + "'," + cantidad + "," + montoEstimado + ",'" 
                        + observaciones + "'," + adomicilio  + "," + x + "," + y + "'" + estado + "'";
            result = LibSQL.getInt("accionsocial.setPersona", params);
        }
         return result;
    }

    public boolean deleteCar() {
        return LibSQL.getBoolean("accionsocial.delEntrega", ""+ idEntrega);
    }


    public void retrieveData() {
        
        try {
            ResultSet rs = LibSQL.exFunction("accionsocial.getEntrega", ""+ idEntrega);
            if (rs.next()) {
                idEntrega = rs.getInt("identrega");
                idBeneficiario = rs.getInt("idbeneficiario");
                idTutor = rs.getInt("idtutor");
                idPrestacion = rs.getInt("idprestacion");
                fechaHoraEntrega = rs.getString("fechahoraentrega");
                despachante = rs.getString("despachante");
                fechaCarga = rs.getString("fechacarga");
                usuario = rs.getString("usuario");
                cantidad = rs.getDouble("cantidad");
                montoEstimado = rs.getDouble("montoestimado");
                observaciones = rs.getString("observaciones");
                adomicilio = rs.getBoolean("adomicilio");
                estado = rs.getString("estado");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }

    public int getIdEntrega() {
        return idEntrega;
    }

    public void setIdBeneficiario(int idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
    }

    public int getIdBeneficiario() {
        return idBeneficiario;
    }

    public void setIdTutor(int idTutor) {
        this.idTutor = idTutor;
    }

    public int getIdTutor() {
        return idTutor;
    }

    public void setIdPrestacion(int idPrestacion) {
        this.idPrestacion = idPrestacion;
    }

    public int getIdPrestacion() {
        return idPrestacion;
    }

    public void setFechaHoraEntrega(String fechaHoraEntrega) {
        this.fechaHoraEntrega = fechaHoraEntrega;
    }

    public String getFechaHoraEntrega() {
        return fechaHoraEntrega;
    }

    public void setDespachante(String despachante) {
        this.despachante = despachante;
    }

    public String getDespachante() {
        return despachante;
    }

    public void setFechaCarga(String fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public String getFechaCarga() {
        return fechaCarga;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setMontoEstimado(double montoEstimado) {
        this.montoEstimado = montoEstimado;
    }

    public double getMontoEstimado() {
        return montoEstimado;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setAdomicilio(boolean adomicilio) {
        this.adomicilio = adomicilio;
    }

    public boolean isAdomicilio() {
        return adomicilio;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }
}
