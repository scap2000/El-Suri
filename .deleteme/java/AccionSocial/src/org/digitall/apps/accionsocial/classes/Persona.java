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
 * Persona.java
 *
 * */
package org.digitall.apps.accionsocial.classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.digitall.lib.sql.LibSQL;

public class Persona {
    
    private int idPersona = -1;
    private String apellidos = "";
    private String nombres = "";
    private String tipoDocumento = "";
    private String nroDocumento = "";
    private String cuil = "";
    private String sexo = "";
    private String fechaNacimiento = "";
    private int idNacionalidad = 1;
    private int idBarrio = -1;
    private int idCalle = -1;
    private String numeracion = "";
    private String piso = "";
    private String departamento = "";
    private String sectorBloque = "";
    private String manzana = "";
    private String casa = "";
    private String medidor = "";
    private String domicilio = "";
    private String estado = "";
    private String codigoPostal = "";
    private boolean tienePlanSocial = false;
    private double x = 0;
    private double y = 0;
    private int idLocalidad = -1;
    private int idProvincia = 17;
    private boolean vive = true;
    
    
    //the_geom        geometry        
        
    public Persona() {
        super();
    }
    
    public void cargarse(int _idPersona) {
        idPersona = _idPersona;
        retrieveData();
    }
    
    public int saveData() {
        int result = -1;
        String params = "";
        if (idPersona == -1)  {
            params = "'"+ apellidos + "','" + nombres + "','" + tipoDocumento + "','" + nroDocumento + "','" 
                        + cuil + "','" + sexo + "','" + fechaNacimiento + "'," + idNacionalidad + ","
                        + idBarrio + "," + idCalle + ",'" + numeracion + "','" + piso + "','" + departamento + "','"
                        + sectorBloque + "','" + manzana + "','" + casa + "','" + medidor + "',"+ x + "," + y + ",'" +domicilio + "',"
                        + idProvincia + "," + idLocalidad + ","+ vive + ",'" + codigoPostal + "'";
            result = LibSQL.getInt("accionsocial.addPersona", params);
            idPersona = result; 
        } else {
            params =  ""+ idPersona + ",'" + apellidos + "','" + nombres + "','" + tipoDocumento + "','" + nroDocumento + "','" 
                        + cuil + "','" + sexo + "','" + fechaNacimiento + "'," + idNacionalidad + ","
                        + idBarrio + "," + idCalle + ",'" + numeracion + "','" + piso + "','" + departamento + "','"
                        + sectorBloque + "','" + manzana + "','" + casa + "','" + medidor + "',"+ x + "," + y + ",'" +domicilio + "',"
                        + idProvincia + "," + idLocalidad + ","+ vive +  ",'" + codigoPostal + "'";
            result = LibSQL.getInt("accionsocial.setPersona", params);
        }
         return result;
    }

    public boolean deleteCar() {
        return LibSQL.getBoolean("accionsocial.delPersona", ""+ idPersona);
    }


    public void retrieveData() {
        
        try {
            ResultSet rs = LibSQL.exFunction("accionsocial.getPersona", ""+ idPersona);
            if (rs.next()) {
                idPersona = rs.getInt("idpersona");
                apellidos = rs.getString("apellidos");
                nombres = rs.getString("nombres");
                tipoDocumento = rs.getString("tipodocumento");
                nroDocumento = rs.getString("nrodocumento");
                cuil = rs.getString("cuil");
                sexo = rs.getString("sexo");
                fechaNacimiento = rs.getString("fechanacimiento");
                idNacionalidad = rs.getInt("idnacionalidad");
                idBarrio = rs.getInt("idbarrio");
                idCalle = rs.getInt("idcalle");
                numeracion = rs.getString("numeracion");
                piso = rs.getString("piso");
                departamento = rs.getString("departamento");
                sectorBloque = rs.getString("sectorbloque");
                manzana = rs.getString("manzana");
                casa = rs.getString("casa");
                medidor = rs.getString("medidor");
                domicilio = rs.getString("domicilio");
                estado = rs.getString("estado");
                idProvincia = rs.getInt("idprovincia");
                idLocalidad = rs.getInt("idlocalidad");
                tienePlanSocial = rs.getBoolean("tieneplansocial");
                vive = rs.getBoolean("vive");
                codigoPostal = rs.getString("codigopostal");
                setX(rs.getDouble("x"));
                setY(rs.getDouble("y"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNombres() {
        return nombres;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getCuil() {
        return cuil;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setIdNacionalidad(int idNacionalidad) {
        this.idNacionalidad = idNacionalidad;
    }

    public int getIdNacionalidad() {
        return idNacionalidad;
    }

    public void setIdBarrio(int idBarrio) {
        this.idBarrio = idBarrio;
    }

    public int getIdBarrio() {
        return idBarrio;
    }

    public void setIdCalle(int idCalle) {
        this.idCalle = idCalle;
    }

    public int getIdCalle() {
        return idCalle;
    }

    public void setNumeracion(String numeracion) {
        this.numeracion = numeracion;
    }

    public String getNumeracion() {
        return numeracion;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getPiso() {
        return piso;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setSectorBloque(String sectorBloque) {
        this.sectorBloque = sectorBloque;
    }

    public String getSectorBloque() {
        return sectorBloque;
    }

    public void setManzana(String manzana) {
        this.manzana = manzana;
    }

    public String getManzana() {
        return manzana;
    }

    public void setCasa(String casa) {
        this.casa = casa;
    }

    public String getCasa() {
        return casa;
    }

    public void setMedidor(String medidor) {
        this.medidor = medidor;
    }

    public String getMedidor() {
        return medidor;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getDomicilio() {
        return domicilio;
    }
    
    public boolean tienePlanesSociales() {
        return tienePlanSocial;
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

    public void setTienePlanSocial(boolean tienePlanSocial) {
        this.tienePlanSocial = tienePlanSocial;
    }

    public boolean isTienePlanSocial() {
        return tienePlanSocial;
    }

    public void setIdLocalidad(int idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public int getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    public int getIdProvincia() {
        return idProvincia;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setVive(boolean vive) {
        this.vive = vive;
    }

    public boolean isVive() {
        return vive;
    }
}
