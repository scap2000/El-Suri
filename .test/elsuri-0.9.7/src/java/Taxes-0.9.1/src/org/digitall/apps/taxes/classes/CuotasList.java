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
 * CuotasList.java
 *
 * */
package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.JTextField;

import org.digitall.apps.taxes.interfases.FeeOptions;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class CuotasList extends Vector<Cuota> {

    private Date fechaInicial = null;
    private Date fechaFinal = null;
    private Date fchRegistro = null;
    private Date fchInicial = null;
    private Date fchFinal = null;
    private Date fchUltimoPago = null;
    private Date fchBaja = null;
    private Date fchEximision = null;
    
    private int idTipoImpuesto = -1;
    private int idBien = -1;

    public CuotasList(int _idTipoImpuesto, int _idBien) {
	idTipoImpuesto = _idTipoImpuesto;
	idBien = _idBien;
	setDates();
	fetchData();
    }

    private void fetchData() {
	//desde fecha inicial a fecha final 
	//si no se ha seteado fecha inicial, tomar 01/01/primer año que adeude alguna cuota
	//si no se ha seteado fecha final, tomar fecha actual, por transicion, sera el 31/12/año actual
	int anioactual = Integer.parseInt(Environment.currentYear.toString());
	int nroCuotaPagar = -1;
	int ultimaCuotaPagada = -1;
	int anioUltimaCuotaPagada = -1;
	int anio = -1;
	boolean calcular = false;
	ResultSet lastPayment = LibSQL.exFunction("impuestos.getLastPayment", "" + idTipoImpuesto + "," + idBien);
	try {
	    if (lastPayment.next()) {
		ultimaCuotaPagada = lastPayment.getInt("idtipocuota");
		anioUltimaCuotaPagada = lastPayment.getInt("anio");
		nroCuotaPagar = ultimaCuotaPagada;
		anio = anioUltimaCuotaPagada;
		if ((nroCuotaPagar == 6) || (nroCuotaPagar == 0)) {
		    nroCuotaPagar = 1;
		    anio = anio + 1;
		} else {
		    nroCuotaPagar = nroCuotaPagar + 1;
		}
		calcular = true;
	    } else {
		if (fechaInicial != null) {
		    
		    calcular = true;
		} else {
		    //Advisor.messageBox("El catastro/dominio no registra nigún pago!", "Mensaje");
		}
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	if (calcular) {
	    calculaCuotas(anioUltimaCuotaPagada, ultimaCuotaPagada, anio, anioactual, nroCuotaPagar);
	}
    }

    public void calculaCuotas(int anioUltimaCuotaPagada, int ultimaCuotaPagada, int anio, int anioactual, int nroCuotaPagar) {
	int qtyCuotasPag = LibSQL.getInt("impuestos.getQtyCuotasPagadas", "" + idTipoImpuesto + "," + idBien + "," + anioUltimaCuotaPagada);
	if (qtyCuotasPag < ultimaCuotaPagada) {
	    for (int i = 0; i < ultimaCuotaPagada-qtyCuotasPag; i++) {
		Cuota _cuota = new Cuota();
		_cuota.setIdTipoImpuesto(idTipoImpuesto);
		_cuota.setIdBien(idBien);
		_cuota.setAnio(anioUltimaCuotaPagada);
		_cuota.setIdTipoCuota(-1);
		add(_cuota);
	    }
	    ResultSet cuotasPagadas = LibSQL.exFunction("impuestos.getCuotasPagadas", "" + idTipoImpuesto + "," + idBien);
	    try {
		while (cuotasPagadas.next()) {
		    Cuota _cuota = new Cuota();
		    _cuota.setIdTipoImpuesto(idTipoImpuesto);
		    _cuota.setIdBien(idBien);
		    _cuota.setPagada(true);
		    _cuota.setIdTipoCuota(cuotasPagadas.getInt("idtipocuota"));
		    _cuota.setAnio(cuotasPagadas.getInt("anio"));
		    _cuota.setCuota(cuotasPagadas.getDouble("valorreal"));
		    _cuota.setMonto(cuotasPagadas.getDouble("monto"));
		    _cuota.setFecha(cuotasPagadas.getDate("fechapago"));
		    _cuota.setFechaVenc(cuotasPagadas.getDate("fechavto"));
		    _cuota.setInteres(cuotasPagadas.getDouble("monto") - cuotasPagadas.getDouble("valorreal"));
		    int dias = LibSQL.getInt("impuestos.getDays", "'" + _cuota.getFecha() + "','" + _cuota.getFechaVenc() + "'");
		    if (dias >= 0) {
			_cuota.setVencida(false);
		    } else {
			_cuota.setVencida(true);
		    }
		    add(_cuota);
		}
	    } catch (Exception ex) {
		ex.printStackTrace();
	    }
	    /**
	   * parche
	   * */
	} else if (qtyCuotasPag == ultimaCuotaPagada) {
	    //nroCuotaPagar) {
	    ResultSet cuotasPagadas = LibSQL.exFunction("impuestos.getCuotasPagadas", "" + idTipoImpuesto + "," + idBien);
	    try {
		while (cuotasPagadas.next()) {
		    Cuota _cuota = new Cuota();
		    _cuota.setIdTipoImpuesto(idTipoImpuesto);
		    _cuota.setIdBien(idBien);
		    _cuota.setPagada(true);
		    _cuota.setIdTipoCuota(cuotasPagadas.getInt("idtipocuota"));
		    _cuota.setAnio(cuotasPagadas.getInt("anio"));
		    _cuota.setCuota(cuotasPagadas.getDouble("valorreal"));
		    _cuota.setMonto(cuotasPagadas.getDouble("monto"));
		    _cuota.setFecha(cuotasPagadas.getDate("fechapago"));
		    _cuota.setFechaVenc(cuotasPagadas.getDate("fechavto"));
		    _cuota.setInteres(cuotasPagadas.getDouble("monto") - cuotasPagadas.getDouble("valorreal"));
		    int dias = LibSQL.getInt("impuestos.getDays", "'" + _cuota.getFecha() + "','" + _cuota.getFechaVenc() + "'");
		    if (dias >= 0) {
			_cuota.setVencida(false);
		    } else {
			_cuota.setVencida(true);
		    }
		    //_cuota.setCuota(_cuota.calcularCuota());
		    add(_cuota);
		}
	    } catch (Exception ex) {
		ex.printStackTrace();
	    }
	}
	//anio: año del la cuota a pagar; anioactual: año final
	GregorianCalendar xx = new GregorianCalendar();
	xx.setTime(fchFinal);
	anioactual = xx.get(Calendar.YEAR);
	//aca voy
	//while (anio <= anioactual + 1) {
	while (anio <= anioactual) {
	    for (int idtipocuota = nroCuotaPagar; idtipocuota <= 6; idtipocuota++) {
		Cuota _cuota = new Cuota();
		_cuota.setIdTipoCuota(idtipocuota);
		_cuota.setIdTipoImpuesto(idTipoImpuesto);
		_cuota.setAnio(anio);
		_cuota.fetchData(idBien);
		Date fechavto = LibSQL.getDate("impuestos.getFechaVto2", "" + idtipocuota + "," + anio + "," + idTipoImpuesto);
		_cuota.setFechaVenc(fechavto);
		//_cuota.setCuota(_cuota.calcularCuota());
		//_cuota.calcularMonto();
		add(_cuota);
	    }
	    anio++;
	    nroCuotaPagar = 1;
	}
    }

    public void setFechaInicial(Date _fechaInicial) {
	fechaInicial = _fechaInicial;
    }

    public Date getFechaInicial() {
	return fechaInicial;
    }

    public void setFechaFinal(Date _fechaFinal) {
	fechaFinal = _fechaFinal;
    }

    public Date getFechaFinal() {
	return fechaFinal;
    }

    public void setIdtipoimpuesto(int _idtipoimpuesto) {
	idTipoImpuesto = _idtipoimpuesto;
    }

    public int getIdtipoImpuesto() {
	return idTipoImpuesto;
    }

    public void setIdbien(int _idbien) {
	idBien = _idbien;
    }

    public int getIdbien() {
	return idBien;
    }

    private void setDates() {
	int tipoCuota = -1;
	int anio = -1;
	ResultSet lastPayment = LibSQL.exFunction("impuestos.getLastPayment", "" + idTipoImpuesto + "," + idBien);
	try {
	    if (lastPayment.next()) {
	        tipoCuota = lastPayment.getInt("idtipocuota");
	        anio = lastPayment.getInt("anio");
		fchUltimoPago = LibSQL.getDate("impuestos.getFechaVto2", "" + tipoCuota + "," + anio + "," + idTipoImpuesto);
		if (fchUltimoPago != null)  {
		    fchInicial = fchUltimoPago;
		}
	    }
	    
	}catch (Exception ex)  {
	    ex.printStackTrace();
	}   
	
	if (idTipoImpuesto == 3)  {		/** si (Impuesto == Automotor) */
	    ResultSet data = LibSQL.exFunction("impuestos.getCar","" + idBien);
	    try  {
		if (data.next())  {
		    fchRegistro = data.getDate("pagadesde");
		    fchBaja = data.getDate("fechabaja");
		    fchEximision = data.getDate("fechaeximision");
		    if (fchUltimoPago != null)  { //--> Obtengo la Fecha inicial
		        fchInicial = fchUltimoPago;
		    } else  {
			fchInicial = fchRegistro;
		    }
		    if ( (fchBaja != null) && (fchEximision != null) )  {
			if (fchBaja.after(fchEximision))  {
			    fchFinal = fchBaja;
			} else {
			    fchFinal = fchEximision;
			}
		    } else if ( (fchBaja != null) && (fchEximision == null)) {
		        fchFinal = fchBaja;
		    } else if ( (fchBaja == null) && (fchEximision != null)) {
		        fchFinal = fchEximision;
		    } else {
		         int finalYear = Integer.parseInt(Environment.currentYear)  + 1;
		         GregorianCalendar dateAux = new GregorianCalendar(finalYear,Calendar.JANUARY,1);
		         fchFinal = dateAux.getTime();
		    }
		}
		
	    } catch (Exception ex)  {
		ex.printStackTrace();
	    }
	    
	} else {	/** si se trata de Impuesto TGS o Inmobiliario */
	    ResultSet data = LibSQL.exFunction("impuestos.getCadastral","" + idBien);
	    try  {
		if (data.next())  {
		    fchRegistro = data.getDate("fechaasig");
		    fchBaja = data.getDate("fechabaja");
		    if (fchUltimoPago != null)  { //--> Obtengo la Fecha inicial
		     fchInicial = fchUltimoPago;
		    } else  {
		     fchInicial = fchRegistro;
		    }
		    if ( fchBaja != null )  {	//--> Obtengo la fecha final
			fchFinal = fchBaja;
		    } else {
			 int finalYear = Integer.parseInt(Environment.currentYear)  + 1;
			 GregorianCalendar dateAux = new GregorianCalendar(finalYear,Calendar.JANUARY,1);
			 fchFinal = dateAux.getTime();
		    }
	    }
	     
	} catch (Exception ex)  {
	 ex.printStackTrace();
	}
	}
    }

}
