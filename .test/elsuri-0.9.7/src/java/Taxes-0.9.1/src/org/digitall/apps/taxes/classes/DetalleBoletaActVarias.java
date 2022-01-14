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
 * DetalleBoletaActVarias.java
 *
 * */
package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class DetalleBoletaActVarias {

    private int iddetalleboletaactvarias = -1;
    private int idboletaactvarias = -1;
    private int idactividad = -1;
    private String actividad = "";
    private String fechavto = "";
    private int dias = -1;
    private double monto = 0;
    private double alicuota = 0;
    private double deducciones = 0;
    private double montototal = 0;
    private String estado  = "";
    private String fechaimpresion  = "";
    private String fechapago  = "";
    private double montoxAlicuota = 0;
    private double montoBase = 0;
    private double valorModulo = 0;
    private int multiplicador = 0;

    public DetalleBoletaActVarias() {
    }

    public void setIddetalleboletaactvarias(int _iddetalleboletaactvarias) {
        iddetalleboletaactvarias = _iddetalleboletaactvarias;
    }

    public int getIddetalleboletaactvarias() {
        return iddetalleboletaactvarias;
    }

    public void setIdboletaactvarias(int _idboletaactvarias) {
        idboletaactvarias = _idboletaactvarias;
    }

    public int getIdboletaactvarias() {
        return idboletaactvarias;
    }

    public void setIdactividad(int _idactividad) {
        idactividad = _idactividad;
    }

    public int getIdactividad() {
        return idactividad;
    }

    public void setActividad(String _actividad) {
        actividad = _actividad;
    }

    public String getActividad() {
        return actividad;
    }

    public void setFechavto(String _fechavto) {
        fechavto = _fechavto;
    }

    public String getFechavto() {
        return fechavto;
    }

    public void setDias(int _dias) {
        dias = _dias;
    }

    public int getDias() {
        return dias;
    }

    public void setMonto(double _monto) {
        monto = _monto;
    }

    public double getMonto() {
        return monto;
    }

    public void setAlicuota(double _alicuota) {
        alicuota = _alicuota;
    }

    public double getAlicuota() {
        return alicuota;
    }

    public void setDeducciones(double _deducciones) {
        deducciones = _deducciones;
    }

    public double getDeducciones() {
        return deducciones;
    }

    public void setMontototal(double _montototal) {
        montototal = _montototal;
    }

    public double getMontototal() {
        return montototal;
    }

    public void setEstado(String _estado) {
        estado = _estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setFechaimpresion(String _fechaimpresion) {
        fechaimpresion = _fechaimpresion;
    }

    public String getFechaimpresion() {
        return fechaimpresion;
    }

    public void setFechapago(String _fechapago) {
        fechapago = _fechapago;
    }

    public String getFechapago() {
        return fechapago;
    }
    
    public int deleteItem(){
        return LibSQL.getInt("taxes.delItemBoletaActVarias", iddetalleboletaactvarias);
    }

    public void setMontoxAlicuota(double _montoxAlicuota) {
        montoxAlicuota = _montoxAlicuota;
    }

    public double getMontoxAlicuota() {
        return montoxAlicuota;
    }

    public void setMontoBase(double _montoBase) {
        montoBase = _montoBase;
    }

    public double getMontoBase() {
        return montoBase;
    }
    
    public void setValorModulo(double _valorModulo) {
        valorModulo = _valorModulo;
    }

    public double getValorModulo() {
        return valorModulo;
    }

    public void setMultiplicador(int _multiplicador) {
        multiplicador = _multiplicador;
    }

    public int getMultiplicador() {
        return multiplicador;
    }
    
    public int saveData(){ 
        String params = "";
        int result = -1;
        params = idboletaactvarias +","+ idactividad +",'"+ actividad +"',"+ dias +","+
                 monto +","+ alicuota +","+ deducciones +","+ montototal +","+
                 montoxAlicuota +","+ montoBase +","+ valorModulo +","+ multiplicador;
        if (iddetalleboletaactvarias == -1)  {
            result = LibSQL.getInt("taxes.addDetalleBoletaActVarias",params);
            idboletaactvarias = result; 
        } else {
        }
        return result;
    }
    
    public void retrieveData() {
        /*ResultSet result = LibSQL.exFunction("taxes.getBoletaActVarias", idboletaactvarias);
        try {
            if (result.next()) {
                idboletaactvarias = result.getInt("idboletaactvarias");
                idaccountingentry = result.getInt("idaccountingentry");
                fechapago = result.getString("fechapago");
                fechaimpresion = result.getString("fechaimpresion");
                contribuyente = result.getString("contribuyente");
                padron = result.getString("padron");
                dni = result.getString("dni");
                rubro = result.getString("rubro");
                razonsocial = result.getString("razonsocial");
                expte = result.getString("expte");
                nrocuenta = result.getInt("nrocuenta");
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
            } 
        } catch (SQLException e) {
            // TODO
            System.out.println("error");
        } catch (NullPointerException e) {
            // TODO
        }*/
    }

    
}
