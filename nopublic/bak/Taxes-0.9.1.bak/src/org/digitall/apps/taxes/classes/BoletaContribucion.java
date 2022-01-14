package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;

import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class BoletaContribucion {

    private int idBoletaContribucion = -1;
    private String fechaPago = "";
    private String fechaImpresion = "";
    private String concepto = "";
    private double importe = 0.0;
    private String contribuyente = "";
    private String nroBien = "";
    private String tipoBien = "";
    private int anio = -1;
    private int numero = -1;
    private String barcode = "";
    private String información = "";
    private boolean pagada = false;
    private String leyenda1 = "";
    private String leyenda2 = "";
    private String usuario = "";
    private String nombreUsuario = "";
    private String estado = "";
    private int idTipoImpuesto = 0;
    private int idAlicuotaContribucion = 0;
    private int idContribucion = 0;
    private double multiplicador = 1;
    private double valorModulo = 0;
    private double montoBase = 0;
    private double porcentaje = 0;
    private double montoFijo = 0;
    
    private int idBien = 0;
    private Contribucion contribucion;
    private String numeroconformato = "";
    private String nroDocumento = "0";

    public BoletaContribucion() {
    }

    public void setIdBoletaContribucion(int idBoletaContribucion) {
	this.idBoletaContribucion = idBoletaContribucion;
    }

    public int getIdBoletaContribucion() {
	return idBoletaContribucion;
    }

    public void setFechaPago(String fechaPago) {
	this.fechaPago = fechaPago;
    }

    public String getFechaPago() {
	return fechaPago;
    }

    public void setFechaImpresion(String fechaImpresion) {
	this.fechaImpresion = fechaImpresion;
    }

    public String getFechaImpresion() {
	return fechaImpresion;
    }

    public void setConcepto(String concepto) {
	this.concepto = concepto;
    }

    public String getConcepto() {
	return concepto;
    }

    public void setImporte(double importe) {
	this.importe = importe;
    }

    public double getImporte() {
	return importe;
    }

    public void setContribuyente(String contribuyente) {
	this.contribuyente = contribuyente;
    }

    public String getContribuyente() {
	return contribuyente;
    }

    public void setNroBien(String nroBien) {
	this.nroBien = nroBien;
    }

    public String getNroBien() {
	return nroBien;
    }

    public void setTipoBien(String tipoBien) {
	this.tipoBien = tipoBien;
    }

    public String getTipoBien() {
	return tipoBien;
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

    public void setInformación(String información) {
	this.información = información;
    }

    public String getInformación() {
	return información;
    }

    public void setPagada(boolean pagada) {
	this.pagada = pagada;
    }

    public boolean isPagada() {
	return pagada;
    }

    public void setLeyenda1(String leyenda1) {
	this.leyenda1 = leyenda1;
    }

    public String getLeyenda1() {
	return leyenda1;
    }

    public void setLeyenda2(String leyenda2) {
	this.leyenda2 = leyenda2;
    }

    public String getLeyenda2() {
	return leyenda2;
    }

    public void setUsuario(String usuario) {
	this.usuario = usuario;
    }

    public String getUsuario() {
	return usuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
	this.nombreUsuario = nombreUsuario;
    }

    public String getNombreUsuario() {
	return nombreUsuario;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }
    
    public void setIdTipoImpuesto(int idTipoImpuesto) {
        this.idTipoImpuesto = idTipoImpuesto;
    }

    public int getIdTipoImpuesto() {
        return idTipoImpuesto;
    }

    public void setContribucion(Contribucion contribucion) {
        this.contribucion = contribucion;
    }

    public Contribucion getContribucion() {
        return contribucion;
    }

    public void setIdAlicuotaContribucion(int idAlicuotaContribucion) {
        this.idAlicuotaContribucion = idAlicuotaContribucion;
    }

    public int getIdAlicuotaContribucion() {
        return idAlicuotaContribucion;
    }

    public void setIdBien(int idBien) {
        this.idBien = idBien;
    }

    public int getIdBien() {
        return idBien;
    }

    public void setNumeroconformato(String numeroconformato) {
        this.numeroconformato = numeroconformato;
    }

    public String getNumeroFormateado() {
        return numeroconformato;
    }

    public void setIdContribucion(int idContribucion) {
        this.idContribucion = idContribucion;
    }

    public int getIdContribucion() {
        return idContribucion;
    }
    
    public boolean delete() {
        return LibSQL.getBoolean("taxes.delBoletaContribucion",""+ idBoletaContribucion);
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

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }
    
    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("taxes.getBoletaContribucion",""+ idBoletaContribucion);
	try  {
	    if (result.next())  {
	        setFechaPago(result.getString("fechapago"));
	        setFechaImpresion(result.getString("fechaimpresion"));
		setConcepto(result.getString("concepto"));
	        setImporte(result.getDouble("importe"));
		setContribuyente(result.getString("contribuyente"));
		setNroBien(result.getString("nrobien"));
		setTipoBien(result.getString("tipobien"));
		setAnio(result.getInt("anio"));
	        setNumero(result.getInt("numero"));
	        setBarcode(result.getString("barcode"));
	        setInformación(result.getString("informacion"));
	        setPagada(result.getBoolean("pagada"));
		setLeyenda1(result.getString("leyenda1"));
		setLeyenda2(result.getString("leyenda2")); 
		setUsuario(result.getString("usuario"));
	        setNombreUsuario(result.getString("nombreusuario"));
		setIdTipoImpuesto(result.getInt("idtipoimpuesto"));
		setIdAlicuotaContribucion(result.getInt("idalicuotacontribucion"));
		setNumeroconformato(result.getString("numeroconformato"));
	        setIdContribucion(result.getInt("idcontribucion"));
                setMultiplicador(result.getDouble("multiplicador"));
	        setPorcentaje(result.getDouble("porcentaje"));
	        setMontoBase(result.getDouble("montobase"));
	        setMontoFijo(result.getDouble("montofijo"));
	        setValorModulo(result.getDouble("valormodulo"));
                setNroDocumento(result.getString("nrodocumento"));
	    }
	    
	} catch (SQLException e) {
	    // TODO
	    e.printStackTrace();
	}
	
    }
    
    public int saveData() {
	String params = "";
	int result = -1;
	    params = "'"+ concepto + "'," + importe + ",'" + contribuyente +"',"+ idBien +",'" + nroBien + "','" + 
	             tipoBien + "','"+ información +"','"+ leyenda1 +"','"+ leyenda2 +"',"+ 
	             idTipoImpuesto +","+ idAlicuotaContribucion + "," + multiplicador + "," + 
                     valorModulo + "," + montoBase + "," + porcentaje + "," + montoFijo + ",'" + nroDocumento + "','"+ estado +"'";
	if (idBoletaContribucion == -1) {
	    result = LibSQL.getInt("taxes.addBoletaContribucion",params);
	    idBoletaContribucion = result;
	} else {
	    params = idBoletaContribucion +","+ params;
	    result = LibSQL.getInt("taxes.setBoletaContribucion",params); //falta crearla (verificar si es necesario)
	}
	return result;
    }

    public boolean approveBoletaContribucion() {
        return LibSQL.getBoolean("taxes.approveBoletaContribucion",idBoletaContribucion);
    }
}
