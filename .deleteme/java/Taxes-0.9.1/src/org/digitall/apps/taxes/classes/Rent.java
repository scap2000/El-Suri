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
 * Rent.java
 *
 * */
package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class Rent {

    private int idalquiler = -1;
    private int idempresa = -1;
    private String empresa = "";
    private String responsable = "";
    private String predio = "";
    private String contratocomodato = "";
    private String vigencia = "null";
    private String duracion = "";
    private double importemensual = 0;
    private String observacion = "";
    private String estado = "";
    private String dni = "";
    private String cuit = "";
    private String fechaBaja = "";

    public Rent() {
    }

    public void setIdalquiler(int _idalquiler) {
        idalquiler = _idalquiler;
    }

    public int getIdalquiler() {
        return idalquiler;
    }

    public void setIdempresa(int _idempresa) {
        idempresa = _idempresa;
    }

    public int getIdempresa() {
        return idempresa;
    }

    public void setEmpresa(String _empresa) {
        empresa = _empresa;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setResponsable(String _responsable) {
        responsable = _responsable;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setPredio(String predio) {
        this.predio = predio;
    }

    public String getPredio() {
        return predio;
    }

    public void setContratocomodato(String contratocomodato) {
        this.contratocomodato = contratocomodato;
    }

    public String getContratocomodato() {
        return contratocomodato;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setImportemensual(double importemensual) {
        this.importemensual = importemensual;
    }

    public double getImportemensual() {
        return importemensual;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
    
    public void setDni(String _dni) {
        dni = _dni;
    }

    public String getDni() {
        return dni;
    }

    public void setCuit(String _cuit) {
        cuit = _cuit;
    }

    public String getCuit() {
        return cuit;
    }
    
    public void setFechaBaja(String _fechaBaja) {
        fechaBaja = _fechaBaja;
    }

    public String getFechaBaja() {
        return fechaBaja;
    }
    
    public int saveData(){
        int result = -1;
        String params = "";
        if (idempresa == -1)  {
            params = "'"+ empresa +"','"+ responsable +"','"+ predio +"','"+ contratocomodato +"','"+ vigencia +"','"+ 
                          duracion +"',"+ importemensual +",'" + observacion +"','"+ dni +"','"+ cuit +"'";
            result = LibSQL.getInt("taxes.addRent", params);
            idempresa = result;
        } else {
            params =  idempresa +",'"+ empresa +"','"+ responsable +"','"+ predio +"','"+ contratocomodato +"','"+ vigencia +"','"+ 
                          duracion +"',"+ importemensual +",'" + observacion  +"','"+ dni +"','"+ cuit +"','"+ fechaBaja +"'";
            result = LibSQL.getInt("taxes.setRent", params);
        }
         return result;
    }
    
    public void retrieveData() {
        ResultSet result = LibSQL.exFunction("taxes.getRent", idempresa);
        try {
            if (result.next()) {
                idempresa = result.getInt("idempresa");
                empresa = result.getString("empresa");
                responsable = result.getString("responsable");
                predio = result.getString("predio");
                contratocomodato = result.getString("contratocomodato");
                vigencia = result.getString("vigencia");
                duracion = result.getString("duracion");
                importemensual = result.getDouble("importemensual");
                observacion = result.getString("observacion");
                estado = result.getString("estado"); 
                idalquiler = result.getInt("idalquiler");
                dni = result.getString("dni"); 
                cuit = result.getString("cuit");
                fechaBaja = result.getString("hasta");
            }
            
        } catch (SQLException e) {
            // TODO
            System.out.println("error");
        } catch (NullPointerException e) {
            // TODO
        }
    }
    
    public int setIdboletaAlquilerOnCuotaAlquiler(int _anticipo, int _anio, int _idBoletaAlquileres){
        int result = LibSQL.getInt("taxes.setCuotasRentByIdboletaRent",idalquiler +","+ _anticipo +","+ _anio +","+ _idBoletaAlquileres);
        return result;
    }

    
}
