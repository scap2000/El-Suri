/**
 LIMITACIÃN DE RESPONSABILIDAD - COPYRIGHT - [EspaÃ±ol]
 ================================================================================
 KamarDesktop - Entorno JAVA de Trabajo y Desarrollo para Taller de Servicios Kamar
 ================================================================================

 Copyright (C) 2011 Lic. Santiago Cassina (scap2000@yahoo.com)
 http://www.scassi.com.ar

 La copia y distribuciÃ³n de este archivo, con o sin modificaciones,
 estÃ¡n permitidas por cualquier medio sin cargo mientras se preserven
 el Aviso de Copyright y este mismo aviso.

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los tÃ©rminos de la Licencia PÃºblica General GNU publicada
 por la FundaciÃ³n para el Software Libre, ya sea la versiÃ³n 3
 de la Licencia, o (a su elecciÃ³n) cualquier versiÃ³n posterior.

 Este programa se distribuye con la esperanza de que sea Ãºtil, pero
 SIN GARANTÃA ALGUNA; ni siquiera la garantÃ­a implÃ­cita
 MERCANTIL o de APTITUD PARA UN PROPÃSITO DETERMINADO.
 Consulte los detalles de la Licencia PÃºblica General GNU para obtener
 una informaciÃ³n mÃ¡s detallada.

 DeberÃ­a haber recibido una copia de la Licencia PÃºblica General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
KamarDesktop - JAVA Management & Development Environment for Kamar Mechanical Services
 =====================================================================================

 Copyright (C) 2011 by Lic. Santiago Cassina (scap2000@yahoo.com)
 http://www.scassi.com.ar

 Copying and distribution of this file, with or without modification,
 are permitted in any medium without royalty provided the copyright
 notice and this notice are preserved.

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
 * OrdenServicio.java
 *
 * */
package org.digitall.projects.kamar.taller.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.sql.LibSQL;

public class OrdenServicio {

    private int idOrdenServicio = -1;
    private String numero = "";
    private int idCliente = -1;
    private long idClienteExterno = -1;
    private String telefono = "";
    private int idEstado = -1;
    private String ingresoVehiculo = "";
    private String egresoVehiculo = "";
    private String fechaInicio = "";
    private String fechaFin = "";
    private String observaciones = "";
    private int tiempoEstimado = 0;
    private double montoEstimado = 0.0;
    private String numeroFactura = "";
    private double montoFacturado = 0.0;
    private Vehiculo vehiculo;

    /** Estados posibles de la Orden de Servicio [idEstado]
     * -1 Sin asignar
     * 0 Alta
     * 1 Iniciada/En proceso
     * 2 Finalizada/A facturar
     * 3 Facturada/Cerrada
     * 4 Cancelada/Anulada
     * */
 
    public OrdenServicio() {
	this(-1);
    }
    
    public OrdenServicio(int _idOrdenServicio) {
	super();
	idOrdenServicio = _idOrdenServicio;
	vehiculo = new Vehiculo();
    }

    public int getIdOrdenServicio() {
	return idOrdenServicio;
    }

    public void setIdEstado(int idEstado) {
	this.idEstado = idEstado;
    }

    public int getIdEstado() {
	return idEstado;
    }

    public void setIngresoVehiculo(String turno) {
	this.ingresoVehiculo = turno;
    }

    public String getIngresoVehiculo() {
	return ingresoVehiculo;
    }

    public void setFechaInicio(String fechaInicio) {
	this.fechaInicio = fechaInicio;
    }

    public String getFechaInicio() {
	return fechaInicio;
    }

    public void setFechaFin(String fechaFin) {
	this.fechaFin = fechaFin;
    }

    public String getFechaFin() {
	return fechaFin;
    }

    public void setNumeroFactura(String numeroFactura) {
	this.numeroFactura = numeroFactura;
    }

    public String getNumeroFactura() {
	return numeroFactura;
    }

    public void setMontoFacturado(double montoFacturado) {
	this.montoFacturado = montoFacturado;
    }

    public double getMontoFacturado() {
	return montoFacturado;
    }

    public void setTiempoEstimado(int tiempoEstimado) {
	this.tiempoEstimado = tiempoEstimado;
    }

    public int getTiempoEstimado() {
	return tiempoEstimado;
    }

    public void setMontoEstimado(double montoEstimado) {
	this.montoEstimado = montoEstimado;
    }

    public double getMontoEstimado() {
	return montoEstimado;
    }

    public void setIdCliente(int idCliente) {
	this.idCliente = idCliente;
    }

    public int getIdCliente() {
	return idCliente;
    }

    public void setEgresoVehiculo(String egresoVehiculo) {
	this.egresoVehiculo = egresoVehiculo;
    }

    public String getEgresoVehiculo() {
	return egresoVehiculo;
    }
    
    public int saveData(){
	int _returns = -1;
	if (idClienteExterno == -1) {
	    Advisor.messageBox("Debe seleccionar un cliente", "Cliente no válido");
	} else if (numero.length() == 0) {
	    Advisor.messageBox("Debe ingresar un Número de Orden de Servicio", "Datos no válidos");
	} else {
	    _returns = LibSQL.getInt("taller.setOrdenServicio", idOrdenServicio + ",'" + numero + "','" + idClienteExterno + "','" + telefono + "','" + observaciones + "'");
	    if (idOrdenServicio == -1){
	        idOrdenServicio = _returns;
	    }
	}
	return _returns;
    }

    public void setIdClienteExterno(long idClienteExterno) {
	this.idClienteExterno = idClienteExterno;
    }

    public long getIdClienteExterno() {
	return idClienteExterno;
    }

    public void setTelefono(String telefono) {
	this.telefono = telefono;
    }

    public String getTelefono() {
	return telefono;
    }

    public void retrieveVehiculo() {
	vehiculo =new Vehiculo();
	vehiculo.loadData(LibSQL.getInt("taller.getvehiculoporordenservicio", idOrdenServicio));
    }

    public Vehiculo getVehiculo() {
	return vehiculo;
    }

    public void setNumero(String numero) {
	this.numero = numero;
    }

    public String getNumero() {
	return numero;
    }

    public void retrieveData() {
	if (idOrdenServicio != -1) {
	    ResultSet result = LibSQL.exFunction("taller.getOrdenServicio", idOrdenServicio);
	    try {
	        if (result.next()) {
	            numero = result.getString("numero");
	            idCliente = result.getInt("idcliente");
	            idClienteExterno = result.getLong("idexterno");
	            telefono = result.getString("telefono");
	            idEstado = result.getInt("idestado");
	            ingresoVehiculo = result.getString("ingresovehiculo");
	            egresoVehiculo = result.getString("egresovehiculo");
	            fechaInicio = result.getString("fechainicio");
	            fechaFin = result.getString("fechafin");
	            tiempoEstimado = result.getInt("tiempoestimado");
	            montoEstimado = result.getDouble("montoestimado");
	            numeroFactura = result.getString("numerofactura");
	            montoFacturado = result.getDouble("montofacturado");
	            observaciones = result.getString("observaciones");
		    retrieveVehiculo();
	        }
	    } catch (SQLException e) {
	        // TODO
	        e.printStackTrace();
	    } catch (NullPointerException e) {
	        // TODO
	    }
	}
    }

    public void setObservaciones(String observaciones) {
	this.observaciones = observaciones;
    }

    public String getObservaciones() {
	return observaciones;
    }
}
