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
 * Beneficiario.java
 *
 * */
package org.digitall.apps.accionsocial.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class Beneficiario {
    
    private int idBeneficiario  = -1;
    private int idPersona = 0;
    private int idPlanSocial = 0;
    private String fechaAlta = "";
    private String fechaBaja = "";
    private String motivoBaja = "";
    private String fechaNacimiento = "";
    private int edad = 0;
    private int idTutor = 0;
    private String domicilio = "";
    private String centroSalud = "";
    private String estado = "";
    private int idSector = 0;
    private String tutor = "";
    private String estadoNutricional = "";
    private int orden = 0;
    private String numeroDocumento = "0";
    private String beneficiario = "";
    private String numeroDocumentoTutor = "0";
    private int firma = 0;
    private int idPrestacion = 0;

    public Beneficiario() {
        super();
    }
    
    public int saveData() {
        int result = -1;
        String params = "";
        
        if (this.fechaNacimiento == null) {
            this.fechaNacimiento = "";
        }
        
        if (idBeneficiario == -1)  {
            params = "" + idPersona + "," + idPlanSocial + ",'" + fechaAlta + "','" + fechaBaja + "','" + motivoBaja + "','"
                        + fechaNacimiento + "'," + edad + "," + idTutor + ",'" + domicilio + "','" + centroSalud + "',"
                        + idSector + ",'" + tutor + "','" + estadoNutricional + "'," + orden + ",'" + numeroDocumento + "','"
                        + beneficiario + "','" + numeroDocumentoTutor + "'," + firma + "," + idPrestacion;
            result = LibSQL.getInt("accionsocial.addBeneficiario", params);
            idBeneficiario = result; 
        } else {
            
            result = LibSQL.getInt("accionsocial.setBeneficiario", params);
        }
         return result;
    }
 
    public boolean deleteCar() {
        return LibSQL.getBoolean("accionsocial.delBeneficiario", ""+ idBeneficiario);
    }

    public void retrieveData(Persona _persona) {
        String params = "" + _persona.getIdPersona();
        idBeneficiario = LibSQL.getInt("accionsocial.getidbeneficiariobypersona",""+ params);
        if (idBeneficiario > 0) {
            retrieveData();
        }
    }

    public void retrieveData() {
        
        try {
            ResultSet rs = LibSQL.exFunction("accionsocial.getBeneficiario", ""+ idBeneficiario);
            if (rs.next()) {
                idBeneficiario  = rs.getInt("idbeneficiario");
                idPersona = rs.getInt("idpersona");
                idPlanSocial = rs.getInt("idplansocial");
                fechaAlta = rs.getString("fechaalta");
                fechaBaja = rs.getString("fechabaja");
                motivoBaja = rs.getString("motivobaja");
                fechaNacimiento = rs.getString("fechanacimiento");
                edad = rs.getInt("edad");
                idTutor = rs.getInt("idtutor");
                domicilio = rs.getString("domicilio");
                centroSalud = rs.getString("centrosalud");
                estado = rs.getString("estado");
                idSector = rs.getInt("idsector");
                tutor = rs.getString("tutor");
                estadoNutricional = rs.getString("estadonutricional");
                orden = rs.getInt("orden");
                numeroDocumento = rs.getString("numerodocumento");
                beneficiario = rs.getString("beneficiario");
                numeroDocumentoTutor = rs.getString("numerodocumentotutor");
                firma = rs.getInt("firma");
                idPrestacion = rs.getInt("idprestacion");       
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getCantidadPrestaciones(Prestacion _prestacion, int _idmes, int _anio) {
        String params = "" + idPersona + "," + _prestacion.getIdPrestacion() + ","+ _idmes + "," + _anio;
        return LibSQL.getDouble("accionsocial.getCantidadPrestacionSeleccionadaPorBeneficiario", params);
    }

    public void setIdBeneficiario(int idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
    }

    public int getIdBeneficiario() {
        return idBeneficiario;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdPersona() {
        return idPersona;
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

    public String getfechaAlta() {
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

    public void setFechaNacimiento(String fechaNacimiento) {
        if (fechaNacimiento == "null") {
            this.fechaNacimiento = "";
        } else {
            this.fechaNacimiento = fechaNacimiento;
        }
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }

    public void setIdTutor(int idTutor) {
        this.idTutor = idTutor;
    }

    public int getIdTutor() {
        return idTutor;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setCentroSalud(String centroSalud) {
        this.centroSalud = centroSalud;
    }

    public String getCentroSalud() {
        return centroSalud;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setIdSector(int idSector) {
        this.idSector = idSector;
    }

    public int getIdSector() {
        return idSector;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getTutor() {
        return tutor;
    }

    public void setEstadoNutricional(String estadoNutricional) {
        this.estadoNutricional = estadoNutricional;
    }

    public String getEstadoNutricional() {
        return estadoNutricional;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getOrden() {
        return orden;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setNumeroDocumentoTutor(String numeroDocumentoTutor) {
        this.numeroDocumentoTutor = numeroDocumentoTutor;
    }

    public String getNumeroDocumentoTutor() {
        return numeroDocumentoTutor;
    }

    public void setFirma(int firma) {
        this.firma = firma;
    }

    public int getFirma() {
        return firma;
    }

    public void setIdPrestacion(int idPrestacion) {
        this.idPrestacion = idPrestacion;
    }

    public int getIdPrestacion() {
        return idPrestacion;
    }
    
    public void cargarse(Persona _persona) {
        idBeneficiario = LibSQL.getInt("accionsocial.getidbeneficiariobypersona", _persona.getIdPersona());
        retrieveData();
    }
    
}
