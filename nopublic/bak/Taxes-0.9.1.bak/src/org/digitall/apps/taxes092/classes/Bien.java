package org.digitall.apps.taxes092.classes;

import org.digitall.apps.taxes.classes.Cadastral;
import org.digitall.apps.taxes.classes.Car;
import org.digitall.apps.taxes.classes.PlanDePago;
import org.digitall.lib.sql.LibSQL;

public class Bien {

    public static int TIPO_CATASTRO = 1;  //1: indica que el bien es de tipo catastro
    public static int TIPO_AUTOMOTOR = 2; //0: indica que el bien es de tipo automotor
    
    private Cadastral catastro = null;
    private Car automotor = null;
    private PlanesPago planesPago = new PlanesPago();
    private int tipoBien = -1;
    private int idBien = -1;

    public Bien() {
	
    }
    
    public void cargarse(int _idBien, int _tipoBien, boolean _revisarEstado){
	tipoBien = _tipoBien;
	idBien = _idBien;
	if( _tipoBien == 1) {
	    catastro = new Cadastral();
	    catastro.setIdCatastro(_idBien);
	    catastro.retrieveData();
	    catastro.retrieveApoderadoData();
	} else {
	    automotor = new Car();
	    automotor.setIdAutomotor(_idBien);
	    automotor.retrieveIdDominio();
	    automotor.retrieveData();
	}
	planesPago.cargarPlanesDePago(this,_revisarEstado);
    }
    
    public void recargarse(){
	cargarse(idBien,tipoBien,false);
    }
    
    public boolean esCatastro() {
	/*boolean resultado = false;
	if( tipoBien == TIPO_CATASTRO) {
	    resultado = true;
	} 
	return resultado;*/
	return tipoBien == TIPO_CATASTRO;
    }
    
    public boolean esAutomotor() {
	/*boolean resultado = false;
	if( tipoBien == TIPO_AUTOMOTOR) {
	    resultado = true;
	} 
	return resultado;*/
	return tipoBien == TIPO_AUTOMOTOR;
    }
    
    public int getIdBien (){
	int resultado = -1;
	if( tipoBien == 1) { // Es Catastro
	    resultado = catastro.getIdCatastro();
	} else { // Es Automotor
	 resultado = automotor.getIdAutomotor();
	}
	return resultado;
    }
    
    public boolean adeudaAnticipos(int _tipoDeImpuesto){
	return LibSQL.getBoolean("taxes.adeudaAnticipos","" + getIdBien()+"," + _tipoDeImpuesto);
    }
    
    public boolean tienePlanDePagoActivo(int _tipoDeImpuesto){
	boolean resultado = false;
	if(_tipoDeImpuesto == 1){
	    resultado = tienePlanDePagoTgs();
	}else{
	    if(_tipoDeImpuesto == 2){
	        resultado = tienePlanDePagoInmob();
	    }else{
	        resultado = tienePlanDePagoAutomotor();
	    }
	}
	return resultado;
    }

    public PlanesPago getPlanesPago() {
	return planesPago;
    }
    
    public boolean tienePlanDePago(){
	return (planesPago.getCantidadPlanesPago() > 0);
    }
    
    public boolean tienePlanDePagoTgs(){
	return (planesPago.tienePlanDePagoTgs());
    }
    
    public boolean tienePlanDePagoInmob(){
	return (planesPago.tienePlanDePagoInmob());
    }
    
    public boolean tienePlanDePagoAutomotor(){
	return (planesPago.tienePlanDePagoAutomotor());
    }
    
    public PlanDePago getPlanPagoTGS() {
	return planesPago.getPlanDePagoTGS();
    }
    
    public PlanDePago getPlanPagoInmob() {
	return planesPago.getPlanDePagoInmob();
    }
    
    public PlanDePago getPlanPagoAutomotor() {
	return planesPago.getPlanDePagoAutomotor();
    }
    
    public boolean puedeHacerPagoContado(Impuesto _impuesto){
	return LibSQL.getBoolean("taxes.puedeHacerPagoContado","" +getIdBien() + "," + _impuesto.getTipoImpuesto().getIdTipoImpuesto());
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

    public void setTipoBien(int tipoBien) {
	this.tipoBien = tipoBien;
    }

    public int getTipoBien() {
	return tipoBien;
    }
    
    public String getApoderado(){
        String resultado = "";
        if( tipoBien == 1) { // Es Catastro
            resultado = catastro.getApoderadoLastName() + ", " + catastro.getApoderadoName() ;
        } else { // Es Automotor
            resultado = automotor.getTitular();
        }
        return resultado;
    }
}
