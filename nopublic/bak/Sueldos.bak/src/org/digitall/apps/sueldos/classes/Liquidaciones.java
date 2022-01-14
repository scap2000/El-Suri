package org.digitall.apps.sueldos.classes;

import java.sql.ResultSet;

import java.util.Vector;

import org.digitall.lib.sql.LibSQL;

public class Liquidaciones {

    /* 2009-09-18 (santiago) Mensaje para el codificador
     * isParcial --> parcial; y sus accesores isParcial() y setParcial(boolean)
     * */
    private boolean isParcial = false;

    /* 2009-09-18 (santiago) Mensaje para el codificador
     * idliquidacion --> idLiquidacion
     * idlegajo --> idLegajo
     * estado no hace falta
     * fechaliquidacion --> fechaLiquidacion
     * */
    
    private int idliquidacion = -1;
    private int idlegajo = -1; //idlegajo de al cual se le hace la liquidacion
    private int anio = -1; //año de la liquidacion
    private int mes = -1; //mes de la liquidacion
    private double total = 0.0; //total neto a cobrar por la liquidacion
    private String estado = ""; // *, borrado
    private double importeHaber = 0.0; //importe total de Haberes
    private double importeDebe = 0.0;  // importe total de Descuentos
    private String fechaliquidacion = "";//fecha en la que se hace efectiva la liquidacion
    
     /* 2009-09-18 (santiago) Mensaje para el codificador
      * idconceto --> idconcepto
      * Es preferible que los vectores sean homogéneos
      * es decir, que tengan atributos del mismo tipo primitivo o bien
      * de la misma clase. Se podría haber creado una clase
      * con tres atributos (int idConcepto, ? cantidad y double valor)
      * y los vectores contener elementos de esa clase para no hacer
      * detalle.setIdconcepto(Integer.parseInt(item.elementAt(0).toString()));
      * detalle.setValor(Double.parseDouble(item.elementAt(2).toString()));
      * sino (fijarse en el método public int saveData(){})
      * detalle.setIdconcepto(detalleHaberes.elementAt(i).getIdConcepto());
      * detalle.setValor(detalleHaberes.elementAt(i).getValor());
      * */
    private Vector detalleHaberes = new Vector(); // idconceto,cantidad,valor
    private Vector detalleDescuentos = new Vector(); // idconceto,cantidad,valor
    
    public Liquidaciones() {
    }
    
    public int saveData() {             
	String params = idliquidacion+","+idlegajo+","+anio+","+mes+","+ total+",'"+estado+"',"+importeHaber+","+importeDebe + ",'"+fechaliquidacion+"'";
	int result = -1;
	if (idliquidacion == -1){
	    if(isParcial){
		result = LibSQL.getInt("sueldos.addLiquidacionParcial",params);
		int idLiq = result;
		if(result != -1){
		    int error = 1;
		    for(int i = 0; i < detalleHaberes.size(); i++){
			Vector item = (Vector)detalleHaberes.elementAt(i);
			DetalleLiquidaciones detalle = new DetalleLiquidaciones();
			detalle.setIdliquidacion(result);
			detalle.setIsParcial(true);
			detalle.setDebehaber(1);
			detalle.setIdconcepto(Integer.parseInt(item.elementAt(0).toString()));
			detalle.setValor(Double.parseDouble(item.elementAt(2).toString()));
			error = error + detalle.saveData();
		    }
		    for(int i = 0; i < detalleDescuentos.size(); i++){
			Vector item = (Vector)detalleDescuentos.elementAt(i);
			DetalleLiquidaciones detalle = new DetalleLiquidaciones();
			detalle.setIdliquidacion(result);
			detalle.setIsParcial(true);
			detalle.setDebehaber(-1);
			detalle.setIdconcepto(Integer.parseInt(item.elementAt(0).toString()));
			detalle.setValor(Double.parseDouble(item.elementAt(2).toString()));
		        error = error + detalle.saveData();
		    }
		    if(error != -1){
		        result = LibSQL.getInt("sueldos.regulateConceptForLegajo","" + idLiq);
		    }
		}
	    }else{
	        result = LibSQL.getInt("sueldos.addLiquidacion",params);                
	    }
	    
	} else {            
	    if(isParcial){
	        result = LibSQL.getInt("sueldos.setLiquidacionParcial",params);        
	    }else{
	        result = LibSQL.getInt("sueldos.setLiquidacion",params);                
	    }
	}               
	return result;
    }
    
    public void retrieveData() {
	String params = ""+idliquidacion;
	ResultSet data;
	if(isParcial){
	     data = LibSQL.exFunction("sueldos.getLiquidacionParcial", params);
	}else{
	    data = LibSQL.exFunction("sueldos.getLiquidacion", params);
	}
	
	try {
	    if (data.next()) {
		idliquidacion  = data.getInt("idliquidacion");
		idlegajo = data.getInt("idlegajo");
		anio = data.getInt("anio");
		mes = data.getInt("mes");
		total  = data.getDouble("total");
		estado  = data.getString("estado");
		importeHaber = data.getDouble("importehaber");
	        importeDebe = data.getDouble("importedebe");
		fechaliquidacion = data.getString("fechaliquidacion");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public void setIsParcial(boolean isParcial) {
	this.isParcial = isParcial;
    }

    public boolean isIsParcial() {
	return isParcial;
    }

    public void setIdliquidacion(int idliquidacion) {
	this.idliquidacion = idliquidacion;
    }

    public int getIdliquidacion() {
	return idliquidacion;
    }

    public void setIdlegajo(int idlegajo) {
	this.idlegajo = idlegajo;
    }

    public int getIdlegajo() {
	return idlegajo;
    }

    public void setAnio(int anio) {
	this.anio = anio;
    }

    public int getAnio() {
	return anio;
    }

    public void setMes(int mes) {
	this.mes = mes;
    }

    public int getMes() {
	return mes;
    }

    public void setTotal(double total) {
	this.total = total;
    }

    public double getTotal() {
	return total;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }

    public void setImporteHaber(double importeHaber) {
	this.importeHaber = importeHaber;
    }

    public double getImporteHaber() {
	return importeHaber;
    }

    public void setImporteDebe(double importeDebe) {
	this.importeDebe = importeDebe;
    }

    public double getImporteDebe() {
	return importeDebe;
    }

    public void setFechaliquidacion(String fechaliquidacion) {
	this.fechaliquidacion = fechaliquidacion;
    }

    public String getFechaliquidacion() {
	return fechaliquidacion;
    }

    public void setDetalleHaberes(Vector detalleHaberes) {
	this.detalleHaberes = detalleHaberes;
    }

    public Vector getDetalleHaberes() {
	return detalleHaberes;
    }

    public void setDetalleDescuentos(Vector detalleDescuentos) {
	this.detalleDescuentos = detalleDescuentos;
    }

    public Vector getDetalleDescuentos() {
	return detalleDescuentos;
    }
}
