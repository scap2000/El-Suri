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
 * ResourceMovements.java
 *
 * */
package org.digitall.common.resourcesrequests.classes;

import org.digitall.lib.sql.LibSQL;

public class ResourceMovements {

    private int idmovement = -1;
    private int idmovementtype  = -1;
    private String date = "";
    private String dispatcher = "";
    private String recipient = "";
    private String delivery = "";
    private String receiver = "";
    private String estado = "";
    private int iddepot = -1;
    private String observations = "";
    //-------------------------------
    private String movementtype = "";
    private String depot = "";
    private int idVoucherRef = -1;

    public ResourceMovements() {

    }

    public void setIdmovement(int _idmovement) {
	idmovement = _idmovement;
    }

    public int getIdmovement() {
	return idmovement;
    }

    public void setIdmovementtype(int _idmovementtype) {
	idmovementtype = _idmovementtype;
    }

    public int getIdmovementtype() {
	return idmovementtype;
    }

    public void setDate(String _date) {
	date = _date;
    }

    public String getDate() {
	return date;
    }

    public void setDispatcher(String _dispatcher) {
	dispatcher = _dispatcher;
    }

    public String getDispatcher() {
	return dispatcher;
    }

    public void setRecipient(String _recipient) {
	recipient = _recipient;
    }

    public String getRecipient() {
	return recipient;
    }

    public void setDelivery(String _delivery) {
	delivery = _delivery;
    }

    public String getDelivery() {
	return delivery;
    }

    public void setReceiver(String _receiver) {
	receiver = _receiver;
    }

    public String getReceiver() {
	return receiver;
    }

    public void setEstado(String _estado) {
	estado = _estado;
    }

    public String getEstado() {
	return estado;
    }

    public void setIddepot(int _iddepot) {
	iddepot = _iddepot;
    }

    public int getIddepot() {
	return iddepot;
    }

    public void setMovementtype(String _movementtype) {
	movementtype = _movementtype;
    }

    public String getMovementtype() {
	return movementtype;
    }

    public void setDepot(String _depot) {
	depot = _depot;
    }

    public String getDepot() {
	return depot;
    }
    
    public void setObservations(String _observations) {
	observations = _observations;
    }

    public String getObservations() {
	return observations;
    }
    
    //METHODS
    
     public int saveData() {
	 int result = -1;
	 String params = "";
	 if ( idmovement == -1 )  {        //--> Insert
	     params = idmovementtype + ",'" 
		     + date + "','" 
		     + dispatcher + "','" 
		     + recipient + "','" 
		     + delivery + "','" 
		     + receiver + "'," 
		     + iddepot + ",'"  
		     + observations + "'" ;
	     //System.out.println("SELECT resourcescontrol.addResourcesMovements(" + params + ")");
	     result = LibSQL.getInt("resourcescontrol.addResourcesMovements", params);
	     idmovement = result;
	 } else if (idmovement != -1) { //--> Update
	    params = idmovement + "," 
		     + idmovementtype + ",'" 
		     + date + "','" 
		     + dispatcher + "','" 
		     + recipient + "','" 
		     + delivery + "','" 
		     + receiver + "'," 
		     + iddepot + ",'"  
		    + observations + "'" ;
	     result = LibSQL.getInt("resourcescontrol.setResourcesMovements", params);
	 }
	 return result;
     }


    public void setIdVoucherRef(int idVoucherRef) {
        this.idVoucherRef = idVoucherRef;
    }

    public int getIdVoucherRef() {
        return idVoucherRef;
    }
    
    public void retrieveIdVoucherRef() {
        idVoucherRef = LibSQL.getInt("resourcescontrol.getIdVoucherByResourcesMovement", idmovement);
    }
    
    public boolean notificar() {
        boolean _return = false;
        retrieveIdVoucherRef();
        if (idVoucherRef > 0) {
            _return = LibSQL.getBoolean("resourcescontrol.setResourcesRequestStatus", "-1, 11, "+ idVoucherRef);
        }
        return _return;
    }
    
}
