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
