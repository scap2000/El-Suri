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
 * TipoPlanDePago.java
 *
 * */
package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.sql.LibSQL;

public class TipoPlanDePago {

    private int idTipoPlanDePago = -1;
    private String nombre = "";
    private String descripcion = "";
    private String fechaInicio = "";
    private String fechaFin = "";
    private int idTipoImpuesto = -1;
    private int idCuentaCaja = -1;
    private int idCuentaImpuesto = -1;
    private int idCuentaBonificacionMoratoria = -1;
    private int idCuentaInteresxMora = -1;
    private String ordenanza = "";
    private String estado = "";
    private int minCuota = 0;
    private int maxCuotas = 0;
    private int maxCuotasVencidas = -1;
    private double porcentDtoPagoContado = 0;
    private double porcentDtoCancelacion = 0;
    private double porcentInteresMora = 0;
    private String fechaInicioInscripcion = "";
    private String fechaFinInscripcion = "";
    private int idCuentaBonificacionContado = -1;
    private int idCuentaPlanDePago = -1;
    private int idBien = -1;
    private String rescision = "";
    private double porcentajeCondonacionIntereses = 0;
    private boolean obligatorio = false;
    private boolean activo = false;
    private double porcentajeInteresCuota = 0.0;
    
    /* 2009-09-24 (santiago) Mensaje para el codificador
     * TipoPlanDePago USA la clase Moratoria,
     * pero la clase Moratoria EXTIENDE a TipoPlanDePago???
     * ¿¿¿Y para que usa la clase Moratoria esta clase???
     * */    
    private Moratoria moratoria;

    public TipoPlanDePago() {
    }

    /* 2009-09-24 (santiago) Mensaje para el codificador
     * Este metodo no deberia existir
     * ya que permite cambiar el idTipoPlanDePago (Clave PRIMARIA)
     * Deberia existir un constructor en todo caso
     * public TipoPlanDePago(int _idTipoPlanDePago) {
     * 		idtipoPlanDePago = _idTipoPlanDePago;
     * }
     * */

    public void setIdTipoPlanDePago(int idTipoPlanDePago) {
	this.idTipoPlanDePago = idTipoPlanDePago;
    }

