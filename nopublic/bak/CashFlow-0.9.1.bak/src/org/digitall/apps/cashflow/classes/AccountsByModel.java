package org.digitall.apps.cashflow.classes;

import org.digitall.lib.sql.LibSQL;

public class AccountsByModel {

    private int idModelByAccount = -1;
    private int idModel = -1;
    private int idaccount = -1;
    
    public AccountsByModel() {
    }

    public void setIdModelByAccount(int idModelByAccount) {
        this.idModelByAccount = idModelByAccount;
    }

    public int getIdModelByAccount() {
        return idModelByAccount;
    }

    public void setIdModel(int idModel) {
        this.idModel = idModel;
    }

    public int getIdModel() {
        return idModel;
    }

    public void setIdaccount(int idaccount) {
        this.idaccount = idaccount;
    }

    public int getIdaccount() {
        return idaccount;
    }
    
    public int saveData(){
        String params = "";
        if (idModelByAccount == -1)  {
            params = idModel +","+ idaccount;
            idModelByAccount = LibSQL.getInt("accounting.addAccountByModel",params);
        } else  {
            params = idModelByAccount +","+ idModel +","+ idaccount;
            idModelByAccount = LibSQL.getInt("accounting.setAccountByModel",params);
        }
        return idModelByAccount;
    }
    
}
