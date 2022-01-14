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
 * AnticipoDeHaberes.java
 *
 * */
package org.digitall.common.personalfiles.classes;

import java.sql.ResultSet;

import org.digitall.lib.sql.LibSQL;


public class AnticipoDeHaberes {
    
    private int idAnticipoDeHaberes = -1;
    private int anio = 2000;
    private int numero = 0;
    private String barcode = "";
    private String fechaEmision = "";
    private String fechaPago = "";
    private int idPersona = -1;
    private int idLegajo = -1;
    private int legajo = -1;
    private String apellido = "";
    private String nombres = "";
    private String fechaDescuento = "";
    private double monto = 0.0;
    private int idBookKeepingEntry = -1;
    private String usuario = "";
    private String estadoAnticipo = "";
    private String estado = "";
    private boolean anulado = false;
    
    public AnticipoDeHaberes() {
        
    }
    
    public AnticipoDeHaberes(int _idAnticipoDeHaberes) {
        idAnticipoDeHaberes = _idAnticipoDeHaberes;
    }

    public void setIdAnticipoDeHaberes(int idAnticipoDeHaberes) {
        this.idAnticipoDeHaberes = idAnticipoDeHaberes;
    }

    public int getIdAnticipoDeHaberes() {
        return idAnticipoDeHaberes;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getAnio() {
        return anio;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdLegajo(int idLegajo) {
        this.idLegajo = idLegajo;
    }

    public int getIdLegajo() {
        return idLegajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public int getLegajo() {
        return legajo;
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

    public void setFechaDescuento(String fechaDescuento) {
        this.fechaDescuento = fechaDescuento;
    }

    public String getFechaDescuento() {
        return fechaDescuento;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getMonto() {
        return monto;
    }

    public void setIdBookKeepingEntry(int idBookKeepingEntry) {
        this.idBookKeepingEntry = idBookKeepingEntry;
    }

    public int getIdBookKeepingEntry() {
        return idBookKeepingEntry;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setEstadoAnticipo(String estadoAnticipo) {
        this.estadoAnticipo = estadoAnticipo;
    }

    public String getEstadoAnticipo() {
        return estadoAnticipo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
    
    public int saveData() {             
        String params = idPersona + ","+ idLegajo + ","+ legajo +",'"+
                        apellido +"','"+ nombres +"','"+ fechaEmision +"','"+ fechaDescuento +"',"+ monto ;
        int result = -1;
        if (idAnticipoDeHaberes == -1){
            result = LibSQL.getInt("sueldos.addAnticipoDeHaberes",params);              
            idAnticipoDeHaberes = result;      
        } else {            
            params = idLegajo+","+ params;
            result = LibSQL.getInt("sueldos.setAnticipoDeHaberes",params);
        }               
        return result;
    }
    
    public void retrieveData() {
        String params = "" + idAnticipoDeHaberes;
        ResultSet data = LibSQL.exFunction("sueldos.getAnticipoDehaberes", params);
        try {
            if (data.next()) {
                setIdAnticipoDeHaberes(data.getInt("idanticipodehaberes"));
                setAnio(data.getInt("anio"));
                setNumero(data.getInt("numero"));
                setBarcode(data.getString("barcode"));
                setFechaEmision(data.getString("fechaemision"));
                setFechaPago(data.getString("fechapago"));
                setIdPersona(data.getInt("idpersona"));
                setIdLegajo(data.getInt("idlegajo"));
                setLegajo(data.getInt("legajo"));
                setApellido(data.getString("apellido"));
                setNombres(data.getString("nombres"));
                setFechaDescuento(data.getString("fechadescuento"));
                setMonto(data.getDouble("monto"));
                setIdBookKeepingEntry(data.getInt("idbookkeepingentry"));
                setUsuario(data.getString("usuario"));
                setEstadoAnticipo(data.getString("estadoanticipo"));
                setEstado(data.getString("estado"));
                setAnulado(data.getBoolean("anulado"));
            } else {
                idLegajo = -1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }

    public boolean isAnulado() {
        return anulado;
    }
}


