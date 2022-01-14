package org.digitall.common.cashflow.classes;

import java.sql.ResultSet;

import org.digitall.lib.sql.LibSQL;

public class CashMovementType {

    private int idCashMovementType = -1;
    private String Name;
    private int idParent;
    private int idCashMovementTypeRef;
    private int Sign;
    private String Description;
			
    public CashMovementType() {

    }
    
    public CashMovementType(int _idCashMovementType) {
	idCashMovementType = _idCashMovementType;
    }

    public int saveData(){
	String params = idCashMovementType +",'"+ Name +"',"+ idParent +","+ idCashMovementTypeRef +","+ Sign +",'"+ Description +"'";
	int result = LibSQL.getInt("cashflow.saveCashMovementType", params);
	if (result > 0){
	    idCashMovementType = result;
	}
	return result;
    }
    
    public void retrieveData() {
        ResultSet data = LibSQL.exFunction("cashflow.getCashMovementTypes","" + idCashMovementType);
        try  {
            if (data.next())  {
                Name = data.getString("name");
                idParent = data.getInt("idparent");
                idCashMovementTypeRef = data.getInt("idcashmovementtyperef");
                Sign = data.getInt("sign");
                Description = data.getString("description");
            }
               
        } catch (Exception ex)  {
            ex.printStackTrace();
        }
    }

    public void setIdCashMovementType(int idCashMovementType) {
	this.idCashMovementType = idCashMovementType;
    }

    public int getIdCashMovementType() {
	return idCashMovementType;
    }

    public void setName(String name) {
	this.Name = name;
    }

    public String getName() {
	return Name;
    }

    public void setIdParent(int idParent) {
	this.idParent = idParent;
    }

    public int getIdParent() {
	return idParent;
    }

    public void setIdCashMovementTypeRef(int idCashMovementTypeRef) {
	this.idCashMovementTypeRef = idCashMovementTypeRef;
    }

    public int getIdCashMovementTypeRef() {
	return idCashMovementTypeRef;
    }

    public void setSign(int sign) {
	this.Sign = sign;
    }

    public int getSign() {
	return Sign;
    }

    public void setDescription(String description) {
	this.Description = description;
    }

    public String getDescription() {
	return Description;
    }

}
