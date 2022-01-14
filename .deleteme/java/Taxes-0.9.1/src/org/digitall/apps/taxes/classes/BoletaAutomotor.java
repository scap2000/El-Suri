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
 * BoletaAutomotor.java
 *
 * */
package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class BoletaAutomotor {

    private int idboletaautomotor = -1;
    private int idaccountingentry = -1;
    private String fechapago = "";
    private String fechaimpresion = "";
    private String titular = "";
    private String dominio = "";
    private int nrocuenta = -1;
    private String concepto = "";
    private String vencimiento = "";
    private double importe = 0;
    private double recargo = 0;
    private double deducciones = 0;
    private double dtoPagoAnual = 0;
    private double total = 0;
    private double porcentajeDtoPagoAnual = 0;    
    private int idusuario = -1;
    private int nroimpresiones = -1;
    private String estado = "";
    private String localidad = "";
    private String domicilio = "";
    private String tipo = "";
    private String marca = "";
    private String motor = "";
    private String categoria = "";
    private String modelo = "";
    private double cuota = 0;
    private double valorAnual = 0;
    private String usuario = "";
    private String barCode = "";
    private String informacion = "";
    private int idDominio = -1;
    private int idDescuento = -1;
    private String nombreDescuento = "";
    private int anioDesde = -1;
    private int anticipoDesde = -1;
    private int anioHasta = -1;
    private int anticipoHasta = -1;
    private int idAutomotor = -1;
    private int anio = -1;
    private String numero = "";
    private int cantAnticipos = -1;
    private String fechaProximoVto = "";
    private boolean pagoAnual = false;
    
    public BoletaAutomotor() {
    }

    public void setIdboletaautomotor(int _idboletaautomotor) {
        idboletaautomotor = _idboletaautomotor;
    }

    public int getIdboletaautomotor() {
        return idboletaautomotor;
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

    public void setTitular(String _titular) {
        titular = _titular;
    }

    public String getTitular() {
        return titular;
    }

    public void setDominio(String _dominio) {
        dominio = _dominio;
    }

    public String getSeccion() {
        return dominio;
    }

    public void setNrocuenta(int _nrocuenta) {
        nrocuenta = _nrocuenta;
    }

    public int getNrocuenta() {
        return nrocuenta;
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

    public void setCategoria(String _categoria) {
        categoria = _categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    
    public void setCuota(double _cuota) {
        cuota = _cuota;
    }

    public double getcuota() {
        return cuota;
    }

    public void setValorAnual(double _valoranual) {
        valorAnual = _valoranual;
    }

    public double getValorAnual() {
        return valorAnual;
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
   
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setMarca(String _marca) {
        marca = _marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMotor(String _motor) {
        motor = _motor;
    }

    public String getMotor() {
        return motor;
    }

    public void setInformacion(String _informacion) {
        informacion = _informacion;
    }

    public String getInformacion() {
        return informacion; 
    }
    
    public void setModelo(String _modelo) {
        modelo = _modelo;
    }

    public String getModelo() {
        return modelo;
    }
    
    public int saveData(){
        String params = "";
        int result = -1;

	params = idaccountingentry +",'"+ fechapago + "','"+ fechaimpresion +"','" + titular + "','" + 
		  dominio + "',"+ nrocuenta +",'"+ concepto +"','"+ vencimiento +"',"+ importe +","+ 
		  recargo +","+ deducciones +","+ total +","+ idusuario +","+ nroimpresiones +",'"+ 
		  estado +"','"+ localidad +"','"+ domicilio +"','"+ tipo +"','"+ marca +"','"+ motor +"','"+ 
		  categoria +"','"+ modelo +"',"+ cuota +","+ valorAnual +",'"+ usuario +"','"+ informacion +"',"+
		  anioDesde +","+ anticipoDesde +","+ anioHasta +","+ anticipoHasta +","+ idDescuento  +",'"+ 
		  nombreDescuento +"',"+ idAutomotor +",'"+ fechaProximoVto +"',"+ dtoPagoAnual +","+ porcentajeDtoPagoAnual +","+ pagoAnual;
        if (idboletaautomotor == -1)  {
            result = LibSQL.getInt("taxes.addBoletaAutomotores",params);
            idboletaautomotor = result;
        } else  {
            params = idboletaautomotor +","+ params;
            result = LibSQL.getInt("taxes.setBoletaAutomotores",params); //falta crearla (verificar si es necesario)
        }
        return result;
    }
    
    public void retrieveData() {
        ResultSet result = LibSQL.exFunction("taxes.getBoletaAutomotor", idboletaautomotor);
        try {
            if (result.next()) {
                idboletaautomotor = result.getInt("idboletaautomotor");
                idaccountingentry = result.getInt("idaccountingentry");
                fechapago = result.getString("fechapago");
                fechaimpresion = result.getString("fechaimpresion");
                titular = result.getString("titular");
                dominio = result.getString("dominio");
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
                tipo = result.getString("tipo");
                marca = result.getString("marca");
                motor = result.getString("motor");
                categoria = result.getString("categoria");
                modelo = result.getString("modelo");
                cuota = result.getDouble("cuota");
                valorAnual = result.getDouble("valoranual");
                usuario = result.getString("usuario");
		anio = result.getInt("anio");
                barCode = result.getString("barcode");
                informacion = result.getString("informacion");
		cantAnticipos = result.getInt("cantanticipos");
		numero = result.getString("numero");
		fechaProximoVto = result.getString("fechaproximovto");
		setDtoPagoAnual(result.getDouble("dtopagoanual"));
		setPorcentajeDtoPagoAnual(result.getDouble("porcentajepagoanual"));
		setPagoAnual(result.getBoolean("pagoanual"));
            } 
        } catch (SQLException e) {
            // TODO
            System.out.println("error");
        } catch (NullPointerException e) {
            // TODO
        }
    }

    public void setIdDescuento(int idDescuento) {
	this.idDescuento = idDescuento;
    }

    public int getIdDescuento() {
	return idDescuento;
    }

    public void setNombreDescuento(String nombreDescuento) {
	this.nombreDescuento = nombreDescuento;
    }

    public String getNombreDescuento() {
	return nombreDescuento;
    }

    public String getDominio() {
	return dominio;
    }

    public void setAnioDesde(int anioDesde) {
	this.anioDesde = anioDesde;
    }

    public int getAnioDesde() {
	return anioDesde;
    }

    public void setAnticipoDesde(int anticipoDesde) {
	this.anticipoDesde = anticipoDesde;
    }

    public int getAnticipoDesde() {
	return anticipoDesde;
    }

    public void setAnioHasta(int anioHasta) {
	this.anioHasta = anioHasta;
    }

    public int getAnioHasta() {
	return anioHasta;
    }

    public void setAnticipoHasta(int anticipoHasta) {
	this.anticipoHasta = anticipoHasta;
    }

    public int getAnticipoHasta() {
	return anticipoHasta;
    }

    public void setIdDominio(int idDominio) {
	this.idDominio = idDominio;
    }

    public int getIdDominio() {
	return idDominio;
    }

    public void setIdAutomotor(int idAutomotor) {
	this.idAutomotor = idAutomotor;
    }

    public int getIdAutomotor() {
	return idAutomotor;
    }

    public void setCantAnticipos(int cantAnticipos) {
	this.cantAnticipos = cantAnticipos;
    }

    public int getCantAnticipos() {
	return cantAnticipos;
    }

    public void setAnio(int anio) {
	this.anio = anio;
    }

    public int getAnio() {
	return anio;
    }

    public void setNumero(String numero) {
	this.numero = numero;
    }

    public String getNumero() {
	return numero;
    }

    public void setFechaProximoVto(String fechaProximoVto) {
	this.fechaProximoVto = fechaProximoVto;
    }

    public String getFechaProximoVto() {
	return fechaProximoVto;
    }

    public void setDtoPagoAnual(double dtoPagoAnual) {
	this.dtoPagoAnual = dtoPagoAnual;
    }

    public double getDtoPagoAnual() {
	return dtoPagoAnual;
    }

    public void setPorcentajeDtoPagoAnual(double porcentajeDtoPagoAnual) {
	this.porcentajeDtoPagoAnual = porcentajeDtoPagoAnual;
    }

    public double getPorcentajeDtoPagoAnual() {
	return porcentajeDtoPagoAnual;
    }

    public void setPagoAnual(boolean pagoAnual) {
	this.pagoAnual = pagoAnual;
    }

    public boolean isPagoAnual() {
	return pagoAnual;
    }
}
