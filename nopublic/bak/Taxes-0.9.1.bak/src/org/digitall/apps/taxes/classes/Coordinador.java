package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class Coordinador {

    private int idBien = -1;
    private int idTipoImpuesto = -1;
    private String impuesto = "";
    private String contribuyente = "";
    private int tipoDescuento = 0;
    private double porcentajeDto = 1.0;
    private String Descuento = "";
    private double monto = 0.0;
    private double interes = 0.0;
    private double montoTotal = 0.0;
    private int anticipoDesde = -1;
    private int anioDesde = -1;
    private int anticipoHasta = -1;
    private int anioHasta = -1;
    private int cantPeriodos = 0;
    private String nroBien = "";
    private String dni = "";
    private String nombreTipoImpuesto = "";
    
    private double porcentajeDtoContado = 0.3;
    private TipoPlanDePago tipoPlanDePago;
    private Cadastral catastro;
    private Car automotor;
    
    private Moratoria moratoria;

    public Coordinador() {
    }

    public void setIdBien(int idBien) {
	this.idBien = idBien;
    }
    public int getIdBien() {
	return idBien;
    }

    public void setIdTipoImpuesto(int idTipoImpuesto) {
	this.idTipoImpuesto = idTipoImpuesto;
    }

    public int getIdTipoImpuesto() {
	return idTipoImpuesto;
    }

    public void setImpuesto(String impuesto) {
	this.impuesto = impuesto;
    }

    public String getImpuesto() {
	return impuesto;
    }

    public void setContribuyente(String contibuyente) {
	this.contribuyente = contibuyente;
    }

    public String getContribuyente() {
	return contribuyente;
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

    public void setMontoTotal(double montoTotal) {
	this.montoTotal = montoTotal;
    }

    public double getMontoTotal() {
	return montoTotal;
    }

    public void setAnticipoDesde(int anticipoDesde) {
	this.anticipoDesde = anticipoDesde;
    }

    public int getAnticipoDesde() {
	return anticipoDesde;
    }

    public void setAnioDesde(int anioDesde) {
	this.anioDesde = anioDesde;
    }

    public int getAnioDesde() {
	return anioDesde;
    }

    public void setAnticipoHasta(int anticipoHasta) {
	this.anticipoHasta = anticipoHasta;
    }

    public int getAnticipoHasta() {
	return anticipoHasta;
    }

    public void setAnioHasta(int anioHasta) {
	this.anioHasta = anioHasta;
    }

    public int getAnioHasta() {
	return anioHasta;
    }

    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("taxes.getDatosCoordinador", idTipoImpuesto +","+ idBien +",'"+ tipoPlanDePago.getFechaInicio() +"','"+ tipoPlanDePago.getFechaFin() +"'");
	try {
	    if (result.next()) {
		anticipoDesde = result.getInt("menoranticipo");
		anioDesde = result.getInt("menoranio");
		anticipoHasta = result.getInt("mayoranticipo");
		anioHasta = result.getInt("mayoranio");
		cantPeriodos = result.getInt("cantidad");
		monto = result.getDouble("montobase");
	        interes = result.getDouble("interes");
	        montoTotal = result.getDouble("montototal");
	        nombreTipoImpuesto = result.getString("nombreTipoImpuesto");
	    } 
	} catch (SQLException e) {
	    // TODO
	    System.out.println("error");
	} catch (NullPointerException e) {
	    // TODO
	}
    }

   
    public void setCantPeriodos(int cantPeriodos) {
	this.cantPeriodos = cantPeriodos;
    }

    public int getCantPeriodos() {
	return cantPeriodos;
    }

    public void setCatastro(Cadastral catastro) {
	this.catastro = catastro;
    }

    public Cadastral getCatastro() {
	return catastro;
    }

    public void setAutomotor(Car automotor) {
	this.automotor = automotor;
    }

    public Car getAutomotor() {
	return automotor;
    }

    public void setTipoDescuento(int tipoDescuento) {
	this.tipoDescuento = tipoDescuento;
	if (tipoDescuento == 0)  {
	    porcentajeDto = 1.0;
	} else  {
	    porcentajeDto = 0.5;
	}
    }

    public int getTipoDescuento() {
	return tipoDescuento;
    }

    public void setDescuento(String descuento) {
	this.Descuento = descuento;
    }

    public String getDescuento() {
	return Descuento;
    }

    public void setTipoPlanDePago(TipoPlanDePago tipoPlanDePago) {
	this.tipoPlanDePago = tipoPlanDePago;
    }

    public TipoPlanDePago getTipoPlanDePago() {
	return tipoPlanDePago;
    }

    public void setPorcentajeDto(double porcentajeDto) {
	this.porcentajeDto = porcentajeDto;
    }

    public double getPorcentajeDto() {
	return porcentajeDto;
    }

    public double getPorcentajeDtoContado() {
	return porcentajeDtoContado;
    }

    public void setPorcentajeDtoContado(double porcentajeDtoContado) {
	this.porcentajeDtoContado = porcentajeDtoContado;
    }
    
    public void loadTipoPlanDePago() {
	tipoPlanDePago = new TipoPlanDePago();
	tipoPlanDePago.setIdTipoImpuesto(idTipoImpuesto);
	tipoPlanDePago.setIdBien(idBien);
	tipoPlanDePago.retrieveIdTipoPlanDePago();
	tipoPlanDePago.retrieveData();
    }

    public void setNroBien(String nroBien) {
	this.nroBien = nroBien;
    }

    public String getNroBien() {
	return nroBien;
    }

    public void setDni(String dni) {
	this.dni = dni;
    }

    public String getDni() {
	return dni;
    }
    
    public void loadObject(){
	if ((getIdTipoImpuesto() == 1) || (getIdTipoImpuesto() == 2) ) {
	    catastro = new Cadastral();
	    catastro.setIdCatastro(getIdBien());
	    catastro.retrieveData();
	    
	} else if (getIdTipoImpuesto() == 3) {
	    automotor = new Car();
	    automotor.setIdAutomotor(getIdBien());
	    automotor.retrieveIdDominio();
	    automotor.retrieveData();
	}
    }

    public void setMoratoria(Moratoria moratoria) {
	this.moratoria = moratoria;
    }

    public Moratoria getMoratoria() {
	return moratoria;
    }

    public void setNombreTipoImpuesto(String nombreTipoImpuesto) {
	this.nombreTipoImpuesto = nombreTipoImpuesto;
    }

    public String getNombreTipoImpuesto() {
	return nombreTipoImpuesto;
    }
}
