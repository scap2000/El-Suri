package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.sql.LibSQL;

public class Moratoria extends TipoPlanDePago {

    private int idPlanDePago = -1;
    private int idTipoPlanDePago = -1;
    private int idBien = -1;
    private String fechaInicio = "";
    private String fechaFin = "";
    private int cantCuotas = 0;
    private String nombre = "";
    private String categoria = "";
    private int anticipoDesde = 0;
    private int anioDesde = 0;
    private int anticipoHasta = 0;
    private int anioHasta = 0;
    private String estado = "";
    private double montoCuotas = 0.0;
    private double montoTotal = 0.0;
    private boolean contado = false;
    private int maxcuotasVencidas = 0;
    private int diaVencimiento = 15;
    private boolean vigente = false;
    private double montoBase = 0.0;
    private double interes = 0.0;
    private double subtotal = 0.0;
    private double bonificacionMoratoria = 0.0;
    private String nombreBonificacionMoratoria = "";
    private double bonificacion = 0.0;
    private String nombreBonificacion = "";
    private double bonificacionContado = 0.0;
    private int cantAnticipos = 0;
    private int idBonificacion = 0;
    private boolean planDePagoCaido = false;
    private double porcentajeInteresFinanciacion = 0.0;
    private double montoInteresFinanciacion = 0.0;
    private double porcentajeCondonacionIntereses = 0.0;
    private double montoCondonacionIntereses = 0.0;
    
    private double montoPrimerCuota = 0.0;
    private double montoCuotasRestantes = 0.0;
    
    private double entrega = 0.0;
    
    public Moratoria() {
    }
    
    public Moratoria(int _idBien, int _idTipoPlanDePago) {
	/* 2009-09-24 (santiago) Mensaje para el codificador
	 * Constructor con atributos que NO SON CLAVE PRIMARIA
	 * */    
	idBien = _idBien;
	idTipoPlanDePago = _idTipoPlanDePago;
    }

    public void setIdPlanDePago(int idplandepago) {
	this.idPlanDePago = idplandepago;
    }

    public int getIdPlanDePago() {
	return idPlanDePago;
    }

    public void setIdTipoPlanDePago(int idtipoplandepago) {
	this.idTipoPlanDePago = idtipoplandepago;
    }

    public int getIdTipoPlanDePago() {
	return idTipoPlanDePago;
    }

    public void setIdBien(int idbien) {
	this.idBien = idbien;
    }

    public int getIdBien() {
	return idBien;
    }

    public void setFechaInicio(String fechainicio) {
	this.fechaInicio = fechainicio;
    }

    public String getFechaInicio() {
	return fechaInicio;
    }

    public void setFechaFin(String fechafin) {
	this.fechaFin = fechafin;
    }

    public String getFechaFin() {
	return fechaFin;
    }

    public void setCantCuotas(int cantcuotas) {
	this.cantCuotas = cantcuotas;
    }

