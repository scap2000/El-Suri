package org.digitall.apps.sueldos.classes;

import java.sql.ResultSet;

import org.digitall.lib.sql.LibSQL;

    /* 2009-09-11 (santiago) Mensaje para el codificador
     * El nombre de la clase debe estar en SINGULAR
     * Ejemplo: ConceptoAportes, tal cual el Stored Proc. que lo carga
     * Se ha visto propagado este error en el proyecto Sueldos.jpr
     * */
public class ConceptosAportes {

    /* 2009-09-11 (santiago) Mensaje para el codificador
     * REVISAR LA HOMOGENEIDAD EN EL IDIOMA, Los atributos tienen nombres en ambos idiomas
     * Se ha visto propagado este error en el proyecto Sueldos.jpr
     * */

    private int idconceptoreferencia = -1; //id tabla que sirve como referencia a la tabla conceptos
    private String name = "";  //Descripcion del concepto
    private double value = 0; //Importe del concepto
    private double percentage = 0; //Porcentaje del concepto
    private int debehaber = -1; //1: Indica si es haber; -1: Indica si es debe
    private String estado = ""; //Estado
    private int orden = -1; //Orden que tiene en el listado de Haberes o Descuentos segun corresponda
    private boolean issetforlegajo = false; // Indica si el concepto se carga por legajo o es generico para todos
    private boolean setgeneral = false; // Indica si el concepto se asigna a todos los legajos en general
    private int idaccount = -1; //Id de la cuenta del plan del cuenta al que pertenece

    public ConceptosAportes() {
    }
    
    public int saveData() {             
	String params = idconceptoreferencia+",'"+name+"',"+value+","+percentage+","+ debehaber+",'"+estado+"',"+orden+","+issetforlegajo+","+setgeneral + "," + idaccount;
	int result = -1;
	if (idconceptoreferencia == -1){
	    result = LibSQL.getInt("sueldos.addConceptoAportes",params);              
	} else {            
	    result = LibSQL.getInt("sueldos.setConceptoAportes",params);
	}               
	return result;
    }
    
    public void retrieveData() {
	String params = ""+idconceptoreferencia;
	ResultSet data = LibSQL.exFunction("sueldos.getConceptoAportes", params);
	try {
	    if (data.next()) {
		idconceptoreferencia  = data.getInt("idconceptoreferencia");
		name = data.getString("name");
		value = data.getDouble("value");
	        percentage  = data.getDouble("percentage");
		debehaber = data.getInt("debehaber");
		estado  = data.getString("estado");
		orden = data.getInt("orden");
		issetforlegajo = data.getBoolean("issetforlegajo");
	        setgeneral = data.getBoolean("setgeneral");
	        idaccount = data.getInt("idaccount");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public void setIdconceptoreferencia(int idconceptoreferencia) {
	this.idconceptoreferencia = idconceptoreferencia;
    }

    public int getIdconceptoreferencia() {
	return idconceptoreferencia;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setValue(double value) {
	this.value = value;
    }

    public double getValue() {
	return value;
    }

    public void setPercentage(double percentage) {
	this.percentage = percentage;
    }

    public double getPercentage() {
	return percentage;
    }

    public void setDebehaber(int debehaber) {
	this.debehaber = debehaber;
    }

    public int getDebehaber() {
	return debehaber;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }

    public void setOrden(int orden) {
	this.orden = orden;
    }

    public int getOrden() {
	return orden;
    }

    public void setIssetforlegajo(boolean issetforlegajo) {
	this.issetforlegajo = issetforlegajo;
    }

    public boolean isIssetforlegajo() {
	return issetforlegajo;
    }

    public void setSetgeneral(boolean setgeneral) {
	this.setgeneral = setgeneral;
    }

    public boolean isSetgeneral() {
	return setgeneral;
    }

    public void setIdaccount(int idaccount) {
        this.idaccount = idaccount;
    }

    public int getIdaccount() {
        return idaccount;
    }
}
