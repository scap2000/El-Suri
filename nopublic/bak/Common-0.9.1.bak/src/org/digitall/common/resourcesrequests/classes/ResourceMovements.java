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
