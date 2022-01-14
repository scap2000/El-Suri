package org.digitall.apps.taxes092.classes;

import java.util.Vector;
import org.digitall.apps.taxes.classes.PlanDePago;
import org.digitall.lib.sql.LibSQL;

public class PlanesPago {

    private Vector<PlanDePago> planesDePago = new Vector<PlanDePago>();

    public PlanesPago() {
    
    }
    
    public void cargarPlanesDePago(Bien _bien, boolean _revisarEstado){
	//Cargar el vector de planes de pago con todos los planes de pago que posee el bien
	planesDePago.removeAllElements();
	PlanDePago planDePagoAux;
	Vector idsPlanesDePago = new Vector();
	int id = 0;
	if(_bien.esCatastro()){
	    idsPlanesDePago = LibSQL.getVector("taxes.getAllIdsPlanesDePago",""+_bien.getIdBien() + "," + _bien.TIPO_CATASTRO + "," +_revisarEstado);    
	}else{
	    idsPlanesDePago = LibSQL.getVector("taxes.getAllIdsPlanesDePago",""+_bien.getIdBien() + "," + _bien.TIPO_AUTOMOTOR + "," +_revisarEstado);    
	}
	for (int i = 0 ; i < idsPlanesDePago.size() ; i++)  {
	    planDePagoAux = new PlanDePago();
	    id  = Integer.parseInt(idsPlanesDePago.elementAt(i).toString());
	    planDePagoAux.setIdplandepago(id);
	    planDePagoAux.retrieveData();
	    planesDePago.add(planDePagoAux);
	}
    }
    
    public int getCantidadPlanesPago(){
	return -1;
    }
    
    public boolean tienePlanDePagoTgs(){
	boolean tienePlanDePagoTgs = false;
	int i = 0;
	while((i < planesDePago.size())&&(!tienePlanDePagoTgs)) {
	    if(planesDePago.elementAt(i).tienePlanPagoTgs()){
		tienePlanDePagoTgs = true;
	    }else{
		i++;
	    }
	}
	return tienePlanDePagoTgs;
    }
    
    public boolean tienePlanDePagoInmob(){
	boolean tienePlanDePagoInmob = false;
	int i = 0;
	while((i < planesDePago.size())&&(!tienePlanDePagoInmob)) {
	    if(planesDePago.elementAt(i).tienePlanPagoInmob()){
		tienePlanDePagoInmob = true;
	    }else{
		i++;
	    }
	}
	return tienePlanDePagoInmob;
    }
    
    public boolean tienePlanDePagoAutomotor(){
	boolean tienePlanDePagoAutomotor = false;
	int i = 0;
	while((i < planesDePago.size())&&(!tienePlanDePagoAutomotor)) {
	    if(planesDePago.elementAt(i).tienePlanPagoAutomotor()){
		tienePlanDePagoAutomotor = true;
	    }else{
		i++;
	    }
	}
	return tienePlanDePagoAutomotor;
    }
    
    public PlanDePago getPlanDePagoTGS(){
	int i = 0;
	boolean encontrado = false;
	while ((i < planesDePago.size()) && (!encontrado)) {
            if((planesDePago.elementAt(i).esPlanDePagoTGS()) && (planesDePago.elementAt(i).isVigente()) && !LibSQL.getBoolean("taxes.esplandepagocancelado","" + planesDePago.elementAt(i).getIdplandepago())) {
		encontrado = true;
		return planesDePago.elementAt(i);
	    }else{
		i++;
	    }
	}
	return null;
    }
    
    public PlanDePago getPlanDePagoInmob(){
	int i = 0;
	boolean encontrado = false;
	while ((i < planesDePago.size()) && (!encontrado)) {
	    if(planesDePago.elementAt(i).esPlanDePagoInmob() && (planesDePago.elementAt(i).isVigente())  && !LibSQL.getBoolean("taxes.esplandepagocancelado","" + planesDePago.elementAt(i).getIdplandepago())) {
		encontrado = true;
		return planesDePago.elementAt(i);
	    }else{
		i++;
	    }
	}
	return null;
    }
    
    public PlanDePago getPlanDePagoAutomotor(){
	int i = 0;
	boolean encontrado = false;
	while ((i < planesDePago.size()) && (!encontrado)) {
	    if(planesDePago.elementAt(i).esPlanDePagoAutomotor() && (planesDePago.elementAt(i).isVigente())  && !LibSQL.getBoolean("taxes.esplandepagocancelado","" + planesDePago.elementAt(i).getIdplandepago())) {
		encontrado = true;
		return planesDePago.elementAt(i);
	    }else{
		i++;
	    }
	}
	return null;
    }

}
