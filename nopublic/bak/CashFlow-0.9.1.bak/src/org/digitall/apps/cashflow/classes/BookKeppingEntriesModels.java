package org.digitall.apps.cashflow.classes;

import org.digitall.lib.sql.LibSQL;

public class BookKeppingEntriesModels {

    private int idmodel = -1;
    private String name = "";
    private int idCashMovementType = -1;

    public BookKeppingEntriesModels() {
    }

    public void setIdmodel(int _idmodel) {
        idmodel = _idmodel;
    }

    public int getIdmodel() {
        return idmodel;
    }

    public void setName(String _name) {
        name = _name;
    }

    public String getName() {
        return name;
    }
    
    public int saveData(){
        int resul = -1;
        String params = "'"+ name +"',"+ idCashMovementType;
        if (idmodel == -1)  {
            idmodel = LibSQL.getInt("accounting.addBookKeepingEntryModel",params);
            resul =  idmodel;
        } else  {
            params = idmodel +","+ params;
            idmodel = LibSQL.getInt("accounting.setBookKeepingEntryModel",params);
            resul =  idmodel;
        }
        return resul;
    }
    
    public int loadNewModel(String _name, int _idBookKeepingEntryRef) {
        int result = -1;
        String params = "'"+ _name + "',"+ _idBookKeepingEntryRef;
        idmodel = LibSQL.getInt("accounting.addCompleteModel",params);
        result =  idmodel;
        return result;
    }

    public void setIdCashMovementType(int _idCashMovementType) {
        idCashMovementType = _idCashMovementType;
    }

    public int getIdCashMovementType() {
        return idCashMovementType;
    }
}
