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
 * BoletaAlquiler.java
 *
 * */
package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class BoletaAlquiler {

    private int idboletaalquiler = -1;
    private int idaccountingentry = -1;
    private String fechapago = "";
    private String fechaimpresion = "";
    private String empresa = "";
    private String reponsable = "";
    private String contratocomodato = "";
    private String duracion = "";
    private String observacion = "";
    private String concepto = "";
    private String vencimiento = "";
    private double importe = 0;
    private double recargo = 0;
    private double deducciones = 0;
    private double total = 0;
    private int idusuario = -1;
    private int nroimpresiones = -1;
    private String estado = "";
    private String localidad = "";
    private String domicilio = "";
    private double cuotamensual = 0;
    private String usuario = "";
    private String barCode = "";
    private String cuit = "";
    private String dni = "";
    
    public BoletaAlquiler() {
    }

    public void setIdboletaAlquiler(int _idboletaalquiler) {
        idboletaalquiler = _idboletaalquiler;
    }

    public int getIdboletaALquiler() {
        return idboletaalquiler;
    }

    public void setIdaccountingentry(int _idaccountingentry) {
        idaccountingentry = _idaccountingentry;
    }

    public int getIdaccountingentry() {
        return idaccountingentry;
    }

    public void setFechapago(String _fechapago) {
        fechapago = _fechapago;
    }

    public String getFechapago() {
        return fechapago;
    }

    public void setFechaimpresion(String _fechaimpresion) {
        fechaimpresion = _fechaimpresion;
    }

    public String getFechaimpresion() {
        return fechaimpresion;
    }

    public void setEmpresa(String _empresa) {
        empresa = _empresa;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setResponsable(String _responsable) {
        reponsable = _responsable;
    }

    public String getResponsable() {
        return reponsable;
    }

    public void setContratoComodato(String _contratocomodato) {
        contratocomodato = _contratocomodato;
    }

    public String getContratoComodato() {
        return contratocomodato;
    }

    public void setDuracion(String _duracion) {
        duracion = _duracion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setObservacion(String _observacion) {
        observacion = _observacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setConcepto(String _concepto) {
        concepto = _concepto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setVencimiento(String _vencimiento) {
        vencimiento = _vencimiento;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public void setImporte(double _importe) {
        importe = _importe;
    }

    public double getImporte() {
        return importe;
    }

    public void setRecargo(double _recargo) {
        recargo = _recargo;
    }

    public double getRecargo() {
        return recargo;
    }

    public void setDeducciones(double _deducciones) {
        deducciones = _deducciones;
    }

    public double getDeducciones() {
        return deducciones;
    }

    public void setTotal(double _total) {
        total = _total;
    }

    public double getTotal() {
        return total;
    }

    public void setIdusuario(int _idusuario) {
        idusuario = _idusuario;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setNroimpresiones(int _nroimpresiones) {
        nroimpresiones = _nroimpresiones;
    }

    public int getNroimpresiones() {
        return nroimpresiones;
    }

    public void setEstado(String _estado) {
        estado = _estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setLocalidad(String _localidad) {
        localidad = _localidad;
    }

    public String getLocalidad() {
        return localidad;
    }
    
    public void setDomicilio(String _domicilio) {
        domicilio = _domicilio;
    }

    public String getDomicilio() {
        return domicilio;
    }
    
    public void setCuotaMensual(double _cuotaMensual) {
        cuotamensual = _cuotaMensual;
    }

    public double getCuotaMensual() {
        return cuotamensual;
    }

    public void setUsuario(String _usuario) {
        usuario = _usuario;
    }

    public String getUsuario() {
        return usuario;
    }
    
    public void setBarCode(String _barCode) {
        barCode = _barCode;
    }

    public String getBarCode() {
        return barCode;
    }
    
    public void setCuit(String _cuit) {
        cuit = _cuit;
    }

    public String getCuit() {
        return cuit;
    }

    public void setDni(String _dni) {
        dni = _dni;
    }

    public String getDni() {
        return dni;
    }
    
    public int saveData(){ 
        String params = "";
        int result = -1;
        params = idaccountingentry +",'"+ fechapago + "','"+ fechaimpresion +"','" + 
                 empresa + "','" + reponsable + "','" + contratocomodato + "','" + duracion + 
                 "','"+ observacion +"','"+ concepto +"','"+ vencimiento +"',"+ importe +","+ 
                 recargo +","+ deducciones +","+ total +","+ idusuario +","+ nroimpresiones +",'"+ 
                 estado +"','"+ localidad +"','"+ domicilio +"',"+ cuotamensual +",'"+ usuario +"','"+ cuit +"','"+ dni +"'";
        if (idboletaalquiler == -1)  {
            result = LibSQL.getInt("taxes.addBoletaAlquiler",params);
            idboletaalquiler = result; 
        } else  {
            params = idboletaalquiler +","+ params;
            result = LibSQL.getInt("taxes.setBoletaAlquiler",params); //falta crearla (verificar si es necesario)
        }
        return result;
    }
    
    public void retrieveData() {
        ResultSet result = LibSQL.exFunction("taxes.getBoletaAlquiler", idboletaalquiler);
        try {
            if (result.next()) {
                idboletaalquiler = result.getInt("idboletaalquiler");
                idaccountingentry = result.getInt("idaccountingentry");
                fechapago = result.getString("fechapago");
                fechaimpresion = result.getString("fechaimpresion");
                empresa = result.getString("empresa");
                reponsable = result.getString("responsable");
                contratocomodato = result.getString("contratocomodato");
                duracion = result.getString("duracion");
                observacion = result.getString("observacion");
                concepto = result.getString("concepto");
                vencimiento = result.getString("vencimiento");
                importe = result.getDouble("importe");
                recargo = result.getDouble("recargo");
                deducciones = result.getDouble("deducciones");
                total = result.getDouble("total");
                idusuario = result.getInt("idusuario");
                nroimpresiones = result.getInt("nroimpresiones");
                estado = result.getString("estado");
                localidad = result.getString("localidad");
                domicilio = result.getString("domicilio");
                cuotamensual = result.getDouble("cuotamensual");
                usuario = result.getString("usuario");
                barCode = result.getString("barcode");
                cuit = result.getString("cuit");
                dni = result.getString("dni");
            } 
        } catch (SQLException e) {
            // TODO
            System.out.println("error");
        } catch (NullPointerException e) {
            // TODO
        }
    }

    
}
