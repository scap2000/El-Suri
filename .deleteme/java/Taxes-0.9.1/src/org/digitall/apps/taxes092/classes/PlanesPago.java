/**
 LIMITACIÓN DE RESPONSABILIDAD - COPYRIGHT - [Español]
 ================================================================================
 El Suri - Entorno JAVA de Trabajo y Desarrollo para Gobierno Electrónico
 ================================================================================

 Información del Proyecto:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 por D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO es propiedad de
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) y
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los términos de la Licencia Pública General GNU publicada
 por la Fundación para el Software Libre, ya sea la versión 3
 de la Licencia, o (a su elección) cualquier versión posterior.

 Este programa se distribuye con la esperanza de que sea útil, pero
 SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita
 MERCANTIL o de APTITUD PARA UN PROPÓSITO DETERMINADO.
 Consulte los detalles de la Licencia Pública General GNU para obtener
 una información más detallada.

 Debería haber recibido una copia de la Licencia Pública General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
 El Suri - JAVA Management & Development Environment for Electronic Government
 =====================================================================================

 Project Info:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 by D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO is owned by
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) and
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/
/**
 * PlanesPago.java
 *
 * */
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