    public int getCantCuotas() {
	return cantCuotas;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getNombre() {
	return nombre;
    }

    public void setCategoria(String categoria) {
	this.categoria = categoria;
    }

    public String getCategoria() {
	return categoria;
    }

    public void setAnticipoDesde(int anticipodesde) {
	this.anticipoDesde = anticipodesde;
    }

    public int getAnticipoDesde() {
	return anticipoDesde;
    }

    public void setAnioDesde(int aniodesde) {
	this.anioDesde = aniodesde;
    }

    public int getAnioDesde() {
	return anioDesde;
    }

    public void setAnticipoHasta(int anticipohasta) {
	this.anticipoHasta = anticipohasta;
    }

    public int getAnticipoHasta() {
	return anticipoHasta;
    }

    public void setAnioHasta(int aniohasta) {
	this.anioHasta = aniohasta;
    }

    public int getAnioHasta() {
	return anioHasta;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }

    public void setMontoTotal(double montototal) {
	this.montoTotal = montototal;
    }

    public double getMontoTotal() {
	return montoTotal;
    }

    public void setContado(boolean contado) {
	this.contado = contado;
    }

    public boolean isContado() {
	return contado;
    }

    public void setMaxcuotasVencidas(int maxcuotasvencidas) {
	this.maxcuotasVencidas = maxcuotasvencidas;
    }

    public int getMaxcuotasVencidas() {
	return maxcuotasVencidas;
    }
    
    public int saveData() {
	int result = -1;
	String params = idTipoPlanDePago +","+ idBien +",'" + nombre + "'," + cantCuotas +",'"+ categoria +"',"+
			anticipoDesde +","+ anioDesde +","+ anticipoHasta +","+	anioHasta +","+ montoTotal +","+ 
			contado +","+ diaVencimiento +","+ montoCuotas +","+ montoBase +","+
			interes +","+ subtotal +","+ bonificacionMoratoria +",'"+nombreBonificacionMoratoria +"',"+ bonificacion +",'"+
			nombreBonificacion +"',"+ bonificacionContado +","+ cantAnticipos +","+ idBonificacion +","+ 
			porcentajeInteresFinanciacion +","+ montoInteresFinanciacion +","+ porcentajeCondonacionIntereses +","+ montoCondonacionIntereses;
	if (getIdPlanDePago() == -1) {
	    result = LibSQL.getInt("taxes.addPlanDePago", params);
	    setIdPlanDePago(result);
	}
	return result;
    }
    
    public int saveData2() {
	int result = -1;
	String params = idTipoPlanDePago +","+ idBien +",'" + nombre + "'," + cantCuotas +",'"+ categoria +"',"+
			anticipoDesde +","+ anioDesde +","+ anticipoHasta +","+ anioHasta +","+ montoTotal +","+ 
			contado +","+ diaVencimiento +","+ montoCuotas +","+ montoBase +","+
			interes +","+ subtotal +","+ bonificacionMoratoria +",'"+nombreBonificacionMoratoria +"',"+ bonificacion +",'"+
			nombreBonificacion +"',"+ bonificacionContado +","+ cantAnticipos +","+ idBonificacion +","+ 
			(porcentajeInteresFinanciacion / 100) +","+ montoInteresFinanciacion +","+ porcentajeCondonacionIntereses +","+ montoCondonacionIntereses + "," + montoPrimerCuota+ "," + montoCuotasRestantes + "," + entrega;
	if (getIdPlanDePago() == -1) {
	    result = LibSQL.getInt("taxes.addPlanDePago2", params);
	    setIdPlanDePago(result);
	}
	return result;
    }

    public void setDiaVencimiento(int diaVencimiento) {
	this.diaVencimiento = diaVencimiento;
    }

    public int getDiaVencimiento() {
	return diaVencimiento;
    }

    public void setMontoCuotas(double montoCuotas) {
	this.montoCuotas = montoCuotas;
    }

    public double getMontoCuotas() {
	return montoCuotas;
    }

    public void setVigente(boolean vigente) {
	this.vigente = vigente;
    }

    public boolean isVigente() {
	return vigente;
    }
    
    public void setMontoBase(double montoBase) {
	this.montoBase = montoBase;
    }

    public double getMontoBase() {
	return montoBase;
    }

    public void setInteres(double interes) {
	this.interes = interes;
    }

    public double getInteres() {
	return interes;
    }

    public void setSubtotal(double subtotal) {
	this.subtotal = subtotal;
    }

    public double getSubtotal() {
	return subtotal;
    }

    public void setBonificacionMoratoria(double bonificacionMoratoria) {
	this.bonificacionMoratoria = bonificacionMoratoria;
    }

    public double getBonificacionMoratoria() {
	return bonificacionMoratoria;
    }

    public void setNombreBonificacionMoratoria(String nombreBonificacionMoratoria) {
	this.nombreBonificacionMoratoria = nombreBonificacionMoratoria;
    }

    public String getNombreBonificacionMoratoria() {
	return nombreBonificacionMoratoria;
    }

    public void setBonificacion(double bonificacion) {
	this.bonificacion = bonificacion;
    }

    public double getBonificacion() {
	return bonificacion;
    }

    public void setNombreBonificacion(String nombreBonificacion) {
	this.nombreBonificacion = nombreBonificacion;
    }

    public String getNombreBonificacion() {
	return nombreBonificacion;
    }
    
    public void setCantAnticipos(int cantAnticipos) {
	this.cantAnticipos = cantAnticipos;
    }

    public int getCantAnticipos() {
	return cantAnticipos;
    }
    
    public void retrieveData() {
	/* 2009-09-24 (santiago) Mensaje para el codificador
	 * No se debe hacer un retrieveData con otros par??metros
	 * que NO SEAN CLAVE PRIMARIA
	 * */    
	ResultSet result = LibSQL.exFunction("taxes.getPlanDePago", idTipoPlanDePago +","+ idBien);
	try {
	    if (result.next()) {
	        idPlanDePago = result.getInt("idplandepago");
		idTipoPlanDePago = result.getInt("idtipoplandepago");
		idBien = result.getInt("idbien");
		fechaInicio  = result.getString("fechainicio");
		fechaFin = result.getString("fechafin");
		cantCuotas = result.getInt("cantcuotas");
		nombre = result.getString("nombre");
		categoria = result.getString("categoria");
		anticipoDesde = result.getInt("anticipodesde");
		anioDesde = result.getInt("aniodesde");
		anticipoHasta = result.getInt("anticipohasta");
		anioHasta = result.getInt("aniohasta");
		montoTotal = result.getDouble("montototal");
		contado = result.getBoolean("contado");
		maxcuotasVencidas = result.getInt("maxcuotasvencidas");
		diaVencimiento = result.getInt("diavencimiento");
		vigente = result.getBoolean("vigente");
	        montoBase = result.getDouble("montobase");
		interes = result.getDouble("interes");
		subtotal = result.getDouble("subtotal");
		bonificacionMoratoria = result.getDouble("bonificacionmoratoria");
		nombreBonificacionMoratoria = result.getString("nombrebonificacionmoratoria");
		bonificacion = result.getDouble("bonificacion");
		nombreBonificacion = result.getString("nombrebonificacion");
	        cantAnticipos = result.getInt("cantanticipos");
	        idBonificacion = result.getInt("idbonificacion");
		planDePagoCaido = result.getBoolean("plandepagocaido");
	        porcentajeInteresFinanciacion = result.getDouble("porcentajeintfin");
	        montoInteresFinanciacion = result.getDouble("montointeresfinanciacion");
		porcentajeCondonacionIntereses = result.getDouble("porcentajecondonacionintereses");
	        montoCondonacionIntereses = result.getDouble("montoCondonacionIntereses");
	        entrega = result.getDouble("entrega");
	    } 
	} catch (SQLException e) {
	    // TODO
	     /* 2009-09-24 (santiago) Mensaje para el codificador
	      * O se pone el error o se pone e.printStackTrace,
	      * pero no un mensaje que no tiene significado ni valor
	      * */    
	    System.out.println("error");
	} catch (NullPointerException e) {
	    // TODO
	     /* 2009-09-24 (santiago) Mensaje para el codificador
	      * Idem Excepci??n anterior
	      * */    
	}
    }

  /* 2009-09-24 (santiago) Mensaje para el codificador
   * Se tuvo que crear este m??todo
   * para no alterar el anterior
   * */    
    public void retrieveDataByPrimaryKey() {
	ResultSet result = LibSQL.exFunction("taxes.getPlanDePago", idPlanDePago);
	try {
	    if (result.next()) {
		idPlanDePago = result.getInt("idplandepago");
		idTipoPlanDePago = result.getInt("idtipoplandepago");
		idBien = result.getInt("idbien");
		fechaInicio  = result.getString("fechainicio");
		fechaFin = result.getString("fechafin");
		cantCuotas = result.getInt("cantcuotas");
		nombre = result.getString("nombre");
		categoria = result.getString("categoria");
		anticipoDesde = result.getInt("anticipodesde");
		anioDesde = result.getInt("aniodesde");
		anticipoHasta = result.getInt("anticipohasta");
		anioHasta = result.getInt("aniohasta");
		montoTotal = result.getDouble("montototal");
		contado = result.getBoolean("contado");
		maxcuotasVencidas = result.getInt("maxcuotasvencidas");
		diaVencimiento = result.getInt("diavencimiento");
		vigente = result.getBoolean("vigente");
		montoBase = result.getDouble("montobase");
		interes = result.getDouble("interes");
		subtotal = result.getDouble("subtotal");
		bonificacionMoratoria = result.getDouble("bonificacionmoratoria");
		nombreBonificacionMoratoria = result.getString("nombrebonificacionmoratoria");
		bonificacion = result.getDouble("bonificacion");
		nombreBonificacion = result.getString("nombrebonificacion");
		cantAnticipos = result.getInt("cantanticipos");
		idBonificacion = result.getInt("idbonificacion");
		planDePagoCaido = result.getBoolean("plandepagocaido");
		porcentajeInteresFinanciacion = result.getDouble("porcentajeintfin");
		montoInteresFinanciacion = result.getDouble("montointeresfinanciacion");
	        entrega = result.getDouble("entrega");
	    } 
	} catch (SQLException e) {
	    Advisor.printException(e);
	}
    }

    public void setBonificacionContado(double bonificacionContado) {
	this.bonificacionContado = bonificacionContado;
    }

    public double getBonificacionContado() { 
	return bonificacionContado;
    }

    public void setIdBonificacion(int idBonificacion) {
	this.idBonificacion = idBonificacion;
    }

    public int getIdBonificacion() {
	return idBonificacion;
    }

    public void setPlanDePagoCaido(boolean planDePagoCaido) {
	this.planDePagoCaido = planDePagoCaido;
    }

    public boolean isPlanDePagoCaido() {
	return planDePagoCaido;
    }
    
	 /* 2009-09-24 (santiago) Mensaje para el codificador
	  * M??todo que parece que no se usa
	  * */
    public void retrieveDataByIdBien() {
	ResultSet result = LibSQL.exFunction("taxes.getPlanDePagoByIdBien", idTipoPlanDePago +","+ idBien);
	try {
	    if (result.next()) {
		//idPlanDePago = result.getInt("idplandepago");
	    } 
	} catch (SQLException e) {
	    // TODO
	    System.out.println("error");
	} catch (NullPointerException e) {
	    // TODO
	}
    }

    public void setPorcentajeInteresFinanciacion(double porcentajeInteresFinanciacion) {
	this.porcentajeInteresFinanciacion = porcentajeInteresFinanciacion;
    }

    public double getPorcentajeInteresFinanciacion() {
	return porcentajeInteresFinanciacion;
    }

    public void setMontoInteresFinanciacion(double montoInteresFinanciacion) {
	this.montoInteresFinanciacion = montoInteresFinanciacion;
    }

    public double getMontoInteresFinanciacion() {
	return montoInteresFinanciacion;
    }

    public void setMontoCondonacionIntereses(double montoCondonacionIntereses) {
	this.montoCondonacionIntereses = montoCondonacionIntereses;
    }

    public double getMontoCondonacionIntereses() {
	return montoCondonacionIntereses;
    }
    
    public void setPorcentajeCondonacionIntereses(double _porcentajeCondonacionIntereses) {
	this.porcentajeCondonacionIntereses = _porcentajeCondonacionIntereses;
    }

    public double getPorcentajeCondonacionIntereses() {
	return porcentajeCondonacionIntereses;
    }

    public void setMontoPrimerCuota(double montoPrimerCuota) {
	this.montoPrimerCuota = montoPrimerCuota;
    }

    public double getMontoPrimerCuota() {
	return montoPrimerCuota;
    }

    public void setMontoCuotasRestantes(double montoCuotasRestantes) {
	this.montoCuotasRestantes = montoCuotasRestantes;
    }

    public double getMontoCuotasRestantes() {
	return montoCuotasRestantes;
    }

    public void setEntrega(double entrega) {
        this.entrega = entrega;
    }

    public double getEntrega() {
        return entrega;
    }
}
