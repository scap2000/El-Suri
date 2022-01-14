package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class BoletaTgs {

    private int idboletatgs = -1;
    private int idaccountingentry = -1;
    private String fechapago = "";
    private String fechaimpresion = "";
    private String contribuyente = "";
    private String seccion = "";
    private String manzana = "";
    private String parcela = "";
    private String zona = "";
    private int catastro = -1;
    private String nrocuenta = "";
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
    private String categoria = "";
    private String terreno = "";
    private double valedificacion = 0;
    private double valfiscal = 0;
    private String usuario = "";
    private String barCode = "";
    private String apoderado = "";
    private int anio = -1;
    private String numero = "";
    private int anioDesde = -1;
    private int anticipoDesde = -1;
    private int anioHasta = -1;
    private int anticipoHasta = -1;
    private int idCatastro = -1;
    private int idDescuento = -1;
    private String nombreDescuento = "";
    
    private int cantAnticipos = 0;
    private String fechaProximoVto = "";
    private boolean pagoAnual = false;
    
    public BoletaTgs() {
    }

    public void setIdboletatgs(int idboletatgs) {
        this.idboletatgs = idboletatgs;
    }

    public int getIdboletatgs() {
        return idboletatgs;
    }

    public void setIdaccountingentry(int idaccountingentry) {
        this.idaccountingentry = idaccountingentry;
    }

    public int getIdaccountingentry() {
        return idaccountingentry;
    }

    public void setFechapago(String fechapago) {
        this.fechapago = fechapago;
    }

    public String getFechapago() {
        return fechapago;
    }

    public void setFechaimpresion(String fechaimpresion) {
        this.fechaimpresion = fechaimpresion;
    }

    public String getFechaimpresion() {
        return fechaimpresion;
    }

    public void setContribuyente(String contribuyente) {
        this.contribuyente = contribuyente;
    }

    public String getContribuyente() {
        return contribuyente;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setManzana(String manzana) {
        this.manzana = manzana;
    }

    public String getManzana() {
        return manzana;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    public String getParcela() {
        return parcela;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getZona() {
        return zona;
    }

    public void setCatastro(int catastro) {
        this.catastro = catastro;
    }

    public int getCatastro() {
        return catastro;
    }

    public void setNrocuenta(String _nrocuenta) {
        nrocuenta = _nrocuenta;
    }

    public String getNrocuenta() {
        return nrocuenta;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double getImporte() {
        return importe;
    }

    public void setRecargo(double recargo) {
        this.recargo = recargo;
    }

    public double getRecargo() {
        return recargo;
    }

    public void setDeducciones(double deducciones) {
        this.deducciones = deducciones;
    }

    public double getDeducciones() {
        return deducciones;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setNroimpresiones(int nroimpresiones) {
        this.nroimpresiones = nroimpresiones;
    }

    public int getNroimpresiones() {
        return nroimpresiones;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
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

    public void setTerreno(String _terreno) {
        terreno = _terreno;
    }

    public String getTerreno() {
        return terreno;
    }

    public void setValedificacion(double _valedificacion) {
        valedificacion = _valedificacion;
    }

    public double getValedificacion() {
        return valedificacion;
    }

    public void setValfiscal(double _valfiscal) {
        valfiscal = _valfiscal;
    }

    public double getValfiscal() {
        return valfiscal;
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
    
    public void setApoderado(String _apoderado) {
        apoderado = _apoderado;
    }

    public String getApoderado() {
        return apoderado;
    }
    
    public void setAnio(int _anio) {
        anio = _anio;
    }

    public int getAnio() {
        return anio;
    }

    public void setNumero(String _numero) {
        numero = _numero;
    }

    public String getNumero() {
        return numero;
    }
    
    public int saveData(){
        String params = "";
        int result = -1;
        params = idCatastro +","+ idaccountingentry +",'"+ fechapago + "','"+ fechaimpresion +"','" + 
                 contribuyente + "','" + seccion + "','" + manzana + "','" + parcela + "','"+ zona +"',"+ 
                 catastro +",'"+ nrocuenta +"','"+ concepto +"','"+ vencimiento +"',"+ importe +","+ 
                 recargo +","+ deducciones +","+ total +","+ idusuario +","+ nroimpresiones +",'"+ 
                 estado +"','"+ localidad +"','"+ domicilio +"','"+ categoria +"','"+ terreno +"',"+ 
                 valedificacion +","+ valfiscal +",'"+ usuario +"','"+ apoderado +"',"+ anioDesde +","+ 
                 anticipoDesde +","+ anioHasta +","+ anticipoHasta +","+ idDescuento +",'"+ 
		 nombreDescuento +"','"+ fechaProximoVto +"',"+ dtoPagoAnual +","+ porcentajeDtoPagoAnual +","+ pagoAnual;
        if (idboletatgs == -1)  {
            result = LibSQL.getInt("taxes.addBoletaTgs",params);
            idboletatgs = result;
        } else  {
            params = idboletatgs +","+ params;
            result = LibSQL.getInt("taxes.setBoletaTgs",params); //falta crearla (verificar si es necesario)
        }
        return result;
    }
    
    
    public void retrieveData() {
        ResultSet result = LibSQL.exFunction("taxes.getBoletaTgs", idboletatgs);
        try {
            if (result.next()) {
                idboletatgs = result.getInt("idboletatgs");
                idaccountingentry = result.getInt("idaccountingentry");
                fechapago = result.getString("fechapago");
                fechaimpresion = result.getString("fechaimpresion");
                contribuyente = result.getString("contribuyente");
                seccion = result.getString("seccion");
                manzana = result.getString("manzana");
                parcela = result.getString("parcela");
                zona = result.getString("zona");
                catastro = result.getInt("catastro");
                nrocuenta = result.getString("nrocuenta");
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
                categoria = result.getString("categoria");
                terreno = result.getString("terreno");
                valedificacion = result.getDouble("valedificacion");
                valfiscal = result.getDouble("valfiscal");
                usuario = result.getString("usuario");
                barCode = result.getString("barcode");
                anio = result.getInt("anio");
                numero = result.getString("numero");
                idDescuento = result.getInt("iddescuento");
                nombreDescuento = result.getString("nombredescuento");
		cantAnticipos = result.getInt("cantanticipos");
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

    public void setAnioDesde(int anioDesde) {
        this.anioDesde = anioDesde;
    }

    public int getAnioDesde() {
        return anioDesde;
    }

    public void setAnticipoDesde(int _anticipoDesde) {
        anticipoDesde = _anticipoDesde;
    }

    public int getAnticipoDesde() {
        return anticipoDesde;
    }

    public void setAnioHasta(int _anioHasta) {
        anioHasta = _anioHasta;
    }

    public int getAnioHasta() {
        return anioHasta;
    }

    public void setAnticipoHasta(int _anticipoHasta) {
        anticipoHasta = _anticipoHasta;
    }

    public int getAnticipoHasta() {
        return anticipoHasta;
    }

    public void setIdCatastro(int _idCatastro) {
        idCatastro = _idCatastro;
    }

    public int getIdCatastro() {
        return idCatastro;
    }

    public void setIdDescuento(int _idDescuento) {
        idDescuento = _idDescuento;
    }

    public int getIdDescuento() {
        return idDescuento;
    }

    public void setNombreDescuento(String _nombreDescuento) {
        nombreDescuento = _nombreDescuento;
    }

    public String getNombreDescuento() {
        return nombreDescuento;
    }

    public void setCantAnticipos(int cantAnticipos) {
	this.cantAnticipos = cantAnticipos;
    }

    public int getCantAnticipos() {
	return cantAnticipos;
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
