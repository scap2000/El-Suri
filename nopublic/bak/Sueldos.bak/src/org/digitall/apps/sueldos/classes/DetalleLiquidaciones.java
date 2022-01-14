package org.digitall.apps.sueldos.classes;

import java.sql.ResultSet;

import org.digitall.lib.sql.LibSQL;

public class DetalleLiquidaciones {

    /* 2009-09-17 (santiago) Mensaje para el codificador
     * El atributo "isParcial" debería llamarse "parcial"
     * y sus accesores "isParcial()" y "setParcial(boolean)"
     * El resto de los atributos deberían llamarse (respectivamente)
     * "idDetalleLiquidacion", "idLiquidacion", "idConcepto" y "debeHaber"
     * El campo estado creo que no hace falta ni sus accesores
     * */
    private boolean isParcial = false;
    private int iddetalleliquidacion = -1; 
    private int idliquidacion = -1;
    private int idconcepto = -1;
    private double valor = 0.0;
    private int debehaber = -1;
    private String estado = "";

    public DetalleLiquidaciones() {
    }
    
    public int saveData() {             
	/* 2009-09-17 (santiago) Mensaje para el codificador
	 * No hace falta enviar el campo estado, ni tenerlo en cuenta desde el Stored Proc.
	 * */
	String params = iddetalleliquidacion+","+idliquidacion+","+idconcepto+","+valor+","+ debehaber+",'"+estado+"'";
	int result = -1;
	if (iddetalleliquidacion == -1){
	    /* 2009-09-17 (santiago) Mensaje para el codificador
	     * En estos casos, es conveniente llamar al accesor
	     * por las dudas el valor varie de acuerdo al accesor y no al atributo
	     * Por lo tanto quedaria "if (isIsParcial()) {"
	     * */
	    if(isParcial){
		result = LibSQL.getInt("sueldos.addDetalleLiquidacionParcial",params);                 
	    }else{
		result = LibSQL.getInt("sueldos.addDetalleLiquidacion",params);                
	    }
	    
	} else {            
	    if(isParcial){
		result = LibSQL.getInt("sueldos.setDetalleLiquidacionParcial",params);        
	    }else{
		result = LibSQL.getInt("sueldos.setDetalleLiquidacion",params);                
	    }
	}               
	return result;
    }
    
    public void retrieveData() {
	String params = ""+idliquidacion;
	ResultSet data;
	/* 2009-09-17 (santiago) Mensaje para el codificador
	 * En estos casos, es conveniente llamar al accesor
	 * por las dudas el valor varie de acuerdo al accesor y no al atributo
	 * Por lo tanto quedaria "if (isIsParcial()) {"
	 * */
	if(isParcial){
	     data = LibSQL.exFunction("sueldos.getDetalleLiquidacionParcial", params);
	}else{
	     data = LibSQL.exFunction("sueldos.getDetalleLiquidacion", params);
	}
	
	try {
	    if (data.next()) {
		iddetalleliquidacion  = data.getInt("iddetalleliquidacion");
		idliquidacion = data.getInt("idliquidacion");
		idconcepto = data.getInt("idconcepto");
		valor = data.getInt("valor");
		debehaber  = data.getInt("debehaber");
		estado  = data.getString("estado");
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

    public void setIddetalleliquidacion(int iddetalleliquidacion) {
	this.iddetalleliquidacion = iddetalleliquidacion;
    }

    public int getIddetalleliquidacion() {
	return iddetalleliquidacion;
    }

    public void setIdliquidacion(int idliquidacion) {
	this.idliquidacion = idliquidacion;
    }

    public int getIdliquidacion() {
	return idliquidacion;
    }

    public void setIdconcepto(int idconcepto) {
	this.idconcepto = idconcepto;
    }

    public int getIdconcepto() {
	return idconcepto;
    }

    public void setValor(double valor) {
	this.valor = valor;
    }

    public double getValor() {
	return valor;
    }

    public void setDebehaber(int debehaber) {
	this.debehaber = debehaber;
    }

    public double getDebehaber() {
	return debehaber;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }
}
