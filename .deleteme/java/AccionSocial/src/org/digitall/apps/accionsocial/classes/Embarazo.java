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
 * Embarazo.java
 *
 * */
package org.digitall.apps.accionsocial.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class Embarazo {

    private int idEmbarazo = -1;
    private int idPersona = -1;
    private String apellido = "";
    private String nombres = "";
    private String nroDocumento = "0";
    private String barrio = "";
    private String fechaNacimiento = "";
    private String fpp = "";
    private String fum = "";
    private double importe = 0;
    private int sector = 0;
    private int orden = 0;
    private int centroSalud = 0;
    private int ordenSector = 0;
    private String estado = "";
    private boolean vigente = false;
    private String fechaBaja = "";
    private String motivoBaja = "";
    private String fechaAlta = "";

    public Embarazo() {
        super();
    }
    
    public int saveData() {
        int result = -1;
        String params = "";
        if (idEmbarazo == -1) {
            params = "" + idEmbarazo + "," + idPersona + ",'" + barrio + "','" + fpp + "','" + fum + "','" + fechaAlta + "'";
            result = LibSQL.getInt("accionsocial.addembarazo", params);
        } else {
            result = LibSQL.getInt("accionsocial.setembarazo", params);
        }
        return result;
    }
    
    public void retrieveData() {
        try {
            ResultSet rs = LibSQL.exFunction("accionsocial.getembarazo", ""+ idEmbarazo);
            if (rs.next()) {
                idPersona = rs.getInt("idpersona");
                apellido = rs.getString("apellido");
                nombres = rs.getString("nombres");
                nroDocumento = rs.getString("nrodocumento");
                barrio = rs.getString("barrio");
                fechaNacimiento = rs.getString("fechanacimiento");
                fpp = rs.getString("fpp");
                fum = rs.getString("fum");
                importe = rs.getDouble("importe");
                sector = rs.getInt("sector");
                orden = rs.getInt("orden");
                centroSalud = rs.getInt("centrosalud");
                ordenSector = rs.getInt("ordensector");
                estado = rs.getString("estado");
                vigente = rs.getBoolean("vigente");
                fechaBaja = rs.getString("fechabaja");
                motivoBaja = rs.getString("motivobaja");
                fechaAlta = rs.getString("fechaalta");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setIdEmbarazo(int idEmbarazo) {
        this.idEmbarazo = idEmbarazo;
    }

    public int getIdEmbarazo() {
        return idEmbarazo;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellido() {
        return apellido;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setFpp(String fpp) {
        this.fpp = fpp;
    }

    public String getFpp() {
        return fpp;
    }

    public void setFum(String fum) {
        this.fum = fum;
    }

    public String getFum() {
        return fum;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double getImporte() {
        return importe;
    }

    public void setSector(int sector) {
        this.sector = sector;
    }

    public int getSector() {
        return sector;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getOrden() {
        return orden;
    }

    public void setCentroSalud(int centroSalud) {
        this.centroSalud = centroSalud;
    }

    public int getCentroSalud() {
        return centroSalud;
    }

    public void setOrdenSector(int ordenSector) {
        this.ordenSector = ordenSector;
    }

    public int getOrdenSector() {
        return ordenSector;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }

    public boolean isVigente() {
        return vigente;
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

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
}