    public int getIdTipoPlanDePago() {
	return idTipoPlanDePago;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getNombre() {
	return nombre;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    public String getDescripcion() {
	return descripcion;
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

    public void setIdTipoImpuesto(int idTipoImpuesto) {
	this.idTipoImpuesto = idTipoImpuesto;
    }

    public int getIdTipoImpuesto() {
	return idTipoImpuesto;
    }

    public void setIdCuentaCaja(int idCuentaCaja) {
	this.idCuentaCaja = idCuentaCaja;
    }

    public int getIdCuentaCaja() {
	return idCuentaCaja;
    }

    public void setIdCuentaImpuesto(int idCuentaImpuesto) {
	this.idCuentaImpuesto = idCuentaImpuesto;
    }

    public int getIdCuentaImpuesto() {
	return idCuentaImpuesto;
    }

    public void setIdCuentaBonificacionMoratoria(int idCuentaBonificacionMoratoria) {
	this.idCuentaBonificacionMoratoria = idCuentaBonificacionMoratoria;
    }

    public int getIdCuentaBonificacionMoratoria() {
	return idCuentaBonificacionMoratoria;
    }

    public void setIdCuentaInteresxMora(int idCuentaInteresxMora) {
	this.idCuentaInteresxMora = idCuentaInteresxMora;
    }

    public int getIdCuentaInteresxMora() {
	return idCuentaInteresxMora;
    }

    public void setOrdenanza(String ordenanza) {
	this.ordenanza = ordenanza;
    }

    public String getOrdenanza() {
	return ordenanza;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }

    public void setMinCuota(int minCuota) {
	this.minCuota = minCuota;
    }

    public int getMinCuota() {
	return minCuota;
    }

    public void setMaxCuotas(int maxCuotas) {
	this.maxCuotas = maxCuotas;
    }

    public int getMaxCuotas() {
	return maxCuotas;
    }

    public void setMaxCuotasVencidas(int maxCuotasVencidas) {
	this.maxCuotasVencidas = maxCuotasVencidas;
    }

    public int getMaxCuotasVencidas() {
	return maxCuotasVencidas;
    }

    public void setPorcentDtoPagoContado(double porcentDtoPagoContado) {
	this.porcentDtoPagoContado = porcentDtoPagoContado;
    }

    public double getPorcentDtoPagoContado() {
	return porcentDtoPagoContado;
    }

    public void setPorcentDtoCancelacion(double porcentDtoCancelacion) {
	this.porcentDtoCancelacion = porcentDtoCancelacion;
    }

    public double getPorcentDtoCancelacion() {
	return porcentDtoCancelacion;
    }

    public void setPorcentInteresMora(double porcentInteresMora) {
	this.porcentInteresMora = porcentInteresMora;
    }

    public double getPorcentInteresMora() {
	return porcentInteresMora;
    }

    public void setFechaInicioInscripcion(String fechaInicioInscripcion) {
	this.fechaInicioInscripcion = fechaInicioInscripcion;
    }

    public String getFechaInicioInscripcion() {
	return fechaInicioInscripcion;
    }

    public void setFechaFinInscripcion(String fechaFinInscripcion) {
	this.fechaFinInscripcion = fechaFinInscripcion;
    }

    public String getFechaFinInscripcion() {
	return fechaFinInscripcion;
    }
    
    public int saveData() {
        int result = -1;
        String params = "";
	
	/* 2009-09-24 (santiago) Mensaje para el codificador
	 * Borrar lineas que no sirven mas
	 * */    
	
	//if (idTipoPlanDePago == -1)  {
	    params = idTipoPlanDePago + ",'"+nombre +"','"+descripcion+"',"+"'"+fechaInicio+"',"+"'"+fechaFin+"',"+idTipoImpuesto+","+idCuentaCaja+","+idCuentaBonificacionMoratoria+
		    ", "+idCuentaInteresxMora + ","+"'"+ordenanza+"',"+"'"+estado+"', "+minCuota + "," + maxCuotas + ", "+ maxCuotasVencidas + ", " + porcentDtoPagoContado +
		    ", "+porcentDtoCancelacion + "," + porcentInteresMora + "," +"'"+fechaInicioInscripcion+"', "+"'"+fechaFinInscripcion+"', " + idCuentaBonificacionContado + 
		    ", "+idCuentaPlanDePago + ", "+"'"+rescision+"',"+porcentajeCondonacionIntereses+",'" + obligatorio + "'";
	    result = LibSQL.getInt("taxes.addTipoPlanDePago", params);
	    idTipoPlanDePago = result;
	/* 2009-09-24 (santiago) Mensaje para el codificador
	 * Borrar lineas que no sirven mas
	 * */    
	/*} else {
	     params = idTipoPlanDePago + ",'"+nombre +"','"+descripcion+"',"+"'"+fechaInicio+"',"+"'"+fechaFin+"',"+idTipoImpuesto+","+idCuentaCaja+","+idCuentaBonificacionMoratoria+
	             ", "+idCuentaInteresxMora + ","+"'"+ordenanza+"',"+"'"+estado+"', "+minCuota + "," + maxCuotas + ", "+ maxCuotasVencidas + ", " + porcentDtoPagoContado +
	             ", "+porcentDtoCancelacion + "," + porcentInteresMora + "," +"'"+fechaInicioInscripcion+"', "+"'"+fechaFinInscripcion+"', " + idCuentaBonificacionContado + 
	             ", "+idCuentaPlanDePago + ", "+"'"+rescision+"'";
	    result = LibSQL.getInt("taxes.setTipoPlanDePago", params);
	}*/
	return (result) ;
    }
    
    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("taxes.getTipoPlanDePago", idTipoPlanDePago);
	try {
	    if (result.next()) {
	        /* 2009-09-24 (santiago) Mensaje para el codificador
	         * Si se tiene el idTipoPlanDePago (CLAVE PRIMARIA)
		 * NO SE DEBE SOBREESCRIBIR!!!
		 * La siguiente linea fue comentada por santiago
	         * */    
		//idTipoPlanDePago = result.getInt("idtipoplandepago");
		nombre = result.getString("nombre");
		descripcion = result.getString("descripcion");
		fechaInicio = result.getString("fechainicio");
		fechaFin = result.getString("fechafin");
		idTipoImpuesto = result.getInt("idtipoimpuesto");
		idCuentaCaja = result.getInt("idcuentacaja");
		idCuentaImpuesto = result.getInt("idcuentaimpuesto");
		idCuentaBonificacionMoratoria = result.getInt("idcuentabonificacionmoratoria");
		idCuentaInteresxMora = result.getInt("idcuentainteresxmora");
		ordenanza = result.getString("ordenanza");
		estado = result.getString("estado");
		minCuota = result.getInt("mincuota");
		maxCuotas = result.getInt("maxcuotas");
		maxCuotasVencidas = result.getInt("maxcuotasvencidas");
		porcentDtoPagoContado = result.getDouble("porcentdtoPagocontado");
		porcentDtoCancelacion = result.getDouble("porcentdtocancelacion");
		porcentInteresMora = result.getDouble("porcentinteresmora");
		fechaInicioInscripcion = result.getString("fechainicioinscripcion");
		fechaFinInscripcion = result.getString("fechafininscripcion");
	        idCuentaBonificacionContado = result.getInt("idcuentabonificacioncontado");
	        idCuentaPlanDePago = result.getInt("idcuentaplandepago");
		rescision = result.getString("rescision");
	        porcentajeCondonacionIntereses = result.getDouble("porcentajeCondonacionIntereses");
		obligatorio = result.getBoolean("obligatorio");
		porcentajeInteresCuota = result.getDouble("porcentajeinterescuota");
	        activo = result.getBoolean("activo");
		loadMoratoria();
	    } 
	} catch (SQLException e) {
	    // TODO
	    Advisor.printException(e);
	}
	/* 2009-09-24 (santiago) Mensaje para el codificador
	 * En el retrieveData de la clase Moratoria además
	 * de la SQLException se está capturando
	 * una NullPointerException... Deberían
	 * ser todas iguales las capturas, o aquí el NullPointerException
	 * o se borra del metodo Moratoria.retrieveData()
	 * y así en TODAS LAS CLASES DEL SISTEMA
	 * */    
	
    }
    
     /* 2009-09-24 (santiago) Mensaje para el codificador
      * Metodo con el cuerpo vacío, aunque sea comentar
      * para qué hubiera servido
      * */    
    public void retrieveDataTipoPlanDePago() {
    }
    
    /* 2009-09-24 (santiago) Mensaje para el codificador
     * Se está obteniendo UN SOLO resultado de una
     * funcion que podría retornar más de uno
     * Esto está mal, ya que depende de un valor
     * que no es ni siquiera clave única (idTipoImpuesto)
     * */    
    public void retrieveIdTipoPlanDePago() {
	idTipoPlanDePago = LibSQL.getInt("taxes.getIdTipoPlanDePago",""+ idTipoImpuesto);
    }
    
    public int delete(){
	return LibSQL.getInt("taxes.deleteTipoPlanDePago",""+ idTipoPlanDePago);
    }
    
    public boolean tienePlanDePago(){
	return LibSQL.getBoolean("taxes.tienePlandePago",""+ idTipoPlanDePago);
    }

    public void setMoratoria(Moratoria moratoria) {
	this.moratoria = moratoria;
    }

    public Moratoria getMoratoria() {
	return moratoria;
    }
    
    public void setIdBien(int idBien) {
	this.idBien = idBien;
    }

    public int getIdBien() {
	return idBien;
    }

    /* 2009-09-24 (santiago) Mensaje para el codificador
     * Para qué está este método?
     * Se mezcla el concepto puro de la clase TipoPlanDePago,
     * la cual no tiene relación con el bien, sea catastro o automotor
     * ya que solo es para parametrizar el sistema
     * ¿y se está cargando una moratoria desde aquí?
     * 
     * */    
    public void loadMoratoria() {
	moratoria = new Moratoria();
	moratoria.setIdTipoPlanDePago(getIdTipoPlanDePago());
	moratoria.setIdBien(getIdBien());
	moratoria.retrieveData();
    }

    public void setIdCuentaBonificacionContado(int idCuentaBonificacionContado) {
	this.idCuentaBonificacionContado = idCuentaBonificacionContado;
    }

    public int getIdCuentaBonificacionContado() {
	return idCuentaBonificacionContado;
    }

    public void setIdCuentaPlanDePago(int idCuentaPlanDePago) {
	this.idCuentaPlanDePago = idCuentaPlanDePago;
    }

    public int getIdCuentaPlanDePago() {
	return idCuentaPlanDePago;
    }

    public void setRescision(String rescision) {
	this.rescision = rescision;
    }

    public String getRescision() {
	return rescision;
    }

    public void setPorcentajeCondonacionIntereses(double _porcentajeCondonacionIntereses) {
	porcentajeCondonacionIntereses = _porcentajeCondonacionIntereses;
    }

    public double getPorcentajeCondonacionIntereses() {
	return porcentajeCondonacionIntereses;
    }
    
    public void setObligatorio(boolean _obligatorio) {
	obligatorio = _obligatorio;
    }
    
    public boolean isObligatorio() {
	return obligatorio;
    }
    
    public void setActivo(boolean _activo) {
	activo = _activo;
    }
    
    public boolean isActivo() {
	return activo;
    }

    public void setPorcentajeInteresCuota(double porcentajeInteresCuota) {
	this.porcentajeInteresCuota = porcentajeInteresCuota;
    }

    public double getPorcentajeInteresCuota() {
	return porcentajeInteresCuota;
    }
    
    public void retrieveData2() {
	ResultSet result = LibSQL.exFunction("taxes.getTipoPlanDePago2", idTipoImpuesto);
	try {
	    if (result.next()) {
		idTipoPlanDePago = result.getInt("idtipoplandepago");
		nombre = result.getString("nombre");
		descripcion = result.getString("descripcion");
		fechaInicio = result.getString("fechainicio");
		fechaFin = result.getString("fechafin");
		idCuentaCaja = result.getInt("idcuentacaja");
		idCuentaImpuesto = result.getInt("idcuentaimpuesto");
		idCuentaBonificacionMoratoria = result.getInt("idcuentabonificacionmoratoria");
		idCuentaInteresxMora = result.getInt("idcuentainteresxmora");
		ordenanza = result.getString("ordenanza");
		estado = result.getString("estado");
		minCuota = result.getInt("mincuota");
		maxCuotas = result.getInt("maxcuotas");
		maxCuotasVencidas = result.getInt("maxcuotasvencidas");
		porcentDtoPagoContado = result.getDouble("porcentdtoPagocontado");
		porcentDtoCancelacion = result.getDouble("porcentdtocancelacion");
		porcentInteresMora = result.getDouble("porcentinteresmora");
		fechaInicioInscripcion = result.getString("fechainicioinscripcion");
		fechaFinInscripcion = result.getString("fechafininscripcion");
		idCuentaBonificacionContado = result.getInt("idcuentabonificacioncontado");
		idCuentaPlanDePago = result.getInt("idcuentaplandepago");
		rescision = result.getString("rescision");
		porcentajeCondonacionIntereses = result.getDouble("porcentajeCondonacionIntereses");
		obligatorio = result.getBoolean("obligatorio");
		porcentajeInteresCuota = result.getDouble("porcentajeinterescuota");
		activo = result.getBoolean("activo");
		loadMoratoria();
	    } 
	} catch (SQLException e) {
	    // TODO
	    Advisor.printException(e);
	}
    }
    
    
}

