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
 * DetalleBoletaContribucion.java
 *
 * */
package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class DetalleBoletaContribucion {
    
    private int idDetalleBoletaContribucion = -1;
    private int idBoletaContribucion = -1;
    private int anio = -1;
    private int numero = -1;
    private String fechaVto = "";
    private int dias = -1;
    private double monto = 0.0;
    private double interes = 0.0;
    private double deducciones = 0.0;
    private double montoTotal = 0.0;
    private int idtipoImpuesto = -1;
    private int idAlicuotaContribucion = -1;
    private String estado = "";
    private String fechaImpresion = "";
    private String fechaPago = "";
    private String concepto = "";
    
    private int idContribucion = -1;
    private String nombreContribucion = "";
    private String nombreAlicuota = "";
    private int calculo = -1;
    private double multiplicador = 0.0;
    private double valorModulo = 0.0;
    private double montoBase = 0.0;
    private double porcentaje = 0.0;
    private double montoFijo = 0.0;
    
    public DetalleBoletaContribucion() {
        super();
    }
    
    public DetalleBoletaContribucion(int _idDetalleBoletaContribucion) {
        idDetalleBoletaContribucion = _idDetalleBoletaContribucion;
    }

    public void setIdDetalleBoletaContribucion(int idDetalleBoletaContribucion) {
        this.idDetalleBoletaContribucion = idDetalleBoletaContribucion;
    }

    public int getIdDetalleBoletaContribucion() {
        return idDetalleBoletaContribucion;
    }

    public void setIdBoletaContribucion(int idBoletaContribucion) {
        this.idBoletaContribucion = idBoletaContribucion;
    }

    public int getIdBoletaContribucion() {
        return idBoletaContribucion;
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

    public void setFechaVto(String fechaVto) {
        this.fechaVto = fechaVto;
    }

    public String getFechaVto() {
        return fechaVto;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public int getDias() {
        return dias;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getMonto() {
        return monto;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public double getInteres() {
        return interes;
    }

    public void setDeducciones(double deducciones) {
        this.deducciones = deducciones;
    }

    public double getDeducciones() {
        return deducciones;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setIdtipoImpuesto(int idtipoImpuesto) {
        this.idtipoImpuesto = idtipoImpuesto;
    }

    public int getIdtipoImpuesto() {
        return idtipoImpuesto;
    }

    public void setIdAlicuotaContribucion(int idAlicuotaContribucion) {
        this.idAlicuotaContribucion = idAlicuotaContribucion;
    }

    public int getIdAlicuotaContribucion() {
        return idAlicuotaContribucion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setFechaImpresion(String fechaImpresion) {
        this.fechaImpresion = fechaImpresion;
    }

    public String getFechaImpresion() {
        return fechaImpresion;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getConcepto() {
        return concepto;
    }
    
    public void setIdContribucion(int idContribucion) {
        this.idContribucion = idContribucion;
    }

    public int getIdContribucion() {
        return idContribucion;
    }

    public void setNombreContribucion(String nombreContribucion) {
        this.nombreContribucion = nombreContribucion;
    }

    public String getNombreContribucion() {
        return nombreContribucion;
    }

    public void setNombreAlicuota(String nombreAlicuota) {
        this.nombreAlicuota = nombreAlicuota;
    }

    public String getNombreAlicuota() {
        return nombreAlicuota;
    }

    public void setCalculo(int calculo) {
        this.calculo = calculo;
    }

    public int getCalculo() {
        return calculo;
    }

    public void setMultiplicador(double multiplicador) {
        this.multiplicador = multiplicador;
    }

    public double getMultiplicador() {
        return multiplicador;
    }

    public void setValorModulo(double valorModulo) {
        this.valorModulo = valorModulo;
    }

    public double getValorModulo() {
        return valorModulo;
    }

    public void setMontoBase(double montoBase) {
        this.montoBase = montoBase;
    }

    public double getMontoBase() {
        return montoBase;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setMontoFijo(double montoFijo) {
        this.montoFijo = montoFijo;
    }

    public double getMontoFijo() {
        return montoFijo;
    }
    
    public void retrieveData() {
        ResultSet result = LibSQL.exFunction("taxes.getDetalleBoletaContribucion",""+ idDetalleBoletaContribucion);
        try  {
            if (result.next())  {
                setIdDetalleBoletaContribucion(result.getInt("idDetalleBoletaContribucion"));
                setIdBoletaContribucion(result.getInt("idBoletaContribucion"));
                setAnio(result.getInt("anio"));
                setNumero(result.getInt("numero"));
                setFechaVto(result.getString("fechaVto"));
                setDias(result.getInt("dias"));
                setMonto(result.getDouble("monto"));
                setInteres(result.getDouble("interes"));
                setDeducciones(result.getDouble("deducciones"));
                setMontoTotal(result.getDouble("montototal"));
                setIdtipoImpuesto(result.getInt("idtipoImpuesto"));
                setIdAlicuotaContribucion(result.getInt("idAlicuotaContribucion"));
                setEstado(result.getString("estado"));
                setFechaImpresion(result.getString("fechaimpresion"));
                setFechaPago(result.getString("fechapago"));
                setConcepto(result.getString("concepto"));
                setIdContribucion(result.getInt("idcontribucion"));
                setNombreContribucion(result.getString("nombrecontribucion")); 
                setNombreAlicuota(result.getString("nombrealicuota")); 
                setCalculo(result.getInt("calculo"));
                setMultiplicador(result.getDouble("multiplicador"));
                setValorModulo(result.getDouble("valormodulo"));
                setMontoBase(result.getDouble("montobase"));
                setPorcentaje(result.getDouble("porcentaje"));
                setMontoFijo(result.getDouble("montofijo"));
            }
            
        } catch (SQLException e) {
            // TODO
            System.out.println("error");
            e.printStackTrace();
        }
        
    }
    
    public int saveData() {
        String params = "";
        int result = -1;
            params = idBoletaContribucion + "," + anio + "," + numero + "," + monto + "," + interes + "," + deducciones + "," + 
                     montoTotal + "," + idtipoImpuesto + "," + idAlicuotaContribucion + ",'"+ estado + "','"+ concepto + "',"+
                     idContribucion +",'"+ nombreContribucion +"','"+ nombreAlicuota +"',"+ calculo +","+ multiplicador +","+
                     valorModulo +","+ montoBase +","+ porcentaje +","+ montoFijo;
        if (idDetalleBoletaContribucion == -1) {
            result = LibSQL.getInt("taxes.addDetalleBoletaContribucion",params);
            idDetalleBoletaContribucion = result;
        } else {
            params = idDetalleBoletaContribucion +","+ params;
            result = LibSQL.getInt("taxes.setDetalleBoletaContribucion",params);
        }
        return result;
    }
    
    public boolean delete() {
        return LibSQL.getBoolean("taxes.deleteItemDetalleBoletaContribucion",idDetalleBoletaContribucion);
    }
    
    public boolean itemCargado(String _item) {
        return LibSQL.getBoolean("taxes.esItemCargado",idBoletaContribucion +",'"+ _item +"'") && idBoletaContribucion == -1;
    }

}
